/*
 * Copyright (C) 1997 by The Open Group, Cambridge, MA, USA.  All Rights
 * Reserved.
 * 
 * This software is being provided to you, the LICENSEE, by The Open Group
 * (T.O.G.) under the following license.  By obtaining, using and/or
 * copying this software, you agree that you have read, understood, and
 * will comply with these terms and conditions:  
 * 
 * Export of this software from the United States of America may
 * require a specific license from the United States Government.
 * It is the responsibility of any person or organization contemplating
 * export to obtain such a license before exporting.
 * 
 * WITHIN THAT CONSTRAINT, permission to use, copy, modify and distribute 
 * this software and its documentation for any purpose and without fee or 
 * royalty is hereby granted, provided that you agree to comply with the 
 * following copyright notice and statements, including the disclaimer, and 
 * that the same appear on ALL copies of the software and documentation, 
 * including modifications that you make for internal use or for 
 * distribution:
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS", AND T.O.G. MAKES NO REPRESENTATIONS 
 * OR WARRANTIES, EXPRESS OR IMPLIED.  By way of example, but not 
 * limitation, T.O.G. MAKES NO REPRESENTATIONS OR WARRANTIES OF 
 * MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT THE USE OF 
 * THE LICENSED SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY 
 * PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.   
 * 
 * The name of The Open Group, or T.O.G., or TOG may NOT be used in
 * advertising or publicity pertaining to distribution of the software.
 * Title to copyright in this software and any associated documentation
 * shall at all times remain with T.O.G., and USER agrees to preserve same.  
 */

package com.zj.qcup.util.iso8583;

/**
 * Provides des algorithm
 */
class Des {

    /**
	 * Defines the initial permutation
	 */
	private static final byte[] initial_permutation = {
		58,	50, 42,	34,	26,	18,	10,	2,	60,	52,	44,	36,	28,	20,	12,	4,
		62,	54,	46,	38,	30,	22,	14,	6,	64,	56,	48,	40,	32,	24,	16,	8,
		57,	49,	41,	33,	25,	17,	9,	1,	59,	51,	43,	35,	27,	19,	11,	3,
		61,	53,	45,	37,	29,	21,	13,	5,	63,	55,	47,	39,	31,	23,	15,	7
	};
	
	/**
	 * Defines the final permutation
	 */
	private static final byte[] final_permutation = {
		40,	8,	48,	16,	56,	24,	64,	32,	39,	7,	47,	15,	55,	23,	63,	31,
		38,	6,	46,	14,	54,	22,	62,	30,	37,	5,	45,	13,	53,	21,	61,	29,
		36, 4,	44,	12,	52,	20,	60,	28,	35,	3,	43,	11,	51,	19,	59,	27,
		34,	2,	42,	10,	50,	18,	58,	26,	33,	1,	41,	9,	49,	17,	57,	25
	};

    /**
	 * Defines the key permutation
	 */
	private static final byte[] key_permutation = {
		57,	49,	41,	33,	25,	17,	9,	1,	58,	50,	42,	34,	26,	18,
		10,	2,	59,	51,	43,	35,	27,	19,	11,	3,	60,	52, 44, 36,
		63,	55,	47,	39,	31,	23,	15,	7,	62,	54,	46,	38,	30,	22,
		14,	6,	61,	53,	45,	37,	29,	21,	13,	5,	28,	20,	12,	4
	};

    /**
	 * Defines the compression permutation
	 */
	private static final byte[] compression_permutation = {
		14,	17,	11,	24,	1,	5,	3,	28,	15,	6,	21,	10,
		23,	19,	12,	4,	26,	8,	16,	7,	27,	20,	13,	2,
		41,	52,	31,	37,	47,	55,	30,	40,	51,	45,	33,	48,
		44,	49,	39,	56,	34,	53,	46,	42,	50,	36,	29,	32
	};

