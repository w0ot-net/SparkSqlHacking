package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"resourceClaims"})
public class PodSchedulingContextStatus implements Editable, KubernetesResource {
   @JsonProperty("resourceClaims")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceClaims = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodSchedulingContextStatus() {
   }

   public PodSchedulingContextStatus(List resourceClaims) {
      this.resourceClaims = resourceClaims;
   }

   @JsonProperty("resourceClaims")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceClaims() {
      return this.resourceClaims;
   }

   @JsonProperty("resourceClaims")
   public void setResourceClaims(List resourceClaims) {
      this.resourceClaims = resourceClaims;
   }

   @JsonIgnore
   public PodSchedulingContextStatusBuilder edit() {
      return new PodSchedulingContextStatusBuilder(this);
   }

   @JsonIgnore
   public PodSchedulingContextStatusBuilder toBuilder() {
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
      List var10000 = this.getResourceClaims();
      return "PodSchedulingContextStatus(resourceClaims=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodSchedulingContextStatus)) {
         return false;
      } else {
         PodSchedulingContextStatus other = (PodSchedulingContextStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$resourceClaims = this.getResourceClaims();
            Object other$resourceClaims = other.getResourceClaims();
            if (this$resourceClaims == null) {
               if (other$resourceClaims != null) {
                  return false;
               }
            } else if (!this$resourceClaims.equals(other$resourceClaims)) {
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
      return other instanceof PodSchedulingContextStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $resourceClaims = this.getResourceClaims();
      result = result * 59 + ($resourceClaims == null ? 43 : $resourceClaims.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
