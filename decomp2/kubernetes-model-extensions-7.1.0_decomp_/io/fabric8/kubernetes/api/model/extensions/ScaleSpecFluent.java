package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ScaleSpecFluent extends BaseFluent {
   private Integer replicas;
   private Map additionalProperties;

   public ScaleSpecFluent() {
   }

   public ScaleSpecFluent(ScaleSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ScaleSpec instance) {
      instance = instance != null ? instance : new ScaleSpec();
      if (instance != null) {
         this.withReplicas(instance.getReplicas());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public ScaleSpecFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public ScaleSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ScaleSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ScaleSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ScaleSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ScaleSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ScaleSpecFluent that = (ScaleSpecFluent)o;
            if (!Objects.equals(this.replicas, that.replicas)) {
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
      return Objects.hash(new Object[]{this.replicas, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
