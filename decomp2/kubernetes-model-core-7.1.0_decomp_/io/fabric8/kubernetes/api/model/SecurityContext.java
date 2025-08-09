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
@JsonPropertyOrder({"allowPrivilegeEscalation", "appArmorProfile", "capabilities", "privileged", "procMount", "readOnlyRootFilesystem", "runAsGroup", "runAsNonRoot", "runAsUser", "seLinuxOptions", "seccompProfile", "windowsOptions"})
public class SecurityContext implements Editable, KubernetesResource {
   @JsonProperty("allowPrivilegeEscalation")
   private Boolean allowPrivilegeEscalation;
   @JsonProperty("appArmorProfile")
   private AppArmorProfile appArmorProfile;
   @JsonProperty("capabilities")
   private Capabilities capabilities;
   @JsonProperty("privileged")
   private Boolean privileged;
   @JsonProperty("procMount")
   private String procMount;
   @JsonProperty("readOnlyRootFilesystem")
   private Boolean readOnlyRootFilesystem;
   @JsonProperty("runAsGroup")
   private Long runAsGroup;
   @JsonProperty("runAsNonRoot")
   private Boolean runAsNonRoot;
   @JsonProperty("runAsUser")
   private Long runAsUser;
   @JsonProperty("seLinuxOptions")
   private SELinuxOptions seLinuxOptions;
   @JsonProperty("seccompProfile")
   private SeccompProfile seccompProfile;
   @JsonProperty("windowsOptions")
   private WindowsSecurityContextOptions windowsOptions;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SecurityContext() {
   }

