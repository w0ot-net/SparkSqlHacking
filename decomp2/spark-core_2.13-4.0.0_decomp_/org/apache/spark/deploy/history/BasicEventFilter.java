package org.apache.spark.deploy.history;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.SparkListenerBlockManagerAdded;
import org.apache.spark.scheduler.SparkListenerBlockManagerRemoved;
import org.apache.spark.scheduler.SparkListenerBlockUpdated;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorBlacklisted;
import org.apache.spark.scheduler.SparkListenerExecutorExcluded;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerExecutorUnblacklisted;
import org.apache.spark.scheduler.SparkListenerExecutorUnexcluded;
import org.apache.spark.scheduler.SparkListenerStageExecutorMetrics;
import org.apache.spark.storage.BlockManagerId;
import scala.Function1;
import scala.PartialFunction;
import scala.Some;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4Q\u0001D\u0007\u0001#]A\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tq\u0001\u0011\t\u0011)A\u0005s!Aq\t\u0001B\u0001B\u0003%\u0011\b\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003J\u0011!i\u0005A!A!\u0002\u0013I\u0004\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u000bM\u0003A\u0011\u0001+\t\u000fq\u0003!\u0019!C\u0005;\"1!\u000e\u0001Q\u0001\nyCQa\u001b\u0001\u0005\n1DQ!\u001e\u0001\u0005BY\u0014\u0001CQ1tS\u000e,e/\u001a8u\r&dG/\u001a:\u000b\u00059y\u0011a\u00025jgR|'/\u001f\u0006\u0003!E\ta\u0001Z3qY>L(B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0014\u0007\u0001AB\u0004\u0005\u0002\u001a55\tQ\"\u0003\u0002\u001c\u001b\tq!j\u001c2Fm\u0016tGOR5mi\u0016\u0014\bCA\u000f!\u001b\u0005q\"BA\u0010\u0012\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0011\u001f\u0005\u001daunZ4j]\u001e\fQa\u001d;biN\u001c\u0001\u0001\u0005\u0002&k9\u0011ae\r\b\u0003OIr!\u0001K\u0019\u000f\u0005%\u0002dB\u0001\u00160\u001d\tYc&D\u0001-\u0015\ti3%\u0001\u0004=e>|GOP\u0005\u0002-%\u0011A#F\u0005\u0003%MI!\u0001E\t\n\u00059y\u0011B\u0001\u001b\u000e\u0003-)e/\u001a8u\r&dG/\u001a:\n\u0005Y:$\u0001\u0005$jYR,'o\u0015;bi&\u001cH/[2t\u0015\t!T\"\u0001\u0005mSZ,'j\u001c2t!\rQ\u0004i\u0011\b\u0003wy\u0002\"a\u000b\u001f\u000b\u0003u\nQa]2bY\u0006L!a\u0010\u001f\u0002\rA\u0013X\rZ3g\u0013\t\t%IA\u0002TKRT!a\u0010\u001f\u0011\u0005\u0011+U\"\u0001\u001f\n\u0005\u0019c$aA%oi\u0006QA.\u001b<f'R\fw-Z:\u0002\u00131Lg/\u001a+bg.\u001c\bc\u0001\u001eA\u0015B\u0011AiS\u0005\u0003\u0019r\u0012A\u0001T8oO\u0006AA.\u001b<f%\u0012#5/A\u0007mSZ,W\t_3dkR|'o\u001d\t\u0004u\u0001\u0003\u0006C\u0001\u001eR\u0013\t\u0011&I\u0001\u0004TiJLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000fU3v\u000bW-[7B\u0011\u0011\u0004\u0001\u0005\u0006E\u001d\u0001\r\u0001\n\u0005\u0006q\u001d\u0001\r!\u000f\u0005\u0006\u000f\u001e\u0001\r!\u000f\u0005\u0006\u0011\u001e\u0001\r!\u0013\u0005\u0006\u001b\u001e\u0001\r!\u000f\u0005\u0006\u001d\u001e\u0001\raT\u0001\n?\u0006\u001c7-\u001a9u\r:,\u0012A\u0018\t\u0005\t~\u000bw-\u0003\u0002ay\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0002cK6\t1M\u0003\u0002e#\u0005I1o\u00195fIVdWM]\u0005\u0003M\u000e\u0014!c\u00159be.d\u0015n\u001d;f]\u0016\u0014XI^3oiB\u0011A\t[\u0005\u0003Sr\u0012qAQ8pY\u0016\fg.\u0001\u0006`C\u000e\u001cW\r\u001d;G]\u0002\nq#Y2dKB$(\t\\8dW6\u000bg.Y4fe\u00163XM\u001c;\u0015\u0005\u001dl\u0007\"\u00028\u000b\u0001\u0004y\u0017A\u00042m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\t\u0003aNl\u0011!\u001d\u0006\u0003eF\tqa\u001d;pe\u0006<W-\u0003\u0002uc\nq!\t\\8dW6\u000bg.Y4fe&#\u0017\u0001C1dG\u0016\u0004HO\u00128\u0015\u0003y\u0003"
)
public class BasicEventFilter extends JobEventFilter {
   public final Set org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors;
   private final PartialFunction _acceptFn;

