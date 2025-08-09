package org.apache.curator.framework.state;

import org.apache.curator.framework.CuratorFramework;

public interface ConnectionStateListener {
   void stateChanged(CuratorFramework var1, ConnectionState var2);

   default boolean doNotProxy() {
      return false;
   }
}
