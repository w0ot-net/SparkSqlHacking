package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryContext;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.utils.ConcurrentCyclicSequence;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.MetricsProvider;
import io.vertx.core.spi.metrics.VertxMetrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.Function;

public class EventBusImpl implements EventBusInternal, MetricsProvider {
   private static final AtomicReferenceFieldUpdater OUTBOUND_INTERCEPTORS_UPDATER = AtomicReferenceFieldUpdater.newUpdater(EventBusImpl.class, Handler[].class, "outboundInterceptors");
   private static final AtomicReferenceFieldUpdater INBOUND_INTERCEPTORS_UPDATER = AtomicReferenceFieldUpdater.newUpdater(EventBusImpl.class, Handler[].class, "inboundInterceptors");
   private volatile Handler[] outboundInterceptors = new Handler[0];
   private volatile Handler[] inboundInterceptors = new Handler[0];
   private final AtomicLong replySequence = new AtomicLong(0L);
   protected final VertxInternal vertx;
   protected final EventBusMetrics metrics;
   protected final ConcurrentMap handlerMap = new ConcurrentHashMap();
   protected final CodecManager codecManager = new CodecManager();
   protected volatile boolean started;

   public EventBusImpl(VertxInternal vertx) {
      VertxMetrics metrics = vertx.metricsSPI();
      this.vertx = vertx;
      this.metrics = metrics != null ? metrics.createEventBusMetrics() : null;
   }

   public EventBus addOutboundInterceptor(Handler interceptor) {
      this.addInterceptor(OUTBOUND_INTERCEPTORS_UPDATER, (Handler)Objects.requireNonNull(interceptor));
      return this;
   }

   public EventBus addInboundInterceptor(Handler interceptor) {
      this.addInterceptor(INBOUND_INTERCEPTORS_UPDATER, (Handler)Objects.requireNonNull(interceptor));
      return this;
   }

   public EventBus removeOutboundInterceptor(Handler interceptor) {
      this.removeInterceptor(OUTBOUND_INTERCEPTORS_UPDATER, (Handler)Objects.requireNonNull(interceptor));
      return this;
   }

   public EventBus removeInboundInterceptor(Handler interceptor) {
      this.removeInterceptor(INBOUND_INTERCEPTORS_UPDATER, (Handler)Objects.requireNonNull(interceptor));
      return this;
   }

   Handler[] inboundInterceptors() {
      return this.inboundInterceptors;
   }

   Handler[] outboundInterceptors() {
      return this.outboundInterceptors;
   }

   public EventBus clusterSerializableChecker(Function classNamePredicate) {
      this.codecManager.clusterSerializableCheck(classNamePredicate);
      return this;
   }

   public EventBus serializableChecker(Function classNamePredicate) {
      this.codecManager.serializableCheck(classNamePredicate);
      return this;
   }

   public synchronized void start(Promise promise) {
      if (this.started) {
         throw new IllegalStateException("Already started");
      } else {
         this.started = true;
         promise.complete();
      }
   }

   public EventBus send(String address, Object message) {
      return this.send(address, message, new DeliveryOptions());
   }

   public EventBus send(String address, Object message, DeliveryOptions options) {
      MessageImpl msg = this.createMessage(true, this.isLocalOnly(options), address, options.getHeaders(), message, options.getCodecName());
      this.sendOrPubInternal(msg, options, (ReplyHandler)null, (Promise)null);
      return this;
   }

   public Future request(String address, Object message, DeliveryOptions options) {
      MessageImpl msg = this.createMessage(true, this.isLocalOnly(options), address, options.getHeaders(), message, options.getCodecName());
      ReplyHandler<T> handler = this.createReplyHandler(msg, true, options);
      this.sendOrPubInternal(msg, options, handler, (Promise)null);
      return handler.result();
   }

   public MessageProducer sender(String address) {
      Objects.requireNonNull(address, "address");
      return new MessageProducerImpl(this.vertx, address, true, new DeliveryOptions());
   }

