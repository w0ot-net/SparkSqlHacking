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
@JsonPropertyOrder({"status", "targetVolumeAttributesClassName"})
public class ModifyVolumeStatus implements Editable, KubernetesResource {
   @JsonProperty("status")
   private String status;
   @JsonProperty("targetVolumeAttributesClassName")
   private String targetVolumeAttributesClassName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ModifyVolumeStatus() {
   }

   public ModifyVolumeStatus(String status, String targetVolumeAttributesClassName) {
      this.status = status;
      this.targetVolumeAttributesClassName = targetVolumeAttributesClassName;
   }

   @JsonProperty("status")
   public String getStatus() {
      return this.status;
   }

   @JsonProperty("status")
   public void setStatus(String status) {
      this.status = status;
   }

   @JsonProperty("targetVolumeAttributesClassName")
   public String getTargetVolumeAttributesClassName() {
      return this.targetVolumeAttributesClassName;
   }

   @JsonProperty("targetVolumeAttributesClassName")
   public void setTargetVolumeAttributesClassName(String targetVolumeAttributesClassName) {
      this.targetVolumeAttributesClassName = targetVolumeAttributesClassName;
   }

   @JsonIgnore
   public ModifyVolumeStatusBuilder edit() {
      return new ModifyVolumeStatusBuilder(this);
   }

   @JsonIgnore
   public ModifyVolumeStatusBuilder toBuilder() {
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
      String var10000 = this.getStatus();
      return "ModifyVolumeStatus(status=" + var10000 + ", targetVolumeAttributesClassName=" + this.getTargetVolumeAttributesClassName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ModifyVolumeStatus)) {
         return false;
      } else {
         ModifyVolumeStatus other = (ModifyVolumeStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$status = this.getStatus();
            Object other$status = other.getStatus();
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
               return false;
            }

            Object this$targetVolumeAttributesClassName = this.getTargetVolumeAttributesClassName();
            Object other$targetVolumeAttributesClassName = other.getTargetVolumeAttributesClassName();
            if (this$targetVolumeAttributesClassName == null) {
               if (other$targetVolumeAttributesClassName != null) {
                  return false;
               }
            } else if (!this$targetVolumeAttributesClassName.equals(other$targetVolumeAttributesClassName)) {
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
      return other instanceof ModifyVolumeStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $status = this.getStatus();
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $targetVolumeAttributesClassName = this.getTargetVolumeAttributesClassName();
      result = result * 59 + ($targetVolumeAttributesClassName == null ? 43 : $targetVolumeAttributesClassName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
