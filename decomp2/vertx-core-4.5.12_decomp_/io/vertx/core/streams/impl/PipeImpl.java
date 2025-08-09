package io.vertx.core.streams.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.streams.Pipe;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;

public class PipeImpl implements Pipe {
   private final Promise result;
   private final ReadStream src;
   private boolean endOnSuccess = true;
   private boolean endOnFailure = true;
   private WriteStream dst;

   public PipeImpl(ReadStream src) {
      this.src = src;
      this.result = Promise.promise();
      Promise var10001 = this.result;
      src.endHandler(var10001::tryComplete);
      var10001 = this.result;
      src.exceptionHandler(var10001::tryFail);
   }

   public synchronized Pipe endOnFailure(boolean end) {
      this.endOnFailure = end;
      return this;
   }

   public synchronized Pipe endOnSuccess(boolean end) {
      this.endOnSuccess = end;
      return this;
   }

   public synchronized Pipe endOnComplete(boolean end) {
      this.endOnSuccess = end;
      this.endOnFailure = end;
      return this;
   }

   private void handleWriteResult(AsyncResult ack) {
      if (ack.failed()) {
         this.result.tryFail((Throwable)(new WriteException(ack.cause())));
      }

   }

   public void to(WriteStream ws, Handler completionHandler) {
      if (ws == null) {
         throw new NullPointerException();
      } else {
         synchronized(this) {
            if (this.dst != null) {
               throw new IllegalStateException();
            }

            this.dst = ws;
            boolean endOnSuccess = this.endOnSuccess;
            boolean endOnFailure = this.endOnFailure;
         }

         Handler<Void> drainHandler = (v) -> this.src.resume();
         this.src.handler((item) -> {
            ws.write(item, this::handleWriteResult);
            if (ws.writeQueueFull()) {
               this.src.pause();
               ws.drainHandler(drainHandler);
            }

         });
         this.src.resume();
         this.result.future().onComplete((ar) -> {
            try {
               this.src.handler((Handler)null);
            } catch (Exception var6) {
            }

            try {
               this.src.exceptionHandler((Handler)null);
            } catch (Exception var5) {
            }

            try {
               this.src.endHandler((Handler)null);
            } catch (Exception var4) {
            }

            if (ar.succeeded()) {
               this.handleSuccess(completionHandler);
            } else {
               Throwable err = ar.cause();
               if (err instanceof WriteException) {
                  this.src.resume();
                  err = err.getCause();
               }

               this.handleFailure(err, completionHandler);
            }

         });
      }
   }

   private void handleSuccess(Handler completionHandler) {
      if (this.endOnSuccess) {
         this.dst.end(completionHandler);
      } else {
         completionHandler.handle(Future.succeededFuture());
      }

   }

   private void handleFailure(Throwable cause, Handler completionHandler) {
      Future<Void> res = Future.failedFuture(cause);
      if (this.endOnFailure) {
         this.dst.end((Handler)((ignore) -> completionHandler.handle(res)));
      } else {
         completionHandler.handle(res);
      }

   }

   public void close() {
      synchronized(this) {
         this.src.exceptionHandler((Handler)null);
         this.src.handler((Handler)null);
         if (this.dst != null) {
            this.dst.drainHandler((Handler)null);
            this.dst.exceptionHandler((Handler)null);
         }
      }

      VertxException err = new VertxException("Pipe closed", true);
      if (this.result.tryFail((Throwable)err)) {
         this.src.resume();
      }

   }

   private static class WriteException extends VertxException {
      private WriteException(Throwable cause) {
         super(cause, true);
      }
   }
}
