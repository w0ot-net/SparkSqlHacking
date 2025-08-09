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
@JsonPropertyOrder({"error", "port", "protocol"})
public class PortStatus implements Editable, KubernetesResource {
   @JsonProperty("error")
   private String error;
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("protocol")
   private String protocol;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PortStatus() {
   }

   public PortStatus(String error, Integer port, String protocol) {
      this.error = error;
      this.port = port;
      this.protocol = protocol;
   }

   @JsonProperty("error")
   public String getError() {
      return this.error;
   }

   @JsonProperty("error")
   public void setError(String error) {
      this.error = error;
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

   @JsonIgnore
   public PortStatusBuilder edit() {
      return new PortStatusBuilder(this);
   }

   @JsonIgnore
   public PortStatusBuilder toBuilder() {
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
      String var10000 = this.getError();
      return "PortStatus(error=" + var10000 + ", port=" + this.getPort() + ", protocol=" + this.getProtocol() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PortStatus)) {
         return false;
      } else {
         PortStatus other = (PortStatus)o;
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

            Object this$error = this.getError();
            Object other$error = other.getError();
            if (this$error == null) {
               if (other$error != null) {
                  return false;
               }
            } else if (!this$error.equals(other$error)) {
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
      return other instanceof PortStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $error = this.getError();
      result = result * 59 + ($error == null ? 43 : $error.hashCode());
      Object $protocol = this.getProtocol();
      result = result * 59 + ($protocol == null ? 43 : $protocol.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