   public SecurityContext(Boolean allowPrivilegeEscalation, AppArmorProfile appArmorProfile, Capabilities capabilities, Boolean privileged, String procMount, Boolean readOnlyRootFilesystem, Long runAsGroup, Boolean runAsNonRoot, Long runAsUser, SELinuxOptions seLinuxOptions, SeccompProfile seccompProfile, WindowsSecurityContextOptions windowsOptions) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
      this.appArmorProfile = appArmorProfile;
      this.capabilities = capabilities;
      this.privileged = privileged;
      this.procMount = procMount;
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
      this.runAsGroup = runAsGroup;
      this.runAsNonRoot = runAsNonRoot;
      this.runAsUser = runAsUser;
      this.seLinuxOptions = seLinuxOptions;
      this.seccompProfile = seccompProfile;
      this.windowsOptions = windowsOptions;
   }

   @JsonProperty("allowPrivilegeEscalation")
   public Boolean getAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation;
   }

   @JsonProperty("allowPrivilegeEscalation")
   public void setAllowPrivilegeEscalation(Boolean allowPrivilegeEscalation) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
   }

   @JsonProperty("appArmorProfile")
   public AppArmorProfile getAppArmorProfile() {
      return this.appArmorProfile;
   }

   @JsonProperty("appArmorProfile")
   public void setAppArmorProfile(AppArmorProfile appArmorProfile) {
      this.appArmorProfile = appArmorProfile;
   }

   @JsonProperty("capabilities")
   public Capabilities getCapabilities() {
      return this.capabilities;
   }

   @JsonProperty("capabilities")
   public void setCapabilities(Capabilities capabilities) {
      this.capabilities = capabilities;
   }

   @JsonProperty("privileged")
   public Boolean getPrivileged() {
      return this.privileged;
   }

   @JsonProperty("privileged")
   public void setPrivileged(Boolean privileged) {
      this.privileged = privileged;
   }

   @JsonProperty("procMount")
   public String getProcMount() {
      return this.procMount;
   }

   @JsonProperty("procMount")
   public void setProcMount(String procMount) {
      this.procMount = procMount;
   }

   @JsonProperty("readOnlyRootFilesystem")
   public Boolean getReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem;
   }

   @JsonProperty("readOnlyRootFilesystem")
   public void setReadOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
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

   @JsonProperty("windowsOptions")
   public WindowsSecurityContextOptions getWindowsOptions() {
      return this.windowsOptions;
   }

   @JsonProperty("windowsOptions")
   public void setWindowsOptions(WindowsSecurityContextOptions windowsOptions) {
      this.windowsOptions = windowsOptions;
   }

   @JsonIgnore
   public SecurityContextBuilder edit() {
      return new SecurityContextBuilder(this);
   }

   @JsonIgnore
   public SecurityContextBuilder toBuilder() {
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
      Boolean var10000 = this.getAllowPrivilegeEscalation();
      return "SecurityContext(allowPrivilegeEscalation=" + var10000 + ", appArmorProfile=" + this.getAppArmorProfile() + ", capabilities=" + this.getCapabilities() + ", privileged=" + this.getPrivileged() + ", procMount=" + this.getProcMount() + ", readOnlyRootFilesystem=" + this.getReadOnlyRootFilesystem() + ", runAsGroup=" + this.getRunAsGroup() + ", runAsNonRoot=" + this.getRunAsNonRoot() + ", runAsUser=" + this.getRunAsUser() + ", seLinuxOptions=" + this.getSeLinuxOptions() + ", seccompProfile=" + this.getSeccompProfile() + ", windowsOptions=" + this.getWindowsOptions() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SecurityContext)) {
         return false;
      } else {
         SecurityContext other = (SecurityContext)o;
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

            Object this$capabilities = this.getCapabilities();
            Object other$capabilities = other.getCapabilities();
            if (this$capabilities == null) {
               if (other$capabilities != null) {
                  return false;
               }
            } else if (!this$capabilities.equals(other$capabilities)) {
               return false;
            }

            Object this$procMount = this.getProcMount();
            Object other$procMount = other.getProcMount();
            if (this$procMount == null) {
               if (other$procMount != null) {
                  return false;
               }
            } else if (!this$procMount.equals(other$procMount)) {
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
      return other instanceof SecurityContext;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $allowPrivilegeEscalation = this.getAllowPrivilegeEscalation();
      result = result * 59 + ($allowPrivilegeEscalation == null ? 43 : $allowPrivilegeEscalation.hashCode());
      Object $privileged = this.getPrivileged();
      result = result * 59 + ($privileged == null ? 43 : $privileged.hashCode());
      Object $readOnlyRootFilesystem = this.getReadOnlyRootFilesystem();
      result = result * 59 + ($readOnlyRootFilesystem == null ? 43 : $readOnlyRootFilesystem.hashCode());
      Object $runAsGroup = this.getRunAsGroup();
      result = result * 59 + ($runAsGroup == null ? 43 : $runAsGroup.hashCode());
      Object $runAsNonRoot = this.getRunAsNonRoot();
      result = result * 59 + ($runAsNonRoot == null ? 43 : $runAsNonRoot.hashCode());
      Object $runAsUser = this.getRunAsUser();
      result = result * 59 + ($runAsUser == null ? 43 : $runAsUser.hashCode());
      Object $appArmorProfile = this.getAppArmorProfile();
      result = result * 59 + ($appArmorProfile == null ? 43 : $appArmorProfile.hashCode());
      Object $capabilities = this.getCapabilities();
      result = result * 59 + ($capabilities == null ? 43 : $capabilities.hashCode());
      Object $procMount = this.getProcMount();
      result = result * 59 + ($procMount == null ? 43 : $procMount.hashCode());
      Object $seLinuxOptions = this.getSeLinuxOptions();
      result = result * 59 + ($seLinuxOptions == null ? 43 : $seLinuxOptions.hashCode());
      Object $seccompProfile = this.getSeccompProfile();
      result = result * 59 + ($seccompProfile == null ? 43 : $seccompProfile.hashCode());
      Object $windowsOptions = this.getWindowsOptions();
      result = result * 59 + ($windowsOptions == null ? 43 : $windowsOptions.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
