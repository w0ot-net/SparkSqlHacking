package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ResourceFieldSelectorFluent extends BaseFluent {
   private String containerName;
   private QuantityBuilder divisor;
   private String resource;
   private Map additionalProperties;

   public ResourceFieldSelectorFluent() {
   }

   public ResourceFieldSelectorFluent(ResourceFieldSelector instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceFieldSelector instance) {
      instance = instance != null ? instance : new ResourceFieldSelector();
      if (instance != null) {
         this.withContainerName(instance.getContainerName());
         this.withDivisor(instance.getDivisor());
         this.withResource(instance.getResource());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContainerName() {
      return this.containerName;
   }

   public ResourceFieldSelectorFluent withContainerName(String containerName) {
      this.containerName = containerName;
      return this;
   }

   public boolean hasContainerName() {
      return this.containerName != null;
   }

   public Quantity buildDivisor() {
      return this.divisor != null ? this.divisor.build() : null;
   }

   public ResourceFieldSelectorFluent withDivisor(Quantity divisor) {
      this._visitables.remove("divisor");
      if (divisor != null) {
         this.divisor = new QuantityBuilder(divisor);
         this._visitables.get("divisor").add(this.divisor);
      } else {
         this.divisor = null;
         this._visitables.get("divisor").remove(this.divisor);
      }

      return this;
   }

   public boolean hasDivisor() {
      return this.divisor != null;
   }

   public ResourceFieldSelectorFluent withNewDivisor(String amount, String format) {
      return this.withDivisor(new Quantity(amount, format));
   }

   public ResourceFieldSelectorFluent withNewDivisor(String amount) {
      return this.withDivisor(new Quantity(amount));
   }

   public DivisorNested withNewDivisor() {
      return new DivisorNested((Quantity)null);
   }

   public DivisorNested withNewDivisorLike(Quantity item) {
      return new DivisorNested(item);
   }

   public DivisorNested editDivisor() {
      return this.withNewDivisorLike((Quantity)Optional.ofNullable(this.buildDivisor()).orElse((Object)null));
   }

   public DivisorNested editOrNewDivisor() {
      return this.withNewDivisorLike((Quantity)Optional.ofNullable(this.buildDivisor()).orElse((new QuantityBuilder()).build()));
   }

   public DivisorNested editOrNewDivisorLike(Quantity item) {
      return this.withNewDivisorLike((Quantity)Optional.ofNullable(this.buildDivisor()).orElse(item));
   }

   public String getResource() {
      return this.resource;
   }

   public ResourceFieldSelectorFluent withResource(String resource) {
      this.resource = resource;
      return this;
   }

   public boolean hasResource() {
      return this.resource != null;
   }

   public ResourceFieldSelectorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceFieldSelectorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceFieldSelectorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceFieldSelectorFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceFieldSelectorFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceFieldSelectorFluent that = (ResourceFieldSelectorFluent)o;
            if (!Objects.equals(this.containerName, that.containerName)) {
               return false;
            } else if (!Objects.equals(this.divisor, that.divisor)) {
               return false;
            } else if (!Objects.equals(this.resource, that.resource)) {
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
      return Objects.hash(new Object[]{this.containerName, this.divisor, this.resource, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.containerName != null) {
         sb.append("containerName:");
         sb.append(this.containerName + ",");
      }

      if (this.divisor != null) {
         sb.append("divisor:");
         sb.append(this.divisor + ",");
      }

      if (this.resource != null) {
         sb.append("resource:");
         sb.append(this.resource + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DivisorNested extends QuantityFluent implements Nested {
      QuantityBuilder builder;

      DivisorNested(Quantity item) {
         this.builder = new QuantityBuilder(this, item);
      }

      public Object and() {
         return ResourceFieldSelectorFluent.this.withDivisor(this.builder.build());
      }

      public Object endDivisor() {
         return this.and();
      }
   }
}
