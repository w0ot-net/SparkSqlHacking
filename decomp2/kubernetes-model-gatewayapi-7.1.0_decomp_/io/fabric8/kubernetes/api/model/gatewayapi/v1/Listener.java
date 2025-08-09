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
@JsonPropertyOrder({"allowedRoutes", "hostname", "name", "port", "protocol", "tls"})
public class Listener implements Editable, KubernetesResource {
   @JsonProperty("allowedRoutes")
   private AllowedRoutes allowedRoutes;
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("name")
   private String name;
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("protocol")
   private String protocol;
   @JsonProperty("tls")
   private GatewayTLSConfig tls;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Listener() {
   }

   public Listener(AllowedRoutes allowedRoutes, String hostname, String name, Integer port, String protocol, GatewayTLSConfig tls) {
      this.allowedRoutes = allowedRoutes;
      this.hostname = hostname;
      this.name = name;
      this.port = port;
      this.protocol = protocol;
      this.tls = tls;
   }

   @JsonProperty("allowedRoutes")
   public AllowedRoutes getAllowedRoutes() {
      return this.allowedRoutes;
   }

   @JsonProperty("allowedRoutes")
   public void setAllowedRoutes(AllowedRoutes allowedRoutes) {
      this.allowedRoutes = allowedRoutes;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("port")
   public Integer getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(Integer port) {
      this.port = port;
   }

   @JsonProperty("protocol")
   public String getProtocol() {
      return this.protocol;
   }

   @JsonProperty("protocol")
   public void setProtocol(String protocol) {
      this.protocol = protocol;
   }

   @JsonProperty("tls")
   public GatewayTLSConfig getTls() {
      return this.tls;
   }

   @JsonProperty("tls")
   public void setTls(GatewayTLSConfig tls) {
      this.tls = tls;
   }

   @JsonIgnore
   public ListenerBuilder edit() {
      return new ListenerBuilder(this);
   }

   @JsonIgnore
   public ListenerBuilder toBuilder() {
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
      AllowedRoutes var10000 = this.getAllowedRoutes();
      return "Listener(allowedRoutes=" + var10000 + ", hostname=" + this.getHostname() + ", name=" + this.getName() + ", port=" + this.getPort() + ", protocol=" + this.getProtocol() + ", tls=" + this.getTls() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Listener)) {
         return false;
      } else {
         Listener other = (Listener)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$port = this.getPort();
            Object other$port = other.getPort();
            if (this$port == null) {
               if (other$port != null) {
                  return false;
               }
            } else if (!this$port.equals(other$port)) {
               return false;
            }

            Object this$allowedRoutes = this.getAllowedRoutes();
            Object other$allowedRoutes = other.getAllowedRoutes();
            if (this$allowedRoutes == null) {
               if (other$allowedRoutes != null) {
                  return false;
               }
            } else if (!this$allowedRoutes.equals(other$allowedRoutes)) {
               return false;
            }

            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
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

            Object this$protocol = this.getProtocol();
            Object other$protocol = other.getProtocol();
            if (this$protocol == null) {
               if (other$protocol != null) {
                  return false;
               }
            } else if (!this$protocol.equals(other$protocol)) {
               return false;
            }

            Object this$tls = this.getTls();
            Object other$tls = other.getTls();
            if (this$tls == null) {
               if (other$tls != null) {
                  return false;
               }
            } else if (!this$tls.equals(other$tls)) {
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
      return other instanceof Listener;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $allowedRoutes = this.getAllowedRoutes();
      result = result * 59 + ($allowedRoutes == null ? 43 : $allowedRoutes.hashCode());
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $protocol = this.getProtocol();
      result = result * 59 + ($protocol == null ? 43 : $protocol.hashCode());
      Object $tls = this.getTls();
      result = result * 59 + ($tls == null ? 43 : $tls.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
