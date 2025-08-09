package org.apache.parquet.bytes;

import java.nio.ByteBuffer;

public class DirectByteBufferAllocator implements ByteBufferAllocator {
   public static final DirectByteBufferAllocator getInstance() {
      return new DirectByteBufferAllocator();
   }

   public ByteBuffer allocate(int size) {
      return ByteBuffer.allocateDirect(size);
   }

   public void release(ByteBuffer b) {
   }

   public boolean isDirect() {
      return true;
   }
}
