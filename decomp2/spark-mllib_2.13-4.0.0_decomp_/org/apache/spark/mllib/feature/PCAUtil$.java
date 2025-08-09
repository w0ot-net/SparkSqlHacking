package org.apache.spark.mllib.feature;

import scala.math.package.;

public final class PCAUtil$ {
   public static final PCAUtil$ MODULE$ = new PCAUtil$();

   public long memoryCost(final int k, final int numFeatures) {
      return 3L * (long).MODULE$.min(k, numFeatures) * (long).MODULE$.min(k, numFeatures) + .MODULE$.max((long).MODULE$.max(k, numFeatures), 4L * (long).MODULE$.min(k, numFeatures) * (long).MODULE$.min(k, numFeatures) + 4L * (long).MODULE$.min(k, numFeatures));
   }

   private PCAUtil$() {
   }
}
