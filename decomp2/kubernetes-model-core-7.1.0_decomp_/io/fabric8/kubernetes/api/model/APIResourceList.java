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
@JsonPropertyOrder({"apiVersion", "kind", "groupVersion", "resources"})
@Version("v1")
@Group("")
public class APIResourceList implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("groupVersion")
   private String groupVersion;
   @JsonProperty("kind")
   private String kind = "APIResourceList";
   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   private List resources = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public APIResourceList() {
   }

   public APIResourceList(String apiVersion, String groupVersion, String kind, List resources) {
      this.apiVersion = apiVersion;
      this.groupVersion = groupVersion;
      this.kind = kind;
      this.resources = resources;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("groupVersion")
   public String getGroupVersion() {
      return this.groupVersion;
   }

   @JsonProperty("groupVersion")
   public void setGroupVersion(String groupVersion) {
      this.groupVersion = groupVersion;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("resources")
   @JsonInclude(Include.NON_EMPTY)
   public List getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(List resources) {
      this.resources = resources;
   }

   @JsonIgnore
   public APIResourceListBuilder edit() {
      return new APIResourceListBuilder(this);
   }

   @JsonIgnore
   public APIResourceListBuilder toBuilder() {
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
      return "APIResourceList(apiVersion=" + var10000 + ", groupVersion=" + this.getGroupVersion() + ", kind=" + this.getKind() + ", resources=" + this.getResources() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof APIResourceList)) {
         return false;
      } else {
         APIResourceList other = (APIResourceList)o;
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

            Object this$groupVersion = this.getGroupVersion();
            Object other$groupVersion = other.getGroupVersion();
            if (this$groupVersion == null) {
               if (other$groupVersion != null) {
                  return false;
               }
            } else if (!this$groupVersion.equals(other$groupVersion)) {
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

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
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
      return other instanceof APIResourceList;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $groupVersion = this.getGroupVersion();
      result = result * 59 + ($groupVersion == null ? 43 : $groupVersion.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
