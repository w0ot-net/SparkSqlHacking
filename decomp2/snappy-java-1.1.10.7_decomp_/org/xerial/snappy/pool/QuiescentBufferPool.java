package org.xerial.snappy.pool;

import java.nio.ByteBuffer;

public final class QuiescentBufferPool implements BufferPool {
   private static final QuiescentBufferPool INSTANCE = new QuiescentBufferPool();

   private QuiescentBufferPool() {
   }

   public static BufferPool getInstance() {
      return INSTANCE;
   }

   public byte[] allocateArray(int var1) {
      return new byte[var1];
   }

   public void releaseArray(byte[] var1) {
   }

   public ByteBuffer allocateDirect(int var1) {
      return ByteBuffer.allocateDirect(var1);
   }

   public void releaseDirect(ByteBuffer var1) {
      assert var1 != null && var1.isDirect();

      DirectByteBuffers.releaseDirectByteBuffer(var1);
   }
}
