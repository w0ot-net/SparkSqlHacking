package org.apache.hadoop.hive.metastore.events;

import java.util.Collections;
import java.util.Iterator;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public class PreDropPartitionEvent extends PreEventContext {
   private final Iterable partitions;
   private final Table table;
   private final boolean deleteData;

   public PreDropPartitionEvent(Table table, Partition partition, boolean deleteData, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.DROP_PARTITION, handler);
      this.partitions = Collections.singletonList(partition);
      this.table = table;
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
