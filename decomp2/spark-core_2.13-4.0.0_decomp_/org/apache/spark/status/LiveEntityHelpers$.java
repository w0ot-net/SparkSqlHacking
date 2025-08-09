package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.status.api.v1.AccumulableInfo;
import org.apache.spark.status.api.v1.InputMetrics;
import org.apache.spark.status.api.v1.OutputMetrics;
import org.apache.spark.status.api.v1.ShufflePushReadMetrics;
import org.apache.spark.status.api.v1.ShuffleReadMetrics;
import org.apache.spark.status.api.v1.ShuffleWriteMetrics;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.util.AccumulatorContext$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;

public final class LiveEntityHelpers$ {
   public static final LiveEntityHelpers$ MODULE$ = new LiveEntityHelpers$();

   private String accuValuetoString(final Object value) {
      if (value instanceof List var4) {
         if (var4.size() > 5) {
            IterableOnceOps var10000 = (IterableOnceOps).MODULE$.ListHasAsScala(var4).asScala().take(5);
            int var10003 = var4.size();
            return var10000.mkString("[", ",", ",... " + (var10003 - 5) + " more items]");
         } else {
            return var4.toString();
         }
      } else {
         return value.toString();
      }
   }

   public Seq newAccumulatorInfos(final Iterable accums) {
      return ((IterableOnceOps)((IterableOps)accums.filter((acc) -> BoxesRunTime.boxToBoolean($anonfun$newAccumulatorInfos$1(acc)))).map((acc) -> new AccumulableInfo(acc.id(), (String)acc.name().map((s) -> Utils$.MODULE$.weakIntern(s)).orNull(scala..less.colon.less..MODULE$.refl()), acc.update().map((value) -> MODULE$.accuValuetoString(value)), (String)acc.value().map((value) -> MODULE$.accuValuetoString(value)).orNull(scala..less.colon.less..MODULE$.refl())))).toSeq();
   }

   public TaskMetrics createMetrics(final long executorDeserializeTime, final long executorDeserializeCpuTime, final long executorRunTime, final long executorCpuTime, final long resultSize, final long jvmGcTime, final long resultSerializationTime, final long memoryBytesSpilled, final long diskBytesSpilled, final long peakExecutionMemory, final long inputBytesRead, final long inputRecordsRead, final long outputBytesWritten, final long outputRecordsWritten, final long shuffleRemoteBlocksFetched, final long shuffleLocalBlocksFetched, final long shuffleFetchWaitTime, final long shuffleRemoteBytesRead, final long shuffleRemoteBytesReadToDisk, final long shuffleLocalBytesRead, final long shuffleRecordsRead, final long shuffleCorruptMergedBlockChunks, final long shuffleMergedFetchFallbackCount, final long shuffleMergedRemoteBlocksFetched, final long shuffleMergedLocalBlocksFetched, final long shuffleMergedRemoteChunksFetched, final long shuffleMergedLocalChunksFetched, final long shuffleMergedRemoteBytesRead, final long shuffleMergedLocalBytesRead, final long shuffleRemoteReqsDuration, final long shuffleMergedRemoteReqsDuration, final long shuffleBytesWritten, final long shuffleWriteTime, final long shuffleRecordsWritten) {
      return new TaskMetrics(executorDeserializeTime, executorDeserializeCpuTime, executorRunTime, executorCpuTime, resultSize, jvmGcTime, resultSerializationTime, memoryBytesSpilled, diskBytesSpilled, peakExecutionMemory, new InputMetrics(inputBytesRead, inputRecordsRead), new OutputMetrics(outputBytesWritten, outputRecordsWritten), new ShuffleReadMetrics(shuffleRemoteBlocksFetched, shuffleLocalBlocksFetched, shuffleFetchWaitTime, shuffleRemoteBytesRead, shuffleRemoteBytesReadToDisk, shuffleLocalBytesRead, shuffleRecordsRead, shuffleRemoteReqsDuration, new ShufflePushReadMetrics(shuffleCorruptMergedBlockChunks, shuffleMergedFetchFallbackCount, shuffleMergedRemoteBlocksFetched, shuffleMergedLocalBlocksFetched, shuffleMergedRemoteChunksFetched, shuffleMergedLocalChunksFetched, shuffleMergedRemoteBytesRead, shuffleMergedLocalBytesRead, shuffleMergedRemoteReqsDuration)), new ShuffleWriteMetrics(shuffleBytesWritten, shuffleWriteTime, shuffleRecordsWritten));
   }

   public TaskMetrics createMetrics(final long default) {
      return this.createMetrics(default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default, default);
   }

   public TaskMetrics addMetrics(final TaskMetrics m1, final TaskMetrics m2) {
      return this.addMetrics(m1, m2, 1);
   }

   public TaskMetrics subtractMetrics(final TaskMetrics m1, final TaskMetrics m2) {
      return this.addMetrics(m1, m2, -1);
   }

