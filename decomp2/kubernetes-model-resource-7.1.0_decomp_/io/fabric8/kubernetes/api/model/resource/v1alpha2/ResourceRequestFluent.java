package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceRequestFluent extends BaseFluent {
   private NamedResourcesRequestBuilder namedResources;
   private Object vendorParameters;
   private Map additionalProperties;

   public ResourceRequestFluent() {
   }

   public ResourceRequestFluent(ResourceRequest instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceRequest instance) {
      instance = instance != null ? instance : new ResourceRequest();
      if (instance != null) {
         this.withNamedResources(instance.getNamedResources());
         this.withVendorParameters(instance.getVendorParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesRequest buildNamedResources() {
      return this.namedResources != null ? this.namedResources.build() : null;
   }

   public ResourceRequestFluent withNamedResources(NamedResourcesRequest namedResources) {
      this._visitables.remove("namedResources");
      if (namedResources != null) {
         this.namedResources = new NamedResourcesRequestBuilder(namedResources);
         this._visitables.get("namedResources").add(this.namedResources);
      } else {
         this.namedResources = null;
         this._visitables.get("namedResources").remove(this.namedResources);
      }

      return this;
   }

   public boolean hasNamedResources() {
      return this.namedResources != null;
   }

   public ResourceRequestFluent withNewNamedResources(String selector) {
      return this.withNamedResources(new NamedResourcesRequest(selector));
   }

   public NamedResourcesNested withNewNamedResources() {
      return new NamedResourcesNested((NamedResourcesRequest)null);
   }

   public NamedResourcesNested withNewNamedResourcesLike(NamedResourcesRequest item) {
      return new NamedResourcesNested(item);
   }

   public NamedResourcesNested editNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesRequest)Optional.ofNullable(this.buildNamedResources()).orElse((Object)null));
   }

   public NamedResourcesNested editOrNewNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesRequest)Optional.ofNullable(this.buildNamedResources()).orElse((new NamedResourcesRequestBuilder()).build()));
   }

   public NamedResourcesNested editOrNewNamedResourcesLike(NamedResourcesRequest item) {
      return this.withNewNamedResourcesLike((NamedResourcesRequest)Optional.ofNullable(this.buildNamedResources()).orElse(item));
   }

   public Object getVendorParameters() {
      return this.vendorParameters;
   }

   public ResourceRequestFluent withVendorParameters(Object vendorParameters) {
      this.vendorParameters = vendorParameters;
      return this;
   }

   public boolean hasVendorParameters() {
      return this.vendorParameters != null;
   }

   public ResourceRequestFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceRequestFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceRequestFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceRequestFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public ResourceRequestFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            ResourceRequestFluent that = (ResourceRequestFluent)o;
            if (!Objects.equals(this.namedResources, that.namedResources)) {
               return false;
            } else if (!Objects.equals(this.vendorParameters, that.vendorParameters)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.namedResources, this.vendorParameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.namedResources != null) {
         sb.append("namedResources:");
         sb.append(this.namedResources + ",");
      }

      if (this.vendorParameters != null) {
         sb.append("vendorParameters:");
         sb.append(this.vendorParameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NamedResourcesNested extends NamedResourcesRequestFluent implements Nested {
      NamedResourcesRequestBuilder builder;

      NamedResourcesNested(NamedResourcesRequest item) {
         this.builder = new NamedResourcesRequestBuilder(this, item);
      }

      public Object and() {
         return ResourceRequestFluent.this.withNamedResources(this.builder.build());
      }

      public Object endNamedResources() {
         return this.and();
      }
   }
}
