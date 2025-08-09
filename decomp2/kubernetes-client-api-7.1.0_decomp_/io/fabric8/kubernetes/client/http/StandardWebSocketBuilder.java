package io.fabric8.kubernetes.client.http;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.Generated;

public class StandardWebSocketBuilder implements WebSocket.Builder {
   private final StandardHttpClient httpClient;
   private final StandardHttpRequest.Builder builder;
   private String subprotocol;

   public StandardWebSocketBuilder(StandardHttpClient httpClient) {
      this(httpClient, new StandardHttpRequest.Builder());
   }

   public StandardWebSocketBuilder(StandardHttpClient httpClient, StandardHttpRequest.Builder builder) {
      this.httpClient = httpClient;
      this.builder = builder;
   }

   public CompletableFuture buildAsync(WebSocket.Listener listener) {
      return this.httpClient.buildWebSocket(this, listener);
   }

   public StandardWebSocketBuilder subprotocol(String protocol) {
      this.subprotocol = protocol;
      return this;
   }

   public StandardWebSocketBuilder header(String name, String value) {
      this.builder.header(name, value);
      return this;
   }

   public StandardWebSocketBuilder setHeader(String k, String v) {
      this.builder.setHeader(k, v);
      return this;
   }

   public StandardWebSocketBuilder uri(URI uri) {
      this.builder.uri((URI)uri);
      return this;
   }

   public StandardWebSocketBuilder newBuilder() {
      return (new StandardWebSocketBuilder(this.httpClient, this.builder.build().newBuilder())).subprotocol(this.subprotocol);
   }

   public StandardHttpRequest asHttpRequest() {
      return this.builder.build();
   }

   public WebSocket.Builder connectTimeout(long timeout, TimeUnit timeUnit) {
      this.builder.timeout(timeout, timeUnit);
      return this;
   }

   @Generated
   public StandardHttpClient getHttpClient() {
      return this.httpClient;
   }

   @Generated
   public StandardHttpRequest.Builder getBuilder() {
      return this.builder;
   }

   @Generated
   public String getSubprotocol() {
      return this.subprotocol;
   }
}
