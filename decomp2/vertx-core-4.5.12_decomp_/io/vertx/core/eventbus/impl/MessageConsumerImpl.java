package io.vertx.core.eventbus.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.streams.ReadStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class MessageConsumerImpl extends HandlerRegistration implements MessageConsumer {
   private static final Logger log = LoggerFactory.getLogger(MessageConsumerImpl.class);
   private static final int DEFAULT_MAX_BUFFERED_MESSAGES = 1000;
   private final Vertx vertx;
   private final ContextInternal context;
   private final EventBusImpl eventBus;
   private final String address;
   private final boolean localOnly;
   private Handler handler;
   private Handler completionHandler;
   private Handler endHandler;
   private Handler discardHandler;
   private int maxBufferedMessages = 1000;
   private Queue pending = new ArrayDeque(8);
   private long demand = Long.MAX_VALUE;
   private Promise result;

   MessageConsumerImpl(Vertx vertx, ContextInternal context, EventBusImpl eventBus, String address, boolean localOnly) {
      super(context, eventBus, address, false);
      this.vertx = vertx;
      this.context = context;
      this.eventBus = eventBus;
      this.address = address;
      this.localOnly = localOnly;
   }

   public MessageConsumer setMaxBufferedMessages(int maxBufferedMessages) {
      Arguments.require(maxBufferedMessages >= 0, "Max buffered messages cannot be negative");
      List<Message<T>> discarded;
      Handler<Message<T>> discardHandler;
      synchronized(this) {
         this.maxBufferedMessages = maxBufferedMessages;
         int overflow = this.pending.size() - maxBufferedMessages;
         if (overflow <= 0) {
            return this;
         }

         if (this.pending.isEmpty()) {
            return this;
         }

         discardHandler = this.discardHandler;
         discarded = new ArrayList(overflow);

         while(this.pending.size() > maxBufferedMessages) {
            discarded.add(this.pending.poll());
         }
      }

      for(Message msg : discarded) {
         if (discardHandler != null) {
            discardHandler.handle(msg);
         }

         this.discard(msg);
      }

      return this;
   }

   public synchronized int getMaxBufferedMessages() {
      return this.maxBufferedMessages;
   }

   public String address() {
      return this.address;
   }

   public synchronized void completionHandler(Handler handler) {
      Objects.requireNonNull(handler);
      if (this.result != null) {
         this.result.future().onComplete(handler);
      } else {
         this.completionHandler = handler;
      }

   }

   public synchronized Future unregister() {
      this.handler = null;
      if (this.endHandler != null) {
         this.endHandler.handle((Object)null);
      }

      if (this.pending.size() > 0) {
         Queue<Message<T>> discarded = this.pending;
         Handler<Message<T>> handler = this.discardHandler;
         this.pending = new ArrayDeque();

         for(Message msg : discarded) {
            this.discard(msg);
            if (handler != null) {
               this.context.emit(msg, handler);
            }
         }
      }

      this.discardHandler = null;
      Future<Void> fut = super.unregister();
      Promise<Void> res = this.result;
      if (res != null) {
         fut.onComplete((ar) -> res.tryFail("Consumer unregistered before registration completed"));
         this.result = null;
      }

      return fut;
   }

   protected boolean doReceive(Message message) {
      Handler<Message<T>> theHandler;
      synchronized(this) {
         if (this.handler == null) {
            return false;
         }

         if (this.demand == 0L) {
            if (this.pending.size() < this.maxBufferedMessages) {
               this.pending.add(message);
               return true;
            }

            this.discard(message);
            if (this.discardHandler != null) {
               this.discardHandler.handle(message);
            } else {
               log.warn("Discarding message as more than " + this.maxBufferedMessages + " buffered in paused consumer. address: " + this.address);
            }

            return true;
         }

         if (this.pending.size() > 0) {
            this.pending.add(message);
            message = (Message)this.pending.poll();
         }

         if (this.demand != Long.MAX_VALUE) {
            --this.demand;
         }

         theHandler = this.handler;
      }

      this.deliver(theHandler, message);
      return true;
   }

   protected void dispatch(Message msg, ContextInternal context, Handler handler) {
      if (handler == null) {
         throw new NullPointerException();
      } else {
         context.dispatch(msg, handler);
      }
   }

   private void deliver(Handler theHandler, Message message) {
      this.dispatch(theHandler, message, this.context.duplicate());
      this.checkNextTick();
   }

   private synchronized void checkNextTick() {
      if (!this.pending.isEmpty() && this.demand > 0L) {
         this.context.nettyEventLoop().execute(() -> {
            Message<T> message;
            Handler<Message<T>> theHandler;
            synchronized(this) {
               if (this.demand == 0L || (message = (Message)this.pending.poll()) == null) {
                  return;
               }

               if (this.demand != Long.MAX_VALUE) {
                  --this.demand;
               }

               theHandler = this.handler;
            }

            this.deliver(theHandler, message);
         });
      }

   }

   public synchronized void discardHandler(Handler handler) {
      this.discardHandler = handler;
   }

   public synchronized MessageConsumer handler(Handler h) {
      if (h != null) {
         synchronized(this) {
            this.handler = h;
            if (this.result == null) {
               Promise<Void> p = this.context.promise();
               if (this.completionHandler != null) {
                  p.future().onComplete(this.completionHandler);
               }

               this.result = p;
               Promise<Void> reg = this.context.promise();
               this.register((String)null, this.localOnly, reg);
               reg.future().onComplete((ar) -> {
                  if (ar.succeeded()) {
                     p.tryComplete();
                  } else {
                     p.tryFail(ar.cause());
                  }

               });
            }
         }
      } else {
         this.unregister();
      }

      return this;
   }

   public ReadStream bodyStream() {
      return new BodyReadStream(this);
   }

   public synchronized MessageConsumer pause() {
      this.demand = 0L;
      return this;
   }

   public MessageConsumer resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public synchronized MessageConsumer fetch(long amount) {
      if (amount < 0L) {
         throw new IllegalArgumentException();
      } else {
         this.demand += amount;
         if (this.demand < 0L) {
            this.demand = Long.MAX_VALUE;
         }

         if (this.demand > 0L) {
            this.checkNextTick();
         }

         return this;
      }
   }

   public synchronized MessageConsumer endHandler(Handler endHandler) {
      if (endHandler != null) {
         Context endCtx = this.vertx.getOrCreateContext();
         this.endHandler = (v1) -> endCtx.runOnContext((v2) -> endHandler.handle((Object)null));
      } else {
         this.endHandler = null;
      }

      return this;
   }

   public synchronized MessageConsumer exceptionHandler(Handler handler) {
      return this;
   }

   public synchronized Handler getHandler() {
      return this.handler;
   }
}
