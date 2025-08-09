package shaded.parquet.org.apache.thrift;

import shaded.parquet.org.apache.thrift.protocol.TProtocol;

public interface TSerializable {
   void read(TProtocol var1) throws TException;

   void write(TProtocol var1) throws TException;
}
