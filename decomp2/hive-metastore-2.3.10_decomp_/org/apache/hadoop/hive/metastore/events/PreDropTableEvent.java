package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Table;

public class PreDropTableEvent extends PreEventContext {
   private final Table table;
   private final boolean deleteData;

   public PreDropTableEvent(Table table, boolean deleteData, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.DROP_TABLE, handler);
      this.table = table;
      this.deleteData = deleteData;
   }

   public Table getTable() {
      return this.table;
   }

   public boolean getDeleteData() {
      return this.deleteData;
   }
}
