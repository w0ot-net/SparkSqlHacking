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
@JsonPropertyOrder({"appArmorProfile", "fsGroup", "fsGroupChangePolicy", "runAsGroup", "runAsNonRoot", "runAsUser", "seLinuxChangePolicy", "seLinuxOptions", "seccompProfile", "supplementalGroups", "supplementalGroupsPolicy", "sysctls", "windowsOptions"})
public class PodSecurityContext implements Editable, KubernetesResource {
   @JsonProperty("appArmorProfile")
   private AppArmorProfile appArmorProfile;
   @JsonProperty("fsGroup")
   private Long fsGroup;
   @JsonProperty("fsGroupChangePolicy")
   private String fsGroupChangePolicy;
   @JsonProperty("runAsGroup")
   private Long runAsGroup;
   @JsonProperty("runAsNonRoot")
   private Boolean runAsNonRoot;
   @JsonProperty("runAsUser")
   private Long runAsUser;
   @JsonProperty("seLinuxChangePolicy")
   private String seLinuxChangePolicy;
   @JsonProperty("seLinuxOptions")
   private SELinuxOptions seLinuxOptions;
   @JsonProperty("seccompProfile")
   private SeccompProfile seccompProfile;
   @JsonProperty("supplementalGroups")
   @JsonInclude(Include.NON_EMPTY)
   private List supplementalGroups = new ArrayList();
   @JsonProperty("supplementalGroupsPolicy")
   private String supplementalGroupsPolicy;
   @JsonProperty("sysctls")
   @JsonInclude(Include.NON_EMPTY)
   private List sysctls = new ArrayList();
   @JsonProperty("windowsOptions")
   private WindowsSecurityContextOptions windowsOptions;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodSecurityContext() {
   }

   public PodSecurityContext(AppArmorProfile appArmorProfile, Long fsGroup, String fsGroupChangePolicy, Long runAsGroup, Boolean runAsNonRoot, Long runAsUser, String seLinuxChangePolicy, SELinuxOptions seLinuxOptions, SeccompProfile seccompProfile, List supplementalGroups, String supplementalGroupsPolicy, List sysctls, WindowsSecurityContextOptions windowsOptions) {
      this.appArmorProfile = appArmorProfile;
      this.fsGroup = fsGroup;
      this.fsGroupChangePolicy = fsGroupChangePolicy;
      this.runAsGroup = runAsGroup;
      this.runAsNonRoot = runAsNonRoot;
      this.runAsUser = runAsUser;
      this.seLinuxChangePolicy = seLinuxChangePolicy;
      this.seLinuxOptions = seLinuxOptions;
      this.seccompProfile = seccompProfile;
      this.supplementalGroups = supplementalGroups;
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
      this.sysctls = sysctls;
      this.windowsOptions = windowsOptions;
   }

   @JsonProperty("appArmorProfile")
   public AppArmorProfile getAppArmorProfile() {
      return this.appArmorProfile;
   }

   @JsonProperty("appArmorProfile")
   public void setAppArmorProfile(AppArmorProfile appArmorProfile) {
      this.appArmorProfile = appArmorProfile;
   }

   @JsonProperty("fsGroup")
   public Long getFsGroup() {
      return this.fsGroup;
   }

   @JsonProperty("fsGroup")
   public void setFsGroup(Long fsGroup) {
      this.fsGroup = fsGroup;
   }

   @JsonProperty("fsGroupChangePolicy")
   public String getFsGroupChangePolicy() {
      return this.fsGroupChangePolicy;
   }

   @JsonProperty("fsGroupChangePolicy")
   public void setFsGroupChangePolicy(String fsGroupChangePolicy) {
      this.fsGroupChangePolicy = fsGroupChangePolicy;
   }

   @JsonProperty("runAsGroup")
   public Long getRunAsGroup() {
      return this.runAsGroup;
   }

   @JsonProperty("runAsGroup")
   public void setRunAsGroup(Long runAsGroup) {
      this.runAsGroup = runAsGroup;
   }

   @JsonProperty("runAsNonRoot")
   public Boolean getRunAsNonRoot() {
      return this.runAsNonRoot;
   }

   @JsonProperty("runAsNonRoot")
   public void setRunAsNonRoot(Boolean runAsNonRoot) {
      this.runAsNonRoot = runAsNonRoot;
   }

   @JsonProperty("runAsUser")
   public Long getRunAsUser() {
      return this.runAsUser;
   }

   @JsonProperty("runAsUser")
   public void setRunAsUser(Long runAsUser) {
      this.runAsUser = runAsUser;
   }

