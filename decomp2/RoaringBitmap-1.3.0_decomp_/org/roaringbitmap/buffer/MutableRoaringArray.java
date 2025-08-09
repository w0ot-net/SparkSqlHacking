package org.roaringbitmap.buffer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.roaringbitmap.AppendableStorage;
import org.roaringbitmap.InvalidRoaringFormat;
import org.roaringbitmap.Util;

public final class MutableRoaringArray implements Cloneable, Externalizable, PointableRoaringArray, AppendableStorage {
   protected static final int INITIAL_CAPACITY = 4;
   protected static final short SERIAL_COOKIE_NO_RUNCONTAINER = 12346;
   protected static final short SERIAL_COOKIE = 12347;
   protected static final int NO_OFFSET_THRESHOLD = 4;
   private static final long serialVersionUID = 5L;
   char[] keys;
   MappeableContainer[] values;
   int size;

   protected MutableRoaringArray() {
      this(4);
   }

   public MutableRoaringArray(int initialCapacity) {
      this(new char[initialCapacity], new MappeableContainer[initialCapacity], 0);
   }

   MutableRoaringArray(char[] keys, MappeableContainer[] values, int size) {
      this.keys = null;
      this.values = null;
      this.size = 0;
      this.keys = keys;
      this.values = values;
      this.size = size;
   }

   public int advanceUntil(char x, int pos) {
      int lower = pos + 1;
      if (lower < this.size && this.keys[lower] < x) {
         int spansize;
         for(spansize = 1; lower + spansize < this.size && this.keys[lower + spansize] < x; spansize *= 2) {
         }

         int upper = lower + spansize < this.size ? lower + spansize : this.size - 1;
         if (this.keys[upper] == x) {
            return upper;
         } else if (this.keys[upper] < x) {
            return this.size;
         } else {
            lower += spansize / 2;

            while(lower + 1 != upper) {
               int mid = (lower + upper) / 2;
               if (this.keys[mid] == x) {
                  return mid;
               }

               if (this.keys[mid] < x) {
                  lower = mid;
               } else {
                  upper = mid;
               }
            }

            return upper;
         }
      } else {
         return lower;
      }
   }

   public void append(char key, MappeableContainer value) {
      if (this.size > 0 && key < this.keys[this.size - 1]) {
         throw new IllegalArgumentException("append only: " + key + " < " + this.keys[this.size - 1]);
      } else {
         this.extendArray(1);
         this.keys[this.size] = key;
         this.values[this.size] = value;
         ++this.size;
      }
   }

   void append(MutableRoaringArray appendage) {
      assert this.size == 0 || appendage.size == 0 || this.keys[this.size - 1] < appendage.keys[0];

      if (appendage.size != 0 && this.size != 0) {
         this.keys = Arrays.copyOf(this.keys, this.size + appendage.size);
         this.values = (MappeableContainer[])Arrays.copyOf(this.values, this.size + appendage.size);
         System.arraycopy(appendage.keys, 0, this.keys, this.size, appendage.size);
         System.arraycopy(appendage.values, 0, this.values, this.size, appendage.size);
         this.size += appendage.size;
      } else if (this.size == 0 && appendage.size != 0) {
         this.keys = Arrays.copyOf(appendage.keys, appendage.keys.length);
         this.values = (MappeableContainer[])Arrays.copyOf(appendage.values, appendage.values.length);
         this.size = appendage.size;
      }

   }

   protected void appendCopiesAfter(PointableRoaringArray highLowContainer, char beforeStart) {
      int startLocation = highLowContainer.getIndex(beforeStart);
      if (startLocation >= 0) {
         ++startLocation;
      } else {
         startLocation = -startLocation - 1;
      }

      this.extendArray(highLowContainer.size() - startLocation);

      for(int i = startLocation; i < highLowContainer.size(); ++i) {
         this.keys[this.size] = highLowContainer.getKeyAtIndex(i);
         this.values[this.size] = highLowContainer.getContainerAtIndex(i).clone();
         ++this.size;
      }

   }

   protected void appendCopiesUntil(PointableRoaringArray highLowContainer, char stoppingKey) {
      int stopKey = stoppingKey;
      MappeableContainerPointer cp = highLowContainer.getContainerPointer();

      while(cp.hasContainer() && cp.key() < stopKey) {
         this.extendArray(1);
         this.keys[this.size] = cp.key();
         this.values[this.size] = cp.getContainer().clone();
         ++this.size;
         cp.advance();
      }

   }

