package org.apache.hive.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLTimeoutException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.hive.jdbc.logs.InPlaceUpdateStream;
import org.apache.hive.service.cli.RowSetFactory;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.rpc.thrift.TCancelOperationReq;
import org.apache.hive.service.rpc.thrift.TCancelOperationResp;
import org.apache.hive.service.rpc.thrift.TCloseOperationReq;
import org.apache.hive.service.rpc.thrift.TCloseOperationResp;
import org.apache.hive.service.rpc.thrift.TExecuteStatementReq;
import org.apache.hive.service.rpc.thrift.TExecuteStatementResp;
import org.apache.hive.service.rpc.thrift.TFetchOrientation;
import org.apache.hive.service.rpc.thrift.TFetchResultsReq;
import org.apache.hive.service.rpc.thrift.TFetchResultsResp;
import org.apache.hive.service.rpc.thrift.TGetOperationStatusReq;
import org.apache.hive.service.rpc.thrift.TGetOperationStatusResp;
import org.apache.hive.service.rpc.thrift.TOperationHandle;
import org.apache.hive.service.rpc.thrift.TSessionHandle;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveStatement implements Statement {
   public static final Logger LOG = LoggerFactory.getLogger(HiveStatement.class.getName());
   public static final int DEFAULT_FETCH_SIZE = 1000;
   private final HiveConnection connection;
   private TCLIService.Iface client;
   private TOperationHandle stmtHandle;
   private final TSessionHandle sessHandle;
   Map sessConf;
   private int fetchSize;
   private boolean isScrollableResultset;
   private boolean isOperationComplete;
   private ResultSet resultSet;
   private int maxRows;
   private SQLWarning warningChain;
   private boolean isClosed;
   private boolean isCancelled;
   private boolean isQueryClosed;
   private boolean isLogBeingGenerated;
   private boolean isExecuteStatementFailed;
   private int queryTimeout;
   private InPlaceUpdateStream inPlaceUpdateStream;

   public HiveStatement(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle) {
      this(connection, client, sessHandle, false, 1000);
   }

   public HiveStatement(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle, int fetchSize) {
      this(connection, client, sessHandle, false, fetchSize);
   }

   public HiveStatement(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle, boolean isScrollableResultset) {
      this(connection, client, sessHandle, isScrollableResultset, 1000);
   }

   public HiveStatement(HiveConnection connection, TCLIService.Iface client, TSessionHandle sessHandle, boolean isScrollableResultset, int fetchSize) {
      this.stmtHandle = null;
      this.sessConf = new HashMap();
      this.fetchSize = 1000;
      this.isScrollableResultset = false;
      this.isOperationComplete = false;
      this.resultSet = null;
      this.maxRows = 0;
      this.warningChain = null;
      this.isClosed = false;
      this.isCancelled = false;
      this.isQueryClosed = false;
      this.isLogBeingGenerated = true;
      this.isExecuteStatementFailed = false;
      this.queryTimeout = 0;
      this.inPlaceUpdateStream = InPlaceUpdateStream.NO_OP;
      this.connection = connection;
      this.client = client;
      this.sessHandle = sessHandle;
      this.isScrollableResultset = isScrollableResultset;
      this.fetchSize = fetchSize;
   }

   public void addBatch(String sql) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void cancel() throws SQLException {
      this.checkConnection("cancel");
      if (!this.isCancelled) {
         try {
            if (this.stmtHandle != null) {
               TCancelOperationReq cancelReq = new TCancelOperationReq(this.stmtHandle);
               TCancelOperationResp cancelResp = this.client.CancelOperation(cancelReq);
               Utils.verifySuccessWithInfo(cancelResp.getStatus());
            }
         } catch (SQLException e) {
            throw e;
         } catch (Exception e) {
            throw new SQLException(e.toString(), "08S01", e);
         }

         this.isCancelled = true;
      }
   }

   public void clearBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void clearWarnings() throws SQLException {
      this.warningChain = null;
   }

   void closeClientOperation() throws SQLException {
      try {
         if (this.stmtHandle != null) {
            TCloseOperationReq closeReq = new TCloseOperationReq(this.stmtHandle);
            TCloseOperationResp closeResp = this.client.CloseOperation(closeReq);
            Utils.verifySuccessWithInfo(closeResp.getStatus());
         }
      } catch (SQLException e) {
         throw e;
      } catch (Exception e) {
         throw new SQLException(e.toString(), "08S01", e);
      }

      this.isQueryClosed = true;
      this.isExecuteStatementFailed = false;
      this.stmtHandle = null;
   }

   public void close() throws SQLException {
      if (!this.isClosed) {
         this.closeClientOperation();
         this.client = null;
         if (this.resultSet != null) {
            this.resultSet.close();
            this.resultSet = null;
         }

         this.isClosed = true;
      }
   }

   public void closeOnCompletion() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql) throws SQLException {
      this.runAsyncOnServer(sql);
      TGetOperationStatusResp status = this.waitForOperationToComplete();
      if (!status.isHasResultSet() && !this.stmtHandle.isHasResultSet()) {
         return false;
      } else {
         this.resultSet = (new HiveQueryResultSet.Builder(this)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(this.stmtHandle).setMaxRows(this.maxRows).setFetchSize(this.fetchSize).setScrollable(this.isScrollableResultset).build();
         return true;
      }
   }

   public boolean executeAsync(String sql) throws SQLException {
      this.runAsyncOnServer(sql);
      TGetOperationStatusResp status = this.waitForResultSetStatus();
      if (!status.isHasResultSet()) {
         return false;
      } else {
         this.resultSet = (new HiveQueryResultSet.Builder(this)).setClient(this.client).setSessionHandle(this.sessHandle).setStmtHandle(this.stmtHandle).setMaxRows(this.maxRows).setFetchSize(this.fetchSize).setScrollable(this.isScrollableResultset).build();
         return true;
      }
   }

   private void runAsyncOnServer(String sql) throws SQLException {
      this.checkConnection("execute");
      this.closeClientOperation();
      this.initFlags();
      TExecuteStatementReq execReq = new TExecuteStatementReq(this.sessHandle, sql);
      execReq.setRunAsync(true);
      execReq.setConfOverlay(this.sessConf);
      execReq.setQueryTimeout((long)this.queryTimeout);

      try {
         TExecuteStatementResp execResp = this.client.ExecuteStatement(execReq);
         Utils.verifySuccessWithInfo(execResp.getStatus());
         this.stmtHandle = execResp.getOperationHandle();
         this.isExecuteStatementFailed = false;
      } catch (SQLException eS) {
         this.isExecuteStatementFailed = true;
         this.isLogBeingGenerated = false;
         throw eS;
      } catch (Exception ex) {
         this.isExecuteStatementFailed = true;
         this.isLogBeingGenerated = false;
         throw new SQLException(ex.toString(), "08S01", ex);
      }
   }

   private TGetOperationStatusResp waitForResultSetStatus() throws SQLException {
      TGetOperationStatusReq statusReq = new TGetOperationStatusReq(this.stmtHandle);
      TGetOperationStatusResp statusResp = null;

      while(statusResp == null || !statusResp.isSetHasResultSet()) {
         try {
            statusResp = this.client.GetOperationStatus(statusReq);
         } catch (TException e) {
            this.isLogBeingGenerated = false;
            throw new SQLException(e.toString(), "08S01", e);
         }
      }

      return statusResp;
   }

   TGetOperationStatusResp waitForOperationToComplete() throws SQLException {
      TGetOperationStatusReq statusReq = new TGetOperationStatusReq(this.stmtHandle);
      boolean shouldGetProgressUpdate = this.inPlaceUpdateStream != InPlaceUpdateStream.NO_OP;
      statusReq.setGetProgressUpdate(shouldGetProgressUpdate);
      if (!shouldGetProgressUpdate) {
         this.inPlaceUpdateStream.getEventNotifier().progressBarCompleted();
      }

      TGetOperationStatusResp statusResp = null;

      do {
         try {
            statusResp = this.client.GetOperationStatus(statusReq);
            this.inPlaceUpdateStream.update(statusResp.getProgressUpdateResponse());
            Utils.verifySuccessWithInfo(statusResp.getStatus());
            if (statusResp.isSetOperationState()) {
               switch (statusResp.getOperationState()) {
                  case CLOSED_STATE:
                  case FINISHED_STATE:
                     this.isOperationComplete = true;
                     this.isLogBeingGenerated = false;
                     break;
                  case CANCELED_STATE:
                     throw new SQLException("Query was cancelled", "01000");
                  case TIMEDOUT_STATE:
                     throw new SQLTimeoutException("Query timed out after " + this.queryTimeout + " seconds");
                  case ERROR_STATE:
                     throw new SQLException(statusResp.getErrorMessage(), statusResp.getSqlState(), statusResp.getErrorCode());
                  case UKNOWN_STATE:
                     throw new SQLException("Unknown query", "HY000");
                  case INITIALIZED_STATE:
                  case PENDING_STATE:
                  case RUNNING_STATE:
               }
            }
         } catch (SQLException e) {
            this.isLogBeingGenerated = false;
            throw e;
         } catch (Exception e) {
            this.isLogBeingGenerated = false;
            throw new SQLException(e.toString(), "08S01", e);
         }
      } while(!this.isOperationComplete);

      this.inPlaceUpdateStream.getEventNotifier().progressBarCompleted();
      return statusResp;
   }

   private void checkConnection(String action) throws SQLException {
      if (this.isClosed) {
         throw new SQLException("Can't " + action + " after statement has been closed");
      }
   }

   private void initFlags() {
      this.isCancelled = false;
      this.isQueryClosed = false;
      this.isLogBeingGenerated = true;
      this.isExecuteStatementFailed = false;
      this.isOperationComplete = false;
   }

   public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql, int[] columnIndexes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public boolean execute(String sql, String[] columnNames) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int[] executeBatch() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public ResultSet executeQuery(String sql) throws SQLException {
      if (!this.execute(sql)) {
         throw new SQLException("The query did not generate a result set!");
      } else {
         return this.resultSet;
      }
   }

   public int executeUpdate(String sql) throws SQLException {
      this.execute(sql);
      return this.getUpdateCount();
   }

   public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int executeUpdate(String sql, String[] columnNames) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public Connection getConnection() throws SQLException {
      this.checkConnection("getConnection");
      return this.connection;
   }

   public int getFetchDirection() throws SQLException {
      this.checkConnection("getFetchDirection");
      return 1000;
   }

   public int getFetchSize() throws SQLException {
      this.checkConnection("getFetchSize");
      return this.fetchSize;
   }

   public ResultSet getGeneratedKeys() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxFieldSize() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getMaxRows() throws SQLException {
      this.checkConnection("getMaxRows");
      return this.maxRows;
   }

   public boolean getMoreResults() throws SQLException {
      return false;
   }

   public boolean getMoreResults(int current) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getQueryTimeout() throws SQLException {
      this.checkConnection("getQueryTimeout");
      return 0;
   }

   public ResultSet getResultSet() throws SQLException {
      this.checkConnection("getResultSet");
      return this.resultSet;
   }

   public int getResultSetConcurrency() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getResultSetHoldability() throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public int getResultSetType() throws SQLException {
      this.checkConnection("getResultSetType");
      return 1003;
   }

   public int getUpdateCount() throws SQLException {
      this.checkConnection("getUpdateCount");
      long numModifiedRows = -1L;
      TGetOperationStatusResp resp = this.waitForOperationToComplete();
      if (resp != null) {
         try {
            numModifiedRows = (Long)resp.getClass().getDeclaredMethod("getNumModifiedRows").invoke(resp);
         } catch (Exception var5) {
         }
      }

      if (numModifiedRows != -1L && numModifiedRows <= 2147483647L) {
         return (int)numModifiedRows;
      } else {
         LOG.warn("Invalid number of updated rows: {}", numModifiedRows);
         return -1;
      }
   }

   public SQLWarning getWarnings() throws SQLException {
      this.checkConnection("getWarnings");
      return this.warningChain;
   }

   public boolean isClosed() throws SQLException {
      return this.isClosed;
   }

   public boolean isCloseOnCompletion() throws SQLException {
      return false;
   }

   public boolean isPoolable() throws SQLException {
      return false;
   }

   public void setCursorName(String name) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setEscapeProcessing(boolean enable) throws SQLException {
      if (enable) {
         throw new SQLFeatureNotSupportedException("Method not supported");
      }
   }

   public void setFetchDirection(int direction) throws SQLException {
      this.checkConnection("setFetchDirection");
      if (direction != 1000) {
         throw new SQLException("Not supported direction " + direction);
      }
   }

   public void setFetchSize(int rows) throws SQLException {
      this.checkConnection("setFetchSize");
      if (rows > 0) {
         this.fetchSize = rows;
      } else {
         if (rows != 0) {
            throw new SQLException("Fetch size must be greater or equal to 0");
         }

         this.fetchSize = 1000;
      }

   }

   public void setMaxFieldSize(int max) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setMaxRows(int max) throws SQLException {
      this.checkConnection("setMaxRows");
      if (max < 0) {
         throw new SQLException("max must be >= 0");
      } else {
         this.maxRows = max;
      }
   }

   public void setPoolable(boolean poolable) throws SQLException {
      throw new SQLFeatureNotSupportedException("Method not supported");
   }

   public void setQueryTimeout(int seconds) throws SQLException {
      this.queryTimeout = seconds;
   }

   public boolean isWrapperFor(Class iface) throws SQLException {
      return false;
   }

   public Object unwrap(Class iface) throws SQLException {
      throw new SQLException("Cannot unwrap to " + iface);
   }

   public boolean hasMoreLogs() {
      return this.isLogBeingGenerated;
   }

   public List getQueryLog() throws SQLException, ClosedOrCancelledStatementException {
      return this.getQueryLog(true, this.fetchSize);
   }

   public List getQueryLog(boolean incremental, int fetchSize) throws SQLException, ClosedOrCancelledStatementException {
      this.checkConnection("getQueryLog");
      if (this.isCancelled) {
         throw new ClosedOrCancelledStatementException("Method getQueryLog() failed. The statement has been closed or cancelled.");
      } else {
         List<String> logs = new ArrayList();
         TFetchResultsResp tFetchResultsResp = null;

         try {
            if (this.stmtHandle == null) {
               if (this.isQueryClosed) {
                  throw new ClosedOrCancelledStatementException("Method getQueryLog() failed. The statement has been closed or cancelled.");
               }

               return logs;
            }

            TFetchResultsReq tFetchResultsReq = new TFetchResultsReq(this.stmtHandle, this.getFetchOrientation(incremental), (long)fetchSize);
            tFetchResultsReq.setFetchType((short)1);
            tFetchResultsResp = this.client.FetchResults(tFetchResultsReq);
            Utils.verifySuccessWithInfo(tFetchResultsResp.getStatus());
         } catch (SQLException e) {
            throw e;
         } catch (TException e) {
            throw new SQLException("Error when getting query log: " + e, e);
         } catch (Exception e) {
            throw new SQLException("Error when getting query log: " + e, e);
         }

         try {
            for(Object[] row : RowSetFactory.create(tFetchResultsResp.getResults(), this.connection.getProtocol())) {
               logs.add(String.valueOf(row[0]));
            }

            return logs;
         } catch (TException e) {
            throw new SQLException("Error building result set for query log: " + e, e);
         }
      }
   }

   private TFetchOrientation getFetchOrientation(boolean incremental) {
      return incremental ? TFetchOrientation.FETCH_NEXT : TFetchOrientation.FETCH_FIRST;
   }

   public String getYarnATSGuid() {
      if (this.stmtHandle != null) {
         String guid64 = Base64.encodeBase64URLSafeString(this.stmtHandle.getOperationId().getGuid()).trim();
         return guid64;
      } else {
         return null;
      }
   }

   public void setInPlaceUpdateStream(InPlaceUpdateStream stream) {
      this.inPlaceUpdateStream = stream;
   }
}
