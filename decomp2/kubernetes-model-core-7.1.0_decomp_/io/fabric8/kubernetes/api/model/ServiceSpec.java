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
@JsonPropertyOrder({"allocateLoadBalancerNodePorts", "clusterIP", "clusterIPs", "externalIPs", "externalName", "externalTrafficPolicy", "healthCheckNodePort", "internalTrafficPolicy", "ipFamilies", "ipFamilyPolicy", "loadBalancerClass", "loadBalancerIP", "loadBalancerSourceRanges", "ports", "publishNotReadyAddresses", "selector", "sessionAffinity", "sessionAffinityConfig", "trafficDistribution", "type"})
public class ServiceSpec implements Editable, KubernetesResource {
   @JsonProperty("allocateLoadBalancerNodePorts")
   private Boolean allocateLoadBalancerNodePorts;
   @JsonProperty("clusterIP")
   private String clusterIP;
   @JsonProperty("clusterIPs")
   @JsonInclude(Include.NON_EMPTY)
   private List clusterIPs = new ArrayList();
   @JsonProperty("externalIPs")
   @JsonInclude(Include.NON_EMPTY)
   private List externalIPs = new ArrayList();
   @JsonProperty("externalName")
   private String externalName;
   @JsonProperty("externalTrafficPolicy")
   private String externalTrafficPolicy;
   @JsonProperty("healthCheckNodePort")
   private Integer healthCheckNodePort;
   @JsonProperty("internalTrafficPolicy")
   private String internalTrafficPolicy;
   @JsonProperty("ipFamilies")
   @JsonInclude(Include.NON_EMPTY)
   private List ipFamilies = new ArrayList();
   @JsonProperty("ipFamilyPolicy")
   private String ipFamilyPolicy;
   @JsonProperty("loadBalancerClass")
   private String loadBalancerClass;
   @JsonProperty("loadBalancerIP")
   private String loadBalancerIP;
   @JsonProperty("loadBalancerSourceRanges")
   @JsonInclude(Include.NON_EMPTY)
   private List loadBalancerSourceRanges = new ArrayList();
   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   private List ports = new ArrayList();
   @JsonProperty("publishNotReadyAddresses")
   private Boolean publishNotReadyAddresses;
   @JsonProperty("selector")
   @JsonInclude(Include.NON_EMPTY)
   private Map selector = new LinkedHashMap();
   @JsonProperty("sessionAffinity")
   private String sessionAffinity;
   @JsonProperty("sessionAffinityConfig")
   private SessionAffinityConfig sessionAffinityConfig;
   @JsonProperty("trafficDistribution")
   private String trafficDistribution;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ServiceSpec() {
   }

   public ServiceSpec(Boolean allocateLoadBalancerNodePorts, String clusterIP, List clusterIPs, List externalIPs, String externalName, String externalTrafficPolicy, Integer healthCheckNodePort, String internalTrafficPolicy, List ipFamilies, String ipFamilyPolicy, String loadBalancerClass, String loadBalancerIP, List loadBalancerSourceRanges, List ports, Boolean publishNotReadyAddresses, Map selector, String sessionAffinity, SessionAffinityConfig sessionAffinityConfig, String trafficDistribution, String type) {
      this.allocateLoadBalancerNodePorts = allocateLoadBalancerNodePorts;
      this.clusterIP = clusterIP;
      this.clusterIPs = clusterIPs;
      this.externalIPs = externalIPs;
      this.externalName = externalName;
      this.externalTrafficPolicy = externalTrafficPolicy;
      this.healthCheckNodePort = healthCheckNodePort;
      this.internalTrafficPolicy = internalTrafficPolicy;
      this.ipFamilies = ipFamilies;
      this.ipFamilyPolicy = ipFamilyPolicy;
      this.loadBalancerClass = loadBalancerClass;
      this.loadBalancerIP = loadBalancerIP;
      this.loadBalancerSourceRanges = loadBalancerSourceRanges;
      this.ports = ports;
      this.publishNotReadyAddresses = publishNotReadyAddresses;
      this.selector = selector;
      this.sessionAffinity = sessionAffinity;
      this.sessionAffinityConfig = sessionAffinityConfig;
      this.trafficDistribution = trafficDistribution;
      this.type = type;
   }

