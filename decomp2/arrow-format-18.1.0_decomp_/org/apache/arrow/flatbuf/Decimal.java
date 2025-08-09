package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Decimal extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Decimal getRootAsDecimal(ByteBuffer _bb) {
      return getRootAsDecimal(_bb, new Decimal());
   }

   public static Decimal getRootAsDecimal(ByteBuffer _bb, Decimal obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Decimal __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public int precision() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 0;
   }

   public int scale() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 0;
   }

   public int bitWidth() {
      int o = this.__offset(8);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 128;
   }

   public static int createDecimal(FlatBufferBuilder builder, int precision, int scale, int bitWidth) {
      builder.startTable(3);
      addBitWidth(builder, bitWidth);
      addScale(builder, scale);
      addPrecision(builder, precision);
      return endDecimal(builder);
   }

   public static void startDecimal(FlatBufferBuilder builder) {
      builder.startTable(3);
   }

   public static void addPrecision(FlatBufferBuilder builder, int precision) {
      builder.addInt(0, precision, 0);
   }

   public static void addScale(FlatBufferBuilder builder, int scale) {
      builder.addInt(1, scale, 0);
   }

   public static void addBitWidth(FlatBufferBuilder builder, int bitWidth) {
      builder.addInt(2, bitWidth, 128);
   }

   public static int endDecimal(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Decimal get(int j) {
         return this.get(new Decimal(), j);
      }

      public Decimal get(Decimal obj, int j) {
         return obj.__assign(Decimal.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
