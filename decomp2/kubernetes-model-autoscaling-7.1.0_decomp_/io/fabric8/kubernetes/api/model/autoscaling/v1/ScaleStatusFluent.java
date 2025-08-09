package io.fabric8.kubernetes.api.model.autoscaling.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ScaleStatusFluent extends BaseFluent {
   private Integer replicas;
   private String selector;
   private Map additionalProperties;

   public ScaleStatusFluent() {
   }

   public ScaleStatusFluent(ScaleStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ScaleStatus instance) {
      instance = instance != null ? instance : new ScaleStatus();
      if (instance != null) {
         this.withReplicas(instance.getReplicas());
         this.withSelector(instance.getSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public ScaleStatusFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public String getSelector() {
      return this.selector;
   }

   public ScaleStatusFluent withSelector(String selector) {
      this.selector = selector;
      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public ScaleStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ScaleStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ScaleStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ScaleStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ScaleStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ScaleStatusFluent that = (ScaleStatusFluent)o;
            if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
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
      return Objects.hash(new Object[]{this.replicas, this.selector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
