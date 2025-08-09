package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
      builder.addOffset(0, keyOffset, 0);
   }

   public static void addValue(FlatBufferBuilder builder, int valueOffset) {
      builder.addOffset(1, valueOffset, 0);
   }

   public static int endKeyValue(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
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
   }
}
