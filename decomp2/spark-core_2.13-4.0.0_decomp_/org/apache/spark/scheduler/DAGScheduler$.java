package org.apache.spark.scheduler;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class DAGScheduler$ {
   public static final DAGScheduler$ MODULE$ = new DAGScheduler$();
   private static final int RESUBMIT_TIMEOUT = 200;

   public Clock $lessinit$greater$default$7() {
      return new SystemClock();
   }

   public int RESUBMIT_TIMEOUT() {
      return RESUBMIT_TIMEOUT;
   }

   private DAGScheduler$() {
   }
}
