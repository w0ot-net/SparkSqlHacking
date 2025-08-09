package org.apache.parquet.column.values.bloomfilter;

public interface BloomFilterWriter extends AutoCloseable {
   void writeBloomFilter(BloomFilter var1);

   default void close() {
   }
}
