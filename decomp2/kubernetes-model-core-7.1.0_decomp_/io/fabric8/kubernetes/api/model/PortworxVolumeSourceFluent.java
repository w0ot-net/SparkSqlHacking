package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PortworxVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private Boolean readOnly;
   private String volumeID;
   private Map additionalProperties;

   public PortworxVolumeSourceFluent() {
   }

   public PortworxVolumeSourceFluent(PortworxVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PortworxVolumeSource instance) {
      instance = instance != null ? instance : new PortworxVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withReadOnly(instance.getReadOnly());
         this.withVolumeID(instance.getVolumeID());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public PortworxVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public PortworxVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public String getVolumeID() {
      return this.volumeID;
   }

   public PortworxVolumeSourceFluent withVolumeID(String volumeID) {
      this.volumeID = volumeID;
      return this;
   }

   public boolean hasVolumeID() {
      return this.volumeID != null;
   }

   public PortworxVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PortworxVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PortworxVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PortworxVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public PortworxVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            PortworxVolumeSourceFluent that = (PortworxVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.volumeID, that.volumeID)) {
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
      return Objects.hash(new Object[]{this.fsType, this.readOnly, this.volumeID, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.volumeID != null) {
         sb.append("volumeID:");
         sb.append(this.volumeID + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PortworxVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
