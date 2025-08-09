package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class SparseTensorIndexCOO extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static SparseTensorIndexCOO getRootAsSparseTensorIndexCOO(ByteBuffer _bb) {
      return getRootAsSparseTensorIndexCOO(_bb, new SparseTensorIndexCOO());
   }

   public static SparseTensorIndexCOO getRootAsSparseTensorIndexCOO(ByteBuffer _bb, SparseTensorIndexCOO obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public SparseTensorIndexCOO __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public Int indicesType() {
      return this.indicesType(new Int());
   }

   public Int indicesType(Int obj) {
      int o = this.__offset(4);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public long indicesStrides(int j) {
      int o = this.__offset(6);
      return o != 0 ? this.bb.getLong(this.__vector(o) + j * 8) : 0L;
   }

   public int indicesStridesLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public LongVector indicesStridesVector() {
      return this.indicesStridesVector(new LongVector());
   }

   public LongVector indicesStridesVector(LongVector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer indicesStridesAsByteBuffer() {
      return this.__vector_as_bytebuffer(6, 8);
   }

   public ByteBuffer indicesStridesInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 6, 8);
   }

   public Buffer indicesBuffer() {
      return this.indicesBuffer(new Buffer());
   }

   public Buffer indicesBuffer(Buffer obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(o + this.bb_pos, this.bb) : null;
   }

   public boolean isCanonical() {
      int o = this.__offset(10);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public static void startSparseTensorIndexCOO(FlatBufferBuilder builder) {
      builder.startTable(4);
   }

   public static void addIndicesType(FlatBufferBuilder builder, int indicesTypeOffset) {
      builder.addOffset(0, indicesTypeOffset, 0);
   }

   public static void addIndicesStrides(FlatBufferBuilder builder, int indicesStridesOffset) {
      builder.addOffset(1, indicesStridesOffset, 0);
   }

   public static int createIndicesStridesVector(FlatBufferBuilder builder, long[] data) {
      builder.startVector(8, data.length, 8);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addLong(data[i]);
      }

      return builder.endVector();
   }

   public static void startIndicesStridesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(8, numElems, 8);
   }

   public static void addIndicesBuffer(FlatBufferBuilder builder, int indicesBufferOffset) {
      builder.addStruct(2, indicesBufferOffset, 0);
   }

   public static void addIsCanonical(FlatBufferBuilder builder, boolean isCanonical) {
      builder.addBoolean(3, isCanonical, false);
   }

   public static int endSparseTensorIndexCOO(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      builder.required(o, 8);
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public SparseTensorIndexCOO get(int j) {
         return this.get(new SparseTensorIndexCOO(), j);
      }

      public SparseTensorIndexCOO get(SparseTensorIndexCOO obj, int j) {
         return obj.__assign(SparseTensorIndexCOO.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
