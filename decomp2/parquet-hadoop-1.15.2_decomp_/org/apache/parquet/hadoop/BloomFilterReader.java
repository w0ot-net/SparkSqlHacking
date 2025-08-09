package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomFilterReader {
   private final ParquetFileReader reader;
   private final Map columns;
   private final Map cache = new HashMap();
   private Logger logger = LoggerFactory.getLogger(BloomFilterReader.class);

   public BloomFilterReader(ParquetFileReader fileReader, BlockMetaData block) {
      this.reader = fileReader;
      this.columns = new HashMap();

      for(ColumnChunkMetaData column : block.getColumns()) {
         this.columns.put(column.getPath(), column);
      }

   }

   public BloomFilter readBloomFilter(ColumnChunkMetaData meta) {
      if (this.cache.containsKey(meta.getPath())) {
         return (BloomFilter)this.cache.get(meta.getPath());
      } else {
         try {
            if (!this.cache.containsKey(meta.getPath())) {
               BloomFilter bloomFilter = this.reader.readBloomFilter(meta);
               if (bloomFilter == null) {
                  return null;
               }

               this.cache.put(meta.getPath(), bloomFilter);
            }

            return (BloomFilter)this.cache.get(meta.getPath());
         } catch (IOException e) {
            this.logger.error("Failed to read Bloom filter data", e);
            return null;
         }
      }
   }
}
