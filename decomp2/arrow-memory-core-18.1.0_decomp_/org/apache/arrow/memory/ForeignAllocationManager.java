package org.apache.arrow.memory;

class ForeignAllocationManager extends AllocationManager {
   private final ForeignAllocation allocation;

   protected ForeignAllocationManager(BufferAllocator accountingAllocator, ForeignAllocation allocation) {
      super(accountingAllocator);
      this.allocation = allocation;
   }

   public long getSize() {
      return this.allocation.getSize();
   }

   protected long memoryAddress() {
      return this.allocation.memoryAddress();
   }

   protected void release0() {
      this.allocation.release0();
   }
}
