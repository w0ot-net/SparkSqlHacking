package org.apache.hive.service.cli.operation;

import java.util.Map;
import org.apache.hadoop.hive.ql.session.OperationLog;
import org.apache.hive.service.cli.OperationType;
import org.apache.hive.service.cli.session.HiveSession;

public abstract class ExecuteStatementOperation extends Operation {
   protected String statement = null;

   public ExecuteStatementOperation(HiveSession parentSession, String statement, Map confOverlay, boolean runInBackground) {
      super(parentSession, confOverlay, OperationType.EXECUTE_STATEMENT, runInBackground);
      this.statement = statement;
   }

   public String getStatement() {
      return this.statement;
   }

   protected void registerCurrentOperationLog() {
      if (this.isOperationLogEnabled) {
         if (this.operationLog == null) {
            LOG.warn("Failed to get current OperationLog object of Operation: " + String.valueOf(this.getHandle().getHandleIdentifier()));
            this.isOperationLogEnabled = false;
            return;
         }

         OperationLog.setCurrentOperationLog(this.operationLog);
      }

   }
}
