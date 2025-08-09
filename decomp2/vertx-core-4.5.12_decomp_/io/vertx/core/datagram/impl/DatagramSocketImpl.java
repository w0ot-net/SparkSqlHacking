package io.vertx.core.datagram.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.MaxMessagesRecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramPacket;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.impl.AddressResolver;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.CloseFuture;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.VertxHandler;
import io.vertx.core.spi.metrics.DatagramSocketMetrics;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.NetworkMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;
import io.vertx.core.spi.transport.Transport;
import io.vertx.core.streams.WriteStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Objects;

public class DatagramSocketImpl implements DatagramSocket, MetricsProvider, Closeable {
   private final ContextInternal context;
   private final DatagramSocketMetrics metrics;
   private DatagramChannel channel;
   private Handler packetHandler;
   private Handler endHandler;
   private Handler exceptionHandler;
   private long demand;
   private final CloseFuture closeFuture;

   public static DatagramSocketImpl create(VertxInternal vertx, CloseFuture closeFuture, DatagramSocketOptions options) {
      DatagramSocketImpl socket = new DatagramSocketImpl(vertx, closeFuture, options);
      socket.init();
      return socket;
   }

   private DatagramSocketImpl(VertxInternal vertx, CloseFuture closeFuture, DatagramSocketOptions options) {
      Transport transport = vertx.transport();
      DatagramChannel channel = transport.datagramChannel(options.isIpV6() ? io.netty.channel.socket.InternetProtocolFamily.IPv6 : io.netty.channel.socket.InternetProtocolFamily.IPv4);
      transport.configure(channel, new DatagramSocketOptions(options));
      ContextInternal context = vertx.getOrCreateContext();
      channel.config().setOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
      MaxMessagesRecvByteBufAllocator bufAllocator = (MaxMessagesRecvByteBufAllocator)channel.config().getRecvByteBufAllocator();
      bufAllocator.maxMessagesPerRead(1);
      context.nettyEventLoop().register(channel);
      if (options.getLogActivity()) {
         channel.pipeline().addLast("logging", new LoggingHandler(options.getActivityLogDataFormat()));
      }

      VertxMetrics metrics = vertx.metricsSPI();
      this.metrics = metrics != null ? metrics.createDatagramSocketMetrics(options) : null;
      this.channel = channel;
      this.context = context;
      this.demand = Long.MAX_VALUE;
      this.closeFuture = closeFuture;
   }

   private void init() {
      this.channel.pipeline().addLast("handler", VertxHandler.create(this::createConnection));
   }

