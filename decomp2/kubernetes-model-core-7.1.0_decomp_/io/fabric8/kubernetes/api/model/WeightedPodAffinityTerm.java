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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"podAffinityTerm", "weight"})
public class WeightedPodAffinityTerm implements Editable, KubernetesResource {
   @JsonProperty("podAffinityTerm")
   private PodAffinityTerm podAffinityTerm;
   @JsonProperty("weight")
   private Integer weight;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public WeightedPodAffinityTerm() {
   }

   public WeightedPodAffinityTerm(PodAffinityTerm podAffinityTerm, Integer weight) {
      this.podAffinityTerm = podAffinityTerm;
      this.weight = weight;
   }

   @JsonProperty("podAffinityTerm")
   public PodAffinityTerm getPodAffinityTerm() {
      return this.podAffinityTerm;
   }

   @JsonProperty("podAffinityTerm")
   public void setPodAffinityTerm(PodAffinityTerm podAffinityTerm) {
      this.podAffinityTerm = podAffinityTerm;
   }

   @JsonProperty("weight")
   public Integer getWeight() {
      return this.weight;
   }

   @JsonProperty("weight")
   public void setWeight(Integer weight) {
      this.weight = weight;
   }

   @JsonIgnore
   public WeightedPodAffinityTermBuilder edit() {
      return new WeightedPodAffinityTermBuilder(this);
   }

   @JsonIgnore
   public WeightedPodAffinityTermBuilder toBuilder() {
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
      PodAffinityTerm var10000 = this.getPodAffinityTerm();
      return "WeightedPodAffinityTerm(podAffinityTerm=" + var10000 + ", weight=" + this.getWeight() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof WeightedPodAffinityTerm)) {
         return false;
      } else {
         WeightedPodAffinityTerm other = (WeightedPodAffinityTerm)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$weight = this.getWeight();
            Object other$weight = other.getWeight();
            if (this$weight == null) {
               if (other$weight != null) {
                  return false;
               }
            } else if (!this$weight.equals(other$weight)) {
               return false;
            }

            Object this$podAffinityTerm = this.getPodAffinityTerm();
            Object other$podAffinityTerm = other.getPodAffinityTerm();
            if (this$podAffinityTerm == null) {
               if (other$podAffinityTerm != null) {
                  return false;
               }
            } else if (!this$podAffinityTerm.equals(other$podAffinityTerm)) {
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
      return other instanceof WeightedPodAffinityTerm;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $weight = this.getWeight();
      result = result * 59 + ($weight == null ? 43 : $weight.hashCode());
      Object $podAffinityTerm = this.getPodAffinityTerm();
      result = result * 59 + ($podAffinityTerm == null ? 43 : $podAffinityTerm.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
