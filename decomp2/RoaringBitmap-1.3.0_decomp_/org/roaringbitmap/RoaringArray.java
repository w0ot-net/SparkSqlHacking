package org.roaringbitmap;

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

public final class RoaringArray implements Cloneable, Externalizable, AppendableStorage {
   private static final char SERIAL_COOKIE_NO_RUNCONTAINER = '〺';
   private static final char SERIAL_COOKIE = '〻';
   private static final int NO_OFFSET_THRESHOLD = 4;
   private static final long serialVersionUID = 8L;
   static final int INITIAL_CAPACITY = 4;
   char[] keys;
   Container[] values;
   int size;

   protected RoaringArray() {
      this(4);
   }

   RoaringArray(int initialCapacity) {
      this(new char[initialCapacity], new Container[initialCapacity], 0);
   }

   RoaringArray(char[] keys, Container[] values, int size) {
      this.keys = null;
      this.values = null;
      this.size = 0;
      this.keys = keys;
      this.values = values;
      this.size = size;
   }

   protected int advanceUntil(char x, int pos) {
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

   public void append(char key, Container value) {
      if (this.size > 0 && key < this.keys[this.size - 1]) {
         throw new IllegalArgumentException("append only: " + key + " < " + this.keys[this.size - 1]);
      } else {
         this.extendArray(1);
         this.keys[this.size] = key;
         this.values[this.size] = value;
         ++this.size;
      }
   }

   void append(RoaringArray roaringArray) {
      assert this.size == 0 || roaringArray.size == 0 || this.keys[this.size - 1] < roaringArray.keys[0];

      if (roaringArray.size != 0 && this.size != 0) {
         this.keys = Arrays.copyOf(this.keys, this.size + roaringArray.size);
         this.values = (Container[])Arrays.copyOf(this.values, this.size + roaringArray.size);
         System.arraycopy(roaringArray.keys, 0, this.keys, this.size, roaringArray.size);
         System.arraycopy(roaringArray.values, 0, this.values, this.size, roaringArray.size);
         this.size += roaringArray.size;
      } else if (this.size == 0 && roaringArray.size != 0) {
         this.keys = Arrays.copyOf(roaringArray.keys, roaringArray.keys.length);
         this.values = (Container[])Arrays.copyOf(roaringArray.values, roaringArray.values.length);
         this.size = roaringArray.size;
      }

   }

   void appendCopiesAfter(RoaringArray sa, char beforeStart) {
      int startLocation = sa.getIndex(beforeStart);
      if (startLocation >= 0) {
         ++startLocation;
      } else {
         startLocation = -startLocation - 1;
      }

      this.extendArray(sa.size - startLocation);

      for(int i = startLocation; i < sa.size; ++i) {
         this.keys[this.size] = sa.keys[i];
         this.values[this.size] = sa.values[i].clone();
         ++this.size;
      }

   }

   void appendCopiesUntil(RoaringArray sourceArray, char stoppingKey) {
      for(int i = 0; i < sourceArray.size && sourceArray.keys[i] < stoppingKey; ++i) {
         this.extendArray(1);
         this.keys[this.size] = sourceArray.keys[i];
         this.values[this.size] = sourceArray.values[i].clone();
         ++this.size;
      }

   }

   void appendCopy(RoaringArray sa, int index) {
      this.extendArray(1);
      this.keys[this.size] = sa.keys[index];
      this.values[this.size] = sa.values[index].clone();
      ++this.size;
   }

   void appendCopy(RoaringArray sa, int startingIndex, int end) {
      this.extendArray(end - startingIndex);

      for(int i = startingIndex; i < end; ++i) {
         this.keys[this.size] = sa.keys[i];
         this.values[this.size] = sa.values[i].clone();
         ++this.size;
      }

   }

   protected void append(RoaringArray sa, int startingIndex, int end) {
      this.extendArray(end - startingIndex);

      for(int i = startingIndex; i < end; ++i) {
         this.keys[this.size] = sa.keys[i];
         this.values[this.size] = sa.values[i];
         ++this.size;
      }

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
      this.values = (Container[])Arrays.copyOf(this.values, this.size);

      for(Container c : this.values) {
         c.trim();
      }

   }

   public RoaringArray clone() throws CloneNotSupportedException {
      RoaringArray sa = (RoaringArray)super.clone();
      sa.keys = Arrays.copyOf(this.keys, this.size);
      sa.values = (Container[])Arrays.copyOf(this.values, this.size);

      for(int k = 0; k < this.size; ++k) {
         sa.values[k] = sa.values[k].clone();
      }

      sa.size = this.size;
      return sa;
   }

   void copyRange(int begin, int end, int newBegin) {
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
               this.values = new Container[this.size];
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
               Container val;
               if (isBitmap[k]) {
                  long[] bitmapArray = new long[1024];

                  for(int l = 0; l < bitmapArray.length; ++l) {
                     bitmapArray[l] = Long.reverseBytes(in.readLong());
                  }

                  val = new BitmapContainer(bitmapArray, cardinalities[k]);
               } else if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  int nbrruns = Character.reverseBytes(in.readChar());
                  char[] lengthsAndValues = new char[2 * nbrruns];

                  for(int j = 0; j < 2 * nbrruns; ++j) {
                     lengthsAndValues[j] = Character.reverseBytes(in.readChar());
                  }

                  val = new RunContainer(lengthsAndValues, nbrruns);
               } else {
                  char[] charArray = new char[cardinalities[k]];

                  for(int l = 0; l < charArray.length; ++l) {
                     charArray[l] = Character.reverseBytes(in.readChar());
                  }

                  val = new ArrayContainer(charArray);
               }

               this.keys[k] = keys[k];
               this.values[k] = val;
            }

         }
      }
   }

   public void deserialize(DataInput in, byte[] buffer) throws IOException {
      if (buffer != null && buffer.length == 0) {
         buffer = null;
      } else if (buffer != null && buffer.length % 8 != 0) {
         throw new IllegalArgumentException("We need a buffer with a length multiple of 8. was length=" + buffer.length);
      }

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
               this.values = new Container[this.size];
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
               Container val;
               if (isBitmap[k]) {
                  long[] bitmapArray = new long[1024];
                  if (buffer == null) {
                     buffer = new byte[8192];
                  }

                  if (buffer.length < 8192) {
                     for(int iBlock = 0; iBlock <= 8 * bitmapArray.length / buffer.length; ++iBlock) {
                        int start = buffer.length * iBlock;
                        int end = Math.min(buffer.length * (iBlock + 1), 8 * bitmapArray.length);
                        in.readFully(buffer, 0, end - start);
                        ByteBuffer asByteBuffer = ByteBuffer.wrap(buffer);
                        asByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                        LongBuffer asLongBuffer = asByteBuffer.asLongBuffer();
                        asLongBuffer.rewind();
                        asLongBuffer.get(bitmapArray, start / 8, (end - start) / 8);
                     }
                  } else {
                     in.readFully(buffer, 0, bitmapArray.length * 8);
                     ByteBuffer asByteBuffer = ByteBuffer.wrap(buffer);
                     asByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                     LongBuffer asLongBuffer = asByteBuffer.asLongBuffer();
                     asLongBuffer.rewind();
                     asLongBuffer.get(bitmapArray);
                  }

                  val = new BitmapContainer(bitmapArray, cardinalities[k]);
               } else if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  int nbrruns = Character.reverseBytes(in.readChar());
                  char[] lengthsAndValues = new char[2 * nbrruns];
                  if (buffer == null && lengthsAndValues.length > 8192) {
                     buffer = new byte[8192];
                  }

                  if (buffer == null) {
                     for(int j = 0; j < lengthsAndValues.length; ++j) {
                        lengthsAndValues[j] = Character.reverseBytes(in.readChar());
                     }
                  } else {
                     for(int iBlock = 0; iBlock <= 2 * lengthsAndValues.length / buffer.length; ++iBlock) {
                        int start = buffer.length * iBlock;
                        int end = Math.min(buffer.length * (iBlock + 1), 2 * lengthsAndValues.length);
                        in.readFully(buffer, 0, end - start);
                        ByteBuffer asByteBuffer = ByteBuffer.wrap(buffer);
                        asByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                        CharBuffer asCharBuffer = asByteBuffer.asCharBuffer();
                        asCharBuffer.rewind();
                        asCharBuffer.get(lengthsAndValues, start / 2, (end - start) / 2);
                     }
                  }

                  val = new RunContainer(lengthsAndValues, nbrruns);
               } else {
                  char[] charArray = new char[cardinalities[k]];
                  if (buffer == null && charArray.length > 8192) {
                     buffer = new byte[8192];
                  }

                  if (buffer == null) {
                     for(int j = 0; j < charArray.length; ++j) {
                        charArray[j] = Character.reverseBytes(in.readChar());
                     }
                  } else {
                     for(int iBlock = 0; iBlock <= 2 * charArray.length / buffer.length; ++iBlock) {
                        int start = buffer.length * iBlock;
                        int end = Math.min(buffer.length * (iBlock + 1), 2 * charArray.length);
                        in.readFully(buffer, 0, end - start);
                        ByteBuffer asByteBuffer = ByteBuffer.wrap(buffer);
                        asByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                        CharBuffer asCharBuffer = asByteBuffer.asCharBuffer();
                        asCharBuffer.rewind();
                        asCharBuffer.get(charArray, start / 2, (end - start) / 2);
                     }
                  }

                  val = new ArrayContainer(charArray);
               }

               this.keys[k] = keys[k];
               this.values[k] = val;
            }

         }
      }
   }

   public void deserialize(ByteBuffer bbf) {
      this.clear();
      ByteBuffer buffer = bbf.slice();
      buffer.order(ByteOrder.LITTLE_ENDIAN);
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
               this.values = new Container[this.size];
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
               cardinalities[k] = 1 + ('\uffff' & buffer.getChar());
               isBitmap[k] = cardinalities[k] > 4096;
               if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  isBitmap[k] = false;
               }
            }

            if (!hasrun || this.size >= 4) {
               buffer.position(buffer.position() + this.size * 4);
            }

            for(int k = 0; k < this.size; ++k) {
               Container val;
               if (isBitmap[k]) {
                  long[] bitmapArray = new long[1024];
                  buffer.asLongBuffer().get(bitmapArray);
                  buffer.position(buffer.position() + bitmapArray.length * 8);
                  val = new BitmapContainer(bitmapArray, cardinalities[k]);
               } else if (bitmapOfRunContainers != null && (bitmapOfRunContainers[k / 8] & 1 << k % 8) != 0) {
                  int nbrruns = buffer.getChar();
                  char[] lengthsAndValues = new char[2 * nbrruns];
                  buffer.asCharBuffer().get(lengthsAndValues);
                  buffer.position(buffer.position() + lengthsAndValues.length * 2);
                  val = new RunContainer(lengthsAndValues, nbrruns);
               } else {
                  char[] charArray = new char[cardinalities[k]];
                  buffer.asCharBuffer().get(charArray);
                  buffer.position(buffer.position() + charArray.length * 2);
                  val = new ArrayContainer(charArray);
               }

               this.keys[k] = keys[k];
               this.values[k] = val;
            }

         }
      }
   }

   public boolean equals(Object o) {
      if (o instanceof RoaringArray) {
         RoaringArray srb = (RoaringArray)o;
         if (srb.size != this.size) {
            return false;
         }

         if (ArraysShim.equals(this.keys, 0, this.size, srb.keys, 0, srb.size)) {
            for(int i = 0; i < this.size; ++i) {
               if (!this.values[i].equals(srb.values[i])) {
                  return false;
               }
            }

            return true;
         }
      }

      return false;
   }

   void extendArray(int k) {
      if (this.size + k > this.keys.length) {
         int newCapacity;
         if (this.keys.length < 1024) {
            newCapacity = 2 * (this.size + k);
         } else {
            newCapacity = 5 * (this.size + k) / 4;
         }

         this.keys = Arrays.copyOf(this.keys, newCapacity);
         this.values = (Container[])Arrays.copyOf(this.values, newCapacity);
      }

   }

   protected int getContainerIndex(char x) {
      int i = this.binarySearch(0, this.size, x);
      return i;
   }

   protected Container getContainerAtIndex(int i) {
      return this.values[i];
   }

   public ContainerPointer getContainerPointer() {
      return this.getContainerPointer(0);
   }

   public ContainerPointer getContainerPointer(final int startIndex) {
      return new ContainerPointer() {
         int k = startIndex;

         public void advance() {
            ++this.k;
         }

         public ContainerPointer clone() {
            try {
               return (ContainerPointer)super.clone();
            } catch (CloneNotSupportedException var2) {
               return null;
            }
         }

         public int compareTo(ContainerPointer o) {
            return this.key() != o.key() ? this.key() - o.key() : o.getCardinality() - this.getCardinality();
         }

         public int getCardinality() {
            return this.getContainer().getCardinality();
         }

         public Container getContainer() {
            return this.k >= RoaringArray.this.size ? null : RoaringArray.this.values[this.k];
         }

         public boolean isBitmapContainer() {
            return this.getContainer() instanceof BitmapContainer;
         }

         public boolean isRunContainer() {
            return this.getContainer() instanceof RunContainer;
         }

         public char key() {
            return RoaringArray.this.keys[this.k];
         }
      };
   }

   int getIndex(char x) {
      return this.size != 0 && this.keys[this.size - 1] != x ? this.binarySearch(0, this.size, x) : this.size - 1;
   }

   protected char getKeyAtIndex(int i) {
      return this.keys[i];
   }

   public int hashCode() {
      int hashvalue = 0;

      for(int k = 0; k < this.size; ++k) {
         hashvalue = 31 * hashvalue + this.keys[k] * 15790320 + this.values[k].hashCode();
      }

      return hashvalue;
   }

   private boolean hasRunContainer() {
      for(int k = 0; k < this.size; ++k) {
         Container ck = this.values[k];
         if (ck instanceof RunContainer) {
            return true;
         }
      }

      return false;
   }

   private int headerSize() {
      if (this.hasRunContainer()) {
         return this.size < 4 ? 4 + (this.size + 7) / 8 + 4 * this.size : 4 + (this.size + 7) / 8 + 8 * this.size;
      } else {
         return 8 + 8 * this.size;
      }
   }

   void insertNewKeyValueAt(int i, char key, Container value) {
      this.extendArray(1);
      System.arraycopy(this.keys, i, this.keys, i + 1, this.size - i);
      this.keys[i] = key;
      System.arraycopy(this.values, i, this.values, i + 1, this.size - i);
      this.values[i] = value;
      ++this.size;
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.deserialize((DataInput)in);
   }

   void removeAtIndex(int i) {
      System.arraycopy(this.keys, i + 1, this.keys, i, this.size - i - 1);
      this.keys[this.size - 1] = 0;
      System.arraycopy(this.values, i + 1, this.values, i, this.size - i - 1);
      this.values[this.size - 1] = null;
      --this.size;
   }

   void removeIndexRange(int begin, int end) {
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

   void replaceKeyAndContainerAtIndex(int i, char key, Container c) {
      this.keys[i] = key;
      this.values[i] = c;
   }

   void resize(int newLength) {
      Arrays.fill(this.keys, newLength, this.size, '\u0000');
      Arrays.fill(this.values, newLength, this.size, (Object)null);
      this.size = newLength;
   }

   public void serialize(DataOutput out) throws IOException {
      int startOffset = 0;
      boolean hasrun = this.hasRunContainer();
      if (hasrun) {
         out.writeInt(Integer.reverseBytes(12347 | this.size - 1 << 16));
         byte[] bitmapOfRunContainers = new byte[(this.size + 7) / 8];

         for(int i = 0; i < this.size; ++i) {
            if (this.values[i] instanceof RunContainer) {
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
         startOffset = 8 + 4 * this.size + 4 * this.size;
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
      boolean hasrun = this.hasRunContainer();
      int startOffset;
      if (hasrun) {
         buf.putInt(12347 | this.size - 1 << 16);
         int offset = buf.position();

         for(int i = 0; i < this.size; i += 8) {
            int runMarker = 0;

            for(int j = 0; j < 8 && i + j < this.size; ++j) {
               if (this.values[i + j] instanceof RunContainer) {
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

   void setContainerAtIndex(int i, Container c) {
      this.values[i] = c;
   }

   protected int size() {
      return this.size;
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.serialize((DataOutput)out);
   }

   public int first() {
      this.assertNonEmpty();
      char firstKey = this.keys[0];
      Container container = this.values[0];
      return firstKey << 16 | container.first();
   }

   public int last() {
      this.assertNonEmpty();
      char lastKey = this.keys[this.size - 1];
      Container container = this.values[this.size - 1];
      return lastKey << 16 | container.last();
   }

   public int firstSigned() {
      this.assertNonEmpty();
      int index = this.advanceUntil('耀', -1);
      if (index == this.size) {
         index = 0;
      }

      char key = this.keys[index];
      Container container = this.values[index];
      return key << 16 | container.first();
   }

   public int lastSigned() {
      this.assertNonEmpty();
      int index = this.advanceUntil('耀', -1) - 1;
      if (index == -1) {
         index += this.size;
      }

      char key = this.keys[index];
      Container container = this.values[index];
      return key << 16 | container.last();
   }

   private void assertNonEmpty() {
      if (this.size == 0) {
         throw new NoSuchElementException("Empty RoaringArray");
      }
   }
}
