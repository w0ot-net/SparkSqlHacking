package org.apache.zookeeper.server.watch;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.RateLogger;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatcherCleaner extends Thread {
   private static final Logger LOG = LoggerFactory.getLogger(WatcherCleaner.class);
   private final RateLogger RATE_LOGGER;
   private volatile boolean stopped;
   private final Object cleanEvent;
   private final Object processingCompletedEvent;
   private final WorkerService cleaners;
   private final Set deadWatchers;
   private final IDeadWatcherListener listener;
   private final int watcherCleanThreshold;
   private final int watcherCleanIntervalInSeconds;
   private final int maxInProcessingDeadWatchers;
   private final AtomicInteger totalDeadWatchers;

   public WatcherCleaner(IDeadWatcherListener listener) {
      this(listener, Integer.getInteger("zookeeper.watcherCleanThreshold", 1000), Integer.getInteger("zookeeper.watcherCleanIntervalInSeconds", 600), Integer.getInteger("zookeeper.watcherCleanThreadsNum", 2), Integer.getInteger("zookeeper.maxInProcessingDeadWatchers", -1));
   }

   public WatcherCleaner(IDeadWatcherListener listener, int watcherCleanThreshold, int watcherCleanIntervalInSeconds, int watcherCleanThreadsNum, int maxInProcessingDeadWatchers) {
      this.RATE_LOGGER = new RateLogger(LOG);
      this.stopped = false;
      this.cleanEvent = new Object();
      this.processingCompletedEvent = new Object();
      this.totalDeadWatchers = new AtomicInteger();
      this.listener = listener;
      this.watcherCleanThreshold = watcherCleanThreshold;
      this.watcherCleanIntervalInSeconds = watcherCleanIntervalInSeconds;
      int suggestedMaxInProcessingThreshold = watcherCleanThreshold * watcherCleanThreadsNum;
      if (maxInProcessingDeadWatchers > 0 && maxInProcessingDeadWatchers < suggestedMaxInProcessingThreshold) {
         maxInProcessingDeadWatchers = suggestedMaxInProcessingThreshold;
         LOG.info("The maxInProcessingDeadWatchers config is smaller than the suggested one, change it to use {}", suggestedMaxInProcessingThreshold);
      }

      this.maxInProcessingDeadWatchers = maxInProcessingDeadWatchers;
      this.deadWatchers = new HashSet();
      this.cleaners = new WorkerService("DeadWatcherCleanner", watcherCleanThreadsNum, false);
      LOG.info("watcherCleanThreshold={}, watcherCleanIntervalInSeconds={}, watcherCleanThreadsNum={}, maxInProcessingDeadWatchers={}", new Object[]{watcherCleanThreshold, watcherCleanIntervalInSeconds, watcherCleanThreadsNum, maxInProcessingDeadWatchers});
   }

   public void addDeadWatcher(int watcherBit) {
      while(true) {
         if (this.maxInProcessingDeadWatchers > 0 && !this.stopped && this.totalDeadWatchers.get() >= this.maxInProcessingDeadWatchers) {
            try {
               this.RATE_LOGGER.rateLimitLog("Waiting for dead watchers cleaning");
               long startTime = Time.currentElapsedTime();
               synchronized(this.processingCompletedEvent) {
                  this.processingCompletedEvent.wait(100L);
               }

               long latency = Time.currentElapsedTime() - startTime;
               ServerMetrics.getMetrics().ADD_DEAD_WATCHER_STALL_TIME.add(latency);
               continue;
            } catch (InterruptedException var11) {
               LOG.info("Got interrupted while waiting for dead watches queue size");
            }
         }

         synchronized(this) {
            if (this.deadWatchers.add(watcherBit)) {
               this.totalDeadWatchers.incrementAndGet();
               ServerMetrics.getMetrics().DEAD_WATCHERS_QUEUED.add(1L);
               if (this.deadWatchers.size() >= this.watcherCleanThreshold) {
                  synchronized(this.cleanEvent) {
                     this.cleanEvent.notifyAll();
                  }
               }
            }

            return;
         }
      }
   }

   public void run() {
      while(true) {
         if (!this.stopped) {
            label47: {
               synchronized(this.cleanEvent) {
                  try {
                     if (!this.stopped && this.deadWatchers.size() < this.watcherCleanThreshold) {
                        int maxWaitMs = (this.watcherCleanIntervalInSeconds + ThreadLocalRandom.current().nextInt(this.watcherCleanIntervalInSeconds / 2 + 1)) * 1000;
                        this.cleanEvent.wait((long)maxWaitMs);
                     }
                  } catch (InterruptedException var6) {
                     LOG.info("Received InterruptedException while waiting for cleanEvent");
                     break label47;
                  }
               }

               if (this.deadWatchers.isEmpty()) {
                  continue;
               }

               synchronized(this) {
                  final Set<Integer> snapshot = new HashSet(this.deadWatchers);
                  this.deadWatchers.clear();
                  final int total = snapshot.size();
                  LOG.info("Processing {} dead watchers", total);
                  this.cleaners.schedule(new WorkerService.WorkRequest() {
                     public void doWork() throws Exception {
                        long startTime = Time.currentElapsedTime();
                        WatcherCleaner.this.listener.processDeadWatchers(snapshot);
                        long latency = Time.currentElapsedTime() - startTime;
                        WatcherCleaner.LOG.info("Takes {} to process {} watches", latency, total);
                        ServerMetrics.getMetrics().DEAD_WATCHERS_CLEANER_LATENCY.add(latency);
                        ServerMetrics.getMetrics().DEAD_WATCHERS_CLEARED.add((long)total);
                        WatcherCleaner.this.totalDeadWatchers.addAndGet(-total);
                        synchronized(WatcherCleaner.this.processingCompletedEvent) {
                           WatcherCleaner.this.processingCompletedEvent.notifyAll();
                        }
                     }
                  });
                  continue;
               }
            }
         }

         LOG.info("WatcherCleaner thread exited");
         return;
      }
   }

   public void shutdown() {
      this.stopped = true;
      this.deadWatchers.clear();
      this.cleaners.stop();
      this.interrupt();
      if (LOG.isInfoEnabled()) {
         LOG.info("WatcherCleaner thread shutdown is initiated");
      }

   }
}
