package org.roaringbitmap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.roaringbitmap.buffer.MappeableBitmapContainer;
import org.roaringbitmap.buffer.MappeableContainer;

public final class BitmapContainer extends Container implements Cloneable {
   public static final int MAX_CAPACITY = 65536;
   private static final int MAX_CAPACITY_BYTE = 8192;
   private static final int MAX_CAPACITY_LONG = 1024;
   private static final long serialVersionUID = 3L;
   private static final int BLOCKSIZE = 128;
   private static final boolean USE_BRANCHLESS = true;
   final long[] bitmap;
   int cardinality;
   private static final int MAXRUNS = 2047;

   public static CharIterator getReverseShortIterator(long[] bitmap) {
      return new ReverseBitmapContainerCharIterator(bitmap);
   }

   public static PeekableCharIterator getShortIterator(long[] bitmap) {
      return new BitmapContainerCharIterator(bitmap);
   }

   protected static int serializedSizeInBytes(int unusedCardinality) {
      return 8192;
   }

   public BitmapContainer() {
      this.cardinality = 0;
      this.bitmap = new long[1024];
   }

   public BitmapContainer(int firstOfRun, int lastOfRun) {
      this.cardinality = lastOfRun - firstOfRun;
      this.bitmap = new long[1024];
      Util.setBitmapRange(this.bitmap, firstOfRun, lastOfRun);
   }

   private BitmapContainer(int newCardinality, long[] newBitmap) {
      this.cardinality = newCardinality;
      this.bitmap = Arrays.copyOf(newBitmap, newBitmap.length);
   }

   public BitmapContainer(long[] newBitmap, int newCardinality) {
      this.cardinality = newCardinality;
      this.bitmap = newBitmap;
   }

   public BitmapContainer(MappeableBitmapContainer bc) {
      this.cardinality = bc.getCardinality();
      this.bitmap = bc.toLongArray();
   }

   public Container add(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         BitmapContainer answer = this.clone();
         int prevOnesInRange = answer.cardinalityInRange(begin, end);
         Util.setBitmapRange(answer.bitmap, begin, end);
         answer.updateCardinality(prevOnesInRange, end - begin);
         return answer;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Container add(char i) {
      long previous = this.bitmap[i >>> 6];
      long newval = previous | 1L << i;
      this.bitmap[i >>> 6] = newval;
      this.cardinality += (int)((previous ^ newval) >>> i);
      return this;
   }

   public ArrayContainer and(ArrayContainer value2) {
      ArrayContainer answer = new ArrayContainer(value2.content.length);
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         answer.content[answer.cardinality] = v;
         answer.cardinality += this.bitValue(v);
      }

      return answer;
   }

   public Container and(BitmapContainer value2) {
      int newCardinality = this.andCardinality(value2);
      if (newCardinality <= 4096) {
         ArrayContainer ac = new ArrayContainer(newCardinality);
         Util.fillArrayAND(ac.content, this.bitmap, value2.bitmap);
         ac.cardinality = newCardinality;
         return ac;
      } else {
         BitmapContainer answer = new BitmapContainer();

         for(int k = 0; k < answer.bitmap.length; ++k) {
            answer.bitmap[k] = this.bitmap[k] & value2.bitmap[k];
         }

         answer.cardinality = newCardinality;
         return answer;
      }
   }

   public Container and(RunContainer x) {
      return x.and(this);
   }

   public int andCardinality(ArrayContainer value2) {
      int answer = 0;
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         answer += this.bitValue(v);
      }

