package org.apache.curator;

import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

class HandleHolder {
   private final ZookeeperFactory zookeeperFactory;
   private final Watcher watcher;
   private final EnsembleProvider ensembleProvider;
   private final int sessionTimeout;
   private final boolean canBeReadOnly;
   private volatile Helper helper;

   HandleHolder(ZookeeperFactory zookeeperFactory, Watcher watcher, EnsembleProvider ensembleProvider, int sessionTimeout, boolean canBeReadOnly) {
      this.zookeeperFactory = zookeeperFactory;
      this.watcher = watcher;
      this.ensembleProvider = ensembleProvider;
      this.sessionTimeout = sessionTimeout;
      this.canBeReadOnly = canBeReadOnly;
   }

   ZooKeeper getZooKeeper() throws Exception {
      return this.helper != null ? this.helper.getZooKeeper() : null;
   }

   int getNegotiatedSessionTimeoutMs() {
      return this.helper != null ? this.helper.getNegotiatedSessionTimeoutMs() : 0;
   }

   String getConnectionString() {
      return this.helper != null ? this.helper.getConnectionString() : null;
   }

   String getNewConnectionString() {
      String helperConnectionString = this.helper != null ? this.helper.getConnectionString() : null;
      String ensembleProviderConnectionString = this.ensembleProvider.getConnectionString();
      return helperConnectionString != null && !ensembleProviderConnectionString.equals(helperConnectionString) ? ensembleProviderConnectionString : null;
   }

   void resetConnectionString(String connectionString) {
      if (this.helper != null) {
         this.helper.resetConnectionString(connectionString);
      }

   }

   void closeAndClear(int waitForShutdownTimeoutMs) throws Exception {
      this.internalClose(waitForShutdownTimeoutMs);
      this.helper = null;
   }

   void closeAndReset() throws Exception {
      this.internalClose(0);
      final Helper.Data data = new Helper.Data();
      this.helper = new Helper(data) {
         ZooKeeper getZooKeeper() throws Exception {
            synchronized(this) {
               if (data.zooKeeperHandle == null) {
                  this.resetConnectionString(HandleHolder.this.ensembleProvider.getConnectionString());
                  data.zooKeeperHandle = HandleHolder.this.zookeeperFactory.newZooKeeper(data.connectionString, HandleHolder.this.sessionTimeout, HandleHolder.this.watcher, HandleHolder.this.canBeReadOnly);
               }

               HandleHolder.this.helper = new Helper(data);
               return super.getZooKeeper();
            }
         }
      };
   }

   private void internalClose(int waitForShutdownTimeoutMs) throws Exception {
      try {
         ZooKeeper zooKeeper = this.helper != null ? this.helper.getZooKeeper() : null;
         if (zooKeeper != null) {
            Watcher dummyWatcher = new Watcher() {
               public void process(WatchedEvent event) {
               }
            };
            zooKeeper.register(dummyWatcher);
            if (waitForShutdownTimeoutMs == 0) {
               zooKeeper.close();
            } else {
               zooKeeper.close(waitForShutdownTimeoutMs);
            }
         }
      } catch (InterruptedException var4) {
         Thread.currentThread().interrupt();
      }

   }
}
