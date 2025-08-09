package org.sparkproject.jetty.io;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProtocolFamily;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketOption;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.channels.NetworkChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.JavaVersion;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject
public class ClientConnector extends ContainerLifeCycle {
   public static final String CLIENT_CONNECTOR_CONTEXT_KEY = "org.sparkproject.jetty.client.connector";
   public static final String REMOTE_SOCKET_ADDRESS_CONTEXT_KEY = "org.sparkproject.jetty.client.connector.remoteSocketAddress";
   public static final String CLIENT_CONNECTION_FACTORY_CONTEXT_KEY = "org.sparkproject.jetty.client.connector.clientConnectionFactory";
   public static final String CONNECTION_PROMISE_CONTEXT_KEY = "org.sparkproject.jetty.client.connector.connectionPromise";
   public static final String APPLICATION_PROTOCOLS_CONTEXT_KEY = "org.sparkproject.jetty.client.connector.applicationProtocols";
   private static final Logger LOG = LoggerFactory.getLogger(ClientConnector.class);
   private final Configurator configurator;
   private Executor executor;
   private Scheduler scheduler;
   private ByteBufferPool byteBufferPool;
   private SslContextFactory.Client sslContextFactory;
   private SelectorManager selectorManager;
   private int selectors;
   private boolean connectBlocking;
   private Duration connectTimeout;
   private Duration idleTimeout;
   private SocketAddress bindAddress;
   private boolean tcpNoDelay;
   private boolean reuseAddress;
   private boolean reusePort;
   private int receiveBufferSize;
   private int sendBufferSize;

   public static ClientConnector forUnixDomain(Path path) {
      return new ClientConnector(ClientConnector.Configurator.forUnixDomain(path));
   }

   public ClientConnector() {
      this(new Configurator());
   }

   public ClientConnector(Configurator configurator) {
      this.selectors = 1;
      this.connectTimeout = Duration.ofSeconds(5L);
      this.idleTimeout = Duration.ofSeconds(30L);
      this.tcpNoDelay = true;
      this.reuseAddress = true;
      this.receiveBufferSize = -1;
      this.sendBufferSize = -1;
      this.configurator = (Configurator)Objects.requireNonNull(configurator);
      this.addBean(configurator);
      configurator.addBean(this, false);
   }

