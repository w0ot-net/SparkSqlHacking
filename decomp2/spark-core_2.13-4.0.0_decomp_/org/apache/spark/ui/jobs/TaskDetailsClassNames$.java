package org.apache.spark.ui.jobs;

public final class TaskDetailsClassNames$ {
   public static final TaskDetailsClassNames$ MODULE$ = new TaskDetailsClassNames$();
   private static final String SCHEDULER_DELAY = "scheduler_delay";
   private static final String TASK_DESERIALIZATION_TIME = "deserialization_time";
   private static final String SHUFFLE_READ_FETCH_WAIT_TIME = "fetch_wait_time";
   private static final String SHUFFLE_READ_REMOTE_SIZE = "shuffle_read_remote";
   private static final String RESULT_SERIALIZATION_TIME = "serialization_time";
   private static final String GETTING_RESULT_TIME = "getting_result_time";
   private static final String PEAK_EXECUTION_MEMORY = "peak_execution_memory";

   public String SCHEDULER_DELAY() {
      return SCHEDULER_DELAY;
   }

   public String TASK_DESERIALIZATION_TIME() {
      return TASK_DESERIALIZATION_TIME;
   }

   public String SHUFFLE_READ_FETCH_WAIT_TIME() {
      return SHUFFLE_READ_FETCH_WAIT_TIME;
   }

   public String SHUFFLE_READ_REMOTE_SIZE() {
      return SHUFFLE_READ_REMOTE_SIZE;
   }

   public String RESULT_SERIALIZATION_TIME() {
      return RESULT_SERIALIZATION_TIME;
   }

   public String GETTING_RESULT_TIME() {
      return GETTING_RESULT_TIME;
   }

   public String PEAK_EXECUTION_MEMORY() {
      return PEAK_EXECUTION_MEMORY;
   }

   private TaskDetailsClassNames$() {
   }
}
