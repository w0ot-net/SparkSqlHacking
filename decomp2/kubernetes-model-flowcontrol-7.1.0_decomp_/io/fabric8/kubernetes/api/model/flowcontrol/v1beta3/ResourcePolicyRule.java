package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

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
@JsonPropertyOrder({"apiGroups", "clusterScope", "namespaces", "resources", "verbs"})
public class ResourcePolicyRule implements Editable, KubernetesResource {
   @JsonProperty("apiGroups")
   @JsonInclude(Include.NON_EMPTY)
   private List apiGroups = new ArrayList();
   @JsonProperty("clusterScope")
   private Boolean clusterScope;
   @JsonProperty("namespaces")
   @JsonInclude(Include.NON_EMPTY)
   private List namespaces = new ArrayList();
   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   private List resources = new ArrayList();
   @JsonProperty("verbs")
   @JsonInclude(Include.NON_EMPTY)
   private List verbs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourcePolicyRule() {
   }

   public ResourcePolicyRule(List apiGroups, Boolean clusterScope, List namespaces, List resources, List verbs) {
      this.apiGroups = apiGroups;
      this.clusterScope = clusterScope;
      this.namespaces = namespaces;
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

   @JsonProperty("clusterScope")
   public Boolean getClusterScope() {
      return this.clusterScope;
   }

   @JsonProperty("clusterScope")
   public void setClusterScope(Boolean clusterScope) {
      this.clusterScope = clusterScope;
   }

   @JsonProperty("namespaces")
   @JsonInclude(Include.NON_EMPTY)
   public List getNamespaces() {
      return this.namespaces;
   }

   @JsonProperty("namespaces")
   public void setNamespaces(List namespaces) {
      this.namespaces = namespaces;
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
   public ResourcePolicyRuleBuilder edit() {
      return new ResourcePolicyRuleBuilder(this);
   }

   @JsonIgnore
   public ResourcePolicyRuleBuilder toBuilder() {
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
      return "ResourcePolicyRule(apiGroups=" + var10000 + ", clusterScope=" + this.getClusterScope() + ", namespaces=" + this.getNamespaces() + ", resources=" + this.getResources() + ", verbs=" + this.getVerbs() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePolicyRule)) {
         return false;
      } else {
         ResourcePolicyRule other = (ResourcePolicyRule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clusterScope = this.getClusterScope();
            Object other$clusterScope = other.getClusterScope();
            if (this$clusterScope == null) {
               if (other$clusterScope != null) {
                  return false;
               }
            } else if (!this$clusterScope.equals(other$clusterScope)) {
               return false;
            }

            Object this$apiGroups = this.getApiGroups();
            Object other$apiGroups = other.getApiGroups();
            if (this$apiGroups == null) {
               if (other$apiGroups != null) {
                  return false;
               }
            } else if (!this$apiGroups.equals(other$apiGroups)) {
               return false;
            }

            Object this$namespaces = this.getNamespaces();
            Object other$namespaces = other.getNamespaces();
            if (this$namespaces == null) {
               if (other$namespaces != null) {
                  return false;
               }
            } else if (!this$namespaces.equals(other$namespaces)) {
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
      return other instanceof ResourcePolicyRule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clusterScope = this.getClusterScope();
      result = result * 59 + ($clusterScope == null ? 43 : $clusterScope.hashCode());
      Object $apiGroups = this.getApiGroups();
      result = result * 59 + ($apiGroups == null ? 43 : $apiGroups.hashCode());
      Object $namespaces = this.getNamespaces();
      result = result * 59 + ($namespaces == null ? 43 : $namespaces.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $verbs = this.getVerbs();
      result = result * 59 + ($verbs == null ? 43 : $verbs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
