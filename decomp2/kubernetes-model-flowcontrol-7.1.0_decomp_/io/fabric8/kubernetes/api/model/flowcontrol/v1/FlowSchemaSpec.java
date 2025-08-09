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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"distinguisherMethod", "matchingPrecedence", "priorityLevelConfiguration", "rules"})
public class FlowSchemaSpec implements Editable, KubernetesResource {
   @JsonProperty("distinguisherMethod")
   private FlowDistinguisherMethod distinguisherMethod;
   @JsonProperty("matchingPrecedence")
   private Integer matchingPrecedence;
   @JsonProperty("priorityLevelConfiguration")
   private PriorityLevelConfigurationReference priorityLevelConfiguration;
   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   private List rules = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public FlowSchemaSpec() {
   }

   public FlowSchemaSpec(FlowDistinguisherMethod distinguisherMethod, Integer matchingPrecedence, PriorityLevelConfigurationReference priorityLevelConfiguration, List rules) {
      this.distinguisherMethod = distinguisherMethod;
      this.matchingPrecedence = matchingPrecedence;
      this.priorityLevelConfiguration = priorityLevelConfiguration;
      this.rules = rules;
   }

   @JsonProperty("distinguisherMethod")
   public FlowDistinguisherMethod getDistinguisherMethod() {
      return this.distinguisherMethod;
   }

   @JsonProperty("distinguisherMethod")
   public void setDistinguisherMethod(FlowDistinguisherMethod distinguisherMethod) {
      this.distinguisherMethod = distinguisherMethod;
   }

   @JsonProperty("matchingPrecedence")
   public Integer getMatchingPrecedence() {
      return this.matchingPrecedence;
   }

   @JsonProperty("matchingPrecedence")
   public void setMatchingPrecedence(Integer matchingPrecedence) {
      this.matchingPrecedence = matchingPrecedence;
   }

   @JsonProperty("priorityLevelConfiguration")
   public PriorityLevelConfigurationReference getPriorityLevelConfiguration() {
      return this.priorityLevelConfiguration;
   }

   @JsonProperty("priorityLevelConfiguration")
   public void setPriorityLevelConfiguration(PriorityLevelConfigurationReference priorityLevelConfiguration) {
      this.priorityLevelConfiguration = priorityLevelConfiguration;
   }

   @JsonProperty("rules")
   @JsonInclude(Include.NON_EMPTY)
   public List getRules() {
      return this.rules;
   }

   @JsonProperty("rules")
   public void setRules(List rules) {
      this.rules = rules;
   }

   @JsonIgnore
   public FlowSchemaSpecBuilder edit() {
      return new FlowSchemaSpecBuilder(this);
   }

   @JsonIgnore
   public FlowSchemaSpecBuilder toBuilder() {
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
      FlowDistinguisherMethod var10000 = this.getDistinguisherMethod();
      return "FlowSchemaSpec(distinguisherMethod=" + var10000 + ", matchingPrecedence=" + this.getMatchingPrecedence() + ", priorityLevelConfiguration=" + this.getPriorityLevelConfiguration() + ", rules=" + this.getRules() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FlowSchemaSpec)) {
         return false;
      } else {
         FlowSchemaSpec other = (FlowSchemaSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$matchingPrecedence = this.getMatchingPrecedence();
            Object other$matchingPrecedence = other.getMatchingPrecedence();
            if (this$matchingPrecedence == null) {
               if (other$matchingPrecedence != null) {
                  return false;
               }
            } else if (!this$matchingPrecedence.equals(other$matchingPrecedence)) {
               return false;
            }

            Object this$distinguisherMethod = this.getDistinguisherMethod();
            Object other$distinguisherMethod = other.getDistinguisherMethod();
            if (this$distinguisherMethod == null) {
               if (other$distinguisherMethod != null) {
                  return false;
               }
            } else if (!this$distinguisherMethod.equals(other$distinguisherMethod)) {
               return false;
            }

            Object this$priorityLevelConfiguration = this.getPriorityLevelConfiguration();
            Object other$priorityLevelConfiguration = other.getPriorityLevelConfiguration();
            if (this$priorityLevelConfiguration == null) {
               if (other$priorityLevelConfiguration != null) {
                  return false;
               }
            } else if (!this$priorityLevelConfiguration.equals(other$priorityLevelConfiguration)) {
               return false;
            }

            Object this$rules = this.getRules();
            Object other$rules = other.getRules();
            if (this$rules == null) {
               if (other$rules != null) {
                  return false;
               }
            } else if (!this$rules.equals(other$rules)) {
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
      return other instanceof FlowSchemaSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $matchingPrecedence = this.getMatchingPrecedence();
      result = result * 59 + ($matchingPrecedence == null ? 43 : $matchingPrecedence.hashCode());
      Object $distinguisherMethod = this.getDistinguisherMethod();
      result = result * 59 + ($distinguisherMethod == null ? 43 : $distinguisherMethod.hashCode());
      Object $priorityLevelConfiguration = this.getPriorityLevelConfiguration();
      result = result * 59 + ($priorityLevelConfiguration == null ? 43 : $priorityLevelConfiguration.hashCode());
      Object $rules = this.getRules();
      result = result * 59 + ($rules == null ? 43 : $rules.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
