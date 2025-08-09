package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class FixedSizeBinary extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static FixedSizeBinary getRootAsFixedSizeBinary(ByteBuffer _bb) {
      return getRootAsFixedSizeBinary(_bb, new FixedSizeBinary());
   }

   public static FixedSizeBinary getRootAsFixedSizeBinary(ByteBuffer _bb, FixedSizeBinary obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public FixedSizeBinary __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public int byteWidth() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 0;
   }

   public static int createFixedSizeBinary(FlatBufferBuilder builder, int byteWidth) {
      builder.startTable(1);
      addByteWidth(builder, byteWidth);
      return endFixedSizeBinary(builder);
   }

   public static void startFixedSizeBinary(FlatBufferBuilder builder) {
      builder.startTable(1);
   }

   public static void addByteWidth(FlatBufferBuilder builder, int byteWidth) {
      builder.addInt(0, byteWidth, 0);
   }

   public static int endFixedSizeBinary(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public FixedSizeBinary get(int j) {
         return this.get(new FixedSizeBinary(), j);
      }

      public FixedSizeBinary get(FixedSizeBinary obj, int j) {
         return obj.__assign(FixedSizeBinary.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
