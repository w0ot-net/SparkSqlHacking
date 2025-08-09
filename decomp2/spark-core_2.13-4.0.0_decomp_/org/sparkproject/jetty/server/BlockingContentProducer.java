package org.sparkproject.jetty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.thread.AutoLock;

class BlockingContentProducer implements ContentProducer {
   private static final Logger LOG = LoggerFactory.getLogger(BlockingContentProducer.class);
   private final AsyncContentProducer _asyncContentProducer;
   private final AsyncContentProducer.LockedSemaphore _semaphore;

   BlockingContentProducer(AsyncContentProducer delegate) {
      this._asyncContentProducer = delegate;
      this._semaphore = this._asyncContentProducer.newLockedSemaphore();
   }

   public AutoLock lock() {
      return this._asyncContentProducer.lock();
   }

   public void recycle() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("recycling {}", this);
      }

      this._asyncContentProducer.recycle();
   }

   public void reopen() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("reopening {}", this);
      }

      this._asyncContentProducer.reopen();
      this._semaphore.drainPermits();
   }

   public int available() {
      return this._asyncContentProducer.available();
   }

   public boolean hasContent() {
      return this._asyncContentProducer.hasContent();
   }

   public boolean isError() {
      return this._asyncContentProducer.isError();
   }

   public void checkMinDataRate() {
      this._asyncContentProducer.checkMinDataRate();
   }

   public long getRawContentArrived() {
      return this._asyncContentProducer.getRawContentArrived();
   }

   public boolean consumeAll() {
      boolean eof = this._asyncContentProducer.consumeAll();
      this._semaphore.release();
      return eof;
   }

   public HttpInput.Content nextContent() {
      while(true) {
         HttpInput.Content content = this._asyncContentProducer.nextContent();
         if (LOG.isDebugEnabled()) {
            LOG.debug("nextContent async producer returned {}", content);
         }

         if (content != null) {
            return content;
         }

         if (this._asyncContentProducer.isReady()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("nextContent async producer is ready, retrying");
            }
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("nextContent async producer is not ready, waiting on semaphore {}", this._semaphore);
            }

            try {
               this._semaphore.acquire();
            } catch (InterruptedException e) {
               return new HttpInput.ErrorContent(e);
            }
         }
      }
   }

   public void reclaim(HttpInput.Content content) {
      this._asyncContentProducer.reclaim(content);
   }

   public boolean isReady() {
      boolean ready = this.available() > 0;
      if (LOG.isDebugEnabled()) {
         LOG.debug("isReady = {}", ready);
      }

      return ready;
   }

   public HttpInput.Interceptor getInterceptor() {
      return this._asyncContentProducer.getInterceptor();
   }

   public void setInterceptor(HttpInput.Interceptor interceptor) {
      this._asyncContentProducer.setInterceptor(interceptor);
   }

   public boolean onContentProducible() {
      this._semaphore.assertLocked();
      boolean unready = this._asyncContentProducer.isUnready();
      if (LOG.isDebugEnabled()) {
         LOG.debug("onContentProducible releasing semaphore {} unready={}", this._semaphore, unready);
      }

      if (unready) {
         this._semaphore.release();
      }

      return false;
   }
}
