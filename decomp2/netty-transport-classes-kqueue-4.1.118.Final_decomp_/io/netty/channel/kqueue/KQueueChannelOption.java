package io.netty.channel.kqueue;

import io.netty.channel.ChannelOption;
import io.netty.channel.unix.UnixChannelOption;

public final class KQueueChannelOption extends UnixChannelOption {
   public static final ChannelOption SO_SNDLOWAT = valueOf(KQueueChannelOption.class, "SO_SNDLOWAT");
   public static final ChannelOption TCP_NOPUSH = valueOf(KQueueChannelOption.class, "TCP_NOPUSH");
   public static final ChannelOption SO_ACCEPTFILTER = valueOf(KQueueChannelOption.class, "SO_ACCEPTFILTER");
   public static final ChannelOption RCV_ALLOC_TRANSPORT_PROVIDES_GUESS = valueOf(KQueueChannelOption.class, "RCV_ALLOC_TRANSPORT_PROVIDES_GUESS");

   private KQueueChannelOption() {
   }
}
