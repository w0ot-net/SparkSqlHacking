package org.apache.spark.scheduler;

import com.codahale.metrics.Timer;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import org.apache.spark.util.EventLoop;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U3Q\u0001C\u0005\u0001\u0013EA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0007W\u0001\u0001\u000b\u0011\u0002\u0017\t\u000bY\u0002A\u0011I\u001c\t\u000b\u0001\u0003A\u0011B!\t\u000b\r\u0003A\u0011\t#\t\u000bM\u0003A\u0011\t+\u00039\u0011\u000buiU2iK\u0012,H.\u001a:Fm\u0016tG\u000f\u0015:pG\u0016\u001c8\u000fT8pa*\u0011!bC\u0001\ng\u000eDW\rZ;mKJT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0004\u0001Ia\u0002cA\n\u001715\tAC\u0003\u0002\u0016\u0017\u0005!Q\u000f^5m\u0013\t9BCA\u0005Fm\u0016tG\u000fT8paB\u0011\u0011DG\u0007\u0002\u0013%\u00111$\u0003\u0002\u0012\t\u0006;5k\u00195fIVdWM]#wK:$\bCA\u000f!\u001b\u0005q\"BA\u0010\f\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0011\u001f\u0005\u001daunZ4j]\u001e\fA\u0002Z1h'\u000eDW\rZ;mKJ\u001c\u0001\u0001\u0005\u0002\u001aK%\u0011a%\u0003\u0002\r\t\u0006;5k\u00195fIVdWM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005%R\u0003CA\r\u0001\u0011\u0015\u0011#\u00011\u0001%\u0003\u0015!\u0018.\\3s!\tiC'D\u0001/\u0015\ty\u0003'A\u0004nKR\u0014\u0018nY:\u000b\u0005E\u0012\u0014\u0001C2pI\u0006D\u0017\r\\3\u000b\u0003M\n1aY8n\u0013\t)dFA\u0003US6,'/A\u0005p]J+7-Z5wKR\u0011\u0001H\u0010\t\u0003sqj\u0011A\u000f\u0006\u0002w\u0005)1oY1mC&\u0011QH\u000f\u0002\u0005+:LG\u000fC\u0003@\t\u0001\u0007\u0001$A\u0003fm\u0016tG/A\u0006e_>s'+Z2fSZ,GC\u0001\u001dC\u0011\u0015yT\u00011\u0001\u0019\u0003\u001dyg.\u0012:s_J$\"\u0001O#\t\u000b\u00193\u0001\u0019A$\u0002\u0003\u0015\u0004\"\u0001\u0013)\u000f\u0005%seB\u0001&N\u001b\u0005Y%B\u0001'$\u0003\u0019a$o\\8u}%\t1(\u0003\u0002Pu\u00059\u0001/Y2lC\u001e,\u0017BA)S\u0005%!\u0006N]8xC\ndWM\u0003\u0002Pu\u00051qN\\*u_B$\u0012\u0001\u000f"
)
public class DAGSchedulerEventProcessLoop extends EventLoop {
   private final DAGScheduler dagScheduler;
   private final Timer timer;

   public void onReceive(final DAGSchedulerEvent event) {
      Timer.Context timerContext = this.timer.time();

      try {
         this.doOnReceive(event);
      } finally {
         timerContext.stop();
      }

   }

