package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Queues;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.GuardedBy;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
final class ListenerCallQueue {
   private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
   private final List listeners = Collections.synchronizedList(new ArrayList());

   public void addListener(Object listener, Executor executor) {
      Preconditions.checkNotNull(listener, "listener");
      Preconditions.checkNotNull(executor, "executor");
      this.listeners.add(new PerListenerQueue(listener, executor));
   }

   public void enqueue(Event event) {
      this.enqueueHelper(event, event);
   }

   public void enqueue(Event event, String label) {
      this.enqueueHelper(event, label);
   }

   private void enqueueHelper(Event event, Object label) {
      Preconditions.checkNotNull(event, "event");
      Preconditions.checkNotNull(label, "label");
      synchronized(this.listeners) {
         for(PerListenerQueue queue : this.listeners) {
            queue.add(event, label);
         }

      }
   }

   public void dispatch() {
      for(int i = 0; i < this.listeners.size(); ++i) {
         ((PerListenerQueue)this.listeners.get(i)).dispatch();
      }

   }

   private static final class PerListenerQueue implements Runnable {
      final Object listener;
      final Executor executor;
      @GuardedBy("this")
      final Queue waitQueue = Queues.newArrayDeque();
      @GuardedBy("this")
      final Queue labelQueue = Queues.newArrayDeque();
      @GuardedBy("this")
      boolean isThreadScheduled;

      PerListenerQueue(Object listener, Executor executor) {
         this.listener = Preconditions.checkNotNull(listener);
         this.executor = (Executor)Preconditions.checkNotNull(executor);
      }

      synchronized void add(Event event, Object label) {
         this.waitQueue.add(event);
         this.labelQueue.add(label);
      }

      void dispatch() {
         boolean scheduleEventRunner = false;
         synchronized(this) {
            if (!this.isThreadScheduled) {
               this.isThreadScheduled = true;
               scheduleEventRunner = true;
            }
         }

         if (scheduleEventRunner) {
            try {
               this.executor.execute(this);
            } catch (RuntimeException e) {
               synchronized(this) {
                  this.isThreadScheduled = false;
               }

               ListenerCallQueue.logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, e);
               throw e;
            }
         }

      }

      public void run() {
         boolean stillRunning = true;

         try {
            while(true) {
               Event<L> nextToRun;
               Object nextLabel;
               synchronized(this) {
                  Preconditions.checkState(this.isThreadScheduled);
                  nextToRun = (Event)this.waitQueue.poll();
                  nextLabel = this.labelQueue.poll();
                  if (nextToRun == null) {
                     this.isThreadScheduled = false;
                     stillRunning = false;
                     return;
                  }
               }

               try {
                  nextToRun.call(this.listener);
               } catch (RuntimeException e) {
                  ListenerCallQueue.logger.log(Level.SEVERE, "Exception while executing callback: " + this.listener + " " + nextLabel, e);
               }
            }
         } finally {
            if (stillRunning) {
               synchronized(this) {
                  this.isThreadScheduled = false;
               }
            }

         }
      }
   }

   interface Event {
      void call(Object listener);
   }
}
