package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"fsType", "storagePolicyID", "storagePolicyName", "volumePath"})
public class VsphereVirtualDiskVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("storagePolicyID")
   private String storagePolicyID;
   @JsonProperty("storagePolicyName")
   private String storagePolicyName;
   @JsonProperty("volumePath")
   private String volumePath;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public VsphereVirtualDiskVolumeSource() {
   }

   public VsphereVirtualDiskVolumeSource(String fsType, String storagePolicyID, String storagePolicyName, String volumePath) {
      this.fsType = fsType;
      this.storagePolicyID = storagePolicyID;
      this.storagePolicyName = storagePolicyName;
      this.volumePath = volumePath;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("storagePolicyID")
   public String getStoragePolicyID() {
      return this.storagePolicyID;
   }

   @JsonProperty("storagePolicyID")
   public void setStoragePolicyID(String storagePolicyID) {
      this.storagePolicyID = storagePolicyID;
   }

   @JsonProperty("storagePolicyName")
   public String getStoragePolicyName() {
      return this.storagePolicyName;
   }

   @JsonProperty("storagePolicyName")
   public void setStoragePolicyName(String storagePolicyName) {
      this.storagePolicyName = storagePolicyName;
   }

   @JsonProperty("volumePath")
   public String getVolumePath() {
      return this.volumePath;
   }

   @JsonProperty("volumePath")
   public void setVolumePath(String volumePath) {
      this.volumePath = volumePath;
   }

   @JsonIgnore
   public VsphereVirtualDiskVolumeSourceBuilder edit() {
      return new VsphereVirtualDiskVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public VsphereVirtualDiskVolumeSourceBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      String var10000 = this.getFsType();
      return "VsphereVirtualDiskVolumeSource(fsType=" + var10000 + ", storagePolicyID=" + this.getStoragePolicyID() + ", storagePolicyName=" + this.getStoragePolicyName() + ", volumePath=" + this.getVolumePath() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VsphereVirtualDiskVolumeSource)) {
         return false;
      } else {
         VsphereVirtualDiskVolumeSource other = (VsphereVirtualDiskVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$fsType = this.getFsType();
            Object other$fsType = other.getFsType();
            if (this$fsType == null) {
               if (other$fsType != null) {
                  return false;
               }
            } else if (!this$fsType.equals(other$fsType)) {
               return false;
            }

            Object this$storagePolicyID = this.getStoragePolicyID();
            Object other$storagePolicyID = other.getStoragePolicyID();
            if (this$storagePolicyID == null) {
               if (other$storagePolicyID != null) {
                  return false;
               }
            } else if (!this$storagePolicyID.equals(other$storagePolicyID)) {
               return false;
            }

            Object this$storagePolicyName = this.getStoragePolicyName();
            Object other$storagePolicyName = other.getStoragePolicyName();
            if (this$storagePolicyName == null) {
               if (other$storagePolicyName != null) {
                  return false;
               }
            } else if (!this$storagePolicyName.equals(other$storagePolicyName)) {
               return false;
            }

            Object this$volumePath = this.getVolumePath();
            Object other$volumePath = other.getVolumePath();
            if (this$volumePath == null) {
               if (other$volumePath != null) {
                  return false;
               }
            } else if (!this$volumePath.equals(other$volumePath)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof VsphereVirtualDiskVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $storagePolicyID = this.getStoragePolicyID();
      result = result * 59 + ($storagePolicyID == null ? 43 : $storagePolicyID.hashCode());
      Object $storagePolicyName = this.getStoragePolicyName();
      result = result * 59 + ($storagePolicyName == null ? 43 : $storagePolicyName.hashCode());
      Object $volumePath = this.getVolumePath();
      result = result * 59 + ($volumePath == null ? 43 : $volumePath.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
