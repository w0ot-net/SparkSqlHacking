package io.airlift.compress.zstd;

class DoubleFastBlockCompressor implements BlockCompressor {
   private static final int MIN_MATCH = 3;
   private static final int SEARCH_STRENGTH = 8;
   private static final int REP_MOVE = 2;
   private static final int PRIME_4_BYTES = -1640531535;
   private static final long PRIME_5_BYTES = 889523592379L;
   private static final long PRIME_6_BYTES = 227718039650203L;
   private static final long PRIME_7_BYTES = 58295818150454627L;
   private static final long PRIME_8_BYTES = -3523014627327384477L;

   public int compressBlock(Object inputBase, final long inputAddress, int inputSize, SequenceStore output, BlockCompressionState state, RepeatedOffsets offsets, CompressionParameters parameters) {
      int matchSearchLength = Math.max(parameters.getSearchLength(), 4);
      long baseAddress = state.getBaseAddress();
      long windowBaseAddress = baseAddress + (long)state.getWindowBaseOffset();
      int[] longHashTable = state.hashTable;
      int longHashBits = parameters.getHashLog();
      int[] shortHashTable = state.chainTable;
      int shortHashBits = parameters.getChainLog();
      long inputEnd = inputAddress + (long)inputSize;
      long inputLimit = inputEnd - 8L;
      long input = inputAddress;
      long anchor = inputAddress;
      int offset1 = offsets.getOffset0();
      int offset2 = offsets.getOffset1();
      int savedOffset = 0;
      if (inputAddress - windowBaseAddress == 0L) {
         input = inputAddress + 1L;
      }

      int maxRep = (int)(input - windowBaseAddress);
      if (offset2 > maxRep) {
         savedOffset = offset2;
         offset2 = 0;
      }

      if (offset1 > maxRep) {
         savedOffset = offset1;
         offset1 = 0;
      }

      while(true) {
         int current;
         int matchLength;
         while(true) {
            if (input >= inputLimit) {
               offsets.saveOffset0(offset1 != 0 ? offset1 : savedOffset);
               offsets.saveOffset1(offset2 != 0 ? offset2 : savedOffset);
               return (int)(inputEnd - anchor);
            }

            int shortHash = hash(inputBase, input, shortHashBits, matchSearchLength);
            long shortMatchAddress = baseAddress + (long)shortHashTable[shortHash];
            int longHash = hash8(UnsafeUtil.UNSAFE.getLong(inputBase, input), longHashBits);
            long longMatchAddress = baseAddress + (long)longHashTable[longHash];
            current = (int)(input - baseAddress);
            longHashTable[longHash] = current;
            shortHashTable[shortHash] = current;
            if (offset1 > 0 && UnsafeUtil.UNSAFE.getInt(inputBase, input + 1L - (long)offset1) == UnsafeUtil.UNSAFE.getInt(inputBase, input + 1L)) {
               matchLength = count(inputBase, input + 1L + 4L, inputEnd, input + 1L + 4L - (long)offset1) + 4;
               ++input;
               output.storeSequence(inputBase, anchor, (int)(input - anchor), 0, matchLength - 3);
               break;
            }

            int offset;
            if (longMatchAddress > windowBaseAddress && UnsafeUtil.UNSAFE.getLong(inputBase, longMatchAddress) == UnsafeUtil.UNSAFE.getLong(inputBase, input)) {
               matchLength = count(inputBase, input + 8L, inputEnd, longMatchAddress + 8L) + 8;

               for(offset = (int)(input - longMatchAddress); input > anchor && longMatchAddress > windowBaseAddress && UnsafeUtil.UNSAFE.getByte(inputBase, input - 1L) == UnsafeUtil.UNSAFE.getByte(inputBase, longMatchAddress - 1L); ++matchLength) {
                  --input;
                  --longMatchAddress;
               }
            } else {
               if (shortMatchAddress <= windowBaseAddress || UnsafeUtil.UNSAFE.getInt(inputBase, shortMatchAddress) != UnsafeUtil.UNSAFE.getInt(inputBase, input)) {
                  input += (input - anchor >> 8) + 1L;
                  continue;
               }

               int nextOffsetHash = hash8(UnsafeUtil.UNSAFE.getLong(inputBase, input + 1L), longHashBits);
               long nextOffsetMatchAddress = baseAddress + (long)longHashTable[nextOffsetHash];
               longHashTable[nextOffsetHash] = current + 1;
               if (nextOffsetMatchAddress > windowBaseAddress && UnsafeUtil.UNSAFE.getLong(inputBase, nextOffsetMatchAddress) == UnsafeUtil.UNSAFE.getLong(inputBase, input + 1L)) {
                  matchLength = count(inputBase, input + 1L + 8L, inputEnd, nextOffsetMatchAddress + 8L) + 8;
                  ++input;

                  for(offset = (int)(input - nextOffsetMatchAddress); input > anchor && nextOffsetMatchAddress > windowBaseAddress && UnsafeUtil.UNSAFE.getByte(inputBase, input - 1L) == UnsafeUtil.UNSAFE.getByte(inputBase, nextOffsetMatchAddress - 1L); ++matchLength) {
                     --input;
                     --nextOffsetMatchAddress;
                  }
               } else {
                  matchLength = count(inputBase, input + 4L, inputEnd, shortMatchAddress + 4L) + 4;

                  for(offset = (int)(input - shortMatchAddress); input > anchor && shortMatchAddress > windowBaseAddress && UnsafeUtil.UNSAFE.getByte(inputBase, input - 1L) == UnsafeUtil.UNSAFE.getByte(inputBase, shortMatchAddress - 1L); ++matchLength) {
                     --input;
                     --shortMatchAddress;
                  }
               }
            }

            offset2 = offset1;
            offset1 = offset;
            output.storeSequence(inputBase, anchor, (int)(input - anchor), offset + 2, matchLength - 3);
            break;
         }

         input += (long)matchLength;
         anchor = input;
         if (input <= inputLimit) {
            longHashTable[hash8(UnsafeUtil.UNSAFE.getLong(inputBase, baseAddress + (long)current + 2L), longHashBits)] = current + 2;
            shortHashTable[hash(inputBase, baseAddress + (long)current + 2L, shortHashBits, matchSearchLength)] = current + 2;
            longHashTable[hash8(UnsafeUtil.UNSAFE.getLong(inputBase, input - 2L), longHashBits)] = (int)(input - 2L - baseAddress);

            for(shortHashTable[hash(inputBase, input - 2L, shortHashBits, matchSearchLength)] = (int)(input - 2L - baseAddress); input <= inputLimit && offset2 > 0 && UnsafeUtil.UNSAFE.getInt(inputBase, input) == UnsafeUtil.UNSAFE.getInt(inputBase, input - (long)offset2); anchor = input) {
               int repetitionLength = count(inputBase, input + 4L, inputEnd, input + 4L - (long)offset2) + 4;
               int temp = offset2;
               offset2 = offset1;
               offset1 = temp;
               shortHashTable[hash(inputBase, input, shortHashBits, matchSearchLength)] = (int)(input - baseAddress);
               longHashTable[hash8(UnsafeUtil.UNSAFE.getLong(inputBase, input), longHashBits)] = (int)(input - baseAddress);
               output.storeSequence(inputBase, anchor, 0, 0, repetitionLength - 3);
               input += (long)repetitionLength;
            }
         }
      }
   }

