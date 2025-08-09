package org.roaringbitmap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.roaringbitmap.buffer.MappeableArrayContainer;
import org.roaringbitmap.buffer.MappeableContainer;

public final class ArrayContainer extends Container implements Cloneable {
   private static final int DEFAULT_INIT_SIZE = 4;
   private static final int ARRAY_LAZY_LOWERBOUND = 1024;
   static final int DEFAULT_MAX_SIZE = 4096;
   private static final long serialVersionUID = 1L;
   protected int cardinality;
   char[] content;

   protected static int serializedSizeInBytes(int cardinality) {
      return cardinality * 2 + 2;
   }

   public ArrayContainer() {
      this(4);
   }

   public static ArrayContainer empty() {
      return new ArrayContainer();
   }

   public ArrayContainer(int capacity) {
      this.cardinality = 0;
      this.content = new char[capacity];
   }

   public ArrayContainer(int firstOfRun, int lastOfRun) {
      this.cardinality = 0;
      int valuesInRange = lastOfRun - firstOfRun;
      this.content = new char[valuesInRange];

      for(int i = 0; i < valuesInRange; ++i) {
         this.content[i] = (char)(firstOfRun + i);
      }

      this.cardinality = valuesInRange;
   }

   public ArrayContainer(int newCard, char[] newContent) {
      this.cardinality = 0;
      this.cardinality = newCard;
      this.content = Arrays.copyOf(newContent, newCard);
   }

   public ArrayContainer(MappeableArrayContainer bc) {
      this.cardinality = 0;
      this.cardinality = bc.getCardinality();
      this.content = bc.toShortArray();
   }

   public ArrayContainer(char[] newContent) {
      this.cardinality = 0;
      this.cardinality = newContent.length;
      this.content = newContent;
   }

   public Container add(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         int indexstart = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = Util.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = end - begin;
         int newcardinality = indexstart + (this.cardinality - indexend) + rangelength;
         if (newcardinality > 4096) {
            BitmapContainer a = this.toBitmapContainer();
            return a.iadd(begin, end);
         } else {
            ArrayContainer answer = new ArrayContainer(newcardinality, this.content);
            System.arraycopy(this.content, indexend, answer.content, indexstart + rangelength, this.cardinality - indexend);

            for(int k = 0; k < rangelength; ++k) {
               answer.content[k + indexstart] = (char)(begin + k);
            }

            answer.cardinality = newcardinality;
            return answer;
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Container add(char x) {
      if (this.cardinality == 0 || this.cardinality > 0 && x > this.content[this.cardinality - 1]) {
         if (this.cardinality >= 4096) {
            return this.toBitmapContainer().add(x);
         }

         if (this.cardinality >= this.content.length) {
            this.increaseCapacity();
         }

         this.content[this.cardinality++] = x;
      } else {
         int loc = Util.unsignedBinarySearch(this.content, 0, this.cardinality, x);
         if (loc < 0) {
            if (this.cardinality >= 4096) {
               return this.toBitmapContainer().add(x);
            }

            if (this.cardinality >= this.content.length) {
               this.increaseCapacity();
            }

            System.arraycopy(this.content, -loc - 1, this.content, -loc, this.cardinality + loc + 1);
            this.content[-loc - 1] = x;
            ++this.cardinality;
         }
      }

      return this;
   }

   private int advance(CharIterator it) {
      return it.hasNext() ? it.next() : -1;
   }

   public ArrayContainer and(ArrayContainer value2) {
      int desiredCapacity = Math.min(this.getCardinality(), value2.getCardinality());
      ArrayContainer answer = new ArrayContainer(desiredCapacity);
      answer.cardinality = Util.unsignedIntersect2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content);
      return answer;
   }

   public Container and(BitmapContainer x) {
      return x.and(this);
   }

   public Container and(RunContainer x) {
      return x.and(this);
   }

   public int andCardinality(ArrayContainer value2) {
      return Util.unsignedLocalIntersect2by2Cardinality(this.content, this.cardinality, value2.content, value2.getCardinality());
   }

   public int andCardinality(BitmapContainer x) {
      return x.andCardinality(this);
   }

   public int andCardinality(RunContainer x) {
      return x.andCardinality(this);
   }

   public ArrayContainer andNot(ArrayContainer value2) {
      int desiredCapacity = this.getCardinality();
      ArrayContainer answer = new ArrayContainer(desiredCapacity);
      answer.cardinality = Util.unsignedDifference(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content);
      return answer;
   }

   public ArrayContainer andNot(BitmapContainer value2) {
      ArrayContainer answer = new ArrayContainer(this.content.length);
      int pos = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         char val = this.content[k];
         answer.content[pos] = val;
         pos += 1 - value2.bitValue(val);
      }

      answer.cardinality = pos;
      return answer;
   }

