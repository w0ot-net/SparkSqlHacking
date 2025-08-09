package org.apache.spark.deploy.worker.ui;

import org.apache.spark.ui.SparkUI$;

public final class WorkerWebUI$ {
   public static final WorkerWebUI$ MODULE$ = new WorkerWebUI$();
   private static final String STATIC_RESOURCE_BASE;

   static {
      STATIC_RESOURCE_BASE = SparkUI$.MODULE$.STATIC_RESOURCE_DIR();
   }

   public String STATIC_RESOURCE_BASE() {
      return STATIC_RESOURCE_BASE;
   }

   private WorkerWebUI$() {
   }
}
