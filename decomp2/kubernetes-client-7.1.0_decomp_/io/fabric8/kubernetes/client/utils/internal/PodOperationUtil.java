package io.fabric8.kubernetes.client.utils.internal;

import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.PodStatus;
import io.fabric8.kubernetes.client.KubernetesClientTimeoutException;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.PodOperationsImpl;
import io.fabric8.kubernetes.client.readiness.Readiness;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodOperationUtil {
   private static final Logger LOG = LoggerFactory.getLogger(PodOperationUtil.class);

   private PodOperationUtil() {
   }

   public static List getFilteredPodsForLogs(PodOperationsImpl podOperations, PodList controllerPodList, String controllerUid) {
      List<PodResource> pods = new ArrayList();

      for(Pod pod : controllerPodList.getItems()) {
         OwnerReference ownerReference = KubernetesResourceUtil.getControllerUid(pod);
         if (ownerReference != null && ownerReference.getUid().equals(controllerUid)) {
            pods.add((PodResource)podOperations.withName(pod.getMetadata().getName()));
         }
      }

      return pods;
   }

   public static PodOperationsImpl getGenericPodOperations(OperationContext context, PodOperationContext podOperationContext) {
      return new PodOperationsImpl(podOperationContext, context.withName((String)null).withApiGroupName((String)null).withApiGroupVersion("v1"));
   }

   public static List getPodOperationsForController(OperationContext context, PodOperationContext podOperationContext, String controllerUid, Map selectorLabels) {
      return getPodOperationsForController(getGenericPodOperations(context, podOperationContext), controllerUid, selectorLabels);
   }

   public static LogWatch watchLog(List podResources, OutputStream out) {
      return (LogWatch)findFirstPodResource(podResources).map((it) -> it.watchLog(out)).orElse((Object)null);
   }

   public static Reader getLogReader(List podResources) {
      return (Reader)findFirstPodResource(podResources).map(Loggable::getLogReader).orElse((Object)null);
   }

   public static InputStream getLogInputStream(List podResources) {
      return (InputStream)findFirstPodResource(podResources).map(Loggable::getLogInputStream).orElse((Object)null);
   }

   private static Optional findFirstPodResource(List podResources) {
      if (!podResources.isEmpty()) {
         if (podResources.size() > 1) {
            LOG.debug("Found {} pods, Using first one to get log", podResources.size());
         }

         return Optional.ofNullable((PodResource)podResources.get(0));
      } else {
         return Optional.empty();
      }
   }

   public static String getLog(List podOperationList, Boolean isPretty) {
      StringBuilder stringBuilder = new StringBuilder();

      for(PodResource podOperation : podOperationList) {
         stringBuilder.append(podOperation.getLog(isPretty));
      }

      return stringBuilder.toString();
   }

   public static List getPodOperationsForController(PodOperationsImpl podOperations, String controllerUid, Map selectorLabels) {
      PodList controllerPodList = (PodList)podOperations.withLabels(selectorLabels).list();
      return getFilteredPodsForLogs(podOperations, controllerPodList, controllerUid);
   }

   public static Pod waitUntilReadyOrTerminal(PodResource podOperation, int logWaitTimeoutMs) {
      AtomicReference<Pod> podRef = new AtomicReference();

      try {
         podOperation.waitUntilCondition((p) -> {
            podRef.set(p);
            return isReadyOrTerminal(p);
         }, (long)logWaitTimeoutMs, TimeUnit.MILLISECONDS);
      } catch (KubernetesClientTimeoutException timeout) {
         LOG.debug("Timed out waiting for Pod to become Ready: {}, will still proceed", timeout.getMessage());
      } catch (Exception otherException) {
         LOG.warn("Error while waiting for Pod to become Ready", otherException);
      }

      return (Pod)podRef.get();
   }

   static boolean isReadyOrTerminal(Pod p) {
      return p == null || Readiness.isPodReady(p) || Optional.ofNullable(p.getStatus()).map(PodStatus::getPhase).filter((phase) -> !Arrays.asList("Pending", "Unknown").contains(phase)).isPresent();
   }
}
