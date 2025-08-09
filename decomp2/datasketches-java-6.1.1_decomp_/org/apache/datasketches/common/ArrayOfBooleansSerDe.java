package org.apache.datasketches.common;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class ArrayOfBooleansSerDe extends ArrayOfItemsSerDe {
   public static int computeBytesNeeded(int arrayLength) {
      return (arrayLength >>> 3) + ((arrayLength & 7) > 0 ? 1 : 0);
   }

   public byte[] serializeToByteArray(Boolean item) {
      Objects.requireNonNull(item, "Item must not be null");
      byte[] bytes = new byte[1];
      bytes[0] = (byte)(item ? 1 : 0);
      return bytes;
   }

   public byte[] serializeToByteArray(Boolean[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      int bytesNeeded = computeBytesNeeded(items.length);
      byte[] bytes = new byte[bytesNeeded];
      WritableMemory mem = WritableMemory.writableWrap(bytes);
      byte val = 0;

      for(int i = 0; i < items.length; ++i) {
         if (items[i]) {
            val = (byte)(val | 1 << (i & 7));
         }

         if ((i & 7) == 7) {
            mem.putByte((long)(i >>> 3), val);
            val = 0;
         }
      }

      if ((items.length & 7) > 0) {
         mem.putByte((long)(bytesNeeded - 1), val);
      }

      return bytes;
   }

   public Boolean[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public Boolean[] deserializeFromMemory(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return new Boolean[0];
      } else {
         int numBytes = computeBytesNeeded(numItems);
         Util.checkBounds(offsetBytes, (long)numBytes, mem.getCapacity());
         Boolean[] array = new Boolean[numItems];
         byte srcVal = 0;
         int i = 0;

         for(int b = 0; i < numItems; ++i) {
            if ((i & 7) == 0) {
               srcVal = mem.getByte(offsetBytes + (long)(b++));
            }

            array[i] = (srcVal >>> (i & 7) & 1) == 1;
         }

         return array;
      }
   }

   public int sizeOf(Boolean item) {
      Objects.requireNonNull(item, "Item must not be null");
      return computeBytesNeeded(1);
   }

   public int sizeOf(Boolean[] items) {
      Objects.requireNonNull(items, "Item must not be null");
      return computeBytesNeeded(items.length);
   }

   public int sizeOf(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      return computeBytesNeeded(numItems);
   }

   public String toString(Boolean item) {
      if (item == null) {
         return "null";
      } else {
         return item ? "true" : "false";
      }
   }

   public Class getClassOfT() {
      return Boolean.class;
   }
}
