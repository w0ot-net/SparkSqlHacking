package org.apache.spark.network.client;

import io.netty.channel.Channel;

public interface TransportClientBootstrap {
   void doBootstrap(TransportClient var1, Channel var2) throws RuntimeException;
}
