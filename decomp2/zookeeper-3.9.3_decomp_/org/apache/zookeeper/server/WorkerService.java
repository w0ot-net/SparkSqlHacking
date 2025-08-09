package org.apache.zookeeper.server;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerService {
   private static final Logger LOG = LoggerFactory.getLogger(WorkerService.class);
   private final ArrayList workers = new ArrayList();
   private final String threadNamePrefix;
   private int numWorkerThreads;
   private boolean threadsAreAssignable;
   private volatile boolean stopped = true;

   public WorkerService(String name, int numThreads, boolean useAssignableThreads) {
      this.threadNamePrefix = (name == null ? "" : name) + "Thread";
      this.numWorkerThreads = numThreads;
      this.threadsAreAssignable = useAssignableThreads;
      this.start();
   }

   public void schedule(WorkRequest workRequest) {
      this.schedule(workRequest, 0L);
   }

   public void schedule(WorkRequest workRequest, long id) {
      if (this.stopped) {
         workRequest.cleanup();
      } else {
         ScheduledWorkRequest scheduledWorkRequest = new ScheduledWorkRequest(workRequest);
         int size = this.workers.size();
         if (size > 0) {
            try {
               int workerNum = ((int)(id % (long)size) + size) % size;
               ExecutorService worker = (ExecutorService)this.workers.get(workerNum);
               worker.execute(scheduledWorkRequest);
            } catch (RejectedExecutionException e) {
               LOG.warn("ExecutorService rejected execution", e);
               workRequest.cleanup();
            }
         } else {
            scheduledWorkRequest.run();
         }

      }
   }

   public void start() {
      if (this.numWorkerThreads > 0) {
         if (this.threadsAreAssignable) {
            for(int i = 1; i <= this.numWorkerThreads; ++i) {
               this.workers.add(Executors.newFixedThreadPool(1, new DaemonThreadFactory(this.threadNamePrefix, i)));
            }
         } else {
            this.workers.add(Executors.newFixedThreadPool(this.numWorkerThreads, new DaemonThreadFactory(this.threadNamePrefix)));
         }
      }

      this.stopped = false;
   }

   public void stop() {
      this.stopped = true;

      for(ExecutorService worker : this.workers) {
         worker.shutdown();
      }

   }

   public void join(long shutdownTimeoutMS) {
      long now = Time.currentElapsedTime();
      long endTime = now + shutdownTimeoutMS;

      for(ExecutorService worker : this.workers) {
         boolean terminated = false;

         while((now = Time.currentElapsedTime()) <= endTime) {
            try {
               terminated = worker.awaitTermination(endTime - now, TimeUnit.MILLISECONDS);
               break;
            } catch (InterruptedException var11) {
            }
         }

         if (!terminated) {
            worker.shutdownNow();
         }
      }

   }

   public abstract static class WorkRequest {
      public abstract void doWork() throws Exception;

      public void cleanup() {
      }
   }

   private class ScheduledWorkRequest implements Runnable {
      private final WorkRequest workRequest;

      ScheduledWorkRequest(WorkRequest workRequest) {
         this.workRequest = workRequest;
      }

      public void run() {
         try {
            if (WorkerService.this.stopped) {
               this.workRequest.cleanup();
               return;
            }

            this.workRequest.doWork();
         } catch (Exception e) {
            WorkerService.LOG.warn("Unexpected exception", e);
            this.workRequest.cleanup();
         }

      }
   }

   private static class DaemonThreadFactory implements ThreadFactory {
      final ThreadGroup group;
      final AtomicInteger threadNumber;
      final String namePrefix;

      DaemonThreadFactory(String name) {
         this(name, 1);
      }

      DaemonThreadFactory(String name, int firstThreadNum) {
         this.threadNumber = new AtomicInteger(1);
         this.threadNumber.set(firstThreadNum);
         SecurityManager s = System.getSecurityManager();
         this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
         this.namePrefix = name + "-";
      }

      public Thread newThread(Runnable r) {
         Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
         if (!t.isDaemon()) {
            t.setDaemon(true);
         }

         if (t.getPriority() != 5) {
            t.setPriority(5);
         }

         return t;
      }
   }
}
