/**
 * $Log: MutableBCDField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.4  2007/11/09 07:10:50  wangl
 * 加入适当的注释
 *
 * Revision 1.3  2007/11/09 02:40:14  wangl
 * 修改BCDGroupField处理方式
 *
 * Revision 1.2  2007/11/06 09:26:48  wangl
 * 修改对齐方式写错的地方
 *
 * Revision 1.1  2007/11/02 07:16:55  wangl
 * 添加新的重构後的代码
 *
 * Revision 1.2  2007/10/09 03:17:47  liaok
 * 修正可选域的截取问题
 *
 * Revision 1.2  2007/09/19 07:55:24  liaok
 * 增加非定长属性字段
 *
 * Revision 1.1.1.1  2007/09/11 07:15:00  ndscm
 * 提交金融后台代码－zhaol
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * @brief
 * <p>
 * <b>变长的BCD字段的处理</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;变长的BCD字段的处理
 * 
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-9-6
 */

public class MutableBCDField extends MutableField {

	public MutableBCDField(int bit, String data_name, int length,
			boolean optional, String msgAttributeName, int wide,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, wide,isMac);
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		String value = (String) getAttributeValue(msg);
		if (value == null) {
			if (isRequired()) {
				System.out.println("必选域但是EFTMessage数据为空:" + getFieldName());
			}
			return false;
		} else {
			try {
				this.length = value.length();
				writeLengthValue(out);
				byte[] val = ISO8583Util.createBCDByteArray(value,ISO8583Util.SUFFIX);
				out.write(val);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

		}
	}

	
	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		readLengthValue(in);
		byte[] value = new byte[this.lengthInByte];
		try {
			in.read(value,0,value.length);
			String valueStr = ISO8583Util.compressedBCD2String(value);
			String val = valueStr.substring(0,getLength());
			setAttributeValue(msg,val);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
