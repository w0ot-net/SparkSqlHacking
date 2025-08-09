package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import org.apache.spark.status.api.v1.TaskMetrics;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a\u0001\u0002\u000e\u001c\t\u0011B\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005U!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007C\u0003>\u0001\u0011\u0005a\bC\u0004D\u0001\u0001\u0007I\u0011\u0001#\t\u000f!\u0003\u0001\u0019!C\u0001\u0013\"1q\n\u0001Q!\n\u0015Cq\u0001\u0015\u0001A\u0002\u0013\u0005\u0011\u000bC\u0004S\u0001\u0001\u0007I\u0011A*\t\rU\u0003\u0001\u0015)\u0003+\u0011\u001d1\u0006\u00011A\u0005\u0002ECqa\u0016\u0001A\u0002\u0013\u0005\u0001\f\u0003\u0004[\u0001\u0001\u0006KA\u000b\u0005\b7\u0002\u0001\r\u0011\"\u0001R\u0011\u001da\u0006\u00011A\u0005\u0002uCaa\u0018\u0001!B\u0013Q\u0003b\u00021\u0001\u0001\u0004%\t!\u0019\u0005\bK\u0002\u0001\r\u0011\"\u0001g\u0011\u0019A\u0007\u0001)Q\u0005E\"9\u0011\u000e\u0001a\u0001\n\u0003Q\u0007bB:\u0001\u0001\u0004%\t\u0001\u001e\u0005\u0007m\u0002\u0001\u000b\u0015B6\t\u000f]\u0004!\u0019!C\u0001q\"1q\u0010\u0001Q\u0001\neDq!!\u0001\u0001\t#\n\u0019A\u0001\rMSZ,W\t_3dkR|'o\u0015;bO\u0016\u001cV/\\7befT!\u0001H\u000f\u0002\rM$\u0018\r^;t\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0015\u0002\"AJ\u0014\u000e\u0003mI!\u0001K\u000e\u0003\u00151Kg/Z#oi&$\u00180A\u0004ti\u0006<W-\u00133\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\u0007%sG/A\u0005biR,W\u000e\u001d;JI\u0006QQ\r_3dkR|'/\u00133\u0011\u0005MRdB\u0001\u001b9!\t)D&D\u00017\u0015\t94%\u0001\u0004=e>|GOP\u0005\u0003s1\na\u0001\u0015:fI\u00164\u0017BA\u001e=\u0005\u0019\u0019FO]5oO*\u0011\u0011\bL\u0001\u0007y%t\u0017\u000e\u001e \u0015\t}\u0002\u0015I\u0011\t\u0003M\u0001AQ!\u000b\u0003A\u0002)BQ\u0001\r\u0003A\u0002)BQ!\r\u0003A\u0002I\n\u0001\u0002^1tWRKW.Z\u000b\u0002\u000bB\u00111FR\u0005\u0003\u000f2\u0012A\u0001T8oO\u0006aA/Y:l)&lWm\u0018\u0013fcR\u0011!*\u0014\t\u0003W-K!\u0001\u0014\u0017\u0003\tUs\u0017\u000e\u001e\u0005\b\u001d\u001a\t\t\u00111\u0001F\u0003\rAH%M\u0001\ni\u0006\u001c8\u000eV5nK\u0002\nab];dG\u0016,G-\u001a3UCN\\7/F\u0001+\u0003I\u0019XoY2fK\u0012,G\rV1tWN|F%Z9\u0015\u0005)#\u0006b\u0002(\n\u0003\u0003\u0005\rAK\u0001\u0010gV\u001c7-Z3eK\u0012$\u0016m]6tA\u0005Ya-Y5mK\u0012$\u0016m]6t\u0003=1\u0017-\u001b7fIR\u000b7o[:`I\u0015\fHC\u0001&Z\u0011\u001dqE\"!AA\u0002)\nABZ1jY\u0016$G+Y:lg\u0002\n1b[5mY\u0016$G+Y:lg\u0006y1.\u001b7mK\u0012$\u0016m]6t?\u0012*\u0017\u000f\u0006\u0002K=\"9ajDA\u0001\u0002\u0004Q\u0013\u0001D6jY2,G\rV1tWN\u0004\u0013AC5t\u000bb\u001cG.\u001e3fIV\t!\r\u0005\u0002,G&\u0011A\r\f\u0002\b\u0005>|G.Z1o\u00039I7/\u0012=dYV$W\rZ0%KF$\"AS4\t\u000f9\u0013\u0012\u0011!a\u0001E\u0006Y\u0011n]#yG2,H-\u001a3!\u0003\u001diW\r\u001e:jGN,\u0012a\u001b\t\u0003YFl\u0011!\u001c\u0006\u0003]>\f!A^\u0019\u000b\u0005A\\\u0012aA1qS&\u0011!/\u001c\u0002\f)\u0006\u001c8.T3ue&\u001c7/A\u0006nKR\u0014\u0018nY:`I\u0015\fHC\u0001&v\u0011\u001dqU#!AA\u0002-\f\u0001\"\\3ue&\u001c7\u000fI\u0001\u0014a\u0016\f7.\u0012=fGV$xN]'fiJL7m]\u000b\u0002sB\u0011!0`\u0007\u0002w*\u0011A0H\u0001\tKb,7-\u001e;pe&\u0011ap\u001f\u0002\u0010\u000bb,7-\u001e;pe6+GO]5dg\u0006!\u0002/Z1l\u000bb,7-\u001e;pe6+GO]5dg\u0002\n\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0003\u0003\u000b\u00012aKA\u0004\u0013\r\tI\u0001\f\u0002\u0004\u0003:L\b"
)
public class LiveExecutorStageSummary extends LiveEntity {
   private final int stageId;
   private final int attemptId;
   private final String executorId;
   private long taskTime;
   private int succeededTasks;
   private int failedTasks;
   private int killedTasks;
   private boolean isExcluded;
   private TaskMetrics metrics;
   private final ExecutorMetrics peakExecutorMetrics;

