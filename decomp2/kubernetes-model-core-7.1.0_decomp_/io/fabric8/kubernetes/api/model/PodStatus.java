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
@JsonPropertyOrder({"conditions", "containerStatuses", "ephemeralContainerStatuses", "hostIP", "hostIPs", "initContainerStatuses", "message", "nominatedNodeName", "phase", "podIP", "podIPs", "qosClass", "reason", "resize", "resourceClaimStatuses", "startTime"})
public class PodStatus implements Editable, KubernetesResource {
   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   private List conditions = new ArrayList();
   @JsonProperty("containerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   private List containerStatuses = new ArrayList();
   @JsonProperty("ephemeralContainerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   private List ephemeralContainerStatuses = new ArrayList();
   @JsonProperty("hostIP")
   private String hostIP;
   @JsonProperty("hostIPs")
   @JsonInclude(Include.NON_EMPTY)
   private List hostIPs = new ArrayList();
   @JsonProperty("initContainerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   private List initContainerStatuses = new ArrayList();
   @JsonProperty("message")
   private String message;
   @JsonProperty("nominatedNodeName")
   private String nominatedNodeName;
   @JsonProperty("phase")
   private String phase;
   @JsonProperty("podIP")
   private String podIP;
   @JsonProperty("podIPs")
   @JsonInclude(Include.NON_EMPTY)
   private List podIPs = new ArrayList();
   @JsonProperty("qosClass")
   private String qosClass;
   @JsonProperty("reason")
   private String reason;
   @JsonProperty("resize")
   private String resize;
   @JsonProperty("resourceClaimStatuses")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceClaimStatuses = new ArrayList();
   @JsonProperty("startTime")
   private String startTime;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodStatus() {
   }

   public PodStatus(List conditions, List containerStatuses, List ephemeralContainerStatuses, String hostIP, List hostIPs, List initContainerStatuses, String message, String nominatedNodeName, String phase, String podIP, List podIPs, String qosClass, String reason, String resize, List resourceClaimStatuses, String startTime) {
      this.conditions = conditions;
      this.containerStatuses = containerStatuses;
      this.ephemeralContainerStatuses = ephemeralContainerStatuses;
      this.hostIP = hostIP;
      this.hostIPs = hostIPs;
      this.initContainerStatuses = initContainerStatuses;
      this.message = message;
      this.nominatedNodeName = nominatedNodeName;
      this.phase = phase;
      this.podIP = podIP;
      this.podIPs = podIPs;
      this.qosClass = qosClass;
      this.reason = reason;
      this.resize = resize;
      this.resourceClaimStatuses = resourceClaimStatuses;
      this.startTime = startTime;
   }

   @JsonProperty("conditions")
   @JsonInclude(Include.NON_EMPTY)
   public List getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(List conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("containerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   public List getContainerStatuses() {
      return this.containerStatuses;
   }

   @JsonProperty("containerStatuses")
   public void setContainerStatuses(List containerStatuses) {
      this.containerStatuses = containerStatuses;
   }

   @JsonProperty("ephemeralContainerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   public List getEphemeralContainerStatuses() {
      return this.ephemeralContainerStatuses;
   }

   @JsonProperty("ephemeralContainerStatuses")
   public void setEphemeralContainerStatuses(List ephemeralContainerStatuses) {
      this.ephemeralContainerStatuses = ephemeralContainerStatuses;
   }

   @JsonProperty("hostIP")
   public String getHostIP() {
      return this.hostIP;
   }

   @JsonProperty("hostIP")
   public void setHostIP(String hostIP) {
      this.hostIP = hostIP;
   }

   @JsonProperty("hostIPs")
   @JsonInclude(Include.NON_EMPTY)
   public List getHostIPs() {
      return this.hostIPs;
   }

   @JsonProperty("hostIPs")
   public void setHostIPs(List hostIPs) {
      this.hostIPs = hostIPs;
   }

   @JsonProperty("initContainerStatuses")
   @JsonInclude(Include.NON_EMPTY)
   public List getInitContainerStatuses() {
      return this.initContainerStatuses;
   }

   @JsonProperty("initContainerStatuses")
   public void setInitContainerStatuses(List initContainerStatuses) {
      this.initContainerStatuses = initContainerStatuses;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("nominatedNodeName")
   public String getNominatedNodeName() {
      return this.nominatedNodeName;
   }

   @JsonProperty("nominatedNodeName")
   public void setNominatedNodeName(String nominatedNodeName) {
      this.nominatedNodeName = nominatedNodeName;
   }

   @JsonProperty("phase")
   public String getPhase() {
      return this.phase;
   }

   @JsonProperty("phase")
   public void setPhase(String phase) {
      this.phase = phase;
   }

   @JsonProperty("podIP")
   public String getPodIP() {
      return this.podIP;
   }

   @JsonProperty("podIP")
   public void setPodIP(String podIP) {
      this.podIP = podIP;
   }

   @JsonProperty("podIPs")
   @JsonInclude(Include.NON_EMPTY)
   public List getPodIPs() {
      return this.podIPs;
   }

   @JsonProperty("podIPs")
   public void setPodIPs(List podIPs) {
      this.podIPs = podIPs;
   }

   @JsonProperty("qosClass")
   public String getQosClass() {
      return this.qosClass;
   }

   @JsonProperty("qosClass")
   public void setQosClass(String qosClass) {
      this.qosClass = qosClass;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonProperty("resize")
   public String getResize() {
      return this.resize;
   }

   @JsonProperty("resize")
   public void setResize(String resize) {
      this.resize = resize;
   }

   @JsonProperty("resourceClaimStatuses")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceClaimStatuses() {
      return this.resourceClaimStatuses;
   }

   @JsonProperty("resourceClaimStatuses")
   public void setResourceClaimStatuses(List resourceClaimStatuses) {
      this.resourceClaimStatuses = resourceClaimStatuses;
   }

   @JsonProperty("startTime")
   public String getStartTime() {
      return this.startTime;
   }

   @JsonProperty("startTime")
   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   @JsonIgnore
   public PodStatusBuilder edit() {
      return new PodStatusBuilder(this);
   }

   @JsonIgnore
   public PodStatusBuilder toBuilder() {
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
      List var10000 = this.getConditions();
      return "PodStatus(conditions=" + var10000 + ", containerStatuses=" + this.getContainerStatuses() + ", ephemeralContainerStatuses=" + this.getEphemeralContainerStatuses() + ", hostIP=" + this.getHostIP() + ", hostIPs=" + this.getHostIPs() + ", initContainerStatuses=" + this.getInitContainerStatuses() + ", message=" + this.getMessage() + ", nominatedNodeName=" + this.getNominatedNodeName() + ", phase=" + this.getPhase() + ", podIP=" + this.getPodIP() + ", podIPs=" + this.getPodIPs() + ", qosClass=" + this.getQosClass() + ", reason=" + this.getReason() + ", resize=" + this.getResize() + ", resourceClaimStatuses=" + this.getResourceClaimStatuses() + ", startTime=" + this.getStartTime() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodStatus)) {
         return false;
      } else {
         PodStatus other = (PodStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$containerStatuses = this.getContainerStatuses();
            Object other$containerStatuses = other.getContainerStatuses();
            if (this$containerStatuses == null) {
               if (other$containerStatuses != null) {
                  return false;
               }
            } else if (!this$containerStatuses.equals(other$containerStatuses)) {
               return false;
            }

            Object this$ephemeralContainerStatuses = this.getEphemeralContainerStatuses();
            Object other$ephemeralContainerStatuses = other.getEphemeralContainerStatuses();
            if (this$ephemeralContainerStatuses == null) {
               if (other$ephemeralContainerStatuses != null) {
                  return false;
               }
            } else if (!this$ephemeralContainerStatuses.equals(other$ephemeralContainerStatuses)) {
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

            Object this$hostIPs = this.getHostIPs();
            Object other$hostIPs = other.getHostIPs();
            if (this$hostIPs == null) {
               if (other$hostIPs != null) {
                  return false;
               }
            } else if (!this$hostIPs.equals(other$hostIPs)) {
               return false;
            }

            Object this$initContainerStatuses = this.getInitContainerStatuses();
            Object other$initContainerStatuses = other.getInitContainerStatuses();
            if (this$initContainerStatuses == null) {
               if (other$initContainerStatuses != null) {
                  return false;
               }
            } else if (!this$initContainerStatuses.equals(other$initContainerStatuses)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$nominatedNodeName = this.getNominatedNodeName();
            Object other$nominatedNodeName = other.getNominatedNodeName();
            if (this$nominatedNodeName == null) {
               if (other$nominatedNodeName != null) {
                  return false;
               }
            } else if (!this$nominatedNodeName.equals(other$nominatedNodeName)) {
               return false;
            }

            Object this$phase = this.getPhase();
            Object other$phase = other.getPhase();
            if (this$phase == null) {
               if (other$phase != null) {
                  return false;
               }
            } else if (!this$phase.equals(other$phase)) {
               return false;
            }

            Object this$podIP = this.getPodIP();
            Object other$podIP = other.getPodIP();
            if (this$podIP == null) {
               if (other$podIP != null) {
                  return false;
               }
            } else if (!this$podIP.equals(other$podIP)) {
               return false;
            }

            Object this$podIPs = this.getPodIPs();
            Object other$podIPs = other.getPodIPs();
            if (this$podIPs == null) {
               if (other$podIPs != null) {
                  return false;
               }
            } else if (!this$podIPs.equals(other$podIPs)) {
               return false;
            }

            Object this$qosClass = this.getQosClass();
            Object other$qosClass = other.getQosClass();
            if (this$qosClass == null) {
               if (other$qosClass != null) {
                  return false;
               }
            } else if (!this$qosClass.equals(other$qosClass)) {
               return false;
            }

            Object this$reason = this.getReason();
            Object other$reason = other.getReason();
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
               return false;
            }

            Object this$resize = this.getResize();
            Object other$resize = other.getResize();
            if (this$resize == null) {
               if (other$resize != null) {
                  return false;
               }
            } else if (!this$resize.equals(other$resize)) {
               return false;
            }

            Object this$resourceClaimStatuses = this.getResourceClaimStatuses();
            Object other$resourceClaimStatuses = other.getResourceClaimStatuses();
            if (this$resourceClaimStatuses == null) {
               if (other$resourceClaimStatuses != null) {
                  return false;
               }
            } else if (!this$resourceClaimStatuses.equals(other$resourceClaimStatuses)) {
               return false;
            }

            Object this$startTime = this.getStartTime();
            Object other$startTime = other.getStartTime();
            if (this$startTime == null) {
               if (other$startTime != null) {
                  return false;
               }
            } else if (!this$startTime.equals(other$startTime)) {
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
      return other instanceof PodStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $containerStatuses = this.getContainerStatuses();
      result = result * 59 + ($containerStatuses == null ? 43 : $containerStatuses.hashCode());
      Object $ephemeralContainerStatuses = this.getEphemeralContainerStatuses();
      result = result * 59 + ($ephemeralContainerStatuses == null ? 43 : $ephemeralContainerStatuses.hashCode());
      Object $hostIP = this.getHostIP();
      result = result * 59 + ($hostIP == null ? 43 : $hostIP.hashCode());
      Object $hostIPs = this.getHostIPs();
      result = result * 59 + ($hostIPs == null ? 43 : $hostIPs.hashCode());
      Object $initContainerStatuses = this.getInitContainerStatuses();
      result = result * 59 + ($initContainerStatuses == null ? 43 : $initContainerStatuses.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $nominatedNodeName = this.getNominatedNodeName();
      result = result * 59 + ($nominatedNodeName == null ? 43 : $nominatedNodeName.hashCode());
      Object $phase = this.getPhase();
      result = result * 59 + ($phase == null ? 43 : $phase.hashCode());
      Object $podIP = this.getPodIP();
      result = result * 59 + ($podIP == null ? 43 : $podIP.hashCode());
      Object $podIPs = this.getPodIPs();
      result = result * 59 + ($podIPs == null ? 43 : $podIPs.hashCode());
      Object $qosClass = this.getQosClass();
      result = result * 59 + ($qosClass == null ? 43 : $qosClass.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $resize = this.getResize();
      result = result * 59 + ($resize == null ? 43 : $resize.hashCode());
      Object $resourceClaimStatuses = this.getResourceClaimStatuses();
      result = result * 59 + ($resourceClaimStatuses == null ? 43 : $resourceClaimStatuses.hashCode());
      Object $startTime = this.getStartTime();
      result = result * 59 + ($startTime == null ? 43 : $startTime.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
