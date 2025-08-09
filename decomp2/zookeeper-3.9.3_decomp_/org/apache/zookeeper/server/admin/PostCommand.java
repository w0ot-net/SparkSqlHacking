package org.apache.zookeeper.server.admin;

import java.util.List;
import java.util.Map;
import org.apache.zookeeper.server.ZooKeeperServer;

public abstract class PostCommand extends CommandBase {
   protected PostCommand(List names, boolean serverRequired, AuthRequest authRequest) {
      super(names, serverRequired, authRequest);
   }

   public CommandResponse runGet(ZooKeeperServer zkServer, Map kwargs) {
      return null;
   }
}
