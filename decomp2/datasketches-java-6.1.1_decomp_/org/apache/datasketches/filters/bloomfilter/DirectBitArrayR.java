package org.apache.datasketches.filters.bloomfilter;

import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesReadOnlyException;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class DirectBitArrayR extends BitArray {
   protected static final long NUM_BITS_OFFSET = 8L;
   protected static final long DATA_OFFSET = 16L;
   protected final int dataLength_;
   protected final WritableMemory wmem_;
   protected long numBitsSet_;

   protected DirectBitArrayR(int dataLength, long storedNumBitsSet, Memory mem) {
      this.dataLength_ = dataLength;
      this.wmem_ = (WritableMemory)mem;
      if (storedNumBitsSet == -1L) {
         this.numBitsSet_ = 0L;

         for(int i = 0; i < this.dataLength_; ++i) {
            this.numBitsSet_ += (long)Long.bitCount(this.wmem_.getLong(16L + (long)(i << 3)));
         }
      } else {
         this.numBitsSet_ = storedNumBitsSet;
      }

   }

   static DirectBitArrayR wrap(Memory mem, boolean isEmpty) {
      int arrayLength = mem.getInt(0L);
      long storedNumBitsSet = isEmpty ? 0L : mem.getLong(8L);
      if (arrayLength < 0) {
         throw new SketchesArgumentException("Possible corruption: Serialized image indicates non-positive array length");
      } else if (storedNumBitsSet != 0L && mem.getCapacity() < (long)(arrayLength + 2)) {
         throw new SketchesArgumentException("Memory capacity insufficient for Bloom Filter. Needed: " + (arrayLength + 2) + " , found: " + mem.getCapacity());
      } else {
         return new DirectBitArrayR(arrayLength, storedNumBitsSet, mem);
      }
   }

   long getCapacity() {
      return (long)this.dataLength_ * 64L;
   }

   long getNumBitsSet() {
      return this.numBitsSet_;
   }

   protected boolean isDirty() {
      return false;
   }

   int getArrayLength() {
      return this.dataLength_;
   }

   boolean getBit(long index) {
      if (this.isEmpty()) {
         return false;
      } else {
         return (this.wmem_.getByte(16L + (long)((int)index >>> 3)) & 1 << (int)(index & 7L)) != 0;
      }
   }

   protected long getLong(int arrayIndex) {
      return this.isEmpty() ? 0L : this.wmem_.getLong(16L + (long)(arrayIndex << 3));
   }

   public boolean hasMemory() {
      return this.wmem_ != null;
   }

   public boolean isDirect() {
      return this.wmem_ != null ? this.wmem_.isDirect() : false;
   }

   public boolean isReadOnly() {
      return true;
   }

   void reset() {
      throw new SketchesReadOnlyException("Attempt to call reset() on read-only memory");
   }

   void setBit(long index) {
      throw new SketchesReadOnlyException("Attempt to call setBit() on read-only memory");
   }

   boolean getAndSetBit(long index) {
      throw new SketchesReadOnlyException("Attempt to call getAndSetBit() on read-only memory");
   }

   void intersect(BitArray other) {
      throw new SketchesReadOnlyException("Attempt to call intersect() on read-only memory");
   }

   void union(BitArray other) {
      throw new SketchesReadOnlyException("Attempt to call union() on read-only memory");
   }

   void invert() {
      throw new SketchesReadOnlyException("Attempt to call invert() on read-only memory");
   }

   protected void setLong(int arrayIndex, long value) {
      throw new SketchesReadOnlyException("Attempt to call setLong() on read-only memory");
   }
}