    /**
	 * Defines the expansion permutation
	 */
	private static final byte[] expansion_permutation = {
		32,	1,	2,	3,	4,	5,	4,	5,	6,	7,	8,	9,
		8,	9,	10,	11,	12,	13,	12,	13,	14,	15,	16,	17,
		16,	17,	18,	19,	20,	21,	20,	21,	22,	23,	24,	25,
		24, 25,	26,	27,	28,	29,	28,	29,	30,	31,	32,	1
	};

    /**
	 * Defines the p_box permutation
	 */
	private static final byte[] p_box_permutation = {
		16,	7,	20,	21,	29,	12,	28,	17,	1,	15,	23,	26,	5,	18,	31,	10,
		2,	8,	24,	14,	32,	27,	3,	9,	19,	13,	30,	6,	22,	11,	4,	25
	};

    /**
	 * Defines the rotation number
	 */
	private static final byte[] rot_num = {
		1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
	};

    /**
	 * Defines the chunk mask
	 */
	private static final long[] chunk_mask = {
		0xfc00000000000000L,
		0x03f0000000000000L,
		0x000fc00000000000L,
		0x00003f0000000000L,
		0x000000fc00000000L,
		0x00000003f0000000L,
		0x000000000fc00000L,
		0x00000000003f0000L
	};

    /**
	 * List of bad keys
	 */
	private static final long[] bad_keys = {
		0x0101010101010101L, 0xfefefefefefefefeL,
		0x1f1f1f1f1f1f1f1fL, 0xe0e0e0e0e0e0e0e0L,
		0x01fe01fe01fe01feL, 0xfe01fe01fe01fe01L,
		0x1fe01fe00ef10ef1L, 0xe01fe01ff10ef10eL,
		0x01e001e001f101f1L, 0xe001e001f101f101L,
		0x1ffe1ffe0efe0efeL, 0xfe1ffe1ffe0efe0eL,
		0x011f011f010e010eL, 0x1f011f010e010e01L,
		0xe0fee0fef1fef1feL, 0xfee0fee0fef1fef1L
	};

    /**
     * List of good parity
     */
	private static final byte[] good_parity = {
 		1,	 1,   2,   2,   4,   4,   7,   7,
		8,   8,   11,  11,  13,  13,  14,  14, 
		16,  16,  19,  19,  21,  21,  22,  22,
		25,  25,  26,  26,  28,  28,  31,  31, 
		32,  32,  35,  35,  37,  37,  38,  38,
		41,  41,  42,  42,  44,  44,  47,  47, 
		49,  49,  50,  50,  52,  52,  55,  55,
		56,  56,  59,  59,  61,  61,  62,  62, 
		64,  64,  67,  67,  69,  69,  70,  70,
		73,  73,  74,  74,  76,  76,  79,  79, 
		81,  81,  82,  82,  84,  84,  87,  87,
		88,  88,  91,  91,  93,  93,  94,  94, 
		97,  97,  98,  98,  100, 100, 103, 103,
		104, 104, 107, 107, 109, 109, 110, 110, 
		112, 112, 115, 115, 117, 117, 118, 118,
		121, 121, 122, 122, 124, 124, 127, 127, 
		(byte)128, (byte)128, (byte)131, (byte)131,
		(byte)133, (byte)133, (byte)134, (byte)134,
		(byte)137, (byte)137, (byte)138, (byte)138,
		(byte)140, (byte)140, (byte)143, (byte)143,
		(byte)145, (byte)145, (byte)146, (byte)146,
		(byte)148, (byte)148, (byte)151, (byte)151,
		(byte)152, (byte)152, (byte)155, (byte)155,
		(byte)157, (byte)157, (byte)158, (byte)158,
		(byte)161, (byte)161, (byte)162, (byte)162,
		(byte)164, (byte)164, (byte)167, (byte)167,
		(byte)168, (byte)168, (byte)171, (byte)171,
		(byte)173, (byte)173, (byte)174, (byte)174,
		(byte)176, (byte)176, (byte)179, (byte)179,
		(byte)181, (byte)181, (byte)182, (byte)182,
		(byte)185, (byte)185, (byte)186, (byte)186,
		(byte)188, (byte)188, (byte)191, (byte)191,
		(byte)193, (byte)193, (byte)194, (byte)194,
		(byte)196, (byte)196, (byte)199, (byte)199,
		(byte)200, (byte)200, (byte)203, (byte)203,
		(byte)205, (byte)205, (byte)206, (byte)206,
		(byte)208, (byte)208, (byte)211, (byte)211,
		(byte)213, (byte)213, (byte)214, (byte)214,
		(byte)217, (byte)217, (byte)218, (byte)218,
		(byte)220, (byte)220, (byte)223, (byte)223,
		(byte)224, (byte)224, (byte)227, (byte)227,
		(byte)229, (byte)229, (byte)230, (byte)230,
		(byte)233, (byte)233, (byte)234, (byte)234,
		(byte)236, (byte)236, (byte)239, (byte)239,
		(byte)241, (byte)241, (byte)242, (byte)242,
		(byte)244, (byte)244, (byte)247, (byte)247,
		(byte)248, (byte)248, (byte)251, (byte)251,
		(byte)253, (byte)253, (byte)254, (byte)254
	};

