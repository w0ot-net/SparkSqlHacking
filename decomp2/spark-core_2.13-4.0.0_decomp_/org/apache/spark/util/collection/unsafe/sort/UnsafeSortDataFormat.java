package org.apache.spark.util.collection.unsafe.sort;

import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.util.collection.SortDataFormat;

public final class UnsafeSortDataFormat extends SortDataFormat {
   private final LongArray buffer;
   // $FF: synthetic field
   static final boolean $assertionsDisabled = !UnsafeSortDataFormat.class.desiredAssertionStatus();

   public UnsafeSortDataFormat(LongArray buffer) {
      this.buffer = buffer;
   }

   public RecordPointerAndKeyPrefix getKey(LongArray data, int pos) {
      throw new UnsupportedOperationException();
   }

   public RecordPointerAndKeyPrefix newKey() {
      return new RecordPointerAndKeyPrefix();
   }

   public RecordPointerAndKeyPrefix getKey(LongArray data, int pos, RecordPointerAndKeyPrefix reuse) {
      reuse.recordPointer = data.get(pos * 2);
      reuse.keyPrefix = data.get(pos * 2 + 1);
      return reuse;
   }

   public void swap(LongArray data, int pos0, int pos1) {
      long tempPointer = data.get(pos0 * 2);
      long tempKeyPrefix = data.get(pos0 * 2 + 1);
      data.set(pos0 * 2, data.get(pos1 * 2));
      data.set(pos0 * 2 + 1, data.get(pos1 * 2 + 1));
      data.set(pos1 * 2, tempPointer);
      data.set(pos1 * 2 + 1, tempKeyPrefix);
   }

   public void copyElement(LongArray src, int srcPos, LongArray dst, int dstPos) {
      dst.set(dstPos * 2, src.get(srcPos * 2));
      dst.set(dstPos * 2 + 1, src.get(srcPos * 2 + 1));
   }

   public void copyRange(LongArray src, int srcPos, LongArray dst, int dstPos, int length) {
      Platform.copyMemory(src.getBaseObject(), src.getBaseOffset() + (long)srcPos * 16L, dst.getBaseObject(), dst.getBaseOffset() + (long)dstPos * 16L, (long)length * 16L);
   }

   public LongArray allocate(int length) {
      if (!$assertionsDisabled && (long)length * 2L > this.buffer.size()) {
         long var10002 = this.buffer.size();
         throw new AssertionError("the buffer is smaller than required: " + var10002 + " < " + length * 2);
      } else {
         return this.buffer;
      }
   }
}
