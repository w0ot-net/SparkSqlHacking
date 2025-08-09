package io.vertx.core.eventbus.impl;

import io.vertx.core.impl.ContextInternal;
import java.util.Objects;

public class HandlerHolder {
   public final ContextInternal context;
   public final HandlerRegistration handler;
   public final boolean replyHandler;
   public final boolean localOnly;
   private boolean removed;

   public HandlerHolder(HandlerRegistration handler, boolean replyHandler, boolean localOnly, ContextInternal context) {
      this.context = context;
      this.handler = handler;
      this.replyHandler = replyHandler;
      this.localOnly = localOnly;
   }

   boolean setRemoved() {
      boolean unregistered = false;
      synchronized(this) {
         if (!this.removed) {
            this.removed = true;
            unregistered = true;
         }

         return unregistered;
      }
   }

   public synchronized boolean isRemoved() {
      return this.removed;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         HandlerHolder<?> that = (HandlerHolder)o;
         return Objects.equals(this.handler, that.handler);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.handler);
   }

   public long getSeq() {
      return 0L;
   }

   public ContextInternal getContext() {
      return this.context;
   }

   public HandlerRegistration getHandler() {
      return this.handler;
   }

   public boolean isReplyHandler() {
      return this.replyHandler;
   }

   public boolean isLocalOnly() {
      return this.localOnly;
   }
}