   @JsonProperty("allocateLoadBalancerNodePorts")
   public Boolean getAllocateLoadBalancerNodePorts() {
      return this.allocateLoadBalancerNodePorts;
   }

   @JsonProperty("allocateLoadBalancerNodePorts")
   public void setAllocateLoadBalancerNodePorts(Boolean allocateLoadBalancerNodePorts) {
      this.allocateLoadBalancerNodePorts = allocateLoadBalancerNodePorts;
   }

   @JsonProperty("clusterIP")
   public String getClusterIP() {
      return this.clusterIP;
   }

   @JsonProperty("clusterIP")
   public void setClusterIP(String clusterIP) {
      this.clusterIP = clusterIP;
   }

   @JsonProperty("clusterIPs")
   @JsonInclude(Include.NON_EMPTY)
   public List getClusterIPs() {
      return this.clusterIPs;
   }

   @JsonProperty("clusterIPs")
   public void setClusterIPs(List clusterIPs) {
      this.clusterIPs = clusterIPs;
   }

   @JsonProperty("externalIPs")
   @JsonInclude(Include.NON_EMPTY)
   public List getExternalIPs() {
      return this.externalIPs;
   }

   @JsonProperty("externalIPs")
   public void setExternalIPs(List externalIPs) {
      this.externalIPs = externalIPs;
   }

   @JsonProperty("externalName")
   public String getExternalName() {
      return this.externalName;
   }

   @JsonProperty("externalName")
   public void setExternalName(String externalName) {
      this.externalName = externalName;
   }

   @JsonProperty("externalTrafficPolicy")
   public String getExternalTrafficPolicy() {
      return this.externalTrafficPolicy;
   }

   @JsonProperty("externalTrafficPolicy")
   public void setExternalTrafficPolicy(String externalTrafficPolicy) {
      this.externalTrafficPolicy = externalTrafficPolicy;
   }

   @JsonProperty("healthCheckNodePort")
   public Integer getHealthCheckNodePort() {
      return this.healthCheckNodePort;
   }

   @JsonProperty("healthCheckNodePort")
   public void setHealthCheckNodePort(Integer healthCheckNodePort) {
      this.healthCheckNodePort = healthCheckNodePort;
   }

   @JsonProperty("internalTrafficPolicy")
   public String getInternalTrafficPolicy() {
      return this.internalTrafficPolicy;
   }

   @JsonProperty("internalTrafficPolicy")
   public void setInternalTrafficPolicy(String internalTrafficPolicy) {
      this.internalTrafficPolicy = internalTrafficPolicy;
   }

   @JsonProperty("ipFamilies")
   @JsonInclude(Include.NON_EMPTY)
   public List getIpFamilies() {
      return this.ipFamilies;
   }

   @JsonProperty("ipFamilies")
   public void setIpFamilies(List ipFamilies) {
      this.ipFamilies = ipFamilies;
   }

   @JsonProperty("ipFamilyPolicy")
   public String getIpFamilyPolicy() {
      return this.ipFamilyPolicy;
   }

   @JsonProperty("ipFamilyPolicy")
   public void setIpFamilyPolicy(String ipFamilyPolicy) {
      this.ipFamilyPolicy = ipFamilyPolicy;
   }

   @JsonProperty("loadBalancerClass")
   public String getLoadBalancerClass() {
      return this.loadBalancerClass;
   }

   @JsonProperty("loadBalancerClass")
   public void setLoadBalancerClass(String loadBalancerClass) {
      this.loadBalancerClass = loadBalancerClass;
   }

   @JsonProperty("loadBalancerIP")
   public String getLoadBalancerIP() {
      return this.loadBalancerIP;
   }

   @JsonProperty("loadBalancerIP")
   public void setLoadBalancerIP(String loadBalancerIP) {
      this.loadBalancerIP = loadBalancerIP;
   }

   @JsonProperty("loadBalancerSourceRanges")
   @JsonInclude(Include.NON_EMPTY)
   public List getLoadBalancerSourceRanges() {
      return this.loadBalancerSourceRanges;
   }

