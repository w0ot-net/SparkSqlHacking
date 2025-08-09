package org.apache.spark.unsafe.array;

import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.memory.MemoryBlock;

public final class LongArray {
   private static final long WIDTH = 8L;
   private final MemoryBlock memory;
   private final Object baseObj;
   private final long baseOffset;
   private final long length;

   public LongArray(MemoryBlock memory) {
      assert memory.size() < 17179869176L : "Array size >= Integer.MAX_VALUE elements";

      this.memory = memory;
      this.baseObj = memory.getBaseObject();
      this.baseOffset = memory.getBaseOffset();
      this.length = memory.size() / 8L;
   }

   public MemoryBlock memoryBlock() {
      return this.memory;
   }

   public Object getBaseObject() {
      return this.baseObj;
   }

   public long getBaseOffset() {
      return this.baseOffset;
   }

   public long size() {
      return this.length;
   }

   public void zeroOut() {
      for(long off = this.baseOffset; off < this.baseOffset + this.length * 8L; off += 8L) {
         Platform.putLong(this.baseObj, off, 0L);
      }

   }

   public void set(int index, long value) {
      assert index >= 0 : "index (" + index + ") should >= 0";

      assert (long)index < this.length : "index (" + index + ") should < length (" + this.length + ")";

      Platform.putLong(this.baseObj, this.baseOffset + (long)index * 8L, value);
   }

   public long get(int index) {
      assert index >= 0 : "index (" + index + ") should >= 0";

      assert (long)index < this.length : "index (" + index + ") should < length (" + this.length + ")";

      return Platform.getLong(this.baseObj, this.baseOffset + (long)index * 8L);
   }
}
