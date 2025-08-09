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
@JsonPropertyOrder({"fsType", "partition", "pdName", "readOnly"})
public class GCEPersistentDiskVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("partition")
   private Integer partition;
   @JsonProperty("pdName")
   private String pdName;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GCEPersistentDiskVolumeSource() {
   }

   public GCEPersistentDiskVolumeSource(String fsType, Integer partition, String pdName, Boolean readOnly) {
      this.fsType = fsType;
      this.partition = partition;
      this.pdName = pdName;
      this.readOnly = readOnly;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("partition")
   public Integer getPartition() {
      return this.partition;
   }

   @JsonProperty("partition")
   public void setPartition(Integer partition) {
      this.partition = partition;
   }

   @JsonProperty("pdName")
   public String getPdName() {
      return this.pdName;
   }

   @JsonProperty("pdName")
   public void setPdName(String pdName) {
      this.pdName = pdName;
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
   public GCEPersistentDiskVolumeSourceBuilder edit() {
      return new GCEPersistentDiskVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public GCEPersistentDiskVolumeSourceBuilder toBuilder() {
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
      return "GCEPersistentDiskVolumeSource(fsType=" + var10000 + ", partition=" + this.getPartition() + ", pdName=" + this.getPdName() + ", readOnly=" + this.getReadOnly() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GCEPersistentDiskVolumeSource)) {
         return false;
      } else {
         GCEPersistentDiskVolumeSource other = (GCEPersistentDiskVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$partition = this.getPartition();
            Object other$partition = other.getPartition();
            if (this$partition == null) {
               if (other$partition != null) {
                  return false;
               }
            } else if (!this$partition.equals(other$partition)) {
               return false;
            }

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

            Object this$pdName = this.getPdName();
            Object other$pdName = other.getPdName();
            if (this$pdName == null) {
               if (other$pdName != null) {
                  return false;
               }
            } else if (!this$pdName.equals(other$pdName)) {
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
      return other instanceof GCEPersistentDiskVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $partition = this.getPartition();
      result = result * 59 + ($partition == null ? 43 : $partition.hashCode());
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $pdName = this.getPdName();
      result = result * 59 + ($pdName == null ? 43 : $pdName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
