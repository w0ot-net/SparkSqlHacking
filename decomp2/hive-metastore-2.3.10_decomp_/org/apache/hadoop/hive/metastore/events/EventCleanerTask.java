package org.apache.hadoop.hive.metastore.events;

import java.util.TimerTask;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.RawStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventCleanerTask extends TimerTask {
   public static final Logger LOG = LoggerFactory.getLogger(EventCleanerTask.class);
   private final HiveMetaStore.HMSHandler handler;

   public EventCleanerTask(HiveMetaStore.HMSHandler handler) {
      this.handler = handler;
   }

   public void run() {
      try {
         RawStore ms = this.handler.getMS();
         long deleteCnt = ms.cleanupEvents();
         if (deleteCnt > 0L) {
            LOG.info("Number of events deleted from event Table: " + deleteCnt);
         }
      } catch (Exception e) {
         LOG.error("Exception while trying to delete events ", e);
      }

   }
}
