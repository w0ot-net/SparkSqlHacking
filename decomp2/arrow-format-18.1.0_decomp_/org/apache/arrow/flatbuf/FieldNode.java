package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Struct;
import java.nio.ByteBuffer;

public final class FieldNode extends Struct {
   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public FieldNode __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long length() {
      return this.bb.getLong(this.bb_pos + 0);
   }

   public long nullCount() {
      return this.bb.getLong(this.bb_pos + 8);
   }

   public static int createFieldNode(FlatBufferBuilder builder, long length, long nullCount) {
      builder.prep(8, 16);
      builder.putLong(nullCount);
      builder.putLong(length);
      return builder.offset();
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public FieldNode get(int j) {
         return this.get(new FieldNode(), j);
      }

      public FieldNode get(FieldNode obj, int j) {
         return obj.__assign(this.__element(j), this.bb);
      }
   }
}
