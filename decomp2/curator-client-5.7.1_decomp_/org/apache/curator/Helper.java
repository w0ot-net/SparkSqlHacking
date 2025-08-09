package org.apache.curator;

import org.apache.zookeeper.ZooKeeper;

class Helper {
   private final Data data;

   Helper(Data data) {
      this.data = data;
   }

   ZooKeeper getZooKeeper() throws Exception {
      return this.data.zooKeeperHandle;
   }

   String getConnectionString() {
      return this.data.connectionString;
   }

   int getNegotiatedSessionTimeoutMs() {
      return this.data.zooKeeperHandle != null ? this.data.zooKeeperHandle.getSessionTimeout() : 0;
   }

   void resetConnectionString(String connectionString) {
      this.data.connectionString = connectionString;
   }

   static class Data {
      volatile ZooKeeper zooKeeperHandle = null;
      volatile String connectionString = null;
   }
}
