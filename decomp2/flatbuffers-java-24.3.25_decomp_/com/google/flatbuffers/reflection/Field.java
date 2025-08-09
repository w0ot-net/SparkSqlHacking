package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

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

   public Type type() {
      return this.type(new Type());
   }

   public Type type(Type obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public int id() {
      int o = this.__offset(8);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) & '\uffff' : 0;
   }

   public int offset() {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) & '\uffff' : 0;
   }

   public long defaultInteger() {
      int o = this.__offset(12);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public double defaultReal() {
      int o = this.__offset(14);
      return o != 0 ? this.bb.getDouble(o + this.bb_pos) : (double)0.0F;
   }

   public boolean deprecated() {
      int o = this.__offset(16);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public boolean required() {
      int o = this.__offset(18);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public boolean key() {
      int o = this.__offset(20);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public KeyValue attributes(int j) {
      return this.attributes(new KeyValue(), j);
   }

   public KeyValue attributes(KeyValue obj, int j) {
      int o = this.__offset(22);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int attributesLength() {
      int o = this.__offset(22);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue attributesByKey(String key) {
      int o = this.__offset(22);
      return o != 0 ? KeyValue.__lookup_by_key((KeyValue)null, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue attributesByKey(KeyValue obj, String key) {
      int o = this.__offset(22);
      return o != 0 ? KeyValue.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue.Vector attributesVector() {
      return this.attributesVector(new KeyValue.Vector());
   }

   public KeyValue.Vector attributesVector(KeyValue.Vector obj) {
      int o = this.__offset(22);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public String documentation(int j) {
      int o = this.__offset(24);
      return o != 0 ? this.__string(this.__vector(o) + j * 4) : null;
   }

   public int documentationLength() {
      int o = this.__offset(24);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public StringVector documentationVector() {
      return this.documentationVector(new StringVector());
   }

   public StringVector documentationVector(StringVector obj) {
      int o = this.__offset(24);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public boolean optional() {
      int o = this.__offset(26);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public int padding() {
      int o = this.__offset(28);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) & '\uffff' : 0;
   }

   public boolean offset64() {
      int o = this.__offset(30);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public static int createField(FlatBufferBuilder builder, int nameOffset, int typeOffset, int id, int offset, long defaultInteger, double defaultReal, boolean deprecated, boolean required, boolean key, int attributesOffset, int documentationOffset, boolean optional, int padding, boolean offset64) {
      builder.startTable(14);
      addDefaultReal(builder, defaultReal);
      addDefaultInteger(builder, defaultInteger);
      addDocumentation(builder, documentationOffset);
      addAttributes(builder, attributesOffset);
      addType(builder, typeOffset);
      addName(builder, nameOffset);
      addPadding(builder, padding);
      addOffset(builder, offset);
      addId(builder, id);
      addOffset64(builder, offset64);
      addOptional(builder, optional);
      addKey(builder, key);
      addRequired(builder, required);
      addDeprecated(builder, deprecated);
      return endField(builder);
   }

   public static void startField(FlatBufferBuilder builder) {
      builder.startTable(14);
   }

   public static void addName(FlatBufferBuilder builder, int nameOffset) {
      builder.addOffset(nameOffset);
      builder.slot(0);
   }

   public static void addType(FlatBufferBuilder builder, int typeOffset) {
      builder.addOffset(1, typeOffset, 0);
   }

   public static void addId(FlatBufferBuilder builder, int id) {
      builder.addShort(2, (short)id, 0);
   }

   public static void addOffset(FlatBufferBuilder builder, int offset) {
      builder.addShort(3, (short)offset, 0);
   }

   public static void addDefaultInteger(FlatBufferBuilder builder, long defaultInteger) {
      builder.addLong(4, defaultInteger, 0L);
   }

   public static void addDefaultReal(FlatBufferBuilder builder, double defaultReal) {
      builder.addDouble(5, defaultReal, (double)0.0F);
   }

   public static void addDeprecated(FlatBufferBuilder builder, boolean deprecated) {
      builder.addBoolean(6, deprecated, false);
   }

   public static void addRequired(FlatBufferBuilder builder, boolean required) {
      builder.addBoolean(7, required, false);
   }

   public static void addKey(FlatBufferBuilder builder, boolean key) {
      builder.addBoolean(8, key, false);
   }

   public static void addAttributes(FlatBufferBuilder builder, int attributesOffset) {
      builder.addOffset(9, attributesOffset, 0);
   }

   public static int createAttributesVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startAttributesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addDocumentation(FlatBufferBuilder builder, int documentationOffset) {
      builder.addOffset(10, documentationOffset, 0);
   }

   public static int createDocumentationVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startDocumentationVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addOptional(FlatBufferBuilder builder, boolean optional) {
      builder.addBoolean(11, optional, false);
   }

   public static void addPadding(FlatBufferBuilder builder, int padding) {
      builder.addShort(12, (short)padding, 0);
   }

   public static void addOffset64(FlatBufferBuilder builder, boolean offset64) {
      builder.addBoolean(13, offset64, false);
   }

   public static int endField(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      builder.required(o, 6);
      return o;
   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
      return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb);
   }

   public static Field __lookup_by_key(Field obj, int vectorLocation, String key, ByteBuffer bb) {
      byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
      int span = bb.getInt(vectorLocation - 4);
      int start = 0;

      while(span != 0) {
         int middle = span / 2;
         int tableOffset = __indirect(vectorLocation + 4 * (start + middle), bb);
         int comp = compareStrings(__offset(4, bb.capacity() - tableOffset, bb), byteKey, bb);
         if (comp > 0) {
            span = middle;
         } else {
            if (comp >= 0) {
               return (obj == null ? new Field() : obj).__assign(tableOffset, bb);
            }

            ++middle;
            start += middle;
            span -= middle;
         }
      }

      return null;
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

      public Field getByKey(String key) {
         return Field.__lookup_by_key((Field)null, this.__vector(), key, this.bb);
      }

      public Field getByKey(Field obj, String key) {
         return Field.__lookup_by_key(obj, this.__vector(), key, this.bb);
      }
   }
}
