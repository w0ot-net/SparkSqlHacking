package io.fabric8.kubernetes.api.model.authorization.v1beta1;

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
@JsonPropertyOrder({"evaluationError", "incomplete", "nonResourceRules", "resourceRules"})
public class SubjectRulesReviewStatus implements Editable, KubernetesResource {
   @JsonProperty("evaluationError")
   private String evaluationError;
   @JsonProperty("incomplete")
   private Boolean incomplete;
   @JsonProperty("nonResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List nonResourceRules = new ArrayList();
   @JsonProperty("resourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceRules = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SubjectRulesReviewStatus() {
   }

   public SubjectRulesReviewStatus(String evaluationError, Boolean incomplete, List nonResourceRules, List resourceRules) {
      this.evaluationError = evaluationError;
      this.incomplete = incomplete;
      this.nonResourceRules = nonResourceRules;
      this.resourceRules = resourceRules;
   }

   @JsonProperty("evaluationError")
   public String getEvaluationError() {
      return this.evaluationError;
   }

   @JsonProperty("evaluationError")
   public void setEvaluationError(String evaluationError) {
      this.evaluationError = evaluationError;
   }

   @JsonProperty("incomplete")
   public Boolean getIncomplete() {
      return this.incomplete;
   }

   @JsonProperty("incomplete")
   public void setIncomplete(Boolean incomplete) {
      this.incomplete = incomplete;
   }

   @JsonProperty("nonResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   public List getNonResourceRules() {
      return this.nonResourceRules;
   }

   @JsonProperty("nonResourceRules")
   public void setNonResourceRules(List nonResourceRules) {
      this.nonResourceRules = nonResourceRules;
   }

   @JsonProperty("resourceRules")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceRules() {
      return this.resourceRules;
   }

   @JsonProperty("resourceRules")
   public void setResourceRules(List resourceRules) {
      this.resourceRules = resourceRules;
   }

   @JsonIgnore
   public SubjectRulesReviewStatusBuilder edit() {
      return new SubjectRulesReviewStatusBuilder(this);
   }

   @JsonIgnore
   public SubjectRulesReviewStatusBuilder toBuilder() {
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
      String var10000 = this.getEvaluationError();
      return "SubjectRulesReviewStatus(evaluationError=" + var10000 + ", incomplete=" + this.getIncomplete() + ", nonResourceRules=" + this.getNonResourceRules() + ", resourceRules=" + this.getResourceRules() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubjectRulesReviewStatus)) {
         return false;
      } else {
         SubjectRulesReviewStatus other = (SubjectRulesReviewStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$incomplete = this.getIncomplete();
            Object other$incomplete = other.getIncomplete();
            if (this$incomplete == null) {
               if (other$incomplete != null) {
                  return false;
               }
            } else if (!this$incomplete.equals(other$incomplete)) {
               return false;
            }

            Object this$evaluationError = this.getEvaluationError();
            Object other$evaluationError = other.getEvaluationError();
            if (this$evaluationError == null) {
               if (other$evaluationError != null) {
                  return false;
               }
            } else if (!this$evaluationError.equals(other$evaluationError)) {
               return false;
            }

            Object this$nonResourceRules = this.getNonResourceRules();
            Object other$nonResourceRules = other.getNonResourceRules();
            if (this$nonResourceRules == null) {
               if (other$nonResourceRules != null) {
                  return false;
               }
            } else if (!this$nonResourceRules.equals(other$nonResourceRules)) {
               return false;
            }

            Object this$resourceRules = this.getResourceRules();
            Object other$resourceRules = other.getResourceRules();
            if (this$resourceRules == null) {
               if (other$resourceRules != null) {
                  return false;
               }
            } else if (!this$resourceRules.equals(other$resourceRules)) {
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
      return other instanceof SubjectRulesReviewStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $incomplete = this.getIncomplete();
      result = result * 59 + ($incomplete == null ? 43 : $incomplete.hashCode());
      Object $evaluationError = this.getEvaluationError();
      result = result * 59 + ($evaluationError == null ? 43 : $evaluationError.hashCode());
      Object $nonResourceRules = this.getNonResourceRules();
      result = result * 59 + ($nonResourceRules == null ? 43 : $nonResourceRules.hashCode());
      Object $resourceRules = this.getResourceRules();
      result = result * 59 + ($resourceRules == null ? 43 : $resourceRules.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
