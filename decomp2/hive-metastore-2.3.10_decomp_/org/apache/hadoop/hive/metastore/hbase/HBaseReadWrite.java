package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterators;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hive.common.ObjectPair;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PrincipalPrivilegeSet;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hive.common.util.BloomFilter;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseReadWrite implements MetadataStore {
   static final String AGGR_STATS_TABLE = "HBMS_AGGR_STATS";
   static final String DB_TABLE = "HBMS_DBS";
   static final String FUNC_TABLE = "HBMS_FUNCS";
   static final String GLOBAL_PRIVS_TABLE = "HBMS_GLOBAL_PRIVS";
   static final String PART_TABLE = "HBMS_PARTITIONS";
   static final String ROLE_TABLE = "HBMS_ROLES";
   static final String SD_TABLE = "HBMS_SDS";
   static final String SECURITY_TABLE = "HBMS_SECURITY";
   static final String SEQUENCES_TABLE = "HBMS_SEQUENCES";
   static final String TABLE_TABLE = "HBMS_TBLS";
   static final String INDEX_TABLE = "HBMS_INDEX";
   static final String USER_TO_ROLE_TABLE = "HBMS_USER_TO_ROLE";
   static final String FILE_METADATA_TABLE = "HBMS_FILE_METADATA";
   static final byte[] CATALOG_CF;
   static final byte[] STATS_CF;
   static final String NO_CACHE_CONF = "no.use.cache";
   public static final String[] tableNames;
   public static final Map columnFamilies;
   static final byte[] MASTER_KEY_SEQUENCE;
   static final byte[] AGGR_STATS_BLOOM_COL;
   private static final byte[] AGGR_STATS_STATS_COL;
   private static final byte[] CATALOG_COL;
   private static final byte[] ROLES_COL;
   private static final byte[] REF_COUNT_COL;
   private static final byte[] DELEGATION_TOKEN_COL;
   private static final byte[] MASTER_KEY_COL;
   private static final byte[] PRIMARY_KEY_COL;
   private static final byte[] FOREIGN_KEY_COL;
   private static final byte[] GLOBAL_PRIVS_KEY;
   private static final byte[] SEQUENCES_KEY;
   private static final int TABLES_TO_CACHE = 10;
   private static final double STATS_BF_ERROR_RATE = 0.001;
   @VisibleForTesting
   static final String TEST_CONN = "test_connection";
   private static HBaseConnection testConn;
   private static final Logger LOG;
   private static ThreadLocal self;
   private static boolean tablesCreated;
   private static Configuration staticConf;
   private final Configuration conf;
   private HBaseConnection conn;
   private MessageDigest md;
   private ObjectCache tableCache;
   private ObjectCache sdCache;
   private PartitionCache partCache;
   private StatsCache statsCache;
   private Counter tableHits;
   private Counter tableMisses;
   private Counter tableOverflows;
   private Counter partHits;
   private Counter partMisses;
   private Counter partOverflows;
   private Counter sdHits;
   private Counter sdMisses;
   private Counter sdOverflows;
   private List counters;
   private final Map roleCache;
   boolean entireRoleTableInCache;

   public static synchronized void setConf(Configuration configuration) {
      if (staticConf == null) {
         staticConf = configuration;
      } else {
         LOG.info("Attempt to set conf when it has already been set.");
      }

   }

   static HBaseReadWrite getInstance() {
      if (staticConf == null) {
         throw new RuntimeException("Must set conf object before getting an instance");
      } else {
         return (HBaseReadWrite)self.get();
      }
   }

   public Configuration getConf() {
      return this.conf;
   }

   private HBaseReadWrite(Configuration configuration) {
      this.conf = configuration;
      HBaseConfiguration.addHbaseResources(this.conf);

      try {
         String connClass = HiveConf.getVar(this.conf, ConfVars.METASTORE_HBASE_CONNECTION_CLASS);
         if ("test_connection".equals(connClass)) {
            this.conn = testConn;
            LOG.debug("Using test connection.");
         } else {
            LOG.debug("Instantiating connection class " + connClass);
            Class c = Class.forName(connClass);
            Object o = c.newInstance();
            if (!HBaseConnection.class.isAssignableFrom(o.getClass())) {
               throw new IOException(connClass + " is not an instance of HBaseConnection.");
            }

            this.conn = (HBaseConnection)o;
            this.conn.setConf(this.conf);
            this.conn.connect();
         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

      try {
         this.md = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
      }

      int totalCatalogObjectsToCache = HiveConf.getIntVar(this.conf, ConfVars.METASTORE_HBASE_CATALOG_CACHE_SIZE);
      this.tableHits = new Counter("table cache hits");
      this.tableMisses = new Counter("table cache misses");
      this.tableOverflows = new Counter("table cache overflows");
      this.partHits = new Counter("partition cache hits");
      this.partMisses = new Counter("partition cache misses");
      this.partOverflows = new Counter("partition cache overflows");
      this.sdHits = new Counter("storage descriptor cache hits");
      this.sdMisses = new Counter("storage descriptor cache misses");
      this.sdOverflows = new Counter("storage descriptor cache overflows");
      this.counters = new ArrayList();
      this.counters.add(this.tableHits);
      this.counters.add(this.tableMisses);
      this.counters.add(this.tableOverflows);
      this.counters.add(this.partHits);
      this.counters.add(this.partMisses);
      this.counters.add(this.partOverflows);
      this.counters.add(this.sdHits);
      this.counters.add(this.sdMisses);
      this.counters.add(this.sdOverflows);
      int sdsCacheSize = totalCatalogObjectsToCache / 100;
      if (this.conf.getBoolean("no.use.cache", false)) {
         this.tableCache = new BogusObjectCache();
         this.sdCache = new BogusObjectCache();
         this.partCache = new BogusPartitionCache();
      } else {
         this.tableCache = new ObjectCache(10, this.tableHits, this.tableMisses, this.tableOverflows);
         this.sdCache = new ObjectCache(sdsCacheSize, this.sdHits, this.sdMisses, this.sdOverflows);
         this.partCache = new PartitionCache(totalCatalogObjectsToCache, this.partHits, this.partMisses, this.partOverflows);
      }

      this.statsCache = StatsCache.getInstance(this.conf);
      this.roleCache = new HashMap();
      this.entireRoleTableInCache = false;
   }

   static synchronized void createTablesIfNotExist() throws IOException {
      if (!tablesCreated) {
         for(String name : tableNames) {
            if (((HBaseReadWrite)self.get()).conn.getHBaseTable(name, true) == null) {
               List<byte[]> families = (List)columnFamilies.get(name);
               ((HBaseReadWrite)self.get()).conn.createHBaseTable(name, families);
            }
         }

         tablesCreated = true;
      }

   }

   void begin() {
      try {
         this.conn.beginTransaction();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   void commit() {
      try {
         this.conn.commitTransaction();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   void rollback() {
      try {
         this.conn.rollbackTransaction();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   void close() throws IOException {
      this.conn.close();
   }

   Database getDb(String name) throws IOException {
      byte[] key = HBaseUtils.buildKey(name);
      byte[] serialized = this.read("HBMS_DBS", key, CATALOG_CF, CATALOG_COL);
      return serialized == null ? null : HBaseUtils.deserializeDatabase(name, serialized);
   }

   List scanDatabases(String regex) throws IOException {
      Filter filter = null;
      if (regex != null) {
         filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      }

      Iterator<Result> iter = this.scan("HBMS_DBS", CATALOG_CF, CATALOG_COL, filter);
      List<Database> databases = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         databases.add(HBaseUtils.deserializeDatabase(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL)));
      }

      return databases;
   }

   void putDb(Database database) throws IOException {
      byte[][] serialized = HBaseUtils.serializeDatabase(database);
      this.store("HBMS_DBS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
   }

   void deleteDb(String name) throws IOException {
      byte[] key = HBaseUtils.buildKey(name);
      this.delete("HBMS_DBS", key, (byte[])null, (byte[])null);
   }

   String printDatabase(String name) throws IOException, TException {
      Database db = this.getDb(name);
      return db == null ? this.noSuch(name, "database") : this.dumpThriftObject(db);
   }

   List printDatabases(String regex) throws IOException, TException {
      List<Database> dbs = this.scanDatabases(regex);
      if (dbs.size() == 0) {
         return this.noMatch(regex, "database");
      } else {
         List<String> lines = new ArrayList();

         for(Database db : dbs) {
            lines.add(this.dumpThriftObject(db));
         }

         return lines;
      }
   }

   int getDatabaseCount() throws IOException {
      Filter fil = new FirstKeyOnlyFilter();
      Iterator<Result> iter = this.scan("HBMS_DBS", fil);
      return Iterators.size(iter);
   }

   Function getFunction(String dbName, String functionName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, functionName);
      byte[] serialized = this.read("HBMS_FUNCS", key, CATALOG_CF, CATALOG_COL);
      return serialized == null ? null : HBaseUtils.deserializeFunction(dbName, functionName, serialized);
   }

   List scanFunctions(String dbName, String regex) throws IOException {
      byte[] keyPrefix = null;
      if (dbName != null) {
         keyPrefix = HBaseUtils.buildKeyWithTrailingSeparator(dbName);
      }

      Filter filter = null;
      if (regex != null) {
         filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      }

      Iterator<Result> iter = this.scan("HBMS_FUNCS", keyPrefix, HBaseUtils.getEndPrefix(keyPrefix), CATALOG_CF, CATALOG_COL, filter);
      List<Function> functions = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         functions.add(HBaseUtils.deserializeFunction(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL)));
      }

      return functions;
   }

   void putFunction(Function function) throws IOException {
      byte[][] serialized = HBaseUtils.serializeFunction(function);
      this.store("HBMS_FUNCS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
   }

   void deleteFunction(String dbName, String functionName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, functionName);
      this.delete("HBMS_FUNCS", key, (byte[])null, (byte[])null);
   }

   String printFunction(String key) throws IOException, TException {
      byte[] k = HBaseUtils.buildKey(key);
      byte[] serialized = this.read("HBMS_FUNCS", k, CATALOG_CF, CATALOG_COL);
      if (serialized == null) {
         return this.noSuch(key, "function");
      } else {
         Function func = HBaseUtils.deserializeFunction(k, serialized);
         return this.dumpThriftObject(func);
      }
   }

   List printFunctions(String regex) throws IOException, TException {
      Filter filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      Iterator<Result> iter = this.scan("HBMS_FUNCS", (byte[])null, (byte[])null, CATALOG_CF, CATALOG_COL, filter);
      List<String> lines = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         lines.add(this.dumpThriftObject(HBaseUtils.deserializeFunction(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL))));
      }

      if (lines.size() == 0) {
         lines = this.noMatch(regex, "function");
      }

      return lines;
   }

   PrincipalPrivilegeSet getGlobalPrivs() throws IOException {
      byte[] key = GLOBAL_PRIVS_KEY;
      byte[] serialized = this.read("HBMS_GLOBAL_PRIVS", key, CATALOG_CF, CATALOG_COL);
      return serialized == null ? null : HBaseUtils.deserializePrincipalPrivilegeSet(serialized);
   }

   void putGlobalPrivs(PrincipalPrivilegeSet privs) throws IOException {
      byte[] key = GLOBAL_PRIVS_KEY;
      byte[] serialized = HBaseUtils.serializePrincipalPrivilegeSet(privs);
      this.store("HBMS_GLOBAL_PRIVS", key, CATALOG_CF, CATALOG_COL, serialized);
   }

   String printGlobalPrivs() throws IOException, TException {
      PrincipalPrivilegeSet pps = this.getGlobalPrivs();
      return pps == null ? "No global privileges" : this.dumpThriftObject(pps);
   }

   Partition getPartition(String dbName, String tableName, List partVals) throws IOException {
      return this.getPartition(dbName, tableName, partVals, true);
   }

   List getPartitions(String dbName, String tableName, List partTypes, List partValLists) throws IOException {
      List<Partition> parts = new ArrayList(partValLists.size());
      List<Get> gets = new ArrayList(partValLists.size());

      for(List partVals : partValLists) {
         byte[] key = HBaseUtils.buildPartitionKey(dbName, tableName, partTypes, partVals);
         Get get = new Get(key);
         get.addColumn(CATALOG_CF, CATALOG_COL);
         gets.add(get);
      }

      HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
      Result[] results = htab.get(gets);

      for(int i = 0; i < results.length; ++i) {
         HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializePartition(dbName, tableName, (List)partValLists.get(i), results[i].getValue(CATALOG_CF, CATALOG_COL));
         StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
         HBaseUtils.assembleStorageDescriptor(sd, sdParts);
         parts.add(sdParts.containingPartition);
      }

      return parts;
   }

   void putPartition(Partition partition) throws IOException {
      byte[] hash = this.putStorageDescriptor(partition.getSd());
      byte[][] serialized = HBaseUtils.serializePartition(partition, HBaseUtils.getPartitionKeyTypes(this.getTable(partition.getDbName(), partition.getTableName()).getPartitionKeys()), hash);
      this.store("HBMS_PARTITIONS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
      this.partCache.put(partition.getDbName(), partition.getTableName(), partition);
   }

   void replacePartition(Partition oldPart, Partition newPart, List partTypes) throws IOException {
      byte[] oldHash = HBaseUtils.hashStorageDescriptor(oldPart.getSd(), this.md);
      byte[] newHash = HBaseUtils.hashStorageDescriptor(newPart.getSd(), this.md);
      byte[] hash;
      if (Arrays.equals(oldHash, newHash)) {
         hash = oldHash;
      } else {
         this.decrementStorageDescriptorRefCount(oldPart.getSd());
         hash = this.putStorageDescriptor(newPart.getSd());
      }

      byte[][] serialized = HBaseUtils.serializePartition(newPart, HBaseUtils.getPartitionKeyTypes(this.getTable(newPart.getDbName(), newPart.getTableName()).getPartitionKeys()), hash);
      this.store("HBMS_PARTITIONS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
      this.partCache.put(newPart.getDbName(), newPart.getTableName(), newPart);
      if (!oldPart.getTableName().equals(newPart.getTableName())) {
         this.deletePartition(oldPart.getDbName(), oldPart.getTableName(), partTypes, oldPart.getValues());
      }

   }

   void putPartitions(List partitions) throws IOException {
      List<Put> puts = new ArrayList(partitions.size());

      for(Partition partition : partitions) {
         byte[] hash = this.putStorageDescriptor(partition.getSd());
         List<String> partTypes = HBaseUtils.getPartitionKeyTypes(this.getTable(partition.getDbName(), partition.getTableName()).getPartitionKeys());
         byte[][] serialized = HBaseUtils.serializePartition(partition, partTypes, hash);
         Put p = new Put(serialized[0]);
         p.add(CATALOG_CF, CATALOG_COL, serialized[1]);
         puts.add(p);
         this.partCache.put(partition.getDbName(), partition.getTableName(), partition);
      }

      HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
      htab.put(puts);
      this.conn.flush(htab);
   }

   void replacePartitions(List oldParts, List newParts, List oldPartTypes) throws IOException {
      if (oldParts.size() != newParts.size()) {
         throw new RuntimeException("Number of old and new partitions must match.");
      } else {
         List<Put> puts = new ArrayList(newParts.size());

         for(int i = 0; i < newParts.size(); ++i) {
            byte[] oldHash = HBaseUtils.hashStorageDescriptor(((Partition)oldParts.get(i)).getSd(), this.md);
            byte[] newHash = HBaseUtils.hashStorageDescriptor(((Partition)newParts.get(i)).getSd(), this.md);
            byte[] hash;
            if (Arrays.equals(oldHash, newHash)) {
               hash = oldHash;
            } else {
               this.decrementStorageDescriptorRefCount(((Partition)oldParts.get(i)).getSd());
               hash = this.putStorageDescriptor(((Partition)newParts.get(i)).getSd());
            }

            Partition newPart = (Partition)newParts.get(i);
            byte[][] serialized = HBaseUtils.serializePartition(newPart, HBaseUtils.getPartitionKeyTypes(this.getTable(newPart.getDbName(), newPart.getTableName()).getPartitionKeys()), hash);
            Put p = new Put(serialized[0]);
            p.add(CATALOG_CF, CATALOG_COL, serialized[1]);
            puts.add(p);
            this.partCache.put(((Partition)newParts.get(i)).getDbName(), ((Partition)newParts.get(i)).getTableName(), (Partition)newParts.get(i));
            if (!((Partition)newParts.get(i)).getTableName().equals(((Partition)oldParts.get(i)).getTableName())) {
               this.deletePartition(((Partition)oldParts.get(i)).getDbName(), ((Partition)oldParts.get(i)).getTableName(), oldPartTypes, ((Partition)oldParts.get(i)).getValues(), false);
            }
         }

         HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
         htab.put(puts);
         this.conn.flush(htab);
      }
   }

   List scanPartitionsInTable(String dbName, String tableName, int maxPartitions) throws IOException {
      if (maxPartitions < 0) {
         maxPartitions = Integer.MAX_VALUE;
      }

      Collection<Partition> cached = this.partCache.getAllForTable(dbName, tableName);
      if (cached != null) {
         return (List)(maxPartitions < cached.size() ? (new ArrayList(cached)).subList(0, maxPartitions) : new ArrayList(cached));
      } else {
         byte[] keyPrefix = HBaseUtils.buildPartitionKey(dbName, tableName, new ArrayList(), new ArrayList(), false);
         List<Partition> parts = this.scanPartitionsWithFilter(dbName, tableName, keyPrefix, HBaseUtils.getEndPrefix(keyPrefix), -1, (Filter)null);
         this.partCache.put(dbName, tableName, parts, true);
         return maxPartitions < parts.size() ? parts.subList(0, maxPartitions) : parts;
      }
   }

   List scanPartitions(String dbName, String tableName, List partVals, int maxPartitions) throws IOException, NoSuchObjectException {
      PartitionScanInfo psi = this.scanPartitionsInternal(dbName, tableName, partVals, maxPartitions);
      List<Partition> parts = this.scanPartitionsWithFilter(dbName, tableName, psi.keyPrefix, psi.endKeyPrefix, maxPartitions, psi.filter);
      this.partCache.put(dbName, tableName, parts, false);
      return parts;
   }

   List scanPartitions(String dbName, String tableName, byte[] keyStart, byte[] keyEnd, Filter filter, int maxPartitions) throws IOException, NoSuchObjectException {
      byte[] endRow;
      if (keyEnd != null && keyEnd.length != 0) {
         endRow = keyEnd;
      } else {
         endRow = HBaseUtils.getEndPrefix(keyStart);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Scanning partitions with start row <" + new String(keyStart) + "> and end row <" + new String(endRow) + ">");
      }

      return this.scanPartitionsWithFilter(dbName, tableName, keyStart, endRow, maxPartitions, filter);
   }

   void deletePartition(String dbName, String tableName, List partTypes, List partVals) throws IOException {
      this.deletePartition(dbName, tableName, partTypes, partVals, true);
   }

   String printPartition(String partKey) throws IOException, TException {
      String[] partKeyParts = partKey.split(HBaseUtils.KEY_SEPARATOR_STR);
      if (partKeyParts.length < 3) {
         return this.noSuch(partKey, "partition");
      } else {
         Table table = this.getTable(partKeyParts[0], partKeyParts[1]);
         if (table == null) {
            return this.noSuch(partKey, "partition");
         } else {
            byte[] key = HBaseUtils.buildPartitionKey(partKeyParts[0], partKeyParts[1], HBaseUtils.getPartitionKeyTypes(table.getPartitionKeys()), Arrays.asList(Arrays.copyOfRange(partKeyParts, 2, partKeyParts.length)));
            HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
            Get g = new Get(key);
            g.addColumn(CATALOG_CF, CATALOG_COL);
            g.addFamily(STATS_CF);
            Result result = htab.get(g);
            return result.isEmpty() ? this.noSuch(partKey, "partition") : this.printOnePartition(result);
         }
      }
   }

   List printPartitions(String partKey) throws IOException, TException {
      String[] partKeyParts = partKey.split("\\.");
      if (partKeyParts.length < 2) {
         return this.noMatch(partKey, "partition");
      } else {
         List<String> partVals = partKeyParts.length == 2 ? Arrays.asList("*") : Arrays.asList(Arrays.copyOfRange(partKeyParts, 2, partKeyParts.length));

         PartitionScanInfo psi;
         try {
            psi = this.scanPartitionsInternal(partKeyParts[0], partKeyParts[1], partVals, -1);
         } catch (NoSuchObjectException var9) {
            return this.noMatch(partKey, "partition");
         }

         HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
         Scan scan = new Scan();
         scan.addColumn(CATALOG_CF, CATALOG_COL);
         scan.addFamily(STATS_CF);
         scan.setStartRow(psi.keyPrefix);
         scan.setStopRow(psi.endKeyPrefix);
         scan.setFilter(psi.filter);
         Iterator<Result> iter = htab.getScanner(scan).iterator();
         if (!iter.hasNext()) {
            return this.noMatch(partKey, "partition");
         } else {
            List<String> lines = new ArrayList();

            while(iter.hasNext()) {
               lines.add(this.printOnePartition((Result)iter.next()));
            }

            return lines;
         }
      }
   }

   int getPartitionCount() throws IOException {
      Filter fil = new FirstKeyOnlyFilter();
      Iterator<Result> iter = this.scan("HBMS_PARTITIONS", fil);
      return Iterators.size(iter);
   }

   private String printOnePartition(Result result) throws IOException, TException {
      byte[] key = result.getRow();
      HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializePartition(key, result.getValue(CATALOG_CF, CATALOG_COL), this);
      StringBuilder builder = new StringBuilder();
      builder.append(this.dumpThriftObject(sdParts.containingPartition)).append(" sdHash: ").append(Base64.encodeBase64URLSafeString(sdParts.sdHash)).append(" stats:");
      NavigableMap<byte[], byte[]> statsCols = result.getFamilyMap(STATS_CF);

      for(Map.Entry statsCol : statsCols.entrySet()) {
         builder.append(" column ").append(new String((byte[])statsCol.getKey(), HBaseUtils.ENCODING)).append(": ");
         ColumnStatistics pcs = this.buildColStats(key, false);
         ColumnStatisticsObj cso = HBaseUtils.deserializeStatsForOneColumn(pcs, (byte[])statsCol.getValue());
         builder.append(this.dumpThriftObject(cso));
      }

      return builder.toString();
   }

   private void deletePartition(String dbName, String tableName, List partTypes, List partVals, boolean decrementRefCnt) throws IOException {
      this.partCache.remove(dbName, tableName, partVals);
      if (decrementRefCnt) {
         Partition p = this.getPartition(dbName, tableName, partVals, false);
         this.decrementStorageDescriptorRefCount(p.getSd());
      }

      byte[] key = HBaseUtils.buildPartitionKey(dbName, tableName, partTypes, partVals);
      this.delete("HBMS_PARTITIONS", key, (byte[])null, (byte[])null);
   }

   private Partition getPartition(String dbName, String tableName, List partVals, boolean populateCache) throws IOException {
      Partition cached = this.partCache.get(dbName, tableName, partVals);
      if (cached != null) {
         return cached;
      } else {
         byte[] key = HBaseUtils.buildPartitionKey(dbName, tableName, HBaseUtils.getPartitionKeyTypes(this.getTable(dbName, tableName).getPartitionKeys()), partVals);
         byte[] serialized = this.read("HBMS_PARTITIONS", key, CATALOG_CF, CATALOG_COL);
         if (serialized == null) {
            return null;
         } else {
            HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializePartition(dbName, tableName, partVals, serialized);
            StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
            HBaseUtils.assembleStorageDescriptor(sd, sdParts);
            if (populateCache) {
               this.partCache.put(dbName, tableName, sdParts.containingPartition);
            }

            return sdParts.containingPartition;
         }
      }
   }

   private PartitionScanInfo scanPartitionsInternal(String dbName, String tableName, List partVals, int maxPartitions) throws IOException, NoSuchObjectException {
      List<String> keyElements = new ArrayList();
      keyElements.add(dbName);
      keyElements.add(tableName);
      int firstStar = -1;

      for(int i = 0; i < partVals.size(); ++i) {
         if ("*".equals(partVals.get(i))) {
            firstStar = i;
            break;
         }

         if (((String)partVals.get(i)).equals("")) {
            break;
         }

         keyElements.add(partVals.get(i));
      }

      Table table = this.getTable(dbName, tableName);
      if (table == null) {
         throw new NoSuchObjectException("Unable to find table " + dbName + "." + tableName);
      } else {
         byte[] keyPrefix = HBaseUtils.buildPartitionKey(dbName, tableName, HBaseUtils.getPartitionKeyTypes(table.getPartitionKeys().subList(0, keyElements.size() - 2)), keyElements.subList(2, keyElements.size()));
         List<PartitionKeyComparator.Range> ranges = new ArrayList();
         List<PartitionKeyComparator.Operator> ops = new ArrayList();
         if (partVals.size() != table.getPartitionKeys().size() || firstStar != -1) {
            for(int i = Math.max(0, firstStar); i < table.getPartitionKeys().size() && i < partVals.size(); ++i) {
               if ("*".equals(partVals.get(i))) {
                  PartitionKeyComparator.Operator op = new PartitionKeyComparator.Operator(PartitionKeyComparator.Operator.Type.LIKE, ((FieldSchema)table.getPartitionKeys().get(i)).getName(), ".*");
                  ops.add(op);
               } else {
                  PartitionKeyComparator.Range range = new PartitionKeyComparator.Range(((FieldSchema)table.getPartitionKeys().get(i)).getName(), new PartitionKeyComparator.Mark((String)partVals.get(i), true), new PartitionKeyComparator.Mark((String)partVals.get(i), true));
                  ranges.add(range);
               }
            }
         }

         Filter filter = null;
         if (!ranges.isEmpty() || !ops.isEmpty()) {
            filter = new RowFilter(CompareOp.EQUAL, new PartitionKeyComparator(StringUtils.join(HBaseUtils.getPartitionNames(table.getPartitionKeys()), ","), StringUtils.join(HBaseUtils.getPartitionKeyTypes(table.getPartitionKeys()), ","), ranges, ops));
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Scanning partitions with prefix <" + new String(keyPrefix) + "> and filter <" + filter + ">");
         }

         return new PartitionScanInfo(dbName, tableName, keyPrefix, HBaseUtils.getEndPrefix(keyPrefix), maxPartitions, filter);
      }
   }

   private List scanPartitionsWithFilter(String dbName, String tableName, byte[] startRow, byte[] endRow, int maxResults, Filter filter) throws IOException {
      Iterator<Result> iter = this.scan("HBMS_PARTITIONS", startRow, endRow, CATALOG_CF, CATALOG_COL, filter);
      List<FieldSchema> tablePartitions = this.getTable(dbName, tableName).getPartitionKeys();
      List<Partition> parts = new ArrayList();
      int numToFetch = maxResults < 0 ? Integer.MAX_VALUE : maxResults;

      for(int i = 0; i < numToFetch && iter.hasNext(); ++i) {
         Result result = (Result)iter.next();
         HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializePartition(dbName, tableName, tablePartitions, result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL), this.conf);
         StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
         HBaseUtils.assembleStorageDescriptor(sd, sdParts);
         parts.add(sdParts.containingPartition);
      }

      return parts;
   }

   List getUserRoles(String userName) throws IOException {
      byte[] key = HBaseUtils.buildKey(userName);
      byte[] serialized = this.read("HBMS_USER_TO_ROLE", key, CATALOG_CF, CATALOG_COL);
      return serialized == null ? null : HBaseUtils.deserializeRoleList(serialized);
   }

   List getPrincipalDirectRoles(String name, PrincipalType type) throws IOException {
      this.buildRoleCache();
      Set<String> rolesFound = new HashSet();

      for(Map.Entry e : this.roleCache.entrySet()) {
         for(HbaseMetastoreProto.RoleGrantInfo giw : ((HbaseMetastoreProto.RoleGrantInfoList)e.getValue()).getGrantInfoList()) {
            if (HBaseUtils.convertPrincipalTypes(giw.getPrincipalType()) == type && giw.getPrincipalName().equals(name)) {
               rolesFound.add(e.getKey());
               break;
            }
         }
      }

      List<Role> directRoles = new ArrayList(rolesFound.size());
      List<Get> gets = new ArrayList();
      HTableInterface htab = this.conn.getHBaseTable("HBMS_ROLES");

      for(String roleFound : rolesFound) {
         byte[] key = HBaseUtils.buildKey(roleFound);
         Get g = new Get(key);
         g.addColumn(CATALOG_CF, CATALOG_COL);
         gets.add(g);
      }

      Result[] results = htab.get(gets);

      for(int i = 0; i < results.length; ++i) {
         byte[] serialized = results[i].getValue(CATALOG_CF, CATALOG_COL);
         if (serialized != null) {
            directRoles.add(HBaseUtils.deserializeRole(results[i].getRow(), serialized));
         }
      }

      return directRoles;
   }

   HbaseMetastoreProto.RoleGrantInfoList getRolePrincipals(String roleName) throws IOException, NoSuchObjectException {
      HbaseMetastoreProto.RoleGrantInfoList rolePrincipals = (HbaseMetastoreProto.RoleGrantInfoList)this.roleCache.get(roleName);
      if (rolePrincipals != null) {
         return rolePrincipals;
      } else {
         byte[] key = HBaseUtils.buildKey(roleName);
         byte[] serialized = this.read("HBMS_ROLES", key, CATALOG_CF, ROLES_COL);
         if (serialized == null) {
            return null;
         } else {
            rolePrincipals = HbaseMetastoreProto.RoleGrantInfoList.parseFrom(serialized);
            this.roleCache.put(roleName, rolePrincipals);
            return rolePrincipals;
         }
      }
   }

   Set findAllUsersInRole(String roleName) throws IOException {
      Set<String> users = new HashSet();
      Iterator<Result> iter = this.scan("HBMS_USER_TO_ROLE", CATALOG_CF, CATALOG_COL);

      while(iter.hasNext()) {
         Result result = (Result)iter.next();

         for(String rn : HBaseUtils.deserializeRoleList(result.getValue(CATALOG_CF, CATALOG_COL))) {
            if (rn.equals(roleName)) {
               users.add(new String(result.getRow(), HBaseUtils.ENCODING));
               break;
            }
         }
      }

      return users;
   }

   void addPrincipalToRole(String roleName, HbaseMetastoreProto.RoleGrantInfo grantInfo) throws IOException, NoSuchObjectException {
      HbaseMetastoreProto.RoleGrantInfoList proto = this.getRolePrincipals(roleName);
      List<HbaseMetastoreProto.RoleGrantInfo> rolePrincipals = new ArrayList();
      if (proto != null) {
         rolePrincipals.addAll(proto.getGrantInfoList());
      }

      rolePrincipals.add(grantInfo);
      proto = HbaseMetastoreProto.RoleGrantInfoList.newBuilder().addAllGrantInfo(rolePrincipals).build();
      byte[] key = HBaseUtils.buildKey(roleName);
      this.store("HBMS_ROLES", key, CATALOG_CF, ROLES_COL, proto.toByteArray());
      this.roleCache.put(roleName, proto);
   }

   void dropPrincipalFromRole(String roleName, String principalName, PrincipalType type, boolean grantOnly) throws NoSuchObjectException, IOException {
      HbaseMetastoreProto.RoleGrantInfoList proto = this.getRolePrincipals(roleName);
      if (proto != null) {
         List<HbaseMetastoreProto.RoleGrantInfo> rolePrincipals = new ArrayList();
         rolePrincipals.addAll(proto.getGrantInfoList());

         for(int i = 0; i < rolePrincipals.size(); ++i) {
            if (HBaseUtils.convertPrincipalTypes(((HbaseMetastoreProto.RoleGrantInfo)rolePrincipals.get(i)).getPrincipalType()) == type && ((HbaseMetastoreProto.RoleGrantInfo)rolePrincipals.get(i)).getPrincipalName().equals(principalName)) {
               if (grantOnly) {
                  rolePrincipals.set(i, HbaseMetastoreProto.RoleGrantInfo.newBuilder((HbaseMetastoreProto.RoleGrantInfo)rolePrincipals.get(i)).setGrantOption(false).build());
               } else {
                  rolePrincipals.remove(i);
               }
               break;
            }
         }

         byte[] key = HBaseUtils.buildKey(roleName);
         proto = HbaseMetastoreProto.RoleGrantInfoList.newBuilder().addAllGrantInfo(rolePrincipals).build();
         this.store("HBMS_ROLES", key, CATALOG_CF, ROLES_COL, proto.toByteArray());
         this.roleCache.put(roleName, proto);
      }
   }

   void buildRoleMapForUser(String userName) throws IOException, NoSuchObjectException {
      this.buildRoleCache();
      LOG.debug("Building role map for " + userName);
      Set<String> rolesToAdd = new HashSet();
      Set<String> rolesToCheckNext = new HashSet();

      for(Map.Entry e : this.roleCache.entrySet()) {
         for(HbaseMetastoreProto.RoleGrantInfo grantInfo : ((HbaseMetastoreProto.RoleGrantInfoList)e.getValue()).getGrantInfoList()) {
            if (HBaseUtils.convertPrincipalTypes(grantInfo.getPrincipalType()) == PrincipalType.USER && userName.equals(grantInfo.getPrincipalName())) {
               rolesToAdd.add(e.getKey());
               rolesToCheckNext.add(e.getKey());
               LOG.debug("Adding " + (String)e.getKey() + " to list of roles user is in directly");
               break;
            }
         }
      }

      Set<String> tmpRolesToCheckNext;
      for(; rolesToCheckNext.size() > 0; rolesToCheckNext = tmpRolesToCheckNext) {
         tmpRolesToCheckNext = new HashSet();

         for(String roleName : rolesToCheckNext) {
            HbaseMetastoreProto.RoleGrantInfoList grantInfos = (HbaseMetastoreProto.RoleGrantInfoList)this.roleCache.get(roleName);
            if (grantInfos != null) {
               for(HbaseMetastoreProto.RoleGrantInfo grantInfo : grantInfos.getGrantInfoList()) {
                  if (HBaseUtils.convertPrincipalTypes(grantInfo.getPrincipalType()) == PrincipalType.ROLE && rolesToAdd.add(grantInfo.getPrincipalName())) {
                     tmpRolesToCheckNext.add(grantInfo.getPrincipalName());
                     LOG.debug("Adding " + grantInfo.getPrincipalName() + " to list of roles user is in indirectly");
                  }
               }
            }
         }
      }

      byte[] key = HBaseUtils.buildKey(userName);
      byte[] serialized = HBaseUtils.serializeRoleList(new ArrayList(rolesToAdd));
      this.store("HBMS_USER_TO_ROLE", key, CATALOG_CF, CATALOG_COL, serialized);
   }

   void removeRoleGrants(String roleName) throws IOException {
      this.buildRoleCache();
      List<Put> puts = new ArrayList();

      for(Map.Entry e : this.roleCache.entrySet()) {
         boolean madeAChange = false;
         List<HbaseMetastoreProto.RoleGrantInfo> rgil = new ArrayList();
         rgil.addAll(((HbaseMetastoreProto.RoleGrantInfoList)e.getValue()).getGrantInfoList());

         for(int i = 0; i < rgil.size(); ++i) {
            if (HBaseUtils.convertPrincipalTypes(((HbaseMetastoreProto.RoleGrantInfo)rgil.get(i)).getPrincipalType()) == PrincipalType.ROLE && ((HbaseMetastoreProto.RoleGrantInfo)rgil.get(i)).getPrincipalName().equals(roleName)) {
               rgil.remove(i);
               madeAChange = true;
               break;
            }
         }

         if (madeAChange) {
            Put put = new Put(HBaseUtils.buildKey((String)e.getKey()));
            HbaseMetastoreProto.RoleGrantInfoList proto = HbaseMetastoreProto.RoleGrantInfoList.newBuilder().addAllGrantInfo(rgil).build();
            put.add(CATALOG_CF, ROLES_COL, proto.toByteArray());
            puts.add(put);
            this.roleCache.put(e.getKey(), proto);
         }
      }

      if (puts.size() > 0) {
         HTableInterface htab = this.conn.getHBaseTable("HBMS_ROLES");
         htab.put(puts);
         this.conn.flush(htab);
      }

      PrincipalPrivilegeSet global = this.getGlobalPrivs();
      if (global != null && global.getRolePrivileges() != null && global.getRolePrivileges().remove(roleName) != null) {
         this.putGlobalPrivs(global);
      }

      puts.clear();
      List<Database> dbs = this.scanDatabases((String)null);
      if (dbs == null) {
         dbs = new ArrayList();
      }

      for(Database db : dbs) {
         if (db.getPrivileges() != null && db.getPrivileges().getRolePrivileges() != null && db.getPrivileges().getRolePrivileges().remove(roleName) != null) {
            byte[][] serialized = HBaseUtils.serializeDatabase(db);
            Put put = new Put(serialized[0]);
            put.add(CATALOG_CF, CATALOG_COL, serialized[1]);
            puts.add(put);
         }
      }

      if (puts.size() > 0) {
         HTableInterface htab = this.conn.getHBaseTable("HBMS_DBS");
         htab.put(puts);
         this.conn.flush(htab);
      }

      puts.clear();

      for(Database db : dbs) {
         List<Table> tables = this.scanTables(db.getName(), (String)null);
         if (tables != null) {
            for(Table table : tables) {
               if (table.getPrivileges() != null && table.getPrivileges().getRolePrivileges() != null && table.getPrivileges().getRolePrivileges().remove(roleName) != null) {
                  byte[][] serialized = HBaseUtils.serializeTable(table, HBaseUtils.hashStorageDescriptor(table.getSd(), this.md));
                  Put put = new Put(serialized[0]);
                  put.add(CATALOG_CF, CATALOG_COL, serialized[1]);
                  puts.add(put);
               }
            }
         }
      }

      if (puts.size() > 0) {
         HTableInterface htab = this.conn.getHBaseTable("HBMS_TBLS");
         htab.put(puts);
         this.conn.flush(htab);
      }

   }

   Role getRole(String roleName) throws IOException {
      byte[] key = HBaseUtils.buildKey(roleName);
      byte[] serialized = this.read("HBMS_ROLES", key, CATALOG_CF, CATALOG_COL);
      return serialized == null ? null : HBaseUtils.deserializeRole(roleName, serialized);
   }

   List scanRoles() throws IOException {
      return this.scanRoles((String)null);
   }

   void putRole(Role role) throws IOException {
      byte[][] serialized = HBaseUtils.serializeRole(role);
      this.store("HBMS_ROLES", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
   }

   void deleteRole(String roleName) throws IOException {
      byte[] key = HBaseUtils.buildKey(roleName);
      this.delete("HBMS_ROLES", key, (byte[])null, (byte[])null);
      this.roleCache.remove(roleName);
   }

   String printRolesForUser(String userName) throws IOException {
      List<String> roles = this.getUserRoles(userName);
      return roles != null && roles.size() != 0 ? StringUtils.join(roles, ',') : this.noSuch(userName, "user");
   }

   List printRolesForUsers(String regex) throws IOException {
      Filter filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      Iterator<Result> iter = this.scan("HBMS_USER_TO_ROLE", (byte[])null, (byte[])null, CATALOG_CF, CATALOG_COL, filter);
      List<String> lines = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         lines.add(new String(result.getRow(), HBaseUtils.ENCODING) + ": " + StringUtils.join(HBaseUtils.deserializeRoleList(result.getValue(CATALOG_CF, CATALOG_COL)), ','));
      }

      if (lines.size() == 0) {
         lines = this.noMatch(regex, "user");
      }

      return lines;
   }

   String printRole(String name) throws IOException, TException {
      Role role = this.getRole(name);
      return role == null ? this.noSuch(name, "role") : this.dumpThriftObject(role);
   }

   List printRoles(String regex) throws IOException, TException {
      List<Role> roles = this.scanRoles(regex);
      if (roles.size() == 0) {
         return this.noMatch(regex, "role");
      } else {
         List<String> lines = new ArrayList();

         for(Role role : roles) {
            lines.add(this.dumpThriftObject(role));
         }

         return lines;
      }
   }

   private List scanRoles(String regex) throws IOException {
      Filter filter = null;
      if (regex != null) {
         filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      }

      Iterator<Result> iter = this.scan("HBMS_ROLES", (byte[])null, (byte[])null, CATALOG_CF, CATALOG_COL, filter);
      List<Role> roles = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         roles.add(HBaseUtils.deserializeRole(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL)));
      }

      return roles;
   }

   private void buildRoleCache() throws IOException {
      if (!this.entireRoleTableInCache) {
         Iterator<Result> roles = this.scan("HBMS_ROLES", CATALOG_CF, ROLES_COL);

         while(roles.hasNext()) {
            Result res = (Result)roles.next();
            String roleName = new String(res.getRow(), HBaseUtils.ENCODING);
            HbaseMetastoreProto.RoleGrantInfoList grantInfos = HbaseMetastoreProto.RoleGrantInfoList.parseFrom(res.getValue(CATALOG_CF, ROLES_COL));
            this.roleCache.put(roleName, grantInfos);
         }

         this.entireRoleTableInCache = true;
      }

   }

   Table getTable(String dbName, String tableName) throws IOException {
      return this.getTable(dbName, tableName, true);
   }

   List getTables(String dbName, List tableNames) throws IOException {
      List<Table> results = new ArrayList(tableNames.size());
      ObjectPair<String, String>[] hashKeys = new ObjectPair[tableNames.size()];
      boolean atLeastOneMissing = false;

      for(int i = 0; i < tableNames.size(); ++i) {
         hashKeys[i] = new ObjectPair(dbName, tableNames.get(i));
         results.add(this.tableCache.get(hashKeys[i]));
         if (results.get(i) == null) {
            atLeastOneMissing = true;
         }
      }

      if (!atLeastOneMissing) {
         return results;
      } else {
         List<Get> gets = new ArrayList();
         HTableInterface htab = this.conn.getHBaseTable("HBMS_TBLS");

         for(int i = 0; i < tableNames.size(); ++i) {
            if (results.get(i) == null) {
               byte[] key = HBaseUtils.buildKey(dbName, (String)tableNames.get(i));
               Get g = new Get(key);
               g.addColumn(CATALOG_CF, CATALOG_COL);
               gets.add(g);
            }
         }

         Result[] res = htab.get(gets);
         int i = 0;

         for(int nextGet = 0; i < tableNames.size(); ++i) {
            if (results.get(i) == null) {
               byte[] serialized = res[nextGet++].getValue(CATALOG_CF, CATALOG_COL);
               if (serialized != null) {
                  HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeTable(dbName, (String)tableNames.get(i), serialized);
                  StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
                  HBaseUtils.assembleStorageDescriptor(sd, sdParts);
                  this.tableCache.put(hashKeys[i], sdParts.containingTable);
                  results.set(i, sdParts.containingTable);
               }
            }
         }

         return results;
      }
   }

   List scanTables(String dbName, String regex) throws IOException {
      byte[] keyPrefix = null;
      if (dbName != null) {
         keyPrefix = HBaseUtils.buildKeyWithTrailingSeparator(dbName);
      }

      Filter filter = null;
      if (regex != null) {
         filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      }

      Iterator<Result> iter = this.scan("HBMS_TBLS", keyPrefix, HBaseUtils.getEndPrefix(keyPrefix), CATALOG_CF, CATALOG_COL, filter);
      List<Table> tables = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeTable(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL));
         StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
         HBaseUtils.assembleStorageDescriptor(sd, sdParts);
         tables.add(sdParts.containingTable);
      }

      return tables;
   }

   void putTable(Table table) throws IOException {
      byte[] hash = this.putStorageDescriptor(table.getSd());
      byte[][] serialized = HBaseUtils.serializeTable(table, hash);
      this.store("HBMS_TBLS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
      this.tableCache.put(new ObjectPair(table.getDbName(), table.getTableName()), table);
   }

   void replaceTable(Table oldTable, Table newTable) throws IOException {
      byte[] oldHash = HBaseUtils.hashStorageDescriptor(oldTable.getSd(), this.md);
      byte[] newHash = HBaseUtils.hashStorageDescriptor(newTable.getSd(), this.md);
      byte[] hash;
      if (Arrays.equals(oldHash, newHash)) {
         hash = oldHash;
      } else {
         this.decrementStorageDescriptorRefCount(oldTable.getSd());
         hash = this.putStorageDescriptor(newTable.getSd());
      }

      byte[][] serialized = HBaseUtils.serializeTable(newTable, hash);
      this.store("HBMS_TBLS", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
      this.tableCache.put(new ObjectPair(newTable.getDbName(), newTable.getTableName()), newTable);
      if (!oldTable.getTableName().equals(newTable.getTableName())) {
         this.deleteTable(oldTable.getDbName(), oldTable.getTableName());
      }

   }

   void deleteTable(String dbName, String tableName) throws IOException {
      this.deleteTable(dbName, tableName, true);
   }

   String printTable(String name) throws IOException, TException {
      byte[] key = HBaseUtils.buildKey(name);
      HTableInterface htab = this.conn.getHBaseTable("HBMS_TBLS");
      Get g = new Get(key);
      g.addColumn(CATALOG_CF, CATALOG_COL);
      g.addFamily(STATS_CF);
      Result result = htab.get(g);
      return result.isEmpty() ? this.noSuch(name, "table") : this.printOneTable(result);
   }

   List printTables(String regex) throws IOException, TException {
      Filter filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator(regex));
      HTableInterface htab = this.conn.getHBaseTable("HBMS_TBLS");
      Scan scan = new Scan();
      scan.addColumn(CATALOG_CF, CATALOG_COL);
      scan.addFamily(STATS_CF);
      scan.setFilter(filter);
      Iterator<Result> iter = htab.getScanner(scan).iterator();
      if (!iter.hasNext()) {
         return this.noMatch(regex, "table");
      } else {
         List<String> lines = new ArrayList();

         while(iter.hasNext()) {
            lines.add(this.printOneTable((Result)iter.next()));
         }

         return lines;
      }
   }

   int getTableCount() throws IOException {
      Filter fil = new FirstKeyOnlyFilter();
      Iterator<Result> iter = this.scan("HBMS_TBLS", fil);
      return Iterators.size(iter);
   }

   private String printOneTable(Result result) throws IOException, TException {
      byte[] key = result.getRow();
      HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeTable(key, result.getValue(CATALOG_CF, CATALOG_COL));
      StringBuilder builder = new StringBuilder();
      builder.append(this.dumpThriftObject(sdParts.containingTable)).append(" sdHash: ").append(Base64.encodeBase64URLSafeString(sdParts.sdHash)).append(" stats:");
      NavigableMap<byte[], byte[]> statsCols = result.getFamilyMap(STATS_CF);

      for(Map.Entry statsCol : statsCols.entrySet()) {
         builder.append(" column ").append(new String((byte[])statsCol.getKey(), HBaseUtils.ENCODING)).append(": ");
         ColumnStatistics pcs = this.buildColStats(key, true);
         ColumnStatisticsObj cso = HBaseUtils.deserializeStatsForOneColumn(pcs, (byte[])statsCol.getValue());
         builder.append(this.dumpThriftObject(cso));
      }

      List<SQLPrimaryKey> pk = this.getPrimaryKey(sdParts.containingTable.getDbName(), sdParts.containingTable.getTableName());
      if (pk != null && pk.size() > 0) {
         builder.append(" primary key: ");

         for(SQLPrimaryKey pkcol : pk) {
            builder.append(this.dumpThriftObject(pkcol));
         }
      }

      List<SQLForeignKey> fks = this.getForeignKeys(sdParts.containingTable.getDbName(), sdParts.containingTable.getTableName());
      if (fks != null && fks.size() > 0) {
         builder.append(" foreign keys: ");

         for(SQLForeignKey fkcol : fks) {
            builder.append(this.dumpThriftObject(fkcol));
         }
      }

      return builder.toString();
   }

   private void deleteTable(String dbName, String tableName, boolean decrementRefCnt) throws IOException {
      this.tableCache.remove(new ObjectPair(dbName, tableName));
      if (decrementRefCnt) {
         Table t = this.getTable(dbName, tableName, false);
         this.decrementStorageDescriptorRefCount(t.getSd());
      }

      byte[] key = HBaseUtils.buildKey(dbName, tableName);
      this.delete("HBMS_TBLS", key, (byte[])null, (byte[])null);
   }

   private Table getTable(String dbName, String tableName, boolean populateCache) throws IOException {
      ObjectPair<String, String> hashKey = new ObjectPair(dbName, tableName);
      Table cached = (Table)this.tableCache.get(hashKey);
      if (cached != null) {
         return cached;
      } else {
         byte[] key = HBaseUtils.buildKey(dbName, tableName);
         byte[] serialized = this.read("HBMS_TBLS", key, CATALOG_CF, CATALOG_COL);
         if (serialized == null) {
            return null;
         } else {
            HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeTable(dbName, tableName, serialized);
            StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
            HBaseUtils.assembleStorageDescriptor(sd, sdParts);
            if (populateCache) {
               this.tableCache.put(hashKey, sdParts.containingTable);
            }

            return sdParts.containingTable;
         }
      }
   }

   void putIndex(Index index) throws IOException {
      byte[] hash = this.putStorageDescriptor(index.getSd());
      byte[][] serialized = HBaseUtils.serializeIndex(index, hash);
      this.store("HBMS_INDEX", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
   }

   Index getIndex(String dbName, String origTableName, String indexName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, origTableName, indexName);
      byte[] serialized = this.read("HBMS_INDEX", key, CATALOG_CF, CATALOG_COL);
      if (serialized == null) {
         return null;
      } else {
         HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeIndex(dbName, origTableName, indexName, serialized);
         StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
         HBaseUtils.assembleStorageDescriptor(sd, sdParts);
         return sdParts.containingIndex;
      }
   }

   void deleteIndex(String dbName, String origTableName, String indexName) throws IOException {
      this.deleteIndex(dbName, origTableName, indexName, true);
   }

   void deleteIndex(String dbName, String origTableName, String indexName, boolean decrementRefCnt) throws IOException {
      if (decrementRefCnt) {
         Index index = this.getIndex(dbName, origTableName, indexName);
         this.decrementStorageDescriptorRefCount(index.getSd());
      }

      byte[] key = HBaseUtils.buildKey(dbName, origTableName, indexName);
      this.delete("HBMS_INDEX", key, (byte[])null, (byte[])null);
   }

   List scanIndexes(String dbName, String origTableName, int maxResults) throws IOException {
      byte[] keyPrefix = null;
      if (dbName != null) {
         keyPrefix = HBaseUtils.buildKeyWithTrailingSeparator(dbName, origTableName);
      }

      Iterator<Result> iter = this.scan("HBMS_INDEX", keyPrefix, HBaseUtils.getEndPrefix(keyPrefix), CATALOG_CF, CATALOG_COL, (Filter)null);
      List<Index> indexes = new ArrayList();
      int numToFetch = maxResults < 0 ? Integer.MAX_VALUE : maxResults;

      for(int i = 0; i < numToFetch && iter.hasNext(); ++i) {
         Result result = (Result)iter.next();
         HBaseUtils.StorageDescriptorParts sdParts = HBaseUtils.deserializeIndex(result.getRow(), result.getValue(CATALOG_CF, CATALOG_COL));
         StorageDescriptor sd = this.getStorageDescriptor(sdParts.sdHash);
         HBaseUtils.assembleStorageDescriptor(sd, sdParts);
         indexes.add(sdParts.containingIndex);
      }

      return indexes;
   }

   void replaceIndex(Index oldIndex, Index newIndex) throws IOException {
      byte[] oldHash = HBaseUtils.hashStorageDescriptor(oldIndex.getSd(), this.md);
      byte[] newHash = HBaseUtils.hashStorageDescriptor(newIndex.getSd(), this.md);
      byte[] hash;
      if (Arrays.equals(oldHash, newHash)) {
         hash = oldHash;
      } else {
         this.decrementStorageDescriptorRefCount(oldIndex.getSd());
         hash = this.putStorageDescriptor(newIndex.getSd());
      }

      byte[][] serialized = HBaseUtils.serializeIndex(newIndex, hash);
      this.store("HBMS_INDEX", serialized[0], CATALOG_CF, CATALOG_COL, serialized[1]);
      if (!oldIndex.getDbName().equals(newIndex.getDbName()) || !oldIndex.getOrigTableName().equals(newIndex.getOrigTableName()) || !oldIndex.getIndexName().equals(newIndex.getIndexName())) {
         this.deleteIndex(oldIndex.getDbName(), oldIndex.getOrigTableName(), oldIndex.getIndexName(), false);
      }

   }

   StorageDescriptor getStorageDescriptor(byte[] hash) throws IOException {
      ByteArrayWrapper hashKey = new ByteArrayWrapper(hash);
      StorageDescriptor cached = (StorageDescriptor)this.sdCache.get(hashKey);
      if (cached != null) {
         return cached;
      } else {
         LOG.debug("Not found in cache, looking in hbase");
         byte[] serialized = this.read("HBMS_SDS", hash, CATALOG_CF, CATALOG_COL);
         if (serialized == null) {
            throw new RuntimeException("Woh, bad!  Trying to fetch a non-existent storage descriptor from hash " + Base64.encodeBase64String(hash));
         } else {
            StorageDescriptor sd = HBaseUtils.deserializeStorageDescriptor(serialized);
            this.sdCache.put(hashKey, sd);
            return sd;
         }
      }
   }

   void decrementStorageDescriptorRefCount(StorageDescriptor sd) throws IOException {
      byte[] key = HBaseUtils.hashStorageDescriptor(sd, this.md);
      byte[] serializedRefCnt = this.read("HBMS_SDS", key, CATALOG_CF, REF_COUNT_COL);
      if (serializedRefCnt != null) {
         int refCnt = Integer.parseInt(new String(serializedRefCnt, HBaseUtils.ENCODING));
         HTableInterface htab = this.conn.getHBaseTable("HBMS_SDS");
         --refCnt;
         if (refCnt < 1) {
            Delete d = new Delete(key);
            htab.delete(d);
            this.sdCache.remove(new ByteArrayWrapper(key));
         } else {
            Put p = new Put(key);
            p.add(CATALOG_CF, REF_COUNT_COL, Integer.toString(refCnt).getBytes(HBaseUtils.ENCODING));
            htab.put(p);
            this.conn.flush(htab);
         }

      }
   }

   byte[] putStorageDescriptor(StorageDescriptor storageDescriptor) throws IOException {
      byte[] sd = HBaseUtils.serializeStorageDescriptor(storageDescriptor);
      byte[] key = HBaseUtils.hashStorageDescriptor(storageDescriptor, this.md);
      byte[] serializedRefCnt = this.read("HBMS_SDS", key, CATALOG_CF, REF_COUNT_COL);
      HTableInterface htab = this.conn.getHBaseTable("HBMS_SDS");
      if (serializedRefCnt == null) {
         Put p = new Put(key);
         p.add(CATALOG_CF, CATALOG_COL, sd);
         p.add(CATALOG_CF, REF_COUNT_COL, "1".getBytes(HBaseUtils.ENCODING));
         htab.put(p);
         this.sdCache.put(new ByteArrayWrapper(key), storageDescriptor);
      } else {
         int refCnt = Integer.parseInt(new String(serializedRefCnt, HBaseUtils.ENCODING)) + 1;
         Put p = new Put(key);
         p.add(CATALOG_CF, REF_COUNT_COL, Integer.toString(refCnt).getBytes(HBaseUtils.ENCODING));
         htab.put(p);
      }

      this.conn.flush(htab);
      return key;
   }

   String printStorageDescriptor(byte[] hash) throws IOException, TException {
      byte[] serialized = this.read("HBMS_SDS", hash, CATALOG_CF, CATALOG_COL);
      return serialized == null ? this.noSuch(Base64.encodeBase64URLSafeString(hash), "storage descriptor") : this.dumpThriftObject(HBaseUtils.deserializeStorageDescriptor(serialized));
   }

   List printStorageDescriptors() throws IOException, TException {
      Iterator<Result> results = this.scan("HBMS_SDS", CATALOG_CF, CATALOG_COL);
      if (!results.hasNext()) {
         return Arrays.asList("No storage descriptors");
      } else {
         List<String> lines = new ArrayList();

         while(results.hasNext()) {
            Result result = (Result)results.next();
            lines.add(Base64.encodeBase64URLSafeString(result.getRow()) + ": " + this.dumpThriftObject(HBaseUtils.deserializeStorageDescriptor(result.getValue(CATALOG_CF, CATALOG_COL))));
         }

         return lines;
      }
   }

   void updateStatistics(String dbName, String tableName, List partVals, ColumnStatistics stats) throws IOException {
      byte[] key = this.getStatisticsKey(dbName, tableName, partVals);
      String hbaseTable = this.getStatisticsTable(partVals);
      byte[][] colnames = new byte[stats.getStatsObjSize()][];
      byte[][] serialized = new byte[stats.getStatsObjSize()][];

      for(int i = 0; i < stats.getStatsObjSize(); ++i) {
         ColumnStatisticsObj obj = (ColumnStatisticsObj)stats.getStatsObj().get(i);
         serialized[i] = HBaseUtils.serializeStatsForOneColumn(stats, obj);
         String colname = obj.getColName();
         colnames[i] = HBaseUtils.buildKey(colname);
      }

      this.store(hbaseTable, key, STATS_CF, colnames, serialized);
   }

   ColumnStatistics getTableStatistics(String dbName, String tblName, List colNames) throws IOException {
      byte[] tabKey = HBaseUtils.buildKey(dbName, tblName);
      ColumnStatistics tableStats = new ColumnStatistics();
      ColumnStatisticsDesc statsDesc = new ColumnStatisticsDesc();
      statsDesc.setIsTblLevel(true);
      statsDesc.setDbName(dbName);
      statsDesc.setTableName(tblName);
      tableStats.setStatsDesc(statsDesc);
      byte[][] colKeys = new byte[colNames.size()][];

      for(int i = 0; i < colKeys.length; ++i) {
         colKeys[i] = HBaseUtils.buildKey((String)colNames.get(i));
      }

      Result result = this.read("HBMS_TBLS", tabKey, STATS_CF, colKeys);

      for(int i = 0; i < colKeys.length; ++i) {
         byte[] serializedColStats = result.getValue(STATS_CF, colKeys[i]);
         if (serializedColStats != null) {
            ColumnStatisticsObj obj = HBaseUtils.deserializeStatsForOneColumn(tableStats, serializedColStats);
            obj.setColName((String)colNames.get(i));
            tableStats.addToStatsObj(obj);
         }
      }

      return tableStats;
   }

   List getPartitionStatistics(String dbName, String tblName, List partNames, List partVals, List colNames) throws IOException {
      List<ColumnStatistics> statsList = new ArrayList(partNames.size());
      Map<List<String>, String> valToPartMap = new HashMap(partNames.size());
      List<Get> gets = new ArrayList(partNames.size() * colNames.size());

      assert partNames.size() == partVals.size();

      byte[][] colNameBytes = new byte[colNames.size()][];

      for(int i = 0; i < colNames.size(); ++i) {
         colNameBytes[i] = HBaseUtils.buildKey((String)colNames.get(i));
      }

      for(int i = 0; i < partNames.size(); ++i) {
         valToPartMap.put(partVals.get(i), partNames.get(i));
         byte[] partKey = HBaseUtils.buildPartitionKey(dbName, tblName, HBaseUtils.getPartitionKeyTypes(this.getTable(dbName, tblName).getPartitionKeys()), (List)partVals.get(i));
         Get get = new Get(partKey);

         for(byte[] colName : colNameBytes) {
            get.addColumn(STATS_CF, colName);
         }

         gets.add(get);
      }

      HTableInterface htab = this.conn.getHBaseTable("HBMS_PARTITIONS");
      Result[] results = htab.get(gets);

      for(int i = 0; i < results.length; ++i) {
         ColumnStatistics colStats = null;

         for(int j = 0; j < colNameBytes.length; ++j) {
            byte[] serializedColStats = results[i].getValue(STATS_CF, colNameBytes[j]);
            if (serializedColStats != null) {
               if (colStats == null) {
                  colStats = this.buildColStats(results[i].getRow(), false);
                  statsList.add(colStats);
               }

               ColumnStatisticsObj cso = HBaseUtils.deserializeStatsForOneColumn(colStats, serializedColStats);
               cso.setColName((String)colNames.get(j));
               colStats.addToStatsObj(cso);
            }
         }
      }

      return statsList;
   }

   StatsCache getStatsCache() {
      return this.statsCache;
   }

   AggrStats getAggregatedStats(byte[] key) throws IOException {
      byte[] serialized = this.read("HBMS_AGGR_STATS", key, CATALOG_CF, AGGR_STATS_STATS_COL);
      return serialized == null ? null : HBaseUtils.deserializeAggrStats(serialized);
   }

   void putAggregatedStats(byte[] key, String dbName, String tableName, List partNames, String colName, AggrStats stats) throws IOException {
      List<String> protoNames = new ArrayList(partNames.size() + 3);
      protoNames.add(dbName);
      protoNames.add(tableName);
      protoNames.add(colName);
      protoNames.addAll(partNames);
      BloomFilter bloom = new BloomFilter((long)partNames.size(), 0.001);

      for(String partName : partNames) {
         bloom.add(partName.getBytes(HBaseUtils.ENCODING));
      }

      byte[] serializedFilter = HBaseUtils.serializeBloomFilter(dbName, tableName, bloom);
      byte[] serializedStats = HBaseUtils.serializeAggrStats(stats);
      this.store("HBMS_AGGR_STATS", key, CATALOG_CF, new byte[][]{AGGR_STATS_BLOOM_COL, AGGR_STATS_STATS_COL}, new byte[][]{serializedFilter, serializedStats});
   }

   List invalidateAggregatedStats(HbaseMetastoreProto.AggrStatsInvalidatorFilter filter) throws IOException {
      Iterator<Result> results = this.scan("HBMS_AGGR_STATS", new AggrStatsInvalidatorFilter(filter));
      if (!results.hasNext()) {
         return Collections.emptyList();
      } else {
         List<Delete> deletes = new ArrayList();
         List<StatsCache.StatsCacheKey> keys = new ArrayList();

         while(results.hasNext()) {
            Result result = (Result)results.next();
            deletes.add(new Delete(result.getRow()));
            keys.add(new StatsCache.StatsCacheKey(result.getRow()));
         }

         HTableInterface htab = this.conn.getHBaseTable("HBMS_AGGR_STATS");
         htab.delete(deletes);
         return keys;
      }
   }

   private byte[] getStatisticsKey(String dbName, String tableName, List partVals) throws IOException {
      return partVals == null ? HBaseUtils.buildKey(dbName, tableName) : HBaseUtils.buildPartitionKey(dbName, tableName, HBaseUtils.getPartitionKeyTypes(this.getTable(dbName, tableName).getPartitionKeys()), partVals);
   }

   private String getStatisticsTable(List partVals) {
      return partVals == null ? "HBMS_TBLS" : "HBMS_PARTITIONS";
   }

   private ColumnStatistics buildColStats(byte[] key, boolean fromTable) throws IOException {
      ColumnStatistics colStats = new ColumnStatistics();
      ColumnStatisticsDesc csd = new ColumnStatisticsDesc();
      List<String> reconstructedKey;
      if (fromTable) {
         reconstructedKey = Arrays.asList(HBaseUtils.deserializeKey(key));
         csd.setIsTblLevel(true);
      } else {
         reconstructedKey = HBaseUtils.deserializePartitionKey(key, this);
         csd.setIsTblLevel(false);
      }

      csd.setDbName((String)reconstructedKey.get(0));
      csd.setTableName((String)reconstructedKey.get(1));
      if (!fromTable) {
         Table table = this.getTable((String)reconstructedKey.get(0), (String)reconstructedKey.get(1));
         if (table == null) {
            throw new RuntimeException("Unable to find table " + (String)reconstructedKey.get(0) + "." + (String)reconstructedKey.get(1) + " even though I have a partition for it!");
         }

         csd.setPartName(HBaseStore.buildExternalPartName(table, reconstructedKey.subList(2, reconstructedKey.size())));
      }

      colStats.setStatsDesc(csd);
      return colStats;
   }

   ByteBuffer[] getFileMetadata(List fileIds) throws IOException {
      ByteBuffer[] result = new ByteBuffer[fileIds.size()];
      this.getFileMetadata(fileIds, result);
      return result;
   }

   public void getFileMetadata(List fileIds, ByteBuffer[] result) throws IOException {
      byte[][] keys = new byte[fileIds.size()][];

      for(int i = 0; i < fileIds.size(); ++i) {
         keys[i] = HBaseUtils.makeLongKey((Long)fileIds.get(i));
      }

      this.multiRead("HBMS_FILE_METADATA", CATALOG_CF, CATALOG_COL, keys, result);
   }

   public void storeFileMetadata(List fileIds, List metadataBuffers, ByteBuffer[] addedCols, ByteBuffer[][] addedVals) throws IOException, InterruptedException {
      byte[][] keys = new byte[fileIds.size()][];

      for(int i = 0; i < fileIds.size(); ++i) {
         keys[i] = HBaseUtils.makeLongKey((Long)fileIds.get(i));
      }

      ByteBuffer colNameBuf = ByteBuffer.wrap(CATALOG_COL);
      HTableInterface htab = this.conn.getHBaseTable("HBMS_FILE_METADATA");
      List<Row> actions = new ArrayList(keys.length);

      for(int keyIx = 0; keyIx < keys.length; ++keyIx) {
         ByteBuffer value = metadataBuffers != null ? (ByteBuffer)metadataBuffers.get(keyIx) : null;
         ByteBuffer[] av = addedVals == null ? null : addedVals[keyIx];
         if (value == null) {
            actions.add(new Delete(keys[keyIx]));

            assert av == null;
         } else {
            Put p = new Put(keys[keyIx]);
            p.addColumn(CATALOG_CF, colNameBuf, Long.MAX_VALUE, value);
            if (av != null) {
               assert av.length == addedCols.length;

               for(int colIx = 0; colIx < addedCols.length; ++colIx) {
                  p.addColumn(STATS_CF, addedCols[colIx], Long.MAX_VALUE, av[colIx]);
               }
            }

            actions.add(p);
         }
      }

      Object[] results = new Object[keys.length];
      htab.batch(actions, results);
      this.conn.flush(htab);
   }

   public void storeFileMetadata(long fileId, ByteBuffer metadata, ByteBuffer[] addedCols, ByteBuffer[] addedVals) throws IOException, InterruptedException {
      HTableInterface htab = this.conn.getHBaseTable("HBMS_FILE_METADATA");
      Put p = new Put(HBaseUtils.makeLongKey(fileId));
      p.addColumn(CATALOG_CF, ByteBuffer.wrap(CATALOG_COL), Long.MAX_VALUE, metadata);

      assert addedCols == null && addedVals == null || addedCols.length == addedVals.length;

      if (addedCols != null) {
         for(int i = 0; i < addedCols.length; ++i) {
            p.addColumn(STATS_CF, addedCols[i], Long.MAX_VALUE, addedVals[i]);
         }
      }

      htab.put(p);
      this.conn.flush(htab);
   }

   String getDelegationToken(String tokId) throws IOException {
      byte[] key = HBaseUtils.buildKey(tokId);
      byte[] serialized = this.read("HBMS_SECURITY", key, CATALOG_CF, DELEGATION_TOKEN_COL);
      return serialized == null ? null : HBaseUtils.deserializeDelegationToken(serialized);
   }

   List scanDelegationTokenIdentifiers() throws IOException {
      Iterator<Result> iter = this.scan("HBMS_SECURITY", CATALOG_CF, DELEGATION_TOKEN_COL);
      List<String> ids = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         byte[] serialized = result.getValue(CATALOG_CF, DELEGATION_TOKEN_COL);
         if (serialized != null) {
            ids.add(new String(result.getRow(), HBaseUtils.ENCODING));
         }
      }

      return ids;
   }

   void putDelegationToken(String tokId, String token) throws IOException {
      byte[][] serialized = HBaseUtils.serializeDelegationToken(tokId, token);
      this.store("HBMS_SECURITY", serialized[0], CATALOG_CF, DELEGATION_TOKEN_COL, serialized[1]);
   }

   void deleteDelegationToken(String tokId) throws IOException {
      byte[] key = HBaseUtils.buildKey(tokId);
      this.delete("HBMS_SECURITY", key, CATALOG_CF, DELEGATION_TOKEN_COL);
   }

   String getMasterKey(Integer seqNo) throws IOException {
      byte[] key = HBaseUtils.buildKey(seqNo.toString());
      byte[] serialized = this.read("HBMS_SECURITY", key, CATALOG_CF, MASTER_KEY_COL);
      return serialized == null ? null : HBaseUtils.deserializeMasterKey(serialized);
   }

   List scanMasterKeys() throws IOException {
      Iterator<Result> iter = this.scan("HBMS_SECURITY", CATALOG_CF, MASTER_KEY_COL);
      List<String> keys = new ArrayList();

      while(iter.hasNext()) {
         Result result = (Result)iter.next();
         byte[] serialized = result.getValue(CATALOG_CF, MASTER_KEY_COL);
         if (serialized != null) {
            keys.add(HBaseUtils.deserializeMasterKey(serialized));
         }
      }

      return keys;
   }

   void putMasterKey(Integer seqNo, String key) throws IOException {
      byte[][] serialized = HBaseUtils.serializeMasterKey(seqNo, key);
      this.store("HBMS_SECURITY", serialized[0], CATALOG_CF, MASTER_KEY_COL, serialized[1]);
   }

   void deleteMasterKey(Integer seqNo) throws IOException {
      byte[] key = HBaseUtils.buildKey(seqNo.toString());
      this.delete("HBMS_SECURITY", key, CATALOG_CF, MASTER_KEY_COL);
   }

   List printSecurity() throws IOException {
      HTableInterface htab = this.conn.getHBaseTable("HBMS_SECURITY");
      Scan scan = new Scan();
      scan.addColumn(CATALOG_CF, MASTER_KEY_COL);
      scan.addColumn(CATALOG_CF, DELEGATION_TOKEN_COL);
      Iterator<Result> iter = htab.getScanner(scan).iterator();
      if (!iter.hasNext()) {
         return Arrays.asList("No security related entries");
      } else {
         List<String> lines = new ArrayList();

         while(iter.hasNext()) {
            Result result = (Result)iter.next();
            byte[] val = result.getValue(CATALOG_CF, MASTER_KEY_COL);
            if (val != null) {
               int seqNo = Integer.parseInt(new String(result.getRow(), HBaseUtils.ENCODING));
               lines.add("Master key " + seqNo + ": " + HBaseUtils.deserializeMasterKey(val));
            } else {
               val = result.getValue(CATALOG_CF, DELEGATION_TOKEN_COL);
               if (val == null) {
                  throw new RuntimeException("Huh?  No master key, no delegation token!");
               }

               lines.add("Delegation token " + new String(result.getRow(), HBaseUtils.ENCODING) + ": " + HBaseUtils.deserializeDelegationToken(val));
            }
         }

         return lines;
      }
   }

   long peekAtSequence(byte[] sequence) throws IOException {
      byte[] serialized = this.read("HBMS_SEQUENCES", sequence, CATALOG_CF, CATALOG_COL);
      return serialized == null ? 0L : Long.parseLong(new String(serialized, HBaseUtils.ENCODING));
   }

   long getNextSequence(byte[] sequence) throws IOException {
      byte[] serialized = this.read("HBMS_SEQUENCES", sequence, CATALOG_CF, CATALOG_COL);
      long val = 0L;
      if (serialized != null) {
         val = Long.parseLong(new String(serialized, HBaseUtils.ENCODING));
      }

      byte[] incrSerialized = (new Long(val + 1L)).toString().getBytes(HBaseUtils.ENCODING);
      this.store("HBMS_SEQUENCES", sequence, CATALOG_CF, CATALOG_COL, incrSerialized);
      return val;
   }

   List printSequences() throws IOException {
      HTableInterface htab = this.conn.getHBaseTable("HBMS_SEQUENCES");
      Iterator<Result> iter = this.scan("HBMS_SEQUENCES", CATALOG_CF, CATALOG_COL, (Filter)null);
      List<String> sequences = new ArrayList();
      if (!iter.hasNext()) {
         return Arrays.asList("No sequences");
      } else {
         while(iter.hasNext()) {
            Result result = (Result)iter.next();
            sequences.add(new String(result.getRow(), HBaseUtils.ENCODING) + ": " + new String(result.getValue(CATALOG_CF, CATALOG_COL), HBaseUtils.ENCODING));
         }

         return sequences;
      }
   }

   List getPrimaryKey(String dbName, String tableName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, tableName);
      byte[] serialized = this.read("HBMS_TBLS", key, CATALOG_CF, PRIMARY_KEY_COL);
      return serialized == null ? null : HBaseUtils.deserializePrimaryKey(dbName, tableName, serialized);
   }

   List getForeignKeys(String dbName, String tableName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, tableName);
      byte[] serialized = this.read("HBMS_TBLS", key, CATALOG_CF, FOREIGN_KEY_COL);
      return serialized == null ? null : HBaseUtils.deserializeForeignKeys(dbName, tableName, serialized);
   }

   void putPrimaryKey(List pk) throws IOException {
      byte[][] serialized = HBaseUtils.serializePrimaryKey(pk);
      this.store("HBMS_TBLS", serialized[0], CATALOG_CF, PRIMARY_KEY_COL, serialized[1]);
   }

   void putForeignKeys(List fks) throws IOException {
      byte[][] serialized = HBaseUtils.serializeForeignKeys(fks);
      this.store("HBMS_TBLS", serialized[0], CATALOG_CF, FOREIGN_KEY_COL, serialized[1]);
   }

   void deletePrimaryKey(String dbName, String tableName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, tableName);
      this.delete("HBMS_TBLS", key, CATALOG_CF, PRIMARY_KEY_COL);
   }

   void deleteForeignKeys(String dbName, String tableName) throws IOException {
      byte[] key = HBaseUtils.buildKey(dbName, tableName);
      this.delete("HBMS_TBLS", key, CATALOG_CF, FOREIGN_KEY_COL);
   }

   void flushCatalogCache() {
      if (LOG.isDebugEnabled()) {
         for(Counter counter : this.counters) {
            LOG.debug(counter.dump());
            counter.clear();
         }

         this.statsCache.dumpCounters();
      }

      this.tableCache.flush();
      this.sdCache.flush();
      this.partCache.flush();
      this.flushRoleCache();
   }

   private void flushRoleCache() {
      this.roleCache.clear();
      this.entireRoleTableInCache = false;
   }

   private void store(String table, byte[] key, byte[] colFam, byte[] colName, byte[] obj) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Put p = new Put(key);
      p.add(colFam, colName, obj);
      htab.put(p);
      this.conn.flush(htab);
   }

   private void store(String table, byte[] key, byte[] colFam, byte[][] colName, byte[][] obj) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Put p = new Put(key);

      for(int i = 0; i < colName.length; ++i) {
         p.add(colFam, colName[i], obj[i]);
      }

      htab.put(p);
      this.conn.flush(htab);
   }

   private byte[] read(String table, byte[] key, byte[] colFam, byte[] colName) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Get g = new Get(key);
      g.addColumn(colFam, colName);
      Result res = htab.get(g);
      return res.getValue(colFam, colName);
   }

   private void multiRead(String table, byte[] colFam, byte[] colName, byte[][] keys, ByteBuffer[] resultDest) throws IOException {
      assert keys.length == resultDest.length;

      HTableInterface htab = this.conn.getHBaseTable(table);
      List<Get> gets = new ArrayList(keys.length);

      for(byte[] key : keys) {
         Get g = new Get(key);
         g.addColumn(colFam, colName);
         gets.add(g);
      }

      Result[] results = htab.get(gets);

      for(int i = 0; i < results.length; ++i) {
         Result r = results[i];
         if (r.isEmpty()) {
            resultDest[i] = null;
         } else {
            Cell cell = r.getColumnLatestCell(colFam, colName);
            resultDest[i] = ByteBuffer.wrap(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
         }
      }

   }

   private void multiModify(String table, byte[][] keys, byte[] colFam, byte[] colName, List values) throws IOException, InterruptedException {
      assert values == null || keys.length == values.size();

      ByteBuffer colNameBuf = ByteBuffer.wrap(colName);
      HTableInterface htab = this.conn.getHBaseTable(table);
      List<Row> actions = new ArrayList(keys.length);

      for(int i = 0; i < keys.length; ++i) {
         ByteBuffer value = values != null ? (ByteBuffer)values.get(i) : null;
         if (value == null) {
            actions.add(new Delete(keys[i]));
         } else {
            Put p = new Put(keys[i]);
            p.addColumn(colFam, colNameBuf, Long.MAX_VALUE, value);
            actions.add(p);
         }
      }

      Object[] results = new Object[keys.length];
      htab.batch(actions, results);
      this.conn.flush(htab);
   }

   private Result read(String table, byte[] key, byte[] colFam, byte[][] colNames) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Get g = new Get(key);

      for(byte[] colName : colNames) {
         g.addColumn(colFam, colName);
      }

      return htab.get(g);
   }

   private void delete(String table, byte[] key, byte[] colFam, byte[] colName) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Delete d = new Delete(key);
      if (colName != null) {
         d.deleteColumn(colFam, colName);
      } else if (colFam != null) {
         d.deleteFamily(colFam);
      }

      htab.delete(d);
   }

   private Iterator scan(String table, byte[] colFam, byte[] colName) throws IOException {
      return this.scan(table, (byte[])null, (byte[])null, colFam, colName, (Filter)null);
   }

   private Iterator scan(String table, byte[] colFam, byte[] colName, Filter filter) throws IOException {
      return this.scan(table, (byte[])null, (byte[])null, colFam, colName, filter);
   }

   private Iterator scan(String table, Filter filter) throws IOException {
      return this.scan(table, (byte[])null, (byte[])null, (byte[])null, (byte[])null, filter);
   }

   private Iterator scan(String table, byte[] keyStart, byte[] keyEnd, byte[] colFam, byte[] colName, Filter filter) throws IOException {
      HTableInterface htab = this.conn.getHBaseTable(table);
      Scan s = new Scan();
      if (keyStart != null) {
         s.setStartRow(keyStart);
      }

      if (keyEnd != null) {
         s.setStopRow(keyEnd);
      }

      if (colFam != null && colName != null) {
         s.addColumn(colFam, colName);
      }

      if (filter != null) {
         s.setFilter(filter);
      }

      ResultScanner scanner = htab.getScanner(s);
      return scanner.iterator();
   }

   private String noSuch(String name, String type) {
      return "No such " + type + ": " + name.replaceAll(HBaseUtils.KEY_SEPARATOR_STR, ".");
   }

   private List noMatch(String regex, String type) {
      return Arrays.asList("No matching " + type + ": " + regex);
   }

   private String dumpThriftObject(TBase obj) throws TException, UnsupportedEncodingException {
      TMemoryBuffer buf = new TMemoryBuffer(1000);
      TProtocol protocol = new TSimpleJSONProtocol(buf);
      obj.write(protocol);
      return buf.toString(StandardCharsets.UTF_8);
   }

   @VisibleForTesting
   int countStorageDescriptor() throws IOException {
      ResultScanner scanner = this.conn.getHBaseTable("HBMS_SDS").getScanner(new Scan());
      int cnt = 0;

      Result r;
      do {
         r = scanner.next();
         if (r != null) {
            LOG.debug("Saw record with hash " + Base64.encodeBase64String(r.getRow()));
            ++cnt;
         }
      } while(r != null);

      return cnt;
   }

   @VisibleForTesting
   static void setTestConnection(HBaseConnection connection) {
      testConn = connection;
   }

   static {
      CATALOG_CF = "c".getBytes(HBaseUtils.ENCODING);
      STATS_CF = "s".getBytes(HBaseUtils.ENCODING);
      tableNames = new String[]{"HBMS_AGGR_STATS", "HBMS_DBS", "HBMS_FUNCS", "HBMS_GLOBAL_PRIVS", "HBMS_PARTITIONS", "HBMS_USER_TO_ROLE", "HBMS_ROLES", "HBMS_SDS", "HBMS_SECURITY", "HBMS_SEQUENCES", "HBMS_TBLS", "HBMS_INDEX", "HBMS_FILE_METADATA"};
      columnFamilies = new HashMap(tableNames.length);
      columnFamilies.put("HBMS_AGGR_STATS", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_DBS", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_FUNCS", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_GLOBAL_PRIVS", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_PARTITIONS", Arrays.asList(CATALOG_CF, STATS_CF));
      columnFamilies.put("HBMS_USER_TO_ROLE", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_ROLES", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_SDS", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_SECURITY", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_SEQUENCES", Arrays.asList(CATALOG_CF));
      columnFamilies.put("HBMS_TBLS", Arrays.asList(CATALOG_CF, STATS_CF));
      columnFamilies.put("HBMS_INDEX", Arrays.asList(CATALOG_CF, STATS_CF));
      columnFamilies.put("HBMS_FILE_METADATA", Arrays.asList(CATALOG_CF, STATS_CF));
      MASTER_KEY_SEQUENCE = "master_key".getBytes(HBaseUtils.ENCODING);
      AGGR_STATS_BLOOM_COL = "b".getBytes(HBaseUtils.ENCODING);
      AGGR_STATS_STATS_COL = "s".getBytes(HBaseUtils.ENCODING);
      CATALOG_COL = "c".getBytes(HBaseUtils.ENCODING);
      ROLES_COL = "roles".getBytes(HBaseUtils.ENCODING);
      REF_COUNT_COL = "ref".getBytes(HBaseUtils.ENCODING);
      DELEGATION_TOKEN_COL = "dt".getBytes(HBaseUtils.ENCODING);
      MASTER_KEY_COL = "mk".getBytes(HBaseUtils.ENCODING);
      PRIMARY_KEY_COL = "pk".getBytes(HBaseUtils.ENCODING);
      FOREIGN_KEY_COL = "fk".getBytes(HBaseUtils.ENCODING);
      GLOBAL_PRIVS_KEY = "gp".getBytes(HBaseUtils.ENCODING);
      SEQUENCES_KEY = "seq".getBytes(HBaseUtils.ENCODING);
      LOG = LoggerFactory.getLogger(HBaseReadWrite.class.getName());
      self = new ThreadLocal() {
         protected HBaseReadWrite initialValue() {
            if (HBaseReadWrite.staticConf == null) {
               throw new RuntimeException("Attempt to create HBaseReadWrite with no configuration set");
            } else {
               return new HBaseReadWrite(HBaseReadWrite.staticConf);
            }
         }
      };
      tablesCreated = false;
      staticConf = null;
   }

   private static class PartitionScanInfo {
      final String dbName;
      final String tableName;
      final byte[] keyPrefix;
      final byte[] endKeyPrefix;
      final int maxPartitions;
      final Filter filter;

      PartitionScanInfo(String d, String t, byte[] k, byte[] e, int m, Filter f) {
         this.dbName = d;
         this.tableName = t;
         this.keyPrefix = k;
         this.endKeyPrefix = e;
         this.maxPartitions = m;
         this.filter = f;
      }

      public String toString() {
         return "dbName:" + this.dbName + " tableName:" + this.tableName + " keyPrefix:" + Base64.encodeBase64URLSafeString(this.keyPrefix) + " endKeyPrefix:" + Base64.encodeBase64URLSafeString(this.endKeyPrefix) + " maxPartitions:" + this.maxPartitions + " filter:" + this.filter.toString();
      }
   }

   private static class ByteArrayWrapper {
      byte[] wrapped;

      ByteArrayWrapper(byte[] b) {
         this.wrapped = b;
      }

      public boolean equals(Object other) {
         return other instanceof ByteArrayWrapper ? Arrays.equals(((ByteArrayWrapper)other).wrapped, this.wrapped) : false;
      }

      public int hashCode() {
         return Arrays.hashCode(this.wrapped);
      }
   }

   private static class BogusObjectCache extends ObjectCache {
      static Counter bogus = new Counter("bogus");

      BogusObjectCache() {
         super(1, bogus, bogus, bogus);
      }

      Object get(Object key) {
         return null;
      }
   }

   private static class BogusPartitionCache extends PartitionCache {
      static Counter bogus = new Counter("bogus");

      BogusPartitionCache() {
         super(1, bogus, bogus, bogus);
      }

      Collection getAllForTable(String dbName, String tableName) {
         return null;
      }

      Partition get(String dbName, String tableName, List partVals) {
         return null;
      }
   }
}
