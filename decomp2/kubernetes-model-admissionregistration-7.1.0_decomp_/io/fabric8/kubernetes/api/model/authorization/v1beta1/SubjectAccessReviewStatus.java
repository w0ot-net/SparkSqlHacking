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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"allowed", "denied", "evaluationError", "reason"})
public class SubjectAccessReviewStatus implements Editable, KubernetesResource {
   @JsonProperty("allowed")
   private Boolean allowed;
   @JsonProperty("denied")
   private Boolean denied;
   @JsonProperty("evaluationError")
   private String evaluationError;
   @JsonProperty("reason")
   private String reason;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SubjectAccessReviewStatus() {
   }

   public SubjectAccessReviewStatus(Boolean allowed, Boolean denied, String evaluationError, String reason) {
      this.allowed = allowed;
      this.denied = denied;
      this.evaluationError = evaluationError;
      this.reason = reason;
   }

   @JsonProperty("allowed")
   public Boolean getAllowed() {
      return this.allowed;
   }

   @JsonProperty("allowed")
   public void setAllowed(Boolean allowed) {
      this.allowed = allowed;
   }

   @JsonProperty("denied")
   public Boolean getDenied() {
      return this.denied;
   }

   @JsonProperty("denied")
   public void setDenied(Boolean denied) {
      this.denied = denied;
   }

   @JsonProperty("evaluationError")
   public String getEvaluationError() {
      return this.evaluationError;
   }

   @JsonProperty("evaluationError")
   public void setEvaluationError(String evaluationError) {
      this.evaluationError = evaluationError;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonIgnore
   public SubjectAccessReviewStatusBuilder edit() {
      return new SubjectAccessReviewStatusBuilder(this);
   }

   @JsonIgnore
   public SubjectAccessReviewStatusBuilder toBuilder() {
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
      Boolean var10000 = this.getAllowed();
      return "SubjectAccessReviewStatus(allowed=" + var10000 + ", denied=" + this.getDenied() + ", evaluationError=" + this.getEvaluationError() + ", reason=" + this.getReason() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubjectAccessReviewStatus)) {
         return false;
      } else {
         SubjectAccessReviewStatus other = (SubjectAccessReviewStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allowed = this.getAllowed();
            Object other$allowed = other.getAllowed();
            if (this$allowed == null) {
               if (other$allowed != null) {
                  return false;
               }
            } else if (!this$allowed.equals(other$allowed)) {
               return false;
            }

            Object this$denied = this.getDenied();
            Object other$denied = other.getDenied();
            if (this$denied == null) {
               if (other$denied != null) {
                  return false;
               }
            } else if (!this$denied.equals(other$denied)) {
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

            Object this$reason = this.getReason();
            Object other$reason = other.getReason();
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
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
      return other instanceof SubjectAccessReviewStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowed = this.getAllowed();
      result = result * 59 + ($allowed == null ? 43 : $allowed.hashCode());
      Object $denied = this.getDenied();
      result = result * 59 + ($denied == null ? 43 : $denied.hashCode());
      Object $evaluationError = this.getEvaluationError();
      result = result * 59 + ($evaluationError == null ? 43 : $evaluationError.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
