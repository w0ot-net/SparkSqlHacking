package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeviceCapacityFluent extends BaseFluent {
   private Quantity value;
   private Map additionalProperties;

   public DeviceCapacityFluent() {
   }

   public DeviceCapacityFluent(DeviceCapacity instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(DeviceCapacity instance) {
      instance = instance != null ? instance : new DeviceCapacity();
      if (instance != null) {
         this.withValue(instance.getValue());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Quantity getValue() {
      return this.value;
   }

   public DeviceCapacityFluent withValue(Quantity value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public DeviceCapacityFluent withNewValue(String amount, String format) {
      return this.withValue(new Quantity(amount, format));
   }

   public DeviceCapacityFluent withNewValue(String amount) {
      return this.withValue(new Quantity(amount));
   }

   public DeviceCapacityFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public DeviceCapacityFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public DeviceCapacityFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public DeviceCapacityFluent removeFromAdditionalProperties(Map map) {
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

   public DeviceCapacityFluent withAdditionalProperties(Map additionalProperties) {
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
            DeviceCapacityFluent that = (DeviceCapacityFluent)o;
            if (!Objects.equals(this.value, that.value)) {
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
      return Objects.hash(new Object[]{this.value, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
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
