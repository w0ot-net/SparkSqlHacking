package io.fabric8.kubernetes.api.model.discovery.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "addressType", "endpoints", "ports"})
@Version("v1beta1")
@Group("discovery.k8s.io")
public class EndpointSlice implements Editable, HasMetadata, Namespaced {
   @JsonProperty("addressType")
   private String addressType;
   @JsonProperty("apiVersion")
   private String apiVersion = "discovery.k8s.io/v1beta1";
   @JsonProperty("endpoints")
   @JsonInclude(Include.NON_EMPTY)
   private List endpoints = new ArrayList();
   @JsonProperty("kind")
   private String kind = "EndpointSlice";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   private List ports = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EndpointSlice() {
   }

   public EndpointSlice(String addressType, String apiVersion, List endpoints, String kind, ObjectMeta metadata, List ports) {
      this.addressType = addressType;
      this.apiVersion = apiVersion;
      this.endpoints = endpoints;
      this.kind = kind;
      this.metadata = metadata;
      this.ports = ports;
   }

   @JsonProperty("addressType")
   public String getAddressType() {
      return this.addressType;
   }

   @JsonProperty("addressType")
   public void setAddressType(String addressType) {
      this.addressType = addressType;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("endpoints")
   @JsonInclude(Include.NON_EMPTY)
   public List getEndpoints() {
      return this.endpoints;
   }

   @JsonProperty("endpoints")
   public void setEndpoints(List endpoints) {
      this.endpoints = endpoints;
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

   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   public List getPorts() {
      return this.ports;
   }

   @JsonProperty("ports")
   public void setPorts(List ports) {
      this.ports = ports;
   }

   @JsonIgnore
   public EndpointSliceBuilder edit() {
      return new EndpointSliceBuilder(this);
   }

   @JsonIgnore
   public EndpointSliceBuilder toBuilder() {
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
      String var10000 = this.getAddressType();
      return "EndpointSlice(addressType=" + var10000 + ", apiVersion=" + this.getApiVersion() + ", endpoints=" + this.getEndpoints() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", ports=" + this.getPorts() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EndpointSlice)) {
         return false;
      } else {
         EndpointSlice other = (EndpointSlice)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$addressType = this.getAddressType();
            Object other$addressType = other.getAddressType();
            if (this$addressType == null) {
               if (other$addressType != null) {
                  return false;
               }
            } else if (!this$addressType.equals(other$addressType)) {
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

            Object this$endpoints = this.getEndpoints();
            Object other$endpoints = other.getEndpoints();
            if (this$endpoints == null) {
               if (other$endpoints != null) {
                  return false;
               }
            } else if (!this$endpoints.equals(other$endpoints)) {
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

            Object this$ports = this.getPorts();
            Object other$ports = other.getPorts();
            if (this$ports == null) {
               if (other$ports != null) {
                  return false;
               }
            } else if (!this$ports.equals(other$ports)) {
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
      return other instanceof EndpointSlice;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addressType = this.getAddressType();
      result = result * 59 + ($addressType == null ? 43 : $addressType.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $endpoints = this.getEndpoints();
      result = result * 59 + ($endpoints == null ? 43 : $endpoints.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $ports = this.getPorts();
      result = result * 59 + ($ports == null ? 43 : $ports.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
