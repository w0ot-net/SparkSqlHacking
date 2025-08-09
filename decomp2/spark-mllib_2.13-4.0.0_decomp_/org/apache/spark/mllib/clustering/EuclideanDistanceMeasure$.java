package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.util.MLUtils$;
import scala.runtime.ModuleSerializationProxy;

public final class EuclideanDistanceMeasure$ implements Serializable {
   public static final EuclideanDistanceMeasure$ MODULE$ = new EuclideanDistanceMeasure$();

   public double fastSquaredDistance(final VectorWithNorm v1, final VectorWithNorm v2) {
      return MLUtils$.MODULE$.fastSquaredDistance(v1.vector(), v1.norm(), v2.vector(), v2.norm(), MLUtils$.MODULE$.fastSquaredDistance$default$5());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EuclideanDistanceMeasure$.class);
   }

   private EuclideanDistanceMeasure$() {
   }
}
