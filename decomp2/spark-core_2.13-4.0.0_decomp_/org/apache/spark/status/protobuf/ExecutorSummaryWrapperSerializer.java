package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.status.ExecutorSummaryWrapper;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.status.api.v1.MemoryMetrics;
import scala.MatchError;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194QAC\u0006\u0001\u0017UAQ\u0001\n\u0001\u0005\u0002\u0019BQ\u0001\u000b\u0001\u0005B%BQA\r\u0001\u0005\u0002MBQA\u000e\u0001\u0005\n]BQa\u0012\u0001\u0005\n!CQa\u0013\u0001\u0005\n1CQ\u0001\u0016\u0001\u0005\nUCQa\u0016\u0001\u0005\naCQa\u0019\u0001\u0005\n\u0011\u0014\u0001%\u0012=fGV$xN]*v[6\f'/_,sCB\u0004XM]*fe&\fG.\u001b>fe*\u0011A\"D\u0001\taJ|Go\u001c2vM*\u0011abD\u0001\u0007gR\fG/^:\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019QD\b\u0011\u000e\u0003-I!aH\u0006\u0003\u001bA\u0013x\u000e^8ck\u001a\u001cVM\u001d#f!\t\t#%D\u0001\u000e\u0013\t\u0019SB\u0001\fFq\u0016\u001cW\u000f^8s'VlW.\u0019:z/J\f\u0007\u000f]3s\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0014\u0011\u0005u\u0001\u0011!C:fe&\fG.\u001b>f)\tQ\u0003\u0007E\u0002\u0018W5J!\u0001\f\r\u0003\u000b\u0005\u0013(/Y=\u0011\u0005]q\u0013BA\u0018\u0019\u0005\u0011\u0011\u0015\u0010^3\t\u000bE\u0012\u0001\u0019\u0001\u0011\u0002\u000b%t\u0007/\u001e;\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0003AQBQ!N\u0002A\u0002)\nQAY=uKN\f\u0001d]3sS\u0006d\u0017N_3Fq\u0016\u001cW\u000f^8s'VlW.\u0019:z)\tAt\b\u0005\u0002:y9\u0011QDO\u0005\u0003w-\t!b\u0015;pe\u0016$\u0016\u0010]3t\u0013\tidHA\bFq\u0016\u001cW\u000f^8s'VlW.\u0019:z\u0015\tY4\u0002C\u00032\t\u0001\u0007\u0001\t\u0005\u0002B\r6\t!I\u0003\u0002D\t\u0006\u0011a/\r\u0006\u0003\u000b6\t1!\u00199j\u0013\ti$)\u0001\u000eeKN,'/[1mSj,W\t_3dkR|'oU;n[\u0006\u0014\u0018\u0010\u0006\u0002A\u0013\")!*\u0002a\u0001q\u00051!-\u001b8bef\fac]3sS\u0006d\u0017N_3NK6|'/_'fiJL7m\u001d\u000b\u0003\u001bB\u0003\"!\u000f(\n\u0005=s$!D'f[>\u0014\u00180T3ue&\u001c7\u000fC\u0003R\r\u0001\u0007!+A\u0004nKR\u0014\u0018nY:\u0011\u0005\u0005\u001b\u0016BA(C\u0003a!Wm]3sS\u0006d\u0017N_3NK6|'/_'fiJL7m\u001d\u000b\u0003%ZCQAS\u0004A\u00025\u000bAd]3sS\u0006d\u0017N_3SKN|WO]2f\u0013:4wN]7bi&|g\u000e\u0006\u0002Z9B\u0011\u0011HW\u0005\u00037z\u00121CU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:DQ!\u0018\u0005A\u0002y\u000bA!\u001b8g_B\u0011qLY\u0007\u0002A*\u0011\u0011mD\u0001\te\u0016\u001cx.\u001e:dK&\u00111\fY\u0001\u001fI\u0016\u001cXM]5bY&TXMU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:$\"AX3\t\u000b)K\u0001\u0019A-"
)
public class ExecutorSummaryWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final ExecutorSummaryWrapper input) {
      StoreTypes.ExecutorSummary info = this.serializeExecutorSummary(input.info());
      StoreTypes.ExecutorSummaryWrapper.Builder builder = StoreTypes.ExecutorSummaryWrapper.newBuilder().setInfo(info);
      return builder.build().toByteArray();
   }

   public ExecutorSummaryWrapper deserialize(final byte[] bytes) {
      StoreTypes.ExecutorSummaryWrapper binary = StoreTypes.ExecutorSummaryWrapper.parseFrom(bytes);
      ExecutorSummary info = this.deserializeExecutorSummary(binary.getInfo());
      return new ExecutorSummaryWrapper(info);
   }

   private StoreTypes.ExecutorSummary serializeExecutorSummary(final ExecutorSummary input) {
      StoreTypes.ExecutorSummary.Builder builder = StoreTypes.ExecutorSummary.newBuilder().setIsActive(input.isActive()).setRddBlocks(input.rddBlocks()).setMemoryUsed(input.memoryUsed()).setDiskUsed(input.diskUsed()).setTotalCores(input.totalCores()).setMaxTasks(input.maxTasks()).setActiveTasks(input.activeTasks()).setFailedTasks(input.failedTasks()).setCompletedTasks(input.completedTasks()).setTotalTasks(input.totalTasks()).setTotalDuration(input.totalDuration()).setTotalGcTime(input.totalGCTime()).setTotalInputBytes(input.totalInputBytes()).setTotalShuffleRead(input.totalShuffleRead()).setTotalShuffleWrite(input.totalShuffleWrite()).setIsBlacklisted(input.isBlacklisted()).setMaxMemory(input.maxMemory()).setAddTime(input.addTime().getTime());
      Utils$.MODULE$.setStringField(input.id(), (value) -> builder.setId(value));
      Utils$.MODULE$.setStringField(input.hostPort(), (value) -> builder.setHostPort(value));
      input.removeTime().foreach((date) -> builder.setRemoveTime(date.getTime()));
      input.removeReason().foreach((value) -> builder.setRemoveReason(value));
      input.executorLogs().foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return builder.putExecutorLogs(k, v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      input.memoryMetrics().foreach((metrics) -> builder.setMemoryMetrics(this.serializeMemoryMetrics(metrics)));
      input.blacklistedInStages().foreach((stage) -> $anonfun$serializeExecutorSummary$7(builder, BoxesRunTime.unboxToInt(stage)));
      input.peakMemoryMetrics().foreach((metrics) -> builder.setPeakMemoryMetrics(ExecutorMetricsSerializer$.MODULE$.serialize(metrics)));
      input.attributes().foreach((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return builder.putAttributes(k, v);
         } else {
            throw new MatchError(x0$2);
         }
      });
      input.resources().foreach((x0$3) -> {
         if (x0$3 != null) {
            String k = (String)x0$3._1();
            ResourceInformation v = (ResourceInformation)x0$3._2();
            return builder.putResources(k, this.serializeResourceInformation(v));
         } else {
            throw new MatchError(x0$3);
         }
      });
      builder.setResourceProfileId(input.resourceProfileId());
      builder.setIsExcluded(input.isExcluded());
      input.excludedInStages().foreach((stage) -> $anonfun$serializeExecutorSummary$11(builder, BoxesRunTime.unboxToInt(stage)));
      return builder.build();
   }

   private ExecutorSummary deserializeExecutorSummary(final StoreTypes.ExecutorSummary binary) {
      Option peakMemoryMetrics = Utils$.MODULE$.getOptional(binary.hasPeakMemoryMetrics(), () -> ExecutorMetricsSerializer$.MODULE$.deserialize(binary.getPeakMemoryMetrics()));
      Option removeTime = Utils$.MODULE$.getOptional(binary.hasRemoveTime(), () -> new Date(binary.getRemoveTime()));
      Option removeReason = Utils$.MODULE$.getOptional(binary.hasRemoveReason(), () -> binary.getRemoveReason());
      Option memoryMetrics = Utils$.MODULE$.getOptional(binary.hasMemoryMetrics(), () -> this.deserializeMemoryMetrics(binary.getMemoryMetrics()));
      return new ExecutorSummary(Utils$.MODULE$.getStringField(binary.hasId(), () -> binary.getId()), Utils$.MODULE$.getStringField(binary.hasHostPort(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getHostPort())), binary.getIsActive(), binary.getRddBlocks(), binary.getMemoryUsed(), binary.getDiskUsed(), binary.getTotalCores(), binary.getMaxTasks(), binary.getActiveTasks(), binary.getFailedTasks(), binary.getCompletedTasks(), binary.getTotalTasks(), binary.getTotalDuration(), binary.getTotalGcTime(), binary.getTotalInputBytes(), binary.getTotalShuffleRead(), binary.getTotalShuffleWrite(), binary.getIsBlacklisted(), binary.getMaxMemory(), new Date(binary.getAddTime()), removeTime, removeReason, .MODULE$.MapHasAsScala(binary.getExecutorLogsMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), memoryMetrics, ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getBlacklistedInStagesList()).asScala().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$deserializeExecutorSummary$7(x$1)))).toSet(), peakMemoryMetrics, .MODULE$.MapHasAsScala(binary.getAttributesMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), (Map).MODULE$.MapHasAsScala(binary.getResourcesMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$2, v) -> this.deserializeResourceInformation(v)), binary.getResourceProfileId(), binary.getIsExcluded(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExcludedInStagesList()).asScala().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$deserializeExecutorSummary$9(x$3)))).toSet());
   }

   private StoreTypes.MemoryMetrics serializeMemoryMetrics(final MemoryMetrics metrics) {
      StoreTypes.MemoryMetrics.Builder builder = StoreTypes.MemoryMetrics.newBuilder();
      builder.setUsedOnHeapStorageMemory(metrics.usedOnHeapStorageMemory());
      builder.setUsedOffHeapStorageMemory(metrics.usedOffHeapStorageMemory());
      builder.setTotalOnHeapStorageMemory(metrics.totalOnHeapStorageMemory());
      builder.setTotalOffHeapStorageMemory(metrics.totalOffHeapStorageMemory());
      return builder.build();
   }

   private MemoryMetrics deserializeMemoryMetrics(final StoreTypes.MemoryMetrics binary) {
      return new MemoryMetrics(binary.getUsedOnHeapStorageMemory(), binary.getUsedOffHeapStorageMemory(), binary.getTotalOnHeapStorageMemory(), binary.getTotalOffHeapStorageMemory());
   }

   private StoreTypes.ResourceInformation serializeResourceInformation(final ResourceInformation info) {
      StoreTypes.ResourceInformation.Builder builder = StoreTypes.ResourceInformation.newBuilder();
      Utils$.MODULE$.setStringField(info.name(), (value) -> builder.setName(value));
      if (info.addresses() != null) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])info.addresses()), (value) -> builder.addAddresses(value));
      }

      return builder.build();
   }

   private ResourceInformation deserializeResourceInformation(final StoreTypes.ResourceInformation binary) {
      return new ResourceInformation(Utils$.MODULE$.getStringField(binary.hasName(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getName())), (String[])((IterableOnceOps).MODULE$.ListHasAsScala(binary.getAddressesList()).asScala().map((s) -> org.apache.spark.util.Utils$.MODULE$.weakIntern(s))).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorSummary.Builder $anonfun$serializeExecutorSummary$7(final StoreTypes.ExecutorSummary.Builder builder$1, final int stage) {
      return builder$1.addBlacklistedInStages((long)stage);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorSummary.Builder $anonfun$serializeExecutorSummary$11(final StoreTypes.ExecutorSummary.Builder builder$1, final int stage) {
      return builder$1.addExcludedInStages((long)stage);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeExecutorSummary$7(final Long x$1) {
      return (int)scala.Predef..MODULE$.Long2long(x$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeExecutorSummary$9(final Long x$3) {
      return (int)scala.Predef..MODULE$.Long2long(x$3);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
