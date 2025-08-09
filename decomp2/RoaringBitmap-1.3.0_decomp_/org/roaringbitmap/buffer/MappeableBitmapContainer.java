package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.util.Iterator;
import org.roaringbitmap.BitmapContainer;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.Container;
import org.roaringbitmap.ContainerBatchIterator;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.Util;

public final class MappeableBitmapContainer extends MappeableContainer implements Cloneable {
   public static final int MAX_CAPACITY = 65536;
   private static final int MAX_CAPACITY_BYTE = 8192;
   private static final int MAX_CAPACITY_LONG = 1024;
   private static final long serialVersionUID = 3L;
   private static final int BLOCKSIZE = 128;
   private static final boolean USE_BRANCHLESS = true;
   LongBuffer bitmap;
   int cardinality;
   private static final int MAXRUNS = 2047;

   protected static int serializedSizeInBytes(int unusedCardinality) {
      return 8192;
   }

   public MappeableBitmapContainer() {
      this.cardinality = 0;
      this.bitmap = LongBuffer.allocate(1024);
   }

   public MappeableBitmapContainer(BitmapContainer bc) {
      this.cardinality = bc.getCardinality();
      this.bitmap = bc.toLongBuffer();
   }

   public MappeableBitmapContainer(int firstOfRun, int lastOfRun) {
      this.cardinality = lastOfRun - firstOfRun;
      this.bitmap = LongBuffer.allocate(1024);
      Util.setBitmapRange(this.bitmap.array(), firstOfRun, lastOfRun);
   }

   MappeableBitmapContainer(int newCardinality, LongBuffer newBitmap) {
      this.cardinality = newCardinality;
      LongBuffer tmp = newBitmap.duplicate();
      this.bitmap = LongBuffer.allocate(tmp.limit());
      tmp.rewind();
      this.bitmap.put(tmp);
   }

   public MappeableBitmapContainer(LongBuffer array, int initCardinality) {
      if (array.limit() != 1024) {
         throw new RuntimeException("Mismatch between buffer and storage requirements: " + array.limit() + " vs. " + 1024);
      } else {
         this.cardinality = initCardinality;
         this.bitmap = array;
      }
   }

   public MappeableContainer add(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         MappeableBitmapContainer answer = this.clone();
         int prevOnesInRange = answer.cardinalityInRange(begin, end);
         BufferUtil.setBitmapRange(answer.bitmap, begin, end);
         answer.updateCardinality(prevOnesInRange, end - begin);
         return answer;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableContainer add(char i) {
      long previous = this.bitmap.get(i / 64);
      long newv = previous | 1L << i;
      this.bitmap.put(i / 64, newv);
      this.cardinality += (int)((previous ^ newv) >>> i);
      return this;
   }

   public boolean isEmpty() {
      return this.cardinality == 0;
   }

   public MappeableArrayContainer and(MappeableArrayContainer value2) {
      MappeableArrayContainer answer = new MappeableArrayContainer(value2.content.limit());
      if (!BufferUtil.isBackedBySimpleArray(answer.content)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         char[] sarray = answer.content.array();
         if (BufferUtil.isBackedBySimpleArray(value2.content)) {
            char[] c = value2.content.array();
            int ca = value2.cardinality;

            for(int k = 0; k < ca; ++k) {
               char v = c[k];
               sarray[answer.cardinality] = v;
               answer.cardinality += (int)this.bitValue(v);
            }
         } else {
            int ca = value2.cardinality;

            for(int k = 0; k < ca; ++k) {
               char v = value2.content.get(k);
               sarray[answer.cardinality] = v;
               answer.cardinality += (int)this.bitValue(v);
            }
         }

         return answer;
      }
   }

   public MappeableContainer and(MappeableBitmapContainer value2) {
      int newCardinality = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
         long[] tb = this.bitmap.array();
         long[] v2b = value2.bitmap.array();
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(tb[k] & v2b[k]);
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(this.bitmap.get(k) & value2.bitmap.get(k));
         }
      }

