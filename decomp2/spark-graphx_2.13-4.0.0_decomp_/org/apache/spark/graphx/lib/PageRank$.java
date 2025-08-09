package org.apache.spark.graphx.lib;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.Vector;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.EdgeDirection;
import org.apache.spark.graphx.EdgeDirection$;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Graph$;
import org.apache.spark.graphx.Pregel$;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

public final class PageRank$ implements Logging {
   public static final PageRank$ MODULE$ = new PageRank$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Graph run(final Graph graph, final int numIter, final double resetProb, final ClassTag evidence$1, final ClassTag evidence$2) {
      return this.runWithOptions(graph, numIter, resetProb, .MODULE$, evidence$1, evidence$2);
   }

   public double run$default$3() {
      return 0.15;
   }

   private Graph runUpdate(final Graph rankGraph, final boolean personalized, final double resetProb, final long src) {
      VertexRDD rankUpdates = rankGraph.aggregateMessages((ctx) -> {
         $anonfun$runUpdate$1(ctx);
         return BoxedUnit.UNIT;
      }, (JFunction2.mcDDD.sp)(x$1, x$2) -> x$1 + x$2, TripletFields.Src, scala.reflect.ClassTag..MODULE$.Double());
      Function2 rPrb = personalized ? (srcx, id) -> resetProb * delta$1(srcx, id) : (srcx, id) -> resetProb;
      return rankGraph.outerJoinVertices(rankUpdates, (id, oldRank, msgSumOpt) -> BoxesRunTime.boxToDouble($anonfun$runUpdate$5(rPrb, src, resetProb, BoxesRunTime.unboxToLong(id), BoxesRunTime.unboxToDouble(oldRank), msgSumOpt)), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double(), scala..less.colon.less..MODULE$.refl());
   }

   public Graph runWithOptions(final Graph graph, final int numIter, final double resetProb, final Option srcId, final ClassTag evidence$3, final ClassTag evidence$4) {
      return this.runWithOptions(graph, numIter, resetProb, srcId, true, evidence$3, evidence$4);
   }

   public Graph runWithOptions(final Graph graph, final int numIter, final double resetProb, final Option srcId, final boolean normalized, final ClassTag evidence$5, final ClassTag evidence$6) {
      scala.Predef..MODULE$.require(numIter > 0, () -> "Number of iterations must be greater than 0, but got " + numIter);
      scala.Predef..MODULE$.require(resetProb >= (double)0 && resetProb <= (double)1, () -> "Random reset probability must belong to [0, 1], but got " + resetProb);
      boolean personalized = srcId.isDefined();
      long src = BoxesRunTime.unboxToLong(srcId.getOrElse((JFunction0.mcJ.sp)() -> -1L));
      VertexRDD x$1 = Graph$.MODULE$.graphToGraphOps(graph, evidence$5, evidence$6).outDegrees();
      Function3 x$2 = (vid, vdata, deg) -> BoxesRunTime.boxToInteger($anonfun$runWithOptions$4(BoxesRunTime.unboxToLong(vid), vdata, deg));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Int();
      ClassTag x$4 = scala.reflect.ClassTag..MODULE$.Int();
      Null x$5 = graph.outerJoinVertices$default$5(x$1, x$2);
      Graph qual$1 = graph.outerJoinVertices(x$1, x$2, x$3, x$4, (scala..eq.colon.eq)null).mapTriplets((Function1)((e) -> BoxesRunTime.boxToDouble($anonfun$runWithOptions$6(e))), TripletFields.Src, scala.reflect.ClassTag..MODULE$.Double());
      Function2 x$6 = (id, attr) -> id != src && personalized ? (double)0.0F : (double)1.0F;
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.Double();
      Null x$8 = qual$1.mapVertices$default$3(x$6);
      Graph rankGraph = qual$1.mapVertices(x$6, x$7, (scala..eq.colon.eq)null);
      IntRef iteration = IntRef.create(0);

      for(Graph prevRankGraph = null; iteration.elem < numIter; ++iteration.elem) {
         rankGraph.cache();
         prevRankGraph = rankGraph;
         rankGraph = this.runUpdate(rankGraph, personalized, resetProb, src);
         rankGraph.cache();
         rankGraph.edges().foreachPartition((x) -> {
            $anonfun$runWithOptions$8(x);
            return BoxedUnit.UNIT;
         });
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"PageRank finished iteration ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iteration.elem))})))));
         VertexRDD qual$2 = prevRankGraph.vertices();
         boolean x$9 = qual$2.unpersist$default$1();
         qual$2.unpersist(x$9);
         EdgeRDD qual$3 = prevRankGraph.edges();
         boolean x$10 = qual$3.unpersist$default$1();
         qual$3.unpersist(x$10);
      }

      return normalized ? this.normalizeRankSum(rankGraph, personalized) : rankGraph;
   }

