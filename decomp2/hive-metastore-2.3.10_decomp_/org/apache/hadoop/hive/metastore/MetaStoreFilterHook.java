package org.apache.hadoop.hive.metastore;

import java.util.List;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

@LimitedPrivate({"Apache Sentry (Incubating)"})
@Evolving
public interface MetaStoreFilterHook {
   List filterDatabases(List var1) throws MetaException;

   Database filterDatabase(Database var1) throws MetaException, NoSuchObjectException;

   List filterTableNames(String var1, List var2) throws MetaException;

   Table filterTable(Table var1) throws MetaException, NoSuchObjectException;

   List filterTables(List var1) throws MetaException;

   List filterPartitions(List var1) throws MetaException;

   List filterPartitionSpecs(List var1) throws MetaException;

   Partition filterPartition(Partition var1) throws MetaException, NoSuchObjectException;

   List filterPartitionNames(String var1, String var2, List var3) throws MetaException;

   Index filterIndex(Index var1) throws MetaException, NoSuchObjectException;

   List filterIndexNames(String var1, String var2, List var3) throws MetaException;

   List filterIndexes(List var1) throws MetaException;
}
