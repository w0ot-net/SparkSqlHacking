package io.fabric8.kubernetes.api.model.flowcontrol.v1;

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
@JsonPropertyOrder({"lendablePercent", "nominalConcurrencyShares"})
public class ExemptPriorityLevelConfiguration implements Editable, KubernetesResource {
   @JsonProperty("lendablePercent")
   private Integer lendablePercent;
   @JsonProperty("nominalConcurrencyShares")
   private Integer nominalConcurrencyShares;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExemptPriorityLevelConfiguration() {
   }

   public ExemptPriorityLevelConfiguration(Integer lendablePercent, Integer nominalConcurrencyShares) {
      this.lendablePercent = lendablePercent;
      this.nominalConcurrencyShares = nominalConcurrencyShares;
   }

   @JsonProperty("lendablePercent")
   public Integer getLendablePercent() {
      return this.lendablePercent;
   }

   @JsonProperty("lendablePercent")
   public void setLendablePercent(Integer lendablePercent) {
      this.lendablePercent = lendablePercent;
   }

   @JsonProperty("nominalConcurrencyShares")
   public Integer getNominalConcurrencyShares() {
      return this.nominalConcurrencyShares;
   }

   @JsonProperty("nominalConcurrencyShares")
   public void setNominalConcurrencyShares(Integer nominalConcurrencyShares) {
      this.nominalConcurrencyShares = nominalConcurrencyShares;
   }

   @JsonIgnore
   public ExemptPriorityLevelConfigurationBuilder edit() {
      return new ExemptPriorityLevelConfigurationBuilder(this);
   }

   @JsonIgnore
   public ExemptPriorityLevelConfigurationBuilder toBuilder() {
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
      Integer var10000 = this.getLendablePercent();
      return "ExemptPriorityLevelConfiguration(lendablePercent=" + var10000 + ", nominalConcurrencyShares=" + this.getNominalConcurrencyShares() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExemptPriorityLevelConfiguration)) {
         return false;
      } else {
         ExemptPriorityLevelConfiguration other = (ExemptPriorityLevelConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$lendablePercent = this.getLendablePercent();
            Object other$lendablePercent = other.getLendablePercent();
            if (this$lendablePercent == null) {
               if (other$lendablePercent != null) {
                  return false;
               }
            } else if (!this$lendablePercent.equals(other$lendablePercent)) {
               return false;
            }

            Object this$nominalConcurrencyShares = this.getNominalConcurrencyShares();
            Object other$nominalConcurrencyShares = other.getNominalConcurrencyShares();
            if (this$nominalConcurrencyShares == null) {
               if (other$nominalConcurrencyShares != null) {
                  return false;
               }
            } else if (!this$nominalConcurrencyShares.equals(other$nominalConcurrencyShares)) {
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
      return other instanceof ExemptPriorityLevelConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $lendablePercent = this.getLendablePercent();
      result = result * 59 + ($lendablePercent == null ? 43 : $lendablePercent.hashCode());
      Object $nominalConcurrencyShares = this.getNominalConcurrencyShares();
      result = result * 59 + ($nominalConcurrencyShares == null ? 43 : $nominalConcurrencyShares.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
