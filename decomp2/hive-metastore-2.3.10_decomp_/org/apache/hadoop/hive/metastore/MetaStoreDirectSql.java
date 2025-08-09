package org.apache.hadoop.hive.metastore;

import com.google.common.collect.Lists;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.datastore.JDOConnection;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.AggrStats;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsData;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsDesc;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Order;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.SkewedInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.model.MConstraint;
import org.apache.hadoop.hive.metastore.model.MDatabase;
import org.apache.hadoop.hive.metastore.model.MPartitionColumnStatistics;
import org.apache.hadoop.hive.metastore.model.MTableColumnStatistics;
import org.apache.hadoop.hive.metastore.parser.ExpressionTree;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hive.common.util.BloomFilter;
import org.datanucleus.store.rdbms.query.ForwardQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MetaStoreDirectSql {
   private static final int NO_BATCHING = -1;
   private static final int DETECT_BATCHING = 0;
   private static final Logger LOG = LoggerFactory.getLogger(MetaStoreDirectSql.class);
   private final PersistenceManager pm;
   private final DatabaseProduct dbType;
   private final int batchSize;
   private final boolean convertMapNullsToEmptyStrings;
   private final String defaultPartName;
   private final boolean isCompatibleDatastore;
   private final boolean isAggregateStatsCacheEnabled;
   private AggregateStatsCache aggrStatsCache;
   private static final String STATS_COLLIST = "\"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\" ";

   public MetaStoreDirectSql(PersistenceManager pm, Configuration conf) {
      this.pm = pm;
      DatabaseProduct dbType = null;

      try {
         dbType = DatabaseProduct.determineDatabaseProduct(this.getProductName());
      } catch (SQLException e) {
         LOG.warn("Cannot determine database product; assuming OTHER", e);
         dbType = DatabaseProduct.OTHER;
      }

      this.dbType = dbType;
      int batchSize = HiveConf.getIntVar(conf, ConfVars.METASTORE_DIRECT_SQL_PARTITION_BATCH_SIZE);
      if (batchSize == 0) {
         batchSize = DatabaseProduct.needsInBatching(dbType) ? 1000 : -1;
      }

      this.batchSize = batchSize;
      this.convertMapNullsToEmptyStrings = HiveConf.getBoolVar(conf, ConfVars.METASTORE_ORM_RETRIEVE_MAPNULLS_AS_EMPTY_STRINGS);
      this.defaultPartName = HiveConf.getVar(conf, ConfVars.DEFAULTPARTITIONNAME);
      String jdoIdFactory = HiveConf.getVar(conf, ConfVars.METASTORE_IDENTIFIER_FACTORY);
      if (!"datanucleus1".equalsIgnoreCase(jdoIdFactory)) {
         LOG.warn("Underlying metastore does not use 'datanucleus1' for its ORM naming scheme. Disabling directSQL as it uses hand-hardcoded SQL with that assumption.");
         this.isCompatibleDatastore = false;
      } else {
         this.isCompatibleDatastore = this.ensureDbInit() && this.runTestQuery();
         if (this.isCompatibleDatastore) {
            LOG.info("Using direct SQL, underlying DB is " + dbType);
         }
      }

      this.isAggregateStatsCacheEnabled = HiveConf.getBoolVar(conf, ConfVars.METASTORE_AGGREGATE_STATS_CACHE_ENABLED);
      if (this.isAggregateStatsCacheEnabled) {
         this.aggrStatsCache = AggregateStatsCache.getInstance(conf);
      }

   }

   private String getProductName() {
      JDOConnection jdoConn = this.pm.getDataStoreConnection();

      Object var3;
      try {
         String var2 = ((Connection)jdoConn.getNativeConnection()).getMetaData().getDatabaseProductName();
         return var2;
      } catch (Throwable t) {
         LOG.warn("Error retrieving product name", t);
         var3 = null;
      } finally {
         jdoConn.close();
      }

      return (String)var3;
   }

   private boolean ensureDbInit() {
      Transaction tx = this.pm.currentTransaction();
      boolean doCommit = false;
      if (!tx.isActive()) {
         tx.begin();
         doCommit = true;
      }

      Query dbQuery = null;
      Query tblColumnQuery = null;
      Query partColumnQuery = null;
      Query constraintQuery = null;

      boolean var8;
      try {
         dbQuery = this.pm.newQuery(MDatabase.class, "name == ''");
         dbQuery.execute();
         tblColumnQuery = this.pm.newQuery(MTableColumnStatistics.class, "dbName == ''");
         tblColumnQuery.execute();
         partColumnQuery = this.pm.newQuery(MPartitionColumnStatistics.class, "dbName == ''");
         partColumnQuery.execute();
         constraintQuery = this.pm.newQuery(MConstraint.class, "childIntegerIndex < 0");
         constraintQuery.execute();
         boolean var7 = true;
         return var7;
      } catch (Exception ex) {
         doCommit = false;
         LOG.warn("Database initialization failed; direct SQL is disabled", ex);
         tx.rollback();
         var8 = false;
      } finally {
         if (doCommit) {
            tx.commit();
         }

         if (dbQuery != null) {
            dbQuery.closeAll();
         }

         if (tblColumnQuery != null) {
            tblColumnQuery.closeAll();
         }

         if (partColumnQuery != null) {
            partColumnQuery.closeAll();
         }

         if (constraintQuery != null) {
            constraintQuery.cancelAll();
         }

      }

      return var8;
   }

   private boolean runTestQuery() {
      Transaction tx = this.pm.currentTransaction();
      boolean doCommit = false;
      if (!tx.isActive()) {
         tx.begin();
         doCommit = true;
      }

      Query query = null;
      String selfTestQuery = "select \"DB_ID\" from \"DBS\"";

      boolean var6;
      try {
         this.prepareTxn();
         query = this.pm.newQuery("javax.jdo.query.SQL", selfTestQuery);
         query.execute();
         boolean var5 = true;
         return var5;
      } catch (Throwable t) {
         doCommit = false;
         LOG.warn("Self-test query [" + selfTestQuery + "] failed; direct SQL is disabled", t);
         tx.rollback();
         var6 = false;
      } finally {
         if (doCommit) {
            tx.commit();
         }

         if (query != null) {
            query.closeAll();
         }

      }

      return var6;
   }

   public boolean isCompatibleDatastore() {
      return this.isCompatibleDatastore;
   }

   private void executeNoResult(String queryText) throws SQLException {
      JDOConnection jdoConn = this.pm.getDataStoreConnection();
      Statement statement = null;
      boolean doTrace = LOG.isDebugEnabled();

      try {
         long start = doTrace ? System.nanoTime() : 0L;
         statement = ((Connection)jdoConn.getNativeConnection()).createStatement();
         statement.execute(queryText);
         this.timingTrace(doTrace, queryText, start, doTrace ? System.nanoTime() : 0L);
      } finally {
         if (statement != null) {
            statement.close();
         }

         jdoConn.close();
      }

   }

   public Database getDatabase(String dbName) throws MetaException {
      Query queryDbSelector = null;
      Query queryDbParams = null;

      Object var7;
      try {
         dbName = dbName.toLowerCase();
         String queryTextDbSelector = "select \"DB_ID\", \"NAME\", \"DB_LOCATION_URI\", \"DESC\", \"OWNER_NAME\", \"OWNER_TYPE\" FROM \"DBS\" where \"NAME\" = ? ";
         Object[] params = new Object[]{dbName};
         queryDbSelector = this.pm.newQuery("javax.jdo.query.SQL", queryTextDbSelector);
         if (LOG.isTraceEnabled()) {
            LOG.trace("getDatabase:query instantiated : " + queryTextDbSelector + " with param [" + params[0] + "]");
         }

         List<Object[]> sqlResult = (List)this.executeWithArray(queryDbSelector, params, queryTextDbSelector);
         if (sqlResult != null && !sqlResult.isEmpty()) {
            assert sqlResult.size() == 1;

            if (sqlResult.get(0) == null) {
               var7 = null;
               return (Database)var7;
            }

            Object[] dbline = sqlResult.get(0);
            Long dbid = extractSqlLong(dbline[0]);
            String queryTextDbParams = "select \"PARAM_KEY\", \"PARAM_VALUE\"  FROM \"DATABASE_PARAMS\"  WHERE \"DB_ID\" = ?  AND \"PARAM_KEY\" IS NOT NULL";
            params[0] = dbid;
            queryDbParams = this.pm.newQuery("javax.jdo.query.SQL", queryTextDbParams);
            if (LOG.isTraceEnabled()) {
               LOG.trace("getDatabase:query2 instantiated : " + queryTextDbParams + " with param [" + params[0] + "]");
            }

            Map<String, String> dbParams = new HashMap();
            List<Object[]> sqlResult2 = this.ensureList(this.executeWithArray(queryDbParams, params, queryTextDbParams));
            if (!sqlResult2.isEmpty()) {
               for(Object[] line : sqlResult2) {
                  dbParams.put(this.extractSqlString(line[0]), this.extractSqlString(line[1]));
               }
            }

            Database db = new Database();
            db.setName(this.extractSqlString(dbline[1]));
            db.setLocationUri(this.extractSqlString(dbline[2]));
            db.setDescription(this.extractSqlString(dbline[3]));
            db.setOwnerName(this.extractSqlString(dbline[4]));
            String type = this.extractSqlString(dbline[5]);
            db.setOwnerType(null != type && !type.trim().isEmpty() ? PrincipalType.valueOf(type) : null);
            db.setParameters(MetaStoreUtils.trimMapNulls(dbParams, this.convertMapNullsToEmptyStrings));
            if (LOG.isDebugEnabled()) {
               LOG.debug("getDatabase: directsql returning db " + db.getName() + " locn[" + db.getLocationUri() + "] desc [" + db.getDescription() + "] owner [" + db.getOwnerName() + "] ownertype [" + db.getOwnerType() + "]");
            }

            Database var14 = db;
            return var14;
         }

         var7 = null;
      } finally {
         if (queryDbSelector != null) {
            queryDbSelector.closeAll();
         }

         if (queryDbParams != null) {
            queryDbParams.closeAll();
         }

      }

      return (Database)var7;
   }

   public List getPartitionsViaSqlFilter(final String dbName, final String tblName, List partNames) throws MetaException {
      return (List)(partNames.isEmpty() ? new ArrayList() : this.runBatched(partNames, new Batchable() {
         public List run(List input) throws MetaException {
            String filter = "\"PARTITIONS\".\"PART_NAME\" in (" + MetaStoreDirectSql.this.makeParams(input.size()) + ")";
            return MetaStoreDirectSql.this.getPartitionsViaSqlFilterInternal(dbName, tblName, (Boolean)null, filter, input, new ArrayList(), (Integer)null);
         }
      }));
   }

   public List getPartitionsViaSqlFilter(SqlFilterForPushdown filter, Integer max) throws MetaException {
      Boolean isViewTable = isViewTable(filter.table);
      return this.getPartitionsViaSqlFilterInternal(filter.table.getDbName(), filter.table.getTableName(), isViewTable, filter.filter, filter.params, filter.joins, max);
   }

   public boolean generateSqlFilterForPushdown(Table table, ExpressionTree tree, SqlFilterForPushdown result) throws MetaException {
      boolean dbHasJoinCastBug = DatabaseProduct.hasJoinOperationOrderBug(this.dbType);
      result.table = table;
      result.filter = MetaStoreDirectSql.PartitionFilterGenerator.generateSqlFilter(table, tree, result.params, result.joins, dbHasJoinCastBug, this.defaultPartName, this.dbType);
      return result.filter != null;
   }

   public List getPartitions(String dbName, String tblName, Integer max) throws MetaException {
      return this.getPartitionsViaSqlFilterInternal(dbName, tblName, (Boolean)null, (String)null, new ArrayList(), new ArrayList(), max);
   }

   private static Boolean isViewTable(Table t) {
      return t.isSetTableType() ? t.getTableType().equals(TableType.VIRTUAL_VIEW.toString()) : null;
   }

   private boolean isViewTable(String dbName, String tblName) throws MetaException {
      Query query = null;

      boolean var7;
      try {
         String queryText = "select \"TBL_TYPE\" from \"TBLS\" inner join \"DBS\" on \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\"  where \"TBLS\".\"TBL_NAME\" = ? and \"DBS\".\"NAME\" = ?";
         Object[] params = new Object[]{tblName, dbName};
         query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
         query.setUnique(true);
         Object result = this.executeWithArray(query, params, queryText);
         var7 = result != null && result.toString().equals(TableType.VIRTUAL_VIEW.toString());
      } finally {
         if (query != null) {
            query.closeAll();
         }

      }

      return var7;
   }

   private List getPartitionsViaSqlFilterInternal(String dbName, String tblName, final Boolean isView, String sqlFilter, List paramsForFilter, List joinsForFilter, Integer max) throws MetaException {
      boolean doTrace = LOG.isDebugEnabled();
      final String dbNameLcase = dbName.toLowerCase();
      final String tblNameLcase = tblName.toLowerCase();
      String orderForFilter = max != null ? " order by \"PART_NAME\" asc" : "";
      String queryText = "select \"PARTITIONS\".\"PART_ID\" from \"PARTITIONS\"  inner join \"TBLS\" on \"PARTITIONS\".\"TBL_ID\" = \"TBLS\".\"TBL_ID\"     and \"TBLS\".\"TBL_NAME\" = ?   inner join \"DBS\" on \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\"      and \"DBS\".\"NAME\" = ? " + StringUtils.join(joinsForFilter, ' ') + (StringUtils.isBlank(sqlFilter) ? "" : " where " + sqlFilter) + orderForFilter;
      Object[] params = new Object[paramsForFilter.size() + 2];
      params[0] = tblNameLcase;
      params[1] = dbNameLcase;

      for(int i = 0; i < paramsForFilter.size(); ++i) {
         params[i + 2] = paramsForFilter.get(i);
      }

      long start = doTrace ? System.nanoTime() : 0L;
      Query query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      if (max != null) {
         query.setRange(0L, (long)max.shortValue());
      }

      List<Object> sqlResult = (List)this.executeWithArray(query, params, queryText);
      long queryTime = doTrace ? System.nanoTime() : 0L;
      this.timingTrace(doTrace, queryText, start, queryTime);
      if (sqlResult.isEmpty()) {
         return new ArrayList();
      } else {
         List<Partition> result = this.runBatched(sqlResult, new Batchable() {
            public List run(List input) throws MetaException {
               return MetaStoreDirectSql.this.getPartitionsFromPartitionIds(dbNameLcase, tblNameLcase, isView, input);
            }
         });
         query.closeAll();
         return result;
      }
   }

   private List getPartitionsFromPartitionIds(String dbName, String tblName, Boolean isView, List partIdList) throws MetaException {
      boolean doTrace = LOG.isDebugEnabled();
      int idStringWidth = (int)Math.ceil(Math.log10((double)partIdList.size())) + 1;
      int sbCapacity = partIdList.size() * idStringWidth;
      StringBuilder partSb = new StringBuilder(sbCapacity);

      for(Object partitionId : partIdList) {
         partSb.append(extractSqlLong(partitionId)).append(",");
      }

      String partIds = trimCommaList(partSb);
      String queryText = "select \"PARTITIONS\".\"PART_ID\", \"SDS\".\"SD_ID\", \"SDS\".\"CD_ID\", \"SERDES\".\"SERDE_ID\", \"PARTITIONS\".\"CREATE_TIME\", \"PARTITIONS\".\"LAST_ACCESS_TIME\", \"SDS\".\"INPUT_FORMAT\", \"SDS\".\"IS_COMPRESSED\", \"SDS\".\"IS_STOREDASSUBDIRECTORIES\", \"SDS\".\"LOCATION\", \"SDS\".\"NUM_BUCKETS\", \"SDS\".\"OUTPUT_FORMAT\", \"SERDES\".\"NAME\", \"SERDES\".\"SLIB\" from \"PARTITIONS\"  left outer join \"SDS\" on \"PARTITIONS\".\"SD_ID\" = \"SDS\".\"SD_ID\"   left outer join \"SERDES\" on \"SDS\".\"SERDE_ID\" = \"SERDES\".\"SERDE_ID\" where \"PART_ID\" in (" + partIds + ") order by \"PART_NAME\" asc";
      long start = doTrace ? System.nanoTime() : 0L;
      Query query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      List<Object[]> sqlResult = (List)this.executeWithArray(query, (Object[])null, queryText);
      long queryTime = doTrace ? System.nanoTime() : 0L;
      Deadline.checkTimeout();
      TreeMap<Long, Partition> partitions = new TreeMap();
      TreeMap<Long, StorageDescriptor> sds = new TreeMap();
      TreeMap<Long, SerDeInfo> serdes = new TreeMap();
      TreeMap<Long, List<FieldSchema>> colss = new TreeMap();
      ArrayList<Partition> orderedResult = new ArrayList(partIdList.size());
      StringBuilder sdSb = new StringBuilder(sbCapacity);
      StringBuilder serdeSb = new StringBuilder(sbCapacity);
      StringBuilder colsSb = new StringBuilder(7);
      tblName = tblName.toLowerCase();
      dbName = dbName.toLowerCase();

      for(Object[] fields : sqlResult) {
         long partitionId = extractSqlLong(fields[0]);
         Long sdId = extractSqlLong(fields[1]);
         Long colId = extractSqlLong(fields[2]);
         Long serdeId = extractSqlLong(fields[3]);
         if (sdId == null || serdeId == null) {
            if (isView == null) {
               isView = this.isViewTable(dbName, tblName);
            }

            if (sdId != null || colId != null || serdeId != null || !isView) {
               throw new MetaException("Unexpected null for one of the IDs, SD " + sdId + ", serde " + serdeId + " for a " + (isView ? "" : "non-") + " view");
            }
         }

         Partition part = new Partition();
         orderedResult.add(part);
         part.setParameters(new HashMap());
         part.setValues(new ArrayList());
         part.setDbName(dbName);
         part.setTableName(tblName);
         if (fields[4] != null) {
            part.setCreateTime(this.extractSqlInt(fields[4]));
         }

         if (fields[5] != null) {
            part.setLastAccessTime(this.extractSqlInt(fields[5]));
         }

         partitions.put(partitionId, part);
         if (sdId != null) {
            assert serdeId != null;

            StorageDescriptor sd = new StorageDescriptor();
            StorageDescriptor oldSd = (StorageDescriptor)sds.put(sdId, sd);
            if (oldSd != null) {
               throw new MetaException("Partitions reuse SDs; we don't expect that");
            }

            sd.setSortCols(new ArrayList());
            sd.setBucketCols(new ArrayList());
            sd.setParameters(new HashMap());
            sd.setSkewedInfo(new SkewedInfo(new ArrayList(), new ArrayList(), new HashMap()));
            sd.setInputFormat((String)fields[6]);
            Boolean tmpBoolean = extractSqlBoolean(fields[7]);
            if (tmpBoolean != null) {
               sd.setCompressed(tmpBoolean);
            }

            tmpBoolean = extractSqlBoolean(fields[8]);
            if (tmpBoolean != null) {
               sd.setStoredAsSubDirectories(tmpBoolean);
            }

            sd.setLocation((String)fields[9]);
            if (fields[10] != null) {
               sd.setNumBuckets(this.extractSqlInt(fields[10]));
            }

            sd.setOutputFormat((String)fields[11]);
            sdSb.append(sdId).append(",");
            part.setSd(sd);
            if (colId != null) {
               List<FieldSchema> cols = (List)colss.get(colId);
               if (cols == null) {
                  cols = new ArrayList();
                  colss.put(colId, cols);
                  colsSb.append(colId).append(",");
               }

               sd.setCols(cols);
            }

            SerDeInfo serde = new SerDeInfo();
            SerDeInfo oldSerde = (SerDeInfo)serdes.put(serdeId, serde);
            if (oldSerde != null) {
               throw new MetaException("SDs reuse serdes; we don't expect that");
            }

            serde.setParameters(new HashMap());
            serde.setName((String)fields[12]);
            serde.setSerializationLib((String)fields[13]);
            serdeSb.append(serdeId).append(",");
            sd.setSerdeInfo(serde);
            Deadline.checkTimeout();
         }
      }

      query.closeAll();
      this.timingTrace(doTrace, queryText, start, queryTime);
      queryText = "select \"PART_ID\", \"PARAM_KEY\", \"PARAM_VALUE\" from \"PARTITION_PARAMS\" where \"PART_ID\" in (" + partIds + ") and \"PARAM_KEY\" is not null order by \"PART_ID\" asc";
      this.loopJoinOrderedResult(partitions, queryText, 0, new ApplyFunc() {
         public void apply(Partition t, Object[] fields) {
            t.putToParameters((String)fields[1], (String)fields[2]);
         }
      });

      for(Partition t : partitions.values()) {
         t.setParameters(MetaStoreUtils.trimMapNulls(t.getParameters(), this.convertMapNullsToEmptyStrings));
      }

      queryText = "select \"PART_ID\", \"PART_KEY_VAL\" from \"PARTITION_KEY_VALS\" where \"PART_ID\" in (" + partIds + ") and \"INTEGER_IDX\" >= 0 order by \"PART_ID\" asc, \"INTEGER_IDX\" asc";
      this.loopJoinOrderedResult(partitions, queryText, 0, new ApplyFunc() {
         public void apply(Partition t, Object[] fields) {
            t.addToValues((String)fields[1]);
         }
      });
      if (sdSb.length() == 0) {
         assert serdeSb.length() == 0 && colsSb.length() == 0;

         return orderedResult;
      } else {
         String sdIds = trimCommaList(sdSb);
         String serdeIds = trimCommaList(serdeSb);
         String colIds = trimCommaList(colsSb);
         queryText = "select \"SD_ID\", \"PARAM_KEY\", \"PARAM_VALUE\" from \"SD_PARAMS\" where \"SD_ID\" in (" + sdIds + ") and \"PARAM_KEY\" is not null order by \"SD_ID\" asc";
         this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
            public void apply(StorageDescriptor t, Object[] fields) {
               t.putToParameters((String)fields[1], MetaStoreDirectSql.this.extractSqlClob(fields[2]));
            }
         });

         for(StorageDescriptor t : sds.values()) {
            t.setParameters(MetaStoreUtils.trimMapNulls(t.getParameters(), this.convertMapNullsToEmptyStrings));
         }

         queryText = "select \"SD_ID\", \"COLUMN_NAME\", \"SORT_COLS\".\"ORDER\" from \"SORT_COLS\" where \"SD_ID\" in (" + sdIds + ") and \"INTEGER_IDX\" >= 0 order by \"SD_ID\" asc, \"INTEGER_IDX\" asc";
         this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
            public void apply(StorageDescriptor t, Object[] fields) {
               if (fields[2] != null) {
                  t.addToSortCols(new Order((String)fields[1], MetaStoreDirectSql.this.extractSqlInt(fields[2])));
               }
            }
         });
         queryText = "select \"SD_ID\", \"BUCKET_COL_NAME\" from \"BUCKETING_COLS\" where \"SD_ID\" in (" + sdIds + ") and \"INTEGER_IDX\" >= 0 order by \"SD_ID\" asc, \"INTEGER_IDX\" asc";
         this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
            public void apply(StorageDescriptor t, Object[] fields) {
               t.addToBucketCols((String)fields[1]);
            }
         });
         queryText = "select \"SD_ID\", \"SKEWED_COL_NAME\" from \"SKEWED_COL_NAMES\" where \"SD_ID\" in (" + sdIds + ") and \"INTEGER_IDX\" >= 0 order by \"SD_ID\" asc, \"INTEGER_IDX\" asc";
         boolean hasSkewedColumns = this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
            public void apply(StorageDescriptor t, Object[] fields) {
               if (!t.isSetSkewedInfo()) {
                  t.setSkewedInfo(new SkewedInfo());
               }

               t.getSkewedInfo().addToSkewedColNames((String)fields[1]);
            }
         }) > 0;
         if (hasSkewedColumns) {
            queryText = "select \"SKEWED_VALUES\".\"SD_ID_OID\",  \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_ID\",  \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_VALUE\" from \"SKEWED_VALUES\"   left outer join \"SKEWED_STRING_LIST_VALUES\" on \"SKEWED_VALUES\".\"STRING_LIST_ID_EID\" = \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_ID\" where \"SKEWED_VALUES\".\"SD_ID_OID\" in (" + sdIds + ")   and \"SKEWED_VALUES\".\"STRING_LIST_ID_EID\" is not null   and \"SKEWED_VALUES\".\"INTEGER_IDX\" >= 0 order by \"SKEWED_VALUES\".\"SD_ID_OID\" asc, \"SKEWED_VALUES\".\"INTEGER_IDX\" asc,  \"SKEWED_STRING_LIST_VALUES\".\"INTEGER_IDX\" asc";
            this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
               private Long currentListId;
               private List currentList;

               public void apply(StorageDescriptor t, Object[] fields) throws MetaException {
                  if (!t.isSetSkewedInfo()) {
                     t.setSkewedInfo(new SkewedInfo());
                  }

                  if (fields[1] == null) {
                     this.currentList = null;
                     this.currentListId = null;
                     t.getSkewedInfo().addToSkewedColValues(new ArrayList());
                  } else {
                     long fieldsListId = MetaStoreDirectSql.extractSqlLong(fields[1]);
                     if (this.currentListId == null || fieldsListId != this.currentListId) {
                        this.currentList = new ArrayList();
                        this.currentListId = fieldsListId;
                        t.getSkewedInfo().addToSkewedColValues(this.currentList);
                     }

                     this.currentList.add((String)fields[2]);
                  }

               }
            });
            queryText = "select \"SKEWED_COL_VALUE_LOC_MAP\".\"SD_ID\", \"SKEWED_STRING_LIST_VALUES\".STRING_LIST_ID, \"SKEWED_COL_VALUE_LOC_MAP\".\"LOCATION\", \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_VALUE\" from \"SKEWED_COL_VALUE_LOC_MAP\"  left outer join \"SKEWED_STRING_LIST_VALUES\" on \"SKEWED_COL_VALUE_LOC_MAP\".\"STRING_LIST_ID_KID\" = \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_ID\" where \"SKEWED_COL_VALUE_LOC_MAP\".\"SD_ID\" in (" + sdIds + ")  and \"SKEWED_COL_VALUE_LOC_MAP\".\"STRING_LIST_ID_KID\" is not null order by \"SKEWED_COL_VALUE_LOC_MAP\".\"SD_ID\" asc,  \"SKEWED_STRING_LIST_VALUES\".\"STRING_LIST_ID\" asc,  \"SKEWED_STRING_LIST_VALUES\".\"INTEGER_IDX\" asc";
            this.loopJoinOrderedResult(sds, queryText, 0, new ApplyFunc() {
               private Long currentListId;
               private List currentList;

               public void apply(StorageDescriptor t, Object[] fields) throws MetaException {
                  if (!t.isSetSkewedInfo()) {
                     SkewedInfo skewedInfo = new SkewedInfo();
                     skewedInfo.setSkewedColValueLocationMaps(new HashMap());
                     t.setSkewedInfo(skewedInfo);
                  }

                  Map<List<String>, String> skewMap = t.getSkewedInfo().getSkewedColValueLocationMaps();
                  if (fields[1] == null) {
                     this.currentList = new ArrayList();
                     this.currentListId = null;
                  } else {
                     long fieldsListId = MetaStoreDirectSql.extractSqlLong(fields[1]);
                     if (this.currentListId != null && fieldsListId == this.currentListId) {
                        skewMap.remove(this.currentList);
                     } else {
                        this.currentList = new ArrayList();
                        this.currentListId = fieldsListId;
                     }

                     this.currentList.add((String)fields[3]);
                  }

                  skewMap.put(this.currentList, (String)fields[2]);
               }
            });
         }

         if (!colss.isEmpty()) {
            queryText = "select \"CD_ID\", \"COMMENT\", \"COLUMN_NAME\", \"TYPE_NAME\" from \"COLUMNS_V2\" where \"CD_ID\" in (" + colIds + ") and \"INTEGER_IDX\" >= 0 order by \"CD_ID\" asc, \"INTEGER_IDX\" asc";
            this.loopJoinOrderedResult(colss, queryText, 0, new ApplyFunc() {
               public void apply(List t, Object[] fields) {
                  t.add(new FieldSchema((String)fields[2], MetaStoreDirectSql.this.extractSqlClob(fields[3]), (String)fields[1]));
               }
            });
         }

         queryText = "select \"SERDE_ID\", \"PARAM_KEY\", \"PARAM_VALUE\" from \"SERDE_PARAMS\" where \"SERDE_ID\" in (" + serdeIds + ") and \"PARAM_KEY\" is not null order by \"SERDE_ID\" asc";
         this.loopJoinOrderedResult(serdes, queryText, 0, new ApplyFunc() {
            public void apply(SerDeInfo t, Object[] fields) {
               t.putToParameters((String)fields[1], MetaStoreDirectSql.this.extractSqlClob(fields[2]));
            }
         });

         for(SerDeInfo t : serdes.values()) {
            t.setParameters(MetaStoreUtils.trimMapNulls(t.getParameters(), this.convertMapNullsToEmptyStrings));
         }

         return orderedResult;
      }
   }

   public int getNumPartitionsViaSqlFilter(SqlFilterForPushdown filter) throws MetaException {
      boolean doTrace = LOG.isDebugEnabled();
      String dbName = filter.table.getDbName().toLowerCase();
      String tblName = filter.table.getTableName().toLowerCase();
      String queryText = "select count(\"PARTITIONS\".\"PART_ID\") from \"PARTITIONS\"  inner join \"TBLS\" on \"PARTITIONS\".\"TBL_ID\" = \"TBLS\".\"TBL_ID\"     and \"TBLS\".\"TBL_NAME\" = ?   inner join \"DBS\" on \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\"      and \"DBS\".\"NAME\" = ? " + StringUtils.join(filter.joins, ' ') + (filter.filter != null && !filter.filter.trim().isEmpty() ? " where " + filter.filter : "");
      Object[] params = new Object[filter.params.size() + 2];
      params[0] = tblName;
      params[1] = dbName;

      for(int i = 0; i < filter.params.size(); ++i) {
         params[i + 2] = filter.params.get(i);
      }

      long start = doTrace ? System.nanoTime() : 0L;
      Query query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      query.setUnique(true);
      int sqlResult = this.extractSqlInt(query.executeWithArray(params));
      long queryTime = doTrace ? System.nanoTime() : 0L;
      this.timingTrace(doTrace, queryText, start, queryTime);
      return sqlResult;
   }

   private void timingTrace(boolean doTrace, String queryText, long start, long queryTime) {
      if (doTrace) {
         LOG.debug("Direct SQL query in " + (double)(queryTime - start) / (double)1000000.0F + "ms + " + (double)(System.nanoTime() - queryTime) / (double)1000000.0F + "ms, the query is [" + queryText + "]");
      }
   }

   static Long extractSqlLong(Object obj) throws MetaException {
      if (obj == null) {
         return null;
      } else if (!(obj instanceof Number)) {
         throw new MetaException("Expected numeric type but got " + obj.getClass().getName());
      } else {
         return ((Number)obj).longValue();
      }
   }

   private static Boolean extractSqlBoolean(Object value) throws MetaException {
      if (value == null) {
         return null;
      } else if (value instanceof Boolean) {
         return (Boolean)value;
      } else {
         Character c = null;
         if (value instanceof String && ((String)value).length() == 1) {
            c = ((String)value).charAt(0);
         }

         if (c == null) {
            return null;
         } else if (c == 'Y') {
            return true;
         } else if (c == 'N') {
            return false;
         } else {
            throw new MetaException("Cannot extract boolean from column value " + value);
         }
      }
   }

   private int extractSqlInt(Object field) {
      return ((Number)field).intValue();
   }

   private String extractSqlString(Object value) {
      return value == null ? null : value.toString();
   }

   static Double extractSqlDouble(Object obj) throws MetaException {
      if (obj == null) {
         return null;
      } else if (!(obj instanceof Number)) {
         throw new MetaException("Expected numeric type but got " + obj.getClass().getName());
      } else {
         return ((Number)obj).doubleValue();
      }
   }

   private String extractSqlClob(Object value) {
      if (value == null) {
         return null;
      } else {
         try {
            if (value instanceof Clob) {
               int maxLength = ((Clob)value).length() < 2147483645L ? (int)((Clob)value).length() : 2147483645;
               return ((Clob)value).getSubString(1L, maxLength);
            } else {
               return value.toString();
            }
         } catch (SQLException var3) {
            return null;
         }
      }
   }

   private static String trimCommaList(StringBuilder sb) {
      if (sb.length() > 0) {
         sb.setLength(sb.length() - 1);
      }

      return sb.toString();
   }

   private int loopJoinOrderedResult(TreeMap tree, String queryText, int keyIndex, ApplyFunc func) throws MetaException {
      boolean doTrace = LOG.isDebugEnabled();
      long start = doTrace ? System.nanoTime() : 0L;
      Query query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      Object result = query.execute();
      long queryTime = doTrace ? System.nanoTime() : 0L;
      if (result == null) {
         query.closeAll();
         return 0;
      } else {
         List<Object[]> list = this.ensureList(result);
         Iterator<Object[]> iter = list.iterator();
         Object[] fields = null;

         for(Map.Entry entry : tree.entrySet()) {
            if (fields == null && !iter.hasNext()) {
               break;
            }

            for(long id = (Long)entry.getKey(); fields != null || iter.hasNext(); fields = null) {
               if (fields == null) {
                  fields = iter.next();
               }

               long nestedId = extractSqlLong(fields[keyIndex]);
               if (nestedId < id) {
                  throw new MetaException("Found entries for unknown ID " + nestedId);
               }

               if (nestedId > id) {
                  break;
               }

               func.apply(entry.getValue(), fields);
            }

            Deadline.checkTimeout();
         }

         int rv = list.size();
         query.closeAll();
         this.timingTrace(doTrace, queryText, start, queryTime);
         return rv;
      }
   }

   public ColumnStatistics getTableStats(final String dbName, final String tableName, List colNames) throws MetaException {
      if (colNames != null && !colNames.isEmpty()) {
         final boolean doTrace = LOG.isDebugEnabled();
         String queryText0 = "select \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from \"TAB_COL_STATS\"  where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" in (";
         Batchable<String, Object[]> b = new Batchable() {
            public List run(List input) throws MetaException {
               String queryText = "select \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from \"TAB_COL_STATS\"  where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" in (" + MetaStoreDirectSql.this.makeParams(input.size()) + ")";
               Object[] params = new Object[input.size() + 2];
               params[0] = dbName;
               params[1] = tableName;

               for(int i = 0; i < input.size(); ++i) {
                  params[i + 2] = input.get(i);
               }

               long start = doTrace ? System.nanoTime() : 0L;
               Query query = MetaStoreDirectSql.this.pm.newQuery("javax.jdo.query.SQL", queryText);
               Object qResult = MetaStoreDirectSql.this.executeWithArray(query, params, queryText);
               MetaStoreDirectSql.this.timingTrace(doTrace, "select \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from \"TAB_COL_STATS\"  where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" in (...)", start, doTrace ? System.nanoTime() : 0L);
               if (qResult == null) {
                  query.closeAll();
                  return null;
               } else {
                  this.addQueryAfterUse(query);
                  return MetaStoreDirectSql.this.ensureList(qResult);
               }
            }
         };
         List<Object[]> list = this.runBatched(colNames, b);
         if (list.isEmpty()) {
            return null;
         } else {
            ColumnStatisticsDesc csd = new ColumnStatisticsDesc(true, dbName, tableName);
            ColumnStatistics result = this.makeColumnStats(list, csd, 0);
            b.closeAllQueries();
            return result;
         }
      } else {
         return null;
      }
   }

   public AggrStats aggrColStatsForPartitions(String dbName, String tableName, List partNames, List colNames, boolean useDensityFunctionForNDVEstimation, double ndvTuner) throws MetaException {
      if (!colNames.isEmpty() && !partNames.isEmpty()) {
         long partsFound = 0L;
         List<ColumnStatisticsObj> colStatsList;
         if (this.isAggregateStatsCacheEnabled && partNames.size() < this.aggrStatsCache.getMaxPartsPerCacheNode()) {
            int maxPartsPerCacheNode = this.aggrStatsCache.getMaxPartsPerCacheNode();
            float fpp = this.aggrStatsCache.getFalsePositiveProbability();
            colStatsList = new ArrayList();
            BloomFilter bloomFilter = this.createPartsBloomFilter(maxPartsPerCacheNode, fpp, partNames);
            boolean computePartsFound = true;

            for(String colName : colNames) {
               AggregateStatsCache.AggrColStats colStatsAggrCached = this.aggrStatsCache.get(dbName, tableName, colName, partNames);
               if (colStatsAggrCached != null) {
                  colStatsList.add(colStatsAggrCached.getColStats());
                  partsFound = colStatsAggrCached.getNumPartsCached();
               } else {
                  if (computePartsFound) {
                     partsFound = this.partsFoundForPartitions(dbName, tableName, partNames, colNames);
                     computePartsFound = false;
                  }

                  List<String> colNamesForDB = new ArrayList();
                  colNamesForDB.add(colName);
                  List<ColumnStatisticsObj> colStatsAggrFromDB = this.columnStatisticsObjForPartitions(dbName, tableName, partNames, colNamesForDB, partsFound, useDensityFunctionForNDVEstimation, ndvTuner);
                  if (!colStatsAggrFromDB.isEmpty()) {
                     ColumnStatisticsObj colStatsAggr = (ColumnStatisticsObj)colStatsAggrFromDB.get(0);
                     colStatsList.add(colStatsAggr);
                     this.aggrStatsCache.add(dbName, tableName, colName, partsFound, colStatsAggr, bloomFilter);
                  }
               }
            }
         } else {
            partsFound = this.partsFoundForPartitions(dbName, tableName, partNames, colNames);
            colStatsList = this.columnStatisticsObjForPartitions(dbName, tableName, partNames, colNames, partsFound, useDensityFunctionForNDVEstimation, ndvTuner);
         }

         LOG.info("useDensityFunctionForNDVEstimation = " + useDensityFunctionForNDVEstimation + "\npartsFound = " + partsFound + "\nColumnStatisticsObj = " + Arrays.toString(colStatsList.toArray()));
         return new AggrStats(colStatsList, partsFound);
      } else {
         LOG.debug("Columns is empty or partNames is empty : Short-circuiting stats eval");
         return new AggrStats(new ArrayList(), 0L);
      }
   }

   private BloomFilter createPartsBloomFilter(int maxPartsPerCacheNode, float fpp, List partNames) {
      BloomFilter bloomFilter = new BloomFilter((long)maxPartsPerCacheNode, (double)fpp);

      for(String partName : partNames) {
         bloomFilter.add(partName.getBytes());
      }

      return bloomFilter;
   }

   private long partsFoundForPartitions(final String dbName, final String tableName, final List partNames, List colNames) throws MetaException {
      assert !colNames.isEmpty() && !partNames.isEmpty();

      final boolean doTrace = LOG.isDebugEnabled();
      String queryText0 = "select count(\"COLUMN_NAME\") from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ?  and \"COLUMN_NAME\" in (%1$s) and \"PARTITION_NAME\" in (%2$s) group by \"PARTITION_NAME\"";
      List<Long> allCounts = this.runBatched(colNames, new Batchable() {
         public List run(final List inputColName) throws MetaException {
            return MetaStoreDirectSql.this.runBatched(partNames, new Batchable() {
               public List run(List inputPartNames) throws MetaException {
                  long partsFound = 0L;
                  String queryText = String.format("select count(\"COLUMN_NAME\") from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ?  and \"COLUMN_NAME\" in (%1$s) and \"PARTITION_NAME\" in (%2$s) group by \"PARTITION_NAME\"", MetaStoreDirectSql.this.makeParams(inputColName.size()), MetaStoreDirectSql.this.makeParams(inputPartNames.size()));
                  long start = doTrace ? System.nanoTime() : 0L;
                  Query query = MetaStoreDirectSql.this.pm.newQuery("javax.jdo.query.SQL", queryText);

                  ArrayList var13;
                  try {
                     Object qResult = MetaStoreDirectSql.this.executeWithArray(query, MetaStoreDirectSql.this.prepareParams(dbName, tableName, inputPartNames, inputColName), queryText);
                     long end = doTrace ? System.nanoTime() : 0L;
                     MetaStoreDirectSql.this.timingTrace(doTrace, queryText, start, end);
                     ForwardQueryResult fqr = (ForwardQueryResult)qResult;
                     Iterator<?> iter = fqr.iterator();

                     while(iter.hasNext()) {
                        if (MetaStoreDirectSql.extractSqlLong(iter.next()) == (long)inputColName.size()) {
                           ++partsFound;
                        }
                     }

                     var13 = Lists.newArrayList(new Long[]{partsFound});
                  } finally {
                     query.closeAll();
                  }

                  return var13;
               }
            });
         }
      });
      long partsFound = 0L;

      for(Long val : allCounts) {
         partsFound += val;
      }

      return partsFound;
   }

   private List columnStatisticsObjForPartitions(final String dbName, final String tableName, final List partNames, List colNames, long partsFound, final boolean useDensityFunctionForNDVEstimation, final double ndvTuner) throws MetaException {
      final boolean areAllPartsFound = partsFound == (long)partNames.size();
      return this.runBatched(colNames, new Batchable() {
         public List run(final List inputColNames) throws MetaException {
            return MetaStoreDirectSql.this.runBatched(partNames, new Batchable() {
               public List run(List inputPartNames) throws MetaException {
                  return MetaStoreDirectSql.this.columnStatisticsObjForPartitionsBatch(dbName, tableName, inputPartNames, inputColNames, areAllPartsFound, useDensityFunctionForNDVEstimation, ndvTuner);
               }
            });
         }
      });
   }

   private List columnStatisticsObjForPartitionsBatch(String dbName, String tableName, List partNames, List colNames, boolean areAllPartsFound, boolean useDensityFunctionForNDVEstimation, double ndvTuner) throws MetaException {
      String commonPrefix = "select \"COLUMN_NAME\", \"COLUMN_TYPE\", min(\"LONG_LOW_VALUE\"), max(\"LONG_HIGH_VALUE\"), min(\"DOUBLE_LOW_VALUE\"), max(\"DOUBLE_HIGH_VALUE\"), min(cast(\"BIG_DECIMAL_LOW_VALUE\" as decimal)), max(cast(\"BIG_DECIMAL_HIGH_VALUE\" as decimal)), sum(\"NUM_NULLS\"), max(\"NUM_DISTINCTS\"), max(\"AVG_COL_LEN\"), max(\"MAX_COL_LEN\"), sum(\"NUM_TRUES\"), sum(\"NUM_FALSES\"), avg((\"LONG_HIGH_VALUE\"-\"LONG_LOW_VALUE\")/cast(\"NUM_DISTINCTS\" as decimal)),avg((\"DOUBLE_HIGH_VALUE\"-\"DOUBLE_LOW_VALUE\")/\"NUM_DISTINCTS\"),avg((cast(\"BIG_DECIMAL_HIGH_VALUE\" as decimal)-cast(\"BIG_DECIMAL_LOW_VALUE\" as decimal))/\"NUM_DISTINCTS\"),sum(\"NUM_DISTINCTS\") from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? ";
      String queryText = null;
      long start = 0L;
      long end = 0L;
      Query query = null;
      boolean doTrace = LOG.isDebugEnabled();
      Object qResult = null;
      ForwardQueryResult fqr = null;
      if (areAllPartsFound) {
         queryText = commonPrefix + " and \"COLUMN_NAME\" in (" + this.makeParams(colNames.size()) + ") and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") group by \"COLUMN_NAME\", \"COLUMN_TYPE\"";
         start = doTrace ? System.nanoTime() : 0L;
         query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
         qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, colNames), queryText);
         if (qResult == null) {
            query.closeAll();
            return Lists.newArrayList();
         } else {
            end = doTrace ? System.nanoTime() : 0L;
            this.timingTrace(doTrace, queryText, start, end);
            List<Object[]> list = this.ensureList(qResult);
            List<ColumnStatisticsObj> colStats = new ArrayList(list.size());

            for(Object[] row : list) {
               colStats.add(this.prepareCSObjWithAdjustedNDV(row, 0, useDensityFunctionForNDVEstimation, ndvTuner));
               Deadline.checkTimeout();
            }

            query.closeAll();
            return colStats;
         }
      } else {
         List<ColumnStatisticsObj> colStats = new ArrayList(colNames.size());
         queryText = "select \"COLUMN_NAME\", \"COLUMN_TYPE\", count(\"PARTITION_NAME\")  from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ?  and \"COLUMN_NAME\" in (" + this.makeParams(colNames.size()) + ") and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") group by \"COLUMN_NAME\", \"COLUMN_TYPE\"";
         start = doTrace ? System.nanoTime() : 0L;
         query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
         qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, colNames), queryText);
         end = doTrace ? System.nanoTime() : 0L;
         this.timingTrace(doTrace, queryText, start, end);
         if (qResult == null) {
            query.closeAll();
            return Lists.newArrayList();
         } else {
            List<String> noExtraColumnNames = new ArrayList();
            Map<String, String[]> extraColumnNameTypeParts = new HashMap();

            for(Object[] row : this.ensureList(qResult)) {
               String colName = (String)row[0];
               String colType = (String)row[1];
               Long count = extractSqlLong(row[2]);
               if (count != (long)partNames.size() && count >= 2L) {
                  extraColumnNameTypeParts.put(colName, new String[]{colType, String.valueOf(count)});
               } else {
                  noExtraColumnNames.add(colName);
               }

               Deadline.checkTimeout();
            }

            query.closeAll();
            if (noExtraColumnNames.size() != 0) {
               queryText = commonPrefix + " and \"COLUMN_NAME\" in (" + this.makeParams(noExtraColumnNames.size()) + ") and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") group by \"COLUMN_NAME\", \"COLUMN_TYPE\"";
               start = doTrace ? System.nanoTime() : 0L;
               query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
               qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, noExtraColumnNames), queryText);
               if (qResult == null) {
                  query.closeAll();
                  return Lists.newArrayList();
               }

               for(Object[] row : this.ensureList(qResult)) {
                  colStats.add(this.prepareCSObjWithAdjustedNDV(row, 0, useDensityFunctionForNDVEstimation, ndvTuner));
                  Deadline.checkTimeout();
               }

               end = doTrace ? System.nanoTime() : 0L;
               this.timingTrace(doTrace, queryText, start, end);
               query.closeAll();
            }

            if (extraColumnNameTypeParts.size() != 0) {
               Map<String, Integer> indexMap = new HashMap();

               for(int index = 0; index < partNames.size(); ++index) {
                  indexMap.put(partNames.get(index), index);
               }

               Map<String, Map<Integer, Object>> sumMap = new HashMap();
               queryText = "select \"COLUMN_NAME\", sum(\"NUM_NULLS\"), sum(\"NUM_TRUES\"), sum(\"NUM_FALSES\"), sum(\"NUM_DISTINCTS\") from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ?  and \"COLUMN_NAME\" in (" + this.makeParams(extraColumnNameTypeParts.size()) + ") and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") group by \"COLUMN_NAME\"";
               start = doTrace ? System.nanoTime() : 0L;
               query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
               List<String> extraColumnNames = new ArrayList();
               extraColumnNames.addAll(extraColumnNameTypeParts.keySet());
               qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, extraColumnNames), queryText);
               if (qResult == null) {
                  query.closeAll();
                  return Lists.newArrayList();
               }

               List var79 = this.ensureList(qResult);
               Integer[] sumIndex = new Integer[]{6, 10, 11, 15};

               for(Object[] row : var79) {
                  Map<Integer, Object> indexToObject = new HashMap();

                  for(int ind = 1; ind < row.length; ++ind) {
                     indexToObject.put(sumIndex[ind - 1], row[ind]);
                  }

                  sumMap.put((String)row[0], indexToObject);
                  Deadline.checkTimeout();
               }

               end = doTrace ? System.nanoTime() : 0L;
               this.timingTrace(doTrace, queryText, start, end);
               query.closeAll();

               for(Map.Entry entry : extraColumnNameTypeParts.entrySet()) {
                  Object[] row = new Object[IExtrapolatePartStatus.colStatNames.length + 2];
                  String colName = (String)entry.getKey();
                  String colType = ((String[])entry.getValue())[0];
                  Long sumVal = Long.parseLong(((String[])entry.getValue())[1]);
                  row[0] = colName;
                  row[1] = colType;
                  IExtrapolatePartStatus extrapolateMethod = new LinearExtrapolatePartStatus();
                  Integer[] index = null;
                  boolean decimal = false;
                  if (colType.toLowerCase().startsWith("decimal")) {
                     index = (Integer[])IExtrapolatePartStatus.indexMaps.get("decimal");
                     decimal = true;
                  } else {
                     index = (Integer[])IExtrapolatePartStatus.indexMaps.get(colType.toLowerCase());
                  }

                  if (index == null) {
                     index = (Integer[])IExtrapolatePartStatus.indexMaps.get("default");
                  }

                  Integer[] var36 = index;
                  int var37 = index.length;

                  for(int var38 = 0; var38 < var37; ++var38) {
                     int colStatIndex = var36[var38];
                     String colStatName = IExtrapolatePartStatus.colStatNames[colStatIndex];
                     if (IExtrapolatePartStatus.aggrTypes[colStatIndex] == IExtrapolatePartStatus.AggrType.Sum) {
                        Object o = ((Map)sumMap.get(colName)).get(colStatIndex);
                        if (o == null) {
                           row[2 + colStatIndex] = null;
                        } else {
                           Long val = extractSqlLong(o);
                           row[2 + colStatIndex] = val / sumVal * (long)partNames.size();
                        }
                     } else if (IExtrapolatePartStatus.aggrTypes[colStatIndex] != IExtrapolatePartStatus.AggrType.Min && IExtrapolatePartStatus.aggrTypes[colStatIndex] != IExtrapolatePartStatus.AggrType.Max) {
                        queryText = "select avg((\"LONG_HIGH_VALUE\"-\"LONG_LOW_VALUE\")/cast(\"NUM_DISTINCTS\" as decimal)),avg((\"DOUBLE_HIGH_VALUE\"-\"DOUBLE_LOW_VALUE\")/\"NUM_DISTINCTS\"),avg((cast(\"BIG_DECIMAL_HIGH_VALUE\" as decimal)-cast(\"BIG_DECIMAL_LOW_VALUE\" as decimal))/\"NUM_DISTINCTS\") from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" = ? and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") group by \"COLUMN_NAME\"";
                        start = doTrace ? System.nanoTime() : 0L;
                        query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
                        qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, Arrays.asList(colName)), queryText);
                        if (qResult == null) {
                           query.closeAll();
                           return Lists.newArrayList();
                        }

                        fqr = (ForwardQueryResult)qResult;
                        Object[] avg = fqr.get(0);
                        row[2 + colStatIndex] = avg[colStatIndex - 12];
                        end = doTrace ? System.nanoTime() : 0L;
                        this.timingTrace(doTrace, queryText, start, end);
                        query.closeAll();
                     } else {
                        if (!decimal) {
                           queryText = "select \"" + colStatName + "\",\"PARTITION_NAME\" from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" = ? and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") order by \"" + colStatName + "\"";
                        } else {
                           queryText = "select \"" + colStatName + "\",\"PARTITION_NAME\" from \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\" = ? and \"PARTITION_NAME\" in (" + this.makeParams(partNames.size()) + ") order by cast(\"" + colStatName + "\" as decimal)";
                        }

                        start = doTrace ? System.nanoTime() : 0L;
                        query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
                        qResult = this.executeWithArray(query, this.prepareParams(dbName, tableName, partNames, Arrays.asList(colName)), queryText);
                        if (qResult == null) {
                           query.closeAll();
                           return Lists.newArrayList();
                        }

                        fqr = (ForwardQueryResult)qResult;
                        Object[] min = fqr.get(0);
                        Object[] max = fqr.get(fqr.size() - 1);
                        end = doTrace ? System.nanoTime() : 0L;
                        this.timingTrace(doTrace, queryText, start, end);
                        query.closeAll();
                        if (min[0] != null && max[0] != null) {
                           row[2 + colStatIndex] = extrapolateMethod.extrapolate(min, max, colStatIndex, indexMap);
                        } else {
                           row[2 + colStatIndex] = null;
                        }
                     }
                  }

                  colStats.add(this.prepareCSObjWithAdjustedNDV(row, 0, useDensityFunctionForNDVEstimation, ndvTuner));
                  Deadline.checkTimeout();
               }
            }

            return colStats;
         }
      }
   }

   private ColumnStatisticsObj prepareCSObj(Object[] row, int i) throws MetaException {
      ColumnStatisticsData data = new ColumnStatisticsData();
      ColumnStatisticsObj cso = new ColumnStatisticsObj((String)row[i++], (String)row[i++], data);
      Object llow = row[i++];
      Object lhigh = row[i++];
      Object dlow = row[i++];
      Object dhigh = row[i++];
      Object declow = row[i++];
      Object dechigh = row[i++];
      Object nulls = row[i++];
      Object dist = row[i++];
      Object avglen = row[i++];
      Object maxlen = row[i++];
      Object trues = row[i++];
      Object falses = row[i++];
      StatObjectConverter.fillColumnStatisticsData(cso.getColType(), data, llow, lhigh, dlow, dhigh, declow, dechigh, nulls, dist, avglen, maxlen, trues, falses);
      return cso;
   }

   private ColumnStatisticsObj prepareCSObjWithAdjustedNDV(Object[] row, int i, boolean useDensityFunctionForNDVEstimation, double ndvTuner) throws MetaException {
      ColumnStatisticsData data = new ColumnStatisticsData();
      ColumnStatisticsObj cso = new ColumnStatisticsObj((String)row[i++], (String)row[i++], data);
      Object llow = row[i++];
      Object lhigh = row[i++];
      Object dlow = row[i++];
      Object dhigh = row[i++];
      Object declow = row[i++];
      Object dechigh = row[i++];
      Object nulls = row[i++];
      Object dist = row[i++];
      Object avglen = row[i++];
      Object maxlen = row[i++];
      Object trues = row[i++];
      Object falses = row[i++];
      Object avgLong = row[i++];
      Object avgDouble = row[i++];
      Object avgDecimal = row[i++];
      Object sumDist = row[i++];
      StatObjectConverter.fillColumnStatisticsData(cso.getColType(), data, llow, lhigh, dlow, dhigh, declow, dechigh, nulls, dist, avglen, maxlen, trues, falses, avgLong, avgDouble, avgDecimal, sumDist, useDensityFunctionForNDVEstimation, ndvTuner);
      return cso;
   }

   private Object[] prepareParams(String dbName, String tableName, List partNames, List colNames) throws MetaException {
      Object[] params = new Object[colNames.size() + partNames.size() + 2];
      int paramI = 0;
      params[paramI++] = dbName;
      params[paramI++] = tableName;

      for(String colName : colNames) {
         params[paramI++] = colName;
      }

      for(String partName : partNames) {
         params[paramI++] = partName;
      }

      return params;
   }

   public List getPartitionStats(final String dbName, final String tableName, final List partNames, List colNames) throws MetaException {
      if (!colNames.isEmpty() && !partNames.isEmpty()) {
         final boolean doTrace = LOG.isDebugEnabled();
         String queryText0 = "select \"PARTITION_NAME\", \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from  \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\"  in (%1$s) AND \"PARTITION_NAME\" in (%2$s) order by \"PARTITION_NAME\"";
         Batchable<String, Object[]> b = new Batchable() {
            public List run(final List inputColNames) throws MetaException {
               Batchable<String, Object[]> b2 = new Batchable() {
                  public List run(List inputPartNames) throws MetaException {
                     String queryText = String.format("select \"PARTITION_NAME\", \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from  \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\"  in (%1$s) AND \"PARTITION_NAME\" in (%2$s) order by \"PARTITION_NAME\"", MetaStoreDirectSql.this.makeParams(inputColNames.size()), MetaStoreDirectSql.this.makeParams(inputPartNames.size()));
                     long start = doTrace ? System.nanoTime() : 0L;
                     Query query = MetaStoreDirectSql.this.pm.newQuery("javax.jdo.query.SQL", queryText);
                     Object qResult = MetaStoreDirectSql.this.executeWithArray(query, MetaStoreDirectSql.this.prepareParams(dbName, tableName, inputPartNames, inputColNames), queryText);
                     MetaStoreDirectSql.this.timingTrace(doTrace, "select \"PARTITION_NAME\", \"COLUMN_NAME\", \"COLUMN_TYPE\", \"LONG_LOW_VALUE\", \"LONG_HIGH_VALUE\", \"DOUBLE_LOW_VALUE\", \"DOUBLE_HIGH_VALUE\", \"BIG_DECIMAL_LOW_VALUE\", \"BIG_DECIMAL_HIGH_VALUE\", \"NUM_NULLS\", \"NUM_DISTINCTS\", \"AVG_COL_LEN\", \"MAX_COL_LEN\", \"NUM_TRUES\", \"NUM_FALSES\", \"LAST_ANALYZED\"  from  \"PART_COL_STATS\" where \"DB_NAME\" = ? and \"TABLE_NAME\" = ? and \"COLUMN_NAME\"  in (%1$s) AND \"PARTITION_NAME\" in (%2$s) order by \"PARTITION_NAME\"", start, doTrace ? System.nanoTime() : 0L);
                     if (qResult == null) {
                        query.closeAll();
                        return Lists.newArrayList();
                     } else {
                        this.addQueryAfterUse(query);
                        return MetaStoreDirectSql.this.ensureList(qResult);
                     }
                  }
               };

               List var3;
               try {
                  var3 = MetaStoreDirectSql.this.runBatched(partNames, b2);
               } finally {
                  this.addQueryAfterUse(b2);
               }

               return var3;
            }
         };
         List<Object[]> list = this.runBatched(colNames, b);
         List<ColumnStatistics> result = new ArrayList(Math.min(list.size(), partNames.size()));
         String lastPartName = null;
         int from = 0;

         for(int i = 0; i <= list.size(); ++i) {
            boolean isLast = i == list.size();
            String partName = isLast ? null : (String)((Object[])list.get(i))[0];
            if (isLast || !partName.equals(lastPartName)) {
               if (from != i) {
                  ColumnStatisticsDesc csd = new ColumnStatisticsDesc(false, dbName, tableName);
                  csd.setPartName(lastPartName);
                  result.add(this.makeColumnStats(list.subList(from, i), csd, 1));
               }

               lastPartName = partName;
               from = i;
               Deadline.checkTimeout();
            }
         }

         b.closeAllQueries();
         return result;
      } else {
         return Lists.newArrayList();
      }
   }

   private ColumnStatistics makeColumnStats(List list, ColumnStatisticsDesc csd, int offset) throws MetaException {
      ColumnStatistics result = new ColumnStatistics();
      result.setStatsDesc(csd);
      List<ColumnStatisticsObj> csos = new ArrayList(list.size());

      for(Object[] row : list) {
         Object laObj = row[offset + 14];
         if (laObj != null && (!csd.isSetLastAnalyzed() || csd.getLastAnalyzed() > extractSqlLong(laObj))) {
            csd.setLastAnalyzed(extractSqlLong(laObj));
         }

         csos.add(this.prepareCSObj(row, offset));
         Deadline.checkTimeout();
      }

      result.setStatsObj(csos);
      return result;
   }

   private List ensureList(Object result) throws MetaException {
      if (!(result instanceof List)) {
         throw new MetaException("Wrong result type " + result.getClass());
      } else {
         return (List)result;
      }
   }

   private String makeParams(int size) {
      return size == 0 ? "" : StringUtils.repeat(",?", size).substring(1);
   }

   private Object executeWithArray(Query query, Object[] params, String sql) throws MetaException {
      try {
         return params == null ? query.execute() : query.executeWithArray(params);
      } catch (Exception ex) {
         String error = "Failed to execute [" + sql + "] with parameters [";
         if (params != null) {
            boolean isFirst = true;

            for(Object param : params) {
               error = error + (isFirst ? "" : ", ") + param;
               isFirst = false;
            }
         }

         LOG.warn(error + "]", ex);
         throw new MetaException("See previous errors; " + ex.getMessage());
      }
   }

   public void prepareTxn() throws MetaException {
      if (this.dbType == DatabaseProduct.MYSQL) {
         try {
            assert this.pm.currentTransaction().isActive();

            this.executeNoResult("SET @@session.sql_mode=ANSI_QUOTES");
         } catch (SQLException sqlEx) {
            throw new MetaException("Error setting ansi quotes: " + sqlEx.getMessage());
         }
      }
   }

   private List runBatched(List input, Batchable runnable) throws MetaException {
      if (this.batchSize != -1 && this.batchSize < input.size()) {
         List<R> result = new ArrayList(input.size());
         int fromIndex = 0;

         for(int toIndex = 0; toIndex < input.size(); fromIndex = toIndex) {
            toIndex = Math.min(fromIndex + this.batchSize, input.size());
            List<I> batchedInput = input.subList(fromIndex, toIndex);
            List<R> batchedOutput = runnable.run(batchedInput);
            if (batchedOutput != null) {
               result.addAll(batchedOutput);
            }
         }

         return result;
      } else {
         return runnable.run(input);
      }
   }

   public List getForeignKeys(String parent_db_name, String parent_tbl_name, String foreign_db_name, String foreign_tbl_name) throws MetaException {
      List<SQLForeignKey> ret = new ArrayList();
      String queryText = "SELECT  \"D2\".\"NAME\", \"T2\".\"TBL_NAME\", \"C2\".\"COLUMN_NAME\",\"DBS\".\"NAME\", \"TBLS\".\"TBL_NAME\", \"COLUMNS_V2\".\"COLUMN_NAME\", \"KEY_CONSTRAINTS\".\"POSITION\", \"KEY_CONSTRAINTS\".\"UPDATE_RULE\", \"KEY_CONSTRAINTS\".\"DELETE_RULE\", \"KEY_CONSTRAINTS\".\"CONSTRAINT_NAME\" , \"KEY_CONSTRAINTS2\".\"CONSTRAINT_NAME\", \"KEY_CONSTRAINTS\".\"ENABLE_VALIDATE_RELY\"  FROM \"TBLS\"  INNER JOIN \"KEY_CONSTRAINTS\" ON \"TBLS\".\"TBL_ID\" = \"KEY_CONSTRAINTS\".\"CHILD_TBL_ID\"  INNER JOIN \"KEY_CONSTRAINTS\" \"KEY_CONSTRAINTS2\" ON \"KEY_CONSTRAINTS2\".\"PARENT_TBL_ID\"  = \"KEY_CONSTRAINTS\".\"PARENT_TBL_ID\"  AND \"KEY_CONSTRAINTS2\".\"PARENT_CD_ID\"  = \"KEY_CONSTRAINTS\".\"PARENT_CD_ID\" AND  \"KEY_CONSTRAINTS2\".\"PARENT_INTEGER_IDX\"  = \"KEY_CONSTRAINTS\".\"PARENT_INTEGER_IDX\"  INNER JOIN \"DBS\" ON \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\"  INNER JOIN \"TBLS\" \"T2\" ON  \"KEY_CONSTRAINTS\".\"PARENT_TBL_ID\" = \"T2\".\"TBL_ID\"  INNER JOIN \"DBS\" \"D2\" ON \"T2\".\"DB_ID\" = \"D2\".\"DB_ID\"  INNER JOIN \"COLUMNS_V2\"  ON \"COLUMNS_V2\".\"CD_ID\" = \"KEY_CONSTRAINTS\".\"CHILD_CD_ID\" AND  \"COLUMNS_V2\".\"INTEGER_IDX\" = \"KEY_CONSTRAINTS\".\"CHILD_INTEGER_IDX\"  INNER JOIN \"COLUMNS_V2\" \"C2\" ON \"C2\".\"CD_ID\" = \"KEY_CONSTRAINTS\".\"PARENT_CD_ID\" AND  \"C2\".\"INTEGER_IDX\" = \"KEY_CONSTRAINTS\".\"PARENT_INTEGER_IDX\"  WHERE \"KEY_CONSTRAINTS\".\"CONSTRAINT_TYPE\" = 1 AND \"KEY_CONSTRAINTS2\".\"CONSTRAINT_TYPE\" = 0 AND" + (foreign_db_name == null ? "" : " \"DBS\".\"NAME\" = ? AND") + (foreign_tbl_name == null ? "" : " \"TBLS\".\"TBL_NAME\" = ? AND") + (parent_tbl_name == null ? "" : " \"T2\".\"TBL_NAME\" = ? AND") + (parent_db_name == null ? "" : " \"D2\".\"NAME\" = ?");
      queryText = queryText.trim();
      if (queryText.endsWith("WHERE")) {
         queryText = queryText.substring(0, queryText.length() - 5);
      }

      if (queryText.endsWith("AND")) {
         queryText = queryText.substring(0, queryText.length() - 3);
      }

      List<String> pms = new ArrayList();
      if (foreign_db_name != null) {
         pms.add(foreign_db_name);
      }

      if (foreign_tbl_name != null) {
         pms.add(foreign_tbl_name);
      }

      if (parent_tbl_name != null) {
         pms.add(parent_tbl_name);
      }

      if (parent_db_name != null) {
         pms.add(parent_db_name);
      }

      Query queryParams = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      List<Object[]> sqlResult = this.ensureList(this.executeWithArray(queryParams, pms.toArray(), queryText));
      if (!sqlResult.isEmpty()) {
         for(Object[] line : sqlResult) {
            int enableValidateRely = this.extractSqlInt(line[11]);
            boolean enable = (enableValidateRely & 4) != 0;
            boolean validate = (enableValidateRely & 2) != 0;
            boolean rely = (enableValidateRely & 1) != 0;
            SQLForeignKey currKey = new SQLForeignKey(this.extractSqlString(line[0]), this.extractSqlString(line[1]), this.extractSqlString(line[2]), this.extractSqlString(line[3]), this.extractSqlString(line[4]), this.extractSqlString(line[5]), this.extractSqlInt(line[6]), this.extractSqlInt(line[7]), this.extractSqlInt(line[8]), this.extractSqlString(line[9]), this.extractSqlString(line[10]), enable, validate, rely);
            ret.add(currKey);
         }
      }

      return ret;
   }

   public List getPrimaryKeys(String db_name, String tbl_name) throws MetaException {
      List<SQLPrimaryKey> ret = new ArrayList();
      String queryText = "SELECT \"DBS\".\"NAME\", \"TBLS\".\"TBL_NAME\", \"COLUMNS_V2\".\"COLUMN_NAME\",\"KEY_CONSTRAINTS\".\"POSITION\", \"KEY_CONSTRAINTS\".\"CONSTRAINT_NAME\", \"KEY_CONSTRAINTS\".\"ENABLE_VALIDATE_RELY\"  FROM  \"TBLS\"  INNER  JOIN \"KEY_CONSTRAINTS\" ON \"TBLS\".\"TBL_ID\" = \"KEY_CONSTRAINTS\".\"PARENT_TBL_ID\"  INNER JOIN \"DBS\" ON \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\"  INNER JOIN \"COLUMNS_V2\" ON \"COLUMNS_V2\".\"CD_ID\" = \"KEY_CONSTRAINTS\".\"PARENT_CD_ID\" AND  \"COLUMNS_V2\".\"INTEGER_IDX\" = \"KEY_CONSTRAINTS\".\"PARENT_INTEGER_IDX\"  WHERE \"KEY_CONSTRAINTS\".\"CONSTRAINT_TYPE\" = 0 AND " + (db_name == null ? "" : "\"DBS\".\"NAME\" = ? AND") + (tbl_name == null ? "" : " \"TBLS\".\"TBL_NAME\" = ? ");
      queryText = queryText.trim();
      if (queryText.endsWith("WHERE")) {
         queryText = queryText.substring(0, queryText.length() - 5);
      }

      if (queryText.endsWith("AND")) {
         queryText = queryText.substring(0, queryText.length() - 3);
      }

      List<String> pms = new ArrayList();
      if (db_name != null) {
         pms.add(db_name);
      }

      if (tbl_name != null) {
         pms.add(tbl_name);
      }

      Query queryParams = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      List<Object[]> sqlResult = this.ensureList(this.executeWithArray(queryParams, pms.toArray(), queryText));
      if (!sqlResult.isEmpty()) {
         for(Object[] line : sqlResult) {
            int enableValidateRely = this.extractSqlInt(line[5]);
            boolean enable = (enableValidateRely & 4) != 0;
            boolean validate = (enableValidateRely & 2) != 0;
            boolean rely = (enableValidateRely & 1) != 0;
            SQLPrimaryKey currKey = new SQLPrimaryKey(this.extractSqlString(line[0]), this.extractSqlString(line[1]), this.extractSqlString(line[2]), this.extractSqlInt(line[3]), this.extractSqlString(line[4]), enable, validate, rely);
            ret.add(currKey);
         }
      }

      return ret;
   }

   long updateTableParam(Table table, String key, String expectedValue, String newValue) {
      String queryText = String.format("UPDATE \"TABLE_PARAMS\" SET \"PARAM_VALUE\" = ? WHERE \"PARAM_KEY\" = ? AND \"PARAM_VALUE\" = ? AND \"TBL_ID\" IN (SELECT \"TBL_ID\" FROM \"TBLS\" JOIN \"DBS\" ON \"TBLS\".\"DB_ID\" = \"DBS\".\"DB_ID\" WHERE \"TBL_NAME\" = '%s' AND \"NAME\" = '%s')", table.getTableName(), table.getDbName());
      Query query = this.pm.newQuery("javax.jdo.query.SQL", queryText);
      return (Long)query.executeWithArray(new Object[]{newValue, key, expectedValue});
   }

   public static class SqlFilterForPushdown {
      private List params = new ArrayList();
      private List joins = new ArrayList();
      private String filter;
      private Table table;
   }

   private abstract class ApplyFunc {
      private ApplyFunc() {
      }

      public abstract void apply(Object var1, Object[] var2) throws MetaException;
   }

   private static class PartitionFilterGenerator extends ExpressionTree.TreeVisitor {
      private final Table table;
      private final ExpressionTree.FilterBuilder filterBuffer;
      private final List params;
      private final List joins;
      private final boolean dbHasJoinCastBug;
      private final String defaultPartName;
      private final DatabaseProduct dbType;

      private PartitionFilterGenerator(Table table, List params, List joins, boolean dbHasJoinCastBug, String defaultPartName, DatabaseProduct dbType) {
         this.table = table;
         this.params = params;
         this.joins = joins;
         this.dbHasJoinCastBug = dbHasJoinCastBug;
         this.filterBuffer = new ExpressionTree.FilterBuilder(false);
         this.defaultPartName = defaultPartName;
         this.dbType = dbType;
      }

      private static String generateSqlFilter(Table table, ExpressionTree tree, List params, List joins, boolean dbHasJoinCastBug, String defaultPartName, DatabaseProduct dbType) throws MetaException {
         assert table != null;

         if (tree == null) {
            return null;
         } else if (tree.getRoot() == null) {
            return "";
         } else {
            PartitionFilterGenerator visitor = new PartitionFilterGenerator(table, params, joins, dbHasJoinCastBug, defaultPartName, dbType);
            tree.accept(visitor);
            if (visitor.filterBuffer.hasError()) {
               MetaStoreDirectSql.LOG.info("Unable to push down SQL filter: " + visitor.filterBuffer.getErrorMessage());
               return null;
            } else {
               for(int i = 0; i < joins.size(); ++i) {
                  if (joins.get(i) == null) {
                     joins.remove(i--);
                  }
               }

               return "(" + visitor.filterBuffer.getFilter() + ")";
            }
         }
      }

      protected void beginTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.filterBuffer.append(" (");
      }

      protected void midTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.filterBuffer.append(node.getAndOr() == ExpressionTree.LogicalOperator.AND ? " and " : " or ");
      }

      protected void endTreeNode(ExpressionTree.TreeNode node) throws MetaException {
         this.filterBuffer.append(") ");
      }

      protected boolean shouldStop() {
         return this.filterBuffer.hasError();
      }

      public void visit(ExpressionTree.LeafNode node) throws MetaException {
         if (node.operator == ExpressionTree.Operator.LIKE) {
            this.filterBuffer.setError("LIKE is not supported for SQL filter pushdown");
         } else {
            int partColCount = this.table.getPartitionKeys().size();
            int partColIndex = node.getPartColIndexForFilter(this.table, this.filterBuffer);
            if (!this.filterBuffer.hasError()) {
               String colTypeStr = ((FieldSchema)this.table.getPartitionKeys().get(partColIndex)).getType();
               FilterType colType = MetaStoreDirectSql.PartitionFilterGenerator.FilterType.fromType(colTypeStr);
               if (colType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Invalid) {
                  this.filterBuffer.setError("Filter pushdown not supported for type " + colTypeStr);
               } else {
                  FilterType valType = MetaStoreDirectSql.PartitionFilterGenerator.FilterType.fromClass(node.value);
                  Object nodeValue = node.value;
                  if (valType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Invalid) {
                     this.filterBuffer.setError("Filter pushdown not supported for value " + node.value.getClass());
                  } else {
                     if (colType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Date && valType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.String) {
                        try {
                           nodeValue = new Date(((DateFormat)HiveMetaStore.PARTITION_DATE_FORMAT.get()).parse((String)nodeValue).getTime());
                           valType = MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Date;
                        } catch (ParseException var11) {
                        }
                     }

                     if (colType != valType) {
                        this.filterBuffer.setError("Cannot push down filter for " + colTypeStr + " column and value " + nodeValue.getClass());
                     } else {
                        if (this.joins.isEmpty()) {
                           for(int i = 0; i < partColCount; ++i) {
                              this.joins.add((Object)null);
                           }
                        }

                        if (this.joins.get(partColIndex) == null) {
                           this.joins.set(partColIndex, "inner join \"PARTITION_KEY_VALS\" \"FILTER" + partColIndex + "\" on \"FILTER" + partColIndex + "\".\"PART_ID\" = \"PARTITIONS\".\"PART_ID\" and \"FILTER" + partColIndex + "\".\"INTEGER_IDX\" = " + partColIndex);
                        }

                        String tableValue = "\"FILTER" + partColIndex + "\".\"PART_KEY_VAL\"";
                        if (node.isReverseOrder) {
                           this.params.add(nodeValue);
                        }

                        String tableColumn = tableValue;
                        if (colType != MetaStoreDirectSql.PartitionFilterGenerator.FilterType.String) {
                           if (colType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Integral) {
                              tableValue = "cast(" + tableValue + " as decimal(21,0))";
                           } else if (colType == MetaStoreDirectSql.PartitionFilterGenerator.FilterType.Date) {
                              if (this.dbType == DatabaseProduct.ORACLE) {
                                 tableValue = "TO_DATE(" + tableValue + ", 'YYYY-MM-DD')";
                              } else {
                                 tableValue = "cast(" + tableValue + " as date)";
                              }
                           }

                           tableValue = "(case when " + tableColumn + " <> ?";
                           this.params.add(this.defaultPartName);
                           if (this.dbHasJoinCastBug) {
                              tableValue = tableValue + " and \"TBLS\".\"TBL_NAME\" = ? and \"DBS\".\"NAME\" = ? and \"FILTER" + partColIndex + "\".\"PART_ID\" = \"PARTITIONS\".\"PART_ID\" and \"FILTER" + partColIndex + "\".\"INTEGER_IDX\" = " + partColIndex;
                              this.params.add(this.table.getTableName().toLowerCase());
                              this.params.add(this.table.getDbName().toLowerCase());
                           }

                           tableValue = tableValue + " then " + tableValue + " else null end)";
                        }

                        if (!node.isReverseOrder) {
                           this.params.add(nodeValue);
                        }

                        this.filterBuffer.append(node.isReverseOrder ? "(? " + node.operator.getSqlOp() + " " + tableValue + ")" : "(" + tableValue + " " + node.operator.getSqlOp() + " ?)");
                     }
                  }
               }
            }
         }
      }

      private static enum FilterType {
         Integral,
         String,
         Date,
         Invalid;

         static FilterType fromType(String colTypeStr) {
            if (colTypeStr.equals("string")) {
               return String;
            } else if (colTypeStr.equals("date")) {
               return Date;
            } else {
               return serdeConstants.IntegralTypes.contains(colTypeStr) ? Integral : Invalid;
            }
         }

         public static FilterType fromClass(Object value) {
            if (value instanceof String) {
               return String;
            } else if (value instanceof Long) {
               return Integral;
            } else {
               return value instanceof Date ? Date : Invalid;
            }
         }
      }
   }

   private abstract static class Batchable {
      private List queries;

      private Batchable() {
         this.queries = null;
      }

      public abstract List run(List var1) throws MetaException;

      public void addQueryAfterUse(Query query) {
         if (this.queries == null) {
            this.queries = new ArrayList(1);
         }

         this.queries.add(query);
      }

      protected void addQueryAfterUse(Batchable b) {
         if (b.queries != null) {
            if (this.queries == null) {
               this.queries = new ArrayList(1);
            }

            this.queries.addAll(b.queries);
         }
      }

      public void closeAllQueries() {
         for(Query q : this.queries) {
            try {
               q.closeAll();
            } catch (Throwable t) {
               MetaStoreDirectSql.LOG.error("Failed to close a query", t);
            }
         }

      }
   }
}
