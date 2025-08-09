package org.apache.curator.framework.imps;

class StandardInternalConnectionHandler implements InternalConnectionHandler {
   public void suspendConnection(CuratorFrameworkImpl client) {
      client.setToSuspended();
   }

   public void checkNewConnection(CuratorFrameworkImpl client) {
      client.checkInstanceIndex();
   }
}
