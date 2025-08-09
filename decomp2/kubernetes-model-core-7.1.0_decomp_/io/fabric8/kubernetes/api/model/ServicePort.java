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
@JsonPropertyOrder({"appProtocol", "name", "nodePort", "port", "protocol", "targetPort"})
public class ServicePort implements Editable, KubernetesResource {
   @JsonProperty("appProtocol")
   private String appProtocol;
   @JsonProperty("name")
   private String name;
   @JsonProperty("nodePort")
   private Integer nodePort;
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("protocol")
   private String protocol;
   @JsonProperty("targetPort")
   private IntOrString targetPort;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ServicePort() {
   }

   public ServicePort(String appProtocol, String name, Integer nodePort, Integer port, String protocol, IntOrString targetPort) {
      this.appProtocol = appProtocol;
      this.name = name;
      this.nodePort = nodePort;
      this.port = port;
      this.protocol = protocol;
      this.targetPort = targetPort;
   }

   @JsonProperty("appProtocol")
   public String getAppProtocol() {
      return this.appProtocol;
   }

   @JsonProperty("appProtocol")
   public void setAppProtocol(String appProtocol) {
      this.appProtocol = appProtocol;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("nodePort")
   public Integer getNodePort() {
      return this.nodePort;
   }

   @JsonProperty("nodePort")
   public void setNodePort(Integer nodePort) {
      this.nodePort = nodePort;
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

   @JsonProperty("targetPort")
   public IntOrString getTargetPort() {
      return this.targetPort;
   }

   @JsonProperty("targetPort")
   public void setTargetPort(IntOrString targetPort) {
      this.targetPort = targetPort;
   }

   @JsonIgnore
   public ServicePortBuilder edit() {
      return new ServicePortBuilder(this);
   }

   @JsonIgnore
   public ServicePortBuilder toBuilder() {
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
      String var10000 = this.getAppProtocol();
      return "ServicePort(appProtocol=" + var10000 + ", name=" + this.getName() + ", nodePort=" + this.getNodePort() + ", port=" + this.getPort() + ", protocol=" + this.getProtocol() + ", targetPort=" + this.getTargetPort() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServicePort)) {
         return false;
      } else {
         ServicePort other = (ServicePort)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nodePort = this.getNodePort();
            Object other$nodePort = other.getNodePort();
            if (this$nodePort == null) {
               if (other$nodePort != null) {
                  return false;
               }
            } else if (!this$nodePort.equals(other$nodePort)) {
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

            Object this$appProtocol = this.getAppProtocol();
            Object other$appProtocol = other.getAppProtocol();
            if (this$appProtocol == null) {
               if (other$appProtocol != null) {
                  return false;
               }
            } else if (!this$appProtocol.equals(other$appProtocol)) {
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

            Object this$targetPort = this.getTargetPort();
            Object other$targetPort = other.getTargetPort();
            if (this$targetPort == null) {
               if (other$targetPort != null) {
                  return false;
               }
            } else if (!this$targetPort.equals(other$targetPort)) {
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
      return other instanceof ServicePort;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nodePort = this.getNodePort();
      result = result * 59 + ($nodePort == null ? 43 : $nodePort.hashCode());
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $appProtocol = this.getAppProtocol();
      result = result * 59 + ($appProtocol == null ? 43 : $appProtocol.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $protocol = this.getProtocol();
      result = result * 59 + ($protocol == null ? 43 : $protocol.hashCode());
      Object $targetPort = this.getTargetPort();
      result = result * 59 + ($targetPort == null ? 43 : $targetPort.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
