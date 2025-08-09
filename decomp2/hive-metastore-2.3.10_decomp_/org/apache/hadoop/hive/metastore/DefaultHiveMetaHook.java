package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

public abstract class DefaultHiveMetaHook implements HiveMetaHook {
   public abstract void commitInsertTable(Table var1, boolean var2) throws MetaException;

   public abstract void preInsertTable(Table var1, boolean var2) throws MetaException;

   public abstract void rollbackInsertTable(Table var1, boolean var2) throws MetaException;
}
