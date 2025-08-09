package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;

public interface HiveMetaHookLoader {
   HiveMetaHook getHook(Table var1) throws MetaException;
}
