package org.apache.curator.utils;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.admin.ZooKeeperAdmin;

public class DefaultZookeeperFactory implements ZookeeperFactory {
   public ZooKeeper newZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly) throws Exception {
      return new ZooKeeperAdmin(connectString, sessionTimeout, watcher, canBeReadOnly);
   }
}
