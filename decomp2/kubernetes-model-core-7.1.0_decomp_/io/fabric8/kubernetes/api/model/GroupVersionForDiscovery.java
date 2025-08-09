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
@JsonPropertyOrder({"groupVersion", "version"})
public class GroupVersionForDiscovery implements Editable, KubernetesResource {
   @JsonProperty("groupVersion")
   private String groupVersion;
   @JsonProperty("version")
   private String version;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GroupVersionForDiscovery() {
   }

   public GroupVersionForDiscovery(String groupVersion, String version) {
      this.groupVersion = groupVersion;
      this.version = version;
   }

   @JsonProperty("groupVersion")
   public String getGroupVersion() {
      return this.groupVersion;
   }

   @JsonProperty("groupVersion")
   public void setGroupVersion(String groupVersion) {
      this.groupVersion = groupVersion;
   }

   @JsonProperty("version")
   public String getVersion() {
      return this.version;
   }

   @JsonProperty("version")
   public void setVersion(String version) {
      this.version = version;
   }

   @JsonIgnore
   public GroupVersionForDiscoveryBuilder edit() {
      return new GroupVersionForDiscoveryBuilder(this);
   }

   @JsonIgnore
   public GroupVersionForDiscoveryBuilder toBuilder() {
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
      String var10000 = this.getGroupVersion();
      return "GroupVersionForDiscovery(groupVersion=" + var10000 + ", version=" + this.getVersion() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GroupVersionForDiscovery)) {
         return false;
      } else {
         GroupVersionForDiscovery other = (GroupVersionForDiscovery)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$groupVersion = this.getGroupVersion();
            Object other$groupVersion = other.getGroupVersion();
            if (this$groupVersion == null) {
               if (other$groupVersion != null) {
                  return false;
               }
            } else if (!this$groupVersion.equals(other$groupVersion)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
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
      return other instanceof GroupVersionForDiscovery;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $groupVersion = this.getGroupVersion();
      result = result * 59 + ($groupVersion == null ? 43 : $groupVersion.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
