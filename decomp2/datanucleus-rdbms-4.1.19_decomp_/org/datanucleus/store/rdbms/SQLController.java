package org.datanucleus.store.rdbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.connection.ManagedConnectionResourceListener;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class SQLController {
   protected boolean supportsBatching = false;
   protected int maxBatchSize = -1;
   protected int queryTimeout = 0;
   protected boolean jdbcStatements = false;
   protected boolean paramValuesInBrackets = true;
   Map connectionStatements = new ConcurrentHashMap();

   public SQLController(boolean supportsBatching, int maxBatchSize, int queryTimeout, String stmtLogging) {
      this.supportsBatching = supportsBatching;
      this.maxBatchSize = maxBatchSize;
      this.queryTimeout = queryTimeout;
      if (maxBatchSize == 0) {
         this.supportsBatching = false;
      }

      if (stmtLogging.equalsIgnoreCase("jdbc")) {
         this.jdbcStatements = true;
      } else if (stmtLogging.equalsIgnoreCase("values-in-brackets")) {
         this.paramValuesInBrackets = true;
      } else {
         this.paramValuesInBrackets = false;
      }

   }

   public PreparedStatement getStatementForUpdate(ManagedConnection conn, String stmtText, boolean batchable) throws SQLException {
      return this.getStatementForUpdate(conn, stmtText, batchable, false);
   }

   public PreparedStatement getStatementForUpdate(ManagedConnection conn, String stmtText, boolean batchable, boolean getGeneratedKeysFlag) throws SQLException {
      Connection c = (Connection)conn.getConnection();
      if (this.supportsBatching) {
         ConnectionStatementState state = this.getConnectionStatementState(conn);
         if (state != null) {
            if (state.processable) {
               if (!batchable) {
                  this.processConnectionStatement(conn);
               } else if (state.stmtText.equals(stmtText)) {
                  if (this.maxBatchSize == -1 || state.batchSize < this.maxBatchSize) {
                     ++state.batchSize;
                     state.processable = false;
                     if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
                        NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052100", new Object[]{stmtText, "" + state.batchSize}));
                     }

                     return state.stmt;
                  }

                  if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052101", new Object[]{state.stmtText}));
                  }

                  this.processConnectionStatement(conn);
               } else {
                  this.processConnectionStatement(conn);
               }
            } else if (batchable) {
               if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
                  NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052102", new Object[]{state.stmtText, stmtText}));
               }

               batchable = false;
            }
         }
      }

      PreparedStatement ps = getGeneratedKeysFlag ? c.prepareStatement(stmtText, 1) : c.prepareStatement(stmtText);
      ps.clearBatch();
      if (!this.jdbcStatements) {
         ps = new ParamLoggingPreparedStatement(ps, stmtText);
         ((ParamLoggingPreparedStatement)ps).setParamsInAngleBrackets(this.paramValuesInBrackets);
      }

      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("052109", new Object[]{ps, StringUtils.toJVMIDString(c)}));
      }

      if (batchable && this.supportsBatching) {
         if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
            NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052103", new Object[]{stmtText}));
         }

         ConnectionStatementState state = new ConnectionStatementState();
         state.stmt = ps;
         state.stmtText = stmtText;
         state.batchSize = 1;
         this.setConnectionStatementState(conn, state);
      }

      return ps;
   }

   public PreparedStatement getStatementForQuery(ManagedConnection conn, String stmtText) throws SQLException {
      return this.getStatementForQuery(conn, stmtText, (String)null, (String)null);
   }

   public PreparedStatement getStatementForQuery(ManagedConnection conn, String stmtText, String resultSetType, String resultSetConcurrency) throws SQLException {
      Connection c = (Connection)conn.getConnection();
      if (this.supportsBatching) {
         ConnectionStatementState state = this.getConnectionStatementState(conn);
         if (state != null && state.processable) {
            this.processConnectionStatement(conn);
         }
      }

      PreparedStatement ps = null;
      if (resultSetType == null && resultSetConcurrency == null) {
         ps = c.prepareStatement(stmtText);
         ps.clearBatch();
      } else {
         int rsTypeValue = 1003;
         if (resultSetType != null) {
            if (resultSetType.equals("scroll-sensitive")) {
               rsTypeValue = 1005;
            } else if (resultSetType.equals("scroll-insensitive")) {
               rsTypeValue = 1004;
            }
         }

         int rsConcurrencyValue = 1007;
         if (resultSetConcurrency != null && resultSetConcurrency.equals("updateable")) {
            rsConcurrencyValue = 1008;
         }

         ps = c.prepareStatement(stmtText, rsTypeValue, rsConcurrencyValue);
         ps.clearBatch();
      }

      if (this.queryTimeout > 0) {
         ps.setQueryTimeout(this.queryTimeout / 1000);
      }

      if (NucleusLogger.DATASTORE.isDebugEnabled()) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("052110", new Object[]{StringUtils.toJVMIDString(ps)}));
      }

      if (!this.jdbcStatements) {
         ps = new ParamLoggingPreparedStatement(ps, stmtText);
         ((ParamLoggingPreparedStatement)ps).setParamsInAngleBrackets(this.paramValuesInBrackets);
      }

      return ps;
   }

   public int[] executeStatementUpdate(ExecutionContext ec, ManagedConnection conn, String stmt, PreparedStatement ps, boolean processNow) throws SQLException {
      ConnectionStatementState state = this.getConnectionStatementState(conn);
      if (state != null) {
         if (state.stmt == ps) {
            if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
               NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052104", new Object[]{state.stmtText, "" + state.batchSize}));
            }

            state.processable = true;
            state.stmt.addBatch();
            if (processNow) {
               state.closeStatementOnProcess = false;
               return this.processConnectionStatement(conn);
            }

            return null;
         }

         this.processConnectionStatement(conn);
      }

      long startTime = System.currentTimeMillis();
      if (NucleusLogger.DATASTORE_NATIVE.isDebugEnabled()) {
         if (ps instanceof ParamLoggingPreparedStatement) {
            NucleusLogger.DATASTORE_NATIVE.debug(((ParamLoggingPreparedStatement)ps).getStatementWithParamsReplaced());
         } else {
            NucleusLogger.DATASTORE_NATIVE.debug(stmt);
         }
      }

      int ind = ps.executeUpdate();
      if (ec != null && ec.getStatistics() != null) {
         ec.getStatistics().incrementNumWrites();
      }

      ps.clearBatch();
      if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
         NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("045001", new Object[]{"" + (System.currentTimeMillis() - startTime), "" + ind, StringUtils.toJVMIDString(ps)}));
      }

      return new int[]{ind};
   }

   public boolean executeStatement(ExecutionContext ec, ManagedConnection conn, String stmt, PreparedStatement ps) throws SQLException {
      if (this.supportsBatching) {
         ConnectionStatementState state = this.getConnectionStatementState(conn);
         if (state != null && state.processable) {
            this.processConnectionStatement(conn);
         }
      }

      long startTime = System.currentTimeMillis();
      if (NucleusLogger.DATASTORE_NATIVE.isDebugEnabled()) {
         if (ps instanceof ParamLoggingPreparedStatement) {
            NucleusLogger.DATASTORE_NATIVE.debug(((ParamLoggingPreparedStatement)ps).getStatementWithParamsReplaced());
         } else {
            NucleusLogger.DATASTORE_NATIVE.debug(stmt);
         }
      }

      boolean flag = ps.execute();
      if (ec != null && ec.getStatistics() != null) {
         ec.getStatistics().incrementNumWrites();
      }

      ps.clearBatch();
      if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
         NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("045002", new Object[]{"" + (System.currentTimeMillis() - startTime), StringUtils.toJVMIDString(ps)}));
      }

      return flag;
   }

   public ResultSet executeStatementQuery(ExecutionContext ec, ManagedConnection conn, String stmt, PreparedStatement ps) throws SQLException {
      if (this.supportsBatching) {
         ConnectionStatementState state = this.getConnectionStatementState(conn);
         if (state != null) {
            if (state.processable) {
               this.processConnectionStatement(conn);
            } else if (NucleusLogger.DATASTORE_RETRIEVE.isDebugEnabled()) {
               NucleusLogger.DATASTORE_RETRIEVE.debug(Localiser.msg("052106", new Object[]{state.stmtText, stmt}));
            }
         }
      }

      long startTime = System.currentTimeMillis();
      if (NucleusLogger.DATASTORE_NATIVE.isDebugEnabled()) {
         if (ps instanceof ParamLoggingPreparedStatement) {
            NucleusLogger.DATASTORE_NATIVE.debug(((ParamLoggingPreparedStatement)ps).getStatementWithParamsReplaced());
         } else {
            NucleusLogger.DATASTORE_NATIVE.debug(stmt);
         }
      }

      ResultSet rs = ps.executeQuery();
      if (ec != null && ec.getStatistics() != null) {
         ec.getStatistics().incrementNumReads();
      }

      ps.clearBatch();
      if (NucleusLogger.DATASTORE_RETRIEVE.isDebugEnabled()) {
         NucleusLogger.DATASTORE_RETRIEVE.debug(Localiser.msg("045000", System.currentTimeMillis() - startTime));
      }

      return rs;
   }

   public void abortStatementForConnection(ManagedConnection conn, PreparedStatement ps) {
      ConnectionStatementState state = this.getConnectionStatementState(conn);
      if (state != null && state.stmt == ps) {
         try {
            this.removeConnectionStatementState(conn);
            ps.close();
         } catch (SQLException var5) {
         }
      }

   }

   public void closeStatement(ManagedConnection conn, PreparedStatement ps) throws SQLException {
      ConnectionStatementState state = this.getConnectionStatementState(conn);
      if (state != null && state.stmt == ps) {
         state.closeStatementOnProcess = true;
      } else {
         try {
            if (NucleusLogger.DATASTORE.isDebugEnabled()) {
               NucleusLogger.DATASTORE.debug(Localiser.msg("052110", new Object[]{StringUtils.toJVMIDString(ps)}));
            }

            ps.close();
         } catch (SQLException sqle) {
            if (!sqle.getMessage().equals("Already closed")) {
               throw sqle;
            }
         }
      }

   }

   public void processStatementsForConnection(ManagedConnection conn) throws SQLException {
      if (this.supportsBatching && this.getConnectionStatementState(conn) != null) {
         this.processConnectionStatement(conn);
      }
   }

   protected int[] processConnectionStatement(ManagedConnection conn) throws SQLException {
      ConnectionStatementState state = this.getConnectionStatementState(conn);
      if (state != null && state.processable) {
         long startTime = System.currentTimeMillis();
         if (NucleusLogger.DATASTORE_NATIVE.isDebugEnabled()) {
            if (state.stmt instanceof ParamLoggingPreparedStatement) {
               NucleusLogger.DATASTORE_NATIVE.debug(((ParamLoggingPreparedStatement)state.stmt).getStatementWithParamsReplaced());
            } else {
               NucleusLogger.DATASTORE_NATIVE.debug(state.stmtText);
            }
         }

         int[] ind = state.stmt.executeBatch();
         state.stmt.clearBatch();
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("045001", new Object[]{"" + (System.currentTimeMillis() - startTime), StringUtils.intArrayToString(ind), StringUtils.toJVMIDString(state.stmt)}));
         }

         this.removeConnectionStatementState(conn);
         if (state.closeStatementOnProcess) {
            state.stmt.close();
         }

         return ind;
      } else {
         return null;
      }
   }

   protected void removeConnectionStatementState(ManagedConnection conn) {
      this.connectionStatements.remove(conn);
   }

   protected ConnectionStatementState getConnectionStatementState(ManagedConnection conn) {
      return (ConnectionStatementState)this.connectionStatements.get(conn);
   }

   protected void setConnectionStatementState(final ManagedConnection conn, ConnectionStatementState state) {
      this.connectionStatements.put(conn, state);
      conn.addListener(new ManagedConnectionResourceListener() {
         public void transactionFlushed() {
            try {
               SQLController.this.processStatementsForConnection(conn);
            } catch (SQLException e) {
               ConnectionStatementState state = SQLController.this.getConnectionStatementState(conn);
               if (state != null) {
                  SQLController.this.removeConnectionStatementState(conn);
                  if (state.closeStatementOnProcess) {
                     try {
                        state.stmt.close();
                     } catch (SQLException var4) {
                     }
                  }
               }

               throw new NucleusDataStoreException(Localiser.msg("052108"), e);
            }
         }

         public void transactionPreClose() {
         }

         public void managedConnectionPreClose() {
         }

         public void managedConnectionPostClose() {
         }

         public void resourcePostClose() {
         }
      });
   }

   static class ConnectionStatementState {
      PreparedStatement stmt = null;
      String stmtText = null;
      int batchSize = 0;
      boolean processable = false;
      boolean closeStatementOnProcess = false;

      public String toString() {
         return "StmtState : stmt=" + StringUtils.toJVMIDString(this.stmt) + " sql=" + this.stmtText + " batch=" + this.batchSize + " closeOnProcess=" + this.closeStatementOnProcess;
      }
   }
}
