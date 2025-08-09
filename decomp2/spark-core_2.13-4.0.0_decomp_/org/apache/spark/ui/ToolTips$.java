package org.apache.spark.ui;

public final class ToolTips$ {
   public static final ToolTips$ MODULE$ = new ToolTips$();
   private static final String SCHEDULER_DELAY = "Scheduler delay includes time to ship the task from the scheduler to\n       the executor, and time to send the task result from the executor to the scheduler. If\n       scheduler delay is large, consider decreasing the size of tasks or decreasing the size\n       of task results.";
   private static final String TASK_DESERIALIZATION_TIME = "Time spent deserializing the task closure on the executor, including the time to read the\n       broadcasted task.";
   private static final String SHUFFLE_READ_FETCH_WAIT_TIME = "Time that the task spent blocked waiting for shuffle data to be read from remote machines.";
   private static final String INPUT = "Bytes read from Hadoop or from Spark storage.";
   private static final String OUTPUT = "Bytes written to Hadoop.";
   private static final String SHUFFLE_WRITE = "Bytes and records written to disk in order to be read by a shuffle in a future stage.";
   private static final String SHUFFLE_READ = "Total shuffle bytes and records read (includes both data read locally and data read from\n       remote executors). ";
   private static final String SHUFFLE_READ_REMOTE_SIZE = "Total shuffle bytes read from remote executors. This is a subset of the shuffle\n       read bytes; the remaining shuffle data is read locally. ";
   private static final String GETTING_RESULT_TIME = "Time that the driver spends fetching task results from workers. If this is large, consider\n       decreasing the amount of data returned from each task.";
   private static final String RESULT_SERIALIZATION_TIME = "Time spent serializing the task result on the executor before sending it back to the\n       driver.";
   private static final String GC_TIME = "Time that the executor spent paused for Java garbage collection while the task was\n       running.";
   private static final String PEAK_EXECUTION_MEMORY = "Execution memory refers to the memory used by internal data structures created during\n       shuffles, aggregations and joins when Tungsten is enabled. The value of this accumulator\n       should be approximately the sum of the peak sizes across all such data structures created\n       in this task. For SQL jobs, this only tracks all unsafe operators, broadcast joins, and\n       external sort.";
   private static final String JOB_TIMELINE = "Shows when jobs started and ended and when executors joined or left. Drag to scroll.\n       Click Enable Zooming and use mouse wheel to zoom in/out.";
   private static final String STAGE_TIMELINE = "Shows when stages started and ended and when executors joined or left. Drag to scroll.\n       Click Enable Zooming and use mouse wheel to zoom in/out.";
   private static final String JOB_DAG = "Shows a graph of stages executed for this job, each of which can contain\n       multiple RDD operations (e.g. map() and filter()), and of RDDs inside each operation\n       (shown as dots).";
   private static final String STAGE_DAG = "Shows a graph of RDD operations in this stage, and RDDs inside each one. A stage can run\n       multiple operations (e.g. two map() functions) if they can be pipelined. Some operations\n       also create multiple RDDs internally. Cached RDDs are shown in green.\n    ";
   private static final String APPLICATION_EXECUTOR_LIMIT = "Maximum number of executors that this application will use. This limit is finite only when\n       dynamic allocation is enabled. The number of granted executors may exceed the limit\n       ephemerally when executors are being killed.\n    ";
   private static final String DURATION = "Elapsed time since the first task of the stage was launched until execution completion of\n       all its tasks (Excluding the time of the stage waits to be launched after submitted).\n    ";

   public String SCHEDULER_DELAY() {
      return SCHEDULER_DELAY;
   }

   public String TASK_DESERIALIZATION_TIME() {
      return TASK_DESERIALIZATION_TIME;
   }

   public String SHUFFLE_READ_FETCH_WAIT_TIME() {
      return SHUFFLE_READ_FETCH_WAIT_TIME;
   }

   public String INPUT() {
      return INPUT;
   }

   public String OUTPUT() {
      return OUTPUT;
   }

   public String SHUFFLE_WRITE() {
      return SHUFFLE_WRITE;
   }

   public String SHUFFLE_READ() {
      return SHUFFLE_READ;
   }

   public String SHUFFLE_READ_REMOTE_SIZE() {
      return SHUFFLE_READ_REMOTE_SIZE;
   }

   public String GETTING_RESULT_TIME() {
      return GETTING_RESULT_TIME;
   }

   public String RESULT_SERIALIZATION_TIME() {
      return RESULT_SERIALIZATION_TIME;
   }

   public String GC_TIME() {
      return GC_TIME;
   }

   public String PEAK_EXECUTION_MEMORY() {
      return PEAK_EXECUTION_MEMORY;
   }

   public String JOB_TIMELINE() {
      return JOB_TIMELINE;
   }

   public String STAGE_TIMELINE() {
      return STAGE_TIMELINE;
   }

   public String JOB_DAG() {
      return JOB_DAG;
   }

   public String STAGE_DAG() {
      return STAGE_DAG;
   }

   public String APPLICATION_EXECUTOR_LIMIT() {
      return APPLICATION_EXECUTOR_LIMIT;
   }

   public String DURATION() {
      return DURATION;
   }

   private ToolTips$() {
   }
}
