package org.roaringbitmap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.Objects;

public class BitSetUtil {
   public static final int BLOCK_LENGTH = 1024;

   public static BitSet bitsetOf(RoaringBitmap bitmap) {
      return BitSet.valueOf(toLongArray(bitmap));
   }

   public static BitSet bitsetOfWithoutCopy(RoaringBitmap bitmap) {
      if (bitmap.isEmpty()) {
         return new BitSet(0);
      } else {
         int last = bitmap.last();
         if (last < 0) {
            throw new IllegalArgumentException("bitmap has negative bits set");
         } else {
            BitSet bitSet = new BitSet(last);
            Objects.requireNonNull(bitSet);
            bitmap.forEach(bitSet::set);
            return bitSet;
         }
      }
   }

   public static byte[] toByteArray(RoaringBitmap bitmap) {
      long[] words = toLongArray(bitmap);
      ByteBuffer buffer = ByteBuffer.allocate(words.length * 64).order(ByteOrder.LITTLE_ENDIAN);
      buffer.asLongBuffer().put(words);
      return buffer.array();
   }

   public static long[] toLongArray(RoaringBitmap bitmap) {
      if (bitmap.isEmpty()) {
         return new long[0];
      } else {
         int last = bitmap.last();
         if (last < 0) {
            throw new IllegalArgumentException("bitmap has negative bits set");
         } else {
            int lastBit = Math.max(last, 64);
            int remainder = lastBit % 64;
            int numBits = remainder > 0 ? lastBit - remainder : lastBit;
            int wordsInUse = numBits / 64 + 1;
            long[] words = new long[wordsInUse];
            ContainerPointer pointer = bitmap.getContainerPointer();
            int numContainers = Math.max(words.length / 1024, 1);
            int position = 0;

            for(int i = 0; i <= numContainers; ++i) {
               char key = Util.lowbits(i);
               if (key == pointer.key()) {
                  Container container = pointer.getContainer();
                  int remaining = wordsInUse - position;
                  int length = Math.min(1024, remaining);
                  if (container instanceof BitmapContainer) {
                     ((BitmapContainer)container).copyBitmapTo(words, position, length);
                  } else {
                     container.copyBitmapTo(words, position);
                  }

                  position += length;
                  pointer.advance();
                  if (pointer.getContainer() == null) {
                     break;
                  }
               } else {
                  position += 1024;
               }
            }

            assert pointer.getContainer() == null;

            assert position == wordsInUse;

            return words;
         }
      }
   }

   public static char[] arrayContainerBufferOf(int from, int to, int cardinality, long[] words) {
      char[] content = new char[cardinality];
      int index = 0;
      int i = from;

      for(int socket = 0; i < to; socket += 64) {
         for(long word = words[i]; word != 0L; word &= word - 1L) {
            content[index++] = (char)(socket + Long.numberOfTrailingZeros(word));
         }

         ++i;
      }

      return content;
   }

   private static ArrayContainer arrayContainerOf(int from, int to, int cardinality, long[] words) {
      return new ArrayContainer(arrayContainerBufferOf(from, to, cardinality, words));
   }

   public static RoaringBitmap bitmapOf(BitSet bitSet) {
      return bitmapOf(bitSet.toLongArray());
   }

   public static RoaringBitmap bitmapOf(long[] words) {
      RoaringBitmap ans = new RoaringBitmap();
      int containerIndex = 0;

      for(int from = 0; from < words.length; from += 1024) {
         int to = Math.min(from + 1024, words.length);
         int blockCardinality = cardinality(from, to, words);
         if (blockCardinality > 0) {
            ans.highLowContainer.insertNewKeyValueAt(containerIndex++, Util.highbits(from * 64), containerOf(from, to, blockCardinality, words));
         }
      }

      return ans;
   }

   public static RoaringBitmap bitmapOf(ByteBuffer bb, boolean fastRank) {
      return bitmapOf(bb, fastRank, new long[1024]);
   }

   public static RoaringBitmap bitmapOf(ByteBuffer bb, boolean fastRank, long[] wordsBuffer) {
      if (wordsBuffer.length != 1024) {
         throw new IllegalArgumentException("wordsBuffer length should be 1024");
      } else {
         bb = bb.slice().order(ByteOrder.LITTLE_ENDIAN);
         RoaringBitmap ans = (RoaringBitmap)(fastRank ? new FastRankRoaringBitmap() : new RoaringBitmap());
         int containerIndex = 0;
         int blockLength = 0;
         int blockCardinality = 0;
         int offset = 0;

         while(bb.remaining() >= 8) {
            long word = bb.getLong();
            wordsBuffer[blockLength++] = word;
            blockCardinality += Long.bitCount(word);
            if (blockLength == 1024) {
               if (blockCardinality > 0) {
                  ans.highLowContainer.insertNewKeyValueAt(containerIndex++, Util.highbits(offset), containerOf(0, blockLength, blockCardinality, wordsBuffer));
               }

               offset += 65536;
               blockCardinality = 0;
               blockLength = 0;
            }
         }

         if (bb.remaining() > 0) {
            long word = 0L;
            int remaining = bb.remaining();

            for(int j = 0; j < remaining; ++j) {
               word |= ((long)bb.get() & 255L) << 8 * j;
            }

            if (word != 0L) {
               wordsBuffer[blockLength++] = word;
               blockCardinality += Long.bitCount(word);
            }
         }

         if (blockCardinality > 0) {
            ans.highLowContainer.insertNewKeyValueAt(containerIndex, Util.highbits(offset), containerOf(0, blockLength, blockCardinality, wordsBuffer));
         }

         return ans;
      }
   }

   private static int cardinality(int from, int to, long[] words) {
      int sum = 0;

      for(int i = from; i < to; ++i) {
         sum += Long.bitCount(words[i]);
      }

      return sum;
   }

   private static Container containerOf(int from, int to, int blockCardinality, long[] words) {
      if (blockCardinality <= 4096) {
         return arrayContainerOf(from, to, blockCardinality, words);
      } else {
         long[] container = new long[1024];
         System.arraycopy(words, from, container, 0, to - from);
         return new BitmapContainer(container, blockCardinality);
      }
   }

   public static boolean equals(BitSet bitset, RoaringBitmap bitmap) {
      if (bitset.cardinality() != bitmap.getCardinality()) {
         return false;
      } else {
         IntIterator it = bitmap.getIntIterator();

         while(it.hasNext()) {
            int val = it.next();
            if (!bitset.get(val)) {
               return false;
            }
         }

         return true;
      }
   }
}
