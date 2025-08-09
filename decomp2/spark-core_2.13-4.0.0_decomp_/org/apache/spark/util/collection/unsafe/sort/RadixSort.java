package org.apache.spark.util.collection.unsafe.sort;

import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.array.LongArray;
import org.sparkproject.guava.primitives.Ints;

public class RadixSort {
   public static int sort(LongArray array, long numRecords, int startByteIndex, int endByteIndex, boolean desc, boolean signed) {
      assert startByteIndex >= 0 : "startByteIndex (" + startByteIndex + ") should >= 0";

      assert endByteIndex <= 7 : "endByteIndex (" + endByteIndex + ") should <= 7";

      assert endByteIndex > startByteIndex;

      assert numRecords * 2L <= array.size();

      long inIndex = 0L;
      long outIndex = numRecords;
      if (numRecords > 0L) {
         long[][] counts = getCounts(array, numRecords, startByteIndex, endByteIndex);

         for(int i = startByteIndex; i <= endByteIndex; ++i) {
            if (counts[i] != null) {
               sortAtByte(array, numRecords, counts[i], i, inIndex, outIndex, desc, signed && i == endByteIndex);
               long tmp = inIndex;
               inIndex = outIndex;
               outIndex = tmp;
            }
         }
      }

      return Ints.checkedCast(inIndex);
   }

   private static void sortAtByte(LongArray array, long numRecords, long[] counts, int byteIdx, long inIndex, long outIndex, boolean desc, boolean signed) {
      assert counts.length == 256;

      long[] offsets = transformCountsToOffsets(counts, numRecords, array.getBaseOffset() + outIndex * 8L, 8L, desc, signed);
      Object baseObject = array.getBaseObject();
      long baseOffset = array.getBaseOffset() + inIndex * 8L;
      long maxOffset = baseOffset + numRecords * 8L;

      for(long offset = baseOffset; offset < maxOffset; offset += 8L) {
         long value = Platform.getLong(baseObject, offset);
         int bucket = (int)(value >>> byteIdx * 8 & 255L);
         Platform.putLong(baseObject, offsets[bucket], value);
         offsets[bucket] += 8L;
      }

   }

   private static long[][] getCounts(LongArray array, long numRecords, int startByteIndex, int endByteIndex) {
      long[][] counts = new long[8][];
      long bitwiseMax = 0L;
      long bitwiseMin = -1L;
      long maxOffset = array.getBaseOffset() + numRecords * 8L;
      Object baseObject = array.getBaseObject();

      for(long offset = array.getBaseOffset(); offset < maxOffset; offset += 8L) {
         long value = Platform.getLong(baseObject, offset);
         bitwiseMax |= value;
         bitwiseMin &= value;
      }

      long bitsChanged = bitwiseMin ^ bitwiseMax;

      for(int i = startByteIndex; i <= endByteIndex; ++i) {
         if ((bitsChanged >>> i * 8 & 255L) != 0L) {
            counts[i] = new long[256];

            for(long offset = array.getBaseOffset(); offset < maxOffset; offset += 8L) {
               int var10002 = counts[i][(int)(Platform.getLong(baseObject, offset) >>> i * 8 & 255L)]++;
            }
         }
      }

      return counts;
   }

   private static long[] transformCountsToOffsets(long[] counts, long numRecords, long outputOffset, long bytesPerRecord, boolean desc, boolean signed) {
      assert counts.length == 256;

      int start = signed ? 128 : 0;
      if (desc) {
         long pos = numRecords;

         for(int i = start; i < start + 256; ++i) {
            pos -= counts[i & 255];
            counts[i & 255] = outputOffset + pos * bytesPerRecord;
         }
      } else {
         long pos = 0L;

         for(int i = start; i < start + 256; ++i) {
            long tmp = counts[i & 255];
            counts[i & 255] = outputOffset + pos * bytesPerRecord;
            pos += tmp;
         }
      }

      return counts;
   }

   public static int sortKeyPrefixArray(LongArray array, long startIndex, long numRecords, int startByteIndex, int endByteIndex, boolean desc, boolean signed) {
      assert startByteIndex >= 0 : "startByteIndex (" + startByteIndex + ") should >= 0";

      assert endByteIndex <= 7 : "endByteIndex (" + endByteIndex + ") should <= 7";

      assert endByteIndex > startByteIndex;

      assert numRecords * 4L <= array.size();

      long inIndex = startIndex;
      long outIndex = startIndex + numRecords * 2L;
      if (numRecords > 0L) {
         long[][] counts = getKeyPrefixArrayCounts(array, startIndex, numRecords, startByteIndex, endByteIndex);

         for(int i = startByteIndex; i <= endByteIndex; ++i) {
            if (counts[i] != null) {
               sortKeyPrefixArrayAtByte(array, numRecords, counts[i], i, inIndex, outIndex, desc, signed && i == endByteIndex);
               long tmp = inIndex;
               inIndex = outIndex;
               outIndex = tmp;
            }
         }
      }

      return Ints.checkedCast(inIndex);
   }

   private static long[][] getKeyPrefixArrayCounts(LongArray array, long startIndex, long numRecords, int startByteIndex, int endByteIndex) {
      long[][] counts = new long[8][];
      long bitwiseMax = 0L;
      long bitwiseMin = -1L;
      long baseOffset = array.getBaseOffset() + startIndex * 8L;
      long limit = baseOffset + numRecords * 16L;
      Object baseObject = array.getBaseObject();

      for(long offset = baseOffset; offset < limit; offset += 16L) {
         long value = Platform.getLong(baseObject, offset + 8L);
         bitwiseMax |= value;
         bitwiseMin &= value;
      }

      long bitsChanged = bitwiseMin ^ bitwiseMax;

      for(int i = startByteIndex; i <= endByteIndex; ++i) {
         if ((bitsChanged >>> i * 8 & 255L) != 0L) {
            counts[i] = new long[256];

            for(long offset = baseOffset; offset < limit; offset += 16L) {
               int var10002 = counts[i][(int)(Platform.getLong(baseObject, offset + 8L) >>> i * 8 & 255L)]++;
            }
         }
      }

      return counts;
   }

   private static void sortKeyPrefixArrayAtByte(LongArray array, long numRecords, long[] counts, int byteIdx, long inIndex, long outIndex, boolean desc, boolean signed) {
      assert counts.length == 256;

      long[] offsets = transformCountsToOffsets(counts, numRecords, array.getBaseOffset() + outIndex * 8L, 16L, desc, signed);
      Object baseObject = array.getBaseObject();
      long baseOffset = array.getBaseOffset() + inIndex * 8L;
      long maxOffset = baseOffset + numRecords * 16L;

      for(long offset = baseOffset; offset < maxOffset; offset += 16L) {
         long key = Platform.getLong(baseObject, offset);
         long prefix = Platform.getLong(baseObject, offset + 8L);
         int bucket = (int)(prefix >>> byteIdx * 8 & 255L);
         long dest = offsets[bucket];
         Platform.putLong(baseObject, dest, key);
         Platform.putLong(baseObject, dest + 8L, prefix);
         offsets[bucket] += 16L;
      }

   }
}
