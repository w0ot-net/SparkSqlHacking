package io.fabric8.kubernetes.api.model.admissionregistration.v1beta1;

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
@JsonPropertyOrder({"excludeResourceRules", "matchPolicy", "namespaceSelector", "objectSelector", "resourceRules"})
public class MatchResources implements Editable, KubernetesResource {
   @JsonProperty("excludeResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List excludeResourceRules = new ArrayList();
   @JsonProperty("matchPolicy")
   private String matchPolicy;
   @JsonProperty("namespaceSelector")
   private LabelSelector namespaceSelector;
   @JsonProperty("objectSelector")
   private LabelSelector objectSelector;
   @JsonProperty("resourceRules")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceRules = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public MatchResources() {
   }

   public MatchResources(List excludeResourceRules, String matchPolicy, LabelSelector namespaceSelector, LabelSelector objectSelector, List resourceRules) {
      this.excludeResourceRules = excludeResourceRules;
      this.matchPolicy = matchPolicy;
      this.namespaceSelector = namespaceSelector;
      this.objectSelector = objectSelector;
      this.resourceRules = resourceRules;
   }

   @JsonProperty("excludeResourceRules")
   @JsonInclude(Include.NON_EMPTY)
   public List getExcludeResourceRules() {
      return this.excludeResourceRules;
   }

   @JsonProperty("excludeResourceRules")
   public void setExcludeResourceRules(List excludeResourceRules) {
      this.excludeResourceRules = excludeResourceRules;
   }

   @JsonProperty("matchPolicy")
   public String getMatchPolicy() {
      return this.matchPolicy;
   }

   @JsonProperty("matchPolicy")
   public void setMatchPolicy(String matchPolicy) {
      this.matchPolicy = matchPolicy;
   }

   @JsonProperty("namespaceSelector")
   public LabelSelector getNamespaceSelector() {
      return this.namespaceSelector;
   }

   @JsonProperty("namespaceSelector")
   public void setNamespaceSelector(LabelSelector namespaceSelector) {
      this.namespaceSelector = namespaceSelector;
   }

   @JsonProperty("objectSelector")
   public LabelSelector getObjectSelector() {
      return this.objectSelector;
   }

   @JsonProperty("objectSelector")
   public void setObjectSelector(LabelSelector objectSelector) {
      this.objectSelector = objectSelector;
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

   @JsonIgnore
   public MatchResourcesBuilder edit() {
      return new MatchResourcesBuilder(this);
   }

   @JsonIgnore
   public MatchResourcesBuilder toBuilder() {
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
      List var10000 = this.getExcludeResourceRules();
      return "MatchResources(excludeResourceRules=" + var10000 + ", matchPolicy=" + this.getMatchPolicy() + ", namespaceSelector=" + this.getNamespaceSelector() + ", objectSelector=" + this.getObjectSelector() + ", resourceRules=" + this.getResourceRules() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MatchResources)) {
         return false;
      } else {
         MatchResources other = (MatchResources)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$excludeResourceRules = this.getExcludeResourceRules();
            Object other$excludeResourceRules = other.getExcludeResourceRules();
            if (this$excludeResourceRules == null) {
               if (other$excludeResourceRules != null) {
                  return false;
               }
            } else if (!this$excludeResourceRules.equals(other$excludeResourceRules)) {
               return false;
            }

            Object this$matchPolicy = this.getMatchPolicy();
            Object other$matchPolicy = other.getMatchPolicy();
            if (this$matchPolicy == null) {
               if (other$matchPolicy != null) {
                  return false;
               }
            } else if (!this$matchPolicy.equals(other$matchPolicy)) {
               return false;
            }

            Object this$namespaceSelector = this.getNamespaceSelector();
            Object other$namespaceSelector = other.getNamespaceSelector();
            if (this$namespaceSelector == null) {
               if (other$namespaceSelector != null) {
                  return false;
               }
            } else if (!this$namespaceSelector.equals(other$namespaceSelector)) {
               return false;
            }

            Object this$objectSelector = this.getObjectSelector();
            Object other$objectSelector = other.getObjectSelector();
            if (this$objectSelector == null) {
               if (other$objectSelector != null) {
                  return false;
               }
            } else if (!this$objectSelector.equals(other$objectSelector)) {
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
      return other instanceof MatchResources;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $excludeResourceRules = this.getExcludeResourceRules();
      result = result * 59 + ($excludeResourceRules == null ? 43 : $excludeResourceRules.hashCode());
      Object $matchPolicy = this.getMatchPolicy();
      result = result * 59 + ($matchPolicy == null ? 43 : $matchPolicy.hashCode());
      Object $namespaceSelector = this.getNamespaceSelector();
      result = result * 59 + ($namespaceSelector == null ? 43 : $namespaceSelector.hashCode());
      Object $objectSelector = this.getObjectSelector();
      result = result * 59 + ($objectSelector == null ? 43 : $objectSelector.hashCode());
      Object $resourceRules = this.getResourceRules();
      result = result * 59 + ($resourceRules == null ? 43 : $resourceRules.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
