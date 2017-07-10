package com.zj.qcup.util.iso8583;

import com.zj.core.web.interceptor.SysOutInterceptor;



/**
 * @author wangl
 * 
 */
public class Encrypt {
	
	public static final int SUFFIX = 1; // 后面补零

	public static final int PREFIX = 2; // 前面补零
	
	

	/**
	 * @brief
	 * 用主密钥加密mak,pik,带checkvalue
	 * 
	 * @param mak
	 * @param kek
	 * @return
	 */
	public static byte[] encryptKey(byte[] key,byte[] kek) {
		byte[] out = new byte[12];
		System.arraycopy(encrypt(key,kek),0,out,0,8);
		System.arraycopy(encrypt(new byte[8], key),0,out,8,4);
		return out;
	}

	/**
	 * des加密
	 * 
	 * 
	 * @param input
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] input, byte[] key) {
		long[] keynum = new long[16];
		Des.des_set_key(key, keynum);
		byte[] encypted = new byte[8];
		Des.ecb_encrypt(input, encypted, keynum, true);
		return encypted;
	}

	/**
	 * des解密
	 * 
	 * @param input
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] input, byte[] key) {
		long[] keynum = new long[16];
		Des.des_set_key(key, keynum);
		byte[] encypted = new byte[8];
		Des.ecb_encrypt(input, encypted, keynum, false);
		return encypted;
	}
	
	/**
	 * 3DES加密
	 * @param input 要加密数据
	 * @param key 密钥
	 * @return
	 */
	public static byte[] TriDESencrypt(byte[] input,byte[] key){
		byte[] key1 = new byte[8];
		byte[] key2 = new byte[8];
		System.arraycopy(key, 0, key1, 0, key1.length);
		System.arraycopy(key, 8, key2, 0, key2.length);
		byte[] ret = encrypt(input, key1);
		ret = decrypt(ret, key2);
		return encrypt(ret, key1);
	}
	
	/**
	 * 3DES加密
	 * @param input 要解密数据
	 * @param key 密钥
	 * @return
	 */
	public static byte[] TriDESdecrypt(byte[] input,byte[] key){
		byte[] key1 = new byte[8];
		byte[] key2 = new byte[8];
		System.arraycopy(key, 0, key1, 0, key1.length);
		System.arraycopy(key, 8, key2, 0, key2.length);
		byte[] ret = decrypt(input, key1);
		ret = encrypt(ret, key2);
		return decrypt(ret, key1);
	}

	/**
	 * ECB加密
	 * 
	 * @param input
	 * @param MAK
	 * @return
	 */
	public static byte[] ECB(byte[] input, byte[] MAK) {
		// -- P1&&P2
		int len = input.length / 8;
		if (len < 8 || input.length % 8 != 0)
			len++;

		byte[] tem = new byte[len * 8];
		System.arraycopy(input, 0, tem, 0, input.length);

		input = tem;

		byte[] M1 = new byte[8];
		System.arraycopy(input, 0, M1, 0, 8);
		byte[] MB = new byte[8];
		for (int i = 1; i < len; i++) {
			System.arraycopy(input, 8 * i, MB, 0, 8);
			M1 = XOR(M1, MB);
		}

		byte[] result = M1;
		// --p3 2hex
		String block = byte2hex(result);
		// --p4 MAK encrypt
		byte[] b1 = encrypt(block.substring(0, 8).getBytes(), MAK);
		// -p5 XOR
		byte[] tempBlock = XOR(b1, block.substring(8).getBytes());
		// --p6 des
		byte[] b2 = encrypt(tempBlock, MAK);
		// --p7 2hex
		System.out.println("Mac密钥"+byte2hex(b2));

		byte[] out = byte2hex(b2).substring(0, 8).getBytes();
		return out;
	}

	/**
	 * 格式化byte
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] out = new char[b.length * 2];
		for (int i = 0; i < b.length; i++) {
			byte c = b[i];
			out[i * 2] = Digit[(c >>> 4) & 0X0F];
			out[i * 2 + 1] = Digit[c & 0X0F];
		}

		return new String(out);
	}


	/**
	 * 异或
	 * 
	 * @param op1
	 * @param op2
	 * @return
	 */
	private static byte[] XOR(byte[] op1, byte[] op2) {
		int len = op1.length;
		byte[] out = new byte[len];
		for (int i = 0; i < len; i++) {
			out[i] = (byte) (op1[i] ^ op2[i]);
		}
		return out;
	}
	
	public static void main(String[] args) {
		System.out.println(ArrayUtil.hexEncode(XOR(ArrayUtil.hexDecode("5E4C9485F7CEC4159B469B9B9B58FD4C"), ArrayUtil.hexDecode("B8630C695022C3E2950FAF1171810696"))));
		byte [] miyue = XOR(ArrayUtil.hexDecode("5E4C9485F7CEC4159B469B9B9B58FD4C"), ArrayUtil.hexDecode("B8630C695022C3E2950FAF1171810696"));
		System.out.println(ArrayUtil.hexEncode(TriDESencrypt(ArrayUtil.hexDecode("00000000"), miyue)));
	}
		

