package org.apache.hive.service.cli.operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.QueryState;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.hadoop.hive.ql.session.OperationLog;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.OperationStatus;
import org.apache.hive.service.cli.OperationType;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;

public abstract class Operation {
   protected final HiveSession parentSession;
   private OperationState state;
   private final OperationHandle opHandle;
   private HiveConf configuration;
   public static final SparkLogger LOG = SparkLoggerFactory.getLogger(Operation.class);
   public static final FetchOrientation DEFAULT_FETCH_ORIENTATION;
   public static final long DEFAULT_FETCH_MAX_ROWS = 100L;
   protected boolean hasResultSet;
   protected volatile HiveSQLException operationException;
   protected final boolean runAsync;
   protected volatile Future backgroundHandle;
   protected OperationLog operationLog;
   protected boolean isOperationLogEnabled;
   protected Map confOverlay;
   private long operationTimeout;
   private long lastAccessTime;
   protected final QueryState queryState;
   protected static final EnumSet DEFAULT_FETCH_ORIENTATION_SET;

   protected Operation(HiveSession parentSession, OperationType opType) {
      this(parentSession, (Map)null, opType);
   }

   protected Operation(HiveSession parentSession, Map confOverlay, OperationType opType) {
      this(parentSession, confOverlay, opType, false);
   }

   protected Operation(HiveSession parentSession, Map confOverlay, OperationType opType, boolean runInBackground) {
      this.state = OperationState.INITIALIZED;
      this.confOverlay = new HashMap();
      this.parentSession = parentSession;
      this.confOverlay = confOverlay;
      this.runAsync = runInBackground;
      this.opHandle = new OperationHandle(opType, parentSession.getProtocolVersion());
      this.lastAccessTime = System.currentTimeMillis();
      this.operationTimeout = HiveConf.getTimeVar(parentSession.getHiveConf(), ConfVars.HIVE_SERVER2_IDLE_OPERATION_TIMEOUT, TimeUnit.MILLISECONDS);
      this.queryState = new QueryState(parentSession.getHiveConf(), confOverlay, runInBackground);
   }

   public Future getBackgroundHandle() {
      return this.backgroundHandle;
   }

   protected void setBackgroundHandle(Future backgroundHandle) {
      this.backgroundHandle = backgroundHandle;
   }

   public boolean shouldRunAsync() {
      return this.runAsync;
   }

   public void setConfiguration(HiveConf configuration) {
      this.configuration = new HiveConf(configuration);
   }

   public HiveConf getConfiguration() {
      return new HiveConf(this.configuration);
   }

   public HiveSession getParentSession() {
      return this.parentSession;
   }

   public OperationHandle getHandle() {
      return this.opHandle;
   }

   public TProtocolVersion getProtocolVersion() {
      return this.opHandle.getProtocolVersion();
   }

   public OperationType getType() {
      return this.opHandle.getOperationType();
   }

   public OperationStatus getStatus() {
      return new OperationStatus(this.state, this.operationException);
   }

   public boolean hasResultSet() {
      return this.hasResultSet;
   }

   protected void setHasResultSet(boolean hasResultSet) {
      this.hasResultSet = hasResultSet;
      this.opHandle.setHasResultSet(hasResultSet);
   }

   public OperationLog getOperationLog() {
      return this.operationLog;
   }

   protected final OperationState setState(OperationState newState) throws HiveSQLException {
      this.state.validateTransition(newState);
      this.state = newState;
      this.lastAccessTime = System.currentTimeMillis();
      return this.state;
   }

   public boolean isTimedOut(long current) {
      if (this.operationTimeout == 0L) {
         return false;
      } else if (this.operationTimeout <= 0L) {
         return this.lastAccessTime + -this.operationTimeout <= current;
      } else {
         return this.state.isTerminal() && this.lastAccessTime + this.operationTimeout <= current;
      }
   }

   public long getLastAccessTime() {
      return this.lastAccessTime;
   }

   public long getOperationTimeout() {
      return this.operationTimeout;
   }

   public void setOperationTimeout(long operationTimeout) {
      this.operationTimeout = operationTimeout;
   }

   protected void setOperationException(HiveSQLException operationException) {
      this.operationException = operationException;
   }

   protected final void assertState(OperationState state) throws HiveSQLException {
      if (this.state != state) {
         String var10002 = String.valueOf(state);
         throw new HiveSQLException("Expected state " + var10002 + ", but found " + String.valueOf(this.state));
      } else {
         this.lastAccessTime = System.currentTimeMillis();
      }
   }

