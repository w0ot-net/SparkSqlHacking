package org.apache.zookeeper.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.zookeeper.DeleteContainerRequest;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContainerManager {
   private static final Logger LOG = LoggerFactory.getLogger(ContainerManager.class);
   private final ZKDatabase zkDb;
   private final RequestProcessor requestProcessor;
   private final int checkIntervalMs;
   private final int maxPerMinute;
   private final long maxNeverUsedIntervalMs;
   private final Timer timer;
   private final AtomicReference task;

   public ContainerManager(ZKDatabase zkDb, RequestProcessor requestProcessor, int checkIntervalMs, int maxPerMinute) {
      this(zkDb, requestProcessor, checkIntervalMs, maxPerMinute, 0L);
   }

   public ContainerManager(ZKDatabase zkDb, RequestProcessor requestProcessor, int checkIntervalMs, int maxPerMinute, long maxNeverUsedIntervalMs) {
      this.task = new AtomicReference((Object)null);
      this.zkDb = zkDb;
      this.requestProcessor = requestProcessor;
      this.checkIntervalMs = checkIntervalMs;
      this.maxPerMinute = maxPerMinute;
      this.maxNeverUsedIntervalMs = maxNeverUsedIntervalMs;
      this.timer = new Timer("ContainerManagerTask", true);
      LOG.info("Using checkIntervalMs={} maxPerMinute={} maxNeverUsedIntervalMs={}", new Object[]{checkIntervalMs, maxPerMinute, maxNeverUsedIntervalMs});
   }

   public void start() {
      if (this.task.get() == null) {
         TimerTask timerTask = new TimerTask() {
            public void run() {
               try {
                  ContainerManager.this.checkContainers();
               } catch (InterruptedException var2) {
                  Thread.currentThread().interrupt();
                  ContainerManager.LOG.info("interrupted");
                  this.cancel();
               } catch (Throwable e) {
                  ContainerManager.LOG.error("Error checking containers", e);
               }

            }
         };
         if (this.task.compareAndSet((Object)null, timerTask)) {
            this.timer.scheduleAtFixedRate(timerTask, (long)this.checkIntervalMs, (long)this.checkIntervalMs);
         }
      }

   }

   public void stop() {
      TimerTask timerTask = (TimerTask)this.task.getAndSet((Object)null);
      if (timerTask != null) {
         timerTask.cancel();
      }

      this.timer.cancel();
   }

   public void checkContainers() throws InterruptedException {
      long minIntervalMs = this.getMinIntervalMs();

      for(String containerPath : this.getCandidates()) {
         long startMs = Time.currentElapsedTime();
         DeleteContainerRequest record = new DeleteContainerRequest(containerPath);
         Request request = new Request((ServerCnxn)null, 0L, 0, 20, RequestRecord.fromRecord(record), (List)null);

         try {
            LOG.info("Attempting to delete candidate container: {}", containerPath);
            this.postDeleteRequest(request);
         } catch (Exception e) {
            LOG.error("Could not delete container: {}", containerPath, e);
         }

         long elapsedMs = Time.currentElapsedTime() - startMs;
         long waitMs = minIntervalMs - elapsedMs;
         if (waitMs > 0L) {
            Thread.sleep(waitMs);
         }
      }

   }

   protected void postDeleteRequest(Request request) throws RequestProcessor.RequestProcessorException {
      this.requestProcessor.processRequest(request);
   }

   protected long getMinIntervalMs() {
      return TimeUnit.MINUTES.toMillis(1L) / (long)this.maxPerMinute;
   }

   protected Collection getCandidates() {
      Set<String> candidates = new HashSet();

      for(String containerPath : this.zkDb.getDataTree().getContainers()) {
         DataNode node = this.zkDb.getDataTree().getNode(containerPath);
         if (node != null && node.getChildren().isEmpty()) {
            if (node.stat.getCversion() > 0) {
               candidates.add(containerPath);
            } else if (this.maxNeverUsedIntervalMs != 0L && this.getElapsed(node) > this.maxNeverUsedIntervalMs) {
               candidates.add(containerPath);
            }
         }
      }

      for(String ttlPath : this.zkDb.getDataTree().getTtls()) {
         DataNode node = this.zkDb.getDataTree().getNode(ttlPath);
         if (node != null) {
            Set<String> children = node.getChildren();
            if (children.isEmpty() && EphemeralType.get(node.stat.getEphemeralOwner()) == EphemeralType.TTL) {
               long ttl = EphemeralType.TTL.getValue(node.stat.getEphemeralOwner());
               if (ttl != 0L && this.getElapsed(node) > ttl) {
                  candidates.add(ttlPath);
               }
            }
         }
      }

      return candidates;
   }

   protected long getElapsed(DataNode node) {
      return Time.currentWallTime() - node.stat.getMtime();
   }
}
