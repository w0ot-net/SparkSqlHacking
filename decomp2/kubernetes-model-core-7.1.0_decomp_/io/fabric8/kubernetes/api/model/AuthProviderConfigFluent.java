package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AuthProviderConfigFluent extends BaseFluent {
   private Map config;
   private String name;
   private Map additionalProperties;

   public AuthProviderConfigFluent() {
   }

   public AuthProviderConfigFluent(AuthProviderConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AuthProviderConfig instance) {
      instance = instance != null ? instance : new AuthProviderConfig();
      if (instance != null) {
         this.withConfig(instance.getConfig());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AuthProviderConfigFluent addToConfig(String key, String value) {
      if (this.config == null && key != null && value != null) {
         this.config = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.config.put(key, value);
      }

      return this;
   }

   public AuthProviderConfigFluent addToConfig(Map map) {
      if (this.config == null && map != null) {
         this.config = new LinkedHashMap();
      }

      if (map != null) {
         this.config.putAll(map);
      }

      return this;
   }

   public AuthProviderConfigFluent removeFromConfig(String key) {
      if (this.config == null) {
         return this;
      } else {
         if (key != null && this.config != null) {
            this.config.remove(key);
         }

         return this;
      }
   }

   public AuthProviderConfigFluent removeFromConfig(Map map) {
      if (this.config == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.config != null) {
                  this.config.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getConfig() {
      return this.config;
   }

   public AuthProviderConfigFluent withConfig(Map config) {
      if (config == null) {
         this.config = null;
      } else {
         this.config = new LinkedHashMap(config);
      }

      return this;
   }

   public boolean hasConfig() {
      return this.config != null;
   }

   public String getName() {
      return this.name;
   }

   public AuthProviderConfigFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public AuthProviderConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AuthProviderConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AuthProviderConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AuthProviderConfigFluent removeFromAdditionalProperties(Map map) {
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

   public AuthProviderConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            AuthProviderConfigFluent that = (AuthProviderConfigFluent)o;
            if (!Objects.equals(this.config, that.config)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.config, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.config != null && !this.config.isEmpty()) {
         sb.append("config:");
         sb.append(this.config + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
