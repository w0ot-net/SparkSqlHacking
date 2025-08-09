package org.apache.spark.unsafe.memory;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.unsafe.Platform;

public class HeapMemoryAllocator implements MemoryAllocator {
   @GuardedBy("this")
   private final Map bufferPoolsBySize = new HashMap();
   private static final int POOLING_THRESHOLD_BYTES = 1048576;

   private boolean shouldPool(long size) {
      return size >= 1048576L;
   }

   public MemoryBlock allocate(long size) throws OutOfMemoryError {
      int numWords = (int)((size + 7L) / 8L);
      long alignedSize = (long)numWords * 8L;

      assert alignedSize >= size;

      if (this.shouldPool(alignedSize)) {
         synchronized(this) {
            LinkedList<WeakReference<long[]>> pool = (LinkedList)this.bufferPoolsBySize.get(alignedSize);
            if (pool != null) {
               while(!pool.isEmpty()) {
                  WeakReference<long[]> arrayReference = (WeakReference)pool.pop();
                  long[] array = (long[])arrayReference.get();
                  if (array != null) {
                     assert (long)array.length * 8L >= size;

                     MemoryBlock memory = new MemoryBlock(array, (long)Platform.LONG_ARRAY_OFFSET, size);
                     if (MemoryAllocator.MEMORY_DEBUG_FILL_ENABLED) {
                        memory.fill((byte)-91);
                     }

                     return memory;
                  }
               }

               this.bufferPoolsBySize.remove(alignedSize);
            }
         }
      }

      long[] array = new long[numWords];
      MemoryBlock memory = new MemoryBlock(array, (long)Platform.LONG_ARRAY_OFFSET, size);
      if (MemoryAllocator.MEMORY_DEBUG_FILL_ENABLED) {
         memory.fill((byte)-91);
      }

      return memory;
   }

   public void free(MemoryBlock memory) {
      assert memory.obj != null : "baseObject was null; are you trying to use the on-heap allocator to free off-heap memory?";

      assert memory.pageNumber != -3 : "page has already been freed";

      assert memory.pageNumber == -1 || memory.pageNumber == -2 : "TMM-allocated pages must first be freed via TMM.freePage(), not directly in allocator free()";

      long size = memory.size();
      if (MemoryAllocator.MEMORY_DEBUG_FILL_ENABLED) {
         memory.fill((byte)90);
      }

      memory.pageNumber = -3;
      long[] array = (long[])memory.obj;
      memory.setObjAndOffset((Object)null, 0L);
      long alignedSize = (size + 7L) / 8L * 8L;
      if (this.shouldPool(alignedSize)) {
         synchronized(this) {
            LinkedList<WeakReference<long[]>> pool = (LinkedList)this.bufferPoolsBySize.computeIfAbsent(alignedSize, (k) -> new LinkedList());
            pool.add(new WeakReference(array));
         }
      }

   }
}
