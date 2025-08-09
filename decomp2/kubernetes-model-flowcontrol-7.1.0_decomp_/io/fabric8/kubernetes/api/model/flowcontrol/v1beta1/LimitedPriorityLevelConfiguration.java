package io.fabric8.kubernetes.api.model.flowcontrol.v1beta1;

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
@JsonPropertyOrder({"assuredConcurrencyShares", "limitResponse"})
public class LimitedPriorityLevelConfiguration implements Editable, KubernetesResource {
   @JsonProperty("assuredConcurrencyShares")
   private Integer assuredConcurrencyShares;
   @JsonProperty("limitResponse")
   private LimitResponse limitResponse;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LimitedPriorityLevelConfiguration() {
   }

   public LimitedPriorityLevelConfiguration(Integer assuredConcurrencyShares, LimitResponse limitResponse) {
      this.assuredConcurrencyShares = assuredConcurrencyShares;
      this.limitResponse = limitResponse;
   }

   @JsonProperty("assuredConcurrencyShares")
   public Integer getAssuredConcurrencyShares() {
      return this.assuredConcurrencyShares;
   }

   @JsonProperty("assuredConcurrencyShares")
   public void setAssuredConcurrencyShares(Integer assuredConcurrencyShares) {
      this.assuredConcurrencyShares = assuredConcurrencyShares;
   }

   @JsonProperty("limitResponse")
   public LimitResponse getLimitResponse() {
      return this.limitResponse;
   }

   @JsonProperty("limitResponse")
   public void setLimitResponse(LimitResponse limitResponse) {
      this.limitResponse = limitResponse;
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
      Integer var10000 = this.getAssuredConcurrencyShares();
      return "LimitedPriorityLevelConfiguration(assuredConcurrencyShares=" + var10000 + ", limitResponse=" + this.getLimitResponse() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
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
            Object this$assuredConcurrencyShares = this.getAssuredConcurrencyShares();
            Object other$assuredConcurrencyShares = other.getAssuredConcurrencyShares();
            if (this$assuredConcurrencyShares == null) {
               if (other$assuredConcurrencyShares != null) {
                  return false;
               }
            } else if (!this$assuredConcurrencyShares.equals(other$assuredConcurrencyShares)) {
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
      Object $assuredConcurrencyShares = this.getAssuredConcurrencyShares();
      result = result * 59 + ($assuredConcurrencyShares == null ? 43 : $assuredConcurrencyShares.hashCode());
      Object $limitResponse = this.getLimitResponse();
      result = result * 59 + ($limitResponse == null ? 43 : $limitResponse.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