    /**
     * List of sbox
     */
	private static final byte [][] sbox = {
		{
			14,	4,	13,	1,	2,	15,	11,	8,	3,	10,	6,	12,	5,	9,	0,	7,
			0,	15,	7,	4,	14,	2,	13,	1,	10,	6,	12,	11,	9,	5,	3,	8,
			4,	1,	14,	8,	13,	6,	2,	11,	15,	12,	9,	7,	3,	10,	5,	0,
			15,	12,	8,	2,	4,	9,	1,	7,	5,	11,	3,	14,	10,	0,	6,	13
		},
		{
			15,	1,	8,	14,	6,	11,	3,	4,	9,	7,	2,	13,	12,	0,	5,	10,
			3,	13,	4,	7,	15,	2,	8,	14,	12,	0,	1,	10,	6,	9,	11,	5,
			0,	14,	7,	11,	10,	4,	13,	1,	5,	8,	12,	6,	9,	3,	2,	15,
			13,	8,	10,	1,	3,	15,	4,	2,	11,	6,	7,	12,	0,	5,	14,	9
		},
		{
			10,	0,	9,	14,	6,	3,	15,	5,	1,	13,	12,	7,	11,	4,	2,	8,
			13,	7,	0,	9,	3,	4,	6,	10,	2,	8,	5,	14,	12,	11,	15,	1,
			13,	6,	4,	9,	8,	15,	3,	0,	11,	1,	2,	12,	5,	10,	14,	7,
			1,	10,	13,	0,	6,	9,	8,	7,	4,	15,	14,	3,	11,	5,	2,	12
		},
		{
			7,	13,	14,	3,	0,	6,	9,	10,	1,	2,	8,	5,	11,	12,	4,	15,
			13,	8,	11,	5,	6,	15,	0,	3,	4,	7,	2,	12,	1,	10,	14,	9,
			10,	6,	9,	0,	12,	11,	7,	13,	15,	1,	3,	14,	5,	2,	8,	4,
			3,	15,	0,	6,	10,	1,	13,	8,	9,	4,	5,	11,	12,	7,	2,	14
		},
		{
			2,	12,	4,	1,	7,	10,	11,	6,	8,	5,	3,	15,	13,	0,	14,	9,
			14,	11,	2,	12,	4,	7,	13,	1,	5,	0,	15,	10,	3,	9,	8,	6,
			4,	2,	1,	11,	10,	13,	7,	8,	15,	9,	12,	5,	6,	3,	0,	14,
			11,	8,	12,	7,	1,	14,	2,	13,	6,	15,	0,	9,	10,	4,	5,	3
		},
		{
			12,	1,	10,	15,	9,	2,	6,	8,	0,	13,	3,	4,	14,	7,	5,	11,
			10,	15,	4,	2,	7,	12,	9,	5,	6,	1,	13,	14,	0,	11,	3,	8,
			9,	14,	15,	5,	2,	8,	12,	3,	7,	0,	4,	10,	1,	13,	11,	6,
			4,	3,	2,	12,	9,	5,	15,	10,	11,	14,	1,	7,	6,	0,	8,	13
		},
		{
			4,	11,	2,	14,	15,	0,	8,	13,	3,	12,	9,	7,	5,	10,	6,	1,
			13,	0,	11,	7,	4,	9,	1,	10,	14,	3,	5,	12,	2,	15,	8,	6,
			1,	4,	11,	13,	12,	3,	7,	14,	10,	15,	6,	8,	0,	5,	9,	2,
			6,	11,	13,	8,	1,	4,	10,	7,	9,	5,	0,	15,	14,	2,	3,	12
		},
		{
			13,	2,	8,	4,	6,	15,	11,	1,	10,	9,	3,	14,	5,	0,	12,	7,
			1,	15,	13,	8,	10,	3,	7,	4,	12,	5,	6,	11,	0,	14,	9,	2,
			7,	11,	4,	1,	9,	12,	14,	2,	0,	6,	10,	13,	15,	3,	5,	8,
			2,	1,	14,	7,	4,	10,	8,	13,	15,	12,	9,	0,	3,	5,	6,	11
		}
	};

