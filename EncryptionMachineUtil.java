/**
 * 银联加密机操作类
 * @author wangl
 *
 */
public class EncryptionMachineUtil {
	
	/**
	 * 获取SEK索引
	 * @return
	 */
	public static String getSek(){
		return "S0012";
	}
	
	/**
	 * 下载MAK密钥密文
	 * @return
	 */
	public static String getMak(){
		String mak = "";
		return mak;
	}
	
	
	/**
	 * MAC计算
	 * @param sek
	 * @param mak
	 * @param data
	 * @return
	 */
	public static String mac(String sek, String mak,String data){
		String mac = "";
		return mac;
	}
	
	/**
	 * MAC验证
	 * @param sek
	 * @param mak
	 * @param mac
	 * @param data
	 * @return
	 */
	public static boolean mac(String sek, String mak,String mac,String data){
		boolean b = false;
		return b;
	}
	

}
