package io.fabric8.kubernetes.api.model.storage.v1beta1;

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
@JsonPropertyOrder({"audience", "expirationSeconds"})
public class TokenRequest implements Editable, KubernetesResource {
   @JsonProperty("audience")
   private String audience;
   @JsonProperty("expirationSeconds")
   private Long expirationSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TokenRequest() {
   }

   public TokenRequest(String audience, Long expirationSeconds) {
      this.audience = audience;
      this.expirationSeconds = expirationSeconds;
   }

   @JsonProperty("audience")
   public String getAudience() {
      return this.audience;
   }

   @JsonProperty("audience")
   public void setAudience(String audience) {
      this.audience = audience;
   }

   @JsonProperty("expirationSeconds")
   public Long getExpirationSeconds() {
      return this.expirationSeconds;
   }

   @JsonProperty("expirationSeconds")
   public void setExpirationSeconds(Long expirationSeconds) {
      this.expirationSeconds = expirationSeconds;
   }

   @JsonIgnore
   public TokenRequestBuilder edit() {
      return new TokenRequestBuilder(this);
   }

   @JsonIgnore
   public TokenRequestBuilder toBuilder() {
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
      String var10000 = this.getAudience();
      return "TokenRequest(audience=" + var10000 + ", expirationSeconds=" + this.getExpirationSeconds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TokenRequest)) {
         return false;
      } else {
         TokenRequest other = (TokenRequest)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expirationSeconds = this.getExpirationSeconds();
            Object other$expirationSeconds = other.getExpirationSeconds();
            if (this$expirationSeconds == null) {
               if (other$expirationSeconds != null) {
                  return false;
               }
            } else if (!this$expirationSeconds.equals(other$expirationSeconds)) {
               return false;
            }

            Object this$audience = this.getAudience();
            Object other$audience = other.getAudience();
            if (this$audience == null) {
               if (other$audience != null) {
                  return false;
               }
            } else if (!this$audience.equals(other$audience)) {
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
      return other instanceof TokenRequest;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expirationSeconds = this.getExpirationSeconds();
      result = result * 59 + ($expirationSeconds == null ? 43 : $expirationSeconds.hashCode());
      Object $audience = this.getAudience();
      result = result * 59 + ($audience == null ? 43 : $audience.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
