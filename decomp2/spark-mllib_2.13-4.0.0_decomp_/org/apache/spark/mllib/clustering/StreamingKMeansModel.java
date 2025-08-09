package org.apache.spark.mllib.clustering;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r4Aa\u0002\u0005\u0001'!Aa\u0004\u0001BC\u0002\u0013\u0005s\u0004\u0003\u00056\u0001\t\u0005\t\u0015!\u0003!\u0011!9\u0004A!b\u0001\n\u0003A\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u000b\u0001\u0003A\u0011A!\t\u000b!\u0003A\u0011A%\u0003)M#(/Z1nS:<7*T3b]Nlu\u000eZ3m\u0015\tI!\"\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!a\u0003\u0007\u0002\u000b5dG.\u001b2\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001)a\u0001\"!\u0006\f\u000e\u0003!I!a\u0006\u0005\u0003\u0017-kU-\u00198t\u001b>$W\r\u001c\t\u00033qi\u0011A\u0007\u0006\u000371\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003;i\u0011q\u0001T8hO&tw-\u0001\bdYV\u001cH/\u001a:DK:$XM]:\u0016\u0003\u0001\u00022!\t\u0013'\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#!B!se\u0006L\bCA\u0014+\u001b\u0005A#BA\u0015\u000b\u0003\u0019a\u0017N\\1mO&\u00111\u0006\u000b\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u0005i3\u0007\u0005\u0002/c5\tqF\u0003\u00021\u0019\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Iz#!B*j]\u000e,\u0017%\u0001\u001b\u0002\u000bEr#G\f\u0019\u0002\u001f\rdWo\u001d;fe\u000e+g\u000e^3sg\u0002B3AA\u00174\u00039\u0019G.^:uKJ<V-[4iiN,\u0012!\u000f\t\u0004C\u0011R\u0004CA\u0011<\u0013\ta$E\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u00075\u001a\u0014aD2mkN$XM],fS\u001eDGo\u001d\u0011)\u0007\u0011i3'\u0001\u0004=S:LGO\u0010\u000b\u0004\u0005\u000e+\u0005CA\u000b\u0001\u0011\u0015qR\u00011\u0001!Q\r\u0019Uf\r\u0005\u0006o\u0015\u0001\r!\u000f\u0015\u0004\u000b6\u001a\u0004fA\u0003.g\u00051Q\u000f\u001d3bi\u0016$BA\u0011&S)\")1J\u0002a\u0001\u0019\u0006!A-\u0019;b!\ri\u0005KJ\u0007\u0002\u001d*\u0011q\nD\u0001\u0004e\u0012$\u0017BA)O\u0005\r\u0011F\t\u0012\u0005\u0006'\u001a\u0001\rAO\u0001\fI\u0016\u001c\u0017-\u001f$bGR|'\u000fC\u0003V\r\u0001\u0007a+\u0001\u0005uS6,WK\\5u!\t9fL\u0004\u0002Y9B\u0011\u0011LI\u0007\u00025*\u00111LE\u0001\u0007yI|w\u000e\u001e \n\u0005u\u0013\u0013A\u0002)sK\u0012,g-\u0003\u0002`A\n11\u000b\u001e:j]\u001eT!!\u0018\u0012)\u0007\u0019i3\u0007K\u0002\u0001[M\u0002"
)
public class StreamingKMeansModel extends KMeansModel implements Logging {
   private final Vector[] clusterCenters;
   private final double[] clusterWeights;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Vector[] clusterCenters() {
      return this.clusterCenters;
   }

   public double[] clusterWeights() {
      return this.clusterWeights;
   }

   public StreamingKMeansModel update(final RDD data, final double decayFactor, final String timeUnit) {
      RDD closest = data.map((point) -> new Tuple2(BoxesRunTime.boxToInteger(this.predict(point)), new Tuple2(point, BoxesRunTime.boxToLong(1L))), .MODULE$.apply(Tuple2.class));
      int dim = this.clusterCenters()[0].size();
      Tuple2[] pointStats = (Tuple2[])org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(closest, .MODULE$.Int(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).aggregateByKey(new Tuple2((Object)null, BoxesRunTime.boxToLong(0L)), (p1, p2) -> mergeContribs$1(p1, p2), (p1, p2) -> mergeContribs$1(p1, p2), .MODULE$.apply(Tuple2.class)).collect();
      double var10000;
      switch (timeUnit == null ? 0 : timeUnit.hashCode()) {
         case -982754077:
            if (!"points".equals(timeUnit)) {
               throw new MatchError(timeUnit);
            }

            long numNewPoints = BoxesRunTime.unboxToLong(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])pointStats)).map((x0$1) -> BoxesRunTime.boxToLong($anonfun$update$4(x0$1))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
            var10000 = scala.math.package..MODULE$.pow(decayFactor, (double)numNewPoints);
            break;
         case -331743896:
            if ("batches".equals(timeUnit)) {
               var10000 = decayFactor;
               break;
            }

            throw new MatchError(timeUnit);
         default:
            throw new MatchError(timeUnit);
      }

