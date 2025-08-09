package org.apache.hadoop.hive.metastore.txn;

import com.google.common.annotations.VisibleForTesting;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.common.ServerUtils;
import org.apache.hadoop.hive.common.StringableMap;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Private;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.common.classification.RetrySemantics.CannotRetry;
import org.apache.hadoop.hive.common.classification.RetrySemantics.Idempotent;
import org.apache.hadoop.hive.common.classification.RetrySemantics.ReadOnly;
import org.apache.hadoop.hive.common.classification.RetrySemantics.SafeToRetry;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConfUtil;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.DatabaseProduct;
import org.apache.hadoop.hive.metastore.HouseKeeperService;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.AbortTxnRequest;
import org.apache.hadoop.hive.metastore.api.AbortTxnsRequest;
import org.apache.hadoop.hive.metastore.api.AddDynamicPartitions;
import org.apache.hadoop.hive.metastore.api.CheckLockRequest;
import org.apache.hadoop.hive.metastore.api.CommitTxnRequest;
import org.apache.hadoop.hive.metastore.api.CompactionRequest;
import org.apache.hadoop.hive.metastore.api.CompactionResponse;
import org.apache.hadoop.hive.metastore.api.CompactionType;
import org.apache.hadoop.hive.metastore.api.DataOperationType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.HeartbeatRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeRequest;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeResponse;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.LockComponent;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockResponse;
import org.apache.hadoop.hive.metastore.api.LockState;
import org.apache.hadoop.hive.metastore.api.LockType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchLockException;
import org.apache.hadoop.hive.metastore.api.NoSuchTxnException;
import org.apache.hadoop.hive.metastore.api.OpenTxnRequest;
import org.apache.hadoop.hive.metastore.api.OpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.ShowCompactRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponse;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponseElement;
import org.apache.hadoop.hive.metastore.api.ShowLocksRequest;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponse;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponseElement;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TxnAbortedException;
import org.apache.hadoop.hive.metastore.api.TxnInfo;
import org.apache.hadoop.hive.metastore.api.TxnOpenException;
import org.apache.hadoop.hive.metastore.api.TxnState;
import org.apache.hadoop.hive.metastore.api.UnlockRequest;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Private
@Evolving
abstract class TxnHandler implements TxnStore, TxnStore.MutexAPI {
   protected static final char INITIATED_STATE = 'i';
   protected static final char WORKING_STATE = 'w';
   protected static final char READY_FOR_CLEANING = 'r';
   static final char FAILED_STATE = 'f';
   static final char SUCCEEDED_STATE = 's';
   static final char ATTEMPTED_STATE = 'a';
   protected static final char MAJOR_TYPE = 'a';
   protected static final char MINOR_TYPE = 'i';
   protected static final char TXN_ABORTED = 'a';
   protected static final char TXN_OPEN = 'o';
   protected static final char LOCK_ACQUIRED = 'a';
   protected static final char LOCK_WAITING = 'w';
   protected static final char LOCK_EXCLUSIVE = 'e';
   protected static final char LOCK_SHARED = 'r';
   protected static final char LOCK_SEMI_SHARED = 'w';
   private static final int ALLOWED_REPEATED_DEADLOCKS = 10;
   private static final Logger LOG = LoggerFactory.getLogger(TxnHandler.class.getName());
   private static DataSource connPool;
   private static DataSource connPoolMutex;
   private static boolean doRetryOnConnPool = false;
   private static volatile int maxOpenTxns = 0;
   private static volatile long numOpenTxns = 0L;
   private static volatile boolean tooManyOpenTxns = false;
   private static volatile HouseKeeperService openTxnsCounter = null;
   private int deadlockCnt;
   private long deadlockRetryInterval;
   protected HiveConf conf;
   private static DatabaseProduct dbProduct;
   private static SQLGenerator sqlGenerator;
   private long timeout;
   private String identifierQuoteString;
   private long retryInterval;
   private int retryLimit;
   private int retryNum;
   private static final ReentrantLock derbyLock = new ReentrantLock(true);
   private static final ConcurrentHashMap derbyKey2Lock = new ConcurrentHashMap();
   private static final String hostname = ServerUtils.hostname();
   private static Map jumpTable;

   public TxnHandler() {
   }

   public void setConf(HiveConf conf) {
      this.conf = conf;
      this.checkQFileTestHack();
      synchronized(TxnHandler.class) {
         if (connPool == null) {
            LOG.info(HiveConfUtil.dumpConfig(conf).toString());
            Connection dbConn = null;

            try {
               int maxPoolSize = conf.getIntVar(ConfVars.METASTORE_CONNECTION_POOLING_MAX_CONNECTIONS);
               long getConnectionTimeoutMs = 30000L;
               connPool = setupJdbcConnectionPool(conf, maxPoolSize, getConnectionTimeoutMs);
               connPoolMutex = setupJdbcConnectionPool(conf, maxPoolSize + TxnStore.MUTEX_KEY.values().length, getConnectionTimeoutMs);
               dbConn = this.getDbConn(2);
               this.determineDatabaseProduct(dbConn);
               sqlGenerator = new SQLGenerator(dbProduct, conf);
            } catch (SQLException e) {
               String msg = "Unable to instantiate JDBC connection pooling, " + e.getMessage();
               LOG.error(msg);
               throw new RuntimeException(e);
            } finally {
               closeDbConn(dbConn);
            }
         }
      }

      this.timeout = HiveConf.getTimeVar(conf, ConfVars.HIVE_TXN_TIMEOUT, TimeUnit.MILLISECONDS);
      buildJumpTable();
      this.retryInterval = HiveConf.getTimeVar(conf, ConfVars.HMSHANDLERINTERVAL, TimeUnit.MILLISECONDS);
      this.retryLimit = HiveConf.getIntVar(conf, ConfVars.HMSHANDLERATTEMPTS);
      this.deadlockRetryInterval = this.retryInterval / 10L;
      maxOpenTxns = HiveConf.getIntVar(conf, ConfVars.HIVE_MAX_OPEN_TXNS);
   }

   @ReadOnly
   public GetOpenTxnsInfoResponse getOpenTxnsInfo() throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         ResultSet rs = null;

