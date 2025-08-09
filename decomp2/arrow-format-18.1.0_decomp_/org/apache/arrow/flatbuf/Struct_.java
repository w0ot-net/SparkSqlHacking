package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Struct_ extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Struct_ getRootAsStruct_(ByteBuffer _bb) {
      return getRootAsStruct_(_bb, new Struct_());
   }

   public static Struct_ getRootAsStruct_(ByteBuffer _bb, Struct_ obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Struct_ __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startStruct_(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endStruct_(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Struct_ get(int j) {
         return this.get(new Struct_(), j);
      }

      public Struct_ get(Struct_ obj, int j) {
         return obj.__assign(Struct_.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
