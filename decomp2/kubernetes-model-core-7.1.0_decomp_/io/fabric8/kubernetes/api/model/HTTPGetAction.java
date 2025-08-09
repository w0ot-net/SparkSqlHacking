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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"host", "httpHeaders", "path", "port", "scheme"})
public class HTTPGetAction implements Editable, KubernetesResource {
   @JsonProperty("host")
   private String host;
   @JsonProperty("httpHeaders")
   @JsonInclude(Include.NON_EMPTY)
   private List httpHeaders = new ArrayList();
   @JsonProperty("path")
   private String path;
   @JsonProperty("port")
   private IntOrString port;
   @JsonProperty("scheme")
   private String scheme;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPGetAction() {
   }

   public HTTPGetAction(String host, List httpHeaders, String path, IntOrString port, String scheme) {
      this.host = host;
      this.httpHeaders = httpHeaders;
      this.path = path;
      this.port = port;
      this.scheme = scheme;
   }

   @JsonProperty("host")
   public String getHost() {
      return this.host;
   }

   @JsonProperty("host")
   public void setHost(String host) {
      this.host = host;
   }

   @JsonProperty("httpHeaders")
   @JsonInclude(Include.NON_EMPTY)
   public List getHttpHeaders() {
      return this.httpHeaders;
   }

   @JsonProperty("httpHeaders")
   public void setHttpHeaders(List httpHeaders) {
      this.httpHeaders = httpHeaders;
   }

   @JsonProperty("path")
   public String getPath() {
      return this.path;
   }

   @JsonProperty("path")
   public void setPath(String path) {
      this.path = path;
   }

   @JsonProperty("port")
   public IntOrString getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(IntOrString port) {
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

   @JsonIgnore
   public HTTPGetActionBuilder edit() {
      return new HTTPGetActionBuilder(this);
   }

   @JsonIgnore
   public HTTPGetActionBuilder toBuilder() {
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
      return "HTTPGetAction(host=" + var10000 + ", httpHeaders=" + this.getHttpHeaders() + ", path=" + this.getPath() + ", port=" + this.getPort() + ", scheme=" + this.getScheme() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPGetAction)) {
         return false;
      } else {
         HTTPGetAction other = (HTTPGetAction)o;
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

            Object this$httpHeaders = this.getHttpHeaders();
            Object other$httpHeaders = other.getHttpHeaders();
            if (this$httpHeaders == null) {
               if (other$httpHeaders != null) {
                  return false;
               }
            } else if (!this$httpHeaders.equals(other$httpHeaders)) {
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

            Object this$port = this.getPort();
            Object other$port = other.getPort();
            if (this$port == null) {
               if (other$port != null) {
                  return false;
               }
            } else if (!this$port.equals(other$port)) {
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
      return other instanceof HTTPGetAction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $host = this.getHost();
      result = result * 59 + ($host == null ? 43 : $host.hashCode());
      Object $httpHeaders = this.getHttpHeaders();
      result = result * 59 + ($httpHeaders == null ? 43 : $httpHeaders.hashCode());
      Object $path = this.getPath();
      result = result * 59 + ($path == null ? 43 : $path.hashCode());
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $scheme = this.getScheme();
      result = result * 59 + ($scheme == null ? 43 : $scheme.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
