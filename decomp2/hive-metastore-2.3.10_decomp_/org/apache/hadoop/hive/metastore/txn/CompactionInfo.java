package org.apache.hadoop.hive.metastore.txn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.hadoop.hive.metastore.api.CompactionType;

public class CompactionInfo implements Comparable {
   public long id;
   public String dbname;
   public String tableName;
   public String partName;
   char state;
   public CompactionType type;
   String workerId;
   long start;
   public String runAs;
   public String properties;
   public boolean tooManyAborts;
   public long highestTxnId;
   byte[] metaInfo;
   String hadoopJobId;
   private String fullPartitionName;
   private String fullTableName;

   public CompactionInfo(String dbname, String tableName, String partName, CompactionType type) {
      this.tooManyAborts = false;
      this.fullPartitionName = null;
      this.fullTableName = null;
      this.dbname = dbname;
      this.tableName = tableName;
      this.partName = partName;
      this.type = type;
   }

   CompactionInfo(long id, String dbname, String tableName, String partName, char state) {
      this(dbname, tableName, partName, (CompactionType)null);
      this.id = id;
      this.state = state;
   }

   CompactionInfo() {
      this.tooManyAborts = false;
      this.fullPartitionName = null;
      this.fullTableName = null;
   }

   public String getFullPartitionName() {
      if (this.fullPartitionName == null) {
         StringBuilder buf = new StringBuilder(this.dbname);
         buf.append('.');
         buf.append(this.tableName);
         if (this.partName != null) {
            buf.append('.');
            buf.append(this.partName);
         }

         this.fullPartitionName = buf.toString();
      }

      return this.fullPartitionName;
   }

   public String getFullTableName() {
      if (this.fullTableName == null) {
         StringBuilder buf = new StringBuilder(this.dbname);
         buf.append('.');
         buf.append(this.tableName);
         this.fullTableName = buf.toString();
      }

      return this.fullTableName;
   }

   public boolean isMajorCompaction() {
      return CompactionType.MAJOR == this.type;
   }

   public int compareTo(CompactionInfo o) {
      return this.getFullPartitionName().compareTo(o.getFullPartitionName());
   }

   public String toString() {
      return "id:" + this.id + ",dbname:" + this.dbname + ",tableName:" + this.tableName + ",partName:" + this.partName + ",state:" + this.state + ",type:" + this.type + ",properties:" + this.properties + ",runAs:" + this.runAs + ",tooManyAborts:" + this.tooManyAborts + ",highestTxnId:" + this.highestTxnId;
   }

   static CompactionInfo loadFullFromCompactionQueue(ResultSet rs) throws SQLException {
      CompactionInfo fullCi = new CompactionInfo();
      fullCi.id = rs.getLong(1);
      fullCi.dbname = rs.getString(2);
      fullCi.tableName = rs.getString(3);
      fullCi.partName = rs.getString(4);
      fullCi.state = rs.getString(5).charAt(0);
      fullCi.type = TxnHandler.dbCompactionType2ThriftType(rs.getString(6).charAt(0));
      fullCi.properties = rs.getString(7);
      fullCi.workerId = rs.getString(8);
      fullCi.start = rs.getLong(9);
      fullCi.runAs = rs.getString(10);
      fullCi.highestTxnId = rs.getLong(11);
      fullCi.metaInfo = rs.getBytes(12);
      fullCi.hadoopJobId = rs.getString(13);
      return fullCi;
   }

   static void insertIntoCompletedCompactions(PreparedStatement pStmt, CompactionInfo ci, long endTime) throws SQLException {
      pStmt.setLong(1, ci.id);
      pStmt.setString(2, ci.dbname);
      pStmt.setString(3, ci.tableName);
      pStmt.setString(4, ci.partName);
      pStmt.setString(5, Character.toString(ci.state));
      pStmt.setString(6, Character.toString(TxnHandler.thriftCompactionType2DbType(ci.type)));
      pStmt.setString(7, ci.properties);
      pStmt.setString(8, ci.workerId);
      pStmt.setLong(9, ci.start);
      pStmt.setLong(10, endTime);
      pStmt.setString(11, ci.runAs);
      pStmt.setLong(12, ci.highestTxnId);
      pStmt.setBytes(13, ci.metaInfo);
      pStmt.setString(14, ci.hadoopJobId);
   }
}
