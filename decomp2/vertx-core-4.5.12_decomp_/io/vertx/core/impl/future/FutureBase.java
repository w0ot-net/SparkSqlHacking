package io.vertx.core.impl.future;

import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.OrderedEventExecutor;
import io.netty.util.concurrent.ScheduledFuture;
import io.vertx.core.AsyncResult;
import io.vertx.core.Expectation;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NoStackTraceTimeoutException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

abstract class FutureBase implements FutureInternal {
   protected final ContextInternal context;

   FutureBase() {
      this((ContextInternal)null);
   }

   FutureBase(ContextInternal context) {
      this.context = context;
   }

   public final ContextInternal context() {
      return this.context;
   }

   protected final void emitSuccess(Object value, Listener listener) {
      if (this.context != null && !this.context.isRunningOnContext()) {
         this.context.execute((Runnable)(() -> {
            ContextInternal prev = this.context.beginDispatch();

            try {
               listener.onSuccess(value);
            } finally {
               this.context.endDispatch(prev);
            }

         }));
      } else {
         listener.onSuccess(value);
      }

   }

   protected final void emitFailure(Throwable cause, Listener listener) {
      if (this.context != null && !this.context.isRunningOnContext()) {
         this.context.execute((Runnable)(() -> {
            ContextInternal prev = this.context.beginDispatch();

            try {
               listener.onFailure(cause);
            } finally {
               this.context.endDispatch(prev);
            }

         }));
      } else {
         listener.onFailure(cause);
      }

   }

   public Future compose(Function successMapper, Function failureMapper) {
      Objects.requireNonNull(successMapper, "No null success mapper accepted");
      Objects.requireNonNull(failureMapper, "No null failure mapper accepted");
      Composition<T, U> operation = new Composition(this.context, successMapper, failureMapper);
      this.addListener(operation);
      return operation;
   }

   public Future transform(Function mapper) {
      Objects.requireNonNull(mapper, "No null mapper accepted");
      Transformation<T, U> operation = new Transformation(this.context, mapper);
      this.addListener(operation);
      return operation;
   }

   public Future eventually(Function mapper) {
      Objects.requireNonNull(mapper, "No null mapper accepted");
      Eventually<T, U> operation = new Eventually(this.context, mapper);
      this.addListener(operation);
      return operation;
   }

   public Future map(Function mapper) {
      Objects.requireNonNull(mapper, "No null mapper accepted");
      Mapping<T, U> operation = new Mapping(this.context, mapper);
      this.addListener(operation);
      return operation;
   }

   public Future map(Object value) {
      FixedMapping<T, V> transformation = new FixedMapping(this.context, value);
      this.addListener(transformation);
      return transformation;
   }

   public Future otherwise(Function mapper) {
      Objects.requireNonNull(mapper, "No null mapper accepted");
      Otherwise<T> transformation = new Otherwise(this.context, mapper);
      this.addListener(transformation);
      return transformation;
   }

   public Future otherwise(Object value) {
      FixedOtherwise<T> operation = new FixedOtherwise(this.context, value);
      this.addListener(operation);
      return operation;
   }

   public Future expecting(Expectation expectation) {
      Expect<T> expect = new Expect(this.context, expectation);
      this.addListener(expect);
      return expect;
   }

   public Future timeout(long delay, TimeUnit unit) {
      if (this.isComplete()) {
         return this;
      } else {
         OrderedEventExecutor instance;
         final Promise<T> promise;
         if (this.context != null) {
            instance = this.context.nettyEventLoop();
            promise = this.context.promise();
         } else {
            instance = GlobalEventExecutor.INSTANCE;
            promise = Promise.promise();
         }

         final ScheduledFuture<?> task = instance.schedule(() -> {
            String msg = "Timeout " + unit.toMillis(delay) + " (ms) fired";
            promise.fail((Throwable)(new NoStackTraceTimeoutException(msg)));
         }, delay, unit);
         this.addListener(new Listener() {
            public void onSuccess(Object value) {
               if (task.cancel(false)) {
                  promise.complete(value);
               }

            }

            public void onFailure(Throwable failure) {
               if (task.cancel(false)) {
                  promise.fail(failure);
               }

            }
         });
         return promise.future();
      }
   }
}
