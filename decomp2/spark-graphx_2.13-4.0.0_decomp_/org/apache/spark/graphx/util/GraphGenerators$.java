package org.apache.spark.graphx.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Edge$mcD$sp;
import org.apache.spark.graphx.Edge$mcI$sp;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Graph$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Set;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction2;
import scala.util.Random;

public final class GraphGenerators$ implements Logging {
   public static final GraphGenerators$ MODULE$ = new GraphGenerators$();
   private static final double RMATa;
   private static final double RMATb;
   private static final double RMATd;
   private static final double RMATc;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      RMATa = 0.45;
      RMATb = 0.15;
      RMATd = (double)0.25F;
      RMATc = 0.15;
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

   public double RMATa() {
      return RMATa;
   }

   public double RMATb() {
      return RMATb;
   }

   public double RMATd() {
      return RMATd;
   }

   public Graph logNormalGraph(final SparkContext sc, final int numVertices, final int numEParts, final double mu, final double sigma, final long seed) {
      int evalNumEParts = numEParts == 0 ? sc.defaultParallelism() : numEParts;
      Random seedRand = seed == -1L ? new Random() : new Random(seed);
      int seed1 = seedRand.nextInt();
      int seed2 = seedRand.nextInt();
      RDD vertices = sc.parallelize(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numVertices), evalNumEParts, scala.reflect.ClassTag..MODULE$.Int()).map((src) -> $anonfun$logNormalGraph$1(mu, sigma, numVertices, seed1, BoxesRunTime.unboxToInt(src)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      RDD edges = vertices.flatMap((x0$1) -> {
         if (x0$1 != null) {
            long src = x0$1._1$mcJ$sp();
            long degree = x0$1._2$mcJ$sp();
            return scala.Predef..MODULE$.wrapRefArray(MODULE$.generateRandomEdges((int)src, (int)degree, numVertices, (long)seed2 ^ src));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Edge.class));
      return Graph$.MODULE$.apply(vertices, edges, BoxesRunTime.boxToLong(0L), Graph$.MODULE$.apply$default$4(), Graph$.MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.Int());
   }

   public int logNormalGraph$default$3() {
      return 0;
   }

   public double logNormalGraph$default$4() {
      return (double)4.0F;
   }

   public double logNormalGraph$default$5() {
      return 1.3;
   }

   public long logNormalGraph$default$6() {
      return -1L;
   }

   public double RMATc() {
      return RMATc;
   }

   public Edge[] generateRandomEdges(final int src, final int numEdges, final int maxVertexId, final long seed) {
      Random rand = seed == -1L ? new Random() : new Random(seed);
      return (Edge[])scala.Array..MODULE$.fill(numEdges, () -> new Edge$mcI$sp((long)src, (long)rand.nextInt(maxVertexId), 1), scala.reflect.ClassTag..MODULE$.apply(Edge.class));
   }

   public long generateRandomEdges$default$4() {
      return -1L;
   }

   public int sampleLogNormal(final double mu, final double sigma, final int maxVal, final long seed) {
      Random rand = seed == -1L ? new Random() : new Random(seed);

      double X;
      double Z;
      for(X = (double)maxVal; X >= (double)maxVal; X = scala.math.package..MODULE$.exp(mu + sigma * Z)) {
         Z = rand.nextGaussian();
      }

      return (int)scala.math.package..MODULE$.floor(X);
   }

   public long sampleLogNormal$default$4() {
      return -1L;
   }

   public Graph rmatGraph(final SparkContext sc, final int requestedNumVertices, final int numEdges) {
      int numVertices = (int)scala.math.package..MODULE$.round(scala.math.package..MODULE$.pow((double)2.0F, scala.math.package..MODULE$.ceil(scala.math.package..MODULE$.log((double)requestedNumVertices) / scala.math.package..MODULE$.log((double)2.0F))));
      int numEdgesUpperBound = (int)scala.math.package..MODULE$.pow((double)2.0F, (double)2 * (scala.math.package..MODULE$.log((double)numVertices) / scala.math.package..MODULE$.log((double)2.0F) - (double)1));
      if (numEdgesUpperBound < numEdges) {
         throw new IllegalArgumentException("numEdges must be <= " + numEdgesUpperBound + " but was " + numEdges);
      } else {
         Set edges;
         for(edges = (Set)scala.collection.mutable.Set..MODULE$.empty(); edges.size() < numEdges; edges.$plus$eq(this.addEdge(numVertices))) {
            if (edges.size() % 100 == 0) {
               this.logDebug((Function0)(() -> edges.size() + " edges"));
            }
         }

         return this.outDegreeFromEdges(sc.parallelize(edges.toList(), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.apply(Edge.class)), scala.reflect.ClassTag..MODULE$.Int());
      }
   }

   private Graph outDegreeFromEdges(final RDD edges, final ClassTag evidence$1) {
      RDD vertices = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(edges.flatMap((edge) -> new scala.collection.immutable..colon.colon(new Tuple2.mcJI.sp(edge.srcId(), 1), scala.collection.immutable.Nil..MODULE$), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.Long..MODULE$).reduceByKey((JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2).map((x0$1) -> {
         if (x0$1 != null) {
            long vid = x0$1._1$mcJ$sp();
            int degree = x0$1._2$mcI$sp();
            return new Tuple2.mcJI.sp(vid, degree);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return Graph$.MODULE$.apply(vertices, edges, BoxesRunTime.boxToInteger(0), Graph$.MODULE$.apply$default$4(), Graph$.MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.Int(), evidence$1);
   }

   private Edge addEdge(final int numVertices) {
      int v = (int)scala.math.package..MODULE$.round((double)((float)numVertices) / (double)2.0F);
      Tuple2 var5 = this.chooseCell(v, v, v);
      if (var5 != null) {
         int src = var5._1$mcI$sp();
         int dst = var5._2$mcI$sp();
         Tuple2.mcII.sp var4 = new Tuple2.mcII.sp(src, dst);
         int src = ((Tuple2)var4)._1$mcI$sp();
         int dst = ((Tuple2)var4)._2$mcI$sp();
         return new Edge$mcI$sp((long)src, (long)dst, 1);
      } else {
         throw new MatchError(var5);
      }
   }

   private Tuple2 chooseCell(final int x, final int y, final int t) {
      while(t > 1) {
         int newT = (int)scala.math.package..MODULE$.round((double)((float)t) / (double)2.0F);
         int var6 = this.pickQuadrant(this.RMATa(), this.RMATb(), this.RMATc(), this.RMATd());
         switch (var6) {
            case 0:
               t = newT;
               y = y;
               x = x;
               break;
            case 1:
               int var7 = x + newT;
               t = newT;
               y = y;
               x = var7;
               break;
            case 2:
               int var8 = y + newT;
               t = newT;
               y = var8;
               x = x;
               break;
            case 3:
               int var10000 = x + newT;
               int var10001 = y + newT;
               t = newT;
               y = var10001;
               x = var10000;
               break;
            default:
               throw new MatchError(BoxesRunTime.boxToInteger(var6));
         }
      }

      return new Tuple2.mcII.sp(x, y);
   }

   private int pickQuadrant(final double a, final double b, final double c, final double d) {
      if (a + b + c + d != (double)1.0F) {
         throw new IllegalArgumentException("R-MAT probability parameters sum to " + (a + b + c + d) + ", should sum to 1.0");
      } else {
         Random rand = new Random();
         double result = rand.nextDouble();
         if (result < a) {
            return 0;
         } else if (result >= a && result < a + b) {
            return 1;
         } else {
            return result >= a + b && result < a + b + c ? 2 : 3;
         }
      }
   }

   public Graph gridGraph(final SparkContext sc, final int rows, final int cols) {
      RDD vertices = sc.parallelize(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), rows), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.Int()).flatMap((r) -> $anonfun$gridGraph$1(cols, BoxesRunTime.unboxToInt(r)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      RDD edges = vertices.flatMap((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var5 = (Tuple2)x0$1._2();
            if (var5 != null) {
               int r = var5._1$mcI$sp();
               int c = var5._2$mcI$sp();
               return (Seq)((SeqOps)(r + 1 < rows ? new scala.collection.immutable..colon.colon(new Tuple2.mcJJ.sp(sub2ind$1(r, c, cols), sub2ind$1(r + 1, c, cols)), scala.collection.immutable.Nil..MODULE$) : scala.package..MODULE$.Seq().empty())).$plus$plus((IterableOnce)(c + 1 < cols ? new scala.collection.immutable..colon.colon(new Tuple2.mcJJ.sp(sub2ind$1(r, c, cols), sub2ind$1(r, c + 1, cols)), scala.collection.immutable.Nil..MODULE$) : scala.package..MODULE$.Seq().empty()));
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).map((x0$2) -> {
         if (x0$2 != null) {
            long src = x0$2._1$mcJ$sp();
            long dst = x0$2._2$mcJ$sp();
            return new Edge$mcD$sp(src, dst, (double)1.0F);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Edge.class));
      return Graph$.MODULE$.apply(vertices, edges, Graph$.MODULE$.apply$default$3(), Graph$.MODULE$.apply$default$4(), Graph$.MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.Double());
   }

   public Graph starGraph(final SparkContext sc, final int nverts) {
      RDD edges = sc.parallelize(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), nverts), sc.parallelize$default$2(), scala.reflect.ClassTag..MODULE$.Int()).map((vid) -> $anonfun$starGraph$1(BoxesRunTime.unboxToInt(vid)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return Graph$.MODULE$.fromEdgeTuples(edges, BoxesRunTime.boxToInteger(1), Graph$.MODULE$.fromEdgeTuples$default$3(), Graph$.MODULE$.fromEdgeTuples$default$4(), Graph$.MODULE$.fromEdgeTuples$default$5(), scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$logNormalGraph$1(final double mu$1, final double sigma$1, final int numVertices$1, final int seed1$1, final int src) {
      return new Tuple2.mcJJ.sp((long)src, (long)MODULE$.sampleLogNormal(mu$1, sigma$1, numVertices$1, (long)(seed1$1 ^ src)));
   }

   private static final long sub2ind$1(final int r, final int c, final int cols$1) {
      return (long)(r * cols$1 + c);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$gridGraph$2(final int r$1, final int cols$1, final int c) {
      return new Tuple2(BoxesRunTime.boxToLong(sub2ind$1(r$1, c, cols$1)), new Tuple2.mcII.sp(r$1, c));
   }

   // $FF: synthetic method
   public static final IndexedSeq $anonfun$gridGraph$1(final int cols$1, final int r) {
      return .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), cols$1).map((c) -> $anonfun$gridGraph$2(r, cols$1, BoxesRunTime.unboxToInt(c)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$starGraph$1(final int vid) {
      return new Tuple2.mcJJ.sp((long)vid, 0L);
   }

   private GraphGenerators$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
