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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"expression", "message", "messageExpression", "reason"})
public class Validation implements Editable, KubernetesResource {
   @JsonProperty("expression")
   private String expression;
   @JsonProperty("message")
   private String message;
   @JsonProperty("messageExpression")
   private String messageExpression;
   @JsonProperty("reason")
   private String reason;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Validation() {
   }

   public Validation(String expression, String message, String messageExpression, String reason) {
      this.expression = expression;
      this.message = message;
      this.messageExpression = messageExpression;
      this.reason = reason;
   }

   @JsonProperty("expression")
   public String getExpression() {
      return this.expression;
   }

   @JsonProperty("expression")
   public void setExpression(String expression) {
      this.expression = expression;
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

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonIgnore
   public ValidationBuilder edit() {
      return new ValidationBuilder(this);
   }

   @JsonIgnore
   public ValidationBuilder toBuilder() {
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
      String var10000 = this.getExpression();
      return "Validation(expression=" + var10000 + ", message=" + this.getMessage() + ", messageExpression=" + this.getMessageExpression() + ", reason=" + this.getReason() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Validation)) {
         return false;
      } else {
         Validation other = (Validation)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expression = this.getExpression();
            Object other$expression = other.getExpression();
            if (this$expression == null) {
               if (other$expression != null) {
                  return false;
               }
            } else if (!this$expression.equals(other$expression)) {
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
      return other instanceof Validation;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expression = this.getExpression();
      result = result * 59 + ($expression == null ? 43 : $expression.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $messageExpression = this.getMessageExpression();
      result = result * 59 + ($messageExpression == null ? 43 : $messageExpression.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
