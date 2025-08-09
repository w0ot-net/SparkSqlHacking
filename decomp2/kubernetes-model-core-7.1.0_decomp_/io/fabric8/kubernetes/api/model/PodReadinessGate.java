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
@JsonPropertyOrder({"conditionType"})
public class PodReadinessGate implements Editable, KubernetesResource {
   @JsonProperty("conditionType")
   private String conditionType;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodReadinessGate() {
   }

   public PodReadinessGate(String conditionType) {
      this.conditionType = conditionType;
   }

   @JsonProperty("conditionType")
   public String getConditionType() {
      return this.conditionType;
   }

   @JsonProperty("conditionType")
   public void setConditionType(String conditionType) {
      this.conditionType = conditionType;
   }

   @JsonIgnore
   public PodReadinessGateBuilder edit() {
      return new PodReadinessGateBuilder(this);
   }

   @JsonIgnore
   public PodReadinessGateBuilder toBuilder() {
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
      String var10000 = this.getConditionType();
      return "PodReadinessGate(conditionType=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodReadinessGate)) {
         return false;
      } else {
         PodReadinessGate other = (PodReadinessGate)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditionType = this.getConditionType();
            Object other$conditionType = other.getConditionType();
            if (this$conditionType == null) {
               if (other$conditionType != null) {
                  return false;
               }
            } else if (!this$conditionType.equals(other$conditionType)) {
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
      return other instanceof PodReadinessGate;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditionType = this.getConditionType();
      result = result * 59 + ($conditionType == null ? 43 : $conditionType.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
