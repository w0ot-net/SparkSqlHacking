package org.apache.thrift.transport;

import java.nio.channels.Selector;

public abstract class TNonblockingServerTransport extends TServerTransport {
   public abstract void registerSelector(Selector var1);

   public abstract TNonblockingTransport accept() throws TTransportException;
}
