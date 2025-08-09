package org.apache.datasketches.common;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;

public abstract class ArrayOfItemsSerDe {
   public abstract byte[] serializeToByteArray(Object var1);

   public abstract byte[] serializeToByteArray(Object[] var1);

   public Object[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public abstract Object[] deserializeFromMemory(Memory var1, long var2, int var4);

   public abstract int sizeOf(Object var1);

   public int sizeOf(Object[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      int totalBytes = 0;

      for(int i = 0; i < items.length; ++i) {
         totalBytes += this.sizeOf(items[i]);
      }

      return totalBytes;
   }

   public abstract int sizeOf(Memory var1, long var2, int var4);

   public abstract String toString(Object var1);

   public abstract Class getClassOfT();
}