      double discount = var10000;
      BLAS$.MODULE$.scal(discount, Vectors$.MODULE$.dense(this.clusterWeights()));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])pointStats), (x0$2) -> {
         $anonfun$update$5(this, x0$2);
         return BoxedUnit.UNIT;
      });
      Tuple2 var18 = (Tuple2)scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.clusterWeights())).zipWithIndex().maxBy((x$1) -> BoxesRunTime.boxToDouble($anonfun$update$7(x$1)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      if (var18 != null) {
         double maxWeight = var18._1$mcD$sp();
         int largest = var18._2$mcI$sp();
         Tuple2.mcDI.sp var17 = new Tuple2.mcDI.sp(maxWeight, largest);
         double maxWeight = ((Tuple2)var17)._1$mcD$sp();
         int largest = ((Tuple2)var17)._2$mcI$sp();
         Tuple2 var26 = (Tuple2)scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.clusterWeights())).zipWithIndex().minBy((x$3) -> BoxesRunTime.boxToDouble($anonfun$update$8(x$3)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
         if (var26 == null) {
            throw new MatchError(var26);
         } else {
            double minWeight = var26._1$mcD$sp();
            int smallest = var26._2$mcI$sp();
            Tuple2.mcDI.sp var25 = new Tuple2.mcDI.sp(minWeight, smallest);
            double minWeight = ((Tuple2)var25)._1$mcD$sp();
            int smallest = ((Tuple2)var25)._2$mcI$sp();
            if (minWeight < 1.0E-8 * maxWeight) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cluster ", " is dying. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SMALLEST_CLUSTER_INDEX..MODULE$, BoxesRunTime.boxToInteger(smallest))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Split the largest cluster ", " into two."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LARGEST_CLUSTER_INDEX..MODULE$, BoxesRunTime.boxToInteger(largest))}))))));
               double weight = (maxWeight + minWeight) / (double)2.0F;
               this.clusterWeights()[largest] = weight;
               this.clusterWeights()[smallest] = weight;
               Vector largestClusterCenter = this.clusterCenters()[largest];
               Vector smallestClusterCenter = this.clusterCenters()[smallest];

               for(int j = 0; j < dim; ++j) {
                  double x = largestClusterCenter.apply(j);
                  double p = 1.0E-14 * scala.math.package..MODULE$.max(scala.math.package..MODULE$.abs(x), (double)1.0F);
                  largestClusterCenter.asBreeze().update$mcID$sp(j, x + p);
                  smallestClusterCenter.asBreeze().update$mcID$sp(j, x - p);
               }
            }

            return new StreamingKMeansModel(this.clusterCenters(), this.clusterWeights());
         }
      } else {
         throw new MatchError(var18);
      }
   }

   private static final Tuple2 mergeContribs$1(final Tuple2 p1, final Tuple2 p2) {
      Vector var10000;
      if (p1._1() == null) {
         var10000 = (Vector)p2._1();
      } else if (p2._1() == null) {
         var10000 = (Vector)p1._1();
      } else {
         BLAS$.MODULE$.axpy((double)1.0F, (Vector)p2._1(), (Vector)p1._1());
         var10000 = (Vector)p1._1();
      }

      Vector sum = var10000;
      return new Tuple2(sum, BoxesRunTime.boxToLong(p1._2$mcJ$sp() + p2._2$mcJ$sp()));
   }

   // $FF: synthetic method
   public static final long $anonfun$update$4(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Tuple2 var4 = (Tuple2)x0$1._2();
         if (var4 != null) {
            long n = var4._2$mcJ$sp();
            return n;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$update$5(final StreamingKMeansModel $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int label = x0$2._1$mcI$sp();
         Tuple2 var5 = (Tuple2)x0$2._2();
         if (var5 != null) {
            Vector sum = (Vector)var5._1();
            long count = var5._2$mcJ$sp();
            Vector centroid = $this.clusterCenters()[label];
            double updatedWeight = $this.clusterWeights()[label] + (double)count;
            double lambda = (double)count / scala.math.package..MODULE$.max(updatedWeight, 1.0E-16);
            $this.clusterWeights()[label] = updatedWeight;
            BLAS$.MODULE$.scal((double)1.0F - lambda, centroid);
            BLAS$.MODULE$.axpy(lambda / (double)count, sum, centroid);
            int var15 = $this.clusterCenters()[label].size();
            switch (var15) {
               default:
                  String display = var15 > 100 ? scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.doubleArrayOps(centroid.toArray()), 100)).mkString("[", ",", "...") : scala.Predef..MODULE$.wrapDoubleArray(centroid.toArray()).mkString("[", ",", "]");
                  $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cluster ", " updated with weight "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLUSTER_LABEL..MODULE$, BoxesRunTime.boxToInteger(label))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and centroid: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLUSTER_WEIGHT..MODULE$, BoxesRunTime.boxToDouble(updatedWeight)), new MDC(org.apache.spark.internal.LogKeys.CLUSTER_CENTROIDS..MODULE$, display)}))))));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  return;
            }
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final double $anonfun$update$7(final Tuple2 x$1) {
      return x$1._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$update$8(final Tuple2 x$3) {
      return x$3._1$mcD$sp();
   }

   public StreamingKMeansModel(final Vector[] clusterCenters, final double[] clusterWeights) {
      super(clusterCenters);
      this.clusterCenters = clusterCenters;
      this.clusterWeights = clusterWeights;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
