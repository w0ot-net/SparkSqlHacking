package org.apache.orc.impl;

import java.nio.ByteBuffer;
import org.apache.hadoop.hive.common.io.DiskRange;
import org.apache.hadoop.hive.common.io.DiskRangeList;

public class BufferChunk extends DiskRangeList {
   private ByteBuffer chunk;

   public BufferChunk(long offset, int length) {
      super(offset, offset + (long)length);
      this.chunk = null;
   }

   public BufferChunk(ByteBuffer chunk, long offset) {
      super(offset, offset + (long)chunk.remaining());
      this.chunk = chunk;
   }

   public void setChunk(ByteBuffer chunk) {
      this.chunk = chunk;
   }

   public boolean hasData() {
      return this.chunk != null;
   }

   public final String toString() {
      if (this.chunk == null) {
         return "data range[" + this.offset + ", " + this.end + ")";
      } else {
         boolean makesSense = (long)this.chunk.remaining() == this.end - this.offset;
         long var10000 = this.offset;
         return "data range [" + var10000 + ", " + this.end + "), size: " + this.chunk.remaining() + (makesSense ? "" : "(!)") + " type: " + (this.chunk.isDirect() ? "direct" : "array-backed");
      }
   }

   public DiskRange sliceAndShift(long offset, long end, long shiftBy) {
      assert offset <= end && offset >= this.offset && end <= this.end;

      assert offset + shiftBy >= 0L;

      ByteBuffer sliceBuf = this.chunk.slice();
      int newPos = (int)(offset - this.offset);
      int newLimit = newPos + (int)(end - offset);

      try {
         sliceBuf.position(newPos);
         sliceBuf.limit(newLimit);
      } catch (Throwable t) {
         long var10002 = this.offset;
         throw new RuntimeException("Failed to slice buffer chunk with range [" + var10002 + ", " + this.end + "), position: " + this.chunk.position() + " limit: " + this.chunk.limit() + ", " + (this.chunk.isDirect() ? "direct" : "array") + "; to [" + offset + ", " + end + ") " + String.valueOf(t.getClass()), t);
      }

      return new BufferChunk(sliceBuf, offset + shiftBy);
   }

   public boolean equals(Object other) {
      if (other != null && other.getClass() == this.getClass()) {
         BufferChunk ob = (BufferChunk)other;
         return this.chunk.equals(ob.chunk);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.chunk.hashCode();
   }

   public ByteBuffer getData() {
      return this.chunk;
   }
}
