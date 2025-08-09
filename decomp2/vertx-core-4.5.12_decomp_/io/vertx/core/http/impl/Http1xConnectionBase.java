package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.FileRegion;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.stream.ChunkedFile;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.WebSocketFrameType;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.http.impl.ws.WebSocketFrameInternal;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.net.impl.VertxHandler;
import java.util.concurrent.TimeUnit;

abstract class Http1xConnectionBase extends ConnectionBase implements HttpConnection {
   protected WebSocketImplBase webSocket;

   Http1xConnectionBase(ContextInternal context, ChannelHandlerContext chctx) {
      super(context, chctx);
   }

   void handleWsFrame(WebSocketFrame msg) {
      WebSocketFrameInternal frame = this.decodeFrame(msg);
      WebSocketImplBase<?> w;
      synchronized(this) {
         w = this.webSocket;
      }

      if (w != null) {
         w.context.execute(frame, w::handleFrame);
      }

   }

   private WebSocketFrameInternal decodeFrame(WebSocketFrame msg) {
      ByteBuf payload = VertxHandler.safeBuffer(msg.content());
      boolean isFinal = msg.isFinalFragment();
      WebSocketFrameType frameType;
      if (msg instanceof BinaryWebSocketFrame) {
         frameType = WebSocketFrameType.BINARY;
      } else if (msg instanceof CloseWebSocketFrame) {
         frameType = WebSocketFrameType.CLOSE;
      } else if (msg instanceof PingWebSocketFrame) {
         frameType = WebSocketFrameType.PING;
      } else if (msg instanceof PongWebSocketFrame) {
         frameType = WebSocketFrameType.PONG;
      } else if (msg instanceof TextWebSocketFrame) {
         frameType = WebSocketFrameType.TEXT;
      } else {
         if (!(msg instanceof ContinuationWebSocketFrame)) {
            throw new IllegalStateException("Unsupported WebSocket msg " + msg);
         }

         frameType = WebSocketFrameType.CONTINUATION;
      }

      return new WebSocketFrameImpl(frameType, payload, isFinal);
   }

   public Future close() {
      S sock;
      synchronized(this) {
         sock = (S)this.webSocket;
      }

      if (sock == null) {
         return super.close();
      } else {
         sock.close();
         return this.closeFuture();
      }
   }

   public Http1xConnectionBase closeHandler(Handler handler) {
      return (Http1xConnectionBase)super.closeHandler(handler);
   }

   public Http1xConnectionBase exceptionHandler(Handler handler) {
      return (Http1xConnectionBase)super.exceptionHandler(handler);
   }

   public void handleException(Throwable t) {
      super.handleException(t);
   }

   public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
   }

   public HttpConnection goAwayHandler(@Nullable Handler handler) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support GOAWAY");
   }

   public HttpConnection shutdownHandler(@Nullable Handler handler) {
      throw new UnsupportedOperationException("HTTP/1.x connections cannot be shutdown");
   }

   public Future shutdown(long timeout, TimeUnit unit) {
      throw new UnsupportedOperationException("HTTP/1.x connections cannot be shutdown");
   }

   public Http2Settings settings() {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
   }

   public Future updateSettings(Http2Settings settings) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
   }

   public HttpConnection updateSettings(Http2Settings settings, Handler completionHandler) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
   }

   public Http2Settings remoteSettings() {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
   }

   public HttpConnection remoteSettingsHandler(Handler handler) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support SETTINGS");
   }

   public HttpConnection ping(Buffer data, Handler pongHandler) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support PING");
   }

   public HttpConnection pingHandler(@Nullable Handler handler) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support PING");
   }

   public Future ping(Buffer data) {
      throw new UnsupportedOperationException("HTTP/1.x connections don't support PING");
   }

   protected long sizeof(Object obj) {
      if (obj != Unpooled.EMPTY_BUFFER && obj != LastHttpContent.EMPTY_LAST_CONTENT) {
         if (obj instanceof AssembledHttpResponse) {
            return (long)((AssembledHttpResponse)obj).content().readableBytes();
         } else if (obj instanceof Buffer) {
            return (long)((Buffer)obj).length();
         } else if (obj instanceof ByteBuf) {
            return (long)((ByteBuf)obj).readableBytes();
         } else if (obj instanceof FullHttpMessage) {
            return (long)((FullHttpMessage)obj).content().readableBytes();
         } else if (obj instanceof LastHttpContent) {
            return (long)((LastHttpContent)obj).content().readableBytes();
         } else if (obj instanceof HttpContent) {
            return (long)((HttpContent)obj).content().readableBytes();
         } else if (obj instanceof WebSocketFrame) {
            return (long)((WebSocketFrame)obj).content().readableBytes();
         } else if (obj instanceof FileRegion) {
            return ((FileRegion)obj).count();
         } else if (obj instanceof ChunkedFile) {
            ChunkedFile file = (ChunkedFile)obj;
            return file.endOffset() - file.startOffset();
         } else {
            return 0L;
         }
      } else {
         return 0L;
      }
   }
}
