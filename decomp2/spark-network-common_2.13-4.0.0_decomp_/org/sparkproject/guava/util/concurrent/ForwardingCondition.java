package org.sparkproject.guava.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
abstract class ForwardingCondition implements Condition {
   abstract Condition delegate();

   public void await() throws InterruptedException {
      this.delegate().await();
   }

   public boolean await(long time, TimeUnit unit) throws InterruptedException {
      return this.delegate().await(time, unit);
   }

   public void awaitUninterruptibly() {
      this.delegate().awaitUninterruptibly();
   }

   public long awaitNanos(long nanosTimeout) throws InterruptedException {
      return this.delegate().awaitNanos(nanosTimeout);
   }

   public boolean awaitUntil(Date deadline) throws InterruptedException {
      return this.delegate().awaitUntil(deadline);
   }

   public void signal() {
      this.delegate().signal();
   }

   public void signalAll() {
      this.delegate().signalAll();
   }
}
