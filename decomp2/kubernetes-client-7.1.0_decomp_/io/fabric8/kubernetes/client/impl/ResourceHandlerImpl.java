package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.Utils;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

class ResourceHandlerImpl implements ResourceHandler {
   private final ResourceDefinitionContext context;
   private final Class type;
   private final Class builderClass;
   private final Class defaultListClass;
   private final Function operationConstructor;

   ResourceHandlerImpl(Class type, Function operationConstructor) {
      this.type = type;
      this.context = ResourceDefinitionContext.fromResourceType(type);
      this.builderClass = KubernetesResourceUtil.inferBuilderType(type);
      this.defaultListClass = KubernetesResourceUtil.inferListType(type);
      this.operationConstructor = operationConstructor;
   }

   ResourceHandlerImpl(Class type, Class listClass, ResourceDefinitionContext context) {
      this.type = type;
      this.context = context;
      this.defaultListClass = listClass;
      this.builderClass = KubernetesResourceUtil.inferBuilderType(type);
      this.operationConstructor = null;
   }

   public VisitableBuilder edit(HasMetadata item) {
      if (this.builderClass == null) {
         throw new KubernetesClientException(String.format("Cannot edit %s with visitors, no builder was found", this.type.getName()));
      } else {
         try {
            return (VisitableBuilder)this.builderClass.getDeclaredConstructor(item.getClass()).newInstance(item);
         } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }
   }

   public HasMetadataOperation operation(Client client, Class listType) {
      if (this.operationConstructor != null) {
         if (listType != null && !listType.isAssignableFrom(this.defaultListClass)) {
            throw new IllegalArgumentException(String.format("Handler type %s with list %s not compatible with %s", this.type, this.defaultListClass.getName(), listType.getName()));
         } else {
            return (HasMetadataOperation)this.operationConstructor.apply(client);
         }
      } else {
         return new HasMetadataOperationsImpl(client, this.context, this.type, (Class)Utils.getNonNullOrElse(listType, this.defaultListClass));
      }
   }

   public boolean hasOperation() {
      return this.operationConstructor != null;
   }
}
