package org.apache.spark.mllib.tree.impurity;

import java.io.Serializable;
import java.util.Locale;
import scala.runtime.ModuleSerializationProxy;

public final class ImpurityCalculator$ implements Serializable {
   public static final ImpurityCalculator$ MODULE$ = new ImpurityCalculator$();

   public ImpurityCalculator getCalculator(final String impurity, final double[] stats, final long rawCount) {
      String var6 = impurity.toLowerCase(Locale.ROOT);
      switch (var6 == null ? 0 : var6.hashCode()) {
         case -1591567247:
            if ("entropy".equals(var6)) {
               return new EntropyCalculator(stats, rawCount);
            }
            break;
         case -1249575311:
            if ("variance".equals(var6)) {
               return new VarianceCalculator(stats, rawCount);
            }
            break;
         case 3172893:
            if ("gini".equals(var6)) {
               return new GiniCalculator(stats, rawCount);
            }
      }

      throw new IllegalArgumentException("ImpurityCalculator builder did not recognize impurity type: " + impurity);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ImpurityCalculator$.class);
   }

   private ImpurityCalculator$() {
   }
}
