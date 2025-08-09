package org.apache.datasketches.tuple.adouble;

import org.apache.datasketches.tuple.SummaryFactory;

public final class DoubleSummaryFactory implements SummaryFactory {
   private final DoubleSummary.Mode summaryMode_;

   public DoubleSummaryFactory(DoubleSummary.Mode summaryMode) {
      this.summaryMode_ = summaryMode;
   }

   public DoubleSummary newSummary() {
      return new DoubleSummary(this.summaryMode_);
   }
}
