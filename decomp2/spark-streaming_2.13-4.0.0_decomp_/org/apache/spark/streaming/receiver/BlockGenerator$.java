package org.apache.spark.streaming.receiver;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;

public final class BlockGenerator$ {
   public static final BlockGenerator$ MODULE$ = new BlockGenerator$();

   public Clock $lessinit$greater$default$4() {
      return new SystemClock();
   }

   private BlockGenerator$() {
   }
}
