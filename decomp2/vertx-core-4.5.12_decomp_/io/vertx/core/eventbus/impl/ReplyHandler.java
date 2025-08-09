package io.vertx.core.eventbus.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;

class ReplyHandler extends HandlerRegistration implements Handler {
   private final EventBusImpl eventBus;
   private final ContextInternal context;
   private final Promise result;
   private final long timeoutID;
   private final long timeout;
   private final boolean src;
   private final String repliedAddress;
   Object trace;

   ReplyHandler(EventBusImpl eventBus, ContextInternal context, String address, String repliedAddress, boolean src, long timeout) {
      super(context, eventBus, address, src);
      this.eventBus = eventBus;
      this.context = context;
      this.result = context.promise();
      this.src = src;
      this.repliedAddress = repliedAddress;
      this.timeoutID = eventBus.vertx.setTimer(timeout, this);
      this.timeout = timeout;
   }

   private void trace(Object reply, Throwable failure) {
      VertxTracer tracer = this.context.tracer();
      Object trace = this.trace;
      if (tracer != null && this.src && trace != null) {
         tracer.receiveResponse(this.context, reply, trace, failure, TagExtractor.empty());
      }

   }

   Future result() {
      return this.result.future();
   }

   void fail(ReplyException failure) {
      if (this.eventBus.vertx.cancelTimer(this.timeoutID)) {
         this.unregister();
         this.doFail(failure);
      }

   }

   private void doFail(ReplyException failure) {
      this.trace((Object)null, failure);
      this.result.fail((Throwable)failure);
      if (this.eventBus.metrics != null) {
         this.eventBus.metrics.replyFailure(this.repliedAddress, failure.failureType());
      }

   }

   public void handle(Long id) {
      this.unregister();
      this.doFail(new ReplyException(ReplyFailure.TIMEOUT, "Timed out after waiting " + this.timeout + "(ms) for a reply. address: " + this.address + ", repliedAddress: " + this.repliedAddress));
   }

   protected boolean doReceive(Message reply) {
      this.dispatch((Handler)null, reply, this.context);
      return true;
   }

   void register() {
      this.register(this.repliedAddress, true, (Promise)null);
   }

   protected void dispatch(Message reply, ContextInternal context, Handler handler) {
      if (this.eventBus.vertx.cancelTimer(this.timeoutID)) {
         this.unregister();
         if (reply.body() instanceof ReplyException) {
            this.doFail((ReplyException)reply.body());
         } else {
            this.trace(reply, (Throwable)null);
            this.result.complete(reply);
         }
      }

   }
}
