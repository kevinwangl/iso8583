import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.unionpay.common.MacEncryptdllUtils;
import com.unionpay.common.iso8583.DataConverter;
import com.zj.core.log.SnakeLogger;
import com.zj.core.web.SnakePlatform;
import com.zj.qcup.QCUP;
/**
 * 
 * @brief
 * <p>
 * <b>ISO8583数据解析类</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;负责打包成ISO8583数据包和解包成ISO8583数据的64个域,
 * 要打包的数据不包括协议部分和TPDU数据部分之用消息类型，位图和其他域
 * 
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-7-6
 */
public class ISO8583Parser {
	
	private  SnakeLogger logger = SnakePlatform.getLogger(QCUP.class);

	public ISO8583Parser() {
		
	}

	/**
	 * 
	 * @param eftMessage    包数据
	 * @param msgStructure  包结构
	 * @param sek			SEK索引
	 * @param mak			MAK密钥密文
	 * @param isTest		是否使用软加密
	 * @return
	 */
	public  ISO8583Message pack(EFTMessage eftMessage,AField msgStructure[], String sek,String mak, boolean isUsedSoftEncr,String tmpKey,String tmpMacKey,String interfaceName,boolean isChannel) {
		StringBuffer sendLog = new StringBuffer();
		sendLog.append("\n");
		sendLog.append("============================================================================================");
		sendLog.append("\n\n");
		sendLog.append(">>发送的报文	:	"+interfaceName+"\n");
		sendLog.append(">>域信息"+"\n");
		byte[] bitMap = new byte[16];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String msgTYPE = eftMessage.getMsgTypeID();
		sendLog.append("[1] "+msgTYPE+"\n");
		byte[] msgID = null;
		List<String> macList = new ArrayList<String>();
		//消息类型
		if(msgTYPE != null && !"".equals(msgTYPE)){
			msgID = msgTYPE.getBytes();
			ISO8583Util.setBit(bitMap, 1);
		}else{
			return null;
		}
		try {
			out.write(msgID);
			out.write(bitMap);
			macList.add(msgTYPE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//写域数据
		boolean hasMac = false;
		for (int i = 0; i < msgStructure.length; i++) {
			AField field = msgStructure[i];
			if (field.getBit() == 128) {
				ISO8583Util.setBit(bitMap, field.getBit());
				hasMac = true;
			} else if (field.pack(eftMessage, out,macList,sendLog)) {
				if(field.getBit() == 90){
					macList.set(macList.size()-1, macList.get(macList.size()-1).substring(0,20));
				}
				ISO8583Util.setBit(bitMap, field.getBit());
			}else{
				logger.error("域出错：#0 #1",String.valueOf(field.getBit()),field.getFieldName());
				return null;
			}
		}
		
		//组合报文
		byte[] byteArray = out.toByteArray();
		System.arraycopy(bitMap, 0, byteArray, msgID.length, bitMap.length);
		byte[] macField = null;
		ISO8583Message outMessage;
		if (hasMac) {
			//计算第128域Mac
			if(isChannel){
				String mac = genMac(macList);
				if(isUsedSoftEncr){
					String mossStr = DataConverter.stringToHexString(mac);
					String themac = MacEncryptdllUtils.calcMAC(mossStr,mossStr.length(), 0, tmpKey, tmpMacKey).substring(0,8);
					macField = themac.getBytes();
				}else{
					macField = EncryptionMachineUtil.mac(sek, mak, mac).getBytes();
				}
			}else{
				String mossStr = DataConverter.bytesToHexString(byteArray);
				String themac = MacEncryptdllUtils.calcMAC(mossStr,mossStr.length(), 0, tmpKey, tmpMacKey);
				System.out.println("mac str:" + mossStr);
				System.out.println("master Key:" + tmpKey);
				System.out.println("tmpMacKey:" + tmpMacKey);
				System.out.println("themac:" + themac);
				macField = ArrayUtil.hexDecode(themac);
//				macField = themac.getBytes();
				System.out.println("128:::::::::::::" + ArrayUtil.hexEncode(macField));
			}
			byte[] byteArrayHasMac = new byte[byteArray.length+ macField.length];
			System.arraycopy(byteArray, 0, byteArrayHasMac, 0,byteArray.length);
			System.arraycopy(macField, 0, byteArrayHasMac, byteArray.length,macField.length);
			//计算报文总长度
			byte[] byteArrayHasLength = new byte[byteArrayHasMac.length+4];
			byte[] bl = String.format("%04d", byteArrayHasMac.length).getBytes();
			System.arraycopy(bl, 0, byteArrayHasLength, 0,4);
			System.arraycopy(byteArrayHasMac, 0, byteArrayHasLength, 4,byteArrayHasMac.length);
			outMessage = new ISO8583Message(byteArrayHasLength,null);
		} else {
			//计算报文总长度
			byte[] byteArrayHasLength = new byte[byteArray.length+4];
			byte[] bl = String.format("%04d", byteArray.length).getBytes();
			System.arraycopy(bl, 0, byteArrayHasLength, 0,4);
			System.arraycopy(byteArray, 0, byteArrayHasLength, 4,byteArray.length);
			outMessage = new ISO8583Message(byteArrayHasLength,null);
		}
		
		logger.info(sendLog.toString());
		//输出
		return outMessage;

	}

	/**
	 * 解包数据
	 * 
	 * @param receiveData
	 * @param msgStructure
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public  EFTMessage unpack(ISO8583Message receiveData,AField msgStructure[],String interfaceName) throws Exception {
		StringBuffer retLog = new StringBuffer();
		retLog.append("\n");
		retLog.append("============================================================================================");
		retLog.append("\n\n");
		retLog.append(">>收到的报文	:	"+interfaceName+"\n");
		retLog.append(">>域信息"+"\n");
		
		ByteArrayInputStream in = new ByteArrayInputStream(receiveData.getBytes());
		EFTMessage msg = new EFTMessage();
		byte[] packLen = new byte[4];
		byte[] msgID = new byte[4];
		byte[] bitMap = new byte[16];
		in.read(packLen);
		in.read(msgID);
		in.read(bitMap);
		msg.setMsgTypeID(new String(msgID));
		retLog.append("[1] "+new String(msgID)+"\n");
		ArrayList list = (ArrayList) ISO8583Util.generateBitMapIndex(bitMap);
		for (int i = 0; i < list.size(); i++) {
			int index = ((Integer) list.get(i)).intValue();
			if(index == 1){
				continue;
			}
			AField f = searchField(index, msgStructure,MessageStructure.ExcessTable);
			try {
				f.unpack(in, msg, retLog);
			} catch (Exception e) {
				logger.warn("解析域[#0]失败:#1", String.valueOf(index), e.getMessage());
			}
		}
		logger.info(retLog.toString());
		return msg;

	}
	/**
	 * 
	 * 
	 * 查找到唯一一个字段
	 * 
	 * @param index
	 * @param msgStructure
	 * @param excessTable
	 * @return
	 */
	private static AField searchField(int index, AField[] msgStructure,AField[] excessTable) {
		AField f = null;
		f = getExistsField(index, msgStructure);
		if (f != null) {
			return f;
		} else {
			f = getExistsField(index, excessTable);
			return f;
		}
	}
	
	
	/**
	 * 
	 * 
	 * 得到存在的域的字段
	 * 
	 * @param index
	 * @param fields
	 * @return
	 */
	private static AField getExistsField(int index, AField[] fields) {
		if (fields == null) {
			return null;
		} else {
			for (int i = 0; i < fields.length; i++) {
				if (index == fields[i].getBit()) {
					return fields[i];
				} 
			}
			return null;
		}

	}
	
	
	/**
	 * 
	 * 
	 * mac校验
	 * 
	 * @param data
	 * @param mak
	 * @return
	 */
	public static byte[] encryptionMac(final byte[] data, byte[] mak) {
		byte[] ret = Encrypt.ECB(data, mak);
		return ret;
	}
	
	/**
	 * 渠道mac域选择
	 * @return
	 */
	public static String genMac(List<String> macList){
		String space = " ";
		String str = "";
		for (int i = 0; i < macList.size(); i++) {
			String field = macList.get(i).trim(); //删去所有域的起始空格和结尾空格
			field = field.toUpperCase();	      //所有的小写字母转换成大写字母
			field = field.replaceAll("^([a-zA-Z_0-9],.)", ""); //除了字母(A-Z)，数字(0-9)，空格，逗号(，)和点号(.)以外的字符都删去
			if(i != macList.size()){
				field = field + space;	//在域和域之间插入一个空格
			}
			str = str + field;
		}
		str = str.replace(" {2,}", space);  //多于一个的连续空格，由一个空格代替
		return str.trim();
	}
	
	
	/**
	 * 后补空格
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addSpace(String str, int strLength) {
		int strLen = strLength - str.getBytes().length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strLen; i++) {
			sb.append(" ");
		}
		return str + sb.toString();
	}
	
	/**
	 * 左补0
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addZERO(String str, int strLength) {
		String s = str;
		int strLen = strLength - str.length();
		for (int i = 0; i < strLen; i++) {
			s = "0"+s;
		}
		return s;
	}


	

}
