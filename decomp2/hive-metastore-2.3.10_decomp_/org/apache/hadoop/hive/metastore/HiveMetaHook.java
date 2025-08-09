package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

public interface HiveMetaHook {
   void preCreateTable(Table var1) throws MetaException;

   void rollbackCreateTable(Table var1) throws MetaException;

   void commitCreateTable(Table var1) throws MetaException;

   void preDropTable(Table var1) throws MetaException;

   void rollbackDropTable(Table var1) throws MetaException;

   void commitDropTable(Table var1, boolean var2) throws MetaException;
}
