package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerResourceMetricSourceFluent extends BaseFluent {
   private String container;
   private String name;
   private Integer targetAverageUtilization;
   private Quantity targetAverageValue;
   private Map additionalProperties;

   public ContainerResourceMetricSourceFluent() {
   }

   public ContainerResourceMetricSourceFluent(ContainerResourceMetricSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerResourceMetricSource instance) {
      instance = instance != null ? instance : new ContainerResourceMetricSource();
      if (instance != null) {
         this.withContainer(instance.getContainer());
         this.withName(instance.getName());
         this.withTargetAverageUtilization(instance.getTargetAverageUtilization());
         this.withTargetAverageValue(instance.getTargetAverageValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContainer() {
      return this.container;
   }

   public ContainerResourceMetricSourceFluent withContainer(String container) {
      this.container = container;
      return this;
   }

   public boolean hasContainer() {
      return this.container != null;
   }

   public String getName() {
      return this.name;
   }

   public ContainerResourceMetricSourceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Integer getTargetAverageUtilization() {
      return this.targetAverageUtilization;
   }

   public ContainerResourceMetricSourceFluent withTargetAverageUtilization(Integer targetAverageUtilization) {
      this.targetAverageUtilization = targetAverageUtilization;
      return this;
   }

   public boolean hasTargetAverageUtilization() {
      return this.targetAverageUtilization != null;
   }

   public Quantity getTargetAverageValue() {
      return this.targetAverageValue;
   }

   public ContainerResourceMetricSourceFluent withTargetAverageValue(Quantity targetAverageValue) {
      this.targetAverageValue = targetAverageValue;
      return this;
   }

   public boolean hasTargetAverageValue() {
      return this.targetAverageValue != null;
   }

   public ContainerResourceMetricSourceFluent withNewTargetAverageValue(String amount, String format) {
      return this.withTargetAverageValue(new Quantity(amount, format));
   }

   public ContainerResourceMetricSourceFluent withNewTargetAverageValue(String amount) {
      return this.withTargetAverageValue(new Quantity(amount));
   }

   public ContainerResourceMetricSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerResourceMetricSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerResourceMetricSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerResourceMetricSourceFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerResourceMetricSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerResourceMetricSourceFluent that = (ContainerResourceMetricSourceFluent)o;
            if (!Objects.equals(this.container, that.container)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.targetAverageUtilization, that.targetAverageUtilization)) {
               return false;
            } else if (!Objects.equals(this.targetAverageValue, that.targetAverageValue)) {
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
      return Objects.hash(new Object[]{this.container, this.name, this.targetAverageUtilization, this.targetAverageValue, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.container != null) {
         sb.append("container:");
         sb.append(this.container + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.targetAverageUtilization != null) {
         sb.append("targetAverageUtilization:");
         sb.append(this.targetAverageUtilization + ",");
      }

      if (this.targetAverageValue != null) {
         sb.append("targetAverageValue:");
         sb.append(this.targetAverageValue + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
