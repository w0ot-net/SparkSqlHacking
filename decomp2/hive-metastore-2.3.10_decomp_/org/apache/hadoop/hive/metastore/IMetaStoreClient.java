package org.apache.hadoop.hive.metastore;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.ValidTxnList;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.common.classification.RetrySemantics.CannotRetry;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.annotation.NoReconnect;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.CompactionResponse;
import org.apache.hadoop.hive.metastore.api.CompactionType;
import org.apache.hadoop.hive.metastore.api.ConfigValSecurityException;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.DataOperationType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.FireEventRequest;
import org.apache.hadoop.hive.metastore.api.FireEventResponse;
import org.apache.hadoop.hive.metastore.api.ForeignKeysRequest;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.GetAllFunctionsResponse;
import org.apache.hadoop.hive.metastore.api.GetOpenTxnsInfoResponse;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleRequest;
import org.apache.hadoop.hive.metastore.api.GetPrincipalsInRoleResponse;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalRequest;
import org.apache.hadoop.hive.metastore.api.GetRoleGrantsForPrincipalResponse;
import org.apache.hadoop.hive.metastore.api.HeartbeatTxnRangeResponse;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.InvalidPartitionException;
import org.apache.hadoop.hive.metastore.api.LockRequest;
import org.apache.hadoop.hive.metastore.api.LockResponse;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchLockException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.NoSuchTxnException;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.api.OpenTxnsResponse;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionEventType;
import org.apache.hadoop.hive.metastore.api.PartitionValuesRequest;
import org.apache.hadoop.hive.metastore.api.PartitionValuesResponse;
import org.apache.hadoop.hive.metastore.api.PrimaryKeysRequest;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.SetPartitionsStatsRequest;
import org.apache.hadoop.hive.metastore.api.ShowCompactResponse;
import org.apache.hadoop.hive.metastore.api.ShowLocksRequest;
import org.apache.hadoop.hive.metastore.api.ShowLocksResponse;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TxnAbortedException;
import org.apache.hadoop.hive.metastore.api.TxnOpenException;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.thrift.TException;

@Public
@Evolving
public interface IMetaStoreClient {
   boolean isCompatibleWith(HiveConf var1);

   void setHiveAddedJars(String var1);

   boolean isLocalMetaStore();

   void reconnect() throws MetaException;

   @NoReconnect
   void close();

   void setMetaConf(String var1, String var2) throws MetaException, TException;

   String getMetaConf(String var1) throws MetaException, TException;

   List getDatabases(String var1) throws MetaException, TException;

   List getAllDatabases() throws MetaException, TException;

   List getTables(String var1, String var2) throws MetaException, TException, UnknownDBException;

   List getTables(String var1, String var2, TableType var3) throws MetaException, TException, UnknownDBException;

   List getTableMeta(String var1, String var2, List var3) throws MetaException, TException, UnknownDBException;

   List getAllTables(String var1) throws MetaException, TException, UnknownDBException;

   List listTableNamesByFilter(String var1, String var2, short var3) throws MetaException, TException, InvalidOperationException, UnknownDBException;

   void dropTable(String var1, String var2, boolean var3, boolean var4) throws MetaException, TException, NoSuchObjectException;

   void dropTable(String var1, String var2, boolean var3, boolean var4, boolean var5) throws MetaException, TException, NoSuchObjectException;

   /** @deprecated */
   @Deprecated
   void dropTable(String var1, boolean var2) throws MetaException, UnknownTableException, TException, NoSuchObjectException;

   void dropTable(String var1, String var2) throws MetaException, TException, NoSuchObjectException;

   boolean tableExists(String var1, String var2) throws MetaException, TException, UnknownDBException;

   /** @deprecated */
   @Deprecated
   boolean tableExists(String var1) throws MetaException, TException, UnknownDBException;

   /** @deprecated */
   @Deprecated
   Table getTable(String var1) throws MetaException, TException, NoSuchObjectException;

   Database getDatabase(String var1) throws NoSuchObjectException, MetaException, TException;

   Table getTable(String var1, String var2) throws MetaException, TException, NoSuchObjectException;

   List getTableObjectsByName(String var1, List var2) throws MetaException, InvalidOperationException, UnknownDBException, TException;

   Partition appendPartition(String var1, String var2, List var3) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   Partition appendPartition(String var1, String var2, String var3) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   Partition add_partition(Partition var1) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   int add_partitions(List var1) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   int add_partitions_pspec(PartitionSpecProxy var1) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   List add_partitions(List var1, boolean var2, boolean var3) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   Partition getPartition(String var1, String var2, List var3) throws NoSuchObjectException, MetaException, TException;

