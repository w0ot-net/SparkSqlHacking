package org.apache.datasketches.common;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class ArrayOfLongsSerDe extends ArrayOfItemsSerDe {
   public byte[] serializeToByteArray(Long item) {
      Objects.requireNonNull(item, "Item must not be null");
      byte[] byteArr = new byte[8];
      ByteArrayUtil.putLongLE(byteArr, 0, item);
      return byteArr;
   }

   public byte[] serializeToByteArray(Long[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      if (items.length == 0) {
         return new byte[0];
      } else {
         byte[] bytes = new byte[8 * items.length];
         WritableMemory mem = WritableMemory.writableWrap(bytes);
         long offset = 0L;

         for(int i = 0; i < items.length; ++i) {
            mem.putLong(offset, items[i]);
            offset += 8L;
         }

         return bytes;
      }
   }

   public Long[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public Long[] deserializeFromMemory(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return new Long[0];
      } else {
         long offset = offsetBytes;
         Util.checkBounds(offsetBytes, 8L * (long)numItems, mem.getCapacity());
         Long[] array = new Long[numItems];

         for(int i = 0; i < numItems; ++i) {
            array[i] = mem.getLong(offset);
            offset += 8L;
         }

         return array;
      }
   }

   public int sizeOf(Long item) {
      Objects.requireNonNull(item, "Item must not be null");
      return 8;
   }

   public int sizeOf(Long[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      return items.length * 8;
   }

   public int sizeOf(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      return numItems * 8;
   }

   public String toString(Long item) {
      return item == null ? "null" : item.toString();
   }

   public Class getClassOfT() {
      return Long.class;
   }
}
