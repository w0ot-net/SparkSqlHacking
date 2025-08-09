package org.apache.spark.graphx.lib;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeDirection;
import org.apache.spark.graphx.EdgeDirection$;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Graph$;
import org.apache.spark.graphx.Pregel$;
import org.apache.spark.graphx.VertexRDD;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;

public final class StronglyConnectedComponents$ {
   public static final StronglyConnectedComponents$ MODULE$ = new StronglyConnectedComponents$();

   public Graph run(final Graph graph, final int numIter, final ClassTag evidence$1, final ClassTag evidence$2) {
      .MODULE$.require(numIter > 0, () -> "Number of iterations must be greater than 0, but got " + numIter);
      Function2 x$1 = (x0$1, x1$1) -> BoxesRunTime.boxToLong($anonfun$run$2(BoxesRunTime.unboxToLong(x0$1), x1$1));
      ClassTag x$2 = scala.reflect.ClassTag..MODULE$.apply(Long.TYPE);
      Null x$3 = graph.mapVertices$default$3(x$1);
      Graph sccGraph = graph.mapVertices(x$1, x$2, (scala..eq.colon.eq)null);
      Function2 x$4 = (x0$2, x1$2) -> $anonfun$run$3(BoxesRunTime.unboxToLong(x0$2), x1$2);
      ClassTag x$5 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$6 = graph.mapVertices$default$3(x$4);
      Graph sccWorkGraph = graph.mapVertices(x$4, x$5, (scala..eq.colon.eq)null).cache();
      Graph prevSccGraph = sccGraph;
      long numVertices = Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).numVertices();
      int iter = 0;

