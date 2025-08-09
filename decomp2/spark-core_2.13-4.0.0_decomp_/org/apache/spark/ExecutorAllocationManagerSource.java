package org.apache.spark;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.source.Source;
import scala.Function0;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005m4Q!\u0005\n\u0001%aA\u0001b\n\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\u0006[\u0001!\tA\f\u0005\bc\u0001\u0011\r\u0011\"\u00013\u0011\u0019Y\u0004\u0001)A\u0005g!9A\b\u0001b\u0001\n\u0003i\u0004BB$\u0001A\u0003%a\bC\u0003I\u0001\u0011%\u0011\nC\u0003m\u0001\u0011%Q\u000eC\u0004s\u0001\t\u0007I\u0011A:\t\rQ\u0004\u0001\u0015!\u0003o\u0011\u001d)\bA1A\u0005\u0002MDaA\u001e\u0001!\u0002\u0013q\u0007bB<\u0001\u0005\u0004%\ta\u001d\u0005\u0007q\u0002\u0001\u000b\u0011\u00028\t\u000fe\u0004!\u0019!C\u0001g\"1!\u0010\u0001Q\u0001\n9\u0014q$\u0012=fGV$xN]!mY>\u001c\u0017\r^5p]6\u000bg.Y4feN{WO]2f\u0015\t\u0019B#A\u0003ta\u0006\u00148N\u0003\u0002\u0016-\u00051\u0011\r]1dQ\u0016T\u0011aF\u0001\u0004_J<7c\u0001\u0001\u001a?A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u0004\"\u0001I\u0013\u000e\u0003\u0005R!AI\u0012\u0002\rM|WO]2f\u0015\t!##A\u0004nKR\u0014\u0018nY:\n\u0005\u0019\n#AB*pkJ\u001cW-A\rfq\u0016\u001cW\u000f^8s\u00032dwnY1uS>tW*\u00198bO\u0016\u00148\u0001\u0001\t\u0003U-j\u0011AE\u0005\u0003YI\u0011\u0011$\u0012=fGV$xN]!mY>\u001c\u0017\r^5p]6\u000bg.Y4fe\u00061A(\u001b8jiz\"\"a\f\u0019\u0011\u0005)\u0002\u0001\"B\u0014\u0003\u0001\u0004I\u0013AC:pkJ\u001cWMT1nKV\t1\u0007\u0005\u00025s5\tQG\u0003\u00027o\u0005!A.\u00198h\u0015\u0005A\u0014\u0001\u00026bm\u0006L!AO\u001b\u0003\rM#(/\u001b8h\u0003-\u0019x.\u001e:dK:\u000bW.\u001a\u0011\u0002\u001d5,GO]5d%\u0016<\u0017n\u001d;ssV\ta\b\u0005\u0002@\u000b6\t\u0001I\u0003\u0002%\u0003*\u0011!iQ\u0001\tG>$\u0017\r[1mK*\tA)A\u0002d_6L!A\u0012!\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006yQ.\u001a;sS\u000e\u0014VmZ5tiJL\b%A\u0007sK\u001eL7\u000f^3s\u000f\u0006,x-Z\u000b\u0003\u0015\u0006$Ba\u0013([UB\u0011!\u0004T\u0005\u0003\u001bn\u0011A!\u00168ji\")qj\u0002a\u0001!\u0006!a.Y7f!\t\t\u0006L\u0004\u0002S-B\u00111kG\u0007\u0002)*\u0011Q\u000bK\u0001\u0007yI|w\u000e\u001e \n\u0005][\u0012A\u0002)sK\u0012,g-\u0003\u0002;3*\u0011qk\u0007\u0005\u00077\u001e!\t\u0019\u0001/\u0002\u000bY\fG.^3\u0011\u0007iiv,\u0003\u0002_7\tAAHY=oC6,g\b\u0005\u0002aC2\u0001A!\u00022\b\u0005\u0004\u0019'!\u0001+\u0012\u0005\u0011<\u0007C\u0001\u000ef\u0013\t17DA\u0004O_RD\u0017N\\4\u0011\u0005iA\u0017BA5\u001c\u0005\r\te.\u001f\u0005\u0006W\u001e\u0001\raX\u0001\rI\u00164\u0017-\u001e7u-\u0006dW/Z\u0001\u000bO\u0016$8i\\;oi\u0016\u0014HC\u00018r!\tyt.\u0003\u0002q\u0001\n91i\\;oi\u0016\u0014\b\"B(\t\u0001\u0004\u0001\u0016\u0001G4sC\u000e,g-\u001e7ms\u0012+7m\\7nSN\u001c\u0018n\u001c8fIV\ta.A\rhe\u0006\u001cWMZ;mYf$UmY8n[&\u001c8/[8oK\u0012\u0004\u0013A\u00063fG>lW.[:tS>tWK\u001c4j]&\u001c\b.\u001a3\u0002/\u0011,7m\\7nSN\u001c\u0018n\u001c8V]\u001aLg.[:iK\u0012\u0004\u0013\u0001\u00043sSZ,'oS5mY\u0016$\u0017!\u00043sSZ,'oS5mY\u0016$\u0007%\u0001\nfq&$X\rZ+oKb\u0004Xm\u0019;fI2L\u0018aE3ySR,G-\u00168fqB,7\r^3eYf\u0004\u0003"
)
public class ExecutorAllocationManagerSource implements Source {
   private final ExecutorAllocationManager executorAllocationManager;
   private final String sourceName;
   private final MetricRegistry metricRegistry;
   private final Counter gracefullyDecommissioned;
   private final Counter decommissionUnfinished;
   private final Counter driverKilled;
   private final Counter exitedUnexpectedly;

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   private void registerGauge(final String name, final Function0 value, final Object defaultValue) {
      this.metricRegistry().register(MetricRegistry.name("executors", new String[]{name}), new Gauge(value, defaultValue) {
         private final Function0 value$1;
         private final Object defaultValue$1;

         public synchronized Object getValue() {
            return .MODULE$.apply(this.value$1.apply()).getOrElse(() -> this.defaultValue$1);
         }

         public {
            this.value$1 = value$1;
            this.defaultValue$1 = defaultValue$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   private Counter getCounter(final String name) {
      return this.metricRegistry().counter(MetricRegistry.name("executors", new String[]{name}));
   }

   public Counter gracefullyDecommissioned() {
      return this.gracefullyDecommissioned;
   }

   public Counter decommissionUnfinished() {
      return this.decommissionUnfinished;
   }

   public Counter driverKilled() {
      return this.driverKilled;
   }

   public Counter exitedUnexpectedly() {
      return this.exitedUnexpectedly;
   }

   public ExecutorAllocationManagerSource(final ExecutorAllocationManager executorAllocationManager) {
      this.executorAllocationManager = executorAllocationManager;
      this.sourceName = "ExecutorAllocationManager";
      this.metricRegistry = new MetricRegistry();
      this.gracefullyDecommissioned = this.getCounter("numberExecutorsGracefullyDecommissioned");
      this.decommissionUnfinished = this.getCounter("numberExecutorsDecommissionUnfinished");
      this.driverKilled = this.getCounter("numberExecutorsKilledByDriver");
      this.exitedUnexpectedly = this.getCounter("numberExecutorsExitedUnexpectedly");
      this.registerGauge("numberExecutorsToAdd", (JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(this.executorAllocationManager.numExecutorsToAddPerResourceProfileId().values().sum(scala.math.Numeric.IntIsIntegral..MODULE$)), BoxesRunTime.boxToInteger(0));
      this.registerGauge("numberExecutorsPendingToRemove", (JFunction0.mcI.sp)() -> this.executorAllocationManager.executorMonitor().pendingRemovalCount(), BoxesRunTime.boxToInteger(0));
      this.registerGauge("numberAllExecutors", (JFunction0.mcI.sp)() -> this.executorAllocationManager.executorMonitor().executorCount(), BoxesRunTime.boxToInteger(0));
      this.registerGauge("numberTargetExecutors", (JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(this.executorAllocationManager.numExecutorsTargetPerResourceProfileId().values().sum(scala.math.Numeric.IntIsIntegral..MODULE$)), BoxesRunTime.boxToInteger(0));
      this.registerGauge("numberMaxNeededExecutors", (JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(((IterableOnceOps)this.executorAllocationManager.numExecutorsTargetPerResourceProfileId().keys().map((JFunction1.mcII.sp)(x$11) -> this.executorAllocationManager.maxNumExecutorsNeededPerResourceProfile(x$11))).sum(scala.math.Numeric.IntIsIntegral..MODULE$)), BoxesRunTime.boxToInteger(0));
      this.registerGauge("numberDecommissioningExecutors", (JFunction0.mcI.sp)() -> this.executorAllocationManager.executorMonitor().decommissioningCount(), BoxesRunTime.boxToInteger(0));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
