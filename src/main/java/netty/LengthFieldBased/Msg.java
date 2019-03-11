package netty.LengthFieldBased;

import lombok.Data;
import org.apache.mina.core.buffer.IoBuffer;

/**@Description：TODO
 *
 * @projectName：fcs-tcp
 * @ClassName：Msg
 * @author：tan10ler
 * @createTime：Sep 21, 2018 2:41:47 PM
 * @update：tan10ler
 * @updateDate：Sep 21, 2018 2:41:47 PM
 * @version v1.0
 */
@Data
public class Msg {

    // 帧格式同步头
	private byte[] byteStart = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};

    // 控制器地址 命令字
	private byte order = 0x09;

    // 控制器地址
    private byte[] controlAddress;

    // 计算机地址
    private byte companyAddress;

    // 数据长度
    private byte dataLength;

    // 发送数据
    private byte data;

    // 累加
    private byte[] sumCrc;


    public byte[] getBytes() throws Exception {
        IoBuffer buffer = null;
        try {
            buffer = BufferPool.allocate();
            buffer.put(order);
            buffer.put(controlAddress);
            buffer.put(companyAddress);
            buffer.put(dataLength);
            buffer.put(data);
            buffer.put(sumCrc);
            buffer.flip();
            byte[] buf = new byte[buffer.limit()];
            buffer.get(buf);
            // Add terminator
            buf = CommonUtil.getListByte(byteStart, buf);
            return buf;

        } catch (Throwable t) {
            throw new Exception(t);
        } finally {
            if (buffer != null) {
                buffer.free();
            }
        }

    }


    public static int getSumFromArr(byte[] arr) {
        int sum = 0;
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                sum += (arr[i] & 255);
            }
        }
        return sum;
    }

    /*public static void main(String[] args) throws Exception {

        Msg msg = new Msg();

        byte[] controlAddress = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01};
        msg.setControlAddress(controlAddress);

        byte companyAddress = 0x6E;
        msg.setCompanyAddress(companyAddress);

        byte dataLength = 0x01;
        msg.setDataLength(dataLength);

        byte data = 0x0A;
        msg.setData(data);

        // 累加
        byte[] arr = new byte[]{msg.order, msg.companyAddress, msg.dataLength, msg.data};
        arr = CommonUtil.getListByte(arr, msg.controlAddress);
        short sumFromArr = (short) getSumFromArr(arr);
        msg.setSumCrc(BitUtil.shortToBytesH(sumFromArr));

        byte[] dd = msg.getBytes();

        System.out.println(dd);
    }
*/


	/*private String address = "00";
	private String frameType;
	private byte[] data;
	private int crc;
	private byte frameEnd = 0x03;*/

}
