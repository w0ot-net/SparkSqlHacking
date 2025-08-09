package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.parquet.Preconditions;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

class PrefetchIndexCache implements IndexCache {
   private final ParquetFileReader fileReader;
   private final Set columns;
   private final boolean freeCacheAfterGet;
   private Map columnIndexCache;
   private Map offsetIndexCache;
   private Map bloomIndexCache;

   PrefetchIndexCache(ParquetFileReader fileReader, Set columns, boolean freeCacheAfterGet) {
      this.fileReader = fileReader;
      this.columns = columns;
      this.freeCacheAfterGet = freeCacheAfterGet;
   }

   public void setBlockMetadata(BlockMetaData currentBlockMetadata) throws IOException {
      this.clean();
      this.columnIndexCache = this.readAllColumnIndexes(currentBlockMetadata);
      this.offsetIndexCache = this.readAllOffsetIndexes(currentBlockMetadata);
      this.bloomIndexCache = this.readAllBloomFilters(currentBlockMetadata);
   }

   public ColumnIndex getColumnIndex(ColumnChunkMetaData chunk) throws IOException {
      ColumnPath columnPath = chunk.getPath();
      if (this.columns.contains(columnPath)) {
         Preconditions.checkState(this.columnIndexCache.containsKey(columnPath), "Not found cached ColumnIndex for column: %s with cache strategy: %s", columnPath.toDotString(), IndexCache.CacheStrategy.PREFETCH_BLOCK);
      }

      return this.freeCacheAfterGet ? (ColumnIndex)this.columnIndexCache.remove(columnPath) : (ColumnIndex)this.columnIndexCache.get(columnPath);
   }

   public OffsetIndex getOffsetIndex(ColumnChunkMetaData chunk) throws IOException {
      ColumnPath columnPath = chunk.getPath();
      if (this.columns.contains(columnPath)) {
         Preconditions.checkState(this.offsetIndexCache.containsKey(columnPath), "Not found cached OffsetIndex for column: %s with cache strategy: %s", columnPath.toDotString(), IndexCache.CacheStrategy.PREFETCH_BLOCK);
      }

      return this.freeCacheAfterGet ? (OffsetIndex)this.offsetIndexCache.remove(columnPath) : (OffsetIndex)this.offsetIndexCache.get(columnPath);
   }

   public BloomFilter getBloomFilter(ColumnChunkMetaData chunk) throws IOException {
      ColumnPath columnPath = chunk.getPath();
      if (this.columns.contains(columnPath)) {
         Preconditions.checkState(this.bloomIndexCache.containsKey(columnPath), "Not found cached BloomFilter for column: %s with cache strategy: %s", columnPath.toDotString(), IndexCache.CacheStrategy.PREFETCH_BLOCK);
      }

      return this.freeCacheAfterGet ? (BloomFilter)this.bloomIndexCache.remove(columnPath) : (BloomFilter)this.bloomIndexCache.get(columnPath);
   }

   public void clean() {
      if (this.columnIndexCache != null) {
         this.columnIndexCache.clear();
         this.columnIndexCache = null;
      }

      if (this.offsetIndexCache != null) {
         this.offsetIndexCache.clear();
         this.offsetIndexCache = null;
      }

      if (this.bloomIndexCache != null) {
         this.bloomIndexCache.clear();
         this.bloomIndexCache = null;
      }

   }

   private Map readAllColumnIndexes(BlockMetaData blockMetaData) throws IOException {
      Map<ColumnPath, ColumnIndex> columnIndexMap = new HashMap(this.columns.size());

      for(ColumnChunkMetaData chunk : blockMetaData.getColumns()) {
         if (this.columns.contains(chunk.getPath())) {
            columnIndexMap.put(chunk.getPath(), this.fileReader.readColumnIndex(chunk));
         }
      }

      return columnIndexMap;
   }

   private Map readAllOffsetIndexes(BlockMetaData blockMetaData) throws IOException {
      Map<ColumnPath, OffsetIndex> offsetIndexMap = new HashMap(this.columns.size());

      for(ColumnChunkMetaData chunk : blockMetaData.getColumns()) {
         if (this.columns.contains(chunk.getPath())) {
            offsetIndexMap.put(chunk.getPath(), this.fileReader.readOffsetIndex(chunk));
         }
      }

      return offsetIndexMap;
   }

   private Map readAllBloomFilters(BlockMetaData blockMetaData) throws IOException {
      Map<ColumnPath, BloomFilter> bloomFilterMap = new HashMap(this.columns.size());

      for(ColumnChunkMetaData chunk : blockMetaData.getColumns()) {
         if (this.columns.contains(chunk.getPath())) {
            bloomFilterMap.put(chunk.getPath(), this.fileReader.readBloomFilter(chunk));
         }
      }

      return bloomFilterMap;
   }
}
