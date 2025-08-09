package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Union extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Union getRootAsUnion(ByteBuffer _bb) {
      return getRootAsUnion(_bb, new Union());
   }

   public static Union getRootAsUnion(ByteBuffer _bb, Union obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Union __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short mode() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public int typeIds(int j) {
      int o = this.__offset(6);
      return o != 0 ? this.bb.getInt(this.__vector(o) + j * 4) : 0;
   }

   public int typeIdsLength() {
      int o = this.__offset(6);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public IntVector typeIdsVector() {
      return this.typeIdsVector(new IntVector());
   }

   public IntVector typeIdsVector(IntVector obj) {
      int o = this.__offset(6);
      return o != 0 ? obj.__assign(this.__vector(o), this.bb) : null;
   }

   public ByteBuffer typeIdsAsByteBuffer() {
      return this.__vector_as_bytebuffer(6, 4);
   }

   public ByteBuffer typeIdsInByteBuffer(ByteBuffer _bb) {
      return this.__vector_in_bytebuffer(_bb, 6, 4);
   }

   public static int createUnion(FlatBufferBuilder builder, short mode, int typeIdsOffset) {
      builder.startTable(2);
      addTypeIds(builder, typeIdsOffset);
      addMode(builder, mode);
      return endUnion(builder);
   }

   public static void startUnion(FlatBufferBuilder builder) {
      builder.startTable(2);
   }

   public static void addMode(FlatBufferBuilder builder, short mode) {
      builder.addShort(0, mode, 0);
   }

   public static void addTypeIds(FlatBufferBuilder builder, int typeIdsOffset) {
      builder.addOffset(1, typeIdsOffset, 0);
   }

   public static int createTypeIdsVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addInt(data[i]);
      }

      return builder.endVector();
   }

   public static void startTypeIdsVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endUnion(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Union get(int j) {
         return this.get(new Union(), j);
      }

      public Union get(Union obj, int j) {
         return obj.__assign(Union.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
