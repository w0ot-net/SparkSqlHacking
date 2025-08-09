package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Timestamp extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Timestamp getRootAsTimestamp(ByteBuffer _bb) {
      return getRootAsTimestamp(_bb, new Timestamp());
   }

   public static Timestamp getRootAsTimestamp(ByteBuffer _bb, Timestamp obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Timestamp __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short unit() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public String timezone() {
      int o = this.__offset(6);
      return o != 0 ? this.__string(o + this.bb_pos) : null;
   }

   public ByteBuffer timezoneAsByteBuffer() {
      return this.__vector_as_bytebuffer(6, 1);
   }

   public ByteBuffer timezoneInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 6, 1);
   }

   public static int createTimestamp(FlatBufferBuilder builder, short unit, int timezoneOffset) {
      builder.startTable(2);
      addTimezone(builder, timezoneOffset);
      addUnit(builder, unit);
      return endTimestamp(builder);
   }

   public static void startTimestamp(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addUnit(FlatBufferBuilder builder, short unit) {
      builder.addShort(0, unit, 0);
   }

   public static void addTimezone(FlatBufferBuilder builder, int timezoneOffset) {
      builder.addOffset(1, timezoneOffset, 0);
   }

   public static int endTimestamp(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Timestamp get(int j) {
         return this.get(new Timestamp(), j);
      }

      public Timestamp get(Timestamp obj, int j) {
         return obj.__assign(Timestamp.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
