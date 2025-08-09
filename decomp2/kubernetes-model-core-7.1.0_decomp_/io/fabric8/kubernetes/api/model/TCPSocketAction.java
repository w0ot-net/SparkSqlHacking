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
@JsonPropertyOrder({"host", "port"})
public class TCPSocketAction implements Editable, KubernetesResource {
   @JsonProperty("host")
   private String host;
   @JsonProperty("port")
   private IntOrString port;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public TCPSocketAction() {
   }

   public TCPSocketAction(String host, IntOrString port) {
      this.host = host;
      this.port = port;
   }

   @JsonProperty("host")
   public String getHost() {
      return this.host;
   }

   @JsonProperty("host")
   public void setHost(String host) {
      this.host = host;
   }

   @JsonProperty("port")
   public IntOrString getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(IntOrString port) {
      this.port = port;
   }

   @JsonIgnore
   public TCPSocketActionBuilder edit() {
      return new TCPSocketActionBuilder(this);
   }

   @JsonIgnore
   public TCPSocketActionBuilder toBuilder() {
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
      String var10000 = this.getHost();
      return "TCPSocketAction(host=" + var10000 + ", port=" + this.getPort() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TCPSocketAction)) {
         return false;
      } else {
         TCPSocketAction other = (TCPSocketAction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$host = this.getHost();
            Object other$host = other.getHost();
            if (this$host == null) {
               if (other$host != null) {
                  return false;
               }
            } else if (!this$host.equals(other$host)) {
               return false;
            }

            Object this$port = this.getPort();
            Object other$port = other.getPort();
            if (this$port == null) {
               if (other$port != null) {
                  return false;
               }
            } else if (!this$port.equals(other$port)) {
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
      return other instanceof TCPSocketAction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $host = this.getHost();
      result = result * 59 + ($host == null ? 43 : $host.hashCode());
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
