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
@JsonPropertyOrder({"namedResources", "vendorParameters"})
public class ResourceRequest implements Editable, KubernetesResource {
   @JsonProperty("namedResources")
   private NamedResourcesRequest namedResources;
   @JsonProperty("vendorParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   private Object vendorParameters;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceRequest() {
   }

   public ResourceRequest(NamedResourcesRequest namedResources, Object vendorParameters) {
      this.namedResources = namedResources;
      this.vendorParameters = vendorParameters;
   }

   @JsonProperty("namedResources")
   public NamedResourcesRequest getNamedResources() {
      return this.namedResources;
   }

   @JsonProperty("namedResources")
   public void setNamedResources(NamedResourcesRequest namedResources) {
      this.namedResources = namedResources;
   }

   @JsonProperty("vendorParameters")
   public Object getVendorParameters() {
      return this.vendorParameters;
   }

   @JsonProperty("vendorParameters")
   @JsonDeserialize(
      using = KubernetesDeserializer.class
   )
   public void setVendorParameters(Object vendorParameters) {
      this.vendorParameters = vendorParameters;
   }

   @JsonIgnore
   public ResourceRequestBuilder edit() {
      return new ResourceRequestBuilder(this);
   }

   @JsonIgnore
   public ResourceRequestBuilder toBuilder() {
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
      NamedResourcesRequest var10000 = this.getNamedResources();
      return "ResourceRequest(namedResources=" + var10000 + ", vendorParameters=" + this.getVendorParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceRequest)) {
         return false;
      } else {
         ResourceRequest other = (ResourceRequest)o;
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

            Object this$vendorParameters = this.getVendorParameters();
            Object other$vendorParameters = other.getVendorParameters();
            if (this$vendorParameters == null) {
               if (other$vendorParameters != null) {
                  return false;
               }
            } else if (!this$vendorParameters.equals(other$vendorParameters)) {
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
      return other instanceof ResourceRequest;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $namedResources = this.getNamedResources();
      result = result * 59 + ($namedResources == null ? 43 : $namedResources.hashCode());
      Object $vendorParameters = this.getVendorParameters();
      result = result * 59 + ($vendorParameters == null ? 43 : $vendorParameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
