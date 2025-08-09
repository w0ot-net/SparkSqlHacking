package org.sparkproject.jetty.util.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

public class Sweeper extends AbstractLifeCycle implements Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(Sweeper.class);
   private final AtomicReference items = new AtomicReference();
   private final AtomicReference task = new AtomicReference();
   private final Scheduler scheduler;
   private final long period;

   public Sweeper(Scheduler scheduler, long period) {
      this.scheduler = scheduler;
      this.period = period;
   }

   protected void doStart() throws Exception {
      super.doStart();
      this.items.set(new CopyOnWriteArrayList());
      this.activate();
   }

   protected void doStop() throws Exception {
      this.deactivate();
      this.items.set((Object)null);
      super.doStop();
   }

   public int getSize() {
      List<Sweepable> refs = (List)this.items.get();
      return refs == null ? 0 : refs.size();
   }

   public boolean offer(Sweepable sweepable) {
      List<Sweepable> refs = (List)this.items.get();
      if (refs == null) {
         return false;
      } else {
         refs.add(sweepable);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Resource offered {}", sweepable);
         }

         return true;
      }
   }

   public boolean remove(Sweepable sweepable) {
      List<Sweepable> refs = (List)this.items.get();
      return refs != null && refs.remove(sweepable);
   }

   public void run() {
      List<Sweepable> refs = (List)this.items.get();
      if (refs != null) {
         for(Sweepable sweepable : refs) {
            try {
               if (sweepable.sweep()) {
                  refs.remove(sweepable);
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Resource swept {}", sweepable);
                  }
               }
            } catch (Throwable x) {
               LOG.info("Exception while sweeping {}", sweepable, x);
            }
         }

         this.activate();
      }
   }

   private void activate() {
      if (this.isRunning()) {
         Scheduler.Task t = this.scheduler.schedule(this, this.period, TimeUnit.MILLISECONDS);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Scheduled in {} ms sweep task {}", this.period, t);
         }

         this.task.set(t);
      } else if (LOG.isDebugEnabled()) {
         LOG.debug("Skipping sweep task scheduling");
      }

   }

   private void deactivate() {
      Scheduler.Task t = (Scheduler.Task)this.task.getAndSet((Object)null);
      if (t != null) {
         boolean cancelled = t.cancel();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Cancelled ({}) sweep task {}", cancelled, t);
         }
      }

   }

   public interface Sweepable {
      boolean sweep();
   }
}
