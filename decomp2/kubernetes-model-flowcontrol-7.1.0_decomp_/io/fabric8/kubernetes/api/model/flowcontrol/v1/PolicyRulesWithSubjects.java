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
@JsonPropertyOrder({"nonResourceRules", "resourceRules", "subjects"})
public class PolicyRulesWithSubjects implements Editable, KubernetesResource {
   @JsonProperty("nonResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List nonResourceRules = new ArrayList();
   @JsonProperty("resourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceRules = new ArrayList();
   @JsonProperty("subjects")
   @JsonInclude(Include.NON_EMPTY)
   private List subjects = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PolicyRulesWithSubjects() {
   }

   public PolicyRulesWithSubjects(List nonResourceRules, List resourceRules, List subjects) {
      this.nonResourceRules = nonResourceRules;
      this.resourceRules = resourceRules;
      this.subjects = subjects;
   }

   @JsonProperty("nonResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   public List getNonResourceRules() {
      return this.nonResourceRules;
   }

   @JsonProperty("nonResourceRules")
   public void setNonResourceRules(List nonResourceRules) {
      this.nonResourceRules = nonResourceRules;
   }

   @JsonProperty("resourceRules")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceRules() {
      return this.resourceRules;
   }

   @JsonProperty("resourceRules")
   public void setResourceRules(List resourceRules) {
      this.resourceRules = resourceRules;
   }

   @JsonProperty("subjects")
   @JsonInclude(Include.NON_EMPTY)
   public List getSubjects() {
      return this.subjects;
   }

   @JsonProperty("subjects")
   public void setSubjects(List subjects) {
      this.subjects = subjects;
   }

   @JsonIgnore
   public PolicyRulesWithSubjectsBuilder edit() {
      return new PolicyRulesWithSubjectsBuilder(this);
   }

   @JsonIgnore
   public PolicyRulesWithSubjectsBuilder toBuilder() {
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
      List var10000 = this.getNonResourceRules();
      return "PolicyRulesWithSubjects(nonResourceRules=" + var10000 + ", resourceRules=" + this.getResourceRules() + ", subjects=" + this.getSubjects() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PolicyRulesWithSubjects)) {
         return false;
      } else {
         PolicyRulesWithSubjects other = (PolicyRulesWithSubjects)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nonResourceRules = this.getNonResourceRules();
            Object other$nonResourceRules = other.getNonResourceRules();
            if (this$nonResourceRules == null) {
               if (other$nonResourceRules != null) {
                  return false;
               }
            } else if (!this$nonResourceRules.equals(other$nonResourceRules)) {
               return false;
            }

            Object this$resourceRules = this.getResourceRules();
            Object other$resourceRules = other.getResourceRules();
            if (this$resourceRules == null) {
               if (other$resourceRules != null) {
                  return false;
               }
            } else if (!this$resourceRules.equals(other$resourceRules)) {
               return false;
            }

            Object this$subjects = this.getSubjects();
            Object other$subjects = other.getSubjects();
            if (this$subjects == null) {
               if (other$subjects != null) {
                  return false;
               }
            } else if (!this$subjects.equals(other$subjects)) {
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
      return other instanceof PolicyRulesWithSubjects;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nonResourceRules = this.getNonResourceRules();
      result = result * 59 + ($nonResourceRules == null ? 43 : $nonResourceRules.hashCode());
      Object $resourceRules = this.getResourceRules();
      result = result * 59 + ($resourceRules == null ? 43 : $resourceRules.hashCode());
      Object $subjects = this.getSubjects();
      result = result * 59 + ($subjects == null ? 43 : $subjects.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
