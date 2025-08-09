package org.apache.datasketches.tuple.aninteger;

import org.apache.datasketches.tuple.SummaryFactory;

public class IntegerSummaryFactory implements SummaryFactory {
   private final IntegerSummary.Mode summaryMode_;

   public IntegerSummaryFactory(IntegerSummary.Mode summaryMode) {
      this.summaryMode_ = summaryMode;
   }

   public IntegerSummary newSummary() {
      return new IntegerSummary(this.summaryMode_);
   }
}
