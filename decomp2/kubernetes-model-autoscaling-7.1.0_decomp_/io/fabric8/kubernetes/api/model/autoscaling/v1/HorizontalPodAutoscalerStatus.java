package io.fabric8.kubernetes.api.model.autoscaling.v1;

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
@JsonPropertyOrder({"currentCPUUtilizationPercentage", "currentReplicas", "desiredReplicas", "lastScaleTime", "observedGeneration"})
public class HorizontalPodAutoscalerStatus implements Editable, KubernetesResource {
   @JsonProperty("currentCPUUtilizationPercentage")
   private Integer currentCPUUtilizationPercentage;
   @JsonProperty("currentReplicas")
   private Integer currentReplicas;
   @JsonProperty("desiredReplicas")
   private Integer desiredReplicas;
   @JsonProperty("lastScaleTime")
   private String lastScaleTime;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HorizontalPodAutoscalerStatus() {
   }

   public HorizontalPodAutoscalerStatus(Integer currentCPUUtilizationPercentage, Integer currentReplicas, Integer desiredReplicas, String lastScaleTime, Long observedGeneration) {
      this.currentCPUUtilizationPercentage = currentCPUUtilizationPercentage;
      this.currentReplicas = currentReplicas;
      this.desiredReplicas = desiredReplicas;
      this.lastScaleTime = lastScaleTime;
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("currentCPUUtilizationPercentage")
   public Integer getCurrentCPUUtilizationPercentage() {
      return this.currentCPUUtilizationPercentage;
   }

   @JsonProperty("currentCPUUtilizationPercentage")
   public void setCurrentCPUUtilizationPercentage(Integer currentCPUUtilizationPercentage) {
      this.currentCPUUtilizationPercentage = currentCPUUtilizationPercentage;
   }

   @JsonProperty("currentReplicas")
   public Integer getCurrentReplicas() {
      return this.currentReplicas;
   }

   @JsonProperty("currentReplicas")
   public void setCurrentReplicas(Integer currentReplicas) {
      this.currentReplicas = currentReplicas;
   }

   @JsonProperty("desiredReplicas")
   public Integer getDesiredReplicas() {
      return this.desiredReplicas;
   }

   @JsonProperty("desiredReplicas")
   public void setDesiredReplicas(Integer desiredReplicas) {
      this.desiredReplicas = desiredReplicas;
   }

   @JsonProperty("lastScaleTime")
   public String getLastScaleTime() {
      return this.lastScaleTime;
   }

   @JsonProperty("lastScaleTime")
   public void setLastScaleTime(String lastScaleTime) {
      this.lastScaleTime = lastScaleTime;
   }

   @JsonProperty("observedGeneration")
   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   @JsonProperty("observedGeneration")
   public void setObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
   }

   @JsonIgnore
   public HorizontalPodAutoscalerStatusBuilder edit() {
      return new HorizontalPodAutoscalerStatusBuilder(this);
   }

   @JsonIgnore
   public HorizontalPodAutoscalerStatusBuilder toBuilder() {
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
      Integer var10000 = this.getCurrentCPUUtilizationPercentage();
      return "HorizontalPodAutoscalerStatus(currentCPUUtilizationPercentage=" + var10000 + ", currentReplicas=" + this.getCurrentReplicas() + ", desiredReplicas=" + this.getDesiredReplicas() + ", lastScaleTime=" + this.getLastScaleTime() + ", observedGeneration=" + this.getObservedGeneration() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HorizontalPodAutoscalerStatus)) {
         return false;
      } else {
         HorizontalPodAutoscalerStatus other = (HorizontalPodAutoscalerStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$currentCPUUtilizationPercentage = this.getCurrentCPUUtilizationPercentage();
            Object other$currentCPUUtilizationPercentage = other.getCurrentCPUUtilizationPercentage();
            if (this$currentCPUUtilizationPercentage == null) {
               if (other$currentCPUUtilizationPercentage != null) {
                  return false;
               }
            } else if (!this$currentCPUUtilizationPercentage.equals(other$currentCPUUtilizationPercentage)) {
               return false;
            }

            Object this$currentReplicas = this.getCurrentReplicas();
            Object other$currentReplicas = other.getCurrentReplicas();
            if (this$currentReplicas == null) {
               if (other$currentReplicas != null) {
                  return false;
               }
            } else if (!this$currentReplicas.equals(other$currentReplicas)) {
               return false;
            }

            Object this$desiredReplicas = this.getDesiredReplicas();
            Object other$desiredReplicas = other.getDesiredReplicas();
            if (this$desiredReplicas == null) {
               if (other$desiredReplicas != null) {
                  return false;
               }
            } else if (!this$desiredReplicas.equals(other$desiredReplicas)) {
               return false;
            }

            Object this$observedGeneration = this.getObservedGeneration();
            Object other$observedGeneration = other.getObservedGeneration();
            if (this$observedGeneration == null) {
               if (other$observedGeneration != null) {
                  return false;
               }
            } else if (!this$observedGeneration.equals(other$observedGeneration)) {
               return false;
            }

            Object this$lastScaleTime = this.getLastScaleTime();
            Object other$lastScaleTime = other.getLastScaleTime();
            if (this$lastScaleTime == null) {
               if (other$lastScaleTime != null) {
                  return false;
               }
            } else if (!this$lastScaleTime.equals(other$lastScaleTime)) {
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
      return other instanceof HorizontalPodAutoscalerStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $currentCPUUtilizationPercentage = this.getCurrentCPUUtilizationPercentage();
      result = result * 59 + ($currentCPUUtilizationPercentage == null ? 43 : $currentCPUUtilizationPercentage.hashCode());
      Object $currentReplicas = this.getCurrentReplicas();
      result = result * 59 + ($currentReplicas == null ? 43 : $currentReplicas.hashCode());
      Object $desiredReplicas = this.getDesiredReplicas();
      result = result * 59 + ($desiredReplicas == null ? 43 : $desiredReplicas.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $lastScaleTime = this.getLastScaleTime();
      result = result * 59 + ($lastScaleTime == null ? 43 : $lastScaleTime.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
