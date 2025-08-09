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
@JsonPropertyOrder({"kind", "cachingMode", "diskName", "diskURI", "fsType", "readOnly"})
public class AzureDiskVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("cachingMode")
   private String cachingMode;
   @JsonProperty("diskName")
   private String diskName;
   @JsonProperty("diskURI")
   private String diskURI;
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AzureDiskVolumeSource() {
   }

   public AzureDiskVolumeSource(String cachingMode, String diskName, String diskURI, String fsType, String kind, Boolean readOnly) {
      this.cachingMode = cachingMode;
      this.diskName = diskName;
      this.diskURI = diskURI;
      this.fsType = fsType;
      this.kind = kind;
      this.readOnly = readOnly;
   }

   @JsonProperty("cachingMode")
   public String getCachingMode() {
      return this.cachingMode;
   }

   @JsonProperty("cachingMode")
   public void setCachingMode(String cachingMode) {
      this.cachingMode = cachingMode;
   }

   @JsonProperty("diskName")
   public String getDiskName() {
      return this.diskName;
   }

   @JsonProperty("diskName")
   public void setDiskName(String diskName) {
      this.diskName = diskName;
   }

   @JsonProperty("diskURI")
   public String getDiskURI() {
      return this.diskURI;
   }

   @JsonProperty("diskURI")
   public void setDiskURI(String diskURI) {
      this.diskURI = diskURI;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonIgnore
   public AzureDiskVolumeSourceBuilder edit() {
      return new AzureDiskVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public AzureDiskVolumeSourceBuilder toBuilder() {
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
      String var10000 = this.getCachingMode();
      return "AzureDiskVolumeSource(cachingMode=" + var10000 + ", diskName=" + this.getDiskName() + ", diskURI=" + this.getDiskURI() + ", fsType=" + this.getFsType() + ", kind=" + this.getKind() + ", readOnly=" + this.getReadOnly() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AzureDiskVolumeSource)) {
         return false;
      } else {
         AzureDiskVolumeSource other = (AzureDiskVolumeSource)o;
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

            Object this$cachingMode = this.getCachingMode();
            Object other$cachingMode = other.getCachingMode();
            if (this$cachingMode == null) {
               if (other$cachingMode != null) {
                  return false;
               }
            } else if (!this$cachingMode.equals(other$cachingMode)) {
               return false;
            }

            Object this$diskName = this.getDiskName();
            Object other$diskName = other.getDiskName();
            if (this$diskName == null) {
               if (other$diskName != null) {
                  return false;
               }
            } else if (!this$diskName.equals(other$diskName)) {
               return false;
            }

            Object this$diskURI = this.getDiskURI();
            Object other$diskURI = other.getDiskURI();
            if (this$diskURI == null) {
               if (other$diskURI != null) {
                  return false;
               }
            } else if (!this$diskURI.equals(other$diskURI)) {
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

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
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
      return other instanceof AzureDiskVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $cachingMode = this.getCachingMode();
      result = result * 59 + ($cachingMode == null ? 43 : $cachingMode.hashCode());
      Object $diskName = this.getDiskName();
      result = result * 59 + ($diskName == null ? 43 : $diskName.hashCode());
      Object $diskURI = this.getDiskURI();
      result = result * 59 + ($diskURI == null ? 43 : $diskURI.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
