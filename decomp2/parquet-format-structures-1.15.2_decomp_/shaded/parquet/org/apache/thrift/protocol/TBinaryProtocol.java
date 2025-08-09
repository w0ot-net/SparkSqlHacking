package shaded.parquet.org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.partial.TFieldData;
import shaded.parquet.org.apache.thrift.transport.TTransport;
import shaded.parquet.org.apache.thrift.transport.TTransportException;

public class TBinaryProtocol extends TProtocol {
   private static final TStruct ANONYMOUS_STRUCT = new TStruct();
   private static final long NO_LENGTH_LIMIT = -1L;
   protected static final int VERSION_MASK = -65536;
   protected static final int VERSION_1 = -2147418112;
   private final long stringLengthLimit_;
   private final long containerLengthLimit_;
   protected boolean strictRead_;
   protected boolean strictWrite_;
   private final byte[] inoutTemp;

   public TBinaryProtocol(TTransport trans) {
      this(trans, false, true);
   }

   public TBinaryProtocol(TTransport trans, boolean strictRead, boolean strictWrite) {
      this(trans, -1L, -1L, strictRead, strictWrite);
   }

   public TBinaryProtocol(TTransport trans, long stringLengthLimit, long containerLengthLimit) {
      this(trans, stringLengthLimit, containerLengthLimit, false, true);
   }

   public TBinaryProtocol(TTransport trans, long stringLengthLimit, long containerLengthLimit, boolean strictRead, boolean strictWrite) {
      super(trans);
      this.inoutTemp = new byte[16];
      this.stringLengthLimit_ = stringLengthLimit;
      this.containerLengthLimit_ = containerLengthLimit;
      this.strictRead_ = strictRead;
      this.strictWrite_ = strictWrite;
   }

   public void writeMessageBegin(TMessage message) throws TException {
      if (this.strictWrite_) {
         int version = -2147418112 | message.type;
         this.writeI32(version);
         this.writeString(message.name);
         this.writeI32(message.seqid);
      } else {
         this.writeString(message.name);
         this.writeByte(message.type);
         this.writeI32(message.seqid);
      }

   }

   public void writeMessageEnd() throws TException {
   }

   public void writeStructBegin(TStruct struct) throws TException {
   }

   public void writeStructEnd() throws TException {
   }

   public void writeFieldBegin(TField field) throws TException {
      this.writeByte(field.type);
      this.writeI16(field.id);
   }

   public void writeFieldEnd() throws TException {
   }

   public void writeFieldStop() throws TException {
      this.writeByte((byte)0);
   }

   public void writeMapBegin(TMap map) throws TException {
      this.writeByte(map.keyType);
      this.writeByte(map.valueType);
      this.writeI32(map.size);
   }

   public void writeMapEnd() throws TException {
   }

   public void writeListBegin(TList list) throws TException {
      this.writeByte(list.elemType);
      this.writeI32(list.size);
   }

   public void writeListEnd() throws TException {
   }

   public void writeSetBegin(TSet set) throws TException {
      this.writeByte(set.elemType);
      this.writeI32(set.size);
   }

   public void writeSetEnd() throws TException {
   }

   public void writeBool(boolean b) throws TException {
      this.writeByte((byte)(b ? 1 : 0));
   }

   public void writeByte(byte b) throws TException {
      this.inoutTemp[0] = b;
      this.trans_.write(this.inoutTemp, 0, 1);
   }

   public void writeI16(short i16) throws TException {
      this.inoutTemp[0] = (byte)(255 & i16 >> 8);
      this.inoutTemp[1] = (byte)(255 & i16);
      this.trans_.write(this.inoutTemp, 0, 2);
   }

   public void writeI32(int i32) throws TException {
      this.inoutTemp[0] = (byte)(255 & i32 >> 24);
      this.inoutTemp[1] = (byte)(255 & i32 >> 16);
      this.inoutTemp[2] = (byte)(255 & i32 >> 8);
      this.inoutTemp[3] = (byte)(255 & i32);
      this.trans_.write(this.inoutTemp, 0, 4);
   }

