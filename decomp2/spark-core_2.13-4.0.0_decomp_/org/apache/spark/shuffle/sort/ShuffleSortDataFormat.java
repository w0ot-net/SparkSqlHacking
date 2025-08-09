package org.apache.spark.shuffle.sort;

import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.util.collection.SortDataFormat;

final class ShuffleSortDataFormat extends SortDataFormat {
   private final LongArray buffer;
   // $FF: synthetic field
   static final boolean $assertionsDisabled = !ShuffleSortDataFormat.class.desiredAssertionStatus();

   ShuffleSortDataFormat(LongArray buffer) {
      this.buffer = buffer;
   }

   public PackedRecordPointer getKey(LongArray data, int pos) {
      throw new UnsupportedOperationException();
   }

   public PackedRecordPointer newKey() {
      return new PackedRecordPointer();
   }

   public PackedRecordPointer getKey(LongArray data, int pos, PackedRecordPointer reuse) {
      reuse.set(data.get(pos));
      return reuse;
   }

   public void swap(LongArray data, int pos0, int pos1) {
      long temp = data.get(pos0);
      data.set(pos0, data.get(pos1));
      data.set(pos1, temp);
   }

   public void copyElement(LongArray src, int srcPos, LongArray dst, int dstPos) {
      dst.set(dstPos, src.get(srcPos));
   }

   public void copyRange(LongArray src, int srcPos, LongArray dst, int dstPos, int length) {
      Platform.copyMemory(src.getBaseObject(), src.getBaseOffset() + (long)srcPos * 8L, dst.getBaseObject(), dst.getBaseOffset() + (long)dstPos * 8L, (long)length * 8L);
   }

   public LongArray allocate(int length) {
      if (!$assertionsDisabled && (long)length > this.buffer.size()) {
         long var10002 = this.buffer.size();
         throw new AssertionError("the buffer is smaller than required: " + var10002 + " < " + length);
      } else {
         return this.buffer;
      }
   }
}
