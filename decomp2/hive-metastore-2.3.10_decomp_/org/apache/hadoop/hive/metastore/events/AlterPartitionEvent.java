package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public class AlterPartitionEvent extends ListenerEvent {
   private final Partition oldPart;
   private final Partition newPart;
   private final Table table;

   public AlterPartitionEvent(Partition oldPart, Partition newPart, Table table, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.oldPart = oldPart;
      this.newPart = newPart;
      this.table = table;
   }

   public Partition getOldPartition() {
      return this.oldPart;
   }

   public Partition getNewPartition() {
      return this.newPart;
   }

   public Table getTable() {
      return this.table;
   }
}
