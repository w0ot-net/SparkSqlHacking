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
@JsonPropertyOrder({"clientIP"})
public class SessionAffinityConfig implements Editable, KubernetesResource {
   @JsonProperty("clientIP")
   private ClientIPConfig clientIP;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SessionAffinityConfig() {
   }

   public SessionAffinityConfig(ClientIPConfig clientIP) {
      this.clientIP = clientIP;
   }

   @JsonProperty("clientIP")
   public ClientIPConfig getClientIP() {
      return this.clientIP;
   }

   @JsonProperty("clientIP")
   public void setClientIP(ClientIPConfig clientIP) {
      this.clientIP = clientIP;
   }

   @JsonIgnore
   public SessionAffinityConfigBuilder edit() {
      return new SessionAffinityConfigBuilder(this);
   }

   @JsonIgnore
   public SessionAffinityConfigBuilder toBuilder() {
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
      ClientIPConfig var10000 = this.getClientIP();
      return "SessionAffinityConfig(clientIP=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SessionAffinityConfig)) {
         return false;
      } else {
         SessionAffinityConfig other = (SessionAffinityConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clientIP = this.getClientIP();
            Object other$clientIP = other.getClientIP();
            if (this$clientIP == null) {
               if (other$clientIP != null) {
                  return false;
               }
            } else if (!this$clientIP.equals(other$clientIP)) {
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
      return other instanceof SessionAffinityConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clientIP = this.getClientIP();
      result = result * 59 + ($clientIP == null ? 43 : $clientIP.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
