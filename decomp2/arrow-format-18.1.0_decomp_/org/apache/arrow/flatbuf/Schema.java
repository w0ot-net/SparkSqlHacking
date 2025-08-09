package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Schema extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Schema getRootAsSchema(ByteBuffer _bb) {
      return getRootAsSchema(_bb, new Schema());
   }

   public static Schema getRootAsSchema(ByteBuffer _bb, Schema obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Schema __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short endianness() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public Field fields(int j) {
      return this.fields(new Field(), j);
   }

   public Field fields(Field obj, int j) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int fieldsLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Field.Vector fieldsVector() {
      return this.fieldsVector(new Field.Vector());
   }

   public Field.Vector fieldsVector(Field.Vector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public KeyValue customMetadata(int j) {
      return this.customMetadata(new KeyValue(), j);
   }

   public KeyValue customMetadata(KeyValue obj, int j) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int customMetadataLength() {
      int o = this.__offset(8);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue.Vector customMetadataVector() {
      return this.customMetadataVector(new KeyValue.Vector());
   }

   public KeyValue.Vector customMetadataVector(KeyValue.Vector obj) {
      int o = this.__offset(8);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public long features(int j) {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getLong(this.__vector(o) + j * 8) : 0L;
   }

   public int featuresLength() {
      int o = this.__offset(10);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public LongVector featuresVector() {
      return this.featuresVector(new LongVector());
   }

   public LongVector featuresVector(LongVector obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer featuresAsByteBuffer() {
      return this.__vector_as_bytebuffer(10, 8);
   }

   public ByteBuffer featuresInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 10, 8);
   }

   public static int createSchema(FlatBufferBuilder builder, short endianness, int fieldsOffset, int customMetadataOffset, int featuresOffset) {
      builder.startTable(4);
      addFeatures(builder, featuresOffset);
      addCustomMetadata(builder, customMetadataOffset);
      addFields(builder, fieldsOffset);
      addEndianness(builder, endianness);
      return endSchema(builder);
   }

   public static void startSchema(FlatBufferBuilder builder) {
      builder.startTable(4);
   }

   public static void addEndianness(FlatBufferBuilder builder, short endianness) {
      builder.addShort(0, endianness, 0);
   }

   public static void addFields(FlatBufferBuilder builder, int fieldsOffset) {
      builder.addOffset(1, fieldsOffset, 0);
   }

   public static int createFieldsVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startFieldsVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addCustomMetadata(FlatBufferBuilder builder, int customMetadataOffset) {
      builder.addOffset(2, customMetadataOffset, 0);
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

   public static void addFeatures(FlatBufferBuilder builder, int featuresOffset) {
      builder.addOffset(3, featuresOffset, 0);
   }

   public static int createFeaturesVector(FlatBufferBuilder builder, long[] data) {
      builder.startVector(8, data.length, 8);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addLong(data[i]);
      }

      return builder.endVector();
   }

   public static void startFeaturesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(8, numElems, 8);
   }

   public static int endSchema(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static void finishSchemaBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset);
   }

   public static void finishSizePrefixedSchemaBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset);
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Schema get(int j) {
         return this.get(new Schema(), j);
      }

      public Schema get(Schema obj, int j) {
         return obj.__assign(Schema.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
