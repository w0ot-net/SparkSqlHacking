package org.apache.hadoop.hive.common.io;

import java.nio.ByteBuffer;

public class DiskRange {
   protected long offset;
   protected long end;

   public DiskRange(long offset, long end) {
      this.offset = offset;
      this.end = end;
      if (end < offset) {
         throw new IllegalArgumentException("invalid range " + this);
      }
   }

   public boolean equals(Object other) {
      return other != null && other.getClass() == this.getClass() ? this.equalRange((DiskRange)other) : false;
   }

   public boolean equalRange(DiskRange other) {
      return other.offset == this.offset && other.end == this.end;
   }

   public int hashCode() {
      return (int)(this.offset ^ this.offset >>> 32) * 31 + (int)(this.end ^ this.end >>> 32);
   }

   public String toString() {
      return "range start: " + this.offset + " end: " + this.end;
   }

   public long getOffset() {
      return this.offset;
   }

   public long getEnd() {
      return this.end;
   }

   public int getLength() {
      long len = this.end - this.offset;

      assert len <= 2147483647L;

      return (int)len;
   }

   public boolean hasData() {
      return false;
   }

   public DiskRange sliceAndShift(long offset, long end, long shiftBy) {
      throw new UnsupportedOperationException();
   }

   public ByteBuffer getData() {
      throw new UnsupportedOperationException();
   }

   protected boolean merge(long otherOffset, long otherEnd) {
      if (!overlap(this.offset, this.end, otherOffset, otherEnd)) {
         return false;
      } else {
         this.offset = Math.min(this.offset, otherOffset);
         this.end = Math.max(this.end, otherEnd);
         return true;
      }
   }

   private static boolean overlap(long leftA, long rightA, long leftB, long rightB) {
      if (leftA <= leftB) {
         return rightA >= leftB;
      } else {
         return rightB >= leftA;
      }
   }
}
