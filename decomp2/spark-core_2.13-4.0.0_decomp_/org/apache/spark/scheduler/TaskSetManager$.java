package org.apache.spark.scheduler;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.Option;
import scala.None.;

public final class TaskSetManager$ {
   public static final TaskSetManager$ MODULE$ = new TaskSetManager$();
   private static final int TASK_SIZE_TO_WARN_KIB = 1000;
   private static final int BARRIER_LOGGING_INTERVAL = 60000;

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Clock $lessinit$greater$default$5() {
      return new SystemClock();
   }

   public int TASK_SIZE_TO_WARN_KIB() {
      return TASK_SIZE_TO_WARN_KIB;
   }

   public int BARRIER_LOGGING_INTERVAL() {
      return BARRIER_LOGGING_INTERVAL;
   }

   private TaskSetManager$() {
   }
}
