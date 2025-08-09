package org.apache.spark.scheduler;

import scala.runtime.BoxesRunTime;
import scala.util.DynamicVariable;

public final class LiveListenerBus$ {
   public static final LiveListenerBus$ MODULE$ = new LiveListenerBus$();
   private static final DynamicVariable withinListenerThread = new DynamicVariable(BoxesRunTime.boxToBoolean(false));
   private static final String SHARED_QUEUE = "shared";
   private static final String APP_STATUS_QUEUE = "appStatus";
   private static final String EXECUTOR_MANAGEMENT_QUEUE = "executorManagement";
   private static final String EVENT_LOG_QUEUE = "eventLog";

   public DynamicVariable withinListenerThread() {
      return withinListenerThread;
   }

   public String SHARED_QUEUE() {
      return SHARED_QUEUE;
   }

   public String APP_STATUS_QUEUE() {
      return APP_STATUS_QUEUE;
   }

   public String EXECUTOR_MANAGEMENT_QUEUE() {
      return EXECUTOR_MANAGEMENT_QUEUE;
   }

   public String EVENT_LOG_QUEUE() {
      return EVENT_LOG_QUEUE;
   }

   private LiveListenerBus$() {
   }
}
