package org.apache.curator.framework.state;

public class StandardConnectionStateErrorPolicy implements ConnectionStateErrorPolicy {
   public boolean isErrorState(ConnectionState state) {
      return state == ConnectionState.SUSPENDED || state == ConnectionState.LOST;
   }
}
