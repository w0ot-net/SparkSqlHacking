package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class RunEndEncoded extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static RunEndEncoded getRootAsRunEndEncoded(ByteBuffer _bb) {
      return getRootAsRunEndEncoded(_bb, new RunEndEncoded());
   }

   public static RunEndEncoded getRootAsRunEndEncoded(ByteBuffer _bb, RunEndEncoded obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public RunEndEncoded __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startRunEndEncoded(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endRunEndEncoded(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public RunEndEncoded get(int j) {
         return this.get(new RunEndEncoded(), j);
      }

      public RunEndEncoded get(RunEndEncoded obj, int j) {
         return obj.__assign(RunEndEncoded.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
