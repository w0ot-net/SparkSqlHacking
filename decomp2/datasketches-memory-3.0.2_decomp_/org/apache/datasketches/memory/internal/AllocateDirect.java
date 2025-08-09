package org.apache.datasketches.memory.internal;

import java.util.logging.Logger;

final class AllocateDirect {
   static final Logger LOG = Logger.getLogger(AllocateDirect.class.getCanonicalName());
   private final Deallocator deallocator;
   private final long nativeBaseOffset;
   private final MemoryCleaner cleaner;

   AllocateDirect(long capacityBytes) {
      boolean pageAligned = VirtualMachineMemory.getIsPageAligned();
      long pageSize = (long)getPageSize();
      long allocationSize = capacityBytes + (pageAligned ? pageSize : 0L);

      long nativeAddress;
      try {
         nativeAddress = UnsafeUtil.unsafe.allocateMemory(allocationSize);
      } catch (OutOfMemoryError err) {
         throw new RuntimeException(err);
      }

      if (pageAligned && nativeAddress % pageSize != 0L) {
         this.nativeBaseOffset = (nativeAddress & ~(pageSize - 1L)) + pageSize;
      } else {
         this.nativeBaseOffset = nativeAddress;
      }

      this.deallocator = new Deallocator(nativeAddress);
      this.cleaner = new MemoryCleaner(this, this.deallocator);
   }

   public void close() {
      try {
         if (this.deallocator.deallocate(false)) {
            this.cleaner.clean();
         }
      } finally {
         ResourceImpl.reachabilityFence(this);
      }

   }

   long getNativeBaseOffset() {
      return this.nativeBaseOffset;
   }

   public static int getPageSize() {
      return UnsafeUtil.unsafe.pageSize();
   }

   public StepBoolean getValid() {
      return this.deallocator.getValid();
   }

   private static final class Deallocator implements Runnable {
      private final long nativeAddress;
      private final StepBoolean valid = new StepBoolean(true);

      Deallocator(long nativeAddress) {
         this.nativeAddress = nativeAddress;
      }

      StepBoolean getValid() {
         return this.valid;
      }

      public void run() throws IllegalStateException {
         this.deallocate(true);
      }

      boolean deallocate(boolean calledFromCleaner) throws IllegalStateException {
         if (this.valid.change()) {
            if (calledFromCleaner) {
               AllocateDirect.LOG.warning("A direct resource was not closed explicitly.");
            }

            UnsafeUtil.unsafe.freeMemory(this.nativeAddress);
            return true;
         } else {
            return false;
         }
      }
   }
}
