package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Bool extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Bool getRootAsBool(ByteBuffer _bb) {
      return getRootAsBool(_bb, new Bool());
   }

   public static Bool getRootAsBool(ByteBuffer _bb, Bool obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Bool __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startBool(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endBool(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Bool get(int j) {
         return this.get(new Bool(), j);
      }

      public Bool get(Bool obj, int j) {
         return obj.__assign(Bool.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
