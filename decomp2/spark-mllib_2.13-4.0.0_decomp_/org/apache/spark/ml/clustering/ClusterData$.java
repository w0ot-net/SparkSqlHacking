package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ClusterData$ extends AbstractFunction2 implements Serializable {
   public static final ClusterData$ MODULE$ = new ClusterData$();

   public final String toString() {
      return "ClusterData";
   }

   public ClusterData apply(final int clusterIdx, final Vector clusterCenter) {
      return new ClusterData(clusterIdx, clusterCenter);
   }

   public Option unapply(final ClusterData x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.clusterIdx()), x$0.clusterCenter())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClusterData$.class);
   }

   private ClusterData$() {
   }
}
