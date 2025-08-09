package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class Enum extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Enum getRootAsEnum(ByteBuffer _bb) {
      return getRootAsEnum(_bb, new Enum());
   }

   public static Enum getRootAsEnum(ByteBuffer _bb, Enum obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Enum __assign(int _i, ByteBuffer _bb) {
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

   public EnumVal values(int j) {
      return this.values(new EnumVal(), j);
   }

   public EnumVal values(EnumVal obj, int j) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int valuesLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public EnumVal valuesByKey(long key) {
      int o = this.__offset(6);
      return o != 0 ? EnumVal.__lookup_by_key((EnumVal)null, this.__vector(o), key, this.bb) : null;
   }

   public EnumVal valuesByKey(EnumVal obj, long key) {
      int o = this.__offset(6);
      return o != 0 ? EnumVal.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public EnumVal.Vector valuesVector() {
      return this.valuesVector(new EnumVal.Vector());
   }

   public EnumVal.Vector valuesVector(EnumVal.Vector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public boolean isUnion() {
      int o = this.__offset(8);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public Type underlyingType() {
      return this.underlyingType(new Type());
   }

   public Type underlyingType(Type obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public KeyValue attributes(int j) {
      return this.attributes(new KeyValue(), j);
   }

   public KeyValue attributes(KeyValue obj, int j) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int attributesLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue attributesByKey(String key) {
      int o = this.__offset(12);
      return o != 0 ? KeyValue.__lookup_by_key((KeyValue)null, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue attributesByKey(KeyValue obj, String key) {
      int o = this.__offset(12);
      return o != 0 ? KeyValue.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue.Vector attributesVector() {
      return this.attributesVector(new KeyValue.Vector());
   }

   public KeyValue.Vector attributesVector(KeyValue.Vector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public String documentation(int j) {
      int o = this.__offset(14);
      return o != 0 ? this.__string(this.__vector(o) + j * 4) : null;
   }

   public int documentationLength() {
      int o = this.__offset(14);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public StringVector documentationVector() {
      return this.documentationVector(new StringVector());
   }

   public StringVector documentationVector(StringVector obj) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public String declarationFile() {
      int o = this.__offset(16);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer declarationFileAsByteBuffer() {
      return this.__vector_as_bytebuffer(16, 1);
   }

   public ByteBuffer declarationFileInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 16, 1);
   }

   public static int createEnum(FlatBufferBuilder builder, int nameOffset, int valuesOffset, boolean isUnion, int underlyingTypeOffset, int attributesOffset, int documentationOffset, int declarationFileOffset) {
      builder.startTable(7);
      addDeclarationFile(builder, declarationFileOffset);
      addDocumentation(builder, documentationOffset);
      addAttributes(builder, attributesOffset);
      addUnderlyingType(builder, underlyingTypeOffset);
      addValues(builder, valuesOffset);
      addName(builder, nameOffset);
      addIsUnion(builder, isUnion);
      return endEnum(builder);
   }

   public static void startEnum(FlatBufferBuilder builder) {
      builder.startTable(7);
   }

   public static void addName(FlatBufferBuilder builder, int nameOffset) {
      builder.addOffset(nameOffset);
      builder.slot(0);
   }

   public static void addValues(FlatBufferBuilder builder, int valuesOffset) {
      builder.addOffset(1, valuesOffset, 0);
   }

   public static int createValuesVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startValuesVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static void addIsUnion(FlatBufferBuilder builder, boolean isUnion) {
      builder.addBoolean(2, isUnion, false);
   }

   public static void addUnderlyingType(FlatBufferBuilder builder, int underlyingTypeOffset) {
      builder.addOffset(3, underlyingTypeOffset, 0);
   }

   public static void addAttributes(FlatBufferBuilder builder, int attributesOffset) {
      builder.addOffset(4, attributesOffset, 0);
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
      builder.addOffset(5, documentationOffset, 0);
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

   public static void addDeclarationFile(FlatBufferBuilder builder, int declarationFileOffset) {
      builder.addOffset(6, declarationFileOffset, 0);
   }

   public static int endEnum(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      builder.required(o, 6);
      builder.required(o, 10);
      return o;
   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
      return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb);
   }

   public static Enum __lookup_by_key(Enum obj, int vectorLocation, String key, ByteBuffer bb) {
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
               return (obj == null ? new Enum() : obj).__assign(tableOffset, bb);
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

      public Enum get(int j) {
         return this.get(new Enum(), j);
      }

      public Enum get(Enum obj, int j) {
         return obj.__assign(Enum.__indirect(this.__element(j), this.bb), this.bb);
      }

      public Enum getByKey(String key) {
         return Enum.__lookup_by_key((Enum)null, this.__vector(), key, this.bb);
      }

      public Enum getByKey(Enum obj, String key) {
         return Enum.__lookup_by_key(obj, this.__vector(), key, this.bb);
      }
   }
}
