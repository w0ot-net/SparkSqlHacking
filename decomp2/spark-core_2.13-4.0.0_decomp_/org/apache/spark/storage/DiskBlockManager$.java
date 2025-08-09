package org.apache.spark.storage;

public final class DiskBlockManager$ {
   public static final DiskBlockManager$ MODULE$ = new DiskBlockManager$();
   private static final String MERGE_DIRECTORY = "merge_manager";
   private static final String MERGE_DIR_KEY = "mergeDir";
   private static final String ATTEMPT_ID_KEY = "attemptId";

   public String MERGE_DIRECTORY() {
      return MERGE_DIRECTORY;
   }

   public String MERGE_DIR_KEY() {
      return MERGE_DIR_KEY;
   }

   public String ATTEMPT_ID_KEY() {
      return ATTEMPT_ID_KEY;
   }

   private DiskBlockManager$() {
   }
}
