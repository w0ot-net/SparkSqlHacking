package org.apache.curator.framework.imps;

interface InternalConnectionHandler {
   void checkNewConnection(CuratorFrameworkImpl var1);

   void suspendConnection(CuratorFrameworkImpl var1);
}
