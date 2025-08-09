package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AzureDiskVolumeSourceFluent extends BaseFluent {
   private String cachingMode;
   private String diskName;
   private String diskURI;
   private String fsType;
   private String kind;
   private Boolean readOnly;
   private Map additionalProperties;

   public AzureDiskVolumeSourceFluent() {
   }

   public AzureDiskVolumeSourceFluent(AzureDiskVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AzureDiskVolumeSource instance) {
      instance = instance != null ? instance : new AzureDiskVolumeSource();
      if (instance != null) {
         this.withCachingMode(instance.getCachingMode());
         this.withDiskName(instance.getDiskName());
         this.withDiskURI(instance.getDiskURI());
         this.withFsType(instance.getFsType());
         this.withKind(instance.getKind());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCachingMode() {
      return this.cachingMode;
   }

   public AzureDiskVolumeSourceFluent withCachingMode(String cachingMode) {
      this.cachingMode = cachingMode;
      return this;
   }

   public boolean hasCachingMode() {
      return this.cachingMode != null;
   }

   public String getDiskName() {
      return this.diskName;
   }

   public AzureDiskVolumeSourceFluent withDiskName(String diskName) {
      this.diskName = diskName;
      return this;
   }

   public boolean hasDiskName() {
      return this.diskName != null;
   }

   public String getDiskURI() {
      return this.diskURI;
   }

   public AzureDiskVolumeSourceFluent withDiskURI(String diskURI) {
      this.diskURI = diskURI;
      return this;
   }

   public boolean hasDiskURI() {
      return this.diskURI != null;
   }

   public String getFsType() {
      return this.fsType;
   }

   public AzureDiskVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public String getKind() {
      return this.kind;
   }

   public AzureDiskVolumeSourceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public AzureDiskVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public AzureDiskVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AzureDiskVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AzureDiskVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AzureDiskVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public AzureDiskVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            AzureDiskVolumeSourceFluent that = (AzureDiskVolumeSourceFluent)o;
            if (!Objects.equals(this.cachingMode, that.cachingMode)) {
               return false;
            } else if (!Objects.equals(this.diskName, that.diskName)) {
               return false;
            } else if (!Objects.equals(this.diskURI, that.diskURI)) {
               return false;
            } else if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
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
      return Objects.hash(new Object[]{this.cachingMode, this.diskName, this.diskURI, this.fsType, this.kind, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.cachingMode != null) {
         sb.append("cachingMode:");
         sb.append(this.cachingMode + ",");
      }

      if (this.diskName != null) {
         sb.append("diskName:");
         sb.append(this.diskName + ",");
      }

      if (this.diskURI != null) {
         sb.append("diskURI:");
         sb.append(this.diskURI + ",");
      }

      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
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

   public AzureDiskVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