	/**
	 * PIN加密
	 * @param pin 密码
	 * @param pan 卡号
	 * @param pik Pik
	 * @return
	 */
	public static byte[] encryptPIN(String pin,String pan,byte[] pik) {
		
		//输入判断的防御式代码
		if (pin == null || pan == null || pik == null) {
			return null;
		}
		
		if (pan.length()<13) {
			return null;
		}
		
		
		pan = pan.substring(pan.length()-13,pan.length()-1);
		
		//获得PAN
		byte[] subedpan = new byte[8];
		byte[] input = ArrayUtil.hexDecode(pan);
		System.arraycopy(input, 0, subedpan, 2, 6);
		
		//获得PINBLOCK
		byte[] pinblock = new byte[8];
		// 填充FF
		for (int i = 0; i < pinblock.length; i++) {
			pinblock[i] = (byte) 0xFF;
		}
		int size = pin.length();
		String t;
		if (size <10) {
			t = "0"+size+pin;
		} else {
			t = size+pin;
		}
		byte [] p = ArrayUtil.hexDecode(t);
		System.arraycopy(p, 0, pinblock, 0, p.length);		
		
		if (pik.length == 8) {
			return encrypt(XOR(pinblock, subedpan), pik);
		} else {
			return TriDESencrypt(XOR(pinblock, subedpan), pik);
		}
		
		
	}

	

	/**
	 * 获得MAK
	 * 
	 * @param key
	 *            masterkey
	 * @param input
	 *            完整数据
	 * @param output
	 *            mak
	 * @return
	 * @throws Exception
	 */
	public static byte[] mak(byte[] key, byte[] input) throws Exception {
		byte[] output = new byte[8];
		System.arraycopy(input, 12, output, 0, 8);
		byte[] checkValue = new byte[4];
		System.arraycopy(input, 20, checkValue, 0, 4);
		output = decrypt(output, key);
		// check valid
		byte[] value = encrypt(new byte[8], output);
		for (int i = 0; i < 4; i++) {
			if (checkValue[i] != value[i])
				throw new Exception();
		}
		// check over
		return output;
	}
	
	/**
	 * 获得MAK
	 * 
	 * @param key
	 *            masterkey
	 * @param input
	 *            完整数据
	 * @param output
	 *            mak
	 * @return
	 * @throws Exception
	 */
	public static byte[] mak16(byte[] key, byte[] input) throws Exception {
		byte[] output = new byte[16];
		System.arraycopy(input, 20, output, 0, 16);
		byte[] checkValue = new byte[4];
		System.arraycopy(input, 36, checkValue, 0, 4);
		output = TriDESdecrypt(output, key);
		// check valid
		byte[] value = encrypt(new byte[8], output);
		for (int i = 0; i < 4; i++) {
			if (checkValue[i] != value[i])
				throw new Exception();
		}
		// check over
		return output;
	}
	
	
	

	/**
	 * 获得pik
	 * 
	 * @param key
	 * @param input
	 * @param output
	 * @return
	 * @throws Exception
	 */
	public static byte[] pik(byte[] key, byte[] input) throws Exception {
		byte[] pin = new byte[8];
		System.arraycopy(input, 0, pin, 0, 8);
		byte[] checkValue = new byte[4];
		System.arraycopy(input, 8, checkValue, 0, 4);
		pin = decrypt(pin, key);
		// check valid
		byte[] value = encrypt(new byte[8], pin);
		for (int i = 0; i < 4; i++) {
			if (checkValue[i] != value[i])
				throw new Exception();
		}
		// check over
		return pin;
	}
	
	/**
	 * 获得pik
	 * 
	 * @param key
	 * @param input
	 * @param output
	 * @return
	 * @throws Exception
	 */
	public static byte[] pik16(byte[] key, byte[] input) throws Exception {
		byte[] pin1 = new byte[8];
		byte[] pin2 = new byte[8];
		System.arraycopy(input, 0, pin1, 0, 8);
		System.arraycopy(input, 8, pin2, 0, 8);
		byte[] checkValue = new byte[4];
		System.arraycopy(input, 16, checkValue, 0, 4);
		pin1 = TriDESdecrypt(pin1, key);
		pin2 = TriDESdecrypt(pin2, key);
		byte[] pin = new byte[16];
		System.arraycopy(pin1, 0, pin, 0, pin1.length);
		System.arraycopy(pin2, 0, pin, pin1.length, pin2.length);
		
		// check valid
		byte[] value = TriDESencrypt(new byte[8], pin);
		for (int i = 0; i < 4; i++) {
			if (checkValue[i] != value[i])
				throw new Exception();
		}
		// check over
		return pin;
	}

}
