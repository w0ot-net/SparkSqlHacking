package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import java.util.Objects;

@VertxGen
public interface Counter {
   default void get(Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.get().onComplete(resultHandler);
   }

   Future get();

   default void incrementAndGet(Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.incrementAndGet().onComplete(resultHandler);
   }

   Future incrementAndGet();

   default void getAndIncrement(Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getAndIncrement().onComplete(resultHandler);
   }

   Future getAndIncrement();

   default void decrementAndGet(Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.decrementAndGet().onComplete(resultHandler);
   }

   Future decrementAndGet();

   default void addAndGet(long value, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.addAndGet(value).onComplete(resultHandler);
   }

   Future addAndGet(long var1);

   default void getAndAdd(long value, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.getAndAdd(value).onComplete(resultHandler);
   }

   Future getAndAdd(long var1);

   default void compareAndSet(long expected, long value, Handler resultHandler) {
      Objects.requireNonNull(resultHandler, "resultHandler");
      this.compareAndSet(expected, value).onComplete(resultHandler);
   }

   Future compareAndSet(long var1, long var3);
}
