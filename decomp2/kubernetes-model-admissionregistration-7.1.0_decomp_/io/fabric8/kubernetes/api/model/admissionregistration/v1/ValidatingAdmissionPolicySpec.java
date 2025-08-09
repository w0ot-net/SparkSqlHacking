package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
@JsonPropertyOrder({"auditAnnotations", "failurePolicy", "matchConditions", "matchConstraints", "paramKind", "validations", "variables"})
public class ValidatingAdmissionPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("auditAnnotations")
   @JsonInclude(Include.NON_EMPTY)
   private List auditAnnotations = new ArrayList();
   @JsonProperty("failurePolicy")
   private String failurePolicy;
   @JsonProperty("matchConditions")
   @JsonInclude(Include.NON_EMPTY)
   private List matchConditions = new ArrayList();
   @JsonProperty("matchConstraints")
   private MatchResources matchConstraints;
   @JsonProperty("paramKind")
   private ParamKind paramKind;
   @JsonProperty("validations")
   @JsonInclude(Include.NON_EMPTY)
   private List validations = new ArrayList();
   @JsonProperty("variables")
   @JsonInclude(Include.NON_EMPTY)
   private List variables = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ValidatingAdmissionPolicySpec() {
   }

   public ValidatingAdmissionPolicySpec(List auditAnnotations, String failurePolicy, List matchConditions, MatchResources matchConstraints, ParamKind paramKind, List validations, List variables) {
      this.auditAnnotations = auditAnnotations;
      this.failurePolicy = failurePolicy;
      this.matchConditions = matchConditions;
      this.matchConstraints = matchConstraints;
      this.paramKind = paramKind;
      this.validations = validations;
      this.variables = variables;
   }

   @JsonProperty("auditAnnotations")
   @JsonInclude(Include.NON_EMPTY)
   public List getAuditAnnotations() {
      return this.auditAnnotations;
   }

   @JsonProperty("auditAnnotations")
   public void setAuditAnnotations(List auditAnnotations) {
      this.auditAnnotations = auditAnnotations;
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

   @JsonProperty("paramKind")
   public ParamKind getParamKind() {
      return this.paramKind;
   }

   @JsonProperty("paramKind")
   public void setParamKind(ParamKind paramKind) {
      this.paramKind = paramKind;
   }

   @JsonProperty("validations")
   @JsonInclude(Include.NON_EMPTY)
   public List getValidations() {
      return this.validations;
   }

   @JsonProperty("validations")
   public void setValidations(List validations) {
      this.validations = validations;
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
   public ValidatingAdmissionPolicySpecBuilder edit() {
      return new ValidatingAdmissionPolicySpecBuilder(this);
   }

   @JsonIgnore
   public ValidatingAdmissionPolicySpecBuilder toBuilder() {
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
      List var10000 = this.getAuditAnnotations();
      return "ValidatingAdmissionPolicySpec(auditAnnotations=" + var10000 + ", failurePolicy=" + this.getFailurePolicy() + ", matchConditions=" + this.getMatchConditions() + ", matchConstraints=" + this.getMatchConstraints() + ", paramKind=" + this.getParamKind() + ", validations=" + this.getValidations() + ", variables=" + this.getVariables() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ValidatingAdmissionPolicySpec)) {
         return false;
      } else {
         ValidatingAdmissionPolicySpec other = (ValidatingAdmissionPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$auditAnnotations = this.getAuditAnnotations();
            Object other$auditAnnotations = other.getAuditAnnotations();
            if (this$auditAnnotations == null) {
               if (other$auditAnnotations != null) {
                  return false;
               }
            } else if (!this$auditAnnotations.equals(other$auditAnnotations)) {
               return false;
            }

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

            Object this$paramKind = this.getParamKind();
            Object other$paramKind = other.getParamKind();
            if (this$paramKind == null) {
               if (other$paramKind != null) {
                  return false;
               }
            } else if (!this$paramKind.equals(other$paramKind)) {
               return false;
            }

            Object this$validations = this.getValidations();
            Object other$validations = other.getValidations();
            if (this$validations == null) {
               if (other$validations != null) {
                  return false;
               }
            } else if (!this$validations.equals(other$validations)) {
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
      return other instanceof ValidatingAdmissionPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $auditAnnotations = this.getAuditAnnotations();
      result = result * 59 + ($auditAnnotations == null ? 43 : $auditAnnotations.hashCode());
      Object $failurePolicy = this.getFailurePolicy();
      result = result * 59 + ($failurePolicy == null ? 43 : $failurePolicy.hashCode());
      Object $matchConditions = this.getMatchConditions();
      result = result * 59 + ($matchConditions == null ? 43 : $matchConditions.hashCode());
      Object $matchConstraints = this.getMatchConstraints();
      result = result * 59 + ($matchConstraints == null ? 43 : $matchConstraints.hashCode());
      Object $paramKind = this.getParamKind();
      result = result * 59 + ($paramKind == null ? 43 : $paramKind.hashCode());
      Object $validations = this.getValidations();
      result = result * 59 + ($validations == null ? 43 : $validations.hashCode());
      Object $variables = this.getVariables();
      result = result * 59 + ($variables == null ? 43 : $variables.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
