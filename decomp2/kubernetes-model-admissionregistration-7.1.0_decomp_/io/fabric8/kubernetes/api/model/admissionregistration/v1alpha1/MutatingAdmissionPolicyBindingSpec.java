package io.fabric8.kubernetes.api.model.admissionregistration.v1alpha1;

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
@JsonPropertyOrder({"matchResources", "paramRef", "policyName"})
public class MutatingAdmissionPolicyBindingSpec implements Editable, KubernetesResource {
   @JsonProperty("matchResources")
   private MatchResources matchResources;
   @JsonProperty("paramRef")
   private ParamRef paramRef;
   @JsonProperty("policyName")
   private String policyName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MutatingAdmissionPolicyBindingSpec() {
   }

   public MutatingAdmissionPolicyBindingSpec(MatchResources matchResources, ParamRef paramRef, String policyName) {
      this.matchResources = matchResources;
      this.paramRef = paramRef;
      this.policyName = policyName;
   }

   @JsonProperty("matchResources")
   public MatchResources getMatchResources() {
      return this.matchResources;
   }

   @JsonProperty("matchResources")
   public void setMatchResources(MatchResources matchResources) {
      this.matchResources = matchResources;
   }

   @JsonProperty("paramRef")
   public ParamRef getParamRef() {
      return this.paramRef;
   }

   @JsonProperty("paramRef")
   public void setParamRef(ParamRef paramRef) {
      this.paramRef = paramRef;
   }

   @JsonProperty("policyName")
   public String getPolicyName() {
      return this.policyName;
   }

   @JsonProperty("policyName")
   public void setPolicyName(String policyName) {
      this.policyName = policyName;
   }

   @JsonIgnore
   public MutatingAdmissionPolicyBindingSpecBuilder edit() {
      return new MutatingAdmissionPolicyBindingSpecBuilder(this);
   }

   @JsonIgnore
   public MutatingAdmissionPolicyBindingSpecBuilder toBuilder() {
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
      MatchResources var10000 = this.getMatchResources();
      return "MutatingAdmissionPolicyBindingSpec(matchResources=" + var10000 + ", paramRef=" + this.getParamRef() + ", policyName=" + this.getPolicyName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MutatingAdmissionPolicyBindingSpec)) {
         return false;
      } else {
         MutatingAdmissionPolicyBindingSpec other = (MutatingAdmissionPolicyBindingSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$matchResources = this.getMatchResources();
            Object other$matchResources = other.getMatchResources();
            if (this$matchResources == null) {
               if (other$matchResources != null) {
                  return false;
               }
            } else if (!this$matchResources.equals(other$matchResources)) {
               return false;
            }

            Object this$paramRef = this.getParamRef();
            Object other$paramRef = other.getParamRef();
            if (this$paramRef == null) {
               if (other$paramRef != null) {
                  return false;
               }
            } else if (!this$paramRef.equals(other$paramRef)) {
               return false;
            }

            Object this$policyName = this.getPolicyName();
            Object other$policyName = other.getPolicyName();
            if (this$policyName == null) {
               if (other$policyName != null) {
                  return false;
               }
            } else if (!this$policyName.equals(other$policyName)) {
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
      return other instanceof MutatingAdmissionPolicyBindingSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $matchResources = this.getMatchResources();
      result = result * 59 + ($matchResources == null ? 43 : $matchResources.hashCode());
      Object $paramRef = this.getParamRef();
      result = result * 59 + ($paramRef == null ? 43 : $paramRef.hashCode());
      Object $policyName = this.getPolicyName();
      result = result * 59 + ($policyName == null ? 43 : $policyName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
