package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class BodyCompression extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static BodyCompression getRootAsBodyCompression(ByteBuffer _bb) {
      return getRootAsBodyCompression(_bb, new BodyCompression());
   }

   public static BodyCompression getRootAsBodyCompression(ByteBuffer _bb, BodyCompression obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public BodyCompression __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public byte codec() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public byte method() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public static int createBodyCompression(FlatBufferBuilder builder, byte codec, byte method) {
      builder.startTable(2);
      addMethod(builder, method);
      addCodec(builder, codec);
      return endBodyCompression(builder);
   }

   public static void startBodyCompression(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addCodec(FlatBufferBuilder builder, byte codec) {
      builder.addByte(0, codec, 0);
   }

   public static void addMethod(FlatBufferBuilder builder, byte method) {
      builder.addByte(1, method, 0);
   }

   public static int endBodyCompression(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public BodyCompression get(int j) {
         return this.get(new BodyCompression(), j);
      }

      public BodyCompression get(BodyCompression obj, int j) {
         return obj.__assign(BodyCompression.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
