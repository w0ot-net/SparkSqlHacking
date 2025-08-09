package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface HttpClientResponse extends ReadStream, HttpResponseHead {
   HttpClientResponse fetch(long var1);

   HttpClientResponse resume();

   HttpClientResponse exceptionHandler(Handler var1);

   HttpClientResponse handler(Handler var1);

   HttpClientResponse pause();

   HttpClientResponse endHandler(Handler var1);

   @CacheReturn
   NetSocket netSocket();

   @Nullable String getTrailer(String var1);

   @CacheReturn
   MultiMap trailers();

   @Fluent
   default HttpClientResponse bodyHandler(Handler bodyHandler) {
      this.body().onSuccess(bodyHandler);
      return this;
   }

   @Fluent
   default HttpClientResponse body(Handler handler) {
      Future<Buffer> fut = this.body();
      fut.onComplete(handler);
      return this;
   }

   Future body();

   default void end(Handler handler) {
      this.end().onComplete(handler);
   }

   Future end();

   @Fluent
   HttpClientResponse customFrameHandler(Handler var1);

   @CacheReturn
   HttpClientRequest request();

   @Fluent
   HttpClientResponse streamPriorityHandler(Handler var1);
}
