package org.apache.spark.metrics;

public final class MetricsSystemInstances$ {
   public static final MetricsSystemInstances$ MODULE$ = new MetricsSystemInstances$();
   private static final String MASTER = "master";
   private static final String APPLICATIONS = "applications";
   private static final String WORKER = "worker";
   private static final String EXECUTOR = "executor";
   private static final String DRIVER = "driver";
   private static final String SHUFFLE_SERVICE = "shuffleService";
   private static final String APPLICATION_MASTER = "applicationMaster";

   public String MASTER() {
      return MASTER;
   }

   public String APPLICATIONS() {
      return APPLICATIONS;
   }

   public String WORKER() {
      return WORKER;
   }

   public String EXECUTOR() {
      return EXECUTOR;
   }

   public String DRIVER() {
      return DRIVER;
   }

   public String SHUFFLE_SERVICE() {
      return SHUFFLE_SERVICE;
   }

   public String APPLICATION_MASTER() {
      return APPLICATION_MASTER;
   }

   private MetricsSystemInstances$() {
   }
}
