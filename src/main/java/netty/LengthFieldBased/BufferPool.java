package netty.LengthFieldBased;

import org.apache.mina.core.buffer.CachedBufferAllocator;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * @Description：Cache tool class
 *
 * @projectName：fcs-tcp
 * @ClassName：BufferPool
 * @author：tan10ler
 * @createTime：Sep 26, 2018 2:43:05 PM
 * @update：tan10ler
 * @updateDate：Sep 26, 2018 2:43:05 PM
 * @version v1.0
 */
public final class BufferPool {

	private static final int K1 = 1024;

	private BufferPool() {
	}

	static {
		CachedBufferAllocator allocator = new CachedBufferAllocator();
		IoBuffer.setAllocator(allocator);
	}

	/**
	 * @Description:Allocate a buffer whose size is 1024 bytes
	 *
	 * @author：tan10ler
	 * @createTime：Sep 26, 2018 2:42:58 PM
	 * @updateUser：tan10ler
	 * @UpdateTime：Sep 26, 2018 2:42:58 PM
	 * @throws Exception
	 *
	 * @version：1.0
	 *
	 */
	public static IoBuffer allocate() {
		return IoBuffer.allocate(K1).setAutoExpand(true);
	}

	/**
	 * @Description:Allocate a buffer
	 *
	 * @author：tan10ler
	 * @createTime：Sep 26, 2018 2:42:48 PM
	 * @updateUser：tan10ler
	 * @UpdateTime：Sep 26, 2018 2:42:48 PM
	 * @throws Exception
	 *
	 * @version：1.0
	 *
	 */
	public static IoBuffer allocate(int size) {
		return IoBuffer.allocate(size).setAutoExpand(true);
	}

}