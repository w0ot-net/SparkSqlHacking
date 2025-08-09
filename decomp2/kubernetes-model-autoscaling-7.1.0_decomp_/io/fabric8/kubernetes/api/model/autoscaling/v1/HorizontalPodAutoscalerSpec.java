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
@JsonPropertyOrder({"maxReplicas", "minReplicas", "scaleTargetRef", "targetCPUUtilizationPercentage"})
public class HorizontalPodAutoscalerSpec implements Editable, KubernetesResource {
   @JsonProperty("maxReplicas")
   private Integer maxReplicas;
   @JsonProperty("minReplicas")
   private Integer minReplicas;
   @JsonProperty("scaleTargetRef")
   private CrossVersionObjectReference scaleTargetRef;
   @JsonProperty("targetCPUUtilizationPercentage")
   private Integer targetCPUUtilizationPercentage;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HorizontalPodAutoscalerSpec() {
   }

   public HorizontalPodAutoscalerSpec(Integer maxReplicas, Integer minReplicas, CrossVersionObjectReference scaleTargetRef, Integer targetCPUUtilizationPercentage) {
      this.maxReplicas = maxReplicas;
      this.minReplicas = minReplicas;
      this.scaleTargetRef = scaleTargetRef;
      this.targetCPUUtilizationPercentage = targetCPUUtilizationPercentage;
   }

   @JsonProperty("maxReplicas")
   public Integer getMaxReplicas() {
      return this.maxReplicas;
   }

   @JsonProperty("maxReplicas")
   public void setMaxReplicas(Integer maxReplicas) {
      this.maxReplicas = maxReplicas;
   }

   @JsonProperty("minReplicas")
   public Integer getMinReplicas() {
      return this.minReplicas;
   }

   @JsonProperty("minReplicas")
   public void setMinReplicas(Integer minReplicas) {
      this.minReplicas = minReplicas;
   }

   @JsonProperty("scaleTargetRef")
   public CrossVersionObjectReference getScaleTargetRef() {
      return this.scaleTargetRef;
   }

   @JsonProperty("scaleTargetRef")
   public void setScaleTargetRef(CrossVersionObjectReference scaleTargetRef) {
      this.scaleTargetRef = scaleTargetRef;
   }

   @JsonProperty("targetCPUUtilizationPercentage")
   public Integer getTargetCPUUtilizationPercentage() {
      return this.targetCPUUtilizationPercentage;
   }

   @JsonProperty("targetCPUUtilizationPercentage")
   public void setTargetCPUUtilizationPercentage(Integer targetCPUUtilizationPercentage) {
      this.targetCPUUtilizationPercentage = targetCPUUtilizationPercentage;
   }

   @JsonIgnore
   public HorizontalPodAutoscalerSpecBuilder edit() {
      return new HorizontalPodAutoscalerSpecBuilder(this);
   }

   @JsonIgnore
   public HorizontalPodAutoscalerSpecBuilder toBuilder() {
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
      Integer var10000 = this.getMaxReplicas();
      return "HorizontalPodAutoscalerSpec(maxReplicas=" + var10000 + ", minReplicas=" + this.getMinReplicas() + ", scaleTargetRef=" + this.getScaleTargetRef() + ", targetCPUUtilizationPercentage=" + this.getTargetCPUUtilizationPercentage() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HorizontalPodAutoscalerSpec)) {
         return false;
      } else {
         HorizontalPodAutoscalerSpec other = (HorizontalPodAutoscalerSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$maxReplicas = this.getMaxReplicas();
            Object other$maxReplicas = other.getMaxReplicas();
            if (this$maxReplicas == null) {
               if (other$maxReplicas != null) {
                  return false;
               }
            } else if (!this$maxReplicas.equals(other$maxReplicas)) {
               return false;
            }

            Object this$minReplicas = this.getMinReplicas();
            Object other$minReplicas = other.getMinReplicas();
            if (this$minReplicas == null) {
               if (other$minReplicas != null) {
                  return false;
               }
            } else if (!this$minReplicas.equals(other$minReplicas)) {
               return false;
            }

            Object this$targetCPUUtilizationPercentage = this.getTargetCPUUtilizationPercentage();
            Object other$targetCPUUtilizationPercentage = other.getTargetCPUUtilizationPercentage();
            if (this$targetCPUUtilizationPercentage == null) {
               if (other$targetCPUUtilizationPercentage != null) {
                  return false;
               }
            } else if (!this$targetCPUUtilizationPercentage.equals(other$targetCPUUtilizationPercentage)) {
               return false;
            }

            Object this$scaleTargetRef = this.getScaleTargetRef();
            Object other$scaleTargetRef = other.getScaleTargetRef();
            if (this$scaleTargetRef == null) {
               if (other$scaleTargetRef != null) {
                  return false;
               }
            } else if (!this$scaleTargetRef.equals(other$scaleTargetRef)) {
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
      return other instanceof HorizontalPodAutoscalerSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxReplicas = this.getMaxReplicas();
      result = result * 59 + ($maxReplicas == null ? 43 : $maxReplicas.hashCode());
      Object $minReplicas = this.getMinReplicas();
      result = result * 59 + ($minReplicas == null ? 43 : $minReplicas.hashCode());
      Object $targetCPUUtilizationPercentage = this.getTargetCPUUtilizationPercentage();
      result = result * 59 + ($targetCPUUtilizationPercentage == null ? 43 : $targetCPUUtilizationPercentage.hashCode());
      Object $scaleTargetRef = this.getScaleTargetRef();
      result = result * 59 + ($scaleTargetRef == null ? 43 : $scaleTargetRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
