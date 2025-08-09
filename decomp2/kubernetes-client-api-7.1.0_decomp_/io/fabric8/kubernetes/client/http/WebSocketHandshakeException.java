package io.fabric8.kubernetes.client.http;

import java.io.IOException;

public final class WebSocketHandshakeException extends IOException {
   private final transient WebSocketUpgradeResponse response;

   public WebSocketHandshakeException(WebSocketUpgradeResponse response) {
      this.response = response;
   }

   public WebSocketUpgradeResponse getResponse() {
      return this.response;
   }

   public synchronized WebSocketHandshakeException initCause(Throwable cause) {
      return (WebSocketHandshakeException)super.initCause(cause);
   }
}
