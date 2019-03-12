/*******************************************************************************
 * @(#)CrcUtil.java Jun 6, 2018
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.dixn.netty.LengthFieldBased;

/**
 * TODO 这里请补充该类型的简述说明
 * 
 * @author <a href="mailto:zhaol@emrubik.com">zhao lei</a>
 * @version $Revision 1.0 $ Jun 6, 2018 4:48:27 PM
 */
public class CrcUtil {
	public static String makeModbus16CRC(byte[] data) {
		byte[] buf = new byte[data.length];// 存储需要产生校验码的数据
		for (int i = 0; i < data.length; i++) {
			buf[i] = data[i];
		}
		int len = buf.length;
		int crc = 0xFFFF;
		for (int pos = 0; pos < len; pos++) {
			if (buf[pos] < 0) {
				crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
												// crc
			} else {
				crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
			}
			for (int i = 8; i != 0; i--) { // Loop over each bit
				if ((crc & 0x0001) != 0) { // If the LSB is set
					crc >>= 1; // Shift right and XOR 0xA001
					crc ^= 0xA001;
				} else
					// Else LSB is not set
					crc >>= 1; // Just shift right
			}
		}
		String c = Integer.toHexString(crc);
		if (c.length() == 4) {
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 3) {
			c = "0" + c;
			c = c.substring(2, 4) + c.substring(0, 2);
		} else if (c.length() == 2) {
			c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
		}
		return c;
	}

	public static byte[] makeXModem16CRC(byte[] bytes) {
		int crc = 0x00; // initial value
		int polynomial = 0x1021;
		for (int index = 0; index < bytes.length; index++) {
			byte b = bytes[index];
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 1) == 1);
				boolean c15 = ((crc >> 15 & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= polynomial;
			}
		}
		crc &= 0xffff;
		return BitUtil.shortToBytesH((short) crc);
	}

	public static void main(String[] args) {
		byte[] bytes = new byte[] { 1, 3, 2, 0, 0, 16 };
		System.out.println(HexDumper.hexStringToByte(makeModbus16CRC(bytes)));
	}
}
