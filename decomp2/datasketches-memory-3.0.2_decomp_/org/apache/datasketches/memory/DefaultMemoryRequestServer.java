package org.apache.datasketches.memory;

import java.nio.ByteOrder;

public final class DefaultMemoryRequestServer implements MemoryRequestServer {
   private final boolean offHeap;
   private final boolean copyOldToNew;

   public DefaultMemoryRequestServer() {
      this(false, false);
   }

   public DefaultMemoryRequestServer(boolean offHeap, boolean copyOldToNew) {
      this.offHeap = offHeap;
      this.copyOldToNew = copyOldToNew;
   }

   public WritableMemory request(WritableMemory currentWmem, long newCapacityBytes) {
      ByteOrder order = currentWmem.getTypeByteOrder();
      long currentBytes = currentWmem.getCapacity();
      if (newCapacityBytes <= currentBytes) {
         throw new IllegalArgumentException("newCapacityBytes must be &gt; currentBytes");
      } else {
         WritableMemory newWmem;
         if (this.offHeap) {
            newWmem = WritableMemory.allocateDirect(newCapacityBytes, order, this);
         } else {
            if (newCapacityBytes > 2147483647L) {
               throw new IllegalArgumentException("Requested capacity exceeds Integer.MAX_VALUE.");
            }

            newWmem = WritableMemory.allocate((int)newCapacityBytes, order, this);
         }

         if (this.copyOldToNew) {
            currentWmem.copyTo(0L, newWmem, 0L, currentBytes);
         }

         return newWmem;
      }
   }

   public void requestClose(WritableMemory memToClose, WritableMemory newMemory) {
      if (memToClose.isCloseable()) {
         memToClose.close();
      }

   }
}
