package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Field extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Field getRootAsField(ByteBuffer _bb) {
      return getRootAsField(_bb, new Field());
   }

   public static Field getRootAsField(ByteBuffer _bb, Field obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Field __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public String name() {
      int o = this.__offset(4);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer nameAsByteBuffer() {
      return this.__vector_as_bytebuffer(4, 1);
   }

   public ByteBuffer nameInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 4, 1);
   }

   public boolean nullable() {
      int o = this.__offset(6);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public byte typeType() {
      int o = this.__offset(8);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public Table type(Table obj) {
      int o = this.__offset(10);
      return o != 0 ? this.__union(obj, o + this.bb_pos) : null;
   }

   public DictionaryEncoding dictionary() {
      return this.dictionary(new DictionaryEncoding());
   }

   public DictionaryEncoding dictionary(DictionaryEncoding obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public Field children(int j) {
      return this.children(new Field(), j);
   }

   public Field children(Field obj, int j) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int childrenLength() {
      int o = this.__offset(14);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public Vector childrenVector() {
      return this.childrenVector(new Vector());
   }

   public Vector childrenVector(Vector obj) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public KeyValue customMetadata(int j) {
      return this.customMetadata(new KeyValue(), j);
   }

   public KeyValue customMetadata(KeyValue obj, int j) {
      int o = this.__offset(16);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int customMetadataLength() {
      int o = this.__offset(16);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue.Vector customMetadataVector() {
      return this.customMetadataVector(new KeyValue.Vector());
   }

   public KeyValue.Vector customMetadataVector(KeyValue.Vector obj) {
      int o = this.__offset(16);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createField(FlatBufferBuilder builder, int nameOffset, boolean nullable, byte typeType, int typeOffset, int dictionaryOffset, int childrenOffset, int customMetadataOffset) {
      builder.startTable(7);
      addCustomMetadata(builder, customMetadataOffset);
      addChildren(builder, childrenOffset);
      addDictionary(builder, dictionaryOffset);
      addType(builder, typeOffset);
      addName(builder, nameOffset);
      addTypeType(builder, typeType);
      addNullable(builder, nullable);
      return endField(builder);
   }

   public static void startField(FlatBufferBuilder builder) {
      builder.startTable(7);
   }

   public static void addName(FlatBufferBuilder builder, int nameOffset) {
      builder.addOffset(0, nameOffset, 0);
   }

   public static void addNullable(FlatBufferBuilder builder, boolean nullable) {
      builder.addBoolean(1, nullable, false);
   }

   public static void addTypeType(FlatBufferBuilder builder, byte typeType) {
      builder.addByte(2, typeType, 0);
   }

   public static void addType(FlatBufferBuilder builder, int typeOffset) {
      builder.addOffset(3, typeOffset, 0);
   }

   public static void addDictionary(FlatBufferBuilder builder, int dictionaryOffset) {
      builder.addOffset(4, dictionaryOffset, 0);
   }

   public static void addChildren(FlatBufferBuilder builder, int childrenOffset) {
      builder.addOffset(5, childrenOffset, 0);
   }

   public static int createChildrenVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startChildrenVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addCustomMetadata(FlatBufferBuilder builder, int customMetadataOffset) {
      builder.addOffset(6, customMetadataOffset, 0);
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

   public static int endField(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Field get(int j) {
         return this.get(new Field(), j);
      }

      public Field get(Field obj, int j) {
         return obj.__assign(Field.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
