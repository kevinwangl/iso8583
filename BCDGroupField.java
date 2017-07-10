/**
 * $Log: BCDGroupField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.4  2007/11/09 07:10:49  wangl
 * 加入适当的注释
 *
 * Revision 1.3  2007/11/09 06:19:03  wangl
 * 修改錯誤得到壓縮bcd碼時候的錯誤
 *
 * Revision 1.2  2007/11/09 02:40:14  wangl
 * 修改BCDGroupField处理方式
 *
 * Revision 1.1  2007/11/07 08:32:02  wangl
 * 添加新类型Group字段为容错做处理
 *
 */


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 
 *	
 *   <p><b>BCD类型的数字字段数组的处理</b></p>
 * 
 *  &nbsp;&nbsp;&nbsp;&nbsp;BCD类型的数字字段数组的处理
 * 
 *  @author wangl
 *  @version eJPos SDK1.0
 *  @see 
 *  @since 2007-11-9
 */
public class BCDGroupField extends GroupField {

	public BCDGroupField(int bit, int length, int wide, AField[] fields,boolean isMac) {
		super(bit, length, wide, fields,isMac);
		// TODO 自动生成构造函数存根
	}

	
	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		readLengthValue(in);
		AField[] fields = getFields();

		byte[] b = new byte[this.lengthInByte];
		try {
			in.read(b);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		byte[] notCompressedByteArray = ISO8583Util.getNotCompressedBCDArray(b);
		ByteArrayInputStream inInner = new ByteArrayInputStream(notCompressedByteArray);
		for (int i = 0; i < fields.length; i++) {
			AField f = fields[i];
			f.isCompressed = false;
			f.unpack(inInner, msg,retLog);
		}
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		AField[] fields = getFields();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		for (int i = 0; i < fields.length; i++) {
			AField f = fields[i];
			f.isCompressed = false;
			f.pack(msg, os,macList,packLog);	
		}
		byte[] notCompressByteArray = os.toByteArray();
		String notCompressedBCDString = ISO8583Util.notCompressedBCD2String(notCompressByteArray);
		byte[] compressByteArray = ISO8583Util.createBCDByteArray(notCompressedBCDString,getAlign());
		this.length =notCompressByteArray.length;
		try {
			writeLengthValue(out);
			out.write(compressByteArray);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}



}