   public double runWithOptions$default$3() {
      return 0.15;
   }

   public Option runWithOptions$default$4() {
      return .MODULE$;
   }

   public Graph runWithOptionsWithPreviousPageRank(final Graph graph, final int numIter, final double resetProb, final Option srcId, final Graph preRankGraph, final ClassTag evidence$7, final ClassTag evidence$8) {
      return this.runWithOptionsWithPreviousPageRank(graph, numIter, resetProb, srcId, true, preRankGraph, evidence$7, evidence$8);
   }

   public Graph runWithOptionsWithPreviousPageRank(final Graph graph, final int numIter, final double resetProb, final Option srcId, final boolean normalized, final Graph preRankGraph, final ClassTag evidence$9, final ClassTag evidence$10) {
      scala.Predef..MODULE$.require(numIter > 0, () -> "Number of iterations must be greater than 0, but got " + numIter);
      scala.Predef..MODULE$.require(resetProb >= (double)0 && resetProb <= (double)1, () -> "Random reset probability must belong to [0, 1], but got " + resetProb);
      long graphVertices = Graph$.MODULE$.graphToGraphOps(graph, evidence$9, evidence$10).numVertices();
      long prePageRankVertices = Graph$.MODULE$.graphToGraphOps(preRankGraph, scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double()).numVertices();
      scala.Predef..MODULE$.require(graphVertices == prePageRankVertices, () -> "Graph and previous pageRankGraph must have the same number of vertices but got " + graphVertices + " and " + prePageRankVertices);
      boolean personalized = srcId.isDefined();
      long src = BoxesRunTime.unboxToLong(srcId.getOrElse((JFunction0.mcJ.sp)() -> -1L));
      Graph rankGraph = preRankGraph;
      IntRef iteration = IntRef.create(0);

      for(Graph prevRankGraph = null; iteration.elem < numIter; ++iteration.elem) {
         rankGraph.cache();
         prevRankGraph = rankGraph;
         rankGraph = this.runUpdate(rankGraph, personalized, resetProb, src);
         rankGraph.cache();
         rankGraph.edges().foreachPartition((x) -> {
            $anonfun$runWithOptionsWithPreviousPageRank$5(x);
            return BoxedUnit.UNIT;
         });
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"PageRank finished iteration ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iteration.elem))})))));
         VertexRDD qual$1 = prevRankGraph.vertices();
         boolean x$1 = qual$1.unpersist$default$1();
         qual$1.unpersist(x$1);
         EdgeRDD qual$2 = prevRankGraph.edges();
         boolean x$2 = qual$2.unpersist$default$1();
         qual$2.unpersist(x$2);
      }

      return normalized ? this.normalizeRankSum(rankGraph, personalized) : rankGraph;
   }

