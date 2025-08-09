package org.apache.zookeeper.server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.zookeeper.common.Time;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestThrottler extends ZooKeeperCriticalThread {
   private static final Logger LOG = LoggerFactory.getLogger(RequestThrottler.class);
   private final LinkedBlockingQueue submittedRequests = new LinkedBlockingQueue();
   private final ZooKeeperServer zks;
   private volatile boolean stopping;
   private volatile boolean killed;
   private static final String SHUTDOWN_TIMEOUT = "zookeeper.request_throttler.shutdownTimeout";
   private static int shutdownTimeout = Integer.getInteger("zookeeper.request_throttler.shutdownTimeout", 10000);
   private static volatile int maxRequests;
   private static volatile int stallTime;
   private static volatile boolean dropStaleRequests;

   protected boolean shouldThrottleOp(Request request, long elapsedTime) {
      return request.isThrottlable() && ZooKeeperServer.getThrottledOpWaitTime() > 0 && elapsedTime > (long)ZooKeeperServer.getThrottledOpWaitTime();
   }

   public RequestThrottler(ZooKeeperServer zks) {
      super("RequestThrottler", zks.getZooKeeperServerListener());
      this.zks = zks;
      this.stopping = false;
      this.killed = false;
   }

   public static int getMaxRequests() {
      return maxRequests;
   }

   public static void setMaxRequests(int requests) {
      maxRequests = requests;
   }

   public static int getStallTime() {
      return stallTime;
   }

   public static void setStallTime(int time) {
      stallTime = time;
   }

   public static boolean getDropStaleRequests() {
      return dropStaleRequests;
   }

   public static void setDropStaleRequests(boolean drop) {
      dropStaleRequests = drop;
   }

   public void run() {
      try {
         while(!this.killed) {
            Request request = (Request)this.submittedRequests.take();
            if (Request.requestOfDeath != request) {
               if (request.mustDrop()) {
                  continue;
               }

               if (maxRequests > 0) {
                  while(!this.killed) {
                     if (dropStaleRequests && request.isStale()) {
                        this.dropRequest(request);
                        ServerMetrics.getMetrics().STALE_REQUESTS_DROPPED.add(1L);
                        request = null;
                        break;
                     }

                     if (this.zks.getInProcess() < maxRequests) {
                        break;
                     }

                     this.throttleSleep(stallTime);
                  }
               }

               if (!this.killed) {
                  if (request == null) {
                     continue;
                  }

                  if (request.isStale()) {
                     ServerMetrics.getMetrics().STALE_REQUESTS.add(1L);
                  }

                  long elapsedTime = Time.currentElapsedTime() - request.requestThrottleQueueTime;
                  ServerMetrics.getMetrics().REQUEST_THROTTLE_QUEUE_TIME.add(elapsedTime);
                  if (this.shouldThrottleOp(request, elapsedTime)) {
                     request.setIsThrottled(true);
                     ServerMetrics.getMetrics().THROTTLED_OPS.add(1L);
                  }

                  this.zks.submitRequestNow(request);
                  continue;
               }
            }
         }
      } catch (InterruptedException e) {
         LOG.error("Unexpected interruption", e);
      }

      int dropped = this.drainQueue();
      LOG.info("RequestThrottler shutdown. Dropped {} requests", dropped);
   }

   synchronized void throttleSleep(int stallTime) throws InterruptedException {
      ServerMetrics.getMetrics().REQUEST_THROTTLE_WAIT_COUNT.add(1L);
      this.wait((long)stallTime);
   }

   @SuppressFBWarnings(
      value = {"NN_NAKED_NOTIFY"},
      justification = "state change is in ZooKeeperServer.decInProgress() "
   )
   public synchronized void throttleWake() {
      this.notify();
   }

   private int drainQueue() {
      int dropped = 0;
      LOG.info("Draining request throttler queue");

      Request request;
      while((request = (Request)this.submittedRequests.poll()) != null) {
         ++dropped;
         this.dropRequest(request);
      }

      return dropped;
   }

   private void dropRequest(Request request) {
      ServerCnxn conn = request.getConnection();
      if (conn != null) {
         conn.setInvalid();
      }

      this.zks.requestFinished(request);
   }

   public void submitRequest(Request request) {
      if (this.stopping) {
         LOG.debug("Shutdown in progress. Request cannot be processed");
         this.dropRequest(request);
      } else {
         request.requestThrottleQueueTime = Time.currentElapsedTime();
         this.submittedRequests.add(request);
      }

   }

   public int getInflight() {
      return this.submittedRequests.size();
   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.stopping = true;
      this.submittedRequests.add(Request.requestOfDeath);

      try {
         this.join((long)shutdownTimeout);
      } catch (InterruptedException var3) {
         LOG.warn("Interrupted while waiting for {} to finish", this);
      }

      this.killed = true;

      try {
         this.join();
      } catch (InterruptedException var2) {
         LOG.warn("Interrupted while waiting for {} to finish", this);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

   }

   static {
      LOG.info("{} = {} ms", "zookeeper.request_throttler.shutdownTimeout", shutdownTimeout);
      maxRequests = Integer.getInteger("zookeeper.request_throttle_max_requests", 0);
      stallTime = Integer.getInteger("zookeeper.request_throttle_stall_time", 100);
      dropStaleRequests = Boolean.parseBoolean(System.getProperty("zookeeper.request_throttle_drop_stale", "true"));
   }
}
