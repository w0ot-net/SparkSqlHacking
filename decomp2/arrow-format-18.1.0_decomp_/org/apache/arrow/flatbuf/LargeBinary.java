package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class LargeBinary extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static LargeBinary getRootAsLargeBinary(ByteBuffer _bb) {
      return getRootAsLargeBinary(_bb, new LargeBinary());
   }

   public static LargeBinary getRootAsLargeBinary(ByteBuffer _bb, LargeBinary obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public LargeBinary __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startLargeBinary(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endLargeBinary(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public LargeBinary get(int j) {
         return this.get(new LargeBinary(), j);
      }

      public LargeBinary get(LargeBinary obj, int j) {
         return obj.__assign(LargeBinary.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
