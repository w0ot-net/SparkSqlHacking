package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.JobExecutionStatus;
import org.apache.spark.status.JobDataWrapper;
import org.apache.spark.status.api.v1.JobData;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005!3QAB\u0004\u0001\u000fEAQ\u0001\t\u0001\u0005\u0002\tBQ\u0001\n\u0001\u0005B\u0015BQA\f\u0001\u0005\u0002=BQA\r\u0001\u0005\nMBQ\u0001\u0012\u0001\u0005\n\u0015\u0013\u0001DS8c\t\u0006$\u0018m\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tA\u0011\"\u0001\u0005qe>$xNY;g\u0015\tQ1\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rI\"\u0004H\u0007\u0002\u000f%\u00111d\u0002\u0002\u000e!J|Go\u001c2vMN+'\u000fR3\u0011\u0005uqR\"A\u0005\n\u0005}I!A\u0004&pE\u0012\u000bG/Y,sCB\u0004XM]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\u0005\u0005\u0002\u001a\u0001\u0005I1/\u001a:jC2L'0\u001a\u000b\u0003M1\u00022aE\u0014*\u0013\tACCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0014U%\u00111\u0006\u0006\u0002\u0005\u0005f$X\rC\u0003.\u0005\u0001\u0007A$A\u0001k\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0005q\u0001\u0004\"B\u0019\u0004\u0001\u00041\u0013!\u00022zi\u0016\u001c\u0018\u0001E:fe&\fG.\u001b>f\u0015>\u0014G)\u0019;b)\t!4\b\u0005\u00026q9\u0011\u0011DN\u0005\u0003o\u001d\t!b\u0015;pe\u0016$\u0016\u0010]3t\u0013\tI$HA\u0004K_\n$\u0015\r^1\u000b\u0005]:\u0001\"\u0002\u001f\u0005\u0001\u0004i\u0014a\u00026pE\u0012\u000bG/\u0019\t\u0003}\rk\u0011a\u0010\u0006\u0003\u0001\u0006\u000b!A^\u0019\u000b\u0005\tK\u0011aA1qS&\u0011\u0011hP\u0001\u0013I\u0016\u001cXM]5bY&TXMS8c\t\u0006$\u0018\r\u0006\u0002>\r\")q)\u0002a\u0001i\u0005!\u0011N\u001c4p\u0001"
)
public class JobDataWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final JobDataWrapper j) {
      StoreTypes.JobData jobData = this.serializeJobData(j.info());
      StoreTypes.JobDataWrapper.Builder builder = StoreTypes.JobDataWrapper.newBuilder();
      builder.setInfo(jobData);
      j.skippedStages().foreach((value) -> $anonfun$serialize$1(builder, BoxesRunTime.unboxToInt(value)));
      j.sqlExecutionId().foreach((value) -> $anonfun$serialize$2(builder, BoxesRunTime.unboxToLong(value)));
      return builder.build().toByteArray();
   }

   public JobDataWrapper deserialize(final byte[] bytes) {
      StoreTypes.JobDataWrapper wrapper = StoreTypes.JobDataWrapper.parseFrom(bytes);
      Option sqlExecutionId = Utils$.MODULE$.getOptional(wrapper.hasSqlExecutionId(), (JFunction0.mcJ.sp)() -> wrapper.getSqlExecutionId());
      return new JobDataWrapper(this.deserializeJobData(wrapper.getInfo()), ((IterableOnceOps).MODULE$.ListHasAsScala(wrapper.getSkippedStagesList()).asScala().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$deserialize$2(x$1)))).toSet(), sqlExecutionId);
   }

   private StoreTypes.JobData serializeJobData(final JobData jobData) {
      StoreTypes.JobData.Builder jobDataBuilder = StoreTypes.JobData.newBuilder();
      jobDataBuilder.setJobId((long)jobData.jobId()).setStatus(JobExecutionStatusSerializer$.MODULE$.serialize(jobData.status())).setNumTasks(jobData.numTasks()).setNumActiveTasks(jobData.numActiveTasks()).setNumCompletedTasks(jobData.numCompletedTasks()).setNumSkippedTasks(jobData.numSkippedTasks()).setNumFailedTasks(jobData.numFailedTasks()).setNumKilledTasks(jobData.numKilledTasks()).setNumCompletedIndices(jobData.numCompletedIndices()).setNumActiveStages(jobData.numActiveStages()).setNumCompletedStages(jobData.numCompletedStages()).setNumSkippedStages(jobData.numSkippedStages()).setNumFailedStages(jobData.numFailedStages());
      Utils$.MODULE$.setStringField(jobData.name(), (value) -> jobDataBuilder.setName(value));
      jobData.description().foreach((value) -> jobDataBuilder.setDescription(value));
      jobData.submissionTime().foreach((d) -> jobDataBuilder.setSubmissionTime(d.getTime()));
      jobData.completionTime().foreach((d) -> jobDataBuilder.setCompletionTime(d.getTime()));
      jobData.stageIds().foreach((id) -> $anonfun$serializeJobData$5(jobDataBuilder, BoxesRunTime.unboxToInt(id)));
      jobData.jobGroup().foreach((value) -> jobDataBuilder.setJobGroup(value));
      jobData.jobTags().foreach((value) -> jobDataBuilder.addJobTags(value));
      jobData.killedTasksSummary().foreach((entry) -> jobDataBuilder.putKillTasksSummary((String)entry._1(), entry._2$mcI$sp()));
      return jobDataBuilder.build();
   }

   private JobData deserializeJobData(final StoreTypes.JobData info) {
      Option description = Utils$.MODULE$.getOptional(info.hasDescription(), () -> info.getDescription());
      Option submissionTime = Utils$.MODULE$.getOptional(info.hasSubmissionTime(), () -> new Date(info.getSubmissionTime()));
      Option completionTime = Utils$.MODULE$.getOptional(info.hasCompletionTime(), () -> new Date(info.getCompletionTime()));
      Option jobGroup = Utils$.MODULE$.getOptional(info.hasJobGroup(), () -> info.getJobGroup());
      JobExecutionStatus status = JobExecutionStatusSerializer$.MODULE$.deserialize(info.getStatus());
      return new JobData((int)info.getJobId(), Utils$.MODULE$.getStringField(info.hasName(), () -> info.getName()), description, submissionTime, completionTime, (Seq).MODULE$.ListHasAsScala(info.getStageIdsList()).asScala().map((x$2) -> BoxesRunTime.boxToInteger($anonfun$deserializeJobData$6(x$2))), jobGroup, .MODULE$.ListHasAsScala(info.getJobTagsList()).asScala(), status, info.getNumTasks(), info.getNumActiveTasks(), info.getNumCompletedTasks(), info.getNumSkippedTasks(), info.getNumFailedTasks(), info.getNumKilledTasks(), info.getNumCompletedIndices(), info.getNumActiveStages(), info.getNumCompletedStages(), info.getNumSkippedStages(), info.getNumFailedStages(), (Map).MODULE$.MapHasAsScala(info.getKillTasksSummaryMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$3, v) -> BoxesRunTime.boxToInteger($anonfun$deserializeJobData$7(x$3, v))));
   }

   // $FF: synthetic method
   public static final StoreTypes.JobDataWrapper.Builder $anonfun$serialize$1(final StoreTypes.JobDataWrapper.Builder builder$1, final int value) {
      return builder$1.addSkippedStages(value);
   }

   // $FF: synthetic method
   public static final StoreTypes.JobDataWrapper.Builder $anonfun$serialize$2(final StoreTypes.JobDataWrapper.Builder builder$1, final long value) {
      return builder$1.setSqlExecutionId(value);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserialize$2(final Integer x$1) {
      return scala.Predef..MODULE$.Integer2int(x$1);
   }

   // $FF: synthetic method
   public static final StoreTypes.JobData.Builder $anonfun$serializeJobData$5(final StoreTypes.JobData.Builder jobDataBuilder$1, final int id) {
      return jobDataBuilder$1.addStageIds((long)id);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeJobData$6(final Long x$2) {
      return (int)scala.Predef..MODULE$.Long2long(x$2);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeJobData$7(final String x$3, final Integer v) {
      return scala.Predef..MODULE$.Integer2int(v);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
