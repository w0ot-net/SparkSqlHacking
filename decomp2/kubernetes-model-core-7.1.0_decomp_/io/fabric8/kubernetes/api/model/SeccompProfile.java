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
@JsonPropertyOrder({"localhostProfile", "type"})
public class SeccompProfile implements Editable, KubernetesResource {
   @JsonProperty("localhostProfile")
   private String localhostProfile;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SeccompProfile() {
   }

   public SeccompProfile(String localhostProfile, String type) {
      this.localhostProfile = localhostProfile;
      this.type = type;
   }

   @JsonProperty("localhostProfile")
   public String getLocalhostProfile() {
      return this.localhostProfile;
   }

   @JsonProperty("localhostProfile")
   public void setLocalhostProfile(String localhostProfile) {
      this.localhostProfile = localhostProfile;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public SeccompProfileBuilder edit() {
      return new SeccompProfileBuilder(this);
   }

   @JsonIgnore
   public SeccompProfileBuilder toBuilder() {
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
      String var10000 = this.getLocalhostProfile();
      return "SeccompProfile(localhostProfile=" + var10000 + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SeccompProfile)) {
         return false;
      } else {
         SeccompProfile other = (SeccompProfile)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$localhostProfile = this.getLocalhostProfile();
            Object other$localhostProfile = other.getLocalhostProfile();
            if (this$localhostProfile == null) {
               if (other$localhostProfile != null) {
                  return false;
               }
            } else if (!this$localhostProfile.equals(other$localhostProfile)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof SeccompProfile;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $localhostProfile = this.getLocalhostProfile();
      result = result * 59 + ($localhostProfile == null ? 43 : $localhostProfile.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
