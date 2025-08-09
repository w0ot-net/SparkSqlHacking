package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Type extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Type getRootAsType(ByteBuffer _bb) {
      return getRootAsType(_bb, new Type());
   }

   public static Type getRootAsType(ByteBuffer _bb, Type obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Type __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public byte baseType() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public byte element() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public int index() {
      int o = this.__offset(8);
      return o != 0 ? this.bb.getInt(o + this.bb_pos) : -1;
   }

   public int fixedLength() {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) & '\uffff' : 0;
   }

   public long baseSize() {
      int o = this.__offset(12);
      return o != 0 ? (long)this.bb.getInt(o + this.bb_pos) & 4294967295L : 4L;
   }

   public long elementSize() {
      int o = this.__offset(14);
      return o != 0 ? (long)this.bb.getInt(o + this.bb_pos) & 4294967295L : 0L;
   }

   public static int createType(FlatBufferBuilder builder, byte baseType, byte element, int index, int fixedLength, long baseSize, long elementSize) {
      builder.startTable(6);
      addElementSize(builder, elementSize);
      addBaseSize(builder, baseSize);
      addIndex(builder, index);
      addFixedLength(builder, fixedLength);
      addElement(builder, element);
      addBaseType(builder, baseType);
      return endType(builder);
   }

   public static void startType(FlatBufferBuilder builder) {
      builder.startTable(6);
   }

   public static void addBaseType(FlatBufferBuilder builder, byte baseType) {
      builder.addByte(0, baseType, 0);
   }

   public static void addElement(FlatBufferBuilder builder, byte element) {
      builder.addByte(1, element, 0);
   }

   public static void addIndex(FlatBufferBuilder builder, int index) {
      builder.addInt(2, index, -1);
   }

   public static void addFixedLength(FlatBufferBuilder builder, int fixedLength) {
      builder.addShort(3, (short)fixedLength, 0);
   }

   public static void addBaseSize(FlatBufferBuilder builder, long baseSize) {
      builder.addInt(4, (int)baseSize, 4);
   }

   public static void addElementSize(FlatBufferBuilder builder, long elementSize) {
      builder.addInt(5, (int)elementSize, 0);
   }

   public static int endType(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Type get(int j) {
         return this.get(new Type(), j);
      }

      public Type get(Type obj, int j) {
         return obj.__assign(Type.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
