package io.fabric8.kubernetes.api.model.coordination.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaseSpecBuilder extends LeaseSpecFluent implements VisitableBuilder {
   LeaseSpecFluent fluent;

   public LeaseSpecBuilder() {
      this(new LeaseSpec());
   }

   public LeaseSpecBuilder(LeaseSpecFluent fluent) {
      this(fluent, new LeaseSpec());
   }

   public LeaseSpecBuilder(LeaseSpecFluent fluent, LeaseSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaseSpecBuilder(LeaseSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaseSpec build() {
      LeaseSpec buildable = new LeaseSpec(this.fluent.getAcquireTime(), this.fluent.getHolderIdentity(), this.fluent.getLeaseDurationSeconds(), this.fluent.getLeaseTransitions(), this.fluent.getPreferredHolder(), this.fluent.getRenewTime(), this.fluent.getStrategy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
