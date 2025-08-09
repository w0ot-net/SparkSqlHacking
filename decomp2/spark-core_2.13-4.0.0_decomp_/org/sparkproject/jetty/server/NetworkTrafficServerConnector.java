package org.sparkproject.jetty.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.ManagedSelector;
import org.sparkproject.jetty.io.NetworkTrafficListener;
import org.sparkproject.jetty.io.NetworkTrafficSocketChannelEndPoint;
import org.sparkproject.jetty.io.SocketChannelEndPoint;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.Scheduler;

public class NetworkTrafficServerConnector extends ServerConnector {
   private volatile NetworkTrafficListener listener;

   public NetworkTrafficServerConnector(Server server) {
      this(server, (Executor)null, (Scheduler)null, (ByteBufferPool)null, 0, 0, new HttpConnectionFactory());
   }

   public NetworkTrafficServerConnector(Server server, ConnectionFactory connectionFactory, SslContextFactory.Server sslContextFactory) {
      super(server, sslContextFactory, connectionFactory);
   }

   public NetworkTrafficServerConnector(Server server, ConnectionFactory connectionFactory) {
      super(server, connectionFactory);
   }

   public NetworkTrafficServerConnector(Server server, Executor executor, Scheduler scheduler, ByteBufferPool pool, int acceptors, int selectors, ConnectionFactory... factories) {
      super(server, executor, scheduler, pool, acceptors, selectors, factories);
   }

   public NetworkTrafficServerConnector(Server server, SslContextFactory.Server sslContextFactory) {
      super(server, sslContextFactory);
   }

   public void setNetworkTrafficListener(NetworkTrafficListener listener) {
      this.listener = listener;
   }

   public NetworkTrafficListener getNetworkTrafficListener() {
      return this.listener;
   }

   protected SocketChannelEndPoint newEndPoint(SocketChannel channel, ManagedSelector selectSet, SelectionKey key) {
      return new NetworkTrafficSocketChannelEndPoint(channel, selectSet, key, this.getScheduler(), this.getIdleTimeout(), this.getNetworkTrafficListener());
   }
}
