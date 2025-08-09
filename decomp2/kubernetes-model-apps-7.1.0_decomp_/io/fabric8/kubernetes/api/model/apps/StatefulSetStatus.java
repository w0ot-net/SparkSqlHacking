package io.fabric8.kubernetes.api.model.apps;

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
@JsonPropertyOrder({"availableReplicas", "collisionCount", "conditions", "currentReplicas", "currentRevision", "observedGeneration", "readyReplicas", "replicas", "updateRevision", "updatedReplicas"})
public class StatefulSetStatus implements Editable, KubernetesResource {
   @JsonProperty("availableReplicas")
   private Integer availableReplicas;
   @JsonProperty("collisionCount")
   private Integer collisionCount;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("currentReplicas")
   private Integer currentReplicas;
   @JsonProperty("currentRevision")
   private String currentRevision;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonProperty("readyReplicas")
   private Integer readyReplicas;
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonProperty("updateRevision")
   private String updateRevision;
   @JsonProperty("updatedReplicas")
   private Integer updatedReplicas;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatefulSetStatus() {
   }

   public StatefulSetStatus(Integer availableReplicas, Integer collisionCount, List conditions, Integer currentReplicas, String currentRevision, Long observedGeneration, Integer readyReplicas, Integer replicas, String updateRevision, Integer updatedReplicas) {
      this.availableReplicas = availableReplicas;
      this.collisionCount = collisionCount;
      this.conditions = conditions;
      this.currentReplicas = currentReplicas;
      this.currentRevision = currentRevision;
      this.observedGeneration = observedGeneration;
      this.readyReplicas = readyReplicas;
      this.replicas = replicas;
      this.updateRevision = updateRevision;
      this.updatedReplicas = updatedReplicas;
   }

   @JsonProperty("availableReplicas")
   public Integer getAvailableReplicas() {
      return this.availableReplicas;
   }

