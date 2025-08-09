package org.apache.arrow.flatbuf;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Message extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_24_3_25();
   }

   public static Message getRootAsMessage(ByteBuffer _bb) {
      return getRootAsMessage(_bb, new Message());
   }

   public static Message getRootAsMessage(ByteBuffer _bb, Message obj) {
      _bb.order(ByteOrder.LITTLE_ENDIAN);
      return obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb);
   }

   public void __init(int _i, ByteBuffer _bb) {
      this.__reset(_i, _bb);
   }

   public Message __assign(int _i, ByteBuffer _bb) {
      this.__init(_i, _bb);
      return this;
   }

   public short version() {
      int o = this.__offset(4);
      return o != 0 ? this.bb.getShort(o + this.bb_pos) : 0;
   }

   public byte headerType() {
      int o = this.__offset(6);
      return o != 0 ? this.bb.get(o + this.bb_pos) : 0;
   }

   public Table header(Table obj) {
      int o = this.__offset(8);
      return o != 0 ? this.__union(obj, o + this.bb_pos) : null;
   }

   public long bodyLength() {
      int o = this.__offset(10);
      return o != 0 ? this.bb.getLong(o + this.bb_pos) : 0L;
   }

   public KeyValue customMetadata(int j) {
      return this.customMetadata(new KeyValue(), j);
   }

   public KeyValue customMetadata(KeyValue obj, int j) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__indirect(this.__vector(o) + j * 4), this.bb) : null;
   }

   public int customMetadataLength() {
      int o = this.__offset(12);
      return o != 0 ? this.__vector_len(o) : 0;
   }

   public KeyValue.Vector customMetadataVector() {
      return this.customMetadataVector(new KeyValue.Vector());
   }

   public KeyValue.Vector customMetadataVector(KeyValue.Vector obj) {
      int o = this.__offset(12);
      return o != 0 ? obj.__assign(this.__vector(o), 4, this.bb) : null;
   }

   public static int createMessage(FlatBufferBuilder builder, short version, byte headerType, int headerOffset, long bodyLength, int customMetadataOffset) {
      builder.startTable(5);
      addBodyLength(builder, bodyLength);
      addCustomMetadata(builder, customMetadataOffset);
      addHeader(builder, headerOffset);
      addVersion(builder, version);
      addHeaderType(builder, headerType);
      return endMessage(builder);
   }

   public static void startMessage(FlatBufferBuilder builder) {
      builder.startTable(5);
   }

   public static void addVersion(FlatBufferBuilder builder, short version) {
      builder.addShort(0, version, 0);
   }

   public static void addHeaderType(FlatBufferBuilder builder, byte headerType) {
      builder.addByte(1, headerType, 0);
   }

   public static void addHeader(FlatBufferBuilder builder, int headerOffset) {
      builder.addOffset(2, headerOffset, 0);
   }

   public static void addBodyLength(FlatBufferBuilder builder, long bodyLength) {
      builder.addLong(3, bodyLength, 0L);
   }

   public static void addCustomMetadata(FlatBufferBuilder builder, int customMetadataOffset) {
      builder.addOffset(4, customMetadataOffset, 0);
   }

   public static int createCustomMetadataVector(FlatBufferBuilder builder, int[] data) {
      builder.startVector(4, data.length, 4);

      for(int i = data.length - 1; i >= 0; --i) {
         builder.addOffset(data[i]);
      }

      return builder.endVector();
   }

   public static void startCustomMetadataVector(FlatBufferBuilder builder, int numElems) {
      builder.startVector(4, numElems, 4);
   }

   public static int endMessage(FlatBufferBuilder builder) {
      int o = builder.endTable();
      return o;
   }

   public static void finishMessageBuffer(FlatBufferBuilder builder, int offset) {
      builder.finish(offset);
   }

   public static void finishSizePrefixedMessageBuffer(FlatBufferBuilder builder, int offset) {
      builder.finishSizePrefixed(offset);
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) {
         this.__reset(_vector, _element_size, _bb);
         return this;
      }

      public Message get(int j) {
         return this.get(new Message(), j);
      }

      public Message get(Message obj, int j) {
         return obj.__assign(Message.__indirect(this.__element(j), this.bb), this.bb);
      }
   }
}
