package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceQuotaStatusFluent extends BaseFluent {
   private Map hard;
   private Map used;
   private Map additionalProperties;

   public ResourceQuotaStatusFluent() {
   }

   public ResourceQuotaStatusFluent(ResourceQuotaStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceQuotaStatus instance) {
      instance = instance != null ? instance : new ResourceQuotaStatus();
      if (instance != null) {
         this.withHard(instance.getHard());
         this.withUsed(instance.getUsed());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ResourceQuotaStatusFluent addToHard(String key, Quantity value) {
      if (this.hard == null && key != null && value != null) {
         this.hard = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.hard.put(key, value);
      }

      return this;
   }

   public ResourceQuotaStatusFluent addToHard(Map map) {
      if (this.hard == null && map != null) {
         this.hard = new LinkedHashMap();
      }

      if (map != null) {
         this.hard.putAll(map);
      }

      return this;
   }

   public ResourceQuotaStatusFluent removeFromHard(String key) {
      if (this.hard == null) {
         return this;
      } else {
         if (key != null && this.hard != null) {
            this.hard.remove(key);
         }

         return this;
      }
   }

   public ResourceQuotaStatusFluent removeFromHard(Map map) {
      if (this.hard == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.hard != null) {
                  this.hard.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getHard() {
      return this.hard;
   }

   public ResourceQuotaStatusFluent withHard(Map hard) {
      if (hard == null) {
         this.hard = null;
      } else {
         this.hard = new LinkedHashMap(hard);
      }

      return this;
   }

   public boolean hasHard() {
      return this.hard != null;
   }

   public ResourceQuotaStatusFluent addToUsed(String key, Quantity value) {
      if (this.used == null && key != null && value != null) {
         this.used = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.used.put(key, value);
      }

      return this;
   }

   public ResourceQuotaStatusFluent addToUsed(Map map) {
      if (this.used == null && map != null) {
         this.used = new LinkedHashMap();
      }

      if (map != null) {
         this.used.putAll(map);
      }

      return this;
   }

   public ResourceQuotaStatusFluent removeFromUsed(String key) {
      if (this.used == null) {
         return this;
      } else {
         if (key != null && this.used != null) {
            this.used.remove(key);
         }

         return this;
      }
   }

   public ResourceQuotaStatusFluent removeFromUsed(Map map) {
      if (this.used == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.used != null) {
                  this.used.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getUsed() {
      return this.used;
   }

   public ResourceQuotaStatusFluent withUsed(Map used) {
      if (used == null) {
         this.used = null;
      } else {
         this.used = new LinkedHashMap(used);
      }

      return this;
   }

   public boolean hasUsed() {
      return this.used != null;
   }

   public ResourceQuotaStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceQuotaStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceQuotaStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceQuotaStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceQuotaStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceQuotaStatusFluent that = (ResourceQuotaStatusFluent)o;
            if (!Objects.equals(this.hard, that.hard)) {
               return false;
            } else if (!Objects.equals(this.used, that.used)) {
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
      return Objects.hash(new Object[]{this.hard, this.used, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hard != null && !this.hard.isEmpty()) {
         sb.append("hard:");
         sb.append(this.hard + ",");
      }

      if (this.used != null && !this.used.isEmpty()) {
         sb.append("used:");
         sb.append(this.used + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
