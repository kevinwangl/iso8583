/**
 * $Log: BinaryField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.3  2007/11/06 09:26:48  wangl
 * 修改对齐方式写错的地方
 *
 * Revision 1.2  2007/11/02 07:16:55  wangl
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
 * <b>二进制域</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;二进制域
 * 
 * <p>
 * </p>
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-9-18
 */

public class BinaryField extends AField {

	private static final byte STUFF = (byte)0x00;

	public BinaryField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName,boolean isMac) {
		this(bit, data_name, length, optional, msgAttributeName,ISO8583Util.SUFFIX,isMac);
	}

	public BinaryField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName, int align,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, align,isMac);
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		int len = getLength();
		byte[] valueTemp = (byte[]) getAttributeValue(msg);
		if (valueTemp == null) {
			if (isRequired()) {
				System.out.println("必选域但是EFTMessage数据为空:" + getFieldName());
			}
			return false;
		} else {
			byte[] value = ISO8583Util.format(valueTemp,this.length,getAlign(),STUFF);
			try {
				out.write(value, 0, len);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}


	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		int len = getLength();
		byte[] value = new byte[len];
		try {
			in.read(value);
			setAttributeValue(msg, value);
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
