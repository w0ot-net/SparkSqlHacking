package io.vertx.core.shareddata.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.shareddata.Counter;
import java.util.concurrent.atomic.AtomicLong;

public class AsynchronousCounter implements Counter {
   private final VertxInternal vertx;
   private final AtomicLong counter;

   public AsynchronousCounter(VertxInternal vertx) {
      this.vertx = vertx;
      this.counter = new AtomicLong();
   }

   public AsynchronousCounter(VertxInternal vertx, AtomicLong counter) {
      this.vertx = vertx;
      this.counter = counter;
   }

   public Future get() {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.get());
      return promise.future();
   }

   public Future incrementAndGet() {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.incrementAndGet());
      return promise.future();
   }

   public Future getAndIncrement() {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.getAndIncrement());
      return promise.future();
   }

   public Future decrementAndGet() {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.decrementAndGet());
      return promise.future();
   }

   public Future addAndGet(long value) {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.addAndGet(value));
      return promise.future();
   }

   public Future getAndAdd(long value) {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Long> promise = context.promise();
      promise.complete(this.counter.getAndAdd(value));
      return promise.future();
   }

   public Future compareAndSet(long expected, long value) {
      ContextInternal context = this.vertx.getOrCreateContext();
      Promise<Boolean> promise = context.promise();
      promise.complete(this.counter.compareAndSet(expected, value));
      return promise.future();
   }
}
