package org.apache.datasketches.theta;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Memory;

final class ForwardCompatibility {
   static final CompactSketch heapify1to3(Memory srcMem, short seedHash) {
      int memCap = (int)srcMem.getCapacity();
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      if (preLongs != 3) {
         throw new SketchesArgumentException("PreLongs must be 3 for SerVer 1: " + preLongs);
      } else {
         int familyId = PreambleUtil.extractFamilyID(srcMem);
         if (familyId >= 1 && familyId <= 3) {
            int curCount = PreambleUtil.extractCurCount(srcMem);
            long thetaLong = PreambleUtil.extractThetaLong(srcMem);
            boolean empty = curCount == 0 && thetaLong == Long.MAX_VALUE;
            if (!empty && memCap > 24) {
               int reqCap = curCount + preLongs << 3;
               validateInputSize(reqCap, memCap);
               if (thetaLong == Long.MAX_VALUE && curCount == 1) {
                  long hash = srcMem.getLong((long)(preLongs << 3));
                  return new SingleItemSketch(hash, seedHash);
               } else {
                  long[] compactOrderedCache = new long[curCount];
                  srcMem.getLongArray((long)(preLongs << 3), compactOrderedCache, 0, curCount);
                  return new HeapCompactSketch(compactOrderedCache, false, seedHash, curCount, thetaLong, true);
               }
            } else {
               return EmptyCompactSketch.getInstance();
            }
         } else {
            throw new SketchesArgumentException("Family ID (Sketch Type) must be 1 to 3: " + familyId);
         }
      }
   }

   static final CompactSketch heapify2to3(Memory srcMem, short seedHash) {
      int memCap = (int)srcMem.getCapacity();
      int preLongs = PreambleUtil.extractPreLongs(srcMem);
      int familyId = PreambleUtil.extractFamilyID(srcMem);
      if (familyId >= 1 && familyId <= 4) {
         int reqBytesIn = 8;
         int curCount = 0;
         long thetaLong = Long.MAX_VALUE;
         if (preLongs == 1) {
            reqBytesIn = 8;
            validateInputSize(reqBytesIn, memCap);
            return EmptyCompactSketch.getInstance();
         } else if (preLongs == 2) {
            reqBytesIn = preLongs << 3;
            validateInputSize(reqBytesIn, memCap);
            curCount = PreambleUtil.extractCurCount(srcMem);
            if (curCount == 0) {
               return EmptyCompactSketch.getInstance();
            } else if (curCount == 1) {
               reqBytesIn = preLongs + 1 << 3;
               validateInputSize(reqBytesIn, memCap);
               long hash = srcMem.getLong((long)(preLongs << 3));
               return new SingleItemSketch(hash, seedHash);
            } else {
               reqBytesIn = curCount + preLongs << 3;
               validateInputSize(reqBytesIn, memCap);
               long[] compactOrderedCache = new long[curCount];
               srcMem.getLongArray((long)(preLongs << 3), compactOrderedCache, 0, curCount);
               return new HeapCompactSketch(compactOrderedCache, false, seedHash, curCount, thetaLong, true);
            }
         } else if (preLongs == 3) {
            reqBytesIn = preLongs << 3;
            validateInputSize(reqBytesIn, memCap);
            curCount = PreambleUtil.extractCurCount(srcMem);
            thetaLong = PreambleUtil.extractThetaLong(srcMem);
            if (curCount == 0 && thetaLong == Long.MAX_VALUE) {
               return EmptyCompactSketch.getInstance();
            } else if (curCount == 1 && thetaLong == Long.MAX_VALUE) {
               reqBytesIn = preLongs + 1 << 3;
               validateInputSize(reqBytesIn, memCap);
               long hash = srcMem.getLong((long)(preLongs << 3));
               return new SingleItemSketch(hash, seedHash);
            } else {
               reqBytesIn = curCount + preLongs << 3;
               validateInputSize(reqBytesIn, memCap);
               long[] compactOrderedCache = new long[curCount];
               srcMem.getLongArray((long)(preLongs << 3), compactOrderedCache, 0, curCount);
               return new HeapCompactSketch(compactOrderedCache, false, seedHash, curCount, thetaLong, true);
            }
         } else {
            throw new SketchesArgumentException("PreLongs must be 1,2, or 3: " + preLongs);
         }
      } else {
         throw new SketchesArgumentException("Family (Sketch Type) must be 1 to 4: " + familyId);
      }
   }

   private static final void validateInputSize(int reqBytesIn, int memCap) {
      if (reqBytesIn > memCap) {
         throw new SketchesArgumentException("Input Memory or byte[] size is too small: Required Bytes: " + reqBytesIn + ", bytesIn: " + memCap);
      }
   }
}
