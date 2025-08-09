package org.apache.parquet.hadoop;

import java.io.IOException;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

class NoneIndexCache implements IndexCache {
   private final ParquetFileReader fileReader;

   NoneIndexCache(ParquetFileReader fileReader) {
      this.fileReader = fileReader;
   }

   public void setBlockMetadata(BlockMetaData currentBlockMetadata) throws IOException {
   }

   public ColumnIndex getColumnIndex(ColumnChunkMetaData chunk) throws IOException {
      return this.fileReader.readColumnIndex(chunk);
   }

   public OffsetIndex getOffsetIndex(ColumnChunkMetaData chunk) throws IOException {
      return this.fileReader.readOffsetIndex(chunk);
   }

   public BloomFilter getBloomFilter(ColumnChunkMetaData chunk) throws IOException {
      return this.fileReader.readBloomFilter(chunk);
   }

   public void clean() {
   }
}
