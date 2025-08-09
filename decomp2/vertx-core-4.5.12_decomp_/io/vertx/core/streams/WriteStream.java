package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

@VertxGen(
   concrete = false
)
public interface WriteStream extends StreamBase {
   WriteStream exceptionHandler(@Nullable Handler var1);

   Future write(Object var1);

   void write(Object var1, Handler var2);

   default Future end() {
      Promise<Void> promise = Promise.promise();
      this.end((Handler)promise);
      return promise.future();
   }

   void end(Handler var1);

   default Future end(Object data) {
      Promise<Void> provide = Promise.promise();
      this.end(data, provide);
      return provide.future();
   }

   default void end(Object data, Handler handler) {
      if (handler != null) {
         this.write(data, (ar) -> {
            if (ar.succeeded()) {
               this.end(handler);
            } else {
               handler.handle(ar);
            }

         });
      } else {
         this.end(data);
      }

   }

   @Fluent
   WriteStream setWriteQueueMaxSize(int var1);

   boolean writeQueueFull();

   @Fluent
   WriteStream drainHandler(@Nullable Handler var1);
}
