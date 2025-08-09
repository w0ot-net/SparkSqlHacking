package io.vertx.core.http.impl;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateServerExtensionHandshaker;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.ServerWebSocketHandshake;
import io.vertx.core.impl.ContextInternal;
import java.util.ArrayList;

public class HttpServerConnectionHandler implements Handler {
   final HttpServerImpl server;
   final Handler requestHandler;
   final Handler invalidRequestHandler;
   final Handler wsHandshakeHandler;
   final Handler wsHandler;
   final Handler connectionHandler;
   final Handler exceptionHandler;

   public HttpServerConnectionHandler(HttpServerImpl server, Handler requestHandler, Handler invalidRequestHandler, Handler wsHandshakeHandler, Handler wsHandler, Handler connectionHandler, Handler exceptionHandler) {
      this.server = server;
      this.requestHandler = requestHandler;
      this.invalidRequestHandler = invalidRequestHandler == null ? HttpServerRequest.DEFAULT_INVALID_REQUEST_HANDLER : invalidRequestHandler;
      this.wsHandshakeHandler = wsHandshakeHandler;
      this.wsHandler = wsHandler;
      this.connectionHandler = connectionHandler;
      this.exceptionHandler = exceptionHandler;
   }

   public void handle(HttpServerConnection conn) {
      Handler<HttpServerRequest> requestHandler = this.requestHandler;
      if (!HttpServerImpl.DISABLE_WEBSOCKETS && conn instanceof Http1xServerConnection) {
         requestHandler = new Http1xServerRequestHandler(this);
         Http1xServerConnection c = (Http1xServerConnection)conn;
         this.initializeWebSocketExtensions(c.channelHandlerContext().pipeline());
      }

      conn.exceptionHandler(this.exceptionHandler);
      conn.handler(requestHandler);
      conn.invalidRequestHandler(this.invalidRequestHandler);
      if (this.connectionHandler != null) {
         ContextInternal ctx = conn.getContext();
         ContextInternal prev = ctx.beginDispatch();

         try {
            this.connectionHandler.handle(conn);
         } catch (Exception e) {
            ctx.reportException(e);
         } finally {
            ctx.endDispatch(prev);
         }
      }

   }

   private void initializeWebSocketExtensions(ChannelPipeline pipeline) {
      ArrayList<WebSocketServerExtensionHandshaker> extensionHandshakers = new ArrayList();
      if (this.server.options.getPerFrameWebSocketCompressionSupported()) {
         extensionHandshakers.add(new DeflateFrameServerExtensionHandshaker(this.server.options.getWebSocketCompressionLevel()));
      }

      if (this.server.options.getPerMessageWebSocketCompressionSupported()) {
         extensionHandshakers.add(new PerMessageDeflateServerExtensionHandshaker(this.server.options.getWebSocketCompressionLevel(), ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, this.server.options.getWebSocketAllowServerNoContext(), this.server.options.getWebSocketPreferredClientNoContext()));
      }

      if (!extensionHandshakers.isEmpty()) {
         WebSocketServerExtensionHandler extensionHandler = new WebSocketServerExtensionHandler((WebSocketServerExtensionHandshaker[])extensionHandshakers.toArray(new WebSocketServerExtensionHandshaker[0]));
         pipeline.addBefore("handler", "webSocketExtensionHandler", extensionHandler);
      }

   }
}
