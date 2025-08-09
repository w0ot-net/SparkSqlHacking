package org.apache.zookeeper.server.quorum;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.Request;
import org.apache.zookeeper.server.RequestProcessor;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.WorkerService;
import org.apache.zookeeper.server.ZooKeeperCriticalThread;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommitProcessor extends ZooKeeperCriticalThread implements RequestProcessor {
   private static final Logger LOG = LoggerFactory.getLogger(CommitProcessor.class);
   public static final String ZOOKEEPER_COMMIT_PROC_NUM_WORKER_THREADS = "zookeeper.commitProcessor.numWorkerThreads";
   public static final String ZOOKEEPER_COMMIT_PROC_SHUTDOWN_TIMEOUT = "zookeeper.commitProcessor.shutdownTimeout";
   public static final String ZOOKEEPER_COMMIT_PROC_MAX_READ_BATCH_SIZE = "zookeeper.commitProcessor.maxReadBatchSize";
   public static final String ZOOKEEPER_COMMIT_PROC_MAX_COMMIT_BATCH_SIZE = "zookeeper.commitProcessor.maxCommitBatchSize";
   protected LinkedBlockingQueue queuedRequests = new LinkedBlockingQueue();
   protected final LinkedBlockingQueue queuedWriteRequests = new LinkedBlockingQueue();
   private AtomicInteger numReadQueuedRequests = new AtomicInteger(0);
   private AtomicInteger numWriteQueuedRequests = new AtomicInteger(0);
   protected final LinkedBlockingQueue committedRequests = new LinkedBlockingQueue();
   protected final Map pendingRequests = new HashMap(10000);
   protected final AtomicInteger numRequestsProcessing = new AtomicInteger(0);
   RequestProcessor nextProcessor;
   protected volatile boolean stoppedMainLoop = true;
   protected volatile boolean stopped = true;
   private long workerShutdownTimeoutMS;
   protected WorkerService workerPool;
   private Object emptyPoolSync = new Object();
   private static volatile int maxReadBatchSize;
   private static volatile int maxCommitBatchSize;
   boolean matchSyncs;

   public CommitProcessor(RequestProcessor nextProcessor, String id, boolean matchSyncs, ZooKeeperServerListener listener) {
      super("CommitProcessor:" + id, listener);
      this.nextProcessor = nextProcessor;
      this.matchSyncs = matchSyncs;
   }

   private boolean isProcessingRequest() {
      return this.numRequestsProcessing.get() != 0;
   }

   protected boolean needCommit(Request request) {
      if (request.isThrottled()) {
         return false;
      } else {
         switch (request.type) {
            case -11:
            case -10:
               return !request.isLocalSession();
            case -9:
            case -8:
            case -7:
            case -6:
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0:
            case 3:
            case 4:
            case 6:
            case 8:
            case 10:
            case 11:
            case 12:
            case 17:
            case 18:
            default:
               return false;
            case 1:
            case 2:
            case 5:
            case 7:
            case 13:
            case 14:
            case 15:
            case 16:
            case 19:
            case 20:
            case 21:
               return true;
            case 9:
               return this.matchSyncs;
         }
      }
   }

   public void run() {
      try {
         int requestsToProcess = 0;
         boolean commitIsWaiting = false;

         do {
            synchronized(this) {
               commitIsWaiting = !this.committedRequests.isEmpty();
               requestsToProcess = this.queuedRequests.size();
               if (requestsToProcess == 0 && !commitIsWaiting) {
                  while(!this.stopped && requestsToProcess == 0 && !commitIsWaiting) {
                     this.wait();
                     commitIsWaiting = !this.committedRequests.isEmpty();
                     requestsToProcess = this.queuedRequests.size();
                  }
               }
            }

            ServerMetrics.getMetrics().READS_QUEUED_IN_COMMIT_PROCESSOR.add((long)this.numReadQueuedRequests.get());
            ServerMetrics.getMetrics().WRITES_QUEUED_IN_COMMIT_PROCESSOR.add((long)this.numWriteQueuedRequests.get());
            ServerMetrics.getMetrics().COMMITS_QUEUED_IN_COMMIT_PROCESSOR.add((long)this.committedRequests.size());
            long time = Time.currentElapsedTime();
            int readsProcessed = 0;

            Request request;
            while(!this.stopped && requestsToProcess > 0 && (maxReadBatchSize < 0 || readsProcessed <= maxReadBatchSize) && (request = (Request)this.queuedRequests.poll()) != null) {
               --requestsToProcess;
               if (!this.needCommit(request) && !this.pendingRequests.containsKey(request.sessionId)) {
                  ++readsProcessed;
                  this.numReadQueuedRequests.decrementAndGet();
                  this.sendToNextProcessor(request);
               } else {
                  Deque<Request> requests = (Deque)this.pendingRequests.computeIfAbsent(request.sessionId, (sid) -> new ArrayDeque());
                  requests.addLast(request);
                  ServerMetrics.getMetrics().REQUESTS_IN_SESSION_QUEUE.add((long)requests.size());
               }

               if (maxReadBatchSize < 0 && !this.pendingRequests.isEmpty() && !this.committedRequests.isEmpty()) {
                  commitIsWaiting = true;
                  break;
               }
            }

            ServerMetrics.getMetrics().READS_ISSUED_IN_COMMIT_PROC.add((long)readsProcessed);
            if (!commitIsWaiting) {
               commitIsWaiting = !this.committedRequests.isEmpty();
            }

            if (commitIsWaiting && !this.stopped) {
               this.waitForEmptyPool();
               if (this.stopped) {
                  return;
               }

               int commitsToProcess = maxCommitBatchSize;
               Set<Long> queuesToDrain = new HashSet();
               long startWriteTime = Time.currentElapsedTime();

               int commitsProcessed;
               for(commitsProcessed = 0; commitIsWaiting && !this.stopped && commitsToProcess > 0; commitIsWaiting = !this.committedRequests.isEmpty()) {
                  request = (Request)this.committedRequests.peek();
                  if (request.isThrottled()) {
                     LOG.error("Throttled request in committed pool: {}. Exiting.", request);
                     ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
                  }

                  if (!this.queuedWriteRequests.isEmpty() && ((Request)this.queuedWriteRequests.peek()).sessionId == request.sessionId && ((Request)this.queuedWriteRequests.peek()).cxid == request.cxid) {
                     Deque<Request> sessionQueue = (Deque)this.pendingRequests.get(request.sessionId);
                     ServerMetrics.getMetrics().PENDING_SESSION_QUEUE_SIZE.add((long)this.pendingRequests.size());
                     if (sessionQueue == null || sessionQueue.isEmpty() || !this.needCommit((Request)sessionQueue.peek())) {
                        break;
                     }

                     ServerMetrics.getMetrics().REQUESTS_IN_SESSION_QUEUE.add((long)sessionQueue.size());
                     Request topPending = (Request)sessionQueue.poll();
                     topPending.setHdr(request.getHdr());
                     topPending.setTxn(request.getTxn());
                     topPending.setTxnDigest(request.getTxnDigest());
                     topPending.zxid = request.zxid;
                     topPending.commitRecvTime = request.commitRecvTime;
                     request = topPending;
                     if (topPending.isThrottled()) {
                        LOG.error("Throttled request in committed & pending pool: {}. Exiting.", topPending);
                        ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
                     }

                     this.numWriteQueuedRequests.decrementAndGet();
                     this.queuedWriteRequests.poll();
                     queuesToDrain.add(topPending.sessionId);
                  }

                  this.committedRequests.remove();
                  --commitsToProcess;
                  ++commitsProcessed;
                  this.processWrite(request);
               }

               ServerMetrics.getMetrics().WRITE_BATCH_TIME_IN_COMMIT_PROCESSOR.add(Time.currentElapsedTime() - startWriteTime);
               ServerMetrics.getMetrics().WRITES_ISSUED_IN_COMMIT_PROC.add((long)commitsProcessed);
               readsProcessed = 0;

               for(Long sessionId : queuesToDrain) {
                  Deque<Request> sessionQueue = (Deque)this.pendingRequests.get(sessionId);

                  int readsAfterWrite;
                  for(readsAfterWrite = 0; !this.stopped && !sessionQueue.isEmpty() && !this.needCommit((Request)sessionQueue.peek()); ++readsAfterWrite) {
                     this.numReadQueuedRequests.decrementAndGet();
                     this.sendToNextProcessor((Request)sessionQueue.poll());
                  }

                  ServerMetrics.getMetrics().READS_AFTER_WRITE_IN_SESSION_QUEUE.add((long)readsAfterWrite);
                  readsProcessed += readsAfterWrite;
                  if (sessionQueue.isEmpty()) {
                     this.pendingRequests.remove(sessionId);
                  }
               }

               ServerMetrics.getMetrics().SESSION_QUEUES_DRAINED.add((long)queuesToDrain.size());
               ServerMetrics.getMetrics().READ_ISSUED_FROM_SESSION_QUEUE.add((long)readsProcessed);
            }

            ServerMetrics.getMetrics().COMMIT_PROCESS_TIME.add(Time.currentElapsedTime() - time);
            this.endOfIteration();
         } while(!this.stoppedMainLoop);
      } catch (Throwable e) {
         this.handleException(this.getName(), e);
      }

      LOG.info("CommitProcessor exited loop!");
   }

   protected void endOfIteration() {
   }

   protected void waitForEmptyPool() throws InterruptedException {
      int numRequestsInProcess = this.numRequestsProcessing.get();
      if (numRequestsInProcess != 0) {
         ServerMetrics.getMetrics().CONCURRENT_REQUEST_PROCESSING_IN_COMMIT_PROCESSOR.add((long)numRequestsInProcess);
      }

      long startWaitTime = Time.currentElapsedTime();
      synchronized(this.emptyPoolSync) {
         while(!this.stopped && this.isProcessingRequest()) {
            this.emptyPoolSync.wait();
         }
      }

      ServerMetrics.getMetrics().TIME_WAITING_EMPTY_POOL_IN_COMMIT_PROCESSOR_READ.add(Time.currentElapsedTime() - startWaitTime);
   }

   public void start() {
      int numCores = Runtime.getRuntime().availableProcessors();
      int numWorkerThreads = Integer.getInteger("zookeeper.commitProcessor.numWorkerThreads", numCores);
      this.workerShutdownTimeoutMS = Long.getLong("zookeeper.commitProcessor.shutdownTimeout", 5000L);
      initBatchSizes();
      LOG.info("Configuring CommitProcessor with {} worker threads.", numWorkerThreads > 0 ? numWorkerThreads : "no");
      if (this.workerPool == null) {
         this.workerPool = new WorkerService("CommitProcWork", numWorkerThreads, true);
      }

      this.stopped = false;
      this.stoppedMainLoop = false;
      super.start();
   }

   private void sendToNextProcessor(Request request) {
      this.numRequestsProcessing.incrementAndGet();
      CommitWorkRequest workRequest = new CommitWorkRequest(request);
      this.workerPool.schedule(workRequest, request.sessionId);
   }

   private void processWrite(Request request) throws RequestProcessor.RequestProcessorException {
      processCommitMetrics(request, true);
      long timeBeforeFinalProc = Time.currentElapsedTime();
      this.nextProcessor.processRequest(request);
      ServerMetrics.getMetrics().WRITE_FINAL_PROC_TIME.add(Time.currentElapsedTime() - timeBeforeFinalProc);
   }

   private static void initBatchSizes() {
      maxReadBatchSize = Integer.getInteger("zookeeper.commitProcessor.maxReadBatchSize", -1);
      maxCommitBatchSize = Integer.getInteger("zookeeper.commitProcessor.maxCommitBatchSize", 1);
      if (maxCommitBatchSize <= 0) {
         String errorMsg = "maxCommitBatchSize must be positive, was " + maxCommitBatchSize;
         throw new IllegalArgumentException(errorMsg);
      } else {
         LOG.info("Configuring CommitProcessor with readBatchSize {} commitBatchSize {}", maxReadBatchSize, maxCommitBatchSize);
      }
   }

   private static void processCommitMetrics(Request request, boolean isWrite) {
      if (isWrite) {
         if (request.commitProcQueueStartTime != -1L && request.commitRecvTime != -1L) {
            long currentTime = Time.currentElapsedTime();
            long var10001 = currentTime - request.commitProcQueueStartTime;
            ServerMetrics.getMetrics().WRITE_COMMITPROC_TIME.add(var10001);
            var10001 = currentTime - request.commitRecvTime;
            ServerMetrics.getMetrics().LOCAL_WRITE_COMMITTED_TIME.add(var10001);
         } else if (request.commitRecvTime != -1L) {
            ServerMetrics.getMetrics().SERVER_WRITE_COMMITTED_TIME.add(Time.currentElapsedTime() - request.commitRecvTime);
         }
      } else if (request.commitProcQueueStartTime != -1L) {
         ServerMetrics.getMetrics().READ_COMMITPROC_TIME.add(Time.currentElapsedTime() - request.commitProcQueueStartTime);
      }

   }

   public static int getMaxReadBatchSize() {
      return maxReadBatchSize;
   }

   public static int getMaxCommitBatchSize() {
      return maxCommitBatchSize;
   }

   public static void setMaxReadBatchSize(int size) {
      maxReadBatchSize = size;
      LOG.info("Configuring CommitProcessor with readBatchSize {}", maxReadBatchSize);
   }

   public static void setMaxCommitBatchSize(int size) {
      if (size > 0) {
         maxCommitBatchSize = size;
         LOG.info("Configuring CommitProcessor with commitBatchSize {}", maxCommitBatchSize);
      }

   }

   @SuppressFBWarnings({"NN_NAKED_NOTIFY"})
   private synchronized void wakeup() {
      this.notifyAll();
   }

   private void wakeupOnEmpty() {
      synchronized(this.emptyPoolSync) {
         this.emptyPoolSync.notifyAll();
      }
   }

   public void commit(Request request) {
      if (!this.stopped && request != null) {
         LOG.debug("Committing request:: {}", request);
         request.commitRecvTime = Time.currentElapsedTime();
         ServerMetrics.getMetrics().COMMITS_QUEUED.add(1L);
         this.committedRequests.add(request);
         this.wakeup();
      }
   }

   public void processRequest(Request request) {
      if (!this.stopped) {
         LOG.debug("Processing request:: {}", request);
         request.commitProcQueueStartTime = Time.currentElapsedTime();
         this.queuedRequests.add(request);
         if (this.needCommit(request)) {
            this.queuedWriteRequests.add(request);
            this.numWriteQueuedRequests.incrementAndGet();
         } else {
            this.numReadQueuedRequests.incrementAndGet();
         }

         this.wakeup();
      }
   }

   private void halt() {
      this.stoppedMainLoop = true;
      this.stopped = true;
      this.wakeupOnEmpty();
      this.wakeup();
      this.queuedRequests.clear();
      if (this.workerPool != null) {
         this.workerPool.stop();
      }

   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.halt();
      if (this.workerPool != null) {
         this.workerPool.join(this.workerShutdownTimeoutMS);
      }

      if (this.nextProcessor != null) {
         this.nextProcessor.shutdown();
      }

   }

   private class CommitWorkRequest extends WorkerService.WorkRequest {
      private final Request request;

      CommitWorkRequest(Request request) {
         this.request = request;
      }

      public void cleanup() {
         if (!CommitProcessor.this.stopped) {
            CommitProcessor.LOG.error("Exception thrown by downstream processor, unable to continue.");
            CommitProcessor.this.halt();
         }

      }

      public void doWork() throws RequestProcessor.RequestProcessorException {
         try {
            CommitProcessor.processCommitMetrics(this.request, CommitProcessor.this.needCommit(this.request));
            long timeBeforeFinalProc = Time.currentElapsedTime();
            CommitProcessor.this.nextProcessor.processRequest(this.request);
            if (CommitProcessor.this.needCommit(this.request)) {
               ServerMetrics.getMetrics().WRITE_FINAL_PROC_TIME.add(Time.currentElapsedTime() - timeBeforeFinalProc);
            } else {
               ServerMetrics.getMetrics().READ_FINAL_PROC_TIME.add(Time.currentElapsedTime() - timeBeforeFinalProc);
            }
         } finally {
            if (CommitProcessor.this.numRequestsProcessing.decrementAndGet() == 0) {
               CommitProcessor.this.wakeupOnEmpty();
            }

         }

      }
   }
}
