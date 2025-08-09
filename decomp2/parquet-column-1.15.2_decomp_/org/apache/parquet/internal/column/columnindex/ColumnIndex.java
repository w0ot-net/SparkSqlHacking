package org.apache.parquet.internal.column.columnindex;

import java.util.List;
import org.apache.parquet.filter2.predicate.FilterPredicate;

public interface ColumnIndex extends FilterPredicate.Visitor {
   BoundaryOrder getBoundaryOrder();

   List getNullCounts();

   List getNullPages();

   List getMinValues();

   List getMaxValues();

   default List getRepetitionLevelHistogram() {
      throw new UnsupportedOperationException("Repetition level histogram is not implemented");
   }

   default List getDefinitionLevelHistogram() {
      throw new UnsupportedOperationException("Definition level histogram is not implemented");
   }
}
