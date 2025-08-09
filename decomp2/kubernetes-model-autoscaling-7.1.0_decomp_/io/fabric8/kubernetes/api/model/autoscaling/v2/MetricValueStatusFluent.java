package io.fabric8.kubernetes.api.model.autoscaling.v2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MetricValueStatusFluent extends BaseFluent {
   private Integer averageUtilization;
   private Quantity averageValue;
   private Quantity value;
   private Map additionalProperties;

   public MetricValueStatusFluent() {
   }

   public MetricValueStatusFluent(MetricValueStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(MetricValueStatus instance) {
      instance = instance != null ? instance : new MetricValueStatus();
      if (instance != null) {
         this.withAverageUtilization(instance.getAverageUtilization());
         this.withAverageValue(instance.getAverageValue());
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getAverageUtilization() {
      return this.averageUtilization;
   }

   public MetricValueStatusFluent withAverageUtilization(Integer averageUtilization) {
      this.averageUtilization = averageUtilization;
      return this;
   }

   public boolean hasAverageUtilization() {
      return this.averageUtilization != null;
   }

   public Quantity getAverageValue() {
      return this.averageValue;
   }

   public MetricValueStatusFluent withAverageValue(Quantity averageValue) {
      this.averageValue = averageValue;
      return this;
   }

   public boolean hasAverageValue() {
      return this.averageValue != null;
   }

   public MetricValueStatusFluent withNewAverageValue(String amount, String format) {
      return this.withAverageValue(new Quantity(amount, format));
   }

   public MetricValueStatusFluent withNewAverageValue(String amount) {
      return this.withAverageValue(new Quantity(amount));
   }

   public Quantity getValue() {
      return this.value;
   }

   public MetricValueStatusFluent withValue(Quantity value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public MetricValueStatusFluent withNewValue(String amount, String format) {
      return this.withValue(new Quantity(amount, format));
   }

   public MetricValueStatusFluent withNewValue(String amount) {
      return this.withValue(new Quantity(amount));
   }

   public MetricValueStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public MetricValueStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public MetricValueStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public MetricValueStatusFluent removeFromAdditionalProperties(Map map) {
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

   public MetricValueStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            MetricValueStatusFluent that = (MetricValueStatusFluent)o;
            if (!Objects.equals(this.averageUtilization, that.averageUtilization)) {
               return false;
            } else if (!Objects.equals(this.averageValue, that.averageValue)) {
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
      return Objects.hash(new Object[]{this.averageUtilization, this.averageValue, this.value, this.additionalProperties, super.hashCode()});
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
