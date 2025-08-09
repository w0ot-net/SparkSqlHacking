package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import java.util.LinkedHashSet;
import java.util.Set;

/** @deprecated */
@Deprecated
public class PromiseAggregator implements GenericFutureListener {
   private final Promise aggregatePromise;
   private final boolean failPending;
   private Set pendingPromises;

   public PromiseAggregator(Promise aggregatePromise, boolean failPending) {
      this.aggregatePromise = (Promise)ObjectUtil.checkNotNull(aggregatePromise, "aggregatePromise");
      this.failPending = failPending;
   }

   public PromiseAggregator(Promise aggregatePromise) {
      this(aggregatePromise, true);
   }

   @SafeVarargs
   public final PromiseAggregator add(Promise... promises) {
      ObjectUtil.checkNotNull(promises, "promises");
      if (promises.length == 0) {
         return this;
      } else {
         synchronized(this) {
            if (this.pendingPromises == null) {
               int size;
               if (promises.length > 1) {
                  size = promises.length;
               } else {
                  size = 2;
               }

               this.pendingPromises = new LinkedHashSet(size);
            }

            for(Promise p : promises) {
               if (p != null) {
                  this.pendingPromises.add(p);
                  p.addListener(this);
               }
            }

            return this;
         }
      }
   }

   public synchronized void operationComplete(Future future) throws Exception {
      if (this.pendingPromises == null) {
         this.aggregatePromise.setSuccess((Object)null);
      } else {
         this.pendingPromises.remove(future);
         if (!future.isSuccess()) {
            Throwable cause = future.cause();
            this.aggregatePromise.setFailure(cause);
            if (this.failPending) {
               for(Promise pendingFuture : this.pendingPromises) {
                  pendingFuture.setFailure(cause);
               }
            }
         } else if (this.pendingPromises.isEmpty()) {
            this.aggregatePromise.setSuccess((Object)null);
         }
      }

   }
}
