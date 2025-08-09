package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceHealthFluent extends BaseFluent {
   private String health;
   private String resourceID;
   private Map additionalProperties;

   public ResourceHealthFluent() {
   }

   public ResourceHealthFluent(ResourceHealth instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ResourceHealth instance) {
      instance = instance != null ? instance : new ResourceHealth();
      if (instance != null) {
         this.withHealth(instance.getHealth());
         this.withResourceID(instance.getResourceID());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHealth() {
      return this.health;
   }

   public ResourceHealthFluent withHealth(String health) {
      this.health = health;
      return this;
   }

   public boolean hasHealth() {
      return this.health != null;
   }

   public String getResourceID() {
      return this.resourceID;
   }

   public ResourceHealthFluent withResourceID(String resourceID) {
      this.resourceID = resourceID;
      return this;
   }

   public boolean hasResourceID() {
      return this.resourceID != null;
   }

   public ResourceHealthFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ResourceHealthFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ResourceHealthFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ResourceHealthFluent removeFromAdditionalProperties(Map map) {
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

   public ResourceHealthFluent withAdditionalProperties(Map additionalProperties) {
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
            ResourceHealthFluent that = (ResourceHealthFluent)o;
            if (!Objects.equals(this.health, that.health)) {
               return false;
            } else if (!Objects.equals(this.resourceID, that.resourceID)) {
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
      return Objects.hash(new Object[]{this.health, this.resourceID, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.health != null) {
         sb.append("health:");
         sb.append(this.health + ",");
      }

      if (this.resourceID != null) {
         sb.append("resourceID:");
         sb.append(this.resourceID + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
