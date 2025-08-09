package org.apache.arrow.memory;

public abstract class ForeignAllocation {
   private final long memoryAddress;
   private final long size;

   protected ForeignAllocation(long size, long memoryAddress) {
      this.memoryAddress = memoryAddress;
      this.size = size;
   }

   public long getSize() {
      return this.size;
   }

   protected long memoryAddress() {
      return this.memoryAddress;
   }

   protected abstract void release0();
}
