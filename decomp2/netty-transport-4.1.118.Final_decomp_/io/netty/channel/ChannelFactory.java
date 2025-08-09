package io.netty.channel;

public interface ChannelFactory extends io.netty.bootstrap.ChannelFactory {
   Channel newChannel();
}
