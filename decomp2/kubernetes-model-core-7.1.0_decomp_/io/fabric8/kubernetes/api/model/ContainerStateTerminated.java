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
@JsonPropertyOrder({"containerID", "exitCode", "finishedAt", "message", "reason", "signal", "startedAt"})
public class ContainerStateTerminated implements Editable, KubernetesResource {
   @JsonProperty("containerID")
   private String containerID;
   @JsonProperty("exitCode")
   private Integer exitCode;
   @JsonProperty("finishedAt")
   private String finishedAt;
   @JsonProperty("message")
   private String message;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("signal")
   private Integer signal;
   @JsonProperty("startedAt")
   private String startedAt;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerStateTerminated() {
   }

   public ContainerStateTerminated(String containerID, Integer exitCode, String finishedAt, String message, String reason, Integer signal, String startedAt) {
      this.containerID = containerID;
      this.exitCode = exitCode;
      this.finishedAt = finishedAt;
      this.message = message;
      this.reason = reason;
      this.signal = signal;
      this.startedAt = startedAt;
   }

   @JsonProperty("containerID")
   public String getContainerID() {
      return this.containerID;
   }

   @JsonProperty("containerID")
   public void setContainerID(String containerID) {
      this.containerID = containerID;
   }

   @JsonProperty("exitCode")
   public Integer getExitCode() {
      return this.exitCode;
   }

   @JsonProperty("exitCode")
   public void setExitCode(Integer exitCode) {
      this.exitCode = exitCode;
   }

   @JsonProperty("finishedAt")
   public String getFinishedAt() {
      return this.finishedAt;
   }

   @JsonProperty("finishedAt")
   public void setFinishedAt(String finishedAt) {
      this.finishedAt = finishedAt;
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

   @JsonProperty("signal")
   public Integer getSignal() {
      return this.signal;
   }

   @JsonProperty("signal")
   public void setSignal(Integer signal) {
      this.signal = signal;
   }

   @JsonProperty("startedAt")
   public String getStartedAt() {
      return this.startedAt;
   }

   @JsonProperty("startedAt")
   public void setStartedAt(String startedAt) {
      this.startedAt = startedAt;
   }

   @JsonIgnore
   public ContainerStateTerminatedBuilder edit() {
      return new ContainerStateTerminatedBuilder(this);
   }

   @JsonIgnore
   public ContainerStateTerminatedBuilder toBuilder() {
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
      String var10000 = this.getContainerID();
      return "ContainerStateTerminated(containerID=" + var10000 + ", exitCode=" + this.getExitCode() + ", finishedAt=" + this.getFinishedAt() + ", message=" + this.getMessage() + ", reason=" + this.getReason() + ", signal=" + this.getSignal() + ", startedAt=" + this.getStartedAt() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerStateTerminated)) {
         return false;
      } else {
         ContainerStateTerminated other = (ContainerStateTerminated)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$exitCode = this.getExitCode();
            Object other$exitCode = other.getExitCode();
            if (this$exitCode == null) {
               if (other$exitCode != null) {
                  return false;
               }
            } else if (!this$exitCode.equals(other$exitCode)) {
               return false;
            }

            Object this$signal = this.getSignal();
            Object other$signal = other.getSignal();
            if (this$signal == null) {
               if (other$signal != null) {
                  return false;
               }
            } else if (!this$signal.equals(other$signal)) {
               return false;
            }

            Object this$containerID = this.getContainerID();
            Object other$containerID = other.getContainerID();
            if (this$containerID == null) {
               if (other$containerID != null) {
                  return false;
               }
            } else if (!this$containerID.equals(other$containerID)) {
               return false;
            }

            Object this$finishedAt = this.getFinishedAt();
            Object other$finishedAt = other.getFinishedAt();
            if (this$finishedAt == null) {
               if (other$finishedAt != null) {
                  return false;
               }
            } else if (!this$finishedAt.equals(other$finishedAt)) {
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

            Object this$startedAt = this.getStartedAt();
            Object other$startedAt = other.getStartedAt();
            if (this$startedAt == null) {
               if (other$startedAt != null) {
                  return false;
               }
            } else if (!this$startedAt.equals(other$startedAt)) {
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
      return other instanceof ContainerStateTerminated;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $exitCode = this.getExitCode();
      result = result * 59 + ($exitCode == null ? 43 : $exitCode.hashCode());
      Object $signal = this.getSignal();
      result = result * 59 + ($signal == null ? 43 : $signal.hashCode());
      Object $containerID = this.getContainerID();
      result = result * 59 + ($containerID == null ? 43 : $containerID.hashCode());
      Object $finishedAt = this.getFinishedAt();
      result = result * 59 + ($finishedAt == null ? 43 : $finishedAt.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $startedAt = this.getStartedAt();
      result = result * 59 + ($startedAt == null ? 43 : $startedAt.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
