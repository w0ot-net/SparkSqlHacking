package io.fabric8.kubernetes.api.model.authorization.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SubjectAccessReviewStatusFluent extends BaseFluent {
   private Boolean allowed;
   private Boolean denied;
   private String evaluationError;
   private String reason;
   private Map additionalProperties;

   public SubjectAccessReviewStatusFluent() {
   }

   public SubjectAccessReviewStatusFluent(SubjectAccessReviewStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SubjectAccessReviewStatus instance) {
      instance = instance != null ? instance : new SubjectAccessReviewStatus();
      if (instance != null) {
         this.withAllowed(instance.getAllowed());
         this.withDenied(instance.getDenied());
         this.withEvaluationError(instance.getEvaluationError());
         this.withReason(instance.getReason());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowed() {
      return this.allowed;
   }

   public SubjectAccessReviewStatusFluent withAllowed(Boolean allowed) {
      this.allowed = allowed;
      return this;
   }

   public boolean hasAllowed() {
      return this.allowed != null;
   }

   public Boolean getDenied() {
      return this.denied;
   }

   public SubjectAccessReviewStatusFluent withDenied(Boolean denied) {
      this.denied = denied;
      return this;
   }

   public boolean hasDenied() {
      return this.denied != null;
   }

   public String getEvaluationError() {
      return this.evaluationError;
   }

   public SubjectAccessReviewStatusFluent withEvaluationError(String evaluationError) {
      this.evaluationError = evaluationError;
      return this;
   }

   public boolean hasEvaluationError() {
      return this.evaluationError != null;
   }

   public String getReason() {
      return this.reason;
   }

   public SubjectAccessReviewStatusFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public SubjectAccessReviewStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SubjectAccessReviewStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SubjectAccessReviewStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SubjectAccessReviewStatusFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public SubjectAccessReviewStatusFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            SubjectAccessReviewStatusFluent that = (SubjectAccessReviewStatusFluent)o;
            if (!Objects.equals(this.allowed, that.allowed)) {
               return false;
            } else if (!Objects.equals(this.denied, that.denied)) {
               return false;
            } else if (!Objects.equals(this.evaluationError, that.evaluationError)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.allowed, this.denied, this.evaluationError, this.reason, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowed != null) {
         sb.append("allowed:");
         sb.append(this.allowed + ",");
      }

      if (this.denied != null) {
         sb.append("denied:");
         sb.append(this.denied + ",");
      }

      if (this.evaluationError != null) {
         sb.append("evaluationError:");
         sb.append(this.evaluationError + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public SubjectAccessReviewStatusFluent withAllowed() {
      return this.withAllowed(true);
   }

   public SubjectAccessReviewStatusFluent withDenied() {
      return this.withDenied(true);
   }
}
