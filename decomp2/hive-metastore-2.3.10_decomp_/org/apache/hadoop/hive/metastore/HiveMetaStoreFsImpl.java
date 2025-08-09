package org.apache.hadoop.hive.metastore;

import java.io.FileNotFoundException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveMetaStoreFsImpl implements MetaStoreFS {
   public static final Logger LOG = LoggerFactory.getLogger("hive.metastore.hivemetastoreFsimpl");

   public boolean deleteDir(FileSystem fs, Path f, boolean recursive, boolean ifPurge, Configuration conf) throws MetaException {
      try {
         FileUtils.moveToTrash(fs, f, conf, ifPurge);
         if (fs.exists(f)) {
            throw new MetaException("Unable to delete directory: " + f);
         } else {
            return true;
         }
      } catch (FileNotFoundException var7) {
         return true;
      } catch (Exception e) {
         MetaStoreUtils.logAndThrowMetaException(e);
         return false;
      }
   }
}
