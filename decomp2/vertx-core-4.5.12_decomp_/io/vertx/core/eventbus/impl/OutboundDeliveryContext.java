package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.impl.clustered.ClusteredMessage;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingPolicy;
import java.util.function.BiConsumer;

public class OutboundDeliveryContext extends DeliveryContextBase implements Handler {
   public final ContextInternal ctx;
   public final DeliveryOptions options;
   public final ReplyHandler replyHandler;
   private final Promise writePromise;
   private boolean src;
   EventBusImpl bus;
   EventBusMetrics metrics;

   OutboundDeliveryContext(ContextInternal ctx, MessageImpl message, DeliveryOptions options, ReplyHandler replyHandler, Promise writePromise) {
      super(message, message.bus.outboundInterceptors(), ctx);
      this.ctx = ctx;
      this.options = options;
      this.replyHandler = replyHandler;
      this.writePromise = writePromise;
   }

   public void handle(AsyncResult event) {
      this.written(event.cause());
   }

   public void written(Throwable failure) {
      if (this.metrics != null) {
         boolean remote = this.message instanceof ClusteredMessage && ((ClusteredMessage)this.message).isToWire();
         this.metrics.messageSent(this.message.address(), !this.message.send, !remote, remote);
      }

      VertxTracer tracer = this.ctx.tracer();
      if (tracer != null) {
         Object trace = this.message.trace;
         if (trace != null && this.src) {
            if (this.replyHandler != null) {
               this.replyHandler.trace = this.message.trace;
            } else {
               tracer.receiveResponse(this.ctx, (Object)null, trace, failure, TagExtractor.empty());
            }
         }
      }

      if (failure instanceof ReplyException && this.replyHandler != null) {
         this.replyHandler.fail((ReplyException)failure);
      }

      if (this.writePromise != null) {
         if (failure == null) {
            this.writePromise.tryComplete();
         } else {
            this.writePromise.tryFail(failure);
         }
      }

   }

   protected void execute() {
      VertxTracer tracer = this.ctx.tracer();
      if (tracer != null) {
         if (this.message.trace == null) {
            this.src = true;
            BiConsumer<String, String> biConsumer = (key, val) -> this.message.headers().set(key, val);
            TracingPolicy tracingPolicy = this.options.getTracingPolicy();
            if (tracingPolicy == null) {
               tracingPolicy = TracingPolicy.PROPAGATE;
            }

            this.message.trace = tracer.sendRequest(this.ctx, SpanKind.RPC, tracingPolicy, this.message, this.message.send ? "send" : "publish", biConsumer, MessageTagExtractor.INSTANCE);
         } else {
            tracer.sendResponse(this.ctx, (Object)null, this.message.trace, (Throwable)null, TagExtractor.empty());
         }
      }

      this.bus.sendOrPub(this);
   }

   public boolean send() {
      return this.message.isSend();
   }

   public Object body() {
      return this.message.sentBody;
   }
}
