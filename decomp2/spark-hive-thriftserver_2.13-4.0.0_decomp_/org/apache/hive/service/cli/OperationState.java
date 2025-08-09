package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TOperationState;

public enum OperationState {
   INITIALIZED(TOperationState.INITIALIZED_STATE, false),
   RUNNING(TOperationState.RUNNING_STATE, false),
   FINISHED(TOperationState.FINISHED_STATE, true),
   CANCELED(TOperationState.CANCELED_STATE, true),
   CLOSED(TOperationState.CLOSED_STATE, true),
   ERROR(TOperationState.ERROR_STATE, true),
   UNKNOWN(TOperationState.UKNOWN_STATE, false),
   PENDING(TOperationState.PENDING_STATE, false),
   TIMEDOUT(TOperationState.TIMEDOUT_STATE, true);

   private final TOperationState tOperationState;
   private final boolean terminal;

   private OperationState(TOperationState tOperationState, boolean terminal) {
      this.tOperationState = tOperationState;
      this.terminal = terminal;
   }

   public static OperationState getOperationState(TOperationState tOperationState) {
      return values()[tOperationState.getValue()];
   }

   public static void validateTransition(OperationState oldState, OperationState newState) throws HiveSQLException {
      label27:
      switch (oldState) {
         case PENDING:
            switch (newState) {
               case RUNNING:
               case CANCELED:
               case CLOSED:
               case TIMEDOUT:
               case FINISHED:
               case ERROR:
                  return;
               default:
                  break label27;
            }
         case RUNNING:
            switch (newState) {
               case CANCELED:
               case CLOSED:
               case TIMEDOUT:
               case FINISHED:
               case ERROR:
                  return;
               default:
                  break label27;
            }
         case CANCELED:
         case TIMEDOUT:
         case FINISHED:
         case ERROR:
            if (CLOSED.equals(newState)) {
               return;
            }
         case CLOSED:
         default:
            break;
         case INITIALIZED:
            switch (newState) {
               case PENDING:
               case RUNNING:
               case CANCELED:
               case CLOSED:
               case TIMEDOUT:
                  return;
            }
      }

      String var10002 = String.valueOf(oldState);
      throw new HiveSQLException("Illegal Operation state transition from " + var10002 + " to " + String.valueOf(newState));
   }

   public void validateTransition(OperationState newState) throws HiveSQLException {
      validateTransition(this, newState);
   }

   public TOperationState toTOperationState() {
      return this.tOperationState;
   }

   public boolean isTerminal() {
      return this.terminal;
   }

   // $FF: synthetic method
   private static OperationState[] $values() {
      return new OperationState[]{INITIALIZED, RUNNING, FINISHED, CANCELED, CLOSED, ERROR, UNKNOWN, PENDING, TIMEDOUT};
   }
}
