package org.apache.parquet.format;

import java.nio.ByteBuffer;
import java.util.UUID;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TMap;
import shaded.parquet.org.apache.thrift.protocol.TMessage;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TSet;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.transport.TTransport;

public class InterningProtocol extends TProtocol {
   private final TProtocol delegate;

   public InterningProtocol(TProtocol delegate) {
      super(delegate.getTransport());
      this.delegate = delegate;
   }

   public TTransport getTransport() {
      return this.delegate.getTransport();
   }

   public void writeMessageBegin(TMessage message) throws TException {
      this.delegate.writeMessageBegin(message);
   }

   public void writeMessageEnd() throws TException {
      this.delegate.writeMessageEnd();
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   public void writeStructBegin(TStruct struct) throws TException {
      this.delegate.writeStructBegin(struct);
   }

   public void writeStructEnd() throws TException {
      this.delegate.writeStructEnd();
   }

   public void writeFieldBegin(TField field) throws TException {
      this.delegate.writeFieldBegin(field);
   }

   public void writeFieldEnd() throws TException {
      this.delegate.writeFieldEnd();
   }

   public void writeFieldStop() throws TException {
      this.delegate.writeFieldStop();
   }

   public void writeMapBegin(TMap map) throws TException {
      this.delegate.writeMapBegin(map);
   }

   public void writeMapEnd() throws TException {
      this.delegate.writeMapEnd();
   }

   public void writeListBegin(TList list) throws TException {
      this.delegate.writeListBegin(list);
   }

   public void writeListEnd() throws TException {
      this.delegate.writeListEnd();
   }

   public void writeSetBegin(TSet set) throws TException {
      this.delegate.writeSetBegin(set);
   }

   public void writeSetEnd() throws TException {
      this.delegate.writeSetEnd();
   }

   public void writeBool(boolean b) throws TException {
      this.delegate.writeBool(b);
   }

   public void writeByte(byte b) throws TException {
      this.delegate.writeByte(b);
   }

   public void writeI16(short i16) throws TException {
      this.delegate.writeI16(i16);
   }

   public void writeI32(int i32) throws TException {
      this.delegate.writeI32(i32);
   }

   public void writeI64(long i64) throws TException {
      this.delegate.writeI64(i64);
   }

   public void writeUuid(UUID uuid) throws TException {
      this.delegate.writeUuid(uuid);
   }

   public void writeDouble(double dub) throws TException {
      this.delegate.writeDouble(dub);
   }

   public void writeString(String str) throws TException {
      this.delegate.writeString(str);
   }

   public void writeBinary(ByteBuffer buf) throws TException {
      this.delegate.writeBinary(buf);
   }

   public TMessage readMessageBegin() throws TException {
      return this.delegate.readMessageBegin();
   }

   public void readMessageEnd() throws TException {
      this.delegate.readMessageEnd();
   }

   public TStruct readStructBegin() throws TException {
      return this.delegate.readStructBegin();
   }

   public void readStructEnd() throws TException {
      this.delegate.readStructEnd();
   }

   public TField readFieldBegin() throws TException {
      return this.delegate.readFieldBegin();
   }

   public void readFieldEnd() throws TException {
      this.delegate.readFieldEnd();
   }

   public TMap readMapBegin() throws TException {
      return this.delegate.readMapBegin();
   }

   public void readMapEnd() throws TException {
      this.delegate.readMapEnd();
   }

   public TList readListBegin() throws TException {
      return this.delegate.readListBegin();
   }

   public void readListEnd() throws TException {
      this.delegate.readListEnd();
   }

   public TSet readSetBegin() throws TException {
      return this.delegate.readSetBegin();
   }

   public void readSetEnd() throws TException {
      this.delegate.readSetEnd();
   }

   public boolean equals(Object obj) {
      return this.delegate.equals(obj);
   }

   public boolean readBool() throws TException {
      return this.delegate.readBool();
   }

   public byte readByte() throws TException {
      return this.delegate.readByte();
   }

   public short readI16() throws TException {
      return this.delegate.readI16();
   }

   public int readI32() throws TException {
      return this.delegate.readI32();
   }

   public long readI64() throws TException {
      return this.delegate.readI64();
   }

   public UUID readUuid() throws TException {
      return this.delegate.readUuid();
   }

   public double readDouble() throws TException {
      return this.delegate.readDouble();
   }

   public String readString() throws TException {
      return this.delegate.readString().intern();
   }

   public ByteBuffer readBinary() throws TException {
      return this.delegate.readBinary();
   }

   public void reset() {
      this.delegate.reset();
   }

   public String toString() {
      return this.delegate.toString();
   }

   public int getMinSerializedSize(byte type) throws TException {
      return this.delegate.getMinSerializedSize(type);
   }
}
