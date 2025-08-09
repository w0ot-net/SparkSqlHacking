package org.apache.logging.log4j.core;

public interface LifeCycle {
   State getState();

   void initialize();

   void start();

   void stop();

   boolean isStarted();

   boolean isStopped();

   public static enum State {
      INITIALIZING,
      INITIALIZED,
      STARTING,
      STARTED,
      STOPPING,
      STOPPED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{INITIALIZING, INITIALIZED, STARTING, STARTED, STOPPING, STOPPED};
      }
   }
}
