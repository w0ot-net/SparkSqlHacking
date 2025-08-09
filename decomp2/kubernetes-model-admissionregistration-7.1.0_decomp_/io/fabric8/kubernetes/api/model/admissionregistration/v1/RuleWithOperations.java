package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
@JsonPropertyOrder({"apiGroups", "apiVersions", "operations", "resources", "scope"})
public class RuleWithOperations implements Editable, KubernetesResource {
   @JsonProperty("apiGroups")
   @JsonInclude(Include.NON_EMPTY)
   private List apiGroups = new ArrayList();
   @JsonProperty("apiVersions")
   @JsonInclude(Include.NON_EMPTY)
   private List apiVersions = new ArrayList();
   @JsonProperty("operations")
   @JsonInclude(Include.NON_EMPTY)
   private List operations = new ArrayList();
   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   private List resources = new ArrayList();
   @JsonProperty("scope")
   private String scope;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RuleWithOperations() {
   }

   public RuleWithOperations(List apiGroups, List apiVersions, List operations, List resources, String scope) {
      this.apiGroups = apiGroups;
      this.apiVersions = apiVersions;
      this.operations = operations;
      this.resources = resources;
      this.scope = scope;
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

   @JsonProperty("apiVersions")
   @JsonInclude(Include.NON_EMPTY)
   public List getApiVersions() {
      return this.apiVersions;
   }

   @JsonProperty("apiVersions")
   public void setApiVersions(List apiVersions) {
      this.apiVersions = apiVersions;
   }

   @JsonProperty("operations")
   @JsonInclude(Include.NON_EMPTY)
   public List getOperations() {
      return this.operations;
   }

   @JsonProperty("operations")
   public void setOperations(List operations) {
      this.operations = operations;
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

   @JsonProperty("scope")
   public String getScope() {
      return this.scope;
   }

   @JsonProperty("scope")
   public void setScope(String scope) {
      this.scope = scope;
   }

   @JsonIgnore
   public RuleWithOperationsBuilder edit() {
      return new RuleWithOperationsBuilder(this);
   }

   @JsonIgnore
   public RuleWithOperationsBuilder toBuilder() {
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
      return "RuleWithOperations(apiGroups=" + var10000 + ", apiVersions=" + this.getApiVersions() + ", operations=" + this.getOperations() + ", resources=" + this.getResources() + ", scope=" + this.getScope() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RuleWithOperations)) {
         return false;
      } else {
         RuleWithOperations other = (RuleWithOperations)o;
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

            Object this$apiVersions = this.getApiVersions();
            Object other$apiVersions = other.getApiVersions();
            if (this$apiVersions == null) {
               if (other$apiVersions != null) {
                  return false;
               }
            } else if (!this$apiVersions.equals(other$apiVersions)) {
               return false;
            }

            Object this$operations = this.getOperations();
            Object other$operations = other.getOperations();
            if (this$operations == null) {
               if (other$operations != null) {
                  return false;
               }
            } else if (!this$operations.equals(other$operations)) {
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

            Object this$scope = this.getScope();
            Object other$scope = other.getScope();
            if (this$scope == null) {
               if (other$scope != null) {
                  return false;
               }
            } else if (!this$scope.equals(other$scope)) {
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
      return other instanceof RuleWithOperations;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiGroups = this.getApiGroups();
      result = result * 59 + ($apiGroups == null ? 43 : $apiGroups.hashCode());
      Object $apiVersions = this.getApiVersions();
      result = result * 59 + ($apiVersions == null ? 43 : $apiVersions.hashCode());
      Object $operations = this.getOperations();
      result = result * 59 + ($operations == null ? 43 : $operations.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $scope = this.getScope();
      result = result * 59 + ($scope == null ? 43 : $scope.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
