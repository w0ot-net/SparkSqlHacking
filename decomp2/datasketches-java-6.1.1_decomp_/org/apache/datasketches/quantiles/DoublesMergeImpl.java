package org.apache.datasketches.quantiles;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.WritableMemory;

final class DoublesMergeImpl {
   private DoublesMergeImpl() {
   }

   static void mergeInto(DoublesSketch src, UpdateDoublesSketch tgt) {
      int srcK = src.getK();
      int tgtK = tgt.getK();
      long srcN = src.getN();
      long tgtN = tgt.getN();
      if (srcK != tgtK) {
         downSamplingMergeInto(src, tgt);
      } else {
         DoublesSketchAccessor srcSketchBuf = DoublesSketchAccessor.wrap(src);
         long nFinal = tgtN + srcN;

         for(int i = 0; i < srcSketchBuf.numItems(); ++i) {
            tgt.update(srcSketchBuf.get(i));
         }

         int spaceNeeded = DoublesUpdateImpl.getRequiredItemCapacity(tgtK, nFinal);
         int tgtCombBufItemCap = tgt.getCombinedBufferItemCapacity();
         if (spaceNeeded > tgtCombBufItemCap) {
            tgt.growCombinedBuffer(tgtCombBufItemCap, spaceNeeded);
         }

         DoublesArrayAccessor scratch2KAcc = DoublesArrayAccessor.initialize(2 * tgtK);
         long srcBitPattern = src.getBitPattern();

         assert srcBitPattern == srcN / (2L * (long)srcK);

         DoublesSketchAccessor tgtSketchBuf = DoublesSketchAccessor.wrap(tgt, true);
         long newTgtBitPattern = tgt.getBitPattern();

         for(int srcLvl = 0; srcBitPattern != 0L; srcBitPattern >>>= 1) {
            if ((srcBitPattern & 1L) > 0L) {
               newTgtBitPattern = DoublesUpdateImpl.inPlacePropagateCarry(srcLvl, srcSketchBuf.setLevel(srcLvl), scratch2KAcc, false, tgtK, tgtSketchBuf, newTgtBitPattern);
            }

            ++srcLvl;
         }

         if (tgt.hasMemory() && nFinal > 0L) {
            WritableMemory mem = tgt.getMemory();
            mem.clearBits(3L, (byte)4);
         }

         tgt.putN(nFinal);
         tgt.putBitPattern(newTgtBitPattern);

         assert tgt.getN() / (2L * (long)tgtK) == tgt.getBitPattern();

         double srcMax = src.getMaxItem();
         srcMax = Double.isNaN(srcMax) ? Double.NEGATIVE_INFINITY : srcMax;
         double srcMin = src.getMinItem();
         srcMin = Double.isNaN(srcMin) ? Double.POSITIVE_INFINITY : srcMin;
         double tgtMax = tgt.getMaxItem();
         tgtMax = Double.isNaN(tgtMax) ? Double.NEGATIVE_INFINITY : tgtMax;
         double tgtMin = tgt.getMinItem();
         tgtMin = Double.isNaN(tgtMin) ? Double.POSITIVE_INFINITY : tgtMin;
         tgt.putMaxItem(Math.max(srcMax, tgtMax));
         tgt.putMinItem(Math.min(srcMin, tgtMin));
      }
   }

   static void downSamplingMergeInto(DoublesSketch src, UpdateDoublesSketch tgt) {
      int sourceK = src.getK();
      int targetK = tgt.getK();
      long tgtN = tgt.getN();
      if (sourceK % targetK != 0) {
         throw new SketchesArgumentException("source.getK() must equal target.getK() * 2^(nonnegative integer).");
      } else {
         int downFactor = sourceK / targetK;
         Util.checkIfPowerOf2((long)downFactor, "source.getK()/target.getK() ratio");
         int lgDownFactor = Integer.numberOfTrailingZeros(downFactor);
         if (!src.isEmpty()) {
            DoublesSketchAccessor srcSketchBuf = DoublesSketchAccessor.wrap(src);
            long nFinal = tgtN + src.getN();

            for(int i = 0; i < srcSketchBuf.numItems(); ++i) {
               tgt.update(srcSketchBuf.get(i));
            }

            int spaceNeeded = DoublesUpdateImpl.getRequiredItemCapacity(targetK, nFinal);
            int curCombBufCap = tgt.getCombinedBufferItemCapacity();
            if (spaceNeeded > curCombBufCap) {
               tgt.growCombinedBuffer(curCombBufCap, spaceNeeded);
            }

            DoublesArrayAccessor scratch2KAcc = DoublesArrayAccessor.initialize(2 * targetK);
            DoublesArrayAccessor downScratchKAcc = DoublesArrayAccessor.initialize(targetK);
            DoublesSketchAccessor tgtSketchBuf = DoublesSketchAccessor.wrap(tgt, true);
            long srcBitPattern = src.getBitPattern();
            long newTgtBitPattern = tgt.getBitPattern();

            for(int srcLvl = 0; srcBitPattern != 0L; srcBitPattern >>>= 1) {
               if ((srcBitPattern & 1L) > 0L) {
                  justZipWithStride(srcSketchBuf.setLevel(srcLvl), downScratchKAcc, targetK, downFactor);
                  newTgtBitPattern = DoublesUpdateImpl.inPlacePropagateCarry(srcLvl + lgDownFactor, downScratchKAcc, scratch2KAcc, false, targetK, tgtSketchBuf, newTgtBitPattern);
                  tgt.putBitPattern(newTgtBitPattern);
               }

               ++srcLvl;
            }

            if (tgt.hasMemory() && nFinal > 0L) {
               WritableMemory mem = tgt.getMemory();
               mem.clearBits(3L, (byte)4);
            }

            tgt.putN(nFinal);

            assert tgt.getN() / (2L * (long)targetK) == newTgtBitPattern;

            double srcMax = src.getMaxItem();
            srcMax = Double.isNaN(srcMax) ? Double.NEGATIVE_INFINITY : srcMax;
            double srcMin = src.getMinItem();
            srcMin = Double.isNaN(srcMin) ? Double.POSITIVE_INFINITY : srcMin;
            double tgtMax = tgt.getMaxItem();
            tgtMax = Double.isNaN(tgtMax) ? Double.NEGATIVE_INFINITY : tgtMax;
            double tgtMin = tgt.getMinItem();
            tgtMin = Double.isNaN(tgtMin) ? Double.POSITIVE_INFINITY : tgtMin;
            if (srcMax > tgtMax) {
               tgt.putMaxItem(srcMax);
            }

            if (srcMin < tgtMin) {
               tgt.putMinItem(srcMin);
            }

         }
      }
   }

   private static void justZipWithStride(DoublesBufferAccessor bufA, DoublesBufferAccessor bufC, int kC, int stride) {
      int randomOffset = DoublesSketch.rand.nextInt(stride);
      int a = randomOffset;

      for(int c = 0; c < kC; ++c) {
         bufC.set(c, bufA.get(a));
         a += stride;
      }

   }
}
