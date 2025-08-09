package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class SparseMatrixIndexCSX extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static SparseMatrixIndexCSX getRootAsSparseMatrixIndexCSX(ByteBuffer _bb) {
      return getRootAsSparseMatrixIndexCSX(_bb, new SparseMatrixIndexCSX());
   }

   public static SparseMatrixIndexCSX getRootAsSparseMatrixIndexCSX(ByteBuffer _bb, SparseMatrixIndexCSX obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public SparseMatrixIndexCSX __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short compressedAxis() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public Int indptrType() {
      return this.indptrType(new Int());
   }

   public Int indptrType(Int obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Buffer indptrBuffer() {
      return this.indptrBuffer(new Buffer());
   }

   public Buffer indptrBuffer(Buffer obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(o + this.bb_pos, this.bb) : null;
   }

   public Int indicesType() {
      return this.indicesType(new Int());
   }

   public Int indicesType(Int obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Buffer indicesBuffer() {
      return this.indicesBuffer(new Buffer());
   }

   public Buffer indicesBuffer(Buffer obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(o + this.bb_pos, this.bb) : null;
   }

   public static void startSparseMatrixIndexCSX(FlatBufferBuilder builder) {
      builder.startTable(5);
   }

   public static void addCompressedAxis(FlatBufferBuilder builder, short compressedAxis) {
      builder.addShort(0, compressedAxis, 0);
   }

   public static void addIndptrType(FlatBufferBuilder builder, int indptrTypeOffset) {
      builder.addOffset(1, indptrTypeOffset, 0);
   }

   public static void addIndptrBuffer(FlatBufferBuilder builder, int indptrBufferOffset) {
      builder.addStruct(2, indptrBufferOffset, 0);
   }

   public static void addIndicesType(FlatBufferBuilder builder, int indicesTypeOffset) {
      builder.addOffset(3, indicesTypeOffset, 0);
   }

   public static void addIndicesBuffer(FlatBufferBuilder builder, int indicesBufferOffset) {
      builder.addStruct(4, indicesBufferOffset, 0);
   }

   public static int endSparseMatrixIndexCSX(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 6);
      builder.required(o, 8);
      builder.required(o, 10);
      builder.required(o, 12);
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public SparseMatrixIndexCSX get(int j) {
         return this.get(new SparseMatrixIndexCSX(), j);
      }

      public SparseMatrixIndexCSX get(SparseMatrixIndexCSX obj, int j) {
         return obj.__assign(SparseMatrixIndexCSX.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
