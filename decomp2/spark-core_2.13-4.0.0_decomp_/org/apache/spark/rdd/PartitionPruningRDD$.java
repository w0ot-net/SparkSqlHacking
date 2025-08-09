package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class PartitionPruningRDD$ implements Serializable {
   public static final PartitionPruningRDD$ MODULE$ = new PartitionPruningRDD$();

   public PartitionPruningRDD create(final RDD rdd, final Function1 partitionFilterFunc) {
      return new PartitionPruningRDD(rdd, partitionFilterFunc, rdd.elementClassTag());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PartitionPruningRDD$.class);
   }

   private PartitionPruningRDD$() {
   }
}
