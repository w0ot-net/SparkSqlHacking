package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ReplicationControllerListBuilder extends ReplicationControllerListFluent implements VisitableBuilder {
   ReplicationControllerListFluent fluent;

   public ReplicationControllerListBuilder() {
      this(new ReplicationControllerList());
   }

   public ReplicationControllerListBuilder(ReplicationControllerListFluent fluent) {
      this(fluent, new ReplicationControllerList());
   }

   public ReplicationControllerListBuilder(ReplicationControllerListFluent fluent, ReplicationControllerList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ReplicationControllerListBuilder(ReplicationControllerList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ReplicationControllerList build() {
      ReplicationControllerList buildable = new ReplicationControllerList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.buildMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