   private void doOnReceive(final DAGSchedulerEvent event) {
      if (event instanceof JobSubmitted var5) {
         int jobId = var5.jobId();
         RDD rdd = var5.finalRDD();
         Function2 func = var5.func();
         int[] partitions = var5.partitions();
         CallSite callSite = var5.callSite();
         JobListener listener = var5.listener();
         JobArtifactSet artifacts = var5.artifactSet();
         Properties properties = var5.properties();
         this.dagScheduler.handleJobSubmitted(jobId, rdd, func, partitions, callSite, listener, artifacts, properties);
         BoxedUnit var104 = BoxedUnit.UNIT;
      } else if (event instanceof MapStageSubmitted var14) {
         int jobId = var14.jobId();
         ShuffleDependency dependency = var14.dependency();
         CallSite callSite = var14.callSite();
         JobListener listener = var14.listener();
         JobArtifactSet artifacts = var14.artifactSet();
         Properties properties = var14.properties();
         this.dagScheduler.handleMapStageSubmitted(jobId, dependency, callSite, listener, artifacts, properties);
         BoxedUnit var103 = BoxedUnit.UNIT;
      } else if (event instanceof StageCancelled var21) {
         int stageId = var21.stageId();
         Option reason = var21.reason();
         this.dagScheduler.handleStageCancellation(stageId, reason);
         BoxedUnit var102 = BoxedUnit.UNIT;
      } else if (event instanceof JobCancelled var24) {
         int jobId = var24.jobId();
         Option reason = var24.reason();
         this.dagScheduler.handleJobCancellation(jobId, reason);
         BoxedUnit var101 = BoxedUnit.UNIT;
      } else if (event instanceof JobGroupCancelled var27) {
         String groupId = var27.groupId();
         boolean cancelFutureJobs = var27.cancelFutureJobs();
         Option reason = var27.reason();
         this.dagScheduler.handleJobGroupCancelled(groupId, cancelFutureJobs, reason);
         BoxedUnit var100 = BoxedUnit.UNIT;
      } else if (event instanceof JobTagCancelled var31) {
         String tag = var31.tagName();
         Option reason = var31.reason();
         Option cancelledJobs = var31.cancelledJobs();
         this.dagScheduler.handleJobTagCancelled(tag, reason, cancelledJobs);
         BoxedUnit var99 = BoxedUnit.UNIT;
      } else if (AllJobsCancelled$.MODULE$.equals(event)) {
         this.dagScheduler.doCancelAllJobs();
         BoxedUnit var98 = BoxedUnit.UNIT;
      } else if (event instanceof ExecutorAdded) {
         ExecutorAdded var35 = (ExecutorAdded)event;
         String execId = var35.execId();
         String host = var35.host();
         this.dagScheduler.handleExecutorAdded(execId, host);
         BoxedUnit var97 = BoxedUnit.UNIT;
      } else if (event instanceof ExecutorLost) {
         ExecutorLost var38 = (ExecutorLost)event;
         String execId = var38.execId();
         ExecutorLossReason reason = var38.reason();
         Object var95;
         if (reason instanceof ExecutorProcessLost) {
            ExecutorProcessLost var43 = (ExecutorProcessLost)reason;
            Option workerHost = var43.workerHost();
            var95 = workerHost;
         } else if (reason instanceof ExecutorDecommission) {
            ExecutorDecommission var45 = (ExecutorDecommission)reason;
            Option workerHost = var45.workerHost();
            var95 = workerHost;
         } else {
            var95 = .MODULE$;
         }

         Option workerHost = (Option)var95;
         this.dagScheduler.handleExecutorLost(execId, workerHost);
         BoxedUnit var96 = BoxedUnit.UNIT;
      } else if (event instanceof WorkerRemoved) {
         WorkerRemoved var47 = (WorkerRemoved)event;
         String workerId = var47.workerId();
         String host = var47.host();
         String message = var47.message();
         this.dagScheduler.handleWorkerRemoved(workerId, host, message);
         BoxedUnit var94 = BoxedUnit.UNIT;
      } else if (event instanceof BeginEvent) {
         BeginEvent var51 = (BeginEvent)event;
         Task task = var51.task();
         TaskInfo taskInfo = var51.taskInfo();
         this.dagScheduler.handleBeginEvent(task, taskInfo);
         BoxedUnit var93 = BoxedUnit.UNIT;
      } else if (event instanceof SpeculativeTaskSubmitted) {
         SpeculativeTaskSubmitted var54 = (SpeculativeTaskSubmitted)event;
         Task task = var54.task();
         int taskIndex = var54.taskIndex();
         this.dagScheduler.handleSpeculativeTaskSubmitted(task, taskIndex);
         BoxedUnit var92 = BoxedUnit.UNIT;
      } else if (event instanceof UnschedulableTaskSetAdded) {
         UnschedulableTaskSetAdded var57 = (UnschedulableTaskSetAdded)event;
         int stageId = var57.stageId();
         int stageAttemptId = var57.stageAttemptId();
         this.dagScheduler.handleUnschedulableTaskSetAdded(stageId, stageAttemptId);
         BoxedUnit var91 = BoxedUnit.UNIT;
      } else if (event instanceof UnschedulableTaskSetRemoved) {
         UnschedulableTaskSetRemoved var60 = (UnschedulableTaskSetRemoved)event;
         int stageId = var60.stageId();
         int stageAttemptId = var60.stageAttemptId();
         this.dagScheduler.handleUnschedulableTaskSetRemoved(stageId, stageAttemptId);
         BoxedUnit var90 = BoxedUnit.UNIT;
      } else if (event instanceof GettingResultEvent) {
         GettingResultEvent var63 = (GettingResultEvent)event;
         TaskInfo taskInfo = var63.taskInfo();
         this.dagScheduler.handleGetTaskResult(taskInfo);
         BoxedUnit var89 = BoxedUnit.UNIT;
      } else if (event instanceof CompletionEvent) {
         CompletionEvent var65 = (CompletionEvent)event;
         this.dagScheduler.handleTaskCompletion(var65);
         BoxedUnit var88 = BoxedUnit.UNIT;
      } else if (event instanceof StageFailed) {
         StageFailed var66 = (StageFailed)event;
         int stageId = var66.stageId();
         String reason = var66.reason();
         Option exception = var66.exception();
         this.dagScheduler.handleStageFailed(stageId, reason, exception);
         BoxedUnit var87 = BoxedUnit.UNIT;
      } else if (event instanceof TaskSetFailed) {
         TaskSetFailed var70 = (TaskSetFailed)event;
         TaskSet taskSet = var70.taskSet();
         String reason = var70.reason();
         Option exception = var70.exception();
         this.dagScheduler.handleTaskSetFailed(taskSet, reason, exception);
         BoxedUnit var86 = BoxedUnit.UNIT;
      } else if (ResubmitFailedStages$.MODULE$.equals(event)) {
         this.dagScheduler.resubmitFailedStages();
         BoxedUnit var85 = BoxedUnit.UNIT;
      } else if (event instanceof RegisterMergeStatuses) {
         RegisterMergeStatuses var74 = (RegisterMergeStatuses)event;
         ShuffleMapStage stage = var74.stage();
         Seq mergeStatuses = var74.mergeStatuses();
         this.dagScheduler.handleRegisterMergeStatuses(stage, mergeStatuses);
         BoxedUnit var84 = BoxedUnit.UNIT;
      } else if (event instanceof ShuffleMergeFinalized) {
         ShuffleMergeFinalized var77 = (ShuffleMergeFinalized)event;
         ShuffleMapStage stage = var77.stage();
         this.dagScheduler.handleShuffleMergeFinalized(stage, stage.shuffleDep().shuffleMergeId());
         BoxedUnit var83 = BoxedUnit.UNIT;
      } else if (event instanceof ShufflePushCompleted) {
         ShufflePushCompleted var79 = (ShufflePushCompleted)event;
         int shuffleId = var79.shuffleId();
         int shuffleMergeId = var79.shuffleMergeId();
         int mapIndex = var79.mapIndex();
         this.dagScheduler.handleShufflePushCompleted(shuffleId, shuffleMergeId, mapIndex);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(event);
      }
   }

   public void onError(final Throwable e) {
      this.logError(() -> "DAGSchedulerEventProcessLoop failed; shutting down SparkContext", e);

      try {
         this.dagScheduler.doCancelAllJobs();
      } catch (Throwable var3) {
         this.logError(() -> "DAGScheduler failed to cancel all jobs.", var3);
      }

      this.dagScheduler.sc().stopInNewThread();
   }

   public void onStop() {
      this.dagScheduler.cleanUpAfterSchedulerStop();
   }

   public DAGSchedulerEventProcessLoop(final DAGScheduler dagScheduler) {
      super("dag-scheduler-event-loop");
      this.dagScheduler = dagScheduler;
      this.timer = dagScheduler.metricsSource().messageProcessingTimer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
