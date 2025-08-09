package org.apache.arrow.memory;

public interface ReferenceManager {
   String NO_OP_ERROR_MESSAGE = "Operation not supported on NO_OP Reference Manager";
   ReferenceManager NO_OP = new ReferenceManager() {
      public int getRefCount() {
         return 1;
      }

      public boolean release() {
         return false;
      }

      public boolean release(int decrement) {
         return false;
      }

      public void retain() {
      }

      public void retain(int increment) {
      }

      public ArrowBuf retain(ArrowBuf srcBuffer, BufferAllocator targetAllocator) {
         return srcBuffer;
      }

      public ArrowBuf deriveBuffer(ArrowBuf sourceBuffer, long index, long length) {
         return sourceBuffer;
      }

      public OwnershipTransferResult transferOwnership(ArrowBuf sourceBuffer, BufferAllocator targetAllocator) {
         return new OwnershipTransferNOOP(sourceBuffer);
      }

      public BufferAllocator getAllocator() {
         return new RootAllocator(0L);
      }

      public long getSize() {
         return 0L;
      }

      public long getAccountedSize() {
         return 0L;
      }
   };

   int getRefCount();

   boolean release();

   boolean release(int var1);

   void retain();

   void retain(int var1);

   ArrowBuf retain(ArrowBuf var1, BufferAllocator var2);

   ArrowBuf deriveBuffer(ArrowBuf var1, long var2, long var4);

   OwnershipTransferResult transferOwnership(ArrowBuf var1, BufferAllocator var2);

   BufferAllocator getAllocator();

   long getSize();

   long getAccountedSize();
}
