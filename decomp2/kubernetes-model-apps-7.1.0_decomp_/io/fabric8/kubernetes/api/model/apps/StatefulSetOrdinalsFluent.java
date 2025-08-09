package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class StatefulSetOrdinalsFluent extends BaseFluent {
   private Integer start;
   private Map additionalProperties;

   public StatefulSetOrdinalsFluent() {
   }

   public StatefulSetOrdinalsFluent(StatefulSetOrdinals instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatefulSetOrdinals instance) {
      instance = instance != null ? instance : new StatefulSetOrdinals();
      if (instance != null) {
         this.withStart(instance.getStart());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getStart() {
      return this.start;
   }

   public StatefulSetOrdinalsFluent withStart(Integer start) {
      this.start = start;
      return this;
   }

   public boolean hasStart() {
      return this.start != null;
   }

   public StatefulSetOrdinalsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatefulSetOrdinalsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatefulSetOrdinalsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatefulSetOrdinalsFluent removeFromAdditionalProperties(Map map) {
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

   public StatefulSetOrdinalsFluent withAdditionalProperties(Map additionalProperties) {
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
            StatefulSetOrdinalsFluent that = (StatefulSetOrdinalsFluent)o;
            if (!Objects.equals(this.start, that.start)) {
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
      return Objects.hash(new Object[]{this.start, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.start != null) {
         sb.append("start:");
         sb.append(this.start + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
