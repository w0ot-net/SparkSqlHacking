package org.apache.thrift.transport;

import java.util.Objects;
import org.apache.thrift.TConfiguration;

public abstract class TEndpointTransport extends TTransport {
   protected long knownMessageSize;
   protected long remainingMessageSize;
   private TConfiguration _configuration;

   protected long getMaxMessageSize() {
      return (long)this.getConfiguration().getMaxMessageSize();
   }

   public TConfiguration getConfiguration() {
      return this._configuration;
   }

   public TEndpointTransport(TConfiguration config) throws TTransportException {
      this._configuration = Objects.isNull(config) ? new TConfiguration() : config;
      this.resetConsumedMessageSize(-1L);
   }

   protected void resetConsumedMessageSize(long newSize) throws TTransportException {
      if (newSize < 0L) {
         this.knownMessageSize = this.getMaxMessageSize();
         this.remainingMessageSize = this.getMaxMessageSize();
      } else if (newSize > this.knownMessageSize) {
         throw new TTransportException(4, "MaxMessageSize reached");
      } else {
         this.knownMessageSize = newSize;
         this.remainingMessageSize = newSize;
      }
   }

   public void updateKnownMessageSize(long size) throws TTransportException {
      long consumed = this.knownMessageSize - this.remainingMessageSize;
      this.resetConsumedMessageSize(size == 0L ? -1L : size);
      this.countConsumedMessageBytes(consumed);
   }

   public void checkReadBytesAvailable(long numBytes) throws TTransportException {
      if (this.remainingMessageSize < numBytes) {
         throw new TTransportException(4, "MaxMessageSize reached");
      }
   }

   protected void countConsumedMessageBytes(long numBytes) throws TTransportException {
      if (this.remainingMessageSize >= numBytes) {
         this.remainingMessageSize -= numBytes;
      } else {
         this.remainingMessageSize = 0L;
         throw new TTransportException(4, "MaxMessageSize reached");
      }
   }
}
