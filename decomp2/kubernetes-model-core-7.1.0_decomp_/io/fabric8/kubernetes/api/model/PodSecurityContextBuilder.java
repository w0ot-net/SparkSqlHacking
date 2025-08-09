package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodSecurityContextBuilder extends PodSecurityContextFluent implements VisitableBuilder {
   PodSecurityContextFluent fluent;

   public PodSecurityContextBuilder() {
      this(new PodSecurityContext());
   }

   public PodSecurityContextBuilder(PodSecurityContextFluent fluent) {
      this(fluent, new PodSecurityContext());
   }

   public PodSecurityContextBuilder(PodSecurityContextFluent fluent, PodSecurityContext instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodSecurityContextBuilder(PodSecurityContext instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodSecurityContext build() {
      PodSecurityContext buildable = new PodSecurityContext(this.fluent.buildAppArmorProfile(), this.fluent.getFsGroup(), this.fluent.getFsGroupChangePolicy(), this.fluent.getRunAsGroup(), this.fluent.getRunAsNonRoot(), this.fluent.getRunAsUser(), this.fluent.getSeLinuxChangePolicy(), this.fluent.buildSeLinuxOptions(), this.fluent.buildSeccompProfile(), this.fluent.getSupplementalGroups(), this.fluent.getSupplementalGroupsPolicy(), this.fluent.buildSysctls(), this.fluent.buildWindowsOptions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