   private PartialFunction _acceptFn() {
      return this._acceptFn;
   }

   public boolean org$apache$spark$deploy$history$BasicEventFilter$$acceptBlockManagerEvent(final BlockManagerId blockManagerId) {
      return blockManagerId.isDriver() || this.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(blockManagerId.executorId());
   }

   public PartialFunction acceptFn() {
      return this._acceptFn().orElse(this.acceptFnForJobEvents());
   }

   public BasicEventFilter(final EventFilter.FilterStatistics stats, final Set liveJobs, final Set liveStages, final Set liveTasks, final Set liveRDDs, final Set liveExecutors) {
      super(new Some(stats), liveJobs, liveStages, liveTasks, liveRDDs);
      this.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors = liveExecutors;
      this.logDebug(() -> "live executors : " + this.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors);
      this._acceptFn = new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final BasicEventFilter $outer;

         public final Object applyOrElse(final SparkListenerEvent x1, final Function1 default) {
            if (x1 instanceof SparkListenerExecutorAdded var5) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var5.executorId()));
            } else if (x1 instanceof SparkListenerExecutorRemoved var6) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var6.executorId()));
            } else if (x1 instanceof SparkListenerExecutorBlacklisted var7) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var7.executorId()));
            } else if (x1 instanceof SparkListenerExecutorUnblacklisted var8) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var8.executorId()));
            } else if (x1 instanceof SparkListenerExecutorExcluded var9) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var9.executorId()));
            } else if (x1 instanceof SparkListenerExecutorUnexcluded var10) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var10.executorId()));
            } else if (x1 instanceof SparkListenerStageExecutorMetrics var11) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$liveExecutors.contains(var11.execId()));
            } else if (x1 instanceof SparkListenerBlockManagerAdded var12) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$acceptBlockManagerEvent(var12.blockManagerId()));
            } else if (x1 instanceof SparkListenerBlockManagerRemoved var13) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$acceptBlockManagerEvent(var13.blockManagerId()));
            } else if (x1 instanceof SparkListenerBlockUpdated var14) {
               return BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$deploy$history$BasicEventFilter$$acceptBlockManagerEvent(var14.blockUpdatedInfo().blockManagerId()));
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final SparkListenerEvent x1) {
            if (x1 instanceof SparkListenerExecutorAdded) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorRemoved) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorBlacklisted) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorUnblacklisted) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorExcluded) {
               return true;
            } else if (x1 instanceof SparkListenerExecutorUnexcluded) {
               return true;
            } else if (x1 instanceof SparkListenerStageExecutorMetrics) {
               return true;
            } else if (x1 instanceof SparkListenerBlockManagerAdded) {
               return true;
            } else if (x1 instanceof SparkListenerBlockManagerRemoved) {
               return true;
            } else {
               return x1 instanceof SparkListenerBlockUpdated;
            }
         }

         public {
            if (BasicEventFilter.this == null) {
               throw null;
            } else {
               this.$outer = BasicEventFilter.this;
            }
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
