package org.apache.hive.service.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryWithGarbageCleanup implements ThreadFactory {
   private static Map threadRawStoreMap = new ConcurrentHashMap();
   private final String namePrefix;

   public ThreadFactoryWithGarbageCleanup(String threadPoolName) {
      this.namePrefix = threadPoolName;
   }

   public Thread newThread(Runnable runnable) {
      Thread newThread = new ThreadWithGarbageCleanup(runnable);
      String var10001 = this.namePrefix;
      newThread.setName(var10001 + ": Thread-" + newThread.getId());
      return newThread;
   }

   public static Map getThreadRawStoreMap() {
      return threadRawStoreMap;
   }
}
