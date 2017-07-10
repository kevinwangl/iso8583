
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * <p>
 * <b> 数据转换器 </b>
 * </p>
 * 
 * 将各种数据互相转换
 * 
 * <p>
 * </p>
 * 
 */
public class ArrayUtil {
	int i;

	/**
	 * 检查传入数据的最后一个字节是否是正确的校验位
	 * 
	 * @param data
	 *            进行校验的数组
	 * @return 校验是否正确
	 * @throws null
	 */
	public static boolean isCheckRight(byte[] data) {
		// 现在由于同本地接口有问题,暂不实现
		return true;
	}

	/**
	 * 在传入的数组后加上校验位
	 * 
	 * @param data
	 *            传入的数组
	 * @return 加入校验位的数组
	 * @throws null
	 */
	public static byte[] checkSum(byte[] data) {
		byte[] ret = new byte[data.length + 1];
		System.arraycopy(data, 0, ret, 0, data.length);
		ret[data.length] = checkSum(data, data.length);
		return ret;
	}

	/**
	 * 取得奇偶校验位
	 * 
	 * @param data
	 * @return
	 */
	public static byte getParityBit(byte[] data, int length) {
		return checkSum(data, length);
	}

	/**
	 * 测试剪切数组的一部分。
	 * 
	 * @param null
	 * @return null
	 * @throws null
	 */
	public static void testCutArray() {
		byte[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		byte[] a;
		int offset = 0;
		int maxLength = test.length;
		for (; offset < maxLength; offset++) {
			int length = 0;
			for (; offset + length <= maxLength; length++) {
				a = cutArray(test, offset, length);
				list(a, "contents");
			}
		}
	}

	/**
	 * 指定偏移量处的两个字节组成整数。
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的int型
	 * @throws null
	 */
	public static int TwoByteToInt(byte[] datas, int offset) {
		int i1 = datas[offset], i2 = datas[offset + 1];
		if (i1 < 0)
			i1 += 256;
		i1 = i1 << 8;
		if (i2 < 0)
			i2 += 256;
		return i1 + i2;
	}

	/**
	 * 把字节数组指定偏移处的连续四个字节组成整数，对于单个字节，没有符号。
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的int型
	 * @throws null
	 */
	public static int ByteArrayToInt(byte[] datas, int offset) {
		int i1 = datas[offset], i2 = datas[offset + 1], i3 = datas[offset + 2], i4 = datas[offset + 3];
		if (i1 < 0)
			i1 += 256;
		i1 = i1 << 24;
		if (i2 < 0)
			i2 += 256;
		i2 = i2 << 16;
		if (i3 < 0)
			i3 += 256;
		i3 = i3 << 8;
		if (i4 < 0)
			i4 += 256;
		return (i1 + i2 + i3 + i4);
	}

	/**
	 * 4个字节组成的日期，由于可预见的日期不可能造成符号问题。所以单独列出来。
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的int型
	 * @throws null
	 */
	public static int ByteArrayToBCDDate(byte[] datas, int offset) {
		return ByteArrayToInt(datas, offset);
	}

	/**
	 * 将指定的一个字节转化为无符号int
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的int型
	 * @throws null
	 */
	public static int oneByteToUnsignedInt(byte[] datas, int offset) {
		int i3 = datas[offset];
		if (i3 < 0) {
			i3 += 256;
		}
		return i3;
	}

	/**
	 * 
	 * 把字节数组指定偏移处的连续两个字节组成short，对于单个字节，没有符号。
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的short型
	 * @throws null
	 */
	public static short ByteArrayToShort(byte[] datas, int offset) {
		int i3 = datas[offset], i4 = datas[offset + 1];
		if (i3 < 0)
			i3 += 256;
		i3 = i3 << 8;
		if (i4 < 0)
			i4 += 256;
		return (short) (i3 + i4);
	}

	/**
	 * 将指定偏移处的两个字节转化为int
	 * 
	 * @param datas
	 *            进行转换的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return 转换后的int型
	 * @throws null
	 */
	public static int ByteArrayToUnsignedShort(byte[] datas, int offset) {
		int i3 = datas[offset], i4 = datas[offset + 1];
		if (i3 < 0)
			i3 += 256;
		i3 = i3 << 8;
		if (i4 < 0)
			i4 += 256;
		return (i3 + i4);
	}

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
	 * 把short整数转换成字节数组中指定偏移量处的连续2个字节。没有符号限制。
	 * 
	 * @param num
	 *            进行转换short类型
	 * @param datas
	 *            转换后放入的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return null
	 * @throws null
	 */
	public static void ShortToByteArray(short num, byte[] datas, int offset) {
		datas[offset] = (byte) (num >> 8);
		datas[offset + 1] = (byte) (num);
	}

	/**
	 * 把long整数转换成字节数组中指定偏移量处的连续4个字节。没有符号限制。
	 * 
	 * @param num
	 *            进行转换long类型
	 * @param datas
	 *            转换后放入的缓冲
	 * @param offset
	 *            缓冲的偏移点
	 * @return null
	 * @throws null
	 */
	public static void LongToByteArray(long num, byte[] datas, int offset) {
		IntToByteArray((int) (num >> 32), datas, offset);
		IntToByteArray((int) num, datas, offset + 4);
	}

	/**
	 * 比较两个字节数组在指定偏移位置上，制定长度范围内是否一样。
	 * 
	 * 鉴于使用的地方都是判断是否相等，而且在税控盒中得到第一个不等的位置没有意义，所以把返回值更改为相等返回0，不等返回-1
	 * 
	 * @param src
	 *            源缓冲
	 * @param srcoffset
	 *            源缓冲的偏移
	 * @param des
	 *            目的缓冲
	 * @param dstoffset
	 *            目的缓冲的偏移
	 * @param length
	 *            比较的数据长度
	 * @return 返回 0 说明一致，否则返回-1
	 * @throws null
	 */
	public static int ByteArrayCmp(byte[] src, int srcoffset, byte[] dst,
			int dstoffset, int length) {
		if (src == null || dst == null)
			return -1;
		if (srcoffset + length > src.length)
			return -1;
		if (dstoffset + length > dst.length)
			return -1;
		int looper;
		for (looper = 0; looper < length; looper++) {
			if (src[srcoffset + looper] != dst[dstoffset + looper])
				return -1;
		}
		return 0;
	}

	/**
	 * 比较两个无符号字节数组，指定长度范围内是否一样。相当于C中的 memcmp
	 * 
	 * @param src
	 *            源缓冲
	 * @param des
	 *            目的缓冲
	 * @param length
	 *            比较的数据长度
	 * @return 返回 0 说明一致， > 0 说明src 大于 des < 0 说明src 濒于 des
	 * @throws null
	 */

	public static int ByteArrayCmp(byte[] src, byte[] dst, int length) {

		if (src == null || dst == null)

			return -1;

		int looper;
		int source, target;

		for (looper = 0; looper < length; looper++) {

			source = src[looper];
			target = dst[looper];
			source &= 0xff;
			target &= 0xff;

			if (source > target)
				return 1;
			else if (source < target)
				return -1;

		}

		return 0;

	}

	public static void ZeroByteArray(byte[] datas, int offset, int length) {
		for (int looper = 0; looper < length; looper++)
			datas[offset + looper] = 0x0;
	}

	/**
	 * 显示一个数组的内容 用16进制显示,无符号
	 * 
	 * @param src
	 *            进行显示的数组
	 * @param name
	 *            显示名称
	 * @return null
	 * @throws null
	 */
	public static void list(byte[] src, String name) {
		// TODO
	}

	/**
	 * 将源数组中的指定offset开始的length个字节去掉,返回新的数组
	 * 
	 * @param source
	 *            数据缓冲
	 * @param offset
	 *            缓冲偏移
	 * @param length
	 *            处理数据长度
	 * @return 处理后的数组
	 * @throws null
	 */
	public static byte[] cutArray(byte[] source, int offset, int length) {
		// 测试参数
		if (length == 0) {
			byte[] ret = new byte[source.length];
			System.arraycopy(source, 0, ret, 0, source.length);
			return ret;
		}
		int i = source[offset] + source[length - 1]
				+ source[offset + length - 1];
		int l = source.length;
		byte[] ret = new byte[l - length];
		System.arraycopy(source, 0, ret, 0, offset); // 拷贝前offset个字节,测试offset为0的情况!
		if ((offset + length) <= l - 1) {
			System.arraycopy(source, offset + length, ret, offset, l - length
					- offset); // 拷贝余下的字符
		}
		return ret;
	}

	/**
	 * HEX编码 将形如0x12 0x2A 0x01 转换为122A01
	 * @param data
	 * @return
	 */
	public static String hexEncode(byte[] data) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String tmp = Integer.toHexString(data[i]&0xff);
			if (tmp.length() < 2) {
				buffer.append('0');
			}
			buffer.append(tmp);
		}
		String retStr = buffer.toString().toUpperCase();
		return retStr;
	}

	/**
	 * HEX解码 将形如122A01 转换为0x12 0x2A 0x01
	 * @param data
	 * @return
	 */
	public static byte[] hexDecode(String data) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < data.length(); i+=2) {
			String onebyte = data.substring(i,i+2);
			int b = Integer.parseInt(onebyte,16) & 0xff;
			out.write(b);
		}
		return out.toByteArray();
	}

	/**
	 * 判断给定的数组是否全为给定的字节
	 * 
	 * @param src
	 *            进行比较的缓冲
	 * @param toCompare
	 *            进行比较的字符
	 * @return true: 全是 false:不是
	 * @throws null
	 */
	public static boolean ifArrayEqual(byte[] src, byte toCompare) {
		for (int i = 0; i < src.length; i++) {
			if (src[i] != toCompare) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断给定数组的指定范围内数据是否等于给定的字节
	 * 
	 * @param src
	 *            进行比较的缓冲
	 * @param offset
	 *            缓冲偏移
	 * @param length
	 *            比较长度
	 * @param toCompare
	 *            进行比较的字符
	 * @return 是否相同
	 * @throws null
	 */
	public static boolean ifArrayEqual(byte[] src, int offset, int length,
			byte toCompare) {
		for (int i = 0; i < length; i++) {
			if (src[offset + i] != toCompare) {
				return false;
			}
		}
		return true;
	}

	// /**
	// * 将Unicode码转换成byte数组
	// *
	// * @param chineseName 要进行转换的文件名称
	// * @return 转换后的byte数组
	// * @throws null
	// */
	// public static byte[] UnicodeToByteArray(String chineseName) {
	// return Native.UnicodeToByteArray(chineseName);
	// }
//	/**
//	 * 将传入的字节数组转化为最大行字节数j的多个字符串数组
//	 * 
//	 * @param electronicSign
//	 *            进行转换的数据缓冲
//	 * @param j
//	 *            转换后的每个数组最大字符个数
//	 * @return 转换后的数组
//	 * @throws null
//	 */
//	public static String[] toMultipleString(byte[] electronicSign, int j) {
//		int totalLength = electronicSign.length;
//		int completeRow = totalLength / j; // 整行的行数
//		int left = totalLength % j; // 余下不足一行的字节数
//		int totalRow = completeRow;
//		if (left != 0) {
//			totalRow++;
//		}
//		if (totalRow == 0) {
//			return new String[0];
//		}
//		String[] ret = new String[totalRow];
//		for (int i = 0; i < completeRow; i++) {
//			ret[i] = ArrayUtil.byteArrayToStringWithSpace(electronicSign,
//					i * j, j);
//		}
//		if (left != 0) {
//			ret[completeRow] = ArrayUtil.byteArrayToStringWithSpace(
//					electronicSign, completeRow * j, left);
//		}
//		return ret;
//	}

	/**
	 * 将两个字节数组合并为新的字节数组
	 * 
	 * @param bs
	 *            进行合并的数组1
	 * @param bs2
	 *            进行合并的数组2
	 * @return 合并后的数组
	 * @throws null
	 */
	public static byte[] merg(byte[] bs, byte[] bs2) {
		byte[] ret = new byte[bs.length + bs2.length];
		System.arraycopy(bs, 0, ret, 0, bs.length);
		System.arraycopy(bs2, 0, ret, bs.length, bs2.length);
		return ret;
	}

	/**
	 * 将src数组的数转化为BCD码数组des.des的长度是src的一半.且src必须为偶数个数 比如123456转化为0x123456;
	 * 
	 * @param src
	 *            进行转换的数组
	 * @return 转换后的BCD码数组
	 * @throws null
	 * 
	 */
	public static byte[] getBCDArray(byte[] src) {
		if (src.length % 2 != 0)
			throw new IllegalArgumentException("the length of src is wrong:"
					+ src.length);
		int desLength = src.length / 2;
		byte[] des = new byte[desLength];
		for (int i = 0; i < desLength; i++) {
			des[i] = (byte) (src[2 * i] << 4 | src[2 * i + 1]);
		}
		return des;
	}

	/**
	 * 构造bcd形式的字节数组,如"123456"会转化为一个字节数组,0x12,0x34,0x56
	 * 
	 * @param pin
	 *            进行转换的数组
	 * @return 转换后的BCD数组
	 * @throws null
	 */
	public static byte[] createBCDByteArray(String pin) {
		if (pin.length() % 2 == 1) {
			pin = "0" + pin;
		}
		byte[] pinArray = pin.getBytes();
		for (int i = 0; i < pinArray.length; i++) {
			pinArray[i] -= '0';
		}
		byte[] bcdPin = ArrayUtil.getBCDArray(pinArray);
		return bcdPin;
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
	 * 将压缩式BCD数组转换为int
	 * 
	 * @param b
	 * @return
	 */
	public static int BCDArray2Integer(byte[] b) {
		int wei = b.length * 2 - 1;
		int ret = 0;
		for (int i = 0; i < b.length; i++) {
			int jie = 1;
			for (int j = 0; j < wei - 1; j++) {
				jie *= 10;
			}
			ret += (((b[i]>> 4) & 0x0f) * jie *10) + (((b[i]) & 0x0f) * (jie));
			wei -= 2;
		}
		return ret;
	}
	
	/**
	 * 将int转换为压缩式BCD数组
	 * 
	 */
	public static byte[] Integer2BCDArray(int i,int retLength) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(i);
		int tmp = retLength * 2;
		while (buffer.length() < tmp) {
			buffer.insert(0,'0');
		}
		
		return createBCDByteArray(buffer.toString());
	}

	/**
	 * 
	 * @brief 返回源数组的clone
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] cloneBytes(byte[] src) {
		byte[] ret = new byte[src.length];
		System.arraycopy(src, 0, ret, 0, src.length);
		return ret;
	}

	// private static native String GBByteArrayToUnicodeString(byte[] datas,int
	// offset, int length);
	public static byte checkSum(byte[] data, int length) {
		byte sumBit = 0;
		for (int i = 0; i < length; i++) {
			sumBit ^= data[i];
		}
		return sumBit;
	}

	/**
	 * 
	 * @brief 在传入的数组中填充上随机数
	 * 
	 * @param toFill
	 */
	public static void fillWithRandom(byte[] toFill) {
		Random random = new Random();
		for (int i = 0; i < toFill.length; i++) {
			toFill[i] = (byte) random.nextInt();
		}
	}
}
