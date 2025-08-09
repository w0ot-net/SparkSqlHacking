package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SecurityContextBuilder extends SecurityContextFluent implements VisitableBuilder {
   SecurityContextFluent fluent;

   public SecurityContextBuilder() {
      this(new SecurityContext());
   }

   public SecurityContextBuilder(SecurityContextFluent fluent) {
      this(fluent, new SecurityContext());
   }

   public SecurityContextBuilder(SecurityContextFluent fluent, SecurityContext instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SecurityContextBuilder(SecurityContext instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SecurityContext build() {
      SecurityContext buildable = new SecurityContext(this.fluent.getAllowPrivilegeEscalation(), this.fluent.buildAppArmorProfile(), this.fluent.buildCapabilities(), this.fluent.getPrivileged(), this.fluent.getProcMount(), this.fluent.getReadOnlyRootFilesystem(), this.fluent.getRunAsGroup(), this.fluent.getRunAsNonRoot(), this.fluent.getRunAsUser(), this.fluent.buildSeLinuxOptions(), this.fluent.buildSeccompProfile(), this.fluent.buildWindowsOptions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
