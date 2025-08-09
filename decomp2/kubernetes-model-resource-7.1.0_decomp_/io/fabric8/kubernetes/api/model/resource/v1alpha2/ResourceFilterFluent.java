package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceFilterFluent extends BaseFluent {
   private String driverName;
   private NamedResourcesFilterBuilder namedResources;
   private Map additionalProperties;

   public ResourceFilterFluent() {
   }

   public ResourceFilterFluent(ResourceFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceFilter instance) {
      instance = instance != null ? instance : new ResourceFilter();
      if (instance != null) {
         this.withDriverName(instance.getDriverName());
         this.withNamedResources(instance.getNamedResources());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDriverName() {
      return this.driverName;
   }

   public ResourceFilterFluent withDriverName(String driverName) {
      this.driverName = driverName;
      return this;
   }

   public boolean hasDriverName() {
      return this.driverName != null;
   }

   public NamedResourcesFilter buildNamedResources() {
      return this.namedResources != null ? this.namedResources.build() : null;
   }

   public ResourceFilterFluent withNamedResources(NamedResourcesFilter namedResources) {
      this._visitables.remove("namedResources");
      if (namedResources != null) {
         this.namedResources = new NamedResourcesFilterBuilder(namedResources);
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

   public ResourceFilterFluent withNewNamedResources(String selector) {
      return this.withNamedResources(new NamedResourcesFilter(selector));
   }

   public NamedResourcesNested withNewNamedResources() {
      return new NamedResourcesNested((NamedResourcesFilter)null);
   }

   public NamedResourcesNested withNewNamedResourcesLike(NamedResourcesFilter item) {
      return new NamedResourcesNested(item);
   }

   public NamedResourcesNested editNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesFilter)Optional.ofNullable(this.buildNamedResources()).orElse((Object)null));
   }

   public NamedResourcesNested editOrNewNamedResources() {
      return this.withNewNamedResourcesLike((NamedResourcesFilter)Optional.ofNullable(this.buildNamedResources()).orElse((new NamedResourcesFilterBuilder()).build()));
   }

   public NamedResourcesNested editOrNewNamedResourcesLike(NamedResourcesFilter item) {
      return this.withNewNamedResourcesLike((NamedResourcesFilter)Optional.ofNullable(this.buildNamedResources()).orElse(item));
   }

   public ResourceFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceFilterFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceFilterFluent that = (ResourceFilterFluent)o;
            if (!Objects.equals(this.driverName, that.driverName)) {
               return false;
            } else if (!Objects.equals(this.namedResources, that.namedResources)) {
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
      return Objects.hash(new Object[]{this.driverName, this.namedResources, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.driverName != null) {
         sb.append("driverName:");
         sb.append(this.driverName + ",");
      }

      if (this.namedResources != null) {
         sb.append("namedResources:");
         sb.append(this.namedResources + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NamedResourcesNested extends NamedResourcesFilterFluent implements Nested {
      NamedResourcesFilterBuilder builder;

      NamedResourcesNested(NamedResourcesFilter item) {
         this.builder = new NamedResourcesFilterBuilder(this, item);
      }

      public Object and() {
         return ResourceFilterFluent.this.withNamedResources(this.builder.build());
      }

      public Object endNamedResources() {
         return this.and();
      }
   }
}