   @JsonProperty("loadBalancerSourceRanges")
   public void setLoadBalancerSourceRanges(List loadBalancerSourceRanges) {
      this.loadBalancerSourceRanges = loadBalancerSourceRanges;
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

   @JsonProperty("publishNotReadyAddresses")
   public Boolean getPublishNotReadyAddresses() {
      return this.publishNotReadyAddresses;
   }

   @JsonProperty("publishNotReadyAddresses")
   public void setPublishNotReadyAddresses(Boolean publishNotReadyAddresses) {
      this.publishNotReadyAddresses = publishNotReadyAddresses;
   }

   @JsonProperty("selector")
   @JsonInclude(Include.NON_EMPTY)
   public Map getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(Map selector) {
      this.selector = selector;
   }

   @JsonProperty("sessionAffinity")
   public String getSessionAffinity() {
      return this.sessionAffinity;
   }

   @JsonProperty("sessionAffinity")
   public void setSessionAffinity(String sessionAffinity) {
      this.sessionAffinity = sessionAffinity;
   }

   @JsonProperty("sessionAffinityConfig")
   public SessionAffinityConfig getSessionAffinityConfig() {
      return this.sessionAffinityConfig;
   }

   @JsonProperty("sessionAffinityConfig")
   public void setSessionAffinityConfig(SessionAffinityConfig sessionAffinityConfig) {
      this.sessionAffinityConfig = sessionAffinityConfig;
   }

   @JsonProperty("trafficDistribution")
   public String getTrafficDistribution() {
      return this.trafficDistribution;
   }

   @JsonProperty("trafficDistribution")
   public void setTrafficDistribution(String trafficDistribution) {
      this.trafficDistribution = trafficDistribution;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public ServiceSpecBuilder edit() {
      return new ServiceSpecBuilder(this);
   }

   @JsonIgnore
   public ServiceSpecBuilder toBuilder() {
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
      Boolean var10000 = this.getAllocateLoadBalancerNodePorts();
      return "ServiceSpec(allocateLoadBalancerNodePorts=" + var10000 + ", clusterIP=" + this.getClusterIP() + ", clusterIPs=" + this.getClusterIPs() + ", externalIPs=" + this.getExternalIPs() + ", externalName=" + this.getExternalName() + ", externalTrafficPolicy=" + this.getExternalTrafficPolicy() + ", healthCheckNodePort=" + this.getHealthCheckNodePort() + ", internalTrafficPolicy=" + this.getInternalTrafficPolicy() + ", ipFamilies=" + this.getIpFamilies() + ", ipFamilyPolicy=" + this.getIpFamilyPolicy() + ", loadBalancerClass=" + this.getLoadBalancerClass() + ", loadBalancerIP=" + this.getLoadBalancerIP() + ", loadBalancerSourceRanges=" + this.getLoadBalancerSourceRanges() + ", ports=" + this.getPorts() + ", publishNotReadyAddresses=" + this.getPublishNotReadyAddresses() + ", selector=" + this.getSelector() + ", sessionAffinity=" + this.getSessionAffinity() + ", sessionAffinityConfig=" + this.getSessionAffinityConfig() + ", trafficDistribution=" + this.getTrafficDistribution() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServiceSpec)) {
         return false;
      } else {
         ServiceSpec other = (ServiceSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allocateLoadBalancerNodePorts = this.getAllocateLoadBalancerNodePorts();
            Object other$allocateLoadBalancerNodePorts = other.getAllocateLoadBalancerNodePorts();
            if (this$allocateLoadBalancerNodePorts == null) {
               if (other$allocateLoadBalancerNodePorts != null) {
                  return false;
               }
            } else if (!this$allocateLoadBalancerNodePorts.equals(other$allocateLoadBalancerNodePorts)) {
               return false;
            }

            Object this$healthCheckNodePort = this.getHealthCheckNodePort();
            Object other$healthCheckNodePort = other.getHealthCheckNodePort();
            if (this$healthCheckNodePort == null) {
               if (other$healthCheckNodePort != null) {
                  return false;
               }
            } else if (!this$healthCheckNodePort.equals(other$healthCheckNodePort)) {
               return false;
            }

            Object this$publishNotReadyAddresses = this.getPublishNotReadyAddresses();
            Object other$publishNotReadyAddresses = other.getPublishNotReadyAddresses();
            if (this$publishNotReadyAddresses == null) {
               if (other$publishNotReadyAddresses != null) {
                  return false;
               }
            } else if (!this$publishNotReadyAddresses.equals(other$publishNotReadyAddresses)) {
               return false;
            }

            Object this$clusterIP = this.getClusterIP();
            Object other$clusterIP = other.getClusterIP();
            if (this$clusterIP == null) {
               if (other$clusterIP != null) {
                  return false;
               }
            } else if (!this$clusterIP.equals(other$clusterIP)) {
               return false;
            }

            Object this$clusterIPs = this.getClusterIPs();
            Object other$clusterIPs = other.getClusterIPs();
            if (this$clusterIPs == null) {
               if (other$clusterIPs != null) {
                  return false;
               }
            } else if (!this$clusterIPs.equals(other$clusterIPs)) {
               return false;
            }

            Object this$externalIPs = this.getExternalIPs();
            Object other$externalIPs = other.getExternalIPs();
            if (this$externalIPs == null) {
               if (other$externalIPs != null) {
                  return false;
               }
            } else if (!this$externalIPs.equals(other$externalIPs)) {
               return false;
            }

            Object this$externalName = this.getExternalName();
            Object other$externalName = other.getExternalName();
            if (this$externalName == null) {
               if (other$externalName != null) {
                  return false;
               }
            } else if (!this$externalName.equals(other$externalName)) {
               return false;
            }

            Object this$externalTrafficPolicy = this.getExternalTrafficPolicy();
            Object other$externalTrafficPolicy = other.getExternalTrafficPolicy();
            if (this$externalTrafficPolicy == null) {
               if (other$externalTrafficPolicy != null) {
                  return false;
               }
            } else if (!this$externalTrafficPolicy.equals(other$externalTrafficPolicy)) {
               return false;
            }

            Object this$internalTrafficPolicy = this.getInternalTrafficPolicy();
            Object other$internalTrafficPolicy = other.getInternalTrafficPolicy();
            if (this$internalTrafficPolicy == null) {
               if (other$internalTrafficPolicy != null) {
                  return false;
               }
            } else if (!this$internalTrafficPolicy.equals(other$internalTrafficPolicy)) {
               return false;
            }

            Object this$ipFamilies = this.getIpFamilies();
            Object other$ipFamilies = other.getIpFamilies();
            if (this$ipFamilies == null) {
               if (other$ipFamilies != null) {
                  return false;
               }
            } else if (!this$ipFamilies.equals(other$ipFamilies)) {
               return false;
            }

            Object this$ipFamilyPolicy = this.getIpFamilyPolicy();
            Object other$ipFamilyPolicy = other.getIpFamilyPolicy();
            if (this$ipFamilyPolicy == null) {
               if (other$ipFamilyPolicy != null) {
                  return false;
               }
            } else if (!this$ipFamilyPolicy.equals(other$ipFamilyPolicy)) {
               return false;
            }

            Object this$loadBalancerClass = this.getLoadBalancerClass();
            Object other$loadBalancerClass = other.getLoadBalancerClass();
            if (this$loadBalancerClass == null) {
               if (other$loadBalancerClass != null) {
                  return false;
               }
            } else if (!this$loadBalancerClass.equals(other$loadBalancerClass)) {
               return false;
            }

            Object this$loadBalancerIP = this.getLoadBalancerIP();
            Object other$loadBalancerIP = other.getLoadBalancerIP();
            if (this$loadBalancerIP == null) {
               if (other$loadBalancerIP != null) {
                  return false;
               }
            } else if (!this$loadBalancerIP.equals(other$loadBalancerIP)) {
               return false;
            }

            Object this$loadBalancerSourceRanges = this.getLoadBalancerSourceRanges();
            Object other$loadBalancerSourceRanges = other.getLoadBalancerSourceRanges();
            if (this$loadBalancerSourceRanges == null) {
               if (other$loadBalancerSourceRanges != null) {
                  return false;
               }
            } else if (!this$loadBalancerSourceRanges.equals(other$loadBalancerSourceRanges)) {
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

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
               return false;
            }

            Object this$sessionAffinity = this.getSessionAffinity();
            Object other$sessionAffinity = other.getSessionAffinity();
            if (this$sessionAffinity == null) {
               if (other$sessionAffinity != null) {
                  return false;
               }
            } else if (!this$sessionAffinity.equals(other$sessionAffinity)) {
               return false;
            }

            Object this$sessionAffinityConfig = this.getSessionAffinityConfig();
            Object other$sessionAffinityConfig = other.getSessionAffinityConfig();
            if (this$sessionAffinityConfig == null) {
               if (other$sessionAffinityConfig != null) {
                  return false;
               }
            } else if (!this$sessionAffinityConfig.equals(other$sessionAffinityConfig)) {
               return false;
            }

            Object this$trafficDistribution = this.getTrafficDistribution();
            Object other$trafficDistribution = other.getTrafficDistribution();
            if (this$trafficDistribution == null) {
               if (other$trafficDistribution != null) {
                  return false;
               }
            } else if (!this$trafficDistribution.equals(other$trafficDistribution)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof ServiceSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allocateLoadBalancerNodePorts = this.getAllocateLoadBalancerNodePorts();
      result = result * 59 + ($allocateLoadBalancerNodePorts == null ? 43 : $allocateLoadBalancerNodePorts.hashCode());
      Object $healthCheckNodePort = this.getHealthCheckNodePort();
      result = result * 59 + ($healthCheckNodePort == null ? 43 : $healthCheckNodePort.hashCode());
      Object $publishNotReadyAddresses = this.getPublishNotReadyAddresses();
      result = result * 59 + ($publishNotReadyAddresses == null ? 43 : $publishNotReadyAddresses.hashCode());
      Object $clusterIP = this.getClusterIP();
      result = result * 59 + ($clusterIP == null ? 43 : $clusterIP.hashCode());
      Object $clusterIPs = this.getClusterIPs();
      result = result * 59 + ($clusterIPs == null ? 43 : $clusterIPs.hashCode());
      Object $externalIPs = this.getExternalIPs();
      result = result * 59 + ($externalIPs == null ? 43 : $externalIPs.hashCode());
      Object $externalName = this.getExternalName();
      result = result * 59 + ($externalName == null ? 43 : $externalName.hashCode());
      Object $externalTrafficPolicy = this.getExternalTrafficPolicy();
      result = result * 59 + ($externalTrafficPolicy == null ? 43 : $externalTrafficPolicy.hashCode());
      Object $internalTrafficPolicy = this.getInternalTrafficPolicy();
      result = result * 59 + ($internalTrafficPolicy == null ? 43 : $internalTrafficPolicy.hashCode());
      Object $ipFamilies = this.getIpFamilies();
      result = result * 59 + ($ipFamilies == null ? 43 : $ipFamilies.hashCode());
      Object $ipFamilyPolicy = this.getIpFamilyPolicy();
      result = result * 59 + ($ipFamilyPolicy == null ? 43 : $ipFamilyPolicy.hashCode());
      Object $loadBalancerClass = this.getLoadBalancerClass();
      result = result * 59 + ($loadBalancerClass == null ? 43 : $loadBalancerClass.hashCode());
      Object $loadBalancerIP = this.getLoadBalancerIP();
      result = result * 59 + ($loadBalancerIP == null ? 43 : $loadBalancerIP.hashCode());
      Object $loadBalancerSourceRanges = this.getLoadBalancerSourceRanges();
      result = result * 59 + ($loadBalancerSourceRanges == null ? 43 : $loadBalancerSourceRanges.hashCode());
      Object $ports = this.getPorts();
      result = result * 59 + ($ports == null ? 43 : $ports.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $sessionAffinity = this.getSessionAffinity();
      result = result * 59 + ($sessionAffinity == null ? 43 : $sessionAffinity.hashCode());
      Object $sessionAffinityConfig = this.getSessionAffinityConfig();
      result = result * 59 + ($sessionAffinityConfig == null ? 43 : $sessionAffinityConfig.hashCode());
      Object $trafficDistribution = this.getTrafficDistribution();
      result = result * 59 + ($trafficDistribution == null ? 43 : $trafficDistribution.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
