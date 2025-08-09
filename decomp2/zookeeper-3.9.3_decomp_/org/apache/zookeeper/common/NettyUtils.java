package org.apache.zookeeper.common;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyUtils {
   public static final String THREAD_POOL_NAME_PREFIX = "zkNetty-";
   private static final Logger LOG = LoggerFactory.getLogger(NettyUtils.class);
   private static final int DEFAULT_INET_ADDRESS_COUNT = 1;

   private static ThreadFactory createThreadFactory(String clazz) {
      String poolName = "zkNetty-" + clazz;
      return new DefaultThreadFactory(poolName, true);
   }

   public static EventLoopGroup newNioOrEpollEventLoopGroup() {
      return newNioOrEpollEventLoopGroup(0);
   }

   public static EventLoopGroup newNioOrEpollEventLoopGroup(int nThreads) {
      if (Epoll.isAvailable()) {
         String clazz = EpollEventLoopGroup.class.getSimpleName();
         ThreadFactory factory = createThreadFactory(clazz);
         return new EpollEventLoopGroup(nThreads, factory);
      } else {
         String clazz = NioEventLoopGroup.class.getSimpleName();
         ThreadFactory factory = createThreadFactory(clazz);
         return new NioEventLoopGroup(nThreads, factory);
      }
   }

   public static Class nioOrEpollSocketChannel() {
      return Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class;
   }

   public static Class nioOrEpollServerSocketChannel() {
      return Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
   }

   public static int getClientReachableLocalInetAddressCount() {
      try {
         Set<InetAddress> validInetAddresses = new HashSet();
         Enumeration<NetworkInterface> allNetworkInterfaces = NetworkInterface.getNetworkInterfaces();

         for(NetworkInterface networkInterface : Collections.list(allNetworkInterfaces)) {
            for(InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
               if (inetAddress.isLinkLocalAddress()) {
                  LOG.debug("Ignoring link-local InetAddress {}", inetAddress);
               } else if (inetAddress.isMulticastAddress()) {
                  LOG.debug("Ignoring multicast InetAddress {}", inetAddress);
               } else if (inetAddress.isLoopbackAddress()) {
                  LOG.debug("Ignoring loopback InetAddress {}", inetAddress);
               } else {
                  validInetAddresses.add(inetAddress);
               }
            }
         }

         LOG.debug("Detected {} local network addresses: {}", validInetAddresses.size(), validInetAddresses);
         return !validInetAddresses.isEmpty() ? validInetAddresses.size() : 1;
      } catch (SocketException ex) {
         LOG.warn("Failed to list all network interfaces, assuming 1", ex);
         return 1;
      }
   }
}