   public boolean isRunning() {
      return OperationState.RUNNING.equals(this.state);
   }

   public boolean isFinished() {
      return OperationState.FINISHED.equals(this.state);
   }

   public boolean isCanceled() {
      return OperationState.CANCELED.equals(this.state);
   }

   public boolean isFailed() {
      return OperationState.ERROR.equals(this.state);
   }

   protected void createOperationLog() {
      if (this.parentSession.isOperationLogEnabled()) {
         File operationLogFile = new File(this.parentSession.getOperationLogSessionDir(), this.opHandle.getHandleIdentifier().toString());
         this.isOperationLogEnabled = true;

         try {
            if (operationLogFile.exists()) {
               LOG.warn("The operation log file should not exist, but it is already there: {}", new MDC[]{MDC.of(.MODULE$, operationLogFile.getAbsolutePath())});
               operationLogFile.delete();
            }

            if (!operationLogFile.createNewFile() && (!operationLogFile.canRead() || !operationLogFile.canWrite())) {
               LOG.warn("The already existed operation log file cannot be recreated, and it cannot be read or written: {}", new MDC[]{MDC.of(.MODULE$, operationLogFile.getAbsolutePath())});
               this.isOperationLogEnabled = false;
               return;
            }
         } catch (Exception e) {
            LOG.warn("Unable to create operation log file: {}", e, new MDC[]{MDC.of(.MODULE$, operationLogFile.getAbsolutePath())});
            this.isOperationLogEnabled = false;
            return;
         }

         try {
            this.operationLog = new OperationLog(this.opHandle.toString(), operationLogFile, this.parentSession.getHiveConf());
         } catch (FileNotFoundException e) {
            LOG.warn("Unable to instantiate OperationLog object for operation: {}", e, new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.OPERATION_HANDLE..MODULE$, this.opHandle)});
            this.isOperationLogEnabled = false;
            return;
         }

         OperationLog.setCurrentOperationLog(this.operationLog);
      }

   }

   protected void unregisterOperationLog() {
      if (this.isOperationLogEnabled) {
         OperationLog.removeCurrentOperationLog();
      }

   }

   protected void beforeRun() {
      this.createOperationLog();
   }

   protected void afterRun() {
      this.unregisterOperationLog();
   }

   protected abstract void runInternal() throws HiveSQLException;

   public void run() throws HiveSQLException {
      this.beforeRun();

      try {
         this.runInternal();
      } finally {
         this.afterRun();
      }

   }

   protected void cleanupOperationLog() {
      if (this.isOperationLogEnabled) {
         if (this.operationLog == null) {
            LOG.error("Operation [ {} ] logging is enabled, but its OperationLog object cannot be found.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.OPERATION_HANDLE_ID..MODULE$, this.opHandle.getHandleIdentifier())});
         } else {
            this.operationLog.close();
         }
      }

   }

   public void cancel() throws HiveSQLException {
      this.setState(OperationState.CANCELED);
      throw new UnsupportedOperationException("SQLOperation.cancel()");
   }

   public void close() throws HiveSQLException {
      this.setState(OperationState.CLOSED);
      this.cleanupOperationLog();
   }

   public abstract TTableSchema getResultSetSchema() throws HiveSQLException;

   public abstract TRowSet getNextRowSet(FetchOrientation var1, long var2) throws HiveSQLException;

   protected void validateDefaultFetchOrientation(FetchOrientation orientation) throws HiveSQLException {
      this.validateFetchOrientation(orientation, DEFAULT_FETCH_ORIENTATION_SET);
   }

   protected void validateFetchOrientation(FetchOrientation orientation, EnumSet supportedOrientations) throws HiveSQLException {
      if (!supportedOrientations.contains(orientation)) {
         throw new HiveSQLException("The fetch type " + orientation.toString() + " is not supported for this resultset", "HY106");
      }
   }

   protected HiveSQLException toSQLException(String prefix, CommandProcessorResponse response) {
      HiveSQLException ex = new HiveSQLException(prefix + ": " + response.getErrorMessage(), response.getSQLState(), response.getResponseCode());
      if (response.getException() != null) {
         ex.initCause(response.getException());
      }

      return ex;
   }

   protected Map getConfOverlay() {
      return this.confOverlay;
   }

   static {
      DEFAULT_FETCH_ORIENTATION = FetchOrientation.FETCH_NEXT;
      DEFAULT_FETCH_ORIENTATION_SET = EnumSet.of(FetchOrientation.FETCH_NEXT, FetchOrientation.FETCH_FIRST, FetchOrientation.FETCH_PRIOR);
   }
}