      if (newCardinality > 4096) {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();
         if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
            throw new RuntimeException("Should not happen. Internal bug.");
         } else {
            long[] bitArray = answer.bitmap.array();
            if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
               long[] tb = this.bitmap.array();
               long[] v2b = value2.bitmap.array();
               int len = this.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  bitArray[k] = tb[k] & v2b[k];
               }
            } else {
               int len = this.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  bitArray[k] = this.bitmap.get(k) & value2.bitmap.get(k);
               }
            }

            answer.cardinality = newCardinality;
            return answer;
         }
      } else {
         MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
         if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
            Util.fillArrayAND(ac.content.array(), this.bitmap.array(), value2.bitmap.array());
         } else {
            BufferUtil.fillArrayAND(ac.content.array(), this.bitmap, value2.bitmap);
         }

         ac.cardinality = newCardinality;
         return ac;
      }
   }

   public MappeableContainer and(MappeableRunContainer value2) {
      return value2.and(this);
   }

   public MappeableContainer andNot(MappeableArrayContainer value2) {
      MappeableBitmapContainer answer = this.clone();
      if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] bitArray = answer.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(value2.content) && BufferUtil.isBackedBySimpleArray(this.bitmap)) {
            char[] v2 = value2.content.array();
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v = v2[k];
               int i = v >>> 6;
               long w = bitArray[i];
               long aft = w & ~(1L << v);
               bitArray[i] = aft;
               answer.cardinality = (int)((long)answer.cardinality - ((w ^ aft) >>> v));
            }
         } else {
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v2 = value2.content.get(k);
               int i = v2 >>> 6;
               long w = bitArray[i];
               long aft = bitArray[i] & ~(1L << v2);
               bitArray[i] = aft;
               answer.cardinality = (int)((long)answer.cardinality - ((w ^ aft) >>> v2));
            }
         }

         return (MappeableContainer)(answer.cardinality <= 4096 ? answer.toArrayContainer() : answer);
      }
   }

   public MappeableContainer andNot(MappeableBitmapContainer value2) {
      int newCardinality = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
         long[] b = this.bitmap.array();
         long[] v2 = value2.bitmap.array();
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(b[k] & ~v2[k]);
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(this.bitmap.get(k) & ~value2.bitmap.get(k));
         }
      }

      if (newCardinality > 4096) {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();
         if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
            throw new RuntimeException("Should not happen. Internal bug.");
         } else {
            long[] bitArray = answer.bitmap.array();
            if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
               long[] b = this.bitmap.array();
               long[] v2 = value2.bitmap.array();
               int len = answer.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  bitArray[k] = b[k] & ~v2[k];
               }
            } else {
               int len = answer.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  bitArray[k] = this.bitmap.get(k) & ~value2.bitmap.get(k);
               }
            }

            answer.cardinality = newCardinality;
            return answer;
         }
      } else {
         MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
         if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
            Util.fillArrayANDNOT(ac.content.array(), this.bitmap.array(), value2.bitmap.array());
         } else {
            BufferUtil.fillArrayANDNOT(ac.content.array(), this.bitmap, value2.bitmap);
         }

         ac.cardinality = newCardinality;
         return ac;
      }
   }

   public MappeableContainer andNot(MappeableRunContainer value2) {
      MappeableBitmapContainer answer = this.clone();
      long[] b = answer.bitmap.array();

      for(int rlepos = 0; rlepos < value2.nbrruns; ++rlepos) {
         int start = value2.getValue(rlepos);
         int end = value2.getValue(rlepos) + value2.getLength(rlepos) + 1;
         int prevOnesInRange = Util.cardinalityInBitmapRange(b, start, end);
         Util.resetBitmapRange(b, start, end);
         answer.updateCardinality(prevOnesInRange, 0);
      }

      return (MappeableContainer)(answer.getCardinality() > 4096 ? answer : answer.toArrayContainer());
   }

   public void clear() {
      if (this.cardinality != 0) {
         this.cardinality = 0;
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            this.bitmap.put(k, 0L);
         }
      }

   }

   public MappeableBitmapContainer clone() {
      return new MappeableBitmapContainer(this.cardinality, this.bitmap);
   }

   private void computeCardinality() {
      this.cardinality = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int k = 0; k < b.length; ++k) {
            this.cardinality += Long.bitCount(b[k]);
         }
      } else {
         int m = this.bitmap.limit();

         for(int k = 0; k < m; ++k) {
            this.cardinality += Long.bitCount(this.bitmap.get(k));
         }
      }

   }

   int cardinalityInRange(int start, int end) {
      assert this.cardinality != -1;

      if (end - start > 32768) {
         int before = BufferUtil.cardinalityInBitmapRange(this.bitmap, 0, start);
         int after = BufferUtil.cardinalityInBitmapRange(this.bitmap, end, 65536);
         return this.cardinality - before - after;
      } else {
         return BufferUtil.cardinalityInBitmapRange(this.bitmap, start, end);
      }
   }

   void updateCardinality(int prevOnes, int newOnes) {
      int oldCardinality = this.cardinality;
      this.cardinality = oldCardinality - prevOnes + newOnes;
   }

   public boolean contains(char i) {
      return (this.bitmap.get(i >>> 6) & 1L << i) != 0L;
   }

   long bitValue(char i) {
      return this.bitmap.get(i >>> 6) >>> i & 1L;
   }

   public static boolean contains(ByteBuffer buf, int position, char i) {
      return (buf.getLong((i >>> 6 << 3) + position) & 1L << i) != 0L;
   }

   public boolean equals(Object o) {
      if (o instanceof MappeableBitmapContainer) {
         MappeableBitmapContainer srb = (MappeableBitmapContainer)o;
         if (srb.cardinality != this.cardinality) {
            return false;
         } else {
            if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(srb.bitmap)) {
               long[] b = this.bitmap.array();
               long[] s = srb.bitmap.array();
               int len = this.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  if (b[k] != s[k]) {
                     return false;
                  }
               }
            } else {
               int len = this.bitmap.limit();

               for(int k = 0; k < len; ++k) {
                  if (this.bitmap.get(k) != srb.bitmap.get(k)) {
                     return false;
                  }
               }
            }

            return true;
         }
      } else {
         return o instanceof MappeableRunContainer ? o.equals(this) : false;
      }
   }

   public void fillLeastSignificant16bits(int[] x, int i, int mask) {
      int pos = i;
      int base = mask;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            for(long bitset = b[k]; bitset != 0L; bitset &= bitset - 1L) {
               x[pos++] = base + Long.numberOfTrailingZeros(bitset);
            }

            base += 64;
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            for(long bitset = this.bitmap.get(k); bitset != 0L; bitset &= bitset - 1L) {
               x[pos++] = base + Long.numberOfTrailingZeros(bitset);
            }

            base += 64;
         }
      }

   }

   public MappeableContainer flip(char i) {
      long bef = this.bitmap.get(i >>> 6);
      long mask = 1L << i;
      if (this.cardinality == 4097 && (bef & mask) != 0L) {
         --this.cardinality;
         this.bitmap.put(i >>> 6, bef & ~mask);
         return this.toArrayContainer();
      } else {
         long aft = bef ^ mask;
         this.cardinality += 1 - 2 * (int)((bef & mask) >>> i);
         this.bitmap.put(i >>> 6, aft);
         return this;
      }
   }

   protected int getArraySizeInBytes() {
      return 8192;
   }

   public int getCardinality() {
      return this.cardinality;
   }

   public CharIterator getReverseCharIterator() {
      return (CharIterator)(this.isArrayBacked() ? BitmapContainer.getReverseShortIterator(this.bitmap.array()) : new ReverseMappeableBitmapContainerCharIterator(this));
   }

   public PeekableCharIterator getCharIterator() {
      return (PeekableCharIterator)(this.isArrayBacked() ? BitmapContainer.getShortIterator(this.bitmap.array()) : new MappeableBitmapContainerCharIterator(this));
   }

   public ContainerBatchIterator getBatchIterator() {
      return new BitmapBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.bitmap.limit() * 8;
   }

   public int hashCode() {
      long hash = 0L;
      int len = this.bitmap.limit();

      for(int k = 0; k < len; ++k) {
         hash += 31L * hash + this.bitmap.get(k);
      }

      return (int)hash;
   }

   public MappeableContainer iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int prevOnesInRange = this.cardinalityInRange(begin, end);
         BufferUtil.setBitmapRange(this.bitmap, begin, end);
         this.updateCardinality(prevOnesInRange, end - begin);
         return this;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableContainer iand(MappeableArrayContainer b2) {
      if (-1 == this.cardinality) {
         BufferUtil.intersectArrayIntoBitmap(this.bitmap, b2.content, b2.cardinality);
         return this;
      } else {
         return b2.and(this);
      }
   }

   public MappeableContainer iand(MappeableBitmapContainer b2) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
         int newCardinality = 0;
         long[] tb = this.bitmap.array();
         long[] tb2 = b2.bitmap.array();
         int len = this.bitmap.limit();
         if (-1 == this.cardinality) {
            for(int k = 0; k < len; ++k) {
               tb[k] &= tb2[k];
            }

            return this;
         } else {
            for(int k = 0; k < len; ++k) {
               newCardinality += Long.bitCount(tb[k] & tb2[k]);
            }

            if (newCardinality <= 4096) {
               MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
               BufferUtil.fillArrayAND(ac.content.array(), this.bitmap, b2.bitmap);
               ac.cardinality = newCardinality;
               return ac;
            } else {
               for(int k = 0; k < len; ++k) {
                  tb[k] &= tb2[k];
               }

               this.cardinality = newCardinality;
               return this;
            }
         }
      } else if (-1 != this.cardinality) {
         int newCardinality = 0;
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(this.bitmap.get(k) & b2.bitmap.get(k));
         }

         if (newCardinality <= 4096) {
            MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
            BufferUtil.fillArrayAND(ac.content.array(), this.bitmap, b2.bitmap);
            ac.cardinality = newCardinality;
            return ac;
         } else {
            for(int k = 0; k < len; ++k) {
               this.bitmap.put(k, this.bitmap.get(k) & b2.bitmap.get(k));
            }

            this.cardinality = newCardinality;
            return this;
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            this.bitmap.put(k, this.bitmap.get(k) & b2.bitmap.get(k));
         }

         return this;
      }
   }

   public MappeableContainer iand(MappeableRunContainer x) {
      int card = x.getCardinality();
      if (-1 != this.cardinality && card <= 4096) {
         MappeableArrayContainer answer = new MappeableArrayContainer(card);
         answer.cardinality = 0;

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int runStart = x.getValue(rlepos);
            int runEnd = runStart + x.getLength(rlepos);

            for(int runValue = runStart; runValue <= runEnd; ++runValue) {
               answer.content.put(answer.cardinality, (char)runValue);
               answer.cardinality += (int)this.bitValue((char)runValue);
            }
         }

         return answer;
      } else {
         int start = 0;

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int end = x.getValue(rlepos);
            if (-1 == this.cardinality) {
               BufferUtil.resetBitmapRange(this.bitmap, start, end);
            } else {
               int prevOnes = this.cardinalityInRange(start, end);
               BufferUtil.resetBitmapRange(this.bitmap, start, end);
               this.updateCardinality(prevOnes, 0);
            }

            start = end + x.getLength(rlepos) + 1;
         }

         if (-1 == this.cardinality) {
            BufferUtil.resetBitmapRange(this.bitmap, start, 65536);
         } else {
            int ones = this.cardinalityInRange(start, 65536);
            BufferUtil.resetBitmapRange(this.bitmap, start, 65536);
            this.updateCardinality(ones, 0);
            if (this.getCardinality() <= 4096) {
               return this.toArrayContainer();
            }
         }

         return this;
      }
   }

   public MappeableContainer iandNot(MappeableArrayContainer b2) {
      for(int k = 0; k < b2.cardinality; ++k) {
         this.remove(b2.content.get(k));
      }

      return (MappeableContainer)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
   }

   public MappeableContainer iandNot(MappeableBitmapContainer b2) {
      int newCardinality = 0;
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
            long[] b2Arr = b2.bitmap.array();
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               newCardinality += Long.bitCount(b[k] & ~b2Arr[k]);
            }

            if (newCardinality <= 4096) {
               MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
               Util.fillArrayANDNOT(ac.content.array(), b, b2Arr);
               ac.cardinality = newCardinality;
               return ac;
            } else {
               for(int k = 0; k < len; ++k) {
                  this.bitmap.put(k, b[k] & ~b2Arr[k]);
               }

               this.cardinality = newCardinality;
               return this;
            }
         } else {
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               newCardinality += Long.bitCount(b[k] & ~b2.bitmap.get(k));
            }

            if (newCardinality <= 4096) {
               MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
               BufferUtil.fillArrayANDNOT(ac.content.array(), this.bitmap, b2.bitmap);
               ac.cardinality = newCardinality;
               return ac;
            } else {
               for(int k = 0; k < len; ++k) {
                  b[k] &= ~b2.bitmap.get(k);
               }

               this.cardinality = newCardinality;
               return this;
            }
         }
      }
   }

   public MappeableContainer iandNot(MappeableRunContainer x) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnesInRange = Util.cardinalityInBitmapRange(b, start, end);
            Util.resetBitmapRange(b, start, end);
            this.updateCardinality(prevOnesInRange, 0);
         }

         return (MappeableContainer)(this.getCardinality() > 4096 ? this : this.toArrayContainer());
      } else {
         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnesInRange = this.cardinalityInRange(start, end);
            BufferUtil.resetBitmapRange(this.bitmap, start, end);
            this.updateCardinality(prevOnesInRange, 0);
         }

         return (MappeableContainer)(this.getCardinality() > 4096 ? this : this.toArrayContainer());
      }
   }

   MappeableContainer ilazyor(MappeableArrayContainer value2) {
      this.cardinality = -1;
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         int c = value2.cardinality;

         for(int k = 0; k < c; ++k) {
            char v2 = value2.content.get(k);
            int i = v2 >>> 6;
            b[i] |= 1L << v2;
         }

         return this;
      }
   }

   MappeableContainer ilazyor(MappeableBitmapContainer x) {
      if (BufferUtil.isBackedBySimpleArray(x.bitmap)) {
         long[] b = this.bitmap.array();
         long[] b2 = x.bitmap.array();

         for(int k = 0; k < b.length; ++k) {
            b[k] |= b2[k];
         }
      } else {
         int m = this.bitmap.limit();

         for(int k = 0; k < m; ++k) {
            this.bitmap.put(k, this.bitmap.get(k) | x.bitmap.get(k));
         }
      }

      this.cardinality = -1;
      return this;
   }

   MappeableContainer ilazyor(MappeableRunContainer x) {
      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         BufferUtil.setBitmapRange(this.bitmap, start, end);
      }

      this.cardinality = -1;
      return this;
   }

   public MappeableContainer inot(int firstOfRange, int lastOfRange) {
      int prevOnes = this.cardinalityInRange(firstOfRange, lastOfRange);
      BufferUtil.flipBitmapRange(this.bitmap, firstOfRange, lastOfRange);
      this.updateCardinality(prevOnes, lastOfRange - firstOfRange - prevOnes);
      return (MappeableContainer)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
   }

   public boolean intersects(MappeableArrayContainer value2) {
      if (BufferUtil.isBackedBySimpleArray(value2.content)) {
         char[] c = value2.content.array();
         int ca = value2.cardinality;

         for(int k = 0; k < ca; ++k) {
            if (this.contains(c[k])) {
               return true;
            }
         }
      } else {
         int ca = value2.cardinality;

         for(int k = 0; k < ca; ++k) {
            char v = value2.content.get(k);
            if (this.contains(v)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean intersects(MappeableBitmapContainer value2) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
         long[] tb = this.bitmap.array();
         long[] v2b = value2.bitmap.array();
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            if ((tb[k] & v2b[k]) != 0L) {
               return true;
            }
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            if ((this.bitmap.get(k) & value2.bitmap.get(k)) != 0L) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean intersects(MappeableRunContainer x) {
      return x.intersects(this);
   }

   public MappeableBitmapContainer ior(MappeableArrayContainer value2) {
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(value2.content)) {
            char[] v2 = value2.content.array();
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               int i = v2[k] >>> 6;
               long bef = b[i];
               long aft = bef | 1L << v2[k];
               b[i] = aft;
               this.cardinality += (int)(bef - aft >>> 63);
            }

            return this;
         } else {
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v2 = value2.content.get(k);
               int i = v2 >>> 6;
               long bef = b[i];
               long aft = bef | 1L << v2;
               b[i] = aft;
               this.cardinality += (int)(bef - aft >>> 63);
            }

            return this;
         }
      }
   }

   public MappeableContainer ior(MappeableBitmapContainer b2) {
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         this.cardinality = 0;
         if (BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
            long[] b2Arr = b2.bitmap.array();
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               long w = b[k] | b2Arr[k];
               b[k] = w;
               this.cardinality += Long.bitCount(w);
            }

            return (MappeableContainer)(this.isFull() ? MappeableRunContainer.full() : this);
         } else {
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               long w = b[k] | b2.bitmap.get(k);
               b[k] = w;
               this.cardinality += Long.bitCount(w);
            }

            return (MappeableContainer)(this.isFull() ? MappeableRunContainer.full() : this);
         }
      }
   }

   public boolean isFull() {
      return this.cardinality == 65536;
   }

   public void orInto(long[] bits) {
      for(int i = 0; i < bits.length; ++i) {
         bits[i] |= this.bitmap.get(i);
      }

   }

   public void andInto(long[] bits) {
      for(int i = 0; i < bits.length; ++i) {
         bits[i] &= this.bitmap.get(i);
      }

   }

   public void removeFrom(long[] bits) {
      for(int i = 0; i < bits.length; ++i) {
         bits[i] &= ~this.bitmap.get(i);
      }

   }

   public MappeableContainer ior(MappeableRunContainer x) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnesInRange = Util.cardinalityInBitmapRange(b, start, end);
            Util.setBitmapRange(b, start, end);
            this.updateCardinality(prevOnesInRange, end - start);
         }
      } else {
         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnesInRange = this.cardinalityInRange(start, end);
            BufferUtil.setBitmapRange(this.bitmap, start, end);
            this.updateCardinality(prevOnesInRange, end - start);
         }
      }

      return (MappeableContainer)(this.isFull() ? MappeableRunContainer.full() : this);
   }

   public MappeableContainer iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int prevOnesInRange = this.cardinalityInRange(begin, end);
         BufferUtil.resetBitmapRange(this.bitmap, begin, end);
         this.updateCardinality(prevOnesInRange, 0);
         return (MappeableContainer)(this.getCardinality() < 4096 ? this.toArrayContainer() : this);
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   protected boolean isArrayBacked() {
      return BufferUtil.isBackedBySimpleArray(this.bitmap);
   }

   public Iterator iterator() {
      return new Iterator() {
         final CharIterator si = MappeableBitmapContainer.this.getCharIterator();

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

   public MappeableContainer ixor(MappeableArrayContainer value2) {
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(value2.content)) {
            char[] v2 = value2.content.array();
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char vc = v2[k];
               long mask = 1L << v2[k];
               int index = vc >>> 6;
               long ba = b[index];
               this.cardinality += 1 - 2 * (int)((ba & mask) >>> vc);
               b[index] = ba ^ mask;
            }
         } else {
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v2 = value2.content.get(k);
               long mask = 1L << v2;
               int index = v2 >>> 6;
               long ba = b[index];
               this.cardinality += 1 - 2 * (int)((ba & mask) >>> v2);
               b[index] = ba ^ mask;
            }
         }

         return (MappeableContainer)(this.cardinality <= 4096 ? this.toArrayContainer() : this);
      }
   }

   public MappeableContainer ixor(MappeableBitmapContainer b2) {
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = this.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
            long[] b2Arr = b2.bitmap.array();
            int newCardinality = 0;
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               newCardinality += Long.bitCount(b[k] ^ b2Arr[k]);
            }

            if (newCardinality <= 4096) {
               MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
               Util.fillArrayXOR(ac.content.array(), b, b2Arr);
               ac.cardinality = newCardinality;
               return ac;
            } else {
               for(int k = 0; k < len; ++k) {
                  b[k] ^= b2Arr[k];
               }

               this.cardinality = newCardinality;
               return this;
            }
         } else {
            int newCardinality = 0;
            int len = this.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               newCardinality += Long.bitCount(b[k] ^ b2.bitmap.get(k));
            }

            if (newCardinality <= 4096) {
               MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
               BufferUtil.fillArrayXOR(ac.content.array(), this.bitmap, b2.bitmap);
               ac.cardinality = newCardinality;
               return ac;
            } else {
               for(int k = 0; k < len; ++k) {
                  b[k] ^= b2.bitmap.get(k);
               }

               this.cardinality = newCardinality;
               return this;
            }
         }
      }
   }

   public MappeableContainer ixor(MappeableRunContainer x) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnes = Util.cardinalityInBitmapRange(b, start, end);
            Util.flipBitmapRange(b, start, end);
            this.updateCardinality(prevOnes, end - start - prevOnes);
         }
      } else {
         for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
            int start = x.getValue(rlepos);
            int end = start + x.getLength(rlepos) + 1;
            int prevOnes = this.cardinalityInRange(start, end);
            BufferUtil.flipBitmapRange(this.bitmap, start, end);
            this.updateCardinality(prevOnes, end - start - prevOnes);
         }
      }

      return (MappeableContainer)(this.getCardinality() > 4096 ? this : this.toArrayContainer());
   }

   protected MappeableContainer lazyor(MappeableArrayContainer value2) {
      MappeableBitmapContainer answer = this.clone();
      answer.cardinality = -1;
      if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = answer.bitmap.array();
         int c = value2.cardinality;

         for(int k = 0; k < c; ++k) {
            char v2 = value2.content.get(k);
            int i = v2 >>> 6;
            b[i] |= 1L << v2;
         }

         return answer;
      }
   }

   protected MappeableContainer lazyor(MappeableBitmapContainer x) {
      MappeableBitmapContainer answer = new MappeableBitmapContainer();
      answer.cardinality = -1;
      if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] b = answer.bitmap.array();

         for(int k = 0; k < b.length; ++k) {
            b[k] = this.bitmap.get(k) | x.bitmap.get(k);
         }

         return answer;
      }
   }

   protected MappeableContainer lazyor(MappeableRunContainer x) {
      MappeableBitmapContainer bc = this.clone();
      bc.cardinality = -1;
      long[] b = bc.bitmap.array();

      for(int rlepos = 0; rlepos < x.nbrruns; ++rlepos) {
         int start = x.getValue(rlepos);
         int end = start + x.getLength(rlepos) + 1;
         Util.setBitmapRange(b, start, end);
      }

      return bc;
   }

   public MappeableContainer limit(int maxcardinality) {
      if (maxcardinality >= this.cardinality) {
         return this.clone();
      } else if (maxcardinality <= 4096) {
         MappeableArrayContainer ac = new MappeableArrayContainer(maxcardinality);
         int pos = 0;
         if (!BufferUtil.isBackedBySimpleArray(ac.content)) {
            throw new RuntimeException("Should not happen. Internal bug.");
         } else {
            char[] cont = ac.content.array();
            int len = this.bitmap.limit();

            for(int k = 0; ac.cardinality < maxcardinality && k < len; ++k) {
               for(long bitset = this.bitmap.get(k); ac.cardinality < maxcardinality && bitset != 0L; bitset &= bitset - 1L) {
                  cont[pos++] = (char)(k * 64 + Long.numberOfTrailingZeros(bitset));
                  ++ac.cardinality;
               }
            }

            return ac;
         }
      } else {
         LongBuffer newBitmap = LongBuffer.allocate(1024);
         MappeableBitmapContainer bc = new MappeableBitmapContainer(newBitmap, maxcardinality);
         int s = this.select(maxcardinality);
         int usedwords = s + 63 >>> 6;
         if (this.isArrayBacked()) {
            long[] source = this.bitmap.array();
            long[] dest = newBitmap.array();
            System.arraycopy(source, 0, dest, 0, usedwords);
         } else {
            for(int k = 0; k < usedwords; ++k) {
               bc.bitmap.put(k, this.bitmap.get(k));
            }
         }

         int lastword = s % 64;
         if (lastword != 0) {
            bc.bitmap.put(s >>> 6, bc.bitmap.get(s >>> 6) & -1L >>> 64 - lastword);
         }

         return bc;
      }
   }

   void loadData(MappeableArrayContainer arrayContainer) {
      this.cardinality = arrayContainer.cardinality;
      if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] bitArray = this.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(arrayContainer.content)) {
            long[] b = this.bitmap.array();
            char[] ac = arrayContainer.content.array();

            for(int k = 0; k < arrayContainer.cardinality; ++k) {
               char x = ac[k];
               bitArray[x >>> 6] = b[x >>> 6] | 1L << x;
            }
         } else {
            for(int k = 0; k < arrayContainer.cardinality; ++k) {
               char x = arrayContainer.content.get(k);
               bitArray[x >>> 6] = this.bitmap.get(x >>> 6) | 1L << x;
            }
         }

      }
   }

   public int nextSetBit(int i) {
      int x = i >> 6;
      long w = this.bitmap.get(x);
      w >>>= i;
      if (w != 0L) {
         return i + Long.numberOfTrailingZeros(w);
      } else {
         ++x;

         while(x < 1024) {
            long X = this.bitmap.get(x);
            if (X != 0L) {
               return x * 64 + Long.numberOfTrailingZeros(X);
            }

            ++x;
         }

         return -1;
      }
   }

   private int nextClearBit(int i) {
      int x = i >> 6;
      long w = ~this.bitmap.get(x);
      w >>>= i;
      if (w != 0L) {
         return i + Long.numberOfTrailingZeros(w);
      } else {
         int length = this.bitmap.limit();
         ++x;

         while(x < length) {
            long map = ~this.bitmap.get(x);
            if (map != 0L) {
               return x * 64 + Long.numberOfTrailingZeros(map);
            }

            ++x;
         }

         return 65536;
      }
   }

   public MappeableContainer not(int firstOfRange, int lastOfRange) {
      MappeableBitmapContainer answer = this.clone();
      return answer.inot(firstOfRange, lastOfRange);
   }

   int numberOfRuns() {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] src = this.bitmap.array();
         int numRuns = 0;
         long nextWord = src[0];

         for(int i = 0; i < src.length - 1; ++i) {
            long word = nextWord;
            nextWord = src[i + 1];
            numRuns += Long.bitCount(~word & word << 1) + (int)(word >>> 63 & ~nextWord);
         }

         numRuns += Long.bitCount(~nextWord & nextWord << 1);
         if ((nextWord & Long.MIN_VALUE) != 0L) {
            ++numRuns;
         }

         return numRuns;
      } else {
         int numRuns = 0;
         long nextWord = this.bitmap.get(0);
         int len = this.bitmap.limit();

         for(int i = 0; i < len - 1; ++i) {
            long word = nextWord;
            nextWord = this.bitmap.get(i + 1);
            numRuns += Long.bitCount(~word & word << 1) + (int)(word >>> 63 & ~nextWord);
         }

         numRuns += Long.bitCount(~nextWord & nextWord << 1);
         if ((nextWord & Long.MIN_VALUE) != 0L) {
            ++numRuns;
         }

         return numRuns;
      }
   }

   private int numberOfRunsAdjustment() {
      int ans = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();
         long nextWord = b[0];

         for(int i = 0; i < b.length - 1; ++i) {
            long word = nextWord;
            nextWord = b[i + 1];
            ans += (int)(word >>> 63 & ~nextWord);
         }

         if ((nextWord & Long.MIN_VALUE) != 0L) {
            ++ans;
         }
      } else {
         long nextWord = this.bitmap.get(0);
         int len = this.bitmap.limit();

         for(int i = 0; i < len - 1; ++i) {
            long word = nextWord;
            nextWord = this.bitmap.get(i + 1);
            ans += (int)(word >>> 63 & ~nextWord);
         }

         if ((nextWord & Long.MIN_VALUE) != 0L) {
            ++ans;
         }
      }

      return ans;
   }

   private int numberOfRunsLowerBound(int mustNotExceed) {
      int numRuns = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int blockOffset = 0; blockOffset + 128 <= b.length; blockOffset += 128) {
            for(int i = blockOffset; i < blockOffset + 128; ++i) {
               long word = b[i];
               numRuns += Long.bitCount(~word & word << 1);
            }

            if (numRuns > mustNotExceed) {
               return numRuns;
            }
         }
      } else {
         int len = this.bitmap.limit();

         for(int blockOffset = 0; blockOffset < len; blockOffset += 128) {
            for(int i = blockOffset; i < blockOffset + 128; ++i) {
               long word = this.bitmap.get(i);
               numRuns += Long.bitCount(~word & word << 1);
            }

            if (numRuns > mustNotExceed) {
               return numRuns;
            }
         }
      }

      return numRuns;
   }

   public MappeableContainer or(MappeableArrayContainer value2) {
      MappeableBitmapContainer answer = this.clone();
      if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] bitArray = answer.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(answer.bitmap) && BufferUtil.isBackedBySimpleArray(value2.content)) {
            long[] ab = answer.bitmap.array();
            char[] v2 = value2.content.array();
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v = v2[k];
               int i = v >>> 6;
               long w = ab[i];
               long aft = w | 1L << v;
               bitArray[i] = aft;
               answer.cardinality += (int)(w - aft >>> 63);
            }
         } else {
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v2 = value2.content.get(k);
               int i = v2 >>> 6;
               long w = answer.bitmap.get(i);
               long aft = w | 1L << v2;
               bitArray[i] = aft;
               answer.cardinality += (int)(w - aft >>> 63);
            }
         }

         return (MappeableContainer)(answer.isFull() ? MappeableRunContainer.full() : answer);
      }
   }

   public MappeableContainer or(MappeableBitmapContainer value2) {
      MappeableBitmapContainer value1 = this.clone();
      return value1.ior(value2);
   }

   public MappeableContainer or(MappeableRunContainer value2) {
      return value2.or(this);
   }

   int prevSetBit(int i) {
      int x = i >> 6;
      long w = this.bitmap.get(x);
      w <<= 64 - i - 1;
      if (w != 0L) {
         return i - Long.numberOfLeadingZeros(w);
      } else {
         --x;

         while(x >= 0) {
            long X = this.bitmap.get(x);
            if (X != 0L) {
               return x * 64 + 63 - Long.numberOfLeadingZeros(X);
            }

            --x;
         }

         return -1;
      }
   }

   private int prevClearBit(int i) {
      int x = i >> 6;
      long w = ~this.bitmap.get(x);
      w <<= 64 - i - 1;
      if (w != 0L) {
         return i - Long.numberOfLeadingZeros(w);
      } else {
         --x;

         while(x >= 0) {
            long map = ~this.bitmap.get(x);
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
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int k = 0; k < lowbits + 1 >>> 6; ++k) {
            answer += Long.bitCount(b[k]);
         }

         if (leftover != 0) {
            answer += Long.bitCount(b[lowbits + 1 >>> 6] << 64 - leftover);
         }
      } else {
         for(int k = 0; k < lowbits + 1 >>> 6; ++k) {
            answer += Long.bitCount(this.bitmap.get(k));
         }

         if (leftover != 0) {
            answer += Long.bitCount(this.bitmap.get(lowbits + 1 >>> 6) << 64 - leftover);
         }
      }

      return answer;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.cardinality = 0;
      int len = this.bitmap.limit();
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int k = 0; k < len; ++k) {
            long w = Long.reverseBytes(in.readLong());
            b[k] = w;
            this.cardinality += Long.bitCount(w);
         }
      } else {
         for(int k = 0; k < len; ++k) {
            long w = Long.reverseBytes(in.readLong());
            this.bitmap.put(k, w);
            this.cardinality += Long.bitCount(w);
         }
      }

   }

   public MappeableContainer remove(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         MappeableBitmapContainer answer = this.clone();
         int prevOnesInRange = answer.cardinalityInRange(begin, end);
         BufferUtil.resetBitmapRange(answer.bitmap, begin, end);
         answer.updateCardinality(prevOnesInRange, 0);
         return (MappeableContainer)(answer.getCardinality() < 4096 ? answer.toArrayContainer() : answer);
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableContainer remove(char i) {
      long X = this.bitmap.get(i >>> 6);
      long mask = 1L << i;
      if (this.cardinality == 4097 && (X & mask) != 0L) {
         --this.cardinality;
         this.bitmap.put(i >>> 6, X & ~mask);
         return this.toArrayContainer();
      } else {
         long aft = X & ~mask;
         this.cardinality = (int)((long)this.cardinality - (aft - X >>> 63));
         this.bitmap.put(i >>> 6, aft);
         return this;
      }
   }

   public MappeableContainer repairAfterLazy() {
      if (this.getCardinality() < 0) {
         this.computeCardinality();
         if (this.getCardinality() <= 4096) {
            return this.toArrayContainer();
         }

         if (this.isFull()) {
            return MappeableRunContainer.full();
         }
      }

      return this;
   }

   public MappeableContainer runOptimize() {
      int numRuns = this.numberOfRunsLowerBound(2047);
      int sizeAsRunContainerLowerBound = MappeableRunContainer.serializedSizeInBytes(numRuns);
      if (sizeAsRunContainerLowerBound >= this.getArraySizeInBytes()) {
         return this;
      } else {
         numRuns += this.numberOfRunsAdjustment();
         int sizeAsRunContainer = MappeableRunContainer.serializedSizeInBytes(numRuns);
         return (MappeableContainer)(this.getArraySizeInBytes() > sizeAsRunContainer ? new MappeableRunContainer(this, numRuns) : this);
      }
   }

   public char select(int j) {
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();
         if (this.cardinality >>> 1 < j && j < this.cardinality) {
            int leftover = this.cardinality - j;

            for(int k = b.length - 1; k >= 0; --k) {
               long w = b[k];
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

            for(int k = 0; k < b.length; ++k) {
               long w = b[k];
               if (w != 0L) {
                  int bits = Long.bitCount(w);
                  if (bits > leftover) {
                     return (char)(k * 64 + Util.select(w, leftover));
                  }

                  leftover -= bits;
               }
            }
         }
      } else {
         int len = this.bitmap.limit();
         if (this.cardinality >>> 1 < j && j < this.cardinality) {
            int leftover = this.cardinality - j;

            for(int k = len - 1; k >= 0; --k) {
               int w = Long.bitCount(this.bitmap.get(k));
               if (w >= leftover) {
                  return (char)(k * 64 + Util.select(this.bitmap.get(k), w - leftover));
               }

               leftover -= w;
            }
         } else {
            int leftover = j;

            for(int k = 0; k < len; ++k) {
               long X = this.bitmap.get(k);
               int w = Long.bitCount(X);
               if (w > leftover) {
                  return (char)(k * 64 + Util.select(X, leftover));
               }

               leftover -= w;
            }
         }
      }

      throw new IllegalArgumentException("Insufficient cardinality.");
   }

   public char selectOneSide(int j) {
      int leftover = j;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int k = 0; k < b.length; ++k) {
            int w = Long.bitCount(b[k]);
            if (w > leftover) {
               return (char)(k * 64 + Util.select(b[k], leftover));
            }

            leftover -= w;
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            long X = this.bitmap.get(k);
            int w = Long.bitCount(X);
            if (w > leftover) {
               return (char)(k * 64 + Util.select(X, leftover));
            }

            leftover -= w;
         }
      }

      throw new IllegalArgumentException("Insufficient cardinality.");
   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(0);
   }

   MappeableArrayContainer toArrayContainer() {
      MappeableArrayContainer ac = new MappeableArrayContainer(this.cardinality);
      ac.loadData(this);
      if (ac.getCardinality() != this.cardinality) {
         throw new RuntimeException("Internal error.");
      } else {
         return ac;
      }
   }

   public Container toContainer() {
      return new BitmapContainer(this);
   }

   public long[] toLongArray() {
      long[] answer = new long[this.bitmap.limit()];
      this.bitmap.rewind();
      this.bitmap.get(answer);
      return answer;
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

   protected void writeArray(DataOutput out) throws IOException {
      int len = this.bitmap.limit();
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int k = 0; k < len; ++k) {
            out.writeLong(Long.reverseBytes(b[k]));
         }
      } else {
         for(int k = 0; k < len; ++k) {
            long w = this.bitmap.get(k);
            out.writeLong(Long.reverseBytes(w));
         }
      }

   }

   protected void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      LongBuffer buf = this.bitmap.duplicate();
      buf.position(0);
      buffer.asLongBuffer().put(buf);
      buffer.position(buffer.position() + buf.position() * 8);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.writeArray((DataOutput)out);
   }

   public MappeableContainer xor(MappeableArrayContainer value2) {
      MappeableBitmapContainer answer = this.clone();
      if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         long[] bitArray = answer.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(value2.content)) {
            char[] v2 = value2.content.array();
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char vc = v2[k];
               long mask = 1L << vc;
               int index = vc >>> 6;
               long ba = bitArray[index];
               answer.cardinality += 1 - 2 * (int)((ba & mask) >>> vc);
               bitArray[index] = ba ^ mask;
            }
         } else {
            int c = value2.cardinality;

            for(int k = 0; k < c; ++k) {
               char v2 = value2.content.get(k);
               long mask = 1L << v2;
               int index = v2 >>> 6;
               long ba = bitArray[index];
               answer.cardinality += 1 - 2 * (int)((ba & mask) >>> v2);
               bitArray[index] = ba ^ mask;
            }
         }

         return (MappeableContainer)(answer.cardinality <= 4096 ? answer.toArrayContainer() : answer);
      }
   }

   public MappeableContainer xor(MappeableBitmapContainer value2) {
      int newCardinality = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
         long[] b = this.bitmap.array();
         long[] v2 = value2.bitmap.array();
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(b[k] ^ v2[k]);
         }
      } else {
         int len = this.bitmap.limit();

         for(int k = 0; k < len; ++k) {
            newCardinality += Long.bitCount(this.bitmap.get(k) ^ value2.bitmap.get(k));
         }
      }

      if (newCardinality <= 4096) {
         MappeableArrayContainer ac = new MappeableArrayContainer(newCardinality);
         BufferUtil.fillArrayXOR(ac.content.array(), this.bitmap, value2.bitmap);
         ac.cardinality = newCardinality;
         return ac;
      } else {
         MappeableBitmapContainer answer = new MappeableBitmapContainer();
         long[] bitArray = answer.bitmap.array();
         if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
            long[] b = this.bitmap.array();
            long[] v2 = value2.bitmap.array();
            int len = answer.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               bitArray[k] = b[k] ^ v2[k];
            }
         } else {
            int len = answer.bitmap.limit();

            for(int k = 0; k < len; ++k) {
               bitArray[k] = this.bitmap.get(k) ^ value2.bitmap.get(k);
            }
         }

         answer.cardinality = newCardinality;
         return answer;
      }
   }

   public MappeableContainer xor(MappeableRunContainer value2) {
      return value2.xor(this);
   }

   public void forEach(char msb, IntConsumer ic) {
      int high = msb << 16;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] b = this.bitmap.array();

         for(int x = 0; x < b.length; ++x) {
            for(long w = b[x]; w != 0L; w &= w - 1L) {
               ic.accept(x * 64 + Long.numberOfTrailingZeros(w) | high);
            }
         }
      } else {
         int l = this.bitmap.limit();

         for(int x = 0; x < l; ++x) {
            for(long w = this.bitmap.get(x); w != 0L; w &= w - 1L) {
               ic.accept(x * 64 + Long.numberOfTrailingZeros(w) | high);
            }
         }
      }

   }

   public int andCardinality(MappeableArrayContainer value2) {
      int answer = 0;
      int c = value2.cardinality;

      for(int k = 0; k < c; ++k) {
         char v = value2.content.get(k);
         answer += (int)this.bitValue(v);
      }

      return answer;
   }

   public int andCardinality(MappeableBitmapContainer value2) {
      int newCardinality = 0;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
         long[] b1 = this.bitmap.array();
         long[] b2 = value2.bitmap.array();

         for(int k = 0; k < b1.length; ++k) {
            newCardinality += Long.bitCount(b1[k] & b2[k]);
         }
      } else {
         int size = this.bitmap.limit();

         for(int k = 0; k < size; ++k) {
            newCardinality += Long.bitCount(this.bitmap.get(k) & value2.bitmap.get(k));
         }
      }

      return newCardinality;
   }

   public int andCardinality(MappeableRunContainer x) {
      return x.andCardinality(this);
   }

   public MappeableBitmapContainer toBitmapContainer() {
      return this;
   }

   public int first() {
      this.assertNonEmpty(this.cardinality == 0);
      int i = 0;
      long firstNonZeroWord;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] array;
         for(array = this.bitmap.array(); array[i] == 0L; ++i) {
         }

         firstNonZeroWord = array[i];
      } else {
         for(i = this.bitmap.position(); this.bitmap.get(i) == 0L; ++i) {
         }

         firstNonZeroWord = this.bitmap.get(i);
      }

      return i * 64 + Long.numberOfTrailingZeros(firstNonZeroWord);
   }

   public int last() {
      this.assertNonEmpty(this.cardinality == 0);
      int i = this.bitmap.limit() - 1;
      long lastNonZeroWord;
      if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
         long[] array;
         for(array = this.bitmap.array(); i > 0 && array[i] == 0L; --i) {
         }

         lastNonZeroWord = array[i];
      } else {
         while(i > 0 && this.bitmap.get(i) == 0L) {
            --i;
         }

         lastNonZeroWord = this.bitmap.get(i);
      }

      return (i + 1) * 64 - Long.numberOfLeadingZeros(lastNonZeroWord) - 1;
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

   protected boolean contains(MappeableBitmapContainer bitmapContainer) {
      if (this.cardinality != -1 && bitmapContainer.cardinality != -1 && this.cardinality < bitmapContainer.cardinality) {
         return false;
      } else {
         for(int i = 0; i < 1024; ++i) {
            if ((this.bitmap.get(i) & bitmapContainer.bitmap.get(i)) != bitmapContainer.bitmap.get(i)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         int start = minimum >>> 6;
         int end = supremum >>> 6;
         if (start == end) {
            return (this.bitmap.get(start) & -(1L << minimum) & (1L << supremum) - 1L) != 0L;
         } else if ((this.bitmap.get(start) & -(1L << minimum)) != 0L) {
            return true;
         } else if (end < this.bitmap.limit() && (this.bitmap.get(end) & (1L << supremum) - 1L) != 0L) {
            return true;
         } else {
            for(int i = 1 + start; i < end && i < this.bitmap.limit(); ++i) {
               if (this.bitmap.get(i) != 0L) {
                  return true;
               }
            }

            return false;
         }
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public boolean contains(int minimum, int supremum) {
      int start = minimum >>> 6;
      int end = supremum >>> 6;
      long first = -(1L << minimum);
      long last = (1L << supremum) - 1L;
      if (start == end) {
         return (this.bitmap.get(end) & first & last) == (first & last);
      } else if ((this.bitmap.get(start) & first) != first) {
         return false;
      } else if (end < this.bitmap.limit() && (this.bitmap.get(end) & last) != last) {
         return false;
      } else {
         for(int i = start + 1; i < this.bitmap.limit() && i < end; ++i) {
            if (this.bitmap.get(i) != -1L) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean contains(MappeableRunContainer runContainer) {
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

   protected boolean contains(MappeableArrayContainer arrayContainer) {
      if (arrayContainer.cardinality != -1 && this.cardinality < arrayContainer.cardinality) {
         return false;
      } else {
         for(int i = 0; i < arrayContainer.cardinality; ++i) {
            if (!this.contains(arrayContainer.content.get(i))) {
               return false;
            }
         }

         return true;
      }
   }
}
