/**
 * $Log: MutableASCIIField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.2  2007/11/09 07:10:50  wangl
 * 加入适当的注释
 *
 * Revision 1.1  2007/11/02 07:16:55  wangl
 * 添加新的重构後的代码
 *
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @brief
 * <p>
 * <b>变长的ASCII字符字段处理</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;变长的ASCII字符字段处理
 * 
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-9-6
 */

public class MutableASCIIField extends MutableField {

	public MutableASCIIField(int bit, String data_name, int length,boolean optional, String msgAttributeName, int wide,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, wide,isMac);
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {

		String value = (String) getAttributeValue(msg);
		packLog.append("["+this.bit+"] ["+value+"]"+"\n");
		if (value == null) {
			if (isRequired()) {
				return false;
			}
			return false;
		} else {
			try {
				byte[] b = value.getBytes("GBK");
				this.length = b.length;
				String lens = writeLengthValue(out);
				out.write(b);
				if(this.isMac() == true){
					macList.add(lens+value);
				}
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

		}

	}


	
	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		readLengthValue(in);
		byte[] value = new byte[this.length];
		try {
			in.read(value,0,value.length);
			setAttributeValue(msg,new String(value));
			retLog.append("["+this.bit+"] ["+new String(value,"GBK")+"]"+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
