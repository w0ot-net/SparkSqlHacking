package org.apache.datasketches.filters.bloomfilter;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.WritableBuffer;

final class HeapBitArray extends BitArray {
   private long numBitsSet_;
   private boolean isDirty_;
   private final long[] data_;

   HeapBitArray(long numBits) {
      if (numBits <= 0L) {
         throw new SketchesArgumentException("Number of bits must be strictly positive. Found: " + numBits);
      } else if (numBits > 137438953408L) {
         throw new SketchesArgumentException("Number of bits may not exceed 137438953408. Found: " + numBits);
      } else {
         int numLongs = (int)Math.ceil((double)numBits / (double)64.0F);
         this.numBitsSet_ = 0L;
         this.isDirty_ = false;
         this.data_ = new long[numLongs];
      }
   }

   HeapBitArray(long numBitsSet, long[] data) {
      this.data_ = data;
      this.isDirty_ = numBitsSet < 0L;
      this.numBitsSet_ = numBitsSet;
   }

   static HeapBitArray heapify(Buffer buffer, boolean isEmpty) {
      int numLongs = buffer.getInt();
      if (numLongs < 0) {
         throw new SketchesArgumentException("Possible corruption: Must have strictly positive array size. Found: " + numLongs);
      } else if (isEmpty) {
         return new HeapBitArray((long)numLongs * 64L);
      } else {
         buffer.getInt();
         long numBitsSet = buffer.getLong();
         long[] data = new long[numLongs];
         buffer.getLongArray(data, 0, numLongs);
         return new HeapBitArray(numBitsSet, data);
      }
   }

   protected boolean isDirty() {
      return this.isDirty_;
   }

   boolean hasMemory() {
      return false;
   }

   boolean isDirect() {
      return false;
   }

   boolean isReadOnly() {
      return false;
   }

   boolean getBit(long index) {
      return (this.data_[(int)index >>> 6] & 1L << (int)index) != 0L;
   }

   void setBit(long index) {
      long[] var10000 = this.data_;
      var10000[(int)index >>> 6] |= 1L << (int)index;
      this.isDirty_ = true;
   }

   boolean getAndSetBit(long index) {
      int offset = (int)index >>> 6;
      long mask = 1L << (int)index;
      if ((this.data_[offset] & mask) != 0L) {
         return true;
      } else {
         long[] var10000 = this.data_;
         var10000[offset] |= mask;
         ++this.numBitsSet_;
         return false;
      }
   }

   long getNumBitsSet() {
      if (this.isDirty_) {
         this.numBitsSet_ = 0L;

         for(long val : this.data_) {
            this.numBitsSet_ += (long)Long.bitCount(val);
         }
      }

      return this.numBitsSet_;
   }

   long getCapacity() {
      return (long)this.data_.length * 64L;
   }

   int getArrayLength() {
      return this.data_.length;
   }

   void union(BitArray other) {
      if (this.getCapacity() != other.getCapacity()) {
         throw new SketchesArgumentException("Cannot union bit arrays with unequal lengths");
      } else {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.data_.length; ++i) {
            long val = this.data_[i] | other.getLong(i);
            this.numBitsSet_ += (long)Long.bitCount(val);
            this.data_[i] = val;
         }

         this.isDirty_ = false;
      }
   }

   void intersect(BitArray other) {
      if (this.getCapacity() != other.getCapacity()) {
         throw new SketchesArgumentException("Cannot intersect bit arrays with unequal lengths");
      } else {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.data_.length; ++i) {
            long val = this.data_[i] & other.getLong(i);
            this.numBitsSet_ += (long)Long.bitCount(val);
            this.data_[i] = val;
         }

         this.isDirty_ = false;
      }
   }

   void invert() {
      if (this.isDirty_) {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.data_.length; ++i) {
            this.data_[i] = ~this.data_[i];
            this.numBitsSet_ += (long)Long.bitCount(this.data_[i]);
         }

         this.isDirty_ = false;
      } else {
         for(int i = 0; i < this.data_.length; ++i) {
            this.data_[i] = ~this.data_[i];
         }

         this.numBitsSet_ = this.getCapacity() - this.numBitsSet_;
      }

   }

   void writeToBuffer(WritableBuffer wbuf) {
      wbuf.putInt(this.data_.length);
      wbuf.putInt(0);
      if (!this.isEmpty()) {
         wbuf.putLong(this.isDirty_ ? -1L : this.numBitsSet_);
         wbuf.putLongArray(this.data_, 0, this.data_.length);
      }

   }

   protected long getLong(int arrayIndex) {
      return this.data_[arrayIndex];
   }

   protected void setLong(int arrayIndex, long value) {
      this.data_[arrayIndex] = value;
   }

   void reset() {
      Arrays.fill(this.data_, 0L);
      this.numBitsSet_ = 0L;
      this.isDirty_ = false;
   }
}
