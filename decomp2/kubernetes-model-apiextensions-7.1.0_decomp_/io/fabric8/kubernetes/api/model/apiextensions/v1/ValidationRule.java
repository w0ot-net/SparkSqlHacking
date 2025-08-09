package io.fabric8.kubernetes.api.model.apiextensions.v1;

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
@JsonPropertyOrder({"fieldPath", "message", "messageExpression", "optionalOldSelf", "reason", "rule"})
public class ValidationRule implements Editable, KubernetesResource {
   @JsonProperty("fieldPath")
   private String fieldPath;
   @JsonProperty("message")
   private String message;
   @JsonProperty("messageExpression")
   private String messageExpression;
   @JsonProperty("optionalOldSelf")
   private Boolean optionalOldSelf;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("rule")
   private String rule;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ValidationRule() {
   }

   public ValidationRule(String fieldPath, String message, String messageExpression, Boolean optionalOldSelf, String reason, String rule) {
      this.fieldPath = fieldPath;
      this.message = message;
      this.messageExpression = messageExpression;
      this.optionalOldSelf = optionalOldSelf;
      this.reason = reason;
      this.rule = rule;
   }

   @JsonProperty("fieldPath")
   public String getFieldPath() {
      return this.fieldPath;
   }

   @JsonProperty("fieldPath")
   public void setFieldPath(String fieldPath) {
      this.fieldPath = fieldPath;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("messageExpression")
   public String getMessageExpression() {
      return this.messageExpression;
   }

   @JsonProperty("messageExpression")
   public void setMessageExpression(String messageExpression) {
      this.messageExpression = messageExpression;
   }

   @JsonProperty("optionalOldSelf")
   public Boolean getOptionalOldSelf() {
      return this.optionalOldSelf;
   }

   @JsonProperty("optionalOldSelf")
   public void setOptionalOldSelf(Boolean optionalOldSelf) {
      this.optionalOldSelf = optionalOldSelf;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonProperty("rule")
   public String getRule() {
      return this.rule;
   }

   @JsonProperty("rule")
   public void setRule(String rule) {
      this.rule = rule;
   }

   @JsonIgnore
   public ValidationRuleBuilder edit() {
      return new ValidationRuleBuilder(this);
   }

   @JsonIgnore
   public ValidationRuleBuilder toBuilder() {
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
      String var10000 = this.getFieldPath();
      return "ValidationRule(fieldPath=" + var10000 + ", message=" + this.getMessage() + ", messageExpression=" + this.getMessageExpression() + ", optionalOldSelf=" + this.getOptionalOldSelf() + ", reason=" + this.getReason() + ", rule=" + this.getRule() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ValidationRule)) {
         return false;
      } else {
         ValidationRule other = (ValidationRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$optionalOldSelf = this.getOptionalOldSelf();
            Object other$optionalOldSelf = other.getOptionalOldSelf();
            if (this$optionalOldSelf == null) {
               if (other$optionalOldSelf != null) {
                  return false;
               }
            } else if (!this$optionalOldSelf.equals(other$optionalOldSelf)) {
               return false;
            }

            Object this$fieldPath = this.getFieldPath();
            Object other$fieldPath = other.getFieldPath();
            if (this$fieldPath == null) {
               if (other$fieldPath != null) {
                  return false;
               }
            } else if (!this$fieldPath.equals(other$fieldPath)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$messageExpression = this.getMessageExpression();
            Object other$messageExpression = other.getMessageExpression();
            if (this$messageExpression == null) {
               if (other$messageExpression != null) {
                  return false;
               }
            } else if (!this$messageExpression.equals(other$messageExpression)) {
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

            Object this$rule = this.getRule();
            Object other$rule = other.getRule();
            if (this$rule == null) {
               if (other$rule != null) {
                  return false;
               }
            } else if (!this$rule.equals(other$rule)) {
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
      return other instanceof ValidationRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $optionalOldSelf = this.getOptionalOldSelf();
      result = result * 59 + ($optionalOldSelf == null ? 43 : $optionalOldSelf.hashCode());
      Object $fieldPath = this.getFieldPath();
      result = result * 59 + ($fieldPath == null ? 43 : $fieldPath.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $messageExpression = this.getMessageExpression();
      result = result * 59 + ($messageExpression == null ? 43 : $messageExpression.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $rule = this.getRule();
      result = result * 59 + ($rule == null ? 43 : $rule.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
