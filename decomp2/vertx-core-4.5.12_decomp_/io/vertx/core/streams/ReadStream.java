package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.impl.future.PromiseInternal;
import io.vertx.core.streams.impl.PipeImpl;
import java.util.function.BiConsumer;
import java.util.stream.Collector;

@VertxGen(
   concrete = false
)
public interface ReadStream extends StreamBase {
   ReadStream exceptionHandler(@Nullable Handler var1);

   @Fluent
   ReadStream handler(@Nullable Handler var1);

   @Fluent
   ReadStream pause();

   @Fluent
   ReadStream resume();

   @Fluent
   ReadStream fetch(long var1);

   @Fluent
   ReadStream endHandler(@Nullable Handler var1);

   default Pipe pipe() {
      this.pause();
      return new PipeImpl(this);
   }

   default Future pipeTo(WriteStream dst) {
      Promise<Void> promise = Promise.promise();
      (new PipeImpl(this)).to(dst, promise);
      return promise.future();
   }

   default void pipeTo(WriteStream dst, Handler handler) {
      (new PipeImpl(this)).to(dst, handler);
   }

   @GenIgnore
   default Future collect(Collector collector) {
      PromiseInternal<R> promise = (PromiseInternal)Promise.promise();
      A cumulation = (A)collector.supplier().get();
      BiConsumer<A, T> accumulator = collector.accumulator();
      this.handler((elt) -> accumulator.accept(cumulation, elt));
      this.endHandler((v) -> {
         R result = (R)collector.finisher().apply(cumulation);
         promise.tryComplete(result);
      });
      this.exceptionHandler(promise::tryFail);
      return promise.future();
   }
}
