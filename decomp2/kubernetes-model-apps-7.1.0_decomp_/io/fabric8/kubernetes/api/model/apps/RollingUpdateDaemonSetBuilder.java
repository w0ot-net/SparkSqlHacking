package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RollingUpdateDaemonSetBuilder extends RollingUpdateDaemonSetFluent implements VisitableBuilder {
   RollingUpdateDaemonSetFluent fluent;

   public RollingUpdateDaemonSetBuilder() {
      this(new RollingUpdateDaemonSet());
   }

   public RollingUpdateDaemonSetBuilder(RollingUpdateDaemonSetFluent fluent) {
      this(fluent, new RollingUpdateDaemonSet());
   }

   public RollingUpdateDaemonSetBuilder(RollingUpdateDaemonSetFluent fluent, RollingUpdateDaemonSet instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RollingUpdateDaemonSetBuilder(RollingUpdateDaemonSet instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RollingUpdateDaemonSet build() {
      RollingUpdateDaemonSet buildable = new RollingUpdateDaemonSet(this.fluent.buildMaxSurge(), this.fluent.buildMaxUnavailable());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
