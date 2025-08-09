package org.apache.spark.deploy.history;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerExecutorMetricsUpdate;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerSpeculativeTaskSubmitted;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.SparkListenerStageSubmitted;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.SparkListenerTaskGettingResult;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.scheduler.SparkListenerUnpersistRDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple4;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005-4aAC\u0006\u0002\u0002=)\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011}\u0002!\u0011!Q\u0001\n\u0001C\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u001b\"A\u0011\u000b\u0001B\u0001B\u0003%\u0001\tC\u0003S\u0001\u0011\u00051\u000bC\u0003[\u0001\u0011\u00053\fC\u0004]\u0001\t\u0007I\u0011C/\t\r)\u0004\u0001\u0015!\u0003_\u00059QuNY#wK:$h)\u001b7uKJT!\u0001D\u0007\u0002\u000f!L7\u000f^8ss*\u0011abD\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001cB\u0001\u0001\f\u001dAA\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u0004\"!\b\u0010\u000e\u0003-I!aH\u0006\u0003\u0017\u00153XM\u001c;GS2$XM\u001d\t\u0003C\u0011j\u0011A\t\u0006\u0003G=\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003K\t\u0012q\u0001T8hO&tw-A\u0003ti\u0006$8o\u0001\u0001\u0011\u0007]I3&\u0003\u0002+1\t1q\n\u001d;j_:\u0004\"\u0001\f\u001f\u000f\u00055RdB\u0001\u0018:\u001d\ty\u0003H\u0004\u00021o9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\u001d\na\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005I\u0019\u0012B\u0001\t\u0012\u0013\tqq\"\u0003\u0002\r\u001b%\u00111hC\u0001\f\u000bZ,g\u000e\u001e$jYR,'/\u0003\u0002>}\t\u0001b)\u001b7uKJ\u001cF/\u0019;jgRL7m\u001d\u0006\u0003w-\t\u0001\u0002\\5wK*{'m\u001d\t\u0004\u0003\u0016CeB\u0001\"D!\t\u0011\u0004$\u0003\u0002E1\u00051\u0001K]3eK\u001aL!AR$\u0003\u0007M+GO\u0003\u0002E1A\u0011q#S\u0005\u0003\u0015b\u00111!\u00138u\u0003)a\u0017N^3Ti\u0006<Wm]\u0001\nY&4X\rV1tWN\u00042!Q#O!\t9r*\u0003\u0002Q1\t!Aj\u001c8h\u0003!a\u0017N^3S\t\u0012\u001b\u0018A\u0002\u001fj]&$h\b\u0006\u0004U+Z;\u0006,\u0017\t\u0003;\u0001AQA\n\u0004A\u0002!BQa\u0010\u0004A\u0002\u0001CQa\u0013\u0004A\u0002\u0001CQ\u0001\u0014\u0004A\u00025CQ!\u0015\u0004A\u0002\u0001\u000b!b\u001d;bi&\u001cH/[2t)\u0005A\u0013\u0001F1dG\u0016\u0004HO\u00128G_JTuNY#wK:$8/F\u0001_!\u00119r,Y4\n\u0005\u0001D\"a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0011\u0005\t,W\"A2\u000b\u0005\u0011|\u0011!C:dQ\u0016$W\u000f\\3s\u0013\t17M\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bCA\fi\u0013\tI\u0007DA\u0004C_>dW-\u00198\u0002+\u0005\u001c7-\u001a9u\r:4uN\u001d&pE\u00163XM\u001c;tA\u0001"
)
public abstract class JobEventFilter implements EventFilter, Logging {
   private final Option stats;
   public final Set org$apache$spark$deploy$history$JobEventFilter$$liveJobs;
   public final Set org$apache$spark$deploy$history$JobEventFilter$$liveStages;
   public final Set org$apache$spark$deploy$history$JobEventFilter$$liveTasks;
   public final Set org$apache$spark$deploy$history$JobEventFilter$$liveRDDs;
   private final PartialFunction acceptFnForJobEvents;
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

   public Option statistics() {
      return this.stats;
   }

   public PartialFunction acceptFnForJobEvents() {
      return this.acceptFnForJobEvents;
   }

