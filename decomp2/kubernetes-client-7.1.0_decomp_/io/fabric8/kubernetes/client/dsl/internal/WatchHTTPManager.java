package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.http.AsyncBody;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class WatchHTTPManager extends AbstractWatchManager {
   private CompletableFuture call;
   private volatile AsyncBody body;

   public WatchHTTPManager(HttpClient client, BaseOperation baseOperation, ListOptions listOptions, Watcher watcher, int reconnectInterval, int reconnectLimit) throws MalformedURLException {
      super(watcher, baseOperation, listOptions, reconnectLimit, reconnectInterval, client);
   }

   protected synchronized void start(URL url, Map headers, AbstractWatchManager.WatchRequestState state) {
      HttpRequest.Builder builder = this.client.newHttpRequestBuilder().url(url).forStreaming();
      Objects.requireNonNull(builder);
      headers.forEach(builder::header);
      StringBuffer buffer = new StringBuffer();
      this.call = this.client.consumeBytes(builder.build(), (b, a) -> {
         for(ByteBuffer content : b) {
            for(char c : StandardCharsets.UTF_8.decode(content).array()) {
               if (c == '\n') {
                  this.onMessage(buffer.toString(), state);
                  buffer.setLength(0);
               } else {
                  buffer.append(c);
               }
            }
         }

         a.consume();
      });
      this.call.whenComplete((response, t) -> {
         if (t != null) {
            this.watchEnded(t, state);
         }

         if (response != null) {
            this.body = (AsyncBody)response.body();
            if (!response.isSuccessful()) {
               Status status = OperationSupport.createStatus(response.code(), response.message());
               if (this.onStatus(status, state)) {
                  return;
               }

               this.watchEnded(new KubernetesClientException(status), state);
            } else {
               this.resetReconnectAttempts(state);
               this.body.consume();
               this.body.done().whenComplete((v, e) -> this.watchEnded(e, state));
            }
         }

      });
   }

   protected synchronized void closeCurrentRequest() {
      Optional.ofNullable(this.call).ifPresent((theFuture) -> theFuture.cancel(true));
      Optional.ofNullable(this.body).ifPresent(AsyncBody::cancel);
   }
}
