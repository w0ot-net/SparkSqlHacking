package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"lifetimeType"})
public class CookieConfig implements Editable, KubernetesResource {
   @JsonProperty("lifetimeType")
   private String lifetimeType;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CookieConfig() {
   }

   public CookieConfig(String lifetimeType) {
      this.lifetimeType = lifetimeType;
   }

   @JsonProperty("lifetimeType")
   public String getLifetimeType() {
      return this.lifetimeType;
   }

   @JsonProperty("lifetimeType")
   public void setLifetimeType(String lifetimeType) {
      this.lifetimeType = lifetimeType;
   }

   @JsonIgnore
   public CookieConfigBuilder edit() {
      return new CookieConfigBuilder(this);
   }

   @JsonIgnore
   public CookieConfigBuilder toBuilder() {
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
      String var10000 = this.getLifetimeType();
      return "CookieConfig(lifetimeType=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CookieConfig)) {
         return false;
      } else {
         CookieConfig other = (CookieConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$lifetimeType = this.getLifetimeType();
            Object other$lifetimeType = other.getLifetimeType();
            if (this$lifetimeType == null) {
               if (other$lifetimeType != null) {
                  return false;
               }
            } else if (!this$lifetimeType.equals(other$lifetimeType)) {
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
      return other instanceof CookieConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $lifetimeType = this.getLifetimeType();
      result = result * 59 + ($lifetimeType == null ? 43 : $lifetimeType.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
