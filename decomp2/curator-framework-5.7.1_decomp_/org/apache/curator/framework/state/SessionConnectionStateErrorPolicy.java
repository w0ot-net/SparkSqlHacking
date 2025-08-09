package org.apache.curator.framework.state;

public class SessionConnectionStateErrorPolicy implements ConnectionStateErrorPolicy {
   public boolean isErrorState(ConnectionState state) {
      return state == ConnectionState.LOST;
   }
}