   @JsonProperty("seLinuxChangePolicy")
   public String getSeLinuxChangePolicy() {
      return this.seLinuxChangePolicy;
   }

   @JsonProperty("seLinuxChangePolicy")
   public void setSeLinuxChangePolicy(String seLinuxChangePolicy) {
      this.seLinuxChangePolicy = seLinuxChangePolicy;
   }

   @JsonProperty("seLinuxOptions")
   public SELinuxOptions getSeLinuxOptions() {
      return this.seLinuxOptions;
   }

   @JsonProperty("seLinuxOptions")
   public void setSeLinuxOptions(SELinuxOptions seLinuxOptions) {
      this.seLinuxOptions = seLinuxOptions;
   }

   @JsonProperty("seccompProfile")
   public SeccompProfile getSeccompProfile() {
      return this.seccompProfile;
   }

   @JsonProperty("seccompProfile")
   public void setSeccompProfile(SeccompProfile seccompProfile) {
      this.seccompProfile = seccompProfile;
   }

   @JsonProperty("supplementalGroups")
   @JsonInclude(Include.NON_EMPTY)
   public List getSupplementalGroups() {
      return this.supplementalGroups;
   }

   @JsonProperty("supplementalGroups")
   public void setSupplementalGroups(List supplementalGroups) {
      this.supplementalGroups = supplementalGroups;
   }

