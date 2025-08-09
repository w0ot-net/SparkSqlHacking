package io.netty.channel.epoll;

import io.netty.channel.ChannelOption;
import io.netty.channel.unix.UnixChannelOption;

public final class EpollChannelOption extends UnixChannelOption {
   public static final ChannelOption TCP_CORK = valueOf(EpollChannelOption.class, "TCP_CORK");
   public static final ChannelOption TCP_NOTSENT_LOWAT = valueOf(EpollChannelOption.class, "TCP_NOTSENT_LOWAT");
   public static final ChannelOption TCP_KEEPIDLE = valueOf(EpollChannelOption.class, "TCP_KEEPIDLE");
   public static final ChannelOption TCP_KEEPINTVL = valueOf(EpollChannelOption.class, "TCP_KEEPINTVL");
   public static final ChannelOption TCP_KEEPCNT = valueOf(EpollChannelOption.class, "TCP_KEEPCNT");
   public static final ChannelOption TCP_USER_TIMEOUT = valueOf(EpollChannelOption.class, "TCP_USER_TIMEOUT");
   public static final ChannelOption IP_FREEBIND = valueOf("IP_FREEBIND");
   public static final ChannelOption IP_BIND_ADDRESS_NO_PORT = valueOf("IP_BIND_ADDRESS_NO_PORT");
   public static final ChannelOption IP_TRANSPARENT = valueOf("IP_TRANSPARENT");
   public static final ChannelOption IP_RECVORIGDSTADDR = valueOf("IP_RECVORIGDSTADDR");
   /** @deprecated */
   @Deprecated
   public static final ChannelOption TCP_FASTOPEN;
   /** @deprecated */
   @Deprecated
   public static final ChannelOption TCP_FASTOPEN_CONNECT;
   public static final ChannelOption TCP_DEFER_ACCEPT;
   public static final ChannelOption TCP_QUICKACK;
   public static final ChannelOption SO_BUSY_POLL;
   public static final ChannelOption EPOLL_MODE;
   public static final ChannelOption TCP_MD5SIG;
   public static final ChannelOption MAX_DATAGRAM_PAYLOAD_SIZE;
   public static final ChannelOption UDP_GRO;

   private EpollChannelOption() {
   }

   static {
      TCP_FASTOPEN = ChannelOption.TCP_FASTOPEN;
      TCP_FASTOPEN_CONNECT = ChannelOption.TCP_FASTOPEN_CONNECT;
      TCP_DEFER_ACCEPT = ChannelOption.valueOf(EpollChannelOption.class, "TCP_DEFER_ACCEPT");
      TCP_QUICKACK = valueOf(EpollChannelOption.class, "TCP_QUICKACK");
      SO_BUSY_POLL = valueOf(EpollChannelOption.class, "SO_BUSY_POLL");
      EPOLL_MODE = ChannelOption.valueOf(EpollChannelOption.class, "EPOLL_MODE");
      TCP_MD5SIG = valueOf("TCP_MD5SIG");
      MAX_DATAGRAM_PAYLOAD_SIZE = valueOf("MAX_DATAGRAM_PAYLOAD_SIZE");
      UDP_GRO = valueOf("UDP_GRO");
   }
}
