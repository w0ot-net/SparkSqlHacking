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
@JsonPropertyOrder({"hostname", "path", "port", "scheme", "statusCode"})
public class HTTPRequestRedirectFilter implements Editable, KubernetesResource {
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("path")
   private HTTPPathModifier path;
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("scheme")
   private String scheme;
   @JsonProperty("statusCode")
   private Integer statusCode;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPRequestRedirectFilter() {
   }

   public HTTPRequestRedirectFilter(String hostname, HTTPPathModifier path, Integer port, String scheme, Integer statusCode) {
      this.hostname = hostname;
      this.path = path;
      this.port = port;
      this.scheme = scheme;
      this.statusCode = statusCode;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("path")
   public HTTPPathModifier getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(HTTPPathModifier path) {
      this.path = path;
   }

   @JsonProperty("port")
   public Integer getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(Integer port) {
      this.port = port;
   }

   @JsonProperty("scheme")
   public String getScheme() {
      return this.scheme;
   }

   @JsonProperty("scheme")
   public void setScheme(String scheme) {
      this.scheme = scheme;
   }

   @JsonProperty("statusCode")
   public Integer getStatusCode() {
      return this.statusCode;
   }

   @JsonProperty("statusCode")
   public void setStatusCode(Integer statusCode) {
      this.statusCode = statusCode;
   }

   @JsonIgnore
   public HTTPRequestRedirectFilterBuilder edit() {
      return new HTTPRequestRedirectFilterBuilder(this);
   }

   @JsonIgnore
   public HTTPRequestRedirectFilterBuilder toBuilder() {
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
      String var10000 = this.getHostname();
      return "HTTPRequestRedirectFilter(hostname=" + var10000 + ", path=" + this.getPath() + ", port=" + this.getPort() + ", scheme=" + this.getScheme() + ", statusCode=" + this.getStatusCode() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPRequestRedirectFilter)) {
         return false;
      } else {
         HTTPRequestRedirectFilter other = (HTTPRequestRedirectFilter)o;
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

            Object this$statusCode = this.getStatusCode();
            Object other$statusCode = other.getStatusCode();
            if (this$statusCode == null) {
               if (other$statusCode != null) {
                  return false;
               }
            } else if (!this$statusCode.equals(other$statusCode)) {
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

            Object this$path = this.getPath();
            Object other$path = other.getPath();
            if (this$path == null) {
               if (other$path != null) {
                  return false;
               }
            } else if (!this$path.equals(other$path)) {
               return false;
            }

            Object this$scheme = this.getScheme();
            Object other$scheme = other.getScheme();
            if (this$scheme == null) {
               if (other$scheme != null) {
                  return false;
               }
            } else if (!this$scheme.equals(other$scheme)) {
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
      return other instanceof HTTPRequestRedirectFilter;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $statusCode = this.getStatusCode();
      result = result * 59 + ($statusCode == null ? 43 : $statusCode.hashCode());
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $scheme = this.getScheme();
      result = result * 59 + ($scheme == null ? 43 : $scheme.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
