package org.apache.datasketches.quantiles;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.memory.WritableMemory;

final class DoublesByteArrayImpl {
   private DoublesByteArrayImpl() {
   }

   static byte[] toByteArray(DoublesSketch sketch, boolean ordered, boolean compact) {
      boolean empty = sketch.isEmpty();
      int flags = (empty ? 4 : 0) | (ordered ? 16 : 0) | (compact ? 10 : 0);
      if (empty && !sketch.hasMemory()) {
         byte[] outByteArr = new byte[8];
         WritableMemory memOut = WritableMemory.writableWrap(outByteArr);
         int preLongs = 1;
         insertPre0(memOut, 1, flags, sketch.getK());
         return outByteArr;
      } else {
         return convertToByteArray(sketch, flags, ordered, compact);
      }
   }

   private static byte[] convertToByteArray(DoublesSketch sketch, int flags, boolean ordered, boolean compact) {
      int preLongs = sketch.isEmpty() ? 1 : 2;
      int outBytes = compact ? sketch.getCurrentCompactSerializedSizeBytes() : sketch.getCurrentUpdatableSerializedSizeBytes();
      byte[] outByteArr = new byte[outBytes];
      WritableMemory memOut = WritableMemory.writableWrap(outByteArr);
      int k = sketch.getK();
      insertPre0(memOut, preLongs, flags, k);
      if (sketch.isEmpty()) {
         return outByteArr;
      } else {
         long n = sketch.getN();
         PreambleUtil.insertN(memOut, n);
         PreambleUtil.insertMinDouble(memOut, sketch.isEmpty() ? Double.NaN : sketch.getMinItem());
         PreambleUtil.insertMaxDouble(memOut, sketch.isEmpty() ? Double.NaN : sketch.getMaxItem());
         DoublesSketchAccessor dsa = DoublesSketchAccessor.wrap(sketch, !compact);
         int minAndMax = 2;
         long memOffsetBytes = (long)(preLongs + 2 << 3);
         int bbCnt = ClassicUtil.computeBaseBufferItems(k, n);
         if (bbCnt > 0) {
            double[] bbItemsArr = dsa.getArray(0, bbCnt);
            if (ordered) {
               Arrays.sort(bbItemsArr);
            }

            memOut.putDoubleArray(memOffsetBytes, bbItemsArr, 0, bbCnt);
         }

         memOffsetBytes += (long)((compact ? bbCnt : 2 * k) << 3);
         int totalLevels = ClassicUtil.computeTotalLevels(sketch.getBitPattern());

         for(int lvl = 0; lvl < totalLevels; ++lvl) {
            dsa.setLevel(lvl);
            if (dsa.numItems() > 0) {
               assert dsa.numItems() == k;

               memOut.putDoubleArray(memOffsetBytes, dsa.getArray(0, k), 0, k);
               memOffsetBytes += (long)(k << 3);
            }
         }

         return outByteArr;
      }
   }

   private static void insertPre0(WritableMemory wmem, int preLongs, int flags, int k) {
      PreambleUtil.insertPreLongs(wmem, preLongs);
      PreambleUtil.insertSerVer(wmem, 3);
      PreambleUtil.insertFamilyID(wmem, Family.QUANTILES.getID());
      PreambleUtil.insertFlags(wmem, flags);
      PreambleUtil.insertK(wmem, k);
   }
}
