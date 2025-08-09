package io.vertx.core.http.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public interface HttpClientConnection extends HttpConnection {
   Logger log = LoggerFactory.getLogger(HttpClientConnection.class);
   Handler DEFAULT_EVICTION_HANDLER = (v) -> log.warn("Connection evicted");
   Handler DEFAULT_CONCURRENCY_CHANGE_HANDLER = (concurrency) -> {
   };

   HttpClientConnection evictionHandler(Handler var1);

   HttpClientConnection concurrencyChangeHandler(Handler var1);

   long concurrency();

   long activeStreams();

   Channel channel();

   ChannelHandlerContext channelHandlerContext();

   Future createRequest(ContextInternal var1);

   void createStream(ContextInternal var1, Handler var2);

   ContextInternal getContext();

   boolean isValid();

   Object metric();

   long lastResponseReceivedTimestamp();
}