   public JobEventFilter(final Option stats, final Set liveJobs, final Set liveStages, final Set liveTasks, final Set liveRDDs) {
      this.stats = stats;
      this.org$apache$spark$deploy$history$JobEventFilter$$liveJobs = liveJobs;
      this.org$apache$spark$deploy$history$JobEventFilter$$liveStages = liveStages;
      this.org$apache$spark$deploy$history$JobEventFilter$$liveTasks = liveTasks;
      this.org$apache$spark$deploy$history$JobEventFilter$$liveRDDs = liveRDDs;
      Logging.$init$(this);
      this.logDebug((Function0)(() -> "jobs : " + this.org$apache$spark$deploy$history$JobEventFilter$$liveJobs));
      this.logDebug((Function0)(() -> "stages : " + this.org$apache$spark$deploy$history$JobEventFilter$$liveStages));
      this.logDebug((Function0)(() -> "tasks : " + this.org$apache$spark$deploy$history$JobEventFilter$$liveTasks));
      this.logDebug((Function0)(() -> "RDDs : " + this.org$apache$spark$deploy$history$JobEventFilter$$liveRDDs));
      this.acceptFnForJobEvents = new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final JobEventFilter $outer;

         public final Object applyOrElse(final SparkListenerEvent x1, final Function1 default) {
            if (x1 instanceof SparkListenerStageCompleted var5) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveStages.contains(BoxesRunTime.boxToInteger(var5.stageInfo().stageId())));
            } else if (x1 instanceof SparkListenerStageSubmitted var6) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveStages.contains(BoxesRunTime.boxToInteger(var6.stageInfo().stageId())));
            } else if (x1 instanceof SparkListenerTaskStart var7) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveTasks.contains(BoxesRunTime.boxToLong(var7.taskInfo().taskId())));
            } else if (x1 instanceof SparkListenerTaskGettingResult var8) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveTasks.contains(BoxesRunTime.boxToLong(var8.taskInfo().taskId())));
            } else if (x1 instanceof SparkListenerTaskEnd var9) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveTasks.contains(BoxesRunTime.boxToLong(var9.taskInfo().taskId())));
            } else if (x1 instanceof SparkListenerJobStart var10) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveJobs.contains(BoxesRunTime.boxToInteger(var10.jobId())));
            } else if (x1 instanceof SparkListenerJobEnd var11) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveJobs.contains(BoxesRunTime.boxToInteger(var11.jobId())));
            } else if (x1 instanceof SparkListenerUnpersistRDD var12) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveRDDs.contains(BoxesRunTime.boxToInteger(var12.rddId())));
            } else if (x1 instanceof SparkListenerExecutorMetricsUpdate var13) {
               return BoxesRunTime.boxToBoolean(var13.accumUpdates().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$1(this, x0$1))));
            } else if (x1 instanceof SparkListenerSpeculativeTaskSubmitted var14) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveStages.contains(BoxesRunTime.boxToInteger(var14.stageId())));
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final SparkListenerEvent x1) {
            if (x1 instanceof SparkListenerStageCompleted) {
               return true;
            } else if (x1 instanceof SparkListenerStageSubmitted) {
               return true;
            } else if (x1 instanceof SparkListenerTaskStart) {
               return true;
            } else if (x1 instanceof SparkListenerTaskGettingResult) {
               return true;
            } else if (x1 instanceof SparkListenerTaskEnd) {
               return true;
            } else if (x1 instanceof SparkListenerJobStart) {
               return true;
            } else if (x1 instanceof SparkListenerJobEnd) {
               return true;
            } else if (x1 instanceof SparkListenerUnpersistRDD) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorMetricsUpdate) {
               return true;
            } else {
               return x1 instanceof SparkListenerSpeculativeTaskSubmitted;
            }
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$1(final Object $this, final Tuple4 x0$1) {
            if (x0$1 == null) {
               throw new MatchError(x0$1);
            } else {
               long taskId = BoxesRunTime.unboxToLong(x0$1._1());
               int stageId = BoxesRunTime.unboxToInt(x0$1._2());
               return $this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveTasks.contains(BoxesRunTime.boxToLong(taskId)) || $this.$outer.org$apache$spark$deploy$history$JobEventFilter$$liveStages.contains(BoxesRunTime.boxToInteger(stageId));
            }
         }

         public {
            if (JobEventFilter.this == null) {
               throw null;
            } else {
               this.$outer = JobEventFilter.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
