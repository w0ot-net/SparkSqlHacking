package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import scala.Option;

public final class ExecutorStageSummarySerializer$ {
   public static final ExecutorStageSummarySerializer$ MODULE$ = new ExecutorStageSummarySerializer$();

   public StoreTypes.ExecutorStageSummary serialize(final ExecutorStageSummary input) {
      StoreTypes.ExecutorStageSummary.Builder builder = StoreTypes.ExecutorStageSummary.newBuilder().setTaskTime(input.taskTime()).setFailedTasks(input.failedTasks()).setSucceededTasks(input.succeededTasks()).setKilledTasks(input.killedTasks()).setInputBytes(input.inputBytes()).setInputRecords(input.inputRecords()).setOutputBytes(input.outputBytes()).setOutputRecords(input.outputRecords()).setShuffleRead(input.shuffleRead()).setShuffleReadRecords(input.shuffleReadRecords()).setShuffleWrite(input.shuffleWrite()).setShuffleWriteRecords(input.shuffleWriteRecords()).setMemoryBytesSpilled(input.memoryBytesSpilled()).setDiskBytesSpilled(input.diskBytesSpilled()).setIsBlacklistedForStage(input.isBlacklistedForStage()).setIsExcludedForStage(input.isExcludedForStage());
      input.peakMemoryMetrics().map((m) -> builder.setPeakMemoryMetrics(ExecutorMetricsSerializer$.MODULE$.serialize(m)));
      return builder.build();
   }

   public ExecutorStageSummary deserialize(final StoreTypes.ExecutorStageSummary binary) {
      Option peakMemoryMetrics = Utils$.MODULE$.getOptional(binary.hasPeakMemoryMetrics(), () -> ExecutorMetricsSerializer$.MODULE$.deserialize(binary.getPeakMemoryMetrics()));
      return new ExecutorStageSummary(binary.getTaskTime(), binary.getFailedTasks(), binary.getSucceededTasks(), binary.getKilledTasks(), binary.getInputBytes(), binary.getInputRecords(), binary.getOutputBytes(), binary.getOutputRecords(), binary.getShuffleRead(), binary.getShuffleReadRecords(), binary.getShuffleWrite(), binary.getShuffleWriteRecords(), binary.getMemoryBytesSpilled(), binary.getDiskBytesSpilled(), binary.getIsBlacklistedForStage(), peakMemoryMetrics, binary.getIsExcludedForStage());
   }

   private ExecutorStageSummarySerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
