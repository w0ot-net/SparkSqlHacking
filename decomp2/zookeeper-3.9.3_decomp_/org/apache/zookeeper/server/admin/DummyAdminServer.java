package org.apache.zookeeper.server.admin;

import org.apache.zookeeper.server.ZooKeeperServer;

public class DummyAdminServer implements AdminServer {
   public void start() throws AdminServer.AdminServerException {
   }

   public void shutdown() throws AdminServer.AdminServerException {
   }

   public void setZooKeeperServer(ZooKeeperServer zkServer) {
   }
}
