package org.apache.hadoop.hive.metastore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidInputException;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidPartitionException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.hadoop.hive.metastore.api.NotificationEventRequest;
import org.apache.hadoop.hive.metastore.api.NotificationEventResponse;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PartitionEventType;
import org.apache.hadoop.hive.metastore.api.PartitionValuesResponse;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.Type;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.thrift.TException;

public interface RawStore extends Configurable {
   void shutdown();

   boolean openTransaction();

   boolean openTransaction(String var1);

   @RawStore.CanNotRetry
   boolean commitTransaction();

   boolean isActiveTransaction();

   @RawStore.CanNotRetry
   void rollbackTransaction();

   void createDatabase(Database var1) throws InvalidObjectException, MetaException;

   Database getDatabase(String var1) throws NoSuchObjectException;

   boolean dropDatabase(String var1) throws NoSuchObjectException, MetaException;

   boolean alterDatabase(String var1, Database var2) throws NoSuchObjectException, MetaException;

   List getDatabases(String var1) throws MetaException;

   List getAllDatabases() throws MetaException;

   boolean createType(Type var1);

   Type getType(String var1);

   boolean dropType(String var1);

   void createTable(Table var1) throws InvalidObjectException, MetaException;

   boolean dropTable(String var1, String var2) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException;

   Table getTable(String var1, String var2) throws MetaException;

   boolean addPartition(Partition var1) throws InvalidObjectException, MetaException;

   boolean addPartitions(String var1, String var2, List var3) throws InvalidObjectException, MetaException;

   boolean addPartitions(String var1, String var2, PartitionSpecProxy var3, boolean var4) throws InvalidObjectException, MetaException;

   Partition getPartition(String var1, String var2, List var3) throws MetaException, NoSuchObjectException;

   boolean doesPartitionExist(String var1, String var2, List var3) throws MetaException, NoSuchObjectException;

   boolean dropPartition(String var1, String var2, List var3) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException;

   List getPartitions(String var1, String var2, int var3) throws MetaException, NoSuchObjectException;

   void alterTable(String var1, String var2, Table var3) throws InvalidObjectException, MetaException;

   List getTables(String var1, String var2) throws MetaException;

   List getTables(String var1, String var2, TableType var3) throws MetaException;

   List getTableMeta(String var1, String var2, List var3) throws MetaException;

   List getTableObjectsByName(String var1, List var2) throws MetaException, UnknownDBException;

   List getAllTables(String var1) throws MetaException;

   List listTableNamesByFilter(String var1, String var2, short var3) throws MetaException, UnknownDBException;

   List listPartitionNames(String var1, String var2, short var3) throws MetaException;

   PartitionValuesResponse listPartitionValues(String var1, String var2, List var3, boolean var4, String var5, boolean var6, List var7, long var8) throws MetaException;

   List listPartitionNamesByFilter(String var1, String var2, String var3, short var4) throws MetaException;

   void alterPartition(String var1, String var2, List var3, Partition var4) throws InvalidObjectException, MetaException;

   void alterPartitions(String var1, String var2, List var3, List var4) throws InvalidObjectException, MetaException;

   boolean addIndex(Index var1) throws InvalidObjectException, MetaException;

   Index getIndex(String var1, String var2, String var3) throws MetaException;

   boolean dropIndex(String var1, String var2, String var3) throws MetaException;

   List getIndexes(String var1, String var2, int var3) throws MetaException;

   List listIndexNames(String var1, String var2, short var3) throws MetaException;

   void alterIndex(String var1, String var2, String var3, Index var4) throws InvalidObjectException, MetaException;

   List getPartitionsByFilter(String var1, String var2, String var3, short var4) throws MetaException, NoSuchObjectException;

   boolean getPartitionsByExpr(String var1, String var2, byte[] var3, String var4, short var5, List var6) throws TException;

   int getNumPartitionsByFilter(String var1, String var2, String var3) throws MetaException, NoSuchObjectException;

