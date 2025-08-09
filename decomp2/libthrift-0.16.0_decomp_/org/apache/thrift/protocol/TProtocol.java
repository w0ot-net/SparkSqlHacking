package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.partial.TFieldData;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.transport.TTransport;

public abstract class TProtocol {
   protected TTransport trans_;
   static final int MAX_SKIPPED_BYTES = 256;
   protected byte[] skippedBytes = new byte[256];

   private TProtocol() {
   }

   protected TProtocol(TTransport trans) {
      this.trans_ = trans;
   }

   public TTransport getTransport() {
      return this.trans_;
   }

   protected void checkReadBytesAvailable(TMap map) throws TException {
      long elemSize = (long)(this.getMinSerializedSize(map.keyType) + this.getMinSerializedSize(map.valueType));
      this.trans_.checkReadBytesAvailable((long)map.size * elemSize);
   }

   protected void checkReadBytesAvailable(TList list) throws TException {
      this.trans_.checkReadBytesAvailable((long)(list.size * this.getMinSerializedSize(list.elemType)));
   }

   protected void checkReadBytesAvailable(TSet set) throws TException {
      this.trans_.checkReadBytesAvailable((long)(set.size * this.getMinSerializedSize(set.elemType)));
   }

   public abstract int getMinSerializedSize(byte var1) throws TException;

   public abstract void writeMessageBegin(TMessage var1) throws TException;

   public abstract void writeMessageEnd() throws TException;

   public abstract void writeStructBegin(TStruct var1) throws TException;

   public abstract void writeStructEnd() throws TException;

   public abstract void writeFieldBegin(TField var1) throws TException;

   public abstract void writeFieldEnd() throws TException;

   public abstract void writeFieldStop() throws TException;

   public abstract void writeMapBegin(TMap var1) throws TException;

   public abstract void writeMapEnd() throws TException;

   public abstract void writeListBegin(TList var1) throws TException;

   public abstract void writeListEnd() throws TException;

   public abstract void writeSetBegin(TSet var1) throws TException;

   public abstract void writeSetEnd() throws TException;

   public abstract void writeBool(boolean var1) throws TException;

   public abstract void writeByte(byte var1) throws TException;

   public abstract void writeI16(short var1) throws TException;

   public abstract void writeI32(int var1) throws TException;

   public abstract void writeI64(long var1) throws TException;

   public abstract void writeDouble(double var1) throws TException;

   public abstract void writeString(String var1) throws TException;

   public abstract void writeBinary(ByteBuffer var1) throws TException;

   public abstract TMessage readMessageBegin() throws TException;

   public abstract void readMessageEnd() throws TException;

   public abstract TStruct readStructBegin() throws TException;

   public abstract void readStructEnd() throws TException;

   public abstract TField readFieldBegin() throws TException;

   public abstract void readFieldEnd() throws TException;

   public abstract TMap readMapBegin() throws TException;

   public abstract void readMapEnd() throws TException;

   public abstract TList readListBegin() throws TException;

   public abstract void readListEnd() throws TException;

   public abstract TSet readSetBegin() throws TException;

   public abstract void readSetEnd() throws TException;

   public abstract boolean readBool() throws TException;

   public abstract byte readByte() throws TException;

   public abstract short readI16() throws TException;

   public abstract int readI32() throws TException;

   public abstract long readI64() throws TException;

   public abstract double readDouble() throws TException;

   public abstract String readString() throws TException;

   public abstract ByteBuffer readBinary() throws TException;

   public void reset() {
   }

   public Class getScheme() {
      return StandardScheme.class;
   }

   public int readFieldBeginData() throws TException {
      TField tfield = this.readFieldBegin();
      return TFieldData.encode(tfield.type, tfield.id);
   }

   public void skip(byte fieldType) throws TException {
      this.skip(fieldType, Integer.MAX_VALUE);
   }

   public void skip(byte fieldType, int maxDepth) throws TException {
      if (maxDepth <= 0) {
         throw new TException("Maximum skip depth exceeded");
      } else {
         switch (fieldType) {
            case 2:
               this.skipBool();
               break;
            case 3:
               this.skipByte();
               break;
            case 4:
               this.skipDouble();
               break;
            case 5:
            case 7:
            case 9:
            default:
               throw new TProtocolException(1, "Unrecognized type " + fieldType);
            case 6:
               this.skipI16();
               break;
            case 8:
               this.skipI32();
               break;
            case 10:
               this.skipI64();
               break;
            case 11:
               this.skipBinary();
               break;
            case 12:
               this.readStructBegin();

               while(true) {
                  int tfieldData = this.readFieldBeginData();
                  byte tfieldType = TFieldData.getType(tfieldData);
                  if (tfieldType == 0) {
                     this.readStructEnd();
                     return;
                  }

                  this.skip(tfieldType, maxDepth - 1);
                  this.readFieldEnd();
               }
            case 13:
               TMap map = this.readMapBegin();

               for(int i = 0; i < map.size; ++i) {
                  this.skip(map.keyType, maxDepth - 1);
                  this.skip(map.valueType, maxDepth - 1);
               }

               this.readMapEnd();
               break;
            case 14:
               TSet set = this.readSetBegin();

               for(int i = 0; i < set.size; ++i) {
                  this.skip(set.elemType, maxDepth - 1);
               }

               this.readSetEnd();
               break;
            case 15:
               TList list = this.readListBegin();

               for(int i = 0; i < list.size; ++i) {
                  this.skip(list.elemType, maxDepth - 1);
               }

               this.readListEnd();
         }

      }
   }

   protected void skipBool() throws TException {
      this.readBool();
   }

   protected void skipByte() throws TException {
      this.readByte();
   }

   protected void skipI16() throws TException {
      this.readI16();
   }

   protected void skipI32() throws TException {
      this.readI32();
   }

   protected void skipI64() throws TException {
      this.readI64();
   }

   protected void skipDouble() throws TException {
      this.readDouble();
   }

   protected void skipBinary() throws TException {
      this.readBinary();
   }

   protected void skipBytes(int numBytes) throws TException {
      if (numBytes <= 256) {
         if (this.getTransport().getBytesRemainingInBuffer() >= numBytes) {
            this.getTransport().consumeBuffer(numBytes);
         } else {
            this.getTransport().readAll(this.skippedBytes, 0, numBytes);
         }
      } else {
         for(int remaining = numBytes; remaining > 0; remaining -= 256) {
            this.skipBytes(Math.min(remaining, 256));
         }
      }

   }
}
