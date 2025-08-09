package org.apache.thrift.async;

import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingTransport;

public abstract class TAsyncClient {
   protected final TProtocolFactory ___protocolFactory;
   protected final TNonblockingTransport ___transport;
   protected final TAsyncClientManager ___manager;
   protected TAsyncMethodCall ___currentMethod;
   private Exception ___error;
   private long ___timeout;

   public TAsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager manager, TNonblockingTransport transport) {
      this(protocolFactory, manager, transport, 0L);
   }

   public TAsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager manager, TNonblockingTransport transport, long timeout) {
      this.___protocolFactory = protocolFactory;
      this.___manager = manager;
      this.___transport = transport;
      this.___timeout = timeout;
   }

   public TProtocolFactory getProtocolFactory() {
      return this.___protocolFactory;
   }

   public long getTimeout() {
      return this.___timeout;
   }

   public boolean hasTimeout() {
      return this.___timeout > 0L;
   }

   public void setTimeout(long timeout) {
      this.___timeout = timeout;
   }

   public boolean hasError() {
      return this.___error != null;
   }

   public Exception getError() {
      return this.___error;
   }

   protected void checkReady() {
      if (this.___currentMethod != null) {
         throw new IllegalStateException("Client is currently executing another method: " + this.___currentMethod.getClass().getName());
      } else if (this.___error != null) {
         throw new IllegalStateException("Client has an error!", this.___error);
      }
   }

   protected void onComplete() {
      this.___currentMethod = null;
   }

   protected void onError(Exception exception) {
      this.___transport.close();
      this.___currentMethod = null;
      this.___error = exception;
   }
}
