/*******************************************************************************
 * @(#)BitUtil.java 2017年7月21日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.dixn.netty.LengthFieldBased;

/**
 * @Description：Bit util
 *
 * @projectName：fcs-tcp
 * @ClassName：BitUtil
 * @author：tan10ler
 * @createTime：Sep 26, 2018 11:46:49 AM
 * @update：tan10ler
 * @updateDate：Sep 26, 2018 11:46:49 AM
 * @version v1.0
 */
public final class BitUtil {

	/**
	 * Create a new instance of BitUtil
	 */
	private BitUtil() {

	}

	/**
	 * The byte is converted to a string of bits
	 * 
	 * @param b
	 *            Byte
	 * 
	 * @return TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
	 */
	public static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1)
							+ (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
							+ (byte) ((b >> 1) & 0x1)
							+ (byte) ((b >> 0) & 0x1);
	}

	/**
	 * Binary string to byte
	 * 
	 * @param byteStr
	 *            Binary string
	 * @return byte
	 */
	public static byte decodeBinaryString(String byteStr) {
		int re, len;
		if (null == byteStr) {
			return 0;
		}
		len = byteStr.length();
		if (len != 4 && len != 8) {
			return 0;
		}
		if (len == 8) {// 8 bit处理
			if (byteStr.charAt(0) == '0') {// 正数
				re = Integer.parseInt(byteStr, 2);
			} else {// 负数
				re = Integer.parseInt(byteStr, 2) - 256;
			}
		} else {// 4 bit处理
			re = Integer.parseInt(byteStr, 2);
		}
		return (byte) re;
	}

	/**
	 * short to byte array high first
	 * 
	 * @param n
	 *            Short vale
	 * @return byte array
	 */
	public static byte[] shortToBytesH(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) ((n >> 8) & 0xff);
		return b;
	}

	/**
	 * short to byte array low first
	 * 
	 * @param n
	 *            Short vale
	 * @return byte array
	 */
	public static byte[] shortToBytesL(short n) {
		byte[] b = new byte[2];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) ((n >> 8) & 0xff);
		return b;
	}

	/**
	 * intToBytes
	 * 
	 * @param value
	 *            Bytes
	 * @return Bytes
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * byteToLong
	 * 
	 * @param b
	 *            Bytes
	 * @return Long
	 */
	public static long byteToLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 最低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 最低位
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;

		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	/**
	 * main方法
	 * 
	 * @param args
	 *            Args
	 */
	public static void main(String[] args) {
		// System.out.println(shortToBytesL((short) 104));
		// System.out.println(intToBytes(16384));
	}
}
