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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"availableReplicas", "conditions", "fullyLabeledReplicas", "observedGeneration", "readyReplicas", "replicas"})
public class ReplicationControllerStatus implements Editable, KubernetesResource {
   @JsonProperty("availableReplicas")
   private Integer availableReplicas;
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("fullyLabeledReplicas")
   private Integer fullyLabeledReplicas;
   @JsonProperty("observedGeneration")
   private Long observedGeneration;
   @JsonProperty("readyReplicas")
   private Integer readyReplicas;
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ReplicationControllerStatus() {
   }

   public ReplicationControllerStatus(Integer availableReplicas, List conditions, Integer fullyLabeledReplicas, Long observedGeneration, Integer readyReplicas, Integer replicas) {
      this.availableReplicas = availableReplicas;
      this.conditions = conditions;
      this.fullyLabeledReplicas = fullyLabeledReplicas;
      this.observedGeneration = observedGeneration;
      this.readyReplicas = readyReplicas;
      this.replicas = replicas;
   }

   @JsonProperty("availableReplicas")
   public Integer getAvailableReplicas() {
      return this.availableReplicas;
   }

   @JsonProperty("availableReplicas")
   public void setAvailableReplicas(Integer availableReplicas) {
      this.availableReplicas = availableReplicas;
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

   @JsonProperty("fullyLabeledReplicas")
   public Integer getFullyLabeledReplicas() {
      return this.fullyLabeledReplicas;
   }

   @JsonProperty("fullyLabeledReplicas")
   public void setFullyLabeledReplicas(Integer fullyLabeledReplicas) {
      this.fullyLabeledReplicas = fullyLabeledReplicas;
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

   @JsonIgnore
   public ReplicationControllerStatusBuilder edit() {
      return new ReplicationControllerStatusBuilder(this);
   }

   @JsonIgnore
   public ReplicationControllerStatusBuilder toBuilder() {
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
      return "ReplicationControllerStatus(availableReplicas=" + var10000 + ", conditions=" + this.getConditions() + ", fullyLabeledReplicas=" + this.getFullyLabeledReplicas() + ", observedGeneration=" + this.getObservedGeneration() + ", readyReplicas=" + this.getReadyReplicas() + ", replicas=" + this.getReplicas() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReplicationControllerStatus)) {
         return false;
      } else {
         ReplicationControllerStatus other = (ReplicationControllerStatus)o;
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

            Object this$fullyLabeledReplicas = this.getFullyLabeledReplicas();
            Object other$fullyLabeledReplicas = other.getFullyLabeledReplicas();
            if (this$fullyLabeledReplicas == null) {
               if (other$fullyLabeledReplicas != null) {
                  return false;
               }
            } else if (!this$fullyLabeledReplicas.equals(other$fullyLabeledReplicas)) {
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
      return other instanceof ReplicationControllerStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $availableReplicas = this.getAvailableReplicas();
      result = result * 59 + ($availableReplicas == null ? 43 : $availableReplicas.hashCode());
      Object $fullyLabeledReplicas = this.getFullyLabeledReplicas();
      result = result * 59 + ($fullyLabeledReplicas == null ? 43 : $fullyLabeledReplicas.hashCode());
      Object $observedGeneration = this.getObservedGeneration();
      result = result * 59 + ($observedGeneration == null ? 43 : $observedGeneration.hashCode());
      Object $readyReplicas = this.getReadyReplicas();
      result = result * 59 + ($readyReplicas == null ? 43 : $readyReplicas.hashCode());
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
