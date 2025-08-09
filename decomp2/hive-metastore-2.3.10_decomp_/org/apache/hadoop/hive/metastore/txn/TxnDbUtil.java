package org.apache.hadoop.hive.metastore.txn;

import com.google.common.annotations.VisibleForTesting;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TxnDbUtil {
   private static final Logger LOG = LoggerFactory.getLogger(TxnDbUtil.class.getName());
   private static final String TXN_MANAGER = "org.apache.hadoop.hive.ql.lockmgr.DbTxnManager";
   private static int deadlockCnt = 0;

   private TxnDbUtil() {
      throw new UnsupportedOperationException("Can't initialize class");
   }

   public static void setConfValues(HiveConf conf) {
      conf.setVar(ConfVars.HIVE_TXN_MANAGER, "org.apache.hadoop.hive.ql.lockmgr.DbTxnManager");
      conf.setBoolVar(ConfVars.HIVE_SUPPORT_CONCURRENCY, true);
   }

   public static void prepDb(HiveConf conf) throws Exception {
      Connection conn = null;
      Statement stmt = null;

      try {
         conn = getConnection(conf);
         stmt = conn.createStatement();
         stmt.execute("CREATE TABLE TXNS (  TXN_ID bigint PRIMARY KEY,  TXN_STATE char(1) NOT NULL,  TXN_STARTED bigint NOT NULL,  TXN_LAST_HEARTBEAT bigint NOT NULL,  TXN_USER varchar(128) NOT NULL,  TXN_HOST varchar(128) NOT NULL)");
         stmt.execute("CREATE TABLE TXN_COMPONENTS (  TC_TXNID bigint REFERENCES TXNS (TXN_ID),  TC_DATABASE varchar(128) NOT NULL,  TC_TABLE varchar(128),  TC_PARTITION varchar(767),  TC_OPERATION_TYPE char(1) NOT NULL)");
         stmt.execute("CREATE TABLE COMPLETED_TXN_COMPONENTS (  CTC_TXNID bigint,  CTC_DATABASE varchar(128) NOT NULL,  CTC_TABLE varchar(128),  CTC_PARTITION varchar(767))");
         stmt.execute("CREATE TABLE NEXT_TXN_ID (  NTXN_NEXT bigint NOT NULL)");
         stmt.execute("INSERT INTO NEXT_TXN_ID VALUES(1)");
         stmt.execute("CREATE TABLE HIVE_LOCKS ( HL_LOCK_EXT_ID bigint NOT NULL, HL_LOCK_INT_ID bigint NOT NULL, HL_TXNID bigint, HL_DB varchar(128) NOT NULL, HL_TABLE varchar(128), HL_PARTITION varchar(767), HL_LOCK_STATE char(1) NOT NULL, HL_LOCK_TYPE char(1) NOT NULL, HL_LAST_HEARTBEAT bigint NOT NULL, HL_ACQUIRED_AT bigint, HL_USER varchar(128) NOT NULL, HL_HOST varchar(128) NOT NULL, HL_HEARTBEAT_COUNT integer, HL_AGENT_INFO varchar(128), HL_BLOCKEDBY_EXT_ID bigint, HL_BLOCKEDBY_INT_ID bigint, PRIMARY KEY(HL_LOCK_EXT_ID, HL_LOCK_INT_ID))");
         stmt.execute("CREATE INDEX HL_TXNID_INDEX ON HIVE_LOCKS (HL_TXNID)");
         stmt.execute("CREATE TABLE NEXT_LOCK_ID ( NL_NEXT bigint NOT NULL)");
         stmt.execute("INSERT INTO NEXT_LOCK_ID VALUES(1)");
         stmt.execute("CREATE TABLE COMPACTION_QUEUE ( CQ_ID bigint PRIMARY KEY, CQ_DATABASE varchar(128) NOT NULL, CQ_TABLE varchar(128) NOT NULL, CQ_PARTITION varchar(767), CQ_STATE char(1) NOT NULL, CQ_TYPE char(1) NOT NULL, CQ_TBLPROPERTIES varchar(2048), CQ_WORKER_ID varchar(128), CQ_START bigint, CQ_RUN_AS varchar(128), CQ_HIGHEST_TXN_ID bigint, CQ_META_INFO varchar(2048) for bit data, CQ_HADOOP_JOB_ID varchar(32))");
         stmt.execute("CREATE TABLE NEXT_COMPACTION_QUEUE_ID (NCQ_NEXT bigint NOT NULL)");
         stmt.execute("INSERT INTO NEXT_COMPACTION_QUEUE_ID VALUES(1)");
         stmt.execute("CREATE TABLE COMPLETED_COMPACTIONS ( CC_ID bigint PRIMARY KEY, CC_DATABASE varchar(128) NOT NULL, CC_TABLE varchar(128) NOT NULL, CC_PARTITION varchar(767), CC_STATE char(1) NOT NULL, CC_TYPE char(1) NOT NULL, CC_TBLPROPERTIES varchar(2048), CC_WORKER_ID varchar(128), CC_START bigint, CC_END bigint, CC_RUN_AS varchar(128), CC_HIGHEST_TXN_ID bigint, CC_META_INFO varchar(2048) for bit data, CC_HADOOP_JOB_ID varchar(32))");
         stmt.execute("CREATE TABLE AUX_TABLE ( MT_KEY1 varchar(128) NOT NULL, MT_KEY2 bigint NOT NULL, MT_COMMENT varchar(255), PRIMARY KEY(MT_KEY1, MT_KEY2))");
         stmt.execute("CREATE TABLE WRITE_SET ( WS_DATABASE varchar(128) NOT NULL, WS_TABLE varchar(128) NOT NULL, WS_PARTITION varchar(767), WS_TXNID bigint NOT NULL, WS_COMMIT_ID bigint NOT NULL, WS_OPERATION_TYPE char(1) NOT NULL)");
      } catch (SQLException e) {
         try {
            conn.rollback();
         } catch (SQLException re) {
            LOG.error("Error rolling back: " + re.getMessage());
         }

         if (!(e instanceof SQLTransactionRollbackException) || deadlockCnt++ >= 5) {
            throw e;
         }

         LOG.warn("Caught deadlock, retrying db creation");
         prepDb(conf);
      } finally {
         deadlockCnt = 0;
         closeResources(conn, stmt, (ResultSet)null);
      }

   }

   public static void cleanDb(HiveConf conf) throws Exception {
      int retryCount = 0;

      boolean success;
      do {
         ++retryCount;
         if (retryCount > 3) {
            return;
         }

         success = true;
         Connection conn = null;
         Statement stmt = null;

         try {
            conn = getConnection(conf);
            stmt = conn.createStatement();

            try {
               stmt.execute("DROP INDEX HL_TXNID_INDEX");
            } catch (SQLException var9) {
               if (!"42X65".equals(var9.getSQLState()) || 30000 != var9.getErrorCode()) {
                  LOG.error("Unable to drop index HL_TXNID_INDEX " + var9.getMessage() + "State=" + var9.getSQLState() + " code=" + var9.getErrorCode() + " retryCount=" + retryCount);
                  success = false;
               }
            }

            success &= dropTable(stmt, "TXN_COMPONENTS", retryCount);
            success &= dropTable(stmt, "COMPLETED_TXN_COMPONENTS", retryCount);
            success &= dropTable(stmt, "TXNS", retryCount);
            success &= dropTable(stmt, "NEXT_TXN_ID", retryCount);
            success &= dropTable(stmt, "HIVE_LOCKS", retryCount);
            success &= dropTable(stmt, "NEXT_LOCK_ID", retryCount);
            success &= dropTable(stmt, "COMPACTION_QUEUE", retryCount);
            success &= dropTable(stmt, "NEXT_COMPACTION_QUEUE_ID", retryCount);
            success &= dropTable(stmt, "COMPLETED_COMPACTIONS", retryCount);
            success &= dropTable(stmt, "AUX_TABLE", retryCount);
            success &= dropTable(stmt, "WRITE_SET", retryCount);
         } finally {
            closeResources(conn, stmt, (ResultSet)null);
         }
      } while(!success);

   }

   private static boolean dropTable(Statement stmt, String name, int retryCount) throws SQLException {
      try {
         stmt.execute("DROP TABLE " + name);
         return true;
      } catch (SQLException var4) {
         if ("42Y55".equals(var4.getSQLState()) && 30000 == var4.getErrorCode()) {
            return true;
         } else {
            LOG.error("Unable to drop table " + name + ": " + var4.getMessage() + " State=" + var4.getSQLState() + " code=" + var4.getErrorCode() + " retryCount=" + retryCount);
            return false;
         }
      }
   }

   public static int countLockComponents(HiveConf conf, long lockId) throws Exception {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      int var6;
      try {
         conn = getConnection(conf);
         stmt = conn.prepareStatement("SELECT count(*) FROM hive_locks WHERE hl_lock_ext_id = ?");
         stmt.setLong(1, lockId);
         rs = stmt.executeQuery();
         if (rs.next()) {
            var6 = rs.getInt(1);
            return var6;
         }

         var6 = 0;
      } finally {
         closeResources(conn, stmt, rs);
      }

      return var6;
   }

   public static int countQueryAgent(HiveConf conf, String countQuery) throws Exception {
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      int var5;
      try {
         conn = getConnection(conf);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(countQuery);
         if (rs.next()) {
            var5 = rs.getInt(1);
            return var5;
         }

         var5 = 0;
      } finally {
         closeResources(conn, stmt, rs);
      }

      return var5;
   }

   @VisibleForTesting
   public static String queryToString(HiveConf conf, String query) throws Exception {
      return queryToString(conf, query, true);
   }

   public static String queryToString(HiveConf conf, String query, boolean includeHeader) throws Exception {
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      StringBuilder sb = new StringBuilder();

      try {
         conn = getConnection(conf);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(query);
         ResultSetMetaData rsmd = rs.getMetaData();
         if (includeHeader) {
            for(int colPos = 1; colPos <= rsmd.getColumnCount(); ++colPos) {
               sb.append(rsmd.getColumnName(colPos)).append("   ");
            }

            sb.append('\n');
         }

         while(rs.next()) {
            for(int colPos = 1; colPos <= rsmd.getColumnCount(); ++colPos) {
               sb.append(rs.getObject(colPos)).append("   ");
            }

            sb.append('\n');
         }
      } finally {
         closeResources(conn, stmt, rs);
      }

      return sb.toString();
   }

   static Connection getConnection(HiveConf conf) throws Exception {
      String jdbcDriver = HiveConf.getVar(conf, ConfVars.METASTORE_CONNECTION_DRIVER);
      Driver driver = (Driver)Class.forName(jdbcDriver).newInstance();
      Properties prop = new Properties();
      String driverUrl = HiveConf.getVar(conf, ConfVars.METASTORECONNECTURLKEY);
      String user = HiveConf.getVar(conf, ConfVars.METASTORE_CONNECTION_USER_NAME);
      String passwd = ShimLoader.getHadoopShims().getPassword(conf, ConfVars.METASTOREPWD.varname);
      prop.setProperty("user", user);
      prop.setProperty("password", passwd);
      Connection conn = driver.connect(driverUrl, prop);
      conn.setAutoCommit(true);
      return conn;
   }

   static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
      if (rs != null) {
         try {
            rs.close();
         } catch (SQLException e) {
            LOG.error("Error closing ResultSet: " + e.getMessage());
         }
      }

      if (stmt != null) {
         try {
            stmt.close();
         } catch (SQLException e) {
            System.err.println("Error closing Statement: " + e.getMessage());
         }
      }

      if (conn != null) {
         try {
            conn.rollback();
         } catch (SQLException e) {
            System.err.println("Error rolling back: " + e.getMessage());
         }

         try {
            conn.close();
         } catch (SQLException e) {
            System.err.println("Error closing Connection: " + e.getMessage());
         }
      }

   }
}
