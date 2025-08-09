package org.apache.spark.shuffle;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.scheduler.MapStatus;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%4Q\u0001B\u0003\u0001\u000f5AQa\n\u0001\u0005\u0002!BQa\u000b\u0001\u0005\u00121BQA\u000e\u0001\u0005\u0002]\u0012Qc\u00155vM\u001adWm\u0016:ji\u0016\u0004&o\\2fgN|'O\u0003\u0002\u0007\u000f\u000591\u000f[;gM2,'B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0014\t\u0001qA#\t\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005UqbB\u0001\f\u001d\u001d\t92$D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\t\u0012BA\u000f\u0011\u0003\u001d\u0001\u0018mY6bO\u0016L!a\b\u0011\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005u\u0001\u0002C\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\b\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0014$\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRtD#A\u0015\u0011\u0005)\u0002Q\"A\u0003\u0002+\r\u0014X-\u0019;f\u001b\u0016$(/[2t%\u0016\u0004xN\u001d;feR\u0011Q\u0006\r\t\u0003U9J!aL\u0003\u00037MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018nY:SKB|'\u000f^3s\u0011\u0015\t$\u00011\u00013\u0003\u001d\u0019wN\u001c;fqR\u0004\"a\r\u001b\u000e\u0003\u001dI!!N\u0004\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010^\u0001\u0006oJLG/\u001a\u000b\u0007qyzel\u00195\u0011\u0005ebT\"\u0001\u001e\u000b\u0005m:\u0011!C:dQ\u0016$W\u000f\\3s\u0013\ti$HA\u0005NCB\u001cF/\u0019;vg\")qh\u0001a\u0001\u0001\u00061\u0011N\u001c9viN\u0004$!\u0011$\u0011\u0007U\u0011E)\u0003\u0002DA\tA\u0011\n^3sCR|'\u000f\u0005\u0002F\r2\u0001A!C$?\u0003\u0003\u0005\tQ!\u0001I\u0005\ryF%M\t\u0003\u00132\u0003\"a\u0004&\n\u0005-\u0003\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f5K!A\u0014\t\u0003\u0007\u0005s\u0017\u0010C\u0003Q\u0007\u0001\u0007\u0011+A\u0002eKB\u0004DA\u0015,Z9B)1gU+Y7&\u0011Ak\u0002\u0002\u0012'\",hM\u001a7f\t\u0016\u0004XM\u001c3f]\u000eL\bCA#W\t%9v*!A\u0001\u0002\u000b\u0005\u0001JA\u0002`II\u0002\"!R-\u0005\u0013i{\u0015\u0011!A\u0001\u0006\u0003A%aA0%gA\u0011Q\t\u0018\u0003\n;>\u000b\t\u0011!A\u0003\u0002!\u00131a\u0018\u00135\u0011\u0015y6\u00011\u0001a\u0003\u0015i\u0017\r]%e!\ty\u0011-\u0003\u0002c!\t!Aj\u001c8h\u0011\u0015!7\u00011\u0001f\u0003!i\u0017\r]%oI\u0016D\bCA\bg\u0013\t9\u0007CA\u0002J]RDQ!M\u0002A\u0002I\u0002"
)
public class ShuffleWriteProcessor implements Serializable, Logging {
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

   public ShuffleWriteMetricsReporter createMetricsReporter(final TaskContext context) {
      return context.taskMetrics().shuffleWriteMetrics();
   }

   public MapStatus write(final Iterator inputs, final ShuffleDependency dep, final long mapId, final int mapIndex, final TaskContext context) {
      ShuffleWriter writer = null;

      try {
         ShuffleManager manager = SparkEnv$.MODULE$.get().shuffleManager();
         writer = manager.getWriter(dep.shuffleHandle(), mapId, context, this.createMetricsReporter(context));
         writer.write(inputs);
         Option mapStatus = writer.stop(true);
         if (mapStatus.isDefined()) {
            if (dep.shuffleMergeAllowed() && dep.getMergerLocs().isEmpty()) {
               MapOutputTracker mapOutputTracker = SparkEnv$.MODULE$.get().mapOutputTracker();
               Seq mergerLocs = mapOutputTracker.getShufflePushMergerLocations(dep.shuffleId());
               if (mergerLocs.nonEmpty()) {
                  dep.setMergerLocs(mergerLocs);
               }
            }

            if (!dep.shuffleMergeFinalized()) {
               ShuffleBlockResolver var13 = manager.shuffleBlockResolver();
               if (var13 instanceof IndexShuffleBlockResolver) {
                  IndexShuffleBlockResolver var14 = (IndexShuffleBlockResolver)var13;
                  this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shuffle merge enabled with"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " merger locations"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_MERGER_LOCATIONS..MODULE$, BoxesRunTime.boxToInteger(dep.getMergerLocs().size()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" for stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(context.stageId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with shuffle ID ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(dep.shuffleId()))}))))));
                  this.logDebug((Function0)(() -> "Starting pushing blocks for the task " + context.taskAttemptId()));
                  File dataFile = var14.getDataFile(dep.shuffleId(), mapId);
                  (new ShuffleBlockPusher(SparkEnv$.MODULE$.get().conf())).initiateBlockPush(dataFile, writer.getPartitionLengths(), dep, mapIndex);
                  BoxedUnit var22 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var23 = BoxedUnit.UNIT;
               }
            }
         }

         return (MapStatus)mapStatus.get();
      } catch (Exception var19) {
         try {
            if (writer != null) {
               writer.stop(false);
            } else {
               BoxedUnit var21 = BoxedUnit.UNIT;
            }
         } catch (Exception var18) {
            this.log().debug("Could not stop writer", var18);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         throw var19;
      }
   }

   public ShuffleWriteProcessor() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
