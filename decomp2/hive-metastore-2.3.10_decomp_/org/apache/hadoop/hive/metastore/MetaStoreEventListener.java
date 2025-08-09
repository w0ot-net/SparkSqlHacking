package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.events.AddIndexEvent;
import org.apache.hadoop.hive.metastore.events.AddPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterIndexEvent;
import org.apache.hadoop.hive.metastore.events.AlterPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterTableEvent;
import org.apache.hadoop.hive.metastore.events.ConfigChangeEvent;
import org.apache.hadoop.hive.metastore.events.CreateDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.CreateFunctionEvent;
import org.apache.hadoop.hive.metastore.events.CreateTableEvent;
import org.apache.hadoop.hive.metastore.events.DropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.DropFunctionEvent;
import org.apache.hadoop.hive.metastore.events.DropIndexEvent;
import org.apache.hadoop.hive.metastore.events.DropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.DropTableEvent;
import org.apache.hadoop.hive.metastore.events.InsertEvent;
import org.apache.hadoop.hive.metastore.events.LoadPartitionDoneEvent;

public abstract class MetaStoreEventListener implements Configurable {
   private Configuration conf;

   public MetaStoreEventListener(Configuration config) {
      this.conf = config;
   }

   public void onConfigChange(ConfigChangeEvent tableEvent) throws MetaException {
   }

   public void onCreateTable(CreateTableEvent tableEvent) throws MetaException {
   }

   public void onDropTable(DropTableEvent tableEvent) throws MetaException {
   }

   public void onAlterTable(AlterTableEvent tableEvent) throws MetaException {
   }

   public void onAddPartition(AddPartitionEvent partitionEvent) throws MetaException {
   }

   public void onDropPartition(DropPartitionEvent partitionEvent) throws MetaException {
   }

   public void onAlterPartition(AlterPartitionEvent partitionEvent) throws MetaException {
   }

   public void onCreateDatabase(CreateDatabaseEvent dbEvent) throws MetaException {
   }

   public void onDropDatabase(DropDatabaseEvent dbEvent) throws MetaException {
   }

   public void onLoadPartitionDone(LoadPartitionDoneEvent partSetDoneEvent) throws MetaException {
   }

   public void onAddIndex(AddIndexEvent indexEvent) throws MetaException {
   }

   public void onDropIndex(DropIndexEvent indexEvent) throws MetaException {
   }

   public void onAlterIndex(AlterIndexEvent indexEvent) throws MetaException {
   }

   public void onCreateFunction(CreateFunctionEvent fnEvent) throws MetaException {
   }

   public void onDropFunction(DropFunctionEvent fnEvent) throws MetaException {
   }

   public void onInsert(InsertEvent insertEvent) throws MetaException {
   }

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration config) {
      this.conf = config;
   }
}
