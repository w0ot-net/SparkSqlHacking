package org.apache.spark.graphx;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.graphx.impl.EdgePartition;
import org.apache.spark.graphx.impl.RoutingTablePartition;
import org.apache.spark.graphx.impl.ShippableVertexPartition;
import org.apache.spark.graphx.impl.VertexAttributeBlock;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.util.BoundedPriorityQueue;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;

public final class GraphXUtils$ {
   public static final GraphXUtils$ MODULE$ = new GraphXUtils$();

   public void registerKryoClasses(final SparkConf conf) {
      conf.registerKryoClasses((Class[])((Object[])(new Class[]{Edge.class, Tuple2.class, EdgePartition.class, ShippableVertexPartition.class, RoutingTablePartition.class, BitSet.class, OpenHashSet.class, VertexAttributeBlock.class, PartitionStrategy.class, BoundedPriorityQueue.class, EdgeDirection.class, GraphXPrimitiveKeyOpenHashMap.class, OpenHashSet.class, OpenHashSet.class})));
   }

   public VertexRDD mapReduceTriplets(final Graph g, final Function1 mapFunc, final Function2 reduceFunc, final Option activeSetOpt, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      return g.aggregateMessagesWithActiveSet((ctx) -> {
         $anonfun$mapReduceTriplets$2(mapFunc, ctx);
         return BoxedUnit.UNIT;
      }, reduceFunc, TripletFields.All, activeSetOpt, evidence$3);
   }

   public Option mapReduceTriplets$default$4() {
      return .MODULE$;
   }

   // $FF: synthetic method
   public static final void $anonfun$mapReduceTriplets$1(final EdgeContext ctx$1, final Tuple2 kv) {
      long id = kv._1$mcJ$sp();
      Object msg = kv._2();
      if (id == ctx$1.srcId()) {
         ctx$1.sendToSrc(msg);
      } else {
         scala.Predef..MODULE$.assert(id == ctx$1.dstId());
         ctx$1.sendToDst(msg);
      }
   }

   private static final void sendMsg$1(final EdgeContext ctx, final Function1 mapFunc$1) {
      ((IterableOnceOps)mapFunc$1.apply(ctx.toEdgeTriplet())).foreach((kv) -> {
         $anonfun$mapReduceTriplets$1(ctx, kv);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$mapReduceTriplets$2(final Function1 mapFunc$1, final EdgeContext ctx) {
      sendMsg$1(ctx, mapFunc$1);
   }

   private GraphXUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
