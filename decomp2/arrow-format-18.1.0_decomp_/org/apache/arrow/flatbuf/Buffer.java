package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Struct;
import java.nio.ByteBuffer;

public final class Buffer extends Struct {
   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Buffer __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long offset() {
      return this.bb.getLong(this.bb_pos + 0);
   }

   public long length() {
      return this.bb.getLong(this.bb_pos + 8);
   }

   public static int createBuffer(FlatBufferBuilder builder, long offset, long length) {
      builder.prep(8, 16);
      builder.putLong(length);
      builder.putLong(offset);
      return builder.offset();
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Buffer get(int j) {
         return this.get(new Buffer(), j);
      }

      public Buffer get(Buffer obj, int j) {
         return obj.__assign(this.__element(j), this.bb);
      }
   }
}
