package org.apache.spark.deploy.yarn;

import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import scala.Predef.;
import scala.collection.immutable.Set;

public final class YarnAllocator$ {
   public static final YarnAllocator$ MODULE$ = new YarnAllocator$();
   private static final String MEM_REGEX = "[0-9.]+ [KMG]B";
   private static final int DECOMMISSIONING_NODES_CACHE_SIZE = 200;
   private static final Set NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS;

   static {
      NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{-106, -105, -107, -100, -101}));
   }

   public Clock $lessinit$greater$default$10() {
      return new SystemClock();
   }

   public String MEM_REGEX() {
      return MEM_REGEX;
   }

   public int DECOMMISSIONING_NODES_CACHE_SIZE() {
      return DECOMMISSIONING_NODES_CACHE_SIZE;
   }

   public Set NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS() {
      return NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS;
   }

   private YarnAllocator$() {
   }
}
