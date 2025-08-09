package org.apache.hadoop.hive.metastore;

import java.util.List;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public interface AlterHandler extends Configurable {
   /** @deprecated */
   @Deprecated
   void alterTable(RawStore var1, Warehouse var2, String var3, String var4, Table var5, EnvironmentContext var6) throws InvalidOperationException, MetaException;

   void alterTable(RawStore var1, Warehouse var2, String var3, String var4, Table var5, EnvironmentContext var6, HiveMetaStore.HMSHandler var7) throws InvalidOperationException, MetaException;

   /** @deprecated */
   @Deprecated
   Partition alterPartition(RawStore var1, Warehouse var2, String var3, String var4, List var5, Partition var6, EnvironmentContext var7) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException;

   Partition alterPartition(RawStore var1, Warehouse var2, String var3, String var4, List var5, Partition var6, EnvironmentContext var7, HiveMetaStore.HMSHandler var8) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException;

   /** @deprecated */
   @Deprecated
   List alterPartitions(RawStore var1, Warehouse var2, String var3, String var4, List var5, EnvironmentContext var6) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException;

   List alterPartitions(RawStore var1, Warehouse var2, String var3, String var4, List var5, EnvironmentContext var6, HiveMetaStore.HMSHandler var7) throws InvalidOperationException, InvalidObjectException, AlreadyExistsException, MetaException;
}
