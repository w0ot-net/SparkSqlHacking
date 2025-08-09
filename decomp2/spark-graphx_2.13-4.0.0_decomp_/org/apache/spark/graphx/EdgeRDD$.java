package org.apache.spark.graphx;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.impl.EdgePartitionBuilder;
import org.apache.spark.graphx.impl.EdgePartitionBuilder$;
import org.apache.spark.graphx.impl.EdgeRDDImpl;
import org.apache.spark.graphx.impl.EdgeRDDImpl$;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class EdgeRDD$ implements Serializable {
   public static final EdgeRDD$ MODULE$ = new EdgeRDD$();

   public EdgeRDDImpl fromEdges(final RDD edges, final ClassTag evidence$4, final ClassTag evidence$5) {
      RDD edgePartitions = edges.mapPartitionsWithIndex((pid, iter) -> $anonfun$fromEdges$1(evidence$4, evidence$5, BoxesRunTime.unboxToInt(pid), iter), edges.mapPartitionsWithIndex$default$2(), .MODULE$.apply(Tuple2.class));
      return this.fromEdgePartitions(edgePartitions, evidence$4, evidence$5);
   }

   public EdgeRDDImpl fromEdgePartitions(final RDD edgePartitions, final ClassTag evidence$6, final ClassTag evidence$7) {
      return new EdgeRDDImpl(edgePartitions, EdgeRDDImpl$.MODULE$.$lessinit$greater$default$2(), evidence$6, evidence$7);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EdgeRDD$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$fromEdges$2(final EdgePartitionBuilder builder$1, final Edge e) {
      builder$1.add(e.srcId(), e.dstId(), e.attr());
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$fromEdges$1(final ClassTag evidence$4$1, final ClassTag evidence$5$1, final int pid, final Iterator iter) {
      EdgePartitionBuilder builder = new EdgePartitionBuilder(EdgePartitionBuilder$.MODULE$.$lessinit$greater$default$1(), evidence$4$1, evidence$5$1);
      iter.foreach((e) -> {
         $anonfun$fromEdges$2(builder, e);
         return BoxedUnit.UNIT;
      });
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(pid), builder.toEdgePartition())})));
   }

   private EdgeRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
