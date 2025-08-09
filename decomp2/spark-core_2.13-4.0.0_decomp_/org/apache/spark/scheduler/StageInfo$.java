package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.storage.RDDInfo$;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class StageInfo$ {
   public static final StageInfo$ MODULE$ = new StageInfo$();

   public TaskMetrics $lessinit$greater$default$8() {
      return null;
   }

   public Seq $lessinit$greater$default$9() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Option $lessinit$greater$default$10() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$12() {
      return false;
   }

   public int $lessinit$greater$default$13() {
      return 0;
   }

   public StageInfo fromStage(final Stage stage, final int attemptId, final Option numTasks, final TaskMetrics taskMetrics, final Seq taskLocalityPreferences, final int resourceProfileId) {
      Seq ancestorRddInfos = (Seq)stage.rdd().getNarrowAncestors().map((rdd) -> RDDInfo$.MODULE$.fromRdd(rdd));
      Seq rddInfos = (Seq)(new scala.collection.immutable..colon.colon(RDDInfo$.MODULE$.fromRdd(stage.rdd()), scala.collection.immutable.Nil..MODULE$)).$plus$plus(ancestorRddInfos);
      Object var10000;
      if (stage instanceof ShuffleMapStage var12) {
         var10000 = scala.Option..MODULE$.apply(var12.shuffleDep()).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$fromStage$2(x$1)));
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option shuffleDepId = (Option)var10000;
      return new StageInfo(stage.id(), attemptId, stage.name(), BoxesRunTime.unboxToInt(numTasks.getOrElse((JFunction0.mcI.sp)() -> stage.numTasks())), rddInfos, stage.parents().map((x$2) -> BoxesRunTime.boxToInteger($anonfun$fromStage$4(x$2))), stage.details(), taskMetrics, taskLocalityPreferences, shuffleDepId, resourceProfileId, false, 0);
   }

   public Option fromStage$default$3() {
      return scala.None..MODULE$;
   }

   public TaskMetrics fromStage$default$4() {
      return null;
   }

   public Seq fromStage$default$5() {
      return (Seq).MODULE$.Seq().empty();
   }

   // $FF: synthetic method
   public static final int $anonfun$fromStage$2(final ShuffleDependency x$1) {
      return x$1.shuffleId();
   }

   // $FF: synthetic method
   public static final int $anonfun$fromStage$4(final Stage x$2) {
      return x$2.id();
   }

   private StageInfo$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
