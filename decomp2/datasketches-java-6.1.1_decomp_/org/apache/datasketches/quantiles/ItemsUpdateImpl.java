package org.apache.datasketches.quantiles;

import java.util.Arrays;
import java.util.Comparator;

final class ItemsUpdateImpl {
   private ItemsUpdateImpl() {
   }

   static void maybeGrowLevels(ItemsSketch sketch, long newN) {
      int k = sketch.getK();
      int numLevelsNeeded = ClassicUtil.computeNumLevelsNeeded(k, newN);
      if (numLevelsNeeded != 0) {
         assert newN >= 2L * (long)k;

         assert numLevelsNeeded > 0;

         int spaceNeeded = (2 + numLevelsNeeded) * k;
         if (spaceNeeded > sketch.getCombinedBufferAllocatedCount()) {
            sketch.combinedBuffer_ = Arrays.copyOf(sketch.getCombinedBuffer(), spaceNeeded);
            sketch.combinedBufferItemCapacity_ = spaceNeeded;
         }
      }
   }

   static void inPlacePropagateCarry(int startingLevel, Object[] sizeKBuf, int sizeKStart, Object[] size2KBuf, int size2KStart, boolean doUpdateVersion, ItemsSketch sketch) {
      Object[] levelsArr = sketch.getCombinedBuffer();
      long bitPattern = sketch.getBitPattern();
      int k = sketch.getK();
      int endingLevel = ClassicUtil.lowestZeroBitStartingAt(bitPattern, startingLevel);
      if (doUpdateVersion) {
         zipSize2KBuffer(size2KBuf, size2KStart, levelsArr, (2 + endingLevel) * k, k);
      } else {
         System.arraycopy(sizeKBuf, sizeKStart, levelsArr, (2 + endingLevel) * k, k);
      }

      for(int lvl = startingLevel; lvl < endingLevel; ++lvl) {
         assert (bitPattern & 1L << lvl) > 0L;

         mergeTwoSizeKBuffers(levelsArr, (2 + lvl) * k, levelsArr, (2 + endingLevel) * k, size2KBuf, size2KStart, k, sketch.getComparator());
         zipSize2KBuffer(size2KBuf, size2KStart, levelsArr, (2 + endingLevel) * k, k);
         Arrays.fill(levelsArr, (2 + lvl) * k, (2 + lvl + 1) * k, (Object)null);
      }

      sketch.bitPattern_ = bitPattern + (1L << startingLevel);
   }

   private static void zipSize2KBuffer(Object[] bufA, int startA, Object[] bufC, int startC, int k) {
      int randomOffset = ItemsSketch.rand.nextBoolean() ? 1 : 0;
      int limC = startC + k;
      int a = startA + randomOffset;

      for(int c = startC; c < limC; ++c) {
         bufC[c] = bufA[a];
         a += 2;
      }

   }

   private static void mergeTwoSizeKBuffers(Object[] keySrc1, int arrStart1, Object[] keySrc2, int arrStart2, Object[] keyDst, int arrStart3, int k, Comparator comparator) {
      int arrStop1 = arrStart1 + k;
      int arrStop2 = arrStart2 + k;
      int i1 = arrStart1;
      int i2 = arrStart2;
      int i3 = arrStart3;

      while(i1 < arrStop1 && i2 < arrStop2) {
         if (comparator.compare(keySrc2[i2], keySrc1[i1]) < 0) {
            keyDst[i3++] = keySrc2[i2++];
         } else {
            keyDst[i3++] = keySrc1[i1++];
         }
      }

      if (i1 < arrStop1) {
         System.arraycopy(keySrc1, i1, keyDst, i3, arrStop1 - i1);
      } else {
         assert i2 < arrStop2;

         System.arraycopy(keySrc1, i2, keyDst, i3, arrStop2 - i2);
      }

   }
}
