package org.apache.hadoop.hive.metastore.events;

import java.util.List;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;

public class PreAlterPartitionEvent extends PreEventContext {
   private final String dbName;
   private final String tableName;
   private final List oldPartVals;
   private final Partition newPart;

   public PreAlterPartitionEvent(String dbName, String tableName, List oldPartVals, Partition newPart, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.ALTER_PARTITION, handler);
      this.dbName = dbName;
      this.tableName = tableName;
      this.oldPartVals = oldPartVals;
      this.newPart = newPart;
   }

   public String getDbName() {
      return this.dbName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public List getOldPartVals() {
      return this.oldPartVals;
   }

   public Partition getNewPartition() {
      return this.newPart;
   }
}
