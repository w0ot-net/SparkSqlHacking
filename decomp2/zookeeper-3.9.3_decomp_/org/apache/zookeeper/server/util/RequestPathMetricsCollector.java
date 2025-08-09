package org.apache.zookeeper.server.util;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.zookeeper.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestPathMetricsCollector {
   private static final Logger LOG = LoggerFactory.getLogger(RequestPathMetricsCollector.class);
   private final int REQUEST_STATS_SLOT_DURATION;
   private final int REQUEST_STATS_SLOT_CAPACITY;
   private final int REQUEST_PREPROCESS_PATH_DEPTH;
   private final float REQUEST_PREPROCESS_SAMPLE_RATE;
   private final long COLLECTOR_INITIAL_DELAY;
   private final long COLLECTOR_DELAY;
   private final int REQUEST_PREPROCESS_TOPPATH_MAX;
   private final boolean enabled;
   public static final String PATH_STATS_SLOT_CAPACITY = "zookeeper.pathStats.slotCapacity";
   public static final String PATH_STATS_SLOT_DURATION = "zookeeper.pathStats.slotDuration";
   public static final String PATH_STATS_MAX_DEPTH = "zookeeper.pathStats.maxDepth";
   public static final String PATH_STATS_SAMPLE_RATE = "zookeeper.pathStats.sampleRate";
   public static final String PATH_STATS_COLLECTOR_INITIAL_DELAY = "zookeeper.pathStats.initialDelay";
   public static final String PATH_STATS_COLLECTOR_DELAY = "zookeeper.pathStats.delay";
   public static final String PATH_STATS_TOP_PATH_MAX = "zookeeper.pathStats.topPathMax";
   public static final String PATH_STATS_ENABLED = "zookeeper.pathStats.enabled";
   private static final String PATH_SEPERATOR = "/";
   private final Map immutableRequestsMap;
   private final ScheduledThreadPoolExecutor scheduledExecutor;
   private final boolean accurateMode;

   public RequestPathMetricsCollector() {
      this(false);
   }

   public RequestPathMetricsCollector(boolean accurateMode) {
      Map<String, PathStatsQueue> requestsMap = new HashMap();
      this.accurateMode = accurateMode;
      this.REQUEST_PREPROCESS_TOPPATH_MAX = Integer.getInteger("zookeeper.pathStats.topPathMax", 20);
      this.REQUEST_STATS_SLOT_DURATION = Integer.getInteger("zookeeper.pathStats.slotDuration", 15);
      this.REQUEST_STATS_SLOT_CAPACITY = Integer.getInteger("zookeeper.pathStats.slotCapacity", 60);
      this.REQUEST_PREPROCESS_PATH_DEPTH = Integer.getInteger("zookeeper.pathStats.maxDepth", 6);
      this.REQUEST_PREPROCESS_SAMPLE_RATE = Float.parseFloat(System.getProperty("zookeeper.pathStats.sampleRate", "0.1"));
      this.COLLECTOR_INITIAL_DELAY = Long.getLong("zookeeper.pathStats.initialDelay", 5L);
      this.COLLECTOR_DELAY = Long.getLong("zookeeper.pathStats.delay", 5L);
      this.enabled = Boolean.getBoolean("zookeeper.pathStats.enabled");
      LOG.info("{} = {}", "zookeeper.pathStats.slotCapacity", this.REQUEST_STATS_SLOT_CAPACITY);
      LOG.info("{} = {}", "zookeeper.pathStats.slotDuration", this.REQUEST_STATS_SLOT_DURATION);
      LOG.info("{} = {}", "zookeeper.pathStats.maxDepth", this.REQUEST_PREPROCESS_PATH_DEPTH);
      LOG.info("{} = {}", "zookeeper.pathStats.initialDelay", this.COLLECTOR_INITIAL_DELAY);
      LOG.info("{} = {}", "zookeeper.pathStats.delay", this.COLLECTOR_DELAY);
      LOG.info("{} = {}", "zookeeper.pathStats.enabled", this.enabled);
      this.scheduledExecutor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
      this.scheduledExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
      this.scheduledExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
      requestsMap.put(Request.op2String(1), new PathStatsQueue(1));
      requestsMap.put(Request.op2String(15), new PathStatsQueue(15));
      requestsMap.put(Request.op2String(19), new PathStatsQueue(19));
      requestsMap.put(Request.op2String(20), new PathStatsQueue(20));
      requestsMap.put(Request.op2String(2), new PathStatsQueue(2));
      requestsMap.put(Request.op2String(3), new PathStatsQueue(3));
      requestsMap.put(Request.op2String(5), new PathStatsQueue(5));
      requestsMap.put(Request.op2String(4), new PathStatsQueue(4));
      requestsMap.put(Request.op2String(6), new PathStatsQueue(6));
      requestsMap.put(Request.op2String(7), new PathStatsQueue(7));
      requestsMap.put(Request.op2String(8), new PathStatsQueue(8));
      requestsMap.put(Request.op2String(12), new PathStatsQueue(12));
      requestsMap.put(Request.op2String(17), new PathStatsQueue(17));
      requestsMap.put(Request.op2String(18), new PathStatsQueue(18));
      requestsMap.put(Request.op2String(105), new PathStatsQueue(105));
      requestsMap.put(Request.op2String(9), new PathStatsQueue(9));
      this.immutableRequestsMap = Collections.unmodifiableMap(requestsMap);
   }

   static boolean isWriteOp(int requestType) {
      switch (requestType) {
         case 1:
         case 2:
         case 5:
         case 7:
         case 9:
         case 13:
         case 14:
         case 15:
         case 16:
         case 19:
         case 20:
            return true;
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
      }
   }

   static String trimPathDepth(String path, int maxDepth) {
      int count = 0;
      StringBuilder sb = new StringBuilder();
      StringTokenizer pathTokenizer = new StringTokenizer(path, "/");

      while(pathTokenizer.hasMoreElements() && count++ < maxDepth) {
         sb.append("/");
         sb.append(pathTokenizer.nextToken());
      }

      path = sb.toString();
      return path;
   }

   public void shutdown() {
      if (this.enabled) {
         LOG.info("shutdown scheduledExecutor");
         this.scheduledExecutor.shutdownNow();
      }
   }

   public void start() {
      if (this.enabled) {
         LOG.info("Start the RequestPath collector");
         this.immutableRequestsMap.forEach((opType, pathStatsQueue) -> pathStatsQueue.start());
         this.scheduledExecutor.scheduleWithFixedDelay(() -> {
            LOG.info("%nHere are the top Read paths:");
            this.logTopPaths(this.aggregatePaths(4, (queue) -> !queue.isWriteOperation()), (entry) -> LOG.info("{} : {}", entry.getKey(), entry.getValue()));
            LOG.info("%nHere are the top Write paths:");
            this.logTopPaths(this.aggregatePaths(4, (queue) -> queue.isWriteOperation()), (entry) -> LOG.info("{} : {}", entry.getKey(), entry.getValue()));
         }, this.COLLECTOR_INITIAL_DELAY, this.COLLECTOR_DELAY, TimeUnit.MINUTES);
      }
   }

   public void registerRequest(int type, String path) {
      if (this.enabled) {
         if (ThreadLocalRandom.current().nextFloat() <= this.REQUEST_PREPROCESS_SAMPLE_RATE) {
            PathStatsQueue pathStatsQueue = (PathStatsQueue)this.immutableRequestsMap.get(Request.op2String(type));
            if (pathStatsQueue != null) {
               pathStatsQueue.registerRequest(path);
            } else {
               LOG.error("We should not handle {}", type);
            }
         }

      }
   }

   public void dumpTopRequestPath(PrintWriter pwriter, String requestTypeName, int queryMaxDepth) {
      if (queryMaxDepth >= 1) {
         PathStatsQueue pathStatsQueue = (PathStatsQueue)this.immutableRequestsMap.get(requestTypeName);
         if (pathStatsQueue == null) {
            pwriter.println("Can not find path stats for type: " + requestTypeName);
         } else {
            pwriter.println("The top requests of type: " + requestTypeName);
            int maxDepth = Math.min(queryMaxDepth, this.REQUEST_PREPROCESS_PATH_DEPTH);
            Map<String, Integer> combinedMap = pathStatsQueue.collectStats(maxDepth);
            this.logTopPaths(combinedMap, (entry) -> pwriter.println((String)entry.getKey() + " : " + entry.getValue()));
         }
      }
   }

   public void dumpTopReadPaths(PrintWriter pwriter, int queryMaxDepth) {
      pwriter.println("The top read requests are");
      this.dumpTopAggregatedPaths(pwriter, queryMaxDepth, (queue) -> !queue.isWriteOperation);
   }

   public void dumpTopWritePaths(PrintWriter pwriter, int queryMaxDepth) {
      pwriter.println("The top write requests are");
      this.dumpTopAggregatedPaths(pwriter, queryMaxDepth, (queue) -> queue.isWriteOperation);
   }

   public void dumpTopPaths(PrintWriter pwriter, int queryMaxDepth) {
      pwriter.println("The top requests are");
      this.dumpTopAggregatedPaths(pwriter, queryMaxDepth, (queue) -> true);
   }

   private void dumpTopAggregatedPaths(PrintWriter pwriter, int queryMaxDepth, Predicate predicate) {
      if (this.enabled) {
         Map<String, Integer> combinedMap = this.aggregatePaths(queryMaxDepth, predicate);
         this.logTopPaths(combinedMap, (entry) -> pwriter.println((String)entry.getKey() + " : " + entry.getValue()));
      }
   }

   Map aggregatePaths(int queryMaxDepth, Predicate predicate) {
      Map<String, Integer> combinedMap = new HashMap(this.REQUEST_PREPROCESS_TOPPATH_MAX);
      int maxDepth = Math.min(queryMaxDepth, this.REQUEST_PREPROCESS_PATH_DEPTH);
      this.immutableRequestsMap.values().stream().filter(predicate).forEach((pathStatsQueue) -> pathStatsQueue.collectStats(maxDepth).forEach((path, count) -> combinedMap.put(path, (Integer)combinedMap.getOrDefault(path, 0) + count)));
      return combinedMap;
   }

   void logTopPaths(Map combinedMap, Consumer output) {
      combinedMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue).reversed()).limit((long)this.REQUEST_PREPROCESS_TOPPATH_MAX).forEach(output);
   }

   class PathStatsQueue {
      private final String requestTypeName;
      private final AtomicReference currentSlot;
      private final LinkedBlockingQueue requestPathStats;
      private final boolean isWriteOperation;

      public PathStatsQueue(int requestType) {
         this.requestTypeName = Request.op2String(requestType);
         this.isWriteOperation = RequestPathMetricsCollector.isWriteOp(requestType);
         this.requestPathStats = new LinkedBlockingQueue(RequestPathMetricsCollector.this.REQUEST_STATS_SLOT_CAPACITY);
         this.currentSlot = new AtomicReference(new ConcurrentLinkedQueue());
      }

      public void registerRequest(String path) {
         if (RequestPathMetricsCollector.this.enabled) {
            ((ConcurrentLinkedQueue)this.currentSlot.get()).offer(path);
         }
      }

      ConcurrentLinkedQueue getCurrentSlot() {
         return (ConcurrentLinkedQueue)this.currentSlot.get();
      }

      Map mapReducePaths(int maxDepth, Collection tobeProcessedSlot) {
         Map<String, Integer> newSlot = new ConcurrentHashMap();
         tobeProcessedSlot.stream().filter((path) -> path != null).forEach((path) -> {
            path = RequestPathMetricsCollector.trimPathDepth(path, maxDepth);
            newSlot.put(path, (Integer)newSlot.getOrDefault(path, 0) + 1);
         });
         return newSlot;
      }

      public Map collectStats(int maxDepth) {
         Map<String, Integer> snapShot = this.mapReducePaths(maxDepth, Arrays.asList((String[])((ConcurrentLinkedQueue)this.currentSlot.get()).toArray(new String[0])));
         synchronized(RequestPathMetricsCollector.this.accurateMode ? this.requestPathStats : new Object()) {
            Map<String, Integer> combinedMap = (Map)this.requestPathStats.stream().reduce(snapShot, (firstMap, secondMap) -> {
               secondMap.forEach((key, value) -> {
                  String trimmedPath = RequestPathMetricsCollector.trimPathDepth(key, maxDepth);
                  firstMap.put(trimmedPath, (Integer)firstMap.getOrDefault(trimmedPath, 0) + value);
               });
               return firstMap;
            });
            return combinedMap;
         }
      }

      public void start() {
         if (RequestPathMetricsCollector.this.enabled) {
            int delay = ThreadLocalRandom.current().nextInt(RequestPathMetricsCollector.this.REQUEST_STATS_SLOT_DURATION);
            RequestPathMetricsCollector.this.scheduledExecutor.scheduleWithFixedDelay(() -> {
               ConcurrentLinkedQueue<String> tobeProcessedSlot = (ConcurrentLinkedQueue)this.currentSlot.getAndSet(new ConcurrentLinkedQueue());

               try {
                  Map<String, Integer> latestSlot = this.mapReducePaths(RequestPathMetricsCollector.this.REQUEST_PREPROCESS_PATH_DEPTH, tobeProcessedSlot);
                  synchronized(RequestPathMetricsCollector.this.accurateMode ? this.requestPathStats : new Object()) {
                     if (this.requestPathStats.remainingCapacity() <= 0) {
                        this.requestPathStats.poll();
                     }

                     if (!this.requestPathStats.offer(latestSlot)) {
                        RequestPathMetricsCollector.LOG.error("Failed to insert the new request path stats for {}", this.requestTypeName);
                     }
                  }
               } catch (Exception e) {
                  RequestPathMetricsCollector.LOG.error("Failed to insert the new request path stats for {} with exception {}", this.requestTypeName, e);
               }

            }, (long)delay, (long)RequestPathMetricsCollector.this.REQUEST_STATS_SLOT_DURATION, TimeUnit.SECONDS);
         }
      }

      boolean isWriteOperation() {
         return this.isWriteOperation;
      }
   }
}
