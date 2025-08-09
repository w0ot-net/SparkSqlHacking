package org.apache.datasketches.filters.bloomfilter;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.WritableMemory;

final class DirectBitArray extends DirectBitArrayR {
   DirectBitArray(int dataLength, long storedNumBitsSet, WritableMemory wmem) {
      super(dataLength, 0L, wmem);
      this.numBitsSet_ = storedNumBitsSet;
   }

   DirectBitArray(int dataLength, WritableMemory wmem) {
      super(dataLength, 0L, wmem);
      this.wmem_.putInt(0L, this.dataLength_);
      this.setNumBitsSet(0L);
      this.wmem_.clear(16L, (long)this.dataLength_ * 8L);
   }

   static DirectBitArray initialize(long numBits, WritableMemory wmem) {
      if (numBits <= 0L) {
         throw new SketchesArgumentException("Number of bits must be strictly positive. Found: " + numBits);
      } else if (numBits > 137438953408L) {
         throw new SketchesArgumentException("Maximum size of a single filter is 137438953408 + bits. Requested: " + numBits);
      } else {
         int arrayLength = (int)Math.ceil((double)numBits / (double)64.0F);
         long requiredBytes = (2L + (long)arrayLength) * 8L;
         if (wmem.getCapacity() < requiredBytes) {
            throw new SketchesArgumentException("Provided WritableMemory too small for requested array length. Requited: " + requiredBytes + ", provided capcity: " + wmem.getCapacity());
         } else {
            return new DirectBitArray(arrayLength, wmem);
         }
      }
   }

   static DirectBitArray writableWrap(WritableMemory mem, boolean isEmpty) {
      int arrayLength = mem.getInt(0L);
      long storedNumBitsSet = isEmpty ? 0L : mem.getLong(8L);
      if ((long)arrayLength * 64L > 137438953408L) {
         throw new SketchesArgumentException("Possible corruption: Serialized image indicates array beyond maximum filter capacity");
      } else if (isEmpty) {
         throw new SketchesArgumentException("Cannot wrap an empty filter for writing as there is no backing data array");
      } else if (storedNumBitsSet != 0L && mem.getCapacity() < (long)(arrayLength + 2)) {
         throw new SketchesArgumentException("Memory capacity insufficient for Bloom Filter. Needed: " + (arrayLength + 2) + " , found: " + mem.getCapacity());
      } else {
         return new DirectBitArray(arrayLength, storedNumBitsSet, mem);
      }
   }

   long getNumBitsSet() {
      if (this.isDirty()) {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.dataLength_; ++i) {
            this.numBitsSet_ += (long)Long.bitCount(this.getLong(i));
         }

         this.wmem_.putLong(8L, this.numBitsSet_);
      }

      return this.numBitsSet_;
   }

   protected boolean isDirty() {
      return this.numBitsSet_ == -1L;
   }

   boolean getBit(long index) {
      return (this.wmem_.getByte(16L + (long)((int)index >>> 3)) & 1 << (int)(index & 7L)) != 0;
   }

   protected long getLong(int arrayIndex) {
      return this.wmem_.getLong(16L + (long)(arrayIndex << 3));
   }

   public boolean isReadOnly() {
      return false;
   }

   void reset() {
      this.setNumBitsSet(0L);
      this.wmem_.clear(16L, (long)this.dataLength_ * 8L);
   }

   void setBit(long index) {
      long memoryOffset = 16L + (long)((int)index >>> 3);
      byte val = this.wmem_.getByte(memoryOffset);
      this.wmem_.setBits(memoryOffset, (byte)(val | 1 << (int)(index & 7L)));
      this.setNumBitsSet(-1L);
   }

   boolean getAndSetBit(long index) {
      long memoryOffset = 16L + (long)((int)index >>> 3);
      byte mask = (byte)(1 << (int)(index & 7L));
      byte val = this.wmem_.getByte(memoryOffset);
      if ((val & mask) != 0) {
         return true;
      } else {
         this.wmem_.setBits(memoryOffset, (byte)(val | mask));
         if (!this.isDirty()) {
            this.setNumBitsSet(this.numBitsSet_ + 1L);
         }

         return false;
      }
   }

   void intersect(BitArray other) {
      if (this.getCapacity() != other.getCapacity()) {
         throw new SketchesArgumentException("Cannot intersect bit arrays with unequal lengths");
      } else {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.dataLength_; ++i) {
            long val = this.getLong(i) & other.getLong(i);
            this.numBitsSet_ += (long)Long.bitCount(val);
            this.setLong(i, val);
         }

         this.wmem_.putLong(8L, this.numBitsSet_);
      }
   }

   void union(BitArray other) {
      if (this.getCapacity() != other.getCapacity()) {
         throw new SketchesArgumentException("Cannot intersect bit arrays with unequal lengths");
      } else {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.dataLength_; ++i) {
            long val = this.getLong(i) | other.getLong(i);
            this.numBitsSet_ += (long)Long.bitCount(val);
            this.setLong(i, val);
         }

         this.wmem_.putLong(8L, this.numBitsSet_);
      }
   }

   void invert() {
      if (this.isDirty()) {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.dataLength_; ++i) {
            long val = ~this.getLong(i);
            this.setLong(i, val);
            this.numBitsSet_ += (long)Long.bitCount(val);
         }
      } else {
         for(int i = 0; i < this.dataLength_; ++i) {
            this.setLong(i, ~this.getLong(i));
         }

         this.numBitsSet_ = this.getCapacity() - this.numBitsSet_;
      }

      this.wmem_.putLong(8L, this.numBitsSet_);
   }

   protected void setLong(int arrayIndex, long value) {
      this.wmem_.putLong(16L + (long)(arrayIndex << 3), value);
   }

   private final void setNumBitsSet(long numBitsSet) {
      this.numBitsSet_ = numBitsSet;
      this.wmem_.putLong(8L, numBitsSet);
   }
}
