package io.fabric8.kubernetes.api.model.apiextensions.v1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"labelSelectorPath", "specReplicasPath", "statusReplicasPath"})
public class CustomResourceSubresourceScale implements Editable, KubernetesResource {
   @JsonProperty("labelSelectorPath")
   private String labelSelectorPath;
   @JsonProperty("specReplicasPath")
   private String specReplicasPath;
   @JsonProperty("statusReplicasPath")
   private String statusReplicasPath;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceSubresourceScale() {
   }

   public CustomResourceSubresourceScale(String labelSelectorPath, String specReplicasPath, String statusReplicasPath) {
      this.labelSelectorPath = labelSelectorPath;
      this.specReplicasPath = specReplicasPath;
      this.statusReplicasPath = statusReplicasPath;
   }

   @JsonProperty("labelSelectorPath")
   public String getLabelSelectorPath() {
      return this.labelSelectorPath;
   }

   @JsonProperty("labelSelectorPath")
   public void setLabelSelectorPath(String labelSelectorPath) {
      this.labelSelectorPath = labelSelectorPath;
   }

   @JsonProperty("specReplicasPath")
   public String getSpecReplicasPath() {
      return this.specReplicasPath;
   }

   @JsonProperty("specReplicasPath")
   public void setSpecReplicasPath(String specReplicasPath) {
      this.specReplicasPath = specReplicasPath;
   }

   @JsonProperty("statusReplicasPath")
   public String getStatusReplicasPath() {
      return this.statusReplicasPath;
   }

   @JsonProperty("statusReplicasPath")
   public void setStatusReplicasPath(String statusReplicasPath) {
      this.statusReplicasPath = statusReplicasPath;
   }

   @JsonIgnore
   public CustomResourceSubresourceScaleBuilder edit() {
      return new CustomResourceSubresourceScaleBuilder(this);
   }

   @JsonIgnore
   public CustomResourceSubresourceScaleBuilder toBuilder() {
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
      String var10000 = this.getLabelSelectorPath();
      return "CustomResourceSubresourceScale(labelSelectorPath=" + var10000 + ", specReplicasPath=" + this.getSpecReplicasPath() + ", statusReplicasPath=" + this.getStatusReplicasPath() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceSubresourceScale)) {
         return false;
      } else {
         CustomResourceSubresourceScale other = (CustomResourceSubresourceScale)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$labelSelectorPath = this.getLabelSelectorPath();
            Object other$labelSelectorPath = other.getLabelSelectorPath();
            if (this$labelSelectorPath == null) {
               if (other$labelSelectorPath != null) {
                  return false;
               }
            } else if (!this$labelSelectorPath.equals(other$labelSelectorPath)) {
               return false;
            }

            Object this$specReplicasPath = this.getSpecReplicasPath();
            Object other$specReplicasPath = other.getSpecReplicasPath();
            if (this$specReplicasPath == null) {
               if (other$specReplicasPath != null) {
                  return false;
               }
            } else if (!this$specReplicasPath.equals(other$specReplicasPath)) {
               return false;
            }

            Object this$statusReplicasPath = this.getStatusReplicasPath();
            Object other$statusReplicasPath = other.getStatusReplicasPath();
            if (this$statusReplicasPath == null) {
               if (other$statusReplicasPath != null) {
                  return false;
               }
            } else if (!this$statusReplicasPath.equals(other$statusReplicasPath)) {
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
      return other instanceof CustomResourceSubresourceScale;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $labelSelectorPath = this.getLabelSelectorPath();
      result = result * 59 + ($labelSelectorPath == null ? 43 : $labelSelectorPath.hashCode());
      Object $specReplicasPath = this.getSpecReplicasPath();
      result = result * 59 + ($specReplicasPath == null ? 43 : $specReplicasPath.hashCode());
      Object $statusReplicasPath = this.getStatusReplicasPath();
      result = result * 59 + ($statusReplicasPath == null ? 43 : $statusReplicasPath.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