   Partition exchange_partition(Map var1, String var2, String var3, String var4, String var5) throws MetaException, NoSuchObjectException, InvalidObjectException, TException;

   List exchange_partitions(Map var1, String var2, String var3, String var4, String var5) throws MetaException, NoSuchObjectException, InvalidObjectException, TException;

   Partition getPartition(String var1, String var2, String var3) throws MetaException, UnknownTableException, NoSuchObjectException, TException;

   Partition getPartitionWithAuthInfo(String var1, String var2, List var3, String var4, List var5) throws MetaException, UnknownTableException, NoSuchObjectException, TException;

   List listPartitions(String var1, String var2, short var3) throws NoSuchObjectException, MetaException, TException;

   PartitionSpecProxy listPartitionSpecs(String var1, String var2, int var3) throws TException;

   List listPartitions(String var1, String var2, List var3, short var4) throws NoSuchObjectException, MetaException, TException;

   List listPartitionNames(String var1, String var2, short var3) throws MetaException, TException;

   List listPartitionNames(String var1, String var2, List var3, short var4) throws MetaException, TException, NoSuchObjectException;

   PartitionValuesResponse listPartitionValues(PartitionValuesRequest var1) throws MetaException, TException, NoSuchObjectException;

   int getNumPartitionsByFilter(String var1, String var2, String var3) throws MetaException, NoSuchObjectException, TException;

   List listPartitionsByFilter(String var1, String var2, String var3, short var4) throws MetaException, NoSuchObjectException, TException;

   PartitionSpecProxy listPartitionSpecsByFilter(String var1, String var2, String var3, int var4) throws MetaException, NoSuchObjectException, TException;

   boolean listPartitionsByExpr(String var1, String var2, byte[] var3, String var4, short var5, List var6) throws TException;

   List listPartitionsWithAuthInfo(String var1, String var2, short var3, String var4, List var5) throws MetaException, TException, NoSuchObjectException;

   List getPartitionsByNames(String var1, String var2, List var3) throws NoSuchObjectException, MetaException, TException;

   List listPartitionsWithAuthInfo(String var1, String var2, List var3, short var4, String var5, List var6) throws MetaException, TException, NoSuchObjectException;

   void markPartitionForEvent(String var1, String var2, Map var3, PartitionEventType var4) throws MetaException, NoSuchObjectException, TException, UnknownTableException, UnknownDBException, UnknownPartitionException, InvalidPartitionException;

   boolean isPartitionMarkedForEvent(String var1, String var2, Map var3, PartitionEventType var4) throws MetaException, NoSuchObjectException, TException, UnknownTableException, UnknownDBException, UnknownPartitionException, InvalidPartitionException;

   void validatePartitionNameCharacters(List var1) throws TException, MetaException;

   void createTable(Table var1) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException;

   void alter_table(String var1, String var2, Table var3) throws InvalidOperationException, MetaException, TException;

   /** @deprecated */
   @Deprecated
   void alter_table(String var1, String var2, Table var3, boolean var4) throws InvalidOperationException, MetaException, TException;

   void alter_table_with_environmentContext(String var1, String var2, Table var3, EnvironmentContext var4) throws InvalidOperationException, MetaException, TException;

   void createDatabase(Database var1) throws InvalidObjectException, AlreadyExistsException, MetaException, TException;

   void dropDatabase(String var1) throws NoSuchObjectException, InvalidOperationException, MetaException, TException;

   void dropDatabase(String var1, boolean var2, boolean var3) throws NoSuchObjectException, InvalidOperationException, MetaException, TException;

   void dropDatabase(String var1, boolean var2, boolean var3, boolean var4) throws NoSuchObjectException, InvalidOperationException, MetaException, TException;

   void alterDatabase(String var1, Database var2) throws NoSuchObjectException, MetaException, TException;

   boolean dropPartition(String var1, String var2, List var3, boolean var4) throws NoSuchObjectException, MetaException, TException;

   boolean dropPartition(String var1, String var2, List var3, PartitionDropOptions var4) throws TException;

   List dropPartitions(String var1, String var2, List var3, boolean var4, boolean var5) throws NoSuchObjectException, MetaException, TException;

   List dropPartitions(String var1, String var2, List var3, boolean var4, boolean var5, boolean var6) throws NoSuchObjectException, MetaException, TException;

   List dropPartitions(String var1, String var2, List var3, PartitionDropOptions var4) throws TException;

