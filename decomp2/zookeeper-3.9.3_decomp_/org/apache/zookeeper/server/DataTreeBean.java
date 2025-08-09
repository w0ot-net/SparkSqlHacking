package org.apache.zookeeper.server;

import org.apache.zookeeper.jmx.ZKMBeanInfo;

public class DataTreeBean implements DataTreeMXBean, ZKMBeanInfo {
   DataTree dataTree;

   public DataTreeBean(DataTree dataTree) {
      this.dataTree = dataTree;
   }

   public int getNodeCount() {
      return this.dataTree.getNodeCount();
   }

   public long approximateDataSize() {
      return this.dataTree.cachedApproximateDataSize();
   }

   public int countEphemerals() {
      return this.dataTree.getEphemeralsCount();
   }

   public int getWatchCount() {
      return this.dataTree.getWatchCount();
   }

   public String getName() {
      return "InMemoryDataTree";
   }

   public boolean isHidden() {
      return false;
   }

   public String getLastZxid() {
      return "0x" + Long.toHexString(this.dataTree.lastProcessedZxid);
   }
}
