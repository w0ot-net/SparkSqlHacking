package io.vertx.ext.web.codec.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.codec.spi.BodyStream;

public class StreamingBodyCodec implements BodyCodec {
   private final WriteStream stream;
   private final boolean close;
   private Throwable error;

   public StreamingBodyCodec(WriteStream stream) {
      this(stream, true);
   }

   public StreamingBodyCodec(WriteStream stream, boolean close) {
      this.stream = stream;
      this.close = close;
   }

   public void init() {
      this.stream.exceptionHandler((err) -> {
         synchronized(this) {
            this.error = err;
         }
      });
   }

   public void create(Handler handler) {
      AsyncResult<BodyStream<Void>> result;
      synchronized(this) {
         if (this.error != null) {
            result = Future.failedFuture(this.error);
         } else {
            result = Future.succeededFuture(new BodyStream() {
               final Promise promise = Promise.promise();

               public Future result() {
                  return this.promise.future();
               }

               public void handle(Throwable cause) {
                  this.promise.tryFail(cause);
               }

               public WriteStream exceptionHandler(Handler handler) {
                  StreamingBodyCodec.this.stream.exceptionHandler(handler);
                  return this;
               }

               public void write(Buffer data, Handler handler) {
                  StreamingBodyCodec.this.stream.write(data, handler);
               }

               public Future write(Buffer data) {
                  Promise<Void> promise = Promise.promise();
                  this.write((Buffer)data, promise);
                  return promise.future();
               }

               public void end(Handler handler) {
                  if (StreamingBodyCodec.this.close) {
                     StreamingBodyCodec.this.stream.end((ar) -> {
                        if (ar.succeeded()) {
                           this.promise.tryComplete();
                        } else {
                           this.promise.tryFail(ar.cause());
                        }

                        if (handler != null) {
                           handler.handle(ar);
                        }

                     });
                  } else {
                     this.promise.tryComplete();
                     if (handler != null) {
                        handler.handle(Future.succeededFuture());
                     }
                  }

               }

               public WriteStream setWriteQueueMaxSize(int maxSize) {
                  StreamingBodyCodec.this.stream.setWriteQueueMaxSize(maxSize);
                  return this;
               }

               public boolean writeQueueFull() {
                  return StreamingBodyCodec.this.stream.writeQueueFull();
               }

               public WriteStream drainHandler(Handler handler) {
                  StreamingBodyCodec.this.stream.drainHandler(handler);
                  return this;
               }
            });
         }
      }

      handler.handle(result);
   }
}
