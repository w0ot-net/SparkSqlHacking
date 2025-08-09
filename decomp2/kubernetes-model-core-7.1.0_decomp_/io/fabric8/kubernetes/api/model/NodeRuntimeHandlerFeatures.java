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
@JsonPropertyOrder({"recursiveReadOnlyMounts", "userNamespaces"})
public class NodeRuntimeHandlerFeatures implements Editable, KubernetesResource {
   @JsonProperty("recursiveReadOnlyMounts")
   private Boolean recursiveReadOnlyMounts;
   @JsonProperty("userNamespaces")
   private Boolean userNamespaces;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeRuntimeHandlerFeatures() {
   }

   public NodeRuntimeHandlerFeatures(Boolean recursiveReadOnlyMounts, Boolean userNamespaces) {
      this.recursiveReadOnlyMounts = recursiveReadOnlyMounts;
      this.userNamespaces = userNamespaces;
   }

   @JsonProperty("recursiveReadOnlyMounts")
   public Boolean getRecursiveReadOnlyMounts() {
      return this.recursiveReadOnlyMounts;
   }

   @JsonProperty("recursiveReadOnlyMounts")
   public void setRecursiveReadOnlyMounts(Boolean recursiveReadOnlyMounts) {
      this.recursiveReadOnlyMounts = recursiveReadOnlyMounts;
   }

   @JsonProperty("userNamespaces")
   public Boolean getUserNamespaces() {
      return this.userNamespaces;
   }

   @JsonProperty("userNamespaces")
   public void setUserNamespaces(Boolean userNamespaces) {
      this.userNamespaces = userNamespaces;
   }

   @JsonIgnore
   public NodeRuntimeHandlerFeaturesBuilder edit() {
      return new NodeRuntimeHandlerFeaturesBuilder(this);
   }

   @JsonIgnore
   public NodeRuntimeHandlerFeaturesBuilder toBuilder() {
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
      Boolean var10000 = this.getRecursiveReadOnlyMounts();
      return "NodeRuntimeHandlerFeatures(recursiveReadOnlyMounts=" + var10000 + ", userNamespaces=" + this.getUserNamespaces() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeRuntimeHandlerFeatures)) {
         return false;
      } else {
         NodeRuntimeHandlerFeatures other = (NodeRuntimeHandlerFeatures)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$recursiveReadOnlyMounts = this.getRecursiveReadOnlyMounts();
            Object other$recursiveReadOnlyMounts = other.getRecursiveReadOnlyMounts();
            if (this$recursiveReadOnlyMounts == null) {
               if (other$recursiveReadOnlyMounts != null) {
                  return false;
               }
            } else if (!this$recursiveReadOnlyMounts.equals(other$recursiveReadOnlyMounts)) {
               return false;
            }

            Object this$userNamespaces = this.getUserNamespaces();
            Object other$userNamespaces = other.getUserNamespaces();
            if (this$userNamespaces == null) {
               if (other$userNamespaces != null) {
                  return false;
               }
            } else if (!this$userNamespaces.equals(other$userNamespaces)) {
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
      return other instanceof NodeRuntimeHandlerFeatures;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $recursiveReadOnlyMounts = this.getRecursiveReadOnlyMounts();
      result = result * 59 + ($recursiveReadOnlyMounts == null ? 43 : $recursiveReadOnlyMounts.hashCode());
      Object $userNamespaces = this.getUserNamespaces();
      result = result * 59 + ($userNamespaces == null ? 43 : $userNamespaces.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
