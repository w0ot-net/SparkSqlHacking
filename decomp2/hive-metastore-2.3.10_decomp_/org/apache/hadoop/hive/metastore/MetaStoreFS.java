package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.MetaException;

public interface MetaStoreFS {
   boolean deleteDir(FileSystem var1, Path var2, boolean var3, boolean var4, Configuration var5) throws MetaException;
}
