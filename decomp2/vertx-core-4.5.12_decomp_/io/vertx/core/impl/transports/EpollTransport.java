package io.vertx.core.impl.transports;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.DomainSocketAddress;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.core.spi.transport.Transport;
import java.net.SocketAddress;
import java.util.concurrent.ThreadFactory;

public class EpollTransport implements Transport {
   private static volatile int pendingFastOpenRequestsThreshold = 256;

   public static int getPendingFastOpenRequestsThreshold() {
      return pendingFastOpenRequestsThreshold;
   }

   public static void setPendingFastOpenRequestsThreshold(int value) {
      if (value < 0) {
         throw new IllegalArgumentException("Invalid " + value);
      } else {
         pendingFastOpenRequestsThreshold = value;
      }
   }

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
      return Epoll.isAvailable();
   }

   public Throwable unavailabilityCause() {
      return Epoll.unavailabilityCause();
   }

   public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
      EpollEventLoopGroup eventLoopGroup = new EpollEventLoopGroup(nThreads, threadFactory);
      eventLoopGroup.setIoRatio(ioRatio);
      return eventLoopGroup;
   }

   public DatagramChannel datagramChannel() {
      return new EpollDatagramChannel();
   }

   public DatagramChannel datagramChannel(InternetProtocolFamily family) {
      return new EpollDatagramChannel();
   }

   public ChannelFactory channelFactory(boolean domainSocket) {
      return domainSocket ? EpollDomainSocketChannel::new : EpollSocketChannel::new;
   }

   public ChannelFactory serverChannelFactory(boolean domainSocket) {
      return domainSocket ? EpollServerDomainSocketChannel::new : EpollServerSocketChannel::new;
   }

   public void configure(DatagramChannel channel, DatagramSocketOptions options) {
      channel.config().setOption(EpollChannelOption.SO_REUSEPORT, options.isReusePort());
      Transport.super.configure(channel, options);
   }

   public void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
      if (!domainSocket) {
         bootstrap.option(EpollChannelOption.SO_REUSEPORT, options.isReusePort());
         if (options.isTcpFastOpen()) {
            bootstrap.option(EpollChannelOption.TCP_FASTOPEN, options.isTcpFastOpen() ? pendingFastOpenRequestsThreshold : 0);
         }

         bootstrap.childOption(EpollChannelOption.TCP_QUICKACK, options.isTcpQuickAck());
         bootstrap.childOption(EpollChannelOption.TCP_CORK, options.isTcpCork());
         if (options.isTcpKeepAlive() && options.getTcpKeepAliveIdleSeconds() != -1) {
            bootstrap.childOption(EpollChannelOption.TCP_KEEPIDLE, options.getTcpKeepAliveIdleSeconds());
         }

         if (options.isTcpKeepAlive() && options.getTcpKeepAliveCount() != -1) {
            bootstrap.childOption(EpollChannelOption.TCP_KEEPCNT, options.getTcpKeepAliveCount());
         }

         if (options.isTcpKeepAlive() && options.getTcpKeepAliveIntervalSeconds() != -1) {
            bootstrap.childOption(EpollChannelOption.TCP_KEEPINTVL, options.getTcpKeepAliveIntervalSeconds());
         }
      }

      Transport.super.configure(options, domainSocket, bootstrap);
   }

   public void configure(ClientOptionsBase options, boolean domainSocket, Bootstrap bootstrap) {
      if (!domainSocket) {
         if (options.isTcpFastOpen()) {
            bootstrap.option(EpollChannelOption.TCP_FASTOPEN_CONNECT, options.isTcpFastOpen());
         }

         bootstrap.option(EpollChannelOption.TCP_USER_TIMEOUT, options.getTcpUserTimeout());
         bootstrap.option(EpollChannelOption.TCP_QUICKACK, options.isTcpQuickAck());
         bootstrap.option(EpollChannelOption.TCP_CORK, options.isTcpCork());
      }

      Transport.super.configure(options, domainSocket, bootstrap);
   }
}
