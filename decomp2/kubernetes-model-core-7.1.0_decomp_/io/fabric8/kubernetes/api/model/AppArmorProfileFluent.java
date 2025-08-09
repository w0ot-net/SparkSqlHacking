package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AppArmorProfileFluent extends BaseFluent {
   private String localhostProfile;
   private String type;
   private Map additionalProperties;

   public AppArmorProfileFluent() {
   }

   public AppArmorProfileFluent(AppArmorProfile instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AppArmorProfile instance) {
      instance = instance != null ? instance : new AppArmorProfile();
      if (instance != null) {
         this.withLocalhostProfile(instance.getLocalhostProfile());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLocalhostProfile() {
      return this.localhostProfile;
   }

   public AppArmorProfileFluent withLocalhostProfile(String localhostProfile) {
      this.localhostProfile = localhostProfile;
      return this;
   }

   public boolean hasLocalhostProfile() {
      return this.localhostProfile != null;
   }

   public String getType() {
      return this.type;
   }

   public AppArmorProfileFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public AppArmorProfileFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AppArmorProfileFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AppArmorProfileFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AppArmorProfileFluent removeFromAdditionalProperties(Map map) {
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

   public AppArmorProfileFluent withAdditionalProperties(Map additionalProperties) {
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
            AppArmorProfileFluent that = (AppArmorProfileFluent)o;
            if (!Objects.equals(this.localhostProfile, that.localhostProfile)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.localhostProfile, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.localhostProfile != null) {
         sb.append("localhostProfile:");
         sb.append(this.localhostProfile + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