    /** 
     * Permutes des
     *
	 * @return long is a result
	 * @param p is of type byte[]
	 * @param x is of type long
	 */
	public static final long permute_des(byte[] p, long x) {
		long y = 0;
		for (int i = 0; i < p.length; i++) {
			y |= ((x & (0x8000000000000000L >>> (p[i] - 1))) << (p[i] - 1)) >>> i;
		}
		return y;
	}

    /**
	 * Checks parity
	 *
	 * @return boolean is a result
	 * @param key is of type long
	 */
	public static final boolean check_parity(long key) {
		byte[] octet = long2octet(key);
		return check_parity(octet);
	}

    /**
	 * Checks parity for a given key
	 *
	 * @return boolean is a result
	 * @param key is of type byte[]
	 */
	public static final boolean check_parity(byte[] key) {
		for (int i=0; i < 8; i++) {
            	if (key[i] != good_parity[key[i] & 0xff])
				return false;
		}
		return true;
	}

    /**
	 * Sets parity
	 *
	 * @return byte[] is a result
	 * @param key is of type byte[]
	 */
	public static final byte[] set_parity(byte[] key) {
		for (int i=0; i < 8; i++) {
			key[i] = good_parity[key[i] & 0xff];
		}
		return key;
	}

    /**
	 * Sets parity for a given key
	 *
	 * @return long is a result
	 * @param key is of type long
	 */
	public static final long set_parity(long key) {
		return octet2long(set_parity(long2octet(key)));
	}

    /**
	 * Checks to see whether the given key is bad or not
	 *
	 * @return boolean is a result
	 * @param key is of type long
	 */
	public static final boolean bad_key(long key) {
		for (int i = 0; i < bad_keys.length; i++) {
			if (bad_keys[i] == key) {
				return true;
			}
		}
		return false;
	}

    /**
	 * Checks to see whether the given key is bad or not
	 *
	 * @return boolean is a result
	 * @param key is of type byte[]
	 */
	public static final boolean bad_key(byte[] key) {
		return bad_key(octet2long(key));
	}

