package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

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
@JsonPropertyOrder({"borrowingLimitPercent", "lendablePercent", "limitResponse", "nominalConcurrencyShares"})
public class LimitedPriorityLevelConfiguration implements Editable, KubernetesResource {
   @JsonProperty("borrowingLimitPercent")
   private Integer borrowingLimitPercent;
   @JsonProperty("lendablePercent")
   private Integer lendablePercent;
   @JsonProperty("limitResponse")
   private LimitResponse limitResponse;
   @JsonProperty("nominalConcurrencyShares")
   private Integer nominalConcurrencyShares;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LimitedPriorityLevelConfiguration() {
   }

   public LimitedPriorityLevelConfiguration(Integer borrowingLimitPercent, Integer lendablePercent, LimitResponse limitResponse, Integer nominalConcurrencyShares) {
      this.borrowingLimitPercent = borrowingLimitPercent;
      this.lendablePercent = lendablePercent;
      this.limitResponse = limitResponse;
      this.nominalConcurrencyShares = nominalConcurrencyShares;
   }

   @JsonProperty("borrowingLimitPercent")
   public Integer getBorrowingLimitPercent() {
      return this.borrowingLimitPercent;
   }

   @JsonProperty("borrowingLimitPercent")
   public void setBorrowingLimitPercent(Integer borrowingLimitPercent) {
      this.borrowingLimitPercent = borrowingLimitPercent;
   }

   @JsonProperty("lendablePercent")
   public Integer getLendablePercent() {
      return this.lendablePercent;
   }

   @JsonProperty("lendablePercent")
   public void setLendablePercent(Integer lendablePercent) {
      this.lendablePercent = lendablePercent;
   }

   @JsonProperty("limitResponse")
   public LimitResponse getLimitResponse() {
      return this.limitResponse;
   }

   @JsonProperty("limitResponse")
   public void setLimitResponse(LimitResponse limitResponse) {
      this.limitResponse = limitResponse;
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
   public LimitedPriorityLevelConfigurationBuilder edit() {
      return new LimitedPriorityLevelConfigurationBuilder(this);
   }

   @JsonIgnore
   public LimitedPriorityLevelConfigurationBuilder toBuilder() {
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
      Integer var10000 = this.getBorrowingLimitPercent();
      return "LimitedPriorityLevelConfiguration(borrowingLimitPercent=" + var10000 + ", lendablePercent=" + this.getLendablePercent() + ", limitResponse=" + this.getLimitResponse() + ", nominalConcurrencyShares=" + this.getNominalConcurrencyShares() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LimitedPriorityLevelConfiguration)) {
         return false;
      } else {
         LimitedPriorityLevelConfiguration other = (LimitedPriorityLevelConfiguration)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$borrowingLimitPercent = this.getBorrowingLimitPercent();
            Object other$borrowingLimitPercent = other.getBorrowingLimitPercent();
            if (this$borrowingLimitPercent == null) {
               if (other$borrowingLimitPercent != null) {
                  return false;
               }
            } else if (!this$borrowingLimitPercent.equals(other$borrowingLimitPercent)) {
               return false;
            }

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

            Object this$limitResponse = this.getLimitResponse();
            Object other$limitResponse = other.getLimitResponse();
            if (this$limitResponse == null) {
               if (other$limitResponse != null) {
                  return false;
               }
            } else if (!this$limitResponse.equals(other$limitResponse)) {
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
      return other instanceof LimitedPriorityLevelConfiguration;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $borrowingLimitPercent = this.getBorrowingLimitPercent();
      result = result * 59 + ($borrowingLimitPercent == null ? 43 : $borrowingLimitPercent.hashCode());
      Object $lendablePercent = this.getLendablePercent();
      result = result * 59 + ($lendablePercent == null ? 43 : $lendablePercent.hashCode());
      Object $nominalConcurrencyShares = this.getNominalConcurrencyShares();
      result = result * 59 + ($nominalConcurrencyShares == null ? 43 : $nominalConcurrencyShares.hashCode());
      Object $limitResponse = this.getLimitResponse();
      result = result * 59 + ($limitResponse == null ? 43 : $limitResponse.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
