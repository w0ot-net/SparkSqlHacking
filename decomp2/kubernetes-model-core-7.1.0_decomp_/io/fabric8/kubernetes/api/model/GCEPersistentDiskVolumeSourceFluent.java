package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GCEPersistentDiskVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private Integer partition;
   private String pdName;
   private Boolean readOnly;
   private Map additionalProperties;

   public GCEPersistentDiskVolumeSourceFluent() {
   }

   public GCEPersistentDiskVolumeSourceFluent(GCEPersistentDiskVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GCEPersistentDiskVolumeSource instance) {
      instance = instance != null ? instance : new GCEPersistentDiskVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withPartition(instance.getPartition());
         this.withPdName(instance.getPdName());
         this.withReadOnly(instance.getReadOnly());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public GCEPersistentDiskVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public Integer getPartition() {
      return this.partition;
   }

   public GCEPersistentDiskVolumeSourceFluent withPartition(Integer partition) {
      this.partition = partition;
      return this;
   }

   public boolean hasPartition() {
      return this.partition != null;
   }

   public String getPdName() {
      return this.pdName;
   }

   public GCEPersistentDiskVolumeSourceFluent withPdName(String pdName) {
      this.pdName = pdName;
      return this;
   }

   public boolean hasPdName() {
      return this.pdName != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public GCEPersistentDiskVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public GCEPersistentDiskVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GCEPersistentDiskVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GCEPersistentDiskVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GCEPersistentDiskVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public GCEPersistentDiskVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            GCEPersistentDiskVolumeSourceFluent that = (GCEPersistentDiskVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.partition, that.partition)) {
               return false;
            } else if (!Objects.equals(this.pdName, that.pdName)) {
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
      return Objects.hash(new Object[]{this.fsType, this.partition, this.pdName, this.readOnly, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.partition != null) {
         sb.append("partition:");
         sb.append(this.partition + ",");
      }

      if (this.pdName != null) {
         sb.append("pdName:");
         sb.append(this.pdName + ",");
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

   public GCEPersistentDiskVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
