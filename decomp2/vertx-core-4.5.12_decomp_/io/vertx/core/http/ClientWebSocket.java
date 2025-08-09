package io.vertx.core.http;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

@VertxGen
public interface ClientWebSocket extends WebSocket {
   default void connect(int port, String host, String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setPort(port).setHost(host).setURI(requestURI), handler);
   }

   default Future connect(int port, String host, String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setPort(port).setHost(host).setURI(requestURI));
   }

   void connect(WebSocketConnectOptions var1, Handler var2);

   Future connect(WebSocketConnectOptions var1);

   default void connect(String host, String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setHost(host).setURI(requestURI), handler);
   }

   default Future connect(String host, String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setHost(host).setURI(requestURI));
   }

   default void connect(String requestURI, Handler handler) {
      this.connect((new WebSocketConnectOptions()).setURI(requestURI), handler);
   }

   default Future connect(String requestURI) {
      return this.connect((new WebSocketConnectOptions()).setURI(requestURI));
   }

   ClientWebSocket handler(Handler var1);

   ClientWebSocket endHandler(Handler var1);

   ClientWebSocket drainHandler(Handler var1);

   ClientWebSocket closeHandler(Handler var1);

   ClientWebSocket frameHandler(Handler var1);

   ClientWebSocket textMessageHandler(@Nullable Handler var1);

   ClientWebSocket binaryMessageHandler(@Nullable Handler var1);

   ClientWebSocket pongHandler(@Nullable Handler var1);

   ClientWebSocket exceptionHandler(Handler var1);

   ClientWebSocket writeFrame(WebSocketFrame var1, Handler var2);

   ClientWebSocket writeFinalTextFrame(String var1, Handler var2);

   ClientWebSocket writeFinalBinaryFrame(Buffer var1, Handler var2);

   ClientWebSocket writeBinaryMessage(Buffer var1, Handler var2);

   ClientWebSocket writeTextMessage(String var1, Handler var2);

   ClientWebSocket writePing(Buffer var1, Handler var2);

   ClientWebSocket writePong(Buffer var1, Handler var2);
}