   public void writeI64(long i64) throws TException {
      this.inoutTemp[0] = (byte)((int)(255L & i64 >> 56));
      this.inoutTemp[1] = (byte)((int)(255L & i64 >> 48));
      this.inoutTemp[2] = (byte)((int)(255L & i64 >> 40));
      this.inoutTemp[3] = (byte)((int)(255L & i64 >> 32));
      this.inoutTemp[4] = (byte)((int)(255L & i64 >> 24));
      this.inoutTemp[5] = (byte)((int)(255L & i64 >> 16));
      this.inoutTemp[6] = (byte)((int)(255L & i64 >> 8));
      this.inoutTemp[7] = (byte)((int)(255L & i64));
      this.trans_.write(this.inoutTemp, 0, 8);
   }

   public void writeUuid(UUID uuid) throws TException {
      long lsb = uuid.getLeastSignificantBits();
      this.inoutTemp[0] = (byte)((int)(255L & lsb >> 56));
      this.inoutTemp[1] = (byte)((int)(255L & lsb >> 48));
      this.inoutTemp[2] = (byte)((int)(255L & lsb >> 40));
      this.inoutTemp[3] = (byte)((int)(255L & lsb >> 32));
      this.inoutTemp[4] = (byte)((int)(255L & lsb >> 24));
      this.inoutTemp[5] = (byte)((int)(255L & lsb >> 16));
      this.inoutTemp[6] = (byte)((int)(255L & lsb >> 8));
      this.inoutTemp[7] = (byte)((int)(255L & lsb));
      lsb = uuid.getMostSignificantBits();
      this.inoutTemp[8] = (byte)((int)(255L & lsb >> 56));
      this.inoutTemp[9] = (byte)((int)(255L & lsb >> 48));
      this.inoutTemp[10] = (byte)((int)(255L & lsb >> 40));
      this.inoutTemp[11] = (byte)((int)(255L & lsb >> 32));
      this.inoutTemp[12] = (byte)((int)(255L & lsb >> 24));
      this.inoutTemp[13] = (byte)((int)(255L & lsb >> 16));
      this.inoutTemp[14] = (byte)((int)(255L & lsb >> 8));
      this.inoutTemp[15] = (byte)((int)(255L & lsb));
      this.trans_.write(this.inoutTemp, 0, 16);
   }

   public void writeDouble(double dub) throws TException {
      this.writeI64(Double.doubleToLongBits(dub));
   }

   public void writeString(String str) throws TException {
      byte[] dat = str.getBytes(StandardCharsets.UTF_8);
      this.writeI32(dat.length);
      this.trans_.write(dat, 0, dat.length);
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      int length = bin.limit() - bin.position();
      this.writeI32(length);
      this.trans_.write(bin.array(), bin.position() + bin.arrayOffset(), length);
   }

   public TMessage readMessageBegin() throws TException {
      int size = this.readI32();
      if (size < 0) {
         int version = size & -65536;
         if (version != -2147418112) {
            throw new TProtocolException(4, "Bad version in readMessageBegin");
         } else {
            return new TMessage(this.readString(), (byte)(size & 255), this.readI32());
         }
      } else if (this.strictRead_) {
         throw new TProtocolException(4, "Missing version in readMessageBegin, old client?");
      } else {
         return new TMessage(this.readStringBody(size), this.readByte(), this.readI32());
      }
   }

   public void readMessageEnd() throws TException {
   }

   public TStruct readStructBegin() throws TException {
      return ANONYMOUS_STRUCT;
   }

   public void readStructEnd() throws TException {
   }

   public TField readFieldBegin() throws TException {
      byte type = this.readByte();
      short id = type == 0 ? 0 : this.readI16();
      return new TField("", type, id);
   }

