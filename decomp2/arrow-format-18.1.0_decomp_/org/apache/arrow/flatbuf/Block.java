package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Struct;
import java.nio.ByteBuffer;

public final class Block extends Struct {
   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Block __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long offset() {
      return this.bb.getLong(this.bb_pos + 0);
   }

   public int metaDataLength() {
      return this.bb.getInt(this.bb_pos + 8);
   }

   public long bodyLength() {
      return this.bb.getLong(this.bb_pos + 16);
   }

   public static int createBlock(FlatBufferBuilder builder, long offset, int metaDataLength, long bodyLength) {
      builder.prep(8, 24);
      builder.putLong(bodyLength);
      builder.pad(4);
      builder.putInt(metaDataLength);
      builder.putLong(offset);
      return builder.offset();
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Block get(int j) {
         return this.get(new Block(), j);
      }

      public Block get(Block obj, int j) {
         return obj.__assign(this.__element(j), this.bb);
      }
   }
}
