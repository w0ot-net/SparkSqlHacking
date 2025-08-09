package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.LastHttpContent;
import io.vertx.core.http.impl.headers.HeadersMultiMap;

final class VertxHttpResponseEncoder extends HttpResponseEncoder {
   protected void encodeHeaders(HttpHeaders headers, ByteBuf buf) {
      if (headers instanceof HeadersMultiMap) {
         HeadersMultiMap vertxHeaders = (HeadersMultiMap)headers;
         vertxHeaders.encode(buf);
      } else {
         super.encodeHeaders(headers, buf);
      }

   }

   public boolean acceptOutboundMessage(Object msg) throws Exception {
      if (msg != Unpooled.EMPTY_BUFFER && msg != LastHttpContent.EMPTY_LAST_CONTENT) {
         Class<?> msgClazz = msg.getClass();
         return msgClazz != AssembledFullHttpResponse.class && msgClazz != DefaultFullHttpResponse.class && msgClazz != AssembledHttpResponse.class && msgClazz != DefaultHttpContent.class && msgClazz != AssembledLastHttpContent.class && msgClazz != DefaultFileRegion.class ? super.acceptOutboundMessage(msg) : true;
      } else {
         return true;
      }
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      super.handlerAdded(ctx);
   }

   protected boolean isContentAlwaysEmpty(HttpResponse msg) {
      return msg instanceof AssembledHttpResponse && ((AssembledHttpResponse)msg).head() || super.isContentAlwaysEmpty(msg);
   }
}
