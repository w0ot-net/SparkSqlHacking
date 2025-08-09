package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"policies", "selectPolicy", "stabilizationWindowSeconds"})
public class HPAScalingRules implements Editable, KubernetesResource {
   @JsonProperty("policies")
   @JsonInclude(Include.NON_EMPTY)
   private List policies = new ArrayList();
   @JsonProperty("selectPolicy")
   private String selectPolicy;
   @JsonProperty("stabilizationWindowSeconds")
   private Integer stabilizationWindowSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HPAScalingRules() {
   }

   public HPAScalingRules(List policies, String selectPolicy, Integer stabilizationWindowSeconds) {
      this.policies = policies;
      this.selectPolicy = selectPolicy;
      this.stabilizationWindowSeconds = stabilizationWindowSeconds;
   }

   @JsonProperty("policies")
   @JsonInclude(Include.NON_EMPTY)
   public List getPolicies() {
      return this.policies;
   }

   @JsonProperty("policies")
   public void setPolicies(List policies) {
      this.policies = policies;
   }

   @JsonProperty("selectPolicy")
   public String getSelectPolicy() {
      return this.selectPolicy;
   }

   @JsonProperty("selectPolicy")
   public void setSelectPolicy(String selectPolicy) {
      this.selectPolicy = selectPolicy;
   }

   @JsonProperty("stabilizationWindowSeconds")
   public Integer getStabilizationWindowSeconds() {
      return this.stabilizationWindowSeconds;
   }

   @JsonProperty("stabilizationWindowSeconds")
   public void setStabilizationWindowSeconds(Integer stabilizationWindowSeconds) {
      this.stabilizationWindowSeconds = stabilizationWindowSeconds;
   }

   @JsonIgnore
   public HPAScalingRulesBuilder edit() {
      return new HPAScalingRulesBuilder(this);
   }

   @JsonIgnore
   public HPAScalingRulesBuilder toBuilder() {
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
      List var10000 = this.getPolicies();
      return "HPAScalingRules(policies=" + var10000 + ", selectPolicy=" + this.getSelectPolicy() + ", stabilizationWindowSeconds=" + this.getStabilizationWindowSeconds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HPAScalingRules)) {
         return false;
      } else {
         HPAScalingRules other = (HPAScalingRules)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$stabilizationWindowSeconds = this.getStabilizationWindowSeconds();
            Object other$stabilizationWindowSeconds = other.getStabilizationWindowSeconds();
            if (this$stabilizationWindowSeconds == null) {
               if (other$stabilizationWindowSeconds != null) {
                  return false;
               }
            } else if (!this$stabilizationWindowSeconds.equals(other$stabilizationWindowSeconds)) {
               return false;
            }

            Object this$policies = this.getPolicies();
            Object other$policies = other.getPolicies();
            if (this$policies == null) {
               if (other$policies != null) {
                  return false;
               }
            } else if (!this$policies.equals(other$policies)) {
               return false;
            }

            Object this$selectPolicy = this.getSelectPolicy();
            Object other$selectPolicy = other.getSelectPolicy();
            if (this$selectPolicy == null) {
               if (other$selectPolicy != null) {
                  return false;
               }
            } else if (!this$selectPolicy.equals(other$selectPolicy)) {
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
      return other instanceof HPAScalingRules;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $stabilizationWindowSeconds = this.getStabilizationWindowSeconds();
      result = result * 59 + ($stabilizationWindowSeconds == null ? 43 : $stabilizationWindowSeconds.hashCode());
      Object $policies = this.getPolicies();
      result = result * 59 + ($policies == null ? 43 : $policies.hashCode());
      Object $selectPolicy = this.getSelectPolicy();
      result = result * 59 + ($selectPolicy == null ? 43 : $selectPolicy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
