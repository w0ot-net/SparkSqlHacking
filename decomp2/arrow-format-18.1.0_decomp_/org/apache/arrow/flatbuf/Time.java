package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Time extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Time getRootAsTime(ByteBuffer _bb) {
      return getRootAsTime(_bb, new Time());
   }

   public static Time getRootAsTime(ByteBuffer _bb, Time obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Time __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short unit() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 1;
   }

   public int bitWidth() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 32;
   }

   public static int createTime(FlatBufferBuilder builder, short unit, int bitWidth) {
      builder.startTable(2);
      addBitWidth(builder, bitWidth);
      addUnit(builder, unit);
      return endTime(builder);
   }

   public static void startTime(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addUnit(FlatBufferBuilder builder, short unit) {
      builder.addShort(0, unit, 1);
   }

   public static void addBitWidth(FlatBufferBuilder builder, int bitWidth) {
      builder.addInt(1, bitWidth, 32);
   }

   public static int endTime(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Time get(int j) {
         return this.get(new Time(), j);
      }

      public Time get(Time obj, int j) {
         return obj.__assign(Time.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
