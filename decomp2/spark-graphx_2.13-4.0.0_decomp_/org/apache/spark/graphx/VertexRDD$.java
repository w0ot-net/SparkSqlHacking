package org.apache.spark.graphx;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.graphx.impl.EdgePartition;
import org.apache.spark.graphx.impl.RoutingTablePartition;
import org.apache.spark.graphx.impl.RoutingTablePartition$;
import org.apache.spark.graphx.impl.ShippableVertexPartition;
import org.apache.spark.graphx.impl.ShippableVertexPartition$;
import org.apache.spark.graphx.impl.VertexRDDImpl;
import org.apache.spark.graphx.impl.VertexRDDImpl$;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VertexRDD$ implements Serializable {
   public static final VertexRDD$ MODULE$ = new VertexRDD$();

   public VertexRDD apply(final RDD vertices, final ClassTag evidence$14) {
      Option var5 = vertices.partitioner();
      RDD var10000;
      if (var5 instanceof Some) {
         var10000 = vertices;
      } else {
         if (!.MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var10000 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(vertices, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), evidence$14, scala.math.Ordering.Long..MODULE$).partitionBy(new HashPartitioner(vertices.partitions().length));
      }

      RDD vPartitioned = var10000;
      RDD vertexPartitions = vPartitioned.mapPartitions((iter) -> scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{ShippableVertexPartition$.MODULE$.apply(iter, evidence$14)})), true, scala.reflect.ClassTag..MODULE$.apply(ShippableVertexPartition.class));
      return new VertexRDDImpl(vertexPartitions, VertexRDDImpl$.MODULE$.$lessinit$greater$default$2(), evidence$14);
   }

   public VertexRDD apply(final RDD vertices, final EdgeRDD edges, final Object defaultVal, final ClassTag evidence$15) {
      return this.apply(vertices, edges, defaultVal, (a, b) -> a, evidence$15);
   }

   public VertexRDD apply(final RDD vertices, final EdgeRDD edges, final Object defaultVal, final Function2 mergeFunc, final ClassTag evidence$16) {
      Option var8 = vertices.partitioner();
      RDD var10000;
      if (var8 instanceof Some) {
         var10000 = vertices;
      } else {
         if (!.MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         var10000 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(vertices, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), evidence$16, scala.math.Ordering.Long..MODULE$).partitionBy(new HashPartitioner(vertices.partitions().length));
      }

      RDD vPartitioned = var10000;
      RDD routingTables = this.createRoutingTables(edges, (Partitioner)vPartitioned.partitioner().get());
      RDD vertexPartitions = vPartitioned.zipPartitions(routingTables, true, (vertexIter, routingTableIter) -> {
         RoutingTablePartition routingTable = routingTableIter.hasNext() ? (RoutingTablePartition)routingTableIter.next() : RoutingTablePartition$.MODULE$.empty();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{ShippableVertexPartition$.MODULE$.apply(vertexIter, routingTable, defaultVal, mergeFunc, evidence$16)}));
      }, scala.reflect.ClassTag..MODULE$.apply(RoutingTablePartition.class), scala.reflect.ClassTag..MODULE$.apply(ShippableVertexPartition.class));
      return new VertexRDDImpl(vertexPartitions, VertexRDDImpl$.MODULE$.$lessinit$greater$default$2(), evidence$16);
   }

   public VertexRDD fromEdges(final EdgeRDD edges, final int numPartitions, final Object defaultVal, final ClassTag evidence$17) {
      RDD routingTables = this.createRoutingTables(edges, new HashPartitioner(numPartitions));
      RDD vertexPartitions = routingTables.mapPartitions((routingTableIter) -> {
         RoutingTablePartition routingTable = routingTableIter.hasNext() ? (RoutingTablePartition)routingTableIter.next() : RoutingTablePartition$.MODULE$.empty();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{ShippableVertexPartition$.MODULE$.apply(scala.package..MODULE$.Iterator().empty(), routingTable, defaultVal, evidence$17)}));
      }, true, scala.reflect.ClassTag..MODULE$.apply(ShippableVertexPartition.class));
      return new VertexRDDImpl(vertexPartitions, VertexRDDImpl$.MODULE$.$lessinit$greater$default$2(), evidence$17);
   }

   public RDD createRoutingTables(final EdgeRDD edges, final Partitioner vertexPartitioner) {
      RDD qual$1 = edges.partitionsRDD();
      Function1 x$1 = (x$2x) -> x$2x.flatMap(scala.Function..MODULE$.tupled((pid, edgePartition) -> $anonfun$createRoutingTables$2(BoxesRunTime.unboxToInt(pid), edgePartition)));
      boolean x$2 = qual$1.mapPartitions$default$2();
      RDD vid2pid = qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("VertexRDD.createRoutingTables - vid2pid (aggregation)");
      int numEdgePartitions = edges.partitions().length;
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(vid2pid, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.Long..MODULE$).partitionBy(vertexPartitioner).mapPartitions((iter) -> scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new RoutingTablePartition[]{RoutingTablePartition$.MODULE$.fromMsgs(numEdgePartitions, iter)})), true, scala.reflect.ClassTag..MODULE$.apply(RoutingTablePartition.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VertexRDD$.class);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$createRoutingTables$2(final int pid, final EdgePartition edgePartition) {
      return RoutingTablePartition$.MODULE$.edgePartitionToMsgs(pid, edgePartition);
   }

   private VertexRDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
