package org.apache.spark.deploy.k8s.features;

public final class MountVolumesFeatureStep$ {
   public static final MountVolumesFeatureStep$ MODULE$ = new MountVolumesFeatureStep$();
   private static final String PVC_ON_DEMAND = "OnDemand";
   private static final String PVC = "PersistentVolumeClaim";
   private static final String PVC_POSTFIX = "-pvc";
   private static final String PVC_ACCESS_MODE = "ReadWriteOncePod";

   public String PVC_ON_DEMAND() {
      return PVC_ON_DEMAND;
   }

   public String PVC() {
      return PVC;
   }

   public String PVC_POSTFIX() {
      return PVC_POSTFIX;
   }

   public String PVC_ACCESS_MODE() {
      return PVC_ACCESS_MODE;
   }

   private MountVolumesFeatureStep$() {
   }
}
