package shaded.parquet.org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import java.util.UUID;
import shaded.parquet.org.apache.thrift.TException;

public interface TWriteProtocol {
   void writeMessageBegin(TMessage var1) throws TException;

   void writeMessageEnd() throws TException;

   void writeStructBegin(TStruct var1) throws TException;

   void writeStructEnd() throws TException;

   void writeFieldBegin(TField var1) throws TException;

   void writeFieldEnd() throws TException;

   void writeFieldStop() throws TException;

   void writeMapBegin(TMap var1) throws TException;

   void writeMapEnd() throws TException;

   void writeListBegin(TList var1) throws TException;

   void writeListEnd() throws TException;

   void writeSetBegin(TSet var1) throws TException;

   void writeSetEnd() throws TException;

   void writeBool(boolean var1) throws TException;

   void writeByte(byte var1) throws TException;

   void writeI16(short var1) throws TException;

   void writeI32(int var1) throws TException;

   void writeI64(long var1) throws TException;

   void writeUuid(UUID var1) throws TException;

   void writeDouble(double var1) throws TException;

   void writeString(String var1) throws TException;

   void writeBinary(ByteBuffer var1) throws TException;
}
