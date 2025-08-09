package io.fabric8.kubernetes.api.model.rbac;

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
import io.fabric8.kubernetes.api.model.LabelSelector;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"clusterRoleSelectors"})
public class AggregationRule implements Editable, KubernetesResource {
   @JsonProperty("clusterRoleSelectors")
   @JsonInclude(Include.NON_EMPTY)
   private List clusterRoleSelectors = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public AggregationRule() {
   }

   public AggregationRule(List clusterRoleSelectors) {
      this.clusterRoleSelectors = clusterRoleSelectors;
   }

   @JsonProperty("clusterRoleSelectors")
   @JsonInclude(Include.NON_EMPTY)
   public List getClusterRoleSelectors() {
      return this.clusterRoleSelectors;
   }

   @JsonProperty("clusterRoleSelectors")
   public void setClusterRoleSelectors(List clusterRoleSelectors) {
      this.clusterRoleSelectors = clusterRoleSelectors;
   }

   @JsonIgnore
   public AggregationRuleBuilder edit() {
      return new AggregationRuleBuilder(this);
   }

   @JsonIgnore
   public AggregationRuleBuilder toBuilder() {
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
      List var10000 = this.getClusterRoleSelectors();
      return "AggregationRule(clusterRoleSelectors=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AggregationRule)) {
         return false;
      } else {
         AggregationRule other = (AggregationRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clusterRoleSelectors = this.getClusterRoleSelectors();
            Object other$clusterRoleSelectors = other.getClusterRoleSelectors();
            if (this$clusterRoleSelectors == null) {
               if (other$clusterRoleSelectors != null) {
                  return false;
               }
            } else if (!this$clusterRoleSelectors.equals(other$clusterRoleSelectors)) {
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
      return other instanceof AggregationRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clusterRoleSelectors = this.getClusterRoleSelectors();
      result = result * 59 + ($clusterRoleSelectors == null ? 43 : $clusterRoleSelectors.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
