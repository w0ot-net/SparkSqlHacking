package org.sparkproject.jetty.server;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject("AbstractNetworkConnector")
public abstract class AbstractNetworkConnector extends AbstractConnector implements NetworkConnector {
   private volatile String _host;
   private volatile int _port = 0;

   public AbstractNetworkConnector(Server server, Executor executor, Scheduler scheduler, ByteBufferPool pool, int acceptors, ConnectionFactory... factories) {
      super(server, executor, scheduler, pool, acceptors, factories);
   }

   public void setHost(String host) {
      this._host = host;
   }

   @ManagedAttribute("The network interface this connector binds to as an IP address or a hostname.  If null or 0.0.0.0, then bind to all interfaces.")
   public String getHost() {
      return this._host;
   }

   public void setPort(int port) {
      this._port = port;
   }

   @ManagedAttribute("Port this connector listens on. If set the 0 a random port is assigned which may be obtained with getLocalPort()")
   public int getPort() {
      return this._port;
   }

   public int getLocalPort() {
      return -1;
   }

   protected void doStart() throws Exception {
      this.open();
      super.doStart();
   }

   protected void doStop() throws Exception {
      this.close();
      super.doStop();
   }

   public void open() throws IOException {
   }

   public void close() {
   }

   public CompletableFuture shutdown() {
      this.close();
      return super.shutdown();
   }

   protected boolean handleAcceptFailure(Throwable ex) {
      if (this.isOpen()) {
         return super.handleAcceptFailure(ex);
      } else {
         LOG.trace("IGNORED", ex);
         return false;
      }
   }

   public String toString() {
      return String.format("%s{%s:%d}", super.toString(), this.getHost() == null ? "0.0.0.0" : this.getHost(), this.getLocalPort() <= 0 ? this.getPort() : this.getLocalPort());
   }
}
