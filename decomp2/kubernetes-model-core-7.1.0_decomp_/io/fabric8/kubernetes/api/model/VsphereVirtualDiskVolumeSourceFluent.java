package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class VsphereVirtualDiskVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private String storagePolicyID;
   private String storagePolicyName;
   private String volumePath;
   private Map additionalProperties;

   public VsphereVirtualDiskVolumeSourceFluent() {
   }

   public VsphereVirtualDiskVolumeSourceFluent(VsphereVirtualDiskVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(VsphereVirtualDiskVolumeSource instance) {
      instance = instance != null ? instance : new VsphereVirtualDiskVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withStoragePolicyID(instance.getStoragePolicyID());
         this.withStoragePolicyName(instance.getStoragePolicyName());
         this.withVolumePath(instance.getVolumePath());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public VsphereVirtualDiskVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public String getStoragePolicyID() {
      return this.storagePolicyID;
   }

   public VsphereVirtualDiskVolumeSourceFluent withStoragePolicyID(String storagePolicyID) {
      this.storagePolicyID = storagePolicyID;
      return this;
   }

   public boolean hasStoragePolicyID() {
      return this.storagePolicyID != null;
   }

   public String getStoragePolicyName() {
      return this.storagePolicyName;
   }

   public VsphereVirtualDiskVolumeSourceFluent withStoragePolicyName(String storagePolicyName) {
      this.storagePolicyName = storagePolicyName;
      return this;
   }

   public boolean hasStoragePolicyName() {
      return this.storagePolicyName != null;
   }

   public String getVolumePath() {
      return this.volumePath;
   }

   public VsphereVirtualDiskVolumeSourceFluent withVolumePath(String volumePath) {
      this.volumePath = volumePath;
      return this;
   }

   public boolean hasVolumePath() {
      return this.volumePath != null;
   }

   public VsphereVirtualDiskVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public VsphereVirtualDiskVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public VsphereVirtualDiskVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public VsphereVirtualDiskVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public VsphereVirtualDiskVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            VsphereVirtualDiskVolumeSourceFluent that = (VsphereVirtualDiskVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.storagePolicyID, that.storagePolicyID)) {
               return false;
            } else if (!Objects.equals(this.storagePolicyName, that.storagePolicyName)) {
               return false;
            } else if (!Objects.equals(this.volumePath, that.volumePath)) {
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
      return Objects.hash(new Object[]{this.fsType, this.storagePolicyID, this.storagePolicyName, this.volumePath, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.storagePolicyID != null) {
         sb.append("storagePolicyID:");
         sb.append(this.storagePolicyID + ",");
      }

      if (this.storagePolicyName != null) {
         sb.append("storagePolicyName:");
         sb.append(this.storagePolicyName + ",");
      }

      if (this.volumePath != null) {
         sb.append("volumePath:");
         sb.append(this.volumePath + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
