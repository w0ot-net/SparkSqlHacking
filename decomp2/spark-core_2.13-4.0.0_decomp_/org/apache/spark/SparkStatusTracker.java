package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.MemoryMetrics;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005}3Aa\u0003\u0007\u0001'!A!\u0004\u0001B\u0001B\u0003%1\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003!\u0011\u00191\u0003\u0001\"\u0001\rO!)1\u0006\u0001C\u0001Y!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C\u0001\u000b\")a\t\u0001C\u0001\u000b\")q\t\u0001C\u0001\u0011\")\u0011\u000b\u0001C\u0001%\")\u0011\f\u0001C\u00015\n\u00112\u000b]1sWN#\u0018\r^;t)J\f7m[3s\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7\u0001A\n\u0003\u0001Q\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0017AA:d!\taR$D\u0001\r\u0013\tqBB\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/A\u0003ti>\u0014X\r\u0005\u0002\"I5\t!E\u0003\u0002$\u0019\u000511\u000f^1ukNL!!\n\u0012\u0003\u001d\u0005\u0003\bo\u0015;biV\u001c8\u000b^8sK\u00061A(\u001b8jiz\"2\u0001K\u0015+!\ta\u0002\u0001C\u0003\u001b\u0007\u0001\u00071\u0004C\u0003 \u0007\u0001\u0007\u0001%A\thKRTuNY%eg\u001a{'o\u0012:pkB$\"!L\u001a\u0011\u0007Uq\u0003'\u0003\u00020-\t)\u0011I\u001d:bsB\u0011Q#M\u0005\u0003eY\u00111!\u00138u\u0011\u0015!D\u00011\u00016\u0003!QwNY$s_V\u0004\bC\u0001\u001c>\u001d\t94\b\u0005\u00029-5\t\u0011H\u0003\u0002;%\u00051AH]8pizJ!\u0001\u0010\f\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yY\tqbZ3u\u0015>\u0014\u0017\nZ:G_J$\u0016m\u001a\u000b\u0003[\tCQaQ\u0003A\u0002U\naA[8c)\u0006<\u0017!E4fi\u0006\u001bG/\u001b<f'R\fw-Z%egR\tQ&A\bhKR\f5\r^5wK*{'-\u00133t\u0003)9W\r\u001e&pE&sgm\u001c\u000b\u0003\u0013>\u00032!\u0006&M\u0013\tYeC\u0001\u0004PaRLwN\u001c\t\u000395K!A\u0014\u0007\u0003\u0019M\u0003\u0018M]6K_\nLeNZ8\t\u000bAC\u0001\u0019\u0001\u0019\u0002\u000b)|'-\u00133\u0002\u0019\u001d,Go\u0015;bO\u0016LeNZ8\u0015\u0005M;\u0006cA\u000bK)B\u0011A$V\u0005\u0003-2\u0011ab\u00159be.\u001cF/Y4f\u0013:4w\u000eC\u0003Y\u0013\u0001\u0007\u0001'A\u0004ti\u0006<W-\u00133\u0002!\u001d,G/\u0012=fGV$xN]%oM>\u001cX#A.\u0011\u0007UqC\f\u0005\u0002\u001d;&\u0011a\f\u0004\u0002\u0012'B\f'o[#yK\u000e,Ho\u001c:J]\u001a|\u0007"
)
public class SparkStatusTracker {
   private final AppStatusStore store;

