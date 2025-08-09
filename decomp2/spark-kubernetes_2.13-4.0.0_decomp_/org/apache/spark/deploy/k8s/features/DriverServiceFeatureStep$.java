package org.apache.spark.deploy.k8s.features;

import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.internal.config.package.;

public final class DriverServiceFeatureStep$ {
   public static final DriverServiceFeatureStep$ MODULE$ = new DriverServiceFeatureStep$();
   private static final String DRIVER_BIND_ADDRESS_KEY;
   private static final String DRIVER_HOST_KEY;
   private static final String DRIVER_SVC_POSTFIX;
   private static final int MAX_SERVICE_NAME_LENGTH;

   static {
      DRIVER_BIND_ADDRESS_KEY = .MODULE$.DRIVER_BIND_ADDRESS().key();
      DRIVER_HOST_KEY = .MODULE$.DRIVER_HOST_ADDRESS().key();
      DRIVER_SVC_POSTFIX = "-driver-svc";
      MAX_SERVICE_NAME_LENGTH = Config$.MODULE$.KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH();
   }

   public String DRIVER_BIND_ADDRESS_KEY() {
      return DRIVER_BIND_ADDRESS_KEY;
   }

   public String DRIVER_HOST_KEY() {
      return DRIVER_HOST_KEY;
   }

   public String DRIVER_SVC_POSTFIX() {
      return DRIVER_SVC_POSTFIX;
   }

   public int MAX_SERVICE_NAME_LENGTH() {
      return MAX_SERVICE_NAME_LENGTH;
   }

   private DriverServiceFeatureStep$() {
   }
}
