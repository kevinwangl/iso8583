/**
 * $Log: MutableField.java,v $
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
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 *	
 *   <p><b>变长字段数据处理的基类</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp;变长字段数据处理的基类
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-11-9
 */
		
public abstract class MutableField extends AField {

	/**
	 * <code>几位变长</code>
	 */
	protected int wide;
	
	/**
	 * 占多少字节
	 */
	protected int lengthInByte;
	
	
	protected MutableField(int bit, String data_name, int length,boolean optional, String msgAttributeName, int wide,boolean isMac) {
		super(bit, data_name, length, optional, msgAttributeName, wide,isMac);
		this.wide = wide;
	}
	
	
	/**
	 * 将变长的长度写入流
	 * @param out
	 * @throws IOException
	 */
	protected String writeLengthValue(OutputStream out) throws IOException {
		String lens = String.valueOf(this.length);
		int len = lens.length();
		for (int i = len; i < this.wide; i++) {
			lens = "0"+lens;
		}
		byte[] lenByte = lens.getBytes();
		out.write(lenByte);
		return lens;
	}
	
	
	
	/**
	 * 将变长的长度从流中读出来
	 * @param in
	 *
	 */
	protected void readLengthValue(InputStream in) {
		byte[] b = new byte[this.wide];
		try {
			in.read(b);
			int len = Integer.parseInt(new String(b));
			this.length = len;
			this.lengthInByte = len%2==0?len/2:((len+1)/2);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}
