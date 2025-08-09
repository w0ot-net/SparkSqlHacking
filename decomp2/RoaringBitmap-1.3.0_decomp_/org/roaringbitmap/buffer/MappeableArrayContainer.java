package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.roaringbitmap.ArrayContainer;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.Container;
import org.roaringbitmap.ContainerBatchIterator;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.Util;

public final class MappeableArrayContainer extends MappeableContainer implements Cloneable {
   private static final int DEFAULT_INIT_SIZE = 4;
   private static final int ARRAY_LAZY_LOWERBOUND = 1024;
   protected static final int DEFAULT_MAX_SIZE = 4096;
   private static final long serialVersionUID = 1L;
   protected int cardinality;
   protected CharBuffer content;

   protected static int getArraySizeInBytes(int cardinality) {
      return cardinality * 2;
   }

   protected static int serializedSizeInBytes(int cardinality) {
      return cardinality * 2 + 2;
   }

   public MappeableArrayContainer() {
      this(4);
   }

   public static MappeableArrayContainer empty() {
      return new MappeableArrayContainer();
   }

   public MappeableArrayContainer(ArrayContainer bc) {
      this.cardinality = 0;
      this.cardinality = bc.getCardinality();
      this.content = bc.toCharBuffer();
   }

   public MappeableArrayContainer(int capacity) {
      this.cardinality = 0;
      this.content = CharBuffer.allocate(capacity);
   }

   public MappeableArrayContainer(int firstOfRun, int lastOfRun) {
      this.cardinality = 0;
      int valuesInRange = lastOfRun - firstOfRun;
      this.content = CharBuffer.allocate(valuesInRange);
      char[] sarray = this.content.array();

      for(int i = 0; i < valuesInRange; ++i) {
         sarray[i] = (char)(firstOfRun + i);
      }

      this.cardinality = valuesInRange;
   }

   private MappeableArrayContainer(int newCard, CharBuffer newContent) {
      this.cardinality = 0;
      this.cardinality = newCard;
      CharBuffer tmp = newContent.duplicate();
      this.content = CharBuffer.allocate(Math.max(newCard, tmp.limit()));
      tmp.rewind();
      this.content.put(tmp);
   }

   public MappeableArrayContainer(CharBuffer array, int cardinality) {
      this.cardinality = 0;
      if (array.limit() != cardinality) {
         throw new RuntimeException("Mismatch between buffer and cardinality");
      } else {
         this.cardinality = cardinality;
         this.content = array;
      }
   }

   public MappeableContainer add(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         int indexstart = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = BufferUtil.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = end - begin;
         int newcardinality = indexstart + (this.cardinality - indexend) + rangelength;
         if (newcardinality > 4096) {
            MappeableBitmapContainer a = this.toBitmapContainer();
            return a.iadd(begin, end);
         } else {
            MappeableArrayContainer answer = new MappeableArrayContainer(newcardinality, this.content);
            if (!BufferUtil.isBackedBySimpleArray(answer.content)) {
               throw new RuntimeException("Should not happen. Internal bug.");
            } else {
               BufferUtil.arraycopy(this.content, indexend, answer.content, indexstart + rangelength, this.cardinality - indexend);
               char[] answerarray = answer.content.array();

               for(int k = 0; k < rangelength; ++k) {
                  answerarray[k + indexstart] = (char)(begin + k);
               }

               answer.cardinality = newcardinality;
               return answer;
            }
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableContainer add(char x) {
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] sarray = this.content.array();
         if (this.cardinality == 0 || this.cardinality > 0 && x > sarray[this.cardinality - 1]) {
            if (this.cardinality >= 4096) {
               return this.toBitmapContainer().add(x);
            }

            if (this.cardinality >= sarray.length) {
               this.increaseCapacity();
               sarray = this.content.array();
            }

            sarray[this.cardinality++] = x;
         } else {
            int loc = Util.unsignedBinarySearch(sarray, 0, this.cardinality, x);
            if (loc < 0) {
               if (this.cardinality >= 4096) {
                  return this.toBitmapContainer().add(x);
               }

               if (this.cardinality >= sarray.length) {
                  this.increaseCapacity();
                  sarray = this.content.array();
               }

               System.arraycopy(sarray, -loc - 1, sarray, -loc, this.cardinality + loc + 1);
               sarray[-loc - 1] = x;
               ++this.cardinality;
            }
         }
      } else {
         if (this.cardinality == 0 || this.cardinality > 0 && x > this.content.get(this.cardinality - 1)) {
            if (this.cardinality >= 4096) {
               return this.toBitmapContainer().add(x);
            }

            if (this.cardinality >= this.content.limit()) {
               this.increaseCapacity();
            }

            this.content.put(this.cardinality++, x);
         }

         int loc = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, x);
         if (loc < 0) {
            if (this.cardinality >= 4096) {
               MappeableBitmapContainer a = this.toBitmapContainer();
               a.add(x);
               return a;
            }

            if (this.cardinality >= this.content.limit()) {
               this.increaseCapacity();
            }

            for(int k = this.cardinality; k > -loc - 1; --k) {
               this.content.put(k, this.content.get(k - 1));
            }

            this.content.put(-loc - 1, x);
            ++this.cardinality;
         }
      }

