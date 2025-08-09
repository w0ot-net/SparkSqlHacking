package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SecurityContextFluent extends BaseFluent {
   private Boolean allowPrivilegeEscalation;
   private AppArmorProfileBuilder appArmorProfile;
   private CapabilitiesBuilder capabilities;
   private Boolean privileged;
   private String procMount;
   private Boolean readOnlyRootFilesystem;
   private Long runAsGroup;
   private Boolean runAsNonRoot;
   private Long runAsUser;
   private SELinuxOptionsBuilder seLinuxOptions;
   private SeccompProfileBuilder seccompProfile;
   private WindowsSecurityContextOptionsBuilder windowsOptions;
   private Map additionalProperties;

   public SecurityContextFluent() {
   }

   public SecurityContextFluent(SecurityContext instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SecurityContext instance) {
      instance = instance != null ? instance : new SecurityContext();
      if (instance != null) {
         this.withAllowPrivilegeEscalation(instance.getAllowPrivilegeEscalation());
         this.withAppArmorProfile(instance.getAppArmorProfile());
         this.withCapabilities(instance.getCapabilities());
         this.withPrivileged(instance.getPrivileged());
         this.withProcMount(instance.getProcMount());
         this.withReadOnlyRootFilesystem(instance.getReadOnlyRootFilesystem());
         this.withRunAsGroup(instance.getRunAsGroup());
         this.withRunAsNonRoot(instance.getRunAsNonRoot());
         this.withRunAsUser(instance.getRunAsUser());
         this.withSeLinuxOptions(instance.getSeLinuxOptions());
         this.withSeccompProfile(instance.getSeccompProfile());
         this.withWindowsOptions(instance.getWindowsOptions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation;
   }

   public SecurityContextFluent withAllowPrivilegeEscalation(Boolean allowPrivilegeEscalation) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
      return this;
   }

   public boolean hasAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation != null;
   }

   public AppArmorProfile buildAppArmorProfile() {
      return this.appArmorProfile != null ? this.appArmorProfile.build() : null;
   }

   public SecurityContextFluent withAppArmorProfile(AppArmorProfile appArmorProfile) {
      this._visitables.remove("appArmorProfile");
      if (appArmorProfile != null) {
         this.appArmorProfile = new AppArmorProfileBuilder(appArmorProfile);
         this._visitables.get("appArmorProfile").add(this.appArmorProfile);
      } else {
         this.appArmorProfile = null;
         this._visitables.get("appArmorProfile").remove(this.appArmorProfile);
      }

      return this;
   }

   public boolean hasAppArmorProfile() {
      return this.appArmorProfile != null;
   }

   public SecurityContextFluent withNewAppArmorProfile(String localhostProfile, String type) {
      return this.withAppArmorProfile(new AppArmorProfile(localhostProfile, type));
   }

   public AppArmorProfileNested withNewAppArmorProfile() {
      return new AppArmorProfileNested((AppArmorProfile)null);
   }

   public AppArmorProfileNested withNewAppArmorProfileLike(AppArmorProfile item) {
      return new AppArmorProfileNested(item);
   }

   public AppArmorProfileNested editAppArmorProfile() {
      return this.withNewAppArmorProfileLike((AppArmorProfile)Optional.ofNullable(this.buildAppArmorProfile()).orElse((Object)null));
   }

   public AppArmorProfileNested editOrNewAppArmorProfile() {
      return this.withNewAppArmorProfileLike((AppArmorProfile)Optional.ofNullable(this.buildAppArmorProfile()).orElse((new AppArmorProfileBuilder()).build()));
   }

   public AppArmorProfileNested editOrNewAppArmorProfileLike(AppArmorProfile item) {
      return this.withNewAppArmorProfileLike((AppArmorProfile)Optional.ofNullable(this.buildAppArmorProfile()).orElse(item));
   }

   public Capabilities buildCapabilities() {
      return this.capabilities != null ? this.capabilities.build() : null;
   }

   public SecurityContextFluent withCapabilities(Capabilities capabilities) {
      this._visitables.remove("capabilities");
      if (capabilities != null) {
         this.capabilities = new CapabilitiesBuilder(capabilities);
         this._visitables.get("capabilities").add(this.capabilities);
      } else {
         this.capabilities = null;
         this._visitables.get("capabilities").remove(this.capabilities);
      }

      return this;
   }

   public boolean hasCapabilities() {
      return this.capabilities != null;
   }

   public CapabilitiesNested withNewCapabilities() {
      return new CapabilitiesNested((Capabilities)null);
   }

   public CapabilitiesNested withNewCapabilitiesLike(Capabilities item) {
      return new CapabilitiesNested(item);
   }

   public CapabilitiesNested editCapabilities() {
      return this.withNewCapabilitiesLike((Capabilities)Optional.ofNullable(this.buildCapabilities()).orElse((Object)null));
   }

   public CapabilitiesNested editOrNewCapabilities() {
      return this.withNewCapabilitiesLike((Capabilities)Optional.ofNullable(this.buildCapabilities()).orElse((new CapabilitiesBuilder()).build()));
   }

   public CapabilitiesNested editOrNewCapabilitiesLike(Capabilities item) {
      return this.withNewCapabilitiesLike((Capabilities)Optional.ofNullable(this.buildCapabilities()).orElse(item));
   }

   public Boolean getPrivileged() {
      return this.privileged;
   }

   public SecurityContextFluent withPrivileged(Boolean privileged) {
      this.privileged = privileged;
      return this;
   }

   public boolean hasPrivileged() {
      return this.privileged != null;
   }

   public String getProcMount() {
      return this.procMount;
   }

   public SecurityContextFluent withProcMount(String procMount) {
      this.procMount = procMount;
      return this;
   }

   public boolean hasProcMount() {
      return this.procMount != null;
   }

   public Boolean getReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem;
   }

   public SecurityContextFluent withReadOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
      return this;
   }

   public boolean hasReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem != null;
   }

   public Long getRunAsGroup() {
      return this.runAsGroup;
   }

   public SecurityContextFluent withRunAsGroup(Long runAsGroup) {
      this.runAsGroup = runAsGroup;
      return this;
   }

   public boolean hasRunAsGroup() {
      return this.runAsGroup != null;
   }

   public Boolean getRunAsNonRoot() {
      return this.runAsNonRoot;
   }

   public SecurityContextFluent withRunAsNonRoot(Boolean runAsNonRoot) {
      this.runAsNonRoot = runAsNonRoot;
      return this;
   }

   public boolean hasRunAsNonRoot() {
      return this.runAsNonRoot != null;
   }

   public Long getRunAsUser() {
      return this.runAsUser;
   }

   public SecurityContextFluent withRunAsUser(Long runAsUser) {
      this.runAsUser = runAsUser;
      return this;
   }

   public boolean hasRunAsUser() {
      return this.runAsUser != null;
   }

   public SELinuxOptions buildSeLinuxOptions() {
      return this.seLinuxOptions != null ? this.seLinuxOptions.build() : null;
   }

   public SecurityContextFluent withSeLinuxOptions(SELinuxOptions seLinuxOptions) {
      this._visitables.remove("seLinuxOptions");
      if (seLinuxOptions != null) {
         this.seLinuxOptions = new SELinuxOptionsBuilder(seLinuxOptions);
         this._visitables.get("seLinuxOptions").add(this.seLinuxOptions);
      } else {
         this.seLinuxOptions = null;
         this._visitables.get("seLinuxOptions").remove(this.seLinuxOptions);
      }

      return this;
   }

   public boolean hasSeLinuxOptions() {
      return this.seLinuxOptions != null;
   }

   public SecurityContextFluent withNewSeLinuxOptions(String level, String role, String type, String user) {
      return this.withSeLinuxOptions(new SELinuxOptions(level, role, type, user));
   }

   public SeLinuxOptionsNested withNewSeLinuxOptions() {
      return new SeLinuxOptionsNested((SELinuxOptions)null);
   }

   public SeLinuxOptionsNested withNewSeLinuxOptionsLike(SELinuxOptions item) {
      return new SeLinuxOptionsNested(item);
   }

   public SeLinuxOptionsNested editSeLinuxOptions() {
      return this.withNewSeLinuxOptionsLike((SELinuxOptions)Optional.ofNullable(this.buildSeLinuxOptions()).orElse((Object)null));
   }

   public SeLinuxOptionsNested editOrNewSeLinuxOptions() {
      return this.withNewSeLinuxOptionsLike((SELinuxOptions)Optional.ofNullable(this.buildSeLinuxOptions()).orElse((new SELinuxOptionsBuilder()).build()));
   }

   public SeLinuxOptionsNested editOrNewSeLinuxOptionsLike(SELinuxOptions item) {
      return this.withNewSeLinuxOptionsLike((SELinuxOptions)Optional.ofNullable(this.buildSeLinuxOptions()).orElse(item));
   }

   public SeccompProfile buildSeccompProfile() {
      return this.seccompProfile != null ? this.seccompProfile.build() : null;
   }

   public SecurityContextFluent withSeccompProfile(SeccompProfile seccompProfile) {
      this._visitables.remove("seccompProfile");
      if (seccompProfile != null) {
         this.seccompProfile = new SeccompProfileBuilder(seccompProfile);
         this._visitables.get("seccompProfile").add(this.seccompProfile);
      } else {
         this.seccompProfile = null;
         this._visitables.get("seccompProfile").remove(this.seccompProfile);
      }

      return this;
   }

   public boolean hasSeccompProfile() {
      return this.seccompProfile != null;
   }

   public SecurityContextFluent withNewSeccompProfile(String localhostProfile, String type) {
      return this.withSeccompProfile(new SeccompProfile(localhostProfile, type));
   }

   public SeccompProfileNested withNewSeccompProfile() {
      return new SeccompProfileNested((SeccompProfile)null);
   }

   public SeccompProfileNested withNewSeccompProfileLike(SeccompProfile item) {
      return new SeccompProfileNested(item);
   }

   public SeccompProfileNested editSeccompProfile() {
      return this.withNewSeccompProfileLike((SeccompProfile)Optional.ofNullable(this.buildSeccompProfile()).orElse((Object)null));
   }

   public SeccompProfileNested editOrNewSeccompProfile() {
      return this.withNewSeccompProfileLike((SeccompProfile)Optional.ofNullable(this.buildSeccompProfile()).orElse((new SeccompProfileBuilder()).build()));
   }

   public SeccompProfileNested editOrNewSeccompProfileLike(SeccompProfile item) {
      return this.withNewSeccompProfileLike((SeccompProfile)Optional.ofNullable(this.buildSeccompProfile()).orElse(item));
   }

   public WindowsSecurityContextOptions buildWindowsOptions() {
      return this.windowsOptions != null ? this.windowsOptions.build() : null;
   }

   public SecurityContextFluent withWindowsOptions(WindowsSecurityContextOptions windowsOptions) {
      this._visitables.remove("windowsOptions");
      if (windowsOptions != null) {
         this.windowsOptions = new WindowsSecurityContextOptionsBuilder(windowsOptions);
         this._visitables.get("windowsOptions").add(this.windowsOptions);
      } else {
         this.windowsOptions = null;
         this._visitables.get("windowsOptions").remove(this.windowsOptions);
      }

      return this;
   }

   public boolean hasWindowsOptions() {
      return this.windowsOptions != null;
   }

   public SecurityContextFluent withNewWindowsOptions(String gmsaCredentialSpec, String gmsaCredentialSpecName, Boolean hostProcess, String runAsUserName) {
      return this.withWindowsOptions(new WindowsSecurityContextOptions(gmsaCredentialSpec, gmsaCredentialSpecName, hostProcess, runAsUserName));
   }

   public WindowsOptionsNested withNewWindowsOptions() {
      return new WindowsOptionsNested((WindowsSecurityContextOptions)null);
   }

   public WindowsOptionsNested withNewWindowsOptionsLike(WindowsSecurityContextOptions item) {
      return new WindowsOptionsNested(item);
   }

   public WindowsOptionsNested editWindowsOptions() {
      return this.withNewWindowsOptionsLike((WindowsSecurityContextOptions)Optional.ofNullable(this.buildWindowsOptions()).orElse((Object)null));
   }

   public WindowsOptionsNested editOrNewWindowsOptions() {
      return this.withNewWindowsOptionsLike((WindowsSecurityContextOptions)Optional.ofNullable(this.buildWindowsOptions()).orElse((new WindowsSecurityContextOptionsBuilder()).build()));
   }

   public WindowsOptionsNested editOrNewWindowsOptionsLike(WindowsSecurityContextOptions item) {
      return this.withNewWindowsOptionsLike((WindowsSecurityContextOptions)Optional.ofNullable(this.buildWindowsOptions()).orElse(item));
   }

   public SecurityContextFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SecurityContextFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SecurityContextFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SecurityContextFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public SecurityContextFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            SecurityContextFluent that = (SecurityContextFluent)o;
            if (!Objects.equals(this.allowPrivilegeEscalation, that.allowPrivilegeEscalation)) {
               return false;
            } else if (!Objects.equals(this.appArmorProfile, that.appArmorProfile)) {
               return false;
            } else if (!Objects.equals(this.capabilities, that.capabilities)) {
               return false;
            } else if (!Objects.equals(this.privileged, that.privileged)) {
               return false;
            } else if (!Objects.equals(this.procMount, that.procMount)) {
               return false;
            } else if (!Objects.equals(this.readOnlyRootFilesystem, that.readOnlyRootFilesystem)) {
               return false;
            } else if (!Objects.equals(this.runAsGroup, that.runAsGroup)) {
               return false;
            } else if (!Objects.equals(this.runAsNonRoot, that.runAsNonRoot)) {
               return false;
            } else if (!Objects.equals(this.runAsUser, that.runAsUser)) {
               return false;
            } else if (!Objects.equals(this.seLinuxOptions, that.seLinuxOptions)) {
               return false;
            } else if (!Objects.equals(this.seccompProfile, that.seccompProfile)) {
               return false;
            } else if (!Objects.equals(this.windowsOptions, that.windowsOptions)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.allowPrivilegeEscalation, this.appArmorProfile, this.capabilities, this.privileged, this.procMount, this.readOnlyRootFilesystem, this.runAsGroup, this.runAsNonRoot, this.runAsUser, this.seLinuxOptions, this.seccompProfile, this.windowsOptions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowPrivilegeEscalation != null) {
         sb.append("allowPrivilegeEscalation:");
         sb.append(this.allowPrivilegeEscalation + ",");
      }

      if (this.appArmorProfile != null) {
         sb.append("appArmorProfile:");
         sb.append(this.appArmorProfile + ",");
      }

      if (this.capabilities != null) {
         sb.append("capabilities:");
         sb.append(this.capabilities + ",");
      }

      if (this.privileged != null) {
         sb.append("privileged:");
         sb.append(this.privileged + ",");
      }

      if (this.procMount != null) {
         sb.append("procMount:");
         sb.append(this.procMount + ",");
      }

      if (this.readOnlyRootFilesystem != null) {
         sb.append("readOnlyRootFilesystem:");
         sb.append(this.readOnlyRootFilesystem + ",");
      }

      if (this.runAsGroup != null) {
         sb.append("runAsGroup:");
         sb.append(this.runAsGroup + ",");
      }

      if (this.runAsNonRoot != null) {
         sb.append("runAsNonRoot:");
         sb.append(this.runAsNonRoot + ",");
      }

      if (this.runAsUser != null) {
         sb.append("runAsUser:");
         sb.append(this.runAsUser + ",");
      }

      if (this.seLinuxOptions != null) {
         sb.append("seLinuxOptions:");
         sb.append(this.seLinuxOptions + ",");
      }

      if (this.seccompProfile != null) {
         sb.append("seccompProfile:");
         sb.append(this.seccompProfile + ",");
      }

      if (this.windowsOptions != null) {
         sb.append("windowsOptions:");
         sb.append(this.windowsOptions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public SecurityContextFluent withAllowPrivilegeEscalation() {
      return this.withAllowPrivilegeEscalation(true);
   }

   public SecurityContextFluent withPrivileged() {
      return this.withPrivileged(true);
   }

   public SecurityContextFluent withReadOnlyRootFilesystem() {
      return this.withReadOnlyRootFilesystem(true);
   }

   public SecurityContextFluent withRunAsNonRoot() {
      return this.withRunAsNonRoot(true);
   }

   public class AppArmorProfileNested extends AppArmorProfileFluent implements Nested {
      AppArmorProfileBuilder builder;

      AppArmorProfileNested(AppArmorProfile item) {
         this.builder = new AppArmorProfileBuilder(this, item);
      }

      public Object and() {
         return SecurityContextFluent.this.withAppArmorProfile(this.builder.build());
      }

      public Object endAppArmorProfile() {
         return this.and();
      }
   }

   public class CapabilitiesNested extends CapabilitiesFluent implements Nested {
      CapabilitiesBuilder builder;

      CapabilitiesNested(Capabilities item) {
         this.builder = new CapabilitiesBuilder(this, item);
      }

      public Object and() {
         return SecurityContextFluent.this.withCapabilities(this.builder.build());
      }

      public Object endCapabilities() {
         return this.and();
      }
   }

   public class SeLinuxOptionsNested extends SELinuxOptionsFluent implements Nested {
      SELinuxOptionsBuilder builder;

      SeLinuxOptionsNested(SELinuxOptions item) {
         this.builder = new SELinuxOptionsBuilder(this, item);
      }

      public Object and() {
         return SecurityContextFluent.this.withSeLinuxOptions(this.builder.build());
      }

      public Object endSeLinuxOptions() {
         return this.and();
      }
   }

   public class SeccompProfileNested extends SeccompProfileFluent implements Nested {
      SeccompProfileBuilder builder;

      SeccompProfileNested(SeccompProfile item) {
         this.builder = new SeccompProfileBuilder(this, item);
      }

      public Object and() {
         return SecurityContextFluent.this.withSeccompProfile(this.builder.build());
      }

      public Object endSeccompProfile() {
         return this.and();
      }
   }

   public class WindowsOptionsNested extends WindowsSecurityContextOptionsFluent implements Nested {
      WindowsSecurityContextOptionsBuilder builder;

      WindowsOptionsNested(WindowsSecurityContextOptions item) {
         this.builder = new WindowsSecurityContextOptionsBuilder(this, item);
      }

      public Object and() {
         return SecurityContextFluent.this.withWindowsOptions(this.builder.build());
      }

      public Object endWindowsOptions() {
         return this.and();
      }
   }
}
