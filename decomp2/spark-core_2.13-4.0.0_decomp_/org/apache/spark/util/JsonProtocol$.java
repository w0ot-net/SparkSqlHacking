package org.apache.spark.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import java.util.UUID;
import org.apache.spark.ExceptionFailure;
import org.apache.spark.ExceptionFailure$;
import org.apache.spark.ExecutorLostFailure;
import org.apache.spark.FetchFailed;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.Resubmitted$;
import org.apache.spark.SparkConf;
import org.apache.spark.Success$;
import org.apache.spark.TaskCommitDenied;
import org.apache.spark.TaskEndReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskKilled$;
import org.apache.spark.TaskResultLost$;
import org.apache.spark.UnknownReason$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.InputMetrics;
import org.apache.spark.executor.OutputMetrics;
import org.apache.spark.executor.ShuffleWriteMetrics;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.executor.TaskMetrics$;
import org.apache.spark.executor.TempShuffleReadMetrics;
import org.apache.spark.metrics.ExecutorMetricType$;
import org.apache.spark.rdd.DeterministicLevel$;
import org.apache.spark.rdd.RDDOperationScope;
import org.apache.spark.rdd.RDDOperationScope$;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceInformation$;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.scheduler.AccumulableInfo;
import org.apache.spark.scheduler.JobFailed;
import org.apache.spark.scheduler.JobResult;
import org.apache.spark.scheduler.JobSucceeded$;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import org.apache.spark.scheduler.SparkListenerApplicationStart;
import org.apache.spark.scheduler.SparkListenerBlockManagerAdded;
import org.apache.spark.scheduler.SparkListenerBlockManagerRemoved;
import org.apache.spark.scheduler.SparkListenerBlockUpdated;
import org.apache.spark.scheduler.SparkListenerEnvironmentUpdate;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorMetricsUpdate;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerLogStart;
import org.apache.spark.scheduler.SparkListenerResourceProfileAdded;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.SparkListenerStageExecutorMetrics;
import org.apache.spark.scheduler.SparkListenerStageSubmitted;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.SparkListenerTaskGettingResult;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.scheduler.SparkListenerUnpersistRDD;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.scheduler.StageInfo$;
import org.apache.spark.scheduler.TaskInfo;
import org.apache.spark.scheduler.TaskLocality$;
import org.apache.spark.scheduler.cluster.ExecutorInfo;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockId$;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.BlockManagerId$;
import org.apache.spark.storage.BlockStatus;
import org.apache.spark.storage.BlockUpdatedInfo;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.storage.StorageLevel;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.LinkedHashMap;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class JsonProtocol$ implements JsonUtils {
   public static final JsonProtocol$ MODULE$ = new JsonProtocol$();
   private static final JsonProtocolOptions defaultOptions;
   private static final Set accumulableExcludeList;
   private static final Set taskMetricAccumulableNames;
   private static ObjectMapper mapper;

   static {
      JsonUtils.$init$(MODULE$);
      defaultOptions = new JsonProtocolOptions(new SparkConf(false));
      accumulableExcludeList = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{InternalAccumulator$.MODULE$.UPDATED_BLOCK_STATUSES(), InternalAccumulator$.MODULE$.COLLECT_METRICS_ACCUMULATOR()})));
      taskMetricAccumulableNames = TaskMetrics$.MODULE$.empty().nameToAccums().keySet().toSet();
   }

   public String toJsonString(final Function1 block) {
      return JsonUtils.toJsonString$(this, block);
   }

   public ObjectMapper mapper() {
      return mapper;
   }

   public void org$apache$spark$util$JsonUtils$_setter_$mapper_$eq(final ObjectMapper x$1) {
      mapper = x$1;
   }

   public JsonProtocolOptions defaultOptions() {
      return defaultOptions;
   }

   public String sparkEventToJsonString(final SparkListenerEvent event) {
      return this.sparkEventToJsonString(event, this.defaultOptions());
   }

   public String sparkEventToJsonString(final SparkListenerEvent event, final JsonProtocolOptions options) {
      return this.toJsonString((generator) -> {
         $anonfun$sparkEventToJsonString$1(event, options, generator);
         return BoxedUnit.UNIT;
      });
   }

   public void writeSparkEventToJson(final SparkListenerEvent event, final JsonGenerator g, final JsonProtocolOptions options) {
      if (event instanceof SparkListenerStageSubmitted var6) {
         this.stageSubmittedToJson(var6, g, options);
         BoxedUnit var45 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerStageCompleted var7) {
         this.stageCompletedToJson(var7, g, options);
         BoxedUnit var44 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskStart var8) {
         this.taskStartToJson(var8, g, options);
         BoxedUnit var43 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskGettingResult var9) {
         this.taskGettingResultToJson(var9, g, options);
         BoxedUnit var42 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerTaskEnd var10) {
         this.taskEndToJson(var10, g, options);
         BoxedUnit var41 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerJobStart var11) {
         this.jobStartToJson(var11, g, options);
         BoxedUnit var40 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerJobEnd var12) {
         this.jobEndToJson(var12, g);
         BoxedUnit var39 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerEnvironmentUpdate var13) {
         this.environmentUpdateToJson(var13, g);
         BoxedUnit var38 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockManagerAdded var14) {
         this.blockManagerAddedToJson(var14, g);
         BoxedUnit var37 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockManagerRemoved var15) {
         this.blockManagerRemovedToJson(var15, g);
         BoxedUnit var36 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerUnpersistRDD var16) {
         this.unpersistRDDToJson(var16, g);
         BoxedUnit var35 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerApplicationStart var17) {
         this.applicationStartToJson(var17, g);
         BoxedUnit var34 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerApplicationEnd var18) {
         this.applicationEndToJson(var18, g);
         BoxedUnit var33 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorAdded var19) {
         this.executorAddedToJson(var19, g);
         BoxedUnit var32 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorRemoved var20) {
         this.executorRemovedToJson(var20, g);
         BoxedUnit var31 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerLogStart var21) {
         this.logStartToJson(var21, g);
         BoxedUnit var30 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerExecutorMetricsUpdate var22) {
         this.executorMetricsUpdateToJson(var22, g);
         BoxedUnit var29 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerStageExecutorMetrics var23) {
         this.stageExecutorMetricsToJson(var23, g);
         BoxedUnit var28 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerBlockUpdated var24) {
         this.blockUpdateToJson(var24, g);
         BoxedUnit var27 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerResourceProfileAdded var25) {
         this.resourceProfileAddedToJson(var25, g);
         BoxedUnit var26 = BoxedUnit.UNIT;
      } else {
         this.mapper().writeValue(g, event);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void stageSubmittedToJson(final SparkListenerStageSubmitted stageSubmitted, final JsonGenerator g, final JsonProtocolOptions options) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageSubmitted());
      g.writeFieldName("Stage Info");
      this.stageInfoToJson(stageSubmitted.stageInfo(), g, options, false);
      scala.Option..MODULE$.apply(stageSubmitted.properties()).foreach((properties) -> {
         $anonfun$stageSubmittedToJson$1(g, properties);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void stageCompletedToJson(final SparkListenerStageCompleted stageCompleted, final JsonGenerator g, final JsonProtocolOptions options) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageCompleted());
      g.writeFieldName("Stage Info");
      this.stageInfoToJson(stageCompleted.stageInfo(), g, options, true);
      g.writeEndObject();
   }

   public void taskStartToJson(final SparkListenerTaskStart taskStart, final JsonGenerator g, final JsonProtocolOptions options) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskStart());
      g.writeNumberField("Stage ID", taskStart.stageId());
      g.writeNumberField("Stage Attempt ID", taskStart.stageAttemptId());
      g.writeFieldName("Task Info");
      this.taskInfoToJson(taskStart.taskInfo(), g, options, false);
      g.writeEndObject();
   }

   public void taskGettingResultToJson(final SparkListenerTaskGettingResult taskGettingResult, final JsonGenerator g, final JsonProtocolOptions options) {
      TaskInfo taskInfo = taskGettingResult.taskInfo();
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskGettingResult());
      g.writeFieldName("Task Info");
      this.taskInfoToJson(taskInfo, g, options, false);
      g.writeEndObject();
   }

   public void taskEndToJson(final SparkListenerTaskEnd taskEnd, final JsonGenerator g, final JsonProtocolOptions options) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskEnd());
      g.writeNumberField("Stage ID", taskEnd.stageId());
      g.writeNumberField("Stage Attempt ID", taskEnd.stageAttemptId());
      g.writeStringField("Task Type", taskEnd.taskType());
      g.writeFieldName("Task End Reason");
      this.taskEndReasonToJson(taskEnd.reason(), g);
      g.writeFieldName("Task Info");
      this.taskInfoToJson(taskEnd.taskInfo(), g, options, true);
      g.writeFieldName("Task Executor Metrics");
      this.executorMetricsToJson(taskEnd.taskExecutorMetrics(), g);
      scala.Option..MODULE$.apply(taskEnd.taskMetrics()).foreach((m) -> {
         $anonfun$taskEndToJson$1(g, m);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void jobStartToJson(final SparkListenerJobStart jobStart, final JsonGenerator g, final JsonProtocolOptions options) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.jobStart());
      g.writeNumberField("Job ID", jobStart.jobId());
      g.writeNumberField("Submission Time", jobStart.time());
      g.writeArrayFieldStart("Stage Infos");
      jobStart.stageInfos().foreach((x$1) -> {
         $anonfun$jobStartToJson$1(g, options, x$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
      g.writeArrayFieldStart("Stage IDs");
      jobStart.stageIds().foreach((JFunction1.mcVI.sp)(x$1) -> g.writeNumber(x$1));
      g.writeEndArray();
      scala.Option..MODULE$.apply(jobStart.properties()).foreach((properties) -> {
         $anonfun$jobStartToJson$3(g, properties);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void jobEndToJson(final SparkListenerJobEnd jobEnd, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.jobEnd());
      g.writeNumberField("Job ID", jobEnd.jobId());
      g.writeNumberField("Completion Time", jobEnd.time());
      g.writeFieldName("Job Result");
      this.jobResultToJson(jobEnd.jobResult(), g);
      g.writeEndObject();
   }

   public void environmentUpdateToJson(final SparkListenerEnvironmentUpdate environmentUpdate, final JsonGenerator g) {
      Map environmentDetails = environmentUpdate.environmentDetails();
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.environmentUpdate());
      this.writeMapField("JVM Information", ((IterableOnceOps)environmentDetails.apply("JVM Information")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      this.writeMapField("Spark Properties", ((IterableOnceOps)environmentDetails.apply("Spark Properties")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      this.writeMapField("Hadoop Properties", ((IterableOnceOps)environmentDetails.apply("Hadoop Properties")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      this.writeMapField("System Properties", ((IterableOnceOps)environmentDetails.apply("System Properties")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      this.writeMapField("Metrics Properties", ((IterableOnceOps)environmentDetails.apply("Metrics Properties")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      this.writeMapField("Classpath Entries", ((IterableOnceOps)environmentDetails.apply("Classpath Entries")).toMap(scala..less.colon.less..MODULE$.refl()), g);
      g.writeEndObject();
   }

   public void blockManagerAddedToJson(final SparkListenerBlockManagerAdded blockManagerAdded, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockManagerAdded());
      g.writeFieldName("Block Manager ID");
      this.blockManagerIdToJson(blockManagerAdded.blockManagerId(), g);
      g.writeNumberField("Maximum Memory", blockManagerAdded.maxMem());
      g.writeNumberField("Timestamp", blockManagerAdded.time());
      blockManagerAdded.maxOnHeapMem().foreach((JFunction1.mcVJ.sp)(x$2) -> g.writeNumberField("Maximum Onheap Memory", x$2));
      blockManagerAdded.maxOffHeapMem().foreach((JFunction1.mcVJ.sp)(x$3) -> g.writeNumberField("Maximum Offheap Memory", x$3));
      g.writeEndObject();
   }

   public void blockManagerRemovedToJson(final SparkListenerBlockManagerRemoved blockManagerRemoved, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockManagerRemoved());
      g.writeFieldName("Block Manager ID");
      this.blockManagerIdToJson(blockManagerRemoved.blockManagerId(), g);
      g.writeNumberField("Timestamp", blockManagerRemoved.time());
      g.writeEndObject();
   }

   public void unpersistRDDToJson(final SparkListenerUnpersistRDD unpersistRDD, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.unpersistRDD());
      g.writeNumberField("RDD ID", unpersistRDD.rddId());
      g.writeEndObject();
   }

   public void applicationStartToJson(final SparkListenerApplicationStart applicationStart, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.applicationStart());
      g.writeStringField("App Name", applicationStart.appName());
      applicationStart.appId().foreach((x$4) -> {
         $anonfun$applicationStartToJson$1(g, x$4);
         return BoxedUnit.UNIT;
      });
      g.writeNumberField("Timestamp", applicationStart.time());
      g.writeStringField("User", applicationStart.sparkUser());
      applicationStart.appAttemptId().foreach((x$5) -> {
         $anonfun$applicationStartToJson$2(g, x$5);
         return BoxedUnit.UNIT;
      });
      applicationStart.driverLogs().foreach((x$6) -> {
         $anonfun$applicationStartToJson$3(g, x$6);
         return BoxedUnit.UNIT;
      });
      applicationStart.driverAttributes().foreach((x$7) -> {
         $anonfun$applicationStartToJson$4(g, x$7);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void applicationEndToJson(final SparkListenerApplicationEnd applicationEnd, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.applicationEnd());
      g.writeNumberField("Timestamp", applicationEnd.time());
      applicationEnd.exitCode().foreach((JFunction1.mcVI.sp)(exitCode) -> g.writeNumberField("ExitCode", exitCode));
      g.writeEndObject();
   }

   public void resourceProfileAddedToJson(final SparkListenerResourceProfileAdded profileAdded, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.resourceProfileAdded());
      g.writeNumberField("Resource Profile Id", profileAdded.resourceProfile().id());
      g.writeFieldName("Executor Resource Requests");
      this.executorResourceRequestMapToJson(profileAdded.resourceProfile().executorResources(), g);
      g.writeFieldName("Task Resource Requests");
      this.taskResourceRequestMapToJson(profileAdded.resourceProfile().taskResources(), g);
      g.writeEndObject();
   }

   public void executorAddedToJson(final SparkListenerExecutorAdded executorAdded, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.executorAdded());
      g.writeNumberField("Timestamp", executorAdded.time());
      g.writeStringField("Executor ID", executorAdded.executorId());
      g.writeFieldName("Executor Info");
      this.executorInfoToJson(executorAdded.executorInfo(), g);
      g.writeEndObject();
   }

   public void executorRemovedToJson(final SparkListenerExecutorRemoved executorRemoved, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.executorRemoved());
      g.writeNumberField("Timestamp", executorRemoved.time());
      g.writeStringField("Executor ID", executorRemoved.executorId());
      g.writeStringField("Removed Reason", executorRemoved.reason());
      g.writeEndObject();
   }

   public void logStartToJson(final SparkListenerLogStart logStart, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.logStart());
      g.writeStringField("Spark Version", org.apache.spark.package$.MODULE$.SPARK_VERSION());
      g.writeEndObject();
   }

   public void executorMetricsUpdateToJson(final SparkListenerExecutorMetricsUpdate metricsUpdate, final JsonGenerator g) {
      String execId = metricsUpdate.execId();
      Seq accumUpdates = metricsUpdate.accumUpdates();
      Map executorUpdates = metricsUpdate.executorUpdates();
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.metricsUpdate());
      g.writeStringField("Executor ID", execId);
      g.writeArrayFieldStart("Metrics Updated");
      accumUpdates.foreach((x0$1) -> {
         $anonfun$executorMetricsUpdateToJson$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
      g.writeArrayFieldStart("Executor Metrics Updated");
      executorUpdates.foreach((x0$2) -> {
         $anonfun$executorMetricsUpdateToJson$2(g, x0$2);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
      g.writeEndObject();
   }

   public void stageExecutorMetricsToJson(final SparkListenerStageExecutorMetrics metrics, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageExecutorMetrics());
      g.writeStringField("Executor ID", metrics.execId());
      g.writeNumberField("Stage ID", metrics.stageId());
      g.writeNumberField("Stage Attempt ID", metrics.stageAttemptId());
      g.writeFieldName("Executor Metrics");
      this.executorMetricsToJson(metrics.executorMetrics(), g);
      g.writeEndObject();
   }

   public void blockUpdateToJson(final SparkListenerBlockUpdated blockUpdate, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Event", JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockUpdate());
      g.writeFieldName("Block Updated Info");
      this.blockUpdatedInfoToJson(blockUpdate.blockUpdatedInfo(), g);
      g.writeEndObject();
   }

   public void stageInfoToJson(final StageInfo stageInfo, final JsonGenerator g, final JsonProtocolOptions options, final boolean includeAccumulables) {
      g.writeStartObject();
      g.writeNumberField("Stage ID", stageInfo.stageId());
      g.writeNumberField("Stage Attempt ID", stageInfo.attemptNumber());
      g.writeStringField("Stage Name", stageInfo.name());
      g.writeNumberField("Number of Tasks", stageInfo.numTasks());
      g.writeArrayFieldStart("RDD Info");
      stageInfo.rddInfos().foreach((x$8) -> {
         $anonfun$stageInfoToJson$1(g, x$8);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
      g.writeArrayFieldStart("Parent IDs");
      stageInfo.parentIds().foreach((JFunction1.mcVI.sp)(x$1) -> g.writeNumber(x$1));
      g.writeEndArray();
      g.writeStringField("Details", stageInfo.details());
      stageInfo.submissionTime().foreach((JFunction1.mcVJ.sp)(x$9) -> g.writeNumberField("Submission Time", x$9));
      stageInfo.completionTime().foreach((JFunction1.mcVJ.sp)(x$10) -> g.writeNumberField("Completion Time", x$10));
      stageInfo.failureReason().foreach((x$11) -> {
         $anonfun$stageInfoToJson$5(g, x$11);
         return BoxedUnit.UNIT;
      });
      g.writeFieldName("Accumulables");
      if (includeAccumulables) {
         this.accumulablesToJson(stageInfo.accumulables().values(), g, options.includeTaskMetricsAccumulators());
      } else {
         g.writeStartArray();
         g.writeEndArray();
      }

      g.writeNumberField("Resource Profile Id", stageInfo.resourceProfileId());
      g.writeBooleanField("Shuffle Push Enabled", stageInfo.isShufflePushEnabled());
      g.writeNumberField("Shuffle Push Mergers Count", stageInfo.shuffleMergerCount());
      g.writeEndObject();
   }

   public void taskInfoToJson(final TaskInfo taskInfo, final JsonGenerator g, final JsonProtocolOptions options, final boolean includeAccumulables) {
      g.writeStartObject();
      g.writeNumberField("Task ID", taskInfo.taskId());
      g.writeNumberField("Index", taskInfo.index());
      g.writeNumberField("Attempt", taskInfo.attemptNumber());
      g.writeNumberField("Partition ID", taskInfo.partitionId());
      g.writeNumberField("Launch Time", taskInfo.launchTime());
      g.writeStringField("Executor ID", taskInfo.executorId());
      g.writeStringField("Host", taskInfo.host());
      g.writeStringField("Locality", taskInfo.taskLocality().toString());
      g.writeBooleanField("Speculative", taskInfo.speculative());
      g.writeNumberField("Getting Result Time", taskInfo.gettingResultTime());
      g.writeNumberField("Finish Time", taskInfo.finishTime());
      g.writeBooleanField("Failed", taskInfo.failed());
      g.writeBooleanField("Killed", taskInfo.killed());
      g.writeFieldName("Accumulables");
      if (includeAccumulables) {
         this.accumulablesToJson(taskInfo.accumulables(), g, options.includeTaskMetricsAccumulators());
      } else {
         g.writeStartArray();
         g.writeEndArray();
      }

      g.writeEndObject();
   }

   public Set accumulableExcludeList() {
      return accumulableExcludeList;
   }

   public void accumulablesToJson(final Iterable accumulables, final JsonGenerator g, final boolean includeTaskMetricsAccumulators) {
      g.writeStartArray();
      ((List)((IterableOnceOps)accumulables.filterNot((acc) -> BoxesRunTime.boxToBoolean($anonfun$accumulablesToJson$1(includeTaskMetricsAccumulators, acc)))).toList().sortBy((x$12) -> BoxesRunTime.boxToLong($anonfun$accumulablesToJson$4(x$12)), scala.math.Ordering.Long..MODULE$)).foreach((a) -> {
         $anonfun$accumulablesToJson$5(g, a);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
   }

   public boolean accumulablesToJson$default$3() {
      return true;
   }

   public void accumulableInfoToJson(final AccumulableInfo accumulableInfo, final JsonGenerator g) {
      Option name = accumulableInfo.name();
      g.writeStartObject();
      g.writeNumberField("ID", accumulableInfo.id());
      name.foreach((x$13) -> {
         $anonfun$accumulableInfoToJson$1(g, x$13);
         return BoxedUnit.UNIT;
      });
      accumulableInfo.update().foreach((v) -> {
         $anonfun$accumulableInfoToJson$2(name, g, v);
         return BoxedUnit.UNIT;
      });
      accumulableInfo.value().foreach((v) -> {
         $anonfun$accumulableInfoToJson$3(name, g, v);
         return BoxedUnit.UNIT;
      });
      g.writeBooleanField("Internal", accumulableInfo.internal());
      g.writeBooleanField("Count Failed Values", accumulableInfo.countFailedValues());
      accumulableInfo.metadata().foreach((x$14) -> {
         $anonfun$accumulableInfoToJson$4(g, x$14);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void accumValueToJson(final Option name, final Object value, final JsonGenerator g, final Option fieldName) {
      if (name.exists((x$15) -> BoxesRunTime.boxToBoolean($anonfun$accumValueToJson$1(x$15)))) {
         if (value instanceof Integer) {
            int var7 = BoxesRunTime.unboxToInt(value);
            fieldName.foreach((x$1) -> {
               $anonfun$accumValueToJson$2(g, x$1);
               return BoxedUnit.UNIT;
            });
            g.writeNumber(var7);
            BoxedUnit var13 = BoxedUnit.UNIT;
         } else if (value instanceof Long) {
            long var8 = BoxesRunTime.unboxToLong(value);
            fieldName.foreach((x$1) -> {
               $anonfun$accumValueToJson$3(g, x$1);
               return BoxedUnit.UNIT;
            });
            g.writeNumber(var8);
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else if (value instanceof java.util.List) {
            java.util.List var10 = (java.util.List)value;
            fieldName.foreach((x$1) -> {
               $anonfun$accumValueToJson$4(g, x$1);
               return BoxedUnit.UNIT;
            });
            g.writeStartArray();
            scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var10).asScala().foreach((x0$1) -> {
               $anonfun$accumValueToJson$5(g, x0$1);
               return BoxedUnit.UNIT;
            });
            g.writeEndArray();
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         fieldName.foreach((x$1) -> {
            $anonfun$accumValueToJson$6(g, x$1);
            return BoxedUnit.UNIT;
         });
         g.writeString(value.toString());
      }
   }

   public Option accumValueToJson$default$4() {
      return scala.None..MODULE$;
   }

   public void taskMetricsToJson(final TaskMetrics taskMetrics, final JsonGenerator g) {
      g.writeStartObject();
      g.writeNumberField("Executor Deserialize Time", taskMetrics.executorDeserializeTime());
      g.writeNumberField("Executor Deserialize CPU Time", taskMetrics.executorDeserializeCpuTime());
      g.writeNumberField("Executor Run Time", taskMetrics.executorRunTime());
      g.writeNumberField("Executor CPU Time", taskMetrics.executorCpuTime());
      g.writeNumberField("Peak Execution Memory", taskMetrics.peakExecutionMemory());
      g.writeNumberField("Peak On Heap Execution Memory", taskMetrics.peakOnHeapExecutionMemory());
      g.writeNumberField("Peak Off Heap Execution Memory", taskMetrics.peakOffHeapExecutionMemory());
      g.writeNumberField("Result Size", taskMetrics.resultSize());
      g.writeNumberField("JVM GC Time", taskMetrics.jvmGCTime());
      g.writeNumberField("Result Serialization Time", taskMetrics.resultSerializationTime());
      g.writeNumberField("Memory Bytes Spilled", taskMetrics.memoryBytesSpilled());
      g.writeNumberField("Disk Bytes Spilled", taskMetrics.diskBytesSpilled());
      g.writeFieldName("Shuffle Read Metrics");
      writeShuffleReadMetrics$1(g, taskMetrics);
      g.writeFieldName("Shuffle Write Metrics");
      writeShuffleWriteMetrics$1(g, taskMetrics);
      g.writeFieldName("Input Metrics");
      writeInputMetrics$1(g, taskMetrics);
      g.writeFieldName("Output Metrics");
      writeOutputMetrics$1(g, taskMetrics);
      g.writeFieldName("Updated Blocks");
      writeUpdatedBlocks$1(g, taskMetrics);
      g.writeEndObject();
   }

   public void executorMetricsToJson(final ExecutorMetrics executorMetrics, final JsonGenerator g) {
      g.writeStartObject();
      ExecutorMetricType$.MODULE$.metricToOffset().foreach((x0$1) -> {
         $anonfun$executorMetricsToJson$1(g, executorMetrics, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void taskEndReasonToJson(final TaskEndReason taskEndReason, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Reason", Utils$.MODULE$.getFormattedClassName(taskEndReason));
      if (taskEndReason instanceof FetchFailed var5) {
         scala.Option..MODULE$.apply(var5.bmAddress()).foreach((id) -> {
            $anonfun$taskEndReasonToJson$1(g, id);
            return BoxedUnit.UNIT;
         });
         g.writeNumberField("Shuffle ID", var5.shuffleId());
         g.writeNumberField("Map ID", var5.mapId());
         g.writeNumberField("Map Index", var5.mapIndex());
         g.writeNumberField("Reduce ID", var5.reduceId());
         g.writeStringField("Message", var5.message());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (taskEndReason instanceof ExceptionFailure var6) {
         g.writeStringField("Class Name", var6.className());
         g.writeStringField("Description", var6.description());
         g.writeFieldName("Stack Trace");
         this.stackTraceToJson(var6.stackTrace(), g);
         g.writeStringField("Full Stack Trace", var6.fullStackTrace());
         g.writeFieldName("Accumulator Updates");
         this.accumulablesToJson(var6.accumUpdates(), g, this.accumulablesToJson$default$3());
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (taskEndReason instanceof TaskCommitDenied var7) {
         g.writeNumberField("Job ID", var7.jobID());
         g.writeNumberField("Partition ID", var7.partitionID());
         g.writeNumberField("Attempt Number", var7.attemptNumber());
         BoxedUnit var14 = BoxedUnit.UNIT;
      } else if (taskEndReason instanceof ExecutorLostFailure var8) {
         String executorId = var8.execId();
         boolean exitCausedByApp = var8.exitCausedByApp();
         Option reason = var8.reason();
         g.writeStringField("Executor ID", executorId);
         g.writeBooleanField("Exit Caused By App", exitCausedByApp);
         reason.foreach((x$16) -> {
            $anonfun$taskEndReasonToJson$2(g, x$16);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var15 = BoxedUnit.UNIT;
      } else if (taskEndReason instanceof TaskKilled var12) {
         g.writeStringField("Kill Reason", var12.reason());
         g.writeFieldName("Accumulator Updates");
         this.accumulablesToJson(var12.accumUpdates(), g, this.accumulablesToJson$default$3());
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var17 = BoxedUnit.UNIT;
      }

      g.writeEndObject();
   }

   public void blockManagerIdToJson(final BlockManagerId blockManagerId, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Executor ID", blockManagerId.executorId());
      g.writeStringField("Host", blockManagerId.host());
      g.writeNumberField("Port", blockManagerId.port());
      g.writeEndObject();
   }

   public void jobResultToJson(final JobResult jobResult, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Result", Utils$.MODULE$.getFormattedClassName(jobResult));
      if (jobResult instanceof JobFailed var5) {
         g.writeFieldName("Exception");
         this.exceptionToJson(var5.exception(), g);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!JobSucceeded$.MODULE$.equals(jobResult)) {
            throw new MatchError(jobResult);
         }

         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      g.writeEndObject();
   }

   public void rddInfoToJson(final RDDInfo rddInfo, final JsonGenerator g) {
      g.writeStartObject();
      g.writeNumberField("RDD ID", rddInfo.id());
      g.writeStringField("Name", rddInfo.name());
      rddInfo.scope().foreach((s) -> {
         $anonfun$rddInfoToJson$1(g, s);
         return BoxedUnit.UNIT;
      });
      g.writeStringField("Callsite", rddInfo.callSite());
      g.writeArrayFieldStart("Parent IDs");
      rddInfo.parentIds().foreach((JFunction1.mcVI.sp)(x$1) -> g.writeNumber(x$1));
      g.writeEndArray();
      g.writeFieldName("Storage Level");
      this.storageLevelToJson(rddInfo.storageLevel(), g);
      g.writeBooleanField("Barrier", rddInfo.isBarrier());
      g.writeStringField("DeterministicLevel", rddInfo.outputDeterministicLevel().toString());
      g.writeNumberField("Number of Partitions", rddInfo.numPartitions());
      g.writeNumberField("Number of Cached Partitions", rddInfo.numCachedPartitions());
      g.writeNumberField("Memory Size", rddInfo.memSize());
      g.writeNumberField("Disk Size", rddInfo.diskSize());
      g.writeEndObject();
   }

   public void storageLevelToJson(final StorageLevel storageLevel, final JsonGenerator g) {
      g.writeStartObject();
      g.writeBooleanField("Use Disk", storageLevel.useDisk());
      g.writeBooleanField("Use Memory", storageLevel.useMemory());
      g.writeBooleanField("Use Off Heap", storageLevel.useOffHeap());
      g.writeBooleanField("Deserialized", storageLevel.deserialized());
      g.writeNumberField("Replication", storageLevel.replication());
      g.writeEndObject();
   }

   public void blockStatusToJson(final BlockStatus blockStatus, final JsonGenerator g) {
      g.writeStartObject();
      g.writeFieldName("Storage Level");
      this.storageLevelToJson(blockStatus.storageLevel(), g);
      g.writeNumberField("Memory Size", blockStatus.memSize());
      g.writeNumberField("Disk Size", blockStatus.diskSize());
      g.writeEndObject();
   }

   public void executorInfoToJson(final ExecutorInfo executorInfo, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Host", executorInfo.executorHost());
      g.writeNumberField("Total Cores", executorInfo.totalCores());
      this.writeMapField("Log Urls", executorInfo.logUrlMap(), g);
      this.writeMapField("Attributes", executorInfo.attributes(), g);
      g.writeObjectFieldStart("Resources");
      executorInfo.resourcesInfo().foreach((x0$1) -> {
         $anonfun$executorInfoToJson$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
      g.writeNumberField("Resource Profile Id", executorInfo.resourceProfileId());
      executorInfo.registrationTime().foreach((JFunction1.mcVJ.sp)(x$17) -> g.writeNumberField("Registration Time", x$17));
      executorInfo.requestTime().foreach((JFunction1.mcVJ.sp)(x$18) -> g.writeNumberField("Request Time", x$18));
      g.writeEndObject();
   }

   public void blockUpdatedInfoToJson(final BlockUpdatedInfo blockUpdatedInfo, final JsonGenerator g) {
      g.writeStartObject();
      g.writeFieldName("Block Manager ID");
      this.blockManagerIdToJson(blockUpdatedInfo.blockManagerId(), g);
      g.writeStringField("Block ID", blockUpdatedInfo.blockId().toString());
      g.writeFieldName("Storage Level");
      this.storageLevelToJson(blockUpdatedInfo.storageLevel(), g);
      g.writeNumberField("Memory Size", blockUpdatedInfo.memSize());
      g.writeNumberField("Disk Size", blockUpdatedInfo.diskSize());
      g.writeEndObject();
   }

   public void executorResourceRequestToJson(final ExecutorResourceRequest execReq, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Resource Name", execReq.resourceName());
      g.writeNumberField("Amount", execReq.amount());
      g.writeStringField("Discovery Script", execReq.discoveryScript());
      g.writeStringField("Vendor", execReq.vendor());
      g.writeEndObject();
   }

   public void executorResourceRequestMapToJson(final Map m, final JsonGenerator g) {
      g.writeStartObject();
      m.foreach((x0$1) -> {
         $anonfun$executorResourceRequestMapToJson$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void taskResourceRequestToJson(final TaskResourceRequest taskReq, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Resource Name", taskReq.resourceName());
      g.writeNumberField("Amount", taskReq.amount());
      g.writeEndObject();
   }

   public void taskResourceRequestMapToJson(final Map m, final JsonGenerator g) {
      g.writeStartObject();
      m.foreach((x0$1) -> {
         $anonfun$taskResourceRequestMapToJson$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void writeMapField(final String name, final Map m, final JsonGenerator g) {
      g.writeObjectFieldStart(name);
      m.foreach((x0$1) -> {
         $anonfun$writeMapField$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void propertiesToJson(final Properties properties, final JsonGenerator g) {
      g.writeStartObject();
      scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(properties).asScala().foreach((x0$1) -> {
         $anonfun$propertiesToJson$1(g, x0$1);
         return BoxedUnit.UNIT;
      });
      g.writeEndObject();
   }

   public void UUIDToJson(final UUID id, final JsonGenerator g) {
      g.writeStartObject();
      g.writeNumberField("Least Significant Bits", id.getLeastSignificantBits());
      g.writeNumberField("Most Significant Bits", id.getMostSignificantBits());
      g.writeEndObject();
   }

   public void stackTraceToJson(final StackTraceElement[] stackTrace, final JsonGenerator g) {
      g.writeStartArray();
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])stackTrace), (line) -> {
         $anonfun$stackTraceToJson$1(g, line);
         return BoxedUnit.UNIT;
      });
      g.writeEndArray();
   }

   public void exceptionToJson(final Exception exception, final JsonGenerator g) {
      g.writeStartObject();
      g.writeStringField("Message", exception.getMessage());
      g.writeFieldName("Stack Trace");
      this.stackTraceToJson(exception.getStackTrace(), g);
      g.writeEndObject();
   }

   public SparkListenerEvent sparkEventFromJson(final String json) {
      return this.sparkEventFromJson(this.mapper().readTree(json));
   }

   public SparkListenerEvent sparkEventFromJson(final JsonNode json) {
      String var3 = json.get("Event").asText();
      String var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageSubmitted();
      if (var10000 == null) {
         if (var3 == null) {
            return this.stageSubmittedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.stageSubmittedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageCompleted();
      if (var10000 == null) {
         if (var3 == null) {
            return this.stageCompletedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.stageCompletedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskStart();
      if (var10000 == null) {
         if (var3 == null) {
            return this.taskStartFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.taskStartFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskGettingResult();
      if (var10000 == null) {
         if (var3 == null) {
            return this.taskGettingResultFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.taskGettingResultFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.taskEnd();
      if (var10000 == null) {
         if (var3 == null) {
            return this.taskEndFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.taskEndFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.jobStart();
      if (var10000 == null) {
         if (var3 == null) {
            return this.jobStartFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.jobStartFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.jobEnd();
      if (var10000 == null) {
         if (var3 == null) {
            return this.jobEndFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.jobEndFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.environmentUpdate();
      if (var10000 == null) {
         if (var3 == null) {
            return this.environmentUpdateFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.environmentUpdateFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockManagerAdded();
      if (var10000 == null) {
         if (var3 == null) {
            return this.blockManagerAddedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.blockManagerAddedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockManagerRemoved();
      if (var10000 == null) {
         if (var3 == null) {
            return this.blockManagerRemovedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.blockManagerRemovedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.unpersistRDD();
      if (var10000 == null) {
         if (var3 == null) {
            return this.unpersistRDDFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.unpersistRDDFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.applicationStart();
      if (var10000 == null) {
         if (var3 == null) {
            return this.applicationStartFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.applicationStartFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.applicationEnd();
      if (var10000 == null) {
         if (var3 == null) {
            return this.applicationEndFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.applicationEndFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.executorAdded();
      if (var10000 == null) {
         if (var3 == null) {
            return this.executorAddedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.executorAddedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.executorRemoved();
      if (var10000 == null) {
         if (var3 == null) {
            return this.executorRemovedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.executorRemovedFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.logStart();
      if (var10000 == null) {
         if (var3 == null) {
            return this.logStartFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.logStartFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.metricsUpdate();
      if (var10000 == null) {
         if (var3 == null) {
            return this.executorMetricsUpdateFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.executorMetricsUpdateFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.stageExecutorMetrics();
      if (var10000 == null) {
         if (var3 == null) {
            return this.stageExecutorMetricsFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.stageExecutorMetricsFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.blockUpdate();
      if (var10000 == null) {
         if (var3 == null) {
            return this.blockUpdateFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.blockUpdateFromJson(json);
      }

      var10000 = JsonProtocol.SPARK_LISTENER_EVENT_FORMATTED_CLASS_NAMES$.MODULE$.resourceProfileAdded();
      if (var10000 == null) {
         if (var3 == null) {
            return this.resourceProfileAddedFromJson(json);
         }
      } else if (var10000.equals(var3)) {
         return this.resourceProfileAddedFromJson(json);
      }

      return (SparkListenerEvent)this.mapper().readValue(json.toString(), Utils$.MODULE$.classForName(var3, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()));
   }

   public SparkListenerStageSubmitted stageSubmittedFromJson(final JsonNode json) {
      StageInfo stageInfo = this.stageInfoFromJson(json.get("Stage Info"));
      Properties properties = this.propertiesFromJson(json.get("Properties"));
      return new SparkListenerStageSubmitted(stageInfo, properties);
   }

   public SparkListenerStageCompleted stageCompletedFromJson(final JsonNode json) {
      StageInfo stageInfo = this.stageInfoFromJson(json.get("Stage Info"));
      return new SparkListenerStageCompleted(stageInfo);
   }

   public SparkListenerTaskStart taskStartFromJson(final JsonNode json) {
      int stageId = this.JsonNodeImplicits(json.get("Stage ID")).extractInt();
      int stageAttemptId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Stage Attempt ID")).map((x$19) -> BoxesRunTime.boxToInteger($anonfun$taskStartFromJson$1(x$19))).getOrElse((JFunction0.mcI.sp)() -> 0));
      TaskInfo taskInfo = this.taskInfoFromJson(json.get("Task Info"));
      return new SparkListenerTaskStart(stageId, stageAttemptId, taskInfo);
   }

   public SparkListenerTaskGettingResult taskGettingResultFromJson(final JsonNode json) {
      TaskInfo taskInfo = this.taskInfoFromJson(json.get("Task Info"));
      return new SparkListenerTaskGettingResult(taskInfo);
   }

   public ExecutorMetrics executorMetricsFromJson(final JsonNode maybeJson) {
      LinkedHashMap metrics = (LinkedHashMap)ExecutorMetricType$.MODULE$.metricToOffset().map((x0$1) -> {
         if (x0$1 != null) {
            String metric = (String)x0$1._1();
            Option metricValueJson = MODULE$.jsonOption(maybeJson).flatMap((json) -> MODULE$.jsonOption(json.get(metric)));
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(metric), metricValueJson.map((x$20) -> BoxesRunTime.boxToLong($anonfun$executorMetricsFromJson$3(x$20))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new ExecutorMetrics(metrics.toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public SparkListenerTaskEnd taskEndFromJson(final JsonNode json) {
      int stageId = this.JsonNodeImplicits(json.get("Stage ID")).extractInt();
      int stageAttemptId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Stage Attempt ID")).map((x$21) -> BoxesRunTime.boxToInteger($anonfun$taskEndFromJson$1(x$21))).getOrElse((JFunction0.mcI.sp)() -> 0));
      String taskType = this.JsonNodeImplicits(json.get("Task Type")).extractString();
      TaskEndReason taskEndReason = this.taskEndReasonFromJson(json.get("Task End Reason"));
      TaskInfo taskInfo = this.taskInfoFromJson(json.get("Task Info"));
      ExecutorMetrics executorMetrics = this.executorMetricsFromJson(json.get("Task Executor Metrics"));
      TaskMetrics taskMetrics = this.taskMetricsFromJson(json.get("Task Metrics"));
      return new SparkListenerTaskEnd(stageId, stageAttemptId, taskType, taskEndReason, taskInfo, executorMetrics, taskMetrics);
   }

   public SparkListenerJobStart jobStartFromJson(final JsonNode json) {
      int jobId = this.JsonNodeImplicits(json.get("Job ID")).extractInt();
      long submissionTime = BoxesRunTime.unboxToLong(this.jsonOption(json.get("Submission Time")).map((x$22) -> BoxesRunTime.boxToLong($anonfun$jobStartFromJson$1(x$22))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
      ArraySeq stageIds = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.JsonNodeImplicits(json.get("Stage IDs")).extractElements().map((x$23) -> BoxesRunTime.boxToInteger($anonfun$jobStartFromJson$3(x$23))).toArray(scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq();
      Properties properties = this.propertiesFromJson(json.get("Properties"));
      ArraySeq stageInfos = (ArraySeq)this.jsonOption(json.get("Stage Infos")).map((x$24) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(x$24).extractElements().map((json) -> MODULE$.stageInfoFromJson(json)).toArray(scala.reflect.ClassTag..MODULE$.apply(StageInfo.class))).toImmutableArraySeq()).getOrElse(() -> stageIds.map((id) -> $anonfun$jobStartFromJson$7(BoxesRunTime.unboxToInt(id))));
      return new SparkListenerJobStart(jobId, submissionTime, stageInfos, properties);
   }

   public SparkListenerJobEnd jobEndFromJson(final JsonNode json) {
      int jobId = this.JsonNodeImplicits(json.get("Job ID")).extractInt();
      long completionTime = BoxesRunTime.unboxToLong(this.jsonOption(json.get("Completion Time")).map((x$25) -> BoxesRunTime.boxToLong($anonfun$jobEndFromJson$1(x$25))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
      JobResult jobResult = this.jobResultFromJson(json.get("Job Result"));
      return new SparkListenerJobEnd(jobId, completionTime, jobResult);
   }

   public SparkListenerResourceProfileAdded resourceProfileAddedFromJson(final JsonNode json) {
      int profId = this.JsonNodeImplicits(json.get("Resource Profile Id")).extractInt();
      Map executorReqs = this.executorResourceRequestMapFromJson(json.get("Executor Resource Requests"));
      Map taskReqs = this.taskResourceRequestMapFromJson(json.get("Task Resource Requests"));
      ResourceProfile rp = new ResourceProfile(executorReqs.toMap(scala..less.colon.less..MODULE$.refl()), taskReqs.toMap(scala..less.colon.less..MODULE$.refl()));
      rp.setResourceProfileId(profId);
      return new SparkListenerResourceProfileAdded(rp);
   }

   public ExecutorResourceRequest executorResourceRequestFromJson(final JsonNode json) {
      String rName = this.JsonNodeImplicits(json.get("Resource Name")).extractString();
      long amount = this.JsonNodeImplicits(json.get("Amount")).extractLong();
      String discoveryScript = this.JsonNodeImplicits(json.get("Discovery Script")).extractString();
      String vendor = this.JsonNodeImplicits(json.get("Vendor")).extractString();
      return new ExecutorResourceRequest(rName, amount, discoveryScript, vendor);
   }

   public TaskResourceRequest taskResourceRequestFromJson(final JsonNode json) {
      String rName = this.JsonNodeImplicits(json.get("Resource Name")).extractString();
      double amount = this.JsonNodeImplicits(json.get("Amount")).extractDouble();
      return new TaskResourceRequest(rName, amount);
   }

   public Map taskResourceRequestMapFromJson(final JsonNode json) {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(json.fields()).asScala().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final java.util.Map.Entry x1, final Function1 default) {
            TaskResourceRequest req = JsonProtocol$.MODULE$.taskResourceRequestFromJson((JsonNode)x1.getValue());
            return new Tuple2(x1.getKey(), req);
         }

         public final boolean isDefinedAt(final java.util.Map.Entry x1) {
            return true;
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Map executorResourceRequestMapFromJson(final JsonNode json) {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(json.fields()).asScala().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final java.util.Map.Entry x1, final Function1 default) {
            ExecutorResourceRequest req = JsonProtocol$.MODULE$.executorResourceRequestFromJson((JsonNode)x1.getValue());
            return new Tuple2(x1.getKey(), req);
         }

         public final boolean isDefinedAt(final java.util.Map.Entry x1) {
            return true;
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public SparkListenerEnvironmentUpdate environmentUpdateFromJson(final JsonNode json) {
      Seq hadoopProperties = (Seq)this.jsonOption(json.get("Hadoop Properties")).map((x$26) -> MODULE$.mapFromJson(x$26).toSeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      Seq metricsProperties = (Seq)this.jsonOption(json.get("Metrics Properties")).map((x$27) -> MODULE$.mapFromJson(x$27).toSeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      Map environmentDetails = (Map)scala.collection.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("JVM Information"), this.mapFromJson(json.get("JVM Information")).toSeq()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Spark Properties"), this.mapFromJson(json.get("Spark Properties")).toSeq()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Hadoop Properties"), hadoopProperties), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("System Properties"), this.mapFromJson(json.get("System Properties")).toSeq()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Metrics Properties"), metricsProperties), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Classpath Entries"), this.mapFromJson(json.get("Classpath Entries")).toSeq())})));
      return new SparkListenerEnvironmentUpdate(environmentDetails);
   }

   public SparkListenerBlockManagerAdded blockManagerAddedFromJson(final JsonNode json) {
      BlockManagerId blockManagerId = this.blockManagerIdFromJson(json.get("Block Manager ID"));
      long maxMem = this.JsonNodeImplicits(json.get("Maximum Memory")).extractLong();
      long time = BoxesRunTime.unboxToLong(this.jsonOption(json.get("Timestamp")).map((x$28) -> BoxesRunTime.boxToLong($anonfun$blockManagerAddedFromJson$1(x$28))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
      Option maxOnHeapMem = this.jsonOption(json.get("Maximum Onheap Memory")).map((x$29) -> BoxesRunTime.boxToLong($anonfun$blockManagerAddedFromJson$3(x$29)));
      Option maxOffHeapMem = this.jsonOption(json.get("Maximum Offheap Memory")).map((x$30) -> BoxesRunTime.boxToLong($anonfun$blockManagerAddedFromJson$4(x$30)));
      return new SparkListenerBlockManagerAdded(time, blockManagerId, maxMem, maxOnHeapMem, maxOffHeapMem);
   }

   public SparkListenerBlockManagerRemoved blockManagerRemovedFromJson(final JsonNode json) {
      BlockManagerId blockManagerId = this.blockManagerIdFromJson(json.get("Block Manager ID"));
      long time = BoxesRunTime.unboxToLong(this.jsonOption(json.get("Timestamp")).map((x$31) -> BoxesRunTime.boxToLong($anonfun$blockManagerRemovedFromJson$1(x$31))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
      return new SparkListenerBlockManagerRemoved(time, blockManagerId);
   }

   public SparkListenerUnpersistRDD unpersistRDDFromJson(final JsonNode json) {
      return new SparkListenerUnpersistRDD(this.JsonNodeImplicits(json.get("RDD ID")).extractInt());
   }

   public SparkListenerApplicationStart applicationStartFromJson(final JsonNode json) {
      String appName = this.JsonNodeImplicits(json.get("App Name")).extractString();
      Option appId = this.jsonOption(json.get("App ID")).map((x$32) -> x$32.asText());
      long time = this.JsonNodeImplicits(json.get("Timestamp")).extractLong();
      String sparkUser = this.JsonNodeImplicits(json.get("User")).extractString();
      Option appAttemptId = this.jsonOption(json.get("App Attempt ID")).map((x$33) -> x$33.asText());
      Option driverLogs = this.jsonOption(json.get("Driver Logs")).map((jsonx) -> MODULE$.mapFromJson(jsonx));
      Option driverAttributes = this.jsonOption(json.get("Driver Attributes")).map((jsonx) -> MODULE$.mapFromJson(jsonx));
      return new SparkListenerApplicationStart(appName, appId, time, sparkUser, appAttemptId, driverLogs, driverAttributes);
   }

   public SparkListenerApplicationEnd applicationEndFromJson(final JsonNode json) {
      Option exitCode = this.jsonOption(json.get("ExitCode")).map((x$34) -> BoxesRunTime.boxToInteger($anonfun$applicationEndFromJson$1(x$34)));
      return new SparkListenerApplicationEnd(this.JsonNodeImplicits(json.get("Timestamp")).extractLong(), exitCode);
   }

   public SparkListenerExecutorAdded executorAddedFromJson(final JsonNode json) {
      long time = this.JsonNodeImplicits(json.get("Timestamp")).extractLong();
      String executorId = this.JsonNodeImplicits(json.get("Executor ID")).extractString();
      ExecutorInfo executorInfo = this.executorInfoFromJson(json.get("Executor Info"));
      return new SparkListenerExecutorAdded(time, executorId, executorInfo);
   }

   public SparkListenerExecutorRemoved executorRemovedFromJson(final JsonNode json) {
      long time = this.JsonNodeImplicits(json.get("Timestamp")).extractLong();
      String executorId = this.JsonNodeImplicits(json.get("Executor ID")).extractString();
      String reason = this.JsonNodeImplicits(json.get("Removed Reason")).extractString();
      return new SparkListenerExecutorRemoved(time, executorId, reason);
   }

   public SparkListenerLogStart logStartFromJson(final JsonNode json) {
      String sparkVersion = this.JsonNodeImplicits(json.get("Spark Version")).extractString();
      return new SparkListenerLogStart(sparkVersion);
   }

   public SparkListenerExecutorMetricsUpdate executorMetricsUpdateFromJson(final JsonNode json) {
      String execInfo = this.JsonNodeImplicits(json.get("Executor ID")).extractString();
      ArraySeq accumUpdates = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.JsonNodeImplicits(json.get("Metrics Updated")).extractElements().map((jsonx) -> {
         long taskId = MODULE$.JsonNodeImplicits(jsonx.get("Task ID")).extractLong();
         int stageId = MODULE$.JsonNodeImplicits(jsonx.get("Stage ID")).extractInt();
         int stageAttemptId = MODULE$.JsonNodeImplicits(jsonx.get("Stage Attempt ID")).extractInt();
         ArraySeq updates = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(jsonx.get("Accumulator Updates")).extractElements().map((json) -> MODULE$.accumulableInfoFromJson(json)).toArray(scala.reflect.ClassTag..MODULE$.apply(AccumulableInfo.class))).toImmutableArraySeq();
         return new Tuple4(BoxesRunTime.boxToLong(taskId), BoxesRunTime.boxToInteger(stageId), BoxesRunTime.boxToInteger(stageAttemptId), updates);
      }).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple4.class))).toImmutableArraySeq();
      Map executorUpdates = (Map)this.jsonOption(json.get("Executor Metrics Updated")).map((value) -> MODULE$.JsonNodeImplicits(value).extractElements().map((json) -> {
            int stageId = MODULE$.JsonNodeImplicits(json.get("Stage ID")).extractInt();
            int stageAttemptId = MODULE$.JsonNodeImplicits(json.get("Stage Attempt ID")).extractInt();
            ExecutorMetrics executorMetrics = MODULE$.executorMetricsFromJson(json.get("Executor Metrics"));
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(new Tuple2.mcII.sp(stageId, stageAttemptId)), executorMetrics);
         }).toMap(scala..less.colon.less..MODULE$.refl())).getOrElse(() -> (Map)scala.collection.Map..MODULE$.empty());
      return new SparkListenerExecutorMetricsUpdate(execInfo, accumUpdates, executorUpdates);
   }

   public SparkListenerStageExecutorMetrics stageExecutorMetricsFromJson(final JsonNode json) {
      String execId = this.JsonNodeImplicits(json.get("Executor ID")).extractString();
      int stageId = this.JsonNodeImplicits(json.get("Stage ID")).extractInt();
      int stageAttemptId = this.JsonNodeImplicits(json.get("Stage Attempt ID")).extractInt();
      ExecutorMetrics executorMetrics = this.executorMetricsFromJson(json.get("Executor Metrics"));
      return new SparkListenerStageExecutorMetrics(execId, stageId, stageAttemptId, executorMetrics);
   }

   public SparkListenerBlockUpdated blockUpdateFromJson(final JsonNode json) {
      BlockUpdatedInfo blockUpdatedInfo = this.blockUpdatedInfoFromJson(json.get("Block Updated Info"));
      return new SparkListenerBlockUpdated(blockUpdatedInfo);
   }

   public StageInfo stageInfoFromJson(final JsonNode json) {
      int stageId = this.JsonNodeImplicits(json.get("Stage ID")).extractInt();
      int attemptId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Stage Attempt ID")).map((x$35) -> BoxesRunTime.boxToInteger($anonfun$stageInfoFromJson$1(x$35))).getOrElse((JFunction0.mcI.sp)() -> 0));
      String stageName = this.JsonNodeImplicits(json.get("Stage Name")).extractString();
      int numTasks = this.JsonNodeImplicits(json.get("Number of Tasks")).extractInt();
      RDDInfo[] rddInfos = (RDDInfo[])this.JsonNodeImplicits(json.get("RDD Info")).extractElements().map((jsonx) -> MODULE$.rddInfoFromJson(jsonx)).toArray(scala.reflect.ClassTag..MODULE$.apply(RDDInfo.class));
      Seq parentIds = (Seq)this.jsonOption(json.get("Parent IDs")).map((l) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(l).extractElements().map((x$36) -> BoxesRunTime.boxToInteger($anonfun$stageInfoFromJson$5(x$36))).toArray(scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      String details = (String)this.jsonOption(json.get("Details")).map((x$37) -> x$37.asText()).getOrElse(() -> "");
      Option submissionTime = this.jsonOption(json.get("Submission Time")).map((x$38) -> BoxesRunTime.boxToLong($anonfun$stageInfoFromJson$9(x$38)));
      Option completionTime = this.jsonOption(json.get("Completion Time")).map((x$39) -> BoxesRunTime.boxToLong($anonfun$stageInfoFromJson$10(x$39)));
      Option failureReason = this.jsonOption(json.get("Failure Reason")).map((x$40) -> x$40.asText());
      Option var14 = this.jsonOption(json.get("Accumulables")).map((x$41) -> MODULE$.JsonNodeImplicits(x$41).extractElements());
      Object var10000;
      if (var14 instanceof Some var15) {
         Iterator values = (Iterator)var15.value();
         var10000 = values.map((jsonx) -> MODULE$.accumulableInfoFromJson(jsonx));
      } else {
         if (!scala.None..MODULE$.equals(var14)) {
            throw new MatchError(var14);
         }

         var10000 = scala.package..MODULE$.Seq().empty();
      }

      Object accumulatedValues = var10000;
      boolean isShufflePushEnabled = BoxesRunTime.unboxToBoolean(this.jsonOption(json.get("Shuffle Push Enabled")).map((x$42) -> BoxesRunTime.boxToBoolean($anonfun$stageInfoFromJson$14(x$42))).getOrElse((JFunction0.mcZ.sp)() -> false));
      int shufflePushMergersCount = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Shuffle Push Mergers Count")).map((x$43) -> BoxesRunTime.boxToInteger($anonfun$stageInfoFromJson$16(x$43))).getOrElse((JFunction0.mcI.sp)() -> 0));
      Option rpId = this.jsonOption(json.get("Resource Profile Id")).map((x$44) -> BoxesRunTime.boxToInteger($anonfun$stageInfoFromJson$18(x$44)));
      int stageProf = BoxesRunTime.unboxToInt(rpId.getOrElse((JFunction0.mcI.sp)() -> ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()));
      ArraySeq x$5 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(rddInfos).toImmutableArraySeq();
      TaskMetrics x$11 = StageInfo$.MODULE$.$lessinit$greater$default$8();
      Seq x$12 = StageInfo$.MODULE$.$lessinit$greater$default$9();
      Option x$13 = StageInfo$.MODULE$.$lessinit$greater$default$10();
      StageInfo stageInfo = new StageInfo(stageId, attemptId, stageName, numTasks, x$5, parentIds, details, x$11, x$12, x$13, stageProf, isShufflePushEnabled, shufflePushMergersCount);
      stageInfo.submissionTime_$eq(submissionTime);
      stageInfo.completionTime_$eq(completionTime);
      stageInfo.failureReason_$eq(failureReason);
      ((IterableOnceOps)accumulatedValues).foreach((accInfo) -> {
         $anonfun$stageInfoFromJson$20(stageInfo, accInfo);
         return BoxedUnit.UNIT;
      });
      return stageInfo;
   }

   public TaskInfo taskInfoFromJson(final JsonNode json) {
      long taskId = this.JsonNodeImplicits(json.get("Task ID")).extractLong();
      int index = this.JsonNodeImplicits(json.get("Index")).extractInt();
      int attempt = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Attempt")).map((x$45) -> BoxesRunTime.boxToInteger($anonfun$taskInfoFromJson$1(x$45))).getOrElse((JFunction0.mcI.sp)() -> 1));
      int partitionId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Partition ID")).map((x$46) -> BoxesRunTime.boxToInteger($anonfun$taskInfoFromJson$3(x$46))).getOrElse((JFunction0.mcI.sp)() -> -1));
      long launchTime = this.JsonNodeImplicits(json.get("Launch Time")).extractLong();
      String executorId = Utils$.MODULE$.weakIntern(this.JsonNodeImplicits(json.get("Executor ID")).extractString());
      String host = Utils$.MODULE$.weakIntern(this.JsonNodeImplicits(json.get("Host")).extractString());
      Enumeration.Value taskLocality = TaskLocality$.MODULE$.withName(this.JsonNodeImplicits(json.get("Locality")).extractString());
      boolean speculative = this.jsonOption(json.get("Speculative")).exists((x$47) -> BoxesRunTime.boxToBoolean($anonfun$taskInfoFromJson$5(x$47)));
      long gettingResultTime = this.JsonNodeImplicits(json.get("Getting Result Time")).extractLong();
      long finishTime = this.JsonNodeImplicits(json.get("Finish Time")).extractLong();
      boolean failed = this.JsonNodeImplicits(json.get("Failed")).extractBoolean();
      boolean killed = this.jsonOption(json.get("Killed")).exists((x$48) -> BoxesRunTime.boxToBoolean($anonfun$taskInfoFromJson$6(x$48)));
      Option var21 = this.jsonOption(json.get("Accumulables")).map((x$49) -> MODULE$.JsonNodeImplicits(x$49).extractElements());
      Object var10000;
      if (var21 instanceof Some var22) {
         Iterator values = (Iterator)var22.value();
         var10000 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(values.map((jsonx) -> MODULE$.accumulableInfoFromJson(jsonx)).toArray(scala.reflect.ClassTag..MODULE$.apply(AccumulableInfo.class))).toImmutableArraySeq();
      } else {
         if (!scala.None..MODULE$.equals(var21)) {
            throw new MatchError(var21);
         }

         var10000 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      Seq accumulables = (Seq)var10000;
      TaskInfo taskInfo = new TaskInfo(taskId, index, attempt, partitionId, launchTime, executorId, host, taskLocality, speculative);
      taskInfo.gettingResultTime_$eq(gettingResultTime);
      taskInfo.finishTime_$eq(finishTime);
      taskInfo.failed_$eq(failed);
      taskInfo.killed_$eq(killed);
      taskInfo.setAccumulables(accumulables);
      return taskInfo;
   }

   public AccumulableInfo accumulableInfoFromJson(final JsonNode json) {
      long id = this.JsonNodeImplicits(json.get("ID")).extractLong();
      Option name = this.jsonOption(json.get("Name")).map((x$50) -> x$50.asText());
      Option update = this.jsonOption(json.get("Update")).map((v) -> MODULE$.accumValueFromJson(name, v));
      Option value = this.jsonOption(json.get("Value")).map((v) -> MODULE$.accumValueFromJson(name, v));
      boolean internal = this.jsonOption(json.get("Internal")).exists((x$51) -> BoxesRunTime.boxToBoolean($anonfun$accumulableInfoFromJson$4(x$51)));
      boolean countFailedValues = this.jsonOption(json.get("Count Failed Values")).exists((x$52) -> BoxesRunTime.boxToBoolean($anonfun$accumulableInfoFromJson$5(x$52)));
      Option metadata = this.jsonOption(json.get("Metadata")).map((x$53) -> x$53.asText());
      return new AccumulableInfo(id, name, update, value, internal, countFailedValues, metadata);
   }

   public Object accumValueFromJson(final Option name, final JsonNode value) {
      if (name.exists((x$54) -> BoxesRunTime.boxToBoolean($anonfun$accumValueFromJson$1(x$54)))) {
         if (value.isIntegralNumber()) {
            return BoxesRunTime.boxToLong(this.JsonNodeImplicits(value).extractLong());
         } else if (value.isArray()) {
            return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.JsonNodeImplicits(value).extractElements().map((blockJson) -> {
               BlockId id = BlockId$.MODULE$.apply(MODULE$.JsonNodeImplicits(blockJson.get("Block ID")).extractString());
               BlockStatus status = MODULE$.blockStatusFromJson(blockJson.get("Status"));
               return new Tuple2(id, status);
            }).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toImmutableArraySeq()).asJava();
         } else {
            throw new IllegalArgumentException("unexpected json value " + value + " for accumulator " + name.get());
         }
      } else {
         return value.asText();
      }
   }

   public TaskMetrics taskMetricsFromJson(final JsonNode json) {
      TaskMetrics metrics = TaskMetrics$.MODULE$.empty();
      if (json != null && !json.isNull()) {
         metrics.setExecutorDeserializeTime(this.JsonNodeImplicits(json.get("Executor Deserialize Time")).extractLong());
         metrics.setExecutorDeserializeCpuTime(BoxesRunTime.unboxToLong(this.jsonOption(json.get("Executor Deserialize CPU Time")).map((x$55) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$1(x$55))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         metrics.setExecutorRunTime(this.JsonNodeImplicits(json.get("Executor Run Time")).extractLong());
         metrics.setExecutorCpuTime(BoxesRunTime.unboxToLong(this.jsonOption(json.get("Executor CPU Time")).map((x$56) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$3(x$56))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         metrics.setPeakExecutionMemory(BoxesRunTime.unboxToLong(this.jsonOption(json.get("Peak Execution Memory")).map((x$57) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$5(x$57))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         metrics.setPeakOnHeapExecutionMemory(BoxesRunTime.unboxToLong(this.jsonOption(json.get("Peak On Heap Execution Memory")).map((x$58) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$7(x$58))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         metrics.setPeakOffHeapExecutionMemory(BoxesRunTime.unboxToLong(this.jsonOption(json.get("Peak Off Heap Execution Memory")).map((x$59) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$9(x$59))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         metrics.setResultSize(this.JsonNodeImplicits(json.get("Result Size")).extractLong());
         metrics.setJvmGCTime(this.JsonNodeImplicits(json.get("JVM GC Time")).extractLong());
         metrics.setResultSerializationTime(this.JsonNodeImplicits(json.get("Result Serialization Time")).extractLong());
         metrics.incMemoryBytesSpilled(this.JsonNodeImplicits(json.get("Memory Bytes Spilled")).extractLong());
         metrics.incDiskBytesSpilled(this.JsonNodeImplicits(json.get("Disk Bytes Spilled")).extractLong());
         this.jsonOption(json.get("Shuffle Read Metrics")).foreach((readJson) -> {
            $anonfun$taskMetricsFromJson$11(metrics, readJson);
            return BoxedUnit.UNIT;
         });
         this.jsonOption(json.get("Shuffle Write Metrics")).foreach((writeJson) -> {
            $anonfun$taskMetricsFromJson$38(metrics, writeJson);
            return BoxedUnit.UNIT;
         });
         this.jsonOption(json.get("Output Metrics")).foreach((outJson) -> {
            $anonfun$taskMetricsFromJson$41(metrics, outJson);
            return BoxedUnit.UNIT;
         });
         this.jsonOption(json.get("Input Metrics")).foreach((inJson) -> {
            $anonfun$taskMetricsFromJson$44(metrics, inJson);
            return BoxedUnit.UNIT;
         });
         this.jsonOption(json.get("Updated Blocks")).foreach((blocksJson) -> {
            $anonfun$taskMetricsFromJson$47(metrics, blocksJson);
            return BoxedUnit.UNIT;
         });
         return metrics;
      } else {
         return metrics;
      }
   }

   public TaskEndReason taskEndReasonFromJson(final JsonNode json) {
      String var3 = this.JsonNodeImplicits(json.get("Reason")).extractString();
      String var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.success();
      if (var10000 == null) {
         if (var3 == null) {
            return Success$.MODULE$;
         }
      } else if (var10000.equals(var3)) {
         return Success$.MODULE$;
      }

      var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.resubmitted();
      if (var10000 == null) {
         if (var3 == null) {
            return Resubmitted$.MODULE$;
         }
      } else if (var10000.equals(var3)) {
         return Resubmitted$.MODULE$;
      }

      label123: {
         var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.fetchFailed();
         if (var10000 == null) {
            if (var3 == null) {
               break label123;
            }
         } else if (var10000.equals(var3)) {
            break label123;
         }

         label145: {
            var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.exceptionFailure();
            if (var10000 == null) {
               if (var3 == null) {
                  break label145;
               }
            } else if (var10000.equals(var3)) {
               break label145;
            }

            var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.taskResultLost();
            if (var10000 == null) {
               if (var3 == null) {
                  return TaskResultLost$.MODULE$;
               }
            } else if (var10000.equals(var3)) {
               return TaskResultLost$.MODULE$;
            }

            label126: {
               var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.taskKilled();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label126;
                  }
               } else if (var10000.equals(var3)) {
                  break label126;
               }

               label127: {
                  var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.taskCommitDenied();
                  if (var10000 == null) {
                     if (var3 == null) {
                        break label127;
                     }
                  } else if (var10000.equals(var3)) {
                     break label127;
                  }

                  label146: {
                     var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.executorLostFailure();
                     if (var10000 == null) {
                        if (var3 == null) {
                           break label146;
                        }
                     } else if (var10000.equals(var3)) {
                        break label146;
                     }

                     var10000 = JsonProtocol.TASK_END_REASON_FORMATTED_CLASS_NAMES$.MODULE$.unknownReason();
                     if (var10000 == null) {
                        if (var3 == null) {
                           return UnknownReason$.MODULE$;
                        }
                     } else if (var10000.equals(var3)) {
                        return UnknownReason$.MODULE$;
                     }

                     throw new MatchError(var3);
                  }

                  Option exitCausedByApp = this.jsonOption(json.get("Exit Caused By App")).map((x$84) -> BoxesRunTime.boxToBoolean($anonfun$taskEndReasonFromJson$21(x$84)));
                  Option executorId = this.jsonOption(json.get("Executor ID")).map((x$85) -> x$85.asText());
                  Option reason = this.jsonOption(json.get("Loss Reason")).map((x$86) -> x$86.asText());
                  return new ExecutorLostFailure((String)executorId.getOrElse(() -> "Unknown"), BoxesRunTime.unboxToBoolean(exitCausedByApp.getOrElse((JFunction0.mcZ.sp)() -> true)), reason);
               }

               int jobId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Job ID")).map((x$81) -> BoxesRunTime.boxToInteger($anonfun$taskEndReasonFromJson$15(x$81))).getOrElse((JFunction0.mcI.sp)() -> -1));
               int partitionId = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Partition ID")).map((x$82) -> BoxesRunTime.boxToInteger($anonfun$taskEndReasonFromJson$17(x$82))).getOrElse((JFunction0.mcI.sp)() -> -1));
               int attemptNo = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Attempt Number")).map((x$83) -> BoxesRunTime.boxToInteger($anonfun$taskEndReasonFromJson$19(x$83))).getOrElse((JFunction0.mcI.sp)() -> -1));
               return new TaskCommitDenied(jobId, partitionId, attemptNo);
            }

            String killReason = (String)this.jsonOption(json.get("Kill Reason")).map((x$79) -> x$79.asText()).getOrElse(() -> "unknown reason");
            Seq accumUpdates = (Seq)this.jsonOption(json.get("Accumulator Updates")).map((x$80) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(x$80).extractElements().map((json) -> MODULE$.accumulableInfoFromJson(json)).toArray(scala.reflect.ClassTag..MODULE$.apply(AccumulableInfo.class))).toImmutableArraySeq()).getOrElse(() -> (Seq)scala.collection.immutable.Nil..MODULE$);
            return new TaskKilled(killReason, accumUpdates, TaskKilled$.MODULE$.apply$default$3(), TaskKilled$.MODULE$.apply$default$4());
         }

         String className = this.JsonNodeImplicits(json.get("Class Name")).extractString();
         String description = this.JsonNodeImplicits(json.get("Description")).extractString();
         StackTraceElement[] stackTrace = this.stackTraceFromJson(json.get("Stack Trace"));
         String fullStackTrace = (String)this.jsonOption(json.get("Full Stack Trace")).map((x$77) -> x$77.asText()).orNull(scala..less.colon.less..MODULE$.refl());
         ArraySeq accumUpdates = (ArraySeq)this.jsonOption(json.get("Accumulator Updates")).map((x$78) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(x$78).extractElements().map((json) -> MODULE$.accumulableInfoFromJson(json)).toArray(scala.reflect.ClassTag..MODULE$.apply(AccumulableInfo.class))).toImmutableArraySeq()).getOrElse(() -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(((IterableOnceOps)MODULE$.taskMetricsFromJson(json.get("Metrics")).accumulators().map((acc) -> acc.toInfoUpdate())).toArray(scala.reflect.ClassTag..MODULE$.apply(AccumulableInfo.class))).toImmutableArraySeq());
         return new ExceptionFailure(className, description, stackTrace, fullStackTrace, scala.None..MODULE$, accumUpdates, ExceptionFailure$.MODULE$.apply$default$7(), ExceptionFailure$.MODULE$.apply$default$8());
      }

      BlockManagerId blockManagerAddress = this.blockManagerIdFromJson(json.get("Block Manager Address"));
      int shuffleId = this.JsonNodeImplicits(json.get("Shuffle ID")).extractInt();
      long mapId = this.JsonNodeImplicits(json.get("Map ID")).extractLong();
      int mapIndex = BoxesRunTime.unboxToInt(this.jsonOption(json.get("Map Index")).map((x$75) -> BoxesRunTime.boxToInteger($anonfun$taskEndReasonFromJson$1(x$75))).getOrElse((JFunction0.mcI.sp)() -> Integer.MIN_VALUE));
      int reduceId = this.JsonNodeImplicits(json.get("Reduce ID")).extractInt();
      Option message = this.jsonOption(json.get("Message")).map((x$76) -> x$76.asText());
      return new FetchFailed(blockManagerAddress, shuffleId, mapId, mapIndex, reduceId, (String)message.getOrElse(() -> "Unknown reason"));
   }

   public BlockManagerId blockManagerIdFromJson(final JsonNode json) {
      if (json != null && !json.isNull()) {
         String executorId = Utils$.MODULE$.weakIntern(this.JsonNodeImplicits(json.get("Executor ID")).extractString());
         String host = Utils$.MODULE$.weakIntern(this.JsonNodeImplicits(json.get("Host")).extractString());
         int port = this.JsonNodeImplicits(json.get("Port")).extractInt();
         return BlockManagerId$.MODULE$.apply(executorId, host, port, BlockManagerId$.MODULE$.apply$default$4());
      } else {
         return null;
      }
   }

   public JobResult jobResultFromJson(final JsonNode json) {
      String var3 = this.JsonNodeImplicits(json.get("Result")).extractString();
      String var10000 = JsonProtocol.JOB_RESULT_FORMATTED_CLASS_NAMES$.MODULE$.jobSucceeded();
      if (var10000 == null) {
         if (var3 == null) {
            return JobSucceeded$.MODULE$;
         }
      } else if (var10000.equals(var3)) {
         return JobSucceeded$.MODULE$;
      }

      label22: {
         var10000 = JsonProtocol.JOB_RESULT_FORMATTED_CLASS_NAMES$.MODULE$.jobFailed();
         if (var10000 == null) {
            if (var3 == null) {
               break label22;
            }
         } else if (var10000.equals(var3)) {
            break label22;
         }

         throw new MatchError(var3);
      }

      Exception exception = this.exceptionFromJson(json.get("Exception"));
      return new JobFailed(exception);
   }

   public RDDInfo rddInfoFromJson(final JsonNode json) {
      int rddId = this.JsonNodeImplicits(json.get("RDD ID")).extractInt();
      String name = this.JsonNodeImplicits(json.get("Name")).extractString();
      Option scope = this.jsonOption(json.get("Scope")).map((x$87) -> x$87.asText()).map((s) -> RDDOperationScope$.MODULE$.fromJson(s));
      String callsite = (String)this.jsonOption(json.get("Callsite")).map((x$88) -> x$88.asText()).getOrElse(() -> "");
      Seq parentIds = (Seq)this.jsonOption(json.get("Parent IDs")).map((l) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(l).extractElements().map((x$89) -> BoxesRunTime.boxToInteger($anonfun$rddInfoFromJson$6(x$89))).toArray(scala.reflect.ClassTag..MODULE$.Int())).toImmutableArraySeq()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
      StorageLevel storageLevel = this.storageLevelFromJson(json.get("Storage Level"));
      boolean isBarrier = BoxesRunTime.unboxToBoolean(this.jsonOption(json.get("Barrier")).map((x$90) -> BoxesRunTime.boxToBoolean($anonfun$rddInfoFromJson$8(x$90))).getOrElse((JFunction0.mcZ.sp)() -> false));
      int numPartitions = this.JsonNodeImplicits(json.get("Number of Partitions")).extractInt();
      int numCachedPartitions = this.JsonNodeImplicits(json.get("Number of Cached Partitions")).extractInt();
      long memSize = this.JsonNodeImplicits(json.get("Memory Size")).extractLong();
      long diskSize = this.JsonNodeImplicits(json.get("Disk Size")).extractLong();
      Enumeration.Value outputDeterministicLevel = DeterministicLevel$.MODULE$.withName((String)this.jsonOption(json.get("DeterministicLevel")).map((x$91) -> x$91.asText()).getOrElse(() -> "DETERMINATE"));
      RDDInfo rddInfo = new RDDInfo(rddId, name, numPartitions, storageLevel, isBarrier, parentIds, callsite, scope, outputDeterministicLevel);
      rddInfo.numCachedPartitions_$eq(numCachedPartitions);
      rddInfo.memSize_$eq(memSize);
      rddInfo.diskSize_$eq(diskSize);
      return rddInfo;
   }

   public StorageLevel storageLevelFromJson(final JsonNode json) {
      boolean useDisk = this.JsonNodeImplicits(json.get("Use Disk")).extractBoolean();
      boolean useMemory = this.JsonNodeImplicits(json.get("Use Memory")).extractBoolean();
      Option var6 = this.jsonOption(json.get("Use Off Heap"));
      boolean var10000;
      if (var6 instanceof Some var7) {
         JsonNode value = (JsonNode)var7.value();
         var10000 = this.JsonNodeImplicits(value).extractBoolean();
      } else {
         if (!scala.None..MODULE$.equals(var6)) {
            throw new MatchError(var6);
         }

         var10000 = false;
      }

      boolean useOffHeap = var10000;
      boolean deserialized = this.JsonNodeImplicits(json.get("Deserialized")).extractBoolean();
      int replication = this.JsonNodeImplicits(json.get("Replication")).extractInt();
      return org.apache.spark.storage.StorageLevel..MODULE$.apply(useDisk, useMemory, useOffHeap, deserialized, replication);
   }

   public BlockStatus blockStatusFromJson(final JsonNode json) {
      StorageLevel storageLevel = this.storageLevelFromJson(json.get("Storage Level"));
      long memorySize = this.JsonNodeImplicits(json.get("Memory Size")).extractLong();
      long diskSize = this.JsonNodeImplicits(json.get("Disk Size")).extractLong();
      return new BlockStatus(storageLevel, memorySize, diskSize);
   }

   public ExecutorInfo executorInfoFromJson(final JsonNode json) {
      String executorHost = this.JsonNodeImplicits(json.get("Host")).extractString();
      int totalCores = this.JsonNodeImplicits(json.get("Total Cores")).extractInt();
      scala.collection.immutable.Map logUrls = this.mapFromJson(json.get("Log Urls")).toMap(scala..less.colon.less..MODULE$.refl());
      Option var9 = this.jsonOption(json.get("Attributes"));
      Object var10000;
      if (var9 instanceof Some var10) {
         JsonNode attr = (JsonNode)var10.value();
         var10000 = this.mapFromJson(attr).toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         if (!scala.None..MODULE$.equals(var9)) {
            throw new MatchError(var9);
         }

         var10000 = (Map)scala.collection.Map..MODULE$.empty();
      }

      Map attributes = (Map)var10000;
      Option var13 = this.jsonOption(json.get("Resources"));
      if (var13 instanceof Some var14) {
         JsonNode resources = (JsonNode)var14.value();
         var10000 = this.resourcesMapFromJson(resources).toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         if (!scala.None..MODULE$.equals(var13)) {
            throw new MatchError(var13);
         }

         var10000 = (Map)scala.collection.Map..MODULE$.empty();
      }

      Map resources = (Map)var10000;
      Option var17 = this.jsonOption(json.get("Resource Profile Id"));
      int var23;
      if (var17 instanceof Some var18) {
         JsonNode id = (JsonNode)var18.value();
         var23 = this.JsonNodeImplicits(id).extractInt();
      } else {
         if (!scala.None..MODULE$.equals(var17)) {
            throw new MatchError(var17);
         }

         var23 = ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
      }

      int resourceProfileId = var23;
      Option registrationTs = this.jsonOption(json.get("Registration Time")).map((ts) -> BoxesRunTime.boxToLong($anonfun$executorInfoFromJson$1(ts)));
      Option requestTs = this.jsonOption(json.get("Request Time")).map((ts) -> BoxesRunTime.boxToLong($anonfun$executorInfoFromJson$2(ts)));
      return new ExecutorInfo(executorHost, totalCores, logUrls, attributes.toMap(scala..less.colon.less..MODULE$.refl()), resources.toMap(scala..less.colon.less..MODULE$.refl()), resourceProfileId, registrationTs, requestTs);
   }

   public BlockUpdatedInfo blockUpdatedInfoFromJson(final JsonNode json) {
      BlockManagerId blockManagerId = this.blockManagerIdFromJson(json.get("Block Manager ID"));
      BlockId blockId = BlockId$.MODULE$.apply(this.JsonNodeImplicits(json.get("Block ID")).extractString());
      StorageLevel storageLevel = this.storageLevelFromJson(json.get("Storage Level"));
      long memorySize = this.JsonNodeImplicits(json.get("Memory Size")).extractLong();
      long diskSize = this.JsonNodeImplicits(json.get("Disk Size")).extractLong();
      return new BlockUpdatedInfo(blockManagerId, blockId, storageLevel, memorySize, diskSize);
   }

   public Map resourcesMapFromJson(final JsonNode json) {
      .MODULE$.assert(json.isObject(), () -> "expected object, got " + json.getNodeType());
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(json.fields()).asScala().map((field) -> {
         ResourceInformation resourceInfo = ResourceInformation$.MODULE$.parseJson(field.getValue().toString());
         return new Tuple2(field.getKey(), resourceInfo);
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Map mapFromJson(final JsonNode json) {
      .MODULE$.assert(json.isObject(), () -> "expected object, got " + json.getNodeType());
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(json.fields()).asScala().map((field) -> new Tuple2(field.getKey(), MODULE$.JsonNodeImplicits((JsonNode)field.getValue()).extractString())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Properties propertiesFromJson(final JsonNode json) {
      return (Properties)this.jsonOption(json).map((value) -> {
         Properties properties = new Properties();
         MODULE$.mapFromJson(value).foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return properties.setProperty(k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
         return properties;
      }).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public UUID UUIDFromJson(final JsonNode json) {
      long leastSignificantBits = this.JsonNodeImplicits(json.get("Least Significant Bits")).extractLong();
      long mostSignificantBits = this.JsonNodeImplicits(json.get("Most Significant Bits")).extractLong();
      return new UUID(leastSignificantBits, mostSignificantBits);
   }

   public StackTraceElement[] stackTraceFromJson(final JsonNode json) {
      return (StackTraceElement[])this.jsonOption(json).map((x$92) -> (StackTraceElement[])MODULE$.JsonNodeImplicits(x$92).extractElements().map((line) -> {
            String declaringClass = MODULE$.JsonNodeImplicits(line.get("Declaring Class")).extractString();
            String methodName = MODULE$.JsonNodeImplicits(line.get("Method Name")).extractString();
            String fileName = (String)MODULE$.jsonOption(line.get("File Name")).map((x$93) -> MODULE$.JsonNodeImplicits(x$93).extractString()).orNull(scala..less.colon.less..MODULE$.refl());
            int lineNumber = MODULE$.JsonNodeImplicits(line.get("Line Number")).extractInt();
            return new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
         }).toArray(scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class))).getOrElse(() -> (StackTraceElement[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class)));
   }

   public Exception exceptionFromJson(final JsonNode json) {
      String message = (String)this.jsonOption(json.get("Message")).map((x$94) -> MODULE$.JsonNodeImplicits(x$94).extractString()).orNull(scala..less.colon.less..MODULE$.refl());
      Exception e = new Exception(message);
      e.setStackTrace(this.stackTraceFromJson(json.get("Stack Trace")));
      return e;
   }

   private Option jsonOption(final JsonNode json) {
      return (Option)(json != null && !json.isNull() ? new Some(json) : scala.None..MODULE$);
   }

   private JsonProtocol.JsonNodeImplicits JsonNodeImplicits(final JsonNode json) {
      return new JsonProtocol.JsonNodeImplicits(json);
   }

   // $FF: synthetic method
   public static final void $anonfun$sparkEventToJsonString$1(final SparkListenerEvent event$1, final JsonProtocolOptions options$1, final JsonGenerator generator) {
      MODULE$.writeSparkEventToJson(event$1, generator, options$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$stageSubmittedToJson$1(final JsonGenerator g$1, final Properties properties) {
      g$1.writeFieldName("Properties");
      MODULE$.propertiesToJson(properties, g$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$taskEndToJson$1(final JsonGenerator g$2, final TaskMetrics m) {
      g$2.writeFieldName("Task Metrics");
      MODULE$.taskMetricsToJson(m, g$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$jobStartToJson$1(final JsonGenerator g$3, final JsonProtocolOptions options$2, final StageInfo x$1) {
      MODULE$.stageInfoToJson(x$1, g$3, options$2, true);
   }

   // $FF: synthetic method
   public static final void $anonfun$jobStartToJson$3(final JsonGenerator g$3, final Properties properties) {
      g$3.writeFieldName("Properties");
      MODULE$.propertiesToJson(properties, g$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$applicationStartToJson$1(final JsonGenerator g$5, final String x$4) {
      g$5.writeStringField("App ID", x$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$applicationStartToJson$2(final JsonGenerator g$5, final String x$5) {
      g$5.writeStringField("App Attempt ID", x$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$applicationStartToJson$3(final JsonGenerator g$5, final Map x$6) {
      MODULE$.writeMapField("Driver Logs", x$6, g$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$applicationStartToJson$4(final JsonGenerator g$5, final Map x$7) {
      MODULE$.writeMapField("Driver Attributes", x$7, g$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$executorMetricsUpdateToJson$1(final JsonGenerator g$7, final Tuple4 x0$1) {
      if (x0$1 != null) {
         long taskId = BoxesRunTime.unboxToLong(x0$1._1());
         int stageId = BoxesRunTime.unboxToInt(x0$1._2());
         int stageAttemptId = BoxesRunTime.unboxToInt(x0$1._3());
         Seq updates = (Seq)x0$1._4();
         g$7.writeStartObject();
         g$7.writeNumberField("Task ID", taskId);
         g$7.writeNumberField("Stage ID", stageId);
         g$7.writeNumberField("Stage Attempt ID", stageAttemptId);
         g$7.writeFieldName("Accumulator Updates");
         MODULE$.accumulablesToJson(updates, g$7, MODULE$.accumulablesToJson$default$3());
         g$7.writeEndObject();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorMetricsUpdateToJson$2(final JsonGenerator g$7, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 var4 = (Tuple2)x0$2._1();
         ExecutorMetrics metrics = (ExecutorMetrics)x0$2._2();
         if (var4 != null) {
            int stageId = var4._1$mcI$sp();
            int stageAttemptId = var4._2$mcI$sp();
            g$7.writeStartObject();
            g$7.writeNumberField("Stage ID", stageId);
            g$7.writeNumberField("Stage Attempt ID", stageAttemptId);
            g$7.writeFieldName("Executor Metrics");
            MODULE$.executorMetricsToJson(metrics, g$7);
            g$7.writeEndObject();
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$stageInfoToJson$1(final JsonGenerator g$8, final RDDInfo x$8) {
      MODULE$.rddInfoToJson(x$8, g$8);
   }

   // $FF: synthetic method
   public static final void $anonfun$stageInfoToJson$5(final JsonGenerator g$8, final String x$11) {
      g$8.writeStringField("Failure Reason", x$11);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumulablesToJson$2(final String elem) {
      return MODULE$.accumulableExcludeList().contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumulablesToJson$3(final String elem) {
      return taskMetricAccumulableNames.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumulablesToJson$1(final boolean includeTaskMetricsAccumulators$1, final AccumulableInfo acc) {
      return acc.name().exists((elem) -> BoxesRunTime.boxToBoolean($anonfun$accumulablesToJson$2(elem))) || !includeTaskMetricsAccumulators$1 && acc.name().exists((elem) -> BoxesRunTime.boxToBoolean($anonfun$accumulablesToJson$3(elem)));
   }

   // $FF: synthetic method
   public static final long $anonfun$accumulablesToJson$4(final AccumulableInfo x$12) {
      return x$12.id();
   }

   // $FF: synthetic method
   public static final void $anonfun$accumulablesToJson$5(final JsonGenerator g$9, final AccumulableInfo a) {
      MODULE$.accumulableInfoToJson(a, g$9);
   }

   // $FF: synthetic method
   public static final void $anonfun$accumulableInfoToJson$1(final JsonGenerator g$10, final String x$13) {
      g$10.writeStringField("Name", x$13);
   }

   // $FF: synthetic method
   public static final void $anonfun$accumulableInfoToJson$2(final Option name$1, final JsonGenerator g$10, final Object v) {
      MODULE$.accumValueToJson(name$1, v, g$10, new Some("Update"));
   }

   // $FF: synthetic method
   public static final void $anonfun$accumulableInfoToJson$3(final Option name$1, final JsonGenerator g$10, final Object v) {
      MODULE$.accumValueToJson(name$1, v, g$10, new Some("Value"));
   }

   // $FF: synthetic method
   public static final void $anonfun$accumulableInfoToJson$4(final JsonGenerator g$10, final String x$14) {
      g$10.writeStringField("Metadata", x$14);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumValueToJson$1(final String x$15) {
      return x$15.startsWith(InternalAccumulator$.MODULE$.METRICS_PREFIX());
   }

   // $FF: synthetic method
   public static final void $anonfun$accumValueToJson$2(final JsonGenerator g$11, final String x$1) {
      g$11.writeFieldName(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$accumValueToJson$3(final JsonGenerator g$11, final String x$1) {
      g$11.writeFieldName(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$accumValueToJson$4(final JsonGenerator g$11, final String x$1) {
      g$11.writeFieldName(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$accumValueToJson$5(final JsonGenerator g$11, final Object x0$1) {
      if (x0$1 instanceof Tuple2 var4) {
         Object id = var4._1();
         Object status = var4._2();
         if (id instanceof BlockId var7) {
            if (status instanceof BlockStatus var8) {
               g$11.writeStartObject();
               g$11.writeStringField("Block ID", var7.toString());
               g$11.writeFieldName("Status");
               MODULE$.blockStatusToJson(var8, g$11);
               g$11.writeEndObject();
               BoxedUnit var9 = BoxedUnit.UNIT;
               return;
            }
         }
      }

      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final void $anonfun$accumValueToJson$6(final JsonGenerator g$11, final String x$1) {
      g$11.writeFieldName(x$1);
   }

   private static final void writeShufflePushReadMetrics$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartObject();
      g$12.writeNumberField("Corrupt Merged Block Chunks", taskMetrics$1.shuffleReadMetrics().corruptMergedBlockChunks());
      g$12.writeNumberField("Merged Fetch Fallback Count", taskMetrics$1.shuffleReadMetrics().mergedFetchFallbackCount());
      g$12.writeNumberField("Merged Remote Blocks Fetched", taskMetrics$1.shuffleReadMetrics().remoteMergedBlocksFetched());
      g$12.writeNumberField("Merged Local Blocks Fetched", taskMetrics$1.shuffleReadMetrics().localMergedBlocksFetched());
      g$12.writeNumberField("Merged Remote Chunks Fetched", taskMetrics$1.shuffleReadMetrics().remoteMergedChunksFetched());
      g$12.writeNumberField("Merged Local Chunks Fetched", taskMetrics$1.shuffleReadMetrics().localMergedChunksFetched());
      g$12.writeNumberField("Merged Remote Bytes Read", taskMetrics$1.shuffleReadMetrics().remoteMergedBytesRead());
      g$12.writeNumberField("Merged Local Bytes Read", taskMetrics$1.shuffleReadMetrics().localMergedBytesRead());
      g$12.writeNumberField("Merged Remote Requests Duration", taskMetrics$1.shuffleReadMetrics().remoteMergedReqsDuration());
      g$12.writeEndObject();
   }

   private static final void writeShuffleReadMetrics$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartObject();
      g$12.writeNumberField("Remote Blocks Fetched", taskMetrics$1.shuffleReadMetrics().remoteBlocksFetched());
      g$12.writeNumberField("Local Blocks Fetched", taskMetrics$1.shuffleReadMetrics().localBlocksFetched());
      g$12.writeNumberField("Fetch Wait Time", taskMetrics$1.shuffleReadMetrics().fetchWaitTime());
      g$12.writeNumberField("Remote Bytes Read", taskMetrics$1.shuffleReadMetrics().remoteBytesRead());
      g$12.writeNumberField("Remote Bytes Read To Disk", taskMetrics$1.shuffleReadMetrics().remoteBytesReadToDisk());
      g$12.writeNumberField("Local Bytes Read", taskMetrics$1.shuffleReadMetrics().localBytesRead());
      g$12.writeNumberField("Total Records Read", taskMetrics$1.shuffleReadMetrics().recordsRead());
      g$12.writeNumberField("Remote Requests Duration", taskMetrics$1.shuffleReadMetrics().remoteReqsDuration());
      g$12.writeFieldName("Push Based Shuffle");
      writeShufflePushReadMetrics$1(g$12, taskMetrics$1);
      g$12.writeEndObject();
   }

   private static final void writeShuffleWriteMetrics$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartObject();
      g$12.writeNumberField("Shuffle Bytes Written", taskMetrics$1.shuffleWriteMetrics().bytesWritten());
      g$12.writeNumberField("Shuffle Write Time", taskMetrics$1.shuffleWriteMetrics().writeTime());
      g$12.writeNumberField("Shuffle Records Written", taskMetrics$1.shuffleWriteMetrics().recordsWritten());
      g$12.writeEndObject();
   }

   private static final void writeInputMetrics$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartObject();
      g$12.writeNumberField("Bytes Read", taskMetrics$1.inputMetrics().bytesRead());
      g$12.writeNumberField("Records Read", taskMetrics$1.inputMetrics().recordsRead());
      g$12.writeEndObject();
   }

   private static final void writeOutputMetrics$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartObject();
      g$12.writeNumberField("Bytes Written", taskMetrics$1.outputMetrics().bytesWritten());
      g$12.writeNumberField("Records Written", taskMetrics$1.outputMetrics().recordsWritten());
      g$12.writeEndObject();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsToJson$1(final JsonGenerator g$12, final Tuple2 x0$1) {
      if (x0$1 != null) {
         BlockId id = (BlockId)x0$1._1();
         BlockStatus status = (BlockStatus)x0$1._2();
         g$12.writeStartObject();
         g$12.writeStringField("Block ID", id.toString());
         g$12.writeFieldName("Status");
         MODULE$.blockStatusToJson(status, g$12);
         g$12.writeEndObject();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private static final void writeUpdatedBlocks$1(final JsonGenerator g$12, final TaskMetrics taskMetrics$1) {
      g$12.writeStartArray();
      taskMetrics$1.updatedBlockStatuses().foreach((x0$1) -> {
         $anonfun$taskMetricsToJson$1(g$12, x0$1);
         return BoxedUnit.UNIT;
      });
      g$12.writeEndArray();
   }

   // $FF: synthetic method
   public static final void $anonfun$executorMetricsToJson$1(final JsonGenerator g$13, final ExecutorMetrics executorMetrics$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String m = (String)x0$1._1();
         g$13.writeNumberField(m, executorMetrics$1.getMetricValue(m));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$taskEndReasonToJson$1(final JsonGenerator g$14, final BlockManagerId id) {
      g$14.writeFieldName("Block Manager Address");
      MODULE$.blockManagerIdToJson(id, g$14);
   }

   // $FF: synthetic method
   public static final void $anonfun$taskEndReasonToJson$2(final JsonGenerator g$14, final String x$16) {
      g$14.writeStringField("Loss Reason", x$16);
   }

   // $FF: synthetic method
   public static final void $anonfun$rddInfoToJson$1(final JsonGenerator g$15, final RDDOperationScope s) {
      g$15.writeStringField("Scope", s.toJson());
   }

   // $FF: synthetic method
   public static final void $anonfun$executorInfoToJson$1(final JsonGenerator g$16, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         ResourceInformation v = (ResourceInformation)x0$1._2();
         g$16.writeFieldName(k);
         g$16.writeRawValue(org.json4s.jackson.JsonMethods..MODULE$.compact(v.toJson()));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executorResourceRequestMapToJson$1(final JsonGenerator g$17, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         ExecutorResourceRequest execReq = (ExecutorResourceRequest)x0$1._2();
         g$17.writeFieldName(k);
         MODULE$.executorResourceRequestToJson(execReq, g$17);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$taskResourceRequestMapToJson$1(final JsonGenerator g$18, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         TaskResourceRequest taskReq = (TaskResourceRequest)x0$1._2();
         g$18.writeFieldName(k);
         MODULE$.taskResourceRequestToJson(taskReq, g$18);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$writeMapField$1(final JsonGenerator g$19, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         g$19.writeStringField(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$propertiesToJson$1(final JsonGenerator g$20, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         g$20.writeStringField(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$stackTraceToJson$1(final JsonGenerator g$21, final StackTraceElement line) {
      g$21.writeStartObject();
      g$21.writeStringField("Declaring Class", line.getClassName());
      g$21.writeStringField("Method Name", line.getMethodName());
      g$21.writeStringField("File Name", line.getFileName());
      g$21.writeNumberField("Line Number", line.getLineNumber());
      g$21.writeEndObject();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskStartFromJson$1(final JsonNode x$19) {
      return MODULE$.JsonNodeImplicits(x$19).extractInt();
   }

   // $FF: synthetic method
   public static final long $anonfun$executorMetricsFromJson$3(final JsonNode x$20) {
      return MODULE$.JsonNodeImplicits(x$20).extractLong();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskEndFromJson$1(final JsonNode x$21) {
      return MODULE$.JsonNodeImplicits(x$21).extractInt();
   }

   // $FF: synthetic method
   public static final long $anonfun$jobStartFromJson$1(final JsonNode x$22) {
      return MODULE$.JsonNodeImplicits(x$22).extractLong();
   }

   // $FF: synthetic method
   public static final int $anonfun$jobStartFromJson$3(final JsonNode x$23) {
      return MODULE$.JsonNodeImplicits(x$23).extractInt();
   }

   // $FF: synthetic method
   public static final StageInfo $anonfun$jobStartFromJson$7(final int id) {
      int x$2 = 0;
      String x$3 = "unknown";
      int x$4 = 0;
      Seq x$5 = (Seq)scala.package..MODULE$.Seq().empty();
      Seq x$6 = (Seq)scala.package..MODULE$.Seq().empty();
      String x$7 = "unknown";
      int x$8 = ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
      TaskMetrics x$9 = StageInfo$.MODULE$.$lessinit$greater$default$8();
      Seq x$10 = StageInfo$.MODULE$.$lessinit$greater$default$9();
      Option x$11 = StageInfo$.MODULE$.$lessinit$greater$default$10();
      boolean x$12 = StageInfo$.MODULE$.$lessinit$greater$default$12();
      int x$13 = StageInfo$.MODULE$.$lessinit$greater$default$13();
      return new StageInfo(id, 0, "unknown", 0, x$5, x$6, "unknown", x$9, x$10, x$11, x$8, x$12, x$13);
   }

   // $FF: synthetic method
   public static final long $anonfun$jobEndFromJson$1(final JsonNode x$25) {
      return MODULE$.JsonNodeImplicits(x$25).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$blockManagerAddedFromJson$1(final JsonNode x$28) {
      return MODULE$.JsonNodeImplicits(x$28).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$blockManagerAddedFromJson$3(final JsonNode x$29) {
      return MODULE$.JsonNodeImplicits(x$29).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$blockManagerAddedFromJson$4(final JsonNode x$30) {
      return MODULE$.JsonNodeImplicits(x$30).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$blockManagerRemovedFromJson$1(final JsonNode x$31) {
      return MODULE$.JsonNodeImplicits(x$31).extractLong();
   }

   // $FF: synthetic method
   public static final int $anonfun$applicationEndFromJson$1(final JsonNode x$34) {
      return MODULE$.JsonNodeImplicits(x$34).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$stageInfoFromJson$1(final JsonNode x$35) {
      return MODULE$.JsonNodeImplicits(x$35).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$stageInfoFromJson$5(final JsonNode x$36) {
      return MODULE$.JsonNodeImplicits(x$36).extractInt();
   }

   // $FF: synthetic method
   public static final long $anonfun$stageInfoFromJson$9(final JsonNode x$38) {
      return MODULE$.JsonNodeImplicits(x$38).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$stageInfoFromJson$10(final JsonNode x$39) {
      return MODULE$.JsonNodeImplicits(x$39).extractLong();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stageInfoFromJson$14(final JsonNode x$42) {
      return MODULE$.JsonNodeImplicits(x$42).extractBoolean();
   }

   // $FF: synthetic method
   public static final int $anonfun$stageInfoFromJson$16(final JsonNode x$43) {
      return MODULE$.JsonNodeImplicits(x$43).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$stageInfoFromJson$18(final JsonNode x$44) {
      return MODULE$.JsonNodeImplicits(x$44).extractInt();
   }

   // $FF: synthetic method
   public static final void $anonfun$stageInfoFromJson$20(final StageInfo stageInfo$1, final AccumulableInfo accInfo) {
      stageInfo$1.accumulables().update(BoxesRunTime.boxToLong(accInfo.id()), accInfo);
   }

   // $FF: synthetic method
   public static final int $anonfun$taskInfoFromJson$1(final JsonNode x$45) {
      return MODULE$.JsonNodeImplicits(x$45).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskInfoFromJson$3(final JsonNode x$46) {
      return MODULE$.JsonNodeImplicits(x$46).extractInt();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskInfoFromJson$5(final JsonNode x$47) {
      return MODULE$.JsonNodeImplicits(x$47).extractBoolean();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskInfoFromJson$6(final JsonNode x$48) {
      return MODULE$.JsonNodeImplicits(x$48).extractBoolean();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumulableInfoFromJson$4(final JsonNode x$51) {
      return MODULE$.JsonNodeImplicits(x$51).extractBoolean();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumulableInfoFromJson$5(final JsonNode x$52) {
      return MODULE$.JsonNodeImplicits(x$52).extractBoolean();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$accumValueFromJson$1(final String x$54) {
      return x$54.startsWith(InternalAccumulator$.MODULE$.METRICS_PREFIX());
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$1(final JsonNode x$55) {
      return MODULE$.JsonNodeImplicits(x$55).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$3(final JsonNode x$56) {
      return MODULE$.JsonNodeImplicits(x$56).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$5(final JsonNode x$57) {
      return MODULE$.JsonNodeImplicits(x$57).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$7(final JsonNode x$58) {
      return MODULE$.JsonNodeImplicits(x$58).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$9(final JsonNode x$59) {
      return MODULE$.JsonNodeImplicits(x$59).extractLong();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$12(final TempShuffleReadMetrics readMetrics$1, final JsonNode v) {
      readMetrics$1.incRemoteBytesReadToDisk(MODULE$.JsonNodeImplicits(v).extractLong());
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$13(final JsonNode x$60) {
      return MODULE$.JsonNodeImplicits(x$60).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$15(final JsonNode x$61) {
      return MODULE$.JsonNodeImplicits(x$61).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$17(final JsonNode x$62) {
      return MODULE$.JsonNodeImplicits(x$62).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$20(final JsonNode x$63) {
      return MODULE$.JsonNodeImplicits(x$63).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$22(final JsonNode x$64) {
      return MODULE$.JsonNodeImplicits(x$64).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$24(final JsonNode x$65) {
      return MODULE$.JsonNodeImplicits(x$65).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$26(final JsonNode x$66) {
      return MODULE$.JsonNodeImplicits(x$66).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$28(final JsonNode x$67) {
      return MODULE$.JsonNodeImplicits(x$67).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$30(final JsonNode x$68) {
      return MODULE$.JsonNodeImplicits(x$68).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$32(final JsonNode x$69) {
      return MODULE$.JsonNodeImplicits(x$69).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$34(final JsonNode x$70) {
      return MODULE$.JsonNodeImplicits(x$70).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$36(final JsonNode x$71) {
      return MODULE$.JsonNodeImplicits(x$71).extractLong();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$19(final TempShuffleReadMetrics readMetrics$1, final JsonNode shufflePushReadJson) {
      readMetrics$1.incCorruptMergedBlockChunks(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Corrupt Merged Block Chunks")).map((x$63) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$20(x$63))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incMergedFetchFallbackCount(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Fallback Count")).map((x$64) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$22(x$64))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incRemoteMergedBlocksFetched(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Remote Blocks Fetched")).map((x$65) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$24(x$65))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incLocalMergedBlocksFetched(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Local Blocks Fetched")).map((x$66) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$26(x$66))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incRemoteMergedChunksFetched(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Remote Chunks Fetched")).map((x$67) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$28(x$67))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incLocalMergedChunksFetched(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Local Chunks Fetched")).map((x$68) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$30(x$68))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incRemoteMergedBytesRead(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Remote Bytes Read")).map((x$69) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$32(x$69))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incLocalMergedBytesRead(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Local Bytes Read")).map((x$70) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$34(x$70))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics$1.incRemoteMergedReqsDuration(BoxesRunTime.unboxToLong(MODULE$.jsonOption(shufflePushReadJson.get("Merged Remote Requests Duration")).map((x$71) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$36(x$71))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$11(final TaskMetrics metrics$1, final JsonNode readJson) {
      TempShuffleReadMetrics readMetrics = metrics$1.createTempShuffleReadMetrics();
      readMetrics.incRemoteBlocksFetched((long)MODULE$.JsonNodeImplicits(readJson.get("Remote Blocks Fetched")).extractInt());
      readMetrics.incLocalBlocksFetched((long)MODULE$.JsonNodeImplicits(readJson.get("Local Blocks Fetched")).extractInt());
      readMetrics.incRemoteBytesRead(MODULE$.JsonNodeImplicits(readJson.get("Remote Bytes Read")).extractLong());
      MODULE$.jsonOption(readJson.get("Remote Bytes Read To Disk")).foreach((v) -> {
         $anonfun$taskMetricsFromJson$12(readMetrics, v);
         return BoxedUnit.UNIT;
      });
      readMetrics.incLocalBytesRead(BoxesRunTime.unboxToLong(MODULE$.jsonOption(readJson.get("Local Bytes Read")).map((x$60) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$13(x$60))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics.incFetchWaitTime(MODULE$.JsonNodeImplicits(readJson.get("Fetch Wait Time")).extractLong());
      readMetrics.incRecordsRead(BoxesRunTime.unboxToLong(MODULE$.jsonOption(readJson.get("Total Records Read")).map((x$61) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$15(x$61))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      readMetrics.incRemoteReqsDuration(BoxesRunTime.unboxToLong(MODULE$.jsonOption(readJson.get("Remote Requests Duration")).map((x$62) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$17(x$62))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      MODULE$.jsonOption(readJson.get("Shuffle Push Read Metrics")).foreach((shufflePushReadJson) -> {
         $anonfun$taskMetricsFromJson$19(readMetrics, shufflePushReadJson);
         return BoxedUnit.UNIT;
      });
      metrics$1.mergeShuffleReadMetrics();
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$39(final JsonNode x$72) {
      return MODULE$.JsonNodeImplicits(x$72).extractLong();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$38(final TaskMetrics metrics$1, final JsonNode writeJson) {
      ShuffleWriteMetrics writeMetrics = metrics$1.shuffleWriteMetrics();
      writeMetrics.incBytesWritten(MODULE$.JsonNodeImplicits(writeJson.get("Shuffle Bytes Written")).extractLong());
      writeMetrics.incRecordsWritten(BoxesRunTime.unboxToLong(MODULE$.jsonOption(writeJson.get("Shuffle Records Written")).map((x$72) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$39(x$72))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
      writeMetrics.incWriteTime(MODULE$.JsonNodeImplicits(writeJson.get("Shuffle Write Time")).extractLong());
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$42(final JsonNode x$73) {
      return MODULE$.JsonNodeImplicits(x$73).extractLong();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$41(final TaskMetrics metrics$1, final JsonNode outJson) {
      OutputMetrics outputMetrics = metrics$1.outputMetrics();
      outputMetrics.setBytesWritten(MODULE$.JsonNodeImplicits(outJson.get("Bytes Written")).extractLong());
      outputMetrics.setRecordsWritten(BoxesRunTime.unboxToLong(MODULE$.jsonOption(outJson.get("Records Written")).map((x$73) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$42(x$73))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
   }

   // $FF: synthetic method
   public static final long $anonfun$taskMetricsFromJson$45(final JsonNode x$74) {
      return MODULE$.JsonNodeImplicits(x$74).extractLong();
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$44(final TaskMetrics metrics$1, final JsonNode inJson) {
      InputMetrics inputMetrics = metrics$1.inputMetrics();
      inputMetrics.incBytesRead(MODULE$.JsonNodeImplicits(inJson.get("Bytes Read")).extractLong());
      inputMetrics.incRecordsRead(BoxesRunTime.unboxToLong(MODULE$.jsonOption(inJson.get("Records Read")).map((x$74) -> BoxesRunTime.boxToLong($anonfun$taskMetricsFromJson$45(x$74))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
   }

   // $FF: synthetic method
   public static final void $anonfun$taskMetricsFromJson$47(final TaskMetrics metrics$1, final JsonNode blocksJson) {
      metrics$1.setUpdatedBlockStatuses((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(MODULE$.JsonNodeImplicits(blocksJson).extractElements().map((blockJson) -> {
         BlockId id = BlockId$.MODULE$.apply(MODULE$.JsonNodeImplicits(blockJson.get("Block ID")).extractString());
         BlockStatus status = MODULE$.blockStatusFromJson(blockJson.get("Status"));
         return new Tuple2(id, status);
      }).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toImmutableArraySeq());
   }

   // $FF: synthetic method
   public static final int $anonfun$taskEndReasonFromJson$1(final JsonNode x$75) {
      return MODULE$.JsonNodeImplicits(x$75).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskEndReasonFromJson$15(final JsonNode x$81) {
      return MODULE$.JsonNodeImplicits(x$81).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskEndReasonFromJson$17(final JsonNode x$82) {
      return MODULE$.JsonNodeImplicits(x$82).extractInt();
   }

   // $FF: synthetic method
   public static final int $anonfun$taskEndReasonFromJson$19(final JsonNode x$83) {
      return MODULE$.JsonNodeImplicits(x$83).extractInt();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$taskEndReasonFromJson$21(final JsonNode x$84) {
      return MODULE$.JsonNodeImplicits(x$84).extractBoolean();
   }

   // $FF: synthetic method
   public static final int $anonfun$rddInfoFromJson$6(final JsonNode x$89) {
      return MODULE$.JsonNodeImplicits(x$89).extractInt();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$rddInfoFromJson$8(final JsonNode x$90) {
      return MODULE$.JsonNodeImplicits(x$90).extractBoolean();
   }

   // $FF: synthetic method
   public static final long $anonfun$executorInfoFromJson$1(final JsonNode ts) {
      return MODULE$.JsonNodeImplicits(ts).extractLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$executorInfoFromJson$2(final JsonNode ts) {
      return MODULE$.JsonNodeImplicits(ts).extractLong();
   }

   private JsonProtocol$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
