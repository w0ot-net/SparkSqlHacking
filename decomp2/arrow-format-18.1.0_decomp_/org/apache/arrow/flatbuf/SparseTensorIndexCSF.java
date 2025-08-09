package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class SparseTensorIndexCSF extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static SparseTensorIndexCSF getRootAsSparseTensorIndexCSF(ByteBuffer _bb) {
      return getRootAsSparseTensorIndexCSF(_bb, new SparseTensorIndexCSF());
   }

   public static SparseTensorIndexCSF getRootAsSparseTensorIndexCSF(ByteBuffer _bb, SparseTensorIndexCSF obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public SparseTensorIndexCSF __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public Int indptrType() {
      return this.indptrType(new Int());
   }

   public Int indptrType(Int obj) {
      int o = this.__offset(4);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Buffer indptrBuffers(int j) {
      return this.indptrBuffers(new Buffer(), j);
   }

   public Buffer indptrBuffers(Buffer obj, int j) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 16, this.bb) : null;
   }

   public int indptrBuffersLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Buffer.Vector indptrBuffersVector() {
      return this.indptrBuffersVector(new Buffer.Vector());
   }

   public Buffer.Vector indptrBuffersVector(Buffer.Vector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 16, this.bb) : null;
   }

   public Int indicesType() {
      return this.indicesType(new Int());
   }

   public Int indicesType(Int obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Buffer indicesBuffers(int j) {
      return this.indicesBuffers(new Buffer(), j);
   }

   public Buffer indicesBuffers(Buffer obj, int j) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 16, this.bb) : null;
   }

   public int indicesBuffersLength() {
      int o = this.__offset(10);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Buffer.Vector indicesBuffersVector() {
      return this.indicesBuffersVector(new Buffer.Vector());
   }

   public Buffer.Vector indicesBuffersVector(Buffer.Vector obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o), 16, this.bb) : null;
   }

   public int axisOrder(int j) {
      int o = this.__offset(12);
      return o != 0 ? this.bb.getInt(this.__vector(o) + j * 4) : 0;
   }

   public int axisOrderLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public IntVector axisOrderVector() {
      return this.axisOrderVector(new IntVector());
   }

   public IntVector axisOrderVector(IntVector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer axisOrderAsByteBuffer() {
      return this.__vector_as_bytebuffer(12, 4);
   }

   public ByteBuffer axisOrderInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 12, 4);
   }

   public static int createSparseTensorIndexCSF(FlatBufferBuilder builder, int indptrTypeOffset, int indptrBuffersOffset, int indicesTypeOffset, int indicesBuffersOffset, int axisOrderOffset) {
      builder.startTable(5);
      addAxisOrder(builder, axisOrderOffset);
      addIndicesBuffers(builder, indicesBuffersOffset);
      addIndicesType(builder, indicesTypeOffset);
      addIndptrBuffers(builder, indptrBuffersOffset);
      addIndptrType(builder, indptrTypeOffset);
      return endSparseTensorIndexCSF(builder);
   }

   public static void startSparseTensorIndexCSF(FlatBufferBuilder builder) {
      builder.startTable(5);
   }

   public static void addIndptrType(FlatBufferBuilder builder, int indptrTypeOffset) {
      builder.addOffset(0, indptrTypeOffset, 0);
   }

   public static void addIndptrBuffers(FlatBufferBuilder builder, int indptrBuffersOffset) {
      builder.addOffset(1, indptrBuffersOffset, 0);
   }

   public static void startIndptrBuffersVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(16, numElems, 8);
   }

   public static void addIndicesType(FlatBufferBuilder builder, int indicesTypeOffset) {
      builder.addOffset(2, indicesTypeOffset, 0);
   }

   public static void addIndicesBuffers(FlatBufferBuilder builder, int indicesBuffersOffset) {
      builder.addOffset(3, indicesBuffersOffset, 0);
   }

   public static void startIndicesBuffersVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(16, numElems, 8);
   }

   public static void addAxisOrder(FlatBufferBuilder builder, int axisOrderOffset) {
      builder.addOffset(4, axisOrderOffset, 0);
   }

   public static int createAxisOrderVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addInt(data[i]);
      }

      return builder.endVector();
   }

   public static void startAxisOrderVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endSparseTensorIndexCSF(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
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

      public SparseTensorIndexCSF get(int j) {
         return this.get(new SparseTensorIndexCSF(), j);
      }

      public SparseTensorIndexCSF get(SparseTensorIndexCSF obj, int j) {
         return obj.__assign(SparseTensorIndexCSF.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
