package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.http.WebSocketHandshakeException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class WatchConnectionManager extends AbstractWatchManager {
   private final long connectTimeoutMillis;
   protected WatcherWebSocketListener listener;
   private volatile CompletableFuture websocketFuture;
   volatile boolean ready;

   public WatchConnectionManager(HttpClient client, BaseOperation baseOperation, ListOptions listOptions, Watcher watcher, int reconnectInterval, int reconnectLimit, long websocketTimeout) throws MalformedURLException {
      super(watcher, baseOperation, listOptions, reconnectLimit, reconnectInterval, client);
      this.connectTimeoutMillis = websocketTimeout;
   }

   protected void closeCurrentRequest() {
      Optional.ofNullable(this.websocketFuture).ifPresent((theFuture) -> theFuture.whenComplete((w, t) -> Optional.ofNullable(w).ifPresent((ws) -> ws.sendClose(1000, (String)null))));
   }

   public CompletableFuture getWebsocketFuture() {
      return this.websocketFuture;
   }

   protected void start(URL url, Map headers, AbstractWatchManager.WatchRequestState state) {
      this.listener = new WatcherWebSocketListener(this, state);
      WebSocket.Builder builder = this.client.newWebSocketBuilder();
      Objects.requireNonNull(builder);
      headers.forEach(builder::header);
      builder.uri(URI.create(url.toString())).connectTimeout(this.connectTimeoutMillis, TimeUnit.MILLISECONDS);
      this.websocketFuture = builder.buildAsync(this.listener).handle((w, t) -> {
         if (t != null) {
            try {
               if (t instanceof WebSocketHandshakeException) {
                  WebSocketHandshakeException wshe = (WebSocketHandshakeException)t;
                  HttpResponse<?> response = wshe.getResponse();
                  int code = response.code();
                  Status status = OperationSupport.createStatus(response, this.baseOperation.getKubernetesSerialization());
                  t = OperationSupport.requestFailure(this.client.newHttpRequestBuilder().url(url).build(), status, "Received " + code + " on websocket");
               }

               throw KubernetesClientException.launderThrowable(t);
            } finally {
               if (this.ready) {
                  this.watchEnded(t, state);
               }

            }
         } else {
            this.ready = true;
            return w;
         }
      });
   }
}
