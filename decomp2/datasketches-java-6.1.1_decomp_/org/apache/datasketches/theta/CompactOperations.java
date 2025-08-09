package org.apache.datasketches.theta;

import java.util.Arrays;
import org.apache.datasketches.common.Family;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

final class CompactOperations {
   private CompactOperations() {
   }

   static CompactSketch componentsToCompact(long thetaLong, int curCount, short seedHash, boolean srcEmpty, boolean srcCompact, boolean srcOrdered, boolean dstOrdered, WritableMemory dstMem, long[] hashArr) {
      boolean direct = dstMem != null;
      boolean empty = srcEmpty || curCount == 0 && thetaLong == Long.MAX_VALUE;
      boolean single = curCount == 1 && thetaLong == Long.MAX_VALUE;
      long[] hashArrOut;
      if (!srcCompact) {
         hashArrOut = compactCache(hashArr, curCount, thetaLong, dstOrdered);
      } else {
         hashArrOut = hashArr;
      }

      if (!srcOrdered && dstOrdered && !empty && !single) {
         Arrays.sort(hashArrOut);
      }

      boolean dstOrderedOut = !empty && !single ? dstOrdered : true;
      if (direct) {
         int preLongs = computeCompactPreLongs(empty, curCount, thetaLong);
         int flags = 10;
         flags |= empty ? 4 : 0;
         flags |= dstOrderedOut ? 16 : 0;
         flags |= single ? 32 : 0;
         Memory mem = loadCompactMemory(hashArrOut, seedHash, curCount, thetaLong, dstMem, (byte)flags, preLongs);
         return new DirectCompactSketch(mem);
      } else if (empty) {
         return EmptyCompactSketch.getInstance();
      } else {
         return (CompactSketch)(single ? new SingleItemSketch(hashArrOut[0], seedHash) : new HeapCompactSketch(hashArrOut, empty, seedHash, curCount, thetaLong, dstOrderedOut));
      }
   }

   static CompactSketch memoryToCompact(Memory srcMem, boolean dstOrdered, WritableMemory dstMem) {
      int srcPreLongs = PreambleUtil.extractPreLongs(srcMem);
      int srcSerVer = PreambleUtil.extractSerVer(srcMem);
      int srcFamId = PreambleUtil.extractFamilyID(srcMem);
      int srcLgArrLongs = PreambleUtil.extractLgArrLongs(srcMem);
      int srcFlags = PreambleUtil.extractFlags(srcMem);
      short srcSeedHash = (short)PreambleUtil.extractSeedHash(srcMem);
      boolean srcReadOnlyFlag = (srcFlags & 2) > 0;
      boolean srcEmptyFlag = (srcFlags & 4) > 0;
      boolean srcCompactFlag = (srcFlags & 8) > 0;
      boolean srcOrderedFlag = (srcFlags & 16) > 0;
      boolean srcSingleFlag = (srcFlags & 32) > 0;
      boolean single = srcSingleFlag || SingleItemSketch.otherCheckForSingleItem(srcPreLongs, srcSerVer, srcFamId, srcFlags);
      int curCount = single ? 1 : (srcPreLongs > 1 ? PreambleUtil.extractCurCount(srcMem) : 0);
      long thetaLong = srcPreLongs > 2 ? PreambleUtil.extractThetaLong(srcMem) : Long.MAX_VALUE;

      assert !srcEmptyFlag || curCount == 0 && thetaLong == Long.MAX_VALUE;

      assert !single || curCount == 1 && thetaLong == Long.MAX_VALUE;

      checkFamilyAndFlags(srcFamId, srcCompactFlag, srcReadOnlyFlag);
      boolean dstOrderedOut = !srcEmptyFlag && !single ? dstOrdered : true;
      if (srcEmptyFlag) {
         if (dstMem != null) {
            dstMem.putByteArray(0L, EmptyCompactSketch.EMPTY_COMPACT_SKETCH_ARR, 0, 8);
            return new DirectCompactSketch(dstMem);
         } else {
            return EmptyCompactSketch.getInstance();
         }
      } else if (single) {
         long hash = srcMem.getLong((long)(srcPreLongs << 3));
         SingleItemSketch sis = new SingleItemSketch(hash, srcSeedHash);
         if (dstMem != null) {
            dstMem.putByteArray(0L, sis.toByteArray(), 0, 16);
            return new DirectCompactSketch(dstMem);
         } else {
            return sis;
         }
      } else {
         long[] hashArr;
         if (srcCompactFlag) {
            hashArr = new long[curCount];
            srcMem.getLongArray((long)(srcPreLongs << 3), hashArr, 0, curCount);
         } else {
            int srcCacheLen = 1 << srcLgArrLongs;
            long[] tempHashArr = new long[srcCacheLen];
            srcMem.getLongArray((long)(srcPreLongs << 3), tempHashArr, 0, srcCacheLen);
            hashArr = compactCache(tempHashArr, curCount, thetaLong, dstOrderedOut);
         }

         int flagsOut = 10 | (dstOrderedOut ? 16 : 0);
         if (dstMem != null) {
            Memory tgtMem = loadCompactMemory(hashArr, srcSeedHash, curCount, thetaLong, dstMem, (byte)flagsOut, srcPreLongs);
            return new DirectCompactSketch(tgtMem);
         } else {
            return new HeapCompactSketch(hashArr, srcEmptyFlag, srcSeedHash, curCount, thetaLong, dstOrderedOut);
         }
      }
   }

