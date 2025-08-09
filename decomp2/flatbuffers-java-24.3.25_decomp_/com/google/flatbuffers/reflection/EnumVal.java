package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class EnumVal extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static EnumVal getRootAsEnumVal(ByteBuffer _bb) {
      return getRootAsEnumVal(_bb, new EnumVal());
   }

   public static EnumVal getRootAsEnumVal(ByteBuffer _bb, EnumVal obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public EnumVal __assign(int _i, ByteBuffer _bb) {
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

   public long value() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public Type unionType() {
      return this.unionType(new Type());
   }

   public Type unionType(Type obj) {
      int o = this.__offset(10);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public String documentation(int j) {
      int o = this.__offset(12);
      return o != 0 ? this.__string(this.__vector(o) + j * 4) : null;
   }

   public int documentationLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public StringVector documentationVector() {
      return this.documentationVector(new StringVector());
   }

   public StringVector documentationVector(StringVector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public KeyValue attributes(int j) {
      return this.attributes(new KeyValue(), j);
   }

   public KeyValue attributes(KeyValue obj, int j) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int attributesLength() {
      int o = this.__offset(14);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue attributesByKey(String key) {
      int o = this.__offset(14);
      return o != 0 ? KeyValue.__lookup_by_key((KeyValue)null, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue attributesByKey(KeyValue obj, String key) {
      int o = this.__offset(14);
      return o != 0 ? KeyValue.__lookup_by_key(obj, this.__vector(o), key, this.bb) : null;
   }

   public KeyValue.Vector attributesVector() {
      return this.attributesVector(new KeyValue.Vector());
   }

   public KeyValue.Vector attributesVector(KeyValue.Vector obj) {
      int o = this.__offset(14);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createEnumVal(FlatBufferBuilder builder, int nameOffset, long value, int unionTypeOffset, int documentationOffset, int attributesOffset) {
      builder.startTable(6);
      addValue(builder, value);
      addAttributes(builder, attributesOffset);
      addDocumentation(builder, documentationOffset);
      addUnionType(builder, unionTypeOffset);
      addName(builder, nameOffset);
      return endEnumVal(builder);
   }

   public static void startEnumVal(FlatBufferBuilder builder) {
      builder.startTable(6);
   }

   public static void addName(FlatBufferBuilder builder, int nameOffset) {
      builder.addOffset(0, nameOffset, 0);
   }

   public static void addValue(FlatBufferBuilder builder, long value) {
      builder.addLong(value);
      builder.slot(1);
   }

   public static void addUnionType(FlatBufferBuilder builder, int unionTypeOffset) {
      builder.addOffset(3, unionTypeOffset, 0);
   }

   public static void addDocumentation(FlatBufferBuilder builder, int documentationOffset) {
      builder.addOffset(4, documentationOffset, 0);
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

   public static void addAttributes(FlatBufferBuilder builder, int attributesOffset) {
      builder.addOffset(5, attributesOffset, 0);
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

   public static int endEnumVal(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      return o;
   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
      long val_1 = _bb.getLong(__offset(6, o1, _bb));
      long val_2 = _bb.getLong(__offset(6, o2, _bb));
      return val_1 > val_2 ? 1 : (val_1 < val_2 ? -1 : 0);
   }

   public static EnumVal __lookup_by_key(EnumVal obj, int vectorLocation, long key, ByteBuffer bb) {
      int span = bb.getInt(vectorLocation - 4);
      int start = 0;

      while(span != 0) {
         int middle = span / 2;
         int tableOffset = __indirect(vectorLocation + 4 * (start + middle), bb);
         long val = bb.getLong(__offset(6, bb.capacity() - tableOffset, bb));
         int comp = val > key ? 1 : (val < key ? -1 : 0);
         if (comp > 0) {
            span = middle;
         } else {
            if (comp >= 0) {
               return (obj == null ? new EnumVal() : obj).__assign(tableOffset, bb);
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

      public EnumVal get(int j) {
         return this.get(new EnumVal(), j);
      }

      public EnumVal get(EnumVal obj, int j) {
         return obj.__assign(EnumVal.__indirect(this.__element(j), this.bb), this.bb);
      }

      public EnumVal getByKey(long key) {
         return EnumVal.__lookup_by_key((EnumVal)null, this.__vector(), key, this.bb);
      }

      public EnumVal getByKey(EnumVal obj, long key) {
         return EnumVal.__lookup_by_key(obj, this.__vector(), key, this.bb);
      }
   }
}
