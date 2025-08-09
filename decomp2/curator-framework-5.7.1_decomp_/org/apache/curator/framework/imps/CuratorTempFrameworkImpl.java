package org.apache.curator.framework.imps;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorTempFramework;
import org.apache.curator.framework.api.TempGetDataBuilder;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ThreadUtils;

public class CuratorTempFrameworkImpl implements CuratorTempFramework {
   private final CuratorFrameworkFactory.Builder factory;
   private final long inactiveThresholdMs;
   private CuratorFrameworkImpl client;
   private ScheduledExecutorService cleanup;
   private long lastAccess;

   public CuratorTempFrameworkImpl(CuratorFrameworkFactory.Builder factory, long inactiveThresholdMs) {
      this.factory = factory;
      this.inactiveThresholdMs = inactiveThresholdMs;
   }

   public void close() {
      this.closeClient();
   }

   public CuratorTransaction inTransaction() throws Exception {
      this.openConnectionIfNeeded();
      return new CuratorTransactionImpl(this.client);
   }

   public TempGetDataBuilder getData() throws Exception {
      this.openConnectionIfNeeded();
      return new TempGetDataBuilderImpl(this.client);
   }

   @VisibleForTesting
   synchronized CuratorFrameworkImpl getClient() {
      return this.client;
   }

   @VisibleForTesting
   synchronized ScheduledExecutorService getCleanup() {
      return this.cleanup;
   }

   @VisibleForTesting
   synchronized void updateLastAccess() {
      this.lastAccess = System.currentTimeMillis();
   }

   private synchronized void openConnectionIfNeeded() throws Exception {
      if (this.client == null) {
         this.client = (CuratorFrameworkImpl)this.factory.build();
         this.client.start();
      }

      if (this.cleanup == null) {
         ThreadFactory threadFactory = this.factory.getThreadFactory();
         if (threadFactory == null) {
            threadFactory = ThreadUtils.newGenericThreadFactory("CuratorTempFrameworkImpl");
         }

         this.cleanup = Executors.newScheduledThreadPool(1, threadFactory);
         Runnable command = new Runnable() {
            public void run() {
               CuratorTempFrameworkImpl.this.checkInactive();
            }
         };
         this.cleanup.scheduleAtFixedRate(command, this.inactiveThresholdMs, this.inactiveThresholdMs, TimeUnit.MILLISECONDS);
      }

      this.updateLastAccess();
   }

   private synchronized void checkInactive() {
      long elapsed = System.currentTimeMillis() - this.lastAccess;
      if (elapsed >= this.inactiveThresholdMs) {
         this.closeClient();
      }

   }

   private synchronized void closeClient() {
      if (this.cleanup != null) {
         this.cleanup.shutdownNow();
         this.cleanup = null;
      }

      if (this.client != null) {
         CloseableUtils.closeQuietly(this.client);
         this.client = null;
      }

   }
}
