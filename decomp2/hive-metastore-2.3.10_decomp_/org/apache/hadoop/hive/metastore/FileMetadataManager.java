package org.apache.hadoop.hive.metastore;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.io.HdfsUtils;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.hive.shims.ShimLoader;

public class FileMetadataManager {
   private static final Log LOG = LogFactory.getLog(FileMetadataManager.class);
   private static final HadoopShims SHIMS = ShimLoader.getHadoopShims();
   private final HiveMetaStore.ThreadLocalRawStore tlms;
   private final ExecutorService threadPool;
   private final HiveConf conf;

   public FileMetadataManager(HiveMetaStore.ThreadLocalRawStore tlms, HiveConf conf) {
      this.tlms = tlms;
      this.conf = conf;
      int numThreads = HiveConf.getIntVar(conf, ConfVars.METASTORE_HBASE_FILE_METADATA_THREADS);
      this.threadPool = Executors.newFixedThreadPool(numThreads, (new ThreadFactoryBuilder()).setNameFormat("File-Metadata-%d").setDaemon(true).build());
   }

   public void queueCacheMetadata(String location, FileMetadataExprType type) {
      this.threadPool.submit(new CacheUpdateRequest(type, location));
   }

   private void cacheMetadata(FileMetadataExprType type, String location) throws MetaException, IOException, InterruptedException {
      Path path = new Path(location);
      FileSystem fs = path.getFileSystem(this.conf);
      List<Path> files;
      if (!fs.isDirectory(path)) {
         files = Lists.newArrayList(new Path[]{path});
      } else {
         files = new ArrayList();
         RemoteIterator<LocatedFileStatus> iter = fs.listFiles(path, true);

         while(iter.hasNext()) {
            LocatedFileStatus lfs = (LocatedFileStatus)iter.next();
            if (!lfs.isDirectory()) {
               files.add(lfs.getPath());
            }
         }
      }

      for(Path file : files) {
         long fileId;
         try {
            fileId = SHIMS.getFileId(fs, Path.getPathWithoutSchemeAndAuthority(file).toString());
         } catch (UnsupportedOperationException var11) {
            LOG.error("Cannot cache file metadata for " + location + "; " + fs.getClass().getCanonicalName() + " does not support fileId");
            return;
         }

         LOG.info("Caching file metadata for " + file + " (file ID " + fileId + ")");
         file = HdfsUtils.getFileIdPath(fs, file, fileId);
         this.tlms.getMS().getFileMetadataHandler(type).cacheFileMetadata(fileId, fs, file);
      }

   }

   private final class CacheUpdateRequest implements Callable {
      FileMetadataExprType type;
      String location;

      public CacheUpdateRequest(FileMetadataExprType type, String location) {
         this.type = type;
         this.location = location;
      }

      public Void call() throws Exception {
         try {
            FileMetadataManager.this.cacheMetadata(this.type, this.location);
         } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
         } catch (Exception ex) {
            FileMetadataManager.LOG.error("Failed to cache file metadata in background for " + this.type + ", " + this.location, ex);
         }

         return null;
      }
   }
}
