package io.fabric8.kubernetes.api.model.apiextensions.v1beta1;

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
@JsonPropertyOrder({"scale", "status"})
public class CustomResourceSubresources implements Editable, KubernetesResource {
   @JsonProperty("scale")
   private CustomResourceSubresourceScale scale;
   @JsonProperty("status")
   private CustomResourceSubresourceStatus status;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CustomResourceSubresources() {
   }

   public CustomResourceSubresources(CustomResourceSubresourceScale scale, CustomResourceSubresourceStatus status) {
      this.scale = scale;
      this.status = status;
   }

   @JsonProperty("scale")
   public CustomResourceSubresourceScale getScale() {
      return this.scale;
   }

   @JsonProperty("scale")
   public void setScale(CustomResourceSubresourceScale scale) {
      this.scale = scale;
   }

   @JsonProperty("status")
   public CustomResourceSubresourceStatus getStatus() {
      return this.status;
   }

   @JsonProperty("status")
   public void setStatus(CustomResourceSubresourceStatus status) {
      this.status = status;
   }

   @JsonIgnore
   public CustomResourceSubresourcesBuilder edit() {
      return new CustomResourceSubresourcesBuilder(this);
   }

   @JsonIgnore
   public CustomResourceSubresourcesBuilder toBuilder() {
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
      CustomResourceSubresourceScale var10000 = this.getScale();
      return "CustomResourceSubresources(scale=" + var10000 + ", status=" + this.getStatus() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CustomResourceSubresources)) {
         return false;
      } else {
         CustomResourceSubresources other = (CustomResourceSubresources)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$scale = this.getScale();
            Object other$scale = other.getScale();
            if (this$scale == null) {
               if (other$scale != null) {
                  return false;
               }
            } else if (!this$scale.equals(other$scale)) {
               return false;
            }

            Object this$status = this.getStatus();
            Object other$status = other.getStatus();
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
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
      return other instanceof CustomResourceSubresources;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $scale = this.getScale();
      result = result * 59 + ($scale == null ? 43 : $scale.hashCode());
      Object $status = this.getStatus();
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
