package org.apache.curator.framework.imps;

import java.util.UUID;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProtectedMode {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private volatile String protectedId = null;
   private volatile long sessionId = 0L;

   void setProtectedMode() {
      this.resetProtectedId();
   }

   void resetProtectedId() {
      this.protectedId = UUID.randomUUID().toString();
   }

   boolean doProtected() {
      return this.protectedId != null;
   }

   String protectedId() {
      return this.protectedId;
   }

   void checkSetSessionId(CuratorFrameworkImpl client, CreateMode createMode) throws Exception {
      if (this.doProtected() && this.sessionId == 0L && createMode.isEphemeral()) {
         this.sessionId = client.getZooKeeper().getSessionId();
      }

   }

   String validateFoundNode(CuratorFrameworkImpl client, CreateMode createMode, String foundNode) throws Exception {
      if (this.doProtected() && createMode.isEphemeral()) {
         long clientSessionId = client.getZooKeeper().getSessionId();
         if (this.sessionId != clientSessionId) {
            this.log.info("Session has changed during protected mode with ephemeral. old: {} new: {}", this.sessionId, clientSessionId);
            if (foundNode != null) {
               this.log.info("Deleted old session's found node: {}", foundNode);
               client.getFailedDeleteManager().executeGuaranteedOperationInBackground(foundNode);
               foundNode = null;
            }

            this.sessionId = clientSessionId;
         }
      }

      return foundNode;
   }
}
