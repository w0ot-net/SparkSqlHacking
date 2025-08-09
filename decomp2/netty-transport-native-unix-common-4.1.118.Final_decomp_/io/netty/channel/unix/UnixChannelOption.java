package io.netty.channel.unix;

import io.netty.channel.ChannelOption;

public class UnixChannelOption extends ChannelOption {
   public static final ChannelOption SO_REUSEPORT = valueOf(UnixChannelOption.class, "SO_REUSEPORT");
   public static final ChannelOption DOMAIN_SOCKET_READ_MODE = ChannelOption.valueOf(UnixChannelOption.class, "DOMAIN_SOCKET_READ_MODE");

   protected UnixChannelOption() {
      super((String)null);
   }

   UnixChannelOption(String name) {
      super(name);
   }
}
