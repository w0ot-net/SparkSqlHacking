package org.apache.spark.unsafe.memory;

import javax.annotation.Nullable;
import org.apache.spark.unsafe.Platform;

public class MemoryBlock extends MemoryLocation {
   public static final int NO_PAGE_NUMBER = -1;
   public static final int FREED_IN_TMM_PAGE_NUMBER = -2;
   public static final int FREED_IN_ALLOCATOR_PAGE_NUMBER = -3;
   private final long length;
   public int pageNumber = -1;

   public MemoryBlock(@Nullable Object obj, long offset, long length) {
      super(obj, offset);
      this.length = length;
   }

   public long size() {
      return this.length;
   }

   public static MemoryBlock fromLongArray(long[] array) {
      return new MemoryBlock(array, (long)Platform.LONG_ARRAY_OFFSET, (long)array.length * 8L);
   }

   public void fill(byte value) {
      Platform.setMemory(this.obj, this.offset, this.length, value);
   }
}
