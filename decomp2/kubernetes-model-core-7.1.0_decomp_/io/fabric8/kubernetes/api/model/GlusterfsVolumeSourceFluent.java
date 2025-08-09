package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GlusterfsVolumeSourceFluent extends BaseFluent {
   private String endpoints;
   private String path;
   private Boolean readOnly;
   private Map additionalProperties;

   public GlusterfsVolumeSourceFluent() {
   }

   public GlusterfsVolumeSourceFluent(GlusterfsVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GlusterfsVolumeSource instance) {
      instance = instance != null ? instance : new GlusterfsVolumeSource();
      if (instance != null) {
         this.withEndpoints(instance.getEndpoints());
         this.withPath(instance.getPath());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getEndpoints() {
      return this.endpoints;
   }

   public GlusterfsVolumeSourceFluent withEndpoints(String endpoints) {
      this.endpoints = endpoints;
      return this;
   }

   public boolean hasEndpoints() {
      return this.endpoints != null;
   }

   public String getPath() {
      return this.path;
   }

   public GlusterfsVolumeSourceFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public GlusterfsVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public GlusterfsVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GlusterfsVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GlusterfsVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GlusterfsVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public GlusterfsVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            GlusterfsVolumeSourceFluent that = (GlusterfsVolumeSourceFluent)o;
            if (!Objects.equals(this.endpoints, that.endpoints)) {
               return false;
            } else if (!Objects.equals(this.path, that.path)) {
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
      return Objects.hash(new Object[]{this.endpoints, this.path, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.endpoints != null) {
         sb.append("endpoints:");
         sb.append(this.endpoints + ",");
      }

      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
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

   public GlusterfsVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
