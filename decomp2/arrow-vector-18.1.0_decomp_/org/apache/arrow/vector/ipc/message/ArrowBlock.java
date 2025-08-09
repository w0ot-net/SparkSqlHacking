package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import org.apache.arrow.flatbuf.Block;

public class ArrowBlock implements FBSerializable {
   private final long offset;
   private final int metadataLength;
   private final long bodyLength;

   public ArrowBlock(long offset, int metadataLength, long bodyLength) {
      this.offset = offset;
      this.metadataLength = metadataLength;
      this.bodyLength = bodyLength;
   }

   public long getOffset() {
      return this.offset;
   }

   public int getMetadataLength() {
      return this.metadataLength;
   }

   public long getBodyLength() {
      return this.bodyLength;
   }

   public int writeTo(FlatBufferBuilder builder) {
      return Block.createBlock(builder, this.offset, this.metadataLength, this.bodyLength);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (int)(this.bodyLength ^ this.bodyLength >>> 32);
      result = 31 * result + this.metadataLength;
      result = 31 * result + (int)(this.offset ^ this.offset >>> 32);
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
         ArrowBlock other = (ArrowBlock)obj;
         if (this.bodyLength != other.bodyLength) {
            return false;
         } else if (this.metadataLength != other.metadataLength) {
            return false;
         } else {
            return this.offset == other.offset;
         }
      }
   }
}
