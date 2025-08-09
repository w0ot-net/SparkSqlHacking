package org.tukaani.xz.rangecoder;

import java.io.IOException;

public abstract class RangeEncoder extends RangeCoder {
   private static final int MOVE_REDUCING_BITS = 4;
   private static final int BIT_PRICE_SHIFT_BITS = 4;
   private static final int[] prices = new int[128];
   private long low;
   private int range;
   long cacheSize;
   private byte cache;

   public void reset() {
      this.low = 0L;
      this.range = -1;
      this.cache = 0;
      this.cacheSize = 1L;
   }

   public int getPendingSize() {
      throw new Error();
   }

   public int finish() throws IOException {
      for(int i = 0; i < 5; ++i) {
         this.shiftLow();
      }

      return -1;
   }

   abstract void writeByte(int var1) throws IOException;

   private void shiftLow() throws IOException {
      int lowHi = (int)(this.low >>> 32);
      if (lowHi != 0 || this.low < 4278190080L) {
         int temp = this.cache;

         do {
            this.writeByte(temp + lowHi);
            temp = 255;
         } while(--this.cacheSize != 0L);

         this.cache = (byte)((int)(this.low >>> 24));
      }

      ++this.cacheSize;
      this.low = (this.low & 16777215L) << 8;
   }

   public void encodeBit(short[] probs, int index, int bit) throws IOException {
      int prob = probs[index];
      int bound = (this.range >>> 11) * prob;
      if (bit == 0) {
         this.range = bound;
         probs[index] = (short)(prob + (2048 - prob >>> 5));
      } else {
         this.low += (long)bound & 4294967295L;
         this.range -= bound;
         probs[index] = (short)(prob - (prob >>> 5));
      }

      if ((this.range & -16777216) == 0) {
         this.range <<= 8;
         this.shiftLow();
      }

   }

   public static int getBitPrice(int prob, int bit) {
      assert bit == 0 || bit == 1;

      return prices[(prob ^ -bit & 2047) >>> 4];
   }

   public void encodeBitTree(short[] probs, int symbol) throws IOException {
      int index = 1;
      int mask = probs.length;

      do {
         mask >>>= 1;
         int bit = symbol & mask;
         this.encodeBit(probs, index, bit);
         index <<= 1;
         if (bit != 0) {
            index |= 1;
         }
      } while(mask != 1);

   }

   public static int getBitTreePrice(short[] probs, int symbol) {
      int price = 0;
      symbol |= probs.length;

      do {
         int bit = symbol & 1;
         symbol >>>= 1;
         price += getBitPrice(probs[symbol], bit);
      } while(symbol != 1);

      return price;
   }

   public void encodeReverseBitTree(short[] probs, int symbol) throws IOException {
      int index = 1;
      symbol |= probs.length;

      do {
         int bit = symbol & 1;
         symbol >>>= 1;
         this.encodeBit(probs, index, bit);
         index = index << 1 | bit;
      } while(symbol != 1);

   }

   public static int getReverseBitTreePrice(short[] probs, int symbol) {
      int price = 0;
      int index = 1;
      symbol |= probs.length;

      do {
         int bit = symbol & 1;
         symbol >>>= 1;
         price += getBitPrice(probs[index], bit);
         index = index << 1 | bit;
      } while(symbol != 1);

      return price;
   }

   public void encodeDirectBits(int value, int count) throws IOException {
      do {
         this.range >>>= 1;
         --count;
         this.low += (long)(this.range & 0 - (value >>> count & 1));
         if ((this.range & -16777216) == 0) {
            this.range <<= 8;
            this.shiftLow();
         }
      } while(count != 0);

   }

   public static int getDirectBitsPrice(int count) {
      return count << 4;
   }

   static {
      for(int i = 8; i < 2048; i += 16) {
         int w = i;
         int bitCount = 0;

         for(int j = 0; j < 4; ++j) {
            w *= w;

            for(bitCount <<= 1; (w & -65536) != 0; ++bitCount) {
               w >>>= 1;
            }
         }

         prices[i >> 4] = 161 - bitCount;
      }

   }
}
