package org.apache.hadoop.hive.metastore;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.MetaException;

public interface MetaStoreThread {
   void setHiveConf(HiveConf var1);

   void setThreadId(int var1);

   void init(AtomicBoolean var1, AtomicBoolean var2) throws MetaException;

   void start();
}
