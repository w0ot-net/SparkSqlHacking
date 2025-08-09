package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

@VertxGen
public interface WebSocket extends WebSocketBase {
   WebSocket exceptionHandler(Handler var1);

   WebSocket handler(Handler var1);

   WebSocket pause();

   WebSocket resume();

   WebSocket fetch(long var1);

   WebSocket endHandler(Handler var1);

   WebSocket setWriteQueueMaxSize(int var1);

   WebSocket drainHandler(Handler var1);

   WebSocket writeFrame(WebSocketFrame var1, Handler var2);

   WebSocket writeFinalTextFrame(String var1, Handler var2);

   WebSocket writeFinalBinaryFrame(Buffer var1, Handler var2);

   WebSocket writeBinaryMessage(Buffer var1, Handler var2);

   WebSocket writeTextMessage(String var1, Handler var2);

   WebSocket writePing(Buffer var1, Handler var2);

   WebSocket writePong(Buffer var1, Handler var2);

   WebSocket closeHandler(Handler var1);

   WebSocket frameHandler(Handler var1);
}
