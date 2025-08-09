package org.apache.curator.framework.recipes.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;

public abstract class LeaderSelectorListenerAdapter implements LeaderSelectorListener {
   public void stateChanged(CuratorFramework client, ConnectionState newState) {
      if (client.getConnectionStateErrorPolicy().isErrorState(newState)) {
         throw new CancelLeadershipException();
      }
   }
}
