package org.apache.spark.util.logging;

public final class SizeBasedRollingPolicy$ {
   public static final SizeBasedRollingPolicy$ MODULE$ = new SizeBasedRollingPolicy$();
   private static final int MINIMUM_SIZE_BYTES;

   static {
      MINIMUM_SIZE_BYTES = RollingFileAppender$.MODULE$.DEFAULT_BUFFER_SIZE() * 10;
   }

   public boolean $lessinit$greater$default$2() {
      return true;
   }

   public int MINIMUM_SIZE_BYTES() {
      return MINIMUM_SIZE_BYTES;
   }

   private SizeBasedRollingPolicy$() {
   }
}
