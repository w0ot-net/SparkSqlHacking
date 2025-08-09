package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MetricTargetFluent extends BaseFluent {
   private Integer averageUtilization;
   private Quantity averageValue;
   private String type;
   private Quantity value;
   private Map additionalProperties;

   public MetricTargetFluent() {
   }

   public MetricTargetFluent(MetricTarget instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MetricTarget instance) {
      instance = instance != null ? instance : new MetricTarget();
      if (instance != null) {
         this.withAverageUtilization(instance.getAverageUtilization());
         this.withAverageValue(instance.getAverageValue());
         this.withType(instance.getType());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAverageUtilization() {
      return this.averageUtilization;
   }

   public MetricTargetFluent withAverageUtilization(Integer averageUtilization) {
      this.averageUtilization = averageUtilization;
      return this;
   }

   public boolean hasAverageUtilization() {
      return this.averageUtilization != null;
   }

   public Quantity getAverageValue() {
      return this.averageValue;
   }

   public MetricTargetFluent withAverageValue(Quantity averageValue) {
      this.averageValue = averageValue;
      return this;
   }

   public boolean hasAverageValue() {
      return this.averageValue != null;
   }

   public MetricTargetFluent withNewAverageValue(String amount, String format) {
      return this.withAverageValue(new Quantity(amount, format));
   }

   public MetricTargetFluent withNewAverageValue(String amount) {
      return this.withAverageValue(new Quantity(amount));
   }

   public String getType() {
      return this.type;
   }

   public MetricTargetFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public Quantity getValue() {
      return this.value;
   }

   public MetricTargetFluent withValue(Quantity value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public MetricTargetFluent withNewValue(String amount, String format) {
      return this.withValue(new Quantity(amount, format));
   }

   public MetricTargetFluent withNewValue(String amount) {
      return this.withValue(new Quantity(amount));
   }

   public MetricTargetFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MetricTargetFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MetricTargetFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MetricTargetFluent removeFromAdditionalProperties(Map map) {
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

   public MetricTargetFluent withAdditionalProperties(Map additionalProperties) {
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
            MetricTargetFluent that = (MetricTargetFluent)o;
            if (!Objects.equals(this.averageUtilization, that.averageUtilization)) {
               return false;
            } else if (!Objects.equals(this.averageValue, that.averageValue)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
               return false;
            } else if (!Objects.equals(this.value, that.value)) {
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
      return Objects.hash(new Object[]{this.averageUtilization, this.averageValue, this.type, this.value, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.averageUtilization != null) {
         sb.append("averageUtilization:");
         sb.append(this.averageUtilization + ",");
      }

      if (this.averageValue != null) {
         sb.append("averageValue:");
         sb.append(this.averageValue + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
