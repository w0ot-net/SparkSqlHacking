package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ServerChannel;

public abstract class ChannelInitializerExtension {
   public static final String EXTENSIONS_SYSTEM_PROPERTY = "io.netty.bootstrap.extensions";

   public double priority() {
      return (double)0.0F;
   }

   public void postInitializeClientChannel(Channel channel) {
   }

   public void postInitializeServerListenerChannel(ServerChannel channel) {
   }

   public void postInitializeServerChildChannel(Channel channel) {
   }
}
