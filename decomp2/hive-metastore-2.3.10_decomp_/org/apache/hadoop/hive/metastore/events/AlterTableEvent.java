package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Table;

public class AlterTableEvent extends ListenerEvent {
   private final Table newTable;
   private final Table oldTable;

   public AlterTableEvent(Table oldTable, Table newTable, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
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
