package org.apache.spark.ui.jobs;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.ui.UIUtils$;
import scala.Option;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class JobDataUtil$ {
   public static final JobDataUtil$ MODULE$ = new JobDataUtil$();

   public Option getDuration(final JobData jobData) {
      return jobData.submissionTime().map((start) -> BoxesRunTime.boxToLong($anonfun$getDuration$1(jobData, start)));
   }

   public String getFormattedDuration(final JobData jobData) {
      Option duration = this.getDuration(jobData);
      return (String)duration.map((d) -> $anonfun$getFormattedDuration$1(BoxesRunTime.unboxToLong(d))).getOrElse(() -> "Unknown");
   }

   public String getFormattedSubmissionTime(final JobData jobData) {
      return (String)jobData.submissionTime().map((date) -> UIUtils$.MODULE$.formatDate(date)).getOrElse(() -> "Unknown");
   }

   // $FF: synthetic method
   public static final long $anonfun$getDuration$2(final Date x$1) {
      return x$1.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$getDuration$1(final JobData jobData$1, final Date start) {
      long end = BoxesRunTime.unboxToLong(jobData$1.completionTime().map((x$1) -> BoxesRunTime.boxToLong($anonfun$getDuration$2(x$1))).getOrElse((JFunction0.mcJ.sp)() -> System.currentTimeMillis()));
      return end - start.getTime();
   }

   // $FF: synthetic method
   public static final String $anonfun$getFormattedDuration$1(final long d) {
      return UIUtils$.MODULE$.formatDuration(d);
   }

   private JobDataUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
