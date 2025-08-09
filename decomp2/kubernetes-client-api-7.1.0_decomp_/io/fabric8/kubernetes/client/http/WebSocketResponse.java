package io.fabric8.kubernetes.client.http;

import java.util.Objects;

public class WebSocketResponse {
   final WebSocketUpgradeResponse webSocketUpgradeResponse;
   final Throwable throwable;
   final WebSocket webSocket;

   public WebSocketResponse(WebSocketUpgradeResponse webSocketUpgradeResponse, Throwable throwable) {
      this.webSocketUpgradeResponse = (WebSocketUpgradeResponse)Objects.requireNonNull(webSocketUpgradeResponse);
      this.throwable = (Throwable)Objects.requireNonNull(throwable);
      this.webSocket = null;
   }

   public WebSocketResponse(WebSocketUpgradeResponse webSocketUpgradeResponse, WebSocket webSocket) {
      this.webSocketUpgradeResponse = (WebSocketUpgradeResponse)Objects.requireNonNull(webSocketUpgradeResponse);
      this.webSocket = (WebSocket)Objects.requireNonNull(webSocket);
      this.throwable = null;
   }
}
