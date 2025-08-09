package io.vertx.core.net.impl;

import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

public interface NetSocketInternal extends NetSocket {
   ChannelHandlerContext channelHandlerContext();

   Future writeMessage(Object var1);

   NetSocketInternal writeMessage(Object var1, Handler var2);

   NetSocketInternal messageHandler(Handler var1);

   NetSocketInternal eventHandler(Handler var1);
}
