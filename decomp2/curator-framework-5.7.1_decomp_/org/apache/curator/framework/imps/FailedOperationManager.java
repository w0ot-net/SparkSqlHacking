package org.apache.curator.framework.imps;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class FailedOperationManager {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   protected final CuratorFramework client;
   @VisibleForTesting
   volatile FailedOperationManagerListener debugListener = null;

   FailedOperationManager(CuratorFramework client) {
      this.client = client;
   }

   void addFailedOperation(Object details) {
      if (this.debugListener != null) {
         this.debugListener.pathAddedForGuaranteedOperation(details);
      }

      if (this.client.getState() == CuratorFrameworkState.STARTED) {
         this.log.debug("Details being added to guaranteed operation set: " + details);

         try {
            this.executeGuaranteedOperationInBackground(details);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.addFailedOperation(details);
         }
      }

   }

   protected abstract void executeGuaranteedOperationInBackground(Object var1) throws Exception;

   interface FailedOperationManagerListener {
      void pathAddedForGuaranteedOperation(Object var1);
   }
}
