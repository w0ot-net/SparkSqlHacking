package org.apache.spark.launcher;

import java.util.Optional;

public interface SparkAppHandle {
   void addListener(Listener var1);

   State getState();

   String getAppId();

   void stop();

   void kill();

   void disconnect();

   Optional getError();

   public static enum State {
      UNKNOWN(false),
      CONNECTED(false),
      SUBMITTED(false),
      RUNNING(false),
      FINISHED(true),
      FAILED(true),
      KILLED(true),
      LOST(true);

      private final boolean isFinal;

      private State(boolean isFinal) {
         this.isFinal = isFinal;
      }

      public boolean isFinal() {
         return this.isFinal;
      }

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{UNKNOWN, CONNECTED, SUBMITTED, RUNNING, FINISHED, FAILED, KILLED, LOST};
      }
   }

   public interface Listener {
      void stateChanged(SparkAppHandle var1);

      void infoChanged(SparkAppHandle var1);
   }
}