      return this;
   }

   public boolean isEmpty() {
      return this.cardinality == 0;
   }

   public boolean isFull() {
      return false;
   }

   public void orInto(long[] bits) {
      for(int i = 0; i < this.getCardinality(); ++i) {
         char value = this.content.get(i);
         bits[value >>> 6] |= 1L << value;
      }

   }

   public void andInto(long[] bits) {
      int prev = 0;

      for(int i = 0; i < this.getCardinality(); ++i) {
         int value = this.content.get(i);
         Util.resetBitmapRange(bits, prev, value);
         prev = value + 1;
      }

      Util.resetBitmapRange(bits, prev, 65536);
   }

   public void removeFrom(long[] bits) {
      for(int i = 0; i < this.getCardinality(); ++i) {
         int value = this.content.get(i);
         bits[value >>> 6] &= ~(1L << value);
      }

   }

   private int advance(CharIterator it) {
      return it.hasNext() ? it.next() : -1;
   }

   public MappeableArrayContainer and(MappeableArrayContainer value2) {
      int desiredCapacity = Math.min(this.getCardinality(), value2.getCardinality());
      MappeableArrayContainer answer = new MappeableArrayContainer(desiredCapacity);
      if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
         answer.cardinality = Util.unsignedIntersect2by2(this.content.array(), this.getCardinality(), value2.content.array(), value2.getCardinality(), answer.content.array());
      } else {
         answer.cardinality = BufferUtil.unsignedIntersect2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content.array());
      }

      return answer;
   }

   public MappeableContainer and(MappeableBitmapContainer x) {
      return x.and(this);
   }

   public MappeableContainer and(MappeableRunContainer value2) {
      return value2.and(this);
   }

   public MappeableArrayContainer andNot(MappeableArrayContainer value2) {
      int desiredCapacity = this.getCardinality();
      MappeableArrayContainer answer = new MappeableArrayContainer(desiredCapacity);
      if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
         answer.cardinality = Util.unsignedDifference(this.content.array(), this.getCardinality(), value2.content.array(), value2.getCardinality(), answer.content.array());
      } else {
         answer.cardinality = BufferUtil.unsignedDifference(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content.array());
      }

      return answer;
   }

   public MappeableArrayContainer andNot(MappeableBitmapContainer value2) {
      MappeableArrayContainer answer = new MappeableArrayContainer(this.content.limit());
      int pos = 0;
      char[] sarray = answer.content.array();
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] c = this.content.array();

         for(int k = 0; k < this.cardinality; ++k) {
            char v = c[k];
            sarray[pos] = v;
            pos += 1 - (int)value2.bitValue(v);
         }
      } else {
         for(int k = 0; k < this.cardinality; ++k) {
            char v = this.content.get(k);
            sarray[pos] = v;
            pos += 1 - (int)value2.bitValue(v);
         }
      }

      answer.cardinality = pos;
      return answer;
   }

   public MappeableContainer andNot(MappeableRunContainer x) {
      if (x.numberOfRuns() == 0) {
         return this.clone();
      } else if (x.isFull()) {
         return empty();
      } else {
         int write = 0;
         int read = 0;
         MappeableArrayContainer answer = new MappeableArrayContainer(this.cardinality);

         for(int i = 0; i < x.numberOfRuns() && read < this.cardinality; ++i) {
            int runStart = x.getValue(i);
            int runEnd = runStart + x.getLength(i);
            if (this.content.get(read) <= runEnd) {
               int firstInRun = BufferUtil.iterateUntil(this.content, read, this.cardinality, runStart);
               int toWrite = firstInRun - read;
               BufferUtil.arraycopy(this.content, read, answer.content, write, toWrite);
               write += toWrite;
               read = BufferUtil.iterateUntil(this.content, firstInRun, this.cardinality, runEnd + 1);
            }
         }

         BufferUtil.arraycopy(this.content, read, answer.content, write, this.cardinality - read);
         write += this.cardinality - read;
         answer.cardinality = write;
         return answer;
      }
   }

   public void clear() {
      this.cardinality = 0;
   }

   public MappeableArrayContainer clone() {
      return new MappeableArrayContainer(this.cardinality, this.content);
   }

   public boolean contains(char x) {
      return BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, x) >= 0;
   }

   public static boolean contains(ByteBuffer buf, int position, char x, int cardinality) {
      return BufferUtil.unsignedBinarySearch(buf, position, 0, cardinality, x) >= 0;
   }

   private void emit(char val) {
      if (this.cardinality == this.content.limit()) {
         this.increaseCapacity(true);
      }

      this.content.put(this.cardinality++, val);
   }

   public boolean equals(Object o) {
      if (o instanceof MappeableArrayContainer) {
         MappeableArrayContainer srb = (MappeableArrayContainer)o;
         if (srb.cardinality != this.cardinality) {
            return false;
         } else {
            if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(srb.content)) {
               char[] t = this.content.array();
               char[] sr = srb.content.array();

               for(int i = 0; i < this.cardinality; ++i) {
                  if (t[i] != sr[i]) {
                     return false;
                  }
               }
            } else {
               for(int i = 0; i < this.cardinality; ++i) {
                  if (this.content.get(i) != srb.content.get(i)) {
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
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] c = this.content.array();

         for(int k = 0; k < this.cardinality; ++k) {
            x[k + i] = c[k] | mask;
         }
      } else {
         for(int k = 0; k < this.cardinality; ++k) {
            x[k + i] = this.content.get(k) | mask;
         }
      }

   }

   public MappeableContainer flip(char x) {
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] sarray = this.content.array();
         int loc = Util.unsignedBinarySearch(sarray, 0, this.cardinality, x);
         if (loc < 0) {
            if (this.cardinality >= 4096) {
               MappeableBitmapContainer a = this.toBitmapContainer();
               a.add(x);
               return a;
            }

            if (this.cardinality >= sarray.length) {
               this.increaseCapacity();
               sarray = this.content.array();
            }

            System.arraycopy(sarray, -loc - 1, sarray, -loc, this.cardinality + loc + 1);
            sarray[-loc - 1] = x;
            ++this.cardinality;
         } else {
            System.arraycopy(sarray, loc + 1, sarray, loc, this.cardinality - loc - 1);
            --this.cardinality;
         }

         return this;
      } else {
         int loc = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, x);
         if (loc < 0) {
            if (this.cardinality >= 4096) {
               MappeableBitmapContainer a = this.toBitmapContainer();
               a.add(x);
               return a;
            }

            if (this.cardinality >= this.content.limit()) {
               this.increaseCapacity();
            }

            for(int k = this.cardinality; k > -loc - 1; --k) {
               this.content.put(k, this.content.get(k - 1));
            }

            this.content.put(-loc - 1, x);
            ++this.cardinality;
         } else {
            for(int k = loc + 1; k < this.cardinality; --k) {
               this.content.put(k - 1, this.content.get(k));
            }

            --this.cardinality;
         }

         return this;
      }
   }

   protected int getArraySizeInBytes() {
      return getArraySizeInBytes(this.cardinality);
   }

   public int getCardinality() {
      return this.cardinality;
   }

   public CharIterator getReverseCharIterator() {
      return (CharIterator)(this.isArrayBacked() ? new RawReverseArrayContainerCharIterator(this) : new ReverseMappeableArrayContainerCharIterator(this));
   }

   public PeekableCharIterator getCharIterator() {
      return (PeekableCharIterator)(this.isArrayBacked() ? new RawArrayContainerCharIterator(this) : new MappeableArrayContainerCharIterator(this));
   }

   public ContainerBatchIterator getBatchIterator() {
      return new ArrayBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.cardinality * 2;
   }

   public int hashCode() {
      int hash = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         hash += 31 * hash + this.content.get(k);
      }

      return hash;
   }

   public MappeableContainer iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int indexstart = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = BufferUtil.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = end - begin;
         int newcardinality = indexstart + (this.cardinality - indexend) + rangelength;
         if (newcardinality > 4096) {
            MappeableBitmapContainer a = this.toBitmapContainer();
            return a.iadd(begin, end);
         } else {
            if (newcardinality >= this.content.limit()) {
               CharBuffer destination = CharBuffer.allocate(newcardinality);
               BufferUtil.arraycopy(this.content, 0, destination, 0, indexstart);
               if (BufferUtil.isBackedBySimpleArray(this.content)) {
                  char[] destinationarray = destination.array();

                  for(int k = 0; k < rangelength; ++k) {
                     destinationarray[k + indexstart] = (char)(begin + k);
                  }
               } else {
                  for(int k = 0; k < rangelength; ++k) {
                     destination.put(k + indexstart, (char)(begin + k));
                  }
               }

               BufferUtil.arraycopy(this.content, indexend, destination, indexstart + rangelength, this.cardinality - indexend);
               this.content = destination;
            } else {
               BufferUtil.arraycopy(this.content, indexend, this.content, indexstart + rangelength, this.cardinality - indexend);
               if (BufferUtil.isBackedBySimpleArray(this.content)) {
                  char[] contentarray = this.content.array();

                  for(int k = 0; k < rangelength; ++k) {
                     contentarray[k + indexstart] = (char)(begin + k);
                  }
               } else {
                  for(int k = 0; k < rangelength; ++k) {
                     this.content.put(k + indexstart, (char)(begin + k));
                  }
               }
            }

            this.cardinality = newcardinality;
            return this;
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public MappeableArrayContainer iand(MappeableArrayContainer value2) {
      if (!BufferUtil.isBackedBySimpleArray(this.content)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         this.cardinality = BufferUtil.unsignedIntersect2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), this.content.array());
         return this;
      }
   }

   public MappeableContainer iand(MappeableBitmapContainer value2) {
      int pos = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         char v = this.content.get(k);
         this.content.put(pos, v);
         pos += (int)value2.bitValue(v);
      }

      this.cardinality = pos;
      return this;
   }

   public MappeableContainer iand(MappeableRunContainer value2) {
      PeekableCharIterator it = value2.getCharIterator();
      int removed = 0;

      for(int i = 0; i < this.cardinality; ++i) {
         it.advanceIfNeeded(this.content.get(i));
         if (it.peekNext() == this.content.get(i)) {
            this.content.put(i - removed, this.content.get(i));
         } else {
            ++removed;
         }
      }

      this.cardinality -= removed;
      return this;
   }

   public MappeableArrayContainer iandNot(MappeableArrayContainer value2) {
      if (!BufferUtil.isBackedBySimpleArray(this.content)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         if (BufferUtil.isBackedBySimpleArray(value2.content)) {
            this.cardinality = Util.unsignedDifference(this.content.array(), this.getCardinality(), value2.content.array(), value2.getCardinality(), this.content.array());
         } else {
            this.cardinality = BufferUtil.unsignedDifference(this.content, this.getCardinality(), value2.content, value2.getCardinality(), this.content.array());
         }

         return this;
      }
   }

   public MappeableArrayContainer iandNot(MappeableBitmapContainer value2) {
      if (!BufferUtil.isBackedBySimpleArray(this.content)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         char[] c = this.content.array();
         int pos = 0;

         for(int k = 0; k < this.cardinality; ++k) {
            char v = c[k];
            c[pos] = v;
            pos += 1 - (int)value2.bitValue(v);
         }

         this.cardinality = pos;
         return this;
      }
   }

   public MappeableContainer iandNot(MappeableRunContainer value2) {
      PeekableCharIterator it = value2.getCharIterator();
      int removed = 0;

      for(int i = 0; i < this.cardinality; ++i) {
         it.advanceIfNeeded(this.content.get(i));
         if (it.peekNext() != this.content.get(i)) {
            this.content.put(i - removed, this.content.get(i));
         } else {
            ++removed;
         }
      }

      this.cardinality -= removed;
      return this;
   }

   private void increaseCapacity() {
      this.increaseCapacity(false);
   }

   private void increaseCapacity(boolean allowIllegalSize) {
      int newCapacity = this.calculateCapacity();
      if (newCapacity > 4096 && !allowIllegalSize) {
         newCapacity = 4096;
      }

      if (newCapacity > 3840 && !allowIllegalSize) {
         newCapacity = 4096;
      }

      CharBuffer newContent = CharBuffer.allocate(newCapacity);
      this.content.rewind();
      newContent.put(this.content);
      this.content = newContent;
   }

   private int calculateCapacity() {
      int len = this.content.limit();
      int newCapacity = len == 0 ? 4 : (len < 64 ? len * 2 : (len < 1067 ? len * 3 / 2 : len * 5 / 4));
      return newCapacity;
   }

   private int calculateCapacity(int min) {
      int newCapacity = this.calculateCapacity();
      if (newCapacity < min) {
         newCapacity = min;
      }

      if (newCapacity > 4096) {
         newCapacity = 4096;
      }

      if (newCapacity > 3840) {
         newCapacity = 4096;
      }

      return newCapacity;
   }

   public MappeableContainer inot(int firstOfRange, int lastOfRange) {
      int startIndex = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)firstOfRange);
      if (startIndex < 0) {
         startIndex = -startIndex - 1;
      }

      int lastIndex = BufferUtil.unsignedBinarySearch(this.content, startIndex, this.cardinality, (char)(lastOfRange - 1));
      if (lastIndex < 0) {
         lastIndex = -lastIndex - 1 - 1;
      }

      int currentValuesInRange = lastIndex - startIndex + 1;
      int spanToBeFlipped = lastOfRange - firstOfRange;
      int newValuesInRange = spanToBeFlipped - currentValuesInRange;
      CharBuffer buffer = CharBuffer.allocate(newValuesInRange);
      int cardinalityChange = newValuesInRange - currentValuesInRange;
      int newCardinality = this.cardinality + cardinalityChange;
      if (cardinalityChange > 0) {
         if (newCardinality > this.content.limit()) {
            if (newCardinality > 4096) {
               return this.toBitmapContainer().inot(firstOfRange, lastOfRange);
            }

            CharBuffer co = CharBuffer.allocate(newCardinality);
            this.content.rewind();
            co.put(this.content);
            this.content = co;
         }

         for(int pos = this.cardinality - 1; pos > lastIndex; --pos) {
            this.content.put(pos + cardinalityChange, this.content.get(pos));
         }

         this.negateRange(buffer, startIndex, lastIndex, firstOfRange, lastOfRange);
      } else {
         this.negateRange(buffer, startIndex, lastIndex, firstOfRange, lastOfRange);
         if (cardinalityChange < 0) {
            for(int i = startIndex + newValuesInRange; i < newCardinality; ++i) {
               this.content.put(i, this.content.get(i - cardinalityChange));
            }
         }
      }

      this.cardinality = newCardinality;
      return this;
   }

   public boolean intersects(MappeableArrayContainer value2) {
      return BufferUtil.unsignedIntersects(this.content, this.getCardinality(), value2.content, value2.getCardinality());
   }

   public boolean intersects(MappeableBitmapContainer x) {
      return x.intersects(this);
   }

   public boolean intersects(MappeableRunContainer x) {
      return x.intersects(this);
   }

   public MappeableContainer ior(MappeableArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().lazyIOR(value2).repairAfterLazy();
      } else {
         if (totalCardinality >= this.content.limit()) {
            int newCapacity = this.calculateCapacity(totalCardinality);
            CharBuffer destination = CharBuffer.allocate(newCapacity);
            if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
               this.cardinality = Util.unsignedUnion2by2(this.content.array(), 0, this.cardinality, value2.content.array(), 0, value2.cardinality, destination.array());
            } else {
               this.cardinality = BufferUtil.unsignedUnion2by2(this.content, 0, this.cardinality, value2.content, 0, value2.cardinality, destination.array());
            }

            this.content = destination;
         } else {
            BufferUtil.arraycopy(this.content, 0, this.content, value2.cardinality, this.cardinality);
            if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
               this.cardinality = Util.unsignedUnion2by2(this.content.array(), value2.cardinality, this.cardinality, value2.content.array(), 0, value2.cardinality, this.content.array());
            } else {
               this.cardinality = BufferUtil.unsignedUnion2by2(this.content, value2.cardinality, this.cardinality, value2.content, 0, value2.cardinality, this.content.array());
            }
         }

         return this;
      }
   }

   public MappeableContainer ior(MappeableBitmapContainer x) {
      return x.or(this);
   }

   public MappeableContainer ior(MappeableRunContainer value2) {
      return value2.or(this);
   }

   public MappeableContainer iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int indexstart = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = BufferUtil.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = indexend - indexstart;
         BufferUtil.arraycopy(this.content, indexstart + rangelength, this.content, indexstart, this.cardinality - indexstart - rangelength);
         this.cardinality -= rangelength;
         return this;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   protected boolean isArrayBacked() {
      return BufferUtil.isBackedBySimpleArray(this.content);
   }

   public Iterator iterator() {
      return new Iterator() {
         char pos = 0;

         public boolean hasNext() {
            return this.pos < MappeableArrayContainer.this.cardinality;
         }

         public Character next() {
            CharBuffer var10000 = MappeableArrayContainer.this.content;
            char var10003 = this.pos;
            this.pos = (char)(var10003 + 1);
            return var10000.get(var10003);
         }

         public void remove() {
            MappeableArrayContainer.this.removeAtIndex(this.pos - 1);
            --this.pos;
         }
      };
   }

   public MappeableContainer ixor(MappeableArrayContainer value2) {
      return this.xor(value2);
   }

   public MappeableContainer ixor(MappeableBitmapContainer x) {
      return x.xor(this);
   }

   public MappeableContainer ixor(MappeableRunContainer value2) {
      return value2.xor(this);
   }

   public MappeableContainer limit(int maxcardinality) {
      return maxcardinality < this.getCardinality() ? new MappeableArrayContainer(maxcardinality, this.content) : this.clone();
   }

   void loadData(MappeableBitmapContainer bitmapContainer) {
      this.cardinality = bitmapContainer.cardinality;
      if (!BufferUtil.isBackedBySimpleArray(this.content)) {
         throw new RuntimeException("Should not happen. Internal bug.");
      } else {
         Util.fillArray(bitmapContainer.bitmap.array(), this.content.array());
      }
   }

   private void negateRange(CharBuffer buffer, int startIndex, int lastIndex, int startRange, int lastRange) {
      int outPos = 0;
      int inPos = startIndex;

      int valInRange;
      for(valInRange = startRange; valInRange < lastRange && inPos <= lastIndex; ++valInRange) {
         if ((char)valInRange != this.content.get(inPos)) {
            buffer.put(outPos++, (char)valInRange);
         } else {
            ++inPos;
         }
      }

      while(valInRange < lastRange) {
         buffer.put(outPos++, (char)valInRange);
         ++valInRange;
      }

      if (outPos != buffer.limit()) {
         throw new RuntimeException("negateRange: outPos " + outPos + " whereas buffer.length=" + buffer.limit());
      } else {
         int i = startIndex;
         int len = buffer.limit();

         for(int k = 0; k < len; ++k) {
            char item = buffer.get(k);
            this.content.put(i++, item);
         }

      }
   }

   public MappeableContainer not(int firstOfRange, int lastOfRange) {
      if (firstOfRange >= lastOfRange) {
         return this.clone();
      } else {
         int startIndex = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)firstOfRange);
         if (startIndex < 0) {
            startIndex = -startIndex - 1;
         }

         int lastIndex = BufferUtil.unsignedBinarySearch(this.content, startIndex, this.cardinality, (char)(lastOfRange - 1));
         if (lastIndex < 0) {
            lastIndex = -lastIndex - 2;
         }

         int currentValuesInRange = lastIndex - startIndex + 1;
         int spanToBeFlipped = lastOfRange - firstOfRange;
         int newValuesInRange = spanToBeFlipped - currentValuesInRange;
         int cardinalityChange = newValuesInRange - currentValuesInRange;
         int newCardinality = this.cardinality + cardinalityChange;
         if (newCardinality > 4096) {
            return this.toBitmapContainer().not(firstOfRange, lastOfRange);
         } else {
            MappeableArrayContainer answer = new MappeableArrayContainer(newCardinality);
            if (!BufferUtil.isBackedBySimpleArray(answer.content)) {
               throw new RuntimeException("Should not happen. Internal bug.");
            } else {
               char[] sarray = answer.content.array();

               for(int i = 0; i < startIndex; ++i) {
                  sarray[i] = this.content.get(i);
               }

               int outPos = startIndex;
               int inPos = startIndex;

               int valInRange;
               for(valInRange = firstOfRange; valInRange < lastOfRange && inPos <= lastIndex; ++valInRange) {
                  if ((char)valInRange != this.content.get(inPos)) {
                     sarray[outPos++] = (char)valInRange;
                  } else {
                     ++inPos;
                  }
               }

               while(valInRange < lastOfRange) {
                  answer.content.put(outPos++, (char)valInRange);
                  ++valInRange;
               }

               for(int i = lastIndex + 1; i < this.cardinality; ++i) {
                  answer.content.put(outPos++, this.content.get(i));
               }

               answer.cardinality = newCardinality;
               return answer;
            }
         }
      }
   }

   int numberOfRuns() {
      if (this.cardinality == 0) {
         return 0;
      } else if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] c = this.content.array();
         int numRuns = 1;
         int oldv = c[0];

         for(int i = 1; i < this.cardinality; ++i) {
            int newv = c[i];
            if (oldv + 1 != newv) {
               ++numRuns;
            }

            oldv = newv;
         }

         return numRuns;
      } else {
         int numRuns = 1;
         int previous = this.content.get(0);

         for(int i = 1; i < this.cardinality; ++i) {
            int val = this.content.get(i);
            if (val != previous + 1) {
               ++numRuns;
            }

            previous = val;
         }

         return numRuns;
      }
   }

   public MappeableContainer or(MappeableArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().lazyIOR(value2).repairAfterLazy();
      } else {
         MappeableArrayContainer answer = new MappeableArrayContainer(totalCardinality);
         if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
            answer.cardinality = Util.unsignedUnion2by2(this.content.array(), 0, this.getCardinality(), value2.content.array(), 0, value2.getCardinality(), answer.content.array());
         } else {
            answer.cardinality = BufferUtil.unsignedUnion2by2(this.content, 0, this.getCardinality(), value2.content, 0, value2.getCardinality(), answer.content.array());
         }

         return answer;
      }
   }

   protected MappeableContainer lazyor(MappeableArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 1024) {
         return this.toBitmapContainer().lazyIOR(value2);
      } else {
         MappeableArrayContainer answer = new MappeableArrayContainer(totalCardinality);
         if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
            answer.cardinality = Util.unsignedUnion2by2(this.content.array(), 0, this.getCardinality(), value2.content.array(), 0, value2.getCardinality(), answer.content.array());
         } else {
            answer.cardinality = BufferUtil.unsignedUnion2by2(this.content, 0, this.getCardinality(), value2.content, 0, value2.getCardinality(), answer.content.array());
         }

         return answer;
      }
   }

   public MappeableContainer or(MappeableBitmapContainer x) {
      return x.or(this);
   }

   public MappeableContainer or(MappeableRunContainer value2) {
      return value2.or(this);
   }

   protected MappeableContainer or(CharIterator it) {
      return this.or(it, false);
   }

   private MappeableContainer or(CharIterator it, boolean exclusive) {
      MappeableArrayContainer ac = new MappeableArrayContainer();
      int myItPos = 0;
      ac.cardinality = 0;
      int myHead = myItPos == this.cardinality ? -1 : this.content.get(myItPos++);
      int hisHead = this.advance(it);

      while(myHead != -1 && hisHead != -1) {
         if (myHead < hisHead) {
            ac.emit((char)myHead);
            myHead = myItPos == this.cardinality ? -1 : this.content.get(myItPos++);
         } else if (myHead > hisHead) {
            ac.emit((char)hisHead);
            hisHead = this.advance(it);
         } else {
            if (!exclusive) {
               ac.emit((char)hisHead);
            }

            hisHead = this.advance(it);
            myHead = myItPos == this.cardinality ? -1 : this.content.get(myItPos++);
         }
      }

      while(myHead != -1) {
         ac.emit((char)myHead);
         myHead = myItPos == this.cardinality ? -1 : this.content.get(myItPos++);
      }

      while(hisHead != -1) {
         ac.emit((char)hisHead);
         hisHead = this.advance(it);
      }

      return (MappeableContainer)(ac.cardinality > 4096 ? ac.toBitmapContainer() : ac);
   }

   public int rank(char lowbits) {
      int answer = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, lowbits);
      return answer >= 0 ? answer + 1 : -answer - 1;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.cardinality = '\uffff' & Character.reverseBytes(in.readChar());
      if (this.content.limit() < this.cardinality) {
         this.content = CharBuffer.allocate(this.cardinality);
      }

      for(int k = 0; k < this.cardinality; ++k) {
         this.content.put(k, Character.reverseBytes(in.readChar()));
      }

   }

   public MappeableContainer remove(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         int indexstart = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = BufferUtil.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = indexend - indexstart;
         MappeableArrayContainer answer = this.clone();
         BufferUtil.arraycopy(this.content, indexstart + rangelength, answer.content, indexstart, this.cardinality - indexstart - rangelength);
         answer.cardinality = this.cardinality - rangelength;
         return answer;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   void removeAtIndex(int loc) {
      System.arraycopy(this.content.array(), loc + 1, this.content.array(), loc, this.cardinality - loc - 1);
      --this.cardinality;
   }

   public MappeableContainer remove(char x) {
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         int loc = Util.unsignedBinarySearch(this.content.array(), 0, this.cardinality, x);
         if (loc >= 0) {
            this.removeAtIndex(loc);
         }

         return this;
      } else {
         int loc = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, x);
         if (loc >= 0) {
            for(int k = loc + 1; k < this.cardinality; --k) {
               this.content.put(k - 1, this.content.get(k));
            }

            --this.cardinality;
         }

         return this;
      }
   }

   public MappeableContainer repairAfterLazy() {
      return this;
   }

   public MappeableContainer runOptimize() {
      int numRuns = this.numberOfRuns();
      int sizeAsRunContainer = MappeableRunContainer.getArraySizeInBytes(numRuns);
      return (MappeableContainer)(this.getArraySizeInBytes() > sizeAsRunContainer ? new MappeableRunContainer(this, numRuns) : this);
   }

   public char select(int j) {
      return this.content.get(j);
   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(this.cardinality);
   }

   public MappeableBitmapContainer toBitmapContainer() {
      MappeableBitmapContainer bc = new MappeableBitmapContainer();
      bc.loadData(this);
      return bc;
   }

   public int first() {
      this.assertNonEmpty(this.cardinality == 0);
      return this.select(0);
   }

   public int last() {
      this.assertNonEmpty(this.cardinality == 0);
      return this.select(this.cardinality - 1);
   }

   public int nextValue(char fromValue) {
      int index = BufferUtil.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index == this.cardinality) {
         return fromValue == this.content.get(this.cardinality - 1) ? fromValue : -1;
      } else {
         return this.content.get(index);
      }
   }

   public int previousValue(char fromValue) {
      int index = BufferUtil.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index != this.cardinality && this.content.get(index) == fromValue) {
         return this.content.get(index);
      } else {
         return index == 0 ? -1 : this.content.get(index - 1);
      }
   }

   public int nextAbsentValue(char fromValue) {
      int index = BufferUtil.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index >= this.cardinality) {
         return fromValue;
      } else if (index == this.cardinality - 1) {
         return fromValue == this.content.get(this.cardinality - 1) ? fromValue + 1 : fromValue;
      } else if (this.content.get(index) != fromValue) {
         return fromValue;
      } else if (this.content.get(index + 1) > fromValue + 1) {
         return fromValue + 1;
      } else {
         int low = index;
         int high = this.cardinality;

         while(low + 1 < high) {
            int mid = high + low >>> 1;
            if (mid - index < this.content.get(mid) - fromValue) {
               high = mid;
            } else {
               low = mid;
            }
         }

         if (low == this.cardinality - 1) {
            return this.content.get(this.cardinality - 1) + 1;
         } else {
            assert this.content.get(low) + 1 < this.content.get(high);

            assert this.content.get(low) == fromValue + (low - index);

            return this.content.get(low) + 1;
         }
      }
   }

   public int previousAbsentValue(char fromValue) {
      int index = BufferUtil.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index >= this.cardinality) {
         return fromValue;
      } else if (index == 0) {
         return fromValue == this.content.get(0) ? fromValue - 1 : fromValue;
      } else if (this.content.get(index) != fromValue) {
         return fromValue;
      } else if (this.content.get(index - 1) < fromValue - 1) {
         return fromValue - 1;
      } else {
         int low = -1;
         int high = index;

         while(low + 1 < high) {
            int mid = high + low >>> 1;
            if (index - mid < fromValue - this.content.get(mid)) {
               low = mid;
            } else {
               high = mid;
            }
         }

         if (high == 0) {
            return this.content.get(0) - 1;
         } else {
            assert this.content.get(low) + 1 < this.content.get(high);

            assert this.content.get(high) == fromValue - (index - high);

            return this.content.get(high) - 1;
         }
      }
   }

   public Container toContainer() {
      return new ArrayContainer(this);
   }

   public char[] toShortArray() {
      char[] answer = new char[this.cardinality];
      this.content.rewind();
      this.content.get(answer);
      return answer;
   }

   public String toString() {
      if (this.cardinality == 0) {
         return "{}";
      } else {
         StringBuilder sb = new StringBuilder("{}".length() + "-123456789,".length() * this.cardinality);
         sb.append('{');

         for(int i = 0; i < this.cardinality - 1; ++i) {
            sb.append(this.content.get(i));
            sb.append(',');
         }

         sb.append(this.content.get(this.cardinality - 1));
         sb.append('}');
         return sb.toString();
      }
   }

   public void trim() {
      if (this.content.limit() != this.cardinality) {
         if (BufferUtil.isBackedBySimpleArray(this.content)) {
            this.content = CharBuffer.wrap(Arrays.copyOf(this.content.array(), this.cardinality));
         } else {
            CharBuffer co = CharBuffer.allocate(this.cardinality);
            char[] x = co.array();

            for(int k = 0; k < this.cardinality; ++k) {
               x[k] = this.content.get(k);
            }

            this.content = co;
         }

      }
   }

   protected void writeArray(DataOutput out) throws IOException {
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] a = this.content.array();

         for(int k = 0; k < this.cardinality; ++k) {
            out.writeShort(Character.reverseBytes(a[k]));
         }
      } else {
         for(int k = 0; k < this.cardinality; ++k) {
            out.writeShort(Character.reverseBytes(this.content.get(k)));
         }
      }

   }

   protected void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      CharBuffer target = buffer.asCharBuffer();
      CharBuffer source = this.content.duplicate();
      source.position(0);
      source.limit(this.cardinality);
      target.put(source);
      int bytesWritten = 2 * this.cardinality;
      buffer.position(buffer.position() + bytesWritten);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.write(this.cardinality & 255);
      out.write(this.cardinality >>> 8 & 255);
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] a = this.content.array();

         for(int k = 0; k < this.cardinality; ++k) {
            out.writeShort(Character.reverseBytes(a[k]));
         }
      } else {
         for(int k = 0; k < this.cardinality; ++k) {
            out.writeShort(Character.reverseBytes(this.content.get(k)));
         }
      }

   }

   public MappeableContainer xor(MappeableArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().ixor(value2);
      } else {
         MappeableArrayContainer answer = new MappeableArrayContainer(totalCardinality);
         if (BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
            answer.cardinality = Util.unsignedExclusiveUnion2by2(this.content.array(), this.getCardinality(), value2.content.array(), value2.getCardinality(), answer.content.array());
         } else {
            answer.cardinality = BufferUtil.unsignedExclusiveUnion2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content.array());
         }

         return answer;
      }
   }

   public MappeableContainer xor(MappeableBitmapContainer x) {
      return x.xor(this);
   }

   public MappeableContainer xor(MappeableRunContainer value2) {
      return value2.xor(this);
   }

   protected MappeableContainer xor(CharIterator it) {
      return this.or(it, true);
   }

   public void forEach(char msb, IntConsumer ic) {
      int high = msb << 16;
      if (BufferUtil.isBackedBySimpleArray(this.content)) {
         char[] c = this.content.array();

         for(int k = 0; k < this.cardinality; ++k) {
            ic.accept(c[k] & '\uffff' | high);
         }
      } else {
         for(int k = 0; k < this.cardinality; ++k) {
            ic.accept(this.content.get(k) & '\uffff' | high);
         }
      }

   }

   public int andCardinality(MappeableArrayContainer value2) {
      return BufferUtil.isBackedBySimpleArray(this.content) && BufferUtil.isBackedBySimpleArray(value2.content) ? Util.unsignedLocalIntersect2by2Cardinality(this.content.array(), this.cardinality, value2.content.array(), value2.getCardinality()) : BufferUtil.unsignedLocalIntersect2by2Cardinality(this.content, this.cardinality, value2.content, value2.getCardinality());
   }

   public int andCardinality(MappeableBitmapContainer x) {
      return x.andCardinality(this);
   }

   public int andCardinality(MappeableRunContainer x) {
      return x.andCardinality(this);
   }

   protected boolean contains(MappeableRunContainer runContainer) {
      if (runContainer.getCardinality() > this.cardinality) {
         return false;
      } else {
         for(int i = 0; i < runContainer.numberOfRuns(); ++i) {
            int start = runContainer.getValue(i);
            int length = runContainer.getLength(i);
            if (!this.contains(start, start + length + 1)) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean contains(MappeableArrayContainer arrayContainer) {
      if (this.cardinality < arrayContainer.cardinality) {
         return false;
      } else {
         int i1 = 0;
         int i2 = 0;

         while(i1 < this.cardinality && i2 < arrayContainer.cardinality) {
            if (this.content.get(i1) == arrayContainer.content.get(i2)) {
               ++i1;
               ++i2;
            } else {
               if (this.content.get(i1) >= arrayContainer.content.get(i2)) {
                  return false;
               }

               ++i1;
            }
         }

         return i2 == arrayContainer.cardinality;
      }
   }

   protected boolean contains(MappeableBitmapContainer bitmapContainer) {
      return false;
   }

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         int pos = BufferUtil.unsignedBinarySearch(this.content, 0, this.cardinality, (char)minimum);
         int index = pos >= 0 ? pos : -pos - 1;
         return index < this.cardinality && this.content.get(index) < supremum;
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public boolean contains(int minimum, int supremum) {
      int maximum = supremum - 1;
      int start = BufferUtil.advanceUntil(this.content, -1, this.cardinality, (char)minimum);
      int end = BufferUtil.advanceUntil(this.content, start - 1, this.cardinality, (char)maximum);
      return start < this.cardinality && end < this.cardinality && end - start == maximum - minimum && this.content.get(start) == (char)minimum && this.content.get(end) == (char)maximum;
   }
}
