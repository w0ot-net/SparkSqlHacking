package io.fabric8.kubernetes.api.model;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"lastPhaseTransitionTime", "message", "phase", "reason"})
public class PersistentVolumeStatus implements Editable, KubernetesResource {
   @JsonProperty("lastPhaseTransitionTime")
   private String lastPhaseTransitionTime;
   @JsonProperty("message")
   private String message;
   @JsonProperty("phase")
   private String phase;
   @JsonProperty("reason")
   private String reason;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PersistentVolumeStatus() {
   }

   public PersistentVolumeStatus(String lastPhaseTransitionTime, String message, String phase, String reason) {
      this.lastPhaseTransitionTime = lastPhaseTransitionTime;
      this.message = message;
      this.phase = phase;
      this.reason = reason;
   }

   @JsonProperty("lastPhaseTransitionTime")
   public String getLastPhaseTransitionTime() {
      return this.lastPhaseTransitionTime;
   }

   @JsonProperty("lastPhaseTransitionTime")
   public void setLastPhaseTransitionTime(String lastPhaseTransitionTime) {
      this.lastPhaseTransitionTime = lastPhaseTransitionTime;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("phase")
   public String getPhase() {
      return this.phase;
   }

   @JsonProperty("phase")
   public void setPhase(String phase) {
      this.phase = phase;
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
   public PersistentVolumeStatusBuilder edit() {
      return new PersistentVolumeStatusBuilder(this);
   }

   @JsonIgnore
   public PersistentVolumeStatusBuilder toBuilder() {
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
      String var10000 = this.getLastPhaseTransitionTime();
      return "PersistentVolumeStatus(lastPhaseTransitionTime=" + var10000 + ", message=" + this.getMessage() + ", phase=" + this.getPhase() + ", reason=" + this.getReason() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersistentVolumeStatus)) {
         return false;
      } else {
         PersistentVolumeStatus other = (PersistentVolumeStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$lastPhaseTransitionTime = this.getLastPhaseTransitionTime();
            Object other$lastPhaseTransitionTime = other.getLastPhaseTransitionTime();
            if (this$lastPhaseTransitionTime == null) {
               if (other$lastPhaseTransitionTime != null) {
                  return false;
               }
            } else if (!this$lastPhaseTransitionTime.equals(other$lastPhaseTransitionTime)) {
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

            Object this$phase = this.getPhase();
            Object other$phase = other.getPhase();
            if (this$phase == null) {
               if (other$phase != null) {
                  return false;
               }
            } else if (!this$phase.equals(other$phase)) {
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
      return other instanceof PersistentVolumeStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $lastPhaseTransitionTime = this.getLastPhaseTransitionTime();
      result = result * 59 + ($lastPhaseTransitionTime == null ? 43 : $lastPhaseTransitionTime.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $phase = this.getPhase();
      result = result * 59 + ($phase == null ? 43 : $phase.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
