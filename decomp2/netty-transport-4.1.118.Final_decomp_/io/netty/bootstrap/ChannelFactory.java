package io.netty.bootstrap;

import io.netty.channel.Channel;

/** @deprecated */
@Deprecated
public interface ChannelFactory {
   Channel newChannel();
}
