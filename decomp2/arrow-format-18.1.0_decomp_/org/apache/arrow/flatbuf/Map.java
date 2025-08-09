package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Map extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Map getRootAsMap(ByteBuffer _bb) {
      return getRootAsMap(_bb, new Map());
   }

   public static Map getRootAsMap(ByteBuffer _bb, Map obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Map __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public boolean keysSorted() {
      int o = this.__offset(4);
      return o != 0 ? 0 != this.bb.get(o + this.bb_pos) : false;
   }

   public static int createMap(FlatBufferBuilder builder, boolean keysSorted) {
      builder.startTable(1);
      addKeysSorted(builder, keysSorted);
      return endMap(builder);
   }

   public static void startMap(FlatBufferBuilder builder) {
      builder.startTable(1);
   }

   public static void addKeysSorted(FlatBufferBuilder builder, boolean keysSorted) {
      builder.addBoolean(0, keysSorted, false);
   }

   public static int endMap(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Map get(int j) {
         return this.get(new Map(), j);
      }

      public Map get(Map obj, int j) {
         return obj.__assign(Map.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
