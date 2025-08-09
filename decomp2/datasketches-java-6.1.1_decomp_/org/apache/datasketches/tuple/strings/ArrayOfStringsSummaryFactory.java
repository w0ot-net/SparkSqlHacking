package org.apache.datasketches.tuple.strings;

import org.apache.datasketches.tuple.SummaryFactory;

public class ArrayOfStringsSummaryFactory implements SummaryFactory {
   public ArrayOfStringsSummary newSummary() {
      return new ArrayOfStringsSummary();
   }
}