   protected void appendCopy(PointableRoaringArray highLowContainer, int startingIndex, int end) {
      this.extendArray(end - startingIndex);

      for(int i = startingIndex; i < end; ++i) {
         this.keys[this.size] = highLowContainer.getKeyAtIndex(i);
         this.values[this.size] = highLowContainer.getContainerAtIndex(i).clone();
         ++this.size;
      }

   }

   protected void appendCopy(char key, MappeableContainer value) {
      this.extendArray(1);
      this.keys[this.size] = key;
      this.values[this.size] = value.clone();
      ++this.size;
   }

   private int binarySearch(int begin, int end, char key) {
      return Util.unsignedBinarySearch(this.keys, begin, end, key);
   }

   protected void clear() {
      this.keys = null;
      this.values = null;
      this.size = 0;
   }

   public void trim() {
      this.keys = Arrays.copyOf(this.keys, this.size);
      this.values = (MappeableContainer[])Arrays.copyOf(this.values, this.size);

      for(MappeableContainer c : this.values) {
         c.trim();
      }

   }

   public MutableRoaringArray clone() {
      try {
         MutableRoaringArray sa = (MutableRoaringArray)super.clone();
         sa.keys = Arrays.copyOf(this.keys, this.size);
         sa.values = (MappeableContainer[])Arrays.copyOf(this.values, this.size);

         for(int k = 0; k < this.size; ++k) {
            sa.values[k] = sa.values[k].clone();
         }

         sa.size = this.size;
         return sa;
      } catch (CloneNotSupportedException var3) {
         return null;
      }
   }

   protected void copyRange(int begin, int end, int newBegin) {
      int range = end - begin;
      System.arraycopy(this.keys, begin, this.keys, newBegin, range);
      System.arraycopy(this.values, begin, this.values, newBegin, range);
   }