   public void readFieldEnd() throws TException {
   }

   public TMap readMapBegin() throws TException {
      TMap map = new TMap(this.readByte(), this.readByte(), this.readI32());
      this.checkReadBytesAvailable(map);
      this.checkContainerReadLength(map.size);
      return map;
   }

   public void readMapEnd() throws TException {
   }

   public TList readListBegin() throws TException {
      TList list = new TList(this.readByte(), this.readI32());
      this.checkReadBytesAvailable(list);
      this.checkContainerReadLength(list.size);
      return list;
   }

   public void readListEnd() throws TException {
   }

   public TSet readSetBegin() throws TException {
      TSet set = new TSet(this.readByte(), this.readI32());
      this.checkReadBytesAvailable(set);
      this.checkContainerReadLength(set.size);
      return set;
   }

   public void readSetEnd() throws TException {
   }

   public boolean readBool() throws TException {
      return this.readByte() == 1;
   }

   public byte readByte() throws TException {
      if (this.trans_.getBytesRemainingInBuffer() >= 1) {
         byte b = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
         this.trans_.consumeBuffer(1);
         return b;
      } else {
         this.readAll(this.inoutTemp, 0, 1);
         return this.inoutTemp[0];
      }
   }

   public short readI16() throws TException {
      byte[] buf = this.inoutTemp;
      int off = 0;
      if (this.trans_.getBytesRemainingInBuffer() >= 2) {
         buf = this.trans_.getBuffer();
         off = this.trans_.getBufferPosition();
         this.trans_.consumeBuffer(2);
      } else {
         this.readAll(this.inoutTemp, 0, 2);
      }

      return (short)((buf[off] & 255) << 8 | buf[off + 1] & 255);
   }

   public int readI32() throws TException {
      byte[] buf = this.inoutTemp;
      int off = 0;
      if (this.trans_.getBytesRemainingInBuffer() >= 4) {
         buf = this.trans_.getBuffer();
         off = this.trans_.getBufferPosition();
         this.trans_.consumeBuffer(4);
      } else {
         this.readAll(this.inoutTemp, 0, 4);
      }

      return (buf[off] & 255) << 24 | (buf[off + 1] & 255) << 16 | (buf[off + 2] & 255) << 8 | buf[off + 3] & 255;
   }

   public long readI64() throws TException {
      byte[] buf = this.inoutTemp;
      int off = 0;
      if (this.trans_.getBytesRemainingInBuffer() >= 8) {
         buf = this.trans_.getBuffer();
         off = this.trans_.getBufferPosition();
         this.trans_.consumeBuffer(8);
      } else {
         this.readAll(this.inoutTemp, 0, 8);
      }

      return (long)(buf[off] & 255) << 56 | (long)(buf[off + 1] & 255) << 48 | (long)(buf[off + 2] & 255) << 40 | (long)(buf[off + 3] & 255) << 32 | (long)(buf[off + 4] & 255) << 24 | (long)(buf[off + 5] & 255) << 16 | (long)(buf[off + 6] & 255) << 8 | (long)(buf[off + 7] & 255);
   }

   public UUID readUuid() throws TException {
      byte[] buf = this.inoutTemp;
      int off = 0;
      if (this.trans_.getBytesRemainingInBuffer() >= 16) {
         buf = this.trans_.getBuffer();
         off = this.trans_.getBufferPosition();
         this.trans_.consumeBuffer(16);
      } else {
         this.readAll(this.inoutTemp, 0, 16);
      }

      long lsb = (long)(buf[off] & 255) << 56 | (long)(buf[off + 1] & 255) << 48 | (long)(buf[off + 2] & 255) << 40 | (long)(buf[off + 3] & 255) << 32 | (long)(buf[off + 4] & 255) << 24 | (long)(buf[off + 5] & 255) << 16 | (long)(buf[off + 6] & 255) << 8 | (long)(buf[off + 7] & 255);
      long msb = (long)(buf[off + 8] & 255) << 56 | (long)(buf[off + 8 + 1] & 255) << 48 | (long)(buf[off + 8 + 2] & 255) << 40 | (long)(buf[off + 8 + 3] & 255) << 32 | (long)(buf[off + 8 + 4] & 255) << 24 | (long)(buf[off + 8 + 5] & 255) << 16 | (long)(buf[off + 8 + 6] & 255) << 8 | (long)(buf[off + 8 + 7] & 255);
      return new UUID(msb, lsb);
   }

