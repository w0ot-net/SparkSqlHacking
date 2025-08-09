package io.fabric8.kubernetes.api.model.extensions;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"collisionCount", "conditions", "currentNumberScheduled", "desiredNumberScheduled", "numberAvailable", "numberMisscheduled", "numberReady", "numberUnavailable", "observedGeneration", "updatedNumberScheduled"})
public class DaemonSetStatus implements Editable, KubernetesResource {
   @JsonProperty("collisionCount")
   private Integer collisionCount;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("currentNumberScheduled")
   private Integer currentNumberScheduled;
   @JsonProperty("desiredNumberScheduled")
   private Integer desiredNumberScheduled;
   @JsonProperty("numberAvailable")
   private Integer numberAvailable;
   @JsonProperty("numberMisscheduled")
   private Integer numberMisscheduled;
   @JsonProperty("numberReady")
   private Integer numberReady;
   @JsonProperty("numberUnavailable")
   private Integer numberUnavailable;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonProperty("updatedNumberScheduled")
   private Integer updatedNumberScheduled;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DaemonSetStatus() {
   }

   public DaemonSetStatus(Integer collisionCount, List conditions, Integer currentNumberScheduled, Integer desiredNumberScheduled, Integer numberAvailable, Integer numberMisscheduled, Integer numberReady, Integer numberUnavailable, Long observedGeneration, Integer updatedNumberScheduled) {
      this.collisionCount = collisionCount;
      this.conditions = conditions;
      this.currentNumberScheduled = currentNumberScheduled;
      this.desiredNumberScheduled = desiredNumberScheduled;
      this.numberAvailable = numberAvailable;
      this.numberMisscheduled = numberMisscheduled;
      this.numberReady = numberReady;
      this.numberUnavailable = numberUnavailable;
      this.observedGeneration = observedGeneration;
      this.updatedNumberScheduled = updatedNumberScheduled;
   }

   @JsonProperty("collisionCount")
   public Integer getCollisionCount() {
      return this.collisionCount;
   }

