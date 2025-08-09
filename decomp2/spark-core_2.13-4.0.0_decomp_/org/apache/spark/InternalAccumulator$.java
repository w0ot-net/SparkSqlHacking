package org.apache.spark;

public final class InternalAccumulator$ {
   public static final InternalAccumulator$ MODULE$ = new InternalAccumulator$();
   private static final String METRICS_PREFIX = "internal.metrics.";
   private static final String SHUFFLE_READ_METRICS_PREFIX;
   private static final String SHUFFLE_WRITE_METRICS_PREFIX;
   private static final String OUTPUT_METRICS_PREFIX;
   private static final String INPUT_METRICS_PREFIX;
   private static final String SHUFFLE_PUSH_READ_METRICS_PREFIX;
   private static final String EXECUTOR_DESERIALIZE_TIME;
   private static final String EXECUTOR_DESERIALIZE_CPU_TIME;
   private static final String EXECUTOR_RUN_TIME;
   private static final String EXECUTOR_CPU_TIME;
   private static final String RESULT_SIZE;
   private static final String JVM_GC_TIME;
   private static final String RESULT_SERIALIZATION_TIME;
   private static final String MEMORY_BYTES_SPILLED;
   private static final String DISK_BYTES_SPILLED;
   private static final String PEAK_EXECUTION_MEMORY;
   private static final String PEAK_ON_HEAP_EXECUTION_MEMORY;
   private static final String PEAK_OFF_HEAP_EXECUTION_MEMORY;
   private static final String UPDATED_BLOCK_STATUSES;
   private static final String TEST_ACCUM;
   private static final String COLLECT_METRICS_ACCUMULATOR;

   static {
      SHUFFLE_READ_METRICS_PREFIX = MODULE$.METRICS_PREFIX() + "shuffle.read.";
      SHUFFLE_WRITE_METRICS_PREFIX = MODULE$.METRICS_PREFIX() + "shuffle.write.";
      OUTPUT_METRICS_PREFIX = MODULE$.METRICS_PREFIX() + "output.";
      INPUT_METRICS_PREFIX = MODULE$.METRICS_PREFIX() + "input.";
      SHUFFLE_PUSH_READ_METRICS_PREFIX = MODULE$.METRICS_PREFIX() + "shuffle.push.read.";
      EXECUTOR_DESERIALIZE_TIME = MODULE$.METRICS_PREFIX() + "executorDeserializeTime";
      EXECUTOR_DESERIALIZE_CPU_TIME = MODULE$.METRICS_PREFIX() + "executorDeserializeCpuTime";
      EXECUTOR_RUN_TIME = MODULE$.METRICS_PREFIX() + "executorRunTime";
      EXECUTOR_CPU_TIME = MODULE$.METRICS_PREFIX() + "executorCpuTime";
      RESULT_SIZE = MODULE$.METRICS_PREFIX() + "resultSize";
      JVM_GC_TIME = MODULE$.METRICS_PREFIX() + "jvmGCTime";
      RESULT_SERIALIZATION_TIME = MODULE$.METRICS_PREFIX() + "resultSerializationTime";
      MEMORY_BYTES_SPILLED = MODULE$.METRICS_PREFIX() + "memoryBytesSpilled";
      DISK_BYTES_SPILLED = MODULE$.METRICS_PREFIX() + "diskBytesSpilled";
      PEAK_EXECUTION_MEMORY = MODULE$.METRICS_PREFIX() + "peakExecutionMemory";
      PEAK_ON_HEAP_EXECUTION_MEMORY = MODULE$.METRICS_PREFIX() + "peakOnHeapExecutionMemory";
      PEAK_OFF_HEAP_EXECUTION_MEMORY = MODULE$.METRICS_PREFIX() + "peakOffHeapExecutionMemory";
      UPDATED_BLOCK_STATUSES = MODULE$.METRICS_PREFIX() + "updatedBlockStatuses";
      TEST_ACCUM = MODULE$.METRICS_PREFIX() + "testAccumulator";
      COLLECT_METRICS_ACCUMULATOR = MODULE$.METRICS_PREFIX() + "collectMetricsAccumulator";
   }

   public String METRICS_PREFIX() {
      return METRICS_PREFIX;
   }

   public String SHUFFLE_READ_METRICS_PREFIX() {
      return SHUFFLE_READ_METRICS_PREFIX;
   }

   public String SHUFFLE_WRITE_METRICS_PREFIX() {
      return SHUFFLE_WRITE_METRICS_PREFIX;
   }

   public String OUTPUT_METRICS_PREFIX() {
      return OUTPUT_METRICS_PREFIX;
   }

   public String INPUT_METRICS_PREFIX() {
      return INPUT_METRICS_PREFIX;
   }

   public String SHUFFLE_PUSH_READ_METRICS_PREFIX() {
      return SHUFFLE_PUSH_READ_METRICS_PREFIX;
   }

   public String EXECUTOR_DESERIALIZE_TIME() {
      return EXECUTOR_DESERIALIZE_TIME;
   }

   public String EXECUTOR_DESERIALIZE_CPU_TIME() {
      return EXECUTOR_DESERIALIZE_CPU_TIME;
   }

   public String EXECUTOR_RUN_TIME() {
      return EXECUTOR_RUN_TIME;
   }

   public String EXECUTOR_CPU_TIME() {
      return EXECUTOR_CPU_TIME;
   }

   public String RESULT_SIZE() {
      return RESULT_SIZE;
   }

   public String JVM_GC_TIME() {
      return JVM_GC_TIME;
   }

   public String RESULT_SERIALIZATION_TIME() {
      return RESULT_SERIALIZATION_TIME;
   }

   public String MEMORY_BYTES_SPILLED() {
      return MEMORY_BYTES_SPILLED;
   }

   public String DISK_BYTES_SPILLED() {
      return DISK_BYTES_SPILLED;
   }

   public String PEAK_EXECUTION_MEMORY() {
      return PEAK_EXECUTION_MEMORY;
   }

   public String PEAK_ON_HEAP_EXECUTION_MEMORY() {
      return PEAK_ON_HEAP_EXECUTION_MEMORY;
   }

   public String PEAK_OFF_HEAP_EXECUTION_MEMORY() {
      return PEAK_OFF_HEAP_EXECUTION_MEMORY;
   }

   public String UPDATED_BLOCK_STATUSES() {
      return UPDATED_BLOCK_STATUSES;
   }

   public String TEST_ACCUM() {
      return TEST_ACCUM;
   }

   public String COLLECT_METRICS_ACCUMULATOR() {
      return COLLECT_METRICS_ACCUMULATOR;
   }

   private InternalAccumulator$() {
   }
}