   public void deserialize(DataInput in) throws IOException {
      this.clear();
      int cookie = Integer.reverseBytes(in.readInt());
      if ((cookie & '\uffff') != 12347 && cookie != 12346) {
         throw new InvalidRoaringFormat("I failed to find a valid cookie.");
      } else {
         this.size = (cookie & '\uffff') == 12347 ? (cookie >>> 16) + 1 : Integer.reverseBytes(in.readInt());
         if (this.size > 65536) {
            throw new InvalidRoaringFormat("Size too large");
         } else {
            if (this.keys == null || this.keys.length < this.size) {
               this.keys = new char[this.size];
               this.values = new MappeableContainer[this.size];
            }

            byte[] bitmapOfRunContainers = null;
            boolean hasrun = (cookie & '\uffff') == 12347;
            if (hasrun) {
               bitmapOfRunContainers = new byte[(this.size + 7) / 8];
               in.readFully(bitmapOfRunContainers);
            }

            char[] keys = new char[this.size];
            int[] cardinalities = new int[this.size];
            boolean[] isBitmap = new boolean[this.size];

            for(int k = 0; k < this.size; ++k) {
               keys[k] = Character.reverseBytes(in.readChar());
               cardinalities[k] = 1 + ('\uffff' & Character.reverseBytes(in.readChar()));
               isBitmap[k] = cardinalities[k] > 4096;
               if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  isBitmap[k] = false;
               }
            }

            if (!hasrun || this.size >= 4) {
               in.skipBytes(this.size * 4);
            }

            for(int k = 0; k < this.size; ++k) {
               MappeableContainer val;
               if (isBitmap[k]) {
                  LongBuffer bitmapArray = LongBuffer.allocate(1024);

                  for(int l = 0; l < bitmapArray.limit(); ++l) {
                     bitmapArray.put(l, Long.reverseBytes(in.readLong()));
                  }

                  val = new MappeableBitmapContainer(bitmapArray, cardinalities[k]);
               } else if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  int nbrruns = Character.reverseBytes(in.readChar());
                  CharBuffer charArray = CharBuffer.allocate(2 * nbrruns);

                  for(int l = 0; l < charArray.limit(); ++l) {
                     charArray.put(l, Character.reverseBytes(in.readChar()));
                  }

                  val = new MappeableRunContainer(charArray, nbrruns);
               } else {
                  CharBuffer charArray = CharBuffer.allocate(cardinalities[k]);

                  for(int l = 0; l < charArray.limit(); ++l) {
                     charArray.put(l, Character.reverseBytes(in.readChar()));
                  }

                  val = new MappeableArrayContainer(charArray, cardinalities[k]);
               }

               this.keys[k] = keys[k];
               this.values[k] = val;
            }

         }
      }
   }

   public void deserialize(ByteBuffer bbf) {
      this.clear();
      ByteBuffer buffer = bbf.order() == ByteOrder.LITTLE_ENDIAN ? bbf : bbf.slice().order(ByteOrder.LITTLE_ENDIAN);
      int cookie = buffer.getInt();
      if ((cookie & '\uffff') != 12347 && cookie != 12346) {
         throw new InvalidRoaringFormat("I failed to find one of the right cookies. " + cookie);
      } else {
         boolean hasRunContainers = (cookie & '\uffff') == 12347;
         this.size = hasRunContainers ? (cookie >>> 16) + 1 : buffer.getInt();
         if (this.size > 65536) {
            throw new InvalidRoaringFormat("Size too large");
         } else {
            if (this.keys == null || this.keys.length < this.size) {
               this.keys = new char[this.size];
               this.values = new MappeableContainer[this.size];
            }

            byte[] bitmapOfRunContainers = null;
            boolean hasrun = (cookie & '\uffff') == 12347;
            if (hasrun) {
               bitmapOfRunContainers = new byte[(this.size + 7) / 8];
               buffer.get(bitmapOfRunContainers);
            }

            char[] keys = new char[this.size];
            int[] cardinalities = new int[this.size];
            boolean[] isBitmap = new boolean[this.size];

            for(int k = 0; k < this.size; ++k) {
               keys[k] = buffer.getChar();
               cardinalities[k] = 1 + buffer.getChar();
               isBitmap[k] = cardinalities[k] > 4096;
               if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << (k & 7)) != 0) {
                  isBitmap[k] = false;
               }
            }

            if (!hasrun || this.size >= 4) {
               buffer.position(buffer.position() + this.size * 4);
            }

            for(int k = 0; k < this.size; ++k) {
               MappeableContainer container;
               if (isBitmap[k]) {
                  long[] array = new long[1024];
                  buffer.asLongBuffer().get(array);
                  container = new MappeableBitmapContainer(LongBuffer.wrap(array), cardinalities[k]);
                  buffer.position(buffer.position() + 8192);
               } else if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << (k & 7)) != 0) {
                  int nbrruns = buffer.getChar();
                  int length = 2 * nbrruns;
                  char[] array = new char[length];
                  buffer.asCharBuffer().get(array);
                  container = new MappeableRunContainer(CharBuffer.wrap(array), nbrruns);
                  buffer.position(buffer.position() + length * 2);
               } else {
                  int cardinality = cardinalities[k];
                  char[] array = new char[cardinality];
                  buffer.asCharBuffer().get(array);
                  container = new MappeableArrayContainer(CharBuffer.wrap(array), cardinality);
                  buffer.position(buffer.position() + cardinality * 2);
               }

               this.keys[k] = keys[k];
               this.values[k] = container;
            }

         }
      }
   }

   protected void extendArray(int k) {
      if (this.size + k > this.keys.length) {
         int newCapacity;
         if (this.keys.length < 1024) {
            newCapacity = 2 * (this.size + k);
         } else {
            newCapacity = 5 * (this.size + k) / 4;
         }

         this.keys = Arrays.copyOf(this.keys, newCapacity);
         this.values = (MappeableContainer[])Arrays.copyOf(this.values, newCapacity);
      }

   }

   public int getCardinality(int i) {
      return this.getContainerAtIndex(i).getCardinality();
   }

   public int getContainerIndex(char x) {
      return this.binarySearch(0, this.size, x);
   }

   public MappeableContainer getContainerAtIndex(int i) {
      return this.values[i];
   }

   public MappeableContainerPointer getContainerPointer() {
      return this.getContainerPointer(0);
   }

   public MappeableContainerPointer getContainerPointer(final int startIndex) {
      return new MappeableContainerPointer() {
         int k = startIndex;

         public void advance() {
            ++this.k;
         }

         public MappeableContainerPointer clone() {
            try {
               return (MappeableContainerPointer)super.clone();
            } catch (CloneNotSupportedException var2) {
               return null;
            }
         }

         public int compareTo(MappeableContainerPointer o) {
            return this.key() != o.key() ? this.key() - o.key() : o.getCardinality() - this.getCardinality();
         }

         public int getCardinality() {
            return this.getContainer().getCardinality();
         }

         public MappeableContainer getContainer() {
            return this.k >= MutableRoaringArray.this.size ? null : MutableRoaringArray.this.values[this.k];
         }

         public int getSizeInBytes() {
            return this.getContainer().getArraySizeInBytes();
         }

         public boolean hasContainer() {
            return 0 <= this.k && this.k < MutableRoaringArray.this.size;
         }

         public boolean isBitmapContainer() {
            return this.getContainer() instanceof MappeableBitmapContainer;
         }

         public boolean isRunContainer() {
            return this.getContainer() instanceof MappeableRunContainer;
         }

         public char key() {
            return MutableRoaringArray.this.keys[this.k];
         }

         public void previous() {
            --this.k;
         }
      };
   }

   public int getIndex(char x) {
      return this.size != 0 && this.keys[this.size - 1] != x ? this.binarySearch(0, this.size, x) : this.size - 1;
   }

   public char getKeyAtIndex(int i) {
      return this.keys[i];
   }

   public boolean equals(Object o) {
      if (o instanceof ImmutableRoaringArray) {
         ImmutableRoaringArray srb = (ImmutableRoaringArray)o;
         if (srb.size() != this.size()) {
            return false;
         }

         MappeableContainerPointer cp = this.getContainerPointer();
         MappeableContainerPointer cpo = srb.getContainerPointer();

         while(cp.hasContainer() && cpo.hasContainer()) {
            if (cp.key() != cpo.key()) {
               return false;
            }

            if (!cp.getContainer().equals(cpo.getContainer())) {
               return false;
            }
         }
      }

      return false;
   }

   public int hashCode() {
      int hashvalue = 0;

      for(int k = 0; k < this.size; ++k) {
         hashvalue = 31 * hashvalue + this.keys[k] * 15790320 + this.values[k].hashCode();
      }

      return hashvalue;
   }

   public boolean hasRunCompression() {
      for(int k = 0; k < this.size; ++k) {
         MappeableContainer ck = this.values[k];
         if (ck instanceof MappeableRunContainer) {
            return true;
         }
      }

      return false;
   }

   protected int headerSize() {
      if (this.hasRunCompression()) {
         return this.size < 4 ? 4 + (this.size + 7) / 8 + 4 * this.size : 4 + (this.size + 7) / 8 + 8 * this.size;
      } else {
         return 8 + 8 * this.size;
      }
   }

   protected void insertNewKeyValueAt(int i, char key, MappeableContainer value) {
      this.extendArray(1);
      System.arraycopy(this.keys, i, this.keys, i + 1, this.size - i);
      System.arraycopy(this.values, i, this.values, i + 1, this.size - i);
      this.keys[i] = key;
      this.values[i] = value;
      ++this.size;
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.deserialize((DataInput)in);
   }

   protected void removeAtIndex(int i) {
      System.arraycopy(this.keys, i + 1, this.keys, i, this.size - i - 1);
      this.keys[this.size - 1] = 0;
      System.arraycopy(this.values, i + 1, this.values, i, this.size - i - 1);
      this.values[this.size - 1] = null;
      --this.size;
   }

   protected void removeIndexRange(int begin, int end) {
      if (end > begin) {
         int range = end - begin;
         System.arraycopy(this.keys, end, this.keys, begin, this.size - end);
         System.arraycopy(this.values, end, this.values, begin, this.size - end);

         for(int i = 1; i <= range; ++i) {
            this.keys[this.size - i] = 0;
            this.values[this.size - i] = null;
         }

         this.size -= range;
      }
   }

   protected void replaceKeyAndContainerAtIndex(int i, char key, MappeableContainer c) {
      this.keys[i] = key;
      this.values[i] = c;
   }

   protected void resize(int newLength) {
      Arrays.fill(this.keys, newLength, this.size, '\u0000');
      Arrays.fill(this.values, newLength, this.size, (Object)null);
      this.size = newLength;
   }

   public void serialize(DataOutput out) throws IOException {
      int startOffset = 0;
      boolean hasrun = this.hasRunCompression();
      if (hasrun) {
         out.writeInt(Integer.reverseBytes(12347 | this.size - 1 << 16));
         byte[] bitmapOfRunContainers = new byte[(this.size + 7) / 8];

         for(int i = 0; i < this.size; ++i) {
            if (this.values[i] instanceof MappeableRunContainer) {
               bitmapOfRunContainers[i / 8] = (byte)(bitmapOfRunContainers[i / 8] | 1 << i % 8);
            }
         }

         out.write(bitmapOfRunContainers);
         if (this.size < 4) {
            startOffset = 4 + 4 * this.size + bitmapOfRunContainers.length;
         } else {
            startOffset = 4 + 8 * this.size + bitmapOfRunContainers.length;
         }
      } else {
         out.writeInt(Integer.reverseBytes(12346));
         out.writeInt(Integer.reverseBytes(this.size));
         startOffset = 8 + this.size * 4 + this.size * 4;
      }

      for(int k = 0; k < this.size; ++k) {
         out.writeShort(Character.reverseBytes(this.keys[k]));
         out.writeShort(Character.reverseBytes((char)(this.values[k].getCardinality() - 1)));
      }

      if (!hasrun || this.size >= 4) {
         for(int k = 0; k < this.size; ++k) {
            out.writeInt(Integer.reverseBytes(startOffset));
            startOffset += this.values[k].getArraySizeInBytes();
         }
      }

      for(int k = 0; k < this.size; ++k) {
         this.values[k].writeArray(out);
      }

   }

   public void serialize(ByteBuffer buffer) {
      ByteBuffer buf = buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffer : buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
      boolean hasrun = this.hasRunCompression();
      int startOffset;
      if (hasrun) {
         buf.putInt(12347 | this.size - 1 << 16);
         int offset = buf.position();

         for(int i = 0; i < this.size; i += 8) {
            int runMarker = 0;

            for(int j = 0; j < 8 && i + j < this.size; ++j) {
               if (this.values[i + j] instanceof MappeableRunContainer) {
                  runMarker |= 1 << j;
               }
            }

            buf.put((byte)runMarker);
         }

         int runMarkersLength = buf.position() - offset;
         if (this.size < 4) {
            startOffset = 4 + 4 * this.size + runMarkersLength;
         } else {
            startOffset = 4 + 8 * this.size + runMarkersLength;
         }
      } else {
         buf.putInt(12346);
         buf.putInt(this.size);
         startOffset = 8 + 4 * this.size + 4 * this.size;
      }

      for(int k = 0; k < this.size; ++k) {
         buf.putChar(this.keys[k]);
         buf.putChar((char)(this.values[k].getCardinality() - 1));
      }

      if (!hasrun || this.size >= 4) {
         for(int k = 0; k < this.size; ++k) {
            buf.putInt(startOffset);
            startOffset += this.values[k].getArraySizeInBytes();
         }
      }

      for(int k = 0; k < this.size; ++k) {
         this.values[k].writeArray(buf);
      }

      if (buf != buffer) {
         buffer.position(buffer.position() + buf.position());
      }

   }

   public int serializedSizeInBytes() {
      int count = this.headerSize();

      for(int k = 0; k < this.size; ++k) {
         count += this.values[k].getArraySizeInBytes();
      }

      return count;
   }

   protected void setContainerAtIndex(int i, MappeableContainer c) {
      this.values[i] = c;
   }

   public int size() {
      return this.size;
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize((DataOutput)out);
   }

   public boolean containsForContainerAtIndex(int i, char x) {
      return this.getContainerAtIndex(i).contains(x);
   }

   public int first() {
      this.assertNonEmpty();
      char firstKey = this.getKeyAtIndex(0);
      MappeableContainer container = this.getContainerAtIndex(0);
      return firstKey << 16 | container.first();
   }

   public int last() {
      this.assertNonEmpty();
      char lastKey = this.getKeyAtIndex(this.size - 1);
      MappeableContainer container = this.getContainerAtIndex(this.size - 1);
      return lastKey << 16 | container.last();
   }

   public int firstSigned() {
      this.assertNonEmpty();
      int index = this.advanceUntil('耀', -1);
      if (index == this.size) {
         index = 0;
      }

      char key = this.getKeyAtIndex(index);
      MappeableContainer container = this.getContainerAtIndex(index);
      return key << 16 | container.first();
   }

   public int lastSigned() {
      this.assertNonEmpty();
      int index = this.advanceUntil('耀', -1) - 1;
      if (index == -1) {
         index += this.size;
      }

      char key = this.getKeyAtIndex(index);
      MappeableContainer container = this.getContainerAtIndex(index);
      return key << 16 | container.last();
   }

   private void assertNonEmpty() {
      if (this.size == 0) {
         throw new NoSuchElementException("Empty MutableRoaringArray");
      }
   }
}