   public int[] getJobIdsForGroup(final String jobGroup) {
      Option expected = .MODULE$.apply(jobGroup);
      return (int[])((IterableOnceOps)((IterableOps)this.store.jobsList((List)null).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getJobIdsForGroup$1(expected, x$1)))).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$getJobIdsForGroup$2(x$2)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public int[] getJobIdsForTag(final String jobTag) {
      return (int[])((IterableOnceOps)((IterableOps)this.store.jobsList((List)null).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getJobIdsForTag$1(jobTag, x$3)))).map((x$4) -> BoxesRunTime.boxToInteger($anonfun$getJobIdsForTag$2(x$4)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public int[] getActiveStageIds() {
      return (int[])((IterableOnceOps)this.store.stageList(Arrays.asList((Object[])(new StageStatus[]{StageStatus.ACTIVE})), this.store.stageList$default$2(), this.store.stageList$default$3(), this.store.stageList$default$4(), this.store.stageList$default$5()).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$getActiveStageIds$1(x$5)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public int[] getActiveJobIds() {
      return (int[])((IterableOnceOps)this.store.jobsList(Arrays.asList((Object[])(new JobExecutionStatus[]{JobExecutionStatus.RUNNING}))).map((x$6) -> BoxesRunTime.boxToInteger($anonfun$getActiveJobIds$1(x$6)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
   }

   public Option getJobInfo(final int jobId) {
      return this.store.asOption(() -> this.store.job(jobId)).map((job) -> new SparkJobInfoImpl(jobId, (int[])job.stageIds().toArray(scala.reflect.ClassTag..MODULE$.Int()), job.status()));
   }

   public Option getStageInfo(final int stageId) {
      return this.store.asOption(() -> this.store.lastStageAttempt(stageId)).map((stage) -> new SparkStageInfoImpl(stageId, stage.attemptId(), BoxesRunTime.unboxToLong(stage.submissionTime().map((x$7) -> BoxesRunTime.boxToLong($anonfun$getStageInfo$3(x$7))).getOrElse((JFunction0.mcJ.sp)() -> 0L)), stage.name(), stage.numTasks(), stage.numActiveTasks(), stage.numCompleteTasks(), stage.numFailedTasks()));
   }

   public SparkExecutorInfo[] getExecutorInfos() {
      return (SparkExecutorInfo[])((IterableOnceOps)this.store.executorList(true).map((exec) -> {
         Tuple2 var3 = Utils$.MODULE$.parseHostPort(exec.hostPort());
         if (var3 != null) {
            String host = (String)var3._1();
            int port = var3._2$mcI$sp();
            Tuple2 var2 = new Tuple2(host, BoxesRunTime.boxToInteger(port));
            String host = (String)var2._1();
            int port = var2._2$mcI$sp();
            long cachedMem = BoxesRunTime.unboxToLong(exec.memoryMetrics().map((mem) -> BoxesRunTime.boxToLong($anonfun$getExecutorInfos$2(mem))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
            return new SparkExecutorInfoImpl(host, port, cachedMem, exec.activeTasks(), BoxesRunTime.unboxToLong(exec.memoryMetrics().map((x$9) -> BoxesRunTime.boxToLong($anonfun$getExecutorInfos$4(x$9))).getOrElse((JFunction0.mcJ.sp)() -> 0L)), BoxesRunTime.unboxToLong(exec.memoryMetrics().map((x$10) -> BoxesRunTime.boxToLong($anonfun$getExecutorInfos$6(x$10))).getOrElse((JFunction0.mcJ.sp)() -> 0L)), BoxesRunTime.unboxToLong(exec.memoryMetrics().map((x$11) -> BoxesRunTime.boxToLong($anonfun$getExecutorInfos$8(x$11))).getOrElse((JFunction0.mcJ.sp)() -> 0L)), BoxesRunTime.unboxToLong(exec.memoryMetrics().map((x$12) -> BoxesRunTime.boxToLong($anonfun$getExecutorInfos$10(x$12))).getOrElse((JFunction0.mcJ.sp)() -> 0L)));
         } else {
            throw new MatchError(var3);
         }
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(SparkExecutorInfo.class));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJobIdsForGroup$1(final Option expected$1, final JobData x$1) {
      boolean var3;
      label23: {
         Option var10000 = x$1.jobGroup();
         if (var10000 == null) {
            if (expected$1 == null) {
               break label23;
            }
         } else if (var10000.equals(expected$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$getJobIdsForGroup$2(final JobData x$2) {
      return x$2.jobId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getJobIdsForTag$1(final String jobTag$1, final JobData x$3) {
      return x$3.jobTags().contains(jobTag$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$getJobIdsForTag$2(final JobData x$4) {
      return x$4.jobId();
   }

   // $FF: synthetic method
   public static final int $anonfun$getActiveStageIds$1(final StageData x$5) {
      return x$5.stageId();
   }

   // $FF: synthetic method
   public static final int $anonfun$getActiveJobIds$1(final JobData x$6) {
      return x$6.jobId();
   }

   // $FF: synthetic method
   public static final long $anonfun$getStageInfo$3(final Date x$7) {
      return x$7.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorInfos$2(final MemoryMetrics mem) {
      return mem.usedOnHeapStorageMemory() + mem.usedOffHeapStorageMemory();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorInfos$4(final MemoryMetrics x$9) {
      return x$9.usedOnHeapStorageMemory();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorInfos$6(final MemoryMetrics x$10) {
      return x$10.usedOffHeapStorageMemory();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorInfos$8(final MemoryMetrics x$11) {
      return x$11.totalOnHeapStorageMemory();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorInfos$10(final MemoryMetrics x$12) {
      return x$12.totalOffHeapStorageMemory();
   }

   public SparkStatusTracker(final SparkContext sc, final AppStatusStore store) {
      this.store = store;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