         GetOpenTxnsInfoResponse var19;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select ntxn_next - 1 from NEXT_TXN_ID";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               throw new MetaException("Transaction tables not properly initialized, no record found in next_txn_id");
            }

            long hwm = rs.getLong(1);
            if (rs.wasNull()) {
               throw new MetaException("Transaction tables not properly initialized, null record found in next_txn_id");
            }

            close(rs);
            List<TxnInfo> txnInfos = new ArrayList();
            s = "select txn_id, txn_state, txn_user, txn_host, txn_started, txn_last_heartbeat from TXNS where txn_id <= " + hwm;
            LOG.debug("Going to execute query<" + s + ">");
            rs = stmt.executeQuery(s);

            while(rs.next()) {
               char c = rs.getString(2).charAt(0);
               TxnState state;
               switch (c) {
                  case 'a':
                     state = TxnState.ABORTED;
                     break;
                  case 'o':
                     state = TxnState.OPEN;
                     break;
                  default:
                     throw new MetaException("Unexpected transaction state " + c + " found in txns table");
               }

               TxnInfo txnInfo = new TxnInfo(rs.getLong(1), state, rs.getString(3), rs.getString(4));
               txnInfo.setStartedTime(rs.getLong(5));
               txnInfo.setLastHeartbeatTime(rs.getLong(6));
               txnInfos.add(txnInfo);
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
            var19 = new GetOpenTxnsInfoResponse(hwm, txnInfos);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "getOpenTxnsInfo");
            throw new MetaException("Unable to select from transaction database: " + getMessage(e) + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

         return var19;
      } catch (RetryException var17) {
         return this.getOpenTxnsInfo();
      }
   }

   @ReadOnly
   public GetOpenTxnsResponse getOpenTxns() throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         ResultSet rs = null;

         GetOpenTxnsResponse var11;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select ntxn_next - 1 from NEXT_TXN_ID";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               throw new MetaException("Transaction tables not properly initialized, no record found in next_txn_id");
            }

            long hwm = rs.getLong(1);
            if (rs.wasNull()) {
               throw new MetaException("Transaction tables not properly initialized, null record found in next_txn_id");
            }

            close(rs);
            Set<Long> openList = new HashSet();
            s = "select txn_id, txn_state from TXNS where txn_id <= " + hwm;
            LOG.debug("Going to execute query<" + s + ">");
            rs = stmt.executeQuery(s);
            long minOpenTxn = Long.MAX_VALUE;

            while(rs.next()) {
               long txnId = rs.getLong(1);
               openList.add(txnId);
               char c = rs.getString(2).charAt(0);
               if (c == 'o') {
                  minOpenTxn = Math.min(minOpenTxn, txnId);
               }
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
            GetOpenTxnsResponse otr = new GetOpenTxnsResponse(hwm, openList);
            if (minOpenTxn < Long.MAX_VALUE) {
               otr.setMin_open_txn(minOpenTxn);
            }

            var11 = otr;
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "getOpenTxns");
            throw new MetaException("Unable to select from transaction database, " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

         return var11;
      } catch (RetryException var19) {
         return this.getOpenTxns();
      }
   }

   private static void startHouseKeeperService(HiveConf conf, Class c) {
      try {
         openTxnsCounter = (HouseKeeperService)c.newInstance();
         openTxnsCounter.start(conf);
      } catch (Exception ex) {
         LOG.error("Failed to start {}", new Object[]{openTxnsCounter.getClass() + ".  The system will not handle {} ", openTxnsCounter.getServiceDescription(), ".  Root Cause: ", ex});
      }

   }

   @Idempotent
   public OpenTxnsResponse openTxns(OpenTxnRequest rqst) throws MetaException {
      if (openTxnsCounter == null) {
         synchronized(TxnHandler.class) {
            try {
               if (openTxnsCounter == null) {
                  startHouseKeeperService(this.conf, Class.forName("org.apache.hadoop.hive.ql.txn.AcidOpenTxnsCounterService"));
               }
            } catch (ClassNotFoundException e) {
               throw new MetaException(e.getMessage());
            }
         }
      }

      if (!tooManyOpenTxns && numOpenTxns >= (long)maxOpenTxns) {
         tooManyOpenTxns = true;
      }

      if (tooManyOpenTxns) {
         if (!((double)numOpenTxns < (double)maxOpenTxns * 0.9)) {
            LOG.warn("Maximum allowed number of open transactions (" + maxOpenTxns + ") has been reached. Current number of open transactions: " + numOpenTxns);
            throw new MetaException("Maximum allowed number of open transactions has been reached. See hive.max.open.txns.");
         }

         tooManyOpenTxns = false;
      }

      int numTxns = rqst.getNum_txns();

      try {
         Connection dbConn = null;
         Statement stmt = null;
         ResultSet rs = null;

         OpenTxnsResponse var31;
         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            int maxTxns = HiveConf.getIntVar(this.conf, ConfVars.HIVE_TXN_MAX_OPEN_BATCH);
            if (numTxns > maxTxns) {
               numTxns = maxTxns;
            }

            stmt = dbConn.createStatement();
            String s = sqlGenerator.addForUpdateClause("select ntxn_next from NEXT_TXN_ID");
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               throw new MetaException("Transaction database not properly configured, can't find next transaction id.");
            }

            long first = rs.getLong(1);
            s = "update NEXT_TXN_ID set ntxn_next = " + (first + (long)numTxns);
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            long now = this.getDbTime(dbConn);
            List<Long> txnIds = new ArrayList(numTxns);
            List<String> rows = new ArrayList();

            for(long i = first; i < first + (long)numTxns; ++i) {
               txnIds.add(i);
               rows.add(i + "," + quoteChar('o') + "," + now + "," + now + "," + quoteString(rqst.getUser()) + "," + quoteString(rqst.getHostname()));
            }

            for(String q : sqlGenerator.createInsertValuesStmt("TXNS (txn_id, txn_state, txn_started, txn_last_heartbeat, txn_user, txn_host)", rows)) {
               LOG.debug("Going to execute update <" + q + ">");
               stmt.execute(q);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
            var31 = new OpenTxnsResponse(txnIds);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "openTxns(" + rqst + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
            this.unlockInternal();
         }

         return var31;
      } catch (RetryException var25) {
         return this.openTxns(rqst);
      }
   }

   @Idempotent
   public void abortTxn(AbortTxnRequest rqst) throws NoSuchTxnException, MetaException, TxnAbortedException {
      long txnid = rqst.getTxnid();

      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            if (this.abortTxns(dbConn, Collections.singletonList(txnid), true) != 1) {
               stmt = dbConn.createStatement();
               TxnStatus status = this.findTxnState(txnid, stmt);
               if (status == TxnHandler.TxnStatus.ABORTED) {
                  LOG.info("abortTxn(" + JavaUtils.txnIdToString(txnid) + ") requested by it is already " + TxnHandler.TxnStatus.ABORTED);
                  return;
               }

               raiseTxnUnexpectedState(status, txnid);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "abortTxn(" + rqst + ")");
            throw new MetaException("Unable to update transaction database " + StringUtils.stringifyException(e));
         } finally {
            close((ResultSet)null, stmt, dbConn);
            this.unlockInternal();
         }
      } catch (RetryException var13) {
         this.abortTxn(rqst);
      }
   }

   @Idempotent
   public void abortTxns(AbortTxnsRequest rqst) throws NoSuchTxnException, MetaException {
      List<Long> txnids = rqst.getTxn_ids();

      try {
         Connection dbConn = null;

         try {
            dbConn = this.getDbConn(2);
            int numAborted = this.abortTxns(dbConn, txnids, false);
            if (numAborted != txnids.size()) {
               LOG.warn("Abort Transactions command only aborted " + numAborted + " out of " + txnids.size() + " transactions. It's possible that the other " + (txnids.size() - numAborted) + " transactions have been aborted or committed, or the transaction ids are invalid.");
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "abortTxns(" + rqst + ")");
            throw new MetaException("Unable to update transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeDbConn(dbConn);
         }
      } catch (RetryException var11) {
         this.abortTxns(rqst);
      }

   }

   @Idempotent({"No-op if already committed"})
   public void commitTxn(CommitTxnRequest rqst) throws NoSuchTxnException, TxnAbortedException, MetaException {
      long txnid = rqst.getTxnid();

      try {
         Connection dbConn = null;
         Statement stmt = null;
         ResultSet lockHandle = null;
         ResultSet commitIdRs = null;

         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            lockHandle = this.lockTransactionRecord(stmt, txnid, 'o');
            if (lockHandle == null) {
               TxnStatus actualTxnStatus = this.findTxnState(txnid, stmt);
               if (actualTxnStatus == TxnHandler.TxnStatus.COMMITTED) {
                  LOG.info("Nth commitTxn(" + JavaUtils.txnIdToString(txnid) + ") msg");
                  return;
               }

               raiseTxnUnexpectedState(actualTxnStatus, txnid);
               shouldNeverHappen(txnid);
            }

            String conflictSQLSuffix = "from TXN_COMPONENTS where tc_txnid=" + txnid + " and tc_operation_type IN(" + quoteChar(TxnHandler.OpertaionType.UPDATE.sqlConst) + "," + quoteChar(TxnHandler.OpertaionType.DELETE.sqlConst) + ")";
            ResultSet rs = stmt.executeQuery(sqlGenerator.addLimitClause(1, "tc_operation_type " + conflictSQLSuffix));
            if (rs.next()) {
               close(rs);
               commitIdRs = stmt.executeQuery(sqlGenerator.addForUpdateClause("select ntxn_next - 1 from NEXT_TXN_ID"));
               if (!commitIdRs.next()) {
                  throw new IllegalStateException("No rows found in NEXT_TXN_ID");
               }

               long commitId = commitIdRs.getLong(1);
               Savepoint undoWriteSetForCurrentTxn = dbConn.setSavepoint();
               stmt.executeUpdate("insert into WRITE_SET (ws_database, ws_table, ws_partition, ws_txnid, ws_commit_id, ws_operation_type) select distinct tc_database, tc_table, tc_partition, tc_txnid, " + commitId + ", tc_operation_type " + conflictSQLSuffix);
               rs = stmt.executeQuery(sqlGenerator.addLimitClause(1, "committed.ws_txnid, committed.ws_commit_id, committed.ws_database,committed.ws_table, committed.ws_partition, cur.ws_commit_id cur_ws_commit_id, cur.ws_operation_type cur_op, committed.ws_operation_type committed_op from WRITE_SET committed INNER JOIN WRITE_SET cur ON committed.ws_database=cur.ws_database and committed.ws_table=cur.ws_table and (committed.ws_partition=cur.ws_partition or (committed.ws_partition is null and cur.ws_partition is null)) where cur.ws_txnid <= committed.ws_commit_id and cur.ws_txnid=" + txnid + " and committed.ws_txnid <> " + txnid + " and (committed.ws_operation_type=" + quoteChar(TxnHandler.OpertaionType.UPDATE.sqlConst) + " OR cur.ws_operation_type=" + quoteChar(TxnHandler.OpertaionType.UPDATE.sqlConst) + ")"));
               if (rs.next()) {
                  String committedTxn = "[" + JavaUtils.txnIdToString(rs.getLong(1)) + "," + rs.getLong(2) + "]";
                  StringBuilder resource = (new StringBuilder(rs.getString(3))).append("/").append(rs.getString(4));
                  String partitionName = rs.getString(5);
                  if (partitionName != null) {
                     resource.append('/').append(partitionName);
                  }

                  String msg = "Aborting [" + JavaUtils.txnIdToString(txnid) + "," + rs.getLong(6) + "] due to a write conflict on " + resource + " committed by " + committedTxn + " " + rs.getString(7) + "/" + rs.getString(8);
                  close(rs);
                  dbConn.rollback(undoWriteSetForCurrentTxn);
                  LOG.info(msg);
                  if (this.abortTxns(dbConn, Collections.singletonList(txnid), true) != 1) {
                     throw new IllegalStateException(msg + " FAILED!");
                  }

                  dbConn.commit();
                  close((ResultSet)null, stmt, dbConn);
                  throw new TxnAbortedException(msg);
               }
            }

            String s = "insert into COMPLETED_TXN_COMPONENTS select tc_txnid, tc_database, tc_table, tc_partition from TXN_COMPONENTS where tc_txnid = " + txnid;
            LOG.debug("Going to execute insert <" + s + ">");
            int modCount = 0;
            if (stmt.executeUpdate(s) < 1) {
               LOG.info("Expected to move at least one record from txn_components to completed_txn_components when committing txn! " + JavaUtils.txnIdToString(txnid));
            }

            s = "delete from TXN_COMPONENTS where tc_txnid = " + txnid;
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            s = "delete from HIVE_LOCKS where hl_txnid = " + txnid;
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            s = "delete from TXNS where txn_id = " + txnid;
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "commitTxn(" + rqst + ")");
            throw new MetaException("Unable to update transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(commitIdRs);
            close(lockHandle, stmt, dbConn);
            this.unlockInternal();
         }
      } catch (RetryException var24) {
         this.commitTxn(rqst);
      }
   }

   @SafeToRetry
   public void performWriteSetGC() {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         dbConn = this.getDbConn(2);
         stmt = dbConn.createStatement();
         rs = stmt.executeQuery("select ntxn_next - 1 from NEXT_TXN_ID");
         if (!rs.next()) {
            throw new IllegalStateException("NEXT_TXN_ID is empty: DB is corrupted");
         }

         long highestAllocatedTxnId = rs.getLong(1);
         close(rs);
         rs = stmt.executeQuery("select min(txn_id) from TXNS where txn_state=" + quoteChar('o'));
         if (!rs.next()) {
            throw new IllegalStateException("Scalar query returned no rows?!?!!");
         }

         long lowestOpenTxnId = rs.getLong(1);
         long commitHighWaterMark;
         if (rs.wasNull()) {
            commitHighWaterMark = highestAllocatedTxnId + 1L;
         } else {
            commitHighWaterMark = lowestOpenTxnId;
         }

         int delCnt = stmt.executeUpdate("delete from WRITE_SET where ws_commit_id < " + commitHighWaterMark);
         LOG.info("Deleted " + delCnt + " obsolete rows from WRTIE_SET");
         dbConn.commit();
      } catch (SQLException ex) {
         LOG.warn("WriteSet GC failed due to " + getMessage(ex), ex);
      } finally {
         close(rs, stmt, dbConn);
      }

   }

   @CannotRetry
   public LockResponse lock(LockRequest rqst) throws NoSuchTxnException, TxnAbortedException, MetaException {
      ConnectionLockIdPair connAndLockId = this.enqueueLockWithRetry(rqst);

      try {
         return this.checkLockWithRetry(connAndLockId.dbConn, connAndLockId.extLockId, rqst.getTxnid());
      } catch (NoSuchLockException e) {
         throw new MetaException("Couldn't find a lock we just created! " + e.getMessage());
      }
   }

   private ResultSet lockTransactionRecord(Statement stmt, long txnId, Character txnState) throws SQLException, MetaException {
      String query = "select TXN_STATE from TXNS where TXN_ID = " + txnId + (txnState != null ? " AND TXN_STATE=" + quoteChar(txnState) : "");
      ResultSet rs = stmt.executeQuery(sqlGenerator.addForUpdateClause(query));
      if (rs.next()) {
         return rs;
      } else {
         close(rs);
         return null;
      }
   }

   private ConnectionLockIdPair enqueueLockWithRetry(LockRequest rqst) throws NoSuchTxnException, TxnAbortedException, MetaException {
      boolean success = false;
      Connection dbConn = null;

      try {
         Statement stmt = null;
         ResultSet rs = null;
         ResultSet lockHandle = null;

         ConnectionLockIdPair var41;
         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            long txnid = rqst.getTxnid();
            stmt = dbConn.createStatement();
            if (isValidTxn(txnid)) {
               lockHandle = this.lockTransactionRecord(stmt, txnid, 'o');
               if (lockHandle == null) {
                  ensureValidTxn(dbConn, txnid, stmt);
                  shouldNeverHappen(txnid);
               }
            }

            String s = sqlGenerator.addForUpdateClause("select nl_next from NEXT_LOCK_ID");
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               LOG.debug("Going to rollback");
               dbConn.rollback();
               throw new MetaException("Transaction tables not properly initialized, no record found in next_lock_id");
            }

            long extLockId = rs.getLong(1);
            s = "update NEXT_LOCK_ID set nl_next = " + (extLockId + 1L);
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            if (txnid > 0L) {
               List<String> rows = new ArrayList();

               for(LockComponent lc : rqst.getComponent()) {
                  if (!lc.isSetIsAcid() || lc.isIsAcid()) {
                     boolean updateTxnComponents;
                     if (!lc.isSetOperationType()) {
                        updateTxnComponents = true;
                     } else {
                        switch (lc.getOperationType()) {
                           case SELECT:
                              updateTxnComponents = false;
                              break;
                           case INSERT:
                           case UPDATE:
                           case DELETE:
                              if (!lc.isSetIsDynamicPartitionWrite()) {
                                 updateTxnComponents = true;
                              } else {
                                 updateTxnComponents = !lc.isIsDynamicPartitionWrite();
                              }
                              break;
                           default:
                              throw new IllegalStateException("Unexpected DataOperationType: " + lc.getOperationType() + " agentInfo=" + rqst.getAgentInfo() + " " + JavaUtils.txnIdToString(txnid));
                        }
                     }

                     if (updateTxnComponents) {
                        String dbName = lc.getDbname();
                        String tblName = lc.getTablename();
                        String partName = lc.getPartitionname();
                        rows.add(txnid + ", '" + dbName + "', " + (tblName == null ? "null" : "'" + tblName + "'") + ", " + (partName == null ? "null" : "'" + partName + "'") + "," + quoteString(TxnHandler.OpertaionType.fromDataOperationType(lc.getOperationType()).toString()));
                     }
                  }
               }

               for(String query : sqlGenerator.createInsertValuesStmt("TXN_COMPONENTS (tc_txnid, tc_database, tc_table, tc_partition, tc_operation_type)", rows)) {
                  LOG.debug("Going to execute update <" + query + ">");
                  stmt.executeUpdate(query);
               }
            }

            List<String> rows = new ArrayList();
            long intLockId = 0L;

            for(LockComponent lc : rqst.getComponent()) {
               if (lc.isSetOperationType() && lc.getOperationType() == DataOperationType.UNSET && (this.conf.getBoolVar(ConfVars.HIVE_IN_TEST) || this.conf.getBoolVar(ConfVars.HIVE_IN_TEZ_TEST))) {
                  throw new IllegalStateException("Bug: operationType=" + lc.getOperationType() + " for component " + lc + " agentInfo=" + rqst.getAgentInfo());
               }

               ++intLockId;
               String dbName = lc.getDbname();
               String tblName = lc.getTablename();
               String partName = lc.getPartitionname();
               LockType lockType = lc.getType();
               char lockChar = 'z';
               switch (lockType) {
                  case EXCLUSIVE:
                     lockChar = 'e';
                     break;
                  case SHARED_READ:
                     lockChar = 'r';
                     break;
                  case SHARED_WRITE:
                     lockChar = 'w';
               }

               long now = this.getDbTime(dbConn);
               rows.add(extLockId + ", " + intLockId + "," + txnid + ", " + quoteString(dbName) + ", " + valueOrNullLiteral(tblName) + ", " + valueOrNullLiteral(partName) + ", " + quoteChar('w') + ", " + quoteChar(lockChar) + ", " + (isValidTxn(txnid) ? 0L : now) + ", " + valueOrNullLiteral(rqst.getUser()) + ", " + valueOrNullLiteral(rqst.getHostname()) + ", " + valueOrNullLiteral(rqst.getAgentInfo()));
            }

            for(String query : sqlGenerator.createInsertValuesStmt("HIVE_LOCKS (hl_lock_ext_id, hl_lock_int_id, hl_txnid, hl_db, hl_table, hl_partition,hl_lock_state, hl_lock_type, hl_last_heartbeat, hl_user, hl_host, hl_agent_info)", rows)) {
               LOG.debug("Going to execute update <" + query + ">");
               stmt.executeUpdate(query);
            }

            dbConn.commit();
            success = true;
            var41 = new ConnectionLockIdPair(dbConn, extLockId);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "enqueueLockWithRetry(" + rqst + ")");
            throw new MetaException("Unable to update transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(lockHandle);
            close(rs, stmt, (Connection)null);
            if (!success) {
               closeDbConn(dbConn);
            }

            this.unlockInternal();
         }

         return var41;
      } catch (RetryException var30) {
         return this.enqueueLockWithRetry(rqst);
      }
   }

   private LockResponse checkLockWithRetry(Connection dbConn, long extLockId, long txnId) throws NoSuchLockException, NoSuchTxnException, TxnAbortedException, MetaException {
      try {
         LockResponse var6;
         try {
            this.lockInternal();
            if (dbConn.isClosed()) {
               dbConn = this.getDbConn(2);
            }

            var6 = this.checkLock(dbConn, extLockId);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "checkLockWithRetry(" + extLockId + "," + txnId + ")");
            throw new MetaException("Unable to update transaction database " + StringUtils.stringifyException(e));
         } finally {
            this.unlockInternal();
            closeDbConn(dbConn);
         }

         return var6;
      } catch (RetryException var13) {
         return this.checkLockWithRetry(dbConn, extLockId, txnId);
      }
   }

   @SafeToRetry
   public LockResponse checkLock(CheckLockRequest rqst) throws NoSuchTxnException, NoSuchLockException, TxnAbortedException, MetaException {
      try {
         Connection dbConn = null;
         long extLockId = rqst.getLockid();

         LockResponse var6;
         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            LockInfo info = this.getTxnIdFromLockId(dbConn, extLockId);
            if (info == null) {
               throw new NoSuchLockException("No such lock " + JavaUtils.lockIdToString(extLockId));
            }

            if (info.txnId > 0L) {
               this.heartbeatTxn(dbConn, info.txnId);
            } else {
               this.heartbeatLock(dbConn, extLockId);
            }

            var6 = this.checkLock(dbConn, extLockId);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "checkLock(" + rqst + " )");
            throw new MetaException("Unable to update transaction database " + JavaUtils.lockIdToString(extLockId) + " " + StringUtils.stringifyException(e));
         } finally {
            closeDbConn(dbConn);
            this.unlockInternal();
         }

         return var6;
      } catch (RetryException var13) {
         return this.checkLock(rqst);
      }
   }

   @Idempotent
   public void unlock(UnlockRequest rqst) throws NoSuchLockException, TxnOpenException, MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         long extLockId = rqst.getLockid();

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "delete from HIVE_LOCKS where hl_lock_ext_id = " + extLockId + " AND (hl_txnid = 0 OR (hl_txnid <> 0 AND hl_lock_state = '" + 'w' + "'))";
            LOG.debug("Going to execute update <" + s + ">");
            int rc = stmt.executeUpdate(s);
            if (rc < 1) {
               LOG.debug("Going to rollback");
               dbConn.rollback();
               LockInfo info = this.getTxnIdFromLockId(dbConn, extLockId);
               if (info == null) {
                  LOG.info("No lock in w mode found for unlock(" + JavaUtils.lockIdToString(rqst.getLockid()) + ")");
                  return;
               }

               if (info.txnId != 0L) {
                  String msg = "Unlocking locks associated with transaction not permitted.  " + info;
                  LOG.error(msg);
                  throw new TxnOpenException(msg);
               }

               if (info.txnId == 0L) {
                  String msg = "Found lock in unexpected state " + info;
                  LOG.error(msg);
                  throw new MetaException(msg);
               }
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "unlock(" + rqst + ")");
            throw new MetaException("Unable to update transaction database " + JavaUtils.lockIdToString(extLockId) + " " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }
      } catch (RetryException var16) {
         this.unlock(rqst);
      }
   }

   @ReadOnly
   public ShowLocksResponse showLocks(ShowLocksRequest rqst) throws MetaException {
      try {
         Connection dbConn = null;
         ShowLocksResponse rsp = new ShowLocksResponse();
         List<ShowLocksResponseElement> elems = new ArrayList();
         List<LockInfoExt> sortedList = new ArrayList();
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select hl_lock_ext_id, hl_txnid, hl_db, hl_table, hl_partition, hl_lock_state, hl_lock_type, hl_last_heartbeat, hl_acquired_at, hl_user, hl_host, hl_lock_int_id,hl_blockedby_ext_id, hl_blockedby_int_id, hl_agent_info from HIVE_LOCKS";
            String dbName = rqst.getDbname();
            String tableName = rqst.getTablename();
            String partName = rqst.getPartname();
            StringBuilder filter = new StringBuilder();
            if (dbName != null && !dbName.isEmpty()) {
               filter.append("hl_db=").append(quoteString(dbName));
            }

            if (tableName != null && !tableName.isEmpty()) {
               if (filter.length() > 0) {
                  filter.append(" and ");
               }

               filter.append("hl_table=").append(quoteString(tableName));
            }

            if (partName != null && !partName.isEmpty()) {
               if (filter.length() > 0) {
                  filter.append(" and ");
               }

               filter.append("hl_partition=").append(quoteString(partName));
            }

            String whereClause = filter.toString();
            if (!whereClause.isEmpty()) {
               s = s + " where " + whereClause;
            }

            LOG.debug("Doing to execute query <" + s + ">");
            ResultSet rs = stmt.executeQuery(s);

            while(rs.next()) {
               ShowLocksResponseElement e = new ShowLocksResponseElement();
               e.setLockid(rs.getLong(1));
               long txnid = rs.getLong(2);
               if (!rs.wasNull()) {
                  e.setTxnid(txnid);
               }

               e.setDbname(rs.getString(3));
               e.setTablename(rs.getString(4));
               String partition = rs.getString(5);
               if (partition != null) {
                  e.setPartname(partition);
               }

               switch (rs.getString(6).charAt(0)) {
                  case 'a':
                     e.setState(LockState.ACQUIRED);
                     break;
                  case 'w':
                     e.setState(LockState.WAITING);
                     break;
                  default:
                     throw new MetaException("Unknown lock state " + rs.getString(6).charAt(0));
               }

               switch (rs.getString(7).charAt(0)) {
                  case 'e':
                     e.setType(LockType.EXCLUSIVE);
                     break;
                  case 'r':
                     e.setType(LockType.SHARED_READ);
                     break;
                  case 'w':
                     e.setType(LockType.SHARED_WRITE);
                     break;
                  default:
                     throw new MetaException("Unknown lock type " + rs.getString(6).charAt(0));
               }

               e.setLastheartbeat(rs.getLong(8));
               long acquiredAt = rs.getLong(9);
               if (!rs.wasNull()) {
                  e.setAcquiredat(acquiredAt);
               }

               e.setUser(rs.getString(10));
               e.setHostname(rs.getString(11));
               e.setLockIdInternal(rs.getLong(12));
               long id = rs.getLong(13);
               if (!rs.wasNull()) {
                  e.setBlockedByExtId(id);
               }

               id = rs.getLong(14);
               if (!rs.wasNull()) {
                  e.setBlockedByIntId(id);
               }

               e.setAgentInfo(rs.getString(15));
               sortedList.add(new LockInfoExt(e));
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
         } catch (SQLException e) {
            this.checkRetryable(dbConn, e, "showLocks(" + rqst + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }

         Collections.sort(sortedList, new LockInfoComparator());

         for(LockInfoExt lockInfoExt : sortedList) {
            elems.add(lockInfoExt.e);
         }

         rsp.setLocks(elems);
         return rsp;
      } catch (RetryException var28) {
         return this.showLocks(rqst);
      }
   }

   @SafeToRetry
   public void heartbeat(HeartbeatRequest ids) throws NoSuchTxnException, NoSuchLockException, TxnAbortedException, MetaException {
      try {
         Connection dbConn = null;

         try {
            dbConn = this.getDbConn(2);
            this.heartbeatLock(dbConn, ids.getLockid());
            this.heartbeatTxn(dbConn, ids.getTxnid());
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "heartbeat(" + ids + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeDbConn(dbConn);
         }
      } catch (RetryException var10) {
         this.heartbeat(ids);
      }

   }

   @SafeToRetry
   public HeartbeatTxnRangeResponse heartbeatTxnRange(HeartbeatTxnRangeRequest rqst) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         HeartbeatTxnRangeResponse rsp = new HeartbeatTxnRangeResponse();
         Set<Long> nosuch = new HashSet();
         Set<Long> aborted = new HashSet();
         rsp.setNosuch(nosuch);
         rsp.setAborted(aborted);

         HeartbeatTxnRangeResponse var26;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            List<String> queries = new ArrayList();
            int numTxnsToHeartbeat = (int)(rqst.getMax() - rqst.getMin() + 1L);
            List<Long> txnIds = new ArrayList(numTxnsToHeartbeat);

            for(long txn = rqst.getMin(); txn <= rqst.getMax(); ++txn) {
               txnIds.add(txn);
            }

            TxnUtils.buildQueryWithINClause(this.conf, queries, new StringBuilder("update TXNS set txn_last_heartbeat = " + this.getDbTime(dbConn) + " where txn_state = " + quoteChar('o') + " and "), new StringBuilder(""), txnIds, "txn_id", true, false);
            int updateCnt = 0;

            for(String query : queries) {
               LOG.debug("Going to execute update <" + query + ">");
               updateCnt += stmt.executeUpdate(query);
            }

            if (updateCnt != numTxnsToHeartbeat) {
               dbConn.rollback();

               for(long txn = rqst.getMin(); txn <= rqst.getMax(); ++txn) {
                  try {
                     this.heartbeatTxn(dbConn, txn);
                  } catch (NoSuchTxnException var20) {
                     nosuch.add(txn);
                  } catch (TxnAbortedException var21) {
                     aborted.add(txn);
                  }
               }

               var26 = rsp;
               return var26;
            }

            dbConn.commit();
            var26 = rsp;
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "heartbeatTxnRange(" + rqst + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            close((ResultSet)null, stmt, dbConn);
         }

         return var26;
      } catch (RetryException var24) {
         return this.heartbeatTxnRange(rqst);
      }
   }

   long generateCompactionQueueId(Statement stmt) throws SQLException, MetaException {
      String s = sqlGenerator.addForUpdateClause("select ncq_next from NEXT_COMPACTION_QUEUE_ID");
      LOG.debug("going to execute query <" + s + ">");
      ResultSet rs = stmt.executeQuery(s);
      if (!rs.next()) {
         throw new IllegalStateException("Transaction tables not properly initiated, no record found in next_compaction_queue_id");
      } else {
         long id = rs.getLong(1);
         s = "update NEXT_COMPACTION_QUEUE_ID set ncq_next = " + (id + 1L);
         LOG.debug("Going to execute update <" + s + ">");
         stmt.executeUpdate(s);
         return id;
      }
   }

   @Idempotent
   public CompactionResponse compact(CompactionRequest rqst) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         TxnStore.MutexAPI.LockHandle handle = null;

         CompactionResponse var12;
         try {
            this.lockInternal();
            handle = this.getMutexAPI().acquireLock(TxnStore.MUTEX_KEY.CompactionScheduler.name());
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            long id = this.generateCompactionQueueId(stmt);
            StringBuilder sb = (new StringBuilder("select cq_id, cq_state from COMPACTION_QUEUE where")).append(" cq_state IN(").append(quoteChar('i')).append(",").append(quoteChar('w')).append(") AND cq_database=").append(quoteString(rqst.getDbname())).append(" AND cq_table=").append(quoteString(rqst.getTablename())).append(" AND ");
            if (rqst.getPartitionname() == null) {
               sb.append("cq_partition is null");
            } else {
               sb.append("cq_partition=").append(quoteString(rqst.getPartitionname()));
            }

            LOG.debug("Going to execute query <" + sb.toString() + ">");
            ResultSet rs = stmt.executeQuery(sb.toString());
            if (!rs.next()) {
               close(rs);
               StringBuilder buf = new StringBuilder("insert into COMPACTION_QUEUE (cq_id, cq_database, cq_table, ");
               String partName = rqst.getPartitionname();
               if (partName != null) {
                  buf.append("cq_partition, ");
               }

               buf.append("cq_state, cq_type");
               if (rqst.getProperties() != null) {
                  buf.append(", cq_tblproperties");
               }

               if (rqst.getRunas() != null) {
                  buf.append(", cq_run_as");
               }

               buf.append(") values (");
               buf.append(id);
               buf.append(", '");
               buf.append(rqst.getDbname());
               buf.append("', '");
               buf.append(rqst.getTablename());
               buf.append("', '");
               if (partName != null) {
                  buf.append(partName);
                  buf.append("', '");
               }

               buf.append('i');
               buf.append("', '");
               switch (rqst.getType()) {
                  case MAJOR:
                     buf.append('a');
                     break;
                  case MINOR:
                     buf.append('i');
                     break;
                  default:
                     LOG.debug("Going to rollback");
                     dbConn.rollback();
                     throw new MetaException("Unexpected compaction type " + rqst.getType().toString());
               }

               if (rqst.getProperties() != null) {
                  buf.append("', '");
                  buf.append((new StringableMap(rqst.getProperties())).toString());
               }

               if (rqst.getRunas() != null) {
                  buf.append("', '");
                  buf.append(rqst.getRunas());
               }

               buf.append("')");
               String s = buf.toString();
               LOG.debug("Going to execute update <" + s + ">");
               stmt.executeUpdate(s);
               LOG.debug("Going to commit");
               dbConn.commit();
               var12 = new CompactionResponse(id, "initiated", true);
               return var12;
            }

            long enqueuedId = rs.getLong(1);
            String state = compactorStateToResponse(rs.getString(2).charAt(0));
            LOG.info("Ignoring request to compact " + rqst.getDbname() + "/" + rqst.getTablename() + "/" + rqst.getPartitionname() + " since it is already " + quoteString(state) + " with id=" + enqueuedId);
            var12 = new CompactionResponse(enqueuedId, state, false);
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "compact(" + rqst + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
            if (handle != null) {
               handle.releaseLocks();
            }

            this.unlockInternal();
         }

         return var12;
      } catch (RetryException var19) {
         return this.compact(rqst);
      }
   }

   private static String compactorStateToResponse(char s) {
      switch (s) {
         case 'a':
            return "attempted";
         case 'f':
            return "failed";
         case 'i':
            return "initiated";
         case 'r':
            return "ready for cleaning";
         case 's':
            return "succeeded";
         case 'w':
            return "working";
         default:
            return Character.toString(s);
      }
   }

   @ReadOnly
   public ShowCompactResponse showCompact(ShowCompactRequest rqst) throws MetaException {
      ShowCompactResponse response = new ShowCompactResponse(new ArrayList());
      Connection dbConn = null;
      Statement stmt = null;

      try {
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select cq_database, cq_table, cq_partition, cq_state, cq_type, cq_worker_id, cq_start, -1 cc_end, cq_run_as, cq_hadoop_job_id, cq_id from COMPACTION_QUEUE union all select cc_database, cc_table, cc_partition, cc_state, cc_type, cc_worker_id, cc_start, cc_end, cc_run_as, cc_hadoop_job_id, cc_id from COMPLETED_COMPACTIONS";
            LOG.debug("Going to execute query <" + s + ">");
            ResultSet rs = stmt.executeQuery(s);

            while(rs.next()) {
               ShowCompactResponseElement e = new ShowCompactResponseElement();
               e.setDbname(rs.getString(1));
               e.setTablename(rs.getString(2));
               e.setPartitionname(rs.getString(3));
               e.setState(compactorStateToResponse(rs.getString(4).charAt(0)));
               switch (rs.getString(5).charAt(0)) {
                  case 'a':
                     e.setType(CompactionType.MAJOR);
                     break;
                  case 'i':
                     e.setType(CompactionType.MINOR);
               }

               e.setWorkerid(rs.getString(6));
               long start = rs.getLong(7);
               if (!rs.wasNull()) {
                  e.setStart(start);
               }

               long endTime = rs.getLong(8);
               if (endTime != -1L) {
                  e.setEndTime(endTime);
               }

               e.setRunAs(rs.getString(9));
               e.setHadoopJobId(rs.getString(10));
               e.setId(rs.getLong(11));
               response.addToCompacts(e);
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "showCompact(" + rqst + ")");
            throw new MetaException("Unable to select from transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }

         return response;
      } catch (RetryException var18) {
         return this.showCompact(rqst);
      }
   }

   private static void shouldNeverHappen(long txnid) {
      throw new RuntimeException("This should never happen: " + JavaUtils.txnIdToString(txnid));
   }

   private static void shouldNeverHappen(long txnid, long extLockId, long intLockId) {
      throw new RuntimeException("This should never happen: " + JavaUtils.txnIdToString(txnid) + " " + JavaUtils.lockIdToString(extLockId) + " " + intLockId);
   }

   @SafeToRetry
   public void addDynamicPartitions(AddDynamicPartitions rqst) throws NoSuchTxnException, TxnAbortedException, MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet lockHandle = null;
      ResultSet rs = null;

      try {
         try {
            this.lockInternal();
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            lockHandle = this.lockTransactionRecord(stmt, rqst.getTxnid(), 'o');
            if (lockHandle == null) {
               ensureValidTxn(dbConn, rqst.getTxnid(), stmt);
               shouldNeverHappen(rqst.getTxnid());
            }

            OpertaionType ot = TxnHandler.OpertaionType.UPDATE;
            if (rqst.isSetOperationType()) {
               ot = TxnHandler.OpertaionType.fromDataOperationType(rqst.getOperationType());
            }

            List<String> rows = new ArrayList();

            for(String partName : rqst.getPartitionnames()) {
               rows.add(rqst.getTxnid() + "," + quoteString(rqst.getDbname()) + "," + quoteString(rqst.getTablename()) + "," + quoteString(partName) + "," + quoteChar(ot.sqlConst));
            }

            int modCount = 0;

            for(String query : sqlGenerator.createInsertValuesStmt("TXN_COMPONENTS (tc_txnid, tc_database, tc_table, tc_partition, tc_operation_type)", rows)) {
               LOG.debug("Going to execute update <" + query + ">");
               stmt.executeUpdate(query);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "addDynamicPartitions(" + rqst + ")");
            throw new MetaException("Unable to insert into from transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(lockHandle, stmt, dbConn);
            this.unlockInternal();
         }
      } catch (RetryException var18) {
         this.addDynamicPartitions(rqst);
      }

   }

   @Idempotent
   public void cleanupRecords(HiveObjectType type, Database db, Table table, Iterator partitionIterator) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            List<String> queries = new ArrayList();
            StringBuilder buff = new StringBuilder();
            switch (type) {
               case DATABASE:
                  String dbName = db.getName();
                  buff.append("delete from TXN_COMPONENTS where tc_database='");
                  buff.append(dbName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPLETED_TXN_COMPONENTS where ctc_database='");
                  buff.append(dbName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPACTION_QUEUE where cq_database='");
                  buff.append(dbName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPLETED_COMPACTIONS where cc_database='");
                  buff.append(dbName);
                  buff.append("'");
                  queries.add(buff.toString());
                  break;
               case TABLE:
                  String dbName = table.getDbName();
                  String tblName = table.getTableName();
                  buff.append("delete from TXN_COMPONENTS where tc_database='");
                  buff.append(dbName);
                  buff.append("' and tc_table='");
                  buff.append(tblName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPLETED_TXN_COMPONENTS where ctc_database='");
                  buff.append(dbName);
                  buff.append("' and ctc_table='");
                  buff.append(tblName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPACTION_QUEUE where cq_database='");
                  buff.append(dbName);
                  buff.append("' and cq_table='");
                  buff.append(tblName);
                  buff.append("'");
                  queries.add(buff.toString());
                  buff.setLength(0);
                  buff.append("delete from COMPLETED_COMPACTIONS where cc_database='");
                  buff.append(dbName);
                  buff.append("' and cc_table='");
                  buff.append(tblName);
                  buff.append("'");
                  queries.add(buff.toString());
                  break;
               case PARTITION:
                  String dbName = table.getDbName();
                  String tblName = table.getTableName();
                  List<FieldSchema> partCols = table.getPartitionKeys();

                  while(partitionIterator.hasNext()) {
                     Partition p = (Partition)partitionIterator.next();
                     List<String> partVals = p.getValues();
                     String partName = Warehouse.makePartName(partCols, partVals);
                     buff.append("delete from TXN_COMPONENTS where tc_database='");
                     buff.append(dbName);
                     buff.append("' and tc_table='");
                     buff.append(tblName);
                     buff.append("' and tc_partition='");
                     buff.append(partName);
                     buff.append("'");
                     queries.add(buff.toString());
                     buff.setLength(0);
                     buff.append("delete from COMPLETED_TXN_COMPONENTS where ctc_database='");
                     buff.append(dbName);
                     buff.append("' and ctc_table='");
                     buff.append(tblName);
                     buff.append("' and ctc_partition='");
                     buff.append(partName);
                     buff.append("'");
                     queries.add(buff.toString());
                     buff.setLength(0);
                     buff.append("delete from COMPACTION_QUEUE where cq_database='");
                     buff.append(dbName);
                     buff.append("' and cq_table='");
                     buff.append(tblName);
                     buff.append("' and cq_partition='");
                     buff.append(partName);
                     buff.append("'");
                     queries.add(buff.toString());
                     buff.setLength(0);
                     buff.append("delete from COMPLETED_COMPACTIONS where cc_database='");
                     buff.append(dbName);
                     buff.append("' and cc_table='");
                     buff.append(tblName);
                     buff.append("' and cc_partition='");
                     buff.append(partName);
                     buff.append("'");
                     queries.add(buff.toString());
                  }
                  break;
               default:
                  throw new MetaException("Invalid object type for cleanup: " + type);
            }

            for(String query : queries) {
               LOG.debug("Going to execute update <" + query + ">");
               stmt.executeUpdate(query);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "cleanupRecords");
            if (!e.getMessage().contains("does not exist")) {
               throw new MetaException("Unable to clean up " + StringUtils.stringifyException(e));
            }

            LOG.warn("Cannot perform cleanup since metastore table does not exist");
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }
      } catch (RetryException var21) {
         this.cleanupRecords(type, db, table, partitionIterator);
      }

   }

   @VisibleForTesting
   public int numLocksInLockTable() throws SQLException, MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      int var6;
      try {
         dbConn = this.getDbConn(2);
         stmt = dbConn.createStatement();
         String s = "select count(*) from HIVE_LOCKS";
         LOG.debug("Going to execute query <" + s + ">");
         rs = stmt.executeQuery(s);
         rs.next();
         int rc = rs.getInt(1);
         dbConn.rollback();
         var6 = rc;
      } finally {
         close(rs, stmt, dbConn);
      }

      return var6;
   }

   public long setTimeout(long milliseconds) {
      long previous_timeout = this.timeout;
      this.timeout = milliseconds;
      return previous_timeout;
   }

   Connection getDbConn(int isolationLevel) throws SQLException {
      return this.getDbConn(isolationLevel, connPool);
   }

   private Connection getDbConn(int isolationLevel, DataSource connPool) throws SQLException {
      int rc = doRetryOnConnPool ? 10 : 1;
      Connection dbConn = null;

      while(true) {
         try {
            dbConn = connPool.getConnection();
            dbConn.setAutoCommit(false);
            dbConn.setTransactionIsolation(isolationLevel);
            return dbConn;
         } catch (SQLException e) {
            closeDbConn(dbConn);
            --rc;
            if (rc <= 0) {
               throw e;
            }

            LOG.error("There is a problem with a connection from the pool, retrying(rc=" + rc + "): " + getMessage(e), e);
         }
      }
   }

   static void rollbackDBConn(Connection dbConn) {
      try {
         if (dbConn != null && !dbConn.isClosed()) {
            dbConn.rollback();
         }
      } catch (SQLException e) {
         LOG.warn("Failed to rollback db connection " + getMessage(e));
      }

   }

   protected static void closeDbConn(Connection dbConn) {
      try {
         if (dbConn != null && !dbConn.isClosed()) {
            dbConn.close();
         }
      } catch (SQLException e) {
         LOG.warn("Failed to close db connection " + getMessage(e));
      }

   }

   protected static void closeStmt(Statement stmt) {
      try {
         if (stmt != null && !stmt.isClosed()) {
            stmt.close();
         }
      } catch (SQLException e) {
         LOG.warn("Failed to close statement " + getMessage(e));
      }

   }

   static void close(ResultSet rs) {
      try {
         if (rs != null && !rs.isClosed()) {
            rs.close();
         }
      } catch (SQLException ex) {
         LOG.warn("Failed to close statement " + getMessage(ex));
      }

   }

   static void close(ResultSet rs, Statement stmt, Connection dbConn) {
      close(rs);
      closeStmt(stmt);
      closeDbConn(dbConn);
   }

   protected void checkRetryable(Connection conn, SQLException e, String caller) throws RetryException, MetaException {
      boolean sendRetrySignal = false;

      try {
         if (dbProduct == null) {
            throw new IllegalStateException("DB Type not determined yet.");
         }

         if (DatabaseProduct.isDeadlock(dbProduct, e)) {
            if (this.deadlockCnt++ < 10) {
               long waitInterval = this.deadlockRetryInterval * (long)this.deadlockCnt;
               LOG.warn("Deadlock detected in " + caller + ". Will wait " + waitInterval + "ms try again up to " + (10 - this.deadlockCnt + 1) + " times.");

               try {
                  Thread.sleep(waitInterval);
               } catch (InterruptedException var13) {
               }

               sendRetrySignal = true;
            } else {
               LOG.error("Too many repeated deadlocks in " + caller + ", giving up.");
            }
         } else if (isRetryable(this.conf, e)) {
            if (this.retryNum++ < this.retryLimit) {
               LOG.warn("Retryable error detected in " + caller + ".  Will wait " + this.retryInterval + "ms and retry up to " + (this.retryLimit - this.retryNum + 1) + " times.  Error: " + getMessage(e));

               try {
                  Thread.sleep(this.retryInterval);
               } catch (InterruptedException var12) {
               }

               sendRetrySignal = true;
            } else {
               LOG.error("Fatal error in " + caller + ". Retry limit (" + this.retryLimit + ") reached. Last error: " + getMessage(e));
            }
         } else {
            LOG.info("Non-retryable error in " + caller + " : " + getMessage(e));
         }
      } finally {
         if (!sendRetrySignal) {
            this.deadlockCnt = 0;
            this.retryNum = 0;
         }

      }

      if (sendRetrySignal) {
         throw new RetryException();
      }
   }

   protected long getDbTime(Connection conn) throws MetaException {
      Statement stmt = null;

      long var5;
      try {
         stmt = conn.createStatement();
         String s;
         switch (dbProduct) {
            case DERBY:
               s = "values current_timestamp";
               break;
            case MYSQL:
            case POSTGRES:
            case SQLSERVER:
               s = "select current_timestamp";
               break;
            case ORACLE:
               s = "select current_timestamp from dual";
               break;
            default:
               String msg = "Unknown database product: " + dbProduct.toString();
               LOG.error(msg);
               throw new MetaException(msg);
         }

         LOG.debug("Going to execute query <" + s + ">");
         ResultSet rs = stmt.executeQuery(s);
         if (!rs.next()) {
            throw new MetaException("No results from date query");
         }

         var5 = rs.getTimestamp(1).getTime();
      } catch (SQLException e) {
         String msg = "Unable to determine current time: " + e.getMessage();
         LOG.error(msg);
         throw new MetaException(msg);
      } finally {
         closeStmt(stmt);
      }

      return var5;
   }

   protected String getIdentifierQuoteString(Connection conn) throws SQLException {
      if (this.identifierQuoteString == null) {
         this.identifierQuoteString = conn.getMetaData().getIdentifierQuoteString();
      }

      return this.identifierQuoteString;
   }

   private void determineDatabaseProduct(Connection conn) {
      if (dbProduct == null) {
         try {
            String s = conn.getMetaData().getDatabaseProductName();
            dbProduct = DatabaseProduct.determineDatabaseProduct(s);
            if (dbProduct == DatabaseProduct.OTHER) {
               String msg = "Unrecognized database product name <" + s + ">";
               LOG.error(msg);
               throw new IllegalStateException(msg);
            }
         } catch (SQLException e) {
            String msg = "Unable to get database product name";
            LOG.error(msg, e);
            throw new IllegalStateException(msg, e);
         }
      }
   }

   private void checkQFileTestHack() {
      boolean hackOn = HiveConf.getBoolVar(this.conf, ConfVars.HIVE_IN_TEST) || HiveConf.getBoolVar(this.conf, ConfVars.HIVE_IN_TEZ_TEST);
      if (hackOn) {
         LOG.info("Hacking in canned values for transaction manager");
         TxnDbUtil.setConfValues(this.conf);

         try {
            TxnDbUtil.prepDb(this.conf);
         } catch (Exception e) {
            if (e.getMessage() != null && !e.getMessage().contains("already exists")) {
               throw new RuntimeException("Unable to set up transaction database for testing: " + e.getMessage(), e);
            }
         }
      }

   }

   private int abortTxns(Connection dbConn, List txnids, boolean isStrict) throws SQLException {
      return this.abortTxns(dbConn, txnids, -1L, isStrict);
   }

   private int abortTxns(Connection dbConn, List txnids, long max_heartbeat, boolean isStrict) throws SQLException {
      Statement stmt = null;
      int updateCnt = 0;
      if (txnids.isEmpty()) {
         return 0;
      } else {
         int var17;
         try {
            stmt = dbConn.createStatement();
            List<String> queries = new ArrayList();
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            prefix.append("update TXNS set txn_state = " + quoteChar('a') + " where txn_state = " + quoteChar('o') + " and ");
            if (max_heartbeat > 0L) {
               suffix.append(" and txn_last_heartbeat < ").append(max_heartbeat);
            } else {
               suffix.append("");
            }

            TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, txnids, "txn_id", true, false);

            for(String query : queries) {
               LOG.debug("Going to execute update <" + query + ">");
               updateCnt += stmt.executeUpdate(query);
            }

            if (updateCnt >= txnids.size() || !isStrict) {
               queries.clear();
               prefix.setLength(0);
               suffix.setLength(0);
               prefix.append("delete from HIVE_LOCKS where ");
               suffix.append("");
               TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, txnids, "hl_txnid", false, false);

               for(String query : queries) {
                  LOG.debug("Going to execute update <" + query + ">");
                  int rc = stmt.executeUpdate(query);
                  LOG.debug("Removed " + rc + " records from HIVE_LOCKS");
               }

               return updateCnt;
            }

            var17 = updateCnt;
         } finally {
            closeStmt(stmt);
         }

         return var17;
      }
   }

   private static boolean isValidTxn(long txnId) {
      return txnId != 0L;
   }

   @SafeToRetry({"See @SafeToRetry"})
   private LockResponse checkLock(Connection dbConn, long extLockId) throws NoSuchLockException, NoSuchTxnException, TxnAbortedException, MetaException, SQLException {
      TxnStore.MutexAPI.LockHandle handle = null;
      Statement stmt = null;
      ResultSet rs = null;
      LockResponse response = new LockResponse();
      boolean isPartOfDynamicPartitionInsert = true;

      try {
         handle = this.getMutexAPI().acquireLock(TxnStore.MUTEX_KEY.CheckLock.name());
         List<LockInfo> locksBeingChecked = this.getLockInfoFromLockId(dbConn, extLockId);
         response.setLockid(extLockId);
         LOG.debug("checkLock(): Setting savepoint. extLockId=" + JavaUtils.lockIdToString(extLockId));
         Savepoint save = dbConn.setSavepoint();
         StringBuilder query = new StringBuilder("select hl_lock_ext_id, hl_lock_int_id, hl_db, hl_table, hl_partition, hl_lock_state, hl_lock_type, hl_txnid from HIVE_LOCKS where hl_db in (");
         Set<String> strings = new HashSet(locksBeingChecked.size());
         List<LockInfo> writeSet = new ArrayList();

         for(LockInfo info : locksBeingChecked) {
            strings.add(info.db);
            if (!isPartOfDynamicPartitionInsert && info.type == LockType.SHARED_WRITE) {
               writeSet.add(info);
            }
         }

         if (!writeSet.isEmpty()) {
            if (((LockInfo)writeSet.get(0)).txnId == 0L) {
               throw new IllegalStateException("Found Write lock for " + JavaUtils.lockIdToString(extLockId) + " but no txnid");
            }

            stmt = dbConn.createStatement();
            StringBuilder sb = new StringBuilder(" ws_database, ws_table, ws_partition, ws_txnid, ws_commit_id from WRITE_SET where ws_commit_id >= " + ((LockInfo)writeSet.get(0)).txnId + " and (");

            for(LockInfo info : writeSet) {
               sb.append("(ws_database = ").append(quoteString(info.db)).append(" and ws_table = ").append(quoteString(info.table)).append(" and ws_partition ").append(info.partition == null ? "is null" : "= " + quoteString(info.partition)).append(") or ");
            }

            sb.setLength(sb.length() - 4);
            sb.append(")");
            rs = stmt.executeQuery(sqlGenerator.addLimitClause(1, sb.toString()));
            if (rs.next()) {
               String resourceName = rs.getString(1) + '/' + rs.getString(2);
               String partName = rs.getString(3);
               if (partName != null) {
                  resourceName = resourceName + '/' + partName;
               }

               String msg = "Aborting " + JavaUtils.txnIdToString(((LockInfo)writeSet.get(0)).txnId) + " since a concurrent committed transaction [" + JavaUtils.txnIdToString(rs.getLong(4)) + "," + rs.getLong(5) + "] has already updated resouce '" + resourceName + "'";
               LOG.info(msg);
               if (this.abortTxns(dbConn, Collections.singletonList(((LockInfo)writeSet.get(0)).txnId), true) != 1) {
                  throw new IllegalStateException(msg + " FAILED!");
               }

               dbConn.commit();
               throw new TxnAbortedException(msg);
            }

            close(rs, stmt, (Connection)null);
         }

         boolean first = true;

         for(String s : strings) {
            if (first) {
               first = false;
            } else {
               query.append(", ");
            }

            query.append('\'');
            query.append(s);
            query.append('\'');
         }

         query.append(")");
         boolean sawNull = false;
         strings.clear();

         for(LockInfo info : locksBeingChecked) {
            if (info.table == null) {
               sawNull = true;
               break;
            }

            strings.add(info.table);
         }

         if (!sawNull) {
            query.append(" and (hl_table is null or hl_table in(");
            first = true;

            for(String s : strings) {
               if (first) {
                  first = false;
               } else {
                  query.append(", ");
               }

               query.append('\'');
               query.append(s);
               query.append('\'');
            }

            query.append("))");
            sawNull = false;
            strings.clear();

            for(LockInfo info : locksBeingChecked) {
               if (info.partition == null) {
                  sawNull = true;
                  break;
               }

               strings.add(info.partition);
            }

            if (!sawNull) {
               query.append(" and (hl_partition is null or hl_partition in(");
               first = true;

               for(String s : strings) {
                  if (first) {
                     first = false;
                  } else {
                     query.append(", ");
                  }

                  query.append('\'');
                  query.append(s);
                  query.append('\'');
               }

               query.append("))");
            }
         }

         query.append(" and hl_lock_ext_id <= ").append(extLockId);
         LOG.debug("Going to execute query <" + query.toString() + ">");
         stmt = dbConn.createStatement();
         rs = stmt.executeQuery(query.toString());
         SortedSet<LockInfo> lockSet = new TreeSet(new LockInfoComparator());

         while(rs.next()) {
            lockSet.add(new LockInfo(rs));
         }

         LockInfo[] locks = (LockInfo[])lockSet.toArray(new LockInfo[lockSet.size()]);
         if (LOG.isTraceEnabled()) {
            LOG.trace("Locks to check(full): ");

            for(LockInfo info : locks) {
               LOG.trace("  " + info);
            }
         }

         for(LockInfo info : locksBeingChecked) {
            int index = -1;

            for(int i = 0; i < locks.length; ++i) {
               if (locks[i].equals(info)) {
                  index = i;
                  break;
               }
            }

            if (index == -1) {
               LOG.debug("Going to rollback");
               dbConn.rollback();
               throw new MetaException("How did we get here, we heartbeated our lock before we started! ( " + info + ")");
            }

            if (locks[index].state != LockState.ACQUIRED) {
               boolean acquired = false;

               label500:
               for(int i = index - 1; i >= 0; --i) {
                  if (locks[index].db.equals(locks[i].db) && (locks[index].table == null || locks[i].table == null || locks[index].table.equals(locks[i].table)) && (locks[index].partition == null || locks[i].partition == null || locks[index].partition.equals(locks[i].partition))) {
                     LockAction lockAction = (LockAction)((Map)((Map)jumpTable.get(locks[index].type)).get(locks[i].type)).get(locks[i].state);
                     LOG.debug("desired Lock: " + info + " checked Lock: " + locks[i] + " action: " + lockAction);
                     switch (lockAction) {
                        case WAIT:
                           if (!this.ignoreConflict(info, locks[i])) {
                              this.wait(dbConn, save);
                              String sqlText = "update HIVE_LOCKS set HL_BLOCKEDBY_EXT_ID=" + locks[i].extLockId + ", HL_BLOCKEDBY_INT_ID=" + locks[i].intLockId + " where HL_LOCK_EXT_ID=" + info.extLockId + " and HL_LOCK_INT_ID=" + info.intLockId;
                              LOG.debug("Executing sql: " + sqlText);
                              int updCnt = stmt.executeUpdate(sqlText);
                              if (updCnt != 1) {
                                 shouldNeverHappen(info.txnId, info.extLockId, info.intLockId);
                              }

                              LOG.debug("Going to commit");
                              dbConn.commit();
                              response.setState(LockState.WAITING);
                              LOG.debug("Lock(" + info + ") waiting for Lock(" + locks[i] + ")");
                              LockResponse var26 = response;
                              return var26;
                           }
                        case ACQUIRE:
                           this.acquire(dbConn, stmt, extLockId, info);
                           acquired = true;
                        default:
                           if (acquired) {
                              break label500;
                           }
                        case KEEP_LOOKING:
                     }
                  }
               }

               if (!acquired) {
                  this.acquire(dbConn, stmt, extLockId, info);
               }
            }
         }

         LOG.debug("Going to commit");
         dbConn.commit();
         response.setState(LockState.ACQUIRED);
         return response;
      } finally {
         close(rs, stmt, (Connection)null);
         if (handle != null) {
            handle.releaseLocks();
         }

      }
   }

   private boolean ignoreConflict(LockInfo desiredLock, LockInfo existingLock) {
      return desiredLock.isDbLock() && desiredLock.type == LockType.SHARED_READ && existingLock.isTableLock() && existingLock.type == LockType.EXCLUSIVE || existingLock.isDbLock() && existingLock.type == LockType.SHARED_READ && desiredLock.isTableLock() && desiredLock.type == LockType.EXCLUSIVE || desiredLock.isDbLock() && desiredLock.type == LockType.SHARED_READ && existingLock.isPartitionLock() && existingLock.type == LockType.EXCLUSIVE || existingLock.isDbLock() && existingLock.type == LockType.SHARED_READ && desiredLock.isPartitionLock() && desiredLock.type == LockType.EXCLUSIVE || desiredLock.txnId != 0L && desiredLock.txnId == existingLock.txnId || desiredLock.txnId == 0L && desiredLock.extLockId == existingLock.extLockId;
   }

   private void wait(Connection dbConn, Savepoint save) throws SQLException {
      LOG.debug("Going to rollback to savepoint");
      dbConn.rollback(save);
   }

   private void acquire(Connection dbConn, Statement stmt, long extLockId, LockInfo lockInfo) throws SQLException, NoSuchLockException, MetaException {
      long now = this.getDbTime(dbConn);
      String s = "update HIVE_LOCKS set hl_lock_state = 'a', hl_last_heartbeat = " + (isValidTxn(lockInfo.txnId) ? 0L : now) + ", hl_acquired_at = " + now + ",HL_BLOCKEDBY_EXT_ID=NULL,HL_BLOCKEDBY_INT_ID=null where hl_lock_ext_id = " + extLockId + " and hl_lock_int_id = " + lockInfo.intLockId;
      LOG.debug("Going to execute update <" + s + ">");
      int rc = stmt.executeUpdate(s);
      if (rc < 1) {
         LOG.debug("Going to rollback");
         dbConn.rollback();
         throw new NoSuchLockException("No such lock: (" + JavaUtils.lockIdToString(extLockId) + "," + lockInfo.intLockId + ") " + JavaUtils.txnIdToString(lockInfo.txnId));
      }
   }

   private void heartbeatLock(Connection dbConn, long extLockId) throws NoSuchLockException, SQLException, MetaException {
      if (extLockId != 0L) {
         Statement stmt = null;

         try {
            stmt = dbConn.createStatement();
            long now = this.getDbTime(dbConn);
            String s = "update HIVE_LOCKS set hl_last_heartbeat = " + now + " where hl_lock_ext_id = " + extLockId;
            LOG.debug("Going to execute update <" + s + ">");
            int rc = stmt.executeUpdate(s);
            if (rc < 1) {
               LOG.debug("Going to rollback");
               dbConn.rollback();
               throw new NoSuchLockException("No such lock: " + JavaUtils.lockIdToString(extLockId));
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } finally {
            closeStmt(stmt);
         }

      }
   }

   private void heartbeatTxn(Connection dbConn, long txnid) throws NoSuchTxnException, TxnAbortedException, SQLException, MetaException {
      if (txnid != 0L) {
         Statement stmt = null;

         try {
            stmt = dbConn.createStatement();
            long now = this.getDbTime(dbConn);
            String s = "update TXNS set txn_last_heartbeat = " + now + " where txn_id = " + txnid + " and txn_state = '" + 'o' + "'";
            LOG.debug("Going to execute update <" + s + ">");
            int rc = stmt.executeUpdate(s);
            if (rc < 1) {
               ensureValidTxn(dbConn, txnid, stmt);
               LOG.warn("Can neither heartbeat txn nor confirm it as invalid.");
               dbConn.rollback();
               throw new NoSuchTxnException("No such txn: " + txnid);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } finally {
            closeStmt(stmt);
         }

      }
   }

   private TxnStatus findTxnState(long txnid, Statement stmt) throws SQLException, MetaException {
      String s = "select txn_state from TXNS where txn_id = " + txnid;
      LOG.debug("Going to execute query <" + s + ">");
      ResultSet rs = stmt.executeQuery(s);
      if (!rs.next()) {
         s = sqlGenerator.addLimitClause(1, "1 from COMPLETED_TXN_COMPONENTS where CTC_TXNID = " + txnid);
         LOG.debug("Going to execute query <" + s + ">");
         ResultSet rs2 = stmt.executeQuery(s);
         return rs2.next() ? TxnHandler.TxnStatus.COMMITTED : TxnHandler.TxnStatus.UNKNOWN;
      } else {
         char txnState = rs.getString(1).charAt(0);
         if (txnState == 'a') {
            return TxnHandler.TxnStatus.ABORTED;
         } else {
            assert txnState == 'o' : "we found it in TXNS but it's not ABORTED, so must be OPEN";

            return TxnHandler.TxnStatus.OPEN;
         }
      }
   }

   private static void raiseTxnUnexpectedState(TxnStatus actualStatus, long txnid) throws NoSuchTxnException, TxnAbortedException {
      switch (actualStatus) {
         case ABORTED:
            throw new TxnAbortedException("Transaction " + JavaUtils.txnIdToString(txnid) + " already aborted");
         case COMMITTED:
            throw new NoSuchTxnException("Transaction " + JavaUtils.txnIdToString(txnid) + " is already committed.");
         case UNKNOWN:
            throw new NoSuchTxnException("No such transaction " + JavaUtils.txnIdToString(txnid));
         case OPEN:
            throw new NoSuchTxnException(JavaUtils.txnIdToString(txnid) + " is " + TxnHandler.TxnStatus.OPEN);
         default:
            throw new IllegalArgumentException("Unknown TxnStatus " + actualStatus);
      }
   }

   private static void ensureValidTxn(Connection dbConn, long txnid, Statement stmt) throws SQLException, NoSuchTxnException, TxnAbortedException {
      String s = "select txn_state from TXNS where txn_id = " + txnid;
      LOG.debug("Going to execute query <" + s + ">");
      ResultSet rs = stmt.executeQuery(s);
      if (rs.next()) {
         if (rs.getString(1).charAt(0) == 'a') {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            throw new TxnAbortedException("Transaction " + JavaUtils.txnIdToString(txnid) + " already aborted");
         }
      } else {
         s = "select count(*) from COMPLETED_TXN_COMPONENTS where CTC_TXNID = " + txnid;
         ResultSet rs2 = stmt.executeQuery(s);
         boolean alreadyCommitted = rs2.next() && rs2.getInt(1) > 0;
         LOG.debug("Going to rollback");
         rollbackDBConn(dbConn);
         if (alreadyCommitted) {
            throw new NoSuchTxnException("Transaction " + JavaUtils.txnIdToString(txnid) + " is already committed.");
         } else {
            throw new NoSuchTxnException("No such transaction " + JavaUtils.txnIdToString(txnid));
         }
      }
   }

   private LockInfo getTxnIdFromLockId(Connection dbConn, long extLockId) throws NoSuchLockException, MetaException, SQLException {
      Statement stmt = null;
      ResultSet rs = null;

      LockInfo info;
      try {
         stmt = dbConn.createStatement();
         String s = "select hl_lock_ext_id, hl_lock_int_id, hl_db, hl_table, hl_partition, hl_lock_state, hl_lock_type, hl_txnid from HIVE_LOCKS where hl_lock_ext_id = " + extLockId;
         LOG.debug("Going to execute query <" + s + ">");
         rs = stmt.executeQuery(s);
         if (rs.next()) {
            info = new LockInfo(rs);
            LOG.debug("getTxnIdFromLockId(" + extLockId + ") Return " + JavaUtils.txnIdToString(info.txnId));
            LockInfo var8 = info;
            return var8;
         }

         info = null;
      } finally {
         close(rs);
         closeStmt(stmt);
      }

      return info;
   }

   private List getLockInfoFromLockId(Connection dbConn, long extLockId) throws NoSuchLockException, MetaException, SQLException {
      Statement stmt = null;

      Object var9;
      try {
         stmt = dbConn.createStatement();
         String s = "select hl_lock_ext_id, hl_lock_int_id, hl_db, hl_table, hl_partition, hl_lock_state, hl_lock_type, hl_txnid from HIVE_LOCKS where hl_lock_ext_id = " + extLockId;
         LOG.debug("Going to execute query <" + s + ">");
         ResultSet rs = stmt.executeQuery(s);
         boolean sawAtLeastOne = false;

         List<LockInfo> ourLockInfo;
         for(ourLockInfo = new ArrayList(); rs.next(); sawAtLeastOne = true) {
            ourLockInfo.add(new LockInfo(rs));
         }

         if (!sawAtLeastOne) {
            throw new MetaException("This should never happen!  We already checked the lock(" + JavaUtils.lockIdToString(extLockId) + ") existed but now we can't find it!");
         }

         var9 = ourLockInfo;
      } finally {
         closeStmt(stmt);
      }

      return (List)var9;
   }

   private void timeOutLocks(Connection dbConn, long now) {
      Statement stmt = null;
      ResultSet rs = null;

      try {
         stmt = dbConn.createStatement();
         long maxHeartbeatTime = now - this.timeout;
         String s = "select distinct hl_lock_ext_id from HIVE_LOCKS where hl_last_heartbeat < " + maxHeartbeatTime + " and hl_txnid = 0";
         List<Long> extLockIDs = new ArrayList();
         rs = stmt.executeQuery(s);

         while(rs.next()) {
            extLockIDs.add(rs.getLong(1));
         }

         rs.close();
         dbConn.commit();
         if (extLockIDs.size() > 0) {
            List<String> queries = new ArrayList();
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            prefix.append("delete from HIVE_LOCKS where hl_last_heartbeat < ");
            prefix.append(maxHeartbeatTime);
            prefix.append(" and hl_txnid = 0 and ");
            suffix.append("");
            TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, extLockIDs, "hl_lock_ext_id", true, false);
            int deletedLocks = 0;

            for(String query : queries) {
               LOG.debug("Removing expired locks via: " + query);
               deletedLocks += stmt.executeUpdate(query);
            }

            if (deletedLocks > 0) {
               Collections.sort(extLockIDs);
               LOG.info("Deleted " + deletedLocks + " int locks from HIVE_LOCKS due to timeout (HL_LOCK_EXT_ID list:  " + extLockIDs + ") maxHeartbeatTime=" + maxHeartbeatTime);
            }

            LOG.debug("Going to commit");
            dbConn.commit();
            return;
         }
      } catch (SQLException ex) {
         LOG.error("Failed to purge timedout locks due to: " + getMessage(ex), ex);
         return;
      } catch (Exception ex) {
         LOG.error("Failed to purge timedout locks due to: " + ex.getMessage(), ex);
         return;
      } finally {
         close(rs);
         closeStmt(stmt);
      }

   }

   @Idempotent
   public void performTimeOuts() {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         dbConn = this.getDbConn(2);
         long now = this.getDbTime(dbConn);
         this.timeOutLocks(dbConn, now);

         while(true) {
            stmt = dbConn.createStatement();
            String s = " txn_id from TXNS where txn_state = 'o' and txn_last_heartbeat <  " + (now - this.timeout);
            s = sqlGenerator.addLimitClause(500000, s);
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               return;
            }

            List<List<Long>> timedOutTxns = new ArrayList();
            List<Long> currentBatch = new ArrayList(50000);
            timedOutTxns.add(currentBatch);

            do {
               if (currentBatch.size() == 50000) {
                  currentBatch = new ArrayList(50000);
                  timedOutTxns.add(currentBatch);
               }

               currentBatch.add(rs.getLong(1));
            } while(rs.next());

            dbConn.commit();
            close(rs, stmt, (Connection)null);
            int numTxnsAborted = 0;

            for(List batchToAbort : timedOutTxns) {
               if (this.abortTxns(dbConn, batchToAbort, now - this.timeout, true) == batchToAbort.size()) {
                  dbConn.commit();
                  numTxnsAborted += batchToAbort.size();
                  Collections.sort(batchToAbort);
                  LOG.info("Aborted the following transactions due to timeout: " + batchToAbort.toString());
               } else {
                  dbConn.rollback();
               }
            }

            LOG.info("Aborted " + numTxnsAborted + " transactions due to timeout");
         }
      } catch (SQLException ex) {
         LOG.warn("Aborting timedout transactions failed due to " + getMessage(ex), ex);
      } catch (MetaException e) {
         LOG.warn("Aborting timedout transactions failed due to " + e.getMessage(), e);
      } finally {
         close(rs, stmt, dbConn);
      }

   }

   @ReadOnly
   public void countOpenTxns() throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select count(*) from TXNS where txn_state = 'o'";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (!rs.next()) {
               LOG.error("Transaction database not properly configured, can't find txn_state from TXNS.");
            } else {
               numOpenTxns = rs.getLong(1);
            }
         } catch (SQLException e) {
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            LOG.info("Failed to update number of open transactions");
            this.checkRetryable(dbConn, e, "countOpenTxns()");
         } finally {
            close(rs, stmt, dbConn);
         }
      } catch (RetryException var11) {
         this.countOpenTxns();
      }

   }

   private static synchronized DataSource setupJdbcConnectionPool(HiveConf conf, int maxPoolSize, long getConnectionTimeoutMs) throws SQLException {
      String driverUrl = HiveConf.getVar(conf, ConfVars.METASTORECONNECTURLKEY);
      String user = getMetastoreJdbcUser(conf);
      String passwd = getMetastoreJdbcPasswd(conf);
      String connectionPooler = conf.getVar(ConfVars.METASTORE_CONNECTION_POOLING_TYPE).toLowerCase();
      if ("bonecp".equals(connectionPooler)) {
         BoneCPConfig config = new BoneCPConfig();
         config.setJdbcUrl(driverUrl);
         config.setConnectionTimeoutInMs(getConnectionTimeoutMs);
         config.setMaxConnectionsPerPartition(maxPoolSize);
         config.setPartitionCount(1);
         config.setUser(user);
         config.setPassword(passwd);
         doRetryOnConnPool = true;
         return new BoneCPDataSource(config);
      } else if ("dbcp".equals(connectionPooler)) {
         GenericObjectPool objectPool = new GenericObjectPool();
         objectPool.setMaxActive(maxPoolSize);
         objectPool.setMaxWait(getConnectionTimeoutMs);
         ConnectionFactory connFactory = new DriverManagerConnectionFactory(driverUrl, user, passwd);
         new PoolableConnectionFactory(connFactory, objectPool, (KeyedObjectPoolFactory)null, (String)null, false, true);
         return new PoolingDataSource(objectPool);
      } else if ("hikaricp".equals(connectionPooler)) {
         HikariConfig config = new HikariConfig();
         config.setMaximumPoolSize(maxPoolSize);
         config.setJdbcUrl(driverUrl);
         config.setUsername(user);
         config.setPassword(passwd);
         config.setConnectionTimeout(getConnectionTimeoutMs);
         return new HikariDataSource(config);
      } else if ("none".equals(connectionPooler)) {
         LOG.info("Choosing not to pool JDBC connections");
         return new NoPoolConnectionPool(conf);
      } else {
         throw new RuntimeException("Unknown JDBC connection pooling " + connectionPooler);
      }
   }

   private static synchronized void buildJumpTable() {
      if (jumpTable == null) {
         jumpTable = new HashMap(3);
         Map<LockType, Map<LockState, LockAction>> m = new HashMap(3);
         jumpTable.put(LockType.SHARED_READ, m);
         Map<LockState, LockAction> m2 = new HashMap(2);
         m.put(LockType.SHARED_READ, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.ACQUIRE);
         m2.put(LockState.WAITING, TxnHandler.LockAction.KEEP_LOOKING);
         m2 = new HashMap(2);
         m.put(LockType.SHARED_WRITE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.ACQUIRE);
         m2.put(LockState.WAITING, TxnHandler.LockAction.KEEP_LOOKING);
         m2 = new HashMap(2);
         m.put(LockType.EXCLUSIVE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
         m = new HashMap(3);
         jumpTable.put(LockType.SHARED_WRITE, m);
         m2 = new HashMap(2);
         m.put(LockType.SHARED_READ, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.KEEP_LOOKING);
         m2.put(LockState.WAITING, TxnHandler.LockAction.KEEP_LOOKING);
         m2 = new HashMap(2);
         m.put(LockType.SHARED_WRITE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
         m2 = new HashMap(2);
         m.put(LockType.EXCLUSIVE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
         m = new HashMap(3);
         jumpTable.put(LockType.EXCLUSIVE, m);
         m2 = new HashMap(2);
         m.put(LockType.SHARED_READ, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
         m2 = new HashMap(2);
         m.put(LockType.SHARED_WRITE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
         m2 = new HashMap(2);
         m.put(LockType.EXCLUSIVE, m2);
         m2.put(LockState.ACQUIRED, TxnHandler.LockAction.WAIT);
         m2.put(LockState.WAITING, TxnHandler.LockAction.WAIT);
      }
   }

   static boolean isRetryable(HiveConf conf, Exception ex) {
      if (ex instanceof SQLException) {
         SQLException sqlException = (SQLException)ex;
         if ("08S01".equalsIgnoreCase(sqlException.getSQLState())) {
            return true;
         }

         if ("ORA-08176".equalsIgnoreCase(sqlException.getSQLState()) || sqlException.getMessage().contains("consistent read failure; rollback data not available")) {
            return true;
         }

         String regex = HiveConf.getVar(conf, ConfVars.HIVE_TXN_RETRYABLE_SQLEX_REGEX);
         if (regex != null && !regex.isEmpty()) {
            String[] patterns = regex.split(",(?=\\S)");
            String message = getMessage((SQLException)ex);

            for(String p : patterns) {
               if (Pattern.matches(p, message)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean isDuplicateKeyError(SQLException ex) {
      switch (dbProduct) {
         case DERBY:
            if ("23505".equals(ex.getSQLState())) {
               return true;
            }
            break;
         case MYSQL:
            if ((ex.getErrorCode() == 1022 || ex.getErrorCode() == 1062 || ex.getErrorCode() == 1586) && "23000".equals(ex.getSQLState())) {
               return true;
            }
            break;
         case POSTGRES:
            if ("23505".equals(ex.getSQLState())) {
               return true;
            }
            break;
         case SQLSERVER:
            if (ex.getErrorCode() == 2627 && "23000".equals(ex.getSQLState())) {
               return true;
            }
            break;
         case ORACLE:
            if (ex.getErrorCode() == 1 && "23000".equals(ex.getSQLState())) {
               return true;
            }
            break;
         default:
            throw new IllegalArgumentException("Unexpected DB type: " + dbProduct + "; " + getMessage(ex));
      }

      return false;
   }

   private static String getMessage(SQLException ex) {
      return ex.getMessage() + " (SQLState=" + ex.getSQLState() + ", ErrorCode=" + ex.getErrorCode() + ")";
   }

   private static String valueOrNullLiteral(String value) {
      return value == null ? "null" : quoteString(value);
   }

   static String quoteString(String input) {
      return "'" + input + "'";
   }

   static String quoteChar(char c) {
      return "'" + c + "'";
   }

   static CompactionType dbCompactionType2ThriftType(char dbValue) {
      switch (dbValue) {
         case 'a':
            return CompactionType.MAJOR;
         case 'i':
            return CompactionType.MINOR;
         default:
            LOG.warn("Unexpected compaction type " + dbValue);
            return null;
      }
   }

   static Character thriftCompactionType2DbType(CompactionType ct) {
      switch (ct) {
         case MAJOR:
            return 'a';
         case MINOR:
            return 'i';
         default:
            LOG.warn("Unexpected compaction type " + ct);
            return null;
      }
   }

   private void lockInternal() {
      if (dbProduct == DatabaseProduct.DERBY) {
         derbyLock.lock();
      }

   }

   private void unlockInternal() {
      if (dbProduct == DatabaseProduct.DERBY) {
         derbyLock.unlock();
      }

   }

   @Idempotent
   public TxnStore.MutexAPI getMutexAPI() {
      return this;
   }

   public TxnStore.MutexAPI.LockHandle acquireLock(String key) throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         LockHandleImpl var7;
         try {
            String sqlStmt = sqlGenerator.addForUpdateClause("select MT_COMMENT from AUX_TABLE where MT_KEY1=" + quoteString(key) + " and MT_KEY2=0");
            this.lockInternal();
            dbConn = this.getDbConn(2, connPoolMutex);
            stmt = dbConn.createStatement();
            if (LOG.isDebugEnabled()) {
               LOG.debug("About to execute SQL: " + sqlStmt);
            }

            rs = stmt.executeQuery(sqlStmt);
            if (!rs.next()) {
               close(rs);

               try {
                  stmt.executeUpdate("insert into AUX_TABLE(MT_KEY1,MT_KEY2) values(" + quoteString(key) + ", 0)");
                  dbConn.commit();
               } catch (SQLException ex) {
                  if (!this.isDuplicateKeyError(ex)) {
                     throw new RuntimeException("Unable to lock " + quoteString(key) + " due to: " + getMessage(ex), ex);
                  }

                  dbConn.rollback();
               }

               rs = stmt.executeQuery(sqlStmt);
               if (!rs.next()) {
                  throw new IllegalStateException("Unable to lock " + quoteString(key) + ".  Expected row in AUX_TABLE is missing.");
               }
            }

            Semaphore derbySemaphore = null;
            if (dbProduct == DatabaseProduct.DERBY) {
               derbyKey2Lock.putIfAbsent(key, new Semaphore(1));
               derbySemaphore = (Semaphore)derbyKey2Lock.get(key);
               derbySemaphore.acquire();
            }

            LOG.debug(quoteString(key) + " locked by " + quoteString(hostname));
            var7 = new LockHandleImpl(dbConn, stmt, rs, key, derbySemaphore);
         } catch (SQLException ex) {
            rollbackDBConn(dbConn);
            close(rs, stmt, dbConn);
            this.checkRetryable(dbConn, ex, "acquireLock(" + key + ")");
            throw new MetaException("Unable to lock " + quoteString(key) + " due to: " + getMessage(ex) + "; " + StringUtils.stringifyException(ex));
         } catch (InterruptedException ex) {
            rollbackDBConn(dbConn);
            close(rs, stmt, dbConn);
            throw new MetaException("Unable to lock " + quoteString(key) + " due to: " + ex.getMessage() + StringUtils.stringifyException(ex));
         } finally {
            this.unlockInternal();
         }

         return var7;
      } catch (RetryException var18) {
         return this.acquireLock(key);
      }
   }

   public void acquireLock(String key, TxnStore.MutexAPI.LockHandle handle) {
      throw new NotImplementedException();
   }

   private static String getMetastoreJdbcUser(HiveConf conf) {
      return conf.getVar(ConfVars.METASTORE_CONNECTION_USER_NAME);
   }

   private static String getMetastoreJdbcPasswd(HiveConf conf) throws SQLException {
      try {
         return ShimLoader.getHadoopShims().getPassword(conf, ConfVars.METASTOREPWD.varname);
      } catch (IOException err) {
         throw new SQLException("Error getting metastore password", err);
      }
   }

   static enum TxnStatus {
      OPEN,
      ABORTED,
      COMMITTED,
      UNKNOWN;
   }

   private static enum OpertaionType {
      SELECT('s'),
      INSERT('i'),
      UPDATE('u'),
      DELETE('d');

      private final char sqlConst;

      private OpertaionType(char sqlConst) {
         this.sqlConst = sqlConst;
      }

      public String toString() {
         return Character.toString(this.sqlConst);
      }

      public static OpertaionType fromString(char sqlConst) {
         switch (sqlConst) {
            case 'd':
               return DELETE;
            case 'i':
               return INSERT;
            case 's':
               return SELECT;
            case 'u':
               return UPDATE;
            default:
               throw new IllegalArgumentException(TxnHandler.quoteChar(sqlConst));
         }
      }

      public static OpertaionType fromDataOperationType(DataOperationType dop) {
         switch (dop) {
            case SELECT:
               return SELECT;
            case INSERT:
               return INSERT;
            case UPDATE:
               return UPDATE;
            case DELETE:
               return DELETE;
            default:
               throw new IllegalArgumentException("Unexpected value: " + dop);
         }
      }
   }

   private static final class ConnectionLockIdPair {
      private final Connection dbConn;
      private final long extLockId;

      private ConnectionLockIdPair(Connection dbConn, long extLockId) {
         this.dbConn = dbConn;
         this.extLockId = extLockId;
      }
   }

   private static class LockInfoExt extends LockInfo {
      private final ShowLocksResponseElement e;

      LockInfoExt(ShowLocksResponseElement e) {
         super(e);
         this.e = e;
      }
   }

   protected class RetryException extends Exception {
   }

   private static class LockInfo {
      private final long extLockId;
      private final long intLockId;
      private final long txnId;
      private final String db;
      private final String table;
      private final String partition;
      private final LockState state;
      private final LockType type;

      LockInfo(ResultSet rs) throws SQLException, MetaException {
         this.extLockId = rs.getLong("hl_lock_ext_id");
         this.intLockId = rs.getLong("hl_lock_int_id");
         this.db = rs.getString("hl_db");
         String t = rs.getString("hl_table");
         this.table = rs.wasNull() ? null : t;
         String p = rs.getString("hl_partition");
         this.partition = rs.wasNull() ? null : p;
         switch (rs.getString("hl_lock_state").charAt(0)) {
            case 'a':
               this.state = LockState.ACQUIRED;
               break;
            case 'w':
               this.state = LockState.WAITING;
               break;
            default:
               throw new MetaException("Unknown lock state " + rs.getString("hl_lock_state").charAt(0));
         }

         switch (rs.getString("hl_lock_type").charAt(0)) {
            case 'e':
               this.type = LockType.EXCLUSIVE;
               break;
            case 'r':
               this.type = LockType.SHARED_READ;
               break;
            case 'w':
               this.type = LockType.SHARED_WRITE;
               break;
            default:
               throw new MetaException("Unknown lock type " + rs.getString("hl_lock_type").charAt(0));
         }

         this.txnId = rs.getLong("hl_txnid");
      }

      LockInfo(ShowLocksResponseElement e) {
         this.extLockId = e.getLockid();
         this.intLockId = e.getLockIdInternal();
         this.txnId = e.getTxnid();
         this.db = e.getDbname();
         this.table = e.getTablename();
         this.partition = e.getPartname();
         this.state = e.getState();
         this.type = e.getType();
      }

      public boolean equals(Object other) {
         if (!(other instanceof LockInfo)) {
            return false;
         } else {
            LockInfo o = (LockInfo)other;
            return this.extLockId == o.extLockId && this.intLockId == o.intLockId;
         }
      }

      public String toString() {
         return JavaUtils.lockIdToString(this.extLockId) + " intLockId:" + this.intLockId + " " + JavaUtils.txnIdToString(this.txnId) + " db:" + this.db + " table:" + this.table + " partition:" + this.partition + " state:" + (this.state == null ? "null" : this.state.toString()) + " type:" + (this.type == null ? "null" : this.type.toString());
      }

      private boolean isDbLock() {
         return this.db != null && this.table == null && this.partition == null;
      }

      private boolean isTableLock() {
         return this.db != null && this.table != null && this.partition == null;
      }

      private boolean isPartitionLock() {
         return !this.isDbLock() && !this.isTableLock();
      }
   }

   private static class LockInfoComparator implements Comparator {
      private static final LockTypeComparator lockTypeComparator = new LockTypeComparator();

      private LockInfoComparator() {
      }

      public boolean equals(Object other) {
         return this == other;
      }

      public int compare(LockInfo info1, LockInfo info2) {
         if (info1.state == LockState.ACQUIRED && info2.state != LockState.ACQUIRED) {
            return -1;
         } else if (info1.state != LockState.ACQUIRED && info2.state == LockState.ACQUIRED) {
            return 1;
         } else {
            int sortByType = lockTypeComparator.compare(info1.type, info2.type);
            if (sortByType != 0) {
               return sortByType;
            } else if (info1.extLockId < info2.extLockId) {
               return -1;
            } else if (info1.extLockId > info2.extLockId) {
               return 1;
            } else if (info1.intLockId < info2.intLockId) {
               return -1;
            } else {
               return info1.intLockId > info2.intLockId ? 1 : 0;
            }
         }
      }
   }

   private static final class LockTypeComparator implements Comparator {
      private LockTypeComparator() {
      }

      public boolean equals(Object other) {
         return this == other;
      }

      public int compare(LockType t1, LockType t2) {
         switch (t1) {
            case EXCLUSIVE:
               if (t2 == LockType.EXCLUSIVE) {
                  return 0;
               }

               return 1;
            case SHARED_READ:
               if (t2 == LockType.SHARED_READ) {
                  return 0;
               }

               return -1;
            case SHARED_WRITE:
               switch (t2) {
                  case EXCLUSIVE:
                     return -1;
                  case SHARED_READ:
                     return 1;
                  case SHARED_WRITE:
                     return 0;
                  default:
                     throw new RuntimeException("Unexpected LockType: " + t2);
               }
            default:
               throw new RuntimeException("Unexpected LockType: " + t1);
         }
      }
   }

   private static enum LockAction {
      ACQUIRE,
      WAIT,
      KEEP_LOOKING;
   }

   private static final class LockHandleImpl implements TxnStore.MutexAPI.LockHandle {
      private final Connection dbConn;
      private final Statement stmt;
      private final ResultSet rs;
      private final Semaphore derbySemaphore;
      private final List keys = new ArrayList();

      LockHandleImpl(Connection conn, Statement stmt, ResultSet rs, String key, Semaphore derbySemaphore) {
         this.dbConn = conn;
         this.stmt = stmt;
         this.rs = rs;
         this.derbySemaphore = derbySemaphore;

         assert derbySemaphore == null || derbySemaphore.availablePermits() == 0 : "Expected locked Semaphore";

         this.keys.add(key);
      }

      void addKey(String key) {
         throw new NotImplementedException();
      }

      public void releaseLocks() {
         TxnHandler.rollbackDBConn(this.dbConn);
         TxnHandler.close(this.rs, this.stmt, this.dbConn);
         if (this.derbySemaphore != null) {
            this.derbySemaphore.release();
         }

         for(String key : this.keys) {
            TxnHandler.LOG.debug(TxnHandler.quoteString(key) + " unlocked by " + TxnHandler.quoteString(TxnHandler.hostname));
         }

      }
   }

   @VisibleForTesting
   static final class SQLGenerator {
      private final DatabaseProduct dbProduct;
      private final HiveConf conf;

      SQLGenerator(DatabaseProduct dbProduct, HiveConf conf) {
         this.dbProduct = dbProduct;
         this.conf = conf;
      }

      List createInsertValuesStmt(String tblColumns, List rows) {
         if (rows != null && rows.size() != 0) {
            List<String> insertStmts = new ArrayList();
            StringBuilder sb = new StringBuilder();
            switch (this.dbProduct) {
               case ORACLE:
                  if (rows.size() > 1) {
                     for(int numRows = 0; numRows < rows.size(); ++numRows) {
                        if (numRows % this.conf.getIntVar(ConfVars.METASTORE_DIRECT_SQL_MAX_ELEMENTS_VALUES_CLAUSE) == 0) {
                           if (numRows > 0) {
                              sb.append(" select * from dual");
                              insertStmts.add(sb.toString());
                           }

                           sb.setLength(0);
                           sb.append("insert all ");
                        }

                        sb.append("into ").append(tblColumns).append(" values(").append((String)rows.get(numRows)).append(") ");
                     }

                     sb.append("select * from dual");
                     insertStmts.add(sb.toString());
                     return insertStmts;
                  }
               case DERBY:
               case MYSQL:
               case POSTGRES:
               case SQLSERVER:
                  for(int numRows = 0; numRows < rows.size(); ++numRows) {
                     if (numRows % this.conf.getIntVar(ConfVars.METASTORE_DIRECT_SQL_MAX_ELEMENTS_VALUES_CLAUSE) == 0) {
                        if (numRows > 0) {
                           insertStmts.add(sb.substring(0, sb.length() - 1));
                        }

                        sb.setLength(0);
                        sb.append("insert into ").append(tblColumns).append(" values");
                     }

                     sb.append('(').append((String)rows.get(numRows)).append("),");
                  }

                  insertStmts.add(sb.substring(0, sb.length() - 1));
                  return insertStmts;
               default:
                  String msg = "Unrecognized database product name <" + this.dbProduct + ">";
                  TxnHandler.LOG.error(msg);
                  throw new IllegalStateException(msg);
            }
         } else {
            return Collections.emptyList();
         }
      }

      String addForUpdateClause(String selectStatement) throws MetaException {
         switch (this.dbProduct) {
            case DERBY:
               return selectStatement;
            case MYSQL:
            case POSTGRES:
            case ORACLE:
               return selectStatement + " for update";
            case SQLSERVER:
               String modifier = " with (updlock)";
               int wherePos = selectStatement.toUpperCase().indexOf(" WHERE ");
               if (wherePos < 0) {
                  return selectStatement + modifier;
               }

               return selectStatement.substring(0, wherePos) + modifier + selectStatement.substring(wherePos, selectStatement.length());
            default:
               String msg = "Unrecognized database product name <" + this.dbProduct + ">";
               TxnHandler.LOG.error(msg);
               throw new MetaException(msg);
         }
      }

      private String addLimitClause(int numRows, String noSelectsqlQuery) throws MetaException {
         switch (this.dbProduct) {
            case DERBY:
               return "select " + noSelectsqlQuery + " fetch first " + numRows + " rows only";
            case MYSQL:
            case POSTGRES:
               return "select " + noSelectsqlQuery + " limit " + numRows;
            case SQLSERVER:
               return "select TOP(" + numRows + ") " + noSelectsqlQuery;
            case ORACLE:
               return "select * from (select " + noSelectsqlQuery + ") where rownum <= " + numRows;
            default:
               String msg = "Unrecognized database product name <" + this.dbProduct + ">";
               TxnHandler.LOG.error(msg);
               throw new MetaException(msg);
         }
      }
   }

   private static class NoPoolConnectionPool implements DataSource {
      private final HiveConf conf;
      private Driver driver;
      private String connString;
      private String user;
      private String passwd;

      public NoPoolConnectionPool(HiveConf conf) {
         this.conf = conf;
      }

      public Connection getConnection() throws SQLException {
         if (this.user == null) {
            this.user = TxnHandler.getMetastoreJdbcUser(this.conf);
            this.passwd = TxnHandler.getMetastoreJdbcPasswd(this.conf);
         }

         return this.getConnection(this.user, this.passwd);
      }

      public Connection getConnection(String username, String password) throws SQLException {
         if (this.driver == null) {
            String driverName = this.conf.getVar(ConfVars.METASTORE_CONNECTION_DRIVER);
            if (driverName == null || driverName.equals("")) {
               String msg = "JDBC driver for transaction db not set in configuration file, need to set " + ConfVars.METASTORE_CONNECTION_DRIVER.varname;
               TxnHandler.LOG.error(msg);
               throw new RuntimeException(msg);
            }

            try {
               TxnHandler.LOG.info("Going to load JDBC driver " + driverName);
               this.driver = (Driver)Class.forName(driverName).newInstance();
            } catch (InstantiationException e) {
               throw new RuntimeException("Unable to instantiate driver " + driverName + ", " + e.getMessage(), e);
            } catch (IllegalAccessException e) {
               throw new RuntimeException("Unable to access driver " + driverName + ", " + e.getMessage(), e);
            } catch (ClassNotFoundException e) {
               throw new RuntimeException("Unable to find driver " + driverName + ", " + e.getMessage(), e);
            }

            this.connString = this.conf.getVar(ConfVars.METASTORECONNECTURLKEY);
         }

         try {
            TxnHandler.LOG.info("Connecting to transaction db with connection string " + this.connString);
            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", username);
            connectionProps.setProperty("password", password);
            Connection conn = this.driver.connect(this.connString, connectionProps);
            conn.setAutoCommit(false);
            return conn;
         } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to transaction manager using " + this.connString + ", " + e.getMessage(), e);
         }
      }

      public PrintWriter getLogWriter() throws SQLException {
         throw new UnsupportedOperationException();
      }

      public void setLogWriter(PrintWriter out) throws SQLException {
         throw new UnsupportedOperationException();
      }

      public void setLoginTimeout(int seconds) throws SQLException {
         throw new UnsupportedOperationException();
      }

      public int getLoginTimeout() throws SQLException {
         throw new UnsupportedOperationException();
      }

      public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
         throw new UnsupportedOperationException();
      }

      public Object unwrap(Class iface) throws SQLException {
         throw new UnsupportedOperationException();
      }

      public boolean isWrapperFor(Class iface) throws SQLException {
         throw new UnsupportedOperationException();
      }
   }
}
