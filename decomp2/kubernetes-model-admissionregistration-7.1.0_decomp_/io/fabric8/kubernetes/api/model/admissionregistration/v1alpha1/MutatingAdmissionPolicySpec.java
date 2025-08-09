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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"failurePolicy", "matchConditions", "matchConstraints", "mutations", "paramKind", "reinvocationPolicy", "variables"})
public class MutatingAdmissionPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("failurePolicy")
   private String failurePolicy;
   @JsonProperty("matchConditions")
   @JsonInclude(Include.NON_EMPTY)
   private List matchConditions = new ArrayList();
   @JsonProperty("matchConstraints")
   private MatchResources matchConstraints;
   @JsonProperty("mutations")
   @JsonInclude(Include.NON_EMPTY)
   private List mutations = new ArrayList();
   @JsonProperty("paramKind")
   private ParamKind paramKind;
   @JsonProperty("reinvocationPolicy")
   private String reinvocationPolicy;
   @JsonProperty("variables")
   @JsonInclude(Include.NON_EMPTY)
   private List variables = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MutatingAdmissionPolicySpec() {
   }

   public MutatingAdmissionPolicySpec(String failurePolicy, List matchConditions, MatchResources matchConstraints, List mutations, ParamKind paramKind, String reinvocationPolicy, List variables) {
      this.failurePolicy = failurePolicy;
      this.matchConditions = matchConditions;
      this.matchConstraints = matchConstraints;
      this.mutations = mutations;
      this.paramKind = paramKind;
      this.reinvocationPolicy = reinvocationPolicy;
      this.variables = variables;
   }

   @JsonProperty("failurePolicy")
   public String getFailurePolicy() {
      return this.failurePolicy;
   }

   @JsonProperty("failurePolicy")
   public void setFailurePolicy(String failurePolicy) {
      this.failurePolicy = failurePolicy;
   }

   @JsonProperty("matchConditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getMatchConditions() {
      return this.matchConditions;
   }

   @JsonProperty("matchConditions")
   public void setMatchConditions(List matchConditions) {
      this.matchConditions = matchConditions;
   }

   @JsonProperty("matchConstraints")
   public MatchResources getMatchConstraints() {
      return this.matchConstraints;
   }

   @JsonProperty("matchConstraints")
   public void setMatchConstraints(MatchResources matchConstraints) {
      this.matchConstraints = matchConstraints;
   }

   @JsonProperty("mutations")
   @JsonInclude(Include.NON_EMPTY)
   public List getMutations() {
      return this.mutations;
   }

   @JsonProperty("mutations")
   public void setMutations(List mutations) {
      this.mutations = mutations;
   }

   @JsonProperty("paramKind")
   public ParamKind getParamKind() {
      return this.paramKind;
   }

   @JsonProperty("paramKind")
   public void setParamKind(ParamKind paramKind) {
      this.paramKind = paramKind;
   }

   @JsonProperty("reinvocationPolicy")
   public String getReinvocationPolicy() {
      return this.reinvocationPolicy;
   }

   @JsonProperty("reinvocationPolicy")
   public void setReinvocationPolicy(String reinvocationPolicy) {
      this.reinvocationPolicy = reinvocationPolicy;
   }

   @JsonProperty("variables")
   @JsonInclude(Include.NON_EMPTY)
   public List getVariables() {
      return this.variables;
   }

   @JsonProperty("variables")
   public void setVariables(List variables) {
      this.variables = variables;
   }

   @JsonIgnore
   public MutatingAdmissionPolicySpecBuilder edit() {
      return new MutatingAdmissionPolicySpecBuilder(this);
   }

   @JsonIgnore
   public MutatingAdmissionPolicySpecBuilder toBuilder() {
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
      String var10000 = this.getFailurePolicy();
      return "MutatingAdmissionPolicySpec(failurePolicy=" + var10000 + ", matchConditions=" + this.getMatchConditions() + ", matchConstraints=" + this.getMatchConstraints() + ", mutations=" + this.getMutations() + ", paramKind=" + this.getParamKind() + ", reinvocationPolicy=" + this.getReinvocationPolicy() + ", variables=" + this.getVariables() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MutatingAdmissionPolicySpec)) {
         return false;
      } else {
         MutatingAdmissionPolicySpec other = (MutatingAdmissionPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$failurePolicy = this.getFailurePolicy();
            Object other$failurePolicy = other.getFailurePolicy();
            if (this$failurePolicy == null) {
               if (other$failurePolicy != null) {
                  return false;
               }
            } else if (!this$failurePolicy.equals(other$failurePolicy)) {
               return false;
            }

            Object this$matchConditions = this.getMatchConditions();
            Object other$matchConditions = other.getMatchConditions();
            if (this$matchConditions == null) {
               if (other$matchConditions != null) {
                  return false;
               }
            } else if (!this$matchConditions.equals(other$matchConditions)) {
               return false;
            }

            Object this$matchConstraints = this.getMatchConstraints();
            Object other$matchConstraints = other.getMatchConstraints();
            if (this$matchConstraints == null) {
               if (other$matchConstraints != null) {
                  return false;
               }
            } else if (!this$matchConstraints.equals(other$matchConstraints)) {
               return false;
            }

            Object this$mutations = this.getMutations();
            Object other$mutations = other.getMutations();
            if (this$mutations == null) {
               if (other$mutations != null) {
                  return false;
               }
            } else if (!this$mutations.equals(other$mutations)) {
               return false;
            }

            Object this$paramKind = this.getParamKind();
            Object other$paramKind = other.getParamKind();
            if (this$paramKind == null) {
               if (other$paramKind != null) {
                  return false;
               }
            } else if (!this$paramKind.equals(other$paramKind)) {
               return false;
            }

            Object this$reinvocationPolicy = this.getReinvocationPolicy();
            Object other$reinvocationPolicy = other.getReinvocationPolicy();
            if (this$reinvocationPolicy == null) {
               if (other$reinvocationPolicy != null) {
                  return false;
               }
            } else if (!this$reinvocationPolicy.equals(other$reinvocationPolicy)) {
               return false;
            }

            Object this$variables = this.getVariables();
            Object other$variables = other.getVariables();
            if (this$variables == null) {
               if (other$variables != null) {
                  return false;
               }
            } else if (!this$variables.equals(other$variables)) {
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
      return other instanceof MutatingAdmissionPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $failurePolicy = this.getFailurePolicy();
      result = result * 59 + ($failurePolicy == null ? 43 : $failurePolicy.hashCode());
      Object $matchConditions = this.getMatchConditions();
      result = result * 59 + ($matchConditions == null ? 43 : $matchConditions.hashCode());
      Object $matchConstraints = this.getMatchConstraints();
      result = result * 59 + ($matchConstraints == null ? 43 : $matchConstraints.hashCode());
      Object $mutations = this.getMutations();
      result = result * 59 + ($mutations == null ? 43 : $mutations.hashCode());
      Object $paramKind = this.getParamKind();
      result = result * 59 + ($paramKind == null ? 43 : $paramKind.hashCode());
      Object $reinvocationPolicy = this.getReinvocationPolicy();
      result = result * 59 + ($reinvocationPolicy == null ? 43 : $reinvocationPolicy.hashCode());
      Object $variables = this.getVariables();
      result = result * 59 + ($variables == null ? 43 : $variables.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
