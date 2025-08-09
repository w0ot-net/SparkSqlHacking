package org.apache.hadoop.hive.metastore.events;

import java.util.Map;
import org.apache.hadoop.hive.metastore.HiveMetaStore;

public class PreLoadPartitionDoneEvent extends PreEventContext {
   private final String dbName;
   private final String tableName;
   private final Map partSpec;

   public PreLoadPartitionDoneEvent(String dbName, String tableName, Map partSpec, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.LOAD_PARTITION_DONE, handler);
      this.dbName = dbName;
      this.tableName = tableName;
      this.partSpec = partSpec;
   }

   public String getDbName() {
      return this.dbName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public Map getPartitionName() {
      return this.partSpec;
   }
}
