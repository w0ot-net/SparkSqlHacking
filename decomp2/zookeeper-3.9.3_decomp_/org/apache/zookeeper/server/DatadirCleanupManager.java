package org.apache.zookeeper.server;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatadirCleanupManager {
   private static final Logger LOG = LoggerFactory.getLogger(DatadirCleanupManager.class);
   private PurgeTaskStatus purgeTaskStatus;
   private final File snapDir;
   private final File dataLogDir;
   private final int snapRetainCount;
   private final int purgeInterval;
   private Timer timer;

   public DatadirCleanupManager(File snapDir, File dataLogDir, int snapRetainCount, int purgeInterval) {
      this.purgeTaskStatus = DatadirCleanupManager.PurgeTaskStatus.NOT_STARTED;
      this.snapDir = snapDir;
      this.dataLogDir = dataLogDir;
      this.snapRetainCount = snapRetainCount;
      this.purgeInterval = purgeInterval;
      LOG.info("autopurge.snapRetainCount set to {}", snapRetainCount);
      LOG.info("autopurge.purgeInterval set to {}", purgeInterval);
   }

   public void start() {
      if (DatadirCleanupManager.PurgeTaskStatus.STARTED == this.purgeTaskStatus) {
         LOG.warn("Purge task is already running.");
      } else if (this.purgeInterval <= 0) {
         LOG.info("Purge task is not scheduled.");
      } else {
         this.timer = new Timer("PurgeTask", true);
         TimerTask task = new PurgeTask(this.dataLogDir, this.snapDir, this.snapRetainCount);
         this.timer.scheduleAtFixedRate(task, 0L, TimeUnit.HOURS.toMillis((long)this.purgeInterval));
         this.purgeTaskStatus = DatadirCleanupManager.PurgeTaskStatus.STARTED;
      }
   }

   public void shutdown() {
      if (DatadirCleanupManager.PurgeTaskStatus.STARTED == this.purgeTaskStatus) {
         LOG.info("Shutting down purge task.");
         this.timer.cancel();
         this.purgeTaskStatus = DatadirCleanupManager.PurgeTaskStatus.COMPLETED;
      } else {
         LOG.warn("Purge task not started. Ignoring shutdown!");
      }

   }

   public PurgeTaskStatus getPurgeTaskStatus() {
      return this.purgeTaskStatus;
   }

   public File getSnapDir() {
      return this.snapDir;
   }

   public File getDataLogDir() {
      return this.dataLogDir;
   }

   public int getPurgeInterval() {
      return this.purgeInterval;
   }

   public int getSnapRetainCount() {
      return this.snapRetainCount;
   }

   public static enum PurgeTaskStatus {
      NOT_STARTED,
      STARTED,
      COMPLETED;
   }

   static class PurgeTask extends TimerTask {
      private File logsDir;
      private File snapsDir;
      private int snapRetainCount;

      public PurgeTask(File dataDir, File snapDir, int count) {
         this.logsDir = dataDir;
         this.snapsDir = snapDir;
         this.snapRetainCount = count;
      }

      public void run() {
         DatadirCleanupManager.LOG.info("Purge task started.");

         try {
            PurgeTxnLog.purge(this.logsDir, this.snapsDir, this.snapRetainCount);
         } catch (Exception e) {
            DatadirCleanupManager.LOG.error("Error occurred while purging.", e);
         }

         DatadirCleanupManager.LOG.info("Purge task completed.");
      }
   }
}