      while(Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).numVertices() > 0L && iter < numIter) {
         ++iter;

         do {
            numVertices = Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).numVertices();
            sccWorkGraph = sccWorkGraph.outerJoinVertices(Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).outDegrees(), (vid, data, degreeOpt) -> $anonfun$run$4(BoxesRunTime.unboxToLong(vid), data, degreeOpt), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala..less.colon.less..MODULE$.refl()).outerJoinVertices(Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).inDegrees(), (vid, data, degreeOpt) -> $anonfun$run$5(BoxesRunTime.unboxToLong(vid), data, degreeOpt), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala..less.colon.less..MODULE$.refl()).cache();
            VertexRDD finalVertices = sccWorkGraph.vertices().filter((x0$3) -> BoxesRunTime.boxToBoolean($anonfun$run$6(x0$3))).mapValues((Function2)((vid, data) -> BoxesRunTime.boxToLong($anonfun$run$7(BoxesRunTime.unboxToLong(vid), data))), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE));
            sccGraph = sccGraph.outerJoinVertices(finalVertices, (vid, scc, opt) -> BoxesRunTime.boxToLong($anonfun$run$8(BoxesRunTime.unboxToLong(vid), BoxesRunTime.unboxToLong(scc), opt)), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala..less.colon.less..MODULE$.refl()).cache();
            sccGraph.vertices().count();
            sccGraph.edges().count();
            boolean x$7 = prevSccGraph.unpersist$default$1();
            prevSccGraph.unpersist(x$7);
            prevSccGraph = sccGraph;
            Function2 x$8 = (vid, data) -> BoxesRunTime.boxToBoolean($anonfun$run$10(BoxesRunTime.unboxToLong(vid), data));
            Function1 x$9 = sccWorkGraph.subgraph$default$1();
            sccWorkGraph = sccWorkGraph.subgraph(x$9, x$8).cache();
         } while(Graph$.MODULE$.graphToGraphOps(sccWorkGraph, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2).numVertices() < numVertices);

         if (iter < numIter) {
            sccWorkGraph = sccWorkGraph.mapVertices((x0$4, x1$3) -> $anonfun$run$11(BoxesRunTime.unboxToLong(x0$4), x1$3), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala..less.colon.less..MODULE$.refl());
            long x$11 = Long.MAX_VALUE;
            EdgeDirection x$12 = EdgeDirection$.MODULE$.Out();
            int x$13 = Pregel$.MODULE$.apply$default$3();
            Function3 x$14 = (vid, myScc, neighborScc) -> $anonfun$run$12(BoxesRunTime.unboxToLong(vid), myScc, BoxesRunTime.unboxToLong(neighborScc));
            Function1 x$15 = (e) -> ((Tuple2)e.srcAttr())._1$mcJ$sp() < ((Tuple2)e.dstAttr())._1$mcJ$sp() ? scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcJJ.sp(e.dstId(), ((Tuple2)e.srcAttr())._1$mcJ$sp())}))) : scala.package..MODULE$.Iterator().apply(scala.collection.immutable.Nil..MODULE$);
            Function2 x$16 = (vid1, vid2) -> scala.math.package..MODULE$.min(vid1, vid2);
            sccWorkGraph = Pregel$.MODULE$.apply(sccWorkGraph, BoxesRunTime.boxToLong(Long.MAX_VALUE), x$13, x$12, x$14, x$15, x$16, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE));
            boolean x$18 = false;
            EdgeDirection x$19 = EdgeDirection$.MODULE$.In();
            int x$20 = Pregel$.MODULE$.apply$default$3();
            Function3 x$21 = (vid, myScc, existsSameColorFinalNeighbor) -> $anonfun$run$15(BoxesRunTime.unboxToLong(vid), myScc, BoxesRunTime.unboxToBoolean(existsSameColorFinalNeighbor));
            Function1 x$22 = (e) -> {
               boolean sameColor = ((Tuple2)e.dstAttr())._1$mcJ$sp() == ((Tuple2)e.srcAttr())._1$mcJ$sp();
               boolean onlyDstIsFinal = ((Tuple2)e.dstAttr())._2$mcZ$sp() && !((Tuple2)e.srcAttr())._2$mcZ$sp();
               return sameColor && onlyDstIsFinal ? scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcJZ.sp(e.srcId(), ((Tuple2)e.dstAttr())._2$mcZ$sp())}))) : scala.package..MODULE$.Iterator().apply(scala.collection.immutable.Nil..MODULE$);
            };
            Function2 x$23 = (final1, final2) -> BoxesRunTime.boxToBoolean($anonfun$run$17(BoxesRunTime.unboxToBoolean(final1), BoxesRunTime.unboxToBoolean(final2)));
            sccWorkGraph = Pregel$.MODULE$.apply(sccWorkGraph, BoxesRunTime.boxToBoolean(false), x$20, x$19, x$21, x$22, x$23, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), evidence$2, scala.reflect.ClassTag..MODULE$.Boolean());
         }
      }

      return sccGraph;
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

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$3(final long x0$2, final Object x1$2) {
      Tuple2 var4 = new Tuple2(BoxesRunTime.boxToLong(x0$2), x1$2);
      if (var4 != null) {
         long vid = var4._1$mcJ$sp();
         return new Tuple2.mcJZ.sp(vid, false);
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$4(final long vid, final Tuple2 data, final Option degreeOpt) {
      return (Tuple2)(degreeOpt.isDefined() ? data : new Tuple2.mcJZ.sp(vid, true));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$5(final long vid, final Tuple2 data, final Option degreeOpt) {
      return (Tuple2)(degreeOpt.isDefined() ? data : new Tuple2.mcJZ.sp(vid, true));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$6(final Tuple2 x0$3) {
      if (x0$3 != null) {
         Tuple2 var3 = (Tuple2)x0$3._2();
         if (var3 != null) {
            boolean isFinal = var3._2$mcZ$sp();
            return isFinal;
         }
      }

      throw new MatchError(x0$3);
   }

   // $FF: synthetic method
   public static final long $anonfun$run$7(final long vid, final Tuple2 data) {
      return data._1$mcJ$sp();
   }

   // $FF: synthetic method
   public static final long $anonfun$run$8(final long vid, final long scc, final Option opt) {
      return BoxesRunTime.unboxToLong(opt.getOrElse((JFunction0.mcJ.sp)() -> scc));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$10(final long vid, final Tuple2 data) {
      return !data._2$mcZ$sp();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$11(final long x0$4, final Tuple2 x1$3) {
      Tuple2 var4 = new Tuple2(BoxesRunTime.boxToLong(x0$4), x1$3);
      if (var4 != null) {
         long vid = var4._1$mcJ$sp();
         Tuple2 var7 = (Tuple2)var4._2();
         if (var7 != null) {
            boolean isFinal = var7._2$mcZ$sp();
            return new Tuple2.mcJZ.sp(vid, isFinal);
         }
      }

      throw new MatchError(var4);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$12(final long vid, final Tuple2 myScc, final long neighborScc) {
      return new Tuple2.mcJZ.sp(scala.math.package..MODULE$.min(myScc._1$mcJ$sp(), neighborScc), myScc._2$mcZ$sp());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$run$15(final long vid, final Tuple2 myScc, final boolean existsSameColorFinalNeighbor) {
      boolean isColorRoot = vid == myScc._1$mcJ$sp();
      return new Tuple2.mcJZ.sp(myScc._1$mcJ$sp(), myScc._2$mcZ$sp() || isColorRoot || existsSameColorFinalNeighbor);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$run$17(final boolean final1, final boolean final2) {
      return final1 || final2;
   }

   private StronglyConnectedComponents$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
