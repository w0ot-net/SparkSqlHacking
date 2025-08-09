package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSecurityPolicySpecBuilder extends PodSecurityPolicySpecFluent implements VisitableBuilder {
   PodSecurityPolicySpecFluent fluent;

   public PodSecurityPolicySpecBuilder() {
      this(new PodSecurityPolicySpec());
   }

   public PodSecurityPolicySpecBuilder(PodSecurityPolicySpecFluent fluent) {
      this(fluent, new PodSecurityPolicySpec());
   }

   public PodSecurityPolicySpecBuilder(PodSecurityPolicySpecFluent fluent, PodSecurityPolicySpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSecurityPolicySpecBuilder(PodSecurityPolicySpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSecurityPolicySpec build() {
      PodSecurityPolicySpec buildable = new PodSecurityPolicySpec(this.fluent.getAllowPrivilegeEscalation(), this.fluent.buildAllowedCSIDrivers(), this.fluent.getAllowedCapabilities(), this.fluent.buildAllowedFlexVolumes(), this.fluent.buildAllowedHostPaths(), this.fluent.getAllowedProcMountTypes(), this.fluent.getAllowedUnsafeSysctls(), this.fluent.getDefaultAddCapabilities(), this.fluent.getDefaultAllowPrivilegeEscalation(), this.fluent.getForbiddenSysctls(), this.fluent.buildFsGroup(), this.fluent.getHostIPC(), this.fluent.getHostNetwork(), this.fluent.getHostPID(), this.fluent.buildHostPorts(), this.fluent.getPrivileged(), this.fluent.getReadOnlyRootFilesystem(), this.fluent.getRequiredDropCapabilities(), this.fluent.buildRunAsGroup(), this.fluent.buildRunAsUser(), this.fluent.buildRuntimeClass(), this.fluent.buildSeLinux(), this.fluent.buildSupplementalGroups(), this.fluent.getVolumes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
