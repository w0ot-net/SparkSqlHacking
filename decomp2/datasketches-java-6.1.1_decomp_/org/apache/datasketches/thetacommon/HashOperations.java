package org.apache.datasketches.thetacommon;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class HashOperations {
   private static final int STRIDE_HASH_BITS = 7;
   private static final int EMPTY = 0;
   public static final int STRIDE_MASK = 127;

   private HashOperations() {
   }

   private static int getStride(long hash, int lgArrLongs) {
      return 2 * (int)(hash >>> lgArrLongs & 127L) + 1;
   }

   public static int hashSearch(long[] hashTable, int lgArrLongs, long hash) {
      if (hash == 0L) {
         throw new SketchesArgumentException("Given hash must not be zero: " + hash);
      } else {
         int arrayMask = (1 << lgArrLongs) - 1;
         int stride = getStride(hash, lgArrLongs);
         int curProbe = (int)(hash & (long)arrayMask);
         int loopIndex = curProbe;

         do {
            long arrVal = hashTable[curProbe];
            if (arrVal == 0L) {
               return -1;
            }

            if (arrVal == hash) {
               return curProbe;
            }

            curProbe = curProbe + stride & arrayMask;
         } while(curProbe != loopIndex);

         return -1;
      }
   }

   public static int hashInsertOnly(long[] hashTable, int lgArrLongs, long hash) {
      int arrayMask = (1 << lgArrLongs) - 1;
      int stride = getStride(hash, lgArrLongs);
      int curProbe = (int)(hash & (long)arrayMask);
      long loopIndex = (long)curProbe;

      do {
         long arrVal = hashTable[curProbe];
         if (arrVal == 0L) {
            hashTable[curProbe] = hash;
            return curProbe;
         }

         curProbe = curProbe + stride & arrayMask;
      } while((long)curProbe != loopIndex);

      throw new SketchesArgumentException("No empty slot in table!");
   }

   public static int hashSearchOrInsert(long[] hashTable, int lgArrLongs, long hash) {
      int arrayMask = (1 << lgArrLongs) - 1;
      int stride = getStride(hash, lgArrLongs);
      int curProbe = (int)(hash & (long)arrayMask);
      int loopIndex = curProbe;

      do {
         long arrVal = hashTable[curProbe];
         if (arrVal == 0L) {
            hashTable[curProbe] = hash;
            return ~curProbe;
         }

         if (arrVal == hash) {
            return curProbe;
         }

         curProbe = curProbe + stride & arrayMask;
      } while(curProbe != loopIndex);

      throw new SketchesArgumentException("Hash not found and no empty slots!");
   }

   public static int hashArrayInsert(long[] srcArr, long[] hashTable, int lgArrLongs, long thetaLong) {
      int count = 0;
      int arrLen = srcArr.length;
      checkThetaCorruption(thetaLong);

      for(int i = 0; i < arrLen; ++i) {
         long hash = srcArr[i];
         checkHashCorruption(hash);
         if (!continueCondition(thetaLong, hash) && hashSearchOrInsert(hashTable, lgArrLongs, hash) < 0) {
            ++count;
         }
      }

      return count;
   }

   public static int hashSearchMemory(Memory mem, int lgArrLongs, long hash, int memOffsetBytes) {
      if (hash == 0L) {
         throw new SketchesArgumentException("Given hash must not be zero: " + hash);
      } else {
         int arrayMask = (1 << lgArrLongs) - 1;
         int stride = getStride(hash, lgArrLongs);
         int curProbe = (int)(hash & (long)arrayMask);
         int loopIndex = curProbe;

         do {
            int curProbeOffsetBytes = (curProbe << 3) + memOffsetBytes;
            long curArrayHash = mem.getLong((long)curProbeOffsetBytes);
            if (curArrayHash == 0L) {
               return -1;
            }

            if (curArrayHash == hash) {
               return curProbe;
            }

            curProbe = curProbe + stride & arrayMask;
         } while(curProbe != loopIndex);

         return -1;
      }
   }

   public static int hashInsertOnlyMemory(WritableMemory wmem, int lgArrLongs, long hash, int memOffsetBytes) {
      int arrayMask = (1 << lgArrLongs) - 1;
      int stride = getStride(hash, lgArrLongs);
      int curProbe = (int)(hash & (long)arrayMask);
      int loopIndex = curProbe;

      do {
         int curProbeOffsetBytes = (curProbe << 3) + memOffsetBytes;
         long curArrayHash = wmem.getLong((long)curProbeOffsetBytes);
         if (curArrayHash == 0L) {
            wmem.putLong((long)curProbeOffsetBytes, hash);
            return curProbe;
         }

         curProbe = curProbe + stride & arrayMask;
      } while(curProbe != loopIndex);

      throw new SketchesArgumentException("No empty slot in table!");
   }

   public static int hashSearchOrInsertMemory(WritableMemory wmem, int lgArrLongs, long hash, int memOffsetBytes) {
      int arrayMask = (1 << lgArrLongs) - 1;
      int stride = getStride(hash, lgArrLongs);
      int curProbe = (int)(hash & (long)arrayMask);
      int loopIndex = curProbe;

      do {
         int curProbeOffsetBytes = (curProbe << 3) + memOffsetBytes;
         long curArrayHash = wmem.getLong((long)curProbeOffsetBytes);
         if (curArrayHash == 0L) {
            wmem.putLong((long)curProbeOffsetBytes, hash);
            return ~curProbe;
         }

         if (curArrayHash == hash) {
            return curProbe;
         }

         curProbe = curProbe + stride & arrayMask;
      } while(curProbe != loopIndex);

      throw new SketchesArgumentException("Key not found and no empty slot in table!");
   }

   public static void checkThetaCorruption(long thetaLong) {
      if ((thetaLong | thetaLong - 1L) < 0L) {
         throw new SketchesStateException("Data Corruption: thetaLong was negative or zero: ThetaLong: " + thetaLong);
      }
   }

   public static void checkHashCorruption(long hash) {
      if (hash < 0L) {
         throw new SketchesArgumentException("Data Corruption: hash was negative: Hash: " + hash);
      }
   }

   public static boolean continueCondition(long thetaLong, long hash) {
      return (hash - 1L | thetaLong - hash - 1L) < 0L;
   }

   public static long[] convertToHashTable(long[] hashArr, int count, long thetaLong, double rebuildThreshold) {
      int lgArrLongs = minLgHashTableSize(count, rebuildThreshold);
      int arrLongs = 1 << lgArrLongs;
      long[] hashTable = new long[arrLongs];
      hashArrayInsert(hashArr, hashTable, lgArrLongs, thetaLong);
      return hashTable;
   }

   public static int minLgHashTableSize(int count, double rebuild_threshold) {
      int upperCount = (int)Math.ceil((double)count / rebuild_threshold);
      int arrLongs = Math.max(Util.ceilingPowerOf2(upperCount), 32);
      int newLgArrLongs = Integer.numberOfTrailingZeros(arrLongs);
      return newLgArrLongs;
   }

   public static int countPart(long[] srcArr, int lgArrLongs, long thetaLong) {
      int cnt = 0;
      int len = 1 << lgArrLongs;
      int i = len;

      while(i-- > 0) {
         long hash = srcArr[i];
         if (!continueCondition(thetaLong, hash)) {
            ++cnt;
         }
      }

      return cnt;
   }

   public static int count(long[] srcArr, long thetaLong) {
      int cnt = 0;
      int len = srcArr.length;
      int i = len;

      while(i-- > 0) {
         long hash = srcArr[i];
         if (!continueCondition(thetaLong, hash)) {
            ++cnt;
         }
      }

      return cnt;
   }
}
