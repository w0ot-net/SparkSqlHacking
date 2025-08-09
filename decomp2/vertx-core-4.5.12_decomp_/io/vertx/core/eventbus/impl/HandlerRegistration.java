package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingPolicy;

public abstract class HandlerRegistration implements Closeable {
   public final ContextInternal context;
   public final EventBusImpl bus;
   public final String address;
   public final boolean src;
   private HandlerHolder registered;
   private Object metric;

   HandlerRegistration(ContextInternal context, EventBusImpl bus, String address, boolean src) {
      this.context = context;
      this.bus = bus;
      this.src = src;
      this.address = address;
   }

   void receive(MessageImpl msg) {
      if (this.bus.metrics != null) {
         this.bus.metrics.scheduleMessage(this.metric, msg.isLocal());
      }

      this.context.executor().execute(() -> {
         if (!this.doReceive(msg)) {
            this.discard(msg);
         }

      });
   }

   protected abstract boolean doReceive(Message var1);

   protected abstract void dispatch(Message var1, ContextInternal var2, Handler var3);

   synchronized void register(String repliedAddress, boolean localOnly, Promise promise) {
      if (this.registered != null) {
         throw new IllegalStateException();
      } else {
         this.registered = this.bus.addRegistration(this.address, this, repliedAddress != null, localOnly, promise);
         if (this.bus.metrics != null) {
            this.metric = this.bus.metrics.handlerRegistered(this.address, repliedAddress);
         }

      }
   }

   public synchronized boolean isRegistered() {
      return this.registered != null;
   }

   public Future unregister() {
      Promise<Void> promise = this.context.promise();
      synchronized(this) {
         if (this.registered != null) {
            this.bus.removeRegistration(this.registered, promise);
            this.registered = null;
            if (this.bus.metrics != null) {
               this.bus.metrics.handlerUnregistered(this.metric);
            }
         } else {
            promise.complete();
         }
      }

      return promise.future();
   }

   public void unregister(Handler completionHandler) {
      Future<Void> fut = this.unregister();
      if (completionHandler != null) {
         fut.onComplete(completionHandler);
      }

   }

   void dispatch(Handler theHandler, Message message, ContextInternal context) {
      HandlerRegistration<T>.InboundDeliveryContext deliveryCtx = new InboundDeliveryContext((MessageImpl)message, theHandler, context);
      deliveryCtx.dispatch();
   }

   void discard(Message msg) {
      if (this.bus.metrics != null) {
         this.bus.metrics.discardMessage(this.metric, ((MessageImpl)msg).isLocal(), msg);
      }

      String replyAddress = msg.replyAddress();
      if (replyAddress != null) {
         msg.reply(new ReplyException(ReplyFailure.TIMEOUT, "Discarded the request. address: " + replyAddress + ", repliedAddress: " + msg.address()));
      }

   }

   public void close(Promise completion) {
      this.unregister(completion);
   }

   private class InboundDeliveryContext extends DeliveryContextBase {
      private final Handler handler;

      private InboundDeliveryContext(MessageImpl message, Handler handler, ContextInternal context) {
         super(message, message.bus.inboundInterceptors(), context);
         this.handler = handler;
      }

      protected void execute() {
         ContextInternal ctx = access$101(this);
         Object m = HandlerRegistration.this.metric;
         VertxTracer tracer = ctx.tracer();
         if (HandlerRegistration.this.bus.metrics != null) {
            HandlerRegistration.this.bus.metrics.messageDelivered(m, this.message.isLocal());
         }

         if (tracer != null && !HandlerRegistration.this.src) {
            this.message.trace = tracer.receiveRequest(ctx, SpanKind.RPC, TracingPolicy.PROPAGATE, this.message, this.message.isSend() ? "send" : "publish", this.message.headers(), MessageTagExtractor.INSTANCE);
            HandlerRegistration.this.dispatch((Message)this.message, (ContextInternal)ctx, (Handler)this.handler);
            Object trace = this.message.trace;
            if (this.message.replyAddress == null && trace != null) {
               tracer.sendResponse(this.context, (Object)null, trace, (Throwable)null, TagExtractor.empty());
            }
         } else {
            HandlerRegistration.this.dispatch((Message)this.message, (ContextInternal)ctx, (Handler)this.handler);
         }

      }

      public boolean send() {
         return this.message.isSend();
      }

      public Object body() {
         return this.message.receivedBody;
      }

      // $FF: synthetic method
      static ContextInternal access$101(InboundDeliveryContext x0) {
         return x0.context;
      }
   }
}
