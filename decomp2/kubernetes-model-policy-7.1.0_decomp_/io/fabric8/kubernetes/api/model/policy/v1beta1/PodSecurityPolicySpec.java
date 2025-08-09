package io.fabric8.kubernetes.api.model.policy.v1beta1;

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
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "allowPrivilegeEscalation", "allowedCSIDrivers", "allowedCapabilities", "allowedFlexVolumes", "allowedHostPaths", "allowedProcMountTypes", "allowedUnsafeSysctls", "defaultAddCapabilities", "defaultAllowPrivilegeEscalation", "forbiddenSysctls", "fsGroup", "hostIPC", "hostNetwork", "hostPID", "hostPorts", "privileged", "readOnlyRootFilesystem", "requiredDropCapabilities", "runAsGroup", "runAsUser", "runtimeClass", "seLinux", "supplementalGroups", "volumes"})
public class PodSecurityPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("allowPrivilegeEscalation")
   private Boolean allowPrivilegeEscalation;
   @JsonProperty("allowedCSIDrivers")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedCSIDrivers = new ArrayList();
   @JsonProperty("allowedCapabilities")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedCapabilities = new ArrayList();
   @JsonProperty("allowedFlexVolumes")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedFlexVolumes = new ArrayList();
   @JsonProperty("allowedHostPaths")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedHostPaths = new ArrayList();
   @JsonProperty("allowedProcMountTypes")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedProcMountTypes = new ArrayList();
   @JsonProperty("allowedUnsafeSysctls")
   @JsonInclude(Include.NON_EMPTY)
   private List allowedUnsafeSysctls = new ArrayList();
   @JsonProperty("defaultAddCapabilities")
   @JsonInclude(Include.NON_EMPTY)
   private List defaultAddCapabilities = new ArrayList();
   @JsonProperty("defaultAllowPrivilegeEscalation")
   private Boolean defaultAllowPrivilegeEscalation;
   @JsonProperty("forbiddenSysctls")
   @JsonInclude(Include.NON_EMPTY)
   private List forbiddenSysctls = new ArrayList();
   @JsonProperty("fsGroup")
   private FSGroupStrategyOptions fsGroup;
   @JsonProperty("hostIPC")
   private Boolean hostIPC;
   @JsonProperty("hostNetwork")
   private Boolean hostNetwork;
   @JsonProperty("hostPID")
   private Boolean hostPID;
   @JsonProperty("hostPorts")
   @JsonInclude(Include.NON_EMPTY)
   private List hostPorts = new ArrayList();
   @JsonProperty("privileged")
   private Boolean privileged;
   @JsonProperty("readOnlyRootFilesystem")
   private Boolean readOnlyRootFilesystem;
   @JsonProperty("requiredDropCapabilities")
   @JsonInclude(Include.NON_EMPTY)
   private List requiredDropCapabilities = new ArrayList();
   @JsonProperty("runAsGroup")
   private RunAsGroupStrategyOptions runAsGroup;
   @JsonProperty("runAsUser")
   private RunAsUserStrategyOptions runAsUser;
   @JsonProperty("runtimeClass")
   private RuntimeClassStrategyOptions runtimeClass;
   @JsonProperty("seLinux")
   private SELinuxStrategyOptions seLinux;
   @JsonProperty("supplementalGroups")
   private SupplementalGroupsStrategyOptions supplementalGroups;
   @JsonProperty("volumes")
   @JsonInclude(Include.NON_EMPTY)
   private List volumes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodSecurityPolicySpec() {
   }

   public PodSecurityPolicySpec(Boolean allowPrivilegeEscalation, List allowedCSIDrivers, List allowedCapabilities, List allowedFlexVolumes, List allowedHostPaths, List allowedProcMountTypes, List allowedUnsafeSysctls, List defaultAddCapabilities, Boolean defaultAllowPrivilegeEscalation, List forbiddenSysctls, FSGroupStrategyOptions fsGroup, Boolean hostIPC, Boolean hostNetwork, Boolean hostPID, List hostPorts, Boolean privileged, Boolean readOnlyRootFilesystem, List requiredDropCapabilities, RunAsGroupStrategyOptions runAsGroup, RunAsUserStrategyOptions runAsUser, RuntimeClassStrategyOptions runtimeClass, SELinuxStrategyOptions seLinux, SupplementalGroupsStrategyOptions supplementalGroups, List volumes) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
      this.allowedCSIDrivers = allowedCSIDrivers;
      this.allowedCapabilities = allowedCapabilities;
      this.allowedFlexVolumes = allowedFlexVolumes;
      this.allowedHostPaths = allowedHostPaths;
      this.allowedProcMountTypes = allowedProcMountTypes;
      this.allowedUnsafeSysctls = allowedUnsafeSysctls;
      this.defaultAddCapabilities = defaultAddCapabilities;
      this.defaultAllowPrivilegeEscalation = defaultAllowPrivilegeEscalation;
      this.forbiddenSysctls = forbiddenSysctls;
      this.fsGroup = fsGroup;
      this.hostIPC = hostIPC;
      this.hostNetwork = hostNetwork;
      this.hostPID = hostPID;
      this.hostPorts = hostPorts;
      this.privileged = privileged;
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
      this.requiredDropCapabilities = requiredDropCapabilities;
      this.runAsGroup = runAsGroup;
      this.runAsUser = runAsUser;
      this.runtimeClass = runtimeClass;
      this.seLinux = seLinux;
      this.supplementalGroups = supplementalGroups;
      this.volumes = volumes;
   }

   @JsonProperty("allowPrivilegeEscalation")
   public Boolean getAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation;
   }

   @JsonProperty("allowPrivilegeEscalation")
   public void setAllowPrivilegeEscalation(Boolean allowPrivilegeEscalation) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
   }

   @JsonProperty("allowedCSIDrivers")
   public List getAllowedCSIDrivers() {
      return this.allowedCSIDrivers;
   }

   @JsonProperty("allowedCSIDrivers")
   public void setAllowedCSIDrivers(List allowedCSIDrivers) {
      this.allowedCSIDrivers = allowedCSIDrivers;
   }

   @JsonProperty("allowedCapabilities")
   public List getAllowedCapabilities() {
      return this.allowedCapabilities;
   }

   @JsonProperty("allowedCapabilities")
   public void setAllowedCapabilities(List allowedCapabilities) {
      this.allowedCapabilities = allowedCapabilities;
   }

   @JsonProperty("allowedFlexVolumes")
   public List getAllowedFlexVolumes() {
      return this.allowedFlexVolumes;
   }

   @JsonProperty("allowedFlexVolumes")
   public void setAllowedFlexVolumes(List allowedFlexVolumes) {
      this.allowedFlexVolumes = allowedFlexVolumes;
   }

   @JsonProperty("allowedHostPaths")
   public List getAllowedHostPaths() {
      return this.allowedHostPaths;
   }

   @JsonProperty("allowedHostPaths")
   public void setAllowedHostPaths(List allowedHostPaths) {
      this.allowedHostPaths = allowedHostPaths;
   }

   @JsonProperty("allowedProcMountTypes")
   public List getAllowedProcMountTypes() {
      return this.allowedProcMountTypes;
   }

   @JsonProperty("allowedProcMountTypes")
   public void setAllowedProcMountTypes(List allowedProcMountTypes) {
      this.allowedProcMountTypes = allowedProcMountTypes;
   }

   @JsonProperty("allowedUnsafeSysctls")
   public List getAllowedUnsafeSysctls() {
      return this.allowedUnsafeSysctls;
   }

   @JsonProperty("allowedUnsafeSysctls")
   public void setAllowedUnsafeSysctls(List allowedUnsafeSysctls) {
      this.allowedUnsafeSysctls = allowedUnsafeSysctls;
   }

   @JsonProperty("defaultAddCapabilities")
   public List getDefaultAddCapabilities() {
      return this.defaultAddCapabilities;
   }

   @JsonProperty("defaultAddCapabilities")
   public void setDefaultAddCapabilities(List defaultAddCapabilities) {
      this.defaultAddCapabilities = defaultAddCapabilities;
   }

   @JsonProperty("defaultAllowPrivilegeEscalation")
   public Boolean getDefaultAllowPrivilegeEscalation() {
      return this.defaultAllowPrivilegeEscalation;
   }

   @JsonProperty("defaultAllowPrivilegeEscalation")
   public void setDefaultAllowPrivilegeEscalation(Boolean defaultAllowPrivilegeEscalation) {
      this.defaultAllowPrivilegeEscalation = defaultAllowPrivilegeEscalation;
   }

   @JsonProperty("forbiddenSysctls")
   public List getForbiddenSysctls() {
      return this.forbiddenSysctls;
   }

   @JsonProperty("forbiddenSysctls")
   public void setForbiddenSysctls(List forbiddenSysctls) {
      this.forbiddenSysctls = forbiddenSysctls;
   }

   @JsonProperty("fsGroup")
   public FSGroupStrategyOptions getFsGroup() {
      return this.fsGroup;
   }

   @JsonProperty("fsGroup")
   public void setFsGroup(FSGroupStrategyOptions fsGroup) {
      this.fsGroup = fsGroup;
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

   @JsonProperty("hostPorts")
   public List getHostPorts() {
      return this.hostPorts;
   }

   @JsonProperty("hostPorts")
   public void setHostPorts(List hostPorts) {
      this.hostPorts = hostPorts;
   }

   @JsonProperty("privileged")
   public Boolean getPrivileged() {
      return this.privileged;
   }

   @JsonProperty("privileged")
   public void setPrivileged(Boolean privileged) {
      this.privileged = privileged;
   }

   @JsonProperty("readOnlyRootFilesystem")
   public Boolean getReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem;
   }

   @JsonProperty("readOnlyRootFilesystem")
   public void setReadOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
   }

   @JsonProperty("requiredDropCapabilities")
   public List getRequiredDropCapabilities() {
      return this.requiredDropCapabilities;
   }

   @JsonProperty("requiredDropCapabilities")
   public void setRequiredDropCapabilities(List requiredDropCapabilities) {
      this.requiredDropCapabilities = requiredDropCapabilities;
   }

   @JsonProperty("runAsGroup")
   public RunAsGroupStrategyOptions getRunAsGroup() {
      return this.runAsGroup;
   }

   @JsonProperty("runAsGroup")
   public void setRunAsGroup(RunAsGroupStrategyOptions runAsGroup) {
      this.runAsGroup = runAsGroup;
   }

   @JsonProperty("runAsUser")
   public RunAsUserStrategyOptions getRunAsUser() {
      return this.runAsUser;
   }

   @JsonProperty("runAsUser")
   public void setRunAsUser(RunAsUserStrategyOptions runAsUser) {
      this.runAsUser = runAsUser;
   }

   @JsonProperty("runtimeClass")
   public RuntimeClassStrategyOptions getRuntimeClass() {
      return this.runtimeClass;
   }

   @JsonProperty("runtimeClass")
   public void setRuntimeClass(RuntimeClassStrategyOptions runtimeClass) {
      this.runtimeClass = runtimeClass;
   }

   @JsonProperty("seLinux")
   public SELinuxStrategyOptions getSeLinux() {
      return this.seLinux;
   }

   @JsonProperty("seLinux")
   public void setSeLinux(SELinuxStrategyOptions seLinux) {
      this.seLinux = seLinux;
   }

   @JsonProperty("supplementalGroups")
   public SupplementalGroupsStrategyOptions getSupplementalGroups() {
      return this.supplementalGroups;
   }

   @JsonProperty("supplementalGroups")
   public void setSupplementalGroups(SupplementalGroupsStrategyOptions supplementalGroups) {
      this.supplementalGroups = supplementalGroups;
   }

   @JsonProperty("volumes")
   public List getVolumes() {
      return this.volumes;
   }

   @JsonProperty("volumes")
   public void setVolumes(List volumes) {
      this.volumes = volumes;
   }

   @JsonIgnore
   public PodSecurityPolicySpecBuilder edit() {
      return new PodSecurityPolicySpecBuilder(this);
   }

   @JsonIgnore
   public PodSecurityPolicySpecBuilder toBuilder() {
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

   @Generated
   public String toString() {
      Boolean var10000 = this.getAllowPrivilegeEscalation();
      return "PodSecurityPolicySpec(allowPrivilegeEscalation=" + var10000 + ", allowedCSIDrivers=" + this.getAllowedCSIDrivers() + ", allowedCapabilities=" + this.getAllowedCapabilities() + ", allowedFlexVolumes=" + this.getAllowedFlexVolumes() + ", allowedHostPaths=" + this.getAllowedHostPaths() + ", allowedProcMountTypes=" + this.getAllowedProcMountTypes() + ", allowedUnsafeSysctls=" + this.getAllowedUnsafeSysctls() + ", defaultAddCapabilities=" + this.getDefaultAddCapabilities() + ", defaultAllowPrivilegeEscalation=" + this.getDefaultAllowPrivilegeEscalation() + ", forbiddenSysctls=" + this.getForbiddenSysctls() + ", fsGroup=" + this.getFsGroup() + ", hostIPC=" + this.getHostIPC() + ", hostNetwork=" + this.getHostNetwork() + ", hostPID=" + this.getHostPID() + ", hostPorts=" + this.getHostPorts() + ", privileged=" + this.getPrivileged() + ", readOnlyRootFilesystem=" + this.getReadOnlyRootFilesystem() + ", requiredDropCapabilities=" + this.getRequiredDropCapabilities() + ", runAsGroup=" + this.getRunAsGroup() + ", runAsUser=" + this.getRunAsUser() + ", runtimeClass=" + this.getRuntimeClass() + ", seLinux=" + this.getSeLinux() + ", supplementalGroups=" + this.getSupplementalGroups() + ", volumes=" + this.getVolumes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodSecurityPolicySpec)) {
         return false;
      } else {
         PodSecurityPolicySpec other = (PodSecurityPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$allowPrivilegeEscalation = this.getAllowPrivilegeEscalation();
            Object other$allowPrivilegeEscalation = other.getAllowPrivilegeEscalation();
            if (this$allowPrivilegeEscalation == null) {
               if (other$allowPrivilegeEscalation != null) {
                  return false;
               }
            } else if (!this$allowPrivilegeEscalation.equals(other$allowPrivilegeEscalation)) {
               return false;
            }

            Object this$defaultAllowPrivilegeEscalation = this.getDefaultAllowPrivilegeEscalation();
            Object other$defaultAllowPrivilegeEscalation = other.getDefaultAllowPrivilegeEscalation();
            if (this$defaultAllowPrivilegeEscalation == null) {
               if (other$defaultAllowPrivilegeEscalation != null) {
                  return false;
               }
            } else if (!this$defaultAllowPrivilegeEscalation.equals(other$defaultAllowPrivilegeEscalation)) {
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

            Object this$privileged = this.getPrivileged();
            Object other$privileged = other.getPrivileged();
            if (this$privileged == null) {
               if (other$privileged != null) {
                  return false;
               }
            } else if (!this$privileged.equals(other$privileged)) {
               return false;
            }

            Object this$readOnlyRootFilesystem = this.getReadOnlyRootFilesystem();
            Object other$readOnlyRootFilesystem = other.getReadOnlyRootFilesystem();
            if (this$readOnlyRootFilesystem == null) {
               if (other$readOnlyRootFilesystem != null) {
                  return false;
               }
            } else if (!this$readOnlyRootFilesystem.equals(other$readOnlyRootFilesystem)) {
               return false;
            }

            Object this$allowedCSIDrivers = this.getAllowedCSIDrivers();
            Object other$allowedCSIDrivers = other.getAllowedCSIDrivers();
            if (this$allowedCSIDrivers == null) {
               if (other$allowedCSIDrivers != null) {
                  return false;
               }
            } else if (!this$allowedCSIDrivers.equals(other$allowedCSIDrivers)) {
               return false;
            }

            Object this$allowedCapabilities = this.getAllowedCapabilities();
            Object other$allowedCapabilities = other.getAllowedCapabilities();
            if (this$allowedCapabilities == null) {
               if (other$allowedCapabilities != null) {
                  return false;
               }
            } else if (!this$allowedCapabilities.equals(other$allowedCapabilities)) {
               return false;
            }

            Object this$allowedFlexVolumes = this.getAllowedFlexVolumes();
            Object other$allowedFlexVolumes = other.getAllowedFlexVolumes();
            if (this$allowedFlexVolumes == null) {
               if (other$allowedFlexVolumes != null) {
                  return false;
               }
            } else if (!this$allowedFlexVolumes.equals(other$allowedFlexVolumes)) {
               return false;
            }

            Object this$allowedHostPaths = this.getAllowedHostPaths();
            Object other$allowedHostPaths = other.getAllowedHostPaths();
            if (this$allowedHostPaths == null) {
               if (other$allowedHostPaths != null) {
                  return false;
               }
            } else if (!this$allowedHostPaths.equals(other$allowedHostPaths)) {
               return false;
            }

            Object this$allowedProcMountTypes = this.getAllowedProcMountTypes();
            Object other$allowedProcMountTypes = other.getAllowedProcMountTypes();
            if (this$allowedProcMountTypes == null) {
               if (other$allowedProcMountTypes != null) {
                  return false;
               }
            } else if (!this$allowedProcMountTypes.equals(other$allowedProcMountTypes)) {
               return false;
            }

            Object this$allowedUnsafeSysctls = this.getAllowedUnsafeSysctls();
            Object other$allowedUnsafeSysctls = other.getAllowedUnsafeSysctls();
            if (this$allowedUnsafeSysctls == null) {
               if (other$allowedUnsafeSysctls != null) {
                  return false;
               }
            } else if (!this$allowedUnsafeSysctls.equals(other$allowedUnsafeSysctls)) {
               return false;
            }

            Object this$defaultAddCapabilities = this.getDefaultAddCapabilities();
            Object other$defaultAddCapabilities = other.getDefaultAddCapabilities();
            if (this$defaultAddCapabilities == null) {
               if (other$defaultAddCapabilities != null) {
                  return false;
               }
            } else if (!this$defaultAddCapabilities.equals(other$defaultAddCapabilities)) {
               return false;
            }

            Object this$forbiddenSysctls = this.getForbiddenSysctls();
            Object other$forbiddenSysctls = other.getForbiddenSysctls();
            if (this$forbiddenSysctls == null) {
               if (other$forbiddenSysctls != null) {
                  return false;
               }
            } else if (!this$forbiddenSysctls.equals(other$forbiddenSysctls)) {
               return false;
            }

            Object this$fsGroup = this.getFsGroup();
            Object other$fsGroup = other.getFsGroup();
            if (this$fsGroup == null) {
               if (other$fsGroup != null) {
                  return false;
               }
            } else if (!this$fsGroup.equals(other$fsGroup)) {
               return false;
            }

            Object this$hostPorts = this.getHostPorts();
            Object other$hostPorts = other.getHostPorts();
            if (this$hostPorts == null) {
               if (other$hostPorts != null) {
                  return false;
               }
            } else if (!this$hostPorts.equals(other$hostPorts)) {
               return false;
            }

            Object this$requiredDropCapabilities = this.getRequiredDropCapabilities();
            Object other$requiredDropCapabilities = other.getRequiredDropCapabilities();
            if (this$requiredDropCapabilities == null) {
               if (other$requiredDropCapabilities != null) {
                  return false;
               }
            } else if (!this$requiredDropCapabilities.equals(other$requiredDropCapabilities)) {
               return false;
            }

            Object this$runAsGroup = this.getRunAsGroup();
            Object other$runAsGroup = other.getRunAsGroup();
            if (this$runAsGroup == null) {
               if (other$runAsGroup != null) {
                  return false;
               }
            } else if (!this$runAsGroup.equals(other$runAsGroup)) {
               return false;
            }

            Object this$runAsUser = this.getRunAsUser();
            Object other$runAsUser = other.getRunAsUser();
            if (this$runAsUser == null) {
               if (other$runAsUser != null) {
                  return false;
               }
            } else if (!this$runAsUser.equals(other$runAsUser)) {
               return false;
            }

            Object this$runtimeClass = this.getRuntimeClass();
            Object other$runtimeClass = other.getRuntimeClass();
            if (this$runtimeClass == null) {
               if (other$runtimeClass != null) {
                  return false;
               }
            } else if (!this$runtimeClass.equals(other$runtimeClass)) {
               return false;
            }

            Object this$seLinux = this.getSeLinux();
            Object other$seLinux = other.getSeLinux();
            if (this$seLinux == null) {
               if (other$seLinux != null) {
                  return false;
               }
            } else if (!this$seLinux.equals(other$seLinux)) {
               return false;
            }

            Object this$supplementalGroups = this.getSupplementalGroups();
            Object other$supplementalGroups = other.getSupplementalGroups();
            if (this$supplementalGroups == null) {
               if (other$supplementalGroups != null) {
                  return false;
               }
            } else if (!this$supplementalGroups.equals(other$supplementalGroups)) {
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
      return other instanceof PodSecurityPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowPrivilegeEscalation = this.getAllowPrivilegeEscalation();
      result = result * 59 + ($allowPrivilegeEscalation == null ? 43 : $allowPrivilegeEscalation.hashCode());
      Object $defaultAllowPrivilegeEscalation = this.getDefaultAllowPrivilegeEscalation();
      result = result * 59 + ($defaultAllowPrivilegeEscalation == null ? 43 : $defaultAllowPrivilegeEscalation.hashCode());
      Object $hostIPC = this.getHostIPC();
      result = result * 59 + ($hostIPC == null ? 43 : $hostIPC.hashCode());
      Object $hostNetwork = this.getHostNetwork();
      result = result * 59 + ($hostNetwork == null ? 43 : $hostNetwork.hashCode());
      Object $hostPID = this.getHostPID();
      result = result * 59 + ($hostPID == null ? 43 : $hostPID.hashCode());
      Object $privileged = this.getPrivileged();
      result = result * 59 + ($privileged == null ? 43 : $privileged.hashCode());
      Object $readOnlyRootFilesystem = this.getReadOnlyRootFilesystem();
      result = result * 59 + ($readOnlyRootFilesystem == null ? 43 : $readOnlyRootFilesystem.hashCode());
      Object $allowedCSIDrivers = this.getAllowedCSIDrivers();
      result = result * 59 + ($allowedCSIDrivers == null ? 43 : $allowedCSIDrivers.hashCode());
      Object $allowedCapabilities = this.getAllowedCapabilities();
      result = result * 59 + ($allowedCapabilities == null ? 43 : $allowedCapabilities.hashCode());
      Object $allowedFlexVolumes = this.getAllowedFlexVolumes();
      result = result * 59 + ($allowedFlexVolumes == null ? 43 : $allowedFlexVolumes.hashCode());
      Object $allowedHostPaths = this.getAllowedHostPaths();
      result = result * 59 + ($allowedHostPaths == null ? 43 : $allowedHostPaths.hashCode());
      Object $allowedProcMountTypes = this.getAllowedProcMountTypes();
      result = result * 59 + ($allowedProcMountTypes == null ? 43 : $allowedProcMountTypes.hashCode());
      Object $allowedUnsafeSysctls = this.getAllowedUnsafeSysctls();
      result = result * 59 + ($allowedUnsafeSysctls == null ? 43 : $allowedUnsafeSysctls.hashCode());
      Object $defaultAddCapabilities = this.getDefaultAddCapabilities();
      result = result * 59 + ($defaultAddCapabilities == null ? 43 : $defaultAddCapabilities.hashCode());
      Object $forbiddenSysctls = this.getForbiddenSysctls();
      result = result * 59 + ($forbiddenSysctls == null ? 43 : $forbiddenSysctls.hashCode());
      Object $fsGroup = this.getFsGroup();
      result = result * 59 + ($fsGroup == null ? 43 : $fsGroup.hashCode());
      Object $hostPorts = this.getHostPorts();
      result = result * 59 + ($hostPorts == null ? 43 : $hostPorts.hashCode());
      Object $requiredDropCapabilities = this.getRequiredDropCapabilities();
      result = result * 59 + ($requiredDropCapabilities == null ? 43 : $requiredDropCapabilities.hashCode());
      Object $runAsGroup = this.getRunAsGroup();
      result = result * 59 + ($runAsGroup == null ? 43 : $runAsGroup.hashCode());
      Object $runAsUser = this.getRunAsUser();
      result = result * 59 + ($runAsUser == null ? 43 : $runAsUser.hashCode());
      Object $runtimeClass = this.getRuntimeClass();
      result = result * 59 + ($runtimeClass == null ? 43 : $runtimeClass.hashCode());
      Object $seLinux = this.getSeLinux();
      result = result * 59 + ($seLinux == null ? 43 : $seLinux.hashCode());
      Object $supplementalGroups = this.getSupplementalGroups();
      result = result * 59 + ($supplementalGroups == null ? 43 : $supplementalGroups.hashCode());
      Object $volumes = this.getVolumes();
      result = result * 59 + ($volumes == null ? 43 : $volumes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }
}
