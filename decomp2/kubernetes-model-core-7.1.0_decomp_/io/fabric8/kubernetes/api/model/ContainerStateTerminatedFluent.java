package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ContainerStateTerminatedFluent extends BaseFluent {
   private String containerID;
   private Integer exitCode;
   private String finishedAt;
   private String message;
   private String reason;
   private Integer signal;
   private String startedAt;
   private Map additionalProperties;

   public ContainerStateTerminatedFluent() {
   }

   public ContainerStateTerminatedFluent(ContainerStateTerminated instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerStateTerminated instance) {
      instance = instance != null ? instance : new ContainerStateTerminated();
      if (instance != null) {
         this.withContainerID(instance.getContainerID());
         this.withExitCode(instance.getExitCode());
         this.withFinishedAt(instance.getFinishedAt());
         this.withMessage(instance.getMessage());
         this.withReason(instance.getReason());
         this.withSignal(instance.getSignal());
         this.withStartedAt(instance.getStartedAt());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getContainerID() {
      return this.containerID;
   }

   public ContainerStateTerminatedFluent withContainerID(String containerID) {
      this.containerID = containerID;
      return this;
   }

   public boolean hasContainerID() {
      return this.containerID != null;
   }

   public Integer getExitCode() {
      return this.exitCode;
   }

   public ContainerStateTerminatedFluent withExitCode(Integer exitCode) {
      this.exitCode = exitCode;
      return this;
   }

   public boolean hasExitCode() {
      return this.exitCode != null;
   }

   public String getFinishedAt() {
      return this.finishedAt;
   }

   public ContainerStateTerminatedFluent withFinishedAt(String finishedAt) {
      this.finishedAt = finishedAt;
      return this;
   }

   public boolean hasFinishedAt() {
      return this.finishedAt != null;
   }

   public String getMessage() {
      return this.message;
   }

   public ContainerStateTerminatedFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getReason() {
      return this.reason;
   }

   public ContainerStateTerminatedFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public Integer getSignal() {
      return this.signal;
   }

   public ContainerStateTerminatedFluent withSignal(Integer signal) {
      this.signal = signal;
      return this;
   }

   public boolean hasSignal() {
      return this.signal != null;
   }

   public String getStartedAt() {
      return this.startedAt;
   }

   public ContainerStateTerminatedFluent withStartedAt(String startedAt) {
      this.startedAt = startedAt;
      return this;
   }

   public boolean hasStartedAt() {
      return this.startedAt != null;
   }

   public ContainerStateTerminatedFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerStateTerminatedFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerStateTerminatedFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerStateTerminatedFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerStateTerminatedFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerStateTerminatedFluent that = (ContainerStateTerminatedFluent)o;
            if (!Objects.equals(this.containerID, that.containerID)) {
               return false;
            } else if (!Objects.equals(this.exitCode, that.exitCode)) {
               return false;
            } else if (!Objects.equals(this.finishedAt, that.finishedAt)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.signal, that.signal)) {
               return false;
            } else if (!Objects.equals(this.startedAt, that.startedAt)) {
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
      return Objects.hash(new Object[]{this.containerID, this.exitCode, this.finishedAt, this.message, this.reason, this.signal, this.startedAt, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.containerID != null) {
         sb.append("containerID:");
         sb.append(this.containerID + ",");
      }

      if (this.exitCode != null) {
         sb.append("exitCode:");
         sb.append(this.exitCode + ",");
      }

      if (this.finishedAt != null) {
         sb.append("finishedAt:");
         sb.append(this.finishedAt + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.signal != null) {
         sb.append("signal:");
         sb.append(this.signal + ",");
      }

      if (this.startedAt != null) {
         sb.append("startedAt:");
         sb.append(this.startedAt + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
