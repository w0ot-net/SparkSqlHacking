package org.apache.datasketches.quantiles;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.datasketches.common.ArrayOfItemsSerDe;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.memory.WritableMemory;

final class ItemsByteArrayImpl {
   private ItemsByteArrayImpl() {
   }

   static byte[] toByteArray(ItemsSketch sketch, boolean ordered, ArrayOfItemsSerDe serDe) {
      boolean empty = sketch.isEmpty();
      int flags = (empty ? 4 : 0) | (ordered ? 16 : 0) | 8;
      if (empty) {
         byte[] outByteArr = new byte[8];
         WritableMemory memOut = WritableMemory.writableWrap(outByteArr);
         int preLongs = 1;
         insertPre0(memOut, 1, flags, sketch.getK());
         return outByteArr;
      } else {
         T[] dataArr = (T[])combinedBufferToItemsArray(sketch, ordered);
         int preLongs = 2;
         byte[] itemsByteArr = serDe.serializeToByteArray(dataArr);
         int numOutBytes = 16 + itemsByteArr.length;
         byte[] outByteArr = new byte[numOutBytes];
         WritableMemory memOut = WritableMemory.writableWrap(outByteArr);
         insertPre0(memOut, 2, flags, sketch.getK());
         PreambleUtil.insertN(memOut, sketch.getN());
         memOut.putByteArray(16L, itemsByteArr, 0, itemsByteArr.length);
         return outByteArr;
      }
   }

   private static Object[] combinedBufferToItemsArray(ItemsSketch sketch, boolean ordered) {
      int extra = 2;
      int outArrCap = sketch.getNumRetained();
      T minItem = (T)sketch.getMinItem();
      T[] outArr = (T[])((Object[])((Object[])Array.newInstance(minItem.getClass(), outArrCap + 2)));
      outArr[0] = minItem;
      outArr[1] = sketch.getMaxItem();
      int baseBufferCount = sketch.getBaseBufferCount();
      Object[] combinedBuffer = sketch.getCombinedBuffer();
      System.arraycopy(combinedBuffer, 0, outArr, 2, baseBufferCount);
      long bitPattern = sketch.getBitPattern();
      if (bitPattern > 0L) {
         int k = sketch.getK();
         int index = 2 + baseBufferCount;

         for(int level = 0; bitPattern != 0L; bitPattern >>>= 1) {
            if ((bitPattern & 1L) > 0L) {
               System.arraycopy(combinedBuffer, (2 + level) * k, outArr, index, k);
               index += k;
            }

            ++level;
         }
      }

      if (ordered) {
         Arrays.sort(outArr, 2, baseBufferCount + 2, sketch.getComparator());
      }

      return outArr;
   }

   private static void insertPre0(WritableMemory wmem, int preLongs, int flags, int k) {
      PreambleUtil.insertPreLongs(wmem, preLongs);
      PreambleUtil.insertSerVer(wmem, 3);
      PreambleUtil.insertFamilyID(wmem, Family.QUANTILES.getID());
      PreambleUtil.insertFlags(wmem, flags);
      PreambleUtil.insertK(wmem, k);
   }
}
