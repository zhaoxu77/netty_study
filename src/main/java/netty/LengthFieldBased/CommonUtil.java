package netty.LengthFieldBased;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**@Description：Util
 *
 * @projectName：fcs-tcp
 * @ClassName：CommonUtil
 * @author：tan10ler
 * @createTime：Sep 20, 2018 3:58:47 PM
 * @update：tan10ler
 * @updateDate：Sep 20, 2018 3:58:47 PM
 * @version v1.0
 */
public class CommonUtil {



	/**
	 * @Description:Merge multiple arrays
	 *
	 * @author：tan10ler
	 * @createTime：Sep 20, 2018 11:33:32 AM
	 * @updateUser：tan10ler
	 * @UpdateTime：Sep 20, 2018 11:33:32 AM
	 * @throws Exception
	 *
	 * @version：1.0
	 *
	 */
	public static byte[] getListByte(byte[]... bytes) {
		List<byte[]> collect = new ArrayList<byte[]>();
		if (bytes != null) {
			for (int j = 0; j < bytes.length; j++) {
				if (bytes[j] != null) {
					collect.add(bytes[j]);
				}
			}
		}
		byte[] aa0 = null;
		// Each time the two arrays are merged so the number of merges is
		// collect.size (), the first
		// is the virtual array
		for (int i = 0; i < collect.size(); i++) {
			int aa0Length = aa0 == null ? 0 : aa0.length;
			byte[] aa1 = (byte[]) collect.get(i);
			byte[] c = new byte[aa0Length + aa1.length];
			if (aa0 != null) {
				System.arraycopy(aa0, 0, c, 0, aa0Length);
			}
			System.arraycopy(aa1, 0, c, aa0Length, aa1.length);
			aa0 = c;
		}
		return aa0;
	}

	/**@Description:Add crc code
	 *
	 * @author：tan10ler
	 * @createTime：Sep 20, 2018 11:27:18 AM
	 * @updateUser：tan10ler
	 * @UpdateTime：Sep 20, 2018 11:27:18 AM
	 * @throws Exception
	 *
	 * @version：1.0
	 **/
	public static byte[] addCrcCode(byte[] bytes) {
		if (bytes != null) {
			byte[] cBytes = CrcUtil.makeXModem16CRC(bytes);
			bytes = CommonUtil.getListByte(bytes, cBytes);
		}
		return bytes;
	}

	/**
	 * @Description:Split the array
	 *
	 * @author：tan10ler
	 * @createTime：Sep 28, 2018 3:34:23 PM
	 * @updateUser：tan10ler
	 * @UpdateTime：Sep 28, 2018 3:34:23 PM
	 * @throws Exception
	 *
	 * @version：1.0
	 *
	 */
	public static List<byte[]> splitAry(byte[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

		List<byte[]> subAryList = new ArrayList<byte[]>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			int length = subSize;
			if (i == count - 1) {
				length = ary.length - i * subSize;
			}
			byte[] bytes = new byte[length];
			int j = 0;
			while (j < subSize && index < ary.length) {
				bytes[j] = ary[index++];
				j++;
			}
			subAryList.add(bytes);
		}

		return subAryList;
	}

	/**
	 * @Description:Get the peer IP through the ctx object
	 *
	 * @author：tan10ler
	 * @createTime：Oct 30, 2018 9:17:55 AM
	 * @updateUser：tan10ler
	 * @UpdateTime：Oct 30, 2018 9:17:55 AM
	 * @throws Exception
	 *
	 * @version：1.0
	 *
	 */
	public static String getRemoteAddressFromCtx(ChannelHandlerContext ctx) {
		String remoteAddress = ctx.channel().remoteAddress().toString();
		remoteAddress = remoteAddress.substring(1, remoteAddress.indexOf(":"));
		return remoteAddress;
	}



}