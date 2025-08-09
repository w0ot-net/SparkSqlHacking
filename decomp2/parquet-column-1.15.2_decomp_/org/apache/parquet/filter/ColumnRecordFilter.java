package org.apache.parquet.filter;

import java.util.Arrays;
import java.util.Objects;
import org.apache.parquet.column.ColumnReader;

public final class ColumnRecordFilter implements RecordFilter {
   private final ColumnReader filterOnColumn;
   private final ColumnPredicates.Predicate filterPredicate;

   public static final UnboundRecordFilter column(final String columnPath, final ColumnPredicates.Predicate predicate) {
      Objects.requireNonNull(columnPath, "columnPath cannot be null");
      Objects.requireNonNull(predicate, "predicate cannot be null");
      return new UnboundRecordFilter() {
         final String[] filterPath = columnPath.split("\\.");

         public RecordFilter bind(Iterable readers) {
            for(ColumnReader reader : readers) {
               if (Arrays.equals(reader.getDescriptor().getPath(), this.filterPath)) {
                  return new ColumnRecordFilter(reader, predicate);
               }
            }

            throw new IllegalArgumentException("Column " + columnPath + " does not exist.");
         }
      };
   }

   private ColumnRecordFilter(ColumnReader filterOnColumn, ColumnPredicates.Predicate filterPredicate) {
      this.filterOnColumn = filterOnColumn;
      this.filterPredicate = filterPredicate;
   }

   public boolean isMatch() {
      return this.filterPredicate.apply(this.filterOnColumn);
   }
}
