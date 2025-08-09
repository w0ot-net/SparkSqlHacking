package io.vertx.core.impl.transports;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.vertx.core.spi.transport.Transport;
import java.util.concurrent.ThreadFactory;

public class JDKTransport implements Transport {
   public static final Transport INSTANCE = new JDKTransport();

   public EventLoopGroup eventLoopGroup(int type, int nThreads, ThreadFactory threadFactory, int ioRatio) {
      NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(nThreads, threadFactory);
      eventLoopGroup.setIoRatio(ioRatio);
      return eventLoopGroup;
   }

   public DatagramChannel datagramChannel() {
      return new NioDatagramChannel();
   }

   public DatagramChannel datagramChannel(InternetProtocolFamily family) {
      switch (family) {
         case IPv4:
            return new NioDatagramChannel(InternetProtocolFamily.IPv4);
         case IPv6:
            return new NioDatagramChannel(InternetProtocolFamily.IPv6);
         default:
            throw new UnsupportedOperationException();
      }
   }

   public ChannelFactory channelFactory(boolean domainSocket) {
      if (domainSocket) {
         throw new IllegalArgumentException("The Vertx instance must be created with the preferNativeTransport option set to true to create domain sockets");
      } else {
         return NioSocketChannel::new;
      }
   }

   public ChannelFactory serverChannelFactory(boolean domainSocket) {
      if (domainSocket) {
         throw new IllegalArgumentException();
      } else {
         return NioServerSocketChannel::new;
      }
   }
}
