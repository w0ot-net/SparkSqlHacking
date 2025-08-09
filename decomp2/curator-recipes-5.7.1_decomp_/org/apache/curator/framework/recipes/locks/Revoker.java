package org.apache.curator.framework.recipes.locks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;

public class Revoker {
   public static void attemptRevoke(CuratorFramework client, String path) throws Exception {
      try {
         client.setData().forPath(path, LockInternals.REVOKE_MESSAGE);
      } catch (KeeperException.NoNodeException var3) {
      }

   }

   private Revoker() {
   }
}
