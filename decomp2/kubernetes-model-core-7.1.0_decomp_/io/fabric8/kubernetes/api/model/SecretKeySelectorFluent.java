package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SecretKeySelectorFluent extends BaseFluent {
   private String key;
   private String name;
   private Boolean optional;
   private Map additionalProperties;

   public SecretKeySelectorFluent() {
   }

   public SecretKeySelectorFluent(SecretKeySelector instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SecretKeySelector instance) {
      instance = instance != null ? instance : new SecretKeySelector();
      if (instance != null) {
         this.withKey(instance.getKey());
         this.withName(instance.getName());
         this.withOptional(instance.getOptional());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKey() {
      return this.key;
   }

   public SecretKeySelectorFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public String getName() {
      return this.name;
   }

   public SecretKeySelectorFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Boolean getOptional() {
      return this.optional;
   }

   public SecretKeySelectorFluent withOptional(Boolean optional) {
      this.optional = optional;
      return this;
   }

   public boolean hasOptional() {
      return this.optional != null;
   }

   public SecretKeySelectorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SecretKeySelectorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SecretKeySelectorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SecretKeySelectorFluent removeFromAdditionalProperties(Map map) {
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

   public SecretKeySelectorFluent withAdditionalProperties(Map additionalProperties) {
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
            SecretKeySelectorFluent that = (SecretKeySelectorFluent)o;
            if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.optional, that.optional)) {
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
      return Objects.hash(new Object[]{this.key, this.name, this.optional, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.optional != null) {
         sb.append("optional:");
         sb.append(this.optional + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public SecretKeySelectorFluent withOptional() {
      return this.withOptional(true);
   }
}
