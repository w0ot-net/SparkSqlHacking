package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.events.AddPartitionEvent;
import org.apache.hadoop.hive.metastore.events.CreateDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.CreateTableEvent;
import org.apache.hadoop.hive.metastore.events.DropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.DropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.DropTableEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HMSMetricsListener extends MetaStoreEventListener {
   public static final Logger LOGGER = LoggerFactory.getLogger(HMSMetricsListener.class);
   private Metrics metrics;

   public HMSMetricsListener(Configuration config, Metrics metrics) {
      super(config);
      this.metrics = metrics;
   }

   public void onCreateDatabase(CreateDatabaseEvent dbEvent) throws MetaException {
      this.incrementCounterInternal("create_total_count_dbs");
   }

   public void onDropDatabase(DropDatabaseEvent dbEvent) throws MetaException {
      this.incrementCounterInternal("delete_total_count_dbs");
   }

   public void onCreateTable(CreateTableEvent tableEvent) throws MetaException {
      this.incrementCounterInternal("create_total_count_tables");
   }

   public void onDropTable(DropTableEvent tableEvent) throws MetaException {
      this.incrementCounterInternal("delete_total_count_tables");
   }

   public void onDropPartition(DropPartitionEvent partitionEvent) throws MetaException {
      this.incrementCounterInternal("delete_total_count_partitions");
   }

   public void onAddPartition(AddPartitionEvent partitionEvent) throws MetaException {
      this.incrementCounterInternal("create_total_count_partitions");
   }

   private void incrementCounterInternal(String name) {
      if (this.metrics != null) {
         this.metrics.incrementCounter(name);
      }

   }
}
