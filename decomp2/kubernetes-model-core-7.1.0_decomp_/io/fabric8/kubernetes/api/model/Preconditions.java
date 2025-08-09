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
@JsonPropertyOrder({"resourceVersion", "uid"})
public class Preconditions implements Editable, KubernetesResource {
   @JsonProperty("resourceVersion")
   private String resourceVersion;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Preconditions() {
   }

   public Preconditions(String resourceVersion, String uid) {
      this.resourceVersion = resourceVersion;
      this.uid = uid;
   }

   @JsonProperty("resourceVersion")
   public String getResourceVersion() {
      return this.resourceVersion;
   }

   @JsonProperty("resourceVersion")
   public void setResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
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
   public PreconditionsBuilder edit() {
      return new PreconditionsBuilder(this);
   }

   @JsonIgnore
   public PreconditionsBuilder toBuilder() {
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
      String var10000 = this.getResourceVersion();
      return "Preconditions(resourceVersion=" + var10000 + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Preconditions)) {
         return false;
      } else {
         Preconditions other = (Preconditions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$resourceVersion = this.getResourceVersion();
            Object other$resourceVersion = other.getResourceVersion();
            if (this$resourceVersion == null) {
               if (other$resourceVersion != null) {
                  return false;
               }
            } else if (!this$resourceVersion.equals(other$resourceVersion)) {
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
      return other instanceof Preconditions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $resourceVersion = this.getResourceVersion();
      result = result * 59 + ($resourceVersion == null ? 43 : $resourceVersion.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
