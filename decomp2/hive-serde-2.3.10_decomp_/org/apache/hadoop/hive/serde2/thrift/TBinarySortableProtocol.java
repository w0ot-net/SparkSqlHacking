package org.apache.hadoop.hive.serde2.thrift;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TBinarySortableProtocol extends TProtocol implements ConfigurableTProtocol, WriteNullsProtocol, WriteTextProtocol {
   static final Logger LOG = LoggerFactory.getLogger(TBinarySortableProtocol.class.getName());
   static byte ORDERED_TYPE = -1;
   int stackLevel = 0;
   int topLevelStructFieldID;
   String sortOrder;
   boolean ascending;
   byte[] rawBytesBuffer;
   private final byte[] bout = new byte[1];
   private final byte[] i16out = new byte[2];
   private final byte[] i32out = new byte[4];
   private final byte[] i64out = new byte[8];
   protected final byte[] nullByte = new byte[]{0};
   protected final byte[] nonNullByte = new byte[]{1};
   protected final byte[] escapedNull = new byte[]{1, 1};
   protected final byte[] escapedOne = new byte[]{1, 2};
   TStruct tstruct = new TStruct();
   TField f = null;
   private TMap tmap = null;
   private TList tlist = null;
   private TSet set = null;
   private final byte[] wasNull = new byte[1];
   private final byte[] bin = new byte[1];
   private final byte[] i16rd = new byte[2];
   private final byte[] i32rd = new byte[4];
   private final byte[] i64rd = new byte[8];
   private byte[] stringBytes = new byte[1000];
   boolean lastPrimitiveWasNull;

   public TBinarySortableProtocol(TTransport trans) {
      super(trans);
   }

   public int getMinSerializedSize(byte b) throws TException {
      return -1;
   }

   public void initialize(Configuration conf, Properties tbl) throws TException {
      this.sortOrder = tbl.getProperty("serialization.sort.order");
      if (this.sortOrder == null) {
         this.sortOrder = "";
      }

      for(int i = 0; i < this.sortOrder.length(); ++i) {
         char c = this.sortOrder.charAt(i);
         if (c != '+' && c != '-') {
            throw new TException("serialization.sort.order should be a string consists of only '+' and '-'!");
         }
      }

      LOG.info("Sort order is \"" + this.sortOrder + "\"");
   }

   public void writeMessageBegin(TMessage message) throws TException {
   }

   public void writeMessageEnd() throws TException {
   }

   public void writeStructBegin(TStruct struct) throws TException {
      ++this.stackLevel;
      if (this.stackLevel == 1) {
         this.topLevelStructFieldID = 0;
         this.ascending = this.topLevelStructFieldID >= this.sortOrder.length() || this.sortOrder.charAt(this.topLevelStructFieldID) != '-';
      } else {
         this.writeRawBytes(this.nonNullByte, 0, 1);
      }

   }

   public void writeStructEnd() throws TException {
      --this.stackLevel;
   }

   public void writeFieldBegin(TField field) throws TException {
   }

   public void writeFieldEnd() throws TException {
      if (this.stackLevel == 1) {
         ++this.topLevelStructFieldID;
         this.ascending = this.topLevelStructFieldID >= this.sortOrder.length() || this.sortOrder.charAt(this.topLevelStructFieldID) != '-';
      }

   }

   public void writeFieldStop() {
   }

   public void writeMapBegin(TMap map) throws TException {
      ++this.stackLevel;
      if (map == null) {
         this.writeRawBytes(this.nonNullByte, 0, 1);
      } else {
         this.writeI32(map.size);
      }

   }

   public void writeMapEnd() throws TException {
      --this.stackLevel;
   }

   public void writeListBegin(TList list) throws TException {
      ++this.stackLevel;
      if (list == null) {
         this.writeRawBytes(this.nonNullByte, 0, 1);
      } else {
         this.writeI32(list.size);
      }

   }

   public void writeListEnd() throws TException {
      --this.stackLevel;
   }

   public void writeSetBegin(TSet set) throws TException {
      ++this.stackLevel;
      if (set == null) {
         this.writeRawBytes(this.nonNullByte, 0, 1);
      } else {
         this.writeI32(set.size);
      }

   }

   public void writeSetEnd() throws TException {
      --this.stackLevel;
   }

   private void writeRawBytes(byte[] bytes, int begin, int length) throws TException {
      if (this.ascending) {
         this.trans_.write(bytes, begin, length);
      } else {
         if (this.rawBytesBuffer == null || this.rawBytesBuffer.length < bytes.length) {
            this.rawBytesBuffer = new byte[bytes.length];
         }

         for(int i = begin; i < begin + length; ++i) {
            this.rawBytesBuffer[i] = (byte)(~bytes[i]);
         }

         this.trans_.write(this.rawBytesBuffer, begin, length);
      }

   }

   public void writeBool(boolean b) throws TException {
      this.bout[0] = (byte)(b ? 2 : 1);
      this.writeRawBytes(this.bout, 0, 1);
   }

   public void writeByte(byte b) throws TException {
      this.writeRawBytes(this.nonNullByte, 0, 1);
      this.bout[0] = (byte)(b ^ 128);
      this.writeRawBytes(this.bout, 0, 1);
   }

   public void writeI16(short i16) throws TException {
      this.i16out[0] = (byte)(255 & (i16 >> 8 ^ 128));
      this.i16out[1] = (byte)(255 & i16);
      this.writeRawBytes(this.nonNullByte, 0, 1);
      this.writeRawBytes(this.i16out, 0, 2);
   }

   public void writeI32(int i32) throws TException {
      this.i32out[0] = (byte)(255 & (i32 >> 24 ^ 128));
      this.i32out[1] = (byte)(255 & i32 >> 16);
      this.i32out[2] = (byte)(255 & i32 >> 8);
      this.i32out[3] = (byte)(255 & i32);
      this.writeRawBytes(this.nonNullByte, 0, 1);
      this.writeRawBytes(this.i32out, 0, 4);
   }

   public void writeI64(long i64) throws TException {
      this.i64out[0] = (byte)((int)(255L & (i64 >> 56 ^ 128L)));
      this.i64out[1] = (byte)((int)(255L & i64 >> 48));
      this.i64out[2] = (byte)((int)(255L & i64 >> 40));
      this.i64out[3] = (byte)((int)(255L & i64 >> 32));
      this.i64out[4] = (byte)((int)(255L & i64 >> 24));
      this.i64out[5] = (byte)((int)(255L & i64 >> 16));
      this.i64out[6] = (byte)((int)(255L & i64 >> 8));
      this.i64out[7] = (byte)((int)(255L & i64));
      this.writeRawBytes(this.nonNullByte, 0, 1);
      this.writeRawBytes(this.i64out, 0, 8);
   }

   public void writeDouble(double dub) throws TException {
      long i64 = Double.doubleToLongBits(dub);
      if ((i64 & Long.MIN_VALUE) != 0L) {
         this.i64out[0] = (byte)((int)(255L & (i64 >> 56 ^ 255L)));
         this.i64out[1] = (byte)((int)(255L & (i64 >> 48 ^ 255L)));
         this.i64out[2] = (byte)((int)(255L & (i64 >> 40 ^ 255L)));
         this.i64out[3] = (byte)((int)(255L & (i64 >> 32 ^ 255L)));
         this.i64out[4] = (byte)((int)(255L & (i64 >> 24 ^ 255L)));
         this.i64out[5] = (byte)((int)(255L & (i64 >> 16 ^ 255L)));
         this.i64out[6] = (byte)((int)(255L & (i64 >> 8 ^ 255L)));
         this.i64out[7] = (byte)((int)(255L & (i64 ^ 255L)));
      } else {
         this.i64out[0] = (byte)((int)(255L & (i64 >> 56 ^ 128L)));
         this.i64out[1] = (byte)((int)(255L & i64 >> 48));
         this.i64out[2] = (byte)((int)(255L & i64 >> 40));
         this.i64out[3] = (byte)((int)(255L & i64 >> 32));
         this.i64out[4] = (byte)((int)(255L & i64 >> 24));
         this.i64out[5] = (byte)((int)(255L & i64 >> 16));
         this.i64out[6] = (byte)((int)(255L & i64 >> 8));
         this.i64out[7] = (byte)((int)(255L & i64));
      }

      this.writeRawBytes(this.nonNullByte, 0, 1);
      this.writeRawBytes(this.i64out, 0, 8);
   }

   public void writeString(String str) throws TException {
      byte[] dat;
      try {
         dat = str.getBytes("UTF-8");
      } catch (UnsupportedEncodingException uex) {
         throw new TException("JVM DOES NOT SUPPORT UTF-8: ", uex);
      }

      this.writeTextBytes(dat, 0, dat.length);
   }

   public void writeBinary(ByteBuffer bin) throws TException {
      if (bin == null) {
         this.writeRawBytes(this.nullByte, 0, 1);
      } else {
         int length = bin.limit() - bin.position() - bin.arrayOffset();
         if (bin.hasArray()) {
            this.writeBinary(bin.array(), bin.arrayOffset() + bin.position(), length);
         } else {
            byte[] copy = new byte[length];
            bin.get(copy);
            this.writeBinary(copy);
         }

      }
   }

   public void writeBinary(byte[] bin) throws TException {
      if (bin == null) {
         this.writeRawBytes(this.nullByte, 0, 1);
      } else {
         this.writeBinary(bin, 0, bin.length);
      }

   }

   public void writeBinary(byte[] bin, int offset, int length) throws TException {
      if (bin == null) {
         this.writeRawBytes(this.nullByte, 0, 1);
      } else {
         this.writeI32(length);
         this.writeRawBytes(bin, offset, length);
      }

   }

   public TMessage readMessageBegin() throws TException {
      return new TMessage();
   }

   public void readMessageEnd() throws TException {
   }

   public TStruct readStructBegin() throws TException {
      ++this.stackLevel;
      if (this.stackLevel == 1) {
         this.topLevelStructFieldID = 0;
         this.ascending = this.topLevelStructFieldID >= this.sortOrder.length() || this.sortOrder.charAt(this.topLevelStructFieldID) != '-';
      } else if (this.readIsNull()) {
         return null;
      }

      return this.tstruct;
   }

   public void readStructEnd() throws TException {
      --this.stackLevel;
   }

   public TField readFieldBegin() throws TException {
      this.f = new TField("", ORDERED_TYPE, (short)-1);
      return this.f;
   }

   public void readFieldEnd() throws TException {
      if (this.stackLevel == 1) {
         ++this.topLevelStructFieldID;
         this.ascending = this.topLevelStructFieldID >= this.sortOrder.length() || this.sortOrder.charAt(this.topLevelStructFieldID) != '-';
      }

   }

   public TMap readMapBegin() throws TException {
      ++this.stackLevel;
      this.tmap = new TMap(ORDERED_TYPE, ORDERED_TYPE, this.readI32());
      return this.tmap.size == 0 && this.lastPrimitiveWasNull() ? null : this.tmap;
   }

   public void readMapEnd() throws TException {
      --this.stackLevel;
   }

   public TList readListBegin() throws TException {
      ++this.stackLevel;
      this.tlist = new TList(ORDERED_TYPE, this.readI32());
      return this.tlist.size == 0 && this.lastPrimitiveWasNull() ? null : this.tlist;
   }

   public void readListEnd() throws TException {
      --this.stackLevel;
   }

   public TSet readSetBegin() throws TException {
      ++this.stackLevel;
      this.set = new TSet(ORDERED_TYPE, this.readI32());
      return this.set.size == 0 && this.lastPrimitiveWasNull() ? null : this.set;
   }

   public void readSetEnd() throws TException {
      --this.stackLevel;
   }

   private int readRawAll(byte[] buf, int off, int len) throws TException {
      int bytes = this.trans_.readAll(buf, off, len);
      if (!this.ascending) {
         for(int i = off; i < off + bytes; ++i) {
            buf[i] = (byte)(~buf[i]);
         }
      }

      return bytes;
   }

   public boolean readBool() throws TException {
      this.readRawAll(this.bin, 0, 1);
      this.lastPrimitiveWasNull = this.bin[0] == 0;
      return this.lastPrimitiveWasNull ? false : this.bin[0] == 2;
   }

   public final boolean readIsNull() throws TException {
      this.readRawAll(this.wasNull, 0, 1);
      this.lastPrimitiveWasNull = this.wasNull[0] == 0;
      return this.lastPrimitiveWasNull;
   }

   public byte readByte() throws TException {
      if (this.readIsNull()) {
         return 0;
      } else {
         this.readRawAll(this.bin, 0, 1);
         return (byte)(this.bin[0] ^ 128);
      }
   }

   public short readI16() throws TException {
      if (this.readIsNull()) {
         return 0;
      } else {
         this.readRawAll(this.i16rd, 0, 2);
         return (short)(((this.i16rd[0] ^ 128) & 255) << 8 | this.i16rd[1] & 255);
      }
   }

   public int readI32() throws TException {
      if (this.readIsNull()) {
         return 0;
      } else {
         this.readRawAll(this.i32rd, 0, 4);
         return ((this.i32rd[0] ^ 128) & 255) << 24 | (this.i32rd[1] & 255) << 16 | (this.i32rd[2] & 255) << 8 | this.i32rd[3] & 255;
      }
   }

   public long readI64() throws TException {
      if (this.readIsNull()) {
         return 0L;
      } else {
         this.readRawAll(this.i64rd, 0, 8);
         return (long)((this.i64rd[0] ^ 128) & 255) << 56 | (long)(this.i64rd[1] & 255) << 48 | (long)(this.i64rd[2] & 255) << 40 | (long)(this.i64rd[3] & 255) << 32 | (long)(this.i64rd[4] & 255) << 24 | (long)(this.i64rd[5] & 255) << 16 | (long)(this.i64rd[6] & 255) << 8 | (long)(this.i64rd[7] & 255);
      }
   }

   public double readDouble() throws TException {
      if (this.readIsNull()) {
         return (double)0.0F;
      } else {
         this.readRawAll(this.i64rd, 0, 8);
         long v = 0L;
         if ((this.i64rd[0] & 128) != 0) {
            v = (long)((this.i64rd[0] ^ 128) & 255) << 56 | (long)(this.i64rd[1] & 255) << 48 | (long)(this.i64rd[2] & 255) << 40 | (long)(this.i64rd[3] & 255) << 32 | (long)(this.i64rd[4] & 255) << 24 | (long)(this.i64rd[5] & 255) << 16 | (long)(this.i64rd[6] & 255) << 8 | (long)(this.i64rd[7] & 255);
         } else {
            v = (long)((this.i64rd[0] ^ 255) & 255) << 56 | (long)((this.i64rd[1] ^ 255) & 255) << 48 | (long)((this.i64rd[2] ^ 255) & 255) << 40 | (long)((this.i64rd[3] ^ 255) & 255) << 32 | (long)((this.i64rd[4] ^ 255) & 255) << 24 | (long)((this.i64rd[5] ^ 255) & 255) << 16 | (long)((this.i64rd[6] ^ 255) & 255) << 8 | (long)((this.i64rd[7] ^ 255) & 255);
         }

         return Double.longBitsToDouble(v);
      }
   }

   public String readString() throws TException {
      if (this.readIsNull()) {
         return null;
      } else {
         int i = 0;

         while(true) {
            this.readRawAll(this.bin, 0, 1);
            if (this.bin[0] == 0) {
               try {
                  String r = new String(this.stringBytes, 0, i, "UTF-8");
                  return r;
               } catch (UnsupportedEncodingException uex) {
                  throw new TException("JVM DOES NOT SUPPORT UTF-8: ", uex);
               }
            }

            if (this.bin[0] == 1) {
               this.readRawAll(this.bin, 0, 1);

               assert this.bin[0] == 1 || this.bin[0] == 2;

               --this.bin[0];
            }

            if (i == this.stringBytes.length) {
               this.stringBytes = Arrays.copyOf(this.stringBytes, this.stringBytes.length * 2);
            }

            this.stringBytes[i] = this.bin[0];
            ++i;
         }
      }
   }

   public ByteBuffer readBinary() throws TException {
      int size = this.readI32();
      if (this.lastPrimitiveWasNull) {
         return null;
      } else {
         byte[] buf = new byte[size];
         this.readRawAll(buf, 0, size);
         return ByteBuffer.wrap(buf);
      }
   }

   public boolean lastPrimitiveWasNull() throws TException {
      return this.lastPrimitiveWasNull;
   }

   public void writeNull() throws TException {
      this.writeRawBytes(this.nullByte, 0, 1);
   }

   void writeTextBytes(byte[] bytes, int start, int length) throws TException {
      this.writeRawBytes(this.nonNullByte, 0, 1);
      int begin = 0;

      int i;
      for(i = start; i < length; ++i) {
         if (bytes[i] == 0 || bytes[i] == 1) {
            if (i > begin) {
               this.writeRawBytes(bytes, begin, i - begin);
            }

            if (bytes[i] == 0) {
               this.writeRawBytes(this.escapedNull, 0, this.escapedNull.length);
            } else {
               this.writeRawBytes(this.escapedOne, 0, this.escapedOne.length);
            }

            begin = i + 1;
         }
      }

      if (i > begin) {
         this.writeRawBytes(bytes, begin, i - begin);
      }

      this.writeRawBytes(this.nullByte, 0, 1);
   }

   public void writeText(Text text) throws TException {
      this.writeTextBytes(text.getBytes(), 0, text.getLength());
   }

   public static class Factory implements TProtocolFactory {
      public TProtocol getProtocol(TTransport trans) {
         return new TBinarySortableProtocol(trans);
      }
   }
}
