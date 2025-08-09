package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Int extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Int getRootAsInt(ByteBuffer _bb) {
      return getRootAsInt(_bb, new Int());
   }

   public static Int getRootAsInt(ByteBuffer _bb, Int obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Int __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public int bitWidth() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 0;
   }

   public boolean isSigned() {
      int o = this.__offset(6);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public static int createInt(FlatBufferBuilder builder, int bitWidth, boolean isSigned) {
      builder.startTable(2);
      addBitWidth(builder, bitWidth);
      addIsSigned(builder, isSigned);
      return endInt(builder);
   }

   public static void startInt(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addBitWidth(FlatBufferBuilder builder, int bitWidth) {
      builder.addInt(0, bitWidth, 0);
   }

   public static void addIsSigned(FlatBufferBuilder builder, boolean isSigned) {
      builder.addBoolean(1, isSigned, false);
   }

   public static int endInt(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Int get(int j) {
         return this.get(new Int(), j);
      }

      public Int get(Int obj, int j) {
         return obj.__assign(Int.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
