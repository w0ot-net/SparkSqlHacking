package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;

public class MessageProducerImpl implements MessageProducer {
   private final Vertx vertx;
   private final EventBusImpl bus;
   private final boolean send;
   private final String address;
   private final boolean localOnly;
   private DeliveryOptions options;

   public MessageProducerImpl(Vertx vertx, String address, boolean send, DeliveryOptions options) {
      this.vertx = vertx;
      this.bus = (EventBusImpl)vertx.eventBus();
      this.address = address;
      this.send = send;
      this.options = options;
      this.localOnly = vertx.isClustered() ? options.isLocalOnly() : true;
   }

   public synchronized MessageProducer deliveryOptions(DeliveryOptions options) {
      this.options = options;
      return this;
   }

   public Future write(Object body) {
      Promise<Void> promise = ((VertxInternal)this.vertx).getOrCreateContext().promise();
      this.write(body, promise);
      return promise.future();
   }

   public void write(Object body, Handler handler) {
      Promise<Void> promise = null;
      if (handler != null) {
         promise = ((VertxInternal)this.vertx).getOrCreateContext().promise(handler);
      }

      this.write(body, promise);
   }

   private void write(Object data, Promise handler) {
      MessageImpl msg = this.bus.createMessage(this.send, this.localOnly, this.address, this.options.getHeaders(), data, this.options.getCodecName());
      this.bus.sendOrPubInternal(msg, this.options, (ReplyHandler)null, handler);
   }

   public String address() {
      return this.address;
   }

   public Future close() {
      return ((ContextInternal)this.vertx.getOrCreateContext()).succeededFuture();
   }

   public void close(Handler handler) {
      Future<Void> fut = this.close();
      if (handler != null) {
         fut.onComplete(handler);
      }

   }
}
