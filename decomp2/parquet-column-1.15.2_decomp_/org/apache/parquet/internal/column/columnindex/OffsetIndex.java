package org.apache.parquet.internal.column.columnindex;

import java.util.Optional;

public interface OffsetIndex {
   int getPageCount();

   long getOffset(int var1);

   int getCompressedPageSize(int var1);

   long getFirstRowIndex(int var1);

   default int getPageOrdinal(int pageIndex) {
      return pageIndex;
   }

   default long getLastRowIndex(int pageIndex, long rowGroupRowCount) {
      int nextPageIndex = pageIndex + 1;
      return (nextPageIndex >= this.getPageCount() ? rowGroupRowCount : this.getFirstRowIndex(nextPageIndex)) - 1L;
   }

   default Optional getUnencodedByteArrayDataBytes(int pageIndex) {
      throw new UnsupportedOperationException("Un-encoded byte array data bytes is not implemented");
   }
}
