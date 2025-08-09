package org.apache.hive.service.server;

import java.util.Map;
import org.apache.hadoop.hive.metastore.RawStore;
import org.apache.hadoop.hive.metastore.HiveMetaStore.HMSHandler;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class ThreadWithGarbageCleanup extends Thread {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(ThreadWithGarbageCleanup.class);
   Map threadRawStoreMap = ThreadFactoryWithGarbageCleanup.getThreadRawStoreMap();

   public ThreadWithGarbageCleanup(Runnable runnable) {
      super(runnable);
   }

   public void finalize() throws Throwable {
      this.cleanRawStore();
      super.finalize();
   }

   private void cleanRawStore() {
      Long threadId = this.getId();
      RawStore threadLocalRawStore = (RawStore)this.threadRawStoreMap.get(threadId);
      if (threadLocalRawStore != null) {
         SparkLogger var10000 = LOG;
         String var10001 = String.valueOf(threadLocalRawStore);
         var10000.debug("RawStore: " + var10001 + ", for the thread: " + this.getName() + " will be closed now.");
         threadLocalRawStore.shutdown();
         this.threadRawStoreMap.remove(threadId);
      }

   }

   public void cacheThreadLocalRawStore() {
      Long threadId = this.getId();
      RawStore threadLocalRawStore = HMSHandler.getRawStore();
      if (threadLocalRawStore != null && !this.threadRawStoreMap.containsKey(threadId)) {
         SparkLogger var10000 = LOG;
         String var10001 = String.valueOf(threadLocalRawStore);
         var10000.debug("Adding RawStore: " + var10001 + ", for the thread: " + this.getName() + " to threadRawStoreMap for future cleanup.");
         this.threadRawStoreMap.put(threadId, threadLocalRawStore);
      }

   }
}
