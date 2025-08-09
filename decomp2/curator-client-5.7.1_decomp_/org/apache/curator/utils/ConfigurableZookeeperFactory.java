package org.apache.curator.utils;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.admin.ZooKeeperAdmin;
import org.apache.zookeeper.client.ZKClientConfig;

public class ConfigurableZookeeperFactory extends DefaultZookeeperFactory {
   public ZooKeeper newZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly, ZKClientConfig zkClientConfig) throws Exception {
      return new ZooKeeperAdmin(connectString, sessionTimeout, watcher, canBeReadOnly, zkClientConfig);
   }
}
