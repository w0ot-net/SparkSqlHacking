package org.glassfish.hk2.api;

public enum ServiceLocatorState {
   RUNNING,
   SHUTDOWN;

   // $FF: synthetic method
   private static ServiceLocatorState[] $values() {
      return new ServiceLocatorState[]{RUNNING, SHUTDOWN};
   }
}
