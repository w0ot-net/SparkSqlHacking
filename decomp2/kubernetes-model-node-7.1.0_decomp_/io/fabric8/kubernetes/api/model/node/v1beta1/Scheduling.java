package io.fabric8.kubernetes.api.model.node.v1beta1;

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
import io.fabric8.kubernetes.api.model.Toleration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"nodeSelector", "tolerations"})
public class Scheduling implements Editable, KubernetesResource {
   @JsonProperty("nodeSelector")
   @JsonInclude(Include.NON_EMPTY)
   private Map nodeSelector = new LinkedHashMap();
   @JsonProperty("tolerations")
   @JsonInclude(Include.NON_EMPTY)
   private List tolerations = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Scheduling() {
   }

   public Scheduling(Map nodeSelector, List tolerations) {
      this.nodeSelector = nodeSelector;
      this.tolerations = tolerations;
   }

   @JsonProperty("nodeSelector")
   @JsonInclude(Include.NON_EMPTY)
   public Map getNodeSelector() {
      return this.nodeSelector;
   }

   @JsonProperty("nodeSelector")
   public void setNodeSelector(Map nodeSelector) {
      this.nodeSelector = nodeSelector;
   }

   @JsonProperty("tolerations")
   @JsonInclude(Include.NON_EMPTY)
   public List getTolerations() {
      return this.tolerations;
   }

   @JsonProperty("tolerations")
   public void setTolerations(List tolerations) {
      this.tolerations = tolerations;
   }

   @JsonIgnore
   public SchedulingBuilder edit() {
      return new SchedulingBuilder(this);
   }

   @JsonIgnore
   public SchedulingBuilder toBuilder() {
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
      Map var10000 = this.getNodeSelector();
      return "Scheduling(nodeSelector=" + var10000 + ", tolerations=" + this.getTolerations() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Scheduling)) {
         return false;
      } else {
         Scheduling other = (Scheduling)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodeSelector = this.getNodeSelector();
            Object other$nodeSelector = other.getNodeSelector();
            if (this$nodeSelector == null) {
               if (other$nodeSelector != null) {
                  return false;
               }
            } else if (!this$nodeSelector.equals(other$nodeSelector)) {
               return false;
            }

            Object this$tolerations = this.getTolerations();
            Object other$tolerations = other.getTolerations();
            if (this$tolerations == null) {
               if (other$tolerations != null) {
                  return false;
               }
            } else if (!this$tolerations.equals(other$tolerations)) {
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
      return other instanceof Scheduling;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodeSelector = this.getNodeSelector();
      result = result * 59 + ($nodeSelector == null ? 43 : $nodeSelector.hashCode());
      Object $tolerations = this.getTolerations();
      result = result * 59 + ($tolerations == null ? 43 : $tolerations.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
