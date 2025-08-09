package org.apache.parquet.column.page;

import java.util.Optional;
import org.apache.parquet.column.ColumnDescriptor;

public interface PageReadStore extends AutoCloseable {
   PageReader getPageReader(ColumnDescriptor var1);

   long getRowCount();

   default Optional getRowIndexOffset() {
      return Optional.empty();
   }

   default Optional getRowIndexes() {
      return Optional.empty();
   }

   default void close() {
   }
}
