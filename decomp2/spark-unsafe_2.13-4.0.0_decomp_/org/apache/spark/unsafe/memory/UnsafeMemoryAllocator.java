package org.apache.spark.unsafe.memory;

import org.apache.spark.unsafe.Platform;

public class UnsafeMemoryAllocator implements MemoryAllocator {
   public MemoryBlock allocate(long size) throws OutOfMemoryError {
      long address = Platform.allocateMemory(size);
      MemoryBlock memory = new MemoryBlock((Object)null, address, size);
      if (MemoryAllocator.MEMORY_DEBUG_FILL_ENABLED) {
         memory.fill((byte)-91);
      }

      return memory;
   }

   public void free(MemoryBlock memory) {
      assert memory.obj == null : "baseObject not null; are you trying to use the off-heap allocator to free on-heap memory?";

      assert memory.pageNumber != -3 : "page has already been freed";

      assert memory.pageNumber == -1 || memory.pageNumber == -2 : "TMM-allocated pages must be freed via TMM.freePage(), not directly in allocator free()";

      if (MemoryAllocator.MEMORY_DEBUG_FILL_ENABLED) {
         memory.fill((byte)90);
      }

      Platform.freeMemory(memory.offset);
      memory.offset = 0L;
      memory.pageNumber = -3;
   }
}
