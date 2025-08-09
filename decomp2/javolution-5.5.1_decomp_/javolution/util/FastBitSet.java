package javolution.util;

import java.util.Set;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.lang.MathLib;
import javolution.lang.Reusable;

public class FastBitSet extends FastCollection implements Set, Reusable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new FastBitSet();
      }
   };
   private long[] bits;
   private int _length;
   private static final long serialVersionUID = 1L;

   public FastBitSet() {
      this(64);
   }

   public FastBitSet(int bitSize) {
      this._length = (bitSize - 1 >> 6) + 1;
      this.bits = new long[this._length];
   }

   public static FastBitSet newInstance() {
      FastBitSet bitSet = (FastBitSet)FACTORY.object();
      bitSet._length = 0;
      return bitSet;
   }

   public static void recycle(FastBitSet instance) {
      FACTORY.recycle(instance);
   }

   public boolean add(Index index) {
      int bitIndex = index.intValue();
      if (this.get(bitIndex)) {
         return false;
      } else {
         this.set(bitIndex);
         return true;
      }
   }

   public void and(FastBitSet that) {
      int n = MathLib.min(this._length, that._length);

      for(int i = 0; i < n; ++i) {
         long[] var10000 = this.bits;
         var10000[i] &= that.bits[i];
      }

      this._length = n;
   }

   public void andNot(FastBitSet that) {
      int i = Math.min(this._length, that._length);

      while(true) {
         --i;
         if (i < 0) {
            return;
         }

         long[] var10000 = this.bits;
         var10000[i] &= ~that.bits[i];
      }
   }

   public int cardinality() {
      int sum = 0;

      for(int i = 0; i < this._length; ++i) {
         sum += MathLib.bitCount(this.bits[i]);
      }

      return sum;
   }

   public void clear() {
      this._length = 0;
   }

   public void clear(int bitIndex) {
      int longIndex = bitIndex >> 6;
      if (longIndex < this._length) {
         long[] var10000 = this.bits;
         var10000[longIndex] &= ~(1L << bitIndex);
      }
   }

   public void clear(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex >= fromIndex) {
         int i = fromIndex >>> 6;
         if (i < this._length) {
            int j = toIndex >>> 6;
            if (i == j) {
               long[] var7 = this.bits;
               var7[i] &= (1L << fromIndex) - 1L | -1L << toIndex;
            } else {
               long[] var10000 = this.bits;
               var10000[i] &= (1L << fromIndex) - 1L;
               if (j < this._length) {
                  var10000 = this.bits;
                  var10000[j] &= -1L << toIndex;
               }

               for(int k = i + 1; k < j && k < this._length; ++k) {
                  this.bits[k] = 0L;
               }

            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void flip(int bitIndex) {
      int i = bitIndex >> 6;
      this.setLength(i + 1);
      long[] var10000 = this.bits;
      var10000[i] ^= 1L << bitIndex;
   }

   public void flip(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex >= fromIndex) {
         int i = fromIndex >>> 6;
         int j = toIndex >>> 6;
         this.setLength(j + 1);
         if (i == j) {
            long[] var8 = this.bits;
            var8[i] ^= -1L << fromIndex & (1L << toIndex) - 1L;
         } else {
            long[] var10000 = this.bits;
            var10000[i] ^= -1L << fromIndex;
            var10000 = this.bits;
            var10000[j] ^= (1L << toIndex) - 1L;

            for(int k = i + 1; k < j; ++k) {
               var10000 = this.bits;
               var10000[k] = ~var10000[k];
            }

         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public boolean get(int bitIndex) {
      int i = bitIndex >> 6;
      return i >= this._length ? false : (this.bits[i] & 1L << bitIndex) != 0L;
   }

   public FastBitSet get(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && fromIndex <= toIndex) {
         FastBitSet bitSet = newInstance();
         int length = MathLib.min(this._length, (toIndex >>> 6) + 1);
         bitSet.setLength(length);
         System.arraycopy(this.bits, 0, bitSet.bits, 0, length);
         bitSet.clear(0, fromIndex);
         bitSet.clear(toIndex, length << 6);
         return bitSet;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public boolean intersects(FastBitSet that) {
      int i = MathLib.min(this._length, that._length);

      do {
         --i;
         if (i < 0) {
            return false;
         }
      } while((this.bits[i] & that.bits[i]) == 0L);

      return true;
   }

   public int length() {
      int i = this._length;

      long l;
      do {
         --i;
         if (i < 0) {
            return 0;
         }

         l = this.bits[i];
      } while(l == 0L);

      return i << 70 - MathLib.numberOfTrailingZeros(l);
   }

   public int nextClearBit(int fromIndex) {
      int offset = fromIndex >> 6;
      long mask = 1L << fromIndex;

      label21:
      while(offset < this._length) {
         long h = this.bits[offset];

         while((h & mask) != 0L) {
            mask <<= 1;
            ++fromIndex;
            if (mask == 0L) {
               mask = 1L;
               ++offset;
               continue label21;
            }
         }

         return fromIndex;
      }

      return fromIndex;
   }

   public int nextSetBit(int fromIndex) {
      int offset = fromIndex >> 6;
      long mask = 1L << fromIndex;

      label21:
      while(offset < this._length) {
         long h = this.bits[offset];

         while((h & mask) == 0L) {
            mask <<= 1;
            ++fromIndex;
            if (mask == 0L) {
               mask = 1L;
               ++offset;
               continue label21;
            }
         }

         return fromIndex;
      }

      return -1;
   }

   public void or(FastBitSet that) {
      if (that._length > this._length) {
         this.setLength(that._length);
      }

      int i = that._length;

      while(true) {
         --i;
         if (i < 0) {
            return;
         }

         long[] var10000 = this.bits;
         var10000[i] |= that.bits[i];
      }
   }

   public void set(int bitIndex) {
      int i = bitIndex >> 6;
      if (i >= this._length) {
         this.setLength(i + 1);
      }

      long[] var10000 = this.bits;
      var10000[i] |= 1L << bitIndex;
   }

   public void set(int bitIndex, boolean value) {
      if (value) {
         this.set(bitIndex);
      } else {
         this.clear(bitIndex);
      }

   }

   public void set(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex >= fromIndex) {
         int i = fromIndex >>> 6;
         int j = toIndex >>> 6;
         this.setLength(j + 1);
         if (i == j) {
            long[] var7 = this.bits;
            var7[i] |= -1L << fromIndex & (1L << toIndex) - 1L;
         } else {
            long[] var10000 = this.bits;
            var10000[i] |= -1L << fromIndex;
            var10000 = this.bits;
            var10000[j] |= (1L << toIndex) - 1L;

            for(int k = i + 1; k < j; ++k) {
               this.bits[k] = -1L;
            }

         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void set(int fromIndex, int toIndex, boolean value) {
      if (value) {
         this.set(fromIndex, toIndex);
      } else {
         this.clear(fromIndex, toIndex);
      }

   }

   public int size() {
      return this.cardinality();
   }

   public void xor(FastBitSet that) {
      if (that._length > this._length) {
         this.setLength(that._length);
      }

      int i = that._length;

      while(true) {
         --i;
         if (i < 0) {
            return;
         }

         long[] var10000 = this.bits;
         var10000[i] ^= that.bits[i];
      }
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof FastBitSet)) {
         return super.equals(obj);
      } else {
         FastBitSet that = (FastBitSet)obj;
         int n = MathLib.min(this._length, that._length);

         for(int i = 0; i < n; ++i) {
            if (this.bits[i] != that.bits[i]) {
               return false;
            }
         }

         for(int i = n; i < this._length; ++i) {
            if (this.bits[i] != 0L) {
               return false;
            }
         }

         for(int i = n; i < that._length; ++i) {
            if (that.bits[i] != 0L) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int h = 0;

      for(int i = this.nextSetBit(0); i >= 0; i = this.nextSetBit(i)) {
         h += i;
      }

      return h;
   }

   public void reset() {
      this._length = 0;
   }

   public FastCollection.Record head() {
      return Index.valueOf(-1);
   }

   public FastCollection.Record tail() {
      return Index.valueOf(this.cardinality());
   }

   public Index valueOf(FastCollection.Record record) {
      int i = ((Index)record).intValue();
      int count = 0;
      int j = 0;

      while(j < this._length) {
         long l = this.bits[j++];
         count += MathLib.bitCount(l);
         if (count > i) {
            int bitIndex;
            for(bitIndex = j << 6; count != i; --count) {
               int shiftRight = MathLib.numberOfLeadingZeros(l) + 1;
               l <<= shiftRight;
               bitIndex -= shiftRight;
            }

            return Index.valueOf(bitIndex);
         }
      }

      return null;
   }

   public void delete(FastCollection.Record record) {
      Index bitIndex = this.valueOf(record);
      if (bitIndex != null) {
         throw new UnsupportedOperationException("Not supported yet.");
      }
   }

   private final void setLength(final int newLength) {
      if (this.bits.length < newLength) {
         MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
            public void run() {
               int arrayLength;
               for(arrayLength = FastBitSet.this.bits.length; arrayLength < newLength; arrayLength <<= 1) {
               }

               long[] tmp = new long[arrayLength];
               System.arraycopy(FastBitSet.this.bits, 0, tmp, 0, FastBitSet.this._length);
               FastBitSet.this.bits = tmp;
            }
         });
      }

      for(int i = this._length; i < newLength; ++i) {
         this.bits[i] = 0L;
      }

      this._length = newLength;
   }
}
