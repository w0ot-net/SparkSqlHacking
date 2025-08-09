package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class FixedSizeList extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static FixedSizeList getRootAsFixedSizeList(ByteBuffer _bb) {
      return getRootAsFixedSizeList(_bb, new FixedSizeList());
   }

   public static FixedSizeList getRootAsFixedSizeList(ByteBuffer _bb, FixedSizeList obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public FixedSizeList __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public int listSize() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : 0;
   }

   public static int createFixedSizeList(FlatBufferBuilder builder, int listSize) {
      builder.startTable(1);
      addListSize(builder, listSize);
      return endFixedSizeList(builder);
   }

   public static void startFixedSizeList(FlatBufferBuilder builder) {
      builder.startTable(1);
   }

   public static void addListSize(FlatBufferBuilder builder, int listSize) {
      builder.addInt(0, listSize, 0);
   }

   public static int endFixedSizeList(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public FixedSizeList get(int j) {
         return this.get(new FixedSizeList(), j);
      }

      public FixedSizeList get(FixedSizeList obj, int j) {
         return obj.__assign(FixedSizeList.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
