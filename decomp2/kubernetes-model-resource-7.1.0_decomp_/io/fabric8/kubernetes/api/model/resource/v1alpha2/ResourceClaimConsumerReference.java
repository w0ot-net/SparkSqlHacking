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
@JsonPropertyOrder({"apiGroup", "name", "resource", "uid"})
public class ResourceClaimConsumerReference implements Editable, KubernetesResource {
   @JsonProperty("apiGroup")
   private String apiGroup;
   @JsonProperty("name")
   private String name;
   @JsonProperty("resource")
   private String resource;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClaimConsumerReference() {
   }

   public ResourceClaimConsumerReference(String apiGroup, String name, String resource, String uid) {
      this.apiGroup = apiGroup;
      this.name = name;
      this.resource = resource;
      this.uid = uid;
   }

   @JsonProperty("apiGroup")
   public String getApiGroup() {
      return this.apiGroup;
   }

   @JsonProperty("apiGroup")
   public void setApiGroup(String apiGroup) {
      this.apiGroup = apiGroup;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("resource")
   public String getResource() {
      return this.resource;
   }

   @JsonProperty("resource")
   public void setResource(String resource) {
      this.resource = resource;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public ResourceClaimConsumerReferenceBuilder edit() {
      return new ResourceClaimConsumerReferenceBuilder(this);
   }

   @JsonIgnore
   public ResourceClaimConsumerReferenceBuilder toBuilder() {
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
      String var10000 = this.getApiGroup();
      return "ResourceClaimConsumerReference(apiGroup=" + var10000 + ", name=" + this.getName() + ", resource=" + this.getResource() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClaimConsumerReference)) {
         return false;
      } else {
         ResourceClaimConsumerReference other = (ResourceClaimConsumerReference)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$apiGroup = this.getApiGroup();
            Object other$apiGroup = other.getApiGroup();
            if (this$apiGroup == null) {
               if (other$apiGroup != null) {
                  return false;
               }
            } else if (!this$apiGroup.equals(other$apiGroup)) {
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

            Object this$resource = this.getResource();
            Object other$resource = other.getResource();
            if (this$resource == null) {
               if (other$resource != null) {
                  return false;
               }
            } else if (!this$resource.equals(other$resource)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof ResourceClaimConsumerReference;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $apiGroup = this.getApiGroup();
      result = result * 59 + ($apiGroup == null ? 43 : $apiGroup.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $resource = this.getResource();
      result = result * 59 + ($resource == null ? 43 : $resource.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
