package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SysctlBuilder extends SysctlFluent implements VisitableBuilder {
   SysctlFluent fluent;

   public SysctlBuilder() {
      this(new Sysctl());
   }

   public SysctlBuilder(SysctlFluent fluent) {
      this(fluent, new Sysctl());
   }

   public SysctlBuilder(SysctlFluent fluent, Sysctl instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SysctlBuilder(Sysctl instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Sysctl build() {
      Sysctl buildable = new Sysctl(this.fluent.getName(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
