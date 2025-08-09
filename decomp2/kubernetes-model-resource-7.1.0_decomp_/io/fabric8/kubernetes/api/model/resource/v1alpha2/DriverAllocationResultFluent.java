package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DriverAllocationResultFluent extends BaseFluent {
   private NamedResourcesAllocationResultBuilder namedResources;
   private Object vendorRequestParameters;
   private Map additionalProperties;

   public DriverAllocationResultFluent() {
   }

   public DriverAllocationResultFluent(DriverAllocationResult instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DriverAllocationResult instance) {
      instance = instance != null ? instance : new DriverAllocationResult();
      if (instance != null) {
         this.withNamedResources(instance.getNamedResources());
         this.withVendorRequestParameters(instance.getVendorRequestParameters());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesAllocationResult buildNamedResources() {
      return this.namedResources != null ? this.namedResources.build() : null;
   }

   public DriverAllocationResultFluent withNamedResources(NamedResourcesAllocationResult namedResources) {
      this._visitables.remove("namedResources");
      if (namedResources != null) {
         this.namedResources = new NamedResourcesAllocationResultBuilder(namedResources);
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

   public DriverAllocationResultFluent withNewNamedResources(String name) {
      return this.withNamedResources(new NamedResourcesAllocationResult(name));
   }

   public NamedResourcesNested withNewNamedResources() {
      return new NamedResourcesNested((NamedResourcesAllocationResult)null);
   }

   public NamedResourcesNested withNewNamedResourcesLike(NamedResourcesAllocationResult item) {
      return new NamedResourcesNested(item);
   }

   public NamedResourcesNested editNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesAllocationResult)Optional.ofNullable(this.buildNamedResources()).orElse((Object)null));
   }

   public NamedResourcesNested editOrNewNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesAllocationResult)Optional.ofNullable(this.buildNamedResources()).orElse((new NamedResourcesAllocationResultBuilder()).build()));
   }

   public NamedResourcesNested editOrNewNamedResourcesLike(NamedResourcesAllocationResult item) {
      return this.withNewNamedResourcesLike((NamedResourcesAllocationResult)Optional.ofNullable(this.buildNamedResources()).orElse(item));
   }

   public Object getVendorRequestParameters() {
      return this.vendorRequestParameters;
   }

   public DriverAllocationResultFluent withVendorRequestParameters(Object vendorRequestParameters) {
      this.vendorRequestParameters = vendorRequestParameters;
      return this;
   }

   public boolean hasVendorRequestParameters() {
      return this.vendorRequestParameters != null;
   }

   public DriverAllocationResultFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DriverAllocationResultFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DriverAllocationResultFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DriverAllocationResultFluent removeFromAdditionalProperties(Map map) {
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

   public DriverAllocationResultFluent withAdditionalProperties(Map additionalProperties) {
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
            DriverAllocationResultFluent that = (DriverAllocationResultFluent)o;
            if (!Objects.equals(this.namedResources, that.namedResources)) {
               return false;
            } else if (!Objects.equals(this.vendorRequestParameters, that.vendorRequestParameters)) {
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
      return Objects.hash(new Object[]{this.namedResources, this.vendorRequestParameters, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.namedResources != null) {
         sb.append("namedResources:");
         sb.append(this.namedResources + ",");
      }

      if (this.vendorRequestParameters != null) {
         sb.append("vendorRequestParameters:");
         sb.append(this.vendorRequestParameters + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NamedResourcesNested extends NamedResourcesAllocationResultFluent implements Nested {
      NamedResourcesAllocationResultBuilder builder;

      NamedResourcesNested(NamedResourcesAllocationResult item) {
         this.builder = new NamedResourcesAllocationResultBuilder(this, item);
      }

      public Object and() {
         return DriverAllocationResultFluent.this.withNamedResources(this.builder.build());
      }

      public Object endNamedResources() {
         return this.and();
      }
   }
}
