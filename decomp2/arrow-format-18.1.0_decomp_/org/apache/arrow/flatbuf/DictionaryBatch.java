package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class DictionaryBatch extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static DictionaryBatch getRootAsDictionaryBatch(ByteBuffer _bb) {
      return getRootAsDictionaryBatch(_bb, new DictionaryBatch());
   }

   public static DictionaryBatch getRootAsDictionaryBatch(ByteBuffer _bb, DictionaryBatch obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public DictionaryBatch __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public long id() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public RecordBatch data() {
      return this.data(new RecordBatch());
   }

   public RecordBatch data(RecordBatch obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__indirect(o + this.bb_pos), this.bb) : null;
   }

   public boolean isDelta() {
      int o = this.__offset(8);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public static int createDictionaryBatch(FlatBufferBuilder builder, long id, int dataOffset, boolean isDelta) {
      builder.startTable(3);
      addId(builder, id);
      addData(builder, dataOffset);
      addIsDelta(builder, isDelta);
      return endDictionaryBatch(builder);
   }

   public static void startDictionaryBatch(FlatBufferBuilder builder) {
      builder.startTable(3);
   }

   public static void addId(FlatBufferBuilder builder, long id) {
      builder.addLong(0, id, 0L);
   }

   public static void addData(FlatBufferBuilder builder, int dataOffset) {
      builder.addOffset(1, dataOffset, 0);
   }

   public static void addIsDelta(FlatBufferBuilder builder, boolean isDelta) {
      builder.addBoolean(2, isDelta, false);
   }

   public static int endDictionaryBatch(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public DictionaryBatch get(int j) {
         return this.get(new DictionaryBatch(), j);
      }

      public DictionaryBatch get(DictionaryBatch obj, int j) {
         return obj.__assign(DictionaryBatch.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
