package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

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
@JsonPropertyOrder({"scaleDown", "scaleUp"})
public class HorizontalPodAutoscalerBehavior implements Editable, KubernetesResource {
   @JsonProperty("scaleDown")
   private HPAScalingRules scaleDown;
   @JsonProperty("scaleUp")
   private HPAScalingRules scaleUp;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HorizontalPodAutoscalerBehavior() {
   }

   public HorizontalPodAutoscalerBehavior(HPAScalingRules scaleDown, HPAScalingRules scaleUp) {
      this.scaleDown = scaleDown;
      this.scaleUp = scaleUp;
   }

   @JsonProperty("scaleDown")
   public HPAScalingRules getScaleDown() {
      return this.scaleDown;
   }

   @JsonProperty("scaleDown")
   public void setScaleDown(HPAScalingRules scaleDown) {
      this.scaleDown = scaleDown;
   }

   @JsonProperty("scaleUp")
   public HPAScalingRules getScaleUp() {
      return this.scaleUp;
   }

   @JsonProperty("scaleUp")
   public void setScaleUp(HPAScalingRules scaleUp) {
      this.scaleUp = scaleUp;
   }

   @JsonIgnore
   public HorizontalPodAutoscalerBehaviorBuilder edit() {
      return new HorizontalPodAutoscalerBehaviorBuilder(this);
   }

   @JsonIgnore
   public HorizontalPodAutoscalerBehaviorBuilder toBuilder() {
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
      HPAScalingRules var10000 = this.getScaleDown();
      return "HorizontalPodAutoscalerBehavior(scaleDown=" + var10000 + ", scaleUp=" + this.getScaleUp() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HorizontalPodAutoscalerBehavior)) {
         return false;
      } else {
         HorizontalPodAutoscalerBehavior other = (HorizontalPodAutoscalerBehavior)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$scaleDown = this.getScaleDown();
            Object other$scaleDown = other.getScaleDown();
            if (this$scaleDown == null) {
               if (other$scaleDown != null) {
                  return false;
               }
            } else if (!this$scaleDown.equals(other$scaleDown)) {
               return false;
            }

            Object this$scaleUp = this.getScaleUp();
            Object other$scaleUp = other.getScaleUp();
            if (this$scaleUp == null) {
               if (other$scaleUp != null) {
                  return false;
               }
            } else if (!this$scaleUp.equals(other$scaleUp)) {
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
      return other instanceof HorizontalPodAutoscalerBehavior;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $scaleDown = this.getScaleDown();
      result = result * 59 + ($scaleDown == null ? 43 : $scaleDown.hashCode());
      Object $scaleUp = this.getScaleUp();
      result = result * 59 + ($scaleUp == null ? 43 : $scaleUp.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
