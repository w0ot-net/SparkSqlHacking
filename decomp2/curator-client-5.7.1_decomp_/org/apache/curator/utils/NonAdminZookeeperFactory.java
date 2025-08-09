package org.apache.curator.utils;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class NonAdminZookeeperFactory implements ZookeeperFactory {
   public ZooKeeper newZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly) throws Exception {
      return new ZooKeeper(connectString, sessionTimeout, watcher, canBeReadOnly);
   }
}
