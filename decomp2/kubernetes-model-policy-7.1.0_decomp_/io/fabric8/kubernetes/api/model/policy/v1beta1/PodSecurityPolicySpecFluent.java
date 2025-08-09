package io.fabric8.kubernetes.api.model.policy.v1beta1;

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

public class PodSecurityPolicySpecFluent extends BaseFluent {
   private Boolean allowPrivilegeEscalation;
   private ArrayList allowedCSIDrivers = new ArrayList();
   private List allowedCapabilities = new ArrayList();
   private ArrayList allowedFlexVolumes = new ArrayList();
   private ArrayList allowedHostPaths = new ArrayList();
   private List allowedProcMountTypes = new ArrayList();
   private List allowedUnsafeSysctls = new ArrayList();
   private List defaultAddCapabilities = new ArrayList();
   private Boolean defaultAllowPrivilegeEscalation;
   private List forbiddenSysctls = new ArrayList();
   private FSGroupStrategyOptionsBuilder fsGroup;
   private Boolean hostIPC;
   private Boolean hostNetwork;
   private Boolean hostPID;
   private ArrayList hostPorts = new ArrayList();
   private Boolean privileged;
   private Boolean readOnlyRootFilesystem;
   private List requiredDropCapabilities = new ArrayList();
   private RunAsGroupStrategyOptionsBuilder runAsGroup;
   private RunAsUserStrategyOptionsBuilder runAsUser;
   private RuntimeClassStrategyOptionsBuilder runtimeClass;
   private SELinuxStrategyOptionsBuilder seLinux;
   private SupplementalGroupsStrategyOptionsBuilder supplementalGroups;
   private List volumes = new ArrayList();
   private Map additionalProperties;

   public PodSecurityPolicySpecFluent() {
   }