    /**
	 * Sets the des key
	 *
	 * @return boolean is a result
	 * @param key is of type long
	 * @param key_sched is of type long[]
	 */
	public static boolean des_set_key(long key, long[] key_sched) {
		long x = permute_des(key_permutation, key);
		for (int i = 0; i < 16; i++) {
			if (rot_num[i] == 1)
				x = (x & 0x7ffffff7ffffff00L) << 1 |
					(x & 0x8000000800000000L) >>> 27;
			else
				x = (x & 0x3ffffff3ffffff00L) << 2 |
					(x & 0xc000000c00000000L) >>> 26;
			key_sched[i] = permute_des(compression_permutation, x);
		}
		return check_parity(key);
	}

    /**
	 * Sets the des key
	 *
	 * @return boolean is a result
	 * @param key is of type byte[]
	 * @param key_sched is of type long[]
	 */
	public static boolean des_set_key(byte[] key, long[] key_sched) {
		return des_set_key(octet2long(key), key_sched);
	}

    /**
	 * Mangles
	 *
	 * @return long is a result
	 * @param R is of type long
	 * @param key is of type long
	 */
	public static long mangle(long R, long key) {
		long output = 0;
		int chunk, nibble;
		long expanded_R = permute_des(expansion_permutation, R);

		for (int i = 0; i < 8; i++) {
			chunk = (int)(((expanded_R & chunk_mask[i]) ^
				(key & chunk_mask[i])) >>> ((6 * (7 - i)) + 16));
			
			chunk = ((chunk & 0x00000001) << 4) | (chunk & 0x00000020) |
				((chunk & 0x0000001e) >>> 1); 
			
			//chunk -> nibble
			nibble = (int)sbox[i][chunk];

			output |= ((long)nibble) << (((7 - i) * 4) + 32);
		}
		return permute_des(p_box_permutation, output);
	}

    /**
	 * Encrypts with des
	 *
	 * @return long is a result
	 * @param data is of type long
	 * @param key_sched is of type long[]
	 * @param encrypt is of type boolean
	 */
	public static long des_encrypt(long data, long[] key_sched, boolean encrypt) {
		long input, output;
		long L, R, R1 = 0;
		input = permute_des(initial_permutation, data);
		L = (input & 0xffffffff00000000L);
		R = (input & 0x00000000ffffffffL) << 32;
		for (int i = 0; i < 16; i++) {
			if (encrypt)
				R1 = L ^ mangle(R, key_sched[i]);
			else
				R1 = L ^ mangle(R, key_sched[15 - i]);
			L = R;
			R = R1;
		}
		output = R | (L >>> 32);
		output = permute_des(final_permutation, output);
		return output;
	}

    /**
	 * Encrypts with des
	 *
	 * @return long is a result
	 * @param data is of type byte[]
	 * @param key_sched is of type long[]
	 * @param encrypt is of type boolean
	 */
	public static long des_encrypt(byte[] data, long[] key_sched, boolean encrypt) {
		return des_encrypt(octet2long(data), key_sched, encrypt);
	}

    /**
	 * Converts octet to long
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 */
	public static long octet2long(byte[] input) {
		return octet2long(input, 0);
	}

    /**
	 * Converts octet to long with offset
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 * @param offset is of type int
	 */
	public static long octet2long(byte[] input, int offset) {
		long result = 0;
		for (int i = 0; i < 8; i++) {
			if (i + offset < input.length) {
				result |= (((long)input[i + offset]) & 0xffL) << ((7 - i) * 8);
			}
		}
		return result;
	}

    /**
	 * Converts long to octet
	 *
	 * @return byte[] is a result
	 * @param input is of type long
	 */
	public static byte[] long2octet(long input) {
		byte[] output = new byte[8];
		for (int i = 0; i < 8; i++) {
			output[i] =	(byte)((input >>> ((7 - i) * 8)) & 0xffL);
		}
		return output;
	}

    /**
	 * Converts long to octet
	 *
	 * @param input is of type long
	 * @param output is of type byte[]
	 */
	public static void long2octet(long input, byte[] output) {
		long2octet(input, output, 0);
	}

