package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class KeyToPathFluent extends BaseFluent {
   private String key;
   private Integer mode;
   private String path;
   private Map additionalProperties;

   public KeyToPathFluent() {
   }

   public KeyToPathFluent(KeyToPath instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(KeyToPath instance) {
      instance = instance != null ? instance : new KeyToPath();
      if (instance != null) {
         this.withKey(instance.getKey());
         this.withMode(instance.getMode());
         this.withPath(instance.getPath());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getKey() {
      return this.key;
   }

   public KeyToPathFluent withKey(String key) {
      this.key = key;
      return this;
   }

   public boolean hasKey() {
      return this.key != null;
   }

   public Integer getMode() {
      return this.mode;
   }

   public KeyToPathFluent withMode(Integer mode) {
      this.mode = mode;
      return this;
   }

   public boolean hasMode() {
      return this.mode != null;
   }

   public String getPath() {
      return this.path;
   }

   public KeyToPathFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public KeyToPathFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public KeyToPathFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public KeyToPathFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public KeyToPathFluent removeFromAdditionalProperties(Map map) {
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

   public KeyToPathFluent withAdditionalProperties(Map additionalProperties) {
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
            KeyToPathFluent that = (KeyToPathFluent)o;
            if (!Objects.equals(this.key, that.key)) {
               return false;
            } else if (!Objects.equals(this.mode, that.mode)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
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
      return Objects.hash(new Object[]{this.key, this.mode, this.path, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.key != null) {
         sb.append("key:");
         sb.append(this.key + ",");
      }

      if (this.mode != null) {
         sb.append("mode:");
         sb.append(this.mode + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
