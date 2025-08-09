package org.apache.parquet.filter;

public final class PagedRecordFilter implements RecordFilter {
   private final long startPos;
   private final long endPos;
   private long currentPos = 0L;

   public static UnboundRecordFilter page(long startPos, long pageSize) {
      return (readers) -> new PagedRecordFilter(startPos, pageSize);
   }

   private PagedRecordFilter(long startPos, long pageSize) {
      this.startPos = startPos;
      this.endPos = startPos + pageSize;
   }

   public boolean isMatch() {
      ++this.currentPos;
      return this.currentPos >= this.startPos && this.currentPos < this.endPos;
   }
}
