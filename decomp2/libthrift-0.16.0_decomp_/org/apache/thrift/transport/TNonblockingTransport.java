package org.apache.thrift.transport;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import org.apache.thrift.TConfiguration;

public abstract class TNonblockingTransport extends TEndpointTransport {
   public TNonblockingTransport(TConfiguration config) throws TTransportException {
      super(config);
   }

   public abstract boolean startConnect() throws IOException;

   public abstract boolean finishConnect() throws IOException;

   public abstract SelectionKey registerSelector(Selector var1, int var2) throws IOException;
}
