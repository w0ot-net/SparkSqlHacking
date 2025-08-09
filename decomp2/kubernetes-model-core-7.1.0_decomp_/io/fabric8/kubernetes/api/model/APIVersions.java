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
@JsonPropertyOrder({"apiVersion", "kind", "serverAddressByClientCIDRs", "versions"})
@Version("v1")
@Group("")
public class APIVersions implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("kind")
   private String kind = "APIVersions";
   @JsonProperty("serverAddressByClientCIDRs")
   @JsonInclude(Include.NON_EMPTY)
   private List serverAddressByClientCIDRs = new ArrayList();
   @JsonProperty("versions")
   @JsonInclude(Include.NON_EMPTY)
   private List versions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public APIVersions() {
   }

   public APIVersions(String apiVersion, String kind, List serverAddressByClientCIDRs, List versions) {
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.serverAddressByClientCIDRs = serverAddressByClientCIDRs;
      this.versions = versions;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("serverAddressByClientCIDRs")
   @JsonInclude(Include.NON_EMPTY)
   public List getServerAddressByClientCIDRs() {
      return this.serverAddressByClientCIDRs;
   }

   @JsonProperty("serverAddressByClientCIDRs")
   public void setServerAddressByClientCIDRs(List serverAddressByClientCIDRs) {
      this.serverAddressByClientCIDRs = serverAddressByClientCIDRs;
   }

   @JsonProperty("versions")
   @JsonInclude(Include.NON_EMPTY)
   public List getVersions() {
      return this.versions;
   }

   @JsonProperty("versions")
   public void setVersions(List versions) {
      this.versions = versions;
   }

   @JsonIgnore
   public APIVersionsBuilder edit() {
      return new APIVersionsBuilder(this);
   }

   @JsonIgnore
   public APIVersionsBuilder toBuilder() {
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
      return "APIVersions(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", serverAddressByClientCIDRs=" + this.getServerAddressByClientCIDRs() + ", versions=" + this.getVersions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof APIVersions)) {
         return false;
      } else {
         APIVersions other = (APIVersions)o;
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

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$serverAddressByClientCIDRs = this.getServerAddressByClientCIDRs();
            Object other$serverAddressByClientCIDRs = other.getServerAddressByClientCIDRs();
            if (this$serverAddressByClientCIDRs == null) {
               if (other$serverAddressByClientCIDRs != null) {
                  return false;
               }
            } else if (!this$serverAddressByClientCIDRs.equals(other$serverAddressByClientCIDRs)) {
               return false;
            }

            Object this$versions = this.getVersions();
            Object other$versions = other.getVersions();
            if (this$versions == null) {
               if (other$versions != null) {
                  return false;
               }
            } else if (!this$versions.equals(other$versions)) {
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
      return other instanceof APIVersions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $serverAddressByClientCIDRs = this.getServerAddressByClientCIDRs();
      result = result * 59 + ($serverAddressByClientCIDRs == null ? 43 : $serverAddressByClientCIDRs.hashCode());
      Object $versions = this.getVersions();
      result = result * 59 + ($versions == null ? 43 : $versions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
