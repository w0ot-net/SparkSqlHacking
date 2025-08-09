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
@JsonPropertyOrder({"fsType", "readOnly", "secretRef", "volumeName", "volumeNamespace"})
public class StorageOSPersistentVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("secretRef")
   private ObjectReference secretRef;
   @JsonProperty("volumeName")
   private String volumeName;
   @JsonProperty("volumeNamespace")
   private String volumeNamespace;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StorageOSPersistentVolumeSource() {
   }

   public StorageOSPersistentVolumeSource(String fsType, Boolean readOnly, ObjectReference secretRef, String volumeName, String volumeNamespace) {
      this.fsType = fsType;
      this.readOnly = readOnly;
      this.secretRef = secretRef;
      this.volumeName = volumeName;
      this.volumeNamespace = volumeNamespace;
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
   public ObjectReference getSecretRef() {
      return this.secretRef;
   }

   @JsonProperty("secretRef")
   public void setSecretRef(ObjectReference secretRef) {
      this.secretRef = secretRef;
   }

   @JsonProperty("volumeName")
   public String getVolumeName() {
      return this.volumeName;
   }

   @JsonProperty("volumeName")
   public void setVolumeName(String volumeName) {
      this.volumeName = volumeName;
   }

   @JsonProperty("volumeNamespace")
   public String getVolumeNamespace() {
      return this.volumeNamespace;
   }

   @JsonProperty("volumeNamespace")
   public void setVolumeNamespace(String volumeNamespace) {
      this.volumeNamespace = volumeNamespace;
   }

   @JsonIgnore
   public StorageOSPersistentVolumeSourceBuilder edit() {
      return new StorageOSPersistentVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public StorageOSPersistentVolumeSourceBuilder toBuilder() {
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
      return "StorageOSPersistentVolumeSource(fsType=" + var10000 + ", readOnly=" + this.getReadOnly() + ", secretRef=" + this.getSecretRef() + ", volumeName=" + this.getVolumeName() + ", volumeNamespace=" + this.getVolumeNamespace() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StorageOSPersistentVolumeSource)) {
         return false;
      } else {
         StorageOSPersistentVolumeSource other = (StorageOSPersistentVolumeSource)o;
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

            Object this$volumeName = this.getVolumeName();
            Object other$volumeName = other.getVolumeName();
            if (this$volumeName == null) {
               if (other$volumeName != null) {
                  return false;
               }
            } else if (!this$volumeName.equals(other$volumeName)) {
               return false;
            }

            Object this$volumeNamespace = this.getVolumeNamespace();
            Object other$volumeNamespace = other.getVolumeNamespace();
            if (this$volumeNamespace == null) {
               if (other$volumeNamespace != null) {
                  return false;
               }
            } else if (!this$volumeNamespace.equals(other$volumeNamespace)) {
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
      return other instanceof StorageOSPersistentVolumeSource;
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
      Object $volumeName = this.getVolumeName();
      result = result * 59 + ($volumeName == null ? 43 : $volumeName.hashCode());
      Object $volumeNamespace = this.getVolumeNamespace();
      result = result * 59 + ($volumeNamespace == null ? 43 : $volumeNamespace.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
