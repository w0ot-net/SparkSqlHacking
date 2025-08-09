package org.apache.parquet.internal.filter2.columnindex;

import org.apache.parquet.ParquetRuntimeException;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

public interface ColumnIndexStore {
   ColumnIndex getColumnIndex(ColumnPath var1);

   OffsetIndex getOffsetIndex(ColumnPath var1) throws MissingOffsetIndexException;

   public static class MissingOffsetIndexException extends ParquetRuntimeException {
      public MissingOffsetIndexException(ColumnPath path) {
         super("No offset index for column " + path.toDotString() + " is available; Unable to do filtering");
      }
   }
}
