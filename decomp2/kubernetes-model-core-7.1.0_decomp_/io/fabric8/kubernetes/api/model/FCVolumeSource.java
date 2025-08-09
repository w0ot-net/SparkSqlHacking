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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"fsType", "lun", "readOnly", "targetWWNs", "wwids"})
public class FCVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("fsType")
   private String fsType;
   @JsonProperty("lun")
   private Integer lun;
   @JsonProperty("readOnly")
   private Boolean readOnly;
   @JsonProperty("targetWWNs")
   @JsonInclude(Include.NON_EMPTY)
   private List targetWWNs = new ArrayList();
   @JsonProperty("wwids")
   @JsonInclude(Include.NON_EMPTY)
   private List wwids = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FCVolumeSource() {
   }

   public FCVolumeSource(String fsType, Integer lun, Boolean readOnly, List targetWWNs, List wwids) {
      this.fsType = fsType;
      this.lun = lun;
      this.readOnly = readOnly;
      this.targetWWNs = targetWWNs;
      this.wwids = wwids;
   }

   @JsonProperty("fsType")
   public String getFsType() {
      return this.fsType;
   }

   @JsonProperty("fsType")
   public void setFsType(String fsType) {
      this.fsType = fsType;
   }

   @JsonProperty("lun")
   public Integer getLun() {
      return this.lun;
   }

   @JsonProperty("lun")
   public void setLun(Integer lun) {
      this.lun = lun;
   }

   @JsonProperty("readOnly")
   public Boolean getReadOnly() {
      return this.readOnly;
   }

   @JsonProperty("readOnly")
   public void setReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
   }

   @JsonProperty("targetWWNs")
   @JsonInclude(Include.NON_EMPTY)
   public List getTargetWWNs() {
      return this.targetWWNs;
   }

   @JsonProperty("targetWWNs")
   public void setTargetWWNs(List targetWWNs) {
      this.targetWWNs = targetWWNs;
   }

   @JsonProperty("wwids")
   @JsonInclude(Include.NON_EMPTY)
   public List getWwids() {
      return this.wwids;
   }

   @JsonProperty("wwids")
   public void setWwids(List wwids) {
      this.wwids = wwids;
   }

   @JsonIgnore
   public FCVolumeSourceBuilder edit() {
      return new FCVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public FCVolumeSourceBuilder toBuilder() {
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
      return "FCVolumeSource(fsType=" + var10000 + ", lun=" + this.getLun() + ", readOnly=" + this.getReadOnly() + ", targetWWNs=" + this.getTargetWWNs() + ", wwids=" + this.getWwids() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FCVolumeSource)) {
         return false;
      } else {
         FCVolumeSource other = (FCVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$lun = this.getLun();
            Object other$lun = other.getLun();
            if (this$lun == null) {
               if (other$lun != null) {
                  return false;
               }
            } else if (!this$lun.equals(other$lun)) {
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

            Object this$targetWWNs = this.getTargetWWNs();
            Object other$targetWWNs = other.getTargetWWNs();
            if (this$targetWWNs == null) {
               if (other$targetWWNs != null) {
                  return false;
               }
            } else if (!this$targetWWNs.equals(other$targetWWNs)) {
               return false;
            }

            Object this$wwids = this.getWwids();
            Object other$wwids = other.getWwids();
            if (this$wwids == null) {
               if (other$wwids != null) {
                  return false;
               }
            } else if (!this$wwids.equals(other$wwids)) {
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
      return other instanceof FCVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $lun = this.getLun();
      result = result * 59 + ($lun == null ? 43 : $lun.hashCode());
      Object $readOnly = this.getReadOnly();
      result = result * 59 + ($readOnly == null ? 43 : $readOnly.hashCode());
      Object $fsType = this.getFsType();
      result = result * 59 + ($fsType == null ? 43 : $fsType.hashCode());
      Object $targetWWNs = this.getTargetWWNs();
      result = result * 59 + ($targetWWNs == null ? 43 : $targetWWNs.hashCode());
      Object $wwids = this.getWwids();
      result = result * 59 + ($wwids == null ? 43 : $wwids.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
