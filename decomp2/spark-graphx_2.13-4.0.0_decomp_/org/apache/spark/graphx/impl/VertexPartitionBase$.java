package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import scala.Function2;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class VertexPartitionBase$ implements Serializable {
   public static final VertexPartitionBase$ MODULE$ = new VertexPartitionBase$();

   public Tuple3 initFrom(final Iterator iter, final ClassTag evidence$1) {
      GraphXPrimitiveKeyOpenHashMap map = new GraphXPrimitiveKeyOpenHashMap(.MODULE$.apply(Long.TYPE), evidence$1);
      iter.foreach((pair) -> {
         $anonfun$initFrom$1(map, pair);
         return BoxedUnit.UNIT;
      });
      return new Tuple3(map.keySet$mcJ$sp(), map._values(), map.keySet$mcJ$sp().getBitSet());
   }

   public Tuple3 initFrom(final Iterator iter, final Function2 mergeFunc, final ClassTag evidence$2) {
      GraphXPrimitiveKeyOpenHashMap map = new GraphXPrimitiveKeyOpenHashMap(.MODULE$.apply(Long.TYPE), evidence$2);
      iter.foreach((pair) -> {
         $anonfun$initFrom$2(map, mergeFunc, pair);
         return BoxedUnit.UNIT;
      });
      return new Tuple3(map.keySet$mcJ$sp(), map._values(), map.keySet$mcJ$sp().getBitSet());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VertexPartitionBase$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$initFrom$1(final GraphXPrimitiveKeyOpenHashMap map$1, final Tuple2 pair) {
      map$1.update(BoxesRunTime.boxToLong(pair._1$mcJ$sp()), pair._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$initFrom$2(final GraphXPrimitiveKeyOpenHashMap map$2, final Function2 mergeFunc$1, final Tuple2 pair) {
      map$2.setMerge(BoxesRunTime.boxToLong(pair._1$mcJ$sp()), pair._2(), mergeFunc$1);
   }

   private VertexPartitionBase$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
