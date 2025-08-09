package org.apache.spark.streaming.scheduler;

public final class JobScheduler$ {
   public static final JobScheduler$ MODULE$ = new JobScheduler$();
   private static final String BATCH_TIME_PROPERTY_KEY = "spark.streaming.internal.batchTime";
   private static final String OUTPUT_OP_ID_PROPERTY_KEY = "spark.streaming.internal.outputOpId";

   public String BATCH_TIME_PROPERTY_KEY() {
      return BATCH_TIME_PROPERTY_KEY;
   }

   public String OUTPUT_OP_ID_PROPERTY_KEY() {
      return OUTPUT_OP_ID_PROPERTY_KEY;
   }

   private JobScheduler$() {
   }
}
