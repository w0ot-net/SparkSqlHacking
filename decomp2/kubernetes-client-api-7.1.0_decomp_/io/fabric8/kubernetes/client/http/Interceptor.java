package io.fabric8.kubernetes.client.http;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Interceptor {
   default void before(BasicBuilder builder, HttpRequest request, RequestTags tags) {
   }

   default void after(HttpRequest request, HttpResponse response, AsyncBody.Consumer consumer) {
   }

   default AsyncBody.Consumer consumer(AsyncBody.Consumer consumer, HttpRequest request) {
      return consumer;
   }

   default CompletableFuture afterFailure(BasicBuilder builder, HttpResponse response, RequestTags tags) {
      return CompletableFuture.completedFuture(false);
   }

   default CompletableFuture afterFailure(HttpRequest.Builder builder, HttpResponse response, RequestTags tags) {
      return this.afterFailure((BasicBuilder)builder, response, tags);
   }

   default void afterConnectionFailure(HttpRequest request, Throwable failure) {
   }

   public interface RequestTags {
      Object getTag(Class var1);
   }
}
