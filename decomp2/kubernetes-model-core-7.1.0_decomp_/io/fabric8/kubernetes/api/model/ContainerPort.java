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
@JsonPropertyOrder({"containerPort", "hostIP", "hostPort", "name", "protocol"})
public class ContainerPort implements Editable, KubernetesResource {
   @JsonProperty("containerPort")
   private Integer containerPort;
   @JsonProperty("hostIP")
   private String hostIP;
   @JsonProperty("hostPort")
   private Integer hostPort;
   @JsonProperty("name")
   private String name;
   @JsonProperty("protocol")
   private String protocol;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerPort() {
   }

   public ContainerPort(Integer containerPort, String hostIP, Integer hostPort, String name, String protocol) {
      this.containerPort = containerPort;
      this.hostIP = hostIP;
      this.hostPort = hostPort;
      this.name = name;
      this.protocol = protocol;
   }

   @JsonProperty("containerPort")
   public Integer getContainerPort() {
      return this.containerPort;
   }

   @JsonProperty("containerPort")
   public void setContainerPort(Integer containerPort) {
      this.containerPort = containerPort;
   }

   @JsonProperty("hostIP")
   public String getHostIP() {
      return this.hostIP;
   }

   @JsonProperty("hostIP")
   public void setHostIP(String hostIP) {
      this.hostIP = hostIP;
   }

   @JsonProperty("hostPort")
   public Integer getHostPort() {
      return this.hostPort;
   }

   @JsonProperty("hostPort")
   public void setHostPort(Integer hostPort) {
      this.hostPort = hostPort;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
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
   public ContainerPortBuilder edit() {
      return new ContainerPortBuilder(this);
   }

   @JsonIgnore
   public ContainerPortBuilder toBuilder() {
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
      Integer var10000 = this.getContainerPort();
      return "ContainerPort(containerPort=" + var10000 + ", hostIP=" + this.getHostIP() + ", hostPort=" + this.getHostPort() + ", name=" + this.getName() + ", protocol=" + this.getProtocol() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerPort)) {
         return false;
      } else {
         ContainerPort other = (ContainerPort)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$containerPort = this.getContainerPort();
            Object other$containerPort = other.getContainerPort();
            if (this$containerPort == null) {
               if (other$containerPort != null) {
                  return false;
               }
            } else if (!this$containerPort.equals(other$containerPort)) {
               return false;
            }

            Object this$hostPort = this.getHostPort();
            Object other$hostPort = other.getHostPort();
            if (this$hostPort == null) {
               if (other$hostPort != null) {
                  return false;
               }
            } else if (!this$hostPort.equals(other$hostPort)) {
               return false;
            }

            Object this$hostIP = this.getHostIP();
            Object other$hostIP = other.getHostIP();
            if (this$hostIP == null) {
               if (other$hostIP != null) {
                  return false;
               }
            } else if (!this$hostIP.equals(other$hostIP)) {
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
      return other instanceof ContainerPort;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $containerPort = this.getContainerPort();
      result = result * 59 + ($containerPort == null ? 43 : $containerPort.hashCode());
      Object $hostPort = this.getHostPort();
      result = result * 59 + ($hostPort == null ? 43 : $hostPort.hashCode());
      Object $hostIP = this.getHostIP();
      result = result * 59 + ($hostIP == null ? 43 : $hostIP.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $protocol = this.getProtocol();
      result = result * 59 + ($protocol == null ? 43 : $protocol.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