   public ArrayContainer andNot(RunContainer x) {
      if (x.numberOfRuns() == 0) {
         return this.clone();
      } else if (x.isFull()) {
         return empty();
      } else {
         int write = 0;
         int read = 0;
         ArrayContainer answer = new ArrayContainer(this.cardinality);

         for(int i = 0; i < x.numberOfRuns() && read < this.cardinality; ++i) {
            int runStart = x.getValue(i);
            int runEnd = runStart + x.getLength(i);
            if (this.content[read] <= runEnd) {
               int firstInRun = Util.iterateUntil(this.content, read, this.cardinality, runStart);
               int toWrite = firstInRun - read;
               System.arraycopy(this.content, read, answer.content, write, toWrite);
               write += toWrite;
               read = Util.iterateUntil(this.content, firstInRun, this.cardinality, runEnd + 1);
            }
         }

         System.arraycopy(this.content, read, answer.content, write, this.cardinality - read);
         write += this.cardinality - read;
         answer.cardinality = write;
         return answer;
      }
   }

   public void clear() {
      this.cardinality = 0;
   }

   public ArrayContainer clone() {
      return new ArrayContainer(this.cardinality, this.content);
   }

   public boolean isEmpty() {
      return this.cardinality == 0;
   }

   public boolean isFull() {
      return false;
   }

   public boolean contains(char x) {
      return Util.unsignedBinarySearch(this.content, 0, this.cardinality, x) >= 0;
   }

   public boolean contains(int minimum, int supremum) {
      int maximum = supremum - 1;
      int start = Util.advanceUntil(this.content, -1, this.cardinality, (char)minimum);
      int end = Util.advanceUntil(this.content, start - 1, this.cardinality, (char)maximum);
      return start < this.cardinality && end < this.cardinality && end - start == maximum - minimum && this.content[start] == (char)minimum && this.content[end] == (char)maximum;
   }

