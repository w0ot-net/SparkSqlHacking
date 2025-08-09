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
import io.fabric8.kubernetes.api.model.Condition;
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
@JsonPropertyOrder({"conditions", "supportedFeatures"})
public class GatewayClassStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("supportedFeatures")
   @JsonInclude(Include.NON_EMPTY)
   private List supportedFeatures = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GatewayClassStatus() {
   }

   public GatewayClassStatus(List conditions, List supportedFeatures) {
      this.conditions = conditions;
      this.supportedFeatures = supportedFeatures;
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

   @JsonProperty("supportedFeatures")
   @JsonInclude(Include.NON_EMPTY)
   public List getSupportedFeatures() {
      return this.supportedFeatures;
   }

   @JsonProperty("supportedFeatures")
   public void setSupportedFeatures(List supportedFeatures) {
      this.supportedFeatures = supportedFeatures;
   }

   @JsonIgnore
   public GatewayClassStatusBuilder edit() {
      return new GatewayClassStatusBuilder(this);
   }

   @JsonIgnore
   public GatewayClassStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "GatewayClassStatus(conditions=" + var10000 + ", supportedFeatures=" + this.getSupportedFeatures() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GatewayClassStatus)) {
         return false;
      } else {
         GatewayClassStatus other = (GatewayClassStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$supportedFeatures = this.getSupportedFeatures();
            Object other$supportedFeatures = other.getSupportedFeatures();
            if (this$supportedFeatures == null) {
               if (other$supportedFeatures != null) {
                  return false;
               }
            } else if (!this$supportedFeatures.equals(other$supportedFeatures)) {
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
      return other instanceof GatewayClassStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $supportedFeatures = this.getSupportedFeatures();
      result = result * 59 + ($supportedFeatures == null ? 43 : $supportedFeatures.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
