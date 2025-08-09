package org.apache.zookeeper.server.admin;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.server.ZooKeeperServer;

public interface Command {
   Set getNames();

   String getPrimaryName();

   boolean isServerRequired();

   AuthRequest getAuthRequest();

   CommandResponse runGet(ZooKeeperServer var1, Map var2);

   CommandResponse runPost(ZooKeeperServer var1, InputStream var2);
}
