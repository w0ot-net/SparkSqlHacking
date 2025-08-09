package io.vertx.core.impl.future;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import java.util.Objects;
import java.util.function.Function;

public final class SucceededFuture extends FutureBase {
   public static final SucceededFuture EMPTY = new SucceededFuture((ContextInternal)null, (Object)null);
   private final Object result;

   public SucceededFuture(Object result) {
      this((ContextInternal)null, result);
   }

   public SucceededFuture(ContextInternal context, Object result) {
      super(context);
      this.result = result;
   }

   public boolean isComplete() {
      return true;
   }

   public Future onSuccess(Handler handler) {
      if (this.context != null) {
         this.context.emit(this.result, handler);
      } else {
         handler.handle(this.result);
      }

      return this;
   }

   public Future onFailure(Handler handler) {
      return this;
   }

   public Future onComplete(Handler handler) {
      if (handler instanceof Listener) {
         this.emitSuccess(this.result, (Listener)handler);
      } else if (this.context != null) {
         this.context.emit(this, handler);
      } else {
         handler.handle(this);
      }

      return this;
   }

   public void addListener(Listener listener) {
      this.emitSuccess(this.result, listener);
   }

   public void removeListener(Listener listener) {
   }

   public Object result() {
      return this.result;
   }

   public Throwable cause() {
      return null;
   }

   public boolean succeeded() {
      return true;
   }

   public boolean failed() {
      return false;
   }

   public Future map(Object value) {
      return new SucceededFuture(this.context, value);
   }

   public Future otherwise(Function mapper) {
      Objects.requireNonNull(mapper, "No null mapper accepted");
      return this;
   }

   public Future otherwise(Object value) {
      return this;
   }

   public String toString() {
      return "Future{result=" + this.result + "}";
   }
}
