package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.AccumulableInfo;
import org.apache.spark.util.AccumulatorContext$;
import org.apache.spark.util.AccumulatorMetadata;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.LongAccumulator;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class TaskMetrics$ implements Logging, Serializable {
   public static final TaskMetrics$ MODULE$ = new TaskMetrics$();
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

   public TaskMetrics empty() {
      TaskMetrics tm = new TaskMetrics();
      tm.nameToAccums().foreach((x0$1) -> {
         $anonfun$empty$1(x0$1);
         return BoxedUnit.UNIT;
      });
      return tm;
   }

   public TaskMetrics registered() {
      TaskMetrics tm = this.empty();
      tm.internalAccums().foreach((a) -> {
         $anonfun$registered$1(a);
         return BoxedUnit.UNIT;
      });
      return tm;
   }

   public TaskMetrics fromAccumulatorInfos(final Seq infos) {
      TaskMetrics tm = new TaskMetrics();
      ((IterableOnceOps)infos.filter((info) -> BoxesRunTime.boxToBoolean($anonfun$fromAccumulatorInfos$1(info)))).foreach((info) -> {
         $anonfun$fromAccumulatorInfos$2(tm, info);
         return BoxedUnit.UNIT;
      });
      return tm;
   }

   public TaskMetrics fromAccumulators(final Seq accums) {
      TaskMetrics tm = new TaskMetrics();
      accums.foreach((acc) -> {
         Option name = acc.name();
         if (name.isDefined() && tm.nameToAccums().contains(name.get())) {
            AccumulatorV2 tmAcc = (AccumulatorV2)tm.nameToAccums().apply(name.get());
            tmAcc.metadata_$eq(acc.metadata());
            tmAcc.merge(acc);
            return BoxedUnit.UNIT;
         } else {
            return tm._externalAccums().$plus$eq(acc);
         }
      });
      return tm;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskMetrics$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$empty$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         AccumulatorV2 acc = (AccumulatorV2)x0$1._2();
         acc.metadata_$eq(new AccumulatorMetadata(AccumulatorContext$.MODULE$.newId(), new Some(name), true));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$registered$1(final AccumulatorV2 a) {
      AccumulatorContext$.MODULE$.register(a);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fromAccumulatorInfos$1(final AccumulableInfo info) {
      return info.name().isDefined() && info.update().isDefined();
   }

   // $FF: synthetic method
   public static final void $anonfun$fromAccumulatorInfos$3(final Object value$1, final AccumulatorV2 x$4) {
      ((LongAccumulator)x$4).setValue(BoxesRunTime.unboxToLong(value$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$fromAccumulatorInfos$2(final TaskMetrics tm$1, final AccumulableInfo info) {
      Object value;
      label14: {
         String name = (String)info.name().get();
         value = info.update().get();
         String var4 = InternalAccumulator$.MODULE$.UPDATED_BLOCK_STATUSES();
         if (name == null) {
            if (var4 == null) {
               break label14;
            }
         } else if (name.equals(var4)) {
            break label14;
         }

         tm$1.nameToAccums().get(name).foreach((x$4) -> {
            $anonfun$fromAccumulatorInfos$3(value, x$4);
            return BoxedUnit.UNIT;
         });
         return;
      }

      tm$1.setUpdatedBlockStatuses((List)value);
   }

   private TaskMetrics$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