   public Graph runParallelPersonalizedPageRank(final Graph graph, final int numIter, final double resetProb, final long[] sources, final ClassTag evidence$11, final ClassTag evidence$12) {
      scala.Predef..MODULE$.require(numIter > 0, () -> "Number of iterations must be greater than 0, but got " + numIter);
      scala.Predef..MODULE$.require(resetProb >= (double)0 && resetProb <= (double)1, () -> "Random reset probability must belong to [0, 1], but got " + resetProb);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.longArrayOps(sources)), () -> {
         ArraySeq.ofLong var10000 = scala.Predef..MODULE$.wrapLongArray(sources);
         return "The list of sources must be non-empty, but got " + var10000.mkString("[", ",", "]");
      });
      Vector zero = org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(sources.length, scala.collection.immutable.Nil..MODULE$).asBreeze();
      scala.collection.immutable.Map sourcesInitMap = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.longArrayOps(sources))), (x0$1) -> {
         if (x0$1 != null) {
            long vid = x0$1._1$mcJ$sp();
            int i = x0$1._2$mcI$sp();
            Vector v = org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(sources.length, new int[]{i}, new double[]{(double)1.0F}).asBreeze();
            return new Tuple2(BoxesRunTime.boxToLong(vid), v);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      SparkContext sc = graph.vertices().sparkContext();
      Broadcast sourcesInitMapBC = sc.broadcast(sourcesInitMap, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class));
      VertexRDD x$1 = Graph$.MODULE$.graphToGraphOps(graph, evidence$11, evidence$12).outDegrees();
      Function3 x$2 = (vid, vdata, deg) -> BoxesRunTime.boxToInteger($anonfun$runParallelPersonalizedPageRank$5(BoxesRunTime.unboxToLong(vid), vdata, deg));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Int();
      ClassTag x$4 = scala.reflect.ClassTag..MODULE$.Int();
      Null x$5 = graph.outerJoinVertices$default$5(x$1, x$2);
      Graph qual$1 = graph.outerJoinVertices(x$1, x$2, x$3, x$4, (scala..eq.colon.eq)null).mapTriplets((Function1)((e) -> BoxesRunTime.boxToDouble($anonfun$runParallelPersonalizedPageRank$7(e))), TripletFields.Src, scala.reflect.ClassTag..MODULE$.Double());
      Function2 x$6 = (vid, x$3x) -> $anonfun$runParallelPersonalizedPageRank$8(sourcesInitMapBC, zero, BoxesRunTime.unboxToLong(vid), BoxesRunTime.unboxToInt(x$3x));
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Vector.class);
      Null x$8 = qual$1.mapVertices$default$3(x$6);
      Graph rankGraph = qual$1.mapVertices(x$6, x$7, (scala..eq.colon.eq)null);

      for(IntRef i = IntRef.create(0); i.elem < numIter; ++i.elem) {
         Graph prevRankGraph = rankGraph;
         VertexRDD rankUpdates = rankGraph.aggregateMessages((ctx) -> {
            $anonfun$runParallelPersonalizedPageRank$10(ctx);
            return BoxedUnit.UNIT;
         }, (a, b) -> (Vector)a.$plus$colon$plus(b, breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd()), TripletFields.Src, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         rankGraph = rankGraph.outerJoinVertices(rankUpdates, (vid, oldRank, msgSumOpt) -> $anonfun$runParallelPersonalizedPageRank$12(zero, resetProb, sourcesInitMapBC, BoxesRunTime.unboxToLong(vid), oldRank, msgSumOpt), scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala..less.colon.less..MODULE$.refl()).cache();
         rankGraph.edges().foreachPartition((x$4x) -> {
            $anonfun$runParallelPersonalizedPageRank$14(x$4x);
            return BoxedUnit.UNIT;
         });
         prevRankGraph.vertices().unpersist(prevRankGraph.vertices().unpersist$default$1());
         prevRankGraph.edges().unpersist(prevRankGraph.edges().unpersist$default$1());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Parallel Personalized PageRank finished iteration ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(i.elem))})))));
      }

      Vector rankSums = (Vector)org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rankGraph.vertices(), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala.math.Ordering.Long..MODULE$).values().fold(zero, (x$5x, x$6x) -> (Vector)x$5x.$plus$colon$plus(x$6x, breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd()));
      Function2 x$9 = (vid, attr) -> $anonfun$runParallelPersonalizedPageRank$17(rankSums, BoxesRunTime.unboxToLong(vid), attr);
      ClassTag x$10 = scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.ml.linalg.Vector.class);
      Null x$11 = rankGraph.mapVertices$default$3(x$9);
      return rankGraph.mapVertices(x$9, x$10, (scala..eq.colon.eq)null);
   }

   public double runParallelPersonalizedPageRank$default$3() {
      return 0.15;
   }

   public Graph runUntilConvergence(final Graph graph, final double tol, final double resetProb, final ClassTag evidence$13, final ClassTag evidence$14) {
      return this.runUntilConvergenceWithOptions(graph, tol, resetProb, this.runUntilConvergenceWithOptions$default$4(), evidence$13, evidence$14);
   }

   public double runUntilConvergence$default$3() {
      return 0.15;
   }

   public Graph runUntilConvergenceWithOptions(final Graph graph, final double tol, final double resetProb, final Option srcId, final ClassTag evidence$15, final ClassTag evidence$16) {
      scala.Predef..MODULE$.require(tol >= (double)0, () -> "Tolerance must be no less than 0, but got " + tol);
      scala.Predef..MODULE$.require(resetProb >= (double)0 && resetProb <= (double)1, () -> "Random reset probability must belong to [0, 1], but got " + resetProb);
      boolean personalized = srcId.isDefined();
      long src = BoxesRunTime.unboxToLong(srcId.getOrElse((JFunction0.mcJ.sp)() -> -1L));
      VertexRDD x$1 = Graph$.MODULE$.graphToGraphOps(graph, evidence$15, evidence$16).outDegrees();
      Function3 x$2 = (vid, vdata, deg) -> BoxesRunTime.boxToInteger($anonfun$runUntilConvergenceWithOptions$4(BoxesRunTime.unboxToLong(vid), vdata, deg));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Int();
      ClassTag x$4 = scala.reflect.ClassTag..MODULE$.Int();
      Null x$5 = graph.outerJoinVertices$default$5(x$1, x$2);
      Graph qual$1 = graph.outerJoinVertices(x$1, x$2, x$3, x$4, (scala..eq.colon.eq)null).mapTriplets((e) -> BoxesRunTime.boxToDouble($anonfun$runUntilConvergenceWithOptions$6(e)), scala.reflect.ClassTag..MODULE$.Double());
      Function2 x$6 = (id, attr) -> $anonfun$runUntilConvergenceWithOptions$7(src, BoxesRunTime.unboxToLong(id), BoxesRunTime.unboxToInt(attr));
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = qual$1.mapVertices$default$3(x$6);
      Graph pagerankGraph = qual$1.mapVertices(x$6, x$7, (scala..eq.colon.eq)null).cache();
      double initialMessage = personalized ? (double)0.0F : resetProb / ((double)1.0F - resetProb);
      Function3 vp = personalized ? (id, attr, msgSum) -> $anonfun$runUntilConvergenceWithOptions$8(resetProb, BoxesRunTime.unboxToLong(id), attr, BoxesRunTime.unboxToDouble(msgSum)) : (id, attr, msgSum) -> $anonfun$runUntilConvergenceWithOptions$9(resetProb, BoxesRunTime.unboxToLong(id), attr, BoxesRunTime.unboxToDouble(msgSum));
      EdgeDirection x$11 = EdgeDirection$.MODULE$.Out();
      int x$12 = Pregel$.MODULE$.apply$default$3();
      Function1 x$14 = (edge) -> sendMessage$1(edge, tol);
      Function2 x$15 = (a, b) -> messageCombiner$1(a, b);
      Graph qual$2 = Pregel$.MODULE$.apply(pagerankGraph, BoxesRunTime.boxToDouble(initialMessage), x$12, x$11, vp, x$14, x$15, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double());
      Function2 x$16 = (vid, attr) -> BoxesRunTime.boxToDouble($anonfun$runUntilConvergenceWithOptions$12(BoxesRunTime.unboxToLong(vid), attr));
      ClassTag x$17 = scala.reflect.ClassTag..MODULE$.Double();
      Null x$18 = qual$2.mapVertices$default$3(x$16);
      Graph rankGraph = qual$2.mapVertices(x$16, x$17, (scala..eq.colon.eq)null);
      return this.normalizeRankSum(rankGraph, personalized);
   }

   public double runUntilConvergenceWithOptions$default$3() {
      return 0.15;
   }

   public Option runUntilConvergenceWithOptions$default$4() {
      return .MODULE$;
   }

   private Graph normalizeRankSum(final Graph rankGraph, final boolean personalized) {
      double rankSum = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rankGraph.vertices(), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values()).sum();
      if (personalized) {
         return rankGraph.mapVertices((JFunction2.mcDJD.sp)(id, rank) -> rank / rankSum, scala.reflect.ClassTag..MODULE$.Double(), scala..less.colon.less..MODULE$.refl());
      } else {
         long numVertices = Graph$.MODULE$.graphToGraphOps(rankGraph, scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.Double()).numVertices();
         double correctionFactor = (double)numVertices / rankSum;
         return rankGraph.mapVertices((JFunction2.mcDJD.sp)(id, rank) -> rank * correctionFactor, scala.reflect.ClassTag..MODULE$.Double(), scala..less.colon.less..MODULE$.refl());
      }
   }

   private static final double delta$1(final long u, final long v) {
      return u == v ? (double)1.0F : (double)0.0F;
   }

   // $FF: synthetic method
   public static final void $anonfun$runUpdate$1(final EdgeContext ctx) {
      ctx.sendToDst(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(ctx.srcAttr()) * BoxesRunTime.unboxToDouble(ctx.attr())));
   }

   // $FF: synthetic method
   public static final double $anonfun$runUpdate$5(final Function2 rPrb$1, final long src$1, final double resetProb$1, final long id, final double oldRank, final Option msgSumOpt) {
      return rPrb$1.apply$mcDJJ$sp(src$1, id) + ((double)1.0F - resetProb$1) * BoxesRunTime.unboxToDouble(msgSumOpt.getOrElse((JFunction0.mcD.sp)() -> (double)0.0F));
   }

   // $FF: synthetic method
   public static final int $anonfun$runWithOptions$4(final long vid, final Object vdata, final Option deg) {
      return BoxesRunTime.unboxToInt(deg.getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   public static final double $anonfun$runWithOptions$6(final EdgeTriplet e) {
      return (double)1.0F / (double)BoxesRunTime.unboxToInt(e.srcAttr());
   }

   // $FF: synthetic method
   public static final void $anonfun$runWithOptions$8(final Iterator x) {
   }

   // $FF: synthetic method
   public static final void $anonfun$runWithOptionsWithPreviousPageRank$5(final Iterator x) {
   }

   // $FF: synthetic method
   public static final int $anonfun$runParallelPersonalizedPageRank$5(final long vid, final Object vdata, final Option deg) {
      return BoxesRunTime.unboxToInt(deg.getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   public static final double $anonfun$runParallelPersonalizedPageRank$7(final EdgeTriplet e) {
      return (double)1.0F / (double)BoxesRunTime.unboxToInt(e.srcAttr());
   }

   // $FF: synthetic method
   public static final Vector $anonfun$runParallelPersonalizedPageRank$8(final Broadcast sourcesInitMapBC$1, final Vector zero$1, final long vid, final int x$3) {
      return (Vector)((MapOps)sourcesInitMapBC$1.value()).getOrElse(BoxesRunTime.boxToLong(vid), () -> zero$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$runParallelPersonalizedPageRank$10(final EdgeContext ctx) {
      ctx.sendToDst(((ImmutableNumericOps)ctx.srcAttr()).$times$colon$times(ctx.attr(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar()));
   }

   // $FF: synthetic method
   public static final Vector $anonfun$runParallelPersonalizedPageRank$12(final Vector zero$1, final double resetProb$4, final Broadcast sourcesInitMapBC$1, final long vid, final Vector oldRank, final Option msgSumOpt) {
      Vector popActivations = (Vector)((ImmutableNumericOps)msgSumOpt.getOrElse(() -> zero$1)).$times$colon$times(BoxesRunTime.boxToDouble((double)1.0F - resetProb$4), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar());
      Vector resetActivations = ((MapOps)sourcesInitMapBC$1.value()).contains(BoxesRunTime.boxToLong(vid)) ? (Vector)((ImmutableNumericOps)((MapOps)sourcesInitMapBC$1.value()).apply(BoxesRunTime.boxToLong(vid))).$times$colon$times(BoxesRunTime.boxToDouble(resetProb$4), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpMulScalar()) : zero$1;
      return (Vector)popActivations.$plus$colon$plus(resetActivations, breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_idempotent_Double_OpAdd());
   }

   // $FF: synthetic method
   public static final void $anonfun$runParallelPersonalizedPageRank$14(final Iterator x$4) {
   }

   // $FF: synthetic method
   public static final org.apache.spark.ml.linalg.Vector $anonfun$runParallelPersonalizedPageRank$17(final Vector rankSums$1, final long vid, final Vector attr) {
      return org.apache.spark.ml.linalg.Vectors..MODULE$.fromBreeze((Vector)attr.$div$colon$div(rankSums$1, breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_V_eq_V_Double_OpDiv()));
   }

   // $FF: synthetic method
   public static final int $anonfun$runUntilConvergenceWithOptions$4(final long vid, final Object vdata, final Option deg) {
      return BoxesRunTime.unboxToInt(deg.getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   public static final double $anonfun$runUntilConvergenceWithOptions$6(final EdgeTriplet e) {
      return (double)1.0F / (double)BoxesRunTime.unboxToInt(e.srcAttr());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runUntilConvergenceWithOptions$7(final long src$3, final long id, final int attr) {
      return id == src$3 ? new Tuple2.mcDD.sp((double)0.0F, Double.NEGATIVE_INFINITY) : new Tuple2.mcDD.sp((double)0.0F, (double)0.0F);
   }

   private static final Tuple2 vertexProgram$1(final long id, final Tuple2 attr, final double msgSum, final double resetProb$5) {
      if (attr != null) {
         double oldPR = attr._1$mcD$sp();
         double lastDelta = attr._2$mcD$sp();
         Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(oldPR, lastDelta);
         double oldPR = ((Tuple2)var8)._1$mcD$sp();
         double var16 = ((Tuple2)var8)._2$mcD$sp();
         double newPR = oldPR + ((double)1.0F - resetProb$5) * msgSum;
         return new Tuple2.mcDD.sp(newPR, newPR - oldPR);
      } else {
         throw new MatchError(attr);
      }
   }

   private static final Tuple2 personalizedVertexProgram$1(final long id, final Tuple2 attr, final double msgSum, final double resetProb$5) {
      if (attr != null) {
         double oldPR = attr._1$mcD$sp();
         double lastDelta = attr._2$mcD$sp();
         Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(oldPR, lastDelta);
         double oldPR = ((Tuple2)var8)._1$mcD$sp();
         double lastDelta = ((Tuple2)var8)._2$mcD$sp();
         double newPR = lastDelta == Double.NEGATIVE_INFINITY ? (double)1.0F : oldPR + ((double)1.0F - resetProb$5) * msgSum;
         return new Tuple2.mcDD.sp(newPR, newPR - oldPR);
      } else {
         throw new MatchError(attr);
      }
   }

   private static final Iterator sendMessage$1(final EdgeTriplet edge, final double tol$1) {
      return ((Tuple2)edge.srcAttr())._2$mcD$sp() > tol$1 ? scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcJD.sp(edge.dstId(), ((Tuple2)edge.srcAttr())._2$mcD$sp() * edge.attr$mcD$sp())}))) : scala.package..MODULE$.Iterator().empty();
   }

   private static final double messageCombiner$1(final double a, final double b) {
      return a + b;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runUntilConvergenceWithOptions$8(final double resetProb$5, final long id, final Tuple2 attr, final double msgSum) {
      return personalizedVertexProgram$1(id, attr, msgSum, resetProb$5);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runUntilConvergenceWithOptions$9(final double resetProb$5, final long id, final Tuple2 attr, final double msgSum) {
      return vertexProgram$1(id, attr, msgSum, resetProb$5);
   }

   // $FF: synthetic method
   public static final double $anonfun$runUntilConvergenceWithOptions$12(final long vid, final Tuple2 attr) {
      return attr._1$mcD$sp();
   }

   private PageRank$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
