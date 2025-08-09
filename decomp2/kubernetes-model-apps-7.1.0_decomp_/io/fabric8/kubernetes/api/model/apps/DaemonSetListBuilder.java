package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DaemonSetListBuilder extends DaemonSetListFluent implements VisitableBuilder {
   DaemonSetListFluent fluent;

   public DaemonSetListBuilder() {
      this(new DaemonSetList());
   }

   public DaemonSetListBuilder(DaemonSetListFluent fluent) {
      this(fluent, new DaemonSetList());
   }

   public DaemonSetListBuilder(DaemonSetListFluent fluent, DaemonSetList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DaemonSetListBuilder(DaemonSetList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DaemonSetList build() {
      DaemonSetList buildable = new DaemonSetList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