   public TaskMetrics makeNegative(final TaskMetrics m) {
      return this.createMetrics(updateMetricValue$1(m.executorDeserializeTime()), updateMetricValue$1(m.executorDeserializeCpuTime()), updateMetricValue$1(m.executorRunTime()), updateMetricValue$1(m.executorCpuTime()), updateMetricValue$1(m.resultSize()), updateMetricValue$1(m.jvmGcTime()), updateMetricValue$1(m.resultSerializationTime()), updateMetricValue$1(m.memoryBytesSpilled()), updateMetricValue$1(m.diskBytesSpilled()), updateMetricValue$1(m.peakExecutionMemory()), updateMetricValue$1(m.inputMetrics().bytesRead()), updateMetricValue$1(m.inputMetrics().recordsRead()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().corruptMergedBlockChunks()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().mergedFetchFallbackCount()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBlocksFetched()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().localMergedBlocksFetched()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedChunksFetched()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().localMergedChunksFetched()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBytesRead()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().localMergedBytesRead()), updateMetricValue$1(m.shuffleReadMetrics().remoteReqsDuration()), updateMetricValue$1(m.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedReqsDuration()), updateMetricValue$1(m.outputMetrics().bytesWritten()), updateMetricValue$1(m.outputMetrics().recordsWritten()), updateMetricValue$1(m.shuffleReadMetrics().remoteBlocksFetched()), updateMetricValue$1(m.shuffleReadMetrics().localBlocksFetched()), updateMetricValue$1(m.shuffleReadMetrics().fetchWaitTime()), updateMetricValue$1(m.shuffleReadMetrics().remoteBytesRead()), updateMetricValue$1(m.shuffleReadMetrics().remoteBytesReadToDisk()), updateMetricValue$1(m.shuffleReadMetrics().localBytesRead()), updateMetricValue$1(m.shuffleReadMetrics().recordsRead()), updateMetricValue$1(m.shuffleWriteMetrics().bytesWritten()), updateMetricValue$1(m.shuffleWriteMetrics().writeTime()), updateMetricValue$1(m.shuffleWriteMetrics().recordsWritten()));
   }

   private TaskMetrics addMetrics(final TaskMetrics m1, final TaskMetrics m2, final int mult) {
      return this.createMetrics(m1.executorDeserializeTime() + m2.executorDeserializeTime() * (long)mult, m1.executorDeserializeCpuTime() + m2.executorDeserializeCpuTime() * (long)mult, m1.executorRunTime() + m2.executorRunTime() * (long)mult, m1.executorCpuTime() + m2.executorCpuTime() * (long)mult, m1.resultSize() + m2.resultSize() * (long)mult, m1.jvmGcTime() + m2.jvmGcTime() * (long)mult, m1.resultSerializationTime() + m2.resultSerializationTime() * (long)mult, m1.memoryBytesSpilled() + m2.memoryBytesSpilled() * (long)mult, m1.diskBytesSpilled() + m2.diskBytesSpilled() * (long)mult, m1.peakExecutionMemory() + m2.peakExecutionMemory() * (long)mult, m1.inputMetrics().bytesRead() + m2.inputMetrics().bytesRead() * (long)mult, m1.inputMetrics().recordsRead() + m2.inputMetrics().recordsRead() * (long)mult, m1.outputMetrics().bytesWritten() + m2.outputMetrics().bytesWritten() * (long)mult, m1.outputMetrics().recordsWritten() + m2.outputMetrics().recordsWritten() * (long)mult, m1.shuffleReadMetrics().remoteBlocksFetched() + m2.shuffleReadMetrics().remoteBlocksFetched() * (long)mult, m1.shuffleReadMetrics().localBlocksFetched() + m2.shuffleReadMetrics().localBlocksFetched() * (long)mult, m1.shuffleReadMetrics().fetchWaitTime() + m2.shuffleReadMetrics().fetchWaitTime() * (long)mult, m1.shuffleReadMetrics().remoteBytesRead() + m2.shuffleReadMetrics().remoteBytesRead() * (long)mult, m1.shuffleReadMetrics().remoteBytesReadToDisk() + m2.shuffleReadMetrics().remoteBytesReadToDisk() * (long)mult, m1.shuffleReadMetrics().localBytesRead() + m2.shuffleReadMetrics().localBytesRead() * (long)mult, m1.shuffleReadMetrics().recordsRead() + m2.shuffleReadMetrics().recordsRead() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().corruptMergedBlockChunks() + m2.shuffleReadMetrics().shufflePushReadMetrics().corruptMergedBlockChunks() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().mergedFetchFallbackCount() + m2.shuffleReadMetrics().shufflePushReadMetrics().mergedFetchFallbackCount() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBlocksFetched() + m2.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBlocksFetched() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().localMergedBlocksFetched() + m2.shuffleReadMetrics().shufflePushReadMetrics().localMergedBlocksFetched() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedChunksFetched() + m2.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedChunksFetched() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().localMergedChunksFetched() + m2.shuffleReadMetrics().shufflePushReadMetrics().localMergedChunksFetched() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBytesRead() + m2.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedBytesRead() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().localMergedBytesRead() + m2.shuffleReadMetrics().shufflePushReadMetrics().localMergedBytesRead() * (long)mult, m1.shuffleReadMetrics().remoteReqsDuration() + m2.shuffleReadMetrics().remoteReqsDuration() * (long)mult, m1.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedReqsDuration() + m2.shuffleReadMetrics().shufflePushReadMetrics().remoteMergedReqsDuration() * (long)mult, m1.shuffleWriteMetrics().bytesWritten() + m2.shuffleWriteMetrics().bytesWritten() * (long)mult, m1.shuffleWriteMetrics().writeTime() + m2.shuffleWriteMetrics().writeTime() * (long)mult, m1.shuffleWriteMetrics().recordsWritten() + m2.shuffleWriteMetrics().recordsWritten() * (long)mult);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$newAccumulatorInfos$1(final org.apache.spark.scheduler.AccumulableInfo acc) {
      boolean var2;
      label25: {
         if (!acc.internal()) {
            Option var10000 = acc.metadata();
            Some var1 = new Some(AccumulatorContext$.MODULE$.SQL_ACCUM_IDENTIFIER());
            if (var10000 == null) {
               if (var1 != null) {
                  break label25;
               }
            } else if (!var10000.equals(var1)) {
               break label25;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   private static final long updateMetricValue$1(final long metric) {
      return metric * -1L - 1L;
   }

   private LiveEntityHelpers$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
