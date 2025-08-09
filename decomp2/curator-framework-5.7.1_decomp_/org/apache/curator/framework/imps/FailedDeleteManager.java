package org.apache.curator.framework.imps;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.ErrorListenerPathable;

class FailedDeleteManager extends FailedOperationManager {
   FailedDeleteManager(CuratorFramework client) {
      super(client);
   }

   protected void executeGuaranteedOperationInBackground(String path) throws Exception {
      ((ErrorListenerPathable)((ChildrenDeletable)this.client.delete().guaranteed()).inBackground()).forPath(path);
   }
}
