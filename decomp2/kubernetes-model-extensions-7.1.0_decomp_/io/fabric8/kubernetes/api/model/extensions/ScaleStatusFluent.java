package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ScaleStatusFluent extends BaseFluent {
   private Integer replicas;
   private Map selector;
   private String targetSelector;
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
         this.withTargetSelector(instance.getTargetSelector());
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

   public ScaleStatusFluent addToSelector(String key, String value) {
      if (this.selector == null && key != null && value != null) {
         this.selector = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.selector.put(key, value);
      }

      return this;
   }

   public ScaleStatusFluent addToSelector(Map map) {
      if (this.selector == null && map != null) {
         this.selector = new LinkedHashMap();
      }

      if (map != null) {
         this.selector.putAll(map);
      }

      return this;
   }

   public ScaleStatusFluent removeFromSelector(String key) {
      if (this.selector == null) {
         return this;
      } else {
         if (key != null && this.selector != null) {
            this.selector.remove(key);
         }

         return this;
      }
   }

   public ScaleStatusFluent removeFromSelector(Map map) {
      if (this.selector == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.selector != null) {
                  this.selector.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getSelector() {
      return this.selector;
   }

   public ScaleStatusFluent withSelector(Map selector) {
      if (selector == null) {
         this.selector = null;
      } else {
         this.selector = new LinkedHashMap(selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public String getTargetSelector() {
      return this.targetSelector;
   }

   public ScaleStatusFluent withTargetSelector(String targetSelector) {
      this.targetSelector = targetSelector;
      return this;
   }

   public boolean hasTargetSelector() {
      return this.targetSelector != null;
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
            } else if (!Objects.equals(this.targetSelector, that.targetSelector)) {
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
      return Objects.hash(new Object[]{this.replicas, this.selector, this.targetSelector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.selector != null && !this.selector.isEmpty()) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.targetSelector != null) {
         sb.append("targetSelector:");
         sb.append(this.targetSelector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
