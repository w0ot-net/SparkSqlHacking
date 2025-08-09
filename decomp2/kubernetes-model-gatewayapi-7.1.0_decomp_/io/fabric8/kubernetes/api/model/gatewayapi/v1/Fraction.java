package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"denominator", "numerator"})
public class Fraction implements Editable, KubernetesResource {
   @JsonProperty("denominator")
   private Integer denominator;
   @JsonProperty("numerator")
   private Integer numerator;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Fraction() {
   }

   public Fraction(Integer denominator, Integer numerator) {
      this.denominator = denominator;
      this.numerator = numerator;
   }

   @JsonProperty("denominator")
   public Integer getDenominator() {
      return this.denominator;
   }

   @JsonProperty("denominator")
   public void setDenominator(Integer denominator) {
      this.denominator = denominator;
   }

   @JsonProperty("numerator")
   public Integer getNumerator() {
      return this.numerator;
   }

   @JsonProperty("numerator")
   public void setNumerator(Integer numerator) {
      this.numerator = numerator;
   }

   @JsonIgnore
   public FractionBuilder edit() {
      return new FractionBuilder(this);
   }

   @JsonIgnore
   public FractionBuilder toBuilder() {
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
      Integer var10000 = this.getDenominator();
      return "Fraction(denominator=" + var10000 + ", numerator=" + this.getNumerator() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Fraction)) {
         return false;
      } else {
         Fraction other = (Fraction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$denominator = this.getDenominator();
            Object other$denominator = other.getDenominator();
            if (this$denominator == null) {
               if (other$denominator != null) {
                  return false;
               }
            } else if (!this$denominator.equals(other$denominator)) {
               return false;
            }

            Object this$numerator = this.getNumerator();
            Object other$numerator = other.getNumerator();
            if (this$numerator == null) {
               if (other$numerator != null) {
                  return false;
               }
            } else if (!this$numerator.equals(other$numerator)) {
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
      return other instanceof Fraction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $denominator = this.getDenominator();
      result = result * 59 + ($denominator == null ? 43 : $denominator.hashCode());
      Object $numerator = this.getNumerator();
      result = result * 59 + ($numerator == null ? 43 : $numerator.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
