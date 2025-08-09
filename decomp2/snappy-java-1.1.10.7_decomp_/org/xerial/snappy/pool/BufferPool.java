package org.xerial.snappy.pool;

import java.nio.ByteBuffer;

public interface BufferPool {
   byte[] allocateArray(int var1);

   void releaseArray(byte[] var1);

   ByteBuffer allocateDirect(int var1);

   void releaseDirect(ByteBuffer var1);
}
