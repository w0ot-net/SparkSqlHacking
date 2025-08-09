package org.roaringbitmap.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.util.BitSet;
import org.roaringbitmap.BitSetUtil;
import org.roaringbitmap.IntIterator;

public class BufferBitSetUtil {
   private static final int BLOCK_LENGTH = 1024;

   private static MappeableArrayContainer arrayContainerOf(int from, int to, int cardinality, long[] words) {
      char[] content = BitSetUtil.arrayContainerBufferOf(from, to, cardinality, words);
      return new MappeableArrayContainer(CharBuffer.wrap(content), cardinality);
   }

   public static MutableRoaringBitmap bitmapOf(BitSet bitSet) {
      return bitmapOf(bitSet.toLongArray());
   }

   public static MutableRoaringBitmap bitmapOf(long[] words) {
      MutableRoaringBitmap ans = new MutableRoaringBitmap();
      int containerIndex = 0;

      for(int from = 0; from < words.length; from += 1024) {
         int to = Math.min(from + 1024, words.length);
         int blockCardinality = cardinality(from, to, words);
         if (blockCardinality > 0) {
            ((MutableRoaringArray)ans.highLowContainer).insertNewKeyValueAt(containerIndex++, BufferUtil.highbits(from * 64), containerOf(from, to, blockCardinality, words));
         }
      }

      return ans;
   }

   public static MutableRoaringBitmap bitmapOf(ByteBuffer bb) {
      return bitmapOf(bb, new long[1024]);
   }

   public static MutableRoaringBitmap bitmapOf(ByteBuffer bb, long[] wordsBuffer) {
      if (wordsBuffer.length != 1024) {
         throw new IllegalArgumentException("wordsBuffer length should be 1024");
      } else {
         bb = bb.slice().order(ByteOrder.LITTLE_ENDIAN);
         MutableRoaringBitmap ans = new MutableRoaringBitmap();
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
                  ((MutableRoaringArray)ans.highLowContainer).insertNewKeyValueAt(containerIndex++, BufferUtil.highbits(offset), containerOf(0, blockLength, blockCardinality, wordsBuffer));
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
            ((MutableRoaringArray)ans.highLowContainer).insertNewKeyValueAt(containerIndex, BufferUtil.highbits(offset), containerOf(0, blockLength, blockCardinality, wordsBuffer));
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

   private static MappeableContainer containerOf(int from, int to, int blockCardinality, long[] words) {
      if (blockCardinality <= 4096) {
         return arrayContainerOf(from, to, blockCardinality, words);
      } else {
         long[] container = new long[1024];
         System.arraycopy(words, from, container, 0, to - from);
         return new MappeableBitmapContainer(LongBuffer.wrap(container), blockCardinality);
      }
   }

   public static boolean equals(BitSet bitset, ImmutableRoaringBitmap bitmap) {
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
