
/**
 * $Log: BCDField.java,v $
 * Revision 1.4  2007/11/29 08:27:49  wangl
 * ISO8583解析错误修改
 *
 * Revision 1.3  2007/11/29 08:04:08  wangl
 * ISO8583解析错误修改
 *
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.5  2007/11/09 07:10:49  wangl
 * 加入适当的注释
 *
 * Revision 1.4  2007/11/09 02:40:14  wangl
 * 修改BCDGroupField处理方式
 *
 * Revision 1.3  2007/11/08 07:13:58  wangl
 * 修改错误
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
 * <b>定长BCD类型的数字字段的处理</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;数字型域（0-9）
 * 
 * @author wangl
 * @see
 * @since 2007-9-6
 */

public class BCDField extends AField {
	/**
	 * 补齐数字
	 */
	private static final char STUFF = '0';

	/**
	 * 
	 * 
	 * 
	 * 基本构造方法
	 * 
	 * @param bit
	 * @param data_name
	 * @param length
	 * @param optional
	 * @param msgAttributeName
	 */
	public BCDField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName,boolean isMac) {
		this(bit, data_name, length, optional, msgAttributeName,
				ISO8583Util.PREFIX,isMac);

	}

	/**
	 * 
	 * 
	 * 是否压缩式BCD字段构造方法
	 * 
	 * @param bit
	 * @param data_name
	 * @param length
	 * @param optional
	 * @param msgAttributeName
	 * @param isCompressed
	 */
	public BCDField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName, int align, boolean isCompressed,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, align, isMac);
		this.isCompressed = isCompressed;
	}

	/**
	 * 
	 * 
	 * 对齐方式构造方法
	 * 
	 * @param bit
	 * @param data_name
	 * @param length
	 * @param optional
	 * @param msgAttributeName
	 * @param align
	 */
	public BCDField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName, int align,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, align,isMac);
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		String valueTemp = (String) getAttributeValue(msg);
		String value = ISO8583Util.format(valueTemp, this.length, getAlign(),
				STUFF);
		if (valueTemp == null) {
			if (isRequired()) {
				System.out.println("必选域但是EFTMessage数据为空:" + getFieldName());
			}
			return false;
		} else {
			byte[] bcdValue = ISO8583Util.createNotCompressedBCDArray(value);
			if (isCompressed) {
				bcdValue = ISO8583Util.getCompressedBCDArray(bcdValue,ISO8583Util.SUFFIX);
			}
			try {
				out.write(bcdValue);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	
	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		
		byte[] value = null;
		if (isCompressed) {
			int len = getLength() % 2 == 0 ? getLength() / 2: ((getLength() + 1) / 2);
			value = new byte[len];
		} else {
			value = new byte[getLength()];
		}

		try {
			in.read(value);
			String valueStr = null;
			if (isCompressed) {
				valueStr = ISO8583Util.compressedBCD2String(value);
			} else {
				valueStr = ISO8583Util.notCompressedBCD2String(value);
			}
			String val = valueStr.substring(0, getLength());
			setAttributeValue(msg, val);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
