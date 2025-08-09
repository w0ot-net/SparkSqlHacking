package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class TensorDim extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static TensorDim getRootAsTensorDim(ByteBuffer _bb) {
      return getRootAsTensorDim(_bb, new TensorDim());
   }

   public static TensorDim getRootAsTensorDim(ByteBuffer _bb, TensorDim obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public TensorDim __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long size() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public String name() {
      int o = this.__offset(6);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer nameAsByteBuffer() {
      return this.__vector_as_bytebuffer(6, 1);
   }

   public ByteBuffer nameInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 6, 1);
   }

   public static int createTensorDim(FlatBufferBuilder builder, long size, int nameOffset) {
      builder.startTable(2);
      addSize(builder, size);
      addName(builder, nameOffset);
      return endTensorDim(builder);
   }

   public static void startTensorDim(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addSize(FlatBufferBuilder builder, long size) {
      builder.addLong(0, size, 0L);
   }

   public static void addName(FlatBufferBuilder builder, int nameOffset) {
      builder.addOffset(1, nameOffset, 0);
   }

   public static int endTensorDim(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public TensorDim get(int j) {
         return this.get(new TensorDim(), j);
      }

      public TensorDim get(TensorDim obj, int j) {
         return obj.__assign(TensorDim.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
