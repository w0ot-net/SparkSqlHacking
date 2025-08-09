package org.apache.hadoop.hive.metastore;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.FileMetadataExprType;
import org.apache.hadoop.hive.metastore.hbase.MetadataStore;

public abstract class FileMetadataHandler {
   protected static final Log LOG = LogFactory.getLog(FileMetadataHandler.class);
   private Configuration conf;
   private PartitionExpressionProxy expressionProxy;
   private FileFormatProxy fileFormatProxy;
   private MetadataStore store;

   public abstract void getFileMetadataByExpr(List var1, byte[] var2, ByteBuffer[] var3, ByteBuffer[] var4, boolean[] var5) throws IOException;

   protected abstract FileMetadataExprType getType();

   protected PartitionExpressionProxy getExpressionProxy() {
      return this.expressionProxy;
   }

   protected FileFormatProxy getFileFormatProxy() {
      return this.fileFormatProxy;
   }

   protected MetadataStore getStore() {
      return this.store;
   }

   public void configure(Configuration conf, PartitionExpressionProxy expressionProxy, MetadataStore store) {
      this.conf = conf;
      this.expressionProxy = expressionProxy;
      this.store = store;
      this.fileFormatProxy = expressionProxy.getFileFormatProxy(this.getType());
   }

   public void cacheFileMetadata(long fileId, FileSystem fs, Path path) throws IOException, InterruptedException {
      ByteBuffer[] cols = this.fileFormatProxy.getAddedColumnsToCache();
      ByteBuffer[] vals = cols == null ? null : new ByteBuffer[cols.length];
      ByteBuffer metadata = this.fileFormatProxy.getMetadataToCache(fs, path, vals);
      LOG.info("Caching file metadata for " + path + ", size " + metadata.remaining());
      this.store.storeFileMetadata(fileId, metadata, cols, vals);
   }

   public ByteBuffer[] createAddedCols() {
      return this.fileFormatProxy.getAddedColumnsToCache();
   }

   public ByteBuffer[][] createAddedColVals(List metadata) {
      return this.fileFormatProxy.getAddedValuesToCache(metadata);
   }
}
