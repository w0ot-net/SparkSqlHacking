package io.vertx.core.eventbus.impl;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryContext;
import io.vertx.core.eventbus.Message;
import io.vertx.core.impl.ContextInternal;

abstract class DeliveryContextBase implements DeliveryContext {
   public final MessageImpl message;
   public final ContextInternal context;
   private final Handler[] interceptors;
   private int interceptorIdx;
   private boolean invoking;
   private boolean invokeNext;

   protected DeliveryContextBase(MessageImpl message, Handler[] interceptors, ContextInternal context) {
      this.message = message;
      this.interceptors = interceptors;
      this.context = context;
      this.interceptorIdx = 0;
   }

   void dispatch() {
      this.interceptorIdx = 0;
      if (this.invoking) {
         this.invokeNext = true;
      } else {
         this.next();
      }

   }

   public Message message() {
      return this.message;
   }

   protected abstract void execute();

   public void next() {
      if (this.invoking) {
         this.invokeNext = true;
      } else {
         while(this.interceptorIdx < this.interceptors.length) {
            Handler<DeliveryContext> interceptor = this.interceptors[this.interceptorIdx];
            this.invoking = true;
            ++this.interceptorIdx;
            if (this.context.inThread()) {
               this.context.dispatch(this, interceptor);
            } else {
               try {
                  interceptor.handle(this);
               } catch (Throwable t) {
                  this.context.reportException(t);
               }
            }

            this.invoking = false;
            if (!this.invokeNext) {
               return;
            }

            this.invokeNext = false;
         }

         this.interceptorIdx = 0;
         this.execute();
      }

   }
}