   boolean dropPartition(String var1, String var2, String var3, boolean var4) throws NoSuchObjectException, MetaException, TException;

   void alter_partition(String var1, String var2, Partition var3) throws InvalidOperationException, MetaException, TException;

   void alter_partition(String var1, String var2, Partition var3, EnvironmentContext var4) throws InvalidOperationException, MetaException, TException;

   void alter_partitions(String var1, String var2, List var3) throws InvalidOperationException, MetaException, TException;

   void alter_partitions(String var1, String var2, List var3, EnvironmentContext var4) throws InvalidOperationException, MetaException, TException;

   void renamePartition(String var1, String var2, List var3, Partition var4) throws InvalidOperationException, MetaException, TException;

   List getFields(String var1, String var2) throws MetaException, TException, UnknownTableException, UnknownDBException;

   List getSchema(String var1, String var2) throws MetaException, TException, UnknownTableException, UnknownDBException;

   String getConfigValue(String var1, String var2) throws TException, ConfigValSecurityException;

   List partitionNameToVals(String var1) throws MetaException, TException;

   Map partitionNameToSpec(String var1) throws MetaException, TException;

   void createIndex(Index var1, Table var2) throws InvalidObjectException, MetaException, NoSuchObjectException, TException, AlreadyExistsException;

   void alter_index(String var1, String var2, String var3, Index var4) throws InvalidOperationException, MetaException, TException;

   Index getIndex(String var1, String var2, String var3) throws MetaException, UnknownTableException, NoSuchObjectException, TException;

   List listIndexes(String var1, String var2, short var3) throws NoSuchObjectException, MetaException, TException;

   List listIndexNames(String var1, String var2, short var3) throws MetaException, TException;

   boolean dropIndex(String var1, String var2, String var3, boolean var4) throws NoSuchObjectException, MetaException, TException;

   boolean updateTableColumnStatistics(ColumnStatistics var1) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException;

   boolean updatePartitionColumnStatistics(ColumnStatistics var1) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException;

   List getTableColumnStatistics(String var1, String var2, List var3) throws NoSuchObjectException, MetaException, TException;

   Map getPartitionColumnStatistics(String var1, String var2, List var3, List var4) throws NoSuchObjectException, MetaException, TException;

   boolean deletePartitionColumnStatistics(String var1, String var2, String var3, String var4) throws NoSuchObjectException, MetaException, InvalidObjectException, TException, InvalidInputException;

   boolean deleteTableColumnStatistics(String var1, String var2, String var3) throws NoSuchObjectException, MetaException, InvalidObjectException, TException, InvalidInputException;

   boolean create_role(Role var1) throws MetaException, TException;

   boolean drop_role(String var1) throws MetaException, TException;

   List listRoleNames() throws MetaException, TException;

   boolean grant_role(String var1, String var2, PrincipalType var3, String var4, PrincipalType var5, boolean var6) throws MetaException, TException;

   boolean revoke_role(String var1, String var2, PrincipalType var3, boolean var4) throws MetaException, TException;

   List list_roles(String var1, PrincipalType var2) throws MetaException, TException;

   PrincipalPrivilegeSet get_privilege_set(HiveObjectRef var1, String var2, List var3) throws MetaException, TException;

   List list_privileges(String var1, PrincipalType var2, HiveObjectRef var3) throws MetaException, TException;

   boolean grant_privileges(PrivilegeBag var1) throws MetaException, TException;

   boolean revoke_privileges(PrivilegeBag var1, boolean var2) throws MetaException, TException;

   String getDelegationToken(String var1, String var2) throws MetaException, TException;

   long renewDelegationToken(String var1) throws MetaException, TException;

   void cancelDelegationToken(String var1) throws MetaException, TException;

   String getTokenStrForm() throws IOException;

   boolean addToken(String var1, String var2) throws TException;

   boolean removeToken(String var1) throws TException;

   String getToken(String var1) throws TException;

   List getAllTokenIdentifiers() throws TException;

   int addMasterKey(String var1) throws MetaException, TException;

   void updateMasterKey(Integer var1, String var2) throws NoSuchObjectException, MetaException, TException;

   boolean removeMasterKey(Integer var1) throws TException;

   String[] getMasterKeys() throws TException;

   void createFunction(Function var1) throws InvalidObjectException, MetaException, TException;

   void alterFunction(String var1, String var2, Function var3) throws InvalidObjectException, MetaException, TException;

   void dropFunction(String var1, String var2) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException, TException;

   Function getFunction(String var1, String var2) throws MetaException, TException;

