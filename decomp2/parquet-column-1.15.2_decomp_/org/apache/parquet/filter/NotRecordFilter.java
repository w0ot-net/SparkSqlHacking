package org.apache.parquet.filter;

import java.util.Objects;
import org.apache.parquet.column.ColumnReader;

public final class NotRecordFilter implements RecordFilter {
   private final RecordFilter boundFilter;

   public static final UnboundRecordFilter not(final UnboundRecordFilter filter) {
      Objects.requireNonNull(filter, "filter cannot be null");
      return new UnboundRecordFilter() {
         public RecordFilter bind(Iterable readers) {
            return new NotRecordFilter(filter.bind(readers));
         }
      };
   }

   private NotRecordFilter(RecordFilter boundFilter) {
      this.boundFilter = boundFilter;
   }

   public boolean isMatch() {
      return !this.boundFilter.isMatch();
   }
}
