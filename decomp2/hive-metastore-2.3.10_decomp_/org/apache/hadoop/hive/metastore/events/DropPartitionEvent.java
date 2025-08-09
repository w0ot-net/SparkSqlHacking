package org.apache.hadoop.hive.metastore.events;

import java.util.Collections;
import java.util.Iterator;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public class DropPartitionEvent extends ListenerEvent {
   private final Table table;
   private final Iterable partitions;
   private final boolean deleteData;

   public DropPartitionEvent(Table table, Partition partition, boolean status, boolean deleteData, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.table = table;
      this.partitions = Collections.singletonList(partition);
      this.deleteData = deleteData;
   }

   public Iterator getPartitionIterator() {
      return this.partitions.iterator();
   }

   public Table getTable() {
      return this.table;
   }

   public boolean getDeleteData() {
      return this.deleteData;
   }
}
