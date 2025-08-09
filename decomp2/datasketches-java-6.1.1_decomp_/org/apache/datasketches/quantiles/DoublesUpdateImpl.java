package org.apache.datasketches.quantiles;

final class DoublesUpdateImpl {
   private DoublesUpdateImpl() {
   }

   static int getRequiredItemCapacity(int k, long newN) {
      int numLevelsNeeded = ClassicUtil.computeNumLevelsNeeded(k, newN);
      if (numLevelsNeeded == 0) {
         return 2 * k;
      } else {
         assert newN >= 2L * (long)k;

         assert numLevelsNeeded > 0;

         int spaceNeeded = (2 + numLevelsNeeded) * k;
         return spaceNeeded;
      }
   }

   static long inPlacePropagateCarry(int startingLevel, DoublesBufferAccessor optSrcKBuf, DoublesBufferAccessor size2KBuf, boolean doUpdateVersion, int k, DoublesSketchAccessor tgtSketchBuf, long bitPattern) {
      int endingLevel = ClassicUtil.lowestZeroBitStartingAt(bitPattern, startingLevel);
      tgtSketchBuf.setLevel(endingLevel);
      if (doUpdateVersion) {
         zipSize2KBuffer(size2KBuf, tgtSketchBuf);
      } else {
         assert optSrcKBuf != null;

         tgtSketchBuf.putArray(optSrcKBuf.getArray(0, k), 0, 0, k);
      }

      for(int lvl = startingLevel; lvl < endingLevel; ++lvl) {
         assert (bitPattern & 1L << lvl) > 0L;

         DoublesSketchAccessor currLevelBuf = tgtSketchBuf.copyAndSetLevel(lvl);
         mergeTwoSizeKBuffers(currLevelBuf, tgtSketchBuf, size2KBuf);
         zipSize2KBuffer(size2KBuf, tgtSketchBuf);
      }

      return bitPattern + (1L << startingLevel);
   }

   private static void zipSize2KBuffer(DoublesBufferAccessor bufIn, DoublesBufferAccessor bufOut) {
      int randomOffset = DoublesSketch.rand.nextBoolean() ? 1 : 0;
      int limOut = bufOut.numItems();
      int idxIn = randomOffset;

      for(int idxOut = 0; idxOut < limOut; ++idxOut) {
         bufOut.set(idxOut, bufIn.get(idxIn));
         idxIn += 2;
      }

   }

   private static void mergeTwoSizeKBuffers(DoublesBufferAccessor src1, DoublesBufferAccessor src2, DoublesBufferAccessor dst) {
      assert src1.numItems() == src2.numItems();

      int k = src1.numItems();
      int i1 = 0;
      int i2 = 0;
      int iDst = 0;

      while(i1 < k && i2 < k) {
         if (src2.get(i2) < src1.get(i1)) {
            dst.set(iDst++, src2.get(i2++));
         } else {
            dst.set(iDst++, src1.get(i1++));
         }
      }

      if (i1 < k) {
         int numItems = k - i1;
         dst.putArray(src1.getArray(i1, numItems), 0, iDst, numItems);
      } else {
         int numItems = k - i2;
         dst.putArray(src2.getArray(i2, numItems), 0, iDst, numItems);
      }

   }
}
