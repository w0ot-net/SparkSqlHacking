package org.apache.spark.ml.stat;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.util.ArrayData;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class SummaryBuilderImpl$ implements Logging {
   public static final SummaryBuilderImpl$ MODULE$ = new SummaryBuilderImpl$();
   private static final VectorUDT org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT;
   private static final Seq allMetrics;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT = new VectorUDT();
      allMetrics = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple4[]{new Tuple4("mean", SummaryBuilderImpl.Mean$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMean$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeWeightSum$.MODULE$, scala.collection.immutable.Nil..MODULE$))), new Tuple4("sum", SummaryBuilderImpl.Sum$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMean$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeWeightSum$.MODULE$, scala.collection.immutable.Nil..MODULE$))), new Tuple4("variance", SummaryBuilderImpl.Variance$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeWeightSum$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMean$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeM2n$.MODULE$, scala.collection.immutable.Nil..MODULE$)))), new Tuple4("std", SummaryBuilderImpl.Std$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeWeightSum$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMean$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeM2n$.MODULE$, scala.collection.immutable.Nil..MODULE$)))), new Tuple4("count", SummaryBuilderImpl.Count$.MODULE$, org.apache.spark.sql.types.LongType..MODULE$, scala.collection.immutable.Nil..MODULE$), new Tuple4("numNonZeros", SummaryBuilderImpl.NumNonZeros$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeNNZ$.MODULE$, scala.collection.immutable.Nil..MODULE$)), new Tuple4("max", SummaryBuilderImpl.Max$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMax$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeNNZ$.MODULE$, scala.collection.immutable.Nil..MODULE$))), new Tuple4("min", SummaryBuilderImpl.Min$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeMin$.MODULE$, new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeNNZ$.MODULE$, scala.collection.immutable.Nil..MODULE$))), new Tuple4("normL2", SummaryBuilderImpl.NormL2$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeM2$.MODULE$, scala.collection.immutable.Nil..MODULE$)), new Tuple4("normL1", SummaryBuilderImpl.NormL1$.MODULE$, MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT(), new scala.collection.immutable..colon.colon(SummaryBuilderImpl.ComputeL1$.MODULE$, scala.collection.immutable.Nil..MODULE$))})));
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

   public Seq implementedMetrics() {
      return (Seq)((SeqOps)this.allMetrics().map((x$3) -> (String)x$3._1())).sorted(scala.math.Ordering.String..MODULE$);
   }

   public Tuple2 getRelevantMetrics(final Seq requested) throws IllegalArgumentException {
      Seq all = (Seq)requested.map((req) -> {
         Tuple4 var3 = (Tuple4)MODULE$.allMetrics().find((x$4) -> BoxesRunTime.boxToBoolean($anonfun$getRelevantMetrics$2(req, x$4))).getOrElse(() -> {
            throw new IllegalArgumentException("Metric " + req + " cannot be found. Valid metrics are " + MODULE$.implementedMetrics());
         });
         if (var3 != null) {
            SummaryBuilderImpl.Metric metric = (SummaryBuilderImpl.Metric)var3._2();
            Seq deps = (Seq)var3._4();
            Tuple2 var2 = new Tuple2(metric, deps);
            SummaryBuilderImpl.Metric metric = (SummaryBuilderImpl.Metric)var2._1();
            Seq deps = (Seq)var2._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(metric), deps);
         } else {
            throw new MatchError(var3);
         }
      });
      Seq metrics = (Seq)all.map((x$6) -> (SummaryBuilderImpl.Metric)x$6._1());
      Seq computeMetrics = (Seq)((SeqOps)((SeqOps)all.flatMap((x$7) -> (Seq)x$7._2())).distinct()).sortBy((x$8) -> x$8.toString(), scala.math.Ordering.String..MODULE$);
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(metrics), computeMetrics);
   }

   public StructType structureForMetrics(final Seq metrics) {
      scala.collection.immutable.Map dict = ((IterableOnceOps)this.allMetrics().map((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            SummaryBuilderImpl.Metric metric = (SummaryBuilderImpl.Metric)x0$1._2();
            DataType dataType = (DataType)x0$1._3();
            return new Tuple2(metric, new Tuple2(name, dataType));
         } else {
            throw new MatchError(x0$1);
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
      Seq fields = (Seq)((IterableOps)metrics.map((key) -> (Tuple2)dict.apply(key))).map((x0$2) -> {
         if (x0$2 != null) {
            String name = (String)x0$2._1();
            DataType dataType = (DataType)x0$2._2();
            return new StructField(name, dataType, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
         } else {
            throw new MatchError(x0$2);
         }
      });
      return org.apache.spark.sql.types.StructType..MODULE$.apply(fields);
   }

   public Tuple2 org$apache$spark$ml$stat$SummaryBuilderImpl$$extractRequestedMetrics(final Expression metrics) {
      Object var3 = metrics.eval(metrics.eval$default$1());
      if (var3 instanceof ArrayData var4) {
         IndexedSeq requested = var4.toSeq(org.apache.spark.sql.types.StringType..MODULE$);
         return this.getRelevantMetrics((Seq)requested.map((x$9) -> x$9.toString()));
      } else {
         throw new MatchError(var3);
      }
   }

   public VectorUDT org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT() {
      return org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT;
   }

   private Seq allMetrics() {
      return allMetrics;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getRelevantMetrics$2(final String req$1, final Tuple4 x$4) {
      boolean var3;
      label23: {
         Object var10000 = x$4._1();
         if (var10000 == null) {
            if (req$1 == null) {
               break label23;
            }
         } else if (var10000.equals(req$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private SummaryBuilderImpl$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
