package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class WatcherWebSocketListener implements WebSocket.Listener {
   protected static final Logger logger = LoggerFactory.getLogger(WatcherWebSocketListener.class);
   protected final AbstractWatchManager.WatchRequestState state;
   protected final AbstractWatchManager manager;

   protected WatcherWebSocketListener(AbstractWatchManager manager, AbstractWatchManager.WatchRequestState state) {
      this.manager = manager;
      this.state = state;
   }

   public void onOpen(WebSocket webSocket) {
      logger.debug("WebSocket successfully opened");
      this.manager.resetReconnectAttempts(this.state);
   }

   public void onError(WebSocket webSocket, Throwable t) {
      this.manager.watchEnded(t, this.state);
   }

   public void onMessage(WebSocket webSocket, String text) {
      try {
         this.manager.onMessage(text, this.state);
      } finally {
         webSocket.request();
      }

   }

   public void onMessage(WebSocket webSocket, ByteBuffer bytes) {
      this.onMessage(webSocket, StandardCharsets.UTF_8.decode(bytes).toString());
   }

   public void onClose(WebSocket webSocket, int code, String reason) {
      logger.debug("WebSocket close received. code: {}, reason: {}", code, reason);

      try {
         webSocket.sendClose(code, reason);
      } finally {
         this.manager.watchEnded((Throwable)null, this.state);
      }

   }
}
