package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class KeyValue extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static KeyValue getRootAsKeyValue(ByteBuffer _bb) {
      return getRootAsKeyValue(_bb, new KeyValue());
   }

   public static KeyValue getRootAsKeyValue(ByteBuffer _bb, KeyValue obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public KeyValue __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public String key() {
      int o = this.__offset(4);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer keyAsByteBuffer() {
      return this.__vector_as_bytebuffer(4, 1);
   }

   public ByteBuffer keyInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 4, 1);
   }

   public String value() {
      int o = this.__offset(6);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer valueAsByteBuffer() {
      return this.__vector_as_bytebuffer(6, 1);
   }

   public ByteBuffer valueInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 6, 1);
   }

   public static int createKeyValue(FlatBufferBuilder builder, int keyOffset, int valueOffset) {
      builder.startTable(2);
      addValue(builder, valueOffset);
      addKey(builder, keyOffset);
      return endKeyValue(builder);
   }

   public static void startKeyValue(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addKey(FlatBufferBuilder builder, int keyOffset) {
      builder.addOffset(keyOffset);
      builder.slot(0);
   }

   public static void addValue(FlatBufferBuilder builder, int valueOffset) {
      builder.addOffset(1, valueOffset, 0);
   }

   public static int endKeyValue(FlatBufferBuilder builder) {
      int o = builder.endTable();
      builder.required(o, 4);
      return o;
   }

   protected int keysCompare(Integer o1, Integer o2, ByteBuffer _bb) {
      return compareStrings(__offset(4, o1, _bb), __offset(4, o2, _bb), _bb);
   }

   public static KeyValue __lookup_by_key(KeyValue obj, int vectorLocation, String key, ByteBuffer bb) {
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
               return (obj == null ? new KeyValue() : obj).__assign(tableOffset, bb);
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

      public KeyValue get(int j) {
         return this.get(new KeyValue(), j);
      }

      public KeyValue get(KeyValue obj, int j) {
         return obj.__assign(KeyValue.__indirect(this.__element(j), this.bb), this.bb);
      }

      public KeyValue getByKey(String key) {
         return KeyValue.__lookup_by_key((KeyValue)null, this.__vector(), key, this.bb);
      }

      public KeyValue getByKey(KeyValue obj, String key) {
         return KeyValue.__lookup_by_key(obj, this.__vector(), key, this.bb);
      }
   }
}
