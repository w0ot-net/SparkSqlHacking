package org.apache.hadoop.hive.metastore;

import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionSpec;
import org.apache.hadoop.hive.metastore.api.Table;

public class DefaultMetaStoreFilterHookImpl implements MetaStoreFilterHook {
   public DefaultMetaStoreFilterHookImpl(HiveConf conf) {
   }

   public List filterDatabases(List dbList) throws MetaException {
      return dbList;
   }

   public Database filterDatabase(Database dataBase) throws NoSuchObjectException {
      return dataBase;
   }

   public List filterTableNames(String dbName, List tableList) throws MetaException {
      return tableList;
   }

   public Table filterTable(Table table) throws NoSuchObjectException {
      return table;
   }

   public List filterTables(List tableList) throws MetaException {
      return tableList;
   }

   public List filterPartitions(List partitionList) throws MetaException {
      return partitionList;
   }

   public List filterPartitionSpecs(List partitionSpecList) throws MetaException {
      return partitionSpecList;
   }

   public Partition filterPartition(Partition partition) throws NoSuchObjectException {
      return partition;
   }

   public List filterPartitionNames(String dbName, String tblName, List partitionNames) throws MetaException {
      return partitionNames;
   }

   public Index filterIndex(Index index) throws NoSuchObjectException {
      return index;
   }

   public List filterIndexNames(String dbName, String tblName, List indexList) throws MetaException {
      return indexList;
   }

   public List filterIndexes(List indexeList) throws MetaException {
      return indexeList;
   }
}
