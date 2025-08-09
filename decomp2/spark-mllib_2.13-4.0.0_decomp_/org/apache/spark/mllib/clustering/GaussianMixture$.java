package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

public final class GaussianMixture$ implements Serializable {
   public static final GaussianMixture$ MODULE$ = new GaussianMixture$();
   private static final int MAX_NUM_FEATURES;

   static {
      MAX_NUM_FEATURES = (int).MODULE$.sqrt((double)Integer.MAX_VALUE);
   }

   public int MAX_NUM_FEATURES() {
      return MAX_NUM_FEATURES;
   }

   public boolean shouldDistributeGaussians(final int k, final int d) {
      return ((double)k - (double)1.0F) / (double)k * (double)d > (double)25;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GaussianMixture$.class);
   }

   private GaussianMixture$() {
   }
}
