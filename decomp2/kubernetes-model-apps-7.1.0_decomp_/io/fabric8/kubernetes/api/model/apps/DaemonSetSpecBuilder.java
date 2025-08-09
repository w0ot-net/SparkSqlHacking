package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetSpecBuilder extends DaemonSetSpecFluent implements VisitableBuilder {
   DaemonSetSpecFluent fluent;

   public DaemonSetSpecBuilder() {
      this(new DaemonSetSpec());
   }

   public DaemonSetSpecBuilder(DaemonSetSpecFluent fluent) {
      this(fluent, new DaemonSetSpec());
   }

   public DaemonSetSpecBuilder(DaemonSetSpecFluent fluent, DaemonSetSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetSpecBuilder(DaemonSetSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSetSpec build() {
      DaemonSetSpec buildable = new DaemonSetSpec(this.fluent.getMinReadySeconds(), this.fluent.getRevisionHistoryLimit(), this.fluent.buildSelector(), this.fluent.buildTemplate(), this.fluent.buildUpdateStrategy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