   @JsonProperty("collisionCount")
   public void setCollisionCount(Integer collisionCount) {
      this.collisionCount = collisionCount;
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

   @JsonProperty("currentNumberScheduled")
   public Integer getCurrentNumberScheduled() {
      return this.currentNumberScheduled;
   }

   @JsonProperty("currentNumberScheduled")
   public void setCurrentNumberScheduled(Integer currentNumberScheduled) {
      this.currentNumberScheduled = currentNumberScheduled;
   }

   @JsonProperty("desiredNumberScheduled")
   public Integer getDesiredNumberScheduled() {
      return this.desiredNumberScheduled;
   }

   @JsonProperty("desiredNumberScheduled")
   public void setDesiredNumberScheduled(Integer desiredNumberScheduled) {
      this.desiredNumberScheduled = desiredNumberScheduled;
   }

   @JsonProperty("numberAvailable")
   public Integer getNumberAvailable() {
      return this.numberAvailable;
   }

   @JsonProperty("numberAvailable")
   public void setNumberAvailable(Integer numberAvailable) {
      this.numberAvailable = numberAvailable;
   }

   @JsonProperty("numberMisscheduled")
   public Integer getNumberMisscheduled() {
      return this.numberMisscheduled;
   }

   @JsonProperty("numberMisscheduled")
   public void setNumberMisscheduled(Integer numberMisscheduled) {
      this.numberMisscheduled = numberMisscheduled;
   }

   @JsonProperty("numberReady")
   public Integer getNumberReady() {
      return this.numberReady;
   }

   @JsonProperty("numberReady")
   public void setNumberReady(Integer numberReady) {
      this.numberReady = numberReady;
   }

   @JsonProperty("numberUnavailable")
   public Integer getNumberUnavailable() {
      return this.numberUnavailable;
   }

   @JsonProperty("numberUnavailable")
   public void setNumberUnavailable(Integer numberUnavailable) {
      this.numberUnavailable = numberUnavailable;
   }

   @JsonProperty("observedGeneration")
   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   @JsonProperty("observedGeneration")
   public void setObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("updatedNumberScheduled")
   public Integer getUpdatedNumberScheduled() {
      return this.updatedNumberScheduled;
   }

   @JsonProperty("updatedNumberScheduled")
   public void setUpdatedNumberScheduled(Integer updatedNumberScheduled) {
      this.updatedNumberScheduled = updatedNumberScheduled;
   }

   @JsonIgnore
   public DaemonSetStatusBuilder edit() {
      return new DaemonSetStatusBuilder(this);
   }

   @JsonIgnore
   public DaemonSetStatusBuilder toBuilder() {
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
      Integer var10000 = this.getCollisionCount();
      return "DaemonSetStatus(collisionCount=" + var10000 + ", conditions=" + this.getConditions() + ", currentNumberScheduled=" + this.getCurrentNumberScheduled() + ", desiredNumberScheduled=" + this.getDesiredNumberScheduled() + ", numberAvailable=" + this.getNumberAvailable() + ", numberMisscheduled=" + this.getNumberMisscheduled() + ", numberReady=" + this.getNumberReady() + ", numberUnavailable=" + this.getNumberUnavailable() + ", observedGeneration=" + this.getObservedGeneration() + ", updatedNumberScheduled=" + this.getUpdatedNumberScheduled() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DaemonSetStatus)) {
         return false;
      } else {
         DaemonSetStatus other = (DaemonSetStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$collisionCount = this.getCollisionCount();
            Object other$collisionCount = other.getCollisionCount();
            if (this$collisionCount == null) {
               if (other$collisionCount != null) {
                  return false;
               }
            } else if (!this$collisionCount.equals(other$collisionCount)) {
               return false;
            }

            Object this$currentNumberScheduled = this.getCurrentNumberScheduled();
            Object other$currentNumberScheduled = other.getCurrentNumberScheduled();
            if (this$currentNumberScheduled == null) {
               if (other$currentNumberScheduled != null) {
                  return false;
               }
            } else if (!this$currentNumberScheduled.equals(other$currentNumberScheduled)) {
               return false;
            }

            Object this$desiredNumberScheduled = this.getDesiredNumberScheduled();
            Object other$desiredNumberScheduled = other.getDesiredNumberScheduled();
            if (this$desiredNumberScheduled == null) {
               if (other$desiredNumberScheduled != null) {
                  return false;
               }
            } else if (!this$desiredNumberScheduled.equals(other$desiredNumberScheduled)) {
               return false;
            }

            Object this$numberAvailable = this.getNumberAvailable();
            Object other$numberAvailable = other.getNumberAvailable();
            if (this$numberAvailable == null) {
               if (other$numberAvailable != null) {
                  return false;
               }
            } else if (!this$numberAvailable.equals(other$numberAvailable)) {
               return false;
            }

            Object this$numberMisscheduled = this.getNumberMisscheduled();
            Object other$numberMisscheduled = other.getNumberMisscheduled();
            if (this$numberMisscheduled == null) {
               if (other$numberMisscheduled != null) {
                  return false;
               }
            } else if (!this$numberMisscheduled.equals(other$numberMisscheduled)) {
               return false;
            }

            Object this$numberReady = this.getNumberReady();
            Object other$numberReady = other.getNumberReady();
            if (this$numberReady == null) {
               if (other$numberReady != null) {
                  return false;
               }
            } else if (!this$numberReady.equals(other$numberReady)) {
               return false;
            }

            Object this$numberUnavailable = this.getNumberUnavailable();
            Object other$numberUnavailable = other.getNumberUnavailable();
            if (this$numberUnavailable == null) {
               if (other$numberUnavailable != null) {
                  return false;
               }
            } else if (!this$numberUnavailable.equals(other$numberUnavailable)) {
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

            Object this$updatedNumberScheduled = this.getUpdatedNumberScheduled();
            Object other$updatedNumberScheduled = other.getUpdatedNumberScheduled();
            if (this$updatedNumberScheduled == null) {
               if (other$updatedNumberScheduled != null) {
                  return false;
               }
            } else if (!this$updatedNumberScheduled.equals(other$updatedNumberScheduled)) {
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
      return other instanceof DaemonSetStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $collisionCount = this.getCollisionCount();
      result = result * 59 + ($collisionCount == null ? 43 : $collisionCount.hashCode());
      Object $currentNumberScheduled = this.getCurrentNumberScheduled();
      result = result * 59 + ($currentNumberScheduled == null ? 43 : $currentNumberScheduled.hashCode());
      Object $desiredNumberScheduled = this.getDesiredNumberScheduled();
      result = result * 59 + ($desiredNumberScheduled == null ? 43 : $desiredNumberScheduled.hashCode());
      Object $numberAvailable = this.getNumberAvailable();
      result = result * 59 + ($numberAvailable == null ? 43 : $numberAvailable.hashCode());
      Object $numberMisscheduled = this.getNumberMisscheduled();
      result = result * 59 + ($numberMisscheduled == null ? 43 : $numberMisscheduled.hashCode());
      Object $numberReady = this.getNumberReady();
      result = result * 59 + ($numberReady == null ? 43 : $numberReady.hashCode());
      Object $numberUnavailable = this.getNumberUnavailable();
      result = result * 59 + ($numberUnavailable == null ? 43 : $numberUnavailable.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $updatedNumberScheduled = this.getUpdatedNumberScheduled();
      result = result * 59 + ($updatedNumberScheduled == null ? 43 : $updatedNumberScheduled.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
