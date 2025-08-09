package org.apache.datasketches.tuple.adouble;

import org.apache.datasketches.tuple.SummarySetOperations;

public final class DoubleSummarySetOperations implements SummarySetOperations {
   private final DoubleSummary.Mode unionSummaryMode_;
   private final DoubleSummary.Mode intersectionSummaryMode_;

   public DoubleSummarySetOperations() {
      this.unionSummaryMode_ = DoubleSummary.Mode.Sum;
      this.intersectionSummaryMode_ = DoubleSummary.Mode.Sum;
   }

   public DoubleSummarySetOperations(DoubleSummary.Mode summaryMode) {
      this.unionSummaryMode_ = summaryMode;
      this.intersectionSummaryMode_ = summaryMode;
   }

   public DoubleSummarySetOperations(DoubleSummary.Mode unionSummaryMode, DoubleSummary.Mode intersectionSummaryMode) {
      this.unionSummaryMode_ = unionSummaryMode;
      this.intersectionSummaryMode_ = intersectionSummaryMode;
   }

   public DoubleSummary union(DoubleSummary a, DoubleSummary b) {
      DoubleSummary result = new DoubleSummary(this.unionSummaryMode_);
      result.update(a.getValue());
      result.update(b.getValue());
      return result;
   }

   public DoubleSummary intersection(DoubleSummary a, DoubleSummary b) {
      DoubleSummary result = new DoubleSummary(this.intersectionSummaryMode_);
      result.update(a.getValue());
      result.update(b.getValue());
      return result;
   }
}
