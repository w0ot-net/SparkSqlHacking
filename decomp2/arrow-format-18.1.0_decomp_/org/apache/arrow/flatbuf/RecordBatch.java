package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class RecordBatch extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static RecordBatch getRootAsRecordBatch(ByteBuffer _bb) {
      return getRootAsRecordBatch(_bb, new RecordBatch());
   }

   public static RecordBatch getRootAsRecordBatch(ByteBuffer _bb, RecordBatch obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public RecordBatch __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long length() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public FieldNode nodes(int j) {
      return this.nodes(new FieldNode(), j);
   }

   public FieldNode nodes(FieldNode obj, int j) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 16, this.bb) : null;
   }

   public int nodesLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public FieldNode.Vector nodesVector() {
      return this.nodesVector(new FieldNode.Vector());
   }

   public FieldNode.Vector nodesVector(FieldNode.Vector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 16, this.bb) : null;
   }

   public Buffer buffers(int j) {
      return this.buffers(new Buffer(), j);
   }

   public Buffer buffers(Buffer obj, int j) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 16, this.bb) : null;
   }

   public int buffersLength() {
      int o = this.__offset(8);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Buffer.Vector buffersVector() {
      return this.buffersVector(new Buffer.Vector());
   }

   public Buffer.Vector buffersVector(Buffer.Vector obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o), 16, this.bb) : null;
   }

   public BodyCompression compression() {
      return this.compression(new BodyCompression());
   }

   public BodyCompression compression(BodyCompression obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public long variadicBufferCounts(int j) {
      int o = this.__offset(12);
      return o != 0 ? this.bb.getLong(this.__vector(o) + j * 8) : 0L;
   }

   public int variadicBufferCountsLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public LongVector variadicBufferCountsVector() {
      return this.variadicBufferCountsVector(new LongVector());
   }

   public LongVector variadicBufferCountsVector(LongVector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer variadicBufferCountsAsByteBuffer() {
      return this.__vector_as_bytebuffer(12, 8);
   }

   public ByteBuffer variadicBufferCountsInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 12, 8);
   }

   public static int createRecordBatch(FlatBufferBuilder builder, long length, int nodesOffset, int buffersOffset, int compressionOffset, int variadicBufferCountsOffset) {
      builder.startTable(5);
      addLength(builder, length);
      addVariadicBufferCounts(builder, variadicBufferCountsOffset);
      addCompression(builder, compressionOffset);
      addBuffers(builder, buffersOffset);
      addNodes(builder, nodesOffset);
      return endRecordBatch(builder);
   }

   public static void startRecordBatch(FlatBufferBuilder builder) {
      builder.startTable(5);
   }

   public static void addLength(FlatBufferBuilder builder, long length) {
      builder.addLong(0, length, 0L);
   }

   public static void addNodes(FlatBufferBuilder builder, int nodesOffset) {
      builder.addOffset(1, nodesOffset, 0);
   }

   public static void startNodesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(16, numElems, 8);
   }

   public static void addBuffers(FlatBufferBuilder builder, int buffersOffset) {
      builder.addOffset(2, buffersOffset, 0);
   }

   public static void startBuffersVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(16, numElems, 8);
   }

   public static void addCompression(FlatBufferBuilder builder, int compressionOffset) {
      builder.addOffset(3, compressionOffset, 0);
   }

   public static void addVariadicBufferCounts(FlatBufferBuilder builder, int variadicBufferCountsOffset) {
      builder.addOffset(4, variadicBufferCountsOffset, 0);
   }

   public static int createVariadicBufferCountsVector(FlatBufferBuilder builder, long[] data) {
      builder.startVector(8, data.length, 8);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addLong(data[i]);
      }

      return builder.endVector();
   }

   public static void startVariadicBufferCountsVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(8, numElems, 8);
   }

   public static int endRecordBatch(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public RecordBatch get(int j) {
         return this.get(new RecordBatch(), j);
      }

      public RecordBatch get(RecordBatch obj, int j) {
         return obj.__assign(RecordBatch.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
