package io.fabric8.kubernetes.api.model.resource.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NamedResourcesFilterFluent extends BaseFluent {
   private String selector;
   private Map additionalProperties;

   public NamedResourcesFilterFluent() {
   }

   public NamedResourcesFilterFluent(NamedResourcesFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesFilter instance) {
      instance = instance != null ? instance : new NamedResourcesFilter();
      if (instance != null) {
         this.withSelector(instance.getSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getSelector() {
      return this.selector;
   }

   public NamedResourcesFilterFluent withSelector(String selector) {
      this.selector = selector;
      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public NamedResourcesFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesFilterFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesFilterFluent that = (NamedResourcesFilterFluent)o;
            if (!Objects.equals(this.selector, that.selector)) {
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
      return Objects.hash(new Object[]{this.selector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
