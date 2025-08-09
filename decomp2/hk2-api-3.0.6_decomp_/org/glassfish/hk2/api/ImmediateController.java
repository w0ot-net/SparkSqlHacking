package org.glassfish.hk2.api;

import java.util.concurrent.Executor;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface ImmediateController {
   Executor getExecutor();

   void setExecutor(Executor var1) throws IllegalStateException;

   long getThreadInactivityTimeout();

   void setThreadInactivityTimeout(long var1) throws IllegalArgumentException;

   ImmediateServiceState getImmediateState();

   void setImmediateState(ImmediateServiceState var1);

   public static enum ImmediateServiceState {
      SUSPENDED,
      RUNNING;

      // $FF: synthetic method
      private static ImmediateServiceState[] $values() {
         return new ImmediateServiceState[]{SUSPENDED, RUNNING};
      }
   }
}
