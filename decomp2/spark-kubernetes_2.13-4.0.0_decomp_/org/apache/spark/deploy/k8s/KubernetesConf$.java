package org.apache.spark.deploy.k8s;

import java.util.Locale;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.submit.MainAppResource;
import org.apache.spark.resource.ResourceProfile.;
import scala.Option;

public final class KubernetesConf$ {
   public static final KubernetesConf$ MODULE$ = new KubernetesConf$();

   public KubernetesDriverConf createDriverConf(final SparkConf sparkConf, final String appId, final MainAppResource mainAppResource, final String mainClass, final String[] appArgs, final Option proxyUser) {
      KubernetesVolumeUtils$.MODULE$.parseVolumesWithPrefix(sparkConf, Config$.MODULE$.KUBERNETES_EXECUTOR_VOLUMES_PREFIX());
      return new KubernetesDriverConf(sparkConf.clone(), appId, mainAppResource, mainClass, appArgs, proxyUser, KubernetesDriverConf$.MODULE$.$lessinit$greater$default$7());
   }

   public KubernetesExecutorConf createExecutorConf(final SparkConf sparkConf, final String executorId, final String appId, final Option driverPod, final int resourceProfileId) {
      return new KubernetesExecutorConf(sparkConf.clone(), appId, executorId, driverPod, resourceProfileId);
   }

   public int createExecutorConf$default$5() {
      return .MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
   }

   public String getKubernetesAppId() {
      String var10000 = UUID.randomUUID().toString();
      return "spark-" + var10000.replaceAll("-", "");
   }

   public String getResourceNamePrefix(final String appName) {
      String id = KubernetesUtils$.MODULE$.uniqueID(KubernetesUtils$.MODULE$.uniqueID$default$1());
      return (appName + "-" + id).trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9\\-]", "-").replaceAll("-+", "-").replaceAll("^-", "").replaceAll("^[0-9]", "x");
   }

   public String getAppNameLabel(final String appName) {
      return scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(StringUtils.abbreviate(String.valueOf(appName).trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9\\-]", "-").replaceAll("-+", "-"), "", Config$.MODULE$.KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH())), "-")), "-");
   }

   public String buildKubernetesResourceName(final String vendorDomain, final String resourceName) {
      return vendorDomain + "/" + resourceName;
   }

   private KubernetesConf$() {
   }
}
