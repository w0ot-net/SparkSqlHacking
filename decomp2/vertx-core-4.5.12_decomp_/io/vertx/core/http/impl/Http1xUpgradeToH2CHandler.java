package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http2.DefaultHttp2DataFrame;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.DefaultHttp2HeadersFrame;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.net.impl.SslChannelProvider;
import io.vertx.core.net.impl.VertxHandler;
import java.util.Map;

public class Http1xUpgradeToH2CHandler extends ChannelInboundHandlerAdapter {
   private final HttpServerWorker initializer;
   private final SslChannelProvider sslChannelProvider;
   private VertxHttp2ConnectionHandler handler;
   private final boolean isCompressionSupported;
   private final boolean isDecompressionSupported;

   Http1xUpgradeToH2CHandler(HttpServerWorker initializer, SslChannelProvider sslChannelProvider, boolean isCompressionSupported, boolean isDecompressionSupported) {
      this.initializer = initializer;
      this.sslChannelProvider = sslChannelProvider;
      this.isCompressionSupported = isCompressionSupported;
      this.isDecompressionSupported = isDecompressionSupported;
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      if (msg instanceof HttpRequest) {
         HttpRequest request = (HttpRequest)msg;
         if (request.headers().contains(HttpHeaders.UPGRADE, Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME, true)) {
            String connection = request.headers().get(HttpHeaders.CONNECTION);
            int found = 0;
            if (connection != null && connection.length() > 0) {
               StringBuilder buff = new StringBuilder();
               int pos = 0;
               int len = connection.length();

               while(pos < len) {
                  char c = connection.charAt(pos++);
                  if (c != ' ' && c != ',') {
                     buff.append(Character.toLowerCase(c));
                  }

                  if (c == ',' || pos == len) {
                     if (buff.indexOf("upgrade") == 0 && buff.length() == 7) {
                        found |= 1;
                     } else if (buff.indexOf("http2-settings") == 0 && buff.length() == 14) {
                        found |= 2;
                     }

                     buff.setLength(0);
                  }
               }
            }

            if (found == 3) {
               String settingsHeader = request.headers().get(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
               if (settingsHeader != null) {
                  Http2Settings settings = HttpUtils.decodeSettings(settingsHeader);
                  if (settings != null) {
                     if (this.initializer.context.isEventLoopContext()) {
                        ChannelPipeline pipeline = ctx.pipeline();
                        if (pipeline.get("chunkedWriter") != null) {
                           pipeline.remove("chunkedWriter");
                        }

                        DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS, Unpooled.EMPTY_BUFFER, false);
                        res.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
                        res.headers().add(HttpHeaderNames.UPGRADE, Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME);
                        ctx.write(res);
                        pipeline.remove("httpEncoder");
                        if (this.isCompressionSupported) {
                           pipeline.remove("deflater");
                        }

                        if (this.isDecompressionSupported) {
                           pipeline.remove("inflater");
                        }

                        this.handler = this.initializer.buildHttp2ConnectionHandler(this.initializer.context, this.initializer.connectionHandler);
                        pipeline.addLast("handler", this.handler);
                        this.handler.serverUpgrade(ctx, settings);
                        DefaultHttp2Headers headers = new DefaultHttp2Headers();
                        headers.method(request.method().name());
                        headers.path(request.uri());
                        headers.authority(request.headers().get("host"));
                        headers.scheme("http");
                        request.headers().remove("http2-settings");
                        request.headers().remove("host");
                        request.headers().forEach((header) -> {
                           if (!HttpHeaderValidationUtil.isConnectionHeader((CharSequence)header.getKey(), true)) {
                              headers.set(((String)header.getKey()).toLowerCase(), header.getValue());
                           }

                        });
                        ctx.fireChannelRead(new DefaultHttp2HeadersFrame(headers, false));
                     } else {
                        HttpServerImpl.log.warn("Cannot perform HTTP/2 upgrade in a worker verticle");
                     }
                  }
               }
            }

            if (this.handler == null) {
               DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.EMPTY_BUFFER, false);
               res.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
               ctx.writeAndFlush(res);
            }
         } else {
            this.initializer.configureHttp1Handler(ctx.pipeline(), this.sslChannelProvider);
            ctx.fireChannelRead(msg);
            ctx.pipeline().remove(this);
         }
      } else if (this.handler != null) {
         if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = VertxHandler.safeBuffer(content.content());
            boolean end = msg instanceof LastHttpContent;
            ctx.fireChannelRead(new DefaultHttp2DataFrame(buf, end, 0));
            if (end) {
               ChannelPipeline pipeline = ctx.pipeline();

               for(Map.Entry handler : pipeline) {
                  if (!(handler.getValue() instanceof Http2ConnectionHandler)) {
                     pipeline.remove((String)handler.getKey());
                  }
               }

               this.initializer.configureHttp2Pipeline(pipeline);
            }
         } else {
            super.channelRead(ctx, msg);
         }
      }

   }

   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
      if (evt instanceof IdleStateEvent && ((IdleStateEvent)evt).state() == IdleState.ALL_IDLE) {
         ctx.close();
      } else {
         ctx.fireUserEventTriggered(evt);
      }

   }
}
