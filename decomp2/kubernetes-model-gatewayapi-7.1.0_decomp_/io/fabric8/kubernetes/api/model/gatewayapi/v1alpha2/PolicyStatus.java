package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

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
@JsonPropertyOrder({"ancestors"})
public class PolicyStatus implements Editable, KubernetesResource {
   @JsonProperty("ancestors")
   @JsonInclude(Include.NON_EMPTY)
   private List ancestors = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PolicyStatus() {
   }

   public PolicyStatus(List ancestors) {
      this.ancestors = ancestors;
   }

   @JsonProperty("ancestors")
   @JsonInclude(Include.NON_EMPTY)
   public List getAncestors() {
      return this.ancestors;
   }

   @JsonProperty("ancestors")
   public void setAncestors(List ancestors) {
      this.ancestors = ancestors;
   }

   @JsonIgnore
   public PolicyStatusBuilder edit() {
      return new PolicyStatusBuilder(this);
   }

   @JsonIgnore
   public PolicyStatusBuilder toBuilder() {
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
      List var10000 = this.getAncestors();
      return "PolicyStatus(ancestors=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PolicyStatus)) {
         return false;
      } else {
         PolicyStatus other = (PolicyStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ancestors = this.getAncestors();
            Object other$ancestors = other.getAncestors();
            if (this$ancestors == null) {
               if (other$ancestors != null) {
                  return false;
               }
            } else if (!this$ancestors.equals(other$ancestors)) {
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
      return other instanceof PolicyStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ancestors = this.getAncestors();
      result = result * 59 + ($ancestors == null ? 43 : $ancestors.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
