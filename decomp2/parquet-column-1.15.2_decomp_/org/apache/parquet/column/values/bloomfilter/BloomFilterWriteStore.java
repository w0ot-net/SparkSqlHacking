package org.apache.parquet.column.values.bloomfilter;

import org.apache.parquet.column.ColumnDescriptor;

public interface BloomFilterWriteStore extends AutoCloseable {
   BloomFilterWriter getBloomFilterWriter(ColumnDescriptor var1);

   default void close() {
   }
}
