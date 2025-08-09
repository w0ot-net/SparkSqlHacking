package io.fabric8.kubernetes.api.model.autoscaling.v2beta1;

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
@JsonPropertyOrder({"lastTransitionTime", "message", "reason", "status", "type"})
public class HorizontalPodAutoscalerCondition implements Editable, KubernetesResource {
   @JsonProperty("lastTransitionTime")
   private String lastTransitionTime;
   @JsonProperty("message")
   private String message;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("status")
   private String status;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HorizontalPodAutoscalerCondition() {
   }

   public HorizontalPodAutoscalerCondition(String lastTransitionTime, String message, String reason, String status, String type) {
      this.lastTransitionTime = lastTransitionTime;
      this.message = message;
      this.reason = reason;
      this.status = status;
      this.type = type;
   }

   @JsonProperty("lastTransitionTime")
   public String getLastTransitionTime() {
      return this.lastTransitionTime;
   }

   @JsonProperty("lastTransitionTime")
   public void setLastTransitionTime(String lastTransitionTime) {
      this.lastTransitionTime = lastTransitionTime;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonProperty("status")
   public String getStatus() {
      return this.status;
   }

   @JsonProperty("status")
   public void setStatus(String status) {
      this.status = status;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public HorizontalPodAutoscalerConditionBuilder edit() {
      return new HorizontalPodAutoscalerConditionBuilder(this);
   }

   @JsonIgnore
   public HorizontalPodAutoscalerConditionBuilder toBuilder() {
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
      String var10000 = this.getLastTransitionTime();
      return "HorizontalPodAutoscalerCondition(lastTransitionTime=" + var10000 + ", message=" + this.getMessage() + ", reason=" + this.getReason() + ", status=" + this.getStatus() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HorizontalPodAutoscalerCondition)) {
         return false;
      } else {
         HorizontalPodAutoscalerCondition other = (HorizontalPodAutoscalerCondition)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$lastTransitionTime = this.getLastTransitionTime();
            Object other$lastTransitionTime = other.getLastTransitionTime();
            if (this$lastTransitionTime == null) {
               if (other$lastTransitionTime != null) {
                  return false;
               }
            } else if (!this$lastTransitionTime.equals(other$lastTransitionTime)) {
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

            Object this$reason = this.getReason();
            Object other$reason = other.getReason();
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
               return false;
            }

            Object this$status = this.getStatus();
            Object other$status = other.getStatus();
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof HorizontalPodAutoscalerCondition;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $lastTransitionTime = this.getLastTransitionTime();
      result = result * 59 + ($lastTransitionTime == null ? 43 : $lastTransitionTime.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $status = this.getStatus();
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
