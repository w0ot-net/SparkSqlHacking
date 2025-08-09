package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Utf8View extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Utf8View getRootAsUtf8View(ByteBuffer _bb) {
      return getRootAsUtf8View(_bb, new Utf8View());
   }

   public static Utf8View getRootAsUtf8View(ByteBuffer _bb, Utf8View obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Utf8View __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public static void startUtf8View(FlatBufferBuilder builder) {
      builder.startTable(0);
   }

   public static int endUtf8View(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Utf8View get(int j) {
         return this.get(new Utf8View(), j);
      }

      public Utf8View get(Utf8View obj, int j) {
         return obj.__assign(Utf8View.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