   public double readDouble() throws TException {
      return Double.longBitsToDouble(this.readI64());
   }

   public String readString() throws TException {
      int size = this.readI32();
      if (this.trans_.getBytesRemainingInBuffer() >= size) {
         String s = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), size, StandardCharsets.UTF_8);
         this.trans_.consumeBuffer(size);
         return s;
      } else {
         return this.readStringBody(size);
      }
   }

   public String readStringBody(int size) throws TException {
      this.checkStringReadLength(size);
      byte[] buf = new byte[size];
      this.trans_.readAll(buf, 0, size);
      return new String(buf, StandardCharsets.UTF_8);
   }

   public ByteBuffer readBinary() throws TException {
      int size = this.readI32();
      this.checkStringReadLength(size);
      if (this.trans_.getBytesRemainingInBuffer() >= size) {
         ByteBuffer bb = ByteBuffer.wrap(this.trans_.getBuffer(), this.trans_.getBufferPosition(), size);
         this.trans_.consumeBuffer(size);
         return bb;
      } else {
         byte[] buf = new byte[size];
         this.trans_.readAll(buf, 0, size);
         return ByteBuffer.wrap(buf);
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

   private int readAll(byte[] buf, int off, int len) throws TException {
      return this.trans_.readAll(buf, off, len);
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
            return 2;
         case 8:
            return 4;
         case 10:
            return 8;
         case 11:
            return 4;
         case 12:
            return 0;
         case 13:
            return 4;
         case 14:
            return 4;
         case 15:
            return 4;
      }
   }

   public int readFieldBeginData() throws TException {
      byte type = this.readByte();
      if (type == 0) {
         return TFieldData.encode(type);
      } else {
         short id = this.readI16();
         return TFieldData.encode(type, id);
      }
   }

   protected void skipBool() throws TException {
      this.skipBytes(1);
   }

   protected void skipByte() throws TException {
      this.skipBytes(1);
   }

   protected void skipI16() throws TException {
      this.skipBytes(2);
   }

   protected void skipI32() throws TException {
      this.skipBytes(4);
   }

   protected void skipI64() throws TException {
      this.skipBytes(8);
   }

   protected void skipDouble() throws TException {
      this.skipBytes(8);
   }

   protected void skipBinary() throws TException {
      int size = this.readI32();
      this.skipBytes(size);
   }

   public static class Factory implements TProtocolFactory {
      protected long stringLengthLimit_;
      protected long containerLengthLimit_;
      protected boolean strictRead_;
      protected boolean strictWrite_;

      public Factory() {
         this(false, true);
      }

      public Factory(boolean strictRead, boolean strictWrite) {
         this(strictRead, strictWrite, -1L, -1L);
      }

      public Factory(long stringLengthLimit, long containerLengthLimit) {
         this(false, true, stringLengthLimit, containerLengthLimit);
      }

      public Factory(boolean strictRead, boolean strictWrite, long stringLengthLimit, long containerLengthLimit) {
         this.stringLengthLimit_ = stringLengthLimit;
         this.containerLengthLimit_ = containerLengthLimit;
         this.strictRead_ = strictRead;
         this.strictWrite_ = strictWrite;
      }

      public TProtocol getProtocol(TTransport trans) {
         return new TBinaryProtocol(trans, this.stringLengthLimit_, this.containerLengthLimit_, this.strictRead_, this.strictWrite_);
      }
   }
}
