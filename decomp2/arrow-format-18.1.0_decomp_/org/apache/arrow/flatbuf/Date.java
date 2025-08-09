package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Date extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Date getRootAsDate(ByteBuffer _bb) {
      return getRootAsDate(_bb, new Date());
   }

   public static Date getRootAsDate(ByteBuffer _bb, Date obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Date __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short unit() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 1;
   }

   public static int createDate(FlatBufferBuilder builder, short unit) {
      builder.startTable(1);
      addUnit(builder, unit);
      return endDate(builder);
   }

   public static void startDate(FlatBufferBuilder builder) {
      builder.startTable(1);
   }

   public static void addUnit(FlatBufferBuilder builder, short unit) {
      builder.addShort(0, unit, 1);
   }

   public static int endDate(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Date get(int j) {
         return this.get(new Date(), j);
      }

      public Date get(Date obj, int j) {
         return obj.__assign(Date.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
