package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetBuilder extends DaemonSetFluent implements VisitableBuilder {
   DaemonSetFluent fluent;

   public DaemonSetBuilder() {
      this(new DaemonSet());
   }

   public DaemonSetBuilder(DaemonSetFluent fluent) {
      this(fluent, new DaemonSet());
   }

   public DaemonSetBuilder(DaemonSetFluent fluent, DaemonSet instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetBuilder(DaemonSet instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSet build() {
      DaemonSet buildable = new DaemonSet(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
