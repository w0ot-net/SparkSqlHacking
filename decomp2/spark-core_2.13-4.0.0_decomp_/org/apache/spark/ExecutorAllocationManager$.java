package org.apache.spark;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.Option;
import scala.None.;

public final class ExecutorAllocationManager$ {
   public static final ExecutorAllocationManager$ MODULE$ = new ExecutorAllocationManager$();
   private static final long NOT_SET = Long.MAX_VALUE;

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Clock $lessinit$greater$default$5() {
      return new SystemClock();
   }

   public long NOT_SET() {
      return NOT_SET;
   }

   private ExecutorAllocationManager$() {
   }
}
