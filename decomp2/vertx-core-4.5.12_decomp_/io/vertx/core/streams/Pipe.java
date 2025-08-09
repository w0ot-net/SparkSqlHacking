package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;

@VertxGen
public interface Pipe {
   @Fluent
   Pipe endOnFailure(boolean var1);

   @Fluent
   Pipe endOnSuccess(boolean var1);

   @Fluent
   Pipe endOnComplete(boolean var1);

   default Future to(WriteStream dst) {
      Promise<Void> promise = Promise.promise();
      this.to(dst, promise);
      return promise.future();
   }

   void to(WriteStream var1, Handler var2);

   void close();
}
