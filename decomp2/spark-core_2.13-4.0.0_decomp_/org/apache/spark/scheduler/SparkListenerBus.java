package org.apache.spark.scheduler;

import org.apache.spark.util.ListenerBus;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u000512\u0001b\u0001\u0003\u0011\u0002\u0007\u0005a\u0001\u0004\u0005\u0006A\u0001!\tA\t\u0005\u0006M\u0001!\tf\n\u0002\u0011'B\f'o\u001b'jgR,g.\u001a:CkNT!!\u0002\u0004\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0004\t\u0003\u0015\u0019\b/\u0019:l\u0015\tI!\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0005\u0019qN]4\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0005)]IR$D\u0001\u0016\u0015\t1b!\u0001\u0003vi&d\u0017B\u0001\r\u0016\u0005-a\u0015n\u001d;f]\u0016\u0014()^:\u0011\u0005iYR\"\u0001\u0003\n\u0005q!!AF*qCJ\\G*[:uK:,'/\u00138uKJ4\u0017mY3\u0011\u0005iq\u0012BA\u0010\u0005\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\t\t\u0003\u001d\u0011J!!J\b\u0003\tUs\u0017\u000e^\u0001\fI>\u0004vn\u001d;Fm\u0016tG\u000fF\u0002$Q)BQ!\u000b\u0002A\u0002e\t\u0001\u0002\\5ti\u0016tWM\u001d\u0005\u0006W\t\u0001\r!H\u0001\u0006KZ,g\u000e\u001e"
)
public interface SparkListenerBus extends ListenerBus {
   // $FF: synthetic method
   static void doPostEvent$(final SparkListenerBus $this, final SparkListenerInterface listener, final SparkListenerEvent event) {
      $this.doPostEvent(listener, event);
   }

   default void doPostEvent(final SparkListenerInterface listener, final SparkListenerEvent event) {
      if (event instanceof SparkListenerStageSubmitted var5) {
         listener.onStageSubmitted(var5);
         BoxedUnit var72 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerStageCompleted var6) {
         listener.onStageCompleted(var6);
         BoxedUnit var71 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerJobStart var7) {
         listener.onJobStart(var7);
         BoxedUnit var70 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerJobEnd var8) {
         listener.onJobEnd(var8);
         BoxedUnit var69 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskStart var9) {
         listener.onTaskStart(var9);
         BoxedUnit var68 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskGettingResult var10) {
         listener.onTaskGettingResult(var10);
         BoxedUnit var67 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskEnd var11) {
         listener.onTaskEnd(var11);
         BoxedUnit var66 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerEnvironmentUpdate var12) {
         listener.onEnvironmentUpdate(var12);
         BoxedUnit var65 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockManagerAdded var13) {
         listener.onBlockManagerAdded(var13);
         BoxedUnit var64 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockManagerRemoved var14) {
         listener.onBlockManagerRemoved(var14);
         BoxedUnit var63 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerUnpersistRDD var15) {
         listener.onUnpersistRDD(var15);
         BoxedUnit var62 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerApplicationStart var16) {
         listener.onApplicationStart(var16);
         BoxedUnit var61 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerApplicationEnd var17) {
         listener.onApplicationEnd(var17);
         BoxedUnit var60 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorMetricsUpdate var18) {
         listener.onExecutorMetricsUpdate(var18);
         BoxedUnit var59 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerStageExecutorMetrics var19) {
         listener.onStageExecutorMetrics(var19);
         BoxedUnit var58 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorAdded var20) {
         listener.onExecutorAdded(var20);
         BoxedUnit var57 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorRemoved var21) {
         listener.onExecutorRemoved(var21);
         BoxedUnit var56 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorBlacklistedForStage var22) {
         listener.onExecutorBlacklistedForStage(var22);
         BoxedUnit var55 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeBlacklistedForStage var23) {
         listener.onNodeBlacklistedForStage(var23);
         BoxedUnit var54 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorBlacklisted var24) {
         listener.onExecutorBlacklisted(var24);
         BoxedUnit var53 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorUnblacklisted var25) {
         listener.onExecutorUnblacklisted(var25);
         BoxedUnit var52 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeBlacklisted var26) {
         listener.onNodeBlacklisted(var26);
         BoxedUnit var51 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeUnblacklisted var27) {
         listener.onNodeUnblacklisted(var27);
         BoxedUnit var50 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorExcludedForStage var28) {
         listener.onExecutorExcludedForStage(var28);
         BoxedUnit var49 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeExcludedForStage var29) {
         listener.onNodeExcludedForStage(var29);
         BoxedUnit var48 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorExcluded var30) {
         listener.onExecutorExcluded(var30);
         BoxedUnit var47 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorUnexcluded var31) {
         listener.onExecutorUnexcluded(var31);
         BoxedUnit var46 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeExcluded var32) {
         listener.onNodeExcluded(var32);
         BoxedUnit var45 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerNodeUnexcluded var33) {
         listener.onNodeUnexcluded(var33);
         BoxedUnit var44 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockUpdated var34) {
         listener.onBlockUpdated(var34);
         BoxedUnit var43 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerSpeculativeTaskSubmitted var35) {
         listener.onSpeculativeTaskSubmitted(var35);
         BoxedUnit var42 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerUnschedulableTaskSetAdded var36) {
         listener.onUnschedulableTaskSetAdded(var36);
         BoxedUnit var41 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerUnschedulableTaskSetRemoved var37) {
         listener.onUnschedulableTaskSetRemoved(var37);
         BoxedUnit var40 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerResourceProfileAdded var38) {
         listener.onResourceProfileAdded(var38);
         BoxedUnit var39 = BoxedUnit.UNIT;
      } else {
         listener.onOtherEvent(event);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   static void $init$(final SparkListenerBus $this) {
   }
}
