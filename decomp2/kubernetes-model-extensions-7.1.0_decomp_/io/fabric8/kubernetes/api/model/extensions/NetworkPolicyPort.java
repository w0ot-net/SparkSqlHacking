package io.fabric8.kubernetes.api.model.extensions;

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
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"port", "protocol"})
public class NetworkPolicyPort implements Editable, KubernetesResource {
   @JsonProperty("port")
   private IntOrString port;
   @JsonProperty("protocol")
   private String protocol;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NetworkPolicyPort() {
   }

   public NetworkPolicyPort(IntOrString port, String protocol) {
      this.port = port;
      this.protocol = protocol;
   }

   @JsonProperty("port")
   public IntOrString getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(IntOrString port) {
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
   public NetworkPolicyPortBuilder edit() {
      return new NetworkPolicyPortBuilder(this);
   }

   @JsonIgnore
   public NetworkPolicyPortBuilder toBuilder() {
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
      IntOrString var10000 = this.getPort();
      return "NetworkPolicyPort(port=" + var10000 + ", protocol=" + this.getProtocol() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkPolicyPort)) {
         return false;
      } else {
         NetworkPolicyPort other = (NetworkPolicyPort)o;
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
      return other instanceof NetworkPolicyPort;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $protocol = this.getProtocol();
      result = result * 59 + ($protocol == null ? 43 : $protocol.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