   int getNumPartitionsByExpr(String var1, String var2, byte[] var3) throws MetaException, NoSuchObjectException;

   List getPartitionsByNames(String var1, String var2, List var3) throws MetaException, NoSuchObjectException;

   Table markPartitionForEvent(String var1, String var2, Map var3, PartitionEventType var4) throws MetaException, UnknownTableException, InvalidPartitionException, UnknownPartitionException;

   boolean isPartitionMarkedForEvent(String var1, String var2, Map var3, PartitionEventType var4) throws MetaException, UnknownTableException, InvalidPartitionException, UnknownPartitionException;

   boolean addRole(String var1, String var2) throws InvalidObjectException, MetaException, NoSuchObjectException;

   boolean removeRole(String var1) throws MetaException, NoSuchObjectException;

   boolean grantRole(Role var1, String var2, PrincipalType var3, String var4, PrincipalType var5, boolean var6) throws MetaException, NoSuchObjectException, InvalidObjectException;

   boolean revokeRole(Role var1, String var2, PrincipalType var3, boolean var4) throws MetaException, NoSuchObjectException;

   PrincipalPrivilegeSet getUserPrivilegeSet(String var1, List var2) throws InvalidObjectException, MetaException;

   PrincipalPrivilegeSet getDBPrivilegeSet(String var1, String var2, List var3) throws InvalidObjectException, MetaException;

   PrincipalPrivilegeSet getTablePrivilegeSet(String var1, String var2, String var3, List var4) throws InvalidObjectException, MetaException;

   PrincipalPrivilegeSet getPartitionPrivilegeSet(String var1, String var2, String var3, String var4, List var5) throws InvalidObjectException, MetaException;

   PrincipalPrivilegeSet getColumnPrivilegeSet(String var1, String var2, String var3, String var4, String var5, List var6) throws InvalidObjectException, MetaException;

   List listPrincipalGlobalGrants(String var1, PrincipalType var2);

   List listPrincipalDBGrants(String var1, PrincipalType var2, String var3);

   List listAllTableGrants(String var1, PrincipalType var2, String var3, String var4);

   List listPrincipalPartitionGrants(String var1, PrincipalType var2, String var3, String var4, List var5, String var6);

   List listPrincipalTableColumnGrants(String var1, PrincipalType var2, String var3, String var4, String var5);

   List listPrincipalPartitionColumnGrants(String var1, PrincipalType var2, String var3, String var4, List var5, String var6, String var7);

   boolean grantPrivileges(PrivilegeBag var1) throws InvalidObjectException, MetaException, NoSuchObjectException;

   boolean revokePrivileges(PrivilegeBag var1, boolean var2) throws InvalidObjectException, MetaException, NoSuchObjectException;

   Role getRole(String var1) throws NoSuchObjectException;

   List listRoleNames();

   List listRoles(String var1, PrincipalType var2);

   List listRolesWithGrants(String var1, PrincipalType var2);

   List listRoleMembers(String var1);

   Partition getPartitionWithAuth(String var1, String var2, List var3, String var4, List var5) throws MetaException, NoSuchObjectException, InvalidObjectException;

   List getPartitionsWithAuth(String var1, String var2, short var3, String var4, List var5) throws MetaException, NoSuchObjectException, InvalidObjectException;

   List listPartitionNamesPs(String var1, String var2, List var3, short var4) throws MetaException, NoSuchObjectException;

   List listPartitionsPsWithAuth(String var1, String var2, List var3, short var4, String var5, List var6) throws MetaException, InvalidObjectException, NoSuchObjectException;

   boolean updateTableColumnStatistics(ColumnStatistics var1) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException;

   boolean updatePartitionColumnStatistics(ColumnStatistics var1, List var2) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException;

   ColumnStatistics getTableColumnStatistics(String var1, String var2, List var3) throws MetaException, NoSuchObjectException;

   List getPartitionColumnStatistics(String var1, String var2, List var3, List var4) throws MetaException, NoSuchObjectException;

