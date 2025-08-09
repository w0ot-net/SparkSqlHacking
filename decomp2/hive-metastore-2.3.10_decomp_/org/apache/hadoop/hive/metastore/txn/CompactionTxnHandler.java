package org.apache.hadoop.hive.metastore.txn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.hive.common.classification.RetrySemantics.CannotRetry;
import org.apache.hadoop.hive.common.classification.RetrySemantics.Idempotent;
import org.apache.hadoop.hive.common.classification.RetrySemantics.ReadOnly;
import org.apache.hadoop.hive.common.classification.RetrySemantics.SafeToRetry;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.CompactionType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CompactionTxnHandler extends TxnHandler {
   private static final String CLASS_NAME = CompactionTxnHandler.class.getName();
   private static final Logger LOG;

   public CompactionTxnHandler() {
   }

   @ReadOnly
   public Set findPotentialCompactions(int maxAborted) throws MetaException {
      Connection dbConn = null;
      Set<CompactionInfo> response = new HashSet();
      Statement stmt = null;
      ResultSet rs = null;

      try {
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select distinct ctc_database, ctc_table, ctc_partition from COMPLETED_TXN_COMPONENTS";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);

            while(rs.next()) {
               CompactionInfo info = new CompactionInfo();
               info.dbname = rs.getString(1);
               info.tableName = rs.getString(2);
               info.partName = rs.getString(3);
               response.add(info);
            }

            rs.close();
            s = "select tc_database, tc_table, tc_partition from TXNS, TXN_COMPONENTS where txn_id = tc_txnid and txn_state = 'a' group by tc_database, tc_table, tc_partition having count(*) > " + maxAborted;
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);

            while(rs.next()) {
               CompactionInfo info = new CompactionInfo();
               info.dbname = rs.getString(1);
               info.tableName = rs.getString(2);
               info.partName = rs.getString(3);
               info.tooManyAborts = true;
               response.add(info);
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
         } catch (SQLException e) {
            LOG.error("Unable to connect to transaction database " + e.getMessage());
            this.checkRetryable(dbConn, e, "findPotentialCompactions(maxAborted:" + maxAborted + ")");
         } finally {
            close(rs, stmt, dbConn);
         }

         return response;
      } catch (TxnHandler.RetryException var14) {
         return this.findPotentialCompactions(maxAborted);
      }
   }

   @Idempotent
   public void setRunAs(long cq_id, String user) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "update COMPACTION_QUEUE set cq_run_as = '" + user + "' where cq_id = " + cq_id;
            LOG.debug("Going to execute update <" + s + ">");
            int updCnt = stmt.executeUpdate(s);
            if (updCnt != 1) {
               LOG.error("Unable to set cq_run_as=" + user + " for compaction record with cq_id=" + cq_id + ".  updCnt=" + updCnt);
               LOG.debug("Going to rollback");
               dbConn.rollback();
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.error("Unable to update compaction queue, " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "setRunAs(cq_id:" + cq_id + ",user:" + user + ")");
         } finally {
            closeDbConn(dbConn);
            closeStmt(stmt);
         }
      } catch (TxnHandler.RetryException var14) {
         this.setRunAs(cq_id, user);
      }

   }

   @SafeToRetry
   public CompactionInfo findNextToCompact(String workerId) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         Statement updStmt = null;
         ResultSet rs = null;

         CompactionInfo info;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select cq_id, cq_database, cq_table, cq_partition, cq_type, cq_tblproperties from COMPACTION_QUEUE where cq_state = 'i'";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            if (rs.next()) {
               updStmt = dbConn.createStatement();

               do {
                  info = new CompactionInfo();
                  info.id = rs.getLong(1);
                  info.dbname = rs.getString(2);
                  info.tableName = rs.getString(3);
                  info.partName = rs.getString(4);
                  info.type = dbCompactionType2ThriftType(rs.getString(5).charAt(0));
                  info.properties = rs.getString(6);
                  long now = this.getDbTime(dbConn);
                  s = "update COMPACTION_QUEUE set cq_worker_id = '" + workerId + "', cq_start = " + now + ", cq_state = '" + 'w' + "' where cq_id = " + info.id + " AND cq_state='" + 'i' + "'";
                  LOG.debug("Going to execute update <" + s + ">");
                  int updCount = updStmt.executeUpdate(s);
                  if (updCount == 1) {
                     dbConn.commit();
                     CompactionInfo var22 = info;
                     return var22;
                  }

                  if (updCount != 0) {
                     LOG.error("Unable to set to cq_state=w for compaction record: " + info + ". updCnt=" + updCount + ".");
                     dbConn.rollback();
                     Object var11 = null;
                     return (CompactionInfo)var11;
                  }

                  LOG.debug("Another Worker picked up " + info);
               } while(rs.next());

               dbConn.rollback();
               info = null;
               return info;
            }

            LOG.debug("No compactions found ready to compact");
            dbConn.rollback();
            info = null;
         } catch (SQLException e) {
            LOG.error("Unable to select next element for compaction, " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "findNextToCompact(workerId:" + workerId + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(updStmt);
            close(rs, stmt, dbConn);
         }

         return info;
      } catch (TxnHandler.RetryException var18) {
         return this.findNextToCompact(workerId);
      }
   }

   @SafeToRetry
   public void markCompacted(CompactionInfo info) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "update COMPACTION_QUEUE set cq_state = 'r', cq_worker_id = null where cq_id = " + info.id;
            LOG.debug("Going to execute update <" + s + ">");
            int updCnt = stmt.executeUpdate(s);
            if (updCnt != 1) {
               LOG.error("Unable to set cq_state=r for compaction record: " + info + ". updCnt=" + updCnt);
               LOG.debug("Going to rollback");
               dbConn.rollback();
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.error("Unable to update compaction queue " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "markCompacted(" + info + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }
      } catch (TxnHandler.RetryException var12) {
         this.markCompacted(info);
      }

   }

   @ReadOnly
   public List findReadyToClean() throws MetaException {
      Connection dbConn = null;
      List<CompactionInfo> rc = new ArrayList();
      Statement stmt = null;
      ResultSet rs = null;

      try {
         Object var14;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select cq_id, cq_database, cq_table, cq_partition, cq_type, cq_run_as, cq_highest_txn_id from COMPACTION_QUEUE where cq_state = 'r'";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);

            while(rs.next()) {
               CompactionInfo info = new CompactionInfo();
               info.id = rs.getLong(1);
               info.dbname = rs.getString(2);
               info.tableName = rs.getString(3);
               info.partName = rs.getString(4);
               switch (rs.getString(5).charAt(0)) {
                  case 'a':
                     info.type = CompactionType.MAJOR;
                     break;
                  case 'i':
                     info.type = CompactionType.MINOR;
                     break;
                  default:
                     throw new MetaException("Unexpected compaction type " + rs.getString(5));
               }

               info.runAs = rs.getString(6);
               info.highestTxnId = rs.getLong(7);
               rc.add(info);
            }

            LOG.debug("Going to rollback");
            dbConn.rollback();
            var14 = rc;
         } catch (SQLException e) {
            LOG.error("Unable to select next element for cleaning, " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "findReadyToClean");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

         return (List)var14;
      } catch (TxnHandler.RetryException var13) {
         return this.findReadyToClean();
      }
   }

   @CannotRetry
   public void markCleaned(CompactionInfo info) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         PreparedStatement pStmt = null;
         ResultSet rs = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery("select CQ_ID, CQ_DATABASE, CQ_TABLE, CQ_PARTITION, CQ_STATE, CQ_TYPE, CQ_TBLPROPERTIES, CQ_WORKER_ID, CQ_START, CQ_RUN_AS, CQ_HIGHEST_TXN_ID, CQ_META_INFO, CQ_HADOOP_JOB_ID from COMPACTION_QUEUE WHERE CQ_ID = " + info.id);
            if (!rs.next()) {
               throw new IllegalStateException("No record with CQ_ID=" + info.id + " found in COMPACTION_QUEUE");
            }

            info = CompactionInfo.loadFullFromCompactionQueue(rs);
            close(rs);
            String s = "delete from COMPACTION_QUEUE where cq_id = " + info.id;
            LOG.debug("Going to execute update <" + s + ">");
            int updCount = stmt.executeUpdate(s);
            if (updCount != 1) {
               LOG.error("Unable to delete compaction record: " + info + ".  Update count=" + updCount);
               LOG.debug("Going to rollback");
               dbConn.rollback();
            }

            pStmt = dbConn.prepareStatement("insert into COMPLETED_COMPACTIONS(CC_ID, CC_DATABASE, CC_TABLE, CC_PARTITION, CC_STATE, CC_TYPE, CC_TBLPROPERTIES, CC_WORKER_ID, CC_START, CC_END, CC_RUN_AS, CC_HIGHEST_TXN_ID, CC_META_INFO, CC_HADOOP_JOB_ID) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)");
            info.state = 's';
            CompactionInfo.insertIntoCompletedCompactions(pStmt, info, this.getDbTime(dbConn));
            updCount = pStmt.executeUpdate();
            s = "delete from COMPLETED_TXN_COMPONENTS where ctc_database = '" + info.dbname + "' and ctc_table = '" + info.tableName + "'";
            if (info.partName != null) {
               s = s + " and ctc_partition = '" + info.partName + "'";
            }

            if (info.highestTxnId != 0L) {
               s = s + " and ctc_txnid <= " + info.highestTxnId;
            }

            LOG.debug("Going to execute update <" + s + ">");
            if (stmt.executeUpdate(s) < 1) {
               LOG.error("Expected to remove at least one row from completed_txn_components when marking compaction entry as clean!");
            }

            s = "select distinct txn_id from TXNS, TXN_COMPONENTS where txn_id = tc_txnid and txn_state = 'a' and tc_database = '" + info.dbname + "' and tc_table = '" + info.tableName + "'" + (info.highestTxnId == 0L ? "" : " and txn_id <= " + info.highestTxnId);
            if (info.partName != null) {
               s = s + " and tc_partition = '" + info.partName + "'";
            }

            LOG.debug("Going to execute update <" + s + ">");
            rs = stmt.executeQuery(s);
            List<Long> txnids = new ArrayList();

            while(rs.next()) {
               txnids.add(rs.getLong(1));
            }

            if (txnids.size() > 0) {
               List<String> queries = new ArrayList();
               StringBuilder prefix = new StringBuilder();
               StringBuilder suffix = new StringBuilder();
               prefix.append("delete from TXN_COMPONENTS where ");
               suffix.append(" and tc_database = ");
               suffix.append(quoteString(info.dbname));
               suffix.append(" and tc_table = ");
               suffix.append(quoteString(info.tableName));
               if (info.partName != null) {
                  suffix.append(" and tc_partition = ");
                  suffix.append(quoteString(info.partName));
               }

               TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, txnids, "tc_txnid", true, false);

               for(String query : queries) {
                  LOG.debug("Going to execute update <" + query + ">");
                  int rc = stmt.executeUpdate(query);
                  LOG.debug("Removed " + rc + " records from txn_components");
               }
            }

            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.error("Unable to delete from compaction queue " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "markCleaned(" + info + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(pStmt);
            close(rs, stmt, dbConn);
         }
      } catch (TxnHandler.RetryException var21) {
         this.markCleaned(info);
      }

   }

   @SafeToRetry
   public void cleanEmptyAbortedTxns() throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         ResultSet rs = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "select txn_id from TXNS where txn_id not in (select tc_txnid from TXN_COMPONENTS) and txn_state = 'a'";
            LOG.debug("Going to execute query <" + s + ">");
            rs = stmt.executeQuery(s);
            List<Long> txnids = new ArrayList();

            while(rs.next()) {
               txnids.add(rs.getLong(1));
            }

            close(rs);
            if (txnids.size() > 0) {
               Collections.sort(txnids);
               List<String> queries = new ArrayList();
               StringBuilder prefix = new StringBuilder();
               StringBuilder suffix = new StringBuilder();
               prefix.append("delete from TXNS where ");
               suffix.append("");
               TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, txnids, "txn_id", false, false);

               for(String query : queries) {
                  LOG.debug("Going to execute update <" + query + ">");
                  int rc = stmt.executeUpdate(query);
                  LOG.info("Removed " + rc + "  empty Aborted transactions from TXNS");
               }

               LOG.info("Aborted transactions removed from TXNS: " + txnids);
               LOG.debug("Going to commit");
               dbConn.commit();
               return;
            }
         } catch (SQLException e) {
            LOG.error("Unable to delete from txns table " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "cleanEmptyAbortedTxns");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

      } catch (TxnHandler.RetryException var18) {
         this.cleanEmptyAbortedTxns();
      }
   }

   @Idempotent
   public void revokeFromLocalWorkers(String hostname) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "update COMPACTION_QUEUE set cq_worker_id = null, cq_start = null, cq_state = 'i' where cq_state = 'w' and cq_worker_id like '" + hostname + "%'";
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.error("Unable to change dead worker's records back to initiated state " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "revokeFromLocalWorkers(hostname:" + hostname + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }
      } catch (TxnHandler.RetryException var11) {
         this.revokeFromLocalWorkers(hostname);
      }

   }

   @Idempotent
   public void revokeTimedoutWorkers(long timeout) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            long latestValidStart = this.getDbTime(dbConn) - timeout;
            stmt = dbConn.createStatement();
            String s = "update COMPACTION_QUEUE set cq_worker_id = null, cq_start = null, cq_state = 'i' where cq_state = 'w' and cq_start < " + latestValidStart;
            LOG.debug("Going to execute update <" + s + ">");
            stmt.executeUpdate(s);
            LOG.debug("Going to commit");
            dbConn.commit();
         } catch (SQLException e) {
            LOG.error("Unable to change dead worker's records back to initiated state " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "revokeTimedoutWorkers(timeout:" + timeout + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            closeStmt(stmt);
            closeDbConn(dbConn);
         }
      } catch (TxnHandler.RetryException var14) {
         this.revokeTimedoutWorkers(timeout);
      }

   }

   @ReadOnly
   public List findColumnsWithStats(CompactionInfo ci) throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         Object var9;
         try {
            dbConn = this.getDbConn(2);
            String quote = this.getIdentifierQuoteString(dbConn);
            stmt = dbConn.createStatement();
            StringBuilder bldr = new StringBuilder();
            bldr.append("SELECT ").append(quote).append("COLUMN_NAME").append(quote).append(" FROM ").append(quote).append(ci.partName == null ? "TAB_COL_STATS" : "PART_COL_STATS").append(quote).append(" WHERE ").append(quote).append("DB_NAME").append(quote).append(" = '").append(ci.dbname).append("' AND ").append(quote).append("TABLE_NAME").append(quote).append(" = '").append(ci.tableName).append("'");
            if (ci.partName != null) {
               bldr.append(" AND ").append(quote).append("PARTITION_NAME").append(quote).append(" = '").append(ci.partName).append("'");
            }

            String s = bldr.toString();
            LOG.debug("Going to execute <" + s + ">");
            rs = stmt.executeQuery(s);
            List<String> columns = new ArrayList();

            while(rs.next()) {
               columns.add(rs.getString(1));
            }

            LOG.debug("Found columns to update stats: " + columns + " on " + ci.tableName + (ci.partName == null ? "" : "/" + ci.partName));
            dbConn.commit();
            var9 = columns;
         } catch (SQLException e) {
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "findColumnsWithStats(" + ci.tableName + (ci.partName == null ? "" : "/" + ci.partName) + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

         return (List)var9;
      } catch (TxnHandler.RetryException var16) {
         return this.findColumnsWithStats(ci);
      }
   }

   @Idempotent
   public void setCompactionHighestTxnId(CompactionInfo ci, long highestTxnId) throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;

      try {
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            int updCount = stmt.executeUpdate("UPDATE COMPACTION_QUEUE SET CQ_HIGHEST_TXN_ID = " + highestTxnId + " WHERE CQ_ID = " + ci.id);
            if (updCount != 1) {
               throw new IllegalStateException("Could not find record in COMPACTION_QUEUE for " + ci);
            }

            dbConn.commit();
         } catch (SQLException e) {
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "setCompactionHighestTxnId(" + ci + "," + highestTxnId + ")");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            close((ResultSet)null, stmt, dbConn);
         }
      } catch (TxnHandler.RetryException var13) {
         this.setCompactionHighestTxnId(ci, highestTxnId);
      }

   }

   private void checkForDeletion(List deleteSet, CompactionInfo ci, RetentionCounters rc) {
      switch (ci.state) {
         case 'a':
            if (--rc.attemptedRetention < 0) {
               deleteSet.add(ci.id);
            }
            break;
         case 'f':
            if (--rc.failedRetention < 0) {
               deleteSet.add(ci.id);
            }
            break;
         case 's':
            if (--rc.succeededRetention < 0) {
               deleteSet.add(ci.id);
            }
      }

   }

   @SafeToRetry
   public void purgeCompactionHistory() throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;
      List<Long> deleteSet = new ArrayList();
      RetentionCounters rc = null;

      try {
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery("select cc_id, cc_database, cc_table, cc_partition, cc_state from COMPLETED_COMPACTIONS order by cc_database, cc_table, cc_partition, cc_id desc");

            CompactionInfo ci;
            for(String lastCompactedEntity = null; rs.next(); this.checkForDeletion(deleteSet, ci, rc)) {
               ci = new CompactionInfo(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5).charAt(0));
               if (!ci.getFullPartitionName().equals(lastCompactedEntity)) {
                  lastCompactedEntity = ci.getFullPartitionName();
                  rc = new RetentionCounters(this.conf.getIntVar(ConfVars.COMPACTOR_HISTORY_RETENTION_ATTEMPTED), this.getFailedCompactionRetention(), this.conf.getIntVar(ConfVars.COMPACTOR_HISTORY_RETENTION_SUCCEEDED));
               }
            }

            close(rs);
            if (deleteSet.size() > 0) {
               List<String> queries = new ArrayList();
               StringBuilder prefix = new StringBuilder();
               StringBuilder suffix = new StringBuilder();
               prefix.append("delete from COMPLETED_COMPACTIONS where ");
               suffix.append("");
               TxnUtils.buildQueryWithINClause(this.conf, queries, prefix, suffix, deleteSet, "cc_id", false, false);

               for(String query : queries) {
                  LOG.debug("Going to execute update <" + query + ">");
                  int count = stmt.executeUpdate(query);
                  LOG.debug("Removed " + count + " records from COMPLETED_COMPACTIONS");
               }

               dbConn.commit();
               return;
            }
         } catch (SQLException e) {
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "purgeCompactionHistory()");
            throw new MetaException("Unable to connect to transaction database " + StringUtils.stringifyException(e));
         } finally {
            close(rs, stmt, dbConn);
         }

      } catch (TxnHandler.RetryException var19) {
         this.purgeCompactionHistory();
      }
   }

   private int getFailedCompactionRetention() {
      int failedThreshold = this.conf.getIntVar(ConfVars.COMPACTOR_INITIATOR_FAILED_THRESHOLD);
      int failedRetention = this.conf.getIntVar(ConfVars.COMPACTOR_HISTORY_RETENTION_FAILED);
      if (failedRetention < failedThreshold) {
         LOG.warn("Invalid configuration " + ConfVars.COMPACTOR_INITIATOR_FAILED_THRESHOLD.varname + "=" + failedRetention + " < " + ConfVars.COMPACTOR_HISTORY_RETENTION_FAILED + "=" + failedRetention + ".  Will use " + ConfVars.COMPACTOR_INITIATOR_FAILED_THRESHOLD.varname + "=" + failedRetention);
         failedRetention = failedThreshold;
      }

      return failedRetention;
   }

   @ReadOnly
   public boolean checkFailedCompactions(CompactionInfo ci) throws MetaException {
      Connection dbConn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         int numTotal;
         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery("select CC_STATE from COMPLETED_COMPACTIONS where CC_DATABASE = " + quoteString(ci.dbname) + " and CC_TABLE = " + quoteString(ci.tableName) + (ci.partName != null ? "and CC_PARTITION = " + quoteString(ci.partName) : "") + " and CC_STATE != " + quoteChar('a') + " order by CC_ID desc");
            int numFailed = 0;
            numTotal = 0;
            int failedThreshold = this.conf.getIntVar(ConfVars.COMPACTOR_INITIATOR_FAILED_THRESHOLD);

            while(rs.next()) {
               ++numTotal;
               if (numTotal > failedThreshold) {
                  break;
               }

               if (rs.getString(1).charAt(0) == 'f') {
                  ++numFailed;
               } else {
                  --numFailed;
               }
            }

            boolean var8 = numFailed == failedThreshold;
            return var8;
         } catch (SQLException e) {
            LOG.error("Unable to delete from compaction queue " + e.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);
            this.checkRetryable(dbConn, e, "checkFailedCompactions(" + ci + ")");
            LOG.error("Unable to connect to transaction database " + StringUtils.stringifyException(e));
            numTotal = 0;
         } finally {
            close(rs, stmt, dbConn);
         }

         return (boolean)numTotal;
      } catch (TxnHandler.RetryException var15) {
         return this.checkFailedCompactions(ci);
      }
   }

   @CannotRetry
   public void markFailed(CompactionInfo ci) throws MetaException {
      try {
         Connection dbConn = null;
         Statement stmt = null;
         PreparedStatement pStmt = null;
         ResultSet rs = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery("select CQ_ID, CQ_DATABASE, CQ_TABLE, CQ_PARTITION, CQ_STATE, CQ_TYPE, CQ_TBLPROPERTIES, CQ_WORKER_ID, CQ_START, CQ_RUN_AS, CQ_HIGHEST_TXN_ID, CQ_META_INFO, CQ_HADOOP_JOB_ID from COMPACTION_QUEUE WHERE CQ_ID = " + ci.id);
            if (rs.next()) {
               ci = CompactionInfo.loadFullFromCompactionQueue(rs);
               String s = "delete from COMPACTION_QUEUE where cq_id = " + ci.id;
               LOG.debug("Going to execute update <" + s + ">");
               stmt.executeUpdate(s);
            } else if (ci.id > 0L) {
               throw new IllegalStateException("No record with CQ_ID=" + ci.id + " found in COMPACTION_QUEUE");
            }

            if (ci.id == 0L) {
               ci.id = this.generateCompactionQueueId(stmt);
               ci.state = 'a';
               if (ci.type == null) {
                  ci.type = CompactionType.MINOR;
               }

               ci.start = this.getDbTime(dbConn);
            } else {
               ci.state = 'f';
            }

            close(rs, stmt, (Connection)null);
            pStmt = dbConn.prepareStatement("insert into COMPLETED_COMPACTIONS(CC_ID, CC_DATABASE, CC_TABLE, CC_PARTITION, CC_STATE, CC_TYPE, CC_TBLPROPERTIES, CC_WORKER_ID, CC_START, CC_END, CC_RUN_AS, CC_HIGHEST_TXN_ID, CC_META_INFO, CC_HADOOP_JOB_ID) VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)");
            CompactionInfo.insertIntoCompletedCompactions(pStmt, ci, this.getDbTime(dbConn));
            int updCount = pStmt.executeUpdate();
            LOG.debug("Going to commit");
            closeStmt(pStmt);
            dbConn.commit();
         } catch (SQLException var14) {
            SQLException e = var14;
            LOG.warn("markFailed(" + ci.id + "):" + var14.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);

            try {
               this.checkRetryable(dbConn, e, "markFailed(" + ci + ")");
            } catch (MetaException ex) {
               LOG.error("Unable to connect to transaction database " + StringUtils.stringifyException(ex));
            }

            LOG.error("markFailed(" + ci + ") failed: " + var14.getMessage(), var14);
         } finally {
            close(rs, stmt, (Connection)null);
            close((ResultSet)null, pStmt, dbConn);
         }
      } catch (TxnHandler.RetryException var16) {
         this.markFailed(ci);
      }

   }

   @Idempotent
   public void setHadoopJobId(String hadoopJobId, long id) {
      try {
         Connection dbConn = null;
         Statement stmt = null;

         try {
            dbConn = this.getDbConn(2);
            stmt = dbConn.createStatement();
            String s = "update COMPACTION_QUEUE set CQ_HADOOP_JOB_ID = " + quoteString(hadoopJobId) + " WHERE CQ_ID = " + id;
            LOG.debug("Going to execute <" + s + ">");
            stmt.executeUpdate(s);
            LOG.debug("Going to commit");
            closeStmt(stmt);
            dbConn.commit();
         } catch (SQLException var14) {
            SQLException e = var14;
            LOG.warn("setHadoopJobId(" + hadoopJobId + "," + id + "):" + var14.getMessage());
            LOG.debug("Going to rollback");
            rollbackDBConn(dbConn);

            try {
               this.checkRetryable(dbConn, e, "setHadoopJobId(" + hadoopJobId + "," + id + ")");
            } catch (MetaException ex) {
               LOG.error("Unable to connect to transaction database " + StringUtils.stringifyException(ex));
            }

            LOG.error("setHadoopJobId(" + hadoopJobId + "," + id + ") failed: " + var14.getMessage(), var14);
         } finally {
            close((ResultSet)null, stmt, dbConn);
         }
      } catch (TxnHandler.RetryException var16) {
         this.setHadoopJobId(hadoopJobId, id);
      }

   }

   static {
      LOG = LoggerFactory.getLogger(CLASS_NAME);
   }

   private static class RetentionCounters {
      int attemptedRetention = 0;
      int failedRetention = 0;
      int succeededRetention = 0;

      RetentionCounters(int attemptedRetention, int failedRetention, int succeededRetention) {
         this.attemptedRetention = attemptedRetention;
         this.failedRetention = failedRetention;
         this.succeededRetention = succeededRetention;
      }
   }
}
