package io.vertx.core.spi.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.vertx.core.buffer.impl.PartialPooledByteBufAllocator;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.impl.transports.JDKTransport;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.impl.SocketAddressImpl;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.ThreadFactory;

public interface Transport {
   int ACCEPTOR_EVENT_LOOP_GROUP = 0;
   int IO_EVENT_LOOP_GROUP = 1;

   default boolean supportsDomainSockets() {
      return false;
   }

   default boolean supportFileRegion() {
      return true;
   }

   default boolean isAvailable() {
      return true;
   }

   default Throwable unavailabilityCause() {
      return null;
   }

   default SocketAddress convert(io.vertx.core.net.SocketAddress address) {
      if (address.isDomainSocket()) {
         throw new IllegalArgumentException("Domain socket are not supported by JDK transport, you need to use native transport to use them");
      } else {
         InetAddress ip = ((SocketAddressImpl)address).ipAddress();
         return ip != null ? new InetSocketAddress(ip, address.port()) : InetSocketAddress.createUnresolved(address.host(), address.port());
      }
   }

   default io.vertx.core.net.SocketAddress convert(SocketAddress address) {
      return address instanceof InetSocketAddress ? io.vertx.core.net.SocketAddress.inetSocketAddress((InetSocketAddress)address) : null;
   }

   EventLoopGroup eventLoopGroup(int var1, int var2, ThreadFactory var3, int var4);

   DatagramChannel datagramChannel();

   DatagramChannel datagramChannel(InternetProtocolFamily var1);

   ChannelFactory channelFactory(boolean var1);

   ChannelFactory serverChannelFactory(boolean var1);

   default void configure(DatagramChannel channel, DatagramSocketOptions options) {
      channel.config().setAllocator(PartialPooledByteBufAllocator.INSTANCE);
      if (options.getSendBufferSize() != -1) {
         channel.config().setSendBufferSize(options.getSendBufferSize());
      }

      if (options.getReceiveBufferSize() != -1) {
         channel.config().setReceiveBufferSize(options.getReceiveBufferSize());
         channel.config().setRecvByteBufAllocator(new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
      }

      channel.config().setOption(ChannelOption.SO_REUSEADDR, options.isReuseAddress());
      if (options.getTrafficClass() != -1) {
         channel.config().setTrafficClass(options.getTrafficClass());
      }

      channel.config().setBroadcast(options.isBroadcast());
      if (this instanceof JDKTransport) {
         channel.config().setLoopbackModeDisabled(options.isLoopbackModeDisabled());
         if (options.getMulticastTimeToLive() != -1) {
            channel.config().setTimeToLive(options.getMulticastTimeToLive());
         }

         if (options.getMulticastNetworkInterface() != null) {
            try {
               channel.config().setNetworkInterface(NetworkInterface.getByName(options.getMulticastNetworkInterface()));
            } catch (SocketException var4) {
               throw new IllegalArgumentException("Could not find network interface with name " + options.getMulticastNetworkInterface());
            }
         }
      }

   }

   default void configure(ClientOptionsBase options, boolean domainSocket, Bootstrap bootstrap) {
      if (!domainSocket) {
         bootstrap.option(ChannelOption.SO_REUSEADDR, options.isReuseAddress());
         bootstrap.option(ChannelOption.TCP_NODELAY, options.isTcpNoDelay());
         bootstrap.option(ChannelOption.SO_KEEPALIVE, options.isTcpKeepAlive());
      }

      if (options.getLocalAddress() != null) {
         bootstrap.localAddress(options.getLocalAddress(), 0);
      }

      if (options.getSendBufferSize() != -1) {
         bootstrap.option(ChannelOption.SO_SNDBUF, options.getSendBufferSize());
      }

      if (options.getReceiveBufferSize() != -1) {
         bootstrap.option(ChannelOption.SO_RCVBUF, options.getReceiveBufferSize());
         bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
      }

      if (options.getSoLinger() != -1) {
         bootstrap.option(ChannelOption.SO_LINGER, options.getSoLinger());
      }

      if (options.getTrafficClass() != -1) {
         bootstrap.option(ChannelOption.IP_TOS, options.getTrafficClass());
      }

      bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, options.getConnectTimeout());
   }

   default void configure(NetServerOptions options, boolean domainSocket, ServerBootstrap bootstrap) {
      bootstrap.option(ChannelOption.SO_REUSEADDR, options.isReuseAddress());
      if (!domainSocket) {
         bootstrap.childOption(ChannelOption.SO_KEEPALIVE, options.isTcpKeepAlive());
         bootstrap.childOption(ChannelOption.TCP_NODELAY, options.isTcpNoDelay());
      }

      if (options.getSendBufferSize() != -1) {
         bootstrap.childOption(ChannelOption.SO_SNDBUF, options.getSendBufferSize());
      }

      if (options.getReceiveBufferSize() != -1) {
         bootstrap.childOption(ChannelOption.SO_RCVBUF, options.getReceiveBufferSize());
         bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(options.getReceiveBufferSize()));
      }

      if (options.getSoLinger() != -1) {
         bootstrap.childOption(ChannelOption.SO_LINGER, options.getSoLinger());
      }

      if (options.getTrafficClass() != -1) {
         bootstrap.childOption(ChannelOption.IP_TOS, options.getTrafficClass());
      }

      if (options.getAcceptBacklog() != -1) {
         bootstrap.option(ChannelOption.SO_BACKLOG, options.getAcceptBacklog());
      }

   }
}
