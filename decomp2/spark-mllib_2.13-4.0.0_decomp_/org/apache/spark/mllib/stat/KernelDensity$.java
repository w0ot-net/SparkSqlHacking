package org.apache.spark.mllib.stat;

import java.io.Serializable;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class KernelDensity$ implements Serializable {
   public static final KernelDensity$ MODULE$ = new KernelDensity$();

   public double normPdf(final double mean, final double standardDeviation, final double logStandardDeviationPlusHalfLog2Pi, final double x) {
      double x0 = x - mean;
      double x1 = x0 / standardDeviation;
      double logDensity = (double)-0.5F * x1 * x1 - logStandardDeviationPlusHalfLog2Pi;
      return .MODULE$.exp(logDensity);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(KernelDensity$.class);
   }

   private KernelDensity$() {
   }
}
