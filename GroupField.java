/**
 * $Log: GroupField.java,v $
 * Revision 1.2  2007/11/13 06:36:54  wangl
 * 同步代码
 *
 * Revision 1.4  2007/11/09 07:10:50  wangl
 * 加入适当的注释
 *
 * Revision 1.3  2007/11/07 06:36:51  wangl
 * 修改加入空字段保护
 *
 * Revision 1.2  2007/11/07 02:28:54  wangl
 * 修改一些小错误
 *
 * Revision 1.1  2007/11/02 07:16:55  wangl
 * 添加新的重构後的代码
 *
 */

package com.zj.qcup.util.iso8583;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief
 * <p>
 * <b>字段数组处理基类</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;字段数组处理基类
 * 
 * @author wangl
 * @version eJPos SDK1.0
 * @see
 * @since 2007-10-26
 */

public class GroupField extends MutableField {

	private ArrayList array = new ArrayList();

	public GroupField(int bit, int length, int wide, AField[] fields,boolean isMac) {
		super(bit, null, length, true, null, wide,isMac);
		if(fields != null){
		for (int i = 0; i < fields.length; i++) {
				array.add(fields[i]);
			}
		}
	}

	public AField[] getFields() {
		return (AField[]) array.toArray(new AField[]{});
	}

	
	public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int length = 0;
		for (int i = 0; i < array.size(); i++) {
			AField f = (AField)array.get(i);
			f.pack(msg, os,macList,packLog);
			length += f.length;
		}
		this.length = length;
		byte[] b = os.toByteArray();
		try {
			writeLengthValue(out);
			out.write(b);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		
	}


	public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog) {
		readLengthValue(in);
		for (int i = 0; i < array.size(); i++) {
			AField f = (AField)array.get(i);
			f.unpack(in,msg,retLog);
		}
	}

}
