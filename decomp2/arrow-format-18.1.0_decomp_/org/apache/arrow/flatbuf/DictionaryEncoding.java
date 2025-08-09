package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class DictionaryEncoding extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static DictionaryEncoding getRootAsDictionaryEncoding(ByteBuffer _bb) {
      return getRootAsDictionaryEncoding(_bb, new DictionaryEncoding());
   }

   public static DictionaryEncoding getRootAsDictionaryEncoding(ByteBuffer _bb, DictionaryEncoding obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public DictionaryEncoding __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long id() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public Int indexType() {
      return this.indexType(new Int());
   }

   public Int indexType(Int obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public boolean isOrdered() {
      int o = this.__offset(8);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public short dictionaryKind() {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public static int createDictionaryEncoding(FlatBufferBuilder builder, long id, int indexTypeOffset, boolean isOrdered, short dictionaryKind) {
      builder.startTable(4);
      addId(builder, id);
      addIndexType(builder, indexTypeOffset);
      addDictionaryKind(builder, dictionaryKind);
      addIsOrdered(builder, isOrdered);
      return endDictionaryEncoding(builder);
   }

   public static void startDictionaryEncoding(FlatBufferBuilder builder) {
      builder.startTable(4);
   }

   public static void addId(FlatBufferBuilder builder, long id) {
      builder.addLong(0, id, 0L);
   }

   public static void addIndexType(FlatBufferBuilder builder, int indexTypeOffset) {
      builder.addOffset(1, indexTypeOffset, 0);
   }

   public static void addIsOrdered(FlatBufferBuilder builder, boolean isOrdered) {
      builder.addBoolean(2, isOrdered, false);
   }

   public static void addDictionaryKind(FlatBufferBuilder builder, short dictionaryKind) {
      builder.addShort(3, dictionaryKind, 0);
   }

   public static int endDictionaryEncoding(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public DictionaryEncoding get(int j) {
         return this.get(new DictionaryEncoding(), j);
      }

      public DictionaryEncoding get(DictionaryEncoding obj, int j) {
         return obj.__assign(DictionaryEncoding.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
