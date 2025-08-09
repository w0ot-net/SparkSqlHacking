package org.apache.thrift;

import org.apache.thrift.protocol.TProtocol;

public interface TServiceClientFactory {
   TServiceClient getClient(TProtocol var1);

   TServiceClient getClient(TProtocol var1, TProtocol var2);
}
