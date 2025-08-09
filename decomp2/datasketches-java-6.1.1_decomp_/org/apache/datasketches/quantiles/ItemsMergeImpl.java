package org.apache.datasketches.quantiles;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;

final class ItemsMergeImpl {
   private ItemsMergeImpl() {
   }

   static void mergeInto(ItemsSketch src, ItemsSketch tgt) {
      int srcK = src.getK();
      int tgtK = tgt.getK();
      long srcN = src.getN();
      long tgtN = tgt.getN();
      if (srcK != tgtK) {
         downSamplingMergeInto(src, tgt);
      } else {
         Object[] srcCombBuf = src.getCombinedBuffer();
         long nFinal = tgtN + srcN;

         for(int i = 0; i < src.getBaseBufferCount(); ++i) {
            tgt.update(srcCombBuf[i]);
         }

         ItemsUpdateImpl.maybeGrowLevels(tgt, nFinal);
         Object[] scratchBuf = new Object[2 * tgtK];
         long srcBitPattern = src.getBitPattern();

         assert srcBitPattern == srcN / (2L * (long)srcK);

         for(int srcLvl = 0; srcBitPattern != 0L; srcBitPattern >>>= 1) {
            if ((srcBitPattern & 1L) > 0L) {
               ItemsUpdateImpl.inPlacePropagateCarry(srcLvl, srcCombBuf, (2 + srcLvl) * tgtK, scratchBuf, 0, false, tgt);
            }

            ++srcLvl;
         }

         tgt.n_ = nFinal;

         assert tgt.getN() / (2L * (long)tgtK) == tgt.getBitPattern();

         T srcMax = (T)(src.isEmpty() ? null : src.getMaxItem());
         T srcMin = (T)(src.isEmpty() ? null : src.getMinItem());
         T tgtMax = (T)(tgt.isEmpty() ? null : tgt.getMaxItem());
         T tgtMin = (T)(tgt.isEmpty() ? null : tgt.getMinItem());
         if (srcMax != null && tgtMax != null) {
            tgt.maxItem_ = src.getComparator().compare(srcMax, tgtMax) > 0 ? srcMax : tgtMax;
         } else if (tgtMax == null) {
            tgt.maxItem_ = srcMax;
         }

         if (srcMin != null && tgtMin != null) {
            tgt.minItem_ = src.getComparator().compare(srcMin, tgtMin) > 0 ? tgtMin : srcMin;
         } else if (tgtMin == null) {
            tgt.minItem_ = srcMin;
         }

      }
   }

   static void downSamplingMergeInto(ItemsSketch src, ItemsSketch tgt) {
      int sourceK = src.getK();
      int targetK = tgt.getK();
      if (sourceK % targetK != 0) {
         throw new SketchesArgumentException("source.getK() must equal target.getK() * 2^(nonnegative integer).");
      } else {
         int downFactor = sourceK / targetK;
         Util.checkIfPowerOf2((long)downFactor, "source.getK()/target.getK() ratio");
         int lgDownFactor = Integer.numberOfTrailingZeros(downFactor);
         Object[] sourceLevels = src.getCombinedBuffer();
         Object[] sourceBaseBuffer = src.getCombinedBuffer();
         long nFinal = tgt.getN() + src.getN();

         for(int i = 0; i < src.getBaseBufferCount(); ++i) {
            tgt.update(sourceBaseBuffer[i]);
         }

         ItemsUpdateImpl.maybeGrowLevels(tgt, nFinal);
         Object[] scratchBuf = new Object[2 * targetK];
         Object[] downBuf = new Object[targetK];
         long srcBitPattern = src.getBitPattern();

         for(int srcLvl = 0; srcBitPattern != 0L; srcBitPattern >>>= 1) {
            if ((srcBitPattern & 1L) > 0L) {
               justZipWithStride(sourceLevels, (2 + srcLvl) * sourceK, downBuf, 0, targetK, downFactor);
               ItemsUpdateImpl.inPlacePropagateCarry(srcLvl + lgDownFactor, downBuf, 0, scratchBuf, 0, false, tgt);
            }

            ++srcLvl;
         }

         tgt.n_ = nFinal;

         assert tgt.getN() / (2L * (long)targetK) == tgt.getBitPattern();

         T srcMax = (T)(src.isEmpty() ? null : src.getMaxItem());
         T srcMin = (T)(src.isEmpty() ? null : src.getMinItem());
         T tgtMax = (T)(tgt.isEmpty() ? null : tgt.getMaxItem());
         T tgtMin = (T)(tgt.isEmpty() ? null : tgt.getMinItem());
         if (srcMax != null && tgtMax != null) {
            tgt.maxItem_ = src.getComparator().compare(srcMax, tgtMax) > 0 ? srcMax : tgtMax;
         } else if (tgtMax == null) {
            tgt.maxItem_ = srcMax;
         }

         if (srcMin != null && tgtMin != null) {
            tgt.minItem_ = src.getComparator().compare(srcMin, tgtMin) > 0 ? tgtMin : srcMin;
         } else if (tgtMin == null) {
            tgt.minItem_ = srcMin;
         }

      }
   }

