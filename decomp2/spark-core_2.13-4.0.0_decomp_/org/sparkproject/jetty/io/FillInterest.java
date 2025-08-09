package org.sparkproject.jetty.io;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadPendingException;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

public abstract class FillInterest {
   private static final Logger LOG = LoggerFactory.getLogger(FillInterest.class);
   private final AtomicReference _interested = new AtomicReference((Object)null);

   protected FillInterest() {
   }

   public void register(Callback callback) throws ReadPendingException {
      if (!this.tryRegister(callback)) {
         LOG.warn("Read pending for {} prevented {}", this._interested, callback);
         throw new ReadPendingException();
      }
   }

   public boolean tryRegister(Callback callback) {
      if (callback == null) {
         throw new IllegalArgumentException();
      } else if (!this._interested.compareAndSet((Object)null, callback)) {
         return false;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("interested {}", this);
         }

         try {
            this.needsFillInterest();
         } catch (Throwable e) {
            this.onFail(e);
         }

         return true;
      }
   }

   public boolean fillable() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("fillable {}", this);
      }

      Callback callback = (Callback)this._interested.get();
      if (callback != null && this._interested.compareAndSet(callback, (Object)null)) {
         callback.succeeded();
         return true;
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} lost race {}", this, callback);
         }

         return false;
      }
   }

   public boolean isInterested() {
      return this._interested.get() != null;
   }

   public Invocable.InvocationType getCallbackInvocationType() {
      Callback callback = (Callback)this._interested.get();
      return Invocable.getInvocationType(callback);
   }

   public boolean onFail(Throwable cause) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onFail {}", this, cause);
      }

      Callback callback = (Callback)this._interested.get();
      if (callback != null && this._interested.compareAndSet(callback, (Object)null)) {
         callback.failed(cause);
         return true;
      } else {
         return false;
      }
   }

   public void onClose() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onClose {}", this);
      }

      Callback callback = (Callback)this._interested.get();
      if (callback != null && this._interested.compareAndSet(callback, (Object)null)) {
         callback.failed(new ClosedChannelException());
      }

   }

   public String toString() {
      return String.format("FillInterest@%x{%s}", this.hashCode(), this._interested.get());
   }

   public String toStateString() {
      return this._interested.get() == null ? "-" : "FI";
   }

   protected abstract void needsFillInterest() throws IOException;
}