    /**
	 * Converts long to octet with offset
	 *
	 * @param input is of type long
	 * @param output is of type byte[]
	 * @param offset is of type int
	 */
	public static void long2octet(long input, byte[] output, int offset) {
		for (int i = 0; i < 8; i++) {
			if (i + offset < output.length) {
				output[i + offset] =
					(byte)((input >>> ((7 - i) * 8)) & 0xffL);
			}
		}
	}

    /**
	 * Converts string to byte
	 *
	 * @return byte[] is a result
	 * @param str is of type String
	 * @see java.lang.String
	 */
	public static byte[] string2byte(String str) {
/*		byte[] result = new byte[str.length()];
		getBytes(0, str.length(), result, 0);
		return result;*/
		return str.getBytes();
	}
	

	/**
	 * ECB encryption
	 *
	 * @param input is of type byte[]
	 * @param output is of type byte[]
	 * @param schedule is of type long[]
	 * @param encrypt is of type boolean
	 */
	public static void ecb_encrypt(
		byte[] input,
		byte[] output,
		long[] schedule,
		boolean encrypt) {
		long2octet(des_encrypt(octet2long(input), schedule, encrypt), output);
	}

    /**
	 * ECB encryption
	 *
	 * @param input is of type byte[]
	 * @param input_offset is of type int
	 * @param output is of type byte[]
	 * @param output_offset is of type int
	 * @param schedule is of type long[]
	 * @param encrypt is of type boolean
	 */
	public static void ecb_encrypt(
		byte[] input,
		int input_offset,
		byte[] output,
		int output_offset,
		long[] schedule,
		boolean encrypt) {
		long2octet(des_encrypt(octet2long(input, input_offset), schedule,
			encrypt), output, output_offset);
	}

    /**
	 * CBC encryption
	 *
	 * @param input is of type byte[]
	 * @param output is of type byte[]
	 * @param schedule is of type long[]
	 * @param ivec is of type long
	 * @param encrypt is of type boolean
	 */
	public static void cbc_encrypt(
		byte[] input,
		byte[] output,
		long[] schedule,
		long ivec,
		boolean encrypt) {
		cbc_encrypt(input, 0, output, 0, input.length, schedule, ivec, encrypt);
	}

    /**
	 * CBC encryption
	 *
	 * @param input is of type byte[]
	 * @param output is of type byte[]
	 * @param schedule is of type long[]
	 * @param ivec is of type byte[]
	 * @param encrypt is of type boolean
	 */
	public static void cbc_encrypt(
		byte[] input,
		byte[] output,
		long[] schedule,
		byte[] ivec,
		boolean encrypt) {
		cbc_encrypt(input, 0, output, 0, input.length, schedule, octet2long(ivec), encrypt);
	}

    /**
	 * CBC encryption
	 *
	 * @param input is of type byte[]
	 * @param input_offset is of type int
	 * @param output is of type byte[]
	 * @param output_offset is of type int
	 * @param length is of type int
	 * @param schedule is of type long[]
	 * @param ivec is of type byte[]
	 * @param encrypt is of type boolean
	 */
	public static void cbc_encrypt(
		byte[] input,
		int input_offset,
		byte[] output,
		int output_offset,
		int	length,
		long[] schedule,
		byte[] ivec,
		boolean encrypt) {
		cbc_encrypt(input, input_offset, output, output_offset, length, schedule, octet2long(ivec), encrypt);
	}
	
