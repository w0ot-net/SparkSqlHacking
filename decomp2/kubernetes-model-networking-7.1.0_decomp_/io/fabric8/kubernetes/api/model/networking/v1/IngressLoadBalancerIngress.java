package io.fabric8.kubernetes.api.model.networking.v1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"hostname", "ip", "ports"})
public class IngressLoadBalancerIngress implements Editable, KubernetesResource {
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("ip")
   private String ip;
   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   private List ports = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IngressLoadBalancerIngress() {
   }

   public IngressLoadBalancerIngress(String hostname, String ip, List ports) {
      this.hostname = hostname;
      this.ip = ip;
      this.ports = ports;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("ip")
   public String getIp() {
      return this.ip;
   }

   @JsonProperty("ip")
   public void setIp(String ip) {
      this.ip = ip;
   }

   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   public List getPorts() {
      return this.ports;
   }

   @JsonProperty("ports")
   public void setPorts(List ports) {
      this.ports = ports;
   }

   @JsonIgnore
   public IngressLoadBalancerIngressBuilder edit() {
      return new IngressLoadBalancerIngressBuilder(this);
   }

   @JsonIgnore
   public IngressLoadBalancerIngressBuilder toBuilder() {
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
      return "IngressLoadBalancerIngress(hostname=" + var10000 + ", ip=" + this.getIp() + ", ports=" + this.getPorts() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IngressLoadBalancerIngress)) {
         return false;
      } else {
         IngressLoadBalancerIngress other = (IngressLoadBalancerIngress)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
               return false;
            }

            Object this$ip = this.getIp();
            Object other$ip = other.getIp();
            if (this$ip == null) {
               if (other$ip != null) {
                  return false;
               }
            } else if (!this$ip.equals(other$ip)) {
               return false;
            }

            Object this$ports = this.getPorts();
            Object other$ports = other.getPorts();
            if (this$ports == null) {
               if (other$ports != null) {
                  return false;
               }
            } else if (!this$ports.equals(other$ports)) {
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
      return other instanceof IngressLoadBalancerIngress;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $ip = this.getIp();
      result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
      Object $ports = this.getPorts();
      result = result * 59 + ($ports == null ? 43 : $ports.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
