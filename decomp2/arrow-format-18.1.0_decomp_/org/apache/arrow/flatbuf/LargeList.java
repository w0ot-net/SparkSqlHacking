package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class LargeList extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static LargeList getRootAsLargeList(ByteBuffer _bb) {
      return getRootAsLargeList(_bb, new LargeList());
   }

   public static LargeList getRootAsLargeList(ByteBuffer _bb, LargeList obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public LargeList __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startLargeList(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endLargeList(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public LargeList get(int j) {
         return this.get(new LargeList(), j);
      }

      public LargeList get(LargeList obj, int j) {
         return obj.__assign(LargeList.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