   @JsonProperty("supplementalGroupsPolicy")
   public String getSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy;
   }

   @JsonProperty("supplementalGroupsPolicy")
   public void setSupplementalGroupsPolicy(String supplementalGroupsPolicy) {
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
   }

   @JsonProperty("sysctls")
   @JsonInclude(Include.NON_EMPTY)
   public List getSysctls() {
      return this.sysctls;
   }

   @JsonProperty("sysctls")
   public void setSysctls(List sysctls) {
      this.sysctls = sysctls;
   }

   @JsonProperty("windowsOptions")
   public WindowsSecurityContextOptions getWindowsOptions() {
      return this.windowsOptions;
   }

   @JsonProperty("windowsOptions")
   public void setWindowsOptions(WindowsSecurityContextOptions windowsOptions) {
      this.windowsOptions = windowsOptions;
   }

   @JsonIgnore
   public PodSecurityContextBuilder edit() {
      return new PodSecurityContextBuilder(this);
   }

   @JsonIgnore
   public PodSecurityContextBuilder toBuilder() {
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
      AppArmorProfile var10000 = this.getAppArmorProfile();
      return "PodSecurityContext(appArmorProfile=" + var10000 + ", fsGroup=" + this.getFsGroup() + ", fsGroupChangePolicy=" + this.getFsGroupChangePolicy() + ", runAsGroup=" + this.getRunAsGroup() + ", runAsNonRoot=" + this.getRunAsNonRoot() + ", runAsUser=" + this.getRunAsUser() + ", seLinuxChangePolicy=" + this.getSeLinuxChangePolicy() + ", seLinuxOptions=" + this.getSeLinuxOptions() + ", seccompProfile=" + this.getSeccompProfile() + ", supplementalGroups=" + this.getSupplementalGroups() + ", supplementalGroupsPolicy=" + this.getSupplementalGroupsPolicy() + ", sysctls=" + this.getSysctls() + ", windowsOptions=" + this.getWindowsOptions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodSecurityContext)) {
         return false;
      } else {
         PodSecurityContext other = (PodSecurityContext)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$fsGroup = this.getFsGroup();
            Object other$fsGroup = other.getFsGroup();
            if (this$fsGroup == null) {
               if (other$fsGroup != null) {
                  return false;
               }
            } else if (!this$fsGroup.equals(other$fsGroup)) {
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

            Object this$runAsNonRoot = this.getRunAsNonRoot();
            Object other$runAsNonRoot = other.getRunAsNonRoot();
            if (this$runAsNonRoot == null) {
               if (other$runAsNonRoot != null) {
                  return false;
               }
            } else if (!this$runAsNonRoot.equals(other$runAsNonRoot)) {
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

            Object this$appArmorProfile = this.getAppArmorProfile();
            Object other$appArmorProfile = other.getAppArmorProfile();
            if (this$appArmorProfile == null) {
               if (other$appArmorProfile != null) {
                  return false;
               }
            } else if (!this$appArmorProfile.equals(other$appArmorProfile)) {
               return false;
            }

            Object this$fsGroupChangePolicy = this.getFsGroupChangePolicy();
            Object other$fsGroupChangePolicy = other.getFsGroupChangePolicy();
            if (this$fsGroupChangePolicy == null) {
               if (other$fsGroupChangePolicy != null) {
                  return false;
               }
            } else if (!this$fsGroupChangePolicy.equals(other$fsGroupChangePolicy)) {
               return false;
            }

            Object this$seLinuxChangePolicy = this.getSeLinuxChangePolicy();
            Object other$seLinuxChangePolicy = other.getSeLinuxChangePolicy();
            if (this$seLinuxChangePolicy == null) {
               if (other$seLinuxChangePolicy != null) {
                  return false;
               }
            } else if (!this$seLinuxChangePolicy.equals(other$seLinuxChangePolicy)) {
               return false;
            }

            Object this$seLinuxOptions = this.getSeLinuxOptions();
            Object other$seLinuxOptions = other.getSeLinuxOptions();
            if (this$seLinuxOptions == null) {
               if (other$seLinuxOptions != null) {
                  return false;
               }
            } else if (!this$seLinuxOptions.equals(other$seLinuxOptions)) {
               return false;
            }

            Object this$seccompProfile = this.getSeccompProfile();
            Object other$seccompProfile = other.getSeccompProfile();
            if (this$seccompProfile == null) {
               if (other$seccompProfile != null) {
                  return false;
               }
            } else if (!this$seccompProfile.equals(other$seccompProfile)) {
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

            Object this$supplementalGroupsPolicy = this.getSupplementalGroupsPolicy();
            Object other$supplementalGroupsPolicy = other.getSupplementalGroupsPolicy();
            if (this$supplementalGroupsPolicy == null) {
               if (other$supplementalGroupsPolicy != null) {
                  return false;
               }
            } else if (!this$supplementalGroupsPolicy.equals(other$supplementalGroupsPolicy)) {
               return false;
            }

            Object this$sysctls = this.getSysctls();
            Object other$sysctls = other.getSysctls();
            if (this$sysctls == null) {
               if (other$sysctls != null) {
                  return false;
               }
            } else if (!this$sysctls.equals(other$sysctls)) {
               return false;
            }

            Object this$windowsOptions = this.getWindowsOptions();
            Object other$windowsOptions = other.getWindowsOptions();
            if (this$windowsOptions == null) {
               if (other$windowsOptions != null) {
                  return false;
               }
            } else if (!this$windowsOptions.equals(other$windowsOptions)) {
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
      return other instanceof PodSecurityContext;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fsGroup = this.getFsGroup();
      result = result * 59 + ($fsGroup == null ? 43 : $fsGroup.hashCode());
      Object $runAsGroup = this.getRunAsGroup();
      result = result * 59 + ($runAsGroup == null ? 43 : $runAsGroup.hashCode());
      Object $runAsNonRoot = this.getRunAsNonRoot();
      result = result * 59 + ($runAsNonRoot == null ? 43 : $runAsNonRoot.hashCode());
      Object $runAsUser = this.getRunAsUser();
      result = result * 59 + ($runAsUser == null ? 43 : $runAsUser.hashCode());
      Object $appArmorProfile = this.getAppArmorProfile();
      result = result * 59 + ($appArmorProfile == null ? 43 : $appArmorProfile.hashCode());
      Object $fsGroupChangePolicy = this.getFsGroupChangePolicy();
      result = result * 59 + ($fsGroupChangePolicy == null ? 43 : $fsGroupChangePolicy.hashCode());
      Object $seLinuxChangePolicy = this.getSeLinuxChangePolicy();
      result = result * 59 + ($seLinuxChangePolicy == null ? 43 : $seLinuxChangePolicy.hashCode());
      Object $seLinuxOptions = this.getSeLinuxOptions();
      result = result * 59 + ($seLinuxOptions == null ? 43 : $seLinuxOptions.hashCode());
      Object $seccompProfile = this.getSeccompProfile();
      result = result * 59 + ($seccompProfile == null ? 43 : $seccompProfile.hashCode());
      Object $supplementalGroups = this.getSupplementalGroups();
      result = result * 59 + ($supplementalGroups == null ? 43 : $supplementalGroups.hashCode());
      Object $supplementalGroupsPolicy = this.getSupplementalGroupsPolicy();
      result = result * 59 + ($supplementalGroupsPolicy == null ? 43 : $supplementalGroupsPolicy.hashCode());
      Object $sysctls = this.getSysctls();
      result = result * 59 + ($sysctls == null ? 43 : $sysctls.hashCode());
      Object $windowsOptions = this.getWindowsOptions();
      result = result * 59 + ($windowsOptions == null ? 43 : $windowsOptions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
