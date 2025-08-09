package org.apache.arrow.memory;

public interface AllocationListener {
   AllocationListener NOOP = new AllocationListener() {
   };

   default void onPreAllocation(long size) {
   }

   default void onAllocation(long size) {
   }

   default void onRelease(long size) {
   }

   default boolean onFailedAllocation(long size, AllocationOutcome outcome) {
      return false;
   }

   default void onChildAdded(BufferAllocator parentAllocator, BufferAllocator childAllocator) {
   }

   default void onChildRemoved(BufferAllocator parentAllocator, BufferAllocator childAllocator) {
   }
}
