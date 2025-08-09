package org.apache.hive.service.cli;

public class OperationStatus {
   private final OperationState state;
   private final HiveSQLException operationException;

   public OperationStatus(OperationState state, HiveSQLException operationException) {
      this.state = state;
      this.operationException = operationException;
   }

   public OperationState getState() {
      return this.state;
   }

   public HiveSQLException getOperationException() {
      return this.operationException;
   }
}
