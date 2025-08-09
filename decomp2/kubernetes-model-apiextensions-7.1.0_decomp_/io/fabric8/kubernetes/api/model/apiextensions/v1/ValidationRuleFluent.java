package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ValidationRuleFluent extends BaseFluent {
   private String fieldPath;
   private String message;
   private String messageExpression;
   private Boolean optionalOldSelf;
   private String reason;
   private String rule;
   private Map additionalProperties;

   public ValidationRuleFluent() {
   }

   public ValidationRuleFluent(ValidationRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ValidationRule instance) {
      instance = instance != null ? instance : new ValidationRule();
      if (instance != null) {
         this.withFieldPath(instance.getFieldPath());
         this.withMessage(instance.getMessage());
         this.withMessageExpression(instance.getMessageExpression());
         this.withOptionalOldSelf(instance.getOptionalOldSelf());
         this.withReason(instance.getReason());
         this.withRule(instance.getRule());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFieldPath() {
      return this.fieldPath;
   }

   public ValidationRuleFluent withFieldPath(String fieldPath) {
      this.fieldPath = fieldPath;
      return this;
   }

   public boolean hasFieldPath() {
      return this.fieldPath != null;
   }

   public String getMessage() {
      return this.message;
   }

   public ValidationRuleFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getMessageExpression() {
      return this.messageExpression;
   }

   public ValidationRuleFluent withMessageExpression(String messageExpression) {
      this.messageExpression = messageExpression;
      return this;
   }

   public boolean hasMessageExpression() {
      return this.messageExpression != null;
   }

   public Boolean getOptionalOldSelf() {
      return this.optionalOldSelf;
   }

   public ValidationRuleFluent withOptionalOldSelf(Boolean optionalOldSelf) {
      this.optionalOldSelf = optionalOldSelf;
      return this;
   }

   public boolean hasOptionalOldSelf() {
      return this.optionalOldSelf != null;
   }

   public String getReason() {
      return this.reason;
   }

   public ValidationRuleFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public String getRule() {
      return this.rule;
   }

   public ValidationRuleFluent withRule(String rule) {
      this.rule = rule;
      return this;
   }

   public boolean hasRule() {
      return this.rule != null;
   }

   public ValidationRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ValidationRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ValidationRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ValidationRuleFluent removeFromAdditionalProperties(Map map) {
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

   public ValidationRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            ValidationRuleFluent that = (ValidationRuleFluent)o;
            if (!Objects.equals(this.fieldPath, that.fieldPath)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.messageExpression, that.messageExpression)) {
               return false;
            } else if (!Objects.equals(this.optionalOldSelf, that.optionalOldSelf)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.rule, that.rule)) {
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
      return Objects.hash(new Object[]{this.fieldPath, this.message, this.messageExpression, this.optionalOldSelf, this.reason, this.rule, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fieldPath != null) {
         sb.append("fieldPath:");
         sb.append(this.fieldPath + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.messageExpression != null) {
         sb.append("messageExpression:");
         sb.append(this.messageExpression + ",");
      }

      if (this.optionalOldSelf != null) {
         sb.append("optionalOldSelf:");
         sb.append(this.optionalOldSelf + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.rule != null) {
         sb.append("rule:");
         sb.append(this.rule + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ValidationRuleFluent withOptionalOldSelf() {
      return this.withOptionalOldSelf(true);
   }
}
