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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "driverRequests", "generatedFrom", "shareable"})
@Version("v1alpha2")
@Group("resource.k8s.io")
public class ResourceClaimParameters implements Editable, HasMetadata, Namespaced {
   @JsonProperty("apiVersion")
   private String apiVersion = "resource.k8s.io/v1alpha2";
   @JsonProperty("driverRequests")
   @JsonInclude(Include.NON_EMPTY)
   private List driverRequests = new ArrayList();
   @JsonProperty("generatedFrom")
   private ResourceClaimParametersReference generatedFrom;
   @JsonProperty("kind")
   private String kind = "ResourceClaimParameters";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("shareable")
   private Boolean shareable;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimParameters() {
   }

   public ResourceClaimParameters(String apiVersion, List driverRequests, ResourceClaimParametersReference generatedFrom, String kind, ObjectMeta metadata, Boolean shareable) {
      this.apiVersion = apiVersion;
      this.driverRequests = driverRequests;
      this.generatedFrom = generatedFrom;
      this.kind = kind;
      this.metadata = metadata;
      this.shareable = shareable;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("driverRequests")
   @JsonInclude(Include.NON_EMPTY)
   public List getDriverRequests() {
      return this.driverRequests;
   }

   @JsonProperty("driverRequests")
   public void setDriverRequests(List driverRequests) {
      this.driverRequests = driverRequests;
   }

   @JsonProperty("generatedFrom")
   public ResourceClaimParametersReference getGeneratedFrom() {
      return this.generatedFrom;
   }

   @JsonProperty("generatedFrom")
   public void setGeneratedFrom(ResourceClaimParametersReference generatedFrom) {
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

   @JsonProperty("shareable")
   public Boolean getShareable() {
      return this.shareable;
   }

   @JsonProperty("shareable")
   public void setShareable(Boolean shareable) {
      this.shareable = shareable;
   }

   @JsonIgnore
   public ResourceClaimParametersBuilder edit() {
      return new ResourceClaimParametersBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimParametersBuilder toBuilder() {
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
      return "ResourceClaimParameters(apiVersion=" + var10000 + ", driverRequests=" + this.getDriverRequests() + ", generatedFrom=" + this.getGeneratedFrom() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", shareable=" + this.getShareable() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimParameters)) {
         return false;
      } else {
         ResourceClaimParameters other = (ResourceClaimParameters)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$shareable = this.getShareable();
            Object other$shareable = other.getShareable();
            if (this$shareable == null) {
               if (other$shareable != null) {
                  return false;
               }
            } else if (!this$shareable.equals(other$shareable)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$driverRequests = this.getDriverRequests();
            Object other$driverRequests = other.getDriverRequests();
            if (this$driverRequests == null) {
               if (other$driverRequests != null) {
                  return false;
               }
            } else if (!this$driverRequests.equals(other$driverRequests)) {
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
      return other instanceof ResourceClaimParameters;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $shareable = this.getShareable();
      result = result * 59 + ($shareable == null ? 43 : $shareable.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $driverRequests = this.getDriverRequests();
      result = result * 59 + ($driverRequests == null ? 43 : $driverRequests.hashCode());
      Object $generatedFrom = this.getGeneratedFrom();
      result = result * 59 + ($generatedFrom == null ? 43 : $generatedFrom.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
