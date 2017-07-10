import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @brief
 * <p>
 * <b>ISO8583数据包相关工具类</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;将数据转换为bcd和反转bcd为字符串相关功能
 * 
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-7-6
 */
public class ISO8583Util {
	public static final int SUFFIX = 1; // 后面补位

	public static final int PREFIX = 2; // 前面补位

	/**
	 * 
	 * 把整数转换成字节数组中指定偏移量处的连续四个字节。没有符号限制。
	 * 
	 * @param num
	 *            进行转换int类型
	 * @param datas
	 *            转换后放入的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return null
	 * @throws null
	 */

	public static void IntToByteArray(int num, byte[] datas, int offset) {

		datas[offset] = (byte) (num >> 24);

		datas[offset + 1] = (byte) (num >> 16);

		datas[offset + 2] = (byte) (num >> 8);

		datas[offset + 3] = (byte) (num);

	}

	/**
	 * 将bcd码转换为字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String compressedBCD2String(byte[] b) {
		StringBuffer buffer = new StringBuffer();
		byte bit = 0;
		for (int i = 0; i < b.length; i++) {
			bit = (byte) ((b[i] >> 4) & 0xf);
			buffer.append(Integer.toHexString(bit));
			bit = (byte) ((b[i]) & 0xf);
			buffer.append(Integer.toHexString(bit));
		}
		return buffer.toString();
	}

	/**
	 * @brief 将两个压缩式BCD码转换为int
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int BCD2Integer(byte a, byte b) {
		return (((a >> 4) & 0x0f) * 1000) + (((a) & 0x0f) * 100)
				+ (((b >> 4) & 0x0f) * 10) + ((b) & 0x0f);
	}

	/**
	 * 构造bcd形式的字节数组,如"123456"会转化为一个字节数组,0x12,0x34,0x56
	 * 
	 * @param pin
	 *            进行转换的数组
	 * @mode 补零的模式 1为补零到后面，2位补零到前面
	 * @return 转换后的BCD数组
	 * @throws null
	 */
	public static byte[] createBCDByteArray(String pin, int mode) {
		if ((pin.length() % 2) != 0) {
			if (mode == ISO8583Util.PREFIX) {
				pin = "0" + pin;
			}else{
				pin = pin + "0";
			}
		}

		byte[] pinArray = pin.getBytes();
		for (int i = 0; i < pinArray.length; i++) {
			pinArray[i] -= '0';
		}
		byte[] bcdPin = getCompressedBCDArray(pinArray,SUFFIX);
		return bcdPin;
	}

	/**
	 * 非压缩式转化为压缩式 得到压缩式bcd码的字节数组
	 * 
	 * @param src
	 *            要转化的非压缩式字节数组
	 * @return
	 */
	public static byte[] getCompressedBCDArray(byte[] src,int mode) {
		
		int desLength = 0;
		if (src.length % 2 == 0) {
			desLength = src.length ;
		}else{
			desLength = src.length+1;
		}
		byte[] desBytes = format(src,desLength,mode,(byte)0);
		byte[] des = new byte[desLength/2];
		for (int i = 0; i < des.length; i++) {
			des[i] = (byte) (desBytes[2 * i] << 4 | desBytes[2 * i + 1]);
		}
		
		return des;
	}

	/**
	 * 
	 * @brief 校验
	 * 
	 * @param data
	 * @param length
	 * @return
	 */
	public static byte getCheckSumBit(byte[] data, int length) {
		byte sumBit = 0;
		for (int i = 0; i < length; i++) {
			sumBit ^= data[i];
		}
		return sumBit;
	}

	/**
	 * 将字节数组中某位置1
	 * 
	 * @param index
	 */
	public static byte[] setBit(byte[] bytes, int index)// 设置BIT MAP的第index位
	{
		int size = 8;
		int i = index / size;// 先看是哪个byte
		int j = index % size;// 再看是这个byte的第几位
		int mask = 0x80;
		if (j == 0) {// 如果刚好整除8，则要退一位
			j = size;
			i = i - 1;
		}

		mask >>= j - 1;// 右移j-1位作为或数；
		bytes[i] |= mask;// 作按位或，相当于置1
		return bytes;
	}
	
	

	/**
	 * 判断该域是否有效
	 * 
	 * @param index
	 * @return
	 */
	public static boolean validateField(byte[] bytes, int index) {
		if (index > 128 || index < 1)
			return false;
		int i = index / 8;// 先看是哪个byte
		int j = index % 8;// 再看是这个byte的第几位
		byte b = bytes[i];// 取出这个bitmap的字节来判断
		b <<= j;// 左移n位，使要判断的这位升到第一位
		byte mask = (byte) 0x80;
		return (b & mask) == mask;// 判断左移后的值是否大于128，即该位是否为1

	}

