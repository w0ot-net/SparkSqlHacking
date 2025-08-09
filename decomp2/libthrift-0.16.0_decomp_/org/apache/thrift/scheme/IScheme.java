package org.apache.thrift.scheme;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public interface IScheme {
   void read(TProtocol var1, TBase var2) throws TException;

   void write(TProtocol var1, TBase var2) throws TException;
}