   List getFunctions(String var1, String var2) throws MetaException, TException;

   GetAllFunctionsResponse getAllFunctions() throws MetaException, TException;

   ValidTxnList getValidTxns() throws TException;

   ValidTxnList getValidTxns(long var1) throws TException;

   long openTxn(String var1) throws TException;

   OpenTxnsResponse openTxns(String var1, int var2) throws TException;

   void rollbackTxn(long var1) throws NoSuchTxnException, TException;

   void commitTxn(long var1) throws NoSuchTxnException, TxnAbortedException, TException;

   void abortTxns(List var1) throws TException;

   GetOpenTxnsInfoResponse showTxns() throws TException;

   @CannotRetry
   LockResponse lock(LockRequest var1) throws NoSuchTxnException, TxnAbortedException, TException;

   LockResponse checkLock(long var1) throws NoSuchTxnException, TxnAbortedException, NoSuchLockException, TException;

   void unlock(long var1) throws NoSuchLockException, TxnOpenException, TException;

   /** @deprecated */
   @Deprecated
   ShowLocksResponse showLocks() throws TException;

   ShowLocksResponse showLocks(ShowLocksRequest var1) throws TException;

   void heartbeat(long var1, long var3) throws NoSuchLockException, NoSuchTxnException, TxnAbortedException, TException;

   HeartbeatTxnRangeResponse heartbeatTxnRange(long var1, long var3) throws TException;

   /** @deprecated */
   @Deprecated
   void compact(String var1, String var2, String var3, CompactionType var4) throws TException;

   /** @deprecated */
   @Deprecated
   void compact(String var1, String var2, String var3, CompactionType var4, Map var5) throws TException;

   CompactionResponse compact2(String var1, String var2, String var3, CompactionType var4, Map var5) throws TException;

   ShowCompactResponse showCompactions() throws TException;

   /** @deprecated */
   @Deprecated
   void addDynamicPartitions(long var1, String var3, String var4, List var5) throws TException;

   void addDynamicPartitions(long var1, String var3, String var4, List var5, DataOperationType var6) throws TException;

   void insertTable(Table var1, boolean var2) throws MetaException;

   @LimitedPrivate({"HCatalog"})
   NotificationEventResponse getNextNotification(long var1, int var3, NotificationFilter var4) throws TException;

   @LimitedPrivate({"HCatalog"})
   CurrentNotificationEventId getCurrentNotificationEventId() throws TException;

   @LimitedPrivate({"Apache Hive, HCatalog"})
   FireEventResponse fireListenerEvent(FireEventRequest var1) throws TException;

   GetPrincipalsInRoleResponse get_principals_in_role(GetPrincipalsInRoleRequest var1) throws MetaException, TException;

   GetRoleGrantsForPrincipalResponse get_role_grants_for_principal(GetRoleGrantsForPrincipalRequest var1) throws MetaException, TException;

   AggrStats getAggrColStatsFor(String var1, String var2, List var3, List var4) throws NoSuchObjectException, MetaException, TException;

   boolean setPartitionColumnStatistics(SetPartitionsStatsRequest var1) throws NoSuchObjectException, InvalidObjectException, MetaException, TException, InvalidInputException;

   void flushCache();

   Iterable getFileMetadata(List var1) throws TException;

   Iterable getFileMetadataBySarg(List var1, ByteBuffer var2, boolean var3) throws TException;

   void clearFileMetadata(List var1) throws TException;

   void putFileMetadata(List var1, List var2) throws TException;

   boolean isSameConfObj(HiveConf var1);

   boolean cacheFileMetadata(String var1, String var2, String var3, boolean var4) throws TException;

   List getPrimaryKeys(PrimaryKeysRequest var1) throws MetaException, NoSuchObjectException, TException;

   List getForeignKeys(ForeignKeysRequest var1) throws MetaException, NoSuchObjectException, TException;

   void createTableWithConstraints(Table var1, List var2, List var3) throws AlreadyExistsException, InvalidObjectException, MetaException, NoSuchObjectException, TException;

   void dropConstraint(String var1, String var2, String var3) throws MetaException, NoSuchObjectException, TException;

   void addPrimaryKey(List var1) throws MetaException, NoSuchObjectException, TException;

   void addForeignKey(List var1) throws MetaException, NoSuchObjectException, TException;

   public static class IncompatibleMetastoreException extends MetaException {
      IncompatibleMetastoreException(String message) {
         super(message);
      }
   }

   @LimitedPrivate({"HCatalog"})
   public interface NotificationFilter {
      boolean accept(NotificationEvent var1);
   }
}
