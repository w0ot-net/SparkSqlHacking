package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class FloatingPoint extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static FloatingPoint getRootAsFloatingPoint(ByteBuffer _bb) {
      return getRootAsFloatingPoint(_bb, new FloatingPoint());
   }

   public static FloatingPoint getRootAsFloatingPoint(ByteBuffer _bb, FloatingPoint obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public FloatingPoint __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short precision() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public static int createFloatingPoint(FlatBufferBuilder builder, short precision) {
      builder.startTable(1);
      addPrecision(builder, precision);
      return endFloatingPoint(builder);
   }

   public static void startFloatingPoint(FlatBufferBuilder builder) {
      builder.startTable(1);
   }

   public static void addPrecision(FlatBufferBuilder builder, short precision) {
      builder.addShort(0, precision, 0);
   }

   public static int endFloatingPoint(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public FloatingPoint get(int j) {
         return this.get(new FloatingPoint(), j);
      }

      public FloatingPoint get(FloatingPoint obj, int j) {
         return obj.__assign(FloatingPoint.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