   protected boolean contains(RunContainer runContainer) {
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

   protected boolean contains(ArrayContainer arrayContainer) {
      if (this.cardinality < arrayContainer.cardinality) {
         return false;
      } else {
         int i1 = 0;
         int i2 = 0;

         while(i1 < this.cardinality && i2 < arrayContainer.cardinality) {
            if (this.content[i1] == arrayContainer.content[i2]) {
               ++i1;
               ++i2;
            } else {
               if (this.content[i1] >= arrayContainer.content[i2]) {
                  return false;
               }

               ++i1;
            }
         }

         return i2 == arrayContainer.cardinality;
      }
   }

   protected boolean contains(BitmapContainer bitmapContainer) {
      return false;
   }

   public void deserialize(DataInput in) throws IOException {
      this.cardinality = '\uffff' & Character.reverseBytes(in.readChar());
      if (this.content.length < this.cardinality) {
         this.content = new char[this.cardinality];
      }

      for(int k = 0; k < this.cardinality; ++k) {
         this.content[k] = Character.reverseBytes(in.readChar());
      }

   }

   private void emit(char val) {
      if (this.cardinality == this.content.length) {
         this.increaseCapacity(true);
      }

      this.content[this.cardinality++] = val;
   }

   public boolean equals(Object o) {
      if (o instanceof ArrayContainer) {
         ArrayContainer srb = (ArrayContainer)o;
         return ArraysShim.equals(this.content, 0, this.cardinality, srb.content, 0, srb.cardinality);
      } else {
         return o instanceof RunContainer ? o.equals(this) : false;
      }
   }

   public void fillLeastSignificant16bits(int[] x, int i, int mask) {
      for(int k = 0; k < this.cardinality; ++k) {
         x[k + i] = this.content[k] | mask;
      }

   }

   public Container flip(char x) {
      int loc = Util.unsignedBinarySearch(this.content, 0, this.cardinality, x);
      if (loc < 0) {
         if (this.cardinality >= 4096) {
            BitmapContainer a = this.toBitmapContainer();
            a.add(x);
            return a;
         }

         if (this.cardinality >= this.content.length) {
            this.increaseCapacity();
         }

         System.arraycopy(this.content, -loc - 1, this.content, -loc, this.cardinality + loc + 1);
         this.content[-loc - 1] = x;
         ++this.cardinality;
      } else {
         System.arraycopy(this.content, loc + 1, this.content, loc, this.cardinality - loc - 1);
         --this.cardinality;
      }

      return this;
   }

   public int getArraySizeInBytes() {
      return this.cardinality * 2;
   }

   public int getCardinality() {
      return this.cardinality;
   }

   public PeekableCharIterator getReverseCharIterator() {
      return new ReverseArrayContainerCharIterator(this);
   }

   public PeekableCharIterator getCharIterator() {
      return new ArrayContainerCharIterator(this);
   }

   public PeekableCharRankIterator getCharRankIterator() {
      return new ArrayContainerCharIterator(this);
   }

   public ContainerBatchIterator getBatchIterator() {
      return new ArrayBatchIterator(this);
   }

   public int getSizeInBytes() {
      return this.cardinality * 2 + 4;
   }

   public int hashCode() {
      int hash = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         hash += 31 * hash + this.content[k];
      }

      return hash;
   }

