package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class PodSecurityContextFluent extends BaseFluent {
   private AppArmorProfileBuilder appArmorProfile;
   private Long fsGroup;
   private String fsGroupChangePolicy;
   private Long runAsGroup;
   private Boolean runAsNonRoot;
   private Long runAsUser;
   private String seLinuxChangePolicy;
   private SELinuxOptionsBuilder seLinuxOptions;
   private SeccompProfileBuilder seccompProfile;
   private List supplementalGroups = new ArrayList();
   private String supplementalGroupsPolicy;
   private ArrayList sysctls = new ArrayList();
   private WindowsSecurityContextOptionsBuilder windowsOptions;
   private Map additionalProperties;

   public PodSecurityContextFluent() {
   }

   public PodSecurityContextFluent(PodSecurityContext instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodSecurityContext instance) {
      instance = instance != null ? instance : new PodSecurityContext();
      if (instance != null) {
         this.withAppArmorProfile(instance.getAppArmorProfile());
         this.withFsGroup(instance.getFsGroup());
         this.withFsGroupChangePolicy(instance.getFsGroupChangePolicy());
         this.withRunAsGroup(instance.getRunAsGroup());
         this.withRunAsNonRoot(instance.getRunAsNonRoot());
         this.withRunAsUser(instance.getRunAsUser());
         this.withSeLinuxChangePolicy(instance.getSeLinuxChangePolicy());
         this.withSeLinuxOptions(instance.getSeLinuxOptions());
         this.withSeccompProfile(instance.getSeccompProfile());
         this.withSupplementalGroups(instance.getSupplementalGroups());
         this.withSupplementalGroupsPolicy(instance.getSupplementalGroupsPolicy());
         this.withSysctls(instance.getSysctls());
         this.withWindowsOptions(instance.getWindowsOptions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public AppArmorProfile buildAppArmorProfile() {
      return this.appArmorProfile != null ? this.appArmorProfile.build() : null;
   }

   public PodSecurityContextFluent withAppArmorProfile(AppArmorProfile appArmorProfile) {
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

   public PodSecurityContextFluent withNewAppArmorProfile(String localhostProfile, String type) {
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

   public Long getFsGroup() {
      return this.fsGroup;
   }

   public PodSecurityContextFluent withFsGroup(Long fsGroup) {
      this.fsGroup = fsGroup;
      return this;
   }

   public boolean hasFsGroup() {
      return this.fsGroup != null;
   }

   public String getFsGroupChangePolicy() {
      return this.fsGroupChangePolicy;
   }

   public PodSecurityContextFluent withFsGroupChangePolicy(String fsGroupChangePolicy) {
      this.fsGroupChangePolicy = fsGroupChangePolicy;
      return this;
   }

   public boolean hasFsGroupChangePolicy() {
      return this.fsGroupChangePolicy != null;
   }

   public Long getRunAsGroup() {
      return this.runAsGroup;
   }

   public PodSecurityContextFluent withRunAsGroup(Long runAsGroup) {
      this.runAsGroup = runAsGroup;
      return this;
   }

   public boolean hasRunAsGroup() {
      return this.runAsGroup != null;
   }

   public Boolean getRunAsNonRoot() {
      return this.runAsNonRoot;
   }

   public PodSecurityContextFluent withRunAsNonRoot(Boolean runAsNonRoot) {
      this.runAsNonRoot = runAsNonRoot;
      return this;
   }

   public boolean hasRunAsNonRoot() {
      return this.runAsNonRoot != null;
   }

   public Long getRunAsUser() {
      return this.runAsUser;
   }

   public PodSecurityContextFluent withRunAsUser(Long runAsUser) {
      this.runAsUser = runAsUser;
      return this;
   }

   public boolean hasRunAsUser() {
      return this.runAsUser != null;
   }

   public String getSeLinuxChangePolicy() {
      return this.seLinuxChangePolicy;
   }

   public PodSecurityContextFluent withSeLinuxChangePolicy(String seLinuxChangePolicy) {
      this.seLinuxChangePolicy = seLinuxChangePolicy;
      return this;
   }

   public boolean hasSeLinuxChangePolicy() {
      return this.seLinuxChangePolicy != null;
   }

   public SELinuxOptions buildSeLinuxOptions() {
      return this.seLinuxOptions != null ? this.seLinuxOptions.build() : null;
   }

   public PodSecurityContextFluent withSeLinuxOptions(SELinuxOptions seLinuxOptions) {
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

   public PodSecurityContextFluent withNewSeLinuxOptions(String level, String role, String type, String user) {
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

   public PodSecurityContextFluent withSeccompProfile(SeccompProfile seccompProfile) {
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

   public PodSecurityContextFluent withNewSeccompProfile(String localhostProfile, String type) {
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

   public PodSecurityContextFluent addToSupplementalGroups(int index, Long item) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      this.supplementalGroups.add(index, item);
      return this;
   }

   public PodSecurityContextFluent setToSupplementalGroups(int index, Long item) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      this.supplementalGroups.set(index, item);
      return this;
   }

   public PodSecurityContextFluent addToSupplementalGroups(Long... items) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      for(Long item : items) {
         this.supplementalGroups.add(item);
      }

      return this;
   }

   public PodSecurityContextFluent addAllToSupplementalGroups(Collection items) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      for(Long item : items) {
         this.supplementalGroups.add(item);
      }

      return this;
   }

   public PodSecurityContextFluent removeFromSupplementalGroups(Long... items) {
      if (this.supplementalGroups == null) {
         return this;
      } else {
         for(Long item : items) {
            this.supplementalGroups.remove(item);
         }

         return this;
      }
   }

   public PodSecurityContextFluent removeAllFromSupplementalGroups(Collection items) {
      if (this.supplementalGroups == null) {
         return this;
      } else {
         for(Long item : items) {
            this.supplementalGroups.remove(item);
         }

         return this;
      }
   }

   public List getSupplementalGroups() {
      return this.supplementalGroups;
   }

   public Long getSupplementalGroup(int index) {
      return (Long)this.supplementalGroups.get(index);
   }

   public Long getFirstSupplementalGroup() {
      return (Long)this.supplementalGroups.get(0);
   }

   public Long getLastSupplementalGroup() {
      return (Long)this.supplementalGroups.get(this.supplementalGroups.size() - 1);
   }

   public Long getMatchingSupplementalGroup(Predicate predicate) {
      for(Long item : this.supplementalGroups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingSupplementalGroup(Predicate predicate) {
      for(Long item : this.supplementalGroups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityContextFluent withSupplementalGroups(List supplementalGroups) {
      if (supplementalGroups != null) {
         this.supplementalGroups = new ArrayList();

         for(Long item : supplementalGroups) {
            this.addToSupplementalGroups(item);
         }
      } else {
         this.supplementalGroups = null;
      }

      return this;
   }

   public PodSecurityContextFluent withSupplementalGroups(Long... supplementalGroups) {
      if (this.supplementalGroups != null) {
         this.supplementalGroups.clear();
         this._visitables.remove("supplementalGroups");
      }

      if (supplementalGroups != null) {
         for(Long item : supplementalGroups) {
            this.addToSupplementalGroups(item);
         }
      }

      return this;
   }

   public boolean hasSupplementalGroups() {
      return this.supplementalGroups != null && !this.supplementalGroups.isEmpty();
   }

   public String getSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy;
   }

   public PodSecurityContextFluent withSupplementalGroupsPolicy(String supplementalGroupsPolicy) {
      this.supplementalGroupsPolicy = supplementalGroupsPolicy;
      return this;
   }

   public boolean hasSupplementalGroupsPolicy() {
      return this.supplementalGroupsPolicy != null;
   }

   public PodSecurityContextFluent addToSysctls(int index, Sysctl item) {
      if (this.sysctls == null) {
         this.sysctls = new ArrayList();
      }

      SysctlBuilder builder = new SysctlBuilder(item);
      if (index >= 0 && index < this.sysctls.size()) {
         this._visitables.get("sysctls").add(index, builder);
         this.sysctls.add(index, builder);
      } else {
         this._visitables.get("sysctls").add(builder);
         this.sysctls.add(builder);
      }

      return this;
   }

   public PodSecurityContextFluent setToSysctls(int index, Sysctl item) {
      if (this.sysctls == null) {
         this.sysctls = new ArrayList();
      }

      SysctlBuilder builder = new SysctlBuilder(item);
      if (index >= 0 && index < this.sysctls.size()) {
         this._visitables.get("sysctls").set(index, builder);
         this.sysctls.set(index, builder);
      } else {
         this._visitables.get("sysctls").add(builder);
         this.sysctls.add(builder);
      }

      return this;
   }

   public PodSecurityContextFluent addToSysctls(Sysctl... items) {
      if (this.sysctls == null) {
         this.sysctls = new ArrayList();
      }

      for(Sysctl item : items) {
         SysctlBuilder builder = new SysctlBuilder(item);
         this._visitables.get("sysctls").add(builder);
         this.sysctls.add(builder);
      }

      return this;
   }

   public PodSecurityContextFluent addAllToSysctls(Collection items) {
      if (this.sysctls == null) {
         this.sysctls = new ArrayList();
      }

      for(Sysctl item : items) {
         SysctlBuilder builder = new SysctlBuilder(item);
         this._visitables.get("sysctls").add(builder);
         this.sysctls.add(builder);
      }

      return this;
   }

   public PodSecurityContextFluent removeFromSysctls(Sysctl... items) {
      if (this.sysctls == null) {
         return this;
      } else {
         for(Sysctl item : items) {
            SysctlBuilder builder = new SysctlBuilder(item);
            this._visitables.get("sysctls").remove(builder);
            this.sysctls.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityContextFluent removeAllFromSysctls(Collection items) {
      if (this.sysctls == null) {
         return this;
      } else {
         for(Sysctl item : items) {
            SysctlBuilder builder = new SysctlBuilder(item);
            this._visitables.get("sysctls").remove(builder);
            this.sysctls.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityContextFluent removeMatchingFromSysctls(Predicate predicate) {
      if (this.sysctls == null) {
         return this;
      } else {
         Iterator<SysctlBuilder> each = this.sysctls.iterator();
         List visitables = this._visitables.get("sysctls");

         while(each.hasNext()) {
            SysctlBuilder builder = (SysctlBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSysctls() {
      return this.sysctls != null ? build(this.sysctls) : null;
   }

   public Sysctl buildSysctl(int index) {
      return ((SysctlBuilder)this.sysctls.get(index)).build();
   }

   public Sysctl buildFirstSysctl() {
      return ((SysctlBuilder)this.sysctls.get(0)).build();
   }

   public Sysctl buildLastSysctl() {
      return ((SysctlBuilder)this.sysctls.get(this.sysctls.size() - 1)).build();
   }

   public Sysctl buildMatchingSysctl(Predicate predicate) {
      for(SysctlBuilder item : this.sysctls) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSysctl(Predicate predicate) {
      for(SysctlBuilder item : this.sysctls) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityContextFluent withSysctls(List sysctls) {
      if (this.sysctls != null) {
         this._visitables.get("sysctls").clear();
      }

      if (sysctls != null) {
         this.sysctls = new ArrayList();

         for(Sysctl item : sysctls) {
            this.addToSysctls(item);
         }
      } else {
         this.sysctls = null;
      }

      return this;
   }

   public PodSecurityContextFluent withSysctls(Sysctl... sysctls) {
      if (this.sysctls != null) {
         this.sysctls.clear();
         this._visitables.remove("sysctls");
      }

      if (sysctls != null) {
         for(Sysctl item : sysctls) {
            this.addToSysctls(item);
         }
      }

      return this;
   }

   public boolean hasSysctls() {
      return this.sysctls != null && !this.sysctls.isEmpty();
   }

   public PodSecurityContextFluent addNewSysctl(String name, String value) {
      return this.addToSysctls(new Sysctl(name, value));
   }

   public SysctlsNested addNewSysctl() {
      return new SysctlsNested(-1, (Sysctl)null);
   }

   public SysctlsNested addNewSysctlLike(Sysctl item) {
      return new SysctlsNested(-1, item);
   }

   public SysctlsNested setNewSysctlLike(int index, Sysctl item) {
      return new SysctlsNested(index, item);
   }

   public SysctlsNested editSysctl(int index) {
      if (this.sysctls.size() <= index) {
         throw new RuntimeException("Can't edit sysctls. Index exceeds size.");
      } else {
         return this.setNewSysctlLike(index, this.buildSysctl(index));
      }
   }

   public SysctlsNested editFirstSysctl() {
      if (this.sysctls.size() == 0) {
         throw new RuntimeException("Can't edit first sysctls. The list is empty.");
      } else {
         return this.setNewSysctlLike(0, this.buildSysctl(0));
      }
   }

   public SysctlsNested editLastSysctl() {
      int index = this.sysctls.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last sysctls. The list is empty.");
      } else {
         return this.setNewSysctlLike(index, this.buildSysctl(index));
      }
   }

   public SysctlsNested editMatchingSysctl(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.sysctls.size(); ++i) {
         if (predicate.test((SysctlBuilder)this.sysctls.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching sysctls. No match found.");
      } else {
         return this.setNewSysctlLike(index, this.buildSysctl(index));
      }
   }

   public WindowsSecurityContextOptions buildWindowsOptions() {
      return this.windowsOptions != null ? this.windowsOptions.build() : null;
   }

   public PodSecurityContextFluent withWindowsOptions(WindowsSecurityContextOptions windowsOptions) {
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

   public PodSecurityContextFluent withNewWindowsOptions(String gmsaCredentialSpec, String gmsaCredentialSpecName, Boolean hostProcess, String runAsUserName) {
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

   public PodSecurityContextFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodSecurityContextFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodSecurityContextFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodSecurityContextFluent removeFromAdditionalProperties(Map map) {
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

   public PodSecurityContextFluent withAdditionalProperties(Map additionalProperties) {
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
            PodSecurityContextFluent that = (PodSecurityContextFluent)o;
            if (!Objects.equals(this.appArmorProfile, that.appArmorProfile)) {
               return false;
            } else if (!Objects.equals(this.fsGroup, that.fsGroup)) {
               return false;
            } else if (!Objects.equals(this.fsGroupChangePolicy, that.fsGroupChangePolicy)) {
               return false;
            } else if (!Objects.equals(this.runAsGroup, that.runAsGroup)) {
               return false;
            } else if (!Objects.equals(this.runAsNonRoot, that.runAsNonRoot)) {
               return false;
            } else if (!Objects.equals(this.runAsUser, that.runAsUser)) {
               return false;
            } else if (!Objects.equals(this.seLinuxChangePolicy, that.seLinuxChangePolicy)) {
               return false;
            } else if (!Objects.equals(this.seLinuxOptions, that.seLinuxOptions)) {
               return false;
            } else if (!Objects.equals(this.seccompProfile, that.seccompProfile)) {
               return false;
            } else if (!Objects.equals(this.supplementalGroups, that.supplementalGroups)) {
               return false;
            } else if (!Objects.equals(this.supplementalGroupsPolicy, that.supplementalGroupsPolicy)) {
               return false;
            } else if (!Objects.equals(this.sysctls, that.sysctls)) {
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
      return Objects.hash(new Object[]{this.appArmorProfile, this.fsGroup, this.fsGroupChangePolicy, this.runAsGroup, this.runAsNonRoot, this.runAsUser, this.seLinuxChangePolicy, this.seLinuxOptions, this.seccompProfile, this.supplementalGroups, this.supplementalGroupsPolicy, this.sysctls, this.windowsOptions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.appArmorProfile != null) {
         sb.append("appArmorProfile:");
         sb.append(this.appArmorProfile + ",");
      }

      if (this.fsGroup != null) {
         sb.append("fsGroup:");
         sb.append(this.fsGroup + ",");
      }

      if (this.fsGroupChangePolicy != null) {
         sb.append("fsGroupChangePolicy:");
         sb.append(this.fsGroupChangePolicy + ",");
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

      if (this.seLinuxChangePolicy != null) {
         sb.append("seLinuxChangePolicy:");
         sb.append(this.seLinuxChangePolicy + ",");
      }

      if (this.seLinuxOptions != null) {
         sb.append("seLinuxOptions:");
         sb.append(this.seLinuxOptions + ",");
      }

      if (this.seccompProfile != null) {
         sb.append("seccompProfile:");
         sb.append(this.seccompProfile + ",");
      }

      if (this.supplementalGroups != null && !this.supplementalGroups.isEmpty()) {
         sb.append("supplementalGroups:");
         sb.append(this.supplementalGroups + ",");
      }

      if (this.supplementalGroupsPolicy != null) {
         sb.append("supplementalGroupsPolicy:");
         sb.append(this.supplementalGroupsPolicy + ",");
      }

      if (this.sysctls != null && !this.sysctls.isEmpty()) {
         sb.append("sysctls:");
         sb.append(this.sysctls + ",");
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

   public PodSecurityContextFluent withRunAsNonRoot() {
      return this.withRunAsNonRoot(true);
   }

   public class AppArmorProfileNested extends AppArmorProfileFluent implements Nested {
      AppArmorProfileBuilder builder;

      AppArmorProfileNested(AppArmorProfile item) {
         this.builder = new AppArmorProfileBuilder(this, item);
      }

      public Object and() {
         return PodSecurityContextFluent.this.withAppArmorProfile(this.builder.build());
      }

      public Object endAppArmorProfile() {
         return this.and();
      }
   }

   public class SeLinuxOptionsNested extends SELinuxOptionsFluent implements Nested {
      SELinuxOptionsBuilder builder;

      SeLinuxOptionsNested(SELinuxOptions item) {
         this.builder = new SELinuxOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityContextFluent.this.withSeLinuxOptions(this.builder.build());
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
         return PodSecurityContextFluent.this.withSeccompProfile(this.builder.build());
      }

      public Object endSeccompProfile() {
         return this.and();
      }
   }

   public class SysctlsNested extends SysctlFluent implements Nested {
      SysctlBuilder builder;
      int index;

      SysctlsNested(int index, Sysctl item) {
         this.index = index;
         this.builder = new SysctlBuilder(this, item);
      }

      public Object and() {
         return PodSecurityContextFluent.this.setToSysctls(this.index, this.builder.build());
      }

      public Object endSysctl() {
         return this.and();
      }
   }

   public class WindowsOptionsNested extends WindowsSecurityContextOptionsFluent implements Nested {
      WindowsSecurityContextOptionsBuilder builder;

      WindowsOptionsNested(WindowsSecurityContextOptions item) {
         this.builder = new WindowsSecurityContextOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityContextFluent.this.withWindowsOptions(this.builder.build());
      }

      public Object endWindowsOptions() {
         return this.and();
      }
   }
}
