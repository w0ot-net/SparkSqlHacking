package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class APIServiceConditionFluent extends BaseFluent {
   private String lastTransitionTime;
   private String message;
   private String reason;
   private String status;
   private String type;
   private Map additionalProperties;

   public APIServiceConditionFluent() {
   }

   public APIServiceConditionFluent(APIServiceCondition instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(APIServiceCondition instance) {
      instance = instance != null ? instance : new APIServiceCondition();
      if (instance != null) {
         this.withLastTransitionTime(instance.getLastTransitionTime());
         this.withMessage(instance.getMessage());
         this.withReason(instance.getReason());
         this.withStatus(instance.getStatus());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getLastTransitionTime() {
      return this.lastTransitionTime;
   }

   public APIServiceConditionFluent withLastTransitionTime(String lastTransitionTime) {
      this.lastTransitionTime = lastTransitionTime;
      return this;
   }

   public boolean hasLastTransitionTime() {
      return this.lastTransitionTime != null;
   }

   public String getMessage() {
      return this.message;
   }

   public APIServiceConditionFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getReason() {
      return this.reason;
   }

   public APIServiceConditionFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public String getStatus() {
      return this.status;
   }

   public APIServiceConditionFluent withStatus(String status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public String getType() {
      return this.type;
   }

   public APIServiceConditionFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public APIServiceConditionFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public APIServiceConditionFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public APIServiceConditionFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public APIServiceConditionFluent removeFromAdditionalProperties(Map map) {
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

   public APIServiceConditionFluent withAdditionalProperties(Map additionalProperties) {
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
            APIServiceConditionFluent that = (APIServiceConditionFluent)o;
            if (!Objects.equals(this.lastTransitionTime, that.lastTransitionTime)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.lastTransitionTime, this.message, this.reason, this.status, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.lastTransitionTime != null) {
         sb.append("lastTransitionTime:");
         sb.append(this.lastTransitionTime + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
