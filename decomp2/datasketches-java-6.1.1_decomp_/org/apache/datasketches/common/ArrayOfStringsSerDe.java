package org.apache.datasketches.common;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.datasketches.memory.Memory;

public class ArrayOfStringsSerDe extends ArrayOfItemsSerDe {
   public byte[] serializeToByteArray(String item) {
      Objects.requireNonNull(item, "Item must not be null");
      if (item.isEmpty()) {
         return new byte[]{0, 0, 0, 0};
      } else {
         byte[] utf8ByteArr = item.getBytes(StandardCharsets.UTF_8);
         int numBytes = utf8ByteArr.length;
         byte[] out = new byte[numBytes + 4];
         ByteArrayUtil.copyBytes(utf8ByteArr, 0, out, 4, numBytes);
         ByteArrayUtil.putIntLE(out, 0, numBytes);
         return out;
      }
   }

   public byte[] serializeToByteArray(String[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      if (items.length == 0) {
         return new byte[0];
      } else {
         int totalBytes = 0;
         int numItems = items.length;
         byte[][] serialized2DArray = new byte[numItems][];

         for(int i = 0; i < numItems; ++i) {
            serialized2DArray[i] = items[i].getBytes(StandardCharsets.UTF_8);
            totalBytes += serialized2DArray[i].length + 4;
         }

         byte[] bytesOut = new byte[totalBytes];
         int offset = 0;

         for(int i = 0; i < numItems; ++i) {
            int utf8len = serialized2DArray[i].length;
            ByteArrayUtil.putIntLE(bytesOut, offset, utf8len);
            offset += 4;
            ByteArrayUtil.copyBytes(serialized2DArray[i], 0, bytesOut, offset, utf8len);
            offset += utf8len;
         }

         return bytesOut;
      }
   }

   public String[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public String[] deserializeFromMemory(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return new String[0];
      } else {
         String[] array = new String[numItems];
         long offset = offsetBytes;

         for(int i = 0; i < numItems; ++i) {
            Util.checkBounds(offset, 4L, mem.getCapacity());
            int strLength = mem.getInt(offset);
            offset += 4L;
            byte[] utf8Bytes = new byte[strLength];
            Util.checkBounds(offset, (long)strLength, mem.getCapacity());
            mem.getByteArray(offset, utf8Bytes, 0, strLength);
            offset += (long)strLength;
            array[i] = new String(utf8Bytes, StandardCharsets.UTF_8);
         }

         return array;
      }
   }

   public int sizeOf(String item) {
      Objects.requireNonNull(item, "Item must not be null");
      return item.isEmpty() ? 4 : item.getBytes(StandardCharsets.UTF_8).length + 4;
   }

   public int sizeOf(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return 0;
      } else {
         long offset = offsetBytes;
         long memCap = mem.getCapacity();

         for(int i = 0; i < numItems; ++i) {
            Util.checkBounds(offset, 4L, memCap);
            int itemLenBytes = mem.getInt(offset);
            offset += 4L;
            Util.checkBounds(offset, (long)itemLenBytes, memCap);
            offset += (long)itemLenBytes;
         }

         return (int)(offset - offsetBytes);
      }
   }

   public String toString(String item) {
      return item == null ? "null" : item;
   }

   public Class getClassOfT() {
      return String.class;
   }
}
