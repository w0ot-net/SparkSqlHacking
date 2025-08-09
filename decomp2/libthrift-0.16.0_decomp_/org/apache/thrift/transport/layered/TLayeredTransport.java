package org.apache.thrift.transport.layered;

import java.util.Objects;
import org.apache.thrift.TConfiguration;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public abstract class TLayeredTransport extends TTransport {
   private TTransport innerTransport;

   public TConfiguration getConfiguration() {
      return this.innerTransport.getConfiguration();
   }

   public TLayeredTransport(TTransport transport) {
      Objects.requireNonNull(transport, "TTransport cannot be null.");
      this.innerTransport = transport;
   }

   public void updateKnownMessageSize(long size) throws TTransportException {
      this.innerTransport.updateKnownMessageSize(size);
   }

   public void checkReadBytesAvailable(long numBytes) throws TTransportException {
      this.innerTransport.checkReadBytesAvailable(numBytes);
   }

   public TTransport getInnerTransport() {
      return this.innerTransport;
   }
}
