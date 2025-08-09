package org.apache.thrift;

import org.apache.thrift.protocol.TProtocol;

public interface TProcessor {
   void process(TProtocol var1, TProtocol var2) throws TException;
}
