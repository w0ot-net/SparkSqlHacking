package org.xerial.snappy.buffer;

public interface BufferAllocator {
   byte[] allocate(int var1);

   void release(byte[] var1);
}
