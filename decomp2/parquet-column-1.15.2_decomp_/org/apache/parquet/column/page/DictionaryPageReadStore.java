package org.apache.parquet.column.page;

import org.apache.parquet.column.ColumnDescriptor;

public interface DictionaryPageReadStore extends AutoCloseable {
   DictionaryPage readDictionaryPage(ColumnDescriptor var1);

   default void close() {
   }
}
