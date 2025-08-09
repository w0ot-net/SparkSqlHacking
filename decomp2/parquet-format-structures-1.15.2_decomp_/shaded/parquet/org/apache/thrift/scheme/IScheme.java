package shaded.parquet.org.apache.thrift.scheme;

import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;

public interface IScheme {
   void read(TProtocol var1, TBase var2) throws TException;

   void write(TProtocol var1, TBase var2) throws TException;
}
