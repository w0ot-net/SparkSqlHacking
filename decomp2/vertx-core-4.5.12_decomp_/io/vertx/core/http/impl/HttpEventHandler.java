package io.vertx.core.http.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.ContextInternal;

class HttpEventHandler {
   final ContextInternal context;
   private Handler chunkHandler;
   private Handler endHandler;
   private Handler exceptionHandler;
   private Buffer body;
   private Promise bodyPromise;
   private Promise endPromise;

   HttpEventHandler(ContextInternal context) {
      this.context = context;
   }

   void chunkHandler(Handler handler) {
      this.chunkHandler = handler;
   }

   void endHandler(Handler handler) {
      this.endHandler = handler;
   }

   void exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
   }

   void handleChunk(Buffer chunk) {
      Handler<Buffer> handler = this.chunkHandler;
      if (handler != null) {
         this.context.dispatch(chunk, handler);
      }

      if (this.body != null) {
         this.body.appendBuffer(chunk);
      }

   }

   Future body() {
      if (this.body == null) {
         this.body = Buffer.buffer();
         this.bodyPromise = this.context.promise();
      }

      return this.bodyPromise.future();
   }

   Future end() {
      if (this.endPromise == null) {
         this.endPromise = this.context.promise();
      }

      return this.endPromise.future();
   }

   void handleEnd() {
      Handler<Void> handler = this.endHandler;
      if (handler != null) {
         this.context.dispatch(handler);
      }

      if (this.bodyPromise != null) {
         this.bodyPromise.tryComplete(this.body);
      }

      if (this.endPromise != null) {
         this.endPromise.tryComplete();
      }

   }

   void handleException(Throwable err) {
      Handler<Throwable> handler = this.exceptionHandler;
      if (handler != null) {
         this.context.dispatch(err, handler);
      }

      if (this.bodyPromise != null) {
         this.bodyPromise.tryFail(err);
      }

      if (this.endPromise != null) {
         this.endPromise.tryFail(err);
      }

   }
}
