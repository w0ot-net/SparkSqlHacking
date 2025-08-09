package org.apache.parquet.filter;

import java.util.Objects;
import org.apache.parquet.column.ColumnReader;

public final class AndRecordFilter implements RecordFilter {
   private final RecordFilter boundFilter1;
   private final RecordFilter boundFilter2;

   public static final UnboundRecordFilter and(final UnboundRecordFilter filter1, final UnboundRecordFilter filter2) {
      Objects.requireNonNull(filter1, "filter1 cannot be null");
      Objects.requireNonNull(filter2, "filter2 cannot be null");
      return new UnboundRecordFilter() {
         public RecordFilter bind(Iterable readers) {
            return new AndRecordFilter(filter1.bind(readers), filter2.bind(readers));
         }
      };
   }

   private AndRecordFilter(RecordFilter boundFilter1, RecordFilter boundFilter2) {
      this.boundFilter1 = boundFilter1;
      this.boundFilter2 = boundFilter2;
   }

   public boolean isMatch() {
      return this.boundFilter1.isMatch() && this.boundFilter2.isMatch();
   }
}
