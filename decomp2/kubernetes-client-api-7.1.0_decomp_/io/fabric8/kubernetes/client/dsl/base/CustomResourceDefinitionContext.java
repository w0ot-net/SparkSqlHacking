package io.fabric8.kubernetes.client.dsl.base;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionBuilder;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionFluent;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionSpec;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionSpecFluent;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionVersion;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.KubernetesVersionPriority;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.model.Scope;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomResourceDefinitionContext extends ResourceDefinitionContext {
   private String name;
   private String scope;
   private boolean statusSubresource;

   public String getName() {
      return this.name;
   }

   public String getScope() {
      return this.scope;
   }

   public boolean isStatusSubresource() {
      return this.statusSubresource;
   }

   public boolean isNamespaceScoped() {
      return this.scope != null ? Scope.NAMESPACED.value().equals(this.scope) : false;
   }

   public static CustomResourceDefinitionBuilder v1beta1CRDFromCustomResourceType(Class customResource) {
      try {
         CustomResource instance = (CustomResource)customResource.getDeclaredConstructor().newInstance();
         String version = instance.getVersion();
         return (CustomResourceDefinitionBuilder)((CustomResourceDefinitionFluent.SpecNested)((CustomResourceDefinitionSpecFluent.NamesNested)((CustomResourceDefinitionSpecFluent.NamesNested)((CustomResourceDefinitionSpecFluent.NamesNested)((CustomResourceDefinitionFluent.SpecNested)((CustomResourceDefinitionFluent.SpecNested)((CustomResourceDefinitionSpecFluent.VersionsNested)((CustomResourceDefinitionSpecFluent.VersionsNested)((CustomResourceDefinitionSpecFluent.VersionsNested)((CustomResourceDefinitionFluent.SpecNested)((CustomResourceDefinitionFluent.SpecNested)((CustomResourceDefinitionBuilder)((CustomResourceDefinitionFluent.MetadataNested)(new CustomResourceDefinitionBuilder()).withNewMetadata().withName(instance.getCRDName())).endMetadata()).withNewSpec().withGroup(instance.getGroup())).withVersion(version)).addNewVersion().withName(version)).withServed(instance.isServed())).withStorage(instance.isStorage())).endVersion()).withScope(instance.getScope())).withNewNames().withKind(instance.getKind())).withPlural(instance.getPlural())).withSingular(instance.getSingular())).endNames()).endSpec();
      } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public static io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionBuilder v1CRDFromCustomResourceType(Class customResource) {
      try {
         CustomResource instance = (CustomResource)customResource.getDeclaredConstructor().newInstance();
         return (io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionBuilder)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionFluent.SpecNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.NamesNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.NamesNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.NamesNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionFluent.SpecNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionFluent.SpecNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.VersionsNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.VersionsNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpecFluent.VersionsNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionFluent.SpecNested)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionBuilder)((io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionFluent.MetadataNested)(new io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionBuilder()).withNewMetadata().withName(instance.getCRDName())).endMetadata()).withNewSpec().withGroup(instance.getGroup())).addNewVersion().withName(instance.getVersion())).withServed(instance.isServed())).withStorage(instance.isStorage())).endVersion()).withScope(instance.getScope())).withNewNames().withKind(instance.getKind())).withPlural(instance.getPlural())).withSingular(instance.getSingular())).endNames()).endSpec();
      } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public static CustomResourceDefinitionContext fromResourceType(Class customResource, String crdName) {
      return (new Builder()).withGroup(HasMetadata.getGroup(customResource)).withVersion(HasMetadata.getVersion(customResource)).withScope(Utils.isResourceNamespaced(customResource) ? Scope.NAMESPACED.value() : Scope.CLUSTER.value()).withName(crdName).withPlural(HasMetadata.getPlural(customResource)).withKind(HasMetadata.getKind(customResource)).build();
   }

   public static CustomResourceDefinitionContext fromCustomResourceType(Class customResource) {
      try {
         CustomResource instance = (CustomResource)customResource.getDeclaredConstructor().newInstance();
         return (new Builder()).withGroup(instance.getGroup()).withVersion(instance.getVersion()).withScope(instance.getScope()).withName(instance.getCRDName()).withPlural(instance.getPlural()).withKind(instance.getKind()).build();
      } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public static CustomResourceDefinitionContext fromCrd(CustomResourceDefinition crd) {
      CustomResourceDefinitionSpec spec = crd.getSpec();
      return (new Builder()).withGroup(spec.getGroup()).withVersion(getVersion(spec)).withScope(spec.getScope()).withName(crd.getMetadata().getName()).withPlural(spec.getNames().getPlural()).withKind(spec.getNames().getKind()).withStatusSubresource(spec.getSubresources() != null && spec.getSubresources().getStatus() != null).build();
   }

   public static CustomResourceDefinitionContext fromCrd(io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition crd) {
      String version = getVersion(crd.getSpec());
      return (new Builder()).withGroup(crd.getSpec().getGroup()).withVersion(version).withScope(crd.getSpec().getScope()).withName(crd.getMetadata().getName()).withPlural(crd.getSpec().getNames().getPlural()).withKind(crd.getSpec().getNames().getKind()).withStatusSubresource(crd.getSpec().getVersions().stream().filter((v) -> version.equals(v.getName())).anyMatch((v) -> v.getSubresources() != null && v.getSubresources().getStatus() != null)).build();
   }

   private static String getVersion(List versions, String defaultVersion) {
      return (String)Optional.ofNullable(versions).map(KubernetesVersionPriority::highestPriority).orElse(defaultVersion);
   }

   private static String getVersion(CustomResourceDefinitionSpec spec) {
      return getVersion((List)spec.getVersions().stream().map(CustomResourceDefinitionVersion::getName).collect(Collectors.toList()), spec.getVersion());
   }

   private static String getVersion(io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionSpec spec) {
      return getVersion((List)spec.getVersions().stream().map(io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionVersion::getName).collect(Collectors.toList()), (String)null);
   }

   public static class Builder {
      private final CustomResourceDefinitionContext customResourceDefinitionContext = new CustomResourceDefinitionContext();

      public Builder withName(String name) {
         this.customResourceDefinitionContext.name = name;
         return this;
      }

      public Builder withGroup(String group) {
         this.customResourceDefinitionContext.group = group;
         return this;
      }

      public Builder withScope(String scope) {
         this.customResourceDefinitionContext.scope = scope;
         return this;
      }

      public Builder withPlural(String plural) {
         this.customResourceDefinitionContext.plural = plural;
         return this;
      }

      public Builder withVersion(String version) {
         this.customResourceDefinitionContext.version = version;
         return this;
      }

      public Builder withKind(String kind) {
         this.customResourceDefinitionContext.kind = kind;
         return this;
      }

      public Builder withStatusSubresource(boolean statusSubresource) {
         this.customResourceDefinitionContext.statusSubresource = statusSubresource;
         return this;
      }

      public CustomResourceDefinitionContext build() {
         this.customResourceDefinitionContext.validate();
         return this.customResourceDefinitionContext;
      }
   }
}
