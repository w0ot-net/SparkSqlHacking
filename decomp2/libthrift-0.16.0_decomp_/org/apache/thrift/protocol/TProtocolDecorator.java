package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;

public abstract class TProtocolDecorator extends TProtocol {
   private final TProtocol concreteProtocol;

   public TProtocolDecorator(TProtocol protocol) {
      super(protocol.getTransport());
      this.concreteProtocol = protocol;
   }

   public void writeMessageBegin(TMessage tMessage) throws TException {
      this.concreteProtocol.writeMessageBegin(tMessage);
   }

   public void writeMessageEnd() throws TException {
      this.concreteProtocol.writeMessageEnd();
   }

   public void writeStructBegin(TStruct tStruct) throws TException {
      this.concreteProtocol.writeStructBegin(tStruct);
   }

   public void writeStructEnd() throws TException {
      this.concreteProtocol.writeStructEnd();
   }

   public void writeFieldBegin(TField tField) throws TException {
      this.concreteProtocol.writeFieldBegin(tField);
   }

   public void writeFieldEnd() throws TException {
      this.concreteProtocol.writeFieldEnd();
   }

   public void writeFieldStop() throws TException {
      this.concreteProtocol.writeFieldStop();
   }

   public void writeMapBegin(TMap tMap) throws TException {
      this.concreteProtocol.writeMapBegin(tMap);
   }

   public void writeMapEnd() throws TException {
      this.concreteProtocol.writeMapEnd();
   }

   public void writeListBegin(TList tList) throws TException {
      this.concreteProtocol.writeListBegin(tList);
   }

   public void writeListEnd() throws TException {
      this.concreteProtocol.writeListEnd();
   }

   public void writeSetBegin(TSet tSet) throws TException {
      this.concreteProtocol.writeSetBegin(tSet);
   }

   public void writeSetEnd() throws TException {
      this.concreteProtocol.writeSetEnd();
   }

   public void writeBool(boolean b) throws TException {
      this.concreteProtocol.writeBool(b);
   }

   public void writeByte(byte b) throws TException {
      this.concreteProtocol.writeByte(b);
   }

   public void writeI16(short i) throws TException {
      this.concreteProtocol.writeI16(i);
   }

   public void writeI32(int i) throws TException {
      this.concreteProtocol.writeI32(i);
   }

   public void writeI64(long l) throws TException {
      this.concreteProtocol.writeI64(l);
   }

   public void writeDouble(double v) throws TException {
      this.concreteProtocol.writeDouble(v);
   }

   public void writeString(String s) throws TException {
      this.concreteProtocol.writeString(s);
   }

   public void writeBinary(ByteBuffer buf) throws TException {
      this.concreteProtocol.writeBinary(buf);
   }

   public TMessage readMessageBegin() throws TException {
      return this.concreteProtocol.readMessageBegin();
   }

   public void readMessageEnd() throws TException {
      this.concreteProtocol.readMessageEnd();
   }

   public TStruct readStructBegin() throws TException {
      return this.concreteProtocol.readStructBegin();
   }

   public void readStructEnd() throws TException {
      this.concreteProtocol.readStructEnd();
   }

   public TField readFieldBegin() throws TException {
      return this.concreteProtocol.readFieldBegin();
   }

   public void readFieldEnd() throws TException {
      this.concreteProtocol.readFieldEnd();
   }

   public TMap readMapBegin() throws TException {
      return this.concreteProtocol.readMapBegin();
   }

   public void readMapEnd() throws TException {
      this.concreteProtocol.readMapEnd();
   }

   public TList readListBegin() throws TException {
      return this.concreteProtocol.readListBegin();
   }

   public void readListEnd() throws TException {
      this.concreteProtocol.readListEnd();
   }

   public TSet readSetBegin() throws TException {
      return this.concreteProtocol.readSetBegin();
   }

   public void readSetEnd() throws TException {
      this.concreteProtocol.readSetEnd();
   }

   public boolean readBool() throws TException {
      return this.concreteProtocol.readBool();
   }

   public byte readByte() throws TException {
      return this.concreteProtocol.readByte();
   }

   public short readI16() throws TException {
      return this.concreteProtocol.readI16();
   }

   public int readI32() throws TException {
      return this.concreteProtocol.readI32();
   }

   public long readI64() throws TException {
      return this.concreteProtocol.readI64();
   }

   public double readDouble() throws TException {
      return this.concreteProtocol.readDouble();
   }

   public String readString() throws TException {
      return this.concreteProtocol.readString();
   }

   public ByteBuffer readBinary() throws TException {
      return this.concreteProtocol.readBinary();
   }

   public int getMinSerializedSize(byte type) throws TException {
      return this.concreteProtocol.getMinSerializedSize(type);
   }
}
