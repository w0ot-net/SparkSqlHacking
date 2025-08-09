package org.sparkproject.jetty.client.util;

import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class AbstractRequestContent implements Request.Content {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractRequestContent.class);
   private final AutoLock lock = new AutoLock();
   private final String contentType;

   protected AbstractRequestContent(String contentType) {
      this.contentType = contentType;
   }

   public String getContentType() {
      return this.contentType;
   }

   public Request.Content.Subscription subscribe(Request.Content.Consumer consumer, boolean emitInitialContent) {
      Request.Content.Subscription subscription = this.newSubscription(consumer, emitInitialContent);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Content subscription for {}: {}", subscription, consumer);
      }

      return subscription;
   }

   protected abstract Request.Content.Subscription newSubscription(Request.Content.Consumer var1, boolean var2);

   public abstract class AbstractSubscription implements Request.Content.Subscription {
      private final Request.Content.Consumer consumer;
      private final boolean emitInitialContent;
      private Throwable failure;
      private int demand;
      private boolean stalled;
      private boolean committed;

      public AbstractSubscription(Request.Content.Consumer consumer, boolean emitInitialContent) {
         this.consumer = consumer;
         this.emitInitialContent = emitInitialContent;
         this.stalled = true;
      }

      public void demand() {
         boolean produce;
         try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
            ++this.demand;
            produce = this.stalled;
            if (this.stalled) {
               this.stalled = false;
            }
         }

         if (AbstractRequestContent.LOG.isDebugEnabled()) {
            AbstractRequestContent.LOG.debug("Content demand, producing {} for {}", produce, this);
         }

         if (produce) {
            this.produce();
         }

      }

      private void produce() {
         while(true) {
            Throwable failure;
            boolean committed;
            try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
               failure = this.failure;
               committed = this.committed;
            }

            if (failure != null) {
               this.notifyFailure(failure);
               return;
            }

            if (!committed && !this.emitInitialContent) {
               if (!this.processContent(BufferUtil.EMPTY_BUFFER, false, Callback.NOOP)) {
                  return;
               }
            } else {
               try {
                  if (!this.produceContent(this::processContent)) {
                     return;
                  }
               } catch (Throwable x) {
                  this.fail(x);
               }
            }
         }
      }

      protected abstract boolean produceContent(Producer var1) throws Exception;

      public void fail(Throwable failure) {
         try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
            if (this.failure == null) {
               this.failure = failure;
            }
         }

      }

      private boolean processContent(ByteBuffer content, boolean last, Callback callback) {
         try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
            this.committed = true;
            --this.demand;
         }

         if (content != null) {
            this.notifyContent(content, last, callback);
         } else {
            callback.succeeded();
         }

         boolean noDemand;
         try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
            noDemand = this.demand == 0;
            if (noDemand) {
               this.stalled = true;
            }
         }

         if (noDemand) {
            if (AbstractRequestContent.LOG.isDebugEnabled()) {
               AbstractRequestContent.LOG.debug("No demand, processing stalled for {}", this);
            }

            return false;
         } else {
            return true;
         }
      }

      protected void notifyContent(ByteBuffer buffer, boolean last, Callback callback) {
         try {
            if (AbstractRequestContent.LOG.isDebugEnabled()) {
               AbstractRequestContent.LOG.debug("Notifying content last={} {} for {}", new Object[]{last, BufferUtil.toDetailString(buffer), this});
            }

            this.consumer.onContent(buffer, last, callback);
         } catch (Throwable x) {
            callback.failed(x);
            this.fail(x);
         }

      }

      private void notifyFailure(Throwable failure) {
         try {
            if (AbstractRequestContent.LOG.isDebugEnabled()) {
               AbstractRequestContent.LOG.debug("Notifying failure for {}", this, failure);
            }

            this.consumer.onFailure(failure);
         } catch (Exception x) {
            AbstractRequestContent.LOG.trace("Failure while notifying content failure {}", failure, x);
         }

      }

      public String toString() {
         int demand;
         boolean stalled;
         boolean committed;
         try (AutoLock ignored = AbstractRequestContent.this.lock.lock()) {
            demand = this.demand;
            stalled = this.stalled;
            committed = this.committed;
         }

         return String.format("%s.%s@%x[demand=%d,stalled=%b,committed=%b,emitInitial=%b]", this.getClass().getEnclosingClass().getSimpleName(), this.getClass().getSimpleName(), this.hashCode(), demand, stalled, committed, this.emitInitialContent);
      }
   }

   public interface Producer {
      boolean produce(ByteBuffer var1, boolean var2, Callback var3);
   }
}