   public PodSecurityPolicySpecFluent(PodSecurityPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodSecurityPolicySpec instance) {
      instance = instance != null ? instance : new PodSecurityPolicySpec();
      if (instance != null) {
         this.withAllowPrivilegeEscalation(instance.getAllowPrivilegeEscalation());
         this.withAllowedCSIDrivers(instance.getAllowedCSIDrivers());
         this.withAllowedCapabilities(instance.getAllowedCapabilities());
         this.withAllowedFlexVolumes(instance.getAllowedFlexVolumes());
         this.withAllowedHostPaths(instance.getAllowedHostPaths());
         this.withAllowedProcMountTypes(instance.getAllowedProcMountTypes());
         this.withAllowedUnsafeSysctls(instance.getAllowedUnsafeSysctls());
         this.withDefaultAddCapabilities(instance.getDefaultAddCapabilities());
         this.withDefaultAllowPrivilegeEscalation(instance.getDefaultAllowPrivilegeEscalation());
         this.withForbiddenSysctls(instance.getForbiddenSysctls());
         this.withFsGroup(instance.getFsGroup());
         this.withHostIPC(instance.getHostIPC());
         this.withHostNetwork(instance.getHostNetwork());
         this.withHostPID(instance.getHostPID());
         this.withHostPorts(instance.getHostPorts());
         this.withPrivileged(instance.getPrivileged());
         this.withReadOnlyRootFilesystem(instance.getReadOnlyRootFilesystem());
         this.withRequiredDropCapabilities(instance.getRequiredDropCapabilities());
         this.withRunAsGroup(instance.getRunAsGroup());
         this.withRunAsUser(instance.getRunAsUser());
         this.withRuntimeClass(instance.getRuntimeClass());
         this.withSeLinux(instance.getSeLinux());
         this.withSupplementalGroups(instance.getSupplementalGroups());
         this.withVolumes(instance.getVolumes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation;
   }

   public PodSecurityPolicySpecFluent withAllowPrivilegeEscalation(Boolean allowPrivilegeEscalation) {
      this.allowPrivilegeEscalation = allowPrivilegeEscalation;
      return this;
   }

   public boolean hasAllowPrivilegeEscalation() {
      return this.allowPrivilegeEscalation != null;
   }

   public PodSecurityPolicySpecFluent addToAllowedCSIDrivers(int index, AllowedCSIDriver item) {
      if (this.allowedCSIDrivers == null) {
         this.allowedCSIDrivers = new ArrayList();
      }

      AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
      if (index >= 0 && index < this.allowedCSIDrivers.size()) {
         this._visitables.get("allowedCSIDrivers").add(index, builder);
         this.allowedCSIDrivers.add(index, builder);
      } else {
         this._visitables.get("allowedCSIDrivers").add(builder);
         this.allowedCSIDrivers.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedCSIDrivers(int index, AllowedCSIDriver item) {
      if (this.allowedCSIDrivers == null) {
         this.allowedCSIDrivers = new ArrayList();
      }

      AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
      if (index >= 0 && index < this.allowedCSIDrivers.size()) {
         this._visitables.get("allowedCSIDrivers").set(index, builder);
         this.allowedCSIDrivers.set(index, builder);
      } else {
         this._visitables.get("allowedCSIDrivers").add(builder);
         this.allowedCSIDrivers.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedCSIDrivers(AllowedCSIDriver... items) {
      if (this.allowedCSIDrivers == null) {
         this.allowedCSIDrivers = new ArrayList();
      }

      for(AllowedCSIDriver item : items) {
         AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
         this._visitables.get("allowedCSIDrivers").add(builder);
         this.allowedCSIDrivers.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedCSIDrivers(Collection items) {
      if (this.allowedCSIDrivers == null) {
         this.allowedCSIDrivers = new ArrayList();
      }

      for(AllowedCSIDriver item : items) {
         AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
         this._visitables.get("allowedCSIDrivers").add(builder);
         this.allowedCSIDrivers.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedCSIDrivers(AllowedCSIDriver... items) {
      if (this.allowedCSIDrivers == null) {
         return this;
      } else {
         for(AllowedCSIDriver item : items) {
            AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
            this._visitables.get("allowedCSIDrivers").remove(builder);
            this.allowedCSIDrivers.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedCSIDrivers(Collection items) {
      if (this.allowedCSIDrivers == null) {
         return this;
      } else {
         for(AllowedCSIDriver item : items) {
            AllowedCSIDriverBuilder builder = new AllowedCSIDriverBuilder(item);
            this._visitables.get("allowedCSIDrivers").remove(builder);
            this.allowedCSIDrivers.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeMatchingFromAllowedCSIDrivers(Predicate predicate) {
      if (this.allowedCSIDrivers == null) {
         return this;
      } else {
         Iterator<AllowedCSIDriverBuilder> each = this.allowedCSIDrivers.iterator();
         List visitables = this._visitables.get("allowedCSIDrivers");

         while(each.hasNext()) {
            AllowedCSIDriverBuilder builder = (AllowedCSIDriverBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAllowedCSIDrivers() {
      return this.allowedCSIDrivers != null ? build(this.allowedCSIDrivers) : null;
   }

   public AllowedCSIDriver buildAllowedCSIDriver(int index) {
      return ((AllowedCSIDriverBuilder)this.allowedCSIDrivers.get(index)).build();
   }

   public AllowedCSIDriver buildFirstAllowedCSIDriver() {
      return ((AllowedCSIDriverBuilder)this.allowedCSIDrivers.get(0)).build();
   }

   public AllowedCSIDriver buildLastAllowedCSIDriver() {
      return ((AllowedCSIDriverBuilder)this.allowedCSIDrivers.get(this.allowedCSIDrivers.size() - 1)).build();
   }

   public AllowedCSIDriver buildMatchingAllowedCSIDriver(Predicate predicate) {
      for(AllowedCSIDriverBuilder item : this.allowedCSIDrivers) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedCSIDriver(Predicate predicate) {
      for(AllowedCSIDriverBuilder item : this.allowedCSIDrivers) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedCSIDrivers(List allowedCSIDrivers) {
      if (this.allowedCSIDrivers != null) {
         this._visitables.get("allowedCSIDrivers").clear();
      }

      if (allowedCSIDrivers != null) {
         this.allowedCSIDrivers = new ArrayList();

         for(AllowedCSIDriver item : allowedCSIDrivers) {
            this.addToAllowedCSIDrivers(item);
         }
      } else {
         this.allowedCSIDrivers = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedCSIDrivers(AllowedCSIDriver... allowedCSIDrivers) {
      if (this.allowedCSIDrivers != null) {
         this.allowedCSIDrivers.clear();
         this._visitables.remove("allowedCSIDrivers");
      }

      if (allowedCSIDrivers != null) {
         for(AllowedCSIDriver item : allowedCSIDrivers) {
            this.addToAllowedCSIDrivers(item);
         }
      }

      return this;
   }

   public boolean hasAllowedCSIDrivers() {
      return this.allowedCSIDrivers != null && !this.allowedCSIDrivers.isEmpty();
   }

   public PodSecurityPolicySpecFluent addNewAllowedCSIDriver(String name) {
      return this.addToAllowedCSIDrivers(new AllowedCSIDriver(name));
   }

   public AllowedCSIDriversNested addNewAllowedCSIDriver() {
      return new AllowedCSIDriversNested(-1, (AllowedCSIDriver)null);
   }

   public AllowedCSIDriversNested addNewAllowedCSIDriverLike(AllowedCSIDriver item) {
      return new AllowedCSIDriversNested(-1, item);
   }

   public AllowedCSIDriversNested setNewAllowedCSIDriverLike(int index, AllowedCSIDriver item) {
      return new AllowedCSIDriversNested(index, item);
   }

   public AllowedCSIDriversNested editAllowedCSIDriver(int index) {
      if (this.allowedCSIDrivers.size() <= index) {
         throw new RuntimeException("Can't edit allowedCSIDrivers. Index exceeds size.");
      } else {
         return this.setNewAllowedCSIDriverLike(index, this.buildAllowedCSIDriver(index));
      }
   }

   public AllowedCSIDriversNested editFirstAllowedCSIDriver() {
      if (this.allowedCSIDrivers.size() == 0) {
         throw new RuntimeException("Can't edit first allowedCSIDrivers. The list is empty.");
      } else {
         return this.setNewAllowedCSIDriverLike(0, this.buildAllowedCSIDriver(0));
      }
   }

   public AllowedCSIDriversNested editLastAllowedCSIDriver() {
      int index = this.allowedCSIDrivers.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last allowedCSIDrivers. The list is empty.");
      } else {
         return this.setNewAllowedCSIDriverLike(index, this.buildAllowedCSIDriver(index));
      }
   }

   public AllowedCSIDriversNested editMatchingAllowedCSIDriver(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.allowedCSIDrivers.size(); ++i) {
         if (predicate.test((AllowedCSIDriverBuilder)this.allowedCSIDrivers.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching allowedCSIDrivers. No match found.");
      } else {
         return this.setNewAllowedCSIDriverLike(index, this.buildAllowedCSIDriver(index));
      }
   }

   public PodSecurityPolicySpecFluent addToAllowedCapabilities(int index, String item) {
      if (this.allowedCapabilities == null) {
         this.allowedCapabilities = new ArrayList();
      }

      this.allowedCapabilities.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedCapabilities(int index, String item) {
      if (this.allowedCapabilities == null) {
         this.allowedCapabilities = new ArrayList();
      }

      this.allowedCapabilities.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedCapabilities(String... items) {
      if (this.allowedCapabilities == null) {
         this.allowedCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.allowedCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedCapabilities(Collection items) {
      if (this.allowedCapabilities == null) {
         this.allowedCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.allowedCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedCapabilities(String... items) {
      if (this.allowedCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedCapabilities.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedCapabilities(Collection items) {
      if (this.allowedCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedCapabilities.remove(item);
         }

         return this;
      }
   }

   public List getAllowedCapabilities() {
      return this.allowedCapabilities;
   }

   public String getAllowedCapability(int index) {
      return (String)this.allowedCapabilities.get(index);
   }

   public String getFirstAllowedCapability() {
      return (String)this.allowedCapabilities.get(0);
   }

   public String getLastAllowedCapability() {
      return (String)this.allowedCapabilities.get(this.allowedCapabilities.size() - 1);
   }

   public String getMatchingAllowedCapability(Predicate predicate) {
      for(String item : this.allowedCapabilities) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedCapability(Predicate predicate) {
      for(String item : this.allowedCapabilities) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedCapabilities(List allowedCapabilities) {
      if (allowedCapabilities != null) {
         this.allowedCapabilities = new ArrayList();

         for(String item : allowedCapabilities) {
            this.addToAllowedCapabilities(item);
         }
      } else {
         this.allowedCapabilities = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedCapabilities(String... allowedCapabilities) {
      if (this.allowedCapabilities != null) {
         this.allowedCapabilities.clear();
         this._visitables.remove("allowedCapabilities");
      }

      if (allowedCapabilities != null) {
         for(String item : allowedCapabilities) {
            this.addToAllowedCapabilities(item);
         }
      }

      return this;
   }

   public boolean hasAllowedCapabilities() {
      return this.allowedCapabilities != null && !this.allowedCapabilities.isEmpty();
   }

   public PodSecurityPolicySpecFluent addToAllowedFlexVolumes(int index, AllowedFlexVolume item) {
      if (this.allowedFlexVolumes == null) {
         this.allowedFlexVolumes = new ArrayList();
      }

      AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
      if (index >= 0 && index < this.allowedFlexVolumes.size()) {
         this._visitables.get("allowedFlexVolumes").add(index, builder);
         this.allowedFlexVolumes.add(index, builder);
      } else {
         this._visitables.get("allowedFlexVolumes").add(builder);
         this.allowedFlexVolumes.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedFlexVolumes(int index, AllowedFlexVolume item) {
      if (this.allowedFlexVolumes == null) {
         this.allowedFlexVolumes = new ArrayList();
      }

      AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
      if (index >= 0 && index < this.allowedFlexVolumes.size()) {
         this._visitables.get("allowedFlexVolumes").set(index, builder);
         this.allowedFlexVolumes.set(index, builder);
      } else {
         this._visitables.get("allowedFlexVolumes").add(builder);
         this.allowedFlexVolumes.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedFlexVolumes(AllowedFlexVolume... items) {
      if (this.allowedFlexVolumes == null) {
         this.allowedFlexVolumes = new ArrayList();
      }

      for(AllowedFlexVolume item : items) {
         AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
         this._visitables.get("allowedFlexVolumes").add(builder);
         this.allowedFlexVolumes.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedFlexVolumes(Collection items) {
      if (this.allowedFlexVolumes == null) {
         this.allowedFlexVolumes = new ArrayList();
      }

      for(AllowedFlexVolume item : items) {
         AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
         this._visitables.get("allowedFlexVolumes").add(builder);
         this.allowedFlexVolumes.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedFlexVolumes(AllowedFlexVolume... items) {
      if (this.allowedFlexVolumes == null) {
         return this;
      } else {
         for(AllowedFlexVolume item : items) {
            AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
            this._visitables.get("allowedFlexVolumes").remove(builder);
            this.allowedFlexVolumes.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedFlexVolumes(Collection items) {
      if (this.allowedFlexVolumes == null) {
         return this;
      } else {
         for(AllowedFlexVolume item : items) {
            AllowedFlexVolumeBuilder builder = new AllowedFlexVolumeBuilder(item);
            this._visitables.get("allowedFlexVolumes").remove(builder);
            this.allowedFlexVolumes.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeMatchingFromAllowedFlexVolumes(Predicate predicate) {
      if (this.allowedFlexVolumes == null) {
         return this;
      } else {
         Iterator<AllowedFlexVolumeBuilder> each = this.allowedFlexVolumes.iterator();
         List visitables = this._visitables.get("allowedFlexVolumes");

         while(each.hasNext()) {
            AllowedFlexVolumeBuilder builder = (AllowedFlexVolumeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAllowedFlexVolumes() {
      return this.allowedFlexVolumes != null ? build(this.allowedFlexVolumes) : null;
   }

   public AllowedFlexVolume buildAllowedFlexVolume(int index) {
      return ((AllowedFlexVolumeBuilder)this.allowedFlexVolumes.get(index)).build();
   }

   public AllowedFlexVolume buildFirstAllowedFlexVolume() {
      return ((AllowedFlexVolumeBuilder)this.allowedFlexVolumes.get(0)).build();
   }

   public AllowedFlexVolume buildLastAllowedFlexVolume() {
      return ((AllowedFlexVolumeBuilder)this.allowedFlexVolumes.get(this.allowedFlexVolumes.size() - 1)).build();
   }

   public AllowedFlexVolume buildMatchingAllowedFlexVolume(Predicate predicate) {
      for(AllowedFlexVolumeBuilder item : this.allowedFlexVolumes) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedFlexVolume(Predicate predicate) {
      for(AllowedFlexVolumeBuilder item : this.allowedFlexVolumes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedFlexVolumes(List allowedFlexVolumes) {
      if (this.allowedFlexVolumes != null) {
         this._visitables.get("allowedFlexVolumes").clear();
      }

      if (allowedFlexVolumes != null) {
         this.allowedFlexVolumes = new ArrayList();

         for(AllowedFlexVolume item : allowedFlexVolumes) {
            this.addToAllowedFlexVolumes(item);
         }
      } else {
         this.allowedFlexVolumes = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedFlexVolumes(AllowedFlexVolume... allowedFlexVolumes) {
      if (this.allowedFlexVolumes != null) {
         this.allowedFlexVolumes.clear();
         this._visitables.remove("allowedFlexVolumes");
      }

      if (allowedFlexVolumes != null) {
         for(AllowedFlexVolume item : allowedFlexVolumes) {
            this.addToAllowedFlexVolumes(item);
         }
      }

      return this;
   }

   public boolean hasAllowedFlexVolumes() {
      return this.allowedFlexVolumes != null && !this.allowedFlexVolumes.isEmpty();
   }

   public PodSecurityPolicySpecFluent addNewAllowedFlexVolume(String driver) {
      return this.addToAllowedFlexVolumes(new AllowedFlexVolume(driver));
   }

   public AllowedFlexVolumesNested addNewAllowedFlexVolume() {
      return new AllowedFlexVolumesNested(-1, (AllowedFlexVolume)null);
   }

   public AllowedFlexVolumesNested addNewAllowedFlexVolumeLike(AllowedFlexVolume item) {
      return new AllowedFlexVolumesNested(-1, item);
   }

   public AllowedFlexVolumesNested setNewAllowedFlexVolumeLike(int index, AllowedFlexVolume item) {
      return new AllowedFlexVolumesNested(index, item);
   }

   public AllowedFlexVolumesNested editAllowedFlexVolume(int index) {
      if (this.allowedFlexVolumes.size() <= index) {
         throw new RuntimeException("Can't edit allowedFlexVolumes. Index exceeds size.");
      } else {
         return this.setNewAllowedFlexVolumeLike(index, this.buildAllowedFlexVolume(index));
      }
   }

   public AllowedFlexVolumesNested editFirstAllowedFlexVolume() {
      if (this.allowedFlexVolumes.size() == 0) {
         throw new RuntimeException("Can't edit first allowedFlexVolumes. The list is empty.");
      } else {
         return this.setNewAllowedFlexVolumeLike(0, this.buildAllowedFlexVolume(0));
      }
   }

   public AllowedFlexVolumesNested editLastAllowedFlexVolume() {
      int index = this.allowedFlexVolumes.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last allowedFlexVolumes. The list is empty.");
      } else {
         return this.setNewAllowedFlexVolumeLike(index, this.buildAllowedFlexVolume(index));
      }
   }

   public AllowedFlexVolumesNested editMatchingAllowedFlexVolume(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.allowedFlexVolumes.size(); ++i) {
         if (predicate.test((AllowedFlexVolumeBuilder)this.allowedFlexVolumes.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching allowedFlexVolumes. No match found.");
      } else {
         return this.setNewAllowedFlexVolumeLike(index, this.buildAllowedFlexVolume(index));
      }
   }

   public PodSecurityPolicySpecFluent addToAllowedHostPaths(int index, AllowedHostPath item) {
      if (this.allowedHostPaths == null) {
         this.allowedHostPaths = new ArrayList();
      }

      AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
      if (index >= 0 && index < this.allowedHostPaths.size()) {
         this._visitables.get("allowedHostPaths").add(index, builder);
         this.allowedHostPaths.add(index, builder);
      } else {
         this._visitables.get("allowedHostPaths").add(builder);
         this.allowedHostPaths.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedHostPaths(int index, AllowedHostPath item) {
      if (this.allowedHostPaths == null) {
         this.allowedHostPaths = new ArrayList();
      }

      AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
      if (index >= 0 && index < this.allowedHostPaths.size()) {
         this._visitables.get("allowedHostPaths").set(index, builder);
         this.allowedHostPaths.set(index, builder);
      } else {
         this._visitables.get("allowedHostPaths").add(builder);
         this.allowedHostPaths.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedHostPaths(AllowedHostPath... items) {
      if (this.allowedHostPaths == null) {
         this.allowedHostPaths = new ArrayList();
      }

      for(AllowedHostPath item : items) {
         AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
         this._visitables.get("allowedHostPaths").add(builder);
         this.allowedHostPaths.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedHostPaths(Collection items) {
      if (this.allowedHostPaths == null) {
         this.allowedHostPaths = new ArrayList();
      }

      for(AllowedHostPath item : items) {
         AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
         this._visitables.get("allowedHostPaths").add(builder);
         this.allowedHostPaths.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedHostPaths(AllowedHostPath... items) {
      if (this.allowedHostPaths == null) {
         return this;
      } else {
         for(AllowedHostPath item : items) {
            AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
            this._visitables.get("allowedHostPaths").remove(builder);
            this.allowedHostPaths.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedHostPaths(Collection items) {
      if (this.allowedHostPaths == null) {
         return this;
      } else {
         for(AllowedHostPath item : items) {
            AllowedHostPathBuilder builder = new AllowedHostPathBuilder(item);
            this._visitables.get("allowedHostPaths").remove(builder);
            this.allowedHostPaths.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeMatchingFromAllowedHostPaths(Predicate predicate) {
      if (this.allowedHostPaths == null) {
         return this;
      } else {
         Iterator<AllowedHostPathBuilder> each = this.allowedHostPaths.iterator();
         List visitables = this._visitables.get("allowedHostPaths");

         while(each.hasNext()) {
            AllowedHostPathBuilder builder = (AllowedHostPathBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAllowedHostPaths() {
      return this.allowedHostPaths != null ? build(this.allowedHostPaths) : null;
   }

   public AllowedHostPath buildAllowedHostPath(int index) {
      return ((AllowedHostPathBuilder)this.allowedHostPaths.get(index)).build();
   }

   public AllowedHostPath buildFirstAllowedHostPath() {
      return ((AllowedHostPathBuilder)this.allowedHostPaths.get(0)).build();
   }

   public AllowedHostPath buildLastAllowedHostPath() {
      return ((AllowedHostPathBuilder)this.allowedHostPaths.get(this.allowedHostPaths.size() - 1)).build();
   }

   public AllowedHostPath buildMatchingAllowedHostPath(Predicate predicate) {
      for(AllowedHostPathBuilder item : this.allowedHostPaths) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedHostPath(Predicate predicate) {
      for(AllowedHostPathBuilder item : this.allowedHostPaths) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedHostPaths(List allowedHostPaths) {
      if (this.allowedHostPaths != null) {
         this._visitables.get("allowedHostPaths").clear();
      }

      if (allowedHostPaths != null) {
         this.allowedHostPaths = new ArrayList();

         for(AllowedHostPath item : allowedHostPaths) {
            this.addToAllowedHostPaths(item);
         }
      } else {
         this.allowedHostPaths = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedHostPaths(AllowedHostPath... allowedHostPaths) {
      if (this.allowedHostPaths != null) {
         this.allowedHostPaths.clear();
         this._visitables.remove("allowedHostPaths");
      }

      if (allowedHostPaths != null) {
         for(AllowedHostPath item : allowedHostPaths) {
            this.addToAllowedHostPaths(item);
         }
      }

      return this;
   }

   public boolean hasAllowedHostPaths() {
      return this.allowedHostPaths != null && !this.allowedHostPaths.isEmpty();
   }

   public PodSecurityPolicySpecFluent addNewAllowedHostPath(String pathPrefix, Boolean readOnly) {
      return this.addToAllowedHostPaths(new AllowedHostPath(pathPrefix, readOnly));
   }

   public AllowedHostPathsNested addNewAllowedHostPath() {
      return new AllowedHostPathsNested(-1, (AllowedHostPath)null);
   }

   public AllowedHostPathsNested addNewAllowedHostPathLike(AllowedHostPath item) {
      return new AllowedHostPathsNested(-1, item);
   }

   public AllowedHostPathsNested setNewAllowedHostPathLike(int index, AllowedHostPath item) {
      return new AllowedHostPathsNested(index, item);
   }

   public AllowedHostPathsNested editAllowedHostPath(int index) {
      if (this.allowedHostPaths.size() <= index) {
         throw new RuntimeException("Can't edit allowedHostPaths. Index exceeds size.");
      } else {
         return this.setNewAllowedHostPathLike(index, this.buildAllowedHostPath(index));
      }
   }

   public AllowedHostPathsNested editFirstAllowedHostPath() {
      if (this.allowedHostPaths.size() == 0) {
         throw new RuntimeException("Can't edit first allowedHostPaths. The list is empty.");
      } else {
         return this.setNewAllowedHostPathLike(0, this.buildAllowedHostPath(0));
      }
   }

   public AllowedHostPathsNested editLastAllowedHostPath() {
      int index = this.allowedHostPaths.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last allowedHostPaths. The list is empty.");
      } else {
         return this.setNewAllowedHostPathLike(index, this.buildAllowedHostPath(index));
      }
   }

   public AllowedHostPathsNested editMatchingAllowedHostPath(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.allowedHostPaths.size(); ++i) {
         if (predicate.test((AllowedHostPathBuilder)this.allowedHostPaths.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching allowedHostPaths. No match found.");
      } else {
         return this.setNewAllowedHostPathLike(index, this.buildAllowedHostPath(index));
      }
   }

   public PodSecurityPolicySpecFluent addToAllowedProcMountTypes(int index, String item) {
      if (this.allowedProcMountTypes == null) {
         this.allowedProcMountTypes = new ArrayList();
      }

      this.allowedProcMountTypes.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedProcMountTypes(int index, String item) {
      if (this.allowedProcMountTypes == null) {
         this.allowedProcMountTypes = new ArrayList();
      }

      this.allowedProcMountTypes.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedProcMountTypes(String... items) {
      if (this.allowedProcMountTypes == null) {
         this.allowedProcMountTypes = new ArrayList();
      }

      for(String item : items) {
         this.allowedProcMountTypes.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedProcMountTypes(Collection items) {
      if (this.allowedProcMountTypes == null) {
         this.allowedProcMountTypes = new ArrayList();
      }

      for(String item : items) {
         this.allowedProcMountTypes.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedProcMountTypes(String... items) {
      if (this.allowedProcMountTypes == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedProcMountTypes.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedProcMountTypes(Collection items) {
      if (this.allowedProcMountTypes == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedProcMountTypes.remove(item);
         }

         return this;
      }
   }

   public List getAllowedProcMountTypes() {
      return this.allowedProcMountTypes;
   }

   public String getAllowedProcMountType(int index) {
      return (String)this.allowedProcMountTypes.get(index);
   }

   public String getFirstAllowedProcMountType() {
      return (String)this.allowedProcMountTypes.get(0);
   }

   public String getLastAllowedProcMountType() {
      return (String)this.allowedProcMountTypes.get(this.allowedProcMountTypes.size() - 1);
   }

   public String getMatchingAllowedProcMountType(Predicate predicate) {
      for(String item : this.allowedProcMountTypes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedProcMountType(Predicate predicate) {
      for(String item : this.allowedProcMountTypes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedProcMountTypes(List allowedProcMountTypes) {
      if (allowedProcMountTypes != null) {
         this.allowedProcMountTypes = new ArrayList();

         for(String item : allowedProcMountTypes) {
            this.addToAllowedProcMountTypes(item);
         }
      } else {
         this.allowedProcMountTypes = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedProcMountTypes(String... allowedProcMountTypes) {
      if (this.allowedProcMountTypes != null) {
         this.allowedProcMountTypes.clear();
         this._visitables.remove("allowedProcMountTypes");
      }

      if (allowedProcMountTypes != null) {
         for(String item : allowedProcMountTypes) {
            this.addToAllowedProcMountTypes(item);
         }
      }

      return this;
   }

   public boolean hasAllowedProcMountTypes() {
      return this.allowedProcMountTypes != null && !this.allowedProcMountTypes.isEmpty();
   }

   public PodSecurityPolicySpecFluent addToAllowedUnsafeSysctls(int index, String item) {
      if (this.allowedUnsafeSysctls == null) {
         this.allowedUnsafeSysctls = new ArrayList();
      }

      this.allowedUnsafeSysctls.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToAllowedUnsafeSysctls(int index, String item) {
      if (this.allowedUnsafeSysctls == null) {
         this.allowedUnsafeSysctls = new ArrayList();
      }

      this.allowedUnsafeSysctls.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToAllowedUnsafeSysctls(String... items) {
      if (this.allowedUnsafeSysctls == null) {
         this.allowedUnsafeSysctls = new ArrayList();
      }

      for(String item : items) {
         this.allowedUnsafeSysctls.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToAllowedUnsafeSysctls(Collection items) {
      if (this.allowedUnsafeSysctls == null) {
         this.allowedUnsafeSysctls = new ArrayList();
      }

      for(String item : items) {
         this.allowedUnsafeSysctls.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAllowedUnsafeSysctls(String... items) {
      if (this.allowedUnsafeSysctls == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedUnsafeSysctls.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromAllowedUnsafeSysctls(Collection items) {
      if (this.allowedUnsafeSysctls == null) {
         return this;
      } else {
         for(String item : items) {
            this.allowedUnsafeSysctls.remove(item);
         }

         return this;
      }
   }

   public List getAllowedUnsafeSysctls() {
      return this.allowedUnsafeSysctls;
   }

   public String getAllowedUnsafeSysctl(int index) {
      return (String)this.allowedUnsafeSysctls.get(index);
   }

   public String getFirstAllowedUnsafeSysctl() {
      return (String)this.allowedUnsafeSysctls.get(0);
   }

   public String getLastAllowedUnsafeSysctl() {
      return (String)this.allowedUnsafeSysctls.get(this.allowedUnsafeSysctls.size() - 1);
   }

   public String getMatchingAllowedUnsafeSysctl(Predicate predicate) {
      for(String item : this.allowedUnsafeSysctls) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAllowedUnsafeSysctl(Predicate predicate) {
      for(String item : this.allowedUnsafeSysctls) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withAllowedUnsafeSysctls(List allowedUnsafeSysctls) {
      if (allowedUnsafeSysctls != null) {
         this.allowedUnsafeSysctls = new ArrayList();

         for(String item : allowedUnsafeSysctls) {
            this.addToAllowedUnsafeSysctls(item);
         }
      } else {
         this.allowedUnsafeSysctls = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withAllowedUnsafeSysctls(String... allowedUnsafeSysctls) {
      if (this.allowedUnsafeSysctls != null) {
         this.allowedUnsafeSysctls.clear();
         this._visitables.remove("allowedUnsafeSysctls");
      }

      if (allowedUnsafeSysctls != null) {
         for(String item : allowedUnsafeSysctls) {
            this.addToAllowedUnsafeSysctls(item);
         }
      }

      return this;
   }

   public boolean hasAllowedUnsafeSysctls() {
      return this.allowedUnsafeSysctls != null && !this.allowedUnsafeSysctls.isEmpty();
   }

   public PodSecurityPolicySpecFluent addToDefaultAddCapabilities(int index, String item) {
      if (this.defaultAddCapabilities == null) {
         this.defaultAddCapabilities = new ArrayList();
      }

      this.defaultAddCapabilities.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToDefaultAddCapabilities(int index, String item) {
      if (this.defaultAddCapabilities == null) {
         this.defaultAddCapabilities = new ArrayList();
      }

      this.defaultAddCapabilities.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToDefaultAddCapabilities(String... items) {
      if (this.defaultAddCapabilities == null) {
         this.defaultAddCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.defaultAddCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToDefaultAddCapabilities(Collection items) {
      if (this.defaultAddCapabilities == null) {
         this.defaultAddCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.defaultAddCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromDefaultAddCapabilities(String... items) {
      if (this.defaultAddCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.defaultAddCapabilities.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromDefaultAddCapabilities(Collection items) {
      if (this.defaultAddCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.defaultAddCapabilities.remove(item);
         }

         return this;
      }
   }

   public List getDefaultAddCapabilities() {
      return this.defaultAddCapabilities;
   }

   public String getDefaultAddCapability(int index) {
      return (String)this.defaultAddCapabilities.get(index);
   }

   public String getFirstDefaultAddCapability() {
      return (String)this.defaultAddCapabilities.get(0);
   }

   public String getLastDefaultAddCapability() {
      return (String)this.defaultAddCapabilities.get(this.defaultAddCapabilities.size() - 1);
   }

   public String getMatchingDefaultAddCapability(Predicate predicate) {
      for(String item : this.defaultAddCapabilities) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingDefaultAddCapability(Predicate predicate) {
      for(String item : this.defaultAddCapabilities) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withDefaultAddCapabilities(List defaultAddCapabilities) {
      if (defaultAddCapabilities != null) {
         this.defaultAddCapabilities = new ArrayList();

         for(String item : defaultAddCapabilities) {
            this.addToDefaultAddCapabilities(item);
         }
      } else {
         this.defaultAddCapabilities = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withDefaultAddCapabilities(String... defaultAddCapabilities) {
      if (this.defaultAddCapabilities != null) {
         this.defaultAddCapabilities.clear();
         this._visitables.remove("defaultAddCapabilities");
      }

      if (defaultAddCapabilities != null) {
         for(String item : defaultAddCapabilities) {
            this.addToDefaultAddCapabilities(item);
         }
      }

      return this;
   }

   public boolean hasDefaultAddCapabilities() {
      return this.defaultAddCapabilities != null && !this.defaultAddCapabilities.isEmpty();
   }

   public Boolean getDefaultAllowPrivilegeEscalation() {
      return this.defaultAllowPrivilegeEscalation;
   }

   public PodSecurityPolicySpecFluent withDefaultAllowPrivilegeEscalation(Boolean defaultAllowPrivilegeEscalation) {
      this.defaultAllowPrivilegeEscalation = defaultAllowPrivilegeEscalation;
      return this;
   }

   public boolean hasDefaultAllowPrivilegeEscalation() {
      return this.defaultAllowPrivilegeEscalation != null;
   }

   public PodSecurityPolicySpecFluent addToForbiddenSysctls(int index, String item) {
      if (this.forbiddenSysctls == null) {
         this.forbiddenSysctls = new ArrayList();
      }

      this.forbiddenSysctls.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToForbiddenSysctls(int index, String item) {
      if (this.forbiddenSysctls == null) {
         this.forbiddenSysctls = new ArrayList();
      }

      this.forbiddenSysctls.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToForbiddenSysctls(String... items) {
      if (this.forbiddenSysctls == null) {
         this.forbiddenSysctls = new ArrayList();
      }

      for(String item : items) {
         this.forbiddenSysctls.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToForbiddenSysctls(Collection items) {
      if (this.forbiddenSysctls == null) {
         this.forbiddenSysctls = new ArrayList();
      }

      for(String item : items) {
         this.forbiddenSysctls.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromForbiddenSysctls(String... items) {
      if (this.forbiddenSysctls == null) {
         return this;
      } else {
         for(String item : items) {
            this.forbiddenSysctls.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromForbiddenSysctls(Collection items) {
      if (this.forbiddenSysctls == null) {
         return this;
      } else {
         for(String item : items) {
            this.forbiddenSysctls.remove(item);
         }

         return this;
      }
   }

   public List getForbiddenSysctls() {
      return this.forbiddenSysctls;
   }

   public String getForbiddenSysctl(int index) {
      return (String)this.forbiddenSysctls.get(index);
   }

   public String getFirstForbiddenSysctl() {
      return (String)this.forbiddenSysctls.get(0);
   }

   public String getLastForbiddenSysctl() {
      return (String)this.forbiddenSysctls.get(this.forbiddenSysctls.size() - 1);
   }

   public String getMatchingForbiddenSysctl(Predicate predicate) {
      for(String item : this.forbiddenSysctls) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingForbiddenSysctl(Predicate predicate) {
      for(String item : this.forbiddenSysctls) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withForbiddenSysctls(List forbiddenSysctls) {
      if (forbiddenSysctls != null) {
         this.forbiddenSysctls = new ArrayList();

         for(String item : forbiddenSysctls) {
            this.addToForbiddenSysctls(item);
         }
      } else {
         this.forbiddenSysctls = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withForbiddenSysctls(String... forbiddenSysctls) {
      if (this.forbiddenSysctls != null) {
         this.forbiddenSysctls.clear();
         this._visitables.remove("forbiddenSysctls");
      }

      if (forbiddenSysctls != null) {
         for(String item : forbiddenSysctls) {
            this.addToForbiddenSysctls(item);
         }
      }

      return this;
   }

   public boolean hasForbiddenSysctls() {
      return this.forbiddenSysctls != null && !this.forbiddenSysctls.isEmpty();
   }

   public FSGroupStrategyOptions buildFsGroup() {
      return this.fsGroup != null ? this.fsGroup.build() : null;
   }

   public PodSecurityPolicySpecFluent withFsGroup(FSGroupStrategyOptions fsGroup) {
      this._visitables.remove("fsGroup");
      if (fsGroup != null) {
         this.fsGroup = new FSGroupStrategyOptionsBuilder(fsGroup);
         this._visitables.get("fsGroup").add(this.fsGroup);
      } else {
         this.fsGroup = null;
         this._visitables.get("fsGroup").remove(this.fsGroup);
      }

      return this;
   }

   public boolean hasFsGroup() {
      return this.fsGroup != null;
   }

   public FsGroupNested withNewFsGroup() {
      return new FsGroupNested((FSGroupStrategyOptions)null);
   }

   public FsGroupNested withNewFsGroupLike(FSGroupStrategyOptions item) {
      return new FsGroupNested(item);
   }

   public FsGroupNested editFsGroup() {
      return this.withNewFsGroupLike((FSGroupStrategyOptions)Optional.ofNullable(this.buildFsGroup()).orElse((Object)null));
   }

   public FsGroupNested editOrNewFsGroup() {
      return this.withNewFsGroupLike((FSGroupStrategyOptions)Optional.ofNullable(this.buildFsGroup()).orElse((new FSGroupStrategyOptionsBuilder()).build()));
   }

   public FsGroupNested editOrNewFsGroupLike(FSGroupStrategyOptions item) {
      return this.withNewFsGroupLike((FSGroupStrategyOptions)Optional.ofNullable(this.buildFsGroup()).orElse(item));
   }

   public Boolean getHostIPC() {
      return this.hostIPC;
   }

   public PodSecurityPolicySpecFluent withHostIPC(Boolean hostIPC) {
      this.hostIPC = hostIPC;
      return this;
   }

   public boolean hasHostIPC() {
      return this.hostIPC != null;
   }

   public Boolean getHostNetwork() {
      return this.hostNetwork;
   }

   public PodSecurityPolicySpecFluent withHostNetwork(Boolean hostNetwork) {
      this.hostNetwork = hostNetwork;
      return this;
   }

   public boolean hasHostNetwork() {
      return this.hostNetwork != null;
   }

   public Boolean getHostPID() {
      return this.hostPID;
   }

   public PodSecurityPolicySpecFluent withHostPID(Boolean hostPID) {
      this.hostPID = hostPID;
      return this;
   }

   public boolean hasHostPID() {
      return this.hostPID != null;
   }

   public PodSecurityPolicySpecFluent addToHostPorts(int index, HostPortRange item) {
      if (this.hostPorts == null) {
         this.hostPorts = new ArrayList();
      }

      HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
      if (index >= 0 && index < this.hostPorts.size()) {
         this._visitables.get("hostPorts").add(index, builder);
         this.hostPorts.add(index, builder);
      } else {
         this._visitables.get("hostPorts").add(builder);
         this.hostPorts.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent setToHostPorts(int index, HostPortRange item) {
      if (this.hostPorts == null) {
         this.hostPorts = new ArrayList();
      }

      HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
      if (index >= 0 && index < this.hostPorts.size()) {
         this._visitables.get("hostPorts").set(index, builder);
         this.hostPorts.set(index, builder);
      } else {
         this._visitables.get("hostPorts").add(builder);
         this.hostPorts.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addToHostPorts(HostPortRange... items) {
      if (this.hostPorts == null) {
         this.hostPorts = new ArrayList();
      }

      for(HostPortRange item : items) {
         HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
         this._visitables.get("hostPorts").add(builder);
         this.hostPorts.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToHostPorts(Collection items) {
      if (this.hostPorts == null) {
         this.hostPorts = new ArrayList();
      }

      for(HostPortRange item : items) {
         HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
         this._visitables.get("hostPorts").add(builder);
         this.hostPorts.add(builder);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromHostPorts(HostPortRange... items) {
      if (this.hostPorts == null) {
         return this;
      } else {
         for(HostPortRange item : items) {
            HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
            this._visitables.get("hostPorts").remove(builder);
            this.hostPorts.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromHostPorts(Collection items) {
      if (this.hostPorts == null) {
         return this;
      } else {
         for(HostPortRange item : items) {
            HostPortRangeBuilder builder = new HostPortRangeBuilder(item);
            this._visitables.get("hostPorts").remove(builder);
            this.hostPorts.remove(builder);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeMatchingFromHostPorts(Predicate predicate) {
      if (this.hostPorts == null) {
         return this;
      } else {
         Iterator<HostPortRangeBuilder> each = this.hostPorts.iterator();
         List visitables = this._visitables.get("hostPorts");

         while(each.hasNext()) {
            HostPortRangeBuilder builder = (HostPortRangeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildHostPorts() {
      return this.hostPorts != null ? build(this.hostPorts) : null;
   }

   public HostPortRange buildHostPort(int index) {
      return ((HostPortRangeBuilder)this.hostPorts.get(index)).build();
   }

   public HostPortRange buildFirstHostPort() {
      return ((HostPortRangeBuilder)this.hostPorts.get(0)).build();
   }

   public HostPortRange buildLastHostPort() {
      return ((HostPortRangeBuilder)this.hostPorts.get(this.hostPorts.size() - 1)).build();
   }

   public HostPortRange buildMatchingHostPort(Predicate predicate) {
      for(HostPortRangeBuilder item : this.hostPorts) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHostPort(Predicate predicate) {
      for(HostPortRangeBuilder item : this.hostPorts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withHostPorts(List hostPorts) {
      if (this.hostPorts != null) {
         this._visitables.get("hostPorts").clear();
      }

      if (hostPorts != null) {
         this.hostPorts = new ArrayList();

         for(HostPortRange item : hostPorts) {
            this.addToHostPorts(item);
         }
      } else {
         this.hostPorts = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withHostPorts(HostPortRange... hostPorts) {
      if (this.hostPorts != null) {
         this.hostPorts.clear();
         this._visitables.remove("hostPorts");
      }

      if (hostPorts != null) {
         for(HostPortRange item : hostPorts) {
            this.addToHostPorts(item);
         }
      }

      return this;
   }

   public boolean hasHostPorts() {
      return this.hostPorts != null && !this.hostPorts.isEmpty();
   }

   public PodSecurityPolicySpecFluent addNewHostPort(Integer max, Integer min) {
      return this.addToHostPorts(new HostPortRange(max, min));
   }

   public HostPortsNested addNewHostPort() {
      return new HostPortsNested(-1, (HostPortRange)null);
   }

   public HostPortsNested addNewHostPortLike(HostPortRange item) {
      return new HostPortsNested(-1, item);
   }

   public HostPortsNested setNewHostPortLike(int index, HostPortRange item) {
      return new HostPortsNested(index, item);
   }

   public HostPortsNested editHostPort(int index) {
      if (this.hostPorts.size() <= index) {
         throw new RuntimeException("Can't edit hostPorts. Index exceeds size.");
      } else {
         return this.setNewHostPortLike(index, this.buildHostPort(index));
      }
   }

   public HostPortsNested editFirstHostPort() {
      if (this.hostPorts.size() == 0) {
         throw new RuntimeException("Can't edit first hostPorts. The list is empty.");
      } else {
         return this.setNewHostPortLike(0, this.buildHostPort(0));
      }
   }

   public HostPortsNested editLastHostPort() {
      int index = this.hostPorts.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last hostPorts. The list is empty.");
      } else {
         return this.setNewHostPortLike(index, this.buildHostPort(index));
      }
   }

   public HostPortsNested editMatchingHostPort(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.hostPorts.size(); ++i) {
         if (predicate.test((HostPortRangeBuilder)this.hostPorts.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching hostPorts. No match found.");
      } else {
         return this.setNewHostPortLike(index, this.buildHostPort(index));
      }
   }

   public Boolean getPrivileged() {
      return this.privileged;
   }

   public PodSecurityPolicySpecFluent withPrivileged(Boolean privileged) {
      this.privileged = privileged;
      return this;
   }

   public boolean hasPrivileged() {
      return this.privileged != null;
   }

   public Boolean getReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem;
   }

   public PodSecurityPolicySpecFluent withReadOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
      this.readOnlyRootFilesystem = readOnlyRootFilesystem;
      return this;
   }

   public boolean hasReadOnlyRootFilesystem() {
      return this.readOnlyRootFilesystem != null;
   }

   public PodSecurityPolicySpecFluent addToRequiredDropCapabilities(int index, String item) {
      if (this.requiredDropCapabilities == null) {
         this.requiredDropCapabilities = new ArrayList();
      }

      this.requiredDropCapabilities.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToRequiredDropCapabilities(int index, String item) {
      if (this.requiredDropCapabilities == null) {
         this.requiredDropCapabilities = new ArrayList();
      }

      this.requiredDropCapabilities.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToRequiredDropCapabilities(String... items) {
      if (this.requiredDropCapabilities == null) {
         this.requiredDropCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.requiredDropCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToRequiredDropCapabilities(Collection items) {
      if (this.requiredDropCapabilities == null) {
         this.requiredDropCapabilities = new ArrayList();
      }

      for(String item : items) {
         this.requiredDropCapabilities.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromRequiredDropCapabilities(String... items) {
      if (this.requiredDropCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.requiredDropCapabilities.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromRequiredDropCapabilities(Collection items) {
      if (this.requiredDropCapabilities == null) {
         return this;
      } else {
         for(String item : items) {
            this.requiredDropCapabilities.remove(item);
         }

         return this;
      }
   }

   public List getRequiredDropCapabilities() {
      return this.requiredDropCapabilities;
   }

   public String getRequiredDropCapability(int index) {
      return (String)this.requiredDropCapabilities.get(index);
   }

   public String getFirstRequiredDropCapability() {
      return (String)this.requiredDropCapabilities.get(0);
   }

   public String getLastRequiredDropCapability() {
      return (String)this.requiredDropCapabilities.get(this.requiredDropCapabilities.size() - 1);
   }

   public String getMatchingRequiredDropCapability(Predicate predicate) {
      for(String item : this.requiredDropCapabilities) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingRequiredDropCapability(Predicate predicate) {
      for(String item : this.requiredDropCapabilities) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withRequiredDropCapabilities(List requiredDropCapabilities) {
      if (requiredDropCapabilities != null) {
         this.requiredDropCapabilities = new ArrayList();

         for(String item : requiredDropCapabilities) {
            this.addToRequiredDropCapabilities(item);
         }
      } else {
         this.requiredDropCapabilities = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withRequiredDropCapabilities(String... requiredDropCapabilities) {
      if (this.requiredDropCapabilities != null) {
         this.requiredDropCapabilities.clear();
         this._visitables.remove("requiredDropCapabilities");
      }

      if (requiredDropCapabilities != null) {
         for(String item : requiredDropCapabilities) {
            this.addToRequiredDropCapabilities(item);
         }
      }

      return this;
   }

   public boolean hasRequiredDropCapabilities() {
      return this.requiredDropCapabilities != null && !this.requiredDropCapabilities.isEmpty();
   }

   public RunAsGroupStrategyOptions buildRunAsGroup() {
      return this.runAsGroup != null ? this.runAsGroup.build() : null;
   }

   public PodSecurityPolicySpecFluent withRunAsGroup(RunAsGroupStrategyOptions runAsGroup) {
      this._visitables.remove("runAsGroup");
      if (runAsGroup != null) {
         this.runAsGroup = new RunAsGroupStrategyOptionsBuilder(runAsGroup);
         this._visitables.get("runAsGroup").add(this.runAsGroup);
      } else {
         this.runAsGroup = null;
         this._visitables.get("runAsGroup").remove(this.runAsGroup);
      }

      return this;
   }

   public boolean hasRunAsGroup() {
      return this.runAsGroup != null;
   }

   public RunAsGroupNested withNewRunAsGroup() {
      return new RunAsGroupNested((RunAsGroupStrategyOptions)null);
   }

   public RunAsGroupNested withNewRunAsGroupLike(RunAsGroupStrategyOptions item) {
      return new RunAsGroupNested(item);
   }

   public RunAsGroupNested editRunAsGroup() {
      return this.withNewRunAsGroupLike((RunAsGroupStrategyOptions)Optional.ofNullable(this.buildRunAsGroup()).orElse((Object)null));
   }

   public RunAsGroupNested editOrNewRunAsGroup() {
      return this.withNewRunAsGroupLike((RunAsGroupStrategyOptions)Optional.ofNullable(this.buildRunAsGroup()).orElse((new RunAsGroupStrategyOptionsBuilder()).build()));
   }

   public RunAsGroupNested editOrNewRunAsGroupLike(RunAsGroupStrategyOptions item) {
      return this.withNewRunAsGroupLike((RunAsGroupStrategyOptions)Optional.ofNullable(this.buildRunAsGroup()).orElse(item));
   }

   public RunAsUserStrategyOptions buildRunAsUser() {
      return this.runAsUser != null ? this.runAsUser.build() : null;
   }

   public PodSecurityPolicySpecFluent withRunAsUser(RunAsUserStrategyOptions runAsUser) {
      this._visitables.remove("runAsUser");
      if (runAsUser != null) {
         this.runAsUser = new RunAsUserStrategyOptionsBuilder(runAsUser);
         this._visitables.get("runAsUser").add(this.runAsUser);
      } else {
         this.runAsUser = null;
         this._visitables.get("runAsUser").remove(this.runAsUser);
      }

      return this;
   }

   public boolean hasRunAsUser() {
      return this.runAsUser != null;
   }

   public RunAsUserNested withNewRunAsUser() {
      return new RunAsUserNested((RunAsUserStrategyOptions)null);
   }

   public RunAsUserNested withNewRunAsUserLike(RunAsUserStrategyOptions item) {
      return new RunAsUserNested(item);
   }

   public RunAsUserNested editRunAsUser() {
      return this.withNewRunAsUserLike((RunAsUserStrategyOptions)Optional.ofNullable(this.buildRunAsUser()).orElse((Object)null));
   }

   public RunAsUserNested editOrNewRunAsUser() {
      return this.withNewRunAsUserLike((RunAsUserStrategyOptions)Optional.ofNullable(this.buildRunAsUser()).orElse((new RunAsUserStrategyOptionsBuilder()).build()));
   }

   public RunAsUserNested editOrNewRunAsUserLike(RunAsUserStrategyOptions item) {
      return this.withNewRunAsUserLike((RunAsUserStrategyOptions)Optional.ofNullable(this.buildRunAsUser()).orElse(item));
   }

   public RuntimeClassStrategyOptions buildRuntimeClass() {
      return this.runtimeClass != null ? this.runtimeClass.build() : null;
   }

   public PodSecurityPolicySpecFluent withRuntimeClass(RuntimeClassStrategyOptions runtimeClass) {
      this._visitables.remove("runtimeClass");
      if (runtimeClass != null) {
         this.runtimeClass = new RuntimeClassStrategyOptionsBuilder(runtimeClass);
         this._visitables.get("runtimeClass").add(this.runtimeClass);
      } else {
         this.runtimeClass = null;
         this._visitables.get("runtimeClass").remove(this.runtimeClass);
      }

      return this;
   }

   public boolean hasRuntimeClass() {
      return this.runtimeClass != null;
   }

   public RuntimeClassNested withNewRuntimeClass() {
      return new RuntimeClassNested((RuntimeClassStrategyOptions)null);
   }

   public RuntimeClassNested withNewRuntimeClassLike(RuntimeClassStrategyOptions item) {
      return new RuntimeClassNested(item);
   }

   public RuntimeClassNested editRuntimeClass() {
      return this.withNewRuntimeClassLike((RuntimeClassStrategyOptions)Optional.ofNullable(this.buildRuntimeClass()).orElse((Object)null));
   }

   public RuntimeClassNested editOrNewRuntimeClass() {
      return this.withNewRuntimeClassLike((RuntimeClassStrategyOptions)Optional.ofNullable(this.buildRuntimeClass()).orElse((new RuntimeClassStrategyOptionsBuilder()).build()));
   }

   public RuntimeClassNested editOrNewRuntimeClassLike(RuntimeClassStrategyOptions item) {
      return this.withNewRuntimeClassLike((RuntimeClassStrategyOptions)Optional.ofNullable(this.buildRuntimeClass()).orElse(item));
   }

   public SELinuxStrategyOptions buildSeLinux() {
      return this.seLinux != null ? this.seLinux.build() : null;
   }

   public PodSecurityPolicySpecFluent withSeLinux(SELinuxStrategyOptions seLinux) {
      this._visitables.remove("seLinux");
      if (seLinux != null) {
         this.seLinux = new SELinuxStrategyOptionsBuilder(seLinux);
         this._visitables.get("seLinux").add(this.seLinux);
      } else {
         this.seLinux = null;
         this._visitables.get("seLinux").remove(this.seLinux);
      }

      return this;
   }

   public boolean hasSeLinux() {
      return this.seLinux != null;
   }

   public SeLinuxNested withNewSeLinux() {
      return new SeLinuxNested((SELinuxStrategyOptions)null);
   }

   public SeLinuxNested withNewSeLinuxLike(SELinuxStrategyOptions item) {
      return new SeLinuxNested(item);
   }

   public SeLinuxNested editSeLinux() {
      return this.withNewSeLinuxLike((SELinuxStrategyOptions)Optional.ofNullable(this.buildSeLinux()).orElse((Object)null));
   }

   public SeLinuxNested editOrNewSeLinux() {
      return this.withNewSeLinuxLike((SELinuxStrategyOptions)Optional.ofNullable(this.buildSeLinux()).orElse((new SELinuxStrategyOptionsBuilder()).build()));
   }

   public SeLinuxNested editOrNewSeLinuxLike(SELinuxStrategyOptions item) {
      return this.withNewSeLinuxLike((SELinuxStrategyOptions)Optional.ofNullable(this.buildSeLinux()).orElse(item));
   }

   public SupplementalGroupsStrategyOptions buildSupplementalGroups() {
      return this.supplementalGroups != null ? this.supplementalGroups.build() : null;
   }

   public PodSecurityPolicySpecFluent withSupplementalGroups(SupplementalGroupsStrategyOptions supplementalGroups) {
      this._visitables.remove("supplementalGroups");
      if (supplementalGroups != null) {
         this.supplementalGroups = new SupplementalGroupsStrategyOptionsBuilder(supplementalGroups);
         this._visitables.get("supplementalGroups").add(this.supplementalGroups);
      } else {
         this.supplementalGroups = null;
         this._visitables.get("supplementalGroups").remove(this.supplementalGroups);
      }

      return this;
   }

   public boolean hasSupplementalGroups() {
      return this.supplementalGroups != null;
   }

   public SupplementalGroupsNested withNewSupplementalGroups() {
      return new SupplementalGroupsNested((SupplementalGroupsStrategyOptions)null);
   }

   public SupplementalGroupsNested withNewSupplementalGroupsLike(SupplementalGroupsStrategyOptions item) {
      return new SupplementalGroupsNested(item);
   }

   public SupplementalGroupsNested editSupplementalGroups() {
      return this.withNewSupplementalGroupsLike((SupplementalGroupsStrategyOptions)Optional.ofNullable(this.buildSupplementalGroups()).orElse((Object)null));
   }

   public SupplementalGroupsNested editOrNewSupplementalGroups() {
      return this.withNewSupplementalGroupsLike((SupplementalGroupsStrategyOptions)Optional.ofNullable(this.buildSupplementalGroups()).orElse((new SupplementalGroupsStrategyOptionsBuilder()).build()));
   }

   public SupplementalGroupsNested editOrNewSupplementalGroupsLike(SupplementalGroupsStrategyOptions item) {
      return this.withNewSupplementalGroupsLike((SupplementalGroupsStrategyOptions)Optional.ofNullable(this.buildSupplementalGroups()).orElse(item));
   }

   public PodSecurityPolicySpecFluent addToVolumes(int index, String item) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      this.volumes.add(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent setToVolumes(int index, String item) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      this.volumes.set(index, item);
      return this;
   }

   public PodSecurityPolicySpecFluent addToVolumes(String... items) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      for(String item : items) {
         this.volumes.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addAllToVolumes(Collection items) {
      if (this.volumes == null) {
         this.volumes = new ArrayList();
      }

      for(String item : items) {
         this.volumes.add(item);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromVolumes(String... items) {
      if (this.volumes == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumes.remove(item);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeAllFromVolumes(Collection items) {
      if (this.volumes == null) {
         return this;
      } else {
         for(String item : items) {
            this.volumes.remove(item);
         }

         return this;
      }
   }

   public List getVolumes() {
      return this.volumes;
   }

   public String getVolume(int index) {
      return (String)this.volumes.get(index);
   }

   public String getFirstVolume() {
      return (String)this.volumes.get(0);
   }

   public String getLastVolume() {
      return (String)this.volumes.get(this.volumes.size() - 1);
   }

   public String getMatchingVolume(Predicate predicate) {
      for(String item : this.volumes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingVolume(Predicate predicate) {
      for(String item : this.volumes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodSecurityPolicySpecFluent withVolumes(List volumes) {
      if (volumes != null) {
         this.volumes = new ArrayList();

         for(String item : volumes) {
            this.addToVolumes(item);
         }
      } else {
         this.volumes = null;
      }

      return this;
   }

   public PodSecurityPolicySpecFluent withVolumes(String... volumes) {
      if (this.volumes != null) {
         this.volumes.clear();
         this._visitables.remove("volumes");
      }

      if (volumes != null) {
         for(String item : volumes) {
            this.addToVolumes(item);
         }
      }

      return this;
   }

   public boolean hasVolumes() {
      return this.volumes != null && !this.volumes.isEmpty();
   }

   public PodSecurityPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodSecurityPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodSecurityPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public PodSecurityPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            PodSecurityPolicySpecFluent that = (PodSecurityPolicySpecFluent)o;
            if (!Objects.equals(this.allowPrivilegeEscalation, that.allowPrivilegeEscalation)) {
               return false;
            } else if (!Objects.equals(this.allowedCSIDrivers, that.allowedCSIDrivers)) {
               return false;
            } else if (!Objects.equals(this.allowedCapabilities, that.allowedCapabilities)) {
               return false;
            } else if (!Objects.equals(this.allowedFlexVolumes, that.allowedFlexVolumes)) {
               return false;
            } else if (!Objects.equals(this.allowedHostPaths, that.allowedHostPaths)) {
               return false;
            } else if (!Objects.equals(this.allowedProcMountTypes, that.allowedProcMountTypes)) {
               return false;
            } else if (!Objects.equals(this.allowedUnsafeSysctls, that.allowedUnsafeSysctls)) {
               return false;
            } else if (!Objects.equals(this.defaultAddCapabilities, that.defaultAddCapabilities)) {
               return false;
            } else if (!Objects.equals(this.defaultAllowPrivilegeEscalation, that.defaultAllowPrivilegeEscalation)) {
               return false;
            } else if (!Objects.equals(this.forbiddenSysctls, that.forbiddenSysctls)) {
               return false;
            } else if (!Objects.equals(this.fsGroup, that.fsGroup)) {
               return false;
            } else if (!Objects.equals(this.hostIPC, that.hostIPC)) {
               return false;
            } else if (!Objects.equals(this.hostNetwork, that.hostNetwork)) {
               return false;
            } else if (!Objects.equals(this.hostPID, that.hostPID)) {
               return false;
            } else if (!Objects.equals(this.hostPorts, that.hostPorts)) {
               return false;
            } else if (!Objects.equals(this.privileged, that.privileged)) {
               return false;
            } else if (!Objects.equals(this.readOnlyRootFilesystem, that.readOnlyRootFilesystem)) {
               return false;
            } else if (!Objects.equals(this.requiredDropCapabilities, that.requiredDropCapabilities)) {
               return false;
            } else if (!Objects.equals(this.runAsGroup, that.runAsGroup)) {
               return false;
            } else if (!Objects.equals(this.runAsUser, that.runAsUser)) {
               return false;
            } else if (!Objects.equals(this.runtimeClass, that.runtimeClass)) {
               return false;
            } else if (!Objects.equals(this.seLinux, that.seLinux)) {
               return false;
            } else if (!Objects.equals(this.supplementalGroups, that.supplementalGroups)) {
               return false;
            } else if (!Objects.equals(this.volumes, that.volumes)) {
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
      return Objects.hash(new Object[]{this.allowPrivilegeEscalation, this.allowedCSIDrivers, this.allowedCapabilities, this.allowedFlexVolumes, this.allowedHostPaths, this.allowedProcMountTypes, this.allowedUnsafeSysctls, this.defaultAddCapabilities, this.defaultAllowPrivilegeEscalation, this.forbiddenSysctls, this.fsGroup, this.hostIPC, this.hostNetwork, this.hostPID, this.hostPorts, this.privileged, this.readOnlyRootFilesystem, this.requiredDropCapabilities, this.runAsGroup, this.runAsUser, this.runtimeClass, this.seLinux, this.supplementalGroups, this.volumes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allowPrivilegeEscalation != null) {
         sb.append("allowPrivilegeEscalation:");
         sb.append(this.allowPrivilegeEscalation + ",");
      }

      if (this.allowedCSIDrivers != null && !this.allowedCSIDrivers.isEmpty()) {
         sb.append("allowedCSIDrivers:");
         sb.append(this.allowedCSIDrivers + ",");
      }

      if (this.allowedCapabilities != null && !this.allowedCapabilities.isEmpty()) {
         sb.append("allowedCapabilities:");
         sb.append(this.allowedCapabilities + ",");
      }

      if (this.allowedFlexVolumes != null && !this.allowedFlexVolumes.isEmpty()) {
         sb.append("allowedFlexVolumes:");
         sb.append(this.allowedFlexVolumes + ",");
      }

      if (this.allowedHostPaths != null && !this.allowedHostPaths.isEmpty()) {
         sb.append("allowedHostPaths:");
         sb.append(this.allowedHostPaths + ",");
      }

      if (this.allowedProcMountTypes != null && !this.allowedProcMountTypes.isEmpty()) {
         sb.append("allowedProcMountTypes:");
         sb.append(this.allowedProcMountTypes + ",");
      }

      if (this.allowedUnsafeSysctls != null && !this.allowedUnsafeSysctls.isEmpty()) {
         sb.append("allowedUnsafeSysctls:");
         sb.append(this.allowedUnsafeSysctls + ",");
      }

      if (this.defaultAddCapabilities != null && !this.defaultAddCapabilities.isEmpty()) {
         sb.append("defaultAddCapabilities:");
         sb.append(this.defaultAddCapabilities + ",");
      }

      if (this.defaultAllowPrivilegeEscalation != null) {
         sb.append("defaultAllowPrivilegeEscalation:");
         sb.append(this.defaultAllowPrivilegeEscalation + ",");
      }

      if (this.forbiddenSysctls != null && !this.forbiddenSysctls.isEmpty()) {
         sb.append("forbiddenSysctls:");
         sb.append(this.forbiddenSysctls + ",");
      }

      if (this.fsGroup != null) {
         sb.append("fsGroup:");
         sb.append(this.fsGroup + ",");
      }

      if (this.hostIPC != null) {
         sb.append("hostIPC:");
         sb.append(this.hostIPC + ",");
      }

      if (this.hostNetwork != null) {
         sb.append("hostNetwork:");
         sb.append(this.hostNetwork + ",");
      }

      if (this.hostPID != null) {
         sb.append("hostPID:");
         sb.append(this.hostPID + ",");
      }

      if (this.hostPorts != null && !this.hostPorts.isEmpty()) {
         sb.append("hostPorts:");
         sb.append(this.hostPorts + ",");
      }

      if (this.privileged != null) {
         sb.append("privileged:");
         sb.append(this.privileged + ",");
      }

      if (this.readOnlyRootFilesystem != null) {
         sb.append("readOnlyRootFilesystem:");
         sb.append(this.readOnlyRootFilesystem + ",");
      }

      if (this.requiredDropCapabilities != null && !this.requiredDropCapabilities.isEmpty()) {
         sb.append("requiredDropCapabilities:");
         sb.append(this.requiredDropCapabilities + ",");
      }

      if (this.runAsGroup != null) {
         sb.append("runAsGroup:");
         sb.append(this.runAsGroup + ",");
      }

      if (this.runAsUser != null) {
         sb.append("runAsUser:");
         sb.append(this.runAsUser + ",");
      }

      if (this.runtimeClass != null) {
         sb.append("runtimeClass:");
         sb.append(this.runtimeClass + ",");
      }

      if (this.seLinux != null) {
         sb.append("seLinux:");
         sb.append(this.seLinux + ",");
      }

      if (this.supplementalGroups != null) {
         sb.append("supplementalGroups:");
         sb.append(this.supplementalGroups + ",");
      }

      if (this.volumes != null && !this.volumes.isEmpty()) {
         sb.append("volumes:");
         sb.append(this.volumes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PodSecurityPolicySpecFluent withAllowPrivilegeEscalation() {
      return this.withAllowPrivilegeEscalation(true);
   }

   public PodSecurityPolicySpecFluent withDefaultAllowPrivilegeEscalation() {
      return this.withDefaultAllowPrivilegeEscalation(true);
   }

   public PodSecurityPolicySpecFluent withHostIPC() {
      return this.withHostIPC(true);
   }

   public PodSecurityPolicySpecFluent withHostNetwork() {
      return this.withHostNetwork(true);
   }

   public PodSecurityPolicySpecFluent withHostPID() {
      return this.withHostPID(true);
   }

   public PodSecurityPolicySpecFluent withPrivileged() {
      return this.withPrivileged(true);
   }

   public PodSecurityPolicySpecFluent withReadOnlyRootFilesystem() {
      return this.withReadOnlyRootFilesystem(true);
   }

   public class AllowedCSIDriversNested extends AllowedCSIDriverFluent implements Nested {
      AllowedCSIDriverBuilder builder;
      int index;

      AllowedCSIDriversNested(int index, AllowedCSIDriver item) {
         this.index = index;
         this.builder = new AllowedCSIDriverBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.setToAllowedCSIDrivers(this.index, this.builder.build());
      }

      public Object endAllowedCSIDriver() {
         return this.and();
      }
   }

   public class AllowedFlexVolumesNested extends AllowedFlexVolumeFluent implements Nested {
      AllowedFlexVolumeBuilder builder;
      int index;

      AllowedFlexVolumesNested(int index, AllowedFlexVolume item) {
         this.index = index;
         this.builder = new AllowedFlexVolumeBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.setToAllowedFlexVolumes(this.index, this.builder.build());
      }

      public Object endAllowedFlexVolume() {
         return this.and();
      }
   }

   public class AllowedHostPathsNested extends AllowedHostPathFluent implements Nested {
      AllowedHostPathBuilder builder;
      int index;

      AllowedHostPathsNested(int index, AllowedHostPath item) {
         this.index = index;
         this.builder = new AllowedHostPathBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.setToAllowedHostPaths(this.index, this.builder.build());
      }

      public Object endAllowedHostPath() {
         return this.and();
      }
   }

   public class FsGroupNested extends FSGroupStrategyOptionsFluent implements Nested {
      FSGroupStrategyOptionsBuilder builder;

      FsGroupNested(FSGroupStrategyOptions item) {
         this.builder = new FSGroupStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withFsGroup(this.builder.build());
      }

      public Object endFsGroup() {
         return this.and();
      }
   }

   public class HostPortsNested extends HostPortRangeFluent implements Nested {
      HostPortRangeBuilder builder;
      int index;

      HostPortsNested(int index, HostPortRange item) {
         this.index = index;
         this.builder = new HostPortRangeBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.setToHostPorts(this.index, this.builder.build());
      }

      public Object endHostPort() {
         return this.and();
      }
   }

   public class RunAsGroupNested extends RunAsGroupStrategyOptionsFluent implements Nested {
      RunAsGroupStrategyOptionsBuilder builder;

      RunAsGroupNested(RunAsGroupStrategyOptions item) {
         this.builder = new RunAsGroupStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withRunAsGroup(this.builder.build());
      }

      public Object endRunAsGroup() {
         return this.and();
      }
   }

   public class RunAsUserNested extends RunAsUserStrategyOptionsFluent implements Nested {
      RunAsUserStrategyOptionsBuilder builder;

      RunAsUserNested(RunAsUserStrategyOptions item) {
         this.builder = new RunAsUserStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withRunAsUser(this.builder.build());
      }

      public Object endRunAsUser() {
         return this.and();
      }
   }

   public class RuntimeClassNested extends RuntimeClassStrategyOptionsFluent implements Nested {
      RuntimeClassStrategyOptionsBuilder builder;

      RuntimeClassNested(RuntimeClassStrategyOptions item) {
         this.builder = new RuntimeClassStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withRuntimeClass(this.builder.build());
      }

      public Object endRuntimeClass() {
         return this.and();
      }
   }

   public class SeLinuxNested extends SELinuxStrategyOptionsFluent implements Nested {
      SELinuxStrategyOptionsBuilder builder;

      SeLinuxNested(SELinuxStrategyOptions item) {
         this.builder = new SELinuxStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withSeLinux(this.builder.build());
      }

      public Object endSeLinux() {
         return this.and();
      }
   }

   public class SupplementalGroupsNested extends SupplementalGroupsStrategyOptionsFluent implements Nested {
      SupplementalGroupsStrategyOptionsBuilder builder;

      SupplementalGroupsNested(SupplementalGroupsStrategyOptions item) {
         this.builder = new SupplementalGroupsStrategyOptionsBuilder(this, item);
      }

      public Object and() {
         return PodSecurityPolicySpecFluent.this.withSupplementalGroups(this.builder.build());
      }

      public Object endSupplementalGroups() {
         return this.and();
      }
   }
}
