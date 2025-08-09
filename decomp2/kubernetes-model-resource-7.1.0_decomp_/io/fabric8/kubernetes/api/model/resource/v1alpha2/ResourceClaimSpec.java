package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"allocationMode", "parametersRef", "resourceClassName"})
public class ResourceClaimSpec implements Editable, KubernetesResource {
   @JsonProperty("allocationMode")
   private String allocationMode;
   @JsonProperty("parametersRef")
   private ResourceClaimParametersReference parametersRef;
   @JsonProperty("resourceClassName")
   private String resourceClassName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimSpec() {
   }

   public ResourceClaimSpec(String allocationMode, ResourceClaimParametersReference parametersRef, String resourceClassName) {
      this.allocationMode = allocationMode;
      this.parametersRef = parametersRef;
      this.resourceClassName = resourceClassName;
   }

   @JsonProperty("allocationMode")
   public String getAllocationMode() {
      return this.allocationMode;
   }

   @JsonProperty("allocationMode")
   public void setAllocationMode(String allocationMode) {
      this.allocationMode = allocationMode;
   }

   @JsonProperty("parametersRef")
   public ResourceClaimParametersReference getParametersRef() {
      return this.parametersRef;
   }

   @JsonProperty("parametersRef")
   public void setParametersRef(ResourceClaimParametersReference parametersRef) {
      this.parametersRef = parametersRef;
   }

   @JsonProperty("resourceClassName")
   public String getResourceClassName() {
      return this.resourceClassName;
   }

   @JsonProperty("resourceClassName")
   public void setResourceClassName(String resourceClassName) {
      this.resourceClassName = resourceClassName;
   }

   @JsonIgnore
   public ResourceClaimSpecBuilder edit() {
      return new ResourceClaimSpecBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimSpecBuilder toBuilder() {
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
      String var10000 = this.getAllocationMode();
      return "ResourceClaimSpec(allocationMode=" + var10000 + ", parametersRef=" + this.getParametersRef() + ", resourceClassName=" + this.getResourceClassName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimSpec)) {
         return false;
      } else {
         ResourceClaimSpec other = (ResourceClaimSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allocationMode = this.getAllocationMode();
            Object other$allocationMode = other.getAllocationMode();
            if (this$allocationMode == null) {
               if (other$allocationMode != null) {
                  return false;
               }
            } else if (!this$allocationMode.equals(other$allocationMode)) {
               return false;
            }

            Object this$parametersRef = this.getParametersRef();
            Object other$parametersRef = other.getParametersRef();
            if (this$parametersRef == null) {
               if (other$parametersRef != null) {
                  return false;
               }
            } else if (!this$parametersRef.equals(other$parametersRef)) {
               return false;
            }

            Object this$resourceClassName = this.getResourceClassName();
            Object other$resourceClassName = other.getResourceClassName();
            if (this$resourceClassName == null) {
               if (other$resourceClassName != null) {
                  return false;
               }
            } else if (!this$resourceClassName.equals(other$resourceClassName)) {
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
      return other instanceof ResourceClaimSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allocationMode = this.getAllocationMode();
      result = result * 59 + ($allocationMode == null ? 43 : $allocationMode.hashCode());
      Object $parametersRef = this.getParametersRef();
      result = result * 59 + ($parametersRef == null ? 43 : $parametersRef.hashCode());
      Object $resourceClassName = this.getResourceClassName();
      result = result * 59 + ($resourceClassName == null ? 43 : $resourceClassName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
