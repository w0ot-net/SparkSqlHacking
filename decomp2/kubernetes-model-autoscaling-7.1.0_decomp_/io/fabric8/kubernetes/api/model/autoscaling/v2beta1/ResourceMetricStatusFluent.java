package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceMetricStatusFluent extends BaseFluent {
   private Integer currentAverageUtilization;
   private Quantity currentAverageValue;
   private String name;
   private Map additionalProperties;

   public ResourceMetricStatusFluent() {
   }

   public ResourceMetricStatusFluent(ResourceMetricStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceMetricStatus instance) {
      instance = instance != null ? instance : new ResourceMetricStatus();
      if (instance != null) {
         this.withCurrentAverageUtilization(instance.getCurrentAverageUtilization());
         this.withCurrentAverageValue(instance.getCurrentAverageValue());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getCurrentAverageUtilization() {
      return this.currentAverageUtilization;
   }

   public ResourceMetricStatusFluent withCurrentAverageUtilization(Integer currentAverageUtilization) {
      this.currentAverageUtilization = currentAverageUtilization;
      return this;
   }

   public boolean hasCurrentAverageUtilization() {
      return this.currentAverageUtilization != null;
   }

   public Quantity getCurrentAverageValue() {
      return this.currentAverageValue;
   }

   public ResourceMetricStatusFluent withCurrentAverageValue(Quantity currentAverageValue) {
      this.currentAverageValue = currentAverageValue;
      return this;
   }

   public boolean hasCurrentAverageValue() {
      return this.currentAverageValue != null;
   }

   public ResourceMetricStatusFluent withNewCurrentAverageValue(String amount, String format) {
      return this.withCurrentAverageValue(new Quantity(amount, format));
   }

   public ResourceMetricStatusFluent withNewCurrentAverageValue(String amount) {
      return this.withCurrentAverageValue(new Quantity(amount));
   }

   public String getName() {
      return this.name;
   }

   public ResourceMetricStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public ResourceMetricStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceMetricStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceMetricStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceMetricStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceMetricStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceMetricStatusFluent that = (ResourceMetricStatusFluent)o;
            if (!Objects.equals(this.currentAverageUtilization, that.currentAverageUtilization)) {
               return false;
            } else if (!Objects.equals(this.currentAverageValue, that.currentAverageValue)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.currentAverageUtilization, this.currentAverageValue, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.currentAverageUtilization != null) {
         sb.append("currentAverageUtilization:");
         sb.append(this.currentAverageUtilization + ",");
      }

      if (this.currentAverageValue != null) {
         sb.append("currentAverageValue:");
         sb.append(this.currentAverageValue + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
