package org.apache.curator.framework.recipes.shared;

import org.apache.curator.framework.state.ConnectionStateListener;

public interface SharedCountListener extends ConnectionStateListener {
   void countHasChanged(SharedCountReader var1, int var2) throws Exception;
}
