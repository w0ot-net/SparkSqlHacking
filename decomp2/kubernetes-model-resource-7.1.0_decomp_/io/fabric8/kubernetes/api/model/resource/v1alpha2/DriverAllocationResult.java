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
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"namedResources", "vendorRequestParameters"})
public class DriverAllocationResult implements Editable, KubernetesResource {
   @JsonProperty("namedResources")
   private NamedResourcesAllocationResult namedResources;
   @JsonProperty("vendorRequestParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object vendorRequestParameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DriverAllocationResult() {
   }

   public DriverAllocationResult(NamedResourcesAllocationResult namedResources, Object vendorRequestParameters) {
      this.namedResources = namedResources;
      this.vendorRequestParameters = vendorRequestParameters;
   }

   @JsonProperty("namedResources")
   public NamedResourcesAllocationResult getNamedResources() {
      return this.namedResources;
   }

   @JsonProperty("namedResources")
   public void setNamedResources(NamedResourcesAllocationResult namedResources) {
      this.namedResources = namedResources;
   }

   @JsonProperty("vendorRequestParameters")
   public Object getVendorRequestParameters() {
      return this.vendorRequestParameters;
   }

   @JsonProperty("vendorRequestParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setVendorRequestParameters(Object vendorRequestParameters) {
      this.vendorRequestParameters = vendorRequestParameters;
   }

   @JsonIgnore
   public DriverAllocationResultBuilder edit() {
      return new DriverAllocationResultBuilder(this);
   }

   @JsonIgnore
   public DriverAllocationResultBuilder toBuilder() {
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
      NamedResourcesAllocationResult var10000 = this.getNamedResources();
      return "DriverAllocationResult(namedResources=" + var10000 + ", vendorRequestParameters=" + this.getVendorRequestParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DriverAllocationResult)) {
         return false;
      } else {
         DriverAllocationResult other = (DriverAllocationResult)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$namedResources = this.getNamedResources();
            Object other$namedResources = other.getNamedResources();
            if (this$namedResources == null) {
               if (other$namedResources != null) {
                  return false;
               }
            } else if (!this$namedResources.equals(other$namedResources)) {
               return false;
            }

            Object this$vendorRequestParameters = this.getVendorRequestParameters();
            Object other$vendorRequestParameters = other.getVendorRequestParameters();
            if (this$vendorRequestParameters == null) {
               if (other$vendorRequestParameters != null) {
                  return false;
               }
            } else if (!this$vendorRequestParameters.equals(other$vendorRequestParameters)) {
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
      return other instanceof DriverAllocationResult;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $namedResources = this.getNamedResources();
      result = result * 59 + ($namedResources == null ? 43 : $namedResources.hashCode());
      Object $vendorRequestParameters = this.getVendorRequestParameters();
      result = result * 59 + ($vendorRequestParameters == null ? 43 : $vendorRequestParameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
