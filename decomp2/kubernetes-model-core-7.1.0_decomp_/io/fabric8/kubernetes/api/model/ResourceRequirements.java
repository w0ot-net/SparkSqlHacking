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
@JsonPropertyOrder({"claims", "limits", "requests"})
public class ResourceRequirements implements Editable, KubernetesResource {
   @JsonProperty("claims")
   @JsonInclude(Include.NON_EMPTY)
   private List claims = new ArrayList();
   @JsonProperty("limits")
   @JsonInclude(Include.NON_EMPTY)
   private Map limits = new LinkedHashMap();
   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   private Map requests = new LinkedHashMap();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceRequirements() {
   }

   public ResourceRequirements(List claims, Map limits, Map requests) {
      this.claims = claims;
      this.limits = limits;
      this.requests = requests;
   }

   @JsonProperty("claims")
   @JsonInclude(Include.NON_EMPTY)
   public List getClaims() {
      return this.claims;
   }

   @JsonProperty("claims")
   public void setClaims(List claims) {
      this.claims = claims;
   }

   @JsonProperty("limits")
   @JsonInclude(Include.NON_EMPTY)
   public Map getLimits() {
      return this.limits;
   }

   @JsonProperty("limits")
   public void setLimits(Map limits) {
      this.limits = limits;
   }

   @JsonProperty("requests")
   @JsonInclude(Include.NON_EMPTY)
   public Map getRequests() {
      return this.requests;
   }

   @JsonProperty("requests")
   public void setRequests(Map requests) {
      this.requests = requests;
   }

   @JsonIgnore
   public ResourceRequirementsBuilder edit() {
      return new ResourceRequirementsBuilder(this);
   }

   @JsonIgnore
   public ResourceRequirementsBuilder toBuilder() {
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
      List var10000 = this.getClaims();
      return "ResourceRequirements(claims=" + var10000 + ", limits=" + this.getLimits() + ", requests=" + this.getRequests() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceRequirements)) {
         return false;
      } else {
         ResourceRequirements other = (ResourceRequirements)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$claims = this.getClaims();
            Object other$claims = other.getClaims();
            if (this$claims == null) {
               if (other$claims != null) {
                  return false;
               }
            } else if (!this$claims.equals(other$claims)) {
               return false;
            }

            Object this$limits = this.getLimits();
            Object other$limits = other.getLimits();
            if (this$limits == null) {
               if (other$limits != null) {
                  return false;
               }
            } else if (!this$limits.equals(other$limits)) {
               return false;
            }

            Object this$requests = this.getRequests();
            Object other$requests = other.getRequests();
            if (this$requests == null) {
               if (other$requests != null) {
                  return false;
               }
            } else if (!this$requests.equals(other$requests)) {
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
      return other instanceof ResourceRequirements;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $claims = this.getClaims();
      result = result * 59 + ($claims == null ? 43 : $claims.hashCode());
      Object $limits = this.getLimits();
      result = result * 59 + ($limits == null ? 43 : $limits.hashCode());
      Object $requests = this.getRequests();
      result = result * 59 + ($requests == null ? 43 : $requests.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
