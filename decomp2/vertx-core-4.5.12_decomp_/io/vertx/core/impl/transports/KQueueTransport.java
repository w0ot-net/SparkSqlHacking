package io.vertx.core.impl.transports;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueChannelOption;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueDomainSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerDomainSocketChannel;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.DomainSocketAddress;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.core.spi.transport.Transport;
import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

public class KQueueTransport implements Transport {
   public boolean supportsDomainSockets() {
      return true;
   }

   public SocketAddress convert(io.vertx.core.net.SocketAddress address) {
      return (SocketAddress)(address.isDomainSocket() ? new DomainSocketAddress(address.path()) : Transport.super.convert(address));
   }

   public io.vertx.core.net.SocketAddress convert(SocketAddress address) {
      return (io.vertx.core.net.SocketAddress)(address instanceof DomainSocketAddress ? new SocketAddressImpl(((DomainSocketAddress)address).path()) : Transport.super.convert(address));
   }

   public boolean isAvailable() {
      return KQueue.isAvailable();
   }

   public Throwable unavailabilityCause() {
      return KQueue.unavailabilityCause();
   }

   public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
      KQueueEventLoopGroup eventLoopGroup = new KQueueEventLoopGroup(nThreads, threadFactory);
      eventLoopGroup.setIoRatio(ioRatio);
      return eventLoopGroup;
   }

   public DatagramChannel datagramChannel() {
      return new KQueueDatagramChannel();
   }

   public DatagramChannel datagramChannel(InternetProtocolFamily family) {
      return new KQueueDatagramChannel();
   }

   public ChannelFactory channelFactory(boolean domainSocket) {
      return domainSocket ? KQueueDomainSocketChannel::new : KQueueSocketChannel::new;
   }

   public ChannelFactory serverChannelFactory(boolean domainSocket) {
      return domainSocket ? KQueueServerDomainSocketChannel::new : KQueueServerSocketChannel::new;
   }

   public void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
      if (!domainSocket) {
         bootstrap.option(KQueueChannelOption.SO_REUSEPORT, options.isReusePort());
      }

      Transport.super.configure(options, domainSocket, bootstrap);
   }

   public void configure(DatagramChannel channel, DatagramSocketOptions options) {
      channel.config().setOption(KQueueChannelOption.SO_REUSEPORT, options.isReusePort());
      Transport.super.configure(channel, options);
   }
}
