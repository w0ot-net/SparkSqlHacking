package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.TaskInfo;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005I4A\u0001E\t\u00055!Aq\u0004\u0001BA\u0002\u0013\u0005\u0001\u0005\u0003\u0005(\u0001\t\u0005\r\u0011\"\u0001)\u0011!\t\u0004A!A!B\u0013\t\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u0011Y\u0002!\u0011!Q\u0001\nMB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\u0006}\u0001!\ta\u0010\u0005\b\u000b\u0002\u0001\r\u0011\"\u0003G\u0011\u001dy\u0005\u00011A\u0005\nACaA\u0015\u0001!B\u00139\u0005bB*\u0001\u0001\u0004%\t\u0001\u0016\u0005\bC\u0002\u0001\r\u0011\"\u0001c\u0011\u0019!\u0007\u0001)Q\u0005+\")Q\r\u0001C\u0001M\")Q\u000e\u0001C)]\nAA*\u001b<f)\u0006\u001c8N\u0003\u0002\u0013'\u000511\u000f^1ukNT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\u0002\u0001'\t\u00011\u0004\u0005\u0002\u001d;5\t\u0011#\u0003\u0002\u001f#\tQA*\u001b<f\u000b:$\u0018\u000e^=\u0002\t%tgm\\\u000b\u0002CA\u0011!%J\u0007\u0002G)\u0011AeE\u0001\ng\u000eDW\rZ;mKJL!AJ\u0012\u0003\u0011Q\u000b7o[%oM>\f\u0001\"\u001b8g_~#S-\u001d\u000b\u0003S=\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012A!\u00168ji\"9\u0001GAA\u0001\u0002\u0004\t\u0013a\u0001=%c\u0005)\u0011N\u001c4pA\u000591\u000f^1hK&#\u0007C\u0001\u00165\u0013\t)4FA\u0002J]R\fab\u001d;bO\u0016\fE\u000f^3naRLE-\u0001\bmCN$X\u000b\u001d3bi\u0016$\u0016.\\3\u0011\u0007)J4(\u0003\u0002;W\t1q\n\u001d;j_:\u0004\"A\u000b\u001f\n\u0005uZ#\u0001\u0002'p]\u001e\fa\u0001P5oSRtD#\u0002!B\u0005\u000e#\u0005C\u0001\u000f\u0001\u0011\u0015yr\u00011\u0001\"\u0011\u0015\u0011t\u00011\u00014\u0011\u00151t\u00011\u00014\u0011\u00159t\u00011\u00019\u0003\u001diW\r\u001e:jGN,\u0012a\u0012\t\u0003\u00116k\u0011!\u0013\u0006\u0003\u0015.\u000b!A^\u0019\u000b\u00051\u000b\u0012aA1qS&\u0011a*\u0013\u0002\f)\u0006\u001c8.T3ue&\u001c7/A\u0006nKR\u0014\u0018nY:`I\u0015\fHCA\u0015R\u0011\u001d\u0001\u0014\"!AA\u0002\u001d\u000b\u0001\"\\3ue&\u001c7\u000fI\u0001\rKJ\u0014xN]'fgN\fw-Z\u000b\u0002+B\u0019!&\u000f,\u0011\u0005]sfB\u0001-]!\tI6&D\u0001[\u0015\tY\u0016$\u0001\u0004=e>|GOP\u0005\u0003;.\na\u0001\u0015:fI\u00164\u0017BA0a\u0005\u0019\u0019FO]5oO*\u0011QlK\u0001\u0011KJ\u0014xN]'fgN\fw-Z0%KF$\"!K2\t\u000fAb\u0011\u0011!a\u0001+\u0006iQM\u001d:pe6+7o]1hK\u0002\nQ\"\u001e9eCR,W*\u001a;sS\u000e\u001cHCA$h\u0011\u0015)e\u00021\u0001i!\tIG.D\u0001k\u0015\tY7#\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\tq%.\u0001\u0005e_V\u0003H-\u0019;f)\u0005y\u0007C\u0001\u0016q\u0013\t\t8FA\u0002B]f\u0004"
)
public class LiveTask extends LiveEntity {
   private TaskInfo info;
   private final int stageId;
   private final int stageAttemptId;
   private final Option lastUpdateTime;
   private TaskMetrics metrics;
   private Option errorMessage;

   public TaskInfo info() {
      return this.info;
   }

   public void info_$eq(final TaskInfo x$1) {
      this.info = x$1;
   }

   private TaskMetrics metrics() {
      return this.metrics;
   }

   private void metrics_$eq(final TaskMetrics x$1) {
      this.metrics = x$1;
   }

   public Option errorMessage() {
      return this.errorMessage;
   }

   public void errorMessage_$eq(final Option x$1) {
      this.errorMessage = x$1;
   }

