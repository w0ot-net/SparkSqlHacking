package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.cache.CacheLoader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.FileMetadataHandler;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.PartFilterExprUtil;
import org.apache.hadoop.hive.metastore.PartitionExpressionProxy;
import org.apache.hadoop.hive.metastore.RawStore;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.Warehouse;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.CurrentNotificationEventId;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.HiveObjectPrivilege;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
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
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.RolePrincipalGrant;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.api.TableMeta;
import org.apache.hadoop.hive.metastore.api.Type;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownPartitionException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.hadoop.hive.metastore.parser.ExpressionTree;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseStore implements RawStore {
   private static final Logger LOG = LoggerFactory.getLogger(HBaseStore.class.getName());
   private HBaseReadWrite hbase = null;
   private Configuration conf;
   private int txnNestLevel = 0;
   private PartitionExpressionProxy expressionProxy = null;
   private Map fmHandlers;

   public void shutdown() {
      try {
         if (this.txnNestLevel != 0) {
            this.rollbackTransaction();
         }

         this.getHBase().close();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public boolean openTransaction() {
      if (this.txnNestLevel++ <= 0) {
         LOG.debug("Opening HBase transaction");
         this.getHBase().begin();
         this.txnNestLevel = 1;
      }

      return true;
   }

   public boolean openTransaction(String isolationLevel) {
      throw new UnsupportedOperationException("Not supported");
   }

   public boolean commitTransaction() {
      if (--this.txnNestLevel == 0) {
         LOG.debug("Committing HBase transaction");
         this.getHBase().commit();
      }

      return true;
   }

   public boolean isActiveTransaction() {
      return this.txnNestLevel != 0;
   }

   public void rollbackTransaction() {
      this.txnNestLevel = 0;
      LOG.debug("Rolling back HBase transaction");
      this.getHBase().rollback();
   }

   public void createDatabase(Database db) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         Database dbCopy = db.deepCopy();
         dbCopy.setName(HiveStringUtils.normalizeIdentifier(dbCopy.getName()));
         this.getHBase().putDb(dbCopy);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to create database ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public Database getDatabase(String name) throws NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      Database var4;
      try {
         Database db = this.getHBase().getDb(HiveStringUtils.normalizeIdentifier(name));
         if (db == null) {
            throw new NoSuchObjectException("Unable to find db " + name);
         }

         commit = true;
         var4 = db;
      } catch (IOException e) {
         LOG.error("Unable to get db", e);
         throw new NoSuchObjectException("Error reading db " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public boolean dropDatabase(String dbname) throws NoSuchObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      boolean var3;
      try {
         this.getHBase().deleteDb(HiveStringUtils.normalizeIdentifier(dbname));
         commit = true;
         var3 = true;
      } catch (IOException e) {
         LOG.error("Unable to delete db" + e);
         throw new MetaException("Unable to drop database " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public boolean alterDatabase(String dbname, Database db) throws NoSuchObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      boolean var5;
      try {
         Database dbCopy = db.deepCopy();
         dbCopy.setName(HiveStringUtils.normalizeIdentifier(dbCopy.getName()));
         this.getHBase().putDb(dbCopy);
         commit = true;
         var5 = true;
      } catch (IOException e) {
         LOG.error("Unable to alter database ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getDatabases(String pattern) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Object var12;
      try {
         List<Database> dbs = this.getHBase().scanDatabases(pattern == null ? null : HiveStringUtils.normalizeIdentifier(this.likeToRegex(pattern)));
         List<String> dbNames = new ArrayList(dbs.size());

         for(Database db : dbs) {
            dbNames.add(db.getName());
         }

         commit = true;
         var12 = dbNames;
      } catch (IOException e) {
         LOG.error("Unable to get databases ", e);
         throw new MetaException("Unable to get databases, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var12;
   }

   public List getAllDatabases() throws MetaException {
      return this.getDatabases((String)null);
   }

   public int getDatabaseCount() throws MetaException {
      try {
         return this.getHBase().getDatabaseCount();
      } catch (IOException e) {
         LOG.error("Unable to get database count", e);
         throw new MetaException("Error scanning databases");
      }
   }

   public boolean createType(Type type) {
      throw new UnsupportedOperationException();
   }

   public Type getType(String typeName) {
      throw new UnsupportedOperationException();
   }

   public boolean dropType(String typeName) {
      throw new UnsupportedOperationException();
   }

   public void createTable(Table tbl) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         Table tblCopy = tbl.deepCopy();
         tblCopy.setDbName(HiveStringUtils.normalizeIdentifier(tblCopy.getDbName()));
         tblCopy.setTableName(HiveStringUtils.normalizeIdentifier(tblCopy.getTableName()));
         this.normalizeColumnNames(tblCopy);
         this.getHBase().putTable(tblCopy);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to create table ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   private void normalizeColumnNames(Table tbl) {
      if (tbl.getSd().getCols() != null) {
         tbl.getSd().setCols(this.normalizeFieldSchemaList(tbl.getSd().getCols()));
      }

      if (tbl.getPartitionKeys() != null) {
         tbl.setPartitionKeys(this.normalizeFieldSchemaList(tbl.getPartitionKeys()));
      }

   }

   private List normalizeFieldSchemaList(List fieldschemas) {
      List<FieldSchema> ret = new ArrayList();

      for(FieldSchema fieldSchema : fieldschemas) {
         ret.add(new FieldSchema(fieldSchema.getName().toLowerCase(), fieldSchema.getType(), fieldSchema.getComment()));
      }

      return ret;
   }

   public boolean dropTable(String dbName, String tableName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean commit = false;
      this.openTransaction();

      boolean var4;
      try {
         this.getHBase().deleteTable(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName));
         commit = true;
         var4 = true;
      } catch (IOException e) {
         LOG.error("Unable to delete db" + e);
         throw new MetaException("Unable to drop table " + this.tableNameForErrorMsg(dbName, tableName));
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public Table getTable(String dbName, String tableName) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Table var5;
      try {
         Table table = this.getHBase().getTable(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName));
         if (table == null) {
            LOG.debug("Unable to find table " + this.tableNameForErrorMsg(dbName, tableName));
         }

         commit = true;
         var5 = table;
      } catch (IOException e) {
         LOG.error("Unable to get table", e);
         throw new MetaException("Error reading table " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public boolean addPartition(Partition part) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      boolean var4;
      try {
         Partition partCopy = part.deepCopy();
         partCopy.setDbName(HiveStringUtils.normalizeIdentifier(part.getDbName()));
         partCopy.setTableName(HiveStringUtils.normalizeIdentifier(part.getTableName()));
         this.getHBase().putPartition(partCopy);
         commit = true;
         var4 = true;
      } catch (IOException e) {
         LOG.error("Unable to add partition", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public boolean addPartitions(String dbName, String tblName, List parts) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      boolean var13;
      try {
         List<Partition> partsCopy = new ArrayList();

         for(int i = 0; i < parts.size(); ++i) {
            Partition partCopy = ((Partition)parts.get(i)).deepCopy();
            partCopy.setDbName(HiveStringUtils.normalizeIdentifier(partCopy.getDbName()));
            partCopy.setTableName(HiveStringUtils.normalizeIdentifier(partCopy.getTableName()));
            partsCopy.add(i, partCopy);
         }

         this.getHBase().putPartitions(partsCopy);
         commit = true;
         var13 = true;
      } catch (IOException e) {
         LOG.error("Unable to add partitions", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var13;
   }

   public boolean addPartitions(String dbName, String tblName, PartitionSpecProxy partitionSpec, boolean ifNotExists) throws InvalidObjectException, MetaException {
      throw new UnsupportedOperationException();
   }

   public Partition getPartition(String dbName, String tableName, List part_vals) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      Partition var6;
      try {
         Partition part = this.getHBase().getPartition(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName), part_vals);
         if (part == null) {
            throw new NoSuchObjectException("Unable to find partition " + this.partNameForErrorMsg(dbName, tableName, part_vals));
         }

         commit = true;
         var6 = part;
      } catch (IOException e) {
         LOG.error("Unable to get partition", e);
         throw new MetaException("Error reading partition " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public boolean doesPartitionExist(String dbName, String tableName, List part_vals) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean var6;
      try {
         boolean exists = this.getHBase().getPartition(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName), part_vals) != null;
         commit = true;
         var6 = exists;
      } catch (IOException e) {
         LOG.error("Unable to get partition", e);
         throw new MetaException("Error reading partition " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public boolean dropPartition(String dbName, String tableName, List part_vals) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean commit = false;
      this.openTransaction();

      boolean var5;
      try {
         dbName = HiveStringUtils.normalizeIdentifier(dbName);
         tableName = HiveStringUtils.normalizeIdentifier(tableName);
         this.getHBase().deletePartition(dbName, tableName, HBaseUtils.getPartitionKeyTypes(this.getTable(dbName, tableName).getPartitionKeys()), part_vals);
         this.getHBase().getStatsCache().invalidate(dbName, tableName, this.buildExternalPartName(dbName, tableName, part_vals));
         commit = true;
         var5 = true;
      } catch (IOException e) {
         LOG.error("Unable to delete db" + e);
         throw new MetaException("Unable to drop partition " + this.partNameForErrorMsg(dbName, tableName, part_vals));
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getPartitions(String dbName, String tableName, int max) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      List var6;
      try {
         List<Partition> parts = this.getHBase().scanPartitionsInTable(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName), max);
         commit = true;
         var6 = parts;
      } catch (IOException e) {
         LOG.error("Unable to get partitions", e);
         throw new MetaException("Error scanning partitions");
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public void alterTable(String dbName, String tableName, Table newTable) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         Table newTableCopy = newTable.deepCopy();
         newTableCopy.setDbName(HiveStringUtils.normalizeIdentifier(newTableCopy.getDbName()));
         List<String> oldPartTypes = this.getTable(dbName, tableName).getPartitionKeys() == null ? null : HBaseUtils.getPartitionKeyTypes(this.getTable(dbName, tableName).getPartitionKeys());
         newTableCopy.setTableName(HiveStringUtils.normalizeIdentifier(newTableCopy.getTableName()));
         this.getHBase().replaceTable(this.getHBase().getTable(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tableName)), newTableCopy);
         if (newTable.getPartitionKeys() != null && newTable.getPartitionKeys().size() > 0 && !tableName.equals(newTable.getTableName())) {
            try {
               List<Partition> oldParts = this.getPartitions(dbName, tableName, -1);
               List<Partition> newParts = new ArrayList(oldParts.size());

               for(Partition oldPart : oldParts) {
                  Partition newPart = oldPart.deepCopy();
                  newPart.setTableName(newTable.getTableName());
                  newParts.add(newPart);
               }

               this.getHBase().replacePartitions(oldParts, newParts, oldPartTypes);
            } catch (NoSuchObjectException var16) {
               LOG.debug("No partitions found for old table so not worrying about it");
            }
         }

         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to alter table " + this.tableNameForErrorMsg(dbName, tableName), e);
         throw new MetaException("Unable to alter table " + this.tableNameForErrorMsg(dbName, tableName));
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public List getTables(String dbName, String pattern) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List var5;
      try {
         List<String> tableNames = this.getTableNamesInTx(dbName, pattern);
         commit = true;
         var5 = tableNames;
      } catch (IOException e) {
         LOG.error("Unable to get tables ", e);
         throw new MetaException("Unable to get tables, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getTables(String dbName, String pattern, TableType tableType) throws MetaException {
      throw new UnsupportedOperationException();
   }

   private List getTableNamesInTx(String dbName, String pattern) throws IOException {
      List<Table> tables = this.getHBase().scanTables(HiveStringUtils.normalizeIdentifier(dbName), pattern == null ? null : HiveStringUtils.normalizeIdentifier(this.likeToRegex(pattern)));
      List<String> tableNames = new ArrayList(tables.size());

      for(Table table : tables) {
         tableNames.add(table.getTableName());
      }

      return tableNames;
   }

   public List getTableMeta(String dbNames, String tableNames, List tableTypes) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Object var16;
      try {
         List<TableMeta> metas = new ArrayList();

         for(String dbName : this.getDatabases(dbNames)) {
            for(Table table : this.getTableObjectsByName(dbName, this.getTableNamesInTx(dbName, tableNames))) {
               if (tableTypes == null || tableTypes.contains(table.getTableType())) {
                  TableMeta metaData = new TableMeta(table.getDbName(), table.getTableName(), table.getTableType());
                  metaData.setComments((String)table.getParameters().get("comment"));
                  metas.add(metaData);
               }
            }
         }

         commit = true;
         var16 = metas;
      } catch (Exception e) {
         LOG.error("Unable to get tables ", e);
         throw new MetaException("Unable to get tables, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var16;
   }

   public List getTableObjectsByName(String dbname, List tableNames) throws MetaException, UnknownDBException {
      boolean commit = false;
      this.openTransaction();

      List var13;
      try {
         List<String> normalizedTableNames = new ArrayList(tableNames.size());

         for(String tableName : tableNames) {
            normalizedTableNames.add(HiveStringUtils.normalizeIdentifier(tableName));
         }

         List<Table> tables = this.getHBase().getTables(HiveStringUtils.normalizeIdentifier(dbname), normalizedTableNames);
         commit = true;
         var13 = tables;
      } catch (IOException e) {
         LOG.error("Unable to get tables ", e);
         throw new MetaException("Unable to get tables, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var13;
   }

   public List getAllTables(String dbName) throws MetaException {
      return this.getTables(dbName, (String)null);
   }

   public int getTableCount() throws MetaException {
      try {
         return this.getHBase().getTableCount();
      } catch (IOException e) {
         LOG.error("Unable to get table count", e);
         throw new MetaException("Error scanning tables");
      }
   }

   public List listTableNamesByFilter(String dbName, String filter, short max_tables) throws MetaException, UnknownDBException {
      throw new UnsupportedOperationException();
   }

   public List listPartitionNames(String db_name, String tbl_name, short max_parts) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List<String> names;
      try {
         List<Partition> parts = this.getHBase().scanPartitionsInTable(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), max_parts);
         if (parts != null) {
            names = new ArrayList(parts.size());
            Table table = this.getHBase().getTable(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name));

            for(Partition p : parts) {
               names.add(this.buildExternalPartName(table, p));
            }

            commit = true;
            Object var16 = names;
            return (List)var16;
         }

         names = null;
      } catch (IOException e) {
         LOG.error("Unable to get partitions", e);
         throw new MetaException("Error scanning partitions");
      } finally {
         this.commitOrRoleBack(commit);
      }

      return names;
   }

   public PartitionValuesResponse listPartitionValues(String db_name, String tbl_name, List cols, boolean applyDistinct, String filter, boolean ascending, List order, long maxParts) {
      throw new UnsupportedOperationException();
   }

   public List listPartitionNamesByFilter(String db_name, String tbl_name, String filter, short max_parts) throws MetaException {
      throw new UnsupportedOperationException();
   }

   public void alterPartition(String db_name, String tbl_name, List part_vals, Partition new_part) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         Partition new_partCopy = new_part.deepCopy();
         new_partCopy.setDbName(HiveStringUtils.normalizeIdentifier(new_partCopy.getDbName()));
         new_partCopy.setTableName(HiveStringUtils.normalizeIdentifier(new_partCopy.getTableName()));
         Partition oldPart = this.getHBase().getPartition(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), part_vals);
         this.getHBase().replacePartition(oldPart, new_partCopy, HBaseUtils.getPartitionKeyTypes(this.getTable(db_name, tbl_name).getPartitionKeys()));
         this.getHBase().getStatsCache().invalidate(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), this.buildExternalPartName(db_name, tbl_name, part_vals));
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to add partition", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void alterPartitions(String db_name, String tbl_name, List part_vals_list, List new_parts) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         List<Partition> new_partsCopy = new ArrayList();

         for(int i = 0; i < new_parts.size(); ++i) {
            Partition newPartCopy = ((Partition)new_parts.get(i)).deepCopy();
            newPartCopy.setDbName(HiveStringUtils.normalizeIdentifier(newPartCopy.getDbName()));
            newPartCopy.setTableName(HiveStringUtils.normalizeIdentifier(newPartCopy.getTableName()));
            new_partsCopy.add(i, newPartCopy);
         }

         List<Partition> oldParts = this.getHBase().getPartitions(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), HBaseUtils.getPartitionKeyTypes(this.getTable(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name)).getPartitionKeys()), part_vals_list);
         this.getHBase().replacePartitions(oldParts, new_partsCopy, HBaseUtils.getPartitionKeyTypes(this.getTable(db_name, tbl_name).getPartitionKeys()));

         for(List part_vals : part_vals_list) {
            this.getHBase().getStatsCache().invalidate(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), this.buildExternalPartName(db_name, tbl_name, part_vals));
         }

         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to add partition", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public boolean addIndex(Index index) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         index.setDbName(HiveStringUtils.normalizeIdentifier(index.getDbName()));
         index.setOrigTableName(HiveStringUtils.normalizeIdentifier(index.getOrigTableName()));
         index.setIndexName(HiveStringUtils.normalizeIdentifier(index.getIndexName()));
         index.setIndexTableName(HiveStringUtils.normalizeIdentifier(index.getIndexTableName()));
         this.getHBase().putIndex(index);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to create index ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return commit;
   }

   public Index getIndex(String dbName, String origTableName, String indexName) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Index var6;
      try {
         Index index = this.getHBase().getIndex(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(origTableName), HiveStringUtils.normalizeIdentifier(indexName));
         if (index == null) {
            LOG.debug("Unable to find index " + this.indexNameForErrorMsg(dbName, origTableName, indexName));
         }

         commit = true;
         var6 = index;
      } catch (IOException e) {
         LOG.error("Unable to get index", e);
         throw new MetaException("Error reading index " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public boolean dropIndex(String dbName, String origTableName, String indexName) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      boolean var5;
      try {
         this.getHBase().deleteIndex(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(origTableName), HiveStringUtils.normalizeIdentifier(indexName));
         commit = true;
         var5 = true;
      } catch (IOException e) {
         LOG.error("Unable to delete index" + e);
         throw new MetaException("Unable to drop index " + this.indexNameForErrorMsg(dbName, origTableName, indexName));
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getIndexes(String dbName, String origTableName, int max) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List var6;
      try {
         List<Index> indexes = this.getHBase().scanIndexes(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(origTableName), max);
         commit = true;
         var6 = indexes;
      } catch (IOException e) {
         LOG.error("Unable to get indexes", e);
         throw new MetaException("Error scanning indexxes");
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public List listIndexNames(String dbName, String origTableName, short max) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List<String> names;
      try {
         List<Index> indexes = this.getHBase().scanIndexes(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(origTableName), max);
         if (indexes != null) {
            names = new ArrayList(indexes.size());

            for(Index index : indexes) {
               names.add(index.getIndexName());
            }

            commit = true;
            Object var15 = names;
            return (List)var15;
         }

         names = null;
      } catch (IOException e) {
         LOG.error("Unable to get indexes", e);
         throw new MetaException("Error scanning indexes");
      } finally {
         this.commitOrRoleBack(commit);
      }

      return names;
   }

   public void alterIndex(String dbname, String baseTblName, String name, Index newIndex) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         Index newIndexCopy = newIndex.deepCopy();
         newIndexCopy.setDbName(HiveStringUtils.normalizeIdentifier(newIndexCopy.getDbName()));
         newIndexCopy.setOrigTableName(HiveStringUtils.normalizeIdentifier(newIndexCopy.getOrigTableName()));
         newIndexCopy.setIndexName(HiveStringUtils.normalizeIdentifier(newIndexCopy.getIndexName()));
         this.getHBase().replaceIndex(this.getHBase().getIndex(HiveStringUtils.normalizeIdentifier(dbname), HiveStringUtils.normalizeIdentifier(baseTblName), HiveStringUtils.normalizeIdentifier(name)), newIndexCopy);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to alter index " + this.indexNameForErrorMsg(dbname, baseTblName, name), e);
         throw new MetaException("Unable to alter index " + this.indexNameForErrorMsg(dbname, baseTblName, name));
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public List getPartitionsByFilter(String dbName, String tblName, String filter, short maxParts) throws MetaException, NoSuchObjectException {
      ExpressionTree exprTree = filter != null && !filter.isEmpty() ? PartFilterExprUtil.getFilterParser(filter).tree : ExpressionTree.EMPTY_TREE;
      List<Partition> result = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var8;
      try {
         this.getPartitionsByExprInternal(HiveStringUtils.normalizeIdentifier(dbName), HiveStringUtils.normalizeIdentifier(tblName), exprTree, maxParts, result);
         var8 = result;
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var8;
   }

   public boolean getPartitionsByExpr(String dbName, String tblName, byte[] expr, String defaultPartitionName, short maxParts, List result) throws TException {
      ExpressionTree exprTree = PartFilterExprUtil.makeExpressionTree(this.expressionProxy, expr);
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      tblName = HiveStringUtils.normalizeIdentifier(tblName);
      Table table = this.getTable(dbName, tblName);
      boolean commit = false;
      this.openTransaction();

      boolean var12;
      try {
         if (exprTree != null) {
            boolean var18 = this.getPartitionsByExprInternal(dbName, tblName, exprTree, maxParts, result);
            return var18;
         }

         List<String> partNames = new LinkedList();
         boolean hasUnknownPartitions = this.getPartitionNamesPrunedByExprNoTxn(table, expr, defaultPartitionName, maxParts, partNames);
         result.addAll(this.getPartitionsByNames(dbName, tblName, partNames));
         var12 = hasUnknownPartitions;
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var12;
   }

   public int getNumPartitionsByFilter(String dbName, String tblName, String filter) throws MetaException, NoSuchObjectException {
      if (filter != null && !filter.isEmpty()) {
         ExpressionTree var11 = PartFilterExprUtil.getFilterParser(filter).tree;
      } else {
         ExpressionTree var10000 = ExpressionTree.EMPTY_TREE;
      }

      new ArrayList();
      boolean commit = false;
      this.openTransaction();

      int var7;
      try {
         var7 = this.getPartitionsByFilter(dbName, tblName, filter, (short)32767).size();
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var7;
   }

   public int getNumPartitionsByExpr(String dbName, String tblName, byte[] expr) throws MetaException, NoSuchObjectException {
      ExpressionTree exprTree = PartFilterExprUtil.makeExpressionTree(this.expressionProxy, expr);
      List<Partition> result = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      int var7;
      try {
         this.getPartitionsByExprInternal(dbName, tblName, exprTree, (short)32767, result);
         var7 = result.size();
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var7;
   }

   private boolean getPartitionNamesPrunedByExprNoTxn(Table table, byte[] expr, String defaultPartName, short maxParts, List result) throws MetaException, NoSuchObjectException {
      for(Partition part : this.getPartitions(table.getDbName(), table.getTableName(), maxParts)) {
         result.add(Warehouse.makePartName(table.getPartitionKeys(), part.getValues()));
      }

      List<String> columnNames = new ArrayList();
      List<PrimitiveTypeInfo> typeInfos = new ArrayList();

      for(FieldSchema fs : table.getPartitionKeys()) {
         columnNames.add(fs.getName());
         typeInfos.add(TypeInfoFactory.getPrimitiveTypeInfo(fs.getType()));
      }

      if (defaultPartName == null || defaultPartName.isEmpty()) {
         defaultPartName = HiveConf.getVar(this.getConf(), ConfVars.DEFAULTPARTITIONNAME);
      }

      return this.expressionProxy.filterPartitionsByExpr(columnNames, typeInfos, expr, defaultPartName, result);
   }

   private boolean getPartitionsByExprInternal(String dbName, String tblName, ExpressionTree exprTree, short maxParts, List result) throws MetaException, NoSuchObjectException {
      dbName = HiveStringUtils.normalizeIdentifier(dbName);
      tblName = HiveStringUtils.normalizeIdentifier(tblName);
      Table table = this.getTable(dbName, tblName);
      if (table == null) {
         throw new NoSuchObjectException("Unable to find table " + dbName + "." + tblName);
      } else {
         HBaseFilterPlanUtil.PlanResult planRes = HBaseFilterPlanUtil.getFilterPlan(exprTree, table.getPartitionKeys());
         if (LOG.isDebugEnabled()) {
            LOG.debug("Hbase Filter Plan generated : " + planRes.plan);
         }

         Map<List<String>, Partition> mergedParts = new HashMap();

         for(HBaseFilterPlanUtil.ScanPlan splan : planRes.plan.getPlans()) {
            try {
               List<Partition> parts = this.getHBase().scanPartitions(dbName, tblName, splan.getStartRowSuffix(dbName, tblName, table.getPartitionKeys()), splan.getEndRowSuffix(dbName, tblName, table.getPartitionKeys()), splan.getFilter(table.getPartitionKeys()), -1);
               boolean reachedMax = false;

               for(Partition part : parts) {
                  mergedParts.put(part.getValues(), part);
                  if (mergedParts.size() == maxParts) {
                     reachedMax = true;
                     break;
                  }
               }

               if (reachedMax) {
                  break;
               }
            } catch (IOException e) {
               LOG.error("Unable to get partitions", e);
               throw new MetaException("Error scanning partitions" + this.tableNameForErrorMsg(dbName, tblName) + ": " + e);
            }
         }

         for(Map.Entry mp : mergedParts.entrySet()) {
            result.add(mp.getValue());
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Matched partitions " + result);
         }

         return !planRes.hasUnsupportedCondition;
      }
   }

   public List getPartitionsByNames(String dbName, String tblName, List partNames) throws MetaException, NoSuchObjectException {
      List<Partition> parts = new ArrayList();

      for(String partName : partNames) {
         parts.add(this.getPartition(dbName, tblName, partNameToVals(partName)));
      }

      return parts;
   }

   public Table markPartitionForEvent(String dbName, String tblName, Map partVals, PartitionEventType evtType) throws MetaException, UnknownTableException, InvalidPartitionException, UnknownPartitionException {
      throw new UnsupportedOperationException();
   }

   public boolean isPartitionMarkedForEvent(String dbName, String tblName, Map partName, PartitionEventType evtType) throws MetaException, UnknownTableException, InvalidPartitionException, UnknownPartitionException {
      throw new UnsupportedOperationException();
   }

   public int getPartitionCount() throws MetaException {
      try {
         return this.getHBase().getPartitionCount();
      } catch (IOException e) {
         LOG.error("Unable to get partition count", e);
         throw new MetaException("Error scanning partitions");
      }
   }

   public boolean addRole(String roleName, String ownerName) throws InvalidObjectException, MetaException, NoSuchObjectException {
      int now = (int)(System.currentTimeMillis() / 1000L);
      Role role = new Role(roleName, now, ownerName);
      boolean commit = false;
      this.openTransaction();

      boolean var6;
      try {
         if (this.getHBase().getRole(roleName) != null) {
            throw new InvalidObjectException("Role " + roleName + " already exists");
         }

         this.getHBase().putRole(role);
         commit = true;
         var6 = true;
      } catch (IOException e) {
         LOG.error("Unable to create role ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public boolean removeRole(String roleName) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean var11;
      try {
         Set<String> usersInRole = this.getHBase().findAllUsersInRole(roleName);
         this.getHBase().deleteRole(roleName);
         this.getHBase().removeRoleGrants(roleName);

         for(String user : usersInRole) {
            this.getHBase().buildRoleMapForUser(user);
         }

         commit = true;
         var11 = true;
      } catch (IOException e) {
         LOG.error("Unable to delete role" + e);
         throw new MetaException("Unable to drop role " + roleName);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var11;
   }

   public boolean grantRole(Role role, String userName, PrincipalType principalType, String grantor, PrincipalType grantorType, boolean grantOption) throws MetaException, NoSuchObjectException, InvalidObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean var17;
      try {
         Set<String> usersToRemap = this.findUsersToRemapRolesFor(role, userName, principalType);
         HbaseMetastoreProto.RoleGrantInfo.Builder builder = HbaseMetastoreProto.RoleGrantInfo.newBuilder();
         if (userName != null) {
            builder.setPrincipalName(userName);
         }

         if (principalType != null) {
            builder.setPrincipalType(HBaseUtils.convertPrincipalTypes(principalType));
         }

         builder.setAddTime((long)((int)(System.currentTimeMillis() / 1000L)));
         if (grantor != null) {
            builder.setGrantor(grantor);
         }

         if (grantorType != null) {
            builder.setGrantorType(HBaseUtils.convertPrincipalTypes(grantorType));
         }

         builder.setGrantOption(grantOption);
         this.getHBase().addPrincipalToRole(role.getRoleName(), builder.build());

         for(String user : usersToRemap) {
            this.getHBase().buildRoleMapForUser(user);
         }

         commit = true;
         var17 = true;
      } catch (IOException e) {
         LOG.error("Unable to grant role", e);
         throw new MetaException("Unable to grant role " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var17;
   }

   public boolean revokeRole(Role role, String userName, PrincipalType principalType, boolean grantOption) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean e;
      try {
         if (grantOption) {
            this.getHBase().dropPrincipalFromRole(role.getRoleName(), userName, principalType, grantOption);
         } else {
            Set<String> usersToRemap = this.findUsersToRemapRolesFor(role, userName, principalType);
            this.getHBase().dropPrincipalFromRole(role.getRoleName(), userName, principalType, grantOption);

            for(String user : usersToRemap) {
               this.getHBase().buildRoleMapForUser(user);
            }
         }

         commit = true;
         e = true;
      } catch (IOException e) {
         LOG.error("Unable to revoke role " + role.getRoleName() + " from " + userName, e);
         throw new MetaException("Unable to revoke role " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return e;
   }

   public PrincipalPrivilegeSet getUserPrivilegeSet(String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      Object var6;
      try {
         PrincipalPrivilegeSet pps = new PrincipalPrivilegeSet();
         PrincipalPrivilegeSet global = this.getHBase().getGlobalPrivs();
         if (global != null) {
            if (global.getUserPrivileges() != null) {
               List<PrivilegeGrantInfo> pgi = (List)global.getUserPrivileges().get(userName);
               if (pgi != null) {
                  pps.putToUserPrivileges(userName, pgi);
               }
            }

            if (global.getRolePrivileges() != null) {
               List<String> roles = this.getHBase().getUserRoles(userName);
               if (roles != null) {
                  for(String role : roles) {
                     List<PrivilegeGrantInfo> pgi = (List)global.getRolePrivileges().get(role);
                     if (pgi != null) {
                        pps.putToRolePrivileges(role, pgi);
                     }
                  }
               }
            }

            commit = true;
            PrincipalPrivilegeSet var17 = pps;
            return var17;
         }

         var6 = null;
      } catch (IOException e) {
         LOG.error("Unable to get db privileges for user", e);
         throw new MetaException("Unable to get db privileges for user, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (PrincipalPrivilegeSet)var6;
   }

   public PrincipalPrivilegeSet getDBPrivilegeSet(String dbName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      PrincipalPrivilegeSet var17;
      try {
         PrincipalPrivilegeSet pps = new PrincipalPrivilegeSet();
         Database db = this.getHBase().getDb(dbName);
         if (db.getPrivileges() != null) {
            if (db.getPrivileges().getUserPrivileges() != null) {
               List<PrivilegeGrantInfo> pgi = (List)db.getPrivileges().getUserPrivileges().get(userName);
               if (pgi != null) {
                  pps.putToUserPrivileges(userName, pgi);
               }
            }

            if (db.getPrivileges().getRolePrivileges() != null) {
               List<String> roles = this.getHBase().getUserRoles(userName);
               if (roles != null) {
                  for(String role : roles) {
                     List<PrivilegeGrantInfo> pgi = (List)db.getPrivileges().getRolePrivileges().get(role);
                     if (pgi != null) {
                        pps.putToRolePrivileges(role, pgi);
                     }
                  }
               }
            }
         }

         commit = true;
         var17 = pps;
      } catch (IOException e) {
         LOG.error("Unable to get db privileges for user", e);
         throw new MetaException("Unable to get db privileges for user, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var17;
   }

   public PrincipalPrivilegeSet getTablePrivilegeSet(String dbName, String tableName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      PrincipalPrivilegeSet var18;
      try {
         PrincipalPrivilegeSet pps = new PrincipalPrivilegeSet();
         Table table = this.getHBase().getTable(dbName, tableName);
         if (table.getPrivileges() != null) {
            if (table.getPrivileges().getUserPrivileges() != null) {
               List<PrivilegeGrantInfo> pgi = (List)table.getPrivileges().getUserPrivileges().get(userName);
               if (pgi != null) {
                  pps.putToUserPrivileges(userName, pgi);
               }
            }

            if (table.getPrivileges().getRolePrivileges() != null) {
               List<String> roles = this.getHBase().getUserRoles(userName);
               if (roles != null) {
                  for(String role : roles) {
                     List<PrivilegeGrantInfo> pgi = (List)table.getPrivileges().getRolePrivileges().get(role);
                     if (pgi != null) {
                        pps.putToRolePrivileges(role, pgi);
                     }
                  }
               }
            }
         }

         commit = true;
         var18 = pps;
      } catch (IOException e) {
         LOG.error("Unable to get db privileges for user", e);
         throw new MetaException("Unable to get db privileges for user, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var18;
   }

   public PrincipalPrivilegeSet getPartitionPrivilegeSet(String dbName, String tableName, String partition, String userName, List groupNames) throws InvalidObjectException, MetaException {
      return null;
   }

   public PrincipalPrivilegeSet getColumnPrivilegeSet(String dbName, String tableName, String partitionName, String columnName, String userName, List groupNames) throws InvalidObjectException, MetaException {
      return null;
   }

   public List listPrincipalGlobalGrants(String principalName, PrincipalType principalType) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var7;
      try {
         PrincipalPrivilegeSet pps = this.getHBase().getGlobalPrivs();
         if (pps != null) {
            Map<String, List<PrivilegeGrantInfo>> map;
            switch (principalType) {
               case USER:
                  map = pps.getUserPrivileges();
                  break;
               case ROLE:
                  map = pps.getRolePrivileges();
                  break;
               default:
                  throw new RuntimeException("Unknown or unsupported principal type " + principalType.toString());
            }

            if (map == null) {
               Object var18 = privileges;
               return (List)var18;
            }

            List<PrivilegeGrantInfo> grants = (List)map.get(principalName);
            if (grants != null && grants.size() != 0) {
               for(PrivilegeGrantInfo pgi : grants) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null), principalName, principalType, pgi));
               }

               commit = true;
               Object var17 = privileges;
               return (List)var17;
            }

            Object var8 = privileges;
            return (List)var8;
         }

         var7 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var7;
   }

   public List listPrincipalDBGrants(String principalName, PrincipalType principalType, String dbName) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var8;
      try {
         Database db = this.getHBase().getDb(dbName);
         if (db != null) {
            PrincipalPrivilegeSet pps = db.getPrivileges();
            if (pps == null) {
               Object var18 = privileges;
               return (List)var18;
            }

            Map<String, List<PrivilegeGrantInfo>> map;
            switch (principalType) {
               case USER:
                  map = pps.getUserPrivileges();
                  break;
               case ROLE:
                  map = pps.getRolePrivileges();
                  break;
               default:
                  throw new RuntimeException("Unknown or unsupported principal type " + principalType.toString());
            }

            if (map == null) {
               Object var21 = privileges;
               return (List)var21;
            }

            List<PrivilegeGrantInfo> grants = (List)map.get(principalName);
            if (grants != null && grants.size() != 0) {
               for(PrivilegeGrantInfo pgi : grants) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.DATABASE, dbName, (String)null, (List)null, (String)null), principalName, principalType, pgi));
               }

               commit = true;
               Object var20 = privileges;
               return (List)var20;
            }

            Object var10 = privileges;
            return (List)var10;
         }

         var8 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var8;
   }

   public List listAllTableGrants(String principalName, PrincipalType principalType, String dbName, String tableName) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var9;
      try {
         Table table = this.getHBase().getTable(dbName, tableName);
         if (table != null) {
            PrincipalPrivilegeSet pps = table.getPrivileges();
            if (pps == null) {
               Object var19 = privileges;
               return (List)var19;
            }

            Map<String, List<PrivilegeGrantInfo>> map;
            switch (principalType) {
               case USER:
                  map = pps.getUserPrivileges();
                  break;
               case ROLE:
                  map = pps.getRolePrivileges();
                  break;
               default:
                  throw new RuntimeException("Unknown or unsupported principal type " + principalType.toString());
            }

            if (map == null) {
               Object var22 = privileges;
               return (List)var22;
            }

            List<PrivilegeGrantInfo> grants = (List)map.get(principalName);
            if (grants != null && grants.size() != 0) {
               for(PrivilegeGrantInfo pgi : grants) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.TABLE, dbName, tableName, (List)null, (String)null), principalName, principalType, pgi));
               }

               commit = true;
               Object var21 = privileges;
               return (List)var21;
            }

            Object var11 = privileges;
            return (List)var11;
         }

         var9 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var9;
   }

   public List listPrincipalPartitionGrants(String principalName, PrincipalType principalType, String dbName, String tableName, List partValues, String partName) {
      return new ArrayList();
   }

   public List listPrincipalTableColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, String columnName) {
      return new ArrayList();
   }

   public List listPrincipalPartitionColumnGrants(String principalName, PrincipalType principalType, String dbName, String tableName, List partVals, String partName, String columnName) {
      return new ArrayList();
   }

   public boolean grantPrivileges(PrivilegeBag privileges) throws InvalidObjectException, MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean var11;
      try {
         for(HiveObjectPrivilege priv : privileges.getPrivileges()) {
            PrivilegeInfo privilegeInfo = this.findPrivilegeToGrantOrRevoke(priv);

            for(PrivilegeGrantInfo info : privilegeInfo.grants) {
               if (info.getPrivilege().equals(priv.getGrantInfo().getPrivilege())) {
                  throw new InvalidObjectException(priv.getPrincipalName() + " already has " + priv.getGrantInfo().getPrivilege() + " on " + privilegeInfo.typeErrMsg);
               }
            }

            privilegeInfo.grants.add(priv.getGrantInfo());
            this.writeBackGrantOrRevoke(priv, privilegeInfo);
         }

         commit = true;
         var11 = true;
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var11;
   }

   public boolean revokePrivileges(PrivilegeBag privileges, boolean grantOption) throws InvalidObjectException, MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      boolean var11;
      try {
         for(HiveObjectPrivilege priv : privileges.getPrivileges()) {
            PrivilegeInfo privilegeInfo = this.findPrivilegeToGrantOrRevoke(priv);

            for(int i = 0; i < privilegeInfo.grants.size(); ++i) {
               if (((PrivilegeGrantInfo)privilegeInfo.grants.get(i)).getPrivilege().equals(priv.getGrantInfo().getPrivilege())) {
                  if (grantOption) {
                     ((PrivilegeGrantInfo)privilegeInfo.grants.get(i)).setGrantOption(false);
                  } else {
                     privilegeInfo.grants.remove(i);
                  }
                  break;
               }
            }

            this.writeBackGrantOrRevoke(priv, privilegeInfo);
         }

         commit = true;
         var11 = true;
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var11;
   }

   private PrivilegeInfo findPrivilegeToGrantOrRevoke(HiveObjectPrivilege privilege) throws MetaException, NoSuchObjectException, InvalidObjectException {
      PrivilegeInfo result = new PrivilegeInfo();
      switch (privilege.getHiveObject().getObjectType()) {
         case GLOBAL:
            try {
               result.privSet = this.createOnNull(this.getHBase().getGlobalPrivs());
            } catch (IOException e) {
               LOG.error("Unable to fetch global privileges", e);
               throw new MetaException("Unable to fetch global privileges, " + e.getMessage());
            }

            result.typeErrMsg = "global";
            break;
         case DATABASE:
            result.db = this.getDatabase(privilege.getHiveObject().getDbName());
            result.typeErrMsg = "database " + result.db.getName();
            result.privSet = this.createOnNull(result.db.getPrivileges());
            break;
         case TABLE:
            result.table = this.getTable(privilege.getHiveObject().getDbName(), privilege.getHiveObject().getObjectName());
            result.typeErrMsg = "table " + result.table.getTableName();
            result.privSet = this.createOnNull(result.table.getPrivileges());
            break;
         case PARTITION:
         case COLUMN:
            throw new RuntimeException("HBase metastore does not support partition or column permissions");
         default:
            throw new RuntimeException("Woah bad, unknown object type " + privilege.getHiveObject().getObjectType());
      }

      Map<String, List<PrivilegeGrantInfo>> grantInfos;
      switch (privilege.getPrincipalType()) {
         case USER:
            grantInfos = result.privSet.getUserPrivileges();
            result.typeErrMsg = "user";
            break;
         case ROLE:
            grantInfos = result.privSet.getRolePrivileges();
            result.typeErrMsg = "role";
            break;
         case GROUP:
            throw new RuntimeException("HBase metastore does not support group permissions");
         default:
            throw new RuntimeException("Woah bad, unknown principal type " + privilege.getPrincipalType());
      }

      result.grants = (List)grantInfos.get(privilege.getPrincipalName());
      if (result.grants == null) {
         result.grants = new ArrayList();
         grantInfos.put(privilege.getPrincipalName(), result.grants);
      }

      return result;
   }

   private PrincipalPrivilegeSet createOnNull(PrincipalPrivilegeSet pps) {
      if (pps == null) {
         pps = new PrincipalPrivilegeSet();
      }

      if (pps.getUserPrivileges() == null) {
         pps.setUserPrivileges(new HashMap());
      }

      if (pps.getRolePrivileges() == null) {
         pps.setRolePrivileges(new HashMap());
      }

      return pps;
   }

   private void writeBackGrantOrRevoke(HiveObjectPrivilege priv, PrivilegeInfo pi) throws MetaException, NoSuchObjectException, InvalidObjectException {
      switch (priv.getHiveObject().getObjectType()) {
         case GLOBAL:
            try {
               this.getHBase().putGlobalPrivs(pi.privSet);
               break;
            } catch (IOException e) {
               LOG.error("Unable to write global privileges", e);
               throw new MetaException("Unable to write global privileges, " + e.getMessage());
            }
         case DATABASE:
            pi.db.setPrivileges(pi.privSet);
            this.alterDatabase(pi.db.getName(), pi.db);
            break;
         case TABLE:
            pi.table.setPrivileges(pi.privSet);
            this.alterTable(pi.table.getDbName(), pi.table.getTableName(), pi.table);
            break;
         default:
            throw new RuntimeException("Dude, you missed the second switch!");
      }

   }

   public Role getRole(String roleName) throws NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      Role var4;
      try {
         Role role = this.getHBase().getRole(roleName);
         if (role == null) {
            throw new NoSuchObjectException("Unable to find role " + roleName);
         }

         commit = true;
         var4 = role;
      } catch (IOException e) {
         LOG.error("Unable to get role", e);
         throw new NoSuchObjectException("Error reading table " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public List listRoleNames() {
      boolean commit = false;
      this.openTransaction();

      Object var11;
      try {
         List<Role> roles = this.getHBase().scanRoles();
         List<String> roleNames = new ArrayList(roles.size());

         for(Role role : roles) {
            roleNames.add(role.getRoleName());
         }

         commit = true;
         var11 = roleNames;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var11;
   }

   public List listRoles(String principalName, PrincipalType principalType) {
      List<Role> roles = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object e;
      try {
         try {
            roles.addAll(this.getHBase().getPrincipalDirectRoles(principalName, principalType));
         } catch (IOException e) {
            throw new RuntimeException(e);
         }

         if (principalType == PrincipalType.USER) {
            roles.add(new Role("public", 0, (String)null));
         }

         commit = true;
         e = roles;
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)e;
   }

   public List listRolesWithGrants(String principalName, PrincipalType principalType) {
      boolean commit = false;
      this.openTransaction();

      Object var16;
      try {
         List<Role> roles = this.listRoles(principalName, principalType);
         List<RolePrincipalGrant> rpgs = new ArrayList(roles.size());

         for(Role role : roles) {
            HbaseMetastoreProto.RoleGrantInfoList grants = this.getHBase().getRolePrincipals(role.getRoleName());
            if (grants != null) {
               for(HbaseMetastoreProto.RoleGrantInfo grant : grants.getGrantInfoList()) {
                  if (grant.getPrincipalType() == HBaseUtils.convertPrincipalTypes(principalType) && grant.getPrincipalName().equals(principalName)) {
                     rpgs.add(new RolePrincipalGrant(role.getRoleName(), principalName, principalType, grant.getGrantOption(), (int)grant.getAddTime(), grant.getGrantor(), HBaseUtils.convertPrincipalTypes(grant.getGrantorType())));
                  }
               }
            }
         }

         commit = true;
         var16 = rpgs;
      } catch (Exception e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var16;
   }

   public List listRoleMembers(String roleName) {
      boolean commit = false;
      this.openTransaction();

      Object var12;
      try {
         HbaseMetastoreProto.RoleGrantInfoList gil = this.getHBase().getRolePrincipals(roleName);
         List<RolePrincipalGrant> roleMaps = new ArrayList(gil.getGrantInfoList().size());

         for(HbaseMetastoreProto.RoleGrantInfo giw : gil.getGrantInfoList()) {
            roleMaps.add(new RolePrincipalGrant(roleName, giw.getPrincipalName(), HBaseUtils.convertPrincipalTypes(giw.getPrincipalType()), giw.getGrantOption(), (int)giw.getAddTime(), giw.getGrantor(), HBaseUtils.convertPrincipalTypes(giw.getGrantorType())));
         }

         commit = true;
         var12 = roleMaps;
      } catch (Exception e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var12;
   }

   public Partition getPartitionWithAuth(String dbName, String tblName, List partVals, String user_name, List group_names) throws MetaException, NoSuchObjectException, InvalidObjectException {
      return this.getPartition(dbName, tblName, partVals);
   }

   public List getPartitionsWithAuth(String dbName, String tblName, short maxParts, String userName, List groupNames) throws MetaException, NoSuchObjectException, InvalidObjectException {
      return this.getPartitions(dbName, tblName, maxParts);
   }

   public List listPartitionNamesPs(String db_name, String tbl_name, List part_vals, short max_parts) throws MetaException, NoSuchObjectException {
      List<Partition> parts = this.listPartitionsPsWithAuth(db_name, tbl_name, part_vals, max_parts, (String)null, (List)null);
      List<String> partNames = new ArrayList(parts.size());

      for(Partition part : parts) {
         partNames.add(this.buildExternalPartName(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), part.getValues()));
      }

      return partNames;
   }

   public List listPartitionsPsWithAuth(String db_name, String tbl_name, List part_vals, short max_parts, String userName, List groupNames) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      List var9;
      try {
         List<Partition> parts = this.getHBase().scanPartitions(HiveStringUtils.normalizeIdentifier(db_name), HiveStringUtils.normalizeIdentifier(tbl_name), part_vals, max_parts);
         commit = true;
         var9 = parts;
      } catch (IOException e) {
         LOG.error("Unable to list partition names", e);
         throw new MetaException("Failed to list part names, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var9;
   }

   public boolean updateTableColumnStatistics(ColumnStatistics colStats) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean commit = false;
      this.openTransaction();

      boolean var9;
      try {
         List<ColumnStatisticsObj> statsObjs = colStats.getStatsObj();
         List<String> colNames = new ArrayList();

         for(ColumnStatisticsObj statsObj : statsObjs) {
            colNames.add(statsObj.getColName());
         }

         String dbName = colStats.getStatsDesc().getDbName();
         String tableName = colStats.getStatsDesc().getTableName();
         Table newTable = this.getTable(dbName, tableName);
         Table newTableCopy = newTable.deepCopy();
         StatsSetupConst.setColumnStatsState(newTableCopy.getParameters(), colNames);
         this.getHBase().replaceTable(newTable, newTableCopy);
         this.getHBase().updateStatistics(colStats.getStatsDesc().getDbName(), colStats.getStatsDesc().getTableName(), (List)null, colStats);
         commit = true;
         var9 = true;
      } catch (IOException e) {
         LOG.error("Unable to update column statistics", e);
         throw new MetaException("Failed to update column statistics, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var9;
   }

   public boolean updatePartitionColumnStatistics(ColumnStatistics colStats, List partVals) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      boolean commit = false;
      this.openTransaction();

      boolean var17;
      try {
         String db_name = colStats.getStatsDesc().getDbName();
         String tbl_name = colStats.getStatsDesc().getTableName();
         Partition oldPart = this.getHBase().getPartition(db_name, tbl_name, partVals);
         Partition new_partCopy = oldPart.deepCopy();
         List<String> colNames = new ArrayList();

         for(ColumnStatisticsObj statsObj : colStats.getStatsObj()) {
            colNames.add(statsObj.getColName());
         }

         StatsSetupConst.setColumnStatsState(new_partCopy.getParameters(), colNames);
         this.getHBase().replacePartition(oldPart, new_partCopy, HBaseUtils.getPartitionKeyTypes(this.getTable(db_name, tbl_name).getPartitionKeys()));
         this.getHBase().updateStatistics(colStats.getStatsDesc().getDbName(), colStats.getStatsDesc().getTableName(), partVals, colStats);
         this.getHBase().getStatsCache().invalidate(colStats.getStatsDesc().getDbName(), colStats.getStatsDesc().getTableName(), colStats.getStatsDesc().getPartName());
         commit = true;
         var17 = true;
      } catch (IOException e) {
         LOG.error("Unable to update column statistics", e);
         throw new MetaException("Failed to update column statistics, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var17;
   }

   public ColumnStatistics getTableColumnStatistics(String dbName, String tableName, List colName) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      ColumnStatistics var6;
      try {
         ColumnStatistics cs = this.getHBase().getTableStatistics(dbName, tableName, colName);
         commit = true;
         var6 = cs;
      } catch (IOException e) {
         LOG.error("Unable to fetch column statistics", e);
         throw new MetaException("Failed to fetch column statistics, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var6;
   }

   public List getPartitionColumnStatistics(String dbName, String tblName, List partNames, List colNames) throws MetaException, NoSuchObjectException {
      List<List<String>> partVals = new ArrayList(partNames.size());

      for(String partName : partNames) {
         partVals.add(partNameToVals(partName));
      }

      boolean commit = false;
      this.openTransaction();

      List var8;
      try {
         List<ColumnStatistics> cs = this.getHBase().getPartitionStatistics(dbName, tblName, partNames, partVals, colNames);
         commit = true;
         var8 = cs;
      } catch (IOException e) {
         LOG.error("Unable to fetch column statistics", e);
         throw new MetaException("Failed fetching column statistics, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var8;
   }

   public boolean deletePartitionColumnStatistics(String dbName, String tableName, String partName, List partVals, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      return true;
   }

   public boolean deleteTableColumnStatistics(String dbName, String tableName, String colName) throws NoSuchObjectException, MetaException, InvalidObjectException, InvalidInputException {
      return true;
   }

   public AggrStats get_aggr_stats_for(String dbName, String tblName, List partNames, List colNames) throws MetaException, NoSuchObjectException {
      List<List<String>> partVals = new ArrayList(partNames.size());

      for(String partName : partNames) {
         partVals.add(partNameToVals(partName));
      }

      boolean commit = false;
      boolean hasAnyStats = false;
      this.openTransaction();

      AggrStats var21;
      try {
         AggrStats aggrStats = new AggrStats();
         aggrStats.setPartsFound(0L);

         for(String colName : colNames) {
            try {
               AggrStats oneCol = this.getHBase().getStatsCache().get(dbName, tblName, partNames, colName);
               if (oneCol.getColStatsSize() > 0) {
                  assert oneCol.getColStatsSize() == 1;

                  aggrStats.setPartsFound(oneCol.getPartsFound());
                  aggrStats.addToColStats((ColumnStatisticsObj)oneCol.getColStats().get(0));
                  hasAnyStats = true;
               }
            } catch (CacheLoader.InvalidCacheLoadException var16) {
               LOG.debug("Found no stats for column " + colName);
            }
         }

         commit = true;
         if (!hasAnyStats) {
            aggrStats.setColStats(new ArrayList());
         }

         var21 = aggrStats;
      } catch (IOException e) {
         LOG.error("Unable to fetch aggregate column statistics", e);
         throw new MetaException("Failed fetching aggregate column statistics, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var21;
   }

   public long cleanupEvents() {
      throw new UnsupportedOperationException();
   }

   public boolean addToken(String tokenIdentifier, String delegationToken) {
      boolean commit = false;
      this.openTransaction();

      boolean var4;
      try {
         this.getHBase().putDelegationToken(tokenIdentifier, delegationToken);
         commit = true;
         var4 = commit;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public boolean removeToken(String tokenIdentifier) {
      boolean commit = false;
      this.openTransaction();

      boolean var3;
      try {
         this.getHBase().deleteDelegationToken(tokenIdentifier);
         commit = true;
         var3 = commit;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public String getToken(String tokenIdentifier) {
      boolean commit = false;
      this.openTransaction();

      String var4;
      try {
         String token = this.getHBase().getDelegationToken(tokenIdentifier);
         commit = true;
         var4 = token;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var4;
   }

   public List getAllTokenIdentifiers() {
      boolean commit = false;
      this.openTransaction();

      List var3;
      try {
         List<String> ids = this.getHBase().scanDelegationTokenIdentifiers();
         commit = true;
         var3 = ids;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public int addMasterKey(String key) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      int var5;
      try {
         long seq = this.getHBase().getNextSequence(HBaseReadWrite.MASTER_KEY_SEQUENCE);
         this.getHBase().putMasterKey((int)seq, key);
         commit = true;
         var5 = (int)seq;
      } catch (IOException e) {
         LOG.error("Unable to add master key", e);
         throw new MetaException("Failed adding master key, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public void updateMasterKey(Integer seqNo, String key) throws NoSuchObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         if (this.getHBase().getMasterKey(seqNo) == null) {
            throw new NoSuchObjectException("No key found with keyId: " + seqNo);
         }

         this.getHBase().putMasterKey(seqNo, key);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to update master key", e);
         throw new MetaException("Failed updating master key, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public boolean removeMasterKey(Integer keySeq) {
      boolean commit = false;
      this.openTransaction();

      boolean var3;
      try {
         this.getHBase().deleteMasterKey(keySeq);
         commit = true;
         var3 = true;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public String[] getMasterKeys() {
      boolean commit = false;
      this.openTransaction();

      String[] var3;
      try {
         List<String> keys = this.getHBase().scanMasterKeys();
         commit = true;
         var3 = (String[])keys.toArray(new String[keys.size()]);
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public void verifySchema() throws MetaException {
   }

   public String getMetaStoreSchemaVersion() throws MetaException {
      throw new UnsupportedOperationException();
   }

   public void setMetaStoreSchemaVersion(String version, String comment) throws MetaException {
      throw new UnsupportedOperationException();
   }

   public void dropPartitions(String dbName, String tblName, List partNames) throws MetaException, NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      try {
         for(String partName : partNames) {
            this.dropPartition(dbName, tblName, partNameToVals(partName));
         }

         commit = true;
      } catch (Exception e) {
         LOG.error("Unable to drop partitions", e);
         throw new NoSuchObjectException("Failure dropping partitions, " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public List listPrincipalDBGrantsAll(String principalName, PrincipalType principalType) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var18;
      try {
         for(Database db : this.getHBase().scanDatabases((String)null)) {
            PrincipalPrivilegeSet pps = db.getPrivileges();
            if (pps != null) {
               Map<String, List<PrivilegeGrantInfo>> map;
               switch (principalType) {
                  case USER:
                     map = pps.getUserPrivileges();
                     break;
                  case ROLE:
                     map = pps.getRolePrivileges();
                     break;
                  default:
                     throw new RuntimeException("Unknown or unsupported principal type " + principalType.toString());
               }

               if (map != null) {
                  List<PrivilegeGrantInfo> grants = (List)map.get(principalName);
                  if (grants != null && grants.size() != 0) {
                     for(PrivilegeGrantInfo pgi : grants) {
                        privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.DATABASE, db.getName(), (String)null, (List)null, (String)null), principalName, principalType, pgi));
                     }
                  }
               }
            }
         }

         commit = true;
         var18 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var18;
   }

   public List listPrincipalTableGrantsAll(String principalName, PrincipalType principalType) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var18;
      try {
         for(Table table : this.getHBase().scanTables((String)null, (String)null)) {
            PrincipalPrivilegeSet pps = table.getPrivileges();
            if (pps != null) {
               Map<String, List<PrivilegeGrantInfo>> map;
               switch (principalType) {
                  case USER:
                     map = pps.getUserPrivileges();
                     break;
                  case ROLE:
                     map = pps.getRolePrivileges();
                     break;
                  default:
                     throw new RuntimeException("Unknown or unsupported principal type " + principalType.toString());
               }

               if (map != null) {
                  List<PrivilegeGrantInfo> grants = (List)map.get(principalName);
                  if (grants != null && grants.size() != 0) {
                     for(PrivilegeGrantInfo pgi : grants) {
                        privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.TABLE, table.getDbName(), table.getTableName(), (List)null, (String)null), principalName, principalType, pgi));
                     }
                  }
               }
            }
         }

         commit = true;
         var18 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var18;
   }

   public List listPrincipalPartitionGrantsAll(String principalName, PrincipalType principalType) {
      return new ArrayList();
   }

   public List listPrincipalTableColumnGrantsAll(String principalName, PrincipalType principalType) {
      return new ArrayList();
   }

   public List listPrincipalPartitionColumnGrantsAll(String principalName, PrincipalType principalType) {
      return new ArrayList();
   }

   public List listGlobalGrantsAll() {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var14;
      try {
         PrincipalPrivilegeSet pps = this.getHBase().getGlobalPrivs();
         if (pps != null) {
            for(Map.Entry e : pps.getUserPrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null), (String)e.getKey(), PrincipalType.USER, pgi));
               }
            }

            for(Map.Entry e : pps.getRolePrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.GLOBAL, (String)null, (String)null, (List)null, (String)null), (String)e.getKey(), PrincipalType.ROLE, pgi));
               }
            }
         }

         commit = true;
         var14 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var14;
   }

   public List listDBGrantsAll(String dbName) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var16;
      try {
         Database db = this.getHBase().getDb(dbName);
         PrincipalPrivilegeSet pps = db.getPrivileges();
         if (pps != null) {
            for(Map.Entry e : pps.getUserPrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.DATABASE, dbName, (String)null, (List)null, (String)null), (String)e.getKey(), PrincipalType.USER, pgi));
               }
            }

            for(Map.Entry e : pps.getRolePrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.DATABASE, dbName, (String)null, (List)null, (String)null), (String)e.getKey(), PrincipalType.ROLE, pgi));
               }
            }
         }

         commit = true;
         var16 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var16;
   }

   public List listPartitionColumnGrantsAll(String dbName, String tableName, String partitionName, String columnName) {
      return new ArrayList();
   }

   public List listTableGrantsAll(String dbName, String tableName) {
      List<HiveObjectPrivilege> privileges = new ArrayList();
      boolean commit = false;
      this.openTransaction();

      Object var17;
      try {
         Table table = this.getHBase().getTable(dbName, tableName);
         PrincipalPrivilegeSet pps = table.getPrivileges();
         if (pps != null) {
            for(Map.Entry e : pps.getUserPrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.TABLE, dbName, tableName, (List)null, (String)null), (String)e.getKey(), PrincipalType.USER, pgi));
               }
            }

            for(Map.Entry e : pps.getRolePrivileges().entrySet()) {
               for(PrivilegeGrantInfo pgi : (List)e.getValue()) {
                  privileges.add(new HiveObjectPrivilege(new HiveObjectRef(HiveObjectType.TABLE, dbName, tableName, (List)null, (String)null), (String)e.getKey(), PrincipalType.ROLE, pgi));
               }
            }
         }

         commit = true;
         var17 = privileges;
      } catch (IOException e) {
         throw new RuntimeException(e);
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var17;
   }

   public List listPartitionGrantsAll(String dbName, String tableName, String partitionName) {
      return new ArrayList();
   }

   public List listTableColumnGrantsAll(String dbName, String tableName, String columnName) {
      return new ArrayList();
   }

   public void createFunction(Function func) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         this.getHBase().putFunction(func);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to create function", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void alterFunction(String dbName, String funcName, Function newFunction) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         this.getHBase().putFunction(newFunction);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to alter function ", e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void dropFunction(String dbName, String funcName) throws MetaException, NoSuchObjectException, InvalidObjectException, InvalidInputException {
      boolean commit = false;
      this.openTransaction();

      try {
         this.getHBase().deleteFunction(dbName, funcName);
         commit = true;
      } catch (IOException e) {
         LOG.error("Unable to delete function" + e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public Function getFunction(String dbName, String funcName) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Function var5;
      try {
         Function func = this.getHBase().getFunction(dbName, funcName);
         commit = true;
         var5 = func;
      } catch (IOException e) {
         LOG.error("Unable to get function" + e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getAllFunctions() throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List var3;
      try {
         List<Function> funcs = this.getHBase().scanFunctions((String)null, ".*");
         commit = true;
         var3 = funcs;
      } catch (IOException e) {
         LOG.error("Unable to get functions" + e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public List getFunctions(String dbName, String pattern) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      Object var13;
      try {
         List<Function> funcs = this.getHBase().scanFunctions(dbName, this.likeToRegex(pattern));
         List<String> funcNames = new ArrayList(funcs.size());

         for(Function func : funcs) {
            funcNames.add(func.getFunctionName());
         }

         commit = true;
         var13 = funcNames;
      } catch (IOException e) {
         LOG.error("Unable to get functions" + e);
         throw new MetaException("Unable to read from or write to hbase " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return (List)var13;
   }

   public NotificationEventResponse getNextNotification(NotificationEventRequest rqst) {
      throw new UnsupportedOperationException();
   }

   public void addNotificationEvent(NotificationEvent event) {
      throw new UnsupportedOperationException();
   }

   public void cleanNotificationEvents(int olderThan) {
      throw new UnsupportedOperationException();
   }

   public CurrentNotificationEventId getCurrentNotificationEventId() {
      throw new UnsupportedOperationException();
   }

   public void flushCache() {
      this.getHBase().flushCatalogCache();
   }

   public void setConf(Configuration configuration) {
      Configuration oldConf = this.conf;
      this.conf = configuration;
      if (this.expressionProxy != null && this.conf != oldConf) {
         LOG.warn("Unexpected setConf when we were already configured");
      }

      if (this.expressionProxy == null || this.conf != oldConf) {
         this.expressionProxy = PartFilterExprUtil.createExpressionProxy(this.conf);
      }

      if (this.conf != oldConf) {
         this.fmHandlers = HiveMetaStore.createHandlerMap();
         this.configureFileMetadataHandlers(this.fmHandlers.values());
      }

   }

   private void configureFileMetadataHandlers(Collection fmHandlers) {
      for(FileMetadataHandler fmh : fmHandlers) {
         fmh.configure(this.conf, this.expressionProxy, this.getHBase());
      }

   }

   public FileMetadataHandler getFileMetadataHandler(FileMetadataExprType type) {
      return (FileMetadataHandler)this.fmHandlers.get(type);
   }

   public Configuration getConf() {
      return this.conf;
   }

   private HBaseReadWrite getHBase() {
      if (this.hbase == null) {
         HBaseReadWrite.setConf(this.conf);
         this.hbase = HBaseReadWrite.getInstance();
      }

      return this.hbase;
   }

   private String tableNameForErrorMsg(String dbName, String tableName) {
      return dbName + "." + tableName;
   }

   private String partNameForErrorMsg(String dbName, String tableName, List partVals) {
      return this.tableNameForErrorMsg(dbName, tableName) + "." + StringUtils.join(partVals, ':');
   }

   private String indexNameForErrorMsg(String dbName, String origTableName, String indexName) {
      return this.tableNameForErrorMsg(dbName, origTableName) + "." + indexName;
   }

   private String buildExternalPartName(Table table, Partition part) {
      return buildExternalPartName(table, part.getValues());
   }

   private String buildExternalPartName(String dbName, String tableName, List partVals) throws MetaException {
      return buildExternalPartName(this.getTable(dbName, tableName), partVals);
   }

   private Set findUsersToRemapRolesFor(Role role, String principalName, PrincipalType type) throws IOException, NoSuchObjectException {
      Set<String> usersToRemap;
      switch (type) {
         case USER:
            usersToRemap = new HashSet();
            usersToRemap.add(principalName);
            break;
         case ROLE:
            usersToRemap = this.getHBase().findAllUsersInRole(role.getRoleName());
            break;
         default:
            throw new RuntimeException("Unknown principal type " + type);
      }

      return usersToRemap;
   }

   static String buildExternalPartName(Table table, List partVals) {
      List<String> partCols = new ArrayList();

      for(FieldSchema pc : table.getPartitionKeys()) {
         partCols.add(pc.getName());
      }

      return FileUtils.makePartName(partCols, partVals);
   }

   private static List partNameToVals(String name) {
      if (name == null) {
         return null;
      } else {
         List<String> vals = new ArrayList();
         String[] kvp = name.split("/");

         for(String kv : kvp) {
            vals.add(FileUtils.unescapePathName(kv.substring(kv.indexOf(61) + 1)));
         }

         return vals;
      }
   }

   static List partNameListToValsList(List partNames) {
      List<List<String>> valLists = new ArrayList(partNames.size());

      for(String partName : partNames) {
         valLists.add(partNameToVals(partName));
      }

      return valLists;
   }

   private String likeToRegex(String like) {
      return like == null ? null : like.replace("*", ".*");
   }

   private void commitOrRoleBack(boolean commit) {
      if (commit) {
         LOG.debug("Committing transaction");
         this.commitTransaction();
      } else {
         LOG.debug("Rolling back transaction");
         this.rollbackTransaction();
      }

   }

   @VisibleForTesting
   HBaseReadWrite backdoor() {
      return this.getHBase();
   }

   public boolean isFileMetadataSupported() {
      return true;
   }

   public ByteBuffer[] getFileMetadata(List fileIds) throws MetaException {
      this.openTransaction();
      boolean commit = true;

      ByteBuffer[] var3;
      try {
         var3 = this.getHBase().getFileMetadata(fileIds);
      } catch (IOException e) {
         commit = false;
         LOG.error("Unable to get file metadata", e);
         throw new MetaException("Error reading file metadata " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var3;
   }

   public void getFileMetadataByExpr(List fileIds, FileMetadataExprType type, byte[] expr, ByteBuffer[] metadatas, ByteBuffer[] results, boolean[] eliminated) throws MetaException {
      FileMetadataHandler fmh = (FileMetadataHandler)this.fmHandlers.get(type);
      boolean commit = true;

      try {
         fmh.getFileMetadataByExpr(fileIds, expr, metadatas, results, eliminated);
      } catch (IOException e) {
         LOG.error("Unable to get file metadata by expr", e);
         commit = false;
         throw new MetaException("Error reading file metadata by expr" + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void putFileMetadata(List fileIds, List metadata, FileMetadataExprType type) throws MetaException {
      this.openTransaction();
      boolean commit = false;

      try {
         ByteBuffer[][] addedVals = (ByteBuffer[][])null;
         ByteBuffer[] addedCols = null;
         if (type != null) {
            FileMetadataHandler fmh = (FileMetadataHandler)this.fmHandlers.get(type);
            addedCols = fmh.createAddedCols();
            if (addedCols != null) {
               addedVals = fmh.createAddedColVals(metadata);
            }
         }

         this.getHBase().storeFileMetadata(fileIds, metadata, addedCols, addedVals);
         commit = true;
      } catch (InterruptedException | IOException e) {
         LOG.error("Unable to store file metadata", e);
         throw new MetaException("Error storing file metadata " + ((Exception)e).getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public List getPrimaryKeys(String db_name, String tbl_name) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List var5;
      try {
         List<SQLPrimaryKey> pk = this.getHBase().getPrimaryKey(db_name, tbl_name);
         commit = true;
         var5 = pk;
      } catch (IOException e) {
         LOG.error("Unable to get primary key", e);
         throw new MetaException("Error reading db " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return var5;
   }

   public List getForeignKeys(String parent_db_name, String parent_tbl_name, String foreign_db_name, String foreign_tbl_name) throws MetaException {
      boolean commit = false;
      this.openTransaction();

      List<SQLForeignKey> result;
      try {
         List<SQLForeignKey> fks = this.getHBase().getForeignKeys(foreign_db_name, foreign_tbl_name);
         if (fks != null && fks.size() != 0) {
            result = new ArrayList(fks.size());

            for(SQLForeignKey fkcol : fks) {
               if ((parent_db_name == null || fkcol.getPktable_db().equals(parent_db_name)) && (parent_tbl_name == null || fkcol.getPktable_name().equals(parent_tbl_name))) {
                  result.add(fkcol);
               }
            }

            commit = true;
            Object var16 = result;
            return (List)var16;
         }

         result = null;
      } catch (IOException e) {
         LOG.error("Unable to get foreign key", e);
         throw new MetaException("Error reading db " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

      return result;
   }

   public void createTableWithConstraints(Table tbl, List primaryKeys, List foreignKeys) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         this.createTable(tbl);
         if (primaryKeys != null) {
            this.addPrimaryKeys(primaryKeys);
         }

         if (foreignKeys != null) {
            this.addForeignKeys(foreignKeys);
         }

         commit = true;
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void dropConstraint(String dbName, String tableName, String constraintName) throws NoSuchObjectException {
      boolean commit = false;
      this.openTransaction();

      try {
         List<SQLPrimaryKey> pk = this.getHBase().getPrimaryKey(dbName, tableName);
         if (pk == null || pk.size() <= 0 || !((SQLPrimaryKey)pk.get(0)).getPk_name().equals(constraintName)) {
            List<SQLForeignKey> fks = this.getHBase().getForeignKeys(dbName, tableName);
            if (fks != null && fks.size() > 0) {
               List<SQLForeignKey> newKeyList = new ArrayList(fks.size());

               for(SQLForeignKey fkcol : fks) {
                  if (!fkcol.getFk_name().equals(constraintName)) {
                     newKeyList.add(fkcol);
                  }
               }

               if (newKeyList.size() > 0) {
                  this.getHBase().putForeignKeys(newKeyList);
               } else {
                  this.getHBase().deleteForeignKeys(dbName, tableName);
               }

               commit = true;
               return;
            }

            commit = true;
            throw new NoSuchObjectException("Unable to find constraint named " + constraintName + " on table " + this.tableNameForErrorMsg(dbName, tableName));
         }

         this.getHBase().deletePrimaryKey(dbName, tableName);
         commit = true;
      } catch (IOException e) {
         LOG.error("Error fetching primary key for table " + this.tableNameForErrorMsg(dbName, tableName), e);
         throw new NoSuchObjectException("Error fetching primary key for table " + this.tableNameForErrorMsg(dbName, tableName) + " : " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void addPrimaryKeys(List pks) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         List<SQLPrimaryKey> currentPk = this.getHBase().getPrimaryKey(((SQLPrimaryKey)pks.get(0)).getTable_db(), ((SQLPrimaryKey)pks.get(0)).getTable_name());
         if (currentPk != null) {
            throw new MetaException(" Primary key already exists for: " + this.tableNameForErrorMsg(((SQLPrimaryKey)pks.get(0)).getTable_db(), ((SQLPrimaryKey)pks.get(0)).getTable_name()));
         }

         this.getHBase().putPrimaryKey(pks);
         commit = true;
      } catch (IOException e) {
         LOG.error("Error writing primary key", e);
         throw new MetaException("Error writing primary key: " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public void addForeignKeys(List fks) throws InvalidObjectException, MetaException {
      boolean commit = false;
      this.openTransaction();

      try {
         List<SQLForeignKey> existing = this.getHBase().getForeignKeys(((SQLForeignKey)fks.get(0)).getFktable_db(), ((SQLForeignKey)fks.get(0)).getFktable_name());
         if (existing == null) {
            existing = new ArrayList(fks.size());
         }

         existing.addAll(fks);
         this.getHBase().putForeignKeys(existing);
         commit = true;
      } catch (IOException e) {
         LOG.error("Error writing foreign keys", e);
         throw new MetaException("Error writing foreign keys: " + e.getMessage());
      } finally {
         this.commitOrRoleBack(commit);
      }

   }

   public long updateParameterWithExpectedValue(Table table, String key, String expectedValue, String newValue) throws MetaException, NoSuchObjectException {
      throw new UnsupportedOperationException("This Store doesn't support updating table parameter with expected value");
   }

   private static class PrivilegeInfo {
      Database db;
      Table table;
      List grants;
      String typeErrMsg;
      PrincipalPrivilegeSet privSet;

      private PrivilegeInfo() {
      }
   }
}
