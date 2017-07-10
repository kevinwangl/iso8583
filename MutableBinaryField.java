/**
 * $Log: MutableBinaryField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.2  2007/11/09 07:10:50  wangl
 * 加入适当的注释
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
 * <b>变长的二进制字段的处理</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;变长的二进制字段的处理
 * 
 * <p>
 * </p>
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-10-26
 */

public class MutableBinaryField extends MutableField {

	public MutableBinaryField(int bit, String data_name, int length,
			boolean optional, String msgAttributeName, int wide,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, wide,isMac);
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		byte[] value = (byte[]) getAttributeValue(msg);
		if (value == null) {
			if (isRequired()) {
				System.out.println("必选域但是EFTMessage数据为空:" + getFieldName());
			}
			return false;
		} else {
			try {
				this.length = value.length;
				writeLengthValue(out);
				out.write(value);
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
			setAttributeValue(msg,value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
