package org.apache.spark.graphx.lib;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeDirection$;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Pregel$;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction2;

public final class ConnectedComponents$ {
   public static final ConnectedComponents$ MODULE$ = new ConnectedComponents$();

   public Graph run(final Graph graph, final int maxIterations, final ClassTag evidence$1, final ClassTag evidence$2) {
      .MODULE$.require(maxIterations > 0, () -> "Maximum of iterations must be greater than 0, but got " + maxIterations);
      Function2 x$1 = (x0$1, x1$1) -> BoxesRunTime.boxToLong($anonfun$run$2(BoxesRunTime.unboxToLong(x0$1), x1$1));
      ClassTag x$2 = scala.reflect.ClassTag..MODULE$.apply(Long.TYPE);
      Null x$3 = graph.mapVertices$default$3(x$1);
      Graph ccGraph = graph.mapVertices(x$1, x$2, (scala..eq.colon.eq)null);
      long initialMessage = Long.MAX_VALUE;
      Graph pregelGraph = Pregel$.MODULE$.apply(ccGraph, BoxesRunTime.boxToLong(initialMessage), maxIterations, EdgeDirection$.MODULE$.Either(), (id, attr, msg) -> BoxesRunTime.boxToLong($anonfun$run$3(BoxesRunTime.unboxToLong(id), BoxesRunTime.unboxToLong(attr), BoxesRunTime.unboxToLong(msg))), (edge) -> sendMessage$1(edge), (JFunction2.mcJJJ.sp)(a, b) -> scala.math.package..MODULE$.min(a, b), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), evidence$2, scala.reflect.ClassTag..MODULE$.Long());
      ccGraph.unpersist(ccGraph.unpersist$default$1());
      return pregelGraph;
   }

   public Graph run(final Graph graph, final ClassTag evidence$3, final ClassTag evidence$4) {
      return this.run(graph, Integer.MAX_VALUE, evidence$3, evidence$4);
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

   private static final Iterator sendMessage$1(final EdgeTriplet edge) {
      if (BoxesRunTime.unboxToLong(edge.srcAttr()) < BoxesRunTime.unboxToLong(edge.dstAttr())) {
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcJJ.sp(edge.dstId(), BoxesRunTime.unboxToLong(edge.srcAttr()))})));
      } else {
         return BoxesRunTime.unboxToLong(edge.srcAttr()) > BoxesRunTime.unboxToLong(edge.dstAttr()) ? scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcJJ.sp(edge.srcId(), BoxesRunTime.unboxToLong(edge.dstAttr()))}))) : scala.package..MODULE$.Iterator().empty();
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$run$3(final long id, final long attr, final long msg) {
      return scala.math.package..MODULE$.min(attr, msg);
   }

   private ConnectedComponents$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
