package org.apache.parquet.column.page;

import org.apache.parquet.column.ColumnDescriptor;

public interface PageWriteStore extends AutoCloseable {
   PageWriter getPageWriter(ColumnDescriptor var1);

   default void close() {
   }
}
