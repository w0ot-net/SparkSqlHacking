package org.apache.parquet.bytes;

import java.nio.ByteBuffer;

public interface ByteBufferAllocator {
   ByteBuffer allocate(int var1);

   void release(ByteBuffer var1);

   boolean isDirect();
}
