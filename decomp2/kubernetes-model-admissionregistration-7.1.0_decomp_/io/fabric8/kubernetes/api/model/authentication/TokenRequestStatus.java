package io.fabric8.kubernetes.api.model.authentication;

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
@JsonPropertyOrder({"expirationTimestamp", "token"})
public class TokenRequestStatus implements Editable, KubernetesResource {
   @JsonProperty("expirationTimestamp")
   private String expirationTimestamp;
   @JsonProperty("token")
   private String token;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TokenRequestStatus() {
   }

   public TokenRequestStatus(String expirationTimestamp, String token) {
      this.expirationTimestamp = expirationTimestamp;
      this.token = token;
   }

   @JsonProperty("expirationTimestamp")
   public String getExpirationTimestamp() {
      return this.expirationTimestamp;
   }

   @JsonProperty("expirationTimestamp")
   public void setExpirationTimestamp(String expirationTimestamp) {
      this.expirationTimestamp = expirationTimestamp;
   }

   @JsonProperty("token")
   public String getToken() {
      return this.token;
   }

   @JsonProperty("token")
   public void setToken(String token) {
      this.token = token;
   }

   @JsonIgnore
   public TokenRequestStatusBuilder edit() {
      return new TokenRequestStatusBuilder(this);
   }

   @JsonIgnore
   public TokenRequestStatusBuilder toBuilder() {
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
      String var10000 = this.getExpirationTimestamp();
      return "TokenRequestStatus(expirationTimestamp=" + var10000 + ", token=" + this.getToken() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TokenRequestStatus)) {
         return false;
      } else {
         TokenRequestStatus other = (TokenRequestStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$expirationTimestamp = this.getExpirationTimestamp();
            Object other$expirationTimestamp = other.getExpirationTimestamp();
            if (this$expirationTimestamp == null) {
               if (other$expirationTimestamp != null) {
                  return false;
               }
            } else if (!this$expirationTimestamp.equals(other$expirationTimestamp)) {
               return false;
            }

            Object this$token = this.getToken();
            Object other$token = other.getToken();
            if (this$token == null) {
               if (other$token != null) {
                  return false;
               }
            } else if (!this$token.equals(other$token)) {
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
      return other instanceof TokenRequestStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $expirationTimestamp = this.getExpirationTimestamp();
      result = result * 59 + ($expirationTimestamp == null ? 43 : $expirationTimestamp.hashCode());
      Object $token = this.getToken();
      result = result * 59 + ($token == null ? 43 : $token.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
