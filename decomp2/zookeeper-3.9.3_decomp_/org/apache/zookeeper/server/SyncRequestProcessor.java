package org.apache.zookeeper.server;

import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncRequestProcessor extends ZooKeeperCriticalThread implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(SyncRequestProcessor.class);
   private static final Request REQUEST_OF_DEATH;
   private static int snapCount;
   private static long snapSizeInBytes;
   private int randRoll;
   private long randSize;
   private final BlockingQueue queuedRequests = new LinkedBlockingQueue();
   private final Semaphore snapThreadMutex = new Semaphore(1);
   private final ZooKeeperServer zks;
   private final RequestProcessor nextProcessor;
   private final Queue toFlush;
   private long lastFlushTime;

   public SyncRequestProcessor(ZooKeeperServer zks, RequestProcessor nextProcessor) {
      super("SyncThread:" + zks.getServerId(), zks.getZooKeeperServerListener());
      this.zks = zks;
      this.nextProcessor = nextProcessor;
      this.toFlush = new ArrayDeque(zks.getMaxBatchSize());
   }

   public static void setSnapCount(int count) {
      snapCount = count;
   }

   public static int getSnapCount() {
      return snapCount;
   }

   private long getRemainingDelay() {
      long flushDelay = this.zks.getFlushDelay();
      long duration = Time.currentElapsedTime() - this.lastFlushTime;
      return duration < flushDelay ? flushDelay - duration : 0L;
   }

   private boolean shouldFlush() {
      long flushDelay = this.zks.getFlushDelay();
      long maxBatchSize = (long)this.zks.getMaxBatchSize();
      if (flushDelay > 0L && this.getRemainingDelay() == 0L) {
         return true;
      } else {
         return maxBatchSize > 0L && (long)this.toFlush.size() >= maxBatchSize;
      }
   }

   public static void setSnapSizeInBytes(long size) {
      snapSizeInBytes = size;
   }

   private boolean shouldSnapshot() {
      int logCount = this.zks.getZKDatabase().getTxnCount();
      long logSize = this.zks.getZKDatabase().getTxnSize();
      return logCount > snapCount / 2 + this.randRoll || snapSizeInBytes > 0L && logSize > snapSizeInBytes / 2L + this.randSize;
   }

   private void resetSnapshotStats() {
      this.randRoll = ThreadLocalRandom.current().nextInt(snapCount / 2);
      this.randSize = Math.abs(ThreadLocalRandom.current().nextLong() % (snapSizeInBytes / 2L));
   }

   public void run() {
      try {
         this.resetSnapshotStats();
         this.lastFlushTime = Time.currentElapsedTime();

         while(true) {
            ServerMetrics.getMetrics().SYNC_PROCESSOR_QUEUE_SIZE.add((long)this.queuedRequests.size());
            long pollTime = Math.min(this.zks.getMaxWriteQueuePollTime(), this.getRemainingDelay());
            Request si = (Request)this.queuedRequests.poll(pollTime, TimeUnit.MILLISECONDS);
            if (si == null) {
               this.flush();
               si = (Request)this.queuedRequests.take();
            }

            if (si == REQUEST_OF_DEATH) {
               break;
            }

            long startProcessTime = Time.currentElapsedTime();
            long var10001 = startProcessTime - si.syncQueueStartTime;
            ServerMetrics.getMetrics().SYNC_PROCESSOR_QUEUE_TIME.add(var10001);
            if (!si.isThrottled() && this.zks.getZKDatabase().append(si)) {
               if (this.shouldSnapshot()) {
                  this.resetSnapshotStats();
                  this.zks.getZKDatabase().rollLog();
                  if (!this.snapThreadMutex.tryAcquire()) {
                     LOG.warn("Too busy to snap, skipping");
                  } else {
                     (new ZooKeeperThread("Snapshot Thread") {
                        public void run() {
                           try {
                              SyncRequestProcessor.this.zks.takeSnapshot();
                           } catch (Exception e) {
                              SyncRequestProcessor.LOG.warn("Unexpected exception", e);
                           } finally {
                              SyncRequestProcessor.this.snapThreadMutex.release();
                           }

                        }
                     }).start();
                  }
               }
            } else if (this.toFlush.isEmpty()) {
               if (this.nextProcessor != null) {
                  this.nextProcessor.processRequest(si);
                  if (this.nextProcessor instanceof Flushable) {
                     ((Flushable)this.nextProcessor).flush();
                  }
               }
               continue;
            }

            this.toFlush.add(si);
            if (this.shouldFlush()) {
               this.flush();
            }

            ServerMetrics.getMetrics().SYNC_PROCESS_TIME.add(Time.currentElapsedTime() - startProcessTime);
         }
      } catch (Throwable t) {
         this.handleException(this.getName(), t);
      }

      LOG.info("SyncRequestProcessor exited!");
   }

   private void flush() throws IOException, RequestProcessor.RequestProcessorException {
      if (!this.toFlush.isEmpty()) {
         ServerMetrics.getMetrics().BATCH_SIZE.add((long)this.toFlush.size());
         long flushStartTime = Time.currentElapsedTime();
         this.zks.getZKDatabase().commit();
         ServerMetrics.getMetrics().SYNC_PROCESSOR_FLUSH_TIME.add(Time.currentElapsedTime() - flushStartTime);
         if (this.nextProcessor == null) {
            this.toFlush.clear();
         } else {
            while(!this.toFlush.isEmpty()) {
               Request i = (Request)this.toFlush.remove();
               long latency = Time.currentElapsedTime() - i.syncQueueStartTime;
               ServerMetrics.getMetrics().SYNC_PROCESSOR_QUEUE_AND_FLUSH_TIME.add(latency);
               this.nextProcessor.processRequest(i);
            }

            if (this.nextProcessor instanceof Flushable) {
               ((Flushable)this.nextProcessor).flush();
            }
         }

         this.lastFlushTime = Time.currentElapsedTime();
      }
   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.queuedRequests.add(REQUEST_OF_DEATH);

      try {
         this.join();
         this.flush();
      } catch (InterruptedException var2) {
         LOG.warn("Interrupted while wating for {} to finish", this);
         Thread.currentThread().interrupt();
      } catch (IOException var3) {
         LOG.warn("Got IO exception during shutdown");
      } catch (RequestProcessor.RequestProcessorException var4) {
         LOG.warn("Got request processor exception during shutdown");
      }

      if (this.nextProcessor != null) {
         this.nextProcessor.shutdown();
      }

   }

   public void processRequest(Request request) {
      Objects.requireNonNull(request, "Request cannot be null");
      request.syncQueueStartTime = Time.currentElapsedTime();
      this.queuedRequests.add(request);
      ServerMetrics.getMetrics().SYNC_PROCESSOR_QUEUED.add(1L);
   }

   static {
      REQUEST_OF_DEATH = Request.requestOfDeath;
      snapCount = ZooKeeperServer.getSnapCount();
      snapSizeInBytes = ZooKeeperServer.getSnapSizeInBytes();
   }
}
