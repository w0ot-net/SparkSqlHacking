package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.HasMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KubernetesClientTimeoutException extends KubernetesClientException {
   private static final String RESOURCE_FORMAT = "Timed out waiting for [%d] milliseconds for [%s] with name:[%s] in namespace [%s].";
   private static final String KNOWS_RESOURCES_FORMAT = "Timed out waiting for [%d] milliseconds for multiple resources. %s";
   private final List resourcesNotReady;

   public KubernetesClientTimeoutException(String kind, String name, String namespace, long amount, TimeUnit timeUnit) {
      super(String.format("Timed out waiting for [%d] milliseconds for [%s] with name:[%s] in namespace [%s].", timeUnit.toMillis(amount), kind, name, namespace));
      this.resourcesNotReady = Collections.emptyList();
   }

   public KubernetesClientTimeoutException(HasMetadata resource, long amount, TimeUnit timeUnit) {
      super(String.format("Timed out waiting for [%d] milliseconds for [%s] with name:[%s] in namespace [%s].", timeUnit.toMillis(amount), resource.getKind(), resource.getMetadata().getName(), resource.getMetadata().getNamespace()));
      this.resourcesNotReady = Collections.unmodifiableList(Arrays.asList(resource));
   }

   public KubernetesClientTimeoutException(Collection resourcesNotReady, long amount, TimeUnit timeUnit) {
      super(String.format("Timed out waiting for [%d] milliseconds for multiple resources. %s", timeUnit.toMillis(amount), notReadyToString(resourcesNotReady)));
      this.resourcesNotReady = Collections.unmodifiableList(new ArrayList(resourcesNotReady));
   }

   public List getResourcesNotReady() {
      return this.resourcesNotReady;
   }

   private static String notReadyToString(Iterable resources) {
      StringBuilder sb = new StringBuilder();
      sb.append("Resources that are not ready: ");
      boolean first = true;

      for(HasMetadata r : resources) {
         if (first) {
            first = false;
         } else {
            sb.append(", ");
         }

         sb.append("[Kind:").append(r.getKind()).append(" Name:").append(r.getMetadata().getName()).append(" Namespace:").append(r.getMetadata().getNamespace()).append("]");
      }

      return sb.toString();
   }
}