   public TaskMetrics updateMetrics(final org.apache.spark.executor.TaskMetrics metrics) {
      if (metrics != null) {
         TaskMetrics old = this.metrics();
         TaskMetrics newMetrics = LiveEntityHelpers$.MODULE$.createMetrics(metrics.executorDeserializeTime(), metrics.executorDeserializeCpuTime(), metrics.executorRunTime(), metrics.executorCpuTime(), metrics.resultSize(), metrics.jvmGCTime(), metrics.resultSerializationTime(), metrics.memoryBytesSpilled(), metrics.diskBytesSpilled(), metrics.peakExecutionMemory(), metrics.inputMetrics().bytesRead(), metrics.inputMetrics().recordsRead(), metrics.outputMetrics().bytesWritten(), metrics.outputMetrics().recordsWritten(), metrics.shuffleReadMetrics().remoteBlocksFetched(), metrics.shuffleReadMetrics().localBlocksFetched(), metrics.shuffleReadMetrics().fetchWaitTime(), metrics.shuffleReadMetrics().remoteBytesRead(), metrics.shuffleReadMetrics().remoteBytesReadToDisk(), metrics.shuffleReadMetrics().localBytesRead(), metrics.shuffleReadMetrics().recordsRead(), metrics.shuffleReadMetrics().corruptMergedBlockChunks(), metrics.shuffleReadMetrics().mergedFetchFallbackCount(), metrics.shuffleReadMetrics().remoteMergedBlocksFetched(), metrics.shuffleReadMetrics().localMergedBlocksFetched(), metrics.shuffleReadMetrics().remoteMergedChunksFetched(), metrics.shuffleReadMetrics().localMergedChunksFetched(), metrics.shuffleReadMetrics().remoteMergedBytesRead(), metrics.shuffleReadMetrics().localMergedBytesRead(), metrics.shuffleReadMetrics().remoteReqsDuration(), metrics.shuffleReadMetrics().remoteMergedReqsDuration(), metrics.shuffleWriteMetrics().bytesWritten(), metrics.shuffleWriteMetrics().writeTime(), metrics.shuffleWriteMetrics().recordsWritten());
         this.metrics_$eq(newMetrics);
         return old.executorDeserializeTime() >= 0L ? LiveEntityHelpers$.MODULE$.subtractMetrics(newMetrics, old) : newMetrics;
      } else {
         return null;
      }
   }

   public Object doUpdate() {
      long duration = this.info().finished() ? this.info().duration() : this.info().timeRunning(BoxesRunTime.unboxToLong(this.lastUpdateTime.getOrElse((JFunction0.mcJ.sp)() -> System.currentTimeMillis())));
      boolean hasMetrics = this.metrics().executorDeserializeTime() >= 0L;
      TaskMetrics taskMetrics = hasMetrics && !this.info().successful() ? LiveEntityHelpers$.MODULE$.makeNegative(this.metrics()) : this.metrics();
      return new TaskDataWrapper(.MODULE$.long2Long(this.info().taskId()), this.info().index(), this.info().attemptNumber(), this.info().partitionId(), this.info().launchTime(), this.info().gettingResult() ? this.info().gettingResultTime() : -1L, duration, Utils$.MODULE$.weakIntern(this.info().executorId()), Utils$.MODULE$.weakIntern(this.info().host()), Utils$.MODULE$.weakIntern(this.info().status()), Utils$.MODULE$.weakIntern(this.info().taskLocality().toString()), this.info().speculative(), LiveEntityHelpers$.MODULE$.newAccumulatorInfos(this.info().accumulables()), this.errorMessage(), hasMetrics, taskMetrics.executorDeserializeTime(), taskMetrics.executorDeserializeCpuTime(), taskMetrics.executorRunTime(), taskMetrics.executorCpuTime(), taskMetrics.resultSize(), taskMetrics.jvmGcTime(), taskMetrics.resultSerializationTime(), taskMetrics.memoryBytesSpilled(), taskMetrics.diskBytesSpilled(), taskMetrics.peakExecutionMemory(), taskMetrics.inputMetrics().bytesRead(), taskMetrics.inputMetrics().recordsRead(), taskMetrics.outputMetrics().bytesWritten(), taskMetrics.outputMetrics().recordsWritten(), taskMetrics.shuffleReadMetrics().remoteBlocksFetched(), taskMetrics.shuffleReadMetrics().localBlocksFetched(), taskMetrics.shuffleReadMetrics().fetchWaitTime(), taskMetrics.shuffleReadMetrics().remoteBytesRead(), taskMetrics.shuffleReadMetrics().remoteBytesReadToDisk(), taskMetrics.shuffleReadMetrics().localBytesRead(), taskMetrics.shuffleReadMetrics().recordsRead(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().corruptMergedBlockChunks(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().mergedFetchFallbackCount(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBlocksFetched(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().localMergedBlocksFetched(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedChunksFetched(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().localMergedChunksFetched(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBytesRead(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().localMergedBytesRead(), taskMetrics.shuffleReadMetrics().remoteReqsDuration(), taskMetrics.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedReqsDuration(), taskMetrics.shuffleWriteMetrics().bytesWritten(), taskMetrics.shuffleWriteMetrics().writeTime(), taskMetrics.shuffleWriteMetrics().recordsWritten(), this.stageId, this.stageAttemptId);
   }

   public LiveTask(final TaskInfo info, final int stageId, final int stageAttemptId, final Option lastUpdateTime) {
      this.info = info;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.lastUpdateTime = lastUpdateTime;
      super();
      this.metrics = LiveEntityHelpers$.MODULE$.createMetrics(-1L);
      this.errorMessage = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
