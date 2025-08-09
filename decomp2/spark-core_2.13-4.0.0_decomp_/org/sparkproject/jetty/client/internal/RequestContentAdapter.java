package org.sparkproject.jetty.client.internal;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.AsyncContentProvider;
import org.sparkproject.jetty.client.Synchronizable;
import org.sparkproject.jetty.client.api.ContentProvider;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Invocable;

public class RequestContentAdapter implements Request.Content, Request.Content.Subscription, AsyncContentProvider.Listener, Callback {
   private static final Logger LOG = LoggerFactory.getLogger(RequestContentAdapter.class);
   private final AutoLock lock = new AutoLock();
   private final ContentProvider provider;
   private Iterator iterator;
   private Request.Content.Consumer consumer;
   private boolean emitInitialContent;
   private boolean lastContent;
   private boolean committed;
   private int demand;
   private boolean stalled;
   private boolean hasContent;
   private Throwable failure;

   public RequestContentAdapter(ContentProvider provider) {
      this.provider = provider;
      if (provider instanceof AsyncContentProvider) {
         ((AsyncContentProvider)provider).setListener(this);
      }

   }

   public ContentProvider getContentProvider() {
      return this.provider;
   }

   public String getContentType() {
      return this.provider instanceof ContentProvider.Typed ? ((ContentProvider.Typed)this.provider).getContentType() : null;
   }

   public long getLength() {
      return this.provider.getLength();
   }

   public boolean isReproducible() {
      return this.provider.isReproducible();
   }

   public Request.Content.Subscription subscribe(Request.Content.Consumer consumer, boolean emitInitialContent) {
      try (AutoLock ignored = this.lock.lock()) {
         if (this.consumer != null && !this.isReproducible()) {
            throw new IllegalStateException("Multiple subscriptions not supported on " + String.valueOf(this));
         }

         this.iterator = this.provider.iterator();
         this.consumer = consumer;
         this.emitInitialContent = emitInitialContent;
         this.lastContent = false;
         this.committed = false;
         this.demand = 0;
         this.stalled = true;
         this.hasContent = false;
      }

      return this;
   }

   public void demand() {
      boolean produce;
      try (AutoLock ignored = this.lock.lock()) {
         ++this.demand;
         produce = this.stalled;
         if (this.stalled) {
            this.stalled = false;
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content demand, producing {} for {}", produce, this);
      }

      if (produce) {
         this.produce();
      }

   }

   public void fail(Throwable failure) {
      try (AutoLock ignored = this.lock.lock()) {
         if (this.failure == null) {
            this.failure = failure;
         }
      }

      this.failed(failure);
   }

   public void onContent() {
      boolean produce = false;

      try (AutoLock ignored = this.lock.lock()) {
         this.hasContent = true;
         if (this.demand > 0) {
            produce = this.stalled;
            if (this.stalled) {
               this.stalled = false;
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Content event, processing {} for {}", produce, this);
      }

      if (produce) {
         this.produce();
      }

   }

   public void succeeded() {
      if (this.iterator instanceof Callback) {
         ((Callback)this.iterator).succeeded();
      }

      if (this.lastContent && this.iterator instanceof Closeable) {
         IO.close((Closeable)this.iterator);
      }

   }

   public void failed(Throwable x) {
      if (this.iterator == null) {
         this.failed(this.provider, x);
      } else {
         this.failed(this.iterator, x);
      }

   }

   private void failed(Object object, Throwable failure) {
      if (object instanceof Callback) {
         ((Callback)object).failed(failure);
      }

      if (object instanceof Closeable) {
         IO.close((Closeable)object);
      }

   }

   public Invocable.InvocationType getInvocationType() {
      return Invocable.InvocationType.NON_BLOCKING;
   }

   private void produce() {
      while(true) {
         Throwable failure;
         try (AutoLock ignored = this.lock.lock()) {
            failure = this.failure;
         }

         if (failure != null) {
            this.notifyFailure(failure);
            return;
         }

         if (this.committed) {
            ByteBuffer content = this.advance();
            if (content == null) {
               try (AutoLock ignored = this.lock.lock()) {
                  if (this.hasContent) {
                     this.hasContent = false;
                     continue;
                  }

                  this.stalled = true;
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("No content, processing stalled for {}", this);
               }

               return;
            }

            this.notifyContent(content, this.lastContent);
         } else {
            this.committed = true;
            if (this.emitInitialContent) {
               ByteBuffer content = this.advance();
               if (content != null) {
                  this.notifyContent(content, this.lastContent);
               } else {
                  this.notifyContent(BufferUtil.EMPTY_BUFFER, false);
               }
            } else {
               this.notifyContent(BufferUtil.EMPTY_BUFFER, false);
            }
         }

         boolean noDemand;
         try (AutoLock ignored = this.lock.lock()) {
            noDemand = this.demand == 0;
            if (noDemand) {
               this.stalled = true;
            }
         }

         if (noDemand) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No demand, processing stalled for {}", this);
            }

            return;
         }
      }
   }

   private ByteBuffer advance() {
      if (this.iterator instanceof Synchronizable) {
         synchronized(((Synchronizable)this.iterator).getLock()) {
            return this.next();
         }
      } else {
         return this.next();
      }
   }

   private ByteBuffer next() {
      boolean hasNext = this.iterator.hasNext();
      ByteBuffer bytes = hasNext ? (ByteBuffer)this.iterator.next() : null;
      boolean hasMore = hasNext && this.iterator.hasNext();
      this.lastContent = !hasMore;
      return hasNext ? bytes : BufferUtil.EMPTY_BUFFER;
   }

   private void notifyContent(ByteBuffer buffer, boolean last) {
      try (AutoLock ignored = this.lock.lock()) {
         --this.demand;
         this.hasContent = false;
      }

      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Notifying content last={} {} for {}", new Object[]{last, BufferUtil.toDetailString(buffer), this});
         }

         this.consumer.onContent(buffer, last, this);
      } catch (Throwable x) {
         this.fail(x);
      }

   }

   private void notifyFailure(Throwable failure) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Notifying failure for {}", this, failure);
         }

         this.consumer.onFailure(failure);
      } catch (Exception x) {
         LOG.trace("Failure while notifying content failure {}", failure, x);
      }

   }

   public String toString() {
      int demand;
      boolean stalled;
      try (AutoLock ignored = this.lock.lock()) {
         demand = this.demand;
         stalled = this.stalled;
      }

      return String.format("%s@%x[demand=%d,stalled=%b]", this.getClass().getSimpleName(), this.hashCode(), demand, stalled);
   }
}
