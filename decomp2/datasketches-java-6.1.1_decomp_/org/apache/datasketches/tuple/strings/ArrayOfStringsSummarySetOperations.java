package org.apache.datasketches.tuple.strings;

import org.apache.datasketches.tuple.SummarySetOperations;

public class ArrayOfStringsSummarySetOperations implements SummarySetOperations {
   public ArrayOfStringsSummary union(ArrayOfStringsSummary a, ArrayOfStringsSummary b) {
      return a.copy();
   }

   public ArrayOfStringsSummary intersection(ArrayOfStringsSummary a, ArrayOfStringsSummary b) {
      return a.copy();
   }
}