   public DatagramSocket listenMulticastGroup(String multicastAddress, Handler handler) {
      Future<Void> fut = this.listenMulticastGroup(multicastAddress);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   private NetworkInterface determineMulticastNetworkIface() throws Exception {
      NetworkInterface iface = null;
      InetSocketAddress localAddr = this.channel.localAddress();
      if (localAddr != null) {
         iface = NetworkInterface.getByInetAddress(localAddr.getAddress());
      }

      if (iface == null) {
         iface = this.channel.config().getNetworkInterface();
      }

      return iface;
   }

   public Future listenMulticastGroup(String multicastAddress) {
      NetworkInterface iface;
      try {
         iface = this.determineMulticastNetworkIface();
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      if (iface == null) {
         return this.context.failedFuture("A valid network interface could not be determined from the socket bind address or multicast interface");
      } else {
         ChannelFuture fut;
         try {
            fut = this.channel.joinGroup(InetAddress.getByName(multicastAddress), iface, (InetAddress)null);
         } catch (UnknownHostException e) {
            return this.context.failedFuture((Throwable)e);
         }

         PromiseInternal<Void> promise = this.context.promise();
         fut.addListener(promise);
         return promise.future();
      }
   }

   public DatagramSocket listenMulticastGroup(String multicastAddress, String networkInterface, String source, Handler handler) {
      Future<Void> fut = this.listenMulticastGroup(multicastAddress, networkInterface, source);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future listenMulticastGroup(String multicastAddress, String networkInterface, @Nullable String source) {
      ChannelFuture fut;
      try {
         InetAddress sourceAddress;
         if (source == null) {
            sourceAddress = null;
         } else {
            sourceAddress = InetAddress.getByName(source);
         }

         fut = this.channel.joinGroup(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress);
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      PromiseInternal<Void> promise = this.context.promise();
      fut.addListener(promise);
      return promise.future();
   }

   public DatagramSocket unlistenMulticastGroup(String multicastAddress, Handler handler) {
      Future<Void> fut = this.unlistenMulticastGroup(multicastAddress);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future unlistenMulticastGroup(String multicastAddress) {
      NetworkInterface iface;
      try {
         iface = this.determineMulticastNetworkIface();
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      if (iface == null) {
         return this.context.failedFuture("A valid network interface could not be determined from the socket bind address or multicast interface");
      } else {
         ChannelFuture fut;
         try {
            fut = this.channel.leaveGroup(InetAddress.getByName(multicastAddress), iface, (InetAddress)null);
         } catch (Exception e) {
            return this.context.failedFuture((Throwable)e);
         }

         PromiseInternal<Void> promise = this.context.promise();
         fut.addListener(promise);
         return promise.future();
      }
   }

   public DatagramSocket unlistenMulticastGroup(String multicastAddress, String networkInterface, String source, Handler handler) {
      Future<Void> fut = this.unlistenMulticastGroup(multicastAddress, networkInterface, source);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future unlistenMulticastGroup(String multicastAddress, String networkInterface, @Nullable String source) {
      ChannelFuture fut;
      try {
         InetAddress sourceAddress;
         if (source == null) {
            sourceAddress = null;
         } else {
            sourceAddress = InetAddress.getByName(source);
         }

         fut = this.channel.leaveGroup(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress);
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      PromiseInternal<Void> promise = this.context.promise();
      fut.addListener(promise);
      return promise.future();
   }

   public DatagramSocket blockMulticastGroup(String multicastAddress, String networkInterface, String sourceToBlock, Handler handler) {
      Future<Void> fut = this.blockMulticastGroup(multicastAddress, networkInterface, sourceToBlock);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future blockMulticastGroup(String multicastAddress, String networkInterface, String sourceToBlock) {
      ChannelFuture fut;
      try {
         InetAddress sourceAddress;
         if (sourceToBlock == null) {
            sourceAddress = null;
         } else {
            sourceAddress = InetAddress.getByName(sourceToBlock);
         }

         fut = this.channel.block(InetAddress.getByName(multicastAddress), NetworkInterface.getByName(networkInterface), sourceAddress);
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      PromiseInternal<Void> promise = this.context.promise();
      fut.addListener(promise);
      return promise.future();
   }

   public DatagramSocket blockMulticastGroup(String multicastAddress, String sourceToBlock, Handler handler) {
      Future<Void> fut = this.blockMulticastGroup(multicastAddress, sourceToBlock);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future blockMulticastGroup(String multicastAddress, String sourceToBlock) {
      ChannelFuture fut;
      try {
         fut = this.channel.block(InetAddress.getByName(multicastAddress), InetAddress.getByName(sourceToBlock));
      } catch (Exception e) {
         return this.context.failedFuture((Throwable)e);
      }

      PromiseInternal<Void> promise = this.context.promise();
      fut.addListener(promise);
      return promise.future();
   }

   public DatagramSocket listen(int port, String address, Handler handler) {
      Objects.requireNonNull(handler, "no null handler accepted");
      this.listen(SocketAddress.inetSocketAddress(port, address)).onComplete(handler);
      return this;
   }

   public Future listen(int port, String address) {
      return this.listen(SocketAddress.inetSocketAddress(port, address));
   }

   public synchronized DatagramSocket handler(Handler handler) {
      this.packetHandler = handler;
      return this;
   }

   public DatagramSocketImpl endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   public DatagramSocketImpl exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   private Future listen(SocketAddress local) {
      AddressResolver resolver = this.context.owner().addressResolver();
      PromiseInternal<Void> promise = this.context.promise();
      io.netty.util.concurrent.Future<InetSocketAddress> f1 = resolver.resolveHostname(this.context.nettyEventLoop(), local.host());
      f1.addListener((res1) -> {
         if (res1.isSuccess()) {
            ChannelFuture f2 = this.channel.bind(new InetSocketAddress(((InetSocketAddress)res1.getNow()).getAddress(), local.port()));
            if (this.metrics != null) {
               f2.addListener((res2) -> {
                  if (res2.isSuccess()) {
                     this.metrics.listening(local.host(), this.localAddress());
                  }

               });
            }

            f2.addListener(promise);
         } else {
            promise.fail(res1.cause());
         }

      });
      return promise.future().map((Object)this);
   }

   public synchronized DatagramSocket pause() {
      if (this.demand > 0L) {
         this.demand = 0L;
         this.channel.config().setAutoRead(false);
      }

      return this;
   }

   public synchronized DatagramSocket resume() {
      if (this.demand == 0L) {
         this.demand = Long.MAX_VALUE;
         this.channel.config().setAutoRead(true);
      }

      return this;
   }

   public synchronized DatagramSocket fetch(long amount) {
      if (amount < 0L) {
         throw new IllegalArgumentException("Illegal fetch " + amount);
      } else {
         if (amount > 0L) {
            if (this.demand == 0L) {
               this.channel.config().setAutoRead(true);
            }

            this.demand += amount;
            if (this.demand < 0L) {
               this.demand = Long.MAX_VALUE;
            }
         }

         return this;
      }
   }

   public DatagramSocket send(Buffer packet, int port, String host, Handler handler) {
      Future<Void> fut = this.send(packet, port, host);
      if (handler != null) {
         fut.onComplete(handler);
      }

      return this;
   }

   public Future send(Buffer packet, int port, String host) {
      Objects.requireNonNull(packet, "no null packet accepted");
      Objects.requireNonNull(host, "no null host accepted");
      if (port >= 0 && port <= 65535) {
         AddressResolver resolver = this.context.owner().addressResolver();
         PromiseInternal<Void> promise = this.context.promise();
         io.netty.util.concurrent.Future<InetSocketAddress> f1 = resolver.resolveHostname(this.context.nettyEventLoop(), host);
         f1.addListener((res1) -> {
            if (res1.isSuccess()) {
               ChannelFuture f2 = this.channel.writeAndFlush(new io.netty.channel.socket.DatagramPacket(packet.getByteBuf(), new InetSocketAddress(((InetSocketAddress)f1.getNow()).getAddress(), port)));
               if (this.metrics != null) {
                  f2.addListener((fut) -> {
                     if (fut.isSuccess()) {
                        this.metrics.bytesWritten((Object)null, SocketAddress.inetSocketAddress(port, host), (long)packet.length());
                     }

                  });
               }

               f2.addListener(promise);
            } else {
               promise.fail(res1.cause());
            }

         });
         return promise.future();
      } else {
         throw new IllegalArgumentException("port out of range:" + port);
      }
   }

   public WriteStream sender(int port, String host) {
      Arguments.requireInRange(port, 0, 65535, "port p must be in range 0 <= p <= 65535");
      Objects.requireNonNull(host, "no null host accepted");
      return new PacketWriteStreamImpl(this, port, host);
   }

   public DatagramSocket send(String str, int port, String host, Handler handler) {
      return this.send(Buffer.buffer(str), port, host, handler);
   }

   public Future send(String str, int port, String host) {
      return this.send(Buffer.buffer(str), port, host);
   }

   public DatagramSocket send(String str, String enc, int port, String host, Handler handler) {
      return this.send(Buffer.buffer(str, enc), port, host, handler);
   }

   public Future send(String str, String enc, int port, String host) {
      return this.send(Buffer.buffer(str, enc), port, host);
   }

   public SocketAddress localAddress() {
      return this.context.owner().transport().convert((java.net.SocketAddress)this.channel.localAddress());
   }

   public void close(Handler handler) {
      ContextInternal closingCtx = this.context.owner().getOrCreateContext();
      this.closeFuture.close(handler != null ? closingCtx.promise(handler) : null);
   }

   public synchronized Future close() {
      ContextInternal closingCtx = this.context.owner().getOrCreateContext();
      PromiseInternal<Void> promise = closingCtx.promise();
      this.closeFuture.close(promise);
      return promise.future();
   }

   public void close(Promise completion) {
      if (!this.channel.isOpen()) {
         completion.complete();
      } else {
         this.channel.flush();
         ChannelFuture future = this.channel.close();
         future.addListener((PromiseInternal)completion);
      }

   }

   public boolean isMetricsEnabled() {
      return this.metrics != null;
   }

   public Metrics getMetrics() {
      return this.metrics;
   }

   protected void finalize() throws Throwable {
      this.close();
      super.finalize();
   }

   private Connection createConnection(ChannelHandlerContext chctx) {
      return new Connection(this.context, chctx);
   }

   class Connection extends ConnectionBase {
      public Connection(ContextInternal context, ChannelHandlerContext channel) {
         super(context, channel);
      }

      public NetworkMetrics metrics() {
         return DatagramSocketImpl.this.metrics;
      }

      protected void handleInterestedOpsChanged() {
      }

      protected void handleException(Throwable t) {
         super.handleException(t);
         Handler<Throwable> handler;
         synchronized(DatagramSocketImpl.this) {
            handler = DatagramSocketImpl.this.exceptionHandler;
         }

         if (handler != null) {
            handler.handle(t);
         }

      }

      protected void handleClosed() {
         super.handleClosed();
         Handler<Void> handler;
         DatagramSocketMetrics metrics;
         synchronized(DatagramSocketImpl.this) {
            handler = DatagramSocketImpl.this.endHandler;
            metrics = DatagramSocketImpl.this.metrics;
         }

         if (metrics != null) {
            metrics.close();
         }

         if (handler != null) {
            this.context.emit((Object)null, handler);
         }

      }

      public void handleMessage(Object msg) {
         if (msg instanceof io.netty.channel.socket.DatagramPacket) {
            io.netty.channel.socket.DatagramPacket packet = (io.netty.channel.socket.DatagramPacket)msg;
            ByteBuf content = (ByteBuf)packet.content();
            if (content.isDirect()) {
               content = VertxHandler.safeBuffer(content);
            }

            this.handlePacket(new DatagramPacketImpl((InetSocketAddress)packet.sender(), Buffer.buffer(content)));
         }

      }

      void handlePacket(DatagramPacket packet) {
         Handler<DatagramPacket> handler;
         synchronized(DatagramSocketImpl.this) {
            if (DatagramSocketImpl.this.metrics != null) {
               DatagramSocketImpl.this.metrics.bytesRead((Object)null, packet.sender(), (long)packet.data().length());
            }

            if (DatagramSocketImpl.this.demand > 0L) {
               if (DatagramSocketImpl.this.demand != Long.MAX_VALUE) {
                  DatagramSocketImpl.this.demand--;
               }

               handler = DatagramSocketImpl.this.packetHandler;
            } else {
               handler = null;
            }
         }

         if (handler != null) {
            this.context.emit(packet, handler);
         }

      }
   }
}
