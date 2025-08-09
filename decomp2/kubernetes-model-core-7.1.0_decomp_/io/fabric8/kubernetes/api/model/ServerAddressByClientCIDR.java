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
@JsonPropertyOrder({"clientCIDR", "serverAddress"})
public class ServerAddressByClientCIDR implements Editable, KubernetesResource {
   @JsonProperty("clientCIDR")
   private String clientCIDR;
   @JsonProperty("serverAddress")
   private String serverAddress;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ServerAddressByClientCIDR() {
   }

   public ServerAddressByClientCIDR(String clientCIDR, String serverAddress) {
      this.clientCIDR = clientCIDR;
      this.serverAddress = serverAddress;
   }

   @JsonProperty("clientCIDR")
   public String getClientCIDR() {
      return this.clientCIDR;
   }

   @JsonProperty("clientCIDR")
   public void setClientCIDR(String clientCIDR) {
      this.clientCIDR = clientCIDR;
   }

   @JsonProperty("serverAddress")
   public String getServerAddress() {
      return this.serverAddress;
   }

   @JsonProperty("serverAddress")
   public void setServerAddress(String serverAddress) {
      this.serverAddress = serverAddress;
   }

   @JsonIgnore
   public ServerAddressByClientCIDRBuilder edit() {
      return new ServerAddressByClientCIDRBuilder(this);
   }

   @JsonIgnore
   public ServerAddressByClientCIDRBuilder toBuilder() {
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
      String var10000 = this.getClientCIDR();
      return "ServerAddressByClientCIDR(clientCIDR=" + var10000 + ", serverAddress=" + this.getServerAddress() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServerAddressByClientCIDR)) {
         return false;
      } else {
         ServerAddressByClientCIDR other = (ServerAddressByClientCIDR)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clientCIDR = this.getClientCIDR();
            Object other$clientCIDR = other.getClientCIDR();
            if (this$clientCIDR == null) {
               if (other$clientCIDR != null) {
                  return false;
               }
            } else if (!this$clientCIDR.equals(other$clientCIDR)) {
               return false;
            }

            Object this$serverAddress = this.getServerAddress();
            Object other$serverAddress = other.getServerAddress();
            if (this$serverAddress == null) {
               if (other$serverAddress != null) {
                  return false;
               }
            } else if (!this$serverAddress.equals(other$serverAddress)) {
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
      return other instanceof ServerAddressByClientCIDR;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $clientCIDR = this.getClientCIDR();
      result = result * 59 + ($clientCIDR == null ? 43 : $clientCIDR.hashCode());
      Object $serverAddress = this.getServerAddress();
      result = result * 59 + ($serverAddress == null ? 43 : $serverAddress.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
