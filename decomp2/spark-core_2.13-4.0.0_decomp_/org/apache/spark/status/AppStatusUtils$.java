package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.api.v1.TaskData;
import org.apache.spark.status.api.v1.TaskMetrics;
import scala.Predef.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class AppStatusUtils$ {
   public static final AppStatusUtils$ MODULE$ = new AppStatusUtils$();
   private static final Set TASK_FINISHED_STATES;

   static {
      TASK_FINISHED_STATES = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"FAILED", "KILLED", "SUCCESS"})));
   }

   private Set TASK_FINISHED_STATES() {
      return TASK_FINISHED_STATES;
   }

   private boolean isTaskFinished(final TaskData task) {
      return this.TASK_FINISHED_STATES().contains(task.status());
   }

   public long schedulerDelay(final TaskData task) {
      if (this.isTaskFinished(task) && task.taskMetrics().isDefined() && task.duration().isDefined()) {
         TaskMetrics m = (TaskMetrics)task.taskMetrics().get();
         return this.schedulerDelay(task.launchTime().getTime(), this.fetchStart(task), BoxesRunTime.unboxToLong(task.duration().get()), m.executorDeserializeTime(), m.resultSerializationTime(), m.executorRunTime());
      } else {
         return 0L;
      }
   }

   public long gettingResultTime(final TaskData task) {
      return this.gettingResultTime(task.launchTime().getTime(), this.fetchStart(task), BoxesRunTime.unboxToLong(task.duration().getOrElse((JFunction0.mcJ.sp)() -> -1L)));
   }

   public long schedulerDelay(final long launchTime, final long fetchStart, final long duration, final long deserializeTime, final long serializeTime, final long runTime) {
      return scala.math.package..MODULE$.max(0L, duration - runTime - deserializeTime - serializeTime - this.gettingResultTime(launchTime, fetchStart, duration));
   }

   public long gettingResultTime(final long launchTime, final long fetchStart, final long duration) {
      if (fetchStart > 0L) {
         return duration > 0L ? launchTime + duration - fetchStart : System.currentTimeMillis() - fetchStart;
      } else {
         return 0L;
      }
   }

   private long fetchStart(final TaskData task) {
      return task.resultFetchStart().isDefined() ? ((Date)task.resultFetchStart().get()).getTime() : -1L;
   }

   public IndexedSeq getQuantilesValue(final IndexedSeq values, final double[] quantiles) {
      int count = values.size();
      if (count > 0) {
         long[] indices = (long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(quantiles), (JFunction1.mcJD.sp)(q) -> scala.math.package..MODULE$.min((long)(q * (double)count), (long)(count - 1)), scala.reflect.ClassTag..MODULE$.Long());
         return scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(.MODULE$.doubleArrayOps((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(indices), (JFunction1.mcDJ.sp)(i) -> BoxesRunTime.unboxToDouble(values.apply((int)i)), scala.reflect.ClassTag..MODULE$.Double())));
      } else {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().fill(quantiles.length, (JFunction0.mcD.sp)() -> (double)0.0F);
      }
   }

   private AppStatusUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
