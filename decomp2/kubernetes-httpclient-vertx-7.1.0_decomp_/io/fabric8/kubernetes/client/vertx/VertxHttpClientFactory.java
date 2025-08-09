package io.fabric8.kubernetes.client.vertx;

import io.fabric8.kubernetes.client.http.HttpClient;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClientOptions;

public class VertxHttpClientFactory implements HttpClient.Factory {
   final Vertx sharedVertx;

   public VertxHttpClientFactory() {
      this((Vertx)null);
   }

   public VertxHttpClientFactory(Vertx sharedVertx) {
      this.sharedVertx = sharedVertx;
   }

   public VertxHttpClientBuilder newBuilder() {
      return new VertxHttpClientBuilder(this, this.sharedVertx);
   }

   protected void additionalConfig(WebClientOptions options) {
   }
}
