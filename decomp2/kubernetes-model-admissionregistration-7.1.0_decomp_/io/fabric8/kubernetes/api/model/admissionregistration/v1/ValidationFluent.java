package io.fabric8.kubernetes.api.model.admissionregistration.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ValidationFluent extends BaseFluent {
   private String expression;
   private String message;
   private String messageExpression;
   private String reason;
   private Map additionalProperties;

   public ValidationFluent() {
   }

   public ValidationFluent(Validation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Validation instance) {
      instance = instance != null ? instance : new Validation();
      if (instance != null) {
         this.withExpression(instance.getExpression());
         this.withMessage(instance.getMessage());
         this.withMessageExpression(instance.getMessageExpression());
         this.withReason(instance.getReason());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getExpression() {
      return this.expression;
   }

   public ValidationFluent withExpression(String expression) {
      this.expression = expression;
      return this;
   }

   public boolean hasExpression() {
      return this.expression != null;
   }

   public String getMessage() {
      return this.message;
   }

   public ValidationFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getMessageExpression() {
      return this.messageExpression;
   }

   public ValidationFluent withMessageExpression(String messageExpression) {
      this.messageExpression = messageExpression;
      return this;
   }

   public boolean hasMessageExpression() {
      return this.messageExpression != null;
   }

   public String getReason() {
      return this.reason;
   }

   public ValidationFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public ValidationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidationFluent removeFromAdditionalProperties(Map map) {
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

   public ValidationFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidationFluent that = (ValidationFluent)o;
            if (!Objects.equals(this.expression, that.expression)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.messageExpression, that.messageExpression)) {
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
      return Objects.hash(new Object[]{this.expression, this.message, this.messageExpression, this.reason, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.expression != null) {
         sb.append("expression:");
         sb.append(this.expression + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.messageExpression != null) {
         sb.append("messageExpression:");
         sb.append(this.messageExpression + ",");
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
}
