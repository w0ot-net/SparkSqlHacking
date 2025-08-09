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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"replicas", "selector", "targetSelector"})
public class ScaleStatus implements Editable, KubernetesResource {
   @JsonProperty("replicas")
   private Integer replicas;
   @JsonProperty("selector")
   @JsonInclude(Include.NON_EMPTY)
   private Map selector = new LinkedHashMap();
   @JsonProperty("targetSelector")
   private String targetSelector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ScaleStatus() {
   }

   public ScaleStatus(Integer replicas, Map selector, String targetSelector) {
      this.replicas = replicas;
      this.selector = selector;
      this.targetSelector = targetSelector;
   }

   @JsonProperty("replicas")
   public Integer getReplicas() {
      return this.replicas;
   }

   @JsonProperty("replicas")
   public void setReplicas(Integer replicas) {
      this.replicas = replicas;
   }

   @JsonProperty("selector")
   @JsonInclude(Include.NON_EMPTY)
   public Map getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(Map selector) {
      this.selector = selector;
   }

   @JsonProperty("targetSelector")
   public String getTargetSelector() {
      return this.targetSelector;
   }

   @JsonProperty("targetSelector")
   public void setTargetSelector(String targetSelector) {
      this.targetSelector = targetSelector;
   }

   @JsonIgnore
   public ScaleStatusBuilder edit() {
      return new ScaleStatusBuilder(this);
   }

   @JsonIgnore
   public ScaleStatusBuilder toBuilder() {
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
      Integer var10000 = this.getReplicas();
      return "ScaleStatus(replicas=" + var10000 + ", selector=" + this.getSelector() + ", targetSelector=" + this.getTargetSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScaleStatus)) {
         return false;
      } else {
         ScaleStatus other = (ScaleStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$replicas = this.getReplicas();
            Object other$replicas = other.getReplicas();
            if (this$replicas == null) {
               if (other$replicas != null) {
                  return false;
               }
            } else if (!this$replicas.equals(other$replicas)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$targetSelector = this.getTargetSelector();
            Object other$targetSelector = other.getTargetSelector();
            if (this$targetSelector == null) {
               if (other$targetSelector != null) {
                  return false;
               }
            } else if (!this$targetSelector.equals(other$targetSelector)) {
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
      return other instanceof ScaleStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $replicas = this.getReplicas();
      result = result * 59 + ($replicas == null ? 43 : $replicas.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $targetSelector = this.getTargetSelector();
      result = result * 59 + ($targetSelector == null ? 43 : $targetSelector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
