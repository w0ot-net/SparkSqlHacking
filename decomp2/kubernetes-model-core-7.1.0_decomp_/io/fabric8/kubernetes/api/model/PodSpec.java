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
@JsonPropertyOrder({"activeDeadlineSeconds", "affinity", "automountServiceAccountToken", "containers", "dnsConfig", "dnsPolicy", "enableServiceLinks", "ephemeralContainers", "hostAliases", "hostIPC", "hostNetwork", "hostPID", "hostUsers", "hostname", "imagePullSecrets", "initContainers", "nodeName", "nodeSelector", "os", "overhead", "preemptionPolicy", "priority", "priorityClassName", "readinessGates", "resourceClaims", "resources", "restartPolicy", "runtimeClassName", "schedulerName", "schedulingGates", "securityContext", "serviceAccount", "serviceAccountName", "setHostnameAsFQDN", "shareProcessNamespace", "subdomain", "terminationGracePeriodSeconds", "tolerations", "topologySpreadConstraints", "volumes"})
public class PodSpec implements Editable, KubernetesResource {
   @JsonProperty("activeDeadlineSeconds")
   private Long activeDeadlineSeconds;
   @JsonProperty("affinity")
   private Affinity affinity;
   @JsonProperty("automountServiceAccountToken")
   private Boolean automountServiceAccountToken;
   @JsonProperty("containers")
   @JsonInclude(Include.NON_EMPTY)
   private List containers = new ArrayList();
   @JsonProperty("dnsConfig")
   private PodDNSConfig dnsConfig;
   @JsonProperty("dnsPolicy")
   private String dnsPolicy;
   @JsonProperty("enableServiceLinks")
   private Boolean enableServiceLinks;
   @JsonProperty("ephemeralContainers")
   @JsonInclude(Include.NON_EMPTY)
   private List ephemeralContainers = new ArrayList();
   @JsonProperty("hostAliases")
   @JsonInclude(Include.NON_EMPTY)
   private List hostAliases = new ArrayList();
   @JsonProperty("hostIPC")
   private Boolean hostIPC;
   @JsonProperty("hostNetwork")
   private Boolean hostNetwork;
   @JsonProperty("hostPID")
   private Boolean hostPID;
   @JsonProperty("hostUsers")
   private Boolean hostUsers;
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("imagePullSecrets")
   @JsonInclude(Include.NON_EMPTY)
   private List imagePullSecrets = new ArrayList();
   @JsonProperty("initContainers")
   @JsonInclude(Include.NON_EMPTY)
   private List initContainers = new ArrayList();
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("nodeSelector")
   @JsonInclude(Include.NON_EMPTY)
   private Map nodeSelector = new LinkedHashMap();
   @JsonProperty("os")
   private PodOS os;
   @JsonProperty("overhead")
   @JsonInclude(Include.NON_EMPTY)
   private Map overhead = new LinkedHashMap();
   @JsonProperty("preemptionPolicy")
   private String preemptionPolicy;
   @JsonProperty("priority")
   private Integer priority;
   @JsonProperty("priorityClassName")
   private String priorityClassName;
   @JsonProperty("readinessGates")
   @JsonInclude(Include.NON_EMPTY)
   private List readinessGates = new ArrayList();
   @JsonProperty("resourceClaims")
   @JsonInclude(Include.NON_EMPTY)
   private List resourceClaims = new ArrayList();
   @JsonProperty("resources")
   private ResourceRequirements resources;
   @JsonProperty("restartPolicy")
   private String restartPolicy;
   @JsonProperty("runtimeClassName")
   private String runtimeClassName;
   @JsonProperty("schedulerName")
   private String schedulerName;
   @JsonProperty("schedulingGates")
   @JsonInclude(Include.NON_EMPTY)
   private List schedulingGates = new ArrayList();
   @JsonProperty("securityContext")
   private PodSecurityContext securityContext;
   @JsonProperty("serviceAccount")
   private String serviceAccount;
   @JsonProperty("serviceAccountName")
   private String serviceAccountName;
   @JsonProperty("setHostnameAsFQDN")
   private Boolean setHostnameAsFQDN;
   @JsonProperty("shareProcessNamespace")
   private Boolean shareProcessNamespace;
   @JsonProperty("subdomain")
   private String subdomain;
   @JsonProperty("terminationGracePeriodSeconds")
   private Long terminationGracePeriodSeconds;
   @JsonProperty("tolerations")
   @JsonInclude(Include.NON_EMPTY)
   private List tolerations = new ArrayList();
   @JsonProperty("topologySpreadConstraints")
   @JsonInclude(Include.NON_EMPTY)
   private List topologySpreadConstraints = new ArrayList();
   @JsonProperty("volumes")
   @JsonInclude(Include.NON_EMPTY)
   private List volumes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodSpec() {
   }

