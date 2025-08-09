package io.netty.channel.unix;

public final class IntegerUnixChannelOption extends GenericUnixChannelOption {
   public IntegerUnixChannelOption(String name, int level, int optname) {
      super(name, level, optname);
   }
}