   public MessageProducer sender(String address, DeliveryOptions options) {
      Objects.requireNonNull(address, "address");
      Objects.requireNonNull(options, "options");
      return new MessageProducerImpl(this.vertx, address, true, options);
   }

   public MessageProducer publisher(String address) {
      Objects.requireNonNull(address, "address");
      return new MessageProducerImpl(this.vertx, address, false, new DeliveryOptions());
   }

   public MessageProducer publisher(String address, DeliveryOptions options) {
      Objects.requireNonNull(address, "address");
      Objects.requireNonNull(options, "options");
      return new MessageProducerImpl(this.vertx, address, false, options);
   }

   public EventBus publish(String address, Object message) {
      return this.publish(address, message, new DeliveryOptions());
   }

   public EventBus publish(String address, Object message, DeliveryOptions options) {
      this.sendOrPubInternal(this.createMessage(false, this.isLocalOnly(options), address, options.getHeaders(), message, options.getCodecName()), options, (ReplyHandler)null, (Promise)null);
      return this;
   }

   public MessageConsumer consumer(String address) {
      this.checkStarted();
      Objects.requireNonNull(address, "address");
      return new MessageConsumerImpl(this.vertx, this.vertx.getOrCreateContext(), this, address, false);
   }

   public MessageConsumer consumer(String address, Handler handler) {
      Objects.requireNonNull(handler, "handler");
      MessageConsumer<T> consumer = this.consumer(address);
      consumer.handler(handler);
      return consumer;
   }

   public MessageConsumer localConsumer(String address) {
      this.checkStarted();
      Objects.requireNonNull(address, "address");
      return new MessageConsumerImpl(this.vertx, this.vertx.getOrCreateContext(), this, address, true);
   }

   public MessageConsumer localConsumer(String address, Handler handler) {
      Objects.requireNonNull(handler, "handler");
      MessageConsumer<T> consumer = this.localConsumer(address);
      consumer.handler(handler);
      return consumer;
   }

   public EventBus registerCodec(MessageCodec codec) {
      this.codecManager.registerCodec(codec);
      return this;
   }

   public EventBus unregisterCodec(String name) {
      this.codecManager.unregisterCodec(name);
      return this;
   }

   public EventBus registerDefaultCodec(Class clazz, MessageCodec codec) {
      this.codecManager.registerDefaultCodec(clazz, codec);
      return this;
   }

   public EventBus unregisterDefaultCodec(Class clazz) {
      this.codecManager.unregisterDefaultCodec(clazz);
      return this;
   }

   public EventBus codecSelector(Function selector) {
      this.codecManager.codecSelector(selector);
      return this;
   }

   public void close(Promise promise) {
      if (!this.started) {
         promise.complete();
      } else {
         this.unregisterAll().onComplete((ar) -> {
            if (this.metrics != null) {
               this.metrics.close();
            }

            promise.handle(ar);
         });
      }
   }

   public boolean isMetricsEnabled() {
      return this.metrics != null;
   }

   public EventBusMetrics getMetrics() {
      return this.metrics;
   }

   public MessageImpl createMessage(boolean send, boolean localOnly, String address, MultiMap headers, Object body, String codecName) {
      Objects.requireNonNull(address, "no null address accepted");
      MessageCodec codec = this.codecManager.lookupCodec(body, codecName, localOnly);
      MessageImpl msg = new MessageImpl(address, headers, body, codec, send, this);
      return msg;
   }

   protected HandlerHolder addRegistration(String address, HandlerRegistration registration, boolean replyHandler, boolean localOnly, Promise promise) {
      HandlerHolder<T> holder = this.addLocalRegistration(address, registration, replyHandler, localOnly);
      this.onLocalRegistration(holder, promise);
      return holder;
   }

   protected void onLocalRegistration(HandlerHolder handlerHolder, Promise promise) {
      if (promise != null) {
         promise.complete();
      }

   }

