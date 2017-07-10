
/**
 * $Log: ASCIIGroupField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.2  2007/11/09 07:10:49  wangl
 * 加入适当的注释
 *
 * Revision 1.1  2007/11/07 08:32:02  wangl
 * 添加新类型Group字段为容错做处理
 *
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 *	
 *   <p><b>字符组字段处理</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp;处理字符组字段
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-11-9
 */
public class ASCIIGroupField extends GroupField {

	public ASCIIGroupField(int bit, int length, int wide, AField[] fields,boolean isMac) {
		super(bit, length, wide, fields,isMac);
		// TODO 自动生成构造函数存根
	}


	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		readLengthValue(in);
		AField[] fields = getFields();

		byte[] b = new byte[this.length];
		try {
			in.read(b);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		ByteArrayInputStream inInner = new ByteArrayInputStream(b);
		for (int i = 0; i < fields.length; i++) {
			AField f = fields[i];
			f.unpack(inInner, msg,retLog);
		}
	}


}