   public long taskTime() {
      return this.taskTime;
   }

   public void taskTime_$eq(final long x$1) {
      this.taskTime = x$1;
   }

   public int succeededTasks() {
      return this.succeededTasks;
   }

   public void succeededTasks_$eq(final int x$1) {
      this.succeededTasks = x$1;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public void failedTasks_$eq(final int x$1) {
      this.failedTasks = x$1;
   }

   public int killedTasks() {
      return this.killedTasks;
   }

   public void killedTasks_$eq(final int x$1) {
      this.killedTasks = x$1;
   }

   public boolean isExcluded() {
      return this.isExcluded;
   }

   public void isExcluded_$eq(final boolean x$1) {
      this.isExcluded = x$1;
   }

   public TaskMetrics metrics() {
      return this.metrics;
   }

   public void metrics_$eq(final TaskMetrics x$1) {
      this.metrics = x$1;
   }

   public ExecutorMetrics peakExecutorMetrics() {
      return this.peakExecutorMetrics;
   }

   public Object doUpdate() {
      ExecutorStageSummary info = new ExecutorStageSummary(this.taskTime(), this.failedTasks(), this.succeededTasks(), this.killedTasks(), this.metrics().inputMetrics().bytesRead(), this.metrics().inputMetrics().recordsRead(), this.metrics().outputMetrics().bytesWritten(), this.metrics().outputMetrics().recordsWritten(), this.metrics().shuffleReadMetrics().remoteBytesRead() + this.metrics().shuffleReadMetrics().localBytesRead(), this.metrics().shuffleReadMetrics().recordsRead(), this.metrics().shuffleWriteMetrics().bytesWritten(), this.metrics().shuffleWriteMetrics().recordsWritten(), this.metrics().memoryBytesSpilled(), this.metrics().diskBytesSpilled(), this.isExcluded(), (new Some(this.peakExecutorMetrics())).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$doUpdate$3(x$2))), this.isExcluded());
      return new ExecutorStageSummaryWrapper(this.stageId, this.attemptId, this.executorId, info);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doUpdate$3(final ExecutorMetrics x$2) {
      return x$2.isSet();
   }

   public LiveExecutorStageSummary(final int stageId, final int attemptId, final String executorId) {
      this.stageId = stageId;
      this.attemptId = attemptId;
      this.executorId = executorId;
      this.taskTime = 0L;
      this.succeededTasks = 0;
      this.failedTasks = 0;
      this.killedTasks = 0;
      this.isExcluded = false;
      this.metrics = LiveEntityHelpers$.MODULE$.createMetrics(0L);
      this.peakExecutorMetrics = new ExecutorMetrics();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
