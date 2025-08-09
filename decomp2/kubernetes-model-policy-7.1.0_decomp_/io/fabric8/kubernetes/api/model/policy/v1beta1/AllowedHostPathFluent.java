package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AllowedHostPathFluent extends BaseFluent {
   private String pathPrefix;
   private Boolean readOnly;
   private Map additionalProperties;

   public AllowedHostPathFluent() {
   }

   public AllowedHostPathFluent(AllowedHostPath instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AllowedHostPath instance) {
      instance = instance != null ? instance : new AllowedHostPath();
      if (instance != null) {
         this.withPathPrefix(instance.getPathPrefix());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getPathPrefix() {
      return this.pathPrefix;
   }

   public AllowedHostPathFluent withPathPrefix(String pathPrefix) {
      this.pathPrefix = pathPrefix;
      return this;
   }

   public boolean hasPathPrefix() {
      return this.pathPrefix != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public AllowedHostPathFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public AllowedHostPathFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AllowedHostPathFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AllowedHostPathFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AllowedHostPathFluent removeFromAdditionalProperties(Map map) {
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

   public AllowedHostPathFluent withAdditionalProperties(Map additionalProperties) {
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
            AllowedHostPathFluent that = (AllowedHostPathFluent)o;
            if (!Objects.equals(this.pathPrefix, that.pathPrefix)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
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
      return Objects.hash(new Object[]{this.pathPrefix, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.pathPrefix != null) {
         sb.append("pathPrefix:");
         sb.append(this.pathPrefix + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public AllowedHostPathFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
