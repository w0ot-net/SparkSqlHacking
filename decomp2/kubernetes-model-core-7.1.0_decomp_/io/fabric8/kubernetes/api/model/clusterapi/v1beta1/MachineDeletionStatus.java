package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

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
@JsonPropertyOrder({"nodeDrainStartTime", "waitForNodeVolumeDetachStartTime"})
public class MachineDeletionStatus implements Editable, KubernetesResource {
   @JsonProperty("nodeDrainStartTime")
   private String nodeDrainStartTime;
   @JsonProperty("waitForNodeVolumeDetachStartTime")
   private String waitForNodeVolumeDetachStartTime;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MachineDeletionStatus() {
   }

   public MachineDeletionStatus(String nodeDrainStartTime, String waitForNodeVolumeDetachStartTime) {
      this.nodeDrainStartTime = nodeDrainStartTime;
      this.waitForNodeVolumeDetachStartTime = waitForNodeVolumeDetachStartTime;
   }

   @JsonProperty("nodeDrainStartTime")
   public String getNodeDrainStartTime() {
      return this.nodeDrainStartTime;
   }

   @JsonProperty("nodeDrainStartTime")
   public void setNodeDrainStartTime(String nodeDrainStartTime) {
      this.nodeDrainStartTime = nodeDrainStartTime;
   }

   @JsonProperty("waitForNodeVolumeDetachStartTime")
   public String getWaitForNodeVolumeDetachStartTime() {
      return this.waitForNodeVolumeDetachStartTime;
   }

   @JsonProperty("waitForNodeVolumeDetachStartTime")
   public void setWaitForNodeVolumeDetachStartTime(String waitForNodeVolumeDetachStartTime) {
      this.waitForNodeVolumeDetachStartTime = waitForNodeVolumeDetachStartTime;
   }

   @JsonIgnore
   public MachineDeletionStatusBuilder edit() {
      return new MachineDeletionStatusBuilder(this);
   }

   @JsonIgnore
   public MachineDeletionStatusBuilder toBuilder() {
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
      String var10000 = this.getNodeDrainStartTime();
      return "MachineDeletionStatus(nodeDrainStartTime=" + var10000 + ", waitForNodeVolumeDetachStartTime=" + this.getWaitForNodeVolumeDetachStartTime() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MachineDeletionStatus)) {
         return false;
      } else {
         MachineDeletionStatus other = (MachineDeletionStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodeDrainStartTime = this.getNodeDrainStartTime();
            Object other$nodeDrainStartTime = other.getNodeDrainStartTime();
            if (this$nodeDrainStartTime == null) {
               if (other$nodeDrainStartTime != null) {
                  return false;
               }
            } else if (!this$nodeDrainStartTime.equals(other$nodeDrainStartTime)) {
               return false;
            }

            Object this$waitForNodeVolumeDetachStartTime = this.getWaitForNodeVolumeDetachStartTime();
            Object other$waitForNodeVolumeDetachStartTime = other.getWaitForNodeVolumeDetachStartTime();
            if (this$waitForNodeVolumeDetachStartTime == null) {
               if (other$waitForNodeVolumeDetachStartTime != null) {
                  return false;
               }
            } else if (!this$waitForNodeVolumeDetachStartTime.equals(other$waitForNodeVolumeDetachStartTime)) {
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
      return other instanceof MachineDeletionStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodeDrainStartTime = this.getNodeDrainStartTime();
      result = result * 59 + ($nodeDrainStartTime == null ? 43 : $nodeDrainStartTime.hashCode());
      Object $waitForNodeVolumeDetachStartTime = this.getWaitForNodeVolumeDetachStartTime();
      result = result * 59 + ($waitForNodeVolumeDetachStartTime == null ? 43 : $waitForNodeVolumeDetachStartTime.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
