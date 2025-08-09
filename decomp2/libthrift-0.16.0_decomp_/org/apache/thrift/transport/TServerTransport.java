package org.apache.thrift.transport;

import java.io.Closeable;
import java.net.InetSocketAddress;

public abstract class TServerTransport implements Closeable {
   public abstract void listen() throws TTransportException;

   public abstract TTransport accept() throws TTransportException;

   public abstract void close();

   public void interrupt() {
   }

   public abstract static class AbstractServerTransportArgs {
      int backlog = 0;
      int clientTimeout = 0;
      InetSocketAddress bindAddr;

      public AbstractServerTransportArgs backlog(int backlog) {
         this.backlog = backlog;
         return this;
      }

      public AbstractServerTransportArgs clientTimeout(int clientTimeout) {
         this.clientTimeout = clientTimeout;
         return this;
      }

      public AbstractServerTransportArgs port(int port) {
         this.bindAddr = new InetSocketAddress(port);
         return this;
      }

      public AbstractServerTransportArgs bindAddr(InetSocketAddress bindAddr) {
         this.bindAddr = bindAddr;
         return this;
      }
   }
}
