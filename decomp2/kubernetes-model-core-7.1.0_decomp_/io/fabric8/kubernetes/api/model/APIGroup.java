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
@JsonPropertyOrder({"apiVersion", "kind", "name", "preferredVersion", "serverAddressByClientCIDRs", "versions"})
@Version("v1")
@Group("")
public class APIGroup implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("kind")
   private String kind = "APIGroup";
   @JsonProperty("name")
   private String name;
   @JsonProperty("preferredVersion")
   private GroupVersionForDiscovery preferredVersion;
   @JsonProperty("serverAddressByClientCIDRs")
   @JsonInclude(Include.NON_EMPTY)
   private List serverAddressByClientCIDRs = new ArrayList();
   @JsonProperty("versions")
   @JsonInclude(Include.NON_EMPTY)
   private List versions = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public APIGroup() {
   }

   public APIGroup(String apiVersion, String kind, String name, GroupVersionForDiscovery preferredVersion, List serverAddressByClientCIDRs, List versions) {
      this.apiVersion = apiVersion;
      this.kind = kind;
      this.name = name;
      this.preferredVersion = preferredVersion;
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

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("preferredVersion")
   public GroupVersionForDiscovery getPreferredVersion() {
      return this.preferredVersion;
   }

   @JsonProperty("preferredVersion")
   public void setPreferredVersion(GroupVersionForDiscovery preferredVersion) {
      this.preferredVersion = preferredVersion;
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
   public APIGroupBuilder edit() {
      return new APIGroupBuilder(this);
   }

   @JsonIgnore
   public APIGroupBuilder toBuilder() {
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
      return "APIGroup(apiVersion=" + var10000 + ", kind=" + this.getKind() + ", name=" + this.getName() + ", preferredVersion=" + this.getPreferredVersion() + ", serverAddressByClientCIDRs=" + this.getServerAddressByClientCIDRs() + ", versions=" + this.getVersions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof APIGroup)) {
         return false;
      } else {
         APIGroup other = (APIGroup)o;
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

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$preferredVersion = this.getPreferredVersion();
            Object other$preferredVersion = other.getPreferredVersion();
            if (this$preferredVersion == null) {
               if (other$preferredVersion != null) {
                  return false;
               }
            } else if (!this$preferredVersion.equals(other$preferredVersion)) {
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
      return other instanceof APIGroup;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $preferredVersion = this.getPreferredVersion();
      result = result * 59 + ($preferredVersion == null ? 43 : $preferredVersion.hashCode());
      Object $serverAddressByClientCIDRs = this.getServerAddressByClientCIDRs();
      result = result * 59 + ($serverAddressByClientCIDRs == null ? 43 : $serverAddressByClientCIDRs.hashCode());
      Object $versions = this.getVersions();
      result = result * 59 + ($versions == null ? 43 : $versions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
