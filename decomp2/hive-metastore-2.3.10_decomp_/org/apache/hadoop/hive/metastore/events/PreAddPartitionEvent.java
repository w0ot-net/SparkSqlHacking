package org.apache.hadoop.hive.metastore.events;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;

public class PreAddPartitionEvent extends PreEventContext {
   private final Table table;
   private final List partitions;
   private PartitionSpecProxy partitionSpecProxy;

   public PreAddPartitionEvent(Table table, List partitions, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.ADD_PARTITION, handler);
      this.table = table;
      this.partitions = partitions;
      this.partitionSpecProxy = null;
   }

   public PreAddPartitionEvent(Table table, Partition partition, HiveMetaStore.HMSHandler handler) {
      this(table, Arrays.asList(partition), handler);
   }

   public PreAddPartitionEvent(Table table, PartitionSpecProxy partitionSpecProxy, HiveMetaStore.HMSHandler handler) {
      this(table, (List)null, handler);
      this.partitionSpecProxy = partitionSpecProxy;
   }

   public List getPartitions() {
      return this.partitions;
   }

   public Table getTable() {
      return this.table;
   }

   public Iterator getPartitionIterator() {
      return this.partitionSpecProxy == null ? null : this.partitionSpecProxy.getPartitionIterator();
   }
}
