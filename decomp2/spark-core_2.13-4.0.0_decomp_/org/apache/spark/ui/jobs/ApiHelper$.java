package org.apache.spark.ui.jobs;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.AccumulableInfo;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.ShuffleReadMetrics;
import org.apache.spark.status.api.v1.StageData;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;

public final class ApiHelper$ {
   public static final ApiHelper$ MODULE$ = new ApiHelper$();
   private static final String HEADER_ID = "ID";
   private static final String HEADER_TASK_INDEX = "Index";
   private static final String HEADER_ATTEMPT = "Attempt";
   private static final String HEADER_STATUS = "Status";
   private static final String HEADER_LOCALITY = "Locality Level";
   private static final String HEADER_EXECUTOR = "Executor ID";
   private static final String HEADER_HOST = "Host";
   private static final String HEADER_LAUNCH_TIME = "Launch Time";
   private static final String HEADER_DURATION = "Duration";
   private static final String HEADER_SCHEDULER_DELAY = "Scheduler Delay";
   private static final String HEADER_DESER_TIME = "Task Deserialization Time";
   private static final String HEADER_GC_TIME = "GC Time";
   private static final String HEADER_SER_TIME = "Result Serialization Time";
   private static final String HEADER_GETTING_RESULT_TIME = "Getting Result Time";
   private static final String HEADER_PEAK_MEM = "Peak Execution Memory";
   private static final String HEADER_ACCUMULATORS = "Accumulators";
   private static final String HEADER_INPUT_SIZE = "Input Size / Records";
   private static final String HEADER_OUTPUT_SIZE = "Output Size / Records";
   private static final String HEADER_SHUFFLE_READ_FETCH_WAIT_TIME = "Shuffle Read Fetch Wait Time";
   private static final String HEADER_SHUFFLE_TOTAL_READS = "Shuffle Read Size / Records";
   private static final String HEADER_SHUFFLE_REMOTE_READS = "Shuffle Remote Reads";
   private static final String HEADER_SHUFFLE_WRITE_TIME = "Shuffle Write Time";
   private static final String HEADER_SHUFFLE_WRITE_SIZE = "Shuffle Write Size / Records";
   private static final String HEADER_MEM_SPILL = "Spill (Memory)";
   private static final String HEADER_DISK_SPILL = "Spill (Disk)";
   private static final String HEADER_ERROR = "Errors";
   private static final Map COLUMN_TO_INDEX;

