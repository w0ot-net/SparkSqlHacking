package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import org.apache.arrow.flatbuf.Buffer;

public class ArrowBuffer implements FBSerializable {
   private long offset;
   private long size;

   public ArrowBuffer(long offset, long size) {
      this.offset = offset;
      this.size = size;
   }

   public long getOffset() {
      return this.offset;
   }

   public long getSize() {
      return this.size;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (int)(this.offset ^ this.offset >>> 32);
      result = 31 * result + (int)(this.size ^ this.size >>> 32);
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ArrowBuffer other = (ArrowBuffer)obj;
         if (this.offset != other.offset) {
            return false;
         } else {
            return this.size == other.size;
         }
      }
   }

   public int writeTo(FlatBufferBuilder builder) {
      return Buffer.createBuffer(builder, this.offset, this.size);
   }

   public String toString() {
      return "ArrowBuffer [offset=" + this.offset + ", size=" + this.size + "]";
   }
}
