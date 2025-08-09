package org.apache.zookeeper.server.admin;

import java.io.InputStream;
import java.util.List;
import org.apache.zookeeper.server.ZooKeeperServer;

public abstract class GetCommand extends CommandBase {
   protected GetCommand(List names) {
      super(names);
   }

   protected GetCommand(List names, boolean serverRequired) {
      super(names, serverRequired);
   }

   protected GetCommand(List names, boolean serverRequired, AuthRequest authRequest) {
      super(names, serverRequired, authRequest);
   }

   public CommandResponse runPost(ZooKeeperServer zkServer, InputStream inputStream) {
      return null;
   }
}
