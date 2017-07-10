/**
 * $Log: ASCIIField.java,v $
 * Revision 1.3  2007/11/28 11:34:48  wangl
 * 修改错误
 *
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.3  2007/11/09 07:10:49  wangl
 * 加入适当的注释
 *
 * Revision 1.2  2007/11/06 09:26:48  wangl
 * 修改对齐方式写错的地方
 *
 * Revision 1.1  2007/11/02 07:16:55  wangl
 * 添加新的重构後的代码
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * @brief
 * <p>
 * <b>字符字段的处理</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;字符域
 * 
 * @author wangl
 * @see
 * @since 2007-9-6
 */

public class ASCIIField extends AField {

	private static final char STUFF = ' ';
	private static final char STUFF0 = '0';

	public ASCIIField(int bit, String data_name, int length, boolean optional,String msgAttributeName,boolean isMac) {
		this(bit, data_name, length, optional, msgAttributeName,ISO8583Util.SUFFIX,isMac);
		// TODO 自动生成构造函数存根
	}
	
	public ASCIIField(int bit, String data_name, int length, boolean optional,String msgAttributeName,int align,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName,align,isMac);
		// TODO 自动生成构造函数存根
	}
	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		String valueTemp = (String) getAttributeValue(msg);
		packLog.append("["+this.bit+"] ["+valueTemp+"]"+"\n");
		if (valueTemp == null) {
			if (isRequired()) {
				return false;
			}
			return false;
		} else {
			String value = "";
			if(getBit() == 4){
				value = ISO8583Util.format(valueTemp,this.length,getAlign(),STUFF0);
			}else{
				value = ISO8583Util.format(valueTemp,this.length,getAlign(),STUFF);
			}
			try {
				String v = value.substring(0,this.length);
				out.write(v.getBytes("GBK"));
				if(this.isMac() == true){
					macList.add(v);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	
	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		byte[] value = new byte[getLength()];
		try {
			in.read(value);
			setAttributeValue(msg, new String(value));
			retLog.append("["+this.bit+"] ["+new String(value,"GBK")+"]"+"\n");
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
