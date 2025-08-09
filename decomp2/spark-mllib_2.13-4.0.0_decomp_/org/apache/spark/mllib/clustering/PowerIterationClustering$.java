package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class PowerIterationClustering$ implements Logging, Serializable {
   public static final PowerIterationClustering$ MODULE$ = new PowerIterationClustering$();
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

   public Graph normalize(final Graph graph) {
      VertexRDD vD = graph.aggregateMessages((ctx) -> {
         $anonfun$normalize$1(ctx);
         return BoxedUnit.UNIT;
      }, (JFunction2.mcDDD.sp)(x$2, x$3) -> x$2 + x$3, TripletFields.EdgeOnly, .MODULE$.Double());
      return org.apache.spark.graphx.Graph..MODULE$.apply(vD, graph.edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.Double(), .MODULE$.Double()).mapTriplets((e) -> BoxesRunTime.boxToDouble($anonfun$normalize$3(e)), new TripletFields(true, false, true), .MODULE$.Double());
   }

   public Graph normalize(final RDD similarities) {
      RDD edges = similarities.flatMap((x0$1) -> {
         if (x0$1 != null) {
            long i = BoxesRunTime.unboxToLong(x0$1._1());
            long j = BoxesRunTime.unboxToLong(x0$1._2());
            double s = BoxesRunTime.unboxToDouble(x0$1._3());
            if (s < (double)0.0F) {
               throw new SparkException("Similarity must be nonnegative but found s(" + i + ", " + j + ") = " + s + ".");
            } else {
               return i != j ? new scala.collection.immutable..colon.colon(new Edge.mcD.sp(i, j, s), new scala.collection.immutable..colon.colon(new Edge.mcD.sp(j, i, s), scala.collection.immutable.Nil..MODULE$)) : scala.None..MODULE$;
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Edge.class));
      Graph gA = org.apache.spark.graphx.Graph..MODULE$.fromEdges(edges, BoxesRunTime.boxToDouble((double)0.0F), org.apache.spark.graphx.Graph..MODULE$.fromEdges$default$3(), org.apache.spark.graphx.Graph..MODULE$.fromEdges$default$4(), .MODULE$.Double(), .MODULE$.Double());
      VertexRDD vD = gA.aggregateMessages((ctx) -> {
         $anonfun$normalize$5(ctx);
         return BoxedUnit.UNIT;
      }, (JFunction2.mcDDD.sp)(x$4, x$5) -> x$4 + x$5, TripletFields.EdgeOnly, .MODULE$.Double());
      Graph graph = org.apache.spark.graphx.Graph..MODULE$.apply(vD, gA.edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.Double(), .MODULE$.Double()).mapTriplets((e) -> BoxesRunTime.boxToDouble($anonfun$normalize$7(e)), new TripletFields(true, false, true), .MODULE$.Double());
      this.materialize(graph);
      gA.unpersist(gA.unpersist$default$1());
      return graph;
   }

   public Graph randomInit(final Graph g) {
      RDD r = g.vertices().mapPartitionsWithIndex((part, iter) -> $anonfun$randomInit$1(BoxesRunTime.unboxToInt(part), iter), true, .MODULE$.apply(Tuple2.class)).cache();
      double sum = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(r, .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values().map((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.abs(x), .MODULE$.Double())).sum();
      RDD v0 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(r, .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).mapValues((JFunction1.mcDD.sp)(x) -> x / sum);
      Graph graph = org.apache.spark.graphx.Graph..MODULE$.apply(org.apache.spark.graphx.VertexRDD..MODULE$.apply(v0, .MODULE$.Double()), g.edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.Double(), .MODULE$.Double());
      this.materialize(graph);
      r.unpersist(r.unpersist$default$1());
      return graph;
   }

   public Graph initDegreeVector(final Graph g) {
      double sum = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(g.vertices(), .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values()).sum();
      VertexRDD v0 = g.vertices().mapValues((JFunction1.mcDD.sp)(x$6) -> x$6 / sum, .MODULE$.Double());
      Graph graph = org.apache.spark.graphx.Graph..MODULE$.apply(org.apache.spark.graphx.VertexRDD..MODULE$.apply(v0, .MODULE$.Double()), g.edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.Double(), .MODULE$.Double());
      this.materialize(graph);
      return graph;
   }

   public VertexRDD powerIter(final Graph g, final int maxIterations) {
      double tol = scala.math.package..MODULE$.max(1.0E-5 / (double)g.vertices().count(), 1.0E-8);
      DoubleRef prevDelta = DoubleRef.create(Double.MAX_VALUE);
      DoubleRef diffDelta = DoubleRef.create(Double.MAX_VALUE);
      ObjectRef curG = ObjectRef.create(g);
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), maxIterations).withFilter((JFunction1.mcZI.sp)(iter) -> scala.math.package..MODULE$.abs(diffDelta.elem) > tol).foreach((JFunction1.mcVI.sp)(iter) -> {
         MessageWithContext msgPrefix = MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Iteration ", ":"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iter))})));
         VertexRDD v = (VertexRDD)((Graph)curG.elem).aggregateMessages((ctx) -> {
            $anonfun$powerIter$3(ctx);
            return BoxedUnit.UNIT;
         }, (JFunction2.mcDDD.sp)(x$7, x$8) -> x$7 + x$8, new TripletFields(false, true, true), .MODULE$.Double()).cache();
         double norm = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(v, .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values().map((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.abs(x), .MODULE$.Double())).sum();
         MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msgPrefix.$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" norm(v) = ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NORM..MODULE$, BoxesRunTime.boxToDouble(norm))}))))));
         VertexRDD v1 = v.mapValues((JFunction1.mcDD.sp)(x) -> x / norm, .MODULE$.Double());
         double delta = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.graphx.Graph..MODULE$.graphToGraphOps((Graph)curG.elem, .MODULE$.Double(), .MODULE$.Double()).joinVertices(v1, (x0$1, x1$1, x2$1) -> BoxesRunTime.boxToDouble($anonfun$powerIter$8(BoxesRunTime.unboxToLong(x0$1), BoxesRunTime.unboxToDouble(x1$1), BoxesRunTime.unboxToDouble(x2$1))), .MODULE$.Double()).vertices(), .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values()).sum();
         MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msgPrefix.$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" delta = ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DELTA..MODULE$, BoxesRunTime.boxToDouble(delta))}))))));
         diffDelta.elem = scala.math.package..MODULE$.abs(delta - prevDelta.elem);
         MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msgPrefix.$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" diff(delta) = ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DIFF_DELTA..MODULE$, BoxesRunTime.boxToDouble(diffDelta.elem))}))))));
         if (scala.math.package..MODULE$.abs(diffDelta.elem) < tol) {
            double xTAx = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.graphx.Graph..MODULE$.graphToGraphOps((Graph)curG.elem, .MODULE$.Double(), .MODULE$.Double()).joinVertices(v, (x0$2, x1$2, x2$2) -> BoxesRunTime.boxToDouble($anonfun$powerIter$11(BoxesRunTime.unboxToLong(x0$2), BoxesRunTime.unboxToDouble(x1$2), BoxesRunTime.unboxToDouble(x2$2))), .MODULE$.Double()).vertices(), .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values()).sum();
            double xTx = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(((Graph)curG.elem).vertices().mapValues((JFunction1.mcDD.sp)(x) -> x * x, .MODULE$.Double()), .MODULE$.apply(Long.TYPE), .MODULE$.Double(), scala.math.Ordering.Long..MODULE$).values()).sum();
            double rayleigh = xTAx / xTx;
            if (scala.math.package..MODULE$.abs(norm - scala.math.package..MODULE$.abs(rayleigh)) > tol) {
               MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Power Iteration fail to converge. delta = ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DELTA..MODULE$, BoxesRunTime.boxToDouble(delta))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" difference delta = ", " and norm = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DIFF_DELTA..MODULE$, BoxesRunTime.boxToDouble(diffDelta.elem)), new MDC(org.apache.spark.internal.LogKeys.NORM..MODULE$, BoxesRunTime.boxToDouble(norm))}))))));
            }
         }

         VertexRDD qual$1 = ((Graph)curG.elem).vertices();
         boolean x$1 = qual$1.unpersist$default$1();
         qual$1.unpersist(x$1);
         EdgeRDD qual$2 = ((Graph)curG.elem).edges();
         boolean x$2 = qual$2.unpersist$default$1();
         qual$2.unpersist(x$2);
         curG.elem = org.apache.spark.graphx.Graph..MODULE$.apply(org.apache.spark.graphx.VertexRDD..MODULE$.apply(v1, .MODULE$.Double()), g.edges(), org.apache.spark.graphx.Graph..MODULE$.apply$default$3(), org.apache.spark.graphx.Graph..MODULE$.apply$default$4(), org.apache.spark.graphx.Graph..MODULE$.apply$default$5(), .MODULE$.Double(), .MODULE$.Double());
         MODULE$.materialize((Graph)curG.elem);
         v.unpersist(v.unpersist$default$1());
         prevDelta.elem = delta;
      });
      EdgeRDD qual$3 = ((Graph)curG.elem).edges();
      boolean x$3 = qual$3.unpersist$default$1();
      qual$3.unpersist(x$3);
      return ((Graph)curG.elem).vertices();
   }

   public VertexRDD kMeans(final VertexRDD v, final int k) {
      VertexRDD points = (VertexRDD)v.mapValues((x$9) -> $anonfun$kMeans$1(BoxesRunTime.unboxToDouble(x$9)), .MODULE$.apply(Vector.class)).cache();
      KMeansModel model = (new KMeans()).setK(k).setSeed(0L).run(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(points, .MODULE$.apply(Long.TYPE), .MODULE$.apply(Vector.class), scala.math.Ordering.Long..MODULE$).values());
      VertexRDD predict = points.mapValues((x$10) -> BoxesRunTime.boxToInteger($anonfun$kMeans$2(model, x$10)), .MODULE$.Int());
      points.unpersist(points.unpersist$default$1());
      return predict;
   }

   private void materialize(final Graph g) {
      g.edges().foreachPartition((x$11) -> {
         $anonfun$materialize$1(x$11);
         return BoxedUnit.UNIT;
      });
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PowerIterationClustering$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$normalize$1(final EdgeContext ctx) {
      long i = ctx.srcId();
      long j = ctx.dstId();
      double s = BoxesRunTime.unboxToDouble(ctx.attr());
      if (s < (double)0.0F) {
         throw new SparkException("Similarity must be nonnegative but found s(" + i + ", " + j + ") = " + s + ".");
      } else if (s > (double)0.0F) {
         ctx.sendToSrc(BoxesRunTime.boxToDouble(s));
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$normalize$3(final EdgeTriplet e) {
      return e.attr$mcD$sp() / scala.math.package..MODULE$.max(BoxesRunTime.unboxToDouble(e.srcAttr()), MLUtils$.MODULE$.EPSILON());
   }

   // $FF: synthetic method
   public static final void $anonfun$normalize$5(final EdgeContext ctx) {
      ctx.sendToSrc(ctx.attr());
   }

   // $FF: synthetic method
   public static final double $anonfun$normalize$7(final EdgeTriplet e) {
      return e.attr$mcD$sp() / scala.math.package..MODULE$.max(BoxesRunTime.unboxToDouble(e.srcAttr()), MLUtils$.MODULE$.EPSILON());
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$randomInit$1(final int part, final Iterator iter) {
      XORShiftRandom random = new XORShiftRandom((long)part);
      return iter.map((x0$1) -> {
         if (x0$1 != null) {
            long id = x0$1._1$mcJ$sp();
            return new Tuple2.mcJD.sp(id, random.nextGaussian());
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$powerIter$3(final EdgeContext ctx) {
      ctx.sendToSrc(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(ctx.attr()) * BoxesRunTime.unboxToDouble(ctx.dstAttr())));
   }

   // $FF: synthetic method
   public static final double $anonfun$powerIter$8(final long x0$1, final double x1$1, final double x2$1) {
      Tuple3 var8 = new Tuple3(BoxesRunTime.boxToLong(x0$1), BoxesRunTime.boxToDouble(x1$1), BoxesRunTime.boxToDouble(x2$1));
      if (var8 != null) {
         double x = BoxesRunTime.unboxToDouble(var8._2());
         double y = BoxesRunTime.unboxToDouble(var8._3());
         return scala.math.package..MODULE$.abs(x - y);
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$powerIter$11(final long x0$2, final double x1$2, final double x2$2) {
      Tuple3 var8 = new Tuple3(BoxesRunTime.boxToLong(x0$2), BoxesRunTime.boxToDouble(x1$2), BoxesRunTime.boxToDouble(x2$2));
      if (var8 != null) {
         double x = BoxesRunTime.unboxToDouble(var8._2());
         double y = BoxesRunTime.unboxToDouble(var8._3());
         return x * y;
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   public static final Vector $anonfun$kMeans$1(final double x$9) {
      return Vectors$.MODULE$.dense(x$9, (Seq)scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   public static final int $anonfun$kMeans$2(final KMeansModel model$1, final Vector x$10) {
      return model$1.predict(x$10);
   }

   // $FF: synthetic method
   public static final void $anonfun$materialize$1(final Iterator x$11) {
   }

   private PowerIterationClustering$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
