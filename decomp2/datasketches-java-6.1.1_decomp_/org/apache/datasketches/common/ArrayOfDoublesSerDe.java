package org.apache.datasketches.common;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class ArrayOfDoublesSerDe extends ArrayOfItemsSerDe {
   public byte[] serializeToByteArray(Double item) {
      Objects.requireNonNull(item, "Item must not be null");
      byte[] byteArr = new byte[8];
      ByteArrayUtil.putDoubleLE(byteArr, 0, item);
      return byteArr;
   }

   public byte[] serializeToByteArray(Double[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      if (items.length == 0) {
         return new byte[0];
      } else {
         byte[] bytes = new byte[8 * items.length];
         WritableMemory mem = WritableMemory.writableWrap(bytes);
         long offset = 0L;

         for(int i = 0; i < items.length; ++i) {
            mem.putDouble(offset, items[i]);
            offset += 8L;
         }

         return bytes;
      }
   }

   public Double[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public Double[] deserializeFromMemory(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return new Double[0];
      } else {
         long offset = offsetBytes;
         Util.checkBounds(offsetBytes, 8L * (long)numItems, mem.getCapacity());
         Double[] array = new Double[numItems];

         for(int i = 0; i < numItems; ++i) {
            array[i] = mem.getDouble(offset);
            offset += 8L;
         }

         return array;
      }
   }

   public int sizeOf(Double item) {
      Objects.requireNonNull(item, "Item must not be null");
      return 8;
   }

   public int sizeOf(Double[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      return items.length * 8;
   }

   public int sizeOf(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      return numItems * 8;
   }

   public String toString(Double item) {
      return item == null ? "null" : item.toString();
   }

   public Class getClassOfT() {
      return Double.class;
   }
}
