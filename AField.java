
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;


/**
 * @brief
 * <p>
 * <b>字段抽象类</b>
 * </p>
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp;iso域抽象
 * 
 * <p>
 * </p>
 * @author wangl
 * @see
 * @since 2007-9-6
 */

public abstract class AField {

	/**
	 * <code>bit</code> 所在位
	 */
	protected int bit;

	protected String fieldName; /* 域名 */

	protected int length; /* 数据域长度 */

	protected boolean required; /* 是否可选 */

	/**
	 * <code>msgAttributeName</code>对应的Message对象的属性值
	 */
	protected String msgAttributeName;
	
	protected boolean isMac;

	/**
	 * <code>align</code> 对齐方式
	 */
	protected int align;
	/**
	 * bcd类型字段数据是否为压缩式
	 */
	protected boolean isCompressed = true;
	
	protected AField(int bit, String data_name, int length, boolean optional,
			String msgAttributeName,boolean isMac) {
		this(bit,data_name,length,optional,msgAttributeName,ISO8583Util.SUFFIX,isMac);
	}

	protected AField(int bit, String data_name, int length, boolean optional,String msgAttributeName, int align,boolean isMac) {
		this.bit = bit;
		this.fieldName = data_name;
		this.length = length;
		this.required = optional;
		this.msgAttributeName = msgAttributeName;
		this.align = align;
		this.isMac = isMac;
	}

	public int getBit() {
		return bit;
	}

	public String getFieldName() {
		return fieldName;
	}

	public int getLength() {
		return length;
	}

	public boolean isRequired() {
		return required;
	}

	public String getMsgAttributeName() {
		return msgAttributeName;
	}

	public int getAlign() {
		return align;
	}

	

	public boolean isMac() {
		return isMac;
	}

	public void setMac(boolean isMac) {
		this.isMac = isMac;
	}

	/**
	 * @brief 封包过程
	 * 
	 * @param msg
	 *            需要封包的金融消息
	 * @param out
	 *            要输出的打包后的流
	 * @return 
	 */
	abstract public boolean pack(EFTMessage msg, OutputStream out,List<String> macList,StringBuffer packLog);

	/**
	 * @brief 解包过程
	 * 
	 * @param in
	 *            流化后的iso8583数据包
	 * @param msg
	 *            存放具体数据的金融消息
	 */
	abstract public void unpack(InputStream in, EFTMessage msg,StringBuffer retLog);

	/**
	 * @brief 使用反射将值设置到消息对象
	 * 
	 * @param msg
	 *            金融消息
	 * @param obj
	 *            值
	 */
	@SuppressWarnings("rawtypes")
	public void setAttributeValue(EFTMessage msg, Object obj) {
		Class clazz = msg.getClass();
		Field field = null;
		try {
			field = clazz.getField(msgAttributeName);
			field.set(msg, obj);
		} catch (Exception e) {

		}
	}

	/**
	 * @brief 使用反射将消息对象的值读出来
	 * 
	 * @param msg
	 *            金融消息
	 */
	@SuppressWarnings("rawtypes")
	public Object getAttributeValue(EFTMessage msg) {
		Class clazz = msg.getClass();
		Field field = null;
		Object returnObj = null;
		try {
			field = clazz.getField(msgAttributeName);
			returnObj = field.get(msg);
			return returnObj;
		} catch (Exception e) {
			return returnObj;
		}
	}
}
