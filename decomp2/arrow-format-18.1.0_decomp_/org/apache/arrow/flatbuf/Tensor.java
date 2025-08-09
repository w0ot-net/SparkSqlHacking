package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Tensor extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Tensor getRootAsTensor(ByteBuffer _bb) {
      return getRootAsTensor(_bb, new Tensor());
   }

   public static Tensor getRootAsTensor(ByteBuffer _bb, Tensor obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Tensor __assign(int _i, ByteBuffer _bb) {
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

   public long strides(int j) {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getLong(this.__vector(o) + j * 8) : 0L;
   }

   public int stridesLength() {
      int o = this.__offset(10);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public LongVector stridesVector() {
      return this.stridesVector(new LongVector());
   }

   public LongVector stridesVector(LongVector obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer stridesAsByteBuffer() {
      return this.__vector_as_bytebuffer(10, 8);
   }

   public ByteBuffer stridesInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 10, 8);
   }

   public Buffer data() {
      return this.data(new Buffer());
   }

   public Buffer data(Buffer obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(o + this.bb_pos, this.bb) : null;
   }

   public static void startTensor(FlatBufferBuilder builder) {
      builder.startTable(5);
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

   public static void addStrides(FlatBufferBuilder builder, int stridesOffset) {
      builder.addOffset(3, stridesOffset, 0);
   }

   public static int createStridesVector(FlatBufferBuilder builder, long[] data) {
      builder.startVector(8, data.length, 8);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addLong(data[i]);
      }

      return builder.endVector();
   }

   public static void startStridesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(8, numElems, 8);
   }

   public static void addData(FlatBufferBuilder builder, int dataOffset) {
      builder.addStruct(4, dataOffset, 0);
   }

   public static int endTensor(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 6);
      builder.required(o, 8);
      builder.required(o, 12);
      return o;
   }

   public static void finishTensorBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset);
   }

   public static void finishSizePrefixedTensorBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset);
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Tensor get(int j) {
         return this.get(new Tensor(), j);
      }

      public Tensor get(Tensor obj, int j) {
         return obj.__assign(Tensor.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
