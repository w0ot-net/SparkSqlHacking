package org.apache.spark.graphx.lib;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Pregel$;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Map.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

public final class ShortestPaths$ implements Serializable {
   public static final ShortestPaths$ MODULE$ = new ShortestPaths$();

   private Map makeMap(final Seq x) {
      return (Map).MODULE$.apply(x);
   }

   private Map incrementMap(final Map spmap) {
      return (Map)spmap.map((x0$1) -> {
         if (x0$1 != null) {
            long v = x0$1._1$mcJ$sp();
            int d = x0$1._2$mcI$sp();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(v)), BoxesRunTime.boxToInteger(d + 1));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Map addMaps(final Map spmap1, final Map spmap2) {
      scala.collection.mutable.Map map = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      spmap1.keySet().$plus$plus(spmap2.keySet()).foreach((k) -> $anonfun$addMaps$1(map, spmap1, spmap2, BoxesRunTime.unboxToLong(k)));
      return map;
   }

   public Graph run(final Graph graph, final Seq landmarks, final ClassTag evidence$1) {
      Function2 x$1 = (vid, attr) -> $anonfun$run$1(landmarks, BoxesRunTime.unboxToLong(vid), attr);
      ClassTag x$2 = scala.reflect.ClassTag..MODULE$.apply(Map.class);
      Null x$3 = graph.mapVertices$default$3(x$1);
      Graph spGraph = graph.mapVertices(x$1, x$2, (scala..eq.colon.eq)null);
      Map initialMessage = this.makeMap(scala.collection.immutable.Nil..MODULE$);
      return Pregel$.MODULE$.apply(spGraph, initialMessage, Pregel$.MODULE$.apply$default$3(), Pregel$.MODULE$.apply$default$4(), (id, attr, msg) -> $anonfun$run$2(this, BoxesRunTime.unboxToLong(id), attr, msg), (edge) -> this.sendMessage$1(edge), (spmap1, spmap2) -> MODULE$.addMaps(spmap1, spmap2), scala.reflect.ClassTag..MODULE$.apply(Map.class), evidence$1, scala.reflect.ClassTag..MODULE$.apply(Map.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ShortestPaths$.class);
   }

   // $FF: synthetic method
   public static final Option $anonfun$addMaps$1(final scala.collection.mutable.Map map$1, final Map spmap1$1, final Map spmap2$1, final long k) {
      return map$1.put(BoxesRunTime.boxToLong(k), BoxesRunTime.boxToInteger(scala.math.package..MODULE$.min(BoxesRunTime.unboxToInt(spmap1$1.getOrElse(BoxesRunTime.boxToLong(k), (JFunction0.mcI.sp)() -> Integer.MAX_VALUE)), BoxesRunTime.unboxToInt(spmap2$1.getOrElse(BoxesRunTime.boxToLong(k), (JFunction0.mcI.sp)() -> Integer.MAX_VALUE)))));
   }

   // $FF: synthetic method
   public static final Map $anonfun$run$1(final Seq landmarks$1, final long vid, final Object attr) {
      return landmarks$1.contains(BoxesRunTime.boxToLong(vid)) ? MODULE$.makeMap(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(vid)), BoxesRunTime.boxToInteger(0))}))) : MODULE$.makeMap(scala.collection.immutable.Nil..MODULE$);
   }

   private final Map vertexProgram$1(final long id, final Map attr, final Map msg) {
      return this.addMaps(attr, msg);
   }

   private final Iterator sendMessage$1(final EdgeTriplet edge) {
      Map newAttr = this.incrementMap((Map)edge.dstAttr());
      Object var10000 = edge.srcAttr();
      Map var3 = this.addMaps(newAttr, (Map)edge.srcAttr());
      if (var10000 == null) {
         if (var3 != null) {
            return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(edge.srcId()), newAttr)})));
         }
      } else if (!var10000.equals(var3)) {
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(edge.srcId()), newAttr)})));
      }

      return scala.package..MODULE$.Iterator().empty();
   }

   // $FF: synthetic method
   public static final Map $anonfun$run$2(final ShortestPaths$ $this, final long id, final Map attr, final Map msg) {
      return $this.vertexProgram$1(id, attr, msg);
   }

   private ShortestPaths$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
