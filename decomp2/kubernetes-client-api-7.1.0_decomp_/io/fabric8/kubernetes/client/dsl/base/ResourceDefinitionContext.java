package io.fabric8.kubernetes.client.dsl.base;

import io.fabric8.kubernetes.api.Pluralize;
import io.fabric8.kubernetes.api.model.APIResource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.Locale;
import java.util.Optional;

public class ResourceDefinitionContext {
   protected String group;
   protected boolean namespaced;
   protected String plural;
   protected String version;
   protected String kind;

   public String getGroup() {
      return this.group;
   }

   public String getPlural() {
      return this.plural;
   }

   public String getVersion() {
      return this.version;
   }

   public String getKind() {
      return this.kind;
   }

   public boolean isNamespaceScoped() {
      return this.namespaced;
   }

   protected void validate() {
      if (this.plural == null) {
         if (this.kind == null) {
            throw new IllegalArgumentException("Neither kind nor plural was set, at least one is required");
         }

         this.plural = Pluralize.toPlural(this.kind.toLowerCase(Locale.ROOT));
      }

   }

   public static ResourceDefinitionContext fromResourceType(Class resource) {
      try {
         return (new Builder()).withGroup(HasMetadata.getGroup(resource)).withVersion(HasMetadata.getVersion(resource)).withNamespaced(Utils.isResourceNamespaced(resource)).withPlural(HasMetadata.getPlural(resource)).withKind(HasMetadata.getKind(resource)).build();
      } catch (IllegalArgumentException e) {
         throw new KubernetesClientException(String.format("%s is not annotated appropriately: %s", resource.getName(), e.getMessage()), e);
      }
   }

   public static ResourceDefinitionContext fromApiResource(String apiVersion, APIResource resource) {
      return (new Builder()).withGroup((String)Optional.ofNullable(ApiVersionUtil.trimGroupOrNull(apiVersion)).orElse("")).withVersion(ApiVersionUtil.trimVersion(apiVersion)).withNamespaced(Boolean.TRUE.equals(resource.getNamespaced())).withPlural(resource.getName()).withKind(resource.getKind()).build();
   }

   public static class Builder {
      private final ResourceDefinitionContext resourceDefinitionContext = new ResourceDefinitionContext();

      public Builder withGroup(String group) {
         this.resourceDefinitionContext.group = group;
         return this;
      }

      public Builder withNamespaced(boolean namespaced) {
         this.resourceDefinitionContext.namespaced = namespaced;
         return this;
      }

      public Builder withPlural(String plural) {
         this.resourceDefinitionContext.plural = plural;
         return this;
      }

      public Builder withVersion(String version) {
         this.resourceDefinitionContext.version = version;
         return this;
      }

      public Builder withKind(String kind) {
         this.resourceDefinitionContext.kind = kind;
         return this;
      }

      public ResourceDefinitionContext build() {
         this.resourceDefinitionContext.validate();
         return this.resourceDefinitionContext;
      }
   }
}
