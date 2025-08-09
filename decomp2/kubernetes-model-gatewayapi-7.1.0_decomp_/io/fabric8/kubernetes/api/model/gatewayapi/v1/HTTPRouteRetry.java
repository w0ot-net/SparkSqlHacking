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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"attempts", "backoff", "codes"})
public class HTTPRouteRetry implements Editable, KubernetesResource {
   @JsonProperty("attempts")
   private Integer attempts;
   @JsonProperty("backoff")
   private String backoff;
   @JsonProperty("codes")
   @JsonInclude(Include.NON_EMPTY)
   private List codes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPRouteRetry() {
   }

   public HTTPRouteRetry(Integer attempts, String backoff, List codes) {
      this.attempts = attempts;
      this.backoff = backoff;
      this.codes = codes;
   }

   @JsonProperty("attempts")
   public Integer getAttempts() {
      return this.attempts;
   }

   @JsonProperty("attempts")
   public void setAttempts(Integer attempts) {
      this.attempts = attempts;
   }

   @JsonProperty("backoff")
   public String getBackoff() {
      return this.backoff;
   }

   @JsonProperty("backoff")
   public void setBackoff(String backoff) {
      this.backoff = backoff;
   }

   @JsonProperty("codes")
   @JsonInclude(Include.NON_EMPTY)
   public List getCodes() {
      return this.codes;
   }

   @JsonProperty("codes")
   public void setCodes(List codes) {
      this.codes = codes;
   }

   @JsonIgnore
   public HTTPRouteRetryBuilder edit() {
      return new HTTPRouteRetryBuilder(this);
   }

   @JsonIgnore
   public HTTPRouteRetryBuilder toBuilder() {
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
      Integer var10000 = this.getAttempts();
      return "HTTPRouteRetry(attempts=" + var10000 + ", backoff=" + this.getBackoff() + ", codes=" + this.getCodes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPRouteRetry)) {
         return false;
      } else {
         HTTPRouteRetry other = (HTTPRouteRetry)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attempts = this.getAttempts();
            Object other$attempts = other.getAttempts();
            if (this$attempts == null) {
               if (other$attempts != null) {
                  return false;
               }
            } else if (!this$attempts.equals(other$attempts)) {
               return false;
            }

            Object this$backoff = this.getBackoff();
            Object other$backoff = other.getBackoff();
            if (this$backoff == null) {
               if (other$backoff != null) {
                  return false;
               }
            } else if (!this$backoff.equals(other$backoff)) {
               return false;
            }

            Object this$codes = this.getCodes();
            Object other$codes = other.getCodes();
            if (this$codes == null) {
               if (other$codes != null) {
                  return false;
               }
            } else if (!this$codes.equals(other$codes)) {
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
      return other instanceof HTTPRouteRetry;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attempts = this.getAttempts();
      result = result * 59 + ($attempts == null ? 43 : $attempts.hashCode());
      Object $backoff = this.getBackoff();
      result = result * 59 + ($backoff == null ? 43 : $backoff.hashCode());
      Object $codes = this.getCodes();
      result = result * 59 + ($codes == null ? 43 : $codes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
