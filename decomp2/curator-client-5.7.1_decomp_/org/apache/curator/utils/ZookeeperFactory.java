package org.apache.curator.utils;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.admin.ZooKeeperAdmin;
import org.apache.zookeeper.client.ZKClientConfig;

public interface ZookeeperFactory {
   ZooKeeper newZooKeeper(String var1, int var2, Watcher var3, boolean var4) throws Exception;

   default ZooKeeper newZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, ZKClientConfig zkClientConfig) throws Exception {
      return (ZooKeeper)(zkClientConfig == null ? this.newZooKeeper(connectString, sessionTimeout, watcher, canBeReadOnly) : new ZooKeeperAdmin(connectString, sessionTimeout, watcher, canBeReadOnly, zkClientConfig));
   }
}
