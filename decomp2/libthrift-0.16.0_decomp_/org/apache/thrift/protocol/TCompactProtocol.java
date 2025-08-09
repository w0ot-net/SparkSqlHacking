package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TCompactProtocol extends TProtocol {
   private static final byte[] EMPTY_BYTES = new byte[0];
   private static final ByteBuffer EMPTY_BUFFER;
   private static final long NO_LENGTH_LIMIT = -1L;
   private static final TStruct ANONYMOUS_STRUCT;
   private static final TField TSTOP;
   private static final byte[] ttypeToCompactType;
   private static final byte PROTOCOL_ID = -126;
   private static final byte VERSION = 1;
   private static final byte VERSION_MASK = 31;
   private static final byte TYPE_MASK = -32;
   private static final byte TYPE_BITS = 7;
   private static final int TYPE_SHIFT_AMOUNT = 5;
   private ShortStack lastField_;
   private short lastFieldId_;
   private TField booleanField_;
   private Boolean boolValue_;
   private final long stringLengthLimit_;
   private final long containerLengthLimit_;
   private final byte[] temp;

   public TCompactProtocol(TTransport transport, long stringLengthLimit, long containerLengthLimit) {
      super(transport);
      this.lastField_ = new ShortStack(15);
      this.lastFieldId_ = 0;
      this.booleanField_ = null;
      this.boolValue_ = null;
      this.temp = new byte[10];
      this.stringLengthLimit_ = stringLengthLimit;
      this.containerLengthLimit_ = containerLengthLimit;
   }

   /** @deprecated */
   @Deprecated
   public TCompactProtocol(TTransport transport, long stringLengthLimit) {
      this(transport, stringLengthLimit, -1L);
   }

   public TCompactProtocol(TTransport transport) {
      this(transport, -1L, -1L);
   }

   public void reset() {
      this.lastField_.clear();
      this.lastFieldId_ = 0;
   }

   public void writeMessageBegin(TMessage message) throws TException {
      this.writeByteDirect((byte)-126);
      this.writeByteDirect(1 | message.type << 5 & -32);
      this.writeVarint32(message.seqid);
      this.writeString(message.name);
   }

   public void writeStructBegin(TStruct struct) throws TException {
      this.lastField_.push(this.lastFieldId_);
      this.lastFieldId_ = 0;
   }

   public void writeStructEnd() throws TException {
      this.lastFieldId_ = this.lastField_.pop();
   }

   public void writeFieldBegin(TField field) throws TException {
      if (field.type == 2) {
         this.booleanField_ = field;
      } else {
         this.writeFieldBeginInternal(field, (byte)-1);
      }

   }

   private void writeFieldBeginInternal(TField field, byte typeOverride) throws TException {
      byte typeToWrite = typeOverride == -1 ? this.getCompactType(field.type) : typeOverride;
      if (field.id > this.lastFieldId_ && field.id - this.lastFieldId_ <= 15) {
         this.writeByteDirect(field.id - this.lastFieldId_ << 4 | typeToWrite);
      } else {
         this.writeByteDirect(typeToWrite);
         this.writeI16(field.id);
      }

      this.lastFieldId_ = field.id;
   }

   public void writeFieldStop() throws TException {
      this.writeByteDirect((byte)0);
   }

   public void writeMapBegin(TMap map) throws TException {
      if (map.size == 0) {
         this.writeByteDirect((int)0);
      } else {
         this.writeVarint32(map.size);
         this.writeByteDirect(this.getCompactType(map.keyType) << 4 | this.getCompactType(map.valueType));
      }

   }

   public void writeListBegin(TList list) throws TException {
      this.writeCollectionBegin(list.elemType, list.size);
   }

   public void writeSetBegin(TSet set) throws TException {
      this.writeCollectionBegin(set.elemType, set.size);
   }

   public void writeBool(boolean b) throws TException {
      if (this.booleanField_ != null) {
         this.writeFieldBeginInternal(this.booleanField_, (byte)(b ? 1 : 2));
         this.booleanField_ = null;
      } else {
         this.writeByteDirect((byte)(b ? 1 : 2));
      }

   }

   public void writeByte(byte b) throws TException {
      this.writeByteDirect(b);
   }

   public void writeI16(short i16) throws TException {
      this.writeVarint32(this.intToZigZag(i16));
   }

   public void writeI32(int i32) throws TException {
      this.writeVarint32(this.intToZigZag(i32));
   }

   public void writeI64(long i64) throws TException {
      this.writeVarint64(this.longToZigzag(i64));
   }

   public void writeDouble(double dub) throws TException {
      this.fixedLongToBytes(Double.doubleToLongBits(dub), this.temp, 0);
      this.trans_.write(this.temp, 0, 8);
   }

   public void writeString(String str) throws TException {
      byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
      this.writeVarint32(bytes.length);
      this.trans_.write(bytes, 0, bytes.length);
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      ByteBuffer bb = bin.asReadOnlyBuffer();
      this.writeVarint32(bb.remaining());
      this.trans_.write(bb);
   }

   public void writeMessageEnd() throws TException {
   }

   public void writeMapEnd() throws TException {
   }

   public void writeListEnd() throws TException {
   }

   public void writeSetEnd() throws TException {
   }

   public void writeFieldEnd() throws TException {
   }

   protected void writeCollectionBegin(byte elemType, int size) throws TException {
      if (size <= 14) {
         this.writeByteDirect(size << 4 | this.getCompactType(elemType));
      } else {
         this.writeByteDirect(240 | this.getCompactType(elemType));
         this.writeVarint32(size);
      }

   }

   private void writeVarint32(int n) throws TException {
      int idx;
      for(idx = 0; (n & -128) != 0; n >>>= 7) {
         this.temp[idx++] = (byte)(n & 127 | 128);
      }

      this.temp[idx++] = (byte)n;
      this.trans_.write(this.temp, 0, idx);
   }

   private void writeVarint64(long n) throws TException {
      int idx;
      for(idx = 0; (n & -128L) != 0L; n >>>= 7) {
         this.temp[idx++] = (byte)((int)(n & 127L | 128L));
      }

      this.temp[idx++] = (byte)((int)n);
      this.trans_.write(this.temp, 0, idx);
   }

   private long longToZigzag(long l) {
      return l << 1 ^ l >> 63;
   }

   private int intToZigZag(int n) {
      return n << 1 ^ n >> 31;
   }

   private void fixedLongToBytes(long n, byte[] buf, int off) {
      buf[off + 0] = (byte)((int)(n & 255L));
      buf[off + 1] = (byte)((int)(n >> 8 & 255L));
      buf[off + 2] = (byte)((int)(n >> 16 & 255L));
      buf[off + 3] = (byte)((int)(n >> 24 & 255L));
      buf[off + 4] = (byte)((int)(n >> 32 & 255L));
      buf[off + 5] = (byte)((int)(n >> 40 & 255L));
      buf[off + 6] = (byte)((int)(n >> 48 & 255L));
      buf[off + 7] = (byte)((int)(n >> 56 & 255L));
   }

   private void writeByteDirect(byte b) throws TException {
      this.temp[0] = b;
      this.trans_.write(this.temp, 0, 1);
   }

   private void writeByteDirect(int n) throws TException {
      this.writeByteDirect((byte)n);
   }

   public TMessage readMessageBegin() throws TException {
      byte protocolId = this.readByte();
      if (protocolId != -126) {
         throw new TProtocolException("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(protocolId));
      } else {
         byte versionAndType = this.readByte();
         byte version = (byte)(versionAndType & 31);
         if (version != 1) {
            throw new TProtocolException("Expected version 1 but got " + version);
         } else {
            byte type = (byte)(versionAndType >> 5 & 7);
            int seqid = this.readVarint32();
            String messageName = this.readString();
            return new TMessage(messageName, type, seqid);
         }
      }
   }

   public TStruct readStructBegin() throws TException {
      this.lastField_.push(this.lastFieldId_);
      this.lastFieldId_ = 0;
      return ANONYMOUS_STRUCT;
   }

   public void readStructEnd() throws TException {
      this.lastFieldId_ = this.lastField_.pop();
   }

   public TField readFieldBegin() throws TException {
      byte type = this.readByte();
      if (type == 0) {
         return TSTOP;
      } else {
         short modifier = (short)((type & 240) >> 4);
         short fieldId;
         if (modifier == 0) {
            fieldId = this.readI16();
         } else {
            fieldId = (short)(this.lastFieldId_ + modifier);
         }

         TField field = new TField("", this.getTType((byte)(type & 15)), fieldId);
         if (this.isBoolType(type)) {
            this.boolValue_ = (byte)(type & 15) == 1 ? Boolean.TRUE : Boolean.FALSE;
         }

         this.lastFieldId_ = field.id;
         return field;
      }
   }

   public TMap readMapBegin() throws TException {
      int size = this.readVarint32();
      this.checkContainerReadLength(size);
      byte keyAndValueType = size == 0 ? 0 : this.readByte();
      TMap map = new TMap(this.getTType((byte)(keyAndValueType >> 4)), this.getTType((byte)(keyAndValueType & 15)), size);
      this.checkReadBytesAvailable(map);
      return map;
   }

   public TList readListBegin() throws TException {
      byte size_and_type = this.readByte();
      int size = size_and_type >> 4 & 15;
      if (size == 15) {
         size = this.readVarint32();
      }

      this.checkContainerReadLength(size);
      TList list = new TList(this.getTType(size_and_type), size);
      this.checkReadBytesAvailable(list);
      return list;
   }

   public TSet readSetBegin() throws TException {
      return new TSet(this.readListBegin());
   }

   public boolean readBool() throws TException {
      if (this.boolValue_ != null) {
         boolean result = this.boolValue_;
         this.boolValue_ = null;
         return result;
      } else {
         return this.readByte() == 1;
      }
   }

   public byte readByte() throws TException {
      byte b;
      if (this.trans_.getBytesRemainingInBuffer() > 0) {
         b = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
         this.trans_.consumeBuffer(1);
      } else {
         this.trans_.readAll(this.temp, 0, 1);
         b = this.temp[0];
      }

      return b;
   }

   public short readI16() throws TException {
      return (short)this.zigzagToInt(this.readVarint32());
   }

   public int readI32() throws TException {
      return this.zigzagToInt(this.readVarint32());
   }

   public long readI64() throws TException {
      return this.zigzagToLong(this.readVarint64());
   }

   public double readDouble() throws TException {
      this.trans_.readAll(this.temp, 0, 8);
      return Double.longBitsToDouble(this.bytesToLong(this.temp));
   }

   public String readString() throws TException {
      int length = this.readVarint32();
      this.checkStringReadLength(length);
      if (length == 0) {
         return "";
      } else {
         String str;
         if (this.trans_.getBytesRemainingInBuffer() >= length) {
            str = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), length, StandardCharsets.UTF_8);
            this.trans_.consumeBuffer(length);
         } else {
            str = new String(this.readBinary(length), StandardCharsets.UTF_8);
         }

         return str;
      }
   }

   public ByteBuffer readBinary() throws TException {
      int length = this.readVarint32();
      if (length == 0) {
         return EMPTY_BUFFER;
      } else {
         this.getTransport().checkReadBytesAvailable((long)length);
         if (this.trans_.getBytesRemainingInBuffer() >= length) {
            ByteBuffer bb = ByteBuffer.wrap(this.trans_.getBuffer(), this.trans_.getBufferPosition(), length);
            this.trans_.consumeBuffer(length);
            return bb;
         } else {
            byte[] buf = new byte[length];
            this.trans_.readAll(buf, 0, length);
            return ByteBuffer.wrap(buf);
         }
      }
   }

   private byte[] readBinary(int length) throws TException {
      if (length == 0) {
         return EMPTY_BYTES;
      } else {
         byte[] buf = new byte[length];
         this.trans_.readAll(buf, 0, length);
         return buf;
      }
   }

   private void checkStringReadLength(int length) throws TException {
      if (length < 0) {
         throw new TProtocolException(2, "Negative length: " + length);
      } else {
         this.getTransport().checkReadBytesAvailable((long)length);
         if (this.stringLengthLimit_ != -1L && (long)length > this.stringLengthLimit_) {
            throw new TProtocolException(3, "Length exceeded max allowed: " + length);
         }
      }
   }

   private void checkContainerReadLength(int length) throws TProtocolException {
      if (length < 0) {
         throw new TProtocolException(2, "Negative length: " + length);
      } else if (this.containerLengthLimit_ != -1L && (long)length > this.containerLengthLimit_) {
         throw new TProtocolException(3, "Length exceeded max allowed: " + length);
      }
   }

   public void readMessageEnd() throws TException {
   }

   public void readFieldEnd() throws TException {
   }

   public void readMapEnd() throws TException {
   }

   public void readListEnd() throws TException {
   }

   public void readSetEnd() throws TException {
   }

   private int readVarint32() throws TException {
      int result = 0;
      int shift = 0;
      if (this.trans_.getBytesRemainingInBuffer() >= 5) {
         byte[] buf = this.trans_.getBuffer();
         int pos = this.trans_.getBufferPosition();
         int off = 0;

         while(true) {
            byte b = buf[pos + off];
            result |= (b & 127) << shift;
            if ((b & 128) != 128) {
               this.trans_.consumeBuffer(off + 1);
               break;
            }

            shift += 7;
            ++off;
         }
      } else {
         while(true) {
            byte b = this.readByte();
            result |= (b & 127) << shift;
            if ((b & 128) != 128) {
               break;
            }

            shift += 7;
         }
      }

      return result;
   }

   private long readVarint64() throws TException {
      int shift = 0;
      long result = 0L;
      if (this.trans_.getBytesRemainingInBuffer() >= 10) {
         byte[] buf = this.trans_.getBuffer();
         int pos = this.trans_.getBufferPosition();
         int off = 0;

         while(true) {
            byte b = buf[pos + off];
            result |= (long)(b & 127) << shift;
            if ((b & 128) != 128) {
               this.trans_.consumeBuffer(off + 1);
               break;
            }

            shift += 7;
            ++off;
         }
      } else {
         while(true) {
            byte b = this.readByte();
            result |= (long)(b & 127) << shift;
            if ((b & 128) != 128) {
               break;
            }

            shift += 7;
         }
      }

      return result;
   }

   private int zigzagToInt(int n) {
      return n >>> 1 ^ -(n & 1);
   }

   private long zigzagToLong(long n) {
      return n >>> 1 ^ -(n & 1L);
   }

   private long bytesToLong(byte[] bytes) {
      return ((long)bytes[7] & 255L) << 56 | ((long)bytes[6] & 255L) << 48 | ((long)bytes[5] & 255L) << 40 | ((long)bytes[4] & 255L) << 32 | ((long)bytes[3] & 255L) << 24 | ((long)bytes[2] & 255L) << 16 | ((long)bytes[1] & 255L) << 8 | (long)bytes[0] & 255L;
   }

   private boolean isBoolType(byte b) {
      int lowerNibble = b & 15;
      return lowerNibble == 1 || lowerNibble == 2;
   }

   private byte getTType(byte type) throws TProtocolException {
      switch ((byte)(type & 15)) {
         case 0:
            return 0;
         case 1:
         case 2:
            return 2;
         case 3:
            return 3;
         case 4:
            return 6;
         case 5:
            return 8;
         case 6:
            return 10;
         case 7:
            return 4;
         case 8:
            return 11;
         case 9:
            return 15;
         case 10:
            return 14;
         case 11:
            return 13;
         case 12:
            return 12;
         default:
            throw new TProtocolException("don't know what type: " + (byte)(type & 15));
      }
   }

   private byte getCompactType(byte ttype) {
      return ttypeToCompactType[ttype];
   }

   public int getMinSerializedSize(byte type) throws TTransportException {
      switch (type) {
         case 0:
            return 0;
         case 1:
            return 0;
         case 2:
            return 1;
         case 3:
            return 1;
         case 4:
            return 8;
         case 5:
         case 7:
         case 9:
         default:
            throw new TTransportException(0, "unrecognized type code");
         case 6:
            return 1;
         case 8:
            return 1;
         case 10:
            return 1;
         case 11:
            return 1;
         case 12:
            return 0;
         case 13:
            return 1;
         case 14:
            return 1;
         case 15:
            return 1;
      }
   }

   protected void skipBinary() throws TException {
      int size = this.intToZigZag(this.readI32());
      this.skipBytes(size);
   }

   static {
      EMPTY_BUFFER = ByteBuffer.wrap(EMPTY_BYTES);
      ANONYMOUS_STRUCT = new TStruct("");
      TSTOP = new TField("", (byte)0, (short)0);
      ttypeToCompactType = new byte[16];
      ttypeToCompactType[0] = 0;
      ttypeToCompactType[2] = 1;
      ttypeToCompactType[3] = 3;
      ttypeToCompactType[6] = 4;
      ttypeToCompactType[8] = 5;
      ttypeToCompactType[10] = 6;
      ttypeToCompactType[4] = 7;
      ttypeToCompactType[11] = 8;
      ttypeToCompactType[15] = 9;
      ttypeToCompactType[14] = 10;
      ttypeToCompactType[13] = 11;
      ttypeToCompactType[12] = 12;
   }

   public static class Factory implements TProtocolFactory {
      private final long stringLengthLimit_;
      private final long containerLengthLimit_;

      public Factory() {
         this(-1L, -1L);
      }

      public Factory(long stringLengthLimit) {
         this(stringLengthLimit, -1L);
      }

      public Factory(long stringLengthLimit, long containerLengthLimit) {
         this.containerLengthLimit_ = containerLengthLimit;
         this.stringLengthLimit_ = stringLengthLimit;
      }

      public TProtocol getProtocol(TTransport trans) {
         return new TCompactProtocol(trans, this.stringLengthLimit_, this.containerLengthLimit_);
      }
   }

   private static class Types {
      public static final byte BOOLEAN_TRUE = 1;
      public static final byte BOOLEAN_FALSE = 2;
      public static final byte BYTE = 3;
      public static final byte I16 = 4;
      public static final byte I32 = 5;
      public static final byte I64 = 6;
      public static final byte DOUBLE = 7;
      public static final byte BINARY = 8;
      public static final byte LIST = 9;
      public static final byte SET = 10;
      public static final byte MAP = 11;
      public static final byte STRUCT = 12;
   }
}
