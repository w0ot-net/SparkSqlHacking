package org.apache.hadoop.hive.metastore;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.common.StatsSetupConst;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.ColumnStatistics;
import org.apache.hadoop.hive.metastore.api.ColumnStatisticsObj;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.hbase.stats.merge.ColumnStatsMerger;
import org.apache.hadoop.hive.metastore.hbase.stats.merge.ColumnStatsMergerFactory;
import org.apache.hadoop.hive.metastore.partition.spec.PartitionSpecProxy;
import org.apache.hadoop.hive.serde.serdeConstants;
import org.apache.hadoop.hive.serde2.Deserializer;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeUtils;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.hive.thrift.HadoopThriftAuthBridge;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hive.common.util.HiveStringUtils;
import org.apache.hive.common.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaStoreUtils {
   protected static final Logger LOG = LoggerFactory.getLogger("hive.log");
   public static final String DEFAULT_DATABASE_NAME = "default";
   public static final String DEFAULT_DATABASE_COMMENT = "Default Hive database";
   public static final String DEFAULT_SERIALIZATION_FORMAT = "1";
   public static final String DATABASE_WAREHOUSE_SUFFIX = ".db";
   public static final int RETRY_COUNT = 10;
   public static final char[] specialCharactersInTableNames = new char[]{'/'};
   public static final String TYPE_FROM_DESERIALIZER = "<derived from deserializer>";
   static HashMap typeToThriftTypeMap = new HashMap();
   static Set hiveThriftTypeMap;
   private static final String FROM_SERIALIZER = "from deserializer";
   private static final PathFilter hiddenFileFilter;
   public static String ARCHIVING_LEVEL;
   private static final Function transFormNullsToEmptyString;

   public static Table createColumnsetSchema(String name, List columns, List partCols, Configuration conf) throws MetaException {
      if (columns == null) {
         throw new MetaException("columns not specified for table " + name);
      } else {
         Table tTable = new Table();
         tTable.setTableName(name);
         tTable.setSd(new StorageDescriptor());
         StorageDescriptor sd = tTable.getSd();
         sd.setSerdeInfo(new SerDeInfo());
         SerDeInfo serdeInfo = sd.getSerdeInfo();
         serdeInfo.setSerializationLib(LazySimpleSerDe.class.getName());
         serdeInfo.setParameters(new HashMap());
         serdeInfo.getParameters().put("serialization.format", "1");
         List<FieldSchema> fields = new ArrayList();
         sd.setCols(fields);

         for(String col : columns) {
            FieldSchema field = new FieldSchema(col, "string", "'default'");
            fields.add(field);
         }

         tTable.setPartitionKeys(new ArrayList());

         for(String partCol : partCols) {
            FieldSchema part = new FieldSchema();
            part.setName(partCol);
            part.setType("string");
            tTable.getPartitionKeys().add(part);
         }

         sd.setNumBuckets(-1);
         return tTable;
      }
   }

   public static void recursiveDelete(File f) throws IOException {
      if (f.isDirectory()) {
         File[] fs = f.listFiles();

         for(File subf : fs) {
            recursiveDelete(subf);
         }
      }

      if (!f.delete()) {
         throw new IOException("could not delete: " + f.getPath());
      }
   }

   public static boolean containsAllFastStats(Map partParams) {
      for(String stat : StatsSetupConst.fastStats) {
         if (!partParams.containsKey(stat)) {
            return false;
         }
      }

      return true;
   }

   public static boolean updateTableStatsFast(Database db, Table tbl, Warehouse wh, boolean madeDir, EnvironmentContext environmentContext) throws MetaException {
      return updateTableStatsFast(db, tbl, wh, madeDir, false, environmentContext);
   }

   public static boolean updateTableStatsFast(Database db, Table tbl, Warehouse wh, boolean madeDir, boolean forceRecompute, EnvironmentContext environmentContext) throws MetaException {
      if (tbl.getPartitionKeysSize() == 0) {
         FileStatus[] fileStatuses = wh.getFileStatusesForUnpartitionedTable(db, tbl);
         return updateTableStatsFast(tbl, fileStatuses, madeDir, forceRecompute, environmentContext);
      } else {
         return false;
      }
   }

   public static boolean updateTableStatsFast(Table tbl, FileStatus[] fileStatus, boolean newDir, boolean forceRecompute, EnvironmentContext environmentContext) throws MetaException {
      Map<String, String> params = tbl.getParameters();
      if (params != null && params.containsKey("DO_NOT_UPDATE_STATS")) {
         boolean doNotUpdateStats = Boolean.valueOf((String)params.get("DO_NOT_UPDATE_STATS"));
         params.remove("DO_NOT_UPDATE_STATS");
         tbl.setParameters(params);
         if (doNotUpdateStats) {
            return false;
         }
      }

      boolean updated = false;
      if (forceRecompute || params == null || !containsAllFastStats(params)) {
         if (params == null) {
            params = new HashMap();
         }

         if (!newDir) {
            LOG.info("Updating table stats fast for " + tbl.getTableName());
            populateQuickStats(fileStatus, params);
            LOG.info("Updated size of table " + tbl.getTableName() + " to " + (String)params.get("totalSize"));
            if (environmentContext != null && environmentContext.isSetProperties() && "TASK".equals(environmentContext.getProperties().get("STATS_GENERATED"))) {
               StatsSetupConst.setBasicStatsState(params, "true");
            } else {
               StatsSetupConst.setBasicStatsState(params, "false");
            }
         }

         tbl.setParameters(params);
         updated = true;
      }

      return updated;
   }

   public static void populateQuickStats(FileStatus[] fileStatus, Map params) {
      int numFiles = 0;
      long tableSize = 0L;

      for(FileStatus status : fileStatus) {
         if (!status.isDir()) {
            tableSize += status.getLen();
            ++numFiles;
         }
      }

      params.put("numFiles", Integer.toString(numFiles));
      params.put("totalSize", Long.toString(tableSize));
   }

   public static boolean requireCalStats(Configuration hiveConf, Partition oldPart, Partition newPart, Table tbl, EnvironmentContext environmentContext) {
      if (environmentContext != null && environmentContext.isSetProperties() && "true".equals(environmentContext.getProperties().get("DO_NOT_UPDATE_STATS"))) {
         return false;
      } else if (isView(tbl)) {
         return false;
      } else if (oldPart == null && newPart == null) {
         return true;
      } else if (newPart != null && newPart.getParameters() != null && containsAllFastStats(newPart.getParameters())) {
         if (environmentContext != null && environmentContext.isSetProperties()) {
            String statsType = (String)environmentContext.getProperties().get("STATS_GENERATED");
            if ("TASK".equals(statsType) || "USER".equals(statsType)) {
               return true;
            }
         }

         return !isFastStatsSame(oldPart, newPart);
      } else {
         return true;
      }
   }

   static boolean isFastStatsSame(Partition oldPart, Partition newPart) {
      if (oldPart != null && oldPart.getParameters() != null) {
         for(String stat : StatsSetupConst.fastStats) {
            if (!oldPart.getParameters().containsKey(stat)) {
               return false;
            }

            Long oldStat = Long.parseLong((String)oldPart.getParameters().get(stat));
            String newStat = (String)newPart.getParameters().get(stat);
            if (newStat == null || !oldStat.equals(Long.parseLong(newStat))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean updatePartitionStatsFast(Partition part, Warehouse wh, EnvironmentContext environmentContext) throws MetaException {
      return updatePartitionStatsFast(part, wh, false, false, environmentContext);
   }

   public static boolean updatePartitionStatsFast(Partition part, Warehouse wh, boolean madeDir, EnvironmentContext environmentContext) throws MetaException {
      return updatePartitionStatsFast(part, wh, madeDir, false, environmentContext);
   }

   public static boolean updatePartitionStatsFast(Partition part, Warehouse wh, boolean madeDir, boolean forceRecompute, EnvironmentContext environmentContext) throws MetaException {
      return updatePartitionStatsFast((PartitionSpecProxy.PartitionIterator)(new PartitionSpecProxy.SimplePartitionWrapperIterator(part)), wh, madeDir, forceRecompute, environmentContext);
   }

   public static boolean updatePartitionStatsFast(PartitionSpecProxy.PartitionIterator part, Warehouse wh, boolean madeDir, boolean forceRecompute, EnvironmentContext environmentContext) throws MetaException {
      Map<String, String> params = part.getParameters();
      boolean updated = false;
      if (forceRecompute || params == null || !containsAllFastStats(params)) {
         if (params == null) {
            params = new HashMap();
         }

         if (!madeDir) {
            LOG.warn("Updating partition stats fast for: " + part.getTableName());
            FileStatus[] fileStatus = wh.getFileStatusesForLocation(part.getLocation());
            populateQuickStats(fileStatus, params);
            LOG.warn("Updated size to " + (String)params.get("totalSize"));
            updateBasicState(environmentContext, params);
         }

         part.setParameters(params);
         updated = true;
      }

      return updated;
   }

   static void updateBasicState(EnvironmentContext environmentContext, Map params) {
      if (params != null) {
         if (environmentContext != null && environmentContext.isSetProperties() && "TASK".equals(environmentContext.getProperties().get("STATS_GENERATED"))) {
            StatsSetupConst.setBasicStatsState(params, "true");
         } else {
            StatsSetupConst.setBasicStatsState(params, "false");
         }

      }
   }

   public static Deserializer getDeserializer(Configuration conf, Table table, boolean skipConfError) throws MetaException {
      String lib = table.getSd().getSerdeInfo().getSerializationLib();
      return lib == null ? null : getDeserializer(conf, table, skipConfError, lib);
   }

   public static Deserializer getDeserializer(Configuration conf, Table table, boolean skipConfError, String lib) throws MetaException {
      try {
         Deserializer deserializer = (Deserializer)ReflectionUtil.newInstance(conf.getClassByName(lib).asSubclass(Deserializer.class), conf);
         if (skipConfError) {
            SerDeUtils.initializeSerDeWithoutErrorCheck(deserializer, conf, getTableMetadata(table), (Properties)null);
         } else {
            SerDeUtils.initializeSerDe(deserializer, conf, getTableMetadata(table), (Properties)null);
         }

         return deserializer;
      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         LOG.error("error in initSerDe: " + e.getClass().getName() + " " + e.getMessage(), e);
         throw new MetaException(e.getClass().getName() + " " + e.getMessage());
      }
   }

   public static Class getDeserializerClass(Configuration conf, Table table) throws Exception {
      String lib = table.getSd().getSerdeInfo().getSerializationLib();
      return lib == null ? null : conf.getClassByName(lib).asSubclass(Deserializer.class);
   }

   public static Deserializer getDeserializer(Configuration conf, Partition part, Table table) throws MetaException {
      String lib = part.getSd().getSerdeInfo().getSerializationLib();

      try {
         Deserializer deserializer = (Deserializer)ReflectionUtil.newInstance(conf.getClassByName(lib).asSubclass(Deserializer.class), conf);
         SerDeUtils.initializeSerDe(deserializer, conf, getTableMetadata(table), getPartitionMetadata(part, table));
         return deserializer;
      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         LOG.error("error in initSerDe: " + e.getClass().getName() + " " + e.getMessage(), e);
         throw new MetaException(e.getClass().getName() + " " + e.getMessage());
      }
   }

   public static void deleteWHDirectory(Path path, Configuration conf, boolean use_trash) throws MetaException {
      try {
         if (!path.getFileSystem(conf).exists(path)) {
            LOG.warn("drop data called on table/partition with no directory: " + path);
         } else {
            if (use_trash) {
               int count = 0;
               Path newPath = new Path("/Trash/Current" + path.getParent().toUri().getPath());
               if (!path.getFileSystem(conf).exists(newPath)) {
                  path.getFileSystem(conf).mkdirs(newPath);
               }

               do {
                  newPath = new Path("/Trash/Current" + path.toUri().getPath() + "." + count);
                  if (path.getFileSystem(conf).exists(newPath)) {
                     ++count;
                  } else if (path.getFileSystem(conf).rename(path, newPath)) {
                     break;
                  }

                  ++count;
               } while(count < 50);

               if (count >= 50) {
                  throw new MetaException("Rename failed due to maxing out retries");
               }
            } else {
               path.getFileSystem(conf).delete(path, true);
            }

         }
      } catch (IOException e) {
         LOG.error("Got exception trying to delete data dir: " + e);
         throw new MetaException(e.getMessage());
      } catch (MetaException e) {
         LOG.error("Got exception trying to delete data dir: " + e);
         throw e;
      }
   }

   public static List getPvals(List partCols, Map partSpec) {
      List<String> pvals = new ArrayList();

      for(FieldSchema field : partCols) {
         String val = (String)partSpec.get(field.getName());
         if (val == null) {
            val = "";
         }

         pvals.add(val);
      }

      return pvals;
   }

   public static boolean validateName(String name, Configuration conf) {
      Pattern tpat = null;
      String allowedCharacters = "\\w_";
      if (conf != null && HiveConf.getBoolVar(conf, ConfVars.HIVE_SUPPORT_SPECICAL_CHARACTERS_IN_TABLE_NAMES)) {
         char[] var4 = specialCharactersInTableNames;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            Character c = var4[var6];
            allowedCharacters = allowedCharacters + c;
         }
      }

      tpat = Pattern.compile("[" + allowedCharacters + "]+");
      Matcher m = tpat.matcher(name);
      return m.matches();
   }

   public static final boolean validateColumnName(String name) {
      return true;
   }

   public static String validateTblColumns(List cols) {
      for(FieldSchema fieldSchema : cols) {
         if (!validateColumnName(fieldSchema.getName())) {
            return "name: " + fieldSchema.getName();
         }

         String typeError = validateColumnType(fieldSchema.getType());
         if (typeError != null) {
            return typeError;
         }
      }

      return null;
   }

   static void throwExceptionIfIncompatibleColTypeChange(List oldCols, List newCols) throws InvalidOperationException {
      List<String> incompatibleCols = new ArrayList();
      int maxCols = Math.min(oldCols.size(), newCols.size());

      for(int i = 0; i < maxCols; ++i) {
         if (!areColTypesCompatible(((FieldSchema)oldCols.get(i)).getType(), ((FieldSchema)newCols.get(i)).getType())) {
            incompatibleCols.add(((FieldSchema)newCols.get(i)).getName());
         }
      }

      if (!incompatibleCols.isEmpty()) {
         throw new InvalidOperationException("The following columns have types incompatible with the existing columns in their respective positions :\n" + StringUtils.join(incompatibleCols, ','));
      }
   }

   static boolean isCascadeNeededInAlterTable(Table oldTable, Table newTable) {
      List<FieldSchema> oldCols = oldTable.getSd().getCols();
      List<FieldSchema> newCols = newTable.getSd().getCols();
      return !areSameColumns(oldCols, newCols);
   }

   static boolean areSameColumns(List oldCols, List newCols) {
      if (oldCols.size() != newCols.size()) {
         return false;
      } else {
         for(int i = 0; i < oldCols.size(); ++i) {
            FieldSchema oldCol = (FieldSchema)oldCols.get(i);
            FieldSchema newCol = (FieldSchema)newCols.get(i);
            if (!oldCol.equals(newCol)) {
               return false;
            }
         }

         return true;
      }
   }

   static boolean columnsIncluded(List oldCols, List newCols) {
      if (oldCols.size() > newCols.size()) {
         return false;
      } else {
         Set<FieldSchema> newColsSet = new HashSet(newCols);

         for(FieldSchema oldCol : oldCols) {
            if (!newColsSet.contains(oldCol)) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean areColTypesCompatible(String oldType, String newType) {
      return TypeInfoUtils.implicitConvertible(TypeInfoUtils.getTypeInfoFromTypeString(oldType), TypeInfoUtils.getTypeInfoFromTypeString(newType));
   }

   public static String validateColumnType(String type) {
      if (type.equals("<derived from deserializer>")) {
         return null;
      } else {
         int last = 0;
         boolean lastAlphaDigit = isValidTypeChar(type.charAt(last));

         for(int i = 1; i <= type.length(); ++i) {
            if (i == type.length() || isValidTypeChar(type.charAt(i)) != lastAlphaDigit) {
               String token = type.substring(last, i);
               if (!hiveThriftTypeMap.contains(token)) {
                  return "type: " + type;
               }
               break;
            }
         }

         return null;
      }
   }

   private static boolean isValidTypeChar(char c) {
      return Character.isLetterOrDigit(c) || c == '_';
   }

   public static String validateSkewedColNames(List cols) {
      if (null == cols) {
         return null;
      } else {
         for(String col : cols) {
            if (!validateColumnName(col)) {
               return col;
            }
         }

         return null;
      }
   }

   public static String validateSkewedColNamesSubsetCol(List skewedColNames, List cols) {
      if (null == skewedColNames) {
         return null;
      } else {
         List<String> colNames = new ArrayList();

         for(FieldSchema fieldSchema : cols) {
            colNames.add(fieldSchema.getName());
         }

         List<String> copySkewedColNames = new ArrayList(skewedColNames);
         copySkewedColNames.removeAll(colNames);
         return copySkewedColNames.isEmpty() ? null : copySkewedColNames.toString();
      }
   }

   public static String getListType(String t) {
      return "array<" + t + ">";
   }

   public static String getMapType(String k, String v) {
      return "map<" + k + "," + v + ">";
   }

   public static void setSerdeParam(SerDeInfo sdi, Properties schema, String param) {
      String val = schema.getProperty(param);
      if (StringUtils.isNotBlank(val)) {
         sdi.getParameters().put(param, val);
      }

   }

   public static String typeToThriftType(String type) {
      StringBuilder thriftType = new StringBuilder();
      int last = 0;
      boolean lastAlphaDigit = Character.isLetterOrDigit(type.charAt(last));

      for(int i = 1; i <= type.length(); ++i) {
         if (i == type.length() || Character.isLetterOrDigit(type.charAt(i)) != lastAlphaDigit) {
            String token = type.substring(last, i);
            last = i;
            String thriftToken = (String)typeToThriftTypeMap.get(token);
            thriftType.append(thriftToken == null ? token : thriftToken);
            lastAlphaDigit = !lastAlphaDigit;
         }
      }

      return thriftType.toString();
   }

   public static String getFullDDLFromFieldSchema(String structName, List fieldSchemas) {
      StringBuilder ddl = new StringBuilder();
      ddl.append(getDDLFromFieldSchema(structName, fieldSchemas));
      ddl.append('#');
      StringBuilder colnames = new StringBuilder();
      StringBuilder coltypes = new StringBuilder();
      boolean first = true;

      for(FieldSchema col : fieldSchemas) {
         if (first) {
            first = false;
         } else {
            colnames.append(',');
            coltypes.append(':');
         }

         colnames.append(col.getName());
         coltypes.append(col.getType());
      }

      ddl.append(colnames);
      ddl.append('#');
      ddl.append(coltypes);
      return ddl.toString();
   }

   public static String getDDLFromFieldSchema(String structName, List fieldSchemas) {
      StringBuilder ddl = new StringBuilder();
      ddl.append("struct ");
      ddl.append(structName);
      ddl.append(" { ");
      boolean first = true;

      for(FieldSchema col : fieldSchemas) {
         if (first) {
            first = false;
         } else {
            ddl.append(", ");
         }

         ddl.append(typeToThriftType(col.getType()));
         ddl.append(' ');
         ddl.append(col.getName());
      }

      ddl.append("}");
      LOG.debug("DDL: " + ddl);
      return ddl.toString();
   }

   public static Properties getTableMetadata(Table table) {
      return getSchema(table.getSd(), table.getSd(), table.getParameters(), table.getDbName(), table.getTableName(), table.getPartitionKeys());
   }

   public static Properties getPartitionMetadata(Partition partition, Table table) {
      return getSchema(partition.getSd(), partition.getSd(), partition.getParameters(), table.getDbName(), table.getTableName(), table.getPartitionKeys());
   }

   public static Properties getSchema(Partition part, Table table) {
      return getSchema(part.getSd(), table.getSd(), table.getParameters(), table.getDbName(), table.getTableName(), table.getPartitionKeys());
   }

   public static Properties getPartSchemaFromTableSchema(StorageDescriptor sd, StorageDescriptor tblsd, Map parameters, String databaseName, String tableName, List partitionKeys, Properties tblSchema) {
      Properties schema = (Properties)tblSchema.clone();
      String inputFormat = sd.getInputFormat();
      if (inputFormat == null || inputFormat.length() == 0) {
         String tblInput = schema.getProperty("file.inputformat");
         if (tblInput == null) {
            inputFormat = SequenceFileInputFormat.class.getName();
         } else {
            inputFormat = tblInput;
         }
      }

      schema.setProperty("file.inputformat", inputFormat);
      String outputFormat = sd.getOutputFormat();
      if (outputFormat == null || outputFormat.length() == 0) {
         String tblOutput = schema.getProperty("file.outputformat");
         if (tblOutput == null) {
            outputFormat = SequenceFileOutputFormat.class.getName();
         } else {
            outputFormat = tblOutput;
         }
      }

      schema.setProperty("file.outputformat", outputFormat);
      if (sd.getLocation() != null) {
         schema.setProperty("location", sd.getLocation());
      }

      schema.setProperty("bucket_count", Integer.toString(sd.getNumBuckets()));
      if (sd.getBucketCols() != null && sd.getBucketCols().size() > 0) {
         schema.setProperty("bucket_field_name", (String)sd.getBucketCols().get(0));
      }

      if (sd.getSerdeInfo() != null) {
         String cols = "columns";
         String colTypes = "columns.types";
         String parts = "partition_columns";

         for(Map.Entry param : sd.getSerdeInfo().getParameters().entrySet()) {
            String key = (String)param.getKey();
            if (schema.get(key) == null || !key.equals(cols) && !key.equals(colTypes) && !key.equals(parts)) {
               schema.put(key, param.getValue() != null ? (String)param.getValue() : "");
            }
         }

         if (sd.getSerdeInfo().getSerializationLib() != null) {
            schema.setProperty("serialization.lib", sd.getSerdeInfo().getSerializationLib());
         }
      }

      if (parameters != null) {
         for(Map.Entry e : parameters.entrySet()) {
            schema.setProperty((String)e.getKey(), (String)e.getValue());
         }
      }

      return schema;
   }

   public static Properties addCols(Properties schema, List cols) {
      StringBuilder colNameBuf = new StringBuilder();
      StringBuilder colTypeBuf = new StringBuilder();
      StringBuilder colComment = new StringBuilder();
      boolean first = true;
      String columnNameDelimiter = getColumnNameDelimiter(cols);

      for(FieldSchema col : cols) {
         if (!first) {
            colNameBuf.append(columnNameDelimiter);
            colTypeBuf.append(":");
            colComment.append('\u0000');
         }

         colNameBuf.append(col.getName());
         colTypeBuf.append(col.getType());
         colComment.append(null != col.getComment() ? col.getComment() : "");
         first = false;
      }

      schema.setProperty("columns", colNameBuf.toString());
      schema.setProperty("column.name.delimiter", columnNameDelimiter);
      String colTypes = colTypeBuf.toString();
      schema.setProperty("columns.types", colTypes);
      schema.setProperty("columns.comments", colComment.toString());
      return schema;
   }

   public static Properties getSchemaWithoutCols(StorageDescriptor sd, StorageDescriptor tblsd, Map parameters, String databaseName, String tableName, List partitionKeys) {
      Properties schema = new Properties();
      String inputFormat = sd.getInputFormat();
      if (inputFormat == null || inputFormat.length() == 0) {
         inputFormat = SequenceFileInputFormat.class.getName();
      }

      schema.setProperty("file.inputformat", inputFormat);
      String outputFormat = sd.getOutputFormat();
      if (outputFormat == null || outputFormat.length() == 0) {
         outputFormat = SequenceFileOutputFormat.class.getName();
      }

      schema.setProperty("file.outputformat", outputFormat);
      schema.setProperty("name", databaseName + "." + tableName);
      if (sd.getLocation() != null) {
         schema.setProperty("location", sd.getLocation());
      }

      schema.setProperty("bucket_count", Integer.toString(sd.getNumBuckets()));
      if (sd.getBucketCols() != null && sd.getBucketCols().size() > 0) {
         schema.setProperty("bucket_field_name", (String)sd.getBucketCols().get(0));
      }

      if (sd.getSerdeInfo() != null) {
         for(Map.Entry param : sd.getSerdeInfo().getParameters().entrySet()) {
            schema.put(param.getKey(), param.getValue() != null ? (String)param.getValue() : "");
         }

         if (sd.getSerdeInfo().getSerializationLib() != null) {
            schema.setProperty("serialization.lib", sd.getSerdeInfo().getSerializationLib());
         }
      }

      if (sd.getCols() != null) {
         schema.setProperty("serialization.ddl", getDDLFromFieldSchema(tableName, sd.getCols()));
      }

      String partString = "";
      String partStringSep = "";
      String partTypesString = "";
      String partTypesStringSep = "";

      for(FieldSchema partKey : partitionKeys) {
         partString = partString.concat(partStringSep);
         partString = partString.concat(partKey.getName());
         partTypesString = partTypesString.concat(partTypesStringSep);
         partTypesString = partTypesString.concat(partKey.getType());
         if (partStringSep.length() == 0) {
            partStringSep = "/";
            partTypesStringSep = ":";
         }
      }

      if (partString.length() > 0) {
         schema.setProperty("partition_columns", partString);
         schema.setProperty("partition_columns.types", partTypesString);
      }

      if (parameters != null) {
         for(Map.Entry e : parameters.entrySet()) {
            if (e.getValue() != null) {
               schema.setProperty((String)e.getKey(), (String)e.getValue());
            }
         }
      }

      return schema;
   }

   public static Properties getSchema(StorageDescriptor sd, StorageDescriptor tblsd, Map parameters, String databaseName, String tableName, List partitionKeys) {
      return addCols(getSchemaWithoutCols(sd, tblsd, parameters, databaseName, tableName, partitionKeys), tblsd.getCols());
   }

   public static String getColumnNameDelimiter(List fieldSchemas) {
      for(int i = 0; i < fieldSchemas.size(); ++i) {
         if (((FieldSchema)fieldSchemas.get(i)).getName().contains(",")) {
            return String.valueOf('\u0000');
         }
      }

      return String.valueOf(',');
   }

   public static String getColumnNamesFromFieldSchema(List fieldSchemas) {
      String delimiter = getColumnNameDelimiter(fieldSchemas);
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < fieldSchemas.size(); ++i) {
         if (i > 0) {
            sb.append(delimiter);
         }

         sb.append(((FieldSchema)fieldSchemas.get(i)).getName());
      }

      return sb.toString();
   }

   public static String getColumnTypesFromFieldSchema(List fieldSchemas) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < fieldSchemas.size(); ++i) {
         if (i > 0) {
            sb.append(",");
         }

         sb.append(((FieldSchema)fieldSchemas.get(i)).getType());
      }

      return sb.toString();
   }

   public static String getColumnCommentsFromFieldSchema(List fieldSchemas) {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < fieldSchemas.size(); ++i) {
         if (i > 0) {
            sb.append('\u0000');
         }

         sb.append(((FieldSchema)fieldSchemas.get(i)).getComment());
      }

      return sb.toString();
   }

   public static void makeDir(Path path, HiveConf hiveConf) throws MetaException {
      try {
         FileSystem fs = path.getFileSystem(hiveConf);
         if (!fs.exists(path)) {
            fs.mkdirs(path);
         }

      } catch (IOException var4) {
         throw new MetaException("Unable to : " + path);
      }
   }

   public static int startMetaStore() throws Exception {
      return startMetaStore(ShimLoader.getHadoopThriftAuthBridge(), (HiveConf)null);
   }

   public static int startMetaStore(HadoopThriftAuthBridge bridge, HiveConf conf) throws Exception {
      int port = findFreePort();
      startMetaStore(port, bridge, conf);
      return port;
   }

   public static int startMetaStoreWithRetry(HadoopThriftAuthBridge bridge) throws Exception {
      return startMetaStoreWithRetry(bridge, (HiveConf)null);
   }

   public static int startMetaStoreWithRetry(HiveConf conf) throws Exception {
      return startMetaStoreWithRetry(ShimLoader.getHadoopThriftAuthBridge(), conf);
   }

   public static int startMetaStoreWithRetry() throws Exception {
      return startMetaStoreWithRetry(ShimLoader.getHadoopThriftAuthBridge(), (HiveConf)null);
   }

   public static int startMetaStoreWithRetry(HadoopThriftAuthBridge bridge, HiveConf conf) throws Exception {
      Exception metaStoreException = null;
      int metaStorePort = 0;

      for(int tryCount = 0; tryCount < 10; ++tryCount) {
         try {
            metaStorePort = findFreePort();
            startMetaStore(metaStorePort, bridge, conf);
            return metaStorePort;
         } catch (ConnectException ce) {
            metaStoreException = ce;
         }
      }

      throw metaStoreException;
   }

   public static int startMetaStore(HiveConf conf) throws Exception {
      return startMetaStore(ShimLoader.getHadoopThriftAuthBridge(), conf);
   }

   public static void startMetaStore(int port, HadoopThriftAuthBridge bridge) throws Exception {
      startMetaStore(port, bridge, (HiveConf)null);
   }

   public static void startMetaStore(final int port, final HadoopThriftAuthBridge bridge, final HiveConf hiveConf) throws Exception {
      if (hiveConf == null) {
         hiveConf = new HiveConf(HiveMetaStore.HMSHandler.class);
      }

      Thread thread = new Thread(new Runnable() {
         public void run() {
            try {
               HiveMetaStore.startMetaStore(port, bridge, hiveConf);
            } catch (Throwable e) {
               MetaStoreUtils.LOG.error("Metastore Thrift Server threw an exception...", e);
            }

         }
      });
      thread.setDaemon(true);
      thread.start();
      loopUntilHMSReady(port);
   }

   private static void loopUntilHMSReady(int port) throws Exception {
      int retries = 0;
      Exception exc = null;

      while(true) {
         try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(port), 5000);
            socket.close();
            return;
         } catch (Exception e) {
            if (retries++ > 60) {
               LOG.error("Unable to connect to metastore server: " + e.getMessage());
               LOG.info("Printing all thread stack traces for debugging before throwing exception.");
               LOG.info(getAllThreadStacksAsString());
               throw e;
            }

            Thread.sleep(1000L);
         }
      }
   }

   private static String getAllThreadStacksAsString() {
      Map<Thread, StackTraceElement[]> threadStacks = Thread.getAllStackTraces();
      StringBuilder sb = new StringBuilder();

      for(Map.Entry entry : threadStacks.entrySet()) {
         Thread t = (Thread)entry.getKey();
         sb.append(System.lineSeparator());
         sb.append("Name: ").append(t.getName()).append(" State: " + t.getState());
         addStackString((StackTraceElement[])entry.getValue(), sb);
      }

      return sb.toString();
   }

   private static void addStackString(StackTraceElement[] stackElems, StringBuilder sb) {
      sb.append(System.lineSeparator());

      for(StackTraceElement stackElem : stackElems) {
         sb.append(stackElem).append(System.lineSeparator());
      }

   }

   public static int findFreePort() throws IOException {
      ServerSocket socket = new ServerSocket(0);
      int port = socket.getLocalPort();
      socket.close();
      return port;
   }

   public static int findFreePortExcepting(int portToExclude) throws IOException {
      ServerSocket socket1 = null;
      ServerSocket socket2 = null;

      int var3;
      try {
         socket1 = new ServerSocket(0);
         socket2 = new ServerSocket(0);
         if (socket1.getLocalPort() == portToExclude) {
            var3 = socket2.getLocalPort();
            return var3;
         }

         var3 = socket1.getLocalPort();
      } finally {
         if (socket1 != null) {
            socket1.close();
         }

         if (socket2 != null) {
            socket2.close();
         }

      }

      return var3;
   }

   static void logAndThrowMetaException(Exception e) throws MetaException {
      String exInfo = "Got exception: " + e.getClass().getName() + " " + e.getMessage();
      LOG.error(exInfo, e);
      LOG.error("Converting exception to MetaException");
      throw new MetaException(exInfo);
   }

   public static List getFieldsFromDeserializer(String tableName, Deserializer deserializer) throws SerDeException, MetaException {
      ObjectInspector oi = deserializer.getObjectInspector();
      String[] names = tableName.split("\\.");
      String last_name = names[names.length - 1];

      for(int i = 1; i < names.length; ++i) {
         if (oi instanceof StructObjectInspector) {
            StructObjectInspector soi = (StructObjectInspector)oi;
            StructField sf = soi.getStructFieldRef(names[i]);
            if (sf == null) {
               throw new MetaException("Invalid Field " + names[i]);
            }

            oi = sf.getFieldObjectInspector();
         } else if (oi instanceof ListObjectInspector && names[i].equalsIgnoreCase("$elem$")) {
            ListObjectInspector loi = (ListObjectInspector)oi;
            oi = loi.getListElementObjectInspector();
         } else if (oi instanceof MapObjectInspector && names[i].equalsIgnoreCase("$key$")) {
            MapObjectInspector moi = (MapObjectInspector)oi;
            oi = moi.getMapKeyObjectInspector();
         } else {
            if (!(oi instanceof MapObjectInspector) || !names[i].equalsIgnoreCase("$value$")) {
               throw new MetaException("Unknown type for " + names[i]);
            }

            MapObjectInspector moi = (MapObjectInspector)oi;
            oi = moi.getMapValueObjectInspector();
         }
      }

      ArrayList<FieldSchema> str_fields = new ArrayList();
      if (oi.getCategory() != Category.STRUCT) {
         str_fields.add(new FieldSchema(last_name, oi.getTypeName(), "from deserializer"));
      } else {
         List<? extends StructField> fields = ((StructObjectInspector)oi).getAllStructFieldRefs();

         for(int i = 0; i < fields.size(); ++i) {
            StructField structField = (StructField)fields.get(i);
            String fieldName = structField.getFieldName();
            String fieldTypeName = structField.getFieldObjectInspector().getTypeName();
            String fieldComment = determineFieldComment(structField.getFieldComment());
            str_fields.add(new FieldSchema(fieldName, fieldTypeName, fieldComment));
         }
      }

      return str_fields;
   }

   private static String determineFieldComment(String comment) {
      return comment == null ? "from deserializer" : comment;
   }

   public static FieldSchema getFieldSchemaFromTypeInfo(String fieldName, TypeInfo typeInfo) {
      return new FieldSchema(fieldName, typeInfo.getTypeName(), "generated by TypeInfoUtils.getFieldSchemaFromTypeInfo");
   }

   public static boolean isExternalTable(Table table) {
      if (table == null) {
         return false;
      } else {
         Map<String, String> params = table.getParameters();
         return params == null ? false : "TRUE".equalsIgnoreCase((String)params.get("EXTERNAL"));
      }
   }

   public static boolean isImmutableTable(Table table) {
      if (table == null) {
         return false;
      } else {
         Map<String, String> params = table.getParameters();
         return params == null ? false : "TRUE".equalsIgnoreCase((String)params.get("immutable"));
      }
   }

   public static boolean isArchived(Partition part) {
      Map<String, String> params = part.getParameters();
      return "true".equalsIgnoreCase((String)params.get("is_archived"));
   }

   public static Path getOriginalLocation(Partition part) {
      Map<String, String> params = part.getParameters();

      assert isArchived(part);

      String originalLocation = (String)params.get("original_location");

      assert originalLocation != null;

      return new Path(originalLocation);
   }

   public static boolean isNonNativeTable(Table table) {
      if (table != null && table.getParameters() != null) {
         return table.getParameters().get("storage_handler") != null;
      } else {
         return false;
      }
   }

   public static boolean isDirEmpty(FileSystem fs, Path path) throws IOException {
      if (fs.exists(path)) {
         FileStatus[] status = fs.globStatus(new Path(path, "*"), hiddenFileFilter);
         if (status.length > 0) {
            return false;
         }
      }

      return true;
   }

   public static boolean pvalMatches(List partial, List full) {
      if (partial.size() > full.size()) {
         return false;
      } else {
         Iterator<String> p = partial.iterator();
         Iterator<String> f = full.iterator();

         while(p.hasNext()) {
            String pval = (String)p.next();
            String fval = (String)f.next();
            if (pval.length() != 0 && !pval.equals(fval)) {
               return false;
            }
         }

         return true;
      }
   }

   public static String getIndexTableName(String dbName, String baseTblName, String indexName) {
      return dbName + "__" + baseTblName + "_" + indexName + "__";
   }

   public static boolean isIndexTable(Table table) {
      return table == null ? false : TableType.INDEX_TABLE.toString().equals(table.getTableType());
   }

   public static boolean isMaterializedViewTable(Table table) {
      return table == null ? false : TableType.MATERIALIZED_VIEW.toString().equals(table.getTableType());
   }

   public static String makeFilterStringFromMap(Map m) {
      StringBuilder filter = new StringBuilder();

      for(Map.Entry e : m.entrySet()) {
         String col = (String)e.getKey();
         String val = (String)e.getValue();
         if (filter.length() == 0) {
            filter.append(col + "=\"" + val + "\"");
         } else {
            filter.append(" and " + col + "=\"" + val + "\"");
         }
      }

      return filter.toString();
   }

   public static boolean isView(Table table) {
      return table == null ? false : TableType.VIRTUAL_VIEW.toString().equals(table.getTableType());
   }

   static List getMetaStoreListeners(Class clazz, HiveConf conf, String listenerImplList) throws MetaException {
      List<T> listeners = new ArrayList();
      listenerImplList = listenerImplList.trim();
      if (listenerImplList.equals("")) {
         return listeners;
      } else {
         String[] listenerImpls = listenerImplList.split(",");

         for(String listenerImpl : listenerImpls) {
            try {
               T listener = (T)Class.forName(listenerImpl.trim(), true, JavaUtils.getClassLoader()).getConstructor(Configuration.class).newInstance(conf);
               listeners.add(listener);
            } catch (InvocationTargetException ie) {
               throw new MetaException("Failed to instantiate listener named: " + listenerImpl + ", reason: " + ie.getCause());
            } catch (Exception e) {
               throw new MetaException("Failed to instantiate listener named: " + listenerImpl + ", reason: " + e);
            }
         }

         return listeners;
      }
   }

   public static Class getClass(String rawStoreClassName) throws MetaException {
      try {
         return Class.forName(rawStoreClassName, true, JavaUtils.getClassLoader());
      } catch (ClassNotFoundException var2) {
         throw new MetaException(rawStoreClassName + " class not found");
      }
   }

   public static Object newInstance(Class theClass, Class[] parameterTypes, Object[] initargs) {
      if (parameterTypes.length != initargs.length) {
         throw new IllegalArgumentException("Number of constructor parameter types doesn't match number of arguments");
      } else {
         for(int i = 0; i < parameterTypes.length; ++i) {
            Class<?> clazz = parameterTypes[i];
            if (initargs[i] != null && !clazz.isInstance(initargs[i])) {
               throw new IllegalArgumentException("Object : " + initargs[i] + " is not an instance of " + clazz);
            }
         }

         try {
            Constructor<T> meth = theClass.getDeclaredConstructor(parameterTypes);
            meth.setAccessible(true);
            return meth.newInstance(initargs);
         } catch (Exception e) {
            throw new RuntimeException("Unable to instantiate " + theClass.getName(), e);
         }
      }
   }

   public static void validatePartitionNameCharacters(List partVals, Pattern partitionValidationPattern) throws MetaException {
      String invalidPartitionVal = HiveStringUtils.getPartitionValWithInvalidCharacter(partVals, partitionValidationPattern);
      if (invalidPartitionVal != null) {
         throw new MetaException("Partition value '" + invalidPartitionVal + "' contains a character not matched by whitelist pattern '" + partitionValidationPattern.toString() + "'.  (configure with " + ConfVars.METASTORE_PARTITION_NAME_WHITELIST_PATTERN.varname + ")");
      }
   }

   public static boolean partitionNameHasValidCharacters(List partVals, Pattern partitionValidationPattern) {
      return HiveStringUtils.getPartitionValWithInvalidCharacter(partVals, partitionValidationPattern) == null;
   }

   public static boolean compareFieldColumns(List schema1, List schema2) {
      if (schema1.size() != schema2.size()) {
         return false;
      } else {
         for(int i = 0; i < schema1.size(); ++i) {
            FieldSchema f1 = (FieldSchema)schema1.get(i);
            FieldSchema f2 = (FieldSchema)schema2.get(i);
            if (f1.getName() == null) {
               if (f2.getName() != null) {
                  return false;
               }
            } else if (!f1.getName().equals(f2.getName())) {
               return false;
            }

            if (f1.getType() == null) {
               if (f2.getType() != null) {
                  return false;
               }
            } else if (!f1.getType().equals(f2.getType())) {
               return false;
            }
         }

         return true;
      }
   }

   public static Map getMetaStoreSaslProperties(HiveConf conf) {
      return ShimLoader.getHadoopThriftAuthBridge().getHadoopSaslProperties(conf);
   }

   public static int getArchivingLevel(Partition part) throws MetaException {
      if (!isArchived(part)) {
         throw new MetaException("Getting level of unarchived partition");
      } else {
         String lv = (String)part.getParameters().get(ARCHIVING_LEVEL);
         return lv != null ? Integer.parseInt(lv) : part.getValues().size();
      }
   }

   public static String[] getQualifiedName(String defaultDbName, String tableName) {
      String[] names = tableName.split("\\.");
      return names.length == 1 ? new String[]{defaultDbName, tableName} : new String[]{names[0], names[1]};
   }

   public static Map trimMapNulls(Map dnMap, boolean retrieveMapNullsAsEmptyStrings) {
      if (dnMap == null) {
         return null;
      } else {
         return retrieveMapNullsAsEmptyStrings ? Maps.newLinkedHashMap(Maps.transformValues(dnMap, transFormNullsToEmptyString)) : Maps.newLinkedHashMap(Maps.filterValues(dnMap, Predicates.notNull()));
      }
   }

   private static URL urlFromPathString(String onestr) {
      URL oneurl = null;

      try {
         if (StringUtils.indexOf(onestr, "file:/") == 0) {
            oneurl = new URL(onestr);
         } else {
            oneurl = (new File(onestr)).toURL();
         }
      } catch (Exception var3) {
         LOG.error("Bad URL " + onestr + ", ignoring path");
      }

      return oneurl;
   }

   private static List getCurrentClassPaths(ClassLoader parentLoader) {
      return (List)(parentLoader instanceof URLClassLoader ? Lists.newArrayList(((URLClassLoader)parentLoader).getURLs()) : Collections.emptyList());
   }

   public static ClassLoader addToClassPath(ClassLoader cloader, String[] newPaths) throws Exception {
      List<URL> curPath = getCurrentClassPaths(cloader);
      ArrayList<URL> newPath = new ArrayList();

      for(URL onePath : curPath) {
         newPath.add(onePath);
      }

      curPath = newPath;

      for(String onestr : newPaths) {
         URL oneurl = urlFromPathString(onestr);
         if (oneurl != null && !curPath.contains(oneurl)) {
            curPath.add(oneurl);
         }
      }

      return new URLClassLoader((URL[])curPath.toArray(new URL[0]), cloader);
   }

   public static String encodeTableName(String name) {
      String ret = "";

      for(char ch : name.toCharArray()) {
         if (!Character.isLetterOrDigit(ch) && ch != '_') {
            ret = ret + "-" + ch + "-";
         } else {
            ret = ret + ch;
         }
      }

      return ret;
   }

   public static void mergeColStats(ColumnStatistics csNew, ColumnStatistics csOld) throws InvalidObjectException {
      List<ColumnStatisticsObj> list = new ArrayList();
      if (csNew.getStatsObj().size() != csOld.getStatsObjSize()) {
         LOG.debug("New ColumnStats size is " + csNew.getStatsObj().size() + ". But old ColumnStats size is " + csOld.getStatsObjSize());
      }

      Map<String, ColumnStatisticsObj> map = new HashMap();

      for(ColumnStatisticsObj obj : csOld.getStatsObj()) {
         map.put(obj.getColName(), obj);
      }

      for(int index = 0; index < csNew.getStatsObj().size(); ++index) {
         ColumnStatisticsObj statsObjNew = (ColumnStatisticsObj)csNew.getStatsObj().get(index);
         ColumnStatisticsObj statsObjOld = (ColumnStatisticsObj)map.get(statsObjNew.getColName());
         if (statsObjOld != null) {
            ColumnStatsMerger merger = ColumnStatsMergerFactory.getColumnStatsMerger(statsObjNew, statsObjOld);
            merger.merge(statsObjNew, statsObjOld);
         }

         list.add(statsObjNew);
      }

      csNew.setStatsObj(list);
   }

   static {
      typeToThriftTypeMap.put("boolean", "bool");
      typeToThriftTypeMap.put("tinyint", "byte");
      typeToThriftTypeMap.put("smallint", "i16");
      typeToThriftTypeMap.put("int", "i32");
      typeToThriftTypeMap.put("bigint", "i64");
      typeToThriftTypeMap.put("double", "double");
      typeToThriftTypeMap.put("float", "float");
      typeToThriftTypeMap.put("array", "list");
      typeToThriftTypeMap.put("map", "map");
      typeToThriftTypeMap.put("string", "string");
      typeToThriftTypeMap.put("binary", "binary");
      typeToThriftTypeMap.put("date", "date");
      typeToThriftTypeMap.put("datetime", "datetime");
      typeToThriftTypeMap.put("timestamp", "timestamp");
      typeToThriftTypeMap.put("decimal", "decimal");
      typeToThriftTypeMap.put("interval_year_month", "interval_year_month");
      typeToThriftTypeMap.put("interval_day_time", "interval_day_time");
      hiveThriftTypeMap = new HashSet();
      hiveThriftTypeMap.addAll(serdeConstants.PrimitiveTypes);
      hiveThriftTypeMap.addAll(serdeConstants.CollectionTypes);
      hiveThriftTypeMap.add("uniontype");
      hiveThriftTypeMap.add("struct");
      hiddenFileFilter = new PathFilter() {
         public boolean accept(Path p) {
            String name = p.getName();
            return !name.startsWith("_") && !name.startsWith(".");
         }
      };
      ARCHIVING_LEVEL = "archiving_level";
      transFormNullsToEmptyString = new Function() {
         public String apply(@Nullable String string) {
            return string == null ? "" : string;
         }
      };
   }
}
