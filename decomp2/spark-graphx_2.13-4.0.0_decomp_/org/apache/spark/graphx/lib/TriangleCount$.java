package org.apache.spark.graphx.lib;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.EdgeDirection$;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Graph$;
import org.apache.spark.graphx.GraphOps;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

public final class TriangleCount$ {
   public static final TriangleCount$ MODULE$ = new TriangleCount$();

   public Graph run(final Graph graph, final ClassTag evidence$1, final ClassTag evidence$2) {
      GraphOps qual$1 = Graph$.MODULE$.graphToGraphOps(Graph$.MODULE$.graphToGraphOps(graph.mapEdges((Function1)((e) -> BoxesRunTime.boxToBoolean($anonfun$run$1(e))), .MODULE$.Boolean()), evidence$1, .MODULE$.Boolean()).removeSelfEdges(), evidence$1, .MODULE$.Boolean());
      Function2 x$1 = qual$1.convertToCanonicalEdges$default$1();
      Graph canonicalGraph = qual$1.convertToCanonicalEdges(x$1);
      VertexRDD counters = this.runPreCanonicalized(canonicalGraph, evidence$1, .MODULE$.Boolean()).vertices();
      Function3 x$3 = (vid, x$1x, optCounter) -> BoxesRunTime.boxToInteger($anonfun$run$2(BoxesRunTime.unboxToLong(vid), x$1x, optCounter));
      ClassTag x$4 = .MODULE$.Int();
      ClassTag x$5 = .MODULE$.Int();
      Null x$6 = graph.outerJoinVertices$default$5(counters, x$3);
      return graph.outerJoinVertices(counters, x$3, x$4, x$5, (scala..eq.colon.eq)null);
   }

   public Graph runPreCanonicalized(final Graph graph, final ClassTag evidence$3, final ClassTag evidence$4) {
      VertexRDD nbrSets = Graph$.MODULE$.graphToGraphOps(graph, evidence$3, evidence$4).collectNeighborIds(EdgeDirection$.MODULE$.Either()).mapValues((Function2)((vid, nbrs) -> $anonfun$runPreCanonicalized$1(BoxesRunTime.unboxToLong(vid), nbrs)), .MODULE$.apply(OpenHashSet.class));
      Function3 x$2 = (vid, x$2x, optSet) -> $anonfun$runPreCanonicalized$2(BoxesRunTime.unboxToLong(vid), x$2x, optSet);
      ClassTag x$3 = .MODULE$.apply(OpenHashSet.class);
      ClassTag x$4 = .MODULE$.apply(OpenHashSet.class);
      Null x$5 = graph.outerJoinVertices$default$5(nbrSets, x$2);
      Graph setGraph = graph.outerJoinVertices(nbrSets, x$2, x$3, x$4, (scala..eq.colon.eq)null);
      VertexRDD counters = setGraph.aggregateMessages((ctx) -> {
         $anonfun$runPreCanonicalized$3(ctx);
         return BoxedUnit.UNIT;
      }, (JFunction2.mcIII.sp)(x$4x, x$5x) -> x$4x + x$5x, setGraph.aggregateMessages$default$3(), .MODULE$.Int());
      Function3 x$7 = (x$6, x$7x, optCounter) -> BoxesRunTime.boxToInteger($anonfun$runPreCanonicalized$5(BoxesRunTime.unboxToLong(x$6), x$7x, optCounter));
      ClassTag x$8 = .MODULE$.Int();
      ClassTag x$9 = .MODULE$.Int();
      Null x$10 = graph.outerJoinVertices$default$5(counters, x$7);
      return graph.outerJoinVertices(counters, x$7, x$8, x$9, (scala..eq.colon.eq)null);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$1(final Edge e) {
      return true;
   }

   // $FF: synthetic method
   public static final int $anonfun$run$2(final long vid, final Object x$1, final Option optCounter) {
      return BoxesRunTime.unboxToInt(optCounter.getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   public static final OpenHashSet $anonfun$runPreCanonicalized$1(final long vid, final long[] nbrs) {
      OpenHashSet set = new OpenHashSet.mcJ.sp(nbrs.length, .MODULE$.apply(Long.TYPE));

      for(int i = 0; i < nbrs.length; ++i) {
         if (nbrs[i] != vid) {
            set.add$mcJ$sp(nbrs[i]);
         }
      }

      return set;
   }

   // $FF: synthetic method
   public static final OpenHashSet $anonfun$runPreCanonicalized$2(final long vid, final Object x$2, final Option optSet) {
      return (OpenHashSet)optSet.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private static final void edgeFunc$1(final EdgeContext ctx) {
      Tuple2 var3 = ((OpenHashSet)ctx.srcAttr()).size() < ((OpenHashSet)ctx.dstAttr()).size() ? new Tuple2(ctx.srcAttr(), ctx.dstAttr()) : new Tuple2(ctx.dstAttr(), ctx.srcAttr());
      if (var3 != null) {
         OpenHashSet smallSet = (OpenHashSet)var3._1();
         OpenHashSet largeSet = (OpenHashSet)var3._2();
         Tuple2 var2 = new Tuple2(smallSet, largeSet);
         OpenHashSet smallSet = (OpenHashSet)var2._1();
         OpenHashSet largeSet = (OpenHashSet)var2._2();
         Iterator iter = smallSet.iterator();
         int counter = 0;

         while(iter.hasNext()) {
            long vid = BoxesRunTime.unboxToLong(iter.next());
            if (vid != ctx.srcId() && vid != ctx.dstId() && largeSet.contains$mcJ$sp(vid)) {
               ++counter;
            }
         }

         ctx.sendToSrc(BoxesRunTime.boxToInteger(counter));
         ctx.sendToDst(BoxesRunTime.boxToInteger(counter));
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$runPreCanonicalized$3(final EdgeContext ctx) {
      edgeFunc$1(ctx);
   }

   // $FF: synthetic method
   public static final int $anonfun$runPreCanonicalized$5(final long x$6, final Object x$7, final Option optCounter) {
      int dblCount = BoxesRunTime.unboxToInt(optCounter.getOrElse((JFunction0.mcI.sp)() -> 0));
      scala.Predef..MODULE$.require(dblCount % 2 == 0, () -> "Triangle count resulted in an invalid number of triangles.");
      return dblCount / 2;
   }

   private TriangleCount$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