   private static final void checkFamilyAndFlags(int srcFamId, boolean srcCompactFlag, boolean srcReadOnlyFlag) {
      Family srcFamily = Family.idToFamily(srcFamId);
      if (srcCompactFlag) {
         if (srcFamily == Family.COMPACT && srcReadOnlyFlag) {
            return;
         }
      } else {
         if (srcFamily == Family.ALPHA) {
            return;
         }

         if (srcFamily == Family.QUICKSELECT) {
            return;
         }
      }

      throw new SketchesArgumentException("Possible Corruption: Family does not match flags: Family: " + srcFamily.toString() + ", Compact Flag: " + srcCompactFlag + ", ReadOnly Flag: " + srcReadOnlyFlag);
   }

   static final Memory loadCompactMemory(long[] compactHashArr, short seedHash, int curCount, long thetaLong, WritableMemory dstMem, byte flags, int preLongs) {
      assert dstMem != null && compactHashArr != null;

      int outLongs = preLongs + curCount;
      int outBytes = outLongs << 3;
      int dstBytes = (int)dstMem.getCapacity();
      if (outBytes > dstBytes) {
         throw new SketchesArgumentException("Insufficient Memory: " + dstBytes + ", Need: " + outBytes);
      } else {
         byte famID = (byte)Family.COMPACT.getID();
         PreambleUtil.insertPreLongs(dstMem, preLongs);
         PreambleUtil.insertSerVer(dstMem, 3);
         PreambleUtil.insertFamilyID(dstMem, famID);
         dstMem.putShort(3L, (short)0);
         PreambleUtil.insertFlags(dstMem, flags);
         PreambleUtil.insertSeedHash(dstMem, seedHash);
         if (preLongs == 1 && curCount == 1) {
            dstMem.putLong(8L, compactHashArr[0]);
            return dstMem;
         } else {
            if (preLongs > 1) {
               PreambleUtil.insertCurCount(dstMem, curCount);
               PreambleUtil.insertP(dstMem, 1.0F);
            }

            if (preLongs > 2) {
               PreambleUtil.insertThetaLong(dstMem, thetaLong);
            }

            if (curCount > 0) {
               dstMem.putLongArray((long)(preLongs << 3), compactHashArr, 0, curCount);
            }

            return dstMem;
         }
      }
   }

   static final long[] compactCache(long[] srcCache, int curCount, long thetaLong, boolean dstOrdered) {
      if (curCount == 0) {
         return new long[0];
      } else {
         long[] cacheOut = new long[curCount];
         int len = srcCache.length;
         int j = 0;

         for(int i = 0; i < len; ++i) {
            long v = srcCache[i];
            if (v > 0L && v < thetaLong) {
               cacheOut[j++] = v;
            }
         }

         if (j < curCount) {
            throw new SketchesStateException("Possible Corruption: curCount parameter is incorrect.");
         } else {
            if (dstOrdered && curCount > 1) {
               Arrays.sort(cacheOut);
            }

            return cacheOut;
         }
      }
   }

   static final long correctThetaOnCompact(boolean empty, int curCount, long thetaLong) {
      return empty && curCount == 0 ? Long.MAX_VALUE : thetaLong;
   }

   static final void checkIllegalCurCountAndEmpty(boolean empty, int curCount) {
      if (empty && curCount != 0) {
         throw new SketchesStateException("Illegal State: Empty=true and Current Count != 0.");
      }
   }

   static final int computeCompactPreLongs(boolean empty, int curCount, long thetaLong) {
      return thetaLong < Long.MAX_VALUE ? 3 : (empty ? 1 : (curCount > 1 ? 2 : 1));
   }

   static final boolean isSingleItem(boolean empty, int curCount, long thetaLong) {
      return !empty && curCount == 1 && thetaLong == Long.MAX_VALUE;
   }
}
