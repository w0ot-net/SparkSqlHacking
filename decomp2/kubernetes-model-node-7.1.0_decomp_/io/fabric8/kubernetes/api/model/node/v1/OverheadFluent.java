package io.fabric8.kubernetes.api.model.node.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class OverheadFluent extends BaseFluent {
   private Map podFixed;
   private Map additionalProperties;

   public OverheadFluent() {
   }

   public OverheadFluent(Overhead instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Overhead instance) {
      instance = instance != null ? instance : new Overhead();
      if (instance != null) {
         this.withPodFixed(instance.getPodFixed());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public OverheadFluent addToPodFixed(String key, Quantity value) {
      if (this.podFixed == null && key != null && value != null) {
         this.podFixed = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.podFixed.put(key, value);
      }

      return this;
   }

   public OverheadFluent addToPodFixed(Map map) {
      if (this.podFixed == null && map != null) {
         this.podFixed = new LinkedHashMap();
      }

      if (map != null) {
         this.podFixed.putAll(map);
      }

      return this;
   }

   public OverheadFluent removeFromPodFixed(String key) {
      if (this.podFixed == null) {
         return this;
      } else {
         if (key != null && this.podFixed != null) {
            this.podFixed.remove(key);
         }

         return this;
      }
   }

   public OverheadFluent removeFromPodFixed(Map map) {
      if (this.podFixed == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.podFixed != null) {
                  this.podFixed.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getPodFixed() {
      return this.podFixed;
   }

   public OverheadFluent withPodFixed(Map podFixed) {
      if (podFixed == null) {
         this.podFixed = null;
      } else {
         this.podFixed = new LinkedHashMap(podFixed);
      }

      return this;
   }

   public boolean hasPodFixed() {
      return this.podFixed != null;
   }

   public OverheadFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public OverheadFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public OverheadFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public OverheadFluent removeFromAdditionalProperties(Map map) {
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

   public OverheadFluent withAdditionalProperties(Map additionalProperties) {
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
            OverheadFluent that = (OverheadFluent)o;
            if (!Objects.equals(this.podFixed, that.podFixed)) {
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
      return Objects.hash(new Object[]{this.podFixed, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.podFixed != null && !this.podFixed.isEmpty()) {
         sb.append("podFixed:");
         sb.append(this.podFixed + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
