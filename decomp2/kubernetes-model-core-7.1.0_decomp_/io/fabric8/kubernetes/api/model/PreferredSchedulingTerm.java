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
@JsonPropertyOrder({"preference", "weight"})
public class PreferredSchedulingTerm implements Editable, KubernetesResource {
   @JsonProperty("preference")
   private NodeSelectorTerm preference;
   @JsonProperty("weight")
   private Integer weight;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PreferredSchedulingTerm() {
   }

   public PreferredSchedulingTerm(NodeSelectorTerm preference, Integer weight) {
      this.preference = preference;
      this.weight = weight;
   }

   @JsonProperty("preference")
   public NodeSelectorTerm getPreference() {
      return this.preference;
   }

   @JsonProperty("preference")
   public void setPreference(NodeSelectorTerm preference) {
      this.preference = preference;
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
   public PreferredSchedulingTermBuilder edit() {
      return new PreferredSchedulingTermBuilder(this);
   }

   @JsonIgnore
   public PreferredSchedulingTermBuilder toBuilder() {
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
      NodeSelectorTerm var10000 = this.getPreference();
      return "PreferredSchedulingTerm(preference=" + var10000 + ", weight=" + this.getWeight() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PreferredSchedulingTerm)) {
         return false;
      } else {
         PreferredSchedulingTerm other = (PreferredSchedulingTerm)o;
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

            Object this$preference = this.getPreference();
            Object other$preference = other.getPreference();
            if (this$preference == null) {
               if (other$preference != null) {
                  return false;
               }
            } else if (!this$preference.equals(other$preference)) {
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
      return other instanceof PreferredSchedulingTerm;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $weight = this.getWeight();
      result = result * 59 + ($weight == null ? 43 : $weight.hashCode());
      Object $preference = this.getPreference();
      result = result * 59 + ($preference == null ? 43 : $preference.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
