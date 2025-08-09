package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Set;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

public interface IndexCache {
   static IndexCache create(ParquetFileReader fileReader, Set columns, CacheStrategy cacheStrategy, boolean freeCacheAfterGet) {
      if (cacheStrategy == IndexCache.CacheStrategy.NONE) {
         return new NoneIndexCache(fileReader);
      } else if (cacheStrategy == IndexCache.CacheStrategy.PREFETCH_BLOCK) {
         return new PrefetchIndexCache(fileReader, columns, freeCacheAfterGet);
      } else {
         throw new UnsupportedOperationException("Unknown cache strategy: " + cacheStrategy);
      }
   }

   void setBlockMetadata(BlockMetaData var1) throws IOException;

   ColumnIndex getColumnIndex(ColumnChunkMetaData var1) throws IOException;

   OffsetIndex getOffsetIndex(ColumnChunkMetaData var1) throws IOException;

   BloomFilter getBloomFilter(ColumnChunkMetaData var1) throws IOException;

   void clean();

   public static enum CacheStrategy {
      NONE,
      PREFETCH_BLOCK;
   }
}
