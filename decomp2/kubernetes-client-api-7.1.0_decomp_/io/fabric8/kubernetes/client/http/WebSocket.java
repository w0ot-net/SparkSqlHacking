package io.fabric8.kubernetes.client.http;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public interface WebSocket {
   boolean send(ByteBuffer var1);

   boolean sendClose(int var1, String var2);

   long queueSize();

   void request();

   static URI toWebSocketUri(URI httpUri) {
      if (httpUri != null && httpUri.getScheme().startsWith("http")) {
         String var10000 = httpUri.toString();
         return URI.create("ws" + var10000.substring(4));
      } else {
         return httpUri;
      }
   }

   public interface Listener {
      default void onOpen(WebSocket webSocket) {
      }

      default void onMessage(WebSocket webSocket, String text) {
         webSocket.request();
      }

      default void onMessage(WebSocket webSocket, ByteBuffer bytes) {
         webSocket.request();
      }

      default void onClose(WebSocket webSocket, int code, String reason) {
      }

      default void onError(WebSocket webSocket, Throwable error) {
      }
   }

   public interface Builder extends BasicBuilder {
      CompletableFuture buildAsync(Listener var1);

      Builder subprotocol(String var1);

      Builder header(String var1, String var2);

      Builder setHeader(String var1, String var2);

      Builder uri(URI var1);

      Builder connectTimeout(long var1, TimeUnit var3);
   }
}
