package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Table;

public class DropTableEvent extends ListenerEvent {
   private final Table table;
   private final boolean deleteData;

   public DropTableEvent(Table table, boolean status, boolean deleteData, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
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
