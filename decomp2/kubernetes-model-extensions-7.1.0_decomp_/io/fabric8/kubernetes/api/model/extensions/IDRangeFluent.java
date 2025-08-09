package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class IDRangeFluent extends BaseFluent {
   private Long max;
   private Long min;
   private Map additionalProperties;

   public IDRangeFluent() {
   }

   public IDRangeFluent(IDRange instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IDRange instance) {
      instance = instance != null ? instance : new IDRange();
      if (instance != null) {
         this.withMax(instance.getMax());
         this.withMin(instance.getMin());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Long getMax() {
      return this.max;
   }

   public IDRangeFluent withMax(Long max) {
      this.max = max;
      return this;
   }

   public boolean hasMax() {
      return this.max != null;
   }

   public Long getMin() {
      return this.min;
   }

   public IDRangeFluent withMin(Long min) {
      this.min = min;
      return this;
   }

   public boolean hasMin() {
      return this.min != null;
   }

   public IDRangeFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IDRangeFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IDRangeFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IDRangeFluent removeFromAdditionalProperties(Map map) {
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

   public IDRangeFluent withAdditionalProperties(Map additionalProperties) {
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
            IDRangeFluent that = (IDRangeFluent)o;
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