   public PodSpec(Long activeDeadlineSeconds, Affinity affinity, Boolean automountServiceAccountToken, List containers, PodDNSConfig dnsConfig, String dnsPolicy, Boolean enableServiceLinks, List ephemeralContainers, List hostAliases, Boolean hostIPC, Boolean hostNetwork, Boolean hostPID, Boolean hostUsers, String hostname, List imagePullSecrets, List initContainers, String nodeName, Map nodeSelector, PodOS os, Map overhead, String preemptionPolicy, Integer priority, String priorityClassName, List readinessGates, List resourceClaims, ResourceRequirements resources, String restartPolicy, String runtimeClassName, String schedulerName, List schedulingGates, PodSecurityContext securityContext, String serviceAccount, String serviceAccountName, Boolean setHostnameAsFQDN, Boolean shareProcessNamespace, String subdomain, Long terminationGracePeriodSeconds, List tolerations, List topologySpreadConstraints, List volumes) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
      this.affinity = affinity;
      this.automountServiceAccountToken = automountServiceAccountToken;
      this.containers = containers;
      this.dnsConfig = dnsConfig;
      this.dnsPolicy = dnsPolicy;
      this.enableServiceLinks = enableServiceLinks;
      this.ephemeralContainers = ephemeralContainers;
      this.hostAliases = hostAliases;
      this.hostIPC = hostIPC;
      this.hostNetwork = hostNetwork;
      this.hostPID = hostPID;
      this.hostUsers = hostUsers;
      this.hostname = hostname;
      this.imagePullSecrets = imagePullSecrets;
      this.initContainers = initContainers;
      this.nodeName = nodeName;
      this.nodeSelector = nodeSelector;
      this.os = os;
      this.overhead = overhead;
      this.preemptionPolicy = preemptionPolicy;
      this.priority = priority;
      this.priorityClassName = priorityClassName;
      this.readinessGates = readinessGates;
      this.resourceClaims = resourceClaims;
      this.resources = resources;
      this.restartPolicy = restartPolicy;
      this.runtimeClassName = runtimeClassName;
      this.schedulerName = schedulerName;
      this.schedulingGates = schedulingGates;
      this.securityContext = securityContext;
      this.serviceAccount = serviceAccount;
      this.serviceAccountName = serviceAccountName;
      this.setHostnameAsFQDN = setHostnameAsFQDN;
      this.shareProcessNamespace = shareProcessNamespace;
      this.subdomain = subdomain;
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
      this.tolerations = tolerations;
      this.topologySpreadConstraints = topologySpreadConstraints;
      this.volumes = volumes;
   }

   @JsonProperty("activeDeadlineSeconds")
   public Long getActiveDeadlineSeconds() {
      return this.activeDeadlineSeconds;
   }

   @JsonProperty("activeDeadlineSeconds")
   public void setActiveDeadlineSeconds(Long activeDeadlineSeconds) {
      this.activeDeadlineSeconds = activeDeadlineSeconds;
   }

   @JsonProperty("affinity")
   public Affinity getAffinity() {
      return this.affinity;
   }

   @JsonProperty("affinity")
   public void setAffinity(Affinity affinity) {
      this.affinity = affinity;
   }

   @JsonProperty("automountServiceAccountToken")
   public Boolean getAutomountServiceAccountToken() {
      return this.automountServiceAccountToken;
   }

   @JsonProperty("automountServiceAccountToken")
   public void setAutomountServiceAccountToken(Boolean automountServiceAccountToken) {
      this.automountServiceAccountToken = automountServiceAccountToken;
   }

   @JsonProperty("containers")
   @JsonInclude(Include.NON_EMPTY)
   public List getContainers() {
      return this.containers;
   }

   @JsonProperty("containers")
   public void setContainers(List containers) {
      this.containers = containers;
   }

   @JsonProperty("dnsConfig")
   public PodDNSConfig getDnsConfig() {
      return this.dnsConfig;
   }

   @JsonProperty("dnsConfig")
   public void setDnsConfig(PodDNSConfig dnsConfig) {
      this.dnsConfig = dnsConfig;
   }

   @JsonProperty("dnsPolicy")
   public String getDnsPolicy() {
      return this.dnsPolicy;
   }

   @JsonProperty("dnsPolicy")
   public void setDnsPolicy(String dnsPolicy) {
      this.dnsPolicy = dnsPolicy;
   }

   @JsonProperty("enableServiceLinks")
   public Boolean getEnableServiceLinks() {
      return this.enableServiceLinks;
   }

   @JsonProperty("enableServiceLinks")
   public void setEnableServiceLinks(Boolean enableServiceLinks) {
      this.enableServiceLinks = enableServiceLinks;
   }

   @JsonProperty("ephemeralContainers")
   @JsonInclude(Include.NON_EMPTY)
   public List getEphemeralContainers() {
      return this.ephemeralContainers;
   }

   @JsonProperty("ephemeralContainers")
   public void setEphemeralContainers(List ephemeralContainers) {
      this.ephemeralContainers = ephemeralContainers;
   }

   @JsonProperty("hostAliases")
   @JsonInclude(Include.NON_EMPTY)
   public List getHostAliases() {
      return this.hostAliases;
   }

   @JsonProperty("hostAliases")
   public void setHostAliases(List hostAliases) {
      this.hostAliases = hostAliases;
   }

   @JsonProperty("hostIPC")
   public Boolean getHostIPC() {
      return this.hostIPC;
   }

   @JsonProperty("hostIPC")
   public void setHostIPC(Boolean hostIPC) {
      this.hostIPC = hostIPC;
   }

   @JsonProperty("hostNetwork")
   public Boolean getHostNetwork() {
      return this.hostNetwork;
   }

   @JsonProperty("hostNetwork")
   public void setHostNetwork(Boolean hostNetwork) {
      this.hostNetwork = hostNetwork;
   }

   @JsonProperty("hostPID")
   public Boolean getHostPID() {
      return this.hostPID;
   }

   @JsonProperty("hostPID")
   public void setHostPID(Boolean hostPID) {
      this.hostPID = hostPID;
   }

   @JsonProperty("hostUsers")
   public Boolean getHostUsers() {
      return this.hostUsers;
   }

   @JsonProperty("hostUsers")
   public void setHostUsers(Boolean hostUsers) {
      this.hostUsers = hostUsers;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("imagePullSecrets")
   @JsonInclude(Include.NON_EMPTY)
   public List getImagePullSecrets() {
      return this.imagePullSecrets;
   }

   @JsonProperty("imagePullSecrets")
   public void setImagePullSecrets(List imagePullSecrets) {
      this.imagePullSecrets = imagePullSecrets;
   }

   @JsonProperty("initContainers")
   @JsonInclude(Include.NON_EMPTY)
   public List getInitContainers() {
      return this.initContainers;
   }

   @JsonProperty("initContainers")
   public void setInitContainers(List initContainers) {
      this.initContainers = initContainers;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("nodeSelector")
   @JsonInclude(Include.NON_EMPTY)
   public Map getNodeSelector() {
      return this.nodeSelector;
   }

   @JsonProperty("nodeSelector")
   public void setNodeSelector(Map nodeSelector) {
      this.nodeSelector = nodeSelector;
   }

   @JsonProperty("os")
   public PodOS getOs() {
      return this.os;
   }

   @JsonProperty("os")
   public void setOs(PodOS os) {
      this.os = os;
   }

   @JsonProperty("overhead")
   @JsonInclude(Include.NON_EMPTY)
   public Map getOverhead() {
      return this.overhead;
   }

   @JsonProperty("overhead")
   public void setOverhead(Map overhead) {
      this.overhead = overhead;
   }

   @JsonProperty("preemptionPolicy")
   public String getPreemptionPolicy() {
      return this.preemptionPolicy;
   }

   @JsonProperty("preemptionPolicy")
   public void setPreemptionPolicy(String preemptionPolicy) {
      this.preemptionPolicy = preemptionPolicy;
   }

   @JsonProperty("priority")
   public Integer getPriority() {
      return this.priority;
   }

   @JsonProperty("priority")
   public void setPriority(Integer priority) {
      this.priority = priority;
   }

   @JsonProperty("priorityClassName")
   public String getPriorityClassName() {
      return this.priorityClassName;
   }

   @JsonProperty("priorityClassName")
   public void setPriorityClassName(String priorityClassName) {
      this.priorityClassName = priorityClassName;
   }

   @JsonProperty("readinessGates")
   @JsonInclude(Include.NON_EMPTY)
   public List getReadinessGates() {
      return this.readinessGates;
   }

   @JsonProperty("readinessGates")
   public void setReadinessGates(List readinessGates) {
      this.readinessGates = readinessGates;
   }

   @JsonProperty("resourceClaims")
   @JsonInclude(Include.NON_EMPTY)
   public List getResourceClaims() {
      return this.resourceClaims;
   }

   @JsonProperty("resourceClaims")
   public void setResourceClaims(List resourceClaims) {
      this.resourceClaims = resourceClaims;
   }

   @JsonProperty("resources")
   public ResourceRequirements getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(ResourceRequirements resources) {
      this.resources = resources;
   }

   @JsonProperty("restartPolicy")
   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   @JsonProperty("restartPolicy")
   public void setRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
   }

   @JsonProperty("runtimeClassName")
   public String getRuntimeClassName() {
      return this.runtimeClassName;
   }

   @JsonProperty("runtimeClassName")
   public void setRuntimeClassName(String runtimeClassName) {
      this.runtimeClassName = runtimeClassName;
   }

   @JsonProperty("schedulerName")
   public String getSchedulerName() {
      return this.schedulerName;
   }

   @JsonProperty("schedulerName")
   public void setSchedulerName(String schedulerName) {
      this.schedulerName = schedulerName;
   }

   @JsonProperty("schedulingGates")
   @JsonInclude(Include.NON_EMPTY)
   public List getSchedulingGates() {
      return this.schedulingGates;
   }

   @JsonProperty("schedulingGates")
   public void setSchedulingGates(List schedulingGates) {
      this.schedulingGates = schedulingGates;
   }

   @JsonProperty("securityContext")
   public PodSecurityContext getSecurityContext() {
      return this.securityContext;
   }

   @JsonProperty("securityContext")
   public void setSecurityContext(PodSecurityContext securityContext) {
      this.securityContext = securityContext;
   }

   @JsonProperty("serviceAccount")
   public String getServiceAccount() {
      return this.serviceAccount;
   }

   @JsonProperty("serviceAccount")
   public void setServiceAccount(String serviceAccount) {
      this.serviceAccount = serviceAccount;
   }

   @JsonProperty("serviceAccountName")
   public String getServiceAccountName() {
      return this.serviceAccountName;
   }

   @JsonProperty("serviceAccountName")
   public void setServiceAccountName(String serviceAccountName) {
      this.serviceAccountName = serviceAccountName;
   }

   @JsonProperty("setHostnameAsFQDN")
   public Boolean getSetHostnameAsFQDN() {
      return this.setHostnameAsFQDN;
   }

   @JsonProperty("setHostnameAsFQDN")
   public void setSetHostnameAsFQDN(Boolean setHostnameAsFQDN) {
      this.setHostnameAsFQDN = setHostnameAsFQDN;
   }

   @JsonProperty("shareProcessNamespace")
   public Boolean getShareProcessNamespace() {
      return this.shareProcessNamespace;
   }

   @JsonProperty("shareProcessNamespace")
   public void setShareProcessNamespace(Boolean shareProcessNamespace) {
      this.shareProcessNamespace = shareProcessNamespace;
   }

   @JsonProperty("subdomain")
   public String getSubdomain() {
      return this.subdomain;
   }

   @JsonProperty("subdomain")
   public void setSubdomain(String subdomain) {
      this.subdomain = subdomain;
   }

   @JsonProperty("terminationGracePeriodSeconds")
   public Long getTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds;
   }

   @JsonProperty("terminationGracePeriodSeconds")
   public void setTerminationGracePeriodSeconds(Long terminationGracePeriodSeconds) {
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
   }

   @JsonProperty("tolerations")
   @JsonInclude(Include.NON_EMPTY)
   public List getTolerations() {
      return this.tolerations;
   }

   @JsonProperty("tolerations")
   public void setTolerations(List tolerations) {
      this.tolerations = tolerations;
   }

   @JsonProperty("topologySpreadConstraints")
   @JsonInclude(Include.NON_EMPTY)
   public List getTopologySpreadConstraints() {
      return this.topologySpreadConstraints;
   }

   @JsonProperty("topologySpreadConstraints")
   public void setTopologySpreadConstraints(List topologySpreadConstraints) {
      this.topologySpreadConstraints = topologySpreadConstraints;
   }

   @JsonProperty("volumes")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumes() {
      return this.volumes;
   }

   @JsonProperty("volumes")
   public void setVolumes(List volumes) {
      this.volumes = volumes;
   }

   @JsonIgnore
   public PodSpecBuilder edit() {
      return new PodSpecBuilder(this);
   }

   @JsonIgnore
   public PodSpecBuilder toBuilder() {
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
      Long var10000 = this.getActiveDeadlineSeconds();
      return "PodSpec(activeDeadlineSeconds=" + var10000 + ", affinity=" + this.getAffinity() + ", automountServiceAccountToken=" + this.getAutomountServiceAccountToken() + ", containers=" + this.getContainers() + ", dnsConfig=" + this.getDnsConfig() + ", dnsPolicy=" + this.getDnsPolicy() + ", enableServiceLinks=" + this.getEnableServiceLinks() + ", ephemeralContainers=" + this.getEphemeralContainers() + ", hostAliases=" + this.getHostAliases() + ", hostIPC=" + this.getHostIPC() + ", hostNetwork=" + this.getHostNetwork() + ", hostPID=" + this.getHostPID() + ", hostUsers=" + this.getHostUsers() + ", hostname=" + this.getHostname() + ", imagePullSecrets=" + this.getImagePullSecrets() + ", initContainers=" + this.getInitContainers() + ", nodeName=" + this.getNodeName() + ", nodeSelector=" + this.getNodeSelector() + ", os=" + this.getOs() + ", overhead=" + this.getOverhead() + ", preemptionPolicy=" + this.getPreemptionPolicy() + ", priority=" + this.getPriority() + ", priorityClassName=" + this.getPriorityClassName() + ", readinessGates=" + this.getReadinessGates() + ", resourceClaims=" + this.getResourceClaims() + ", resources=" + this.getResources() + ", restartPolicy=" + this.getRestartPolicy() + ", runtimeClassName=" + this.getRuntimeClassName() + ", schedulerName=" + this.getSchedulerName() + ", schedulingGates=" + this.getSchedulingGates() + ", securityContext=" + this.getSecurityContext() + ", serviceAccount=" + this.getServiceAccount() + ", serviceAccountName=" + this.getServiceAccountName() + ", setHostnameAsFQDN=" + this.getSetHostnameAsFQDN() + ", shareProcessNamespace=" + this.getShareProcessNamespace() + ", subdomain=" + this.getSubdomain() + ", terminationGracePeriodSeconds=" + this.getTerminationGracePeriodSeconds() + ", tolerations=" + this.getTolerations() + ", topologySpreadConstraints=" + this.getTopologySpreadConstraints() + ", volumes=" + this.getVolumes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodSpec)) {
         return false;
      } else {
         PodSpec other = (PodSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$activeDeadlineSeconds = this.getActiveDeadlineSeconds();
            Object other$activeDeadlineSeconds = other.getActiveDeadlineSeconds();
            if (this$activeDeadlineSeconds == null) {
               if (other$activeDeadlineSeconds != null) {
                  return false;
               }
            } else if (!this$activeDeadlineSeconds.equals(other$activeDeadlineSeconds)) {
               return false;
            }

            Object this$automountServiceAccountToken = this.getAutomountServiceAccountToken();
            Object other$automountServiceAccountToken = other.getAutomountServiceAccountToken();
            if (this$automountServiceAccountToken == null) {
               if (other$automountServiceAccountToken != null) {
                  return false;
               }
            } else if (!this$automountServiceAccountToken.equals(other$automountServiceAccountToken)) {
               return false;
            }

            Object this$enableServiceLinks = this.getEnableServiceLinks();
            Object other$enableServiceLinks = other.getEnableServiceLinks();
            if (this$enableServiceLinks == null) {
               if (other$enableServiceLinks != null) {
                  return false;
               }
            } else if (!this$enableServiceLinks.equals(other$enableServiceLinks)) {
               return false;
            }

            Object this$hostIPC = this.getHostIPC();
            Object other$hostIPC = other.getHostIPC();
            if (this$hostIPC == null) {
               if (other$hostIPC != null) {
                  return false;
               }
            } else if (!this$hostIPC.equals(other$hostIPC)) {
               return false;
            }

            Object this$hostNetwork = this.getHostNetwork();
            Object other$hostNetwork = other.getHostNetwork();
            if (this$hostNetwork == null) {
               if (other$hostNetwork != null) {
                  return false;
               }
            } else if (!this$hostNetwork.equals(other$hostNetwork)) {
               return false;
            }

            Object this$hostPID = this.getHostPID();
            Object other$hostPID = other.getHostPID();
            if (this$hostPID == null) {
               if (other$hostPID != null) {
                  return false;
               }
            } else if (!this$hostPID.equals(other$hostPID)) {
               return false;
            }

            Object this$hostUsers = this.getHostUsers();
            Object other$hostUsers = other.getHostUsers();
            if (this$hostUsers == null) {
               if (other$hostUsers != null) {
                  return false;
               }
            } else if (!this$hostUsers.equals(other$hostUsers)) {
               return false;
            }

            Object this$priority = this.getPriority();
            Object other$priority = other.getPriority();
            if (this$priority == null) {
               if (other$priority != null) {
                  return false;
               }
            } else if (!this$priority.equals(other$priority)) {
               return false;
            }

            Object this$setHostnameAsFQDN = this.getSetHostnameAsFQDN();
            Object other$setHostnameAsFQDN = other.getSetHostnameAsFQDN();
            if (this$setHostnameAsFQDN == null) {
               if (other$setHostnameAsFQDN != null) {
                  return false;
               }
            } else if (!this$setHostnameAsFQDN.equals(other$setHostnameAsFQDN)) {
               return false;
            }

            Object this$shareProcessNamespace = this.getShareProcessNamespace();
            Object other$shareProcessNamespace = other.getShareProcessNamespace();
            if (this$shareProcessNamespace == null) {
               if (other$shareProcessNamespace != null) {
                  return false;
               }
            } else if (!this$shareProcessNamespace.equals(other$shareProcessNamespace)) {
               return false;
            }

            Object this$terminationGracePeriodSeconds = this.getTerminationGracePeriodSeconds();
            Object other$terminationGracePeriodSeconds = other.getTerminationGracePeriodSeconds();
            if (this$terminationGracePeriodSeconds == null) {
               if (other$terminationGracePeriodSeconds != null) {
                  return false;
               }
            } else if (!this$terminationGracePeriodSeconds.equals(other$terminationGracePeriodSeconds)) {
               return false;
            }

            Object this$affinity = this.getAffinity();
            Object other$affinity = other.getAffinity();
            if (this$affinity == null) {
               if (other$affinity != null) {
                  return false;
               }
            } else if (!this$affinity.equals(other$affinity)) {
               return false;
            }

            Object this$containers = this.getContainers();
            Object other$containers = other.getContainers();
            if (this$containers == null) {
               if (other$containers != null) {
                  return false;
               }
            } else if (!this$containers.equals(other$containers)) {
               return false;
            }

            Object this$dnsConfig = this.getDnsConfig();
            Object other$dnsConfig = other.getDnsConfig();
            if (this$dnsConfig == null) {
               if (other$dnsConfig != null) {
                  return false;
               }
            } else if (!this$dnsConfig.equals(other$dnsConfig)) {
               return false;
            }

            Object this$dnsPolicy = this.getDnsPolicy();
            Object other$dnsPolicy = other.getDnsPolicy();
            if (this$dnsPolicy == null) {
               if (other$dnsPolicy != null) {
                  return false;
               }
            } else if (!this$dnsPolicy.equals(other$dnsPolicy)) {
               return false;
            }

            Object this$ephemeralContainers = this.getEphemeralContainers();
            Object other$ephemeralContainers = other.getEphemeralContainers();
            if (this$ephemeralContainers == null) {
               if (other$ephemeralContainers != null) {
                  return false;
               }
            } else if (!this$ephemeralContainers.equals(other$ephemeralContainers)) {
               return false;
            }

            Object this$hostAliases = this.getHostAliases();
            Object other$hostAliases = other.getHostAliases();
            if (this$hostAliases == null) {
               if (other$hostAliases != null) {
                  return false;
               }
            } else if (!this$hostAliases.equals(other$hostAliases)) {
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

            Object this$imagePullSecrets = this.getImagePullSecrets();
            Object other$imagePullSecrets = other.getImagePullSecrets();
            if (this$imagePullSecrets == null) {
               if (other$imagePullSecrets != null) {
                  return false;
               }
            } else if (!this$imagePullSecrets.equals(other$imagePullSecrets)) {
               return false;
            }

            Object this$initContainers = this.getInitContainers();
            Object other$initContainers = other.getInitContainers();
            if (this$initContainers == null) {
               if (other$initContainers != null) {
                  return false;
               }
            } else if (!this$initContainers.equals(other$initContainers)) {
               return false;
            }

            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$nodeSelector = this.getNodeSelector();
            Object other$nodeSelector = other.getNodeSelector();
            if (this$nodeSelector == null) {
               if (other$nodeSelector != null) {
                  return false;
               }
            } else if (!this$nodeSelector.equals(other$nodeSelector)) {
               return false;
            }

            Object this$os = this.getOs();
            Object other$os = other.getOs();
            if (this$os == null) {
               if (other$os != null) {
                  return false;
               }
            } else if (!this$os.equals(other$os)) {
               return false;
            }

            Object this$overhead = this.getOverhead();
            Object other$overhead = other.getOverhead();
            if (this$overhead == null) {
               if (other$overhead != null) {
                  return false;
               }
            } else if (!this$overhead.equals(other$overhead)) {
               return false;
            }

            Object this$preemptionPolicy = this.getPreemptionPolicy();
            Object other$preemptionPolicy = other.getPreemptionPolicy();
            if (this$preemptionPolicy == null) {
               if (other$preemptionPolicy != null) {
                  return false;
               }
            } else if (!this$preemptionPolicy.equals(other$preemptionPolicy)) {
               return false;
            }

            Object this$priorityClassName = this.getPriorityClassName();
            Object other$priorityClassName = other.getPriorityClassName();
            if (this$priorityClassName == null) {
               if (other$priorityClassName != null) {
                  return false;
               }
            } else if (!this$priorityClassName.equals(other$priorityClassName)) {
               return false;
            }

            Object this$readinessGates = this.getReadinessGates();
            Object other$readinessGates = other.getReadinessGates();
            if (this$readinessGates == null) {
               if (other$readinessGates != null) {
                  return false;
               }
            } else if (!this$readinessGates.equals(other$readinessGates)) {
               return false;
            }

            Object this$resourceClaims = this.getResourceClaims();
            Object other$resourceClaims = other.getResourceClaims();
            if (this$resourceClaims == null) {
               if (other$resourceClaims != null) {
                  return false;
               }
            } else if (!this$resourceClaims.equals(other$resourceClaims)) {
               return false;
            }

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
               return false;
            }

            Object this$restartPolicy = this.getRestartPolicy();
            Object other$restartPolicy = other.getRestartPolicy();
            if (this$restartPolicy == null) {
               if (other$restartPolicy != null) {
                  return false;
               }
            } else if (!this$restartPolicy.equals(other$restartPolicy)) {
               return false;
            }

            Object this$runtimeClassName = this.getRuntimeClassName();
            Object other$runtimeClassName = other.getRuntimeClassName();
            if (this$runtimeClassName == null) {
               if (other$runtimeClassName != null) {
                  return false;
               }
            } else if (!this$runtimeClassName.equals(other$runtimeClassName)) {
               return false;
            }

            Object this$schedulerName = this.getSchedulerName();
            Object other$schedulerName = other.getSchedulerName();
            if (this$schedulerName == null) {
               if (other$schedulerName != null) {
                  return false;
               }
            } else if (!this$schedulerName.equals(other$schedulerName)) {
               return false;
            }

            Object this$schedulingGates = this.getSchedulingGates();
            Object other$schedulingGates = other.getSchedulingGates();
            if (this$schedulingGates == null) {
               if (other$schedulingGates != null) {
                  return false;
               }
            } else if (!this$schedulingGates.equals(other$schedulingGates)) {
               return false;
            }

            Object this$securityContext = this.getSecurityContext();
            Object other$securityContext = other.getSecurityContext();
            if (this$securityContext == null) {
               if (other$securityContext != null) {
                  return false;
               }
            } else if (!this$securityContext.equals(other$securityContext)) {
               return false;
            }

            Object this$serviceAccount = this.getServiceAccount();
            Object other$serviceAccount = other.getServiceAccount();
            if (this$serviceAccount == null) {
               if (other$serviceAccount != null) {
                  return false;
               }
            } else if (!this$serviceAccount.equals(other$serviceAccount)) {
               return false;
            }

            Object this$serviceAccountName = this.getServiceAccountName();
            Object other$serviceAccountName = other.getServiceAccountName();
            if (this$serviceAccountName == null) {
               if (other$serviceAccountName != null) {
                  return false;
               }
            } else if (!this$serviceAccountName.equals(other$serviceAccountName)) {
               return false;
            }

            Object this$subdomain = this.getSubdomain();
            Object other$subdomain = other.getSubdomain();
            if (this$subdomain == null) {
               if (other$subdomain != null) {
                  return false;
               }
            } else if (!this$subdomain.equals(other$subdomain)) {
               return false;
            }

            Object this$tolerations = this.getTolerations();
            Object other$tolerations = other.getTolerations();
            if (this$tolerations == null) {
               if (other$tolerations != null) {
                  return false;
               }
            } else if (!this$tolerations.equals(other$tolerations)) {
               return false;
            }

            Object this$topologySpreadConstraints = this.getTopologySpreadConstraints();
            Object other$topologySpreadConstraints = other.getTopologySpreadConstraints();
            if (this$topologySpreadConstraints == null) {
               if (other$topologySpreadConstraints != null) {
                  return false;
               }
            } else if (!this$topologySpreadConstraints.equals(other$topologySpreadConstraints)) {
               return false;
            }

            Object this$volumes = this.getVolumes();
            Object other$volumes = other.getVolumes();
            if (this$volumes == null) {
               if (other$volumes != null) {
                  return false;
               }
            } else if (!this$volumes.equals(other$volumes)) {
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
      return other instanceof PodSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $activeDeadlineSeconds = this.getActiveDeadlineSeconds();
      result = result * 59 + ($activeDeadlineSeconds == null ? 43 : $activeDeadlineSeconds.hashCode());
      Object $automountServiceAccountToken = this.getAutomountServiceAccountToken();
      result = result * 59 + ($automountServiceAccountToken == null ? 43 : $automountServiceAccountToken.hashCode());
      Object $enableServiceLinks = this.getEnableServiceLinks();
      result = result * 59 + ($enableServiceLinks == null ? 43 : $enableServiceLinks.hashCode());
      Object $hostIPC = this.getHostIPC();
      result = result * 59 + ($hostIPC == null ? 43 : $hostIPC.hashCode());
      Object $hostNetwork = this.getHostNetwork();
      result = result * 59 + ($hostNetwork == null ? 43 : $hostNetwork.hashCode());
      Object $hostPID = this.getHostPID();
      result = result * 59 + ($hostPID == null ? 43 : $hostPID.hashCode());
      Object $hostUsers = this.getHostUsers();
      result = result * 59 + ($hostUsers == null ? 43 : $hostUsers.hashCode());
      Object $priority = this.getPriority();
      result = result * 59 + ($priority == null ? 43 : $priority.hashCode());
      Object $setHostnameAsFQDN = this.getSetHostnameAsFQDN();
      result = result * 59 + ($setHostnameAsFQDN == null ? 43 : $setHostnameAsFQDN.hashCode());
      Object $shareProcessNamespace = this.getShareProcessNamespace();
      result = result * 59 + ($shareProcessNamespace == null ? 43 : $shareProcessNamespace.hashCode());
      Object $terminationGracePeriodSeconds = this.getTerminationGracePeriodSeconds();
      result = result * 59 + ($terminationGracePeriodSeconds == null ? 43 : $terminationGracePeriodSeconds.hashCode());
      Object $affinity = this.getAffinity();
      result = result * 59 + ($affinity == null ? 43 : $affinity.hashCode());
      Object $containers = this.getContainers();
      result = result * 59 + ($containers == null ? 43 : $containers.hashCode());
      Object $dnsConfig = this.getDnsConfig();
      result = result * 59 + ($dnsConfig == null ? 43 : $dnsConfig.hashCode());
      Object $dnsPolicy = this.getDnsPolicy();
      result = result * 59 + ($dnsPolicy == null ? 43 : $dnsPolicy.hashCode());
      Object $ephemeralContainers = this.getEphemeralContainers();
      result = result * 59 + ($ephemeralContainers == null ? 43 : $ephemeralContainers.hashCode());
      Object $hostAliases = this.getHostAliases();
      result = result * 59 + ($hostAliases == null ? 43 : $hostAliases.hashCode());
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $imagePullSecrets = this.getImagePullSecrets();
      result = result * 59 + ($imagePullSecrets == null ? 43 : $imagePullSecrets.hashCode());
      Object $initContainers = this.getInitContainers();
      result = result * 59 + ($initContainers == null ? 43 : $initContainers.hashCode());
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $nodeSelector = this.getNodeSelector();
      result = result * 59 + ($nodeSelector == null ? 43 : $nodeSelector.hashCode());
      Object $os = this.getOs();
      result = result * 59 + ($os == null ? 43 : $os.hashCode());
      Object $overhead = this.getOverhead();
      result = result * 59 + ($overhead == null ? 43 : $overhead.hashCode());
      Object $preemptionPolicy = this.getPreemptionPolicy();
      result = result * 59 + ($preemptionPolicy == null ? 43 : $preemptionPolicy.hashCode());
      Object $priorityClassName = this.getPriorityClassName();
      result = result * 59 + ($priorityClassName == null ? 43 : $priorityClassName.hashCode());
      Object $readinessGates = this.getReadinessGates();
      result = result * 59 + ($readinessGates == null ? 43 : $readinessGates.hashCode());
      Object $resourceClaims = this.getResourceClaims();
      result = result * 59 + ($resourceClaims == null ? 43 : $resourceClaims.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $restartPolicy = this.getRestartPolicy();
      result = result * 59 + ($restartPolicy == null ? 43 : $restartPolicy.hashCode());
      Object $runtimeClassName = this.getRuntimeClassName();
      result = result * 59 + ($runtimeClassName == null ? 43 : $runtimeClassName.hashCode());
      Object $schedulerName = this.getSchedulerName();
      result = result * 59 + ($schedulerName == null ? 43 : $schedulerName.hashCode());
      Object $schedulingGates = this.getSchedulingGates();
      result = result * 59 + ($schedulingGates == null ? 43 : $schedulingGates.hashCode());
      Object $securityContext = this.getSecurityContext();
      result = result * 59 + ($securityContext == null ? 43 : $securityContext.hashCode());
      Object $serviceAccount = this.getServiceAccount();
      result = result * 59 + ($serviceAccount == null ? 43 : $serviceAccount.hashCode());
      Object $serviceAccountName = this.getServiceAccountName();
      result = result * 59 + ($serviceAccountName == null ? 43 : $serviceAccountName.hashCode());
      Object $subdomain = this.getSubdomain();
      result = result * 59 + ($subdomain == null ? 43 : $subdomain.hashCode());
      Object $tolerations = this.getTolerations();
      result = result * 59 + ($tolerations == null ? 43 : $tolerations.hashCode());
      Object $topologySpreadConstraints = this.getTopologySpreadConstraints();
      result = result * 59 + ($topologySpreadConstraints == null ? 43 : $topologySpreadConstraints.hashCode());
      Object $volumes = this.getVolumes();
      result = result * 59 + ($volumes == null ? 43 : $volumes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
