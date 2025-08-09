package io.vertx.core.eventbus.impl;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.streams.ReadStream;

public class BodyReadStream implements ReadStream {
   private ReadStream delegate;

   public BodyReadStream(ReadStream delegate) {
      this.delegate = delegate;
   }

   public ReadStream exceptionHandler(Handler handler) {
      this.delegate.exceptionHandler(handler);
      return this;
   }

   public ReadStream handler(Handler handler) {
      if (handler != null) {
         this.delegate.handler((message) -> handler.handle(message.body()));
      } else {
         this.delegate.handler((Handler)null);
      }

      return this;
   }

   public ReadStream pause() {
      this.delegate.pause();
      return this;
   }

   public ReadStream fetch(long amount) {
      this.delegate.fetch(amount);
      return this;
   }

   public ReadStream resume() {
      this.delegate.resume();
      return this;
   }

   public ReadStream endHandler(Handler endHandler) {
      this.delegate.endHandler(endHandler);
      return this;
   }
}
