package org.apache.thrift.server;

import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

public interface TServerEventHandler {
   void preServe();

   ServerContext createContext(TProtocol var1, TProtocol var2);

   void deleteContext(ServerContext var1, TProtocol var2, TProtocol var3);

   void processContext(ServerContext var1, TTransport var2, TTransport var3);
}
