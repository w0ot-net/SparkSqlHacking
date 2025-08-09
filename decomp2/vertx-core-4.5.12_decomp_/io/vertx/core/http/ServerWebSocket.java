package io.vertx.core.http;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.HostAndPort;
import javax.net.ssl.SSLSession;

@VertxGen
public interface ServerWebSocket extends WebSocketBase {
   ServerWebSocket exceptionHandler(Handler var1);

   ServerWebSocket handler(Handler var1);

   ServerWebSocket pause();

   ServerWebSocket resume();

   ServerWebSocket fetch(long var1);

   ServerWebSocket endHandler(Handler var1);

   ServerWebSocket setWriteQueueMaxSize(int var1);

   ServerWebSocket drainHandler(Handler var1);

   ServerWebSocket writeFrame(WebSocketFrame var1, Handler var2);

   ServerWebSocket writeFinalTextFrame(String var1, Handler var2);

   ServerWebSocket writeFinalBinaryFrame(Buffer var1, Handler var2);

   ServerWebSocket writeBinaryMessage(Buffer var1, Handler var2);

   ServerWebSocket writeTextMessage(String var1, Handler var2);

   ServerWebSocket writePing(Buffer var1, Handler var2);

   ServerWebSocket writePong(Buffer var1, Handler var2);

   ServerWebSocket closeHandler(Handler var1);

   ServerWebSocket frameHandler(Handler var1);

   @Nullable String scheme();

   /** @deprecated */
   @Deprecated
   @Nullable String host();

   @Nullable HostAndPort authority();

   String uri();

   String path();

   @Nullable String query();

   /** @deprecated */
   @Deprecated
   void accept();

   /** @deprecated */
   @Deprecated
   void reject();

   /** @deprecated */
   @Deprecated
   void reject(int var1);

   /** @deprecated */
   @Deprecated
   void setHandshake(Future var1, Handler var2);

   /** @deprecated */
   @Deprecated
   Future setHandshake(Future var1);

   Future close();

   @GenIgnore({"permitted-type"})
   SSLSession sslSession();
}