   static {
      COLUMN_TO_INDEX = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_ID()), (Object)null), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_TASK_INDEX()), "partid"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_ATTEMPT()), "att"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_STATUS()), "sta"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_LOCALITY()), "loc"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_EXECUTOR()), "exe"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_HOST()), "hst"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_LAUNCH_TIME()), "lt"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_DURATION()), "ert"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SCHEDULER_DELAY()), "dly"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_DESER_TIME()), "des"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_GC_TIME()), "gc"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SER_TIME()), "rst"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_GETTING_RESULT_TIME()), "grt"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_PEAK_MEM()), "pem"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_ACCUMULATORS()), "acc"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_INPUT_SIZE()), "is"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_OUTPUT_SIZE()), "os"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SHUFFLE_READ_FETCH_WAIT_TIME()), "srt"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SHUFFLE_TOTAL_READS()), "stby"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SHUFFLE_REMOTE_READS()), "srby"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SHUFFLE_WRITE_TIME()), "swt"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_SHUFFLE_WRITE_SIZE()), "sws"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_MEM_SPILL()), "mbs"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_DISK_SPILL()), "dbs"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(MODULE$.HEADER_ERROR()), "err")})));
   }

   public String HEADER_ID() {
      return HEADER_ID;
   }

   public String HEADER_TASK_INDEX() {
      return HEADER_TASK_INDEX;
   }

   public String HEADER_ATTEMPT() {
      return HEADER_ATTEMPT;
   }

   public String HEADER_STATUS() {
      return HEADER_STATUS;
   }

   public String HEADER_LOCALITY() {
      return HEADER_LOCALITY;
   }

   public String HEADER_EXECUTOR() {
      return HEADER_EXECUTOR;
   }

   public String HEADER_HOST() {
      return HEADER_HOST;
   }

   public String HEADER_LAUNCH_TIME() {
      return HEADER_LAUNCH_TIME;
   }

   public String HEADER_DURATION() {
      return HEADER_DURATION;
   }

   public String HEADER_SCHEDULER_DELAY() {
      return HEADER_SCHEDULER_DELAY;
   }

   public String HEADER_DESER_TIME() {
      return HEADER_DESER_TIME;
   }

   public String HEADER_GC_TIME() {
      return HEADER_GC_TIME;
   }

   public String HEADER_SER_TIME() {
      return HEADER_SER_TIME;
   }

   public String HEADER_GETTING_RESULT_TIME() {
      return HEADER_GETTING_RESULT_TIME;
   }

   public String HEADER_PEAK_MEM() {
      return HEADER_PEAK_MEM;
   }

   public String HEADER_ACCUMULATORS() {
      return HEADER_ACCUMULATORS;
   }

   public String HEADER_INPUT_SIZE() {
      return HEADER_INPUT_SIZE;
   }

   public String HEADER_OUTPUT_SIZE() {
      return HEADER_OUTPUT_SIZE;
   }

   public String HEADER_SHUFFLE_READ_FETCH_WAIT_TIME() {
      return HEADER_SHUFFLE_READ_FETCH_WAIT_TIME;
   }

   public String HEADER_SHUFFLE_TOTAL_READS() {
      return HEADER_SHUFFLE_TOTAL_READS;
   }

   public String HEADER_SHUFFLE_REMOTE_READS() {
      return HEADER_SHUFFLE_REMOTE_READS;
   }

   public String HEADER_SHUFFLE_WRITE_TIME() {
      return HEADER_SHUFFLE_WRITE_TIME;
   }

   public String HEADER_SHUFFLE_WRITE_SIZE() {
      return HEADER_SHUFFLE_WRITE_SIZE;
   }

   public String HEADER_MEM_SPILL() {
      return HEADER_MEM_SPILL;
   }

   public String HEADER_DISK_SPILL() {
      return HEADER_DISK_SPILL;
   }

   public String HEADER_ERROR() {
      return HEADER_ERROR;
   }

   public Map COLUMN_TO_INDEX() {
      return COLUMN_TO_INDEX;
   }

   public boolean hasAccumulators(final StageData stageData) {
      return stageData.accumulatorUpdates().exists((acc) -> BoxesRunTime.boxToBoolean($anonfun$hasAccumulators$1(acc)));
   }

   public boolean hasInput(final StageData stageData) {
      return stageData.inputBytes() > 0L || stageData.inputRecords() > 0L;
   }

   public boolean hasOutput(final StageData stageData) {
      return stageData.outputBytes() > 0L || stageData.outputRecords() > 0L;
   }

   public boolean hasShuffleRead(final StageData stageData) {
      return stageData.shuffleReadBytes() > 0L;
   }

   public boolean hasShuffleWrite(final StageData stageData) {
      return stageData.shuffleWriteBytes() > 0L;
   }

   public boolean hasBytesSpilled(final StageData stageData) {
      return stageData.diskBytesSpilled() > 0L || stageData.memoryBytesSpilled() > 0L;
   }

   public long totalBytesRead(final ShuffleReadMetrics metrics) {
      return metrics.localBytesRead() + metrics.remoteBytesRead();
   }

   public Option indexName(final String sortColumn) {
      Option var3 = this.COLUMN_TO_INDEX().get(sortColumn);
      if (var3 instanceof Some var4) {
         String v = (String)var4.value();
         return scala.Option..MODULE$.apply(v);
      } else {
         throw new IllegalArgumentException("Invalid sort column: " + sortColumn);
      }
   }

   public Tuple2 lastStageNameAndDescription(final AppStatusStore store, final JobData job) {
      if (job.stageIds().isEmpty()) {
         return new Tuple2("", job.name());
      } else {
         Option stage = store.asOption(() -> (StageData)store.stageAttempt(BoxesRunTime.unboxToInt(job.stageIds().max(scala.math.Ordering.Int..MODULE$)), 0, store.stageAttempt$default$3(), store.stageAttempt$default$4(), store.stageAttempt$default$5(), store.stageAttempt$default$6())._1());
         return new Tuple2(stage.map((x$13) -> x$13.name()).getOrElse(() -> ""), stage.flatMap((x$14) -> x$14.description()).getOrElse(() -> job.name()));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasAccumulators$1(final AccumulableInfo acc) {
      return acc.name() != null && acc.value() != null;
   }

   private ApiHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
