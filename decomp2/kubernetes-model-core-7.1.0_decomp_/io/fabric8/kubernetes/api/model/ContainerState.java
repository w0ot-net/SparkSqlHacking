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
@JsonPropertyOrder({"running", "terminated", "waiting"})
public class ContainerState implements Editable, KubernetesResource {
   @JsonProperty("running")
   private ContainerStateRunning running;
   @JsonProperty("terminated")
   private ContainerStateTerminated terminated;
   @JsonProperty("waiting")
   private ContainerStateWaiting waiting;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerState() {
   }

   public ContainerState(ContainerStateRunning running, ContainerStateTerminated terminated, ContainerStateWaiting waiting) {
      this.running = running;
      this.terminated = terminated;
      this.waiting = waiting;
   }

   @JsonProperty("running")
   public ContainerStateRunning getRunning() {
      return this.running;
   }

   @JsonProperty("running")
   public void setRunning(ContainerStateRunning running) {
      this.running = running;
   }

   @JsonProperty("terminated")
   public ContainerStateTerminated getTerminated() {
      return this.terminated;
   }

   @JsonProperty("terminated")
   public void setTerminated(ContainerStateTerminated terminated) {
      this.terminated = terminated;
   }

   @JsonProperty("waiting")
   public ContainerStateWaiting getWaiting() {
      return this.waiting;
   }

   @JsonProperty("waiting")
   public void setWaiting(ContainerStateWaiting waiting) {
      this.waiting = waiting;
   }

   @JsonIgnore
   public ContainerStateBuilder edit() {
      return new ContainerStateBuilder(this);
   }

   @JsonIgnore
   public ContainerStateBuilder toBuilder() {
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
      ContainerStateRunning var10000 = this.getRunning();
      return "ContainerState(running=" + var10000 + ", terminated=" + this.getTerminated() + ", waiting=" + this.getWaiting() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerState)) {
         return false;
      } else {
         ContainerState other = (ContainerState)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$running = this.getRunning();
            Object other$running = other.getRunning();
            if (this$running == null) {
               if (other$running != null) {
                  return false;
               }
            } else if (!this$running.equals(other$running)) {
               return false;
            }

            Object this$terminated = this.getTerminated();
            Object other$terminated = other.getTerminated();
            if (this$terminated == null) {
               if (other$terminated != null) {
                  return false;
               }
            } else if (!this$terminated.equals(other$terminated)) {
               return false;
            }

            Object this$waiting = this.getWaiting();
            Object other$waiting = other.getWaiting();
            if (this$waiting == null) {
               if (other$waiting != null) {
                  return false;
               }
            } else if (!this$waiting.equals(other$waiting)) {
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
      return other instanceof ContainerState;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $running = this.getRunning();
      result = result * 59 + ($running == null ? 43 : $running.hashCode());
      Object $terminated = this.getTerminated();
      result = result * 59 + ($terminated == null ? 43 : $terminated.hashCode());
      Object $waiting = this.getWaiting();
      result = result * 59 + ($waiting == null ? 43 : $waiting.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