	/**
	 * 格式化字符串
	 * 
	 * @param inStr
	 *            输入字符串
	 * @param length
	 *            格式化长度
	 * @param position
	 *            对齐位置
	 * @param stuffing
	 *            填充字符
	 * @return 经过格式化后的字符串
	 */
	public static String format(String inStr, int length, int position,
			char stuffing) {
		if (inStr == null) {
			inStr = "";
		}
		if (inStr.length() >= length) {
			return inStr;
		}
		int blankSize = length - inStr.length();
		StringBuffer out = new StringBuffer(blankSize);
		for (int i = 0; i < blankSize; i++) {
			out.append(stuffing);
		}

		switch (position) {
		case SUFFIX: {
			out.insert(0, inStr);
			break;
		}
		case PREFIX:
			out.append(inStr);
			break;
		default: {
			out.insert(0, inStr);
			break;
		}
		}
		return out.toString();
	}
	
	

	
	

	/**
	 * 格式化字符串
	 * 
	 * @param inBytes
	 *            输入byte数组
	 * @param length
	 *            格式化长度
	 * @param position
	 *            对齐位置
	 * @param stuffing
	 *            填充byte
	 * @return 经过格式化后的byte数组
	 */
	public static byte[] format(byte[] inBytes, int length, int position,
			byte stuffing) {
		if (inBytes == null) {
			return null;
		}
		if (inBytes.length >= length) {
			return inBytes;
		}
		int blankSize = length - inBytes.length;
		byte[] out = new byte[blankSize + inBytes.length];
		for (int i = 0; i < blankSize; i++) {
			out[i] = (stuffing);
		}

		switch (position) {
		case SUFFIX: {
			System.arraycopy(inBytes, 0, out, 0, inBytes.length);
			break;
		}
		case PREFIX:
		default: {
			System.arraycopy(inBytes, 0, out, blankSize, inBytes.length);
		}
		}
		return out;
	}

	/**
	 * 生成bitMap的List
	 * 
	 * @param bitMap
	 * @return
	 */
	public static List generateBitMapIndex(byte[] bitMap) {
		ArrayList list = new ArrayList();
		StringBuffer buf = new StringBuffer();
		byte b = 0x0;
		for (int i = 0; i < 16; i++) {
			b = bitMap[i];// 从位图中取1 byte
			int mask = 0x80;
			for (int j = 0; j < 8; j++) {
				if ((b & mask) == mask)// 如果该位是1
				{
					int index = i * 8 + j + 1;
					if (index >= 0) {
						list.add(new Integer(index));
					}
					buf.append("1");
				}else {
					buf.append("0");
				}
				mask >>= 1;
			}
		}
		return list;
	}

	/**
	 * 判断这个域是否存在
	 * 
	 * @param index
	 * @return
	 */
	public boolean isExistIndex(byte[] bitMap, int index) {
		List list = generateBitMapIndex(bitMap);
		if (list.contains(new Integer(index))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * 将字符串数据转化为非压缩式BCD数组
	 * 
	 * @param pin
	 * @return
	 */
	public static byte[] createNotCompressedBCDArray(String pin) {
		if (pin != null) {
			byte[] b = pin.getBytes();
			for (int i = 0; i < b.length; i++) {
				b[i] -= '0';
			}
			return b;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		byte[] b = createNotCompressedBCDArray("19");
	}

	/**
	 * 
	 * 
	 * 将压缩式字节数组转化为非压缩式字节数组
	 * 
	 * @param src
	 *            要转化的压缩式的字节数组
	 * @return
	 */
	public static byte[] getNotCompressedBCDArray(byte[] src) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < src.length; i++) {
			byte desHigh = (byte) ((src[i] >> 4) & 0x0F);
			byte desLow = (byte) (src[i] & 0x0F);
			out.write(desHigh);
			out.write(desLow);
		}
		return out.toByteArray();
	}

	/**
	 * 
	 * 
	 * 将非压缩式BCD码转化为字符串
	 * 
	 * @param value
	 *            要转化的BCD码
	 * @return
	 */
	public static String notCompressedBCD2String(byte[] value) {
		// TODO 自动生成方法存根

		StringBuffer buffer = new StringBuffer();
		int des = 0;
		for (int i = 0; i < value.length; i++) {
			des = value[i] & 0x0F;
			buffer.append(des);
		}
		return buffer.toString();
	}
}
