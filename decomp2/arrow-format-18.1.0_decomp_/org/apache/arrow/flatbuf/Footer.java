package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Footer extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Footer getRootAsFooter(ByteBuffer _bb) {
      return getRootAsFooter(_bb, new Footer());
   }

   public static Footer getRootAsFooter(ByteBuffer _bb, Footer obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Footer __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short version() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public Schema schema() {
      return this.schema(new Schema());
   }

   public Schema schema(Schema obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Block dictionaries(int j) {
      return this.dictionaries(new Block(), j);
   }

   public Block dictionaries(Block obj, int j) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 24, this.bb) : null;
   }

   public int dictionariesLength() {
      int o = this.__offset(8);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Block.Vector dictionariesVector() {
      return this.dictionariesVector(new Block.Vector());
   }

   public Block.Vector dictionariesVector(Block.Vector obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o), 24, this.bb) : null;
   }

   public Block recordBatches(int j) {
      return this.recordBatches(new Block(), j);
   }

   public Block recordBatches(Block obj, int j) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o) + j * 24, this.bb) : null;
   }

   public int recordBatchesLength() {
      int o = this.__offset(10);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Block.Vector recordBatchesVector() {
      return this.recordBatchesVector(new Block.Vector());
   }

   public Block.Vector recordBatchesVector(Block.Vector obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o), 24, this.bb) : null;
   }

   public KeyValue customMetadata(int j) {
      return this.customMetadata(new KeyValue(), j);
   }

   public KeyValue customMetadata(KeyValue obj, int j) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int customMetadataLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue.Vector customMetadataVector() {
      return this.customMetadataVector(new KeyValue.Vector());
   }

   public KeyValue.Vector customMetadataVector(KeyValue.Vector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createFooter(FlatBufferBuilder builder, short version, int schemaOffset, int dictionariesOffset, int recordBatchesOffset, int customMetadataOffset) {
      builder.startTable(5);
      addCustomMetadata(builder, customMetadataOffset);
      addRecordBatches(builder, recordBatchesOffset);
      addDictionaries(builder, dictionariesOffset);
      addSchema(builder, schemaOffset);
      addVersion(builder, version);
      return endFooter(builder);
   }

   public static void startFooter(FlatBufferBuilder builder) {
      builder.startTable(5);
   }

   public static void addVersion(FlatBufferBuilder builder, short version) {
      builder.addShort(0, version, 0);
   }

   public static void addSchema(FlatBufferBuilder builder, int schemaOffset) {
      builder.addOffset(1, schemaOffset, 0);
   }

   public static void addDictionaries(FlatBufferBuilder builder, int dictionariesOffset) {
      builder.addOffset(2, dictionariesOffset, 0);
   }

   public static void startDictionariesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(24, numElems, 8);
   }

   public static void addRecordBatches(FlatBufferBuilder builder, int recordBatchesOffset) {
      builder.addOffset(3, recordBatchesOffset, 0);
   }

   public static void startRecordBatchesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(24, numElems, 8);
   }

   public static void addCustomMetadata(FlatBufferBuilder builder, int customMetadataOffset) {
      builder.addOffset(4, customMetadataOffset, 0);
   }

   public static int createCustomMetadataVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startCustomMetadataVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endFooter(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static void finishFooterBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset);
   }

   public static void finishSizePrefixedFooterBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset);
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Footer get(int j) {
         return this.get(new Footer(), j);
      }

      public Footer get(Footer obj, int j) {
         return obj.__assign(Footer.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
