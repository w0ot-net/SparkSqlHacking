package io.fabric8.kubernetes.api.model.policy.v1;

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
import io.fabric8.kubernetes.api.model.Condition;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"conditions", "currentHealthy", "desiredHealthy", "disruptedPods", "disruptionsAllowed", "expectedPods", "observedGeneration"})
public class PodDisruptionBudgetStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("currentHealthy")
   private Integer currentHealthy;
   @JsonProperty("desiredHealthy")
   private Integer desiredHealthy;
   @JsonProperty("disruptedPods")
   @JsonInclude(Include.NON_EMPTY)
   private Map disruptedPods = new LinkedHashMap();
   @JsonProperty("disruptionsAllowed")
   private Integer disruptionsAllowed;
   @JsonProperty("expectedPods")
   private Integer expectedPods;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodDisruptionBudgetStatus() {
   }

   public PodDisruptionBudgetStatus(List conditions, Integer currentHealthy, Integer desiredHealthy, Map disruptedPods, Integer disruptionsAllowed, Integer expectedPods, Long observedGeneration) {
      this.conditions = conditions;
      this.currentHealthy = currentHealthy;
      this.desiredHealthy = desiredHealthy;
      this.disruptedPods = disruptedPods;
      this.disruptionsAllowed = disruptionsAllowed;
      this.expectedPods = expectedPods;
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("currentHealthy")
   public Integer getCurrentHealthy() {
      return this.currentHealthy;
   }

   @JsonProperty("currentHealthy")
   public void setCurrentHealthy(Integer currentHealthy) {
      this.currentHealthy = currentHealthy;
   }

   @JsonProperty("desiredHealthy")
   public Integer getDesiredHealthy() {
      return this.desiredHealthy;
   }

   @JsonProperty("desiredHealthy")
   public void setDesiredHealthy(Integer desiredHealthy) {
      this.desiredHealthy = desiredHealthy;
   }

   @JsonProperty("disruptedPods")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDisruptedPods() {
      return this.disruptedPods;
   }

   @JsonProperty("disruptedPods")
   public void setDisruptedPods(Map disruptedPods) {
      this.disruptedPods = disruptedPods;
   }

   @JsonProperty("disruptionsAllowed")
   public Integer getDisruptionsAllowed() {
      return this.disruptionsAllowed;
   }

   @JsonProperty("disruptionsAllowed")
   public void setDisruptionsAllowed(Integer disruptionsAllowed) {
      this.disruptionsAllowed = disruptionsAllowed;
   }

   @JsonProperty("expectedPods")
   public Integer getExpectedPods() {
      return this.expectedPods;
   }

   @JsonProperty("expectedPods")
   public void setExpectedPods(Integer expectedPods) {
      this.expectedPods = expectedPods;
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
   public PodDisruptionBudgetStatusBuilder edit() {
      return new PodDisruptionBudgetStatusBuilder(this);
   }

   @JsonIgnore
   public PodDisruptionBudgetStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "PodDisruptionBudgetStatus(conditions=" + var10000 + ", currentHealthy=" + this.getCurrentHealthy() + ", desiredHealthy=" + this.getDesiredHealthy() + ", disruptedPods=" + this.getDisruptedPods() + ", disruptionsAllowed=" + this.getDisruptionsAllowed() + ", expectedPods=" + this.getExpectedPods() + ", observedGeneration=" + this.getObservedGeneration() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodDisruptionBudgetStatus)) {
         return false;
      } else {
         PodDisruptionBudgetStatus other = (PodDisruptionBudgetStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$currentHealthy = this.getCurrentHealthy();
            Object other$currentHealthy = other.getCurrentHealthy();
            if (this$currentHealthy == null) {
               if (other$currentHealthy != null) {
                  return false;
               }
            } else if (!this$currentHealthy.equals(other$currentHealthy)) {
               return false;
            }

            Object this$desiredHealthy = this.getDesiredHealthy();
            Object other$desiredHealthy = other.getDesiredHealthy();
            if (this$desiredHealthy == null) {
               if (other$desiredHealthy != null) {
                  return false;
               }
            } else if (!this$desiredHealthy.equals(other$desiredHealthy)) {
               return false;
            }

            Object this$disruptionsAllowed = this.getDisruptionsAllowed();
            Object other$disruptionsAllowed = other.getDisruptionsAllowed();
            if (this$disruptionsAllowed == null) {
               if (other$disruptionsAllowed != null) {
                  return false;
               }
            } else if (!this$disruptionsAllowed.equals(other$disruptionsAllowed)) {
               return false;
            }

            Object this$expectedPods = this.getExpectedPods();
            Object other$expectedPods = other.getExpectedPods();
            if (this$expectedPods == null) {
               if (other$expectedPods != null) {
                  return false;
               }
            } else if (!this$expectedPods.equals(other$expectedPods)) {
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

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$disruptedPods = this.getDisruptedPods();
            Object other$disruptedPods = other.getDisruptedPods();
            if (this$disruptedPods == null) {
               if (other$disruptedPods != null) {
                  return false;
               }
            } else if (!this$disruptedPods.equals(other$disruptedPods)) {
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
      return other instanceof PodDisruptionBudgetStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $currentHealthy = this.getCurrentHealthy();
      result = result * 59 + ($currentHealthy == null ? 43 : $currentHealthy.hashCode());
      Object $desiredHealthy = this.getDesiredHealthy();
      result = result * 59 + ($desiredHealthy == null ? 43 : $desiredHealthy.hashCode());
      Object $disruptionsAllowed = this.getDisruptionsAllowed();
      result = result * 59 + ($disruptionsAllowed == null ? 43 : $disruptionsAllowed.hashCode());
      Object $expectedPods = this.getExpectedPods();
      result = result * 59 + ($expectedPods == null ? 43 : $expectedPods.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $disruptedPods = this.getDisruptedPods();
      result = result * 59 + ($disruptedPods == null ? 43 : $disruptedPods.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