	/**
	 * CBC encryption
	 *
	 * @param input is of type byte[]
	 * @param input_offset is of type int
	 * @param output is of type byte[]
	 * @param output_offset is of type int
	 * @param length is of type int
	 * @param schedule is of type long[]
	 * @param ivec is of type long
	 * @param encrypt is of type boolean
	 */
	public static void cbc_encrypt(
		byte[] input,
		int input_offset,
		byte[] output,
		int output_offset,
		int	length,
		long[] schedule,
		long ivec,
		boolean encrypt) {
		long m;
		long c = ivec;
		for (int i = 0; i < (length / 8) + (length % 8 == 0 ? 0 : 1); i++) {
			m = octet2long(input, (i * 8) + input_offset);
			if (encrypt) {
				c = des_encrypt(m ^ c, schedule, encrypt);
				long2octet(c, output, (i * 8) + output_offset);
			}
			else {
				c = c ^ des_encrypt(m, schedule, encrypt);
				long2octet(c, output, (i * 8) + output_offset);
				c = m;
			}
		}
	}

	/**
	 * CBC checksum
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 * @param schedule is of type long[]
	 * @param ivec is of type long
	 */    
	public static long cbc_cksum(
		byte[] input,
		long[] schedule,
		long ivec) {
		return cbc_cksum(input, 0, input.length, schedule, ivec);
	}

    /**
	 * CBC checksum
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 * @param schedule is of type long[]
	 * @param ivec is of type byte[]
	 */
	public static long cbc_cksum(
		byte[] input,
		long[] schedule,
		byte[] ivec) {
		return cbc_cksum(input, 0, input.length, schedule, ivec);
	}

    /**
	 * CBC checksum
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 * @param input_offset is of type int
	 * @param length is of type int
	 * @param schedule is of type long[]
	 * @param ivec is of type long
	 */
	public static long cbc_cksum(
		byte[] input,
		int input_offset,
		int	length,
		long[] schedule,
		long ivec) {
		long m;
		long c = ivec;
		for (int i = 0; i < (length / 8) + (length % 8 == 0 ? 0 : 1); i++) {
			m = octet2long(input, (i * 8) + input_offset);
			c = des_encrypt(m ^ c, schedule, true);
		}
		return c;
	}

	/**
	 * CBC checksum
	 *
	 * @return long is a result
	 * @param input is of type byte[]
	 * @param input_offset is of type int
	 * @param length is of type int
	 * @param schedule is of type long[]
	 * @param ivec is of type byte[]
	 */
	public static long cbc_cksum(
		byte[] input,
		int input_offset,
		int	length,
		long[] schedule,
		byte[] ivec) {
		return cbc_cksum(input, input_offset, length, schedule, octet2long(ivec));
	}
	
	/**
	 * Converts string to key
	 *
	 * @return long is a result
	 * @param str is of type String
	 * @see java.lang.String
	 */
	public static long string_to_key(String str) {
		byte[] bytes = string2byte(str);
		long[] key_sched = new long[16];
		long key = 0;
		long octet = 0;
		long octet1 = 0;
		
		int length = (bytes.length / 8) + (bytes.length % 8  == 0 ? 0 : 1);

		for (int i = 0; i < length; i++) {
			octet = octet2long(bytes, i * 8) & 0x7f7f7f7f7f7f7f7fL;
			if (i % 2 == 1) {
				octet1 = 0;
				for (int j = 0; j < 64; j++) {
					octet1 |= ((octet & (1L << j)) >>> j) << (63 - j);
				}
				octet = octet1 >>> 1;
			}
			key ^= (octet << 1);
		}

		key = set_parity(key);
	    if (bad_key(key)) {
			byte [] temp = long2octet(key);
			temp[7] ^= 0xf0;
			key = octet2long(temp);
		}
	    des_set_key(key, key_sched);
		key = cbc_cksum(bytes, key_sched, key);
	    
		key = set_parity(key);
	    if (bad_key(key)) {
			byte [] temp = long2octet(key);
			temp[7] ^= 0xf0;
			key = octet2long(temp);
		}
		
		return key;
	}

    /**
	 * Converts string to key bytes
	 *
	 * @return byte[] is a result
	 * @param str is of type String
	 * @see java.lang.String
	 */
	public static byte[] string_to_key_bytes(String str) {
		return long2octet(string_to_key(str));
	}

}