   boolean deletePartitionColumnStatistics(String var1, String var2, String var3, List var4, String var5) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException;

   boolean deleteTableColumnStatistics(String var1, String var2, String var3) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException;

   long cleanupEvents();

   boolean addToken(String var1, String var2);

   boolean removeToken(String var1);

   String getToken(String var1);

   List getAllTokenIdentifiers();

   int addMasterKey(String var1) throws MetaException;

   void updateMasterKey(Integer var1, String var2) throws NoSuchObjectException, MetaException;

   boolean removeMasterKey(Integer var1);

   String[] getMasterKeys();

   void verifySchema() throws MetaException;

   String getMetaStoreSchemaVersion() throws MetaException;

   void setMetaStoreSchemaVersion(String var1, String var2) throws MetaException;

   void dropPartitions(String var1, String var2, List var3) throws MetaException, NoSuchObjectException;

   List listPrincipalDBGrantsAll(String var1, PrincipalType var2);

   List listPrincipalTableGrantsAll(String var1, PrincipalType var2);

   List listPrincipalPartitionGrantsAll(String var1, PrincipalType var2);

   List listPrincipalTableColumnGrantsAll(String var1, PrincipalType var2);

   List listPrincipalPartitionColumnGrantsAll(String var1, PrincipalType var2);

   List listGlobalGrantsAll();

   List listDBGrantsAll(String var1);

   List listPartitionColumnGrantsAll(String var1, String var2, String var3, String var4);

   List listTableGrantsAll(String var1, String var2);

   List listPartitionGrantsAll(String var1, String var2, String var3);

   List listTableColumnGrantsAll(String var1, String var2, String var3);

   void createFunction(Function var1) throws InvalidObjectException, MetaException;

   void alterFunction(String var1, String var2, Function var3) throws InvalidObjectException, MetaException;

   void dropFunction(String var1, String var2) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException;

   Function getFunction(String var1, String var2) throws MetaException;

   List getAllFunctions() throws MetaException;

   List getFunctions(String var1, String var2) throws MetaException;

   AggrStats get_aggr_stats_for(String var1, String var2, List var3, List var4) throws MetaException, NoSuchObjectException;

   NotificationEventResponse getNextNotification(NotificationEventRequest var1);

   void addNotificationEvent(NotificationEvent var1);

   void cleanNotificationEvents(int var1);

   CurrentNotificationEventId getCurrentNotificationEventId();

   void flushCache();

   ByteBuffer[] getFileMetadata(List var1) throws MetaException;

   void putFileMetadata(List var1, List var2, FileMetadataExprType var3) throws MetaException;

   boolean isFileMetadataSupported();

   void getFileMetadataByExpr(List var1, FileMetadataExprType var2, byte[] var3, ByteBuffer[] var4, ByteBuffer[] var5, boolean[] var6) throws MetaException;

   FileMetadataHandler getFileMetadataHandler(FileMetadataExprType var1);

   @Evolving
   int getTableCount() throws MetaException;

   @Evolving
   int getPartitionCount() throws MetaException;

   @Evolving
   int getDatabaseCount() throws MetaException;

   List getPrimaryKeys(String var1, String var2) throws MetaException;

   List getForeignKeys(String var1, String var2, String var3, String var4) throws MetaException;

   void createTableWithConstraints(Table var1, List var2, List var3) throws InvalidObjectException, MetaException;

   void dropConstraint(String var1, String var2, String var3) throws NoSuchObjectException;

   void addPrimaryKeys(List var1) throws InvalidObjectException, MetaException;

   void addForeignKeys(List var1) throws InvalidObjectException, MetaException;

   long updateParameterWithExpectedValue(Table var1, String var2, String var3, String var4) throws MetaException, NoSuchObjectException;

   @Target({ElementType.METHOD})
   @Retention(RetentionPolicy.RUNTIME)
   public @interface CanNotRetry {
   }
}
