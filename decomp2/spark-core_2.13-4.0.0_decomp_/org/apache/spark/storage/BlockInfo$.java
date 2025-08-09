package org.apache.spark.storage;

public final class BlockInfo$ {
   public static final BlockInfo$ MODULE$ = new BlockInfo$();
   private static final long NO_WRITER = -1L;
   private static final long NON_TASK_WRITER = -1024L;

   public long NO_WRITER() {
      return NO_WRITER;
   }

   public long NON_TASK_WRITER() {
      return NON_TASK_WRITER;
   }

   private BlockInfo$() {
   }
}
