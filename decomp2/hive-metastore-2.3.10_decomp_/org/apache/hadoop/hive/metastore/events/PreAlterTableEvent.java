package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Table;

public class PreAlterTableEvent extends PreEventContext {
   private final Table newTable;
   private final Table oldTable;

   public PreAlterTableEvent(Table oldTable, Table newTable, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.ALTER_TABLE, handler);
      this.oldTable = oldTable;
      this.newTable = newTable;
   }

   public Table getOldTable() {
      return this.oldTable;
   }

   public Table getNewTable() {
      return this.newTable;
   }
}
