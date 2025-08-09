package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GlusterfsPersistentVolumeSourceFluent extends BaseFluent {
   private String endpoints;
   private String endpointsNamespace;
   private String path;
   private Boolean readOnly;
   private Map additionalProperties;

   public GlusterfsPersistentVolumeSourceFluent() {
   }

   public GlusterfsPersistentVolumeSourceFluent(GlusterfsPersistentVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GlusterfsPersistentVolumeSource instance) {
      instance = instance != null ? instance : new GlusterfsPersistentVolumeSource();
      if (instance != null) {
         this.withEndpoints(instance.getEndpoints());
         this.withEndpointsNamespace(instance.getEndpointsNamespace());
         this.withPath(instance.getPath());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getEndpoints() {
      return this.endpoints;
   }

   public GlusterfsPersistentVolumeSourceFluent withEndpoints(String endpoints) {
      this.endpoints = endpoints;
      return this;
   }

   public boolean hasEndpoints() {
      return this.endpoints != null;
   }

   public String getEndpointsNamespace() {
      return this.endpointsNamespace;
   }

   public GlusterfsPersistentVolumeSourceFluent withEndpointsNamespace(String endpointsNamespace) {
      this.endpointsNamespace = endpointsNamespace;
      return this;
   }

   public boolean hasEndpointsNamespace() {
      return this.endpointsNamespace != null;
   }

   public String getPath() {
      return this.path;
   }

   public GlusterfsPersistentVolumeSourceFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public GlusterfsPersistentVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public GlusterfsPersistentVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GlusterfsPersistentVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GlusterfsPersistentVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GlusterfsPersistentVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public GlusterfsPersistentVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            GlusterfsPersistentVolumeSourceFluent that = (GlusterfsPersistentVolumeSourceFluent)o;
            if (!Objects.equals(this.endpoints, that.endpoints)) {
               return false;
            } else if (!Objects.equals(this.endpointsNamespace, that.endpointsNamespace)) {
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
      return Objects.hash(new Object[]{this.endpoints, this.endpointsNamespace, this.path, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.endpoints != null) {
         sb.append("endpoints:");
         sb.append(this.endpoints + ",");
      }

      if (this.endpointsNamespace != null) {
         sb.append("endpointsNamespace:");
         sb.append(this.endpointsNamespace + ",");
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

   public GlusterfsPersistentVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
