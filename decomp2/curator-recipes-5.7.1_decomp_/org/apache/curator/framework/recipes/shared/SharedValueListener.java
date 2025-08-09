package org.apache.curator.framework.recipes.shared;

import org.apache.curator.framework.state.ConnectionStateListener;

public interface SharedValueListener extends ConnectionStateListener {
   void valueHasChanged(SharedValueReader var1, byte[] var2) throws Exception;
}
