package io.vertx.core.http.impl;

import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.WebSocketBase;

public interface WebSocketInternal extends WebSocketBase {
   ChannelHandlerContext channelHandlerContext();

   HttpConnection connection();
}
