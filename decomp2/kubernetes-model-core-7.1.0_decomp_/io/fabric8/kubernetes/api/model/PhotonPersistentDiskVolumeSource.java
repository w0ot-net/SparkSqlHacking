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
@JsonPropertyOrder({"fsType", "pdID"})
public class PhotonPersistentDiskVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("pdID")
   private String pdID;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PhotonPersistentDiskVolumeSource() {
   }

   public PhotonPersistentDiskVolumeSource(String fsType, String pdID) {
      this.fsType = fsType;
      this.pdID = pdID;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("pdID")
   public String getPdID() {
      return this.pdID;
   }

   @JsonProperty("pdID")
   public void setPdID(String pdID) {
      this.pdID = pdID;
   }

   @JsonIgnore
   public PhotonPersistentDiskVolumeSourceBuilder edit() {
      return new PhotonPersistentDiskVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public PhotonPersistentDiskVolumeSourceBuilder toBuilder() {
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
      return "PhotonPersistentDiskVolumeSource(fsType=" + var10000 + ", pdID=" + this.getPdID() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PhotonPersistentDiskVolumeSource)) {
         return false;
      } else {
         PhotonPersistentDiskVolumeSource other = (PhotonPersistentDiskVolumeSource)o;
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

            Object this$pdID = this.getPdID();
            Object other$pdID = other.getPdID();
            if (this$pdID == null) {
               if (other$pdID != null) {
                  return false;
               }
            } else if (!this$pdID.equals(other$pdID)) {
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
      return other instanceof PhotonPersistentDiskVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $pdID = this.getPdID();
      result = result * 59 + ($pdID == null ? 43 : $pdID.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
