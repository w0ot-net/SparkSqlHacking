package io.fabric8.kubernetes.api.model.coordination.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseBuilder extends LeaseFluent implements VisitableBuilder {
   LeaseFluent fluent;

   public LeaseBuilder() {
      this(new Lease());
   }

   public LeaseBuilder(LeaseFluent fluent) {
      this(fluent, new Lease());
   }

   public LeaseBuilder(LeaseFluent fluent, Lease instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseBuilder(Lease instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Lease build() {
      Lease buildable = new Lease(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
