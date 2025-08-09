package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.APIResourceList;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.GenericKubernetesResourceList;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.NamespacedInOutCreateable;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class Handlers {
   private final Map resourceHandlers = new ConcurrentHashMap();
   private final Map genericDefinitions = new ConcurrentHashMap();

   public void register(Class type, Function operationConstructor) {
      if (this.resourceHandlers.put(type, new ResourceHandlerImpl(type, operationConstructor)) != null) {
         throw new AssertionError(String.format("%s already registered", type.getName()));
      }
   }

   public void unregister(Class type) {
      this.resourceHandlers.remove(type);
   }

   public ResourceHandler get(HasMetadata meta, Client client) {
      Class<T> type = meta.getClass();
      if (type.equals(GenericKubernetesResource.class)) {
         GenericKubernetesResource gkr = (GenericKubernetesResource)meta;
         ResourceDefinitionContext rdc = this.getResourceDefinitionContext(gkr.getApiVersion(), gkr.getKind(), client);
         if (rdc != null) {
            return new ResourceHandlerImpl(GenericKubernetesResource.class, GenericKubernetesResourceList.class, rdc);
         }
      }

      ResourceHandler<T, V> result = this.get(type);
      if (result == null) {
         throw new KubernetesClientException("Could not find a registered handler for item: [" + meta + "].");
      } else {
         return result;
      }
   }

   public ResourceDefinitionContext getResourceDefinitionContext(String apiVersion, String kind, Client client) {
      Class<? extends KubernetesResource> clazz = ((BaseClient)client.adapt(BaseClient.class)).getKubernetesSerialization().getRegisteredKubernetesResource(apiVersion, kind);
      ResourceDefinitionContext rdc = null;
      if (clazz != null) {
         rdc = ResourceDefinitionContext.fromResourceType(clazz);
      } else {
         if (kind == null || apiVersion == null) {
            return null;
         }

         String api = ApiVersionUtil.trimGroupOrNull(apiVersion);
         if (api == null) {
            return null;
         }

         String version = ApiVersionUtil.trimVersion(apiVersion);
         rdc = (ResourceDefinitionContext)this.genericDefinitions.computeIfAbsent(Arrays.asList(kind, apiVersion), (k) -> {
            APIResourceList resourceList = client.getApiResources(apiVersion);
            return resourceList == null ? null : (ResourceDefinitionContext)resourceList.getResources().stream().filter((r) -> kind.equals(r.getKind())).findFirst().map((resource) -> (new ResourceDefinitionContext.Builder()).withGroup(api).withKind(kind).withNamespaced(Boolean.TRUE.equals(resource.getNamespaced())).withPlural(resource.getName()).withVersion(version).build()).orElse((Object)null);
         });
      }

      return rdc;
   }

   private ResourceHandler get(Class type) {
      return type.equals(GenericKubernetesResource.class) ? null : (ResourceHandler)this.resourceHandlers.computeIfAbsent(type, (k) -> new ResourceHandlerImpl(type, (Function)null));
   }

   public HasMetadataOperation getOperation(Class type, Class listType, Client client) {
      ResourceHandler<T, ?> resourceHandler = this.get(type);
      if (resourceHandler == null) {
         throw new IllegalStateException();
      } else {
         return resourceHandler.operation(client, listType);
      }
   }

   public HasMetadataOperation getNonListingOperation(Class type, Client client) {
      return this.getOperation(type, KubernetesResourceUtil.inferListType(type), client);
   }

   public NamespacedInOutCreateable getNamespacedHasMetadataCreateOnlyOperation(Class type, Client client) {
      HasMetadataOperation<T, ?, Resource<T>> operation = this.getNonListingOperation(type, client);
      Objects.requireNonNull(operation);
      return operation::inNamespace;
   }
}
