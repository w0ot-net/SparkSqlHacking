package org.apache.hadoop.hive.metastore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.common.HiveStatsUtils;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Warehouse {
   private Path whRoot;
   private final Configuration conf;
   private final String whRootString;
   public static final Logger LOG = LoggerFactory.getLogger("hive.metastore.warehouse");
   private MetaStoreFS fsHandler = null;
   private boolean storageAuthCheck = false;
   private ReplChangeManager cm = null;
   static final Pattern pat = Pattern.compile("([^/]+)=([^/]+)");
   private static final Pattern slash = Pattern.compile("/");

   public Warehouse(Configuration conf) throws MetaException {
      this.conf = conf;
      this.whRootString = HiveConf.getVar(conf, ConfVars.METASTOREWAREHOUSE);
      if (StringUtils.isBlank(this.whRootString)) {
         throw new MetaException(ConfVars.METASTOREWAREHOUSE.varname + " is not set in the config or blank");
      } else {
         this.fsHandler = this.getMetaStoreFsHandler(conf);
         this.cm = ReplChangeManager.getInstance((HiveConf)conf);
         this.storageAuthCheck = HiveConf.getBoolVar(conf, ConfVars.METASTORE_AUTHORIZATION_STORAGE_AUTH_CHECKS);
      }
   }

   private MetaStoreFS getMetaStoreFsHandler(Configuration conf) throws MetaException {
      String handlerClassStr = HiveConf.getVar(conf, ConfVars.HIVE_METASTORE_FS_HANDLER_CLS);

      try {
         Class<? extends MetaStoreFS> handlerClass = Class.forName(handlerClassStr, true, JavaUtils.getClassLoader());
         MetaStoreFS handler = (MetaStoreFS)ReflectionUtils.newInstance(handlerClass, conf);
         return handler;
      } catch (ClassNotFoundException e) {
         throw new MetaException("Error in loading MetaStoreFS handler." + e.getMessage());
      }
   }

   public static FileSystem getFs(Path f, Configuration conf) throws MetaException {
      try {
         return f.getFileSystem(conf);
      } catch (IOException e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return null;
      }
   }

   public FileSystem getFs(Path f) throws MetaException {
      return getFs(f, this.conf);
   }

   public static Path getDnsPath(Path path, Configuration conf) throws MetaException {
      FileSystem fs = getFs(path, conf);
      return new Path(fs.getUri().getScheme(), fs.getUri().getAuthority(), path.toUri().getPath());
   }

   public Path getDnsPath(Path path) throws MetaException {
      return getDnsPath(path, this.conf);
   }

   public Path getWhRoot() throws MetaException {
      if (this.whRoot != null) {
         return this.whRoot;
      } else {
         this.whRoot = this.getDnsPath(new Path(this.whRootString));
         return this.whRoot;
      }
   }

   public Path getDatabasePath(Database db) throws MetaException {
      return db.getName().equalsIgnoreCase("default") ? this.getWhRoot() : new Path(db.getLocationUri());
   }

   public Path getDefaultDatabasePath(String dbName) throws MetaException {
      return dbName.equalsIgnoreCase("default") ? this.getWhRoot() : new Path(this.getWhRoot(), dbName.toLowerCase() + ".db");
   }

   public Path getDefaultTablePath(Database db, String tableName) throws MetaException {
      return this.getDnsPath(new Path(this.getDatabasePath(db), MetaStoreUtils.encodeTableName(tableName.toLowerCase())));
   }

   public static String getQualifiedName(Table table) {
      return table.getDbName() + "." + table.getTableName();
   }

   public static String getQualifiedName(Partition partition) {
      return partition.getDbName() + "." + partition.getTableName() + partition.getValues();
   }

   public boolean mkdirs(Path f, boolean inheritPermCandidate) throws MetaException {
      boolean inheritPerms = HiveConf.getBoolVar(this.conf, ConfVars.HIVE_WAREHOUSE_SUBDIR_INHERIT_PERMS) && inheritPermCandidate;
      FileSystem fs = null;

      try {
         fs = this.getFs(f);
         return FileUtils.mkdir(fs, f, inheritPerms, this.conf);
      } catch (IOException e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return false;
      }
   }

   public boolean renameDir(Path sourcePath, Path destPath) throws MetaException {
      return this.renameDir(sourcePath, destPath, false);
   }

   public boolean renameDir(Path sourcePath, Path destPath, boolean inheritPerms) throws MetaException {
      try {
         FileSystem fs = this.getFs(sourcePath);
         return FileUtils.renameWithPerms(fs, sourcePath, destPath, inheritPerms, this.conf);
      } catch (Exception ex) {
         MetaStoreUtils.logAndThrowMetaException(ex);
         return false;
      }
   }

   public boolean deleteDir(Path f, boolean recursive) throws MetaException {
      return this.deleteDir(f, recursive, false);
   }

   public boolean deleteDir(Path f, boolean recursive, boolean ifPurge) throws MetaException {
      this.cm.recycle(f, ifPurge);
      FileSystem fs = this.getFs(f);
      return this.fsHandler.deleteDir(fs, f, recursive, ifPurge, this.conf);
   }

   public boolean isEmpty(Path path) throws IOException, MetaException {
      ContentSummary contents = this.getFs(path).getContentSummary(path);
      return contents != null && contents.getFileCount() == 0L && contents.getDirectoryCount() == 1L;
   }

   public boolean isWritable(Path path) throws IOException {
      if (!this.storageAuthCheck) {
         return true;
      } else if (path == null) {
         return false;
      } else {
         try {
            FileSystem fs = this.getFs(path);
            FileStatus stat = fs.getFileStatus(path);
            ShimLoader.getHadoopShims().checkFileAccess(fs, stat, FsAction.WRITE);
            return true;
         } catch (FileNotFoundException var5) {
            return true;
         } catch (Exception e) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Exception when checking if path (" + path + ")", e);
            }

            return false;
         }
      }
   }

   static String escapePathName(String path) {
      return FileUtils.escapePathName(path);
   }

   static String unescapePathName(String path) {
      return FileUtils.unescapePathName(path);
   }

   public static String makePartPath(Map spec) throws MetaException {
      return makePartName(spec, true);
   }

   public static String makePartName(Map spec, boolean addTrailingSeperator) throws MetaException {
      StringBuilder suffixBuf = new StringBuilder();
      int i = 0;

      for(Map.Entry e : spec.entrySet()) {
         if (e.getValue() == null || ((String)e.getValue()).length() == 0) {
            throw new MetaException("Partition spec is incorrect. " + spec);
         }

         if (i > 0) {
            suffixBuf.append("/");
         }

         suffixBuf.append(escapePathName((String)e.getKey()));
         suffixBuf.append('=');
         suffixBuf.append(escapePathName((String)e.getValue()));
         ++i;
      }

      if (addTrailingSeperator) {
         suffixBuf.append("/");
      }

      return suffixBuf.toString();
   }

   public static String makeDynamicPartName(Map spec) {
      StringBuilder suffixBuf = new StringBuilder();

      for(Map.Entry e : spec.entrySet()) {
         if (e.getValue() == null || ((String)e.getValue()).length() <= 0) {
            break;
         }

         suffixBuf.append(escapePathName((String)e.getKey()));
         suffixBuf.append('=');
         suffixBuf.append(escapePathName((String)e.getValue()));
         suffixBuf.append("/");
      }

      return suffixBuf.toString();
   }

   public static AbstractList makeValsFromName(String name, AbstractList result) throws MetaException {
      assert name != null;

      String[] parts = slash.split(name, 0);
      if (result == null) {
         result = new ArrayList(parts.length);

         for(int i = 0; i < parts.length; ++i) {
            result.add((Object)null);
         }
      } else if (parts.length != result.size()) {
         throw new MetaException("Expected " + result.size() + " components, got " + parts.length + " (" + name + ")");
      }

      for(int i = 0; i < parts.length; ++i) {
         int eq = parts[i].indexOf(61);
         if (eq <= 0) {
            throw new MetaException("Unexpected component " + parts[i]);
         }

         result.set(i, unescapePathName(parts[i].substring(eq + 1)));
      }

      return result;
   }

   public static LinkedHashMap makeSpecFromName(String name) throws MetaException {
      if (name != null && !name.isEmpty()) {
         LinkedHashMap<String, String> partSpec = new LinkedHashMap();
         makeSpecFromName(partSpec, new Path(name));
         return partSpec;
      } else {
         throw new MetaException("Partition name is invalid. " + name);
      }
   }

   public static void makeSpecFromName(Map partSpec, Path currPath) {
      List<String[]> kvs = new ArrayList();

      do {
         String component = currPath.getName();
         Matcher m = pat.matcher(component);
         if (m.matches()) {
            String k = unescapePathName(m.group(1));
            String v = unescapePathName(m.group(2));
            String[] kv = new String[]{k, v};
            kvs.add(kv);
         }

         currPath = currPath.getParent();
      } while(currPath != null && !currPath.getName().isEmpty());

      for(int i = kvs.size(); i > 0; --i) {
         partSpec.put(((String[])kvs.get(i - 1))[0], ((String[])kvs.get(i - 1))[1]);
      }

   }

   public static Map makeEscSpecFromName(String name) throws MetaException {
      if (name != null && !name.isEmpty()) {
         LinkedHashMap<String, String> partSpec = new LinkedHashMap();
         Path currPath = new Path(name);
         List<String[]> kvs = new ArrayList();

         do {
            String component = currPath.getName();
            Matcher m = pat.matcher(component);
            if (m.matches()) {
               String k = m.group(1);
               String v = m.group(2);
               String[] kv = new String[]{k, v};
               kvs.add(kv);
            }

            currPath = currPath.getParent();
         } while(currPath != null && !currPath.getName().isEmpty());

         for(int i = kvs.size(); i > 0; --i) {
            partSpec.put(((String[])kvs.get(i - 1))[0], ((String[])kvs.get(i - 1))[1]);
         }

         return partSpec;
      } else {
         throw new MetaException("Partition name is invalid. " + name);
      }
   }

   public Path getDefaultPartitionPath(Database db, String tableName, Map pm) throws MetaException {
      return this.getPartitionPath(this.getDefaultTablePath(db, tableName), pm);
   }

   public Path getPartitionPath(Path tblPath, Map pm) throws MetaException {
      return new Path(tblPath, makePartPath(pm));
   }

   public Path getPartitionPath(Database db, Table table, List vals) throws MetaException {
      List<FieldSchema> partKeys = table.getPartitionKeys();
      if (partKeys != null && partKeys.size() == vals.size()) {
         Map<String, String> pm = new LinkedHashMap(vals.size());
         int i = 0;

         for(FieldSchema key : partKeys) {
            pm.put(key.getName(), vals.get(i));
            ++i;
         }

         return table.getSd().getLocation() != null ? this.getPartitionPath(this.getDnsPath(new Path(table.getSd().getLocation())), pm) : this.getDefaultPartitionPath(db, table.getTableName(), pm);
      } else {
         throw new MetaException("Invalid number of partition keys found for " + table.getTableName());
      }
   }

   public boolean isDir(Path f) throws MetaException {
      FileSystem fs = null;

      try {
         fs = this.getFs(f);
         FileStatus fstatus = fs.getFileStatus(f);
         if (!fstatus.isDir()) {
            return false;
         }
      } catch (FileNotFoundException var4) {
         return false;
      } catch (IOException e) {
         MetaStoreUtils.logAndThrowMetaException(e);
      }

      return true;
   }

   public static String makePartName(List partCols, List vals) throws MetaException {
      return makePartName(partCols, vals, (String)null);
   }

   public FileStatus[] getFileStatusesForSD(StorageDescriptor desc) throws MetaException {
      return this.getFileStatusesForLocation(desc.getLocation());
   }

   public FileStatus[] getFileStatusesForLocation(String location) throws MetaException {
      try {
         Path path = new Path(location);
         FileSystem fileSys = path.getFileSystem(this.conf);
         return HiveStatsUtils.getFileStatusRecurse(path, -1, fileSys);
      } catch (IOException ioe) {
         MetaStoreUtils.logAndThrowMetaException(ioe);
         return null;
      }
   }

   public FileStatus[] getFileStatusesForUnpartitionedTable(Database db, Table table) throws MetaException {
      Path tablePath = this.getDnsPath(new Path(table.getSd().getLocation()));

      try {
         FileSystem fileSys = tablePath.getFileSystem(this.conf);
         return HiveStatsUtils.getFileStatusRecurse(tablePath, -1, fileSys);
      } catch (IOException ioe) {
         MetaStoreUtils.logAndThrowMetaException(ioe);
         return null;
      }
   }

   public static String makePartName(List partCols, List vals, String defaultStr) throws MetaException {
      if (partCols.size() == vals.size() && partCols.size() != 0) {
         List<String> colNames = new ArrayList();

         for(FieldSchema col : partCols) {
            colNames.add(col.getName());
         }

         return FileUtils.makePartName(colNames, vals, defaultStr);
      } else {
         String errorStr = "Invalid partition key & values; keys [";

         for(FieldSchema fs : partCols) {
            errorStr = errorStr + fs.getName() + ", ";
         }

         errorStr = errorStr + "], values [";

         for(String val : vals) {
            errorStr = errorStr + val + ", ";
         }

         throw new MetaException(errorStr + "]");
      }
   }

   public static List getPartValuesFromPartName(String partName) throws MetaException {
      LinkedHashMap<String, String> partSpec = makeSpecFromName(partName);
      List<String> values = new ArrayList();
      values.addAll(partSpec.values());
      return values;
   }

   public static Map makeSpecFromValues(List partCols, List values) {
      Map<String, String> spec = new LinkedHashMap();

      for(int i = 0; i < values.size(); ++i) {
         spec.put(((FieldSchema)partCols.get(i)).getName(), values.get(i));
      }

      return spec;
   }
}
