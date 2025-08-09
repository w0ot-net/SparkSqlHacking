package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NFSVolumeSourceFluent extends BaseFluent {
   private String path;
   private Boolean readOnly;
   private String server;
   private Map additionalProperties;

   public NFSVolumeSourceFluent() {
   }

   public NFSVolumeSourceFluent(NFSVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NFSVolumeSource instance) {
      instance = instance != null ? instance : new NFSVolumeSource();
      if (instance != null) {
         this.withPath(instance.getPath());
         this.withReadOnly(instance.getReadOnly());
         this.withServer(instance.getServer());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getPath() {
      return this.path;
   }

   public NFSVolumeSourceFluent withPath(String path) {
      this.path = path;
      return this;
   }

   public boolean hasPath() {
      return this.path != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public NFSVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getServer() {
      return this.server;
   }

   public NFSVolumeSourceFluent withServer(String server) {
      this.server = server;
      return this;
   }

   public boolean hasServer() {
      return this.server != null;
   }

   public NFSVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NFSVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NFSVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NFSVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public NFSVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            NFSVolumeSourceFluent that = (NFSVolumeSourceFluent)o;
            if (!Objects.equals(this.path, that.path)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.server, that.server)) {
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
      return Objects.hash(new Object[]{this.path, this.readOnly, this.server, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.path != null) {
         sb.append("path:");
         sb.append(this.path + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.server != null) {
         sb.append("server:");
         sb.append(this.server + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public NFSVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
