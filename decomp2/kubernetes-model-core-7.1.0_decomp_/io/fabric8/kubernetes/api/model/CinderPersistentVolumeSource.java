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
@JsonPropertyOrder({"fsType", "readOnly", "secretRef", "volumeID"})
public class CinderPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private SecretReference secretRef;
   @JsonProperty("volumeID")
   private String volumeID;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CinderPersistentVolumeSource() {
   }

   public CinderPersistentVolumeSource(String fsType, Boolean readOnly, SecretReference secretRef, String volumeID) {
      this.fsType = fsType;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
      this.volumeID = volumeID;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("secretRef")
   public SecretReference getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(SecretReference secretRef) {
      this.secretRef = secretRef;
   }

   @JsonProperty("volumeID")
   public String getVolumeID() {
      return this.volumeID;
   }

   @JsonProperty("volumeID")
   public void setVolumeID(String volumeID) {
      this.volumeID = volumeID;
   }

   @JsonIgnore
   public CinderPersistentVolumeSourceBuilder edit() {
      return new CinderPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public CinderPersistentVolumeSourceBuilder toBuilder() {
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
      return "CinderPersistentVolumeSource(fsType=" + var10000 + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", volumeID=" + this.getVolumeID() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CinderPersistentVolumeSource)) {
         return false;
      } else {
         CinderPersistentVolumeSource other = (CinderPersistentVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$readOnly = this.getReadOnly();
            Object other$readOnly = other.getReadOnly();
            if (this$readOnly == null) {
               if (other$readOnly != null) {
                  return false;
               }
            } else if (!this$readOnly.equals(other$readOnly)) {
               return false;
            }

            Object this$fsType = this.getFsType();
            Object other$fsType = other.getFsType();
            if (this$fsType == null) {
               if (other$fsType != null) {
                  return false;
               }
            } else if (!this$fsType.equals(other$fsType)) {
               return false;
            }

            Object this$secretRef = this.getSecretRef();
            Object other$secretRef = other.getSecretRef();
            if (this$secretRef == null) {
               if (other$secretRef != null) {
                  return false;
               }
            } else if (!this$secretRef.equals(other$secretRef)) {
               return false;
            }

            Object this$volumeID = this.getVolumeID();
            Object other$volumeID = other.getVolumeID();
            if (this$volumeID == null) {
               if (other$volumeID != null) {
                  return false;
               }
            } else if (!this$volumeID.equals(other$volumeID)) {
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
      return other instanceof CinderPersistentVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $secretRef = this.getSecretRef();
      result = result * 59 + ($secretRef == null ? 43 : $secretRef.hashCode());
      Object $volumeID = this.getVolumeID();
      result = result * 59 + ($volumeID == null ? 43 : $volumeID.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
