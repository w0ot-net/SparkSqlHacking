package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class ForwardingBlockingQueue extends ForwardingQueue implements BlockingQueue {
   protected ForwardingBlockingQueue() {
   }

   protected abstract BlockingQueue delegate();

   @CanIgnoreReturnValue
   public int drainTo(Collection c, int maxElements) {
      return this.delegate().drainTo(c, maxElements);
   }

   @CanIgnoreReturnValue
   public int drainTo(Collection c) {
      return this.delegate().drainTo(c);
   }

   @CanIgnoreReturnValue
   public boolean offer(Object e, long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate().offer(e, timeout, unit);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
      return this.delegate().poll(timeout, unit);
   }

   public void put(Object e) throws InterruptedException {
      this.delegate().put(e);
   }

   public int remainingCapacity() {
      return this.delegate().remainingCapacity();
   }

   @CanIgnoreReturnValue
   public Object take() throws InterruptedException {
      return this.delegate().take();
   }
}