   public Container iadd(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int indexstart = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = Util.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = end - begin;
         int newcardinality = indexstart + (this.cardinality - indexend) + rangelength;
         if (newcardinality > 4096) {
            BitmapContainer a = this.toBitmapContainer();
            return a.iadd(begin, end);
         } else {
            if (newcardinality >= this.content.length) {
               char[] destination = new char[this.calculateCapacity(newcardinality)];
               System.arraycopy(this.content, 0, destination, 0, indexstart);

               for(int k = 0; k < rangelength; ++k) {
                  destination[k + indexstart] = (char)(begin + k);
               }

               System.arraycopy(this.content, indexend, destination, indexstart + rangelength, this.cardinality - indexend);
               this.content = destination;
            } else {
               System.arraycopy(this.content, indexend, this.content, indexstart + rangelength, this.cardinality - indexend);

               for(int k = 0; k < rangelength; ++k) {
                  this.content[k + indexstart] = (char)(begin + k);
               }
            }

            this.cardinality = newcardinality;
            return this;
         }
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public ArrayContainer iand(ArrayContainer value2) {
      this.cardinality = Util.unsignedIntersect2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), this.content);
      return this;
   }

   public Container iand(BitmapContainer value2) {
      int pos = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         char v = this.content[k];
         this.content[pos] = v;
         pos += value2.bitValue(v);
      }

      this.cardinality = pos;
      return this;
   }

   public Container iand(RunContainer x) {
      PeekableCharIterator it = x.getCharIterator();
      int removed = 0;

      for(int i = 0; i < this.cardinality; ++i) {
         it.advanceIfNeeded(this.content[i]);
         if (it.peekNext() == this.content[i]) {
            this.content[i - removed] = this.content[i];
         } else {
            ++removed;
         }
      }

      this.cardinality -= removed;
      return this;
   }

   public ArrayContainer iandNot(ArrayContainer value2) {
      this.cardinality = Util.unsignedDifference(this.content, this.getCardinality(), value2.content, value2.getCardinality(), this.content);
      return this;
   }

   public ArrayContainer iandNot(BitmapContainer value2) {
      int pos = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         char v = this.content[k];
         this.content[pos] = v;
         pos += 1 - value2.bitValue(v);
      }

      this.cardinality = pos;
      return this;
   }

   public Container iandNot(RunContainer x) {
      PeekableCharIterator it = x.getCharIterator();
      int removed = 0;

      for(int i = 0; i < this.cardinality; ++i) {
         it.advanceIfNeeded(this.content[i]);
         if (it.peekNext() != this.content[i]) {
            this.content[i - removed] = this.content[i];
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
      int newCapacity = this.computeCapacity(this.content.length);
      if (newCapacity > 4096 && !allowIllegalSize) {
         newCapacity = 4096;
      }

      if (newCapacity > 3840 && !allowIllegalSize) {
         newCapacity = 4096;
      }

      this.content = Arrays.copyOf(this.content, newCapacity);
   }

   private int computeCapacity(int oldCapacity) {
      return oldCapacity == 0 ? 4 : (oldCapacity < 64 ? oldCapacity * 2 : (oldCapacity < 1024 ? oldCapacity * 3 / 2 : oldCapacity * 5 / 4));
   }

   private int calculateCapacity(int min) {
      int newCapacity = this.computeCapacity(this.content.length);
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

   public Container inot(int firstOfRange, int lastOfRange) {
      int startIndex = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)firstOfRange);
      if (startIndex < 0) {
         startIndex = -startIndex - 1;
      }

      int lastIndex = Util.unsignedBinarySearch(this.content, startIndex, this.cardinality, (char)(lastOfRange - 1));
      if (lastIndex < 0) {
         lastIndex = -lastIndex - 1 - 1;
      }

      int currentValuesInRange = lastIndex - startIndex + 1;
      int spanToBeFlipped = lastOfRange - firstOfRange;
      int newValuesInRange = spanToBeFlipped - currentValuesInRange;
      char[] buffer = new char[newValuesInRange];
      int cardinalityChange = newValuesInRange - currentValuesInRange;
      int newCardinality = this.cardinality + cardinalityChange;
      if (cardinalityChange > 0) {
         if (newCardinality > this.content.length) {
            if (newCardinality > 4096) {
               return this.toBitmapContainer().inot(firstOfRange, lastOfRange);
            }

            this.content = Arrays.copyOf(this.content, newCardinality);
         }

         System.arraycopy(this.content, lastIndex + 1, this.content, lastIndex + 1 + cardinalityChange, this.cardinality - 1 - lastIndex);
         this.negateRange(buffer, startIndex, lastIndex, firstOfRange, lastOfRange);
      } else {
         this.negateRange(buffer, startIndex, lastIndex, firstOfRange, lastOfRange);
         if (cardinalityChange < 0) {
            System.arraycopy(this.content, startIndex + newValuesInRange - cardinalityChange, this.content, startIndex + newValuesInRange, newCardinality - (startIndex + newValuesInRange));
         }
      }

      this.cardinality = newCardinality;
      return this;
   }

   public boolean intersects(ArrayContainer value2) {
      return Util.unsignedIntersects(this.content, this.getCardinality(), value2.content, value2.getCardinality());
   }

   public boolean intersects(BitmapContainer x) {
      return x.intersects(this);
   }

   public boolean intersects(RunContainer x) {
      return x.intersects(this);
   }

   public boolean intersects(int minimum, int supremum) {
      if (minimum >= 0 && supremum >= minimum && supremum <= 65536) {
         int pos = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)minimum);
         int index = pos >= 0 ? pos : -pos - 1;
         return index < this.cardinality && this.content[index] < supremum;
      } else {
         throw new RuntimeException("This should never happen (bug).");
      }
   }

   public Container ior(ArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().lazyIOR(value2).repairAfterLazy();
      } else {
         if (totalCardinality >= this.content.length) {
            int newCapacity = this.calculateCapacity(totalCardinality);
            char[] destination = new char[newCapacity];
            this.cardinality = Util.unsignedUnion2by2(this.content, 0, this.cardinality, value2.content, 0, value2.cardinality, destination);
            this.content = destination;
         } else {
            System.arraycopy(this.content, 0, this.content, value2.cardinality, this.cardinality);
            this.cardinality = Util.unsignedUnion2by2(this.content, value2.cardinality, this.cardinality, value2.content, 0, value2.cardinality, this.content);
         }

         return this;
      }
   }

   public Container ior(BitmapContainer x) {
      return x.or(this);
   }

   public Container ior(RunContainer x) {
      return x.or(this);
   }

   public Container iremove(int begin, int end) {
      if (end == begin) {
         return this;
      } else if (begin <= end && end <= 65536) {
         int indexstart = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = Util.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = indexend - indexstart;
         System.arraycopy(this.content, indexstart + rangelength, this.content, indexstart, this.cardinality - indexstart - rangelength);
         this.cardinality -= rangelength;
         return this;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   public Iterator iterator() {
      return new Iterator() {
         short pos = 0;

         public boolean hasNext() {
            return this.pos < ArrayContainer.this.cardinality;
         }

         public Character next() {
            char[] var10000 = ArrayContainer.this.content;
            short var10003 = this.pos;
            this.pos = (short)(var10003 + 1);
            return var10000[var10003];
         }

         public void remove() {
            ArrayContainer.this.removeAtIndex(this.pos - 1);
            --this.pos;
         }
      };
   }

   public Container ixor(ArrayContainer value2) {
      return this.xor(value2);
   }

   public Container ixor(BitmapContainer x) {
      return x.xor(this);
   }

   public Container ixor(RunContainer x) {
      return x.xor(this);
   }

   public Container limit(int maxcardinality) {
      return maxcardinality < this.getCardinality() ? new ArrayContainer(maxcardinality, this.content) : this.clone();
   }

   void loadData(BitmapContainer bitmapContainer) {
      this.cardinality = bitmapContainer.cardinality;
      Util.fillArray(bitmapContainer.bitmap, this.content);
   }

   private void negateRange(char[] buffer, int startIndex, int lastIndex, int startRange, int lastRange) {
      int outPos = 0;
      int inPos = startIndex;

      int valInRange;
      for(valInRange = startRange; valInRange < lastRange && inPos <= lastIndex; ++valInRange) {
         if ((char)valInRange != this.content[inPos]) {
            buffer[outPos++] = (char)valInRange;
         } else {
            ++inPos;
         }
      }

      while(valInRange < lastRange) {
         buffer[outPos++] = (char)valInRange;
         ++valInRange;
      }

      if (outPos != buffer.length) {
         throw new RuntimeException("negateRange: outPos " + outPos + " whereas buffer.length=" + buffer.length);
      } else {
         int i = startIndex;

         for(char item : buffer) {
            this.content[i++] = item;
         }

      }
   }

   public Container not(int firstOfRange, int lastOfRange) {
      if (firstOfRange >= lastOfRange) {
         return this.clone();
      } else {
         int startIndex = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)firstOfRange);
         if (startIndex < 0) {
            startIndex = -startIndex - 1;
         }

         int lastIndex = Util.unsignedBinarySearch(this.content, startIndex, this.cardinality, (char)(lastOfRange - 1));
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
            ArrayContainer answer = new ArrayContainer(newCardinality);
            System.arraycopy(this.content, 0, answer.content, 0, startIndex);
            int outPos = startIndex;
            int inPos = startIndex;

            int valInRange;
            for(valInRange = firstOfRange; valInRange < lastOfRange && inPos <= lastIndex; ++valInRange) {
               if ((char)valInRange != this.content[inPos]) {
                  answer.content[outPos++] = (char)valInRange;
               } else {
                  ++inPos;
               }
            }

            while(valInRange < lastOfRange) {
               answer.content[outPos++] = (char)valInRange;
               ++valInRange;
            }

            for(int i = lastIndex + 1; i < this.cardinality; ++i) {
               answer.content[outPos++] = this.content[i];
            }

            answer.cardinality = newCardinality;
            return answer;
         }
      }
   }

   int numberOfRuns() {
      if (this.cardinality == 0) {
         return 0;
      } else {
         int numRuns = 1;
         int oldv = this.content[0];

         for(int i = 1; i < this.cardinality; ++i) {
            int newv = this.content[i];
            if (oldv + 1 != newv) {
               ++numRuns;
            }

            oldv = newv;
         }

         return numRuns;
      }
   }

   public Container or(ArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().lazyIOR(value2).repairAfterLazy();
      } else {
         ArrayContainer answer = new ArrayContainer(totalCardinality);
         answer.cardinality = Util.unsignedUnion2by2(this.content, 0, this.getCardinality(), value2.content, 0, value2.getCardinality(), answer.content);
         return answer;
      }
   }

   public Container or(BitmapContainer x) {
      return x.or(this);
   }

   public Container or(RunContainer x) {
      return x.or(this);
   }

   protected Container or(CharIterator it) {
      return this.or(it, false);
   }

   private Container or(CharIterator it, boolean exclusive) {
      ArrayContainer ac = new ArrayContainer();
      int myItPos = 0;
      ac.cardinality = 0;
      int myHead = myItPos == this.cardinality ? -1 : this.content[myItPos++];
      int hisHead = this.advance(it);

      while(myHead != -1 && hisHead != -1) {
         if (myHead < hisHead) {
            ac.emit((char)myHead);
            myHead = myItPos == this.cardinality ? -1 : this.content[myItPos++];
         } else if (myHead > hisHead) {
            ac.emit((char)hisHead);
            hisHead = this.advance(it);
         } else {
            if (!exclusive) {
               ac.emit((char)hisHead);
            }

            hisHead = this.advance(it);
            myHead = myItPos == this.cardinality ? -1 : this.content[myItPos++];
         }
      }

      while(myHead != -1) {
         ac.emit((char)myHead);
         myHead = myItPos == this.cardinality ? -1 : this.content[myItPos++];
      }

      while(hisHead != -1) {
         ac.emit((char)hisHead);
         hisHead = this.advance(it);
      }

      return (Container)(ac.cardinality > 4096 ? ac.toBitmapContainer() : ac);
   }

   public int rank(char lowbits) {
      int answer = Util.unsignedBinarySearch(this.content, 0, this.cardinality, lowbits);
      return answer >= 0 ? answer + 1 : -answer - 1;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.deserialize(in);
   }

   public Container remove(int begin, int end) {
      if (end == begin) {
         return this.clone();
      } else if (begin <= end && end <= 65536) {
         int indexstart = Util.unsignedBinarySearch(this.content, 0, this.cardinality, (char)begin);
         if (indexstart < 0) {
            indexstart = -indexstart - 1;
         }

         int indexend = Util.unsignedBinarySearch(this.content, indexstart, this.cardinality, (char)(end - 1));
         if (indexend < 0) {
            indexend = -indexend - 1;
         } else {
            ++indexend;
         }

         int rangelength = indexend - indexstart;
         ArrayContainer answer = this.clone();
         System.arraycopy(this.content, indexstart + rangelength, answer.content, indexstart, this.cardinality - indexstart - rangelength);
         answer.cardinality = this.cardinality - rangelength;
         return answer;
      } else {
         throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
      }
   }

   void removeAtIndex(int loc) {
      System.arraycopy(this.content, loc + 1, this.content, loc, this.cardinality - loc - 1);
      --this.cardinality;
   }

   public Container remove(char x) {
      int loc = Util.unsignedBinarySearch(this.content, 0, this.cardinality, x);
      if (loc >= 0) {
         this.removeAtIndex(loc);
      }

      return this;
   }

   public Container repairAfterLazy() {
      return this;
   }

   public Container runOptimize() {
      int numRuns = this.numberOfRuns();
      int sizeAsRunContainer = RunContainer.serializedSizeInBytes(numRuns);
      return (Container)(this.getArraySizeInBytes() > sizeAsRunContainer ? new RunContainer(this, numRuns) : this);
   }

   public char select(int j) {
      return this.content[j];
   }

   public void serialize(DataOutput out) throws IOException {
      out.writeShort(Character.reverseBytes((char)this.cardinality));

      for(int k = 0; k < this.cardinality; ++k) {
         out.writeShort(Character.reverseBytes(this.content[k]));
      }

   }

   public int serializedSizeInBytes() {
      return serializedSizeInBytes(this.cardinality);
   }

   public BitmapContainer toBitmapContainer() {
      BitmapContainer bc = new BitmapContainer();
      bc.loadData(this);
      return bc;
   }

   public void copyBitmapTo(long[] dest, int position) {
      for(int k = 0; k < this.cardinality; ++k) {
         char x = this.content[k];
         dest[position + x / 64] |= 1L << x;
      }

   }

   public int nextValue(char fromValue) {
      int index = Util.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index == this.cardinality) {
         return fromValue == this.content[this.cardinality - 1] ? fromValue : -1;
      } else {
         return this.content[index];
      }
   }

   public int previousValue(char fromValue) {
      int index = Util.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index != this.cardinality && this.content[index] == fromValue) {
         return this.content[index];
      } else {
         return index == 0 ? -1 : this.content[index - 1];
      }
   }

   public int nextAbsentValue(char fromValue) {
      int index = Util.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index >= this.cardinality) {
         return fromValue;
      } else if (index == this.cardinality - 1) {
         return fromValue == this.content[this.cardinality - 1] ? fromValue + 1 : fromValue;
      } else if (this.content[index] != fromValue) {
         return fromValue;
      } else if (this.content[index + 1] > fromValue + 1) {
         return fromValue + 1;
      } else {
         int low = index;
         int high = this.cardinality;

         while(low + 1 < high) {
            int mid = high + low >>> 1;
            if (mid - index < this.content[mid] - fromValue) {
               high = mid;
            } else {
               low = mid;
            }
         }

         if (low == this.cardinality - 1) {
            return this.content[this.cardinality - 1] + 1;
         } else {
            assert this.content[low] + 1 < this.content[high];

            assert this.content[low] == fromValue + (low - index);

            return this.content[low] + 1;
         }
      }
   }

   public int previousAbsentValue(char fromValue) {
      int index = Util.advanceUntil(this.content, -1, this.cardinality, fromValue);
      if (index >= this.cardinality) {
         return fromValue;
      } else if (index == 0) {
         return fromValue == this.content[0] ? fromValue - 1 : fromValue;
      } else if (this.content[index] != fromValue) {
         return fromValue;
      } else if (this.content[index - 1] < fromValue - 1) {
         return fromValue - 1;
      } else {
         int low = -1;
         int high = index;

         while(low + 1 < high) {
            int mid = high + low >>> 1;
            if (index - mid < fromValue - this.content[mid]) {
               low = mid;
            } else {
               high = mid;
            }
         }

         if (high == 0) {
            return this.content[0] - 1;
         } else {
            assert this.content[low] + 1 < this.content[high];

            assert this.content[high] == fromValue - (index - high);

            return this.content[high] - 1;
         }
      }
   }

   public int first() {
      this.assertNonEmpty(this.cardinality == 0);
      return this.content[0];
   }

   public int last() {
      this.assertNonEmpty(this.cardinality == 0);
      return this.content[this.cardinality - 1];
   }

   public MappeableContainer toMappeableContainer() {
      return new MappeableArrayContainer(this);
   }

   public CharBuffer toCharBuffer() {
      CharBuffer cb = CharBuffer.allocate(this.cardinality);
      cb.put(this.content, 0, this.cardinality);
      return cb;
   }

   public String toString() {
      if (this.cardinality == 0) {
         return "{}";
      } else {
         StringBuilder sb = new StringBuilder("{}".length() + "-123456789,".length() * this.cardinality);
         sb.append('{');

         for(int i = 0; i < this.cardinality - 1; ++i) {
            sb.append(this.content[i]);
            sb.append(',');
         }

         sb.append(this.content[this.cardinality - 1]);
         sb.append('}');
         return sb.toString();
      }
   }

   public void trim() {
      if (this.content.length != this.cardinality) {
         this.content = Arrays.copyOf(this.content, this.cardinality);
      }
   }

   public void writeArray(DataOutput out) throws IOException {
      for(int k = 0; k < this.cardinality; ++k) {
         char v = this.content[k];
         out.writeChar(Character.reverseBytes(v));
      }

   }

   public void writeArray(ByteBuffer buffer) {
      assert buffer.order() == ByteOrder.LITTLE_ENDIAN;

      CharBuffer buf = buffer.asCharBuffer();
      buf.put(this.content, 0, this.cardinality);
      int bytesWritten = 2 * this.cardinality;
      buffer.position(buffer.position() + bytesWritten);
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize(out);
   }

   public Container xor(ArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 4096) {
         return this.toBitmapContainer().ixor(value2);
      } else {
         ArrayContainer answer = new ArrayContainer(totalCardinality);
         answer.cardinality = Util.unsignedExclusiveUnion2by2(this.content, this.getCardinality(), value2.content, value2.getCardinality(), answer.content);
         return answer;
      }
   }

   public Container xor(BitmapContainer x) {
      return x.xor(this);
   }

   public Container xor(RunContainer x) {
      return x.xor(this);
   }

   protected Container xor(CharIterator it) {
      return this.or(it, true);
   }

   public void forEach(char msb, IntConsumer ic) {
      int high = msb << 16;

      for(int k = 0; k < this.cardinality; ++k) {
         ic.accept(this.content[k] | high);
      }

   }

   public void forAll(int offset, RelativeRangeConsumer rrc) {
      int next = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         int value = this.content[k];
         if (next < value) {
            rrc.acceptAllAbsent(offset + next, offset + value);
         }

         rrc.acceptPresent(offset + value);
         next = value + 1;
      }

      if (next <= 65535) {
         rrc.acceptAllAbsent(offset + next, offset + '\uffff' + 1);
      }

   }

   public void forAllFrom(char startValue, RelativeRangeConsumer rrc) {
      int startOffset = startValue;
      int loc = Util.unsignedBinarySearch(this.content, 0, this.cardinality, startValue);
      int startIndex;
      if (loc >= 0) {
         startIndex = loc;
      } else {
         startIndex = -loc - 1;
      }

      int next = startValue;

      for(int k = startIndex; k < this.cardinality; ++k) {
         int value = this.content[k];
         if (next < value) {
            rrc.acceptAllAbsent(next - startOffset, value - startOffset);
         }

         rrc.acceptPresent(value - startOffset);
         next = value + 1;
      }

      if (next <= 65535) {
         rrc.acceptAllAbsent(next - startOffset, 65536 - startOffset);
      }

   }

   public void forAllUntil(int offset, char endValue, RelativeRangeConsumer rrc) {
      int next = 0;

      for(int k = 0; k < this.cardinality; ++k) {
         int value = this.content[k];
         if (endValue <= value) {
            if (next < endValue) {
               rrc.acceptAllAbsent(offset + next, offset + endValue);
            }

            return;
         }

         if (next < value) {
            rrc.acceptAllAbsent(offset + next, offset + value);
         }

         rrc.acceptPresent(offset + value);
         next = value + 1;
      }

      if (next < endValue) {
         rrc.acceptAllAbsent(offset + next, offset + endValue);
      }

   }

   public void forAllInRange(char startValue, char endValue, RelativeRangeConsumer rrc) {
      if (endValue <= startValue) {
         throw new IllegalArgumentException("startValue (" + startValue + ") must be less than endValue (" + endValue + ")");
      } else {
         int startOffset = startValue;
         int loc = Util.unsignedBinarySearch(this.content, 0, this.cardinality, startValue);
         int startIndex = loc >= 0 ? loc : -loc - 1;
         int next = startValue;

         for(int k = startIndex; k < this.cardinality; ++k) {
            int value = this.content[k];
            if (endValue <= value) {
               if (next < endValue) {
                  rrc.acceptAllAbsent(next - startOffset, endValue - startOffset);
               }

               return;
            }

            if (next < value) {
               rrc.acceptAllAbsent(next - startOffset, value - startOffset);
            }

            rrc.acceptPresent(value - startOffset);
            next = value + 1;
         }

         if (next < endValue) {
            rrc.acceptAllAbsent(next - startOffset, endValue - startOffset);
         }

      }
   }

   protected Container lazyor(ArrayContainer value2) {
      int totalCardinality = this.getCardinality() + value2.getCardinality();
      if (totalCardinality > 1024) {
         return this.toBitmapContainer().lazyIOR(value2);
      } else {
         ArrayContainer answer = new ArrayContainer(totalCardinality);
         answer.cardinality = Util.unsignedUnion2by2(this.content, 0, this.getCardinality(), value2.content, 0, value2.getCardinality(), answer.content);
         return answer;
      }
   }
}
