package org.apache.hadoop.hive.common.io.encoded;

import java.nio.ByteBuffer;

public interface MemoryBuffer {
   ByteBuffer getByteBufferRaw();

   ByteBuffer getByteBufferDup();
}