   private HandlerHolder addLocalRegistration(String address, HandlerRegistration registration, boolean replyHandler, boolean localOnly) {
      Objects.requireNonNull(address, "address");
      ContextInternal context = registration.context;
      HandlerHolder<T> holder = this.createHandlerHolder(registration, replyHandler, localOnly, context);
      ConcurrentCyclicSequence<HandlerHolder> handlers = (new ConcurrentCyclicSequence()).add(holder);
      ConcurrentCyclicSequence<HandlerHolder> actualHandlers = (ConcurrentCyclicSequence)this.handlerMap.merge(address, handlers, (old, prev) -> old.add(prev.first()));
      if (context.isDeployment()) {
         context.addCloseHook(registration);
      }

      return holder;
   }

   protected HandlerHolder createHandlerHolder(HandlerRegistration registration, boolean replyHandler, boolean localOnly, ContextInternal context) {
      return new HandlerHolder(registration, replyHandler, localOnly, context);
   }

   protected void removeRegistration(HandlerHolder handlerHolder, Promise promise) {
      this.removeLocalRegistration(handlerHolder);
      this.onLocalUnregistration(handlerHolder, promise);
   }

   protected void onLocalUnregistration(HandlerHolder handlerHolder, Promise promise) {
      promise.complete();
   }

   private void removeLocalRegistration(HandlerHolder holder) {
      String address = holder.getHandler().address;
      this.handlerMap.compute(address, (key, val) -> {
         if (val == null) {
            return null;
         } else {
            ConcurrentCyclicSequence<HandlerHolder> next = val.remove(holder);
            return next.size() == 0 ? null : next;
         }
      });
      if (holder.setRemoved() && holder.getContext().deploymentID() != null) {
         holder.getContext().removeCloseHook(holder.getHandler());
      }

   }

   protected void sendReply(MessageImpl replyMessage, DeliveryOptions options, ReplyHandler replyHandler) {
      if (replyMessage.address() == null) {
         throw new IllegalStateException("address not specified");
      } else {
         this.sendOrPubInternal(new OutboundDeliveryContext(this.vertx.getOrCreateContext(), replyMessage, options, replyHandler, (Promise)null));
      }
   }

   protected void sendOrPub(OutboundDeliveryContext sendContext) {
      this.sendLocally(sendContext);
   }

   protected void callCompletionHandlerAsync(Handler completionHandler) {
      if (completionHandler != null) {
         this.vertx.runOnContext((v) -> completionHandler.handle(Future.succeededFuture()));
      }

   }

   private void sendLocally(OutboundDeliveryContext sendContext) {
      ReplyException failure = this.deliverMessageLocally(sendContext.message);
      if (failure != null) {
         sendContext.written(failure);
      } else {
         sendContext.written((Throwable)null);
      }

   }

   protected boolean isMessageLocal(MessageImpl msg) {
      return true;
   }

   protected ReplyException deliverMessageLocally(MessageImpl msg) {
      ConcurrentCyclicSequence<HandlerHolder> handlers = (ConcurrentCyclicSequence)this.handlerMap.get(msg.address());
      boolean messageLocal = this.isMessageLocal(msg);
      if (handlers == null) {
         if (this.metrics != null) {
            this.metrics.messageReceived(msg.address(), !msg.isSend(), messageLocal, 0);
         }

         return new ReplyException(ReplyFailure.NO_HANDLERS, "No handlers for address " + msg.address);
      } else {
         if (msg.isSend()) {
            HandlerHolder holder = this.nextHandler(handlers, messageLocal);
            if (this.metrics != null) {
               this.metrics.messageReceived(msg.address(), !msg.isSend(), messageLocal, holder != null ? 1 : 0);
            }

            if (holder != null) {
               holder.handler.receive(msg.copyBeforeReceive());
            }
         } else {
            if (this.metrics != null) {
               this.metrics.messageReceived(msg.address(), !msg.isSend(), messageLocal, handlers.size());
            }

            for(HandlerHolder holder : handlers) {
               if (messageLocal || !holder.isLocalOnly()) {
                  holder.handler.receive(msg.copyBeforeReceive());
               }
            }
         }

         return null;
      }
   }