      return answer;
   }

   public int andCardinality(BitmapContainer value2) {
      int newCardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         newCardinality += Long.bitCount(this.bitmap[k] & value2.bitmap[k]);
      }

      return newCardinality;
   }

   public int andCardinality(RunContainer x) {
      return x.andCardinality(this);
   }

   public Container andNot(ArrayContainer value2) {
      BitmapContainer answer = this.clone();
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         int i = v >>> 6;
         long w = answer.bitmap[i];
         long aft = w & ~(1L << v);
         answer.bitmap[i] = aft;
         answer.cardinality = (int)((long)answer.cardinality - ((w ^ aft) >>> v));
      }

      return (Container)(answer.cardinality <= 4096 ? answer.toArrayContainer() : answer);
   }

   public Container andNot(BitmapContainer value2) {
      int newCardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         newCardinality += Long.bitCount(this.bitmap[k] & ~value2.bitmap[k]);
      }

      if (newCardinality <= 4096) {
         ArrayContainer ac = new ArrayContainer(newCardinality);
         Util.fillArrayANDNOT(ac.content, this.bitmap, value2.bitmap);
         ac.cardinality = newCardinality;
         return ac;
      } else {
         BitmapContainer answer = new BitmapContainer();

         for(int k = 0; k < answer.bitmap.length; ++k) {
            answer.bitmap[k] = this.bitmap[k] & ~value2.bitmap[k];
         }

         answer.cardinality = newCardinality;
         return answer;
      }
   }

   public Container andNot(RunContainer x) {
      BitmapContainer answer = this.clone();

      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         int prevOnesInRange = answer.cardinalityInRange(start, end);
         Util.resetBitmapRange(answer.bitmap, start, end);
         answer.updateCardinality(prevOnesInRange, 0);
      }

      return (Container)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
   }

   public void clear() {
      if (this.cardinality != 0) {
         this.cardinality = 0;
         Arrays.fill(this.bitmap, 0L);
      }

   }

   public BitmapContainer clone() {
      return new BitmapContainer(this.cardinality, this.bitmap);
   }

   public boolean isEmpty() {
      return this.cardinality == 0;
   }

   void computeCardinality() {
      this.cardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         this.cardinality += Long.bitCount(this.bitmap[k]);
      }

   }

   int cardinalityInRange(int start, int end) {
      if (this.cardinality != -1 && end - start > 32768) {
         int before = Util.cardinalityInBitmapRange(this.bitmap, 0, start);
         int after = Util.cardinalityInBitmapRange(this.bitmap, end, 65536);
         return this.cardinality - before - after;
      } else {
         return Util.cardinalityInBitmapRange(this.bitmap, start, end);
      }
   }

   void updateCardinality(int prevOnes, int newOnes) {
      int oldCardinality = this.cardinality;
      this.cardinality = oldCardinality - prevOnes + newOnes;
   }

   public boolean contains(char i) {
      return (this.bitmap[i >>> 6] & 1L << i) != 0L;
   }

   public boolean contains(int minimum, int supremum) {
      int start = minimum >>> 6;
      int end = supremum >>> 6;
      long first = -(1L << minimum);
      long last = (1L << supremum) - 1L;
      if (start == end) {
         return (this.bitmap[end] & first & last) == (first & last);
      } else if ((this.bitmap[start] & first) != first) {
         return false;
      } else if (end < this.bitmap.length && (this.bitmap[end] & last) != last) {
         return false;
      } else {
         for(int i = start + 1; i < this.bitmap.length && i < end; ++i) {
            if (this.bitmap[i] != -1L) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean contains(BitmapContainer bitmapContainer) {
      if (this.cardinality != -1 && bitmapContainer.cardinality != -1 && this.cardinality < bitmapContainer.cardinality) {
         return false;
      } else {
         for(int i = 0; i < bitmapContainer.bitmap.length; ++i) {
            if ((this.bitmap[i] & bitmapContainer.bitmap[i]) != bitmapContainer.bitmap[i]) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean contains(RunContainer runContainer) {
      int runCardinality = runContainer.getCardinality();
      if (this.cardinality != -1) {
         if (this.cardinality < runCardinality) {
            return false;
         }
      } else {
         int card = this.cardinality;
         if (card < runCardinality) {
            return false;
         }
      }

      for(int i = 0; i < runContainer.numberOfRuns(); ++i) {
         int start = runContainer.getValue(i);
         int length = runContainer.getLength(i);
         if (!this.contains(start, start + length)) {
            return false;
         }
      }

      return true;
   }

   protected boolean contains(ArrayContainer arrayContainer) {
      if (arrayContainer.cardinality != -1 && this.cardinality < arrayContainer.cardinality) {
         return false;
      } else {
         for(int i = 0; i < arrayContainer.cardinality; ++i) {
            if (!this.contains(arrayContainer.content[i])) {
               return false;
            }
         }

         return true;
      }
   }

   int bitValue(char i) {
      return (int)(this.bitmap[i >>> 6] >>> i) & 1;
   }

   public void deserialize(DataInput in) throws IOException {
      this.cardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         long w = Long.reverseBytes(in.readLong());
         this.bitmap[k] = w;
         this.cardinality += Long.bitCount(w);
      }

   }

   public boolean equals(Object o) {
      if (o instanceof BitmapContainer) {
         BitmapContainer srb = (BitmapContainer)o;
         return srb.cardinality != this.cardinality ? false : Arrays.equals(this.bitmap, srb.bitmap);
      } else {
         return o instanceof RunContainer ? o.equals(this) : false;
      }
   }

   public void fillLeastSignificant16bits(int[] x, int i, int mask) {
      int pos = i;
      int base = mask;

      for(int k = 0; k < this.bitmap.length; ++k) {
         for(long bitset = this.bitmap[k]; bitset != 0L; bitset &= bitset - 1L) {
            x[pos++] = base + Long.numberOfTrailingZeros(bitset);
         }

         base += 64;
      }

   }

   public Container flip(char i) {
      int index = i >>> 6;
      long bef = this.bitmap[index];
      long mask = 1L << i;
      if (this.cardinality == 4097 && (bef & mask) != 0L) {
         --this.cardinality;
         long[] var7 = this.bitmap;
         var7[index] &= ~mask;
         return this.toArrayContainer();
      } else {
         this.cardinality += 1 - 2 * (int)((bef & mask) >>> i);
         long[] var10000 = this.bitmap;
         var10000[index] ^= mask;
         return this;
      }
   }

   public int getArraySizeInBytes() {
      return 8192;
   }

   public int getCardinality() {
      return this.cardinality;
   }

   public PeekableCharIterator getReverseCharIterator() {
      return new ReverseBitmapContainerCharIterator(this.bitmap);
   }

   public PeekableCharIterator getCharIterator() {
      return new BitmapContainerCharIterator(this.bitmap);
   }

   public PeekableCharRankIterator getCharRankIterator() {
      return new BitmapContainerCharRankIterator(this.bitmap);
   }

   public ContainerBatchIterator getBatchIterator() {
      return new BitmapBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.bitmap.length * 8;
   }

   public int hashCode() {
      return Arrays.hashCode(this.bitmap);
   }

   public Container iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int prevOnesInRange = this.cardinalityInRange(begin, end);
         Util.setBitmapRange(this.bitmap, begin, end);
         this.updateCardinality(prevOnesInRange, end - begin);
         return this;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Container iand(ArrayContainer b2) {
      if (-1 == this.cardinality) {
         Util.intersectArrayIntoBitmap(this.bitmap, b2.content, b2.cardinality);
         return this;
      } else {
         return b2.and(this);
      }
   }

   public Container iand(BitmapContainer b2) {
      if (-1 == this.cardinality) {
         for(int i = 0; i < this.bitmap.length; ++i) {
            long[] var10000 = this.bitmap;
            var10000[i] &= b2.bitmap[i];
         }

         return this;
      } else {
         int newCardinality = this.andCardinality(b2);
         if (newCardinality <= 4096) {
            ArrayContainer ac = new ArrayContainer(newCardinality);
            Util.fillArrayAND(ac.content, this.bitmap, b2.bitmap);
            ac.cardinality = newCardinality;
            return ac;
         } else {
            for(int k = 0; k < this.bitmap.length; ++k) {
               this.bitmap[k] &= b2.bitmap[k];
            }

            this.cardinality = newCardinality;
            return this;
         }
      }
   }

   public Container iand(RunContainer x) {
      int card = x.getCardinality();
      if (-1 != this.cardinality && card <= 4096) {
         ArrayContainer answer = new ArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int runStart = x.getValue(rlepos);
            int runEnd = runStart + x.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               answer.content[answer.cardinality] = (char)runValue;
               answer.cardinality += this.bitValue((char)runValue);
            }
         }

         return answer;
      } else {
         int start = 0;

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int end = x.getValue(rlepos);
            if (-1 == this.cardinality) {
               Util.resetBitmapRange(this.bitmap, start, end);
            } else {
               int prevOnes = this.cardinalityInRange(start, end);
               Util.resetBitmapRange(this.bitmap, start, end);
               this.updateCardinality(prevOnes, 0);
            }

            start = end + x.getLength(rlepos) + 1;
         }

         if (-1 == this.cardinality) {
            Util.resetBitmapRange(this.bitmap, start, 65536);
         } else {
            int ones = this.cardinalityInRange(start, 65536);
            Util.resetBitmapRange(this.bitmap, start, 65536);
            this.updateCardinality(ones, 0);
            if (this.getCardinality() <= 4096) {
               return this.toArrayContainer();
            }
         }

         return this;
      }
   }

   public Container iandNot(ArrayContainer b2) {
      for(int k = 0; k < b2.cardinality; ++k) {
         this.remove(b2.content[k]);
      }

      return (Container)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
   }

   public Container iandNot(BitmapContainer b2) {
      int newCardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         newCardinality += Long.bitCount(this.bitmap[k] & ~b2.bitmap[k]);
      }

      if (newCardinality <= 4096) {
         ArrayContainer ac = new ArrayContainer(newCardinality);
         Util.fillArrayANDNOT(ac.content, this.bitmap, b2.bitmap);
         ac.cardinality = newCardinality;
         return ac;
      } else {
         for(int k = 0; k < this.bitmap.length; ++k) {
            this.bitmap[k] &= ~b2.bitmap[k];
         }

         this.cardinality = newCardinality;
         return this;
      }
   }

   public Container iandNot(RunContainer x) {
      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         int prevOnesInRange = this.cardinalityInRange(start, end);
         Util.resetBitmapRange(this.bitmap, start, end);
         this.updateCardinality(prevOnesInRange, 0);
      }

      return (Container)(this.getCardinality() > 4096 ? this : this.toArrayContainer());
   }

   Container ilazyor(ArrayContainer value2) {
      this.cardinality = -1;
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         int i = v >>> 6;
         long[] var10000 = this.bitmap;
         var10000[i] |= 1L << v;
      }

      return this;
   }

   Container ilazyor(BitmapContainer x) {
      this.cardinality = -1;

      for(int k = 0; k < this.bitmap.length; ++k) {
         long[] var10000 = this.bitmap;
         var10000[k] |= x.bitmap[k];
      }

      return this;
   }

   Container ilazyor(RunContainer x) {
      this.cardinality = -1;

      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         Util.setBitmapRange(this.bitmap, start, end);
      }

      return this;
   }

   public Container inot(int firstOfRange, int lastOfRange) {
      int prevOnes = this.cardinalityInRange(firstOfRange, lastOfRange);
      Util.flipBitmapRange(this.bitmap, firstOfRange, lastOfRange);
      this.updateCardinality(prevOnes, lastOfRange - firstOfRange - prevOnes);
      return (Container)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
   }

   public boolean intersects(ArrayContainer value2) {
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         if (this.contains(value2.content[k])) {
            return true;
         }
      }

      return false;
   }

   public boolean intersects(BitmapContainer value2) {
      for(int k = 0; k < this.bitmap.length; ++k) {
         if ((this.bitmap[k] & value2.bitmap[k]) != 0L) {
            return true;
         }
      }

      return false;
   }

   public boolean intersects(RunContainer x) {
      return x.intersects(this);
   }

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         int start = minimum >>> 6;
         int end = supremum >>> 6;
         if (start == end) {
            return (this.bitmap[end] & -(1L << minimum) & (1L << supremum) - 1L) != 0L;
         } else if ((this.bitmap[start] & -(1L << minimum)) != 0L) {
            return true;
         } else if (end < this.bitmap.length && (this.bitmap[end] & (1L << supremum) - 1L) != 0L) {
            return true;
         } else {
            for(int i = 1 + start; i < end && i < this.bitmap.length; ++i) {
               if (this.bitmap[i] != 0L) {
                  return true;
               }
            }

            return false;
         }
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public BitmapContainer ior(ArrayContainer value2) {
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         int i = value2.content[k] >>> 6;
         long bef = this.bitmap[i];
         long aft = bef | 1L << value2.content[k];
         this.bitmap[i] = aft;
         this.cardinality += (int)(bef - aft >>> 63);
      }

      return this;
   }

   public Container ior(BitmapContainer b2) {
      for(int k = 0; k < this.bitmap.length & k < b2.bitmap.length; ++k) {
         long[] var10000 = this.bitmap;
         var10000[k] |= b2.bitmap[k];
      }

      this.computeCardinality();
      if (this.isFull()) {
         return RunContainer.full();
      } else {
         return this;
      }
   }

   public Container ior(RunContainer x) {
      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         int prevOnesInRange = this.cardinalityInRange(start, end);
         Util.setBitmapRange(this.bitmap, start, end);
         this.updateCardinality(prevOnesInRange, end - start);
      }

      return (Container)(this.isFull() ? RunContainer.full() : this);
   }

   public Container iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int prevOnesInRange = this.cardinalityInRange(begin, end);
         Util.resetBitmapRange(this.bitmap, begin, end);
         this.updateCardinality(prevOnesInRange, 0);
         return (Container)(this.getCardinality() <= 4096 ? this.toArrayContainer() : this);
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         final CharIterator si = BitmapContainer.this.getCharIterator();

         public boolean hasNext() {
            return this.si.hasNext();
         }

         public Character next() {
            return this.si.next();
         }

         public void remove() {
            throw new RuntimeException("unsupported operation: remove");
         }
      };
   }

   public Container ixor(ArrayContainer value2) {
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char vc = value2.content[k];
         long mask = 1L << vc;
         int index = vc >>> 6;
         long ba = this.bitmap[index];
         this.cardinality += 1 - 2 * (int)((ba & mask) >>> vc);
         this.bitmap[index] = ba ^ mask;
      }

      return (Container)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
   }

   public Container ixor(BitmapContainer b2) {
      for(int k = 0; k < this.bitmap.length & k < b2.bitmap.length; ++k) {
         long[] var10000 = this.bitmap;
         var10000[k] ^= b2.bitmap[k];
      }

      this.computeCardinality();
      if (this.cardinality > 4096) {
         return this;
      } else {
         return this.toArrayContainer();
      }
   }

   public Container ixor(RunContainer x) {
      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         int prevOnes = this.cardinalityInRange(start, end);
         Util.flipBitmapRange(this.bitmap, start, end);
         this.updateCardinality(prevOnes, end - start - prevOnes);
      }

      return (Container)(this.getCardinality() > 4096 ? this : this.toArrayContainer());
   }

   protected Container lazyor(ArrayContainer value2) {
      BitmapContainer answer = this.clone();
      answer.cardinality = -1;
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         int i = v >>> 6;
         long[] var10000 = answer.bitmap;
         var10000[i] |= 1L << v;
      }

      return answer;
   }

   protected Container lazyor(BitmapContainer x) {
      BitmapContainer answer = new BitmapContainer();
      answer.cardinality = -1;

      for(int k = 0; k < this.bitmap.length; ++k) {
         answer.bitmap[k] = this.bitmap[k] | x.bitmap[k];
      }

      return answer;
   }

   protected Container lazyor(RunContainer x) {
      BitmapContainer bc = this.clone();
      bc.cardinality = -1;

      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         Util.setBitmapRange(bc.bitmap, start, end);
      }

      return bc;
   }

   public Container limit(int maxcardinality) {
      if (maxcardinality >= this.cardinality) {
         return this.clone();
      } else if (maxcardinality > 4096) {
         long[] newBitmap = new long[1024];
         BitmapContainer bc = new BitmapContainer(newBitmap, maxcardinality);
         int s = this.select(maxcardinality);
         int usedwords = (s + 63) / 64;
         System.arraycopy(this.bitmap, 0, newBitmap, 0, usedwords);
         int lastword = s % 64;
         if (lastword != 0) {
            long[] var10000 = bc.bitmap;
            var10000[s / 64] &= -1L >>> 64 - lastword;
         }

         return bc;
      } else {
         ArrayContainer ac = new ArrayContainer(maxcardinality);
         int pos = 0;

         for(int k = 0; ac.cardinality < maxcardinality && k < this.bitmap.length; ++k) {
            for(long bitset = this.bitmap[k]; ac.cardinality < maxcardinality && bitset != 0L; bitset &= bitset - 1L) {
               ac.content[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
               ++ac.cardinality;
            }
         }

         return ac;
      }
   }

   void loadData(ArrayContainer arrayContainer) {
      this.cardinality = arrayContainer.cardinality;

      for(int k = 0; k < arrayContainer.cardinality; ++k) {
         char x = arrayContainer.content[k];
         long[] var10000 = this.bitmap;
         var10000[x / 64] |= 1L << x;
      }

   }

   public int nextSetBit(int i) {
      int x = i >> 6;
      long w = this.bitmap[x];
      w >>>= i;
      if (w != 0L) {
         return i + Long.numberOfTrailingZeros(w);
      } else {
         ++x;

         while(x < this.bitmap.length) {
            if (this.bitmap[x] != 0L) {
               return x * 64 + Long.numberOfTrailingZeros(this.bitmap[x]);
            }

            ++x;
         }

         return -1;
      }
   }

   private int nextClearBit(int i) {
      int x = i >> 6;
      long w = ~this.bitmap[x];
      w >>>= i;
      if (w != 0L) {
         return i + Long.numberOfTrailingZeros(w);
      } else {
         ++x;

         while(x < this.bitmap.length) {
            long map = ~this.bitmap[x];
            if (map != 0L) {
               return x * 64 + Long.numberOfTrailingZeros(map);
            }

            ++x;
         }

         return 65536;
      }
   }

   public Container not(int firstOfRange, int lastOfRange) {
      BitmapContainer answer = this.clone();
      return answer.inot(firstOfRange, lastOfRange);
   }

   int numberOfRuns() {
      int numRuns = 0;
      long nextWord = this.bitmap[0];

      for(int i = 0; i < this.bitmap.length - 1; ++i) {
         long word = nextWord;
         nextWord = this.bitmap[i + 1];
         numRuns += Long.bitCount(~word & word << 1) + (int)(word >>> 63 & ~nextWord);
      }

      numRuns += Long.bitCount(~nextWord & nextWord << 1);
      if ((nextWord & Long.MIN_VALUE) != 0L) {
         ++numRuns;
      }

      return numRuns;
   }

   public int numberOfRunsAdjustment() {
      int ans = 0;
      long nextWord = this.bitmap[0];

      for(int i = 0; i < this.bitmap.length - 1; ++i) {
         long word = nextWord;
         nextWord = this.bitmap[i + 1];
         ans += (int)(word >>> 63 & ~nextWord);
      }

      if ((nextWord & Long.MIN_VALUE) != 0L) {
         ++ans;
      }

      return ans;
   }

   public int numberOfRunsLowerBound(int mustNotExceed) {
      int numRuns = 0;

      for(int blockOffset = 0; blockOffset + 128 <= this.bitmap.length; blockOffset += 128) {
         for(int i = blockOffset; i < blockOffset + 128; ++i) {
            long word = this.bitmap[i];
            numRuns += Long.bitCount(~word & word << 1);
         }

         if (numRuns > mustNotExceed) {
            return numRuns;
         }
      }

      return numRuns;
   }

   public Container or(ArrayContainer value2) {
      BitmapContainer answer = this.clone();
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content[k];
         int i = v >>> 6;
         long w = answer.bitmap[i];
         long aft = w | 1L << v;
         answer.bitmap[i] = aft;
         answer.cardinality += (int)(w - aft >>> 63);
      }

      return (Container)(answer.isFull() ? RunContainer.full() : answer);
   }

   public boolean isFull() {
      return this.cardinality == 65536;
   }

   public Container or(BitmapContainer value2) {
      BitmapContainer value1 = this.clone();
      return value1.ior(value2);
   }

   public Container or(RunContainer x) {
      return x.or(this);
   }

   int prevSetBit(int i) {
      int x = i >> 6;
      long w = this.bitmap[x];
      w <<= 64 - i - 1;
      if (w != 0L) {
         return i - Long.numberOfLeadingZeros(w);
      } else {
         --x;

         while(x >= 0) {
            if (this.bitmap[x] != 0L) {
               return x * 64 + 63 - Long.numberOfLeadingZeros(this.bitmap[x]);
            }

            --x;
         }

         return -1;
      }
   }

   private int prevClearBit(int i) {
      int x = i >> 6;
      long w = ~this.bitmap[x];
      w <<= 64 - (i + 1);
      if (w != 0L) {
         return i - Long.numberOfLeadingZeros(w);
      } else {
         --x;

         while(x >= 0) {
            long map = ~this.bitmap[x];
            if (map != 0L) {
               return x * 64 + 63 - Long.numberOfLeadingZeros(map);
            }

            --x;
         }

         return -1;
      }
   }

   public int rank(char lowbits) {
      int leftover = lowbits + 1 & 63;
      int answer = 0;

      for(int k = 0; k < lowbits + 1 >>> 6; ++k) {
         answer += Long.bitCount(this.bitmap[k]);
      }

      if (leftover != 0) {
         answer += Long.bitCount(this.bitmap[lowbits + 1 >>> 6] << 64 - leftover);
      }

      return answer;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.deserialize(in);
   }

   public Container remove(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         BitmapContainer answer = this.clone();
         int prevOnesInRange = answer.cardinalityInRange(begin, end);
         Util.resetBitmapRange(answer.bitmap, begin, end);
         answer.updateCardinality(prevOnesInRange, 0);
         return (Container)(answer.getCardinality() <= 4096 ? answer.toArrayContainer() : answer);
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Container remove(char i) {
      int index = i >>> 6;
      long bef = this.bitmap[index];
      long mask = 1L << i;
      if (this.cardinality == 4097 && (bef & mask) != 0L) {
         --this.cardinality;
         this.bitmap[i >>> 6] = bef & ~mask;
         return this.toArrayContainer();
      } else {
         long aft = bef & ~mask;
         this.cardinality = (int)((long)this.cardinality - (aft - bef >>> 63));
         this.bitmap[index] = aft;
         return this;
      }
   }

   public Container repairAfterLazy() {
      if (this.getCardinality() < 0) {
         this.computeCardinality();
         if (this.getCardinality() <= 4096) {
            return this.toArrayContainer();
         }

         if (this.isFull()) {
            return RunContainer.full();
         }
      }

      return this;
   }

   public Container runOptimize() {
      int numRuns = this.numberOfRunsLowerBound(2047);
      int sizeAsRunContainerLowerBound = RunContainer.serializedSizeInBytes(numRuns);
      if (sizeAsRunContainerLowerBound >= this.getArraySizeInBytes()) {
         return this;
      } else {
         numRuns += this.numberOfRunsAdjustment();
         int sizeAsRunContainer = RunContainer.serializedSizeInBytes(numRuns);
         return (Container)(this.getArraySizeInBytes() > sizeAsRunContainer ? new RunContainer(this, numRuns) : this);
      }
   }

   public char select(int j) {
      if (this.cardinality >>> 1 < j && j < this.cardinality) {
         int leftover = this.cardinality - j;

         for(int k = this.bitmap.length - 1; k >= 0; --k) {
            long w = this.bitmap[k];
            if (w != 0L) {
               int bits = Long.bitCount(w);
               if (bits >= leftover) {
                  return (char)(k * 64 + Util.select(w, bits - leftover));
               }

               leftover -= bits;
            }
         }
      } else {
         int leftover = j;

         for(int k = 0; k < this.bitmap.length; ++k) {
            long w = this.bitmap[k];
            if (w != 0L) {
               int bits = Long.bitCount(this.bitmap[k]);
               if (bits > leftover) {
                  return (char)(k * 64 + Util.select(this.bitmap[k], leftover));
               }

               leftover -= bits;
            }
         }
      }

      throw new IllegalArgumentException("Insufficient cardinality.");
   }

   public char selectOneSide(int j) {
      int leftover = j;

      for(int k = 0; k < this.bitmap.length; ++k) {
         int w = Long.bitCount(this.bitmap[k]);
         if (w > leftover) {
            return (char)(k * 64 + Util.select(this.bitmap[k], leftover));
         }

         leftover -= w;
      }

      throw new IllegalArgumentException("Insufficient cardinality.");
   }

   public void serialize(DataOutput out) throws IOException {
      for(long w : this.bitmap) {
         out.writeLong(Long.reverseBytes(w));
      }

   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(0);
   }

   ArrayContainer toArrayContainer() {
      ArrayContainer ac = new ArrayContainer(this.cardinality);
      if (this.cardinality != 0) {
         ac.loadData(this);
      }

      if (ac.getCardinality() != this.cardinality) {
         throw new RuntimeException("Internal error.");
      } else {
         return ac;
      }
   }

   public LongBuffer toLongBuffer() {
      LongBuffer lb = LongBuffer.allocate(this.bitmap.length);
      lb.put(this.bitmap);
      return lb;
   }

   public MappeableContainer toMappeableContainer() {
      return new MappeableBitmapContainer(this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("{}".length() + "-123456789,".length() * 256);
      CharIterator i = this.getCharIterator();
      sb.append('{');

      while(i.hasNext()) {
         sb.append(i.next());
         if (i.hasNext()) {
            sb.append(',');
         }
      }

      sb.append('}');
      return sb.toString();
   }

   public void trim() {
   }

   public void writeArray(DataOutput out) throws IOException {
      this.serialize(out);
   }

   public void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      LongBuffer buf = buffer.asLongBuffer();
      buf.put(this.bitmap);
      int bytesWritten = this.bitmap.length * 8;
      buffer.position(buffer.position() + bytesWritten);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize(out);
   }

   public Container xor(ArrayContainer value2) {
      BitmapContainer answer = this.clone();
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char vc = value2.content[k];
         int index = vc >>> 6;
         long mask = 1L << vc;
         long val = answer.bitmap[index];
         answer.cardinality += (int)(1L - 2L * ((val & mask) >>> vc));
         answer.bitmap[index] = val ^ mask;
      }

      return (Container)(answer.cardinality <= 4096 ? answer.toArrayContainer() : answer);
   }

   public Container xor(BitmapContainer value2) {
      int newCardinality = 0;

      for(int k = 0; k < this.bitmap.length; ++k) {
         newCardinality += Long.bitCount(this.bitmap[k] ^ value2.bitmap[k]);
      }

      if (newCardinality <= 4096) {
         ArrayContainer ac = new ArrayContainer(newCardinality);
         Util.fillArrayXOR(ac.content, this.bitmap, value2.bitmap);
         ac.cardinality = newCardinality;
         return ac;
      } else {
         BitmapContainer answer = new BitmapContainer();

         for(int k = 0; k < answer.bitmap.length; ++k) {
            answer.bitmap[k] = this.bitmap[k] ^ value2.bitmap[k];
         }

         answer.cardinality = newCardinality;
         return answer;
      }
   }

   public Container xor(RunContainer x) {
      return x.xor(this);
   }

   public void forEach(char msb, IntConsumer ic) {
      int high = msb << 16;

      for(int x = 0; x < this.bitmap.length; ++x) {
         for(long w = this.bitmap[x]; w != 0L; w &= w - 1L) {
            ic.accept((x << 6) + Long.numberOfTrailingZeros(w) | high);
         }
      }

   }

   public void forAll(int offset, RelativeRangeConsumer rrc) {
      for(int wordIndex = 0; wordIndex < this.bitmap.length; ++wordIndex) {
         long word = this.bitmap[wordIndex];
         int bufferWordStart = offset + (wordIndex << 6);
         int bufferWordEnd = bufferWordStart + 64;
         this.addWholeWordToRangeConsumer(word, bufferWordStart, bufferWordEnd, rrc);
      }

   }

   public void forAllFrom(char startValue, RelativeRangeConsumer rrc) {
      int startIndex = startValue >>> 6;

      for(int wordIndex = startIndex; wordIndex < this.bitmap.length; ++wordIndex) {
         long word = this.bitmap[wordIndex];
         int wordStart = wordIndex << 6;
         int wordEnd = wordStart + 64;
         if (wordStart >= startValue) {
            this.addWholeWordToRangeConsumer(word, wordStart - startValue, wordEnd - startValue, rrc);
         } else if (word == 0L) {
            rrc.acceptAllAbsent(0, wordEnd - startValue);
         } else if (word == -1L) {
            rrc.acceptAllPresent(0, wordEnd - startValue);
         } else {
            int nextPos;
            for(nextPos = startValue; word != 0L; word &= word - 1L) {
               int pos = wordStart + Long.numberOfTrailingZeros(word);
               if (nextPos < pos) {
                  rrc.acceptAllAbsent(nextPos - startValue, pos - startValue);
                  rrc.acceptPresent(pos - startValue);
                  nextPos = pos + 1;
               } else if (nextPos == pos) {
                  rrc.acceptPresent(pos - startValue);
                  ++nextPos;
               }
            }

            if (nextPos < wordEnd) {
               rrc.acceptAllAbsent(nextPos - startValue, wordEnd - startValue);
            }
         }
      }

   }

   public void forAllUntil(int offset, char endValue, RelativeRangeConsumer rrc) {
      int bufferEndPos = offset + endValue;

      for(int wordIndex = 0; wordIndex < this.bitmap.length; ++wordIndex) {
         long word = this.bitmap[wordIndex];
         int bufferWordStart = offset + (wordIndex << 6);
         int bufferWordEnd = bufferWordStart + 64;
         if (bufferWordStart >= bufferEndPos) {
            return;
         }

         if (bufferEndPos < bufferWordEnd) {
            if (word == 0L) {
               rrc.acceptAllAbsent(bufferWordStart, bufferEndPos);
            } else {
               if (word != -1L) {
                  int nextPos;
                  for(nextPos = bufferWordStart; word != 0L; word &= word - 1L) {
                     int pos = bufferWordStart + Long.numberOfTrailingZeros(word);
                     if (bufferEndPos <= pos) {
                        if (nextPos < bufferEndPos) {
                           rrc.acceptAllAbsent(nextPos, bufferEndPos);
                        }

                        return;
                     }

                     if (nextPos < pos) {
                        rrc.acceptAllAbsent(nextPos, pos);
                        nextPos = pos;
                     }

                     rrc.acceptPresent(pos);
                     ++nextPos;
                  }

                  if (nextPos < bufferEndPos) {
                     rrc.acceptAllAbsent(nextPos, bufferEndPos);
                  }

                  return;
               }

               rrc.acceptAllPresent(bufferWordStart, bufferEndPos);
            }
         } else {
            this.addWholeWordToRangeConsumer(word, bufferWordStart, bufferWordEnd, rrc);
         }
      }

   }

   public void forAllInRange(char startValue, char endValue, RelativeRangeConsumer rrc) {
      if (endValue <= startValue) {
         throw new IllegalArgumentException("startValue (" + startValue + ") must be less than endValue (" + endValue + ")");
      } else {
         int startIndex = startValue >>> 6;

         for(int wordIndex = startIndex; wordIndex < this.bitmap.length; ++wordIndex) {
            long word = this.bitmap[wordIndex];
            int wordStart = wordIndex << 6;
            int wordEndExclusive = wordStart + 64;
            if (wordStart >= endValue) {
               return;
            }

            boolean startInWord = wordStart < startValue;
            boolean endInWord = endValue < wordEndExclusive;
            boolean wordAllZeroes = word == 0L;
            boolean wordAllOnes = word == -1L;
            if (startInWord && endInWord) {
               if (wordAllZeroes) {
                  rrc.acceptAllAbsent(0, endValue - startValue);
               } else if (wordAllOnes) {
                  rrc.acceptAllPresent(0, endValue - startValue);
               } else {
                  int nextPos;
                  for(nextPos = startValue; word != 0L; word &= word - 1L) {
                     int pos = wordStart + Long.numberOfTrailingZeros(word);
                     if (endValue <= pos) {
                        if (nextPos < endValue) {
                           rrc.acceptAllAbsent(nextPos - startValue, endValue - startValue);
                        }

                        return;
                     }

                     if (nextPos < pos) {
                        rrc.acceptAllAbsent(nextPos - startValue, pos - startValue);
                        rrc.acceptPresent(pos - startValue);
                        nextPos = pos + 1;
                     } else if (nextPos == pos) {
                        rrc.acceptPresent(pos - startValue);
                        ++nextPos;
                     }
                  }

                  if (nextPos < endValue) {
                     rrc.acceptAllAbsent(nextPos - startValue, endValue - startValue);
                  }
               }

               return;
            }

            if (startInWord) {
               if (wordAllZeroes) {
                  rrc.acceptAllAbsent(0, 64 - (startValue - wordStart));
               } else if (wordAllOnes) {
                  rrc.acceptAllPresent(0, 64 - (startValue - wordStart));
               } else {
                  int nextPos;
                  for(nextPos = startValue; word != 0L; word &= word - 1L) {
                     int pos = wordStart + Long.numberOfTrailingZeros(word);
                     if (nextPos < pos) {
                        rrc.acceptAllAbsent(nextPos - startValue, pos - startValue);
                        rrc.acceptPresent(pos - startValue);
                        nextPos = pos + 1;
                     } else if (nextPos == pos) {
                        rrc.acceptPresent(pos - startValue);
                        ++nextPos;
                     }
                  }

                  if (nextPos < wordEndExclusive) {
                     rrc.acceptAllAbsent(nextPos - startValue, wordEndExclusive - startValue);
                  }
               }
            } else {
               if (endInWord) {
                  if (wordAllZeroes) {
                     rrc.acceptAllAbsent(wordStart - startValue, endValue - startValue);
                  } else if (wordAllOnes) {
                     rrc.acceptAllPresent(wordStart - startValue, endValue - startValue);
                  } else {
                     int nextPos;
                     for(nextPos = wordStart; word != 0L; word &= word - 1L) {
                        int pos = wordStart + Long.numberOfTrailingZeros(word);
                        if (endValue <= pos) {
                           if (nextPos < endValue) {
                              rrc.acceptAllAbsent(nextPos - startValue, endValue - startValue);
                           }

                           return;
                        }

                        if (nextPos < pos) {
                           rrc.acceptAllAbsent(nextPos - startValue, pos - startValue);
                           nextPos = pos;
                        }

                        rrc.acceptPresent(pos - startValue);
                        ++nextPos;
                     }

                     if (nextPos < endValue) {
                        rrc.acceptAllAbsent(nextPos - startValue, endValue - startValue);
                     }
                  }

                  return;
               }

               this.addWholeWordToRangeConsumer(word, wordStart - startValue, wordEndExclusive - startValue, rrc);
            }
         }

      }
   }

   private void addWholeWordToRangeConsumer(long word, int bufferWordStart, int bufferWordEnd, RelativeRangeConsumer rrc) {
      if (word == 0L) {
         rrc.acceptAllAbsent(bufferWordStart, bufferWordEnd);
      } else if (word == -1L) {
         rrc.acceptAllPresent(bufferWordStart, bufferWordEnd);
      } else {
         int nextPos;
         for(nextPos = bufferWordStart; word != 0L; word &= word - 1L) {
            int pos = bufferWordStart + Long.numberOfTrailingZeros(word);
            if (nextPos < pos) {
               rrc.acceptAllAbsent(nextPos, pos);
               nextPos = pos;
            }

            rrc.acceptPresent(pos);
            ++nextPos;
         }

         if (nextPos < bufferWordEnd) {
            rrc.acceptAllAbsent(nextPos, bufferWordEnd);
         }
      }

   }

   public BitmapContainer toBitmapContainer() {
      return this;
   }

   public void copyBitmapTo(long[] words, int position) {
      System.arraycopy(this.bitmap, 0, words, position, this.bitmap.length);
   }

   public void copyBitmapTo(long[] words, int position, int length) {
      System.arraycopy(this.bitmap, 0, words, position, length);
   }

   public int nextValue(char fromValue) {
      return this.nextSetBit(fromValue);
   }

   public int previousValue(char fromValue) {
      return this.prevSetBit(fromValue);
   }

   public int nextAbsentValue(char fromValue) {
      return this.nextClearBit(fromValue);
   }

   public int previousAbsentValue(char fromValue) {
      return this.prevClearBit(fromValue);
   }

   public int first() {
      this.assertNonEmpty(this.cardinality == 0);

      int i;
      for(i = 0; i < this.bitmap.length - 1 && this.bitmap[i] == 0L; ++i) {
      }

      return i * 64 + Long.numberOfTrailingZeros(this.bitmap[i]);
   }

   public int last() {
      this.assertNonEmpty(this.cardinality == 0);

      int i;
      for(i = this.bitmap.length - 1; i > 0 && this.bitmap[i] == 0L; --i) {
      }

      return (i + 1) * 64 - Long.numberOfLeadingZeros(this.bitmap[i]) - 1;
   }
}
