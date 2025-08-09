package org.apache.spark.graphx.lib;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Pregel$;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

public final class LabelPropagation$ {
   public static final LabelPropagation$ MODULE$ = new LabelPropagation$();

   public Graph run(final Graph graph, final int maxSteps, final ClassTag evidence$1) {
      .MODULE$.require(maxSteps > 0, () -> "Maximum of steps must be greater than 0, but got " + maxSteps);
      Function2 x$1 = (x0$1, x1$1) -> BoxesRunTime.boxToLong($anonfun$run$2(BoxesRunTime.unboxToLong(x0$1), x1$1));
      ClassTag x$2 = scala.reflect.ClassTag..MODULE$.apply(Long.TYPE);
      Null x$3 = graph.mapVertices$default$3(x$1);
      Graph lpaGraph = graph.mapVertices(x$1, x$2, (scala..eq.colon.eq)null);
      Map initialMessage = (Map)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      return Pregel$.MODULE$.apply(lpaGraph, initialMessage, maxSteps, Pregel$.MODULE$.apply$default$4(), (vid, attr, message) -> BoxesRunTime.boxToLong($anonfun$run$7(BoxesRunTime.unboxToLong(vid), BoxesRunTime.unboxToLong(attr), message)), (e) -> sendMessage$1(e), (count1, count2) -> mergeMessage$1(count1, count2), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), evidence$1, scala.reflect.ClassTag..MODULE$.apply(Map.class));
   }

   // $FF: synthetic method
   public static final long $anonfun$run$2(final long x0$1, final Object x1$1) {
      Tuple2 var5 = new Tuple2(BoxesRunTime.boxToLong(x0$1), x1$1);
      if (var5 != null) {
         long vid = var5._1$mcJ$sp();
         return vid;
      } else {
         throw new MatchError(var5);
      }
   }

   private static final Iterator sendMessage$1(final EdgeTriplet e) {
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(e.srcId()), scala.collection.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(e.dstAttr()), BoxesRunTime.boxToLong(1L))})))), new Tuple2(BoxesRunTime.boxToLong(e.dstId()), scala.collection.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(e.srcAttr()), BoxesRunTime.boxToLong(1L))}))))})));
   }

   // $FF: synthetic method
   public static final Option $anonfun$run$3(final Map count1$1, final Map count2$1, final scala.collection.mutable.Map map$1, final long i) {
      long count1Val = BoxesRunTime.unboxToLong(count1$1.getOrElse(BoxesRunTime.boxToLong(i), (JFunction0.mcJ.sp)() -> 0L));
      long count2Val = BoxesRunTime.unboxToLong(count2$1.getOrElse(BoxesRunTime.boxToLong(i), (JFunction0.mcJ.sp)() -> 0L));
      return map$1.put(BoxesRunTime.boxToLong(i), BoxesRunTime.boxToLong(count1Val + count2Val));
   }

   private static final Map mergeMessage$1(final Map count1, final Map count2) {
      scala.collection.mutable.Map map = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      count1.keySet().$plus$plus(count2.keySet()).foreach((i) -> $anonfun$run$3(count1, count2, map, BoxesRunTime.unboxToLong(i)));
      return map;
   }

   // $FF: synthetic method
   public static final long $anonfun$run$6(final Tuple2 x$1) {
      return x$1._2$mcJ$sp();
   }

   private static final long vertexProgram$1(final long vid, final long attr, final Map message) {
      return message.isEmpty() ? attr : ((Tuple2)message.maxBy((x$1) -> BoxesRunTime.boxToLong($anonfun$run$6(x$1)), scala.math.Ordering.Long..MODULE$))._1$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$run$7(final long vid, final long attr, final Map message) {
      return vertexProgram$1(vid, attr, message);
   }

   private LabelPropagation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
