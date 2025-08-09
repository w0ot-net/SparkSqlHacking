package org.apache.commons.io.monitor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;
import org.apache.commons.io.ThreadUtils;

public final class FileAlterationMonitor implements Runnable {
   private static final FileAlterationObserver[] EMPTY_ARRAY = new FileAlterationObserver[0];
   private final long intervalMillis;
   private final List observers;
   private Thread thread;
   private ThreadFactory threadFactory;
   private volatile boolean running;

   public FileAlterationMonitor() {
      this(10000L);
   }

   public FileAlterationMonitor(long intervalMillis) {
      this.observers = new CopyOnWriteArrayList();
      this.intervalMillis = intervalMillis;
   }

   public FileAlterationMonitor(long interval, Collection observers) {
      this(interval, (FileAlterationObserver[])((Collection)Optional.ofNullable(observers).orElse(Collections.emptyList())).toArray(EMPTY_ARRAY));
   }

   public FileAlterationMonitor(long interval, FileAlterationObserver... observers) {
      this(interval);
      if (observers != null) {
         Stream.of(observers).forEach(this::addObserver);
      }

   }

   public void addObserver(FileAlterationObserver observer) {
      if (observer != null) {
         this.observers.add(observer);
      }

   }

   public long getInterval() {
      return this.intervalMillis;
   }

   public Iterable getObservers() {
      return new ArrayList(this.observers);
   }

   public void removeObserver(FileAlterationObserver observer) {
      if (observer != null) {
         List var10000 = this.observers;
         Objects.requireNonNull(observer);
         var10000.removeIf(observer::equals);
      }

   }

   public void run() {
      while(true) {
         if (this.running) {
            this.observers.forEach(FileAlterationObserver::checkAndNotify);
            if (this.running) {
               try {
                  ThreadUtils.sleep(Duration.ofMillis(this.intervalMillis));
               } catch (InterruptedException var2) {
               }
               continue;
            }
         }

         return;
      }
   }

   public synchronized void setThreadFactory(ThreadFactory threadFactory) {
      this.threadFactory = threadFactory;
   }

   public synchronized void start() throws Exception {
      if (this.running) {
         throw new IllegalStateException("Monitor is already running");
      } else {
         for(FileAlterationObserver observer : this.observers) {
            observer.initialize();
         }

         this.running = true;
         if (this.threadFactory != null) {
            this.thread = this.threadFactory.newThread(this);
         } else {
            this.thread = new Thread(this);
         }

         this.thread.start();
      }
   }

   public synchronized void stop() throws Exception {
      this.stop(this.intervalMillis);
   }

   public synchronized void stop(long stopInterval) throws Exception {
      if (!this.running) {
         throw new IllegalStateException("Monitor is not running");
      } else {
         this.running = false;

         try {
            this.thread.interrupt();
            this.thread.join(stopInterval);
         } catch (InterruptedException var5) {
            Thread.currentThread().interrupt();
         }

         for(FileAlterationObserver observer : this.observers) {
            observer.destroy();
         }

      }
   }
}
