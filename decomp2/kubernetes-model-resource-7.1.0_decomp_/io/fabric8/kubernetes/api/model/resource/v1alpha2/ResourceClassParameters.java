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
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "filters", "generatedFrom", "vendorParameters"})
@Version("v1alpha2")
@Group("resource.k8s.io")
public class ResourceClassParameters implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "resource.k8s.io/v1alpha2";
   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   private List filters = new ArrayList();
   @JsonProperty("generatedFrom")
   private ResourceClassParametersReference generatedFrom;
   @JsonProperty("kind")
   private String kind = "ResourceClassParameters";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("vendorParameters")
   @JsonInclude(Include.NON_EMPTY)
   private List vendorParameters = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClassParameters() {
   }

   public ResourceClassParameters(String apiVersion, List filters, ResourceClassParametersReference generatedFrom, String kind, ObjectMeta metadata, List vendorParameters) {
      this.apiVersion = apiVersion;
      this.filters = filters;
      this.generatedFrom = generatedFrom;
      this.kind = kind;
      this.metadata = metadata;
      this.vendorParameters = vendorParameters;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   public List getFilters() {
      return this.filters;
   }

   @JsonProperty("filters")
   public void setFilters(List filters) {
      this.filters = filters;
   }

   @JsonProperty("generatedFrom")
   public ResourceClassParametersReference getGeneratedFrom() {
      return this.generatedFrom;
   }

   @JsonProperty("generatedFrom")
   public void setGeneratedFrom(ResourceClassParametersReference generatedFrom) {
      this.generatedFrom = generatedFrom;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("vendorParameters")
   @JsonInclude(Include.NON_EMPTY)
   public List getVendorParameters() {
      return this.vendorParameters;
   }

   @JsonProperty("vendorParameters")
   public void setVendorParameters(List vendorParameters) {
      this.vendorParameters = vendorParameters;
   }

   @JsonIgnore
   public ResourceClassParametersBuilder edit() {
      return new ResourceClassParametersBuilder(this);
   }

   @JsonIgnore
   public ResourceClassParametersBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "ResourceClassParameters(apiVersion=" + var10000 + ", filters=" + this.getFilters() + ", generatedFrom=" + this.getGeneratedFrom() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", vendorParameters=" + this.getVendorParameters() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClassParameters)) {
         return false;
      } else {
         ResourceClassParameters other = (ResourceClassParameters)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$filters = this.getFilters();
            Object other$filters = other.getFilters();
            if (this$filters == null) {
               if (other$filters != null) {
                  return false;
               }
            } else if (!this$filters.equals(other$filters)) {
               return false;
            }

            Object this$generatedFrom = this.getGeneratedFrom();
            Object other$generatedFrom = other.getGeneratedFrom();
            if (this$generatedFrom == null) {
               if (other$generatedFrom != null) {
                  return false;
               }
            } else if (!this$generatedFrom.equals(other$generatedFrom)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
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
      return other instanceof ResourceClassParameters;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $filters = this.getFilters();
      result = result * 59 + ($filters == null ? 43 : $filters.hashCode());
      Object $generatedFrom = this.getGeneratedFrom();
      result = result * 59 + ($generatedFrom == null ? 43 : $generatedFrom.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $vendorParameters = this.getVendorParameters();
      result = result * 59 + ($vendorParameters == null ? 43 : $vendorParameters.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
