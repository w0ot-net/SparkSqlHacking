package org.apache.hadoop.hive.metastore.events;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;

public class AddPartitionEvent extends ListenerEvent {
   private final Table table;
   private final List partitions;
   private PartitionSpecProxy partitionSpecProxy;

   public AddPartitionEvent(Table table, List partitions, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.table = table;
      this.partitions = partitions;
      this.partitionSpecProxy = null;
   }

   public AddPartitionEvent(Table table, Partition partition, boolean status, HiveMetaStore.HMSHandler handler) {
      this(table, Arrays.asList(partition), status, handler);
   }

   public AddPartitionEvent(Table table, PartitionSpecProxy partitionSpec, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.table = table;
      this.partitions = null;
      this.partitionSpecProxy = partitionSpec;
   }

   public Table getTable() {
      return this.table;
   }

   public Iterator getPartitionIterator() {
      if (this.partitions != null) {
         return this.partitions.iterator();
      } else {
         return this.partitionSpecProxy == null ? null : this.partitionSpecProxy.getPartitionIterator();
      }
   }
}