   public static int count(Object inputBase, final long inputAddress, final long inputLimit, final long matchAddress) {
      long input = inputAddress;
      long match = matchAddress;
      int remaining = (int)(inputLimit - inputAddress);

      int count;
      for(count = 0; count < remaining - 7; match += 8L) {
         long diff = UnsafeUtil.UNSAFE.getLong(inputBase, match) ^ UnsafeUtil.UNSAFE.getLong(inputBase, input);
         if (diff != 0L) {
            return count + (Long.numberOfTrailingZeros(diff) >> 3);
         }

         count += 8;
         input += 8L;
      }

      while(count < remaining && UnsafeUtil.UNSAFE.getByte(inputBase, match) == UnsafeUtil.UNSAFE.getByte(inputBase, input)) {
         ++count;
         ++input;
         ++match;
      }

      return count;
   }

   private static int hash(Object inputBase, long inputAddress, int bits, int matchSearchLength) {
      switch (matchSearchLength) {
         case 5:
            return hash5(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), bits);
         case 6:
            return hash6(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), bits);
         case 7:
            return hash7(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), bits);
         case 8:
            return hash8(UnsafeUtil.UNSAFE.getLong(inputBase, inputAddress), bits);
         default:
            return hash4(UnsafeUtil.UNSAFE.getInt(inputBase, inputAddress), bits);
      }
   }

   private static int hash4(int value, int bits) {
      return value * -1640531535 >>> 32 - bits;
   }

   private static int hash5(long value, int bits) {
      return (int)((value << 24) * 889523592379L >>> 64 - bits);
   }

   private static int hash6(long value, int bits) {
      return (int)((value << 16) * 227718039650203L >>> 64 - bits);
   }

   private static int hash7(long value, int bits) {
      return (int)((value << 8) * 58295818150454627L >>> 64 - bits);
   }

   private static int hash8(long value, int bits) {
      return (int)(value * -3523014627327384477L >>> 64 - bits);
   }
}
