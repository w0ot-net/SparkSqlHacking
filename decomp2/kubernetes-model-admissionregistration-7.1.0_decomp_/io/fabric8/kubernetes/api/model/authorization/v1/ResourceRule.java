package io.fabric8.kubernetes.api.model.authorization.v1;

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
@JsonPropertyOrder({"apiGroups", "resourceNames", "resources", "verbs"})
public class ResourceRule implements Editable, KubernetesResource {
   @JsonProperty("apiGroups")
   @JsonInclude(Include.NON_EMPTY)
   private List apiGroups = new ArrayList();
   @JsonProperty("resourceNames")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceNames = new ArrayList();
   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   private List resources = new ArrayList();
   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   private List verbs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceRule() {
   }

   public ResourceRule(List apiGroups, List resourceNames, List resources, List verbs) {
      this.apiGroups = apiGroups;
      this.resourceNames = resourceNames;
      this.resources = resources;
      this.verbs = verbs;
   }

   @JsonProperty("apiGroups")
   @JsonInclude(Include.NON_EMPTY)
   public List getApiGroups() {
      return this.apiGroups;
   }

   @JsonProperty("apiGroups")
   public void setApiGroups(List apiGroups) {
      this.apiGroups = apiGroups;
   }

   @JsonProperty("resourceNames")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceNames() {
      return this.resourceNames;
   }

   @JsonProperty("resourceNames")
   public void setResourceNames(List resourceNames) {
      this.resourceNames = resourceNames;
   }

   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   public List getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(List resources) {
      this.resources = resources;
   }

   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   public List getVerbs() {
      return this.verbs;
   }

   @JsonProperty("verbs")
   public void setVerbs(List verbs) {
      this.verbs = verbs;
   }

   @JsonIgnore
   public ResourceRuleBuilder edit() {
      return new ResourceRuleBuilder(this);
   }

   @JsonIgnore
   public ResourceRuleBuilder toBuilder() {
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
      List var10000 = this.getApiGroups();
      return "ResourceRule(apiGroups=" + var10000 + ", resourceNames=" + this.getResourceNames() + ", resources=" + this.getResources() + ", verbs=" + this.getVerbs() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceRule)) {
         return false;
      } else {
         ResourceRule other = (ResourceRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiGroups = this.getApiGroups();
            Object other$apiGroups = other.getApiGroups();
            if (this$apiGroups == null) {
               if (other$apiGroups != null) {
                  return false;
               }
            } else if (!this$apiGroups.equals(other$apiGroups)) {
               return false;
            }

            Object this$resourceNames = this.getResourceNames();
            Object other$resourceNames = other.getResourceNames();
            if (this$resourceNames == null) {
               if (other$resourceNames != null) {
                  return false;
               }
            } else if (!this$resourceNames.equals(other$resourceNames)) {
               return false;
            }

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
               return false;
            }

            Object this$verbs = this.getVerbs();
            Object other$verbs = other.getVerbs();
            if (this$verbs == null) {
               if (other$verbs != null) {
                  return false;
               }
            } else if (!this$verbs.equals(other$verbs)) {
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
      return other instanceof ResourceRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiGroups = this.getApiGroups();
      result = result * 59 + ($apiGroups == null ? 43 : $apiGroups.hashCode());
      Object $resourceNames = this.getResourceNames();
      result = result * 59 + ($resourceNames == null ? 43 : $resourceNames.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $verbs = this.getVerbs();
      result = result * 59 + ($verbs == null ? 43 : $verbs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
