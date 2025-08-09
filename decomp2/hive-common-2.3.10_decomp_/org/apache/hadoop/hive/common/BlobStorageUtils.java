package org.apache.hadoop.hive.common;

import java.util.Collection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;

public class BlobStorageUtils {
   private static final boolean DISABLE_BLOBSTORAGE_AS_SCRATCHDIR = false;

   public static boolean isBlobStoragePath(Configuration conf, Path path) {
      return path != null && isBlobStorageScheme(conf, path.toUri().getScheme());
   }

   public static boolean isBlobStorageFileSystem(Configuration conf, FileSystem fs) {
      return fs != null && isBlobStorageScheme(conf, fs.getScheme());
   }

   public static boolean isBlobStorageScheme(Configuration conf, String scheme) {
      Collection<String> supportedBlobStoreSchemes = conf.getStringCollection(HiveConf.ConfVars.HIVE_BLOBSTORE_SUPPORTED_SCHEMES.varname);
      return supportedBlobStoreSchemes.contains(scheme);
   }

   public static boolean isBlobStorageAsScratchDir(Configuration conf) {
      return conf.getBoolean(HiveConf.ConfVars.HIVE_BLOBSTORE_USE_BLOBSTORE_AS_SCRATCHDIR.varname, false);
   }

   public static boolean areOptimizationsEnabled(Configuration conf) {
      return conf.getBoolean(HiveConf.ConfVars.HIVE_BLOBSTORE_OPTIMIZATIONS_ENABLED.varname, HiveConf.ConfVars.HIVE_BLOBSTORE_OPTIMIZATIONS_ENABLED.defaultBoolVal);
   }
}
