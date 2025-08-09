package org.apache.curator.framework.recipes.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionStateListener;

public interface LeaderSelectorListener extends ConnectionStateListener {
   void takeLeadership(CuratorFramework var1) throws Exception;
}