   @JsonProperty("availableReplicas")
   public void setAvailableReplicas(Integer availableReplicas) {
      this.availableReplicas = availableReplicas;
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

   @JsonProperty("currentReplicas")
   public Integer getCurrentReplicas() {
      return this.currentReplicas;
   }

   @JsonProperty("currentReplicas")
   public void setCurrentReplicas(Integer currentReplicas) {
      this.currentReplicas = currentReplicas;
   }

   @JsonProperty("currentRevision")
   public String getCurrentRevision() {
      return this.currentRevision;
   }

   @JsonProperty("currentRevision")
   public void setCurrentRevision(String currentRevision) {
      this.currentRevision = currentRevision;
   }

   @JsonProperty("observedGeneration")
   public Long getObservedGeneration() {
      return this.observedGeneration;
   }

   @JsonProperty("observedGeneration")
   public void setObservedGeneration(Long observedGeneration) {
      this.observedGeneration = observedGeneration;
   }

   @JsonProperty("readyReplicas")
   public Integer getReadyReplicas() {
      return this.readyReplicas;
   }

   @JsonProperty("readyReplicas")
   public void setReadyReplicas(Integer readyReplicas) {
      this.readyReplicas = readyReplicas;
   }

   @JsonProperty("replicas")
   public Integer getReplicas() {
      return this.replicas;
   }

   @JsonProperty("replicas")
   public void setReplicas(Integer replicas) {
      this.replicas = replicas;
   }

   @JsonProperty("updateRevision")
   public String getUpdateRevision() {
      return this.updateRevision;
   }

   @JsonProperty("updateRevision")
   public void setUpdateRevision(String updateRevision) {
      this.updateRevision = updateRevision;
   }

   @JsonProperty("updatedReplicas")
   public Integer getUpdatedReplicas() {
      return this.updatedReplicas;
   }

   @JsonProperty("updatedReplicas")
   public void setUpdatedReplicas(Integer updatedReplicas) {
      this.updatedReplicas = updatedReplicas;
   }

   @JsonIgnore
   public StatefulSetStatusBuilder edit() {
      return new StatefulSetStatusBuilder(this);
   }

   @JsonIgnore
   public StatefulSetStatusBuilder toBuilder() {
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
      Integer var10000 = this.getAvailableReplicas();
      return "StatefulSetStatus(availableReplicas=" + var10000 + ", collisionCount=" + this.getCollisionCount() + ", conditions=" + this.getConditions() + ", currentReplicas=" + this.getCurrentReplicas() + ", currentRevision=" + this.getCurrentRevision() + ", observedGeneration=" + this.getObservedGeneration() + ", readyReplicas=" + this.getReadyReplicas() + ", replicas=" + this.getReplicas() + ", updateRevision=" + this.getUpdateRevision() + ", updatedReplicas=" + this.getUpdatedReplicas() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatefulSetStatus)) {
         return false;
      } else {
         StatefulSetStatus other = (StatefulSetStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$availableReplicas = this.getAvailableReplicas();
            Object other$availableReplicas = other.getAvailableReplicas();
            if (this$availableReplicas == null) {
               if (other$availableReplicas != null) {
                  return false;
               }
            } else if (!this$availableReplicas.equals(other$availableReplicas)) {
               return false;
            }

            Object this$collisionCount = this.getCollisionCount();
            Object other$collisionCount = other.getCollisionCount();
            if (this$collisionCount == null) {
               if (other$collisionCount != null) {
                  return false;
               }
            } else if (!this$collisionCount.equals(other$collisionCount)) {
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

            Object this$observedGeneration = this.getObservedGeneration();
            Object other$observedGeneration = other.getObservedGeneration();
            if (this$observedGeneration == null) {
               if (other$observedGeneration != null) {
                  return false;
               }
            } else if (!this$observedGeneration.equals(other$observedGeneration)) {
               return false;
            }

            Object this$readyReplicas = this.getReadyReplicas();
            Object other$readyReplicas = other.getReadyReplicas();
            if (this$readyReplicas == null) {
               if (other$readyReplicas != null) {
                  return false;
               }
            } else if (!this$readyReplicas.equals(other$readyReplicas)) {
               return false;
            }

            Object this$replicas = this.getReplicas();
            Object other$replicas = other.getReplicas();
            if (this$replicas == null) {
               if (other$replicas != null) {
                  return false;
               }
            } else if (!this$replicas.equals(other$replicas)) {
               return false;
            }

            Object this$updatedReplicas = this.getUpdatedReplicas();
            Object other$updatedReplicas = other.getUpdatedReplicas();
            if (this$updatedReplicas == null) {
               if (other$updatedReplicas != null) {
                  return false;
               }
            } else if (!this$updatedReplicas.equals(other$updatedReplicas)) {
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

            Object this$currentRevision = this.getCurrentRevision();
            Object other$currentRevision = other.getCurrentRevision();
            if (this$currentRevision == null) {
               if (other$currentRevision != null) {
                  return false;
               }
            } else if (!this$currentRevision.equals(other$currentRevision)) {
               return false;
            }

            Object this$updateRevision = this.getUpdateRevision();
            Object other$updateRevision = other.getUpdateRevision();
            if (this$updateRevision == null) {
               if (other$updateRevision != null) {
                  return false;
               }
            } else if (!this$updateRevision.equals(other$updateRevision)) {
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
      return other instanceof StatefulSetStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $availableReplicas = this.getAvailableReplicas();
      result = result * 59 + ($availableReplicas == null ? 43 : $availableReplicas.hashCode());
      Object $collisionCount = this.getCollisionCount();
      result = result * 59 + ($collisionCount == null ? 43 : $collisionCount.hashCode());
      Object $currentReplicas = this.getCurrentReplicas();
      result = result * 59 + ($currentReplicas == null ? 43 : $currentReplicas.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $readyReplicas = this.getReadyReplicas();
      result = result * 59 + ($readyReplicas == null ? 43 : $readyReplicas.hashCode());
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $updatedReplicas = this.getUpdatedReplicas();
      result = result * 59 + ($updatedReplicas == null ? 43 : $updatedReplicas.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $currentRevision = this.getCurrentRevision();
      result = result * 59 + ($currentRevision == null ? 43 : $currentRevision.hashCode());
      Object $updateRevision = this.getUpdateRevision();
      result = result * 59 + ($updateRevision == null ? 43 : $updateRevision.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
