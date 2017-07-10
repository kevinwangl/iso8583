
/**
 * $Log: ISO8583Message.java,v $
 * Revision 1.4  2008/01/28 02:10:47  wangl
 * 增加参数上送的调用
 *
 * Revision 1.3  2007/11/22 06:05:22  liaok
 * 增加TPDU字段并实现对其的赋值
 *
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 *
 */




/**
 *	@brief
 *   <p><b>ISO8583包解析</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp;ISO8583包
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-9-6
 */


public class ISO8583Message {
	
	
	byte[] msgData;
	
	byte[] tpdu = new byte[5];
	
	byte[] head = new byte[6];
	
	/**
	 * @brief 把接受到的数据封装为Iso8583包对象
	 * 
	 * @param data
	 * @return
	 */
	public ISO8583Message(byte[] revdata) {
		this.msgData = revdata;
	}
	
	public ISO8583Message(byte[] data,byte[] tpdu) {
		this.msgData = data;
		this.tpdu = tpdu;
	}
	
	public ISO8583Message() {
		// TODO 自动生成构造函数存根
	}

	/**
	 * @brief
	 * 当前包的MsgID
	 * 
	 * @return
	 */
	public String getMsgId(){
		byte[] tmp = new byte[2];
		System.arraycopy(msgData, 0, tmp, 0, 2);
		return byte2hex(tmp);
	}
	
	private String byte2hex(byte[] b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] out = new char[b.length * 2];
		for (int i = 0; i < b.length; i++) {
			byte c = b[i];
			out[i * 2] = Digit[(c >>> 4) & 0X0F];
			out[i * 2 + 1] = Digit[c & 0X0F];
		}
		return new String(out);
	}
	
	/**
	 * @return 
	 * @brief
	 * 获得byte数据
	 * 
	 */
	public byte[] getBytes() {
		return msgData;
	}
	
	

	public void setBytes(byte[] b) {
			this.msgData = b;
	}
	
	public String getTPDU() {
		return ISO8583Util.compressedBCD2String(head);//byte2hex(head);
	}
	
	



}