   private static void justZipWithStride(Object[] bufSrc, int startSrc, Object[] bufC, int startC, int kC, int stride) {
      int randomOffset = ItemsSketch.rand.nextInt(stride);
      int limC = startC + kC;
      int a = startSrc + randomOffset;

      for(int c = startC; c < limC; ++c) {
         bufC[c] = bufSrc[a];
         a += stride;
      }

   }

   static void blockyTandemMergeSort(Object[] quantiles, long[] cumWts, int arrLen, int blkSize, Comparator comparator) {
      assert blkSize >= 1;

      if (arrLen > blkSize) {
         int numblks = arrLen / blkSize;
         if (numblks * blkSize < arrLen) {
            ++numblks;
         }

         assert numblks * blkSize >= arrLen;

         T[] keyTmp = (T[])Arrays.copyOf(quantiles, arrLen);
         long[] valTmp = Arrays.copyOf(cumWts, arrLen);
         blockyTandemMergeSortRecursion(keyTmp, valTmp, quantiles, cumWts, 0, numblks, blkSize, arrLen, comparator);
      }
   }

   private static void blockyTandemMergeSortRecursion(Object[] qSrc, long[] cwSrc, Object[] qDst, long[] cwDest, int grpStart, int grpLen, int blkSize, int arrLim, Comparator comparator) {
      assert grpLen > 0;

      if (grpLen != 1) {
         int grpLen1 = grpLen / 2;
         int grpLen2 = grpLen - grpLen1;

         assert grpLen1 >= 1;

         assert grpLen2 >= grpLen1;

         int grpStart2 = grpStart + grpLen1;
         blockyTandemMergeSortRecursion(qDst, cwDest, qSrc, cwSrc, grpStart, grpLen1, blkSize, arrLim, comparator);
         blockyTandemMergeSortRecursion(qDst, cwDest, qSrc, cwSrc, grpStart2, grpLen2, blkSize, arrLim, comparator);
         int arrStart1 = grpStart * blkSize;
         int arrStart2 = grpStart2 * blkSize;
         int arrLen1 = grpLen1 * blkSize;
         int arrLen2 = grpLen2 * blkSize;
         if (arrStart2 + arrLen2 > arrLim) {
            arrLen2 = arrLim - arrStart2;
         }

         tandemMerge(qSrc, cwSrc, arrStart1, arrLen1, arrStart2, arrLen2, qDst, cwDest, arrStart1, comparator);
      }
   }

   private static void tandemMerge(Object[] qSrc, long[] cwSrc, int arrStart1, int arrLen1, int arrStart2, int arrLen2, Object[] qDst, long[] cwDst, int arrStart3, Comparator comparator) {
      int arrStop1 = arrStart1 + arrLen1;
      int arrStop2 = arrStart2 + arrLen2;
      int i1 = arrStart1;
      int i2 = arrStart2;
      int i3 = arrStart3;

      while(i1 < arrStop1 && i2 < arrStop2) {
         if (comparator.compare(qSrc[i2], qSrc[i1]) < 0) {
            qDst[i3] = qSrc[i2];
            cwDst[i3] = cwSrc[i2];
            ++i3;
            ++i2;
         } else {
            qDst[i3] = qSrc[i1];
            cwDst[i3] = cwSrc[i1];
            ++i3;
            ++i1;
         }
      }

      if (i1 < arrStop1) {
         System.arraycopy(qSrc, i1, qDst, i3, arrStop1 - i1);
         System.arraycopy(cwSrc, i1, cwDst, i3, arrStop1 - i1);
      } else {
         assert i2 < arrStop2;

         System.arraycopy(qSrc, i2, qDst, i3, arrStop2 - i2);
         System.arraycopy(cwSrc, i2, cwDst, i3, arrStop2 - i2);
      }

   }
}
