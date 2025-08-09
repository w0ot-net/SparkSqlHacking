package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.util.Utils$;
import scala.runtime.ModuleSerializationProxy;

public final class PartitionwiseSampledRDD$ implements Serializable {
   public static final PartitionwiseSampledRDD$ MODULE$ = new PartitionwiseSampledRDD$();

   public long $lessinit$greater$default$4() {
      return Utils$.MODULE$.random().nextLong();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PartitionwiseSampledRDD$.class);
   }

   private PartitionwiseSampledRDD$() {
   }
}