   public boolean isIntrinsicallySecure(SocketAddress address) {
      return this.configurator.isIntrinsicallySecure(this, address);
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public void setExecutor(Executor executor) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.updateBean(this.executor, executor);
         this.executor = executor;
      }
   }

   public Scheduler getScheduler() {
      return this.scheduler;
   }

   public void setScheduler(Scheduler scheduler) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.updateBean(this.scheduler, scheduler);
         this.scheduler = scheduler;
      }
   }

   public ByteBufferPool getByteBufferPool() {
      return this.byteBufferPool;
   }

   public void setByteBufferPool(ByteBufferPool byteBufferPool) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.updateBean(this.byteBufferPool, byteBufferPool);
         this.byteBufferPool = byteBufferPool;
      }
   }

   public SslContextFactory.Client getSslContextFactory() {
      return this.sslContextFactory;
   }

   public void setSslContextFactory(SslContextFactory.Client sslContextFactory) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.updateBean(this.sslContextFactory, sslContextFactory);
         this.sslContextFactory = sslContextFactory;
      }
   }

   @ManagedAttribute("The number of NIO selectors")
   public int getSelectors() {
      return this.selectors;
   }

   public void setSelectors(int selectors) {
      if (this.isStarted()) {
         throw new IllegalStateException();
      } else {
         this.selectors = selectors;
      }
   }

   @ManagedAttribute("Whether connect operations are performed in blocking mode")
   public boolean isConnectBlocking() {
      return this.connectBlocking;
   }

   public void setConnectBlocking(boolean connectBlocking) {
      this.connectBlocking = connectBlocking;
   }

   @ManagedAttribute("The timeout of connect operations")
   public Duration getConnectTimeout() {
      return this.connectTimeout;
   }

   public void setConnectTimeout(Duration connectTimeout) {
      this.connectTimeout = connectTimeout;
      if (this.selectorManager != null) {
         this.selectorManager.setConnectTimeout(connectTimeout.toMillis());
      }

   }

   @ManagedAttribute("The duration for which a connection can be idle")
   public Duration getIdleTimeout() {
      return this.idleTimeout;
   }

   public void setIdleTimeout(Duration idleTimeout) {
      this.idleTimeout = idleTimeout;
   }

   @ManagedAttribute("The socket address to bind sockets to before the connect operation")
   public SocketAddress getBindAddress() {
      return this.bindAddress;
   }

   public void setBindAddress(SocketAddress bindAddress) {
      this.bindAddress = bindAddress;
   }

   @ManagedAttribute("Whether small TCP packets are sent without delay")
   public boolean isTCPNoDelay() {
      return this.tcpNoDelay;
   }

   public void setTCPNoDelay(boolean tcpNoDelay) {
      this.tcpNoDelay = tcpNoDelay;
   }

   @ManagedAttribute("Whether rebinding is allowed with sockets in tear-down states")
   public boolean getReuseAddress() {
      return this.reuseAddress;
   }

   public void setReuseAddress(boolean reuseAddress) {
      this.reuseAddress = reuseAddress;
   }

   @ManagedAttribute("Whether binding to same host and port is allowed")
   public boolean isReusePort() {
      return this.reusePort;
   }

   public void setReusePort(boolean reusePort) {
      this.reusePort = reusePort;
   }

   @ManagedAttribute("The receive buffer size in bytes")
   public int getReceiveBufferSize() {
      return this.receiveBufferSize;
   }

   public void setReceiveBufferSize(int receiveBufferSize) {
      this.receiveBufferSize = receiveBufferSize;
   }

   @ManagedAttribute("The send buffer size in bytes")
   public int getSendBufferSize() {
      return this.sendBufferSize;
   }

   public void setSendBufferSize(int sendBufferSize) {
      this.sendBufferSize = sendBufferSize;
   }

   protected void doStart() throws Exception {
      if (this.executor == null) {
         QueuedThreadPool clientThreads = new QueuedThreadPool();
         clientThreads.setName(String.format("client-pool@%x", this.hashCode()));
         this.setExecutor(clientThreads);
      }

      if (this.scheduler == null) {
         this.setScheduler(new ScheduledExecutorScheduler(String.format("client-scheduler@%x", this.hashCode()), false));
      }

      if (this.byteBufferPool == null) {
         this.setByteBufferPool(new MappedByteBufferPool());
      }

      if (this.sslContextFactory == null) {
         this.setSslContextFactory(this.newSslContextFactory());
      }

      this.selectorManager = this.newSelectorManager();
      this.selectorManager.setConnectTimeout(this.getConnectTimeout().toMillis());
      this.addBean(this.selectorManager);
      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this.removeBean(this.selectorManager);
   }

   protected SslContextFactory.Client newSslContextFactory() {
      SslContextFactory.Client sslContextFactory = new SslContextFactory.Client(false);
      sslContextFactory.setEndpointIdentificationAlgorithm("HTTPS");
      return sslContextFactory;
   }

   protected SelectorManager newSelectorManager() {
      return new ClientSelectorManager(this.getExecutor(), this.getScheduler(), this.getSelectors());
   }

   public void connect(SocketAddress address, Map context) {
      SelectableChannel channel = null;

      try {
         if (context == null) {
            context = new ConcurrentHashMap();
         }

         context.put("org.sparkproject.jetty.client.connector", this);
         context.putIfAbsent("org.sparkproject.jetty.client.connector.remoteSocketAddress", address);
         Configurator.ChannelWithAddress channelWithAddress = this.configurator.newChannelWithAddress(this, address, context);
         channel = channelWithAddress.getSelectableChannel();
         address = channelWithAddress.getSocketAddress();
         this.configure(channel);
         SocketAddress bindAddress = this.getBindAddress();
         if (bindAddress != null && channel instanceof NetworkChannel) {
            this.bind((NetworkChannel)channel, bindAddress);
         }

         boolean connected = true;
         if (!(channel instanceof SocketChannel)) {
            channel.configureBlocking(false);
         } else {
            SocketChannel socketChannel = (SocketChannel)channel;
            boolean blocking = this.isConnectBlocking() && address instanceof InetSocketAddress;
            if (LOG.isDebugEnabled()) {
               LOG.debug("Connecting {} to {}", blocking ? "blocking" : "non-blocking", address);
            }

            if (blocking) {
               socketChannel.socket().connect(address, (int)this.getConnectTimeout().toMillis());
               socketChannel.configureBlocking(false);
            } else {
               socketChannel.configureBlocking(false);
               connected = socketChannel.connect(address);
            }
         }

         if (connected) {
            this.selectorManager.accept(channel, context);
         } else {
            this.selectorManager.connect(channel, context);
         }
      } catch (Throwable var9) {
         Throwable x = var9;
         if (var9.getClass() == SocketException.class) {
            x = (new SocketException("Could not connect to " + String.valueOf(address))).initCause(var9);
         }

         IO.close((Closeable)channel);
         this.connectFailed(x, context);
      }

   }

   public void accept(SelectableChannel selectable, Map context) {
      try {
         SocketChannel channel = (SocketChannel)selectable;
         context.put("org.sparkproject.jetty.client.connector", this);
         if (!channel.isConnected()) {
            throw new IllegalStateException("SocketChannel must be connected");
         }

         this.configure(channel);
         channel.configureBlocking(false);
         this.selectorManager.accept(channel, context);
      } catch (Throwable failure) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Could not accept {}", selectable);
         }

         IO.close((Closeable)selectable);
         Promise<?> promise = (Promise)context.get("org.sparkproject.jetty.client.connector.connectionPromise");
         if (promise != null) {
            promise.failed(failure);
         }
      }

   }

   private void bind(NetworkChannel channel, SocketAddress bindAddress) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Binding {} to {}", channel, bindAddress);
      }

      channel.bind(bindAddress);
   }

   protected void configure(SelectableChannel selectable) throws IOException {
      if (selectable instanceof NetworkChannel) {
         NetworkChannel channel = (NetworkChannel)selectable;
         this.setSocketOption(channel, StandardSocketOptions.TCP_NODELAY, this.isTCPNoDelay());
         this.setSocketOption(channel, StandardSocketOptions.SO_REUSEADDR, this.getReuseAddress());
         this.setSocketOption(channel, StandardSocketOptions.SO_REUSEPORT, this.isReusePort());
         int receiveBufferSize = this.getReceiveBufferSize();
         if (receiveBufferSize >= 0) {
            this.setSocketOption(channel, StandardSocketOptions.SO_RCVBUF, receiveBufferSize);
         }

         int sendBufferSize = this.getSendBufferSize();
         if (sendBufferSize >= 0) {
            this.setSocketOption(channel, StandardSocketOptions.SO_SNDBUF, sendBufferSize);
         }
      }

   }

   private void setSocketOption(NetworkChannel channel, SocketOption option, Object value) {
      try {
         channel.setOption(option, value);
      } catch (Throwable x) {
         if (LOG.isTraceEnabled()) {
            LOG.trace("Could not configure {} to {} on {}", new Object[]{option, value, channel, x});
         }
      }

   }

   protected EndPoint newEndPoint(SelectableChannel selectable, ManagedSelector selector, SelectionKey selectionKey) {
      Map<String, Object> context = (Map)selectionKey.attachment();
      SocketAddress address = (SocketAddress)context.get("org.sparkproject.jetty.client.connector.remoteSocketAddress");
      return this.configurator.newEndPoint(this, address, selectable, selector, selectionKey);
   }

   protected Connection newConnection(EndPoint endPoint, Map context) throws IOException {
      SocketAddress address = (SocketAddress)context.get("org.sparkproject.jetty.client.connector.remoteSocketAddress");
      return this.configurator.newConnection(this, address, endPoint, context);
   }

   protected void connectFailed(Throwable failure, Map context) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Could not connect to {}", context.get("org.sparkproject.jetty.client.connector.remoteSocketAddress"));
      }

      Promise<?> promise = (Promise)context.get("org.sparkproject.jetty.client.connector.connectionPromise");
      if (promise != null) {
         promise.failed(failure);
      }

   }

   protected class ClientSelectorManager extends SelectorManager {
      public ClientSelectorManager(Executor executor, Scheduler scheduler, int selectors) {
         super(executor, scheduler, selectors);
      }

      protected EndPoint newEndPoint(SelectableChannel channel, ManagedSelector selector, SelectionKey selectionKey) {
         EndPoint endPoint = ClientConnector.this.newEndPoint(channel, selector, selectionKey);
         endPoint.setIdleTimeout(ClientConnector.this.getIdleTimeout().toMillis());
         return endPoint;
      }

      public Connection newConnection(SelectableChannel channel, EndPoint endPoint, Object attachment) throws IOException {
         Map<String, Object> context = (Map)attachment;
         return ClientConnector.this.newConnection(endPoint, context);
      }

      public void connectionOpened(Connection connection, Object context) {
         super.connectionOpened(connection, context);
         Map<String, Object> contextMap = (Map)context;
         Promise<Connection> promise = (Promise)contextMap.get("org.sparkproject.jetty.client.connector.connectionPromise");
         if (promise != null) {
            promise.succeeded(connection);
         }

      }

      protected void connectionFailed(SelectableChannel channel, Throwable failure, Object attachment) {
         Map<String, Object> context = (Map)attachment;
         ClientConnector.this.connectFailed(failure, context);
      }
   }

   public static class Configurator extends ContainerLifeCycle {
      public boolean isIntrinsicallySecure(ClientConnector clientConnector, SocketAddress address) {
         return false;
      }

      public ChannelWithAddress newChannelWithAddress(ClientConnector clientConnector, SocketAddress address, Map context) throws IOException {
         return new ChannelWithAddress(SocketChannel.open(), address);
      }

      public EndPoint newEndPoint(ClientConnector clientConnector, SocketAddress address, SelectableChannel selectable, ManagedSelector selector, SelectionKey selectionKey) {
         return new SocketChannelEndPoint((SocketChannel)selectable, selector, selectionKey, clientConnector.getScheduler());
      }

      public Connection newConnection(ClientConnector clientConnector, SocketAddress address, EndPoint endPoint, Map context) throws IOException {
         ClientConnectionFactory factory = (ClientConnectionFactory)context.get("org.sparkproject.jetty.client.connector.clientConnectionFactory");
         return factory.newConnection(endPoint, context);
      }

      private static Configurator forUnixDomain(final Path path) {
         return new Configurator() {
            public ChannelWithAddress newChannelWithAddress(ClientConnector clientConnector, SocketAddress address, Map context) {
               try {
                  ProtocolFamily family = (ProtocolFamily)Enum.valueOf(StandardProtocolFamily.class, "UNIX");
                  SocketChannel socketChannel = (SocketChannel)SocketChannel.class.getMethod("open", ProtocolFamily.class).invoke((Object)null, family);
                  Class<?> addressClass = Class.forName("java.net.UnixDomainSocketAddress");
                  SocketAddress socketAddress = (SocketAddress)addressClass.getMethod("of", Path.class).invoke((Object)null, path);
                  return new ChannelWithAddress(socketChannel, socketAddress);
               } catch (Throwable x) {
                  String message = "Unix-Domain SocketChannels are available starting from Java 16, your Java version is: " + String.valueOf(JavaVersion.VERSION);
                  throw new UnsupportedOperationException(message, x);
               }
            }
         };
      }

      public static class ChannelWithAddress {
         private final SelectableChannel channel;
         private final SocketAddress address;

         public ChannelWithAddress(SelectableChannel channel, SocketAddress address) {
            this.channel = channel;
            this.address = address;
         }

         public SelectableChannel getSelectableChannel() {
            return this.channel;
         }

         public SocketAddress getSocketAddress() {
            return this.address;
         }
      }
   }
}
