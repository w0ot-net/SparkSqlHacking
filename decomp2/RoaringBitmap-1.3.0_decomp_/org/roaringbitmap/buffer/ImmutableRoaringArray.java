package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.NoSuchElementException;
import org.roaringbitmap.InvalidRoaringFormat;

public final class ImmutableRoaringArray implements PointableRoaringArray {
   protected static final short SERIAL_COOKIE = 12347;
   protected static final short SERIAL_COOKIE_NO_RUNCONTAINER = 12346;
   private static final int startofrunbitmap = 4;
   ByteBuffer buffer;
   int size;

   protected ImmutableRoaringArray(ByteBuffer bbf) {
      this.buffer = bbf.slice();
      this.buffer.order(ByteOrder.LITTLE_ENDIAN);
      int cookie = this.buffer.getInt(0);
      boolean hasRunContainers = (cookie & '\uffff') == 12347;
      if (!hasRunContainers && cookie != 12346) {
         throw new InvalidRoaringFormat("I failed to find one of the right cookies. " + cookie);
      } else {
         this.size = hasRunContainers ? (cookie >>> 16) + 1 : this.buffer.getInt(4);
         this.buffer.limit(this.computeSerializedSizeInBytes(hasRunContainers));
      }
   }

   public int advanceUntil(char x, int pos) {
      int lower = pos + 1;
      if (lower < this.size && this.getKey(lower) < x) {
         int spansize;
         for(spansize = 1; lower + spansize < this.size && this.getKey(lower + spansize) < x; spansize *= 2) {
         }

         int upper = lower + spansize < this.size ? lower + spansize : this.size - 1;
         if (this.getKey(upper) == x) {
            return upper;
         } else if (this.getKey(upper) < x) {
            return this.size;
         } else {
            lower += spansize / 2;

            while(lower + 1 != upper) {
               int mid = (lower + upper) / 2;
               if (this.getKey(mid) == x) {
                  return mid;
               }

               if (this.getKey(mid) < x) {
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

   private int branchyUnsignedBinarySearch(char k) {
      int low = 0;
      int high = this.size - 1;
      int ikey = k;

      while(low <= high) {
         int middleIndex = low + high >>> 1;
         int middleValue = this.getKey(middleIndex);
         if (middleValue < ikey) {
            low = middleIndex + 1;
         } else {
            if (middleValue <= ikey) {
               return middleIndex;
            }

            high = middleIndex - 1;
         }
      }

      return -(low + 1);
   }

   public ImmutableRoaringArray clone() {
      try {
         ImmutableRoaringArray sa = (ImmutableRoaringArray)super.clone();
         return sa;
      } catch (CloneNotSupportedException var3) {
         return null;
      }
   }

   private int computeSerializedSizeInBytes(boolean hasRunContainers) {
      if (this.size == 0) {
         return this.headerSize(hasRunContainers);
      } else {
         int positionOfLastContainer = this.getOffsetContainer(this.size - 1, hasRunContainers);
         int sizeOfLastContainer;
         if (this.isRunContainer(this.size - 1, hasRunContainers)) {
            int nbrruns = this.buffer.getChar(positionOfLastContainer);
            sizeOfLastContainer = BufferUtil.getSizeInBytesFromCardinalityEtc(0, nbrruns, true);
         } else {
            int cardinalityOfLastContainer = this.getCardinality(this.size - 1);
            sizeOfLastContainer = BufferUtil.getSizeInBytesFromCardinalityEtc(cardinalityOfLastContainer, 0, false);
         }

         return sizeOfLastContainer + positionOfLastContainer;
      }
   }

   public int getCardinality(int k) {
      if (k >= 0 && k < this.size) {
         return this.buffer.getChar(this.getStartOfKeys() + 4 * k + 2) + 1;
      } else {
         throw new IllegalArgumentException("out of range container index: " + k + " (report as a bug)");
      }
   }

   public int getContainerIndex(char x) {
      return this.unsignedBinarySearch(x);
   }

   public MappeableContainer getContainerAtIndex(int i) {
      boolean hasrun = this.hasRunCompression();
      ByteBuffer tmp = this.buffer.duplicate();
      tmp.order(this.buffer.order());
      tmp.position(this.getOffsetContainer(i, hasrun));
      if (this.isRunContainer(i, hasrun)) {
         int nbrruns = tmp.getChar();
         CharBuffer charArray = tmp.asCharBuffer();
         charArray.limit(2 * nbrruns);
         return new MappeableRunContainer(charArray, nbrruns);
      } else {
         int cardinality = this.getCardinality(i);
         boolean isBitmap = cardinality > 4096;
         if (isBitmap) {
            LongBuffer bitmapArray = tmp.asLongBuffer();
            bitmapArray.limit(1024);
            return new MappeableBitmapContainer(bitmapArray, cardinality);
         } else {
            CharBuffer charArray = tmp.asCharBuffer();
            charArray.limit(cardinality);
            return new MappeableArrayContainer(charArray, cardinality);
         }
      }
   }

   public boolean containsForContainerAtIndex(int i, char x) {
      boolean hasrun = this.hasRunCompression();
      int containerpos = this.getOffsetContainer(i, hasrun);
      if (this.isRunContainer(i, hasrun)) {
         int nbrruns = this.buffer.getChar(containerpos);
         return MappeableRunContainer.contains(this.buffer, containerpos + 2, x, nbrruns);
      } else {
         int cardinality = this.getCardinality(i);
         boolean isBitmap = cardinality > 4096;
         return isBitmap ? MappeableBitmapContainer.contains(this.buffer, containerpos, x) : MappeableArrayContainer.contains(this.buffer, containerpos, x, cardinality);
      }
   }

   public MappeableContainerPointer getContainerPointer() {
      return this.getContainerPointer(0);
   }

   public MappeableContainerPointer getContainerPointer(final int startIndex) {
      final boolean hasrun = !this.isEmpty() && this.hasRunCompression();
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
            return ImmutableRoaringArray.this.getCardinality(this.k);
         }

         public MappeableContainer getContainer() {
            return this.k >= ImmutableRoaringArray.this.size ? null : ImmutableRoaringArray.this.getContainerAtIndex(this.k);
         }

         public int getSizeInBytes() {
            if (ImmutableRoaringArray.this.isRunContainer(this.k, hasrun)) {
               int pos = ImmutableRoaringArray.this.getOffsetContainer(this.k, true);
               int nbrruns = ImmutableRoaringArray.this.buffer.getChar(pos);
               return BufferUtil.getSizeInBytesFromCardinalityEtc(0, nbrruns, true);
            } else {
               int CardinalityOfLastContainer = this.getCardinality();
               return BufferUtil.getSizeInBytesFromCardinalityEtc(CardinalityOfLastContainer, 0, false);
            }
         }

         public boolean hasContainer() {
            return 0 <= this.k && this.k < ImmutableRoaringArray.this.size;
         }

         public boolean isBitmapContainer() {
            if (ImmutableRoaringArray.this.isRunContainer(this.k, hasrun)) {
               return false;
            } else {
               return this.getCardinality() > 4096;
            }
         }

         public boolean isRunContainer() {
            return ImmutableRoaringArray.this.isRunContainer(this.k, hasrun);
         }

         public char key() {
            return ImmutableRoaringArray.this.getKeyAtIndex(this.k);
         }

         public void previous() {
            --this.k;
         }
      };
   }

   public int getIndex(char x) {
      return this.unsignedBinarySearch(x);
   }

   private int getKey(int k) {
      return this.buffer.getChar(this.getStartOfKeys() + 4 * k);
   }

   public char getKeyAtIndex(int i) {
      return this.buffer.getChar(4 * i + this.getStartOfKeys());
   }

   private int getOffsetContainer(int k, boolean hasRunCompression) {
      if (k >= 0 && k < this.size) {
         if (hasRunCompression) {
            return this.size < 4 ? this.getOffsetContainerSlow(k, true) : this.buffer.getInt(4 + 4 * this.size + (this.size + 7) / 8 + 4 * k);
         } else {
            return this.buffer.getInt(8 + 4 * this.size + 4 * k);
         }
      } else {
         throw new IllegalArgumentException("out of range container index: " + k + " (report as a bug)");
      }
   }

   private int getOffsetContainerSlow(int k, boolean hasRunCompression) {
      int pos = this.headerSize(hasRunCompression);

      for(int z = 0; z < k; ++z) {
         if (this.isRunContainer(z, hasRunCompression)) {
            int nbrruns = this.buffer.getChar(pos);
            int sizeOfLastContainer = BufferUtil.getSizeInBytesFromCardinalityEtc(0, nbrruns, true);
            pos += sizeOfLastContainer;
         } else {
            int cardinalityOfLastContainer = this.getCardinality(z);
            int sizeOfLastContainer = BufferUtil.getSizeInBytesFromCardinalityEtc(cardinalityOfLastContainer, 0, false);
            pos += sizeOfLastContainer;
         }
      }

      return pos;
   }

   private int getStartOfKeys() {
      return this.hasRunCompression() ? 4 + (this.size + 7) / 8 : 8;
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
      MappeableContainerPointer cp = this.getContainerPointer();
      int hashvalue = 0;

      while(cp.hasContainer()) {
         int th = cp.key() * 15790320 + cp.getContainer().hashCode();
         hashvalue = 31 * hashvalue + th;
         cp.advance();
      }

      return hashvalue;
   }

   public boolean hasRunCompression() {
      return (this.buffer.getInt(0) & '\uffff') == 12347;
   }

   protected int headerSize(boolean hasrun) {
      if (hasrun) {
         return this.size < 4 ? 4 + (this.size + 7) / 8 + 4 * this.size : 4 + (this.size + 7) / 8 + 8 * this.size;
      } else {
         return 8 + 8 * this.size;
      }
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   private boolean isRunContainer(int i, boolean hasrun) {
      if (hasrun) {
         int j = this.buffer.get(4 + i / 8);
         int mask = 1 << i % 8;
         return (j & mask) != 0;
      } else {
         return false;
      }
   }

   public void serialize(DataOutput out) throws IOException {
      if (this.buffer.hasArray()) {
         out.write(this.buffer.array(), this.buffer.arrayOffset(), this.buffer.limit());
      } else {
         ByteBuffer tmp = this.buffer.duplicate();
         tmp.position(0);
         WritableByteChannel channel = Channels.newChannel((OutputStream)out);

         try {
            channel.write(tmp);
         } catch (Throwable var7) {
            if (channel != null) {
               try {
                  channel.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (channel != null) {
            channel.close();
         }
      }

   }

   public void serialize(ByteBuffer buffer) {
      buffer.put(this.buffer.duplicate());
   }

   public int serializedSizeInBytes() {
      return this.buffer.limit();
   }

   public int size() {
      return this.size;
   }

   private int unsignedBinarySearch(char k) {
      return this.branchyUnsignedBinarySearch(k);
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
         throw new NoSuchElementException("Empty ImmutableRoaringArray");
      }
   }
}
