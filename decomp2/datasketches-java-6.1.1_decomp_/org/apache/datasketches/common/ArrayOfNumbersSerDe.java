package org.apache.datasketches.common;

import java.util.Objects;
import org.apache.datasketches.memory.Memory;

public class ArrayOfNumbersSerDe extends ArrayOfItemsSerDe {
   private static final byte LONG_INDICATOR = 12;
   private static final byte INTEGER_INDICATOR = 9;
   private static final byte SHORT_INDICATOR = 3;
   private static final byte BYTE_INDICATOR = 2;
   private static final byte DOUBLE_INDICATOR = 4;
   private static final byte FLOAT_INDICATOR = 6;

   public byte[] serializeToByteArray(Number item) {
      Objects.requireNonNull(item, "Item must not be null");
      byte[] byteArr;
      if (item instanceof Long) {
         byteArr = new byte[9];
         byteArr[0] = 12;
         ByteArrayUtil.putLongLE(byteArr, 1, (Long)item);
      } else if (item instanceof Integer) {
         byteArr = new byte[5];
         byteArr[0] = 9;
         ByteArrayUtil.putIntLE(byteArr, 1, (Integer)item);
      } else if (item instanceof Short) {
         byteArr = new byte[3];
         byteArr[0] = 3;
         ByteArrayUtil.putShortLE(byteArr, 1, (Short)item);
      } else if (item instanceof Byte) {
         byteArr = new byte[2];
         byteArr[0] = 2;
         byteArr[1] = (Byte)item;
      } else if (item instanceof Double) {
         byteArr = new byte[9];
         byteArr[0] = 4;
         ByteArrayUtil.putDoubleLE(byteArr, 1, (Double)item);
      } else {
         if (!(item instanceof Float)) {
            throw new SketchesArgumentException("Item must be one of: Long, Integer, Short, Byte, Double, Float. item: " + item.toString());
         }

         byteArr = new byte[5];
         byteArr[0] = 6;
         ByteArrayUtil.putFloatLE(byteArr, 1, (Float)item);
      }

      return byteArr;
   }

   public byte[] serializeToByteArray(Number[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      int numItems = items.length;
      int totalBytes = 0;
      byte[][] serialized2DArray = new byte[numItems][];

      for(int i = 0; i < numItems; ++i) {
         serialized2DArray[i] = this.serializeToByteArray(items[i]);
         totalBytes += serialized2DArray[i].length;
      }

      byte[] out = new byte[totalBytes];
      int offset = 0;

      for(int i = 0; i < numItems; ++i) {
         int itemLen = serialized2DArray[i].length;
         ByteArrayUtil.copyBytes(serialized2DArray[i], 0, out, offset, itemLen);
         offset += itemLen;
      }

      return out;
   }

   public Number[] deserializeFromMemory(Memory mem, int numItems) {
      return this.deserializeFromMemory(mem, 0L, numItems);
   }

   public Number[] deserializeFromMemory(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      if (numItems <= 0) {
         return new Number[0];
      } else {
         Number[] array = new Number[numItems];
         long offset = offsetBytes;

         for(int i = 0; i < numItems; ++i) {
            Util.checkBounds(offset, 1L, mem.getCapacity());
            byte typeId = mem.getByte(offset);
            ++offset;
            switch (typeId) {
               case 2:
                  Util.checkBounds(offset, 1L, mem.getCapacity());
                  array[i] = mem.getByte(offset);
                  ++offset;
                  break;
               case 3:
                  Util.checkBounds(offset, 2L, mem.getCapacity());
                  array[i] = mem.getShort(offset);
                  offset += 2L;
                  break;
               case 4:
                  Util.checkBounds(offset, 8L, mem.getCapacity());
                  array[i] = mem.getDouble(offset);
                  offset += 8L;
                  break;
               case 5:
               case 7:
               case 8:
               case 10:
               case 11:
               default:
                  throw new SketchesArgumentException("Item must be one of: Long, Integer, Short, Byte, Double, Float. index: " + i + ", typeId: " + typeId);
               case 6:
                  Util.checkBounds(offset, 4L, mem.getCapacity());
                  array[i] = mem.getFloat(offset);
                  offset += 4L;
                  break;
               case 9:
                  Util.checkBounds(offset, 4L, mem.getCapacity());
                  array[i] = mem.getInt(offset);
                  offset += 4L;
                  break;
               case 12:
                  Util.checkBounds(offset, 8L, mem.getCapacity());
                  array[i] = mem.getLong(offset);
                  offset += 8L;
            }
         }

         return array;
      }
   }

   public int sizeOf(Number item) {
      Objects.requireNonNull(item, "Item must not be null");
      if (item instanceof Long) {
         return 9;
      } else if (item instanceof Integer) {
         return 5;
      } else if (item instanceof Short) {
         return 3;
      } else if (item instanceof Byte) {
         return 2;
      } else if (item instanceof Double) {
         return 9;
      } else if (item instanceof Float) {
         return 5;
      } else {
         throw new SketchesArgumentException("Item must be one of: Long, Integer, Short, Byte, Double, Float. item: " + item.toString());
      }
   }

   public int sizeOf(Number[] items) {
      Objects.requireNonNull(items, "Items must not be null");
      int totalBytes = 0;

      for(Number item : items) {
         totalBytes += this.sizeOf(item);
      }

      return totalBytes;
   }

   public int sizeOf(Memory mem, long offsetBytes, int numItems) {
      Objects.requireNonNull(mem, "Memory must not be null");
      long offset = offsetBytes;

      for(int i = 0; i < numItems; ++i) {
         Util.checkBounds(offset, 1L, mem.getCapacity());
         byte typeId = mem.getByte(offset);
         ++offset;
         switch (typeId) {
            case 2:
               Util.checkBounds(offset, 1L, mem.getCapacity());
               ++offset;
               break;
            case 3:
               Util.checkBounds(offset, 2L, mem.getCapacity());
               offset += 2L;
               break;
            case 4:
               Util.checkBounds(offset, 8L, mem.getCapacity());
               offset += 8L;
               break;
            case 5:
            case 7:
            case 8:
            case 10:
            case 11:
            default:
               throw new SketchesArgumentException("Item must be one of: Long, Integer, Short, Byte, Double, Float. index: " + i + ", typeId: " + typeId);
            case 6:
               Util.checkBounds(offset, 4L, mem.getCapacity());
               offset += 4L;
               break;
            case 9:
               Util.checkBounds(offset, 4L, mem.getCapacity());
               offset += 4L;
               break;
            case 12:
               Util.checkBounds(offset, 8L, mem.getCapacity());
               offset += 8L;
         }
      }

      return (int)(offset - offsetBytes);
   }

   public String toString(Number item) {
      return item == null ? "null" : item.toString();
   }

   public Class getClassOfT() {
      return Number.class;
   }
}
