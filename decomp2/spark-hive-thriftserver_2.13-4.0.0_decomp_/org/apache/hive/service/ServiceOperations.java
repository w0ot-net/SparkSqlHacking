package org.apache.hive.service;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.SERVICE_NAME.;

public final class ServiceOperations {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(ServiceOperations.class);

   private ServiceOperations() {
   }

   public static void ensureCurrentState(Service.STATE state, Service.STATE expectedState) {
      if (state != expectedState) {
         String var10002 = String.valueOf(expectedState);
         throw new IllegalStateException("For this operation, the current service state must be " + var10002 + " instead of " + String.valueOf(state));
      }
   }

   public static void init(Service service, HiveConf configuration) {
      Service.STATE state = service.getServiceState();
      ensureCurrentState(state, Service.STATE.NOTINITED);
      service.init(configuration);
   }

   public static void start(Service service) {
      Service.STATE state = service.getServiceState();
      ensureCurrentState(state, Service.STATE.INITED);
      service.start();
   }

   public static void deploy(Service service, HiveConf configuration) {
      init(service, configuration);
      start(service);
   }

   public static void stop(Service service) {
      if (service != null) {
         Service.STATE state = service.getServiceState();
         if (state == Service.STATE.STARTED) {
            service.stop();
         }
      }

   }

   public static Exception stopQuietly(Service service) {
      try {
         stop(service);
         return null;
      } catch (Exception e) {
         LOG.warn("When stopping the service {}", e, new MDC[]{MDC.of(.MODULE$, service.getName())});
         return e;
      }
   }
}
