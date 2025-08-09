package org.apache.datasketches.tuple.aninteger;

import org.apache.datasketches.tuple.SummarySetOperations;

public class IntegerSummarySetOperations implements SummarySetOperations {
   private final IntegerSummary.Mode unionSummaryMode_;
   private final IntegerSummary.Mode intersectionSummaryMode_;

   public IntegerSummarySetOperations(IntegerSummary.Mode unionSummaryMode, IntegerSummary.Mode intersectionSummaryMode) {
      this.unionSummaryMode_ = unionSummaryMode;
      this.intersectionSummaryMode_ = intersectionSummaryMode;
   }

   public IntegerSummary union(IntegerSummary a, IntegerSummary b) {
      IntegerSummary result = new IntegerSummary(this.unionSummaryMode_);
      result.update(a.getValue());
      result.update(b.getValue());
      return result;
   }

   public IntegerSummary intersection(IntegerSummary a, IntegerSummary b) {
      IntegerSummary result = new IntegerSummary(this.intersectionSummaryMode_);
      result.update(a.getValue());
      result.update(b.getValue());
      return result;
   }
}
