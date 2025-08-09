package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HostPortRangeFluent extends BaseFluent {
   private Integer max;
   private Integer min;
   private Map additionalProperties;

   public HostPortRangeFluent() {
   }

   public HostPortRangeFluent(HostPortRange instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HostPortRange instance) {
      instance = instance != null ? instance : new HostPortRange();
      if (instance != null) {
         this.withMax(instance.getMax());
         this.withMin(instance.getMin());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMax() {
      return this.max;
   }

   public HostPortRangeFluent withMax(Integer max) {
      this.max = max;
      return this;
   }

   public boolean hasMax() {
      return this.max != null;
   }

   public Integer getMin() {
      return this.min;
   }

   public HostPortRangeFluent withMin(Integer min) {
      this.min = min;
      return this;
   }

   public boolean hasMin() {
      return this.min != null;
   }

   public HostPortRangeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HostPortRangeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HostPortRangeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HostPortRangeFluent removeFromAdditionalProperties(Map map) {
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

   public HostPortRangeFluent withAdditionalProperties(Map additionalProperties) {
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
            HostPortRangeFluent that = (HostPortRangeFluent)o;
            if (!Objects.equals(this.max, that.max)) {
               return false;
            } else if (!Objects.equals(this.min, that.min)) {
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
      return Objects.hash(new Object[]{this.max, this.min, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.max != null) {
         sb.append("max:");
         sb.append(this.max + ",");
      }

      if (this.min != null) {
         sb.append("min:");
         sb.append(this.min + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
