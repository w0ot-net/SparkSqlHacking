package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class SparseTensor extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static SparseTensor getRootAsSparseTensor(ByteBuffer _bb) {
      return getRootAsSparseTensor(_bb, new SparseTensor());
   }

   public static SparseTensor getRootAsSparseTensor(ByteBuffer _bb, SparseTensor obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public SparseTensor __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public byte typeType() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public Table type(Table obj) {
      int o = this.__offset(6);
      return o != 0 ? this.__union(obj, o + this.bb_pos) : null;
   }

   public TensorDim shape(int j) {
      return this.shape(new TensorDim(), j);
   }

   public TensorDim shape(TensorDim obj, int j) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int shapeLength() {
      int o = this.__offset(8);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public TensorDim.Vector shapeVector() {
      return this.shapeVector(new TensorDim.Vector());
   }

   public TensorDim.Vector shapeVector(TensorDim.Vector obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public long nonZeroLength() {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public byte sparseIndexType() {
      int o = this.__offset(12);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public Table sparseIndex(Table obj) {
      int o = this.__offset(14);
      return o != 0 ? this.__union(obj, o + this.bb_pos) : null;
   }

   public Buffer data() {
      return this.data(new Buffer());
   }

   public Buffer data(Buffer obj) {
      int o = this.__offset(16);
      return o != 0 ? obj.__assign(o + this.bb_pos, this.bb) : null;
   }

   public static void startSparseTensor(FlatBufferBuilder builder) {
      builder.startTable(7);
   }

   public static void addTypeType(FlatBufferBuilder builder, byte typeType) {
      builder.addByte(0, typeType, 0);
   }

   public static void addType(FlatBufferBuilder builder, int typeOffset) {
      builder.addOffset(1, typeOffset, 0);
   }

   public static void addShape(FlatBufferBuilder builder, int shapeOffset) {
      builder.addOffset(2, shapeOffset, 0);
   }

   public static int createShapeVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startShapeVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addNonZeroLength(FlatBufferBuilder builder, long nonZeroLength) {
      builder.addLong(3, nonZeroLength, 0L);
   }

   public static void addSparseIndexType(FlatBufferBuilder builder, byte sparseIndexType) {
      builder.addByte(4, sparseIndexType, 0);
   }

   public static void addSparseIndex(FlatBufferBuilder builder, int sparseIndexOffset) {
      builder.addOffset(5, sparseIndexOffset, 0);
   }

   public static void addData(FlatBufferBuilder builder, int dataOffset) {
      builder.addStruct(6, dataOffset, 0);
   }

   public static int endSparseTensor(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 6);
      builder.required(o, 8);
      builder.required(o, 14);
      builder.required(o, 16);
      return o;
   }

   public static void finishSparseTensorBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset);
   }

   public static void finishSizePrefixedSparseTensorBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset);
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public SparseTensor get(int j) {
         return this.get(new SparseTensor(), j);
      }

      public SparseTensor get(SparseTensor obj, int j) {
         return obj.__assign(SparseTensor.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
