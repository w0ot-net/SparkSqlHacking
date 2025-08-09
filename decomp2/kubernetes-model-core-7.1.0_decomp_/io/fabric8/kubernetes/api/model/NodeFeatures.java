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
@JsonPropertyOrder({"supplementalGroupsPolicy"})
public class NodeFeatures implements Editable, KubernetesResource {
   @JsonProperty("supplementalGroupsPolicy")
   private Boolean supplementalGroupsPolicy;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeFeatures() {
   }

   public NodeFeatures(Boolean supplementalGroupsPolicy) {
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
   }

   @JsonProperty("supplementalGroupsPolicy")
   public Boolean getSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy;
   }

   @JsonProperty("supplementalGroupsPolicy")
   public void setSupplementalGroupsPolicy(Boolean supplementalGroupsPolicy) {
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
   }

   @JsonIgnore
   public NodeFeaturesBuilder edit() {
      return new NodeFeaturesBuilder(this);
   }

   @JsonIgnore
   public NodeFeaturesBuilder toBuilder() {
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
      Boolean var10000 = this.getSupplementalGroupsPolicy();
      return "NodeFeatures(supplementalGroupsPolicy=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeFeatures)) {
         return false;
      } else {
         NodeFeatures other = (NodeFeatures)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$supplementalGroupsPolicy = this.getSupplementalGroupsPolicy();
            Object other$supplementalGroupsPolicy = other.getSupplementalGroupsPolicy();
            if (this$supplementalGroupsPolicy == null) {
               if (other$supplementalGroupsPolicy != null) {
                  return false;
               }
            } else if (!this$supplementalGroupsPolicy.equals(other$supplementalGroupsPolicy)) {
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
      return other instanceof NodeFeatures;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $supplementalGroupsPolicy = this.getSupplementalGroupsPolicy();
      result = result * 59 + ($supplementalGroupsPolicy == null ? 43 : $supplementalGroupsPolicy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
