package org.apache.parquet.bytes;

import java.nio.ByteBuffer;

public class HeapByteBufferAllocator implements ByteBufferAllocator {
   public static final HeapByteBufferAllocator getInstance() {
      return new HeapByteBufferAllocator();
   }

   public ByteBuffer allocate(int size) {
      return ByteBuffer.allocate(size);
   }

   public void release(ByteBuffer b) {
   }

   public boolean isDirect() {
      return false;
   }
}
