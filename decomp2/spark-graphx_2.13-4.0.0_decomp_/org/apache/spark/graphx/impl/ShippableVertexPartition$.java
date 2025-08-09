package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import scala.Function2;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ShippableVertexPartition$ implements Serializable {
   public static final ShippableVertexPartition$ MODULE$ = new ShippableVertexPartition$();

   public ShippableVertexPartition apply(final Iterator iter, final ClassTag evidence$2) {
      return this.apply(iter, RoutingTablePartition$.MODULE$.empty(), (Object)null, (a, b) -> a, evidence$2);
   }

   public ShippableVertexPartition apply(final Iterator iter, final RoutingTablePartition routingTable, final Object defaultVal, final ClassTag evidence$3) {
      return this.apply(iter, routingTable, defaultVal, (a, b) -> a, evidence$3);
   }

   public ShippableVertexPartition apply(final Iterator iter, final RoutingTablePartition routingTable, final Object defaultVal, final Function2 mergeFunc, final ClassTag evidence$4) {
      GraphXPrimitiveKeyOpenHashMap map = new GraphXPrimitiveKeyOpenHashMap(.MODULE$.apply(Long.TYPE), evidence$4);
      iter.foreach((pair) -> {
         $anonfun$apply$3(map, mergeFunc, pair);
         return BoxedUnit.UNIT;
      });
      routingTable.iterator().foreach((vid) -> $anonfun$apply$4(map, defaultVal, BoxesRunTime.unboxToLong(vid)));
      return new ShippableVertexPartition(map.keySet$mcJ$sp(), map._values(), map.keySet$mcJ$sp().getBitSet(), routingTable, evidence$4);
   }

   public ShippableVertexPartitionOps shippablePartitionToOps(final ShippableVertexPartition partition, final ClassTag evidence$5) {
      return new ShippableVertexPartitionOps(partition, evidence$5);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShippableVertexPartition$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$3(final GraphXPrimitiveKeyOpenHashMap map$1, final Function2 mergeFunc$1, final Tuple2 pair) {
      map$1.setMerge(BoxesRunTime.boxToLong(pair._1$mcJ$sp()), pair._2(), mergeFunc$1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$apply$4(final GraphXPrimitiveKeyOpenHashMap map$1, final Object defaultVal$1, final long vid) {
      return map$1.changeValue(BoxesRunTime.boxToLong(vid), () -> defaultVal$1, (x) -> scala.Predef..MODULE$.identity(x));
   }

   private ShippableVertexPartition$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