   protected HandlerHolder nextHandler(ConcurrentCyclicSequence handlers, boolean messageLocal) {
      return (HandlerHolder)handlers.next();
   }

   protected void checkStarted() {
      if (!this.started) {
         throw new IllegalStateException("Event Bus is not started");
      }
   }

   protected String generateReplyAddress() {
      return "__vertx.reply." + Long.toString(this.replySequence.incrementAndGet());
   }

   ReplyHandler createReplyHandler(MessageImpl message, boolean src, DeliveryOptions options) {
      long timeout = options.getSendTimeout();
      String replyAddress = this.generateReplyAddress();
      message.setReplyAddress(replyAddress);
      ReplyHandler<T> handler = new ReplyHandler(this, this.vertx.getOrCreateContext(), replyAddress, message.address, src, timeout);
      handler.register();
      return handler;
   }

   public OutboundDeliveryContext newSendContext(MessageImpl message, DeliveryOptions options, ReplyHandler handler, Promise writePromise) {
      return new OutboundDeliveryContext(this.vertx.getOrCreateContext(), message, options, handler, writePromise);
   }

   public void sendOrPubInternal(OutboundDeliveryContext senderCtx) {
      this.checkStarted();
      senderCtx.bus = this;
      senderCtx.metrics = this.metrics;
      senderCtx.next();
   }

   public void sendOrPubInternal(MessageImpl message, DeliveryOptions options, ReplyHandler handler, Promise writePromise) {
      this.checkStarted();
      if (writePromise == null) {
         this.sendOrPubInternal(this.newSendContext(message, options, handler, (Promise)null));
      } else {
         Promise<Void> promise = Promise.promise();
         this.sendOrPubInternal(this.newSendContext(message, options, handler, promise));
         Future<Void> future = promise.future();
         if (!message.send) {
            future = future.recover((throwable) -> throwable instanceof ReplyException ? Future.failedFuture(throwable) : Future.succeededFuture());
         }

         future.onComplete(writePromise);
      }
   }

   private Future unregisterAll() {
      List<Future<?>> futures = new ArrayList();

      for(ConcurrentCyclicSequence handlers : this.handlerMap.values()) {
         for(HandlerHolder holder : handlers) {
            futures.add(holder.getHandler().unregister());
         }
      }

      return Future.join(futures).mapEmpty();
   }

   private void addInterceptor(AtomicReferenceFieldUpdater updater, Handler interceptor) {
      Handler[] interceptors;
      Handler[] copy;
      do {
         interceptors = (Handler[])updater.get(this);
         copy = (Handler[])Arrays.copyOf(interceptors, interceptors.length + 1);
         copy[interceptors.length] = interceptor;
      } while(!updater.compareAndSet(this, interceptors, copy));

   }

   private void removeInterceptor(AtomicReferenceFieldUpdater updater, Handler interceptor) {
      Handler[] interceptors;
      Handler<DeliveryContext>[] copy;
      do {
         interceptors = (Handler[])updater.get(this);
         int idx = -1;

         for(int i = 0; i < interceptors.length; ++i) {
            if (interceptors[i].equals(interceptor)) {
               idx = i;
               break;
            }
         }

         if (idx == -1) {
            return;
         }

         copy = new Handler[interceptors.length - 1];
         System.arraycopy(interceptors, 0, copy, 0, idx);
         System.arraycopy(interceptors, idx + 1, copy, idx, copy.length - idx);
      } while(!updater.compareAndSet(this, interceptors, copy));

   }

   private boolean isLocalOnly(DeliveryOptions options) {
      return this.vertx.isClustered() ? options.isLocalOnly() : true;
   }
}
