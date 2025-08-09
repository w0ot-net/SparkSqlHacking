package org.xerial.snappy.buffer;

public class DefaultBufferAllocator implements BufferAllocator {
   public static BufferAllocatorFactory factory = new BufferAllocatorFactory() {
      public BufferAllocator singleton = new DefaultBufferAllocator();

      public BufferAllocator getBufferAllocator(int var1) {
         return this.singleton;
      }
   };

   public byte[] allocate(int var1) {
      return new byte[var1];
   }

   public void release(byte[] var1) {
   }
}
