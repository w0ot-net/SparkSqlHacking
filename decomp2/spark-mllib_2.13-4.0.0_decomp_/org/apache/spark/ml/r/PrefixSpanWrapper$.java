package org.apache.spark.ml.r;

import org.apache.spark.ml.fpm.PrefixSpan;

public final class PrefixSpanWrapper$ {
   public static final PrefixSpanWrapper$ MODULE$ = new PrefixSpanWrapper$();

   public PrefixSpan getPrefixSpan(final double minSupport, final int maxPatternLength, final double maxLocalProjDBSize, final String sequenceCol) {
      return (new PrefixSpan()).setMinSupport(minSupport).setMaxPatternLength(maxPatternLength).setMaxLocalProjDBSize((long)maxLocalProjDBSize).setSequenceCol(sequenceCol);
   }

   private PrefixSpanWrapper$() {
   }
}
