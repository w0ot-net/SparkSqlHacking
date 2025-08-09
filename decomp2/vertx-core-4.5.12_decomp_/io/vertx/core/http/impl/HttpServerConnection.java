package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.impl.ContextInternal;

public interface HttpServerConnection extends HttpConnection {
   ContextInternal getContext();

   Channel channel();

   ChannelHandlerContext channelHandlerContext();

   HttpServerConnection handler(Handler var1);

   HttpServerConnection invalidRequestHandler(Handler var1);
}
