package io.vertx.core.impl.future;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NoStackTraceThrowable;
import java.util.function.Function;

public final class FailedFuture extends FutureBase {
   private final Throwable cause;

   public FailedFuture(Throwable t) {
      this((ContextInternal)null, (Throwable)t);
   }

   public FailedFuture(ContextInternal context, Throwable t) {
      super(context);
      this.cause = (Throwable)(t != null ? t : new NoStackTraceThrowable((String)null));
   }

   public FailedFuture(String failureMessage) {
      this((ContextInternal)null, (String)failureMessage);
   }

   public FailedFuture(ContextInternal context, String failureMessage) {
      this(context, (Throwable)(new NoStackTraceThrowable(failureMessage)));
   }

   public boolean isComplete() {
      return true;
   }

   public Future onComplete(Handler handler) {
      if (handler instanceof Listener) {
         this.emitFailure(this.cause, (Listener)handler);
      } else if (this.context != null) {
         this.context.emit(this, handler);
      } else {
         handler.handle(this);
      }

      return this;
   }

   public Future onSuccess(Handler handler) {
      return this;
   }

   public Future onFailure(Handler handler) {
      if (this.context != null) {
         this.context.emit(this.cause, handler);
      } else {
         handler.handle(this.cause);
      }

      return this;
   }

   public void addListener(Listener listener) {
      this.emitFailure(this.cause, listener);
   }

   public void removeListener(Listener listener) {
   }

   public Object result() {
      return null;
   }

   public Throwable cause() {
      return this.cause;
   }

   public boolean succeeded() {
      return false;
   }

   public boolean failed() {
      return true;
   }

   public Future map(Function mapper) {
      return this;
   }

   public Future map(Object value) {
      return this;
   }

   public Future otherwise(Object value) {
      return new SucceededFuture(this.context, value);
   }

   public String toString() {
      return "Future{cause=" + this.cause.getMessage() + "}";
   }
}
